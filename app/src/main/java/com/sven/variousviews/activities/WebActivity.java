package com.sven.variousviews.activities;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sven.variousviews.MainActivity;
import com.sven.variousviews.R;

import java.util.HashMap;
import java.util.Set;

import static android.view.KeyEvent.KEYCODE_BACK;

public class WebActivity extends AppCompatActivity {
    private static final String TAG = "WebActivity";
    private WebView webView;
    private WebSettings webSettings;

    private TextView tv_title;
    private TextView endLoading;
    private Button bt_alert;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.wb);
        tv_title = (TextView) findViewById(R.id.web_title);
        endLoading = (TextView) findViewById(R.id.text_endLoading);
        bt_alert = (Button) findViewById(R.id.bt_alert);
        mHandler = new Handler();
//        webView = new WebView(this);
//        webView.loadUrl("http://www.baidu.com");
        webView.loadUrl("file:///android_asset/javascript1.html");
        webView.addJavascriptInterface(new AndroidtoJs(), "test");
        webSettings = webView.getSettings();

        setClient();

        initWebSettings();
        bt_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
//                        webView.loadUrl("javascript:callJS()");
                        webView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                Log.i(TAG, "onReceiveValue: " + value);
                            }
                        });
                    }
                });
            }
        });
    }

    private void setClient() {
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                // 步骤2：根据协议的参数，判断是否是所需要的url
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                Uri uri = Uri.parse(request.toString());
                Log.i(TAG, "shouldOverrideUrlLoading = "+request.toString());
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if ( uri.getScheme().equals("js")) {

                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {

                        //  步骤3：
                        // 执行JS所需要调用的逻辑
                        System.out.println("js调用了Android的方法");
                        // 可以在协议上带有参数并传递到Android上
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();

                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                endLoading.setText("结束加载!!!");
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                tv_title.setText(title);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                // 根据协议的参数，判断是否是所需要的url(原理同方式2)
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
                Log.i(TAG, "onJsPrompt = "+message);
                Uri uri = Uri.parse(message);
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if ( uri.getScheme().equals("js")) {

                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {
                        // 执行JS所需要调用的逻辑

                        // 可以在协议上带有参数并传递到Android上
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();

                        //参数result:代表消息框的返回值(输入值)
                        result.confirm("js调用了Android的方法成功啦");
                    }
                    return true;
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                return super.onJsAlert(view, url, message, result);
                AlertDialog.Builder builder = new AlertDialog.Builder(WebActivity.this).setTitle("AlertTitle")
                        .setMessage("this is alertMessage")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(WebActivity.this, "oking", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("quit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(WebActivity.this, "quiting", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();
                return true;
            }
        });
    }

    public void initWebSettings() {
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
        webSettings.setAppCachePath(getFilesDir().getAbsolutePath() + "mywebs");

        //
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        webSettings.setJavaScriptEnabled(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KEYCODE_BACK && webView.canGoBack()) {
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

    class AndroidtoJs extends Object {
        @JavascriptInterface
        public void hello(String msg) {
            Toast.makeText(WebActivity.this, msg, Toast.LENGTH_SHORT).show();
        }

    }
}
