package com.example.hotfix.hotfixapplication.ui.mvp.view.fragment;
import com.example.hotfix.hotfixapplication.R;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWebView extends BaseFragment{

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.webview)
    WebView webview;
    int count = 0;
    private String url;
    String titlestr;
    View view;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_web, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void setJsSupport() {
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                count++;
                progressBar.setProgress(1);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                webview.setVisibility(View.GONE);
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (progressBar != null) {
                    progressBar.setProgress(newProgress);
                }
                if (newProgress == 100) {
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String t) {
                super.onReceivedTitle(view, t);
                if (t != null ) {
                }
            }
        });
        WebSettings s = webview.getSettings();
        s.setBuiltInZoomControls(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setSavePassword(true);
        s.setSaveFormData(true);
        s.setJavaScriptEnabled(true); // enable navigator.geolocation

    }


    public void loadData() {
        Bundle bundle = getArguments();
        url = bundle.getString("url");
        if (bundle.containsKey("title")) {

            titlestr = bundle.getString("title");
        }
        setJsSupport();
        webview.loadUrl(url);
    }


    private void initData() {
        webview.getSettings().setJavaScriptEnabled(true);
        progressBar.setVisibility(View.GONE);
        loadData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
