package com.sven.variousviews.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.sven.variousviews.R;
import com.sven.variousviews.activities.webs.MyWebChromeClient;
import com.sven.variousviews.activities.webs.MyWebViewClient;

import static android.view.KeyEvent.KEYCODE_BACK;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings webSettings;

    private TextView tv_title;
    private TextView endLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.wb);
        tv_title = (TextView) findViewById(R.id.web_title);
        endLoading = (TextView) findViewById(R.id.text_endLoading);

//        webView = new WebView(this);
        webView.loadUrl("http://www.baidu.com");

        webSettings = webView.getSettings();
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                tv_title.setText(title);
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                endLoading.setText("结束加载!!!");
            }
        });
        initWebSettings();
    }

    public void initWebSettings(){
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setPluginsE
        // 自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        // 缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        //缓存
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCachePath(getFilesDir().getAbsolutePath()+"mywebs");

        //
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        webSettings.setJavaScriptEnabled(false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        webSettings.setJavaScriptEnabled(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       if (keyCode == KEYCODE_BACK && webView.canGoBack()){
           webView.goBack();
           return true;
       }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
    }
}
