package com.amazon.music.inappmessaging.internal.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.internal.model.Splash;

/* JADX INFO: loaded from: classes4.dex */
public class FullPageWebViewActivity extends AppCompatActivity {
    private static final String ARGUMENT_SPLASH = "argument_splash";
    private final View.OnClickListener dismissClickListener = new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.internal.view.FullPageWebViewActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            FullPageWebViewActivity.this.finish();
        }
    };

    public static Intent create(final Context context, final Splash splash) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_SPLASH, splash);
        Intent intent = new Intent(context, (Class<?>) FullPageWebViewActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Splash splash = (Splash) getIntent().getExtras().getParcelable(ARGUMENT_SPLASH);
        setContentView(R.layout.iam_full_page_activity_webview);
        WebView webView = (WebView) findViewById(R.id.iam_full_page_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        setClickListeners();
        webView.loadUrl(splash.url);
    }

    private void setClickListeners() {
        findViewById(R.id.full_page_webview_icon_dismiss).setOnClickListener(this.dismissClickListener);
    }
}
