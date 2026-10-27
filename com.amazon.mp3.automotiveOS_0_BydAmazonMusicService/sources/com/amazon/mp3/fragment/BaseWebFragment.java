package com.amazon.mp3.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.store.html5.bridge.JavascriptBridge;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.web.WebEvent;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.InteractionType;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseWebFragment extends BaseFragment {
    protected ImageButton mBackButton;
    protected String mCurrentURL;
    protected JavascriptBridge mJavascriptBridge;
    protected JavascriptBridge.OnMessageReceivedListener mOnMessageReceivedListener = new JavascriptBridge.OnMessageReceivedListener() { // from class: com.amazon.mp3.fragment.BaseWebFragment.1
        @Override // com.amazon.mp3.store.html5.bridge.JavascriptBridge.OnMessageReceivedListener
        public void onMessageReceived(final Message message) {
            BaseWebFragment.this.handleMessage(message);
        }
    };
    protected ProgressBar mProgressBar;
    protected Toolbar mToolBar;
    protected RelativeLayout mVideoFrameContainer;
    protected PublishSubject<WebEvent> mWebEventPublisher;
    protected WebView mWebView;
    protected WebViewClient mWebViewClient;

    protected abstract JavascriptBridge createJavascriptBridge();

    protected abstract WebViewClient createWebViewClient();

    protected abstract void handleMessage(final Message message);

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_webview, container, false);
        this.mWebView = (WebView) viewInflate.findViewById(R.id.WebView);
        this.mVideoFrameContainer = (RelativeLayout) viewInflate.findViewById(R.id.videoFrame);
        this.mProgressBar = (ProgressBar) viewInflate.findViewById(R.id.ProgressBar);
        this.mBackButton = (ImageButton) viewInflate.findViewById(R.id.button_back_web);
        this.mToolBar = (Toolbar) viewInflate.findViewById(R.id.toolbar_web_view);
        this.mBackButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.BaseWebFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BaseWebFragment.this.mWebView.canGoBack()) {
                    BaseWebFragment.this.mWebView.goBack();
                } else {
                    BaseWebFragment.this.requireActivity().onBackPressed();
                }
            }
        });
        String url = getUrl();
        this.mCurrentURL = url;
        if (!url.contains("/campaign")) {
            this.mToolBar.setVisibility(8);
            this.mBackButton.setVisibility(8);
        }
        initWebView();
        return viewInflate;
    }

    private String getUrl() {
        WebTarget webTarget;
        return (getArguments() == null || (webTarget = (WebTarget) getArguments().getSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET")) == null || webTarget.getUrl() == null) ? "" : webTarget.getUrl();
    }

    public boolean handleBackPress() {
        if (!this.mWebView.canGoBack()) {
            return false;
        }
        this.mWebView.goBack();
        return true;
    }

    public void setPublisher(final PublishSubject<WebEvent> publisher) {
        this.mWebEventPublisher = publisher;
    }

    protected void initWebView() {
        this.mWebViewClient = createWebViewClient();
        this.mJavascriptBridge = createJavascriptBridge();
        this.mWebView.setWebViewClient(this.mWebViewClient);
        if (this.mJavascriptBridge != null) {
            this.mWebView.getSettings().setJavaScriptEnabled(true);
            this.mJavascriptBridge.attach(this.mWebView, this.mOnMessageReceivedListener);
        }
    }

    protected void sendUiPageViewMetric(final String identifier, final String tag) {
        NavigationAppEvent.Builder builderWithOrientation = NavigationAppEvent.builder(identifier).withOrientation(ScreenUtil.getScreenOrientation(getContext()));
        if (!TextUtils.isEmpty(tag)) {
            builderWithOrientation.withAssociateTag(tag);
        }
        builderWithOrientation.publish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendUiPageViewMetric(final String identifier) {
        sendUiPageViewMetric(identifier, "");
    }

    protected void sendUiClickMetric(final String identifier, final String refMarker) {
        UserInteractionAppEvent.Builder builderWithInteractionType = UserInteractionAppEvent.builder(identifier).withInteractionType(InteractionType.TAP);
        if (!TextUtils.isEmpty(refMarker)) {
            builderWithInteractionType.withRefMarker(refMarker);
        }
        builderWithInteractionType.publish();
    }

    protected void sendUiClickMetric(final String identifier) {
        sendUiClickMetric(identifier, "");
    }
}
