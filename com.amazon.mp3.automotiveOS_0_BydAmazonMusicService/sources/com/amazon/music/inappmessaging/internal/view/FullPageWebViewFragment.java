package com.amazon.music.inappmessaging.internal.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.events.EventType;
import com.amazon.music.inappmessaging.external.events.IAMEventSender;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.inappmessaging.internal.view.IamWebViewManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class FullPageWebViewFragment extends DialogFragment {
    private static final String ARGUMENT_SPLASH = "argument_splash";
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) FullPageWebViewFragment.class);
    private View.OnClickListener dismissClickListener = new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.internal.view.FullPageWebViewFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            FullPageWebViewFragment.this.dismissView(true);
        }
    };
    private boolean shouldShowEnterAnimation = true;
    private Splash splash;
    private WebView webView;

    public static FullPageWebViewFragment create(final Splash splash) {
        FullPageWebViewFragment fullPageWebViewFragment = new FullPageWebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_SPLASH, splash);
        fullPageWebViewFragment.setArguments(bundle);
        return fullPageWebViewFragment;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, 0);
        this.splash = (Splash) getArguments().getParcelable(ARGUMENT_SPLASH);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.iam_full_page_fragment_webview, container, false);
        IamWebViewManager.DismissListener dismissListener = new IamWebViewManager.DismissListener() { // from class: com.amazon.music.inappmessaging.internal.view.FullPageWebViewFragment.2
            @Override // com.amazon.music.inappmessaging.internal.view.IamWebViewManager.DismissListener
            public void onDismiss(boolean shouldEmitDismissedEvent) {
                FullPageWebViewFragment.this.dismissView(shouldEmitDismissedEvent);
            }
        };
        if (getDialog() != null) {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.music.inappmessaging.internal.view.FullPageWebViewFragment.3
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 4) {
                        if (keyEvent.getRepeatCount() == 0) {
                            IAMEventSender.withType(EventType.CAMPAIGN_DISMISSED).withIamRef(FullPageWebViewFragment.this.splash.iamRef).send();
                            Context context = FullPageWebViewFragment.this.getContext();
                            if (context != null) {
                                context.sendBroadcast(new Intent(InAppMessageBroadcasts.SPLASH_DISMISSED));
                            }
                        } else {
                            FullPageWebViewFragment.this.dismissView(false);
                        }
                    }
                    return false;
                }
            });
        }
        WebView webView = IamWebViewManager.get(getContext()).getWebView(this.splash.generatedUrl, dismissListener);
        if (webView != null) {
            LOG.debug("Attaching webView to view hierarchy");
            this.webView = webView;
            frameLayout.addView(webView, 0);
        }
        setClickListeners(frameLayout);
        return frameLayout;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        WebView webView = this.webView;
        if (webView != null) {
            ((ViewGroup) webView.getParent()).removeView(this.webView);
            this.webView = null;
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || dialog.getWindow() == null) {
            return;
        }
        if (this.shouldShowEnterAnimation) {
            dialog.getWindow().getAttributes().windowAnimations = R.style.WebViewSlideUpDownAnimation;
            this.shouldShowEnterAnimation = false;
        } else {
            dialog.getWindow().getAttributes().windowAnimations = R.style.WebViewOnlySlideDownAnimation;
        }
        dialog.getWindow().setLayout(-1, -1);
        if (this.webView == null) {
            IAMEventSender.withType(EventType.ERROR_OTHER).withIamRef(this.splash.iamRef).withDescription("webView null").send();
            LOG.error("Suppressing splash DialogFragment as webView is null for url: " + this.splash.generatedUrl);
            dismissView(true);
        }
    }

    private void setClickListeners(View root) {
        root.findViewById(R.id.full_page_webview_icon_dismiss).setOnClickListener(this.dismissClickListener);
    }

    public void dismissView(boolean shouldEmitDismissedEvent) {
        IamWebViewManager.get(getContext()).onWebViewDismissed(this.splash.generatedUrl);
        if (shouldEmitDismissedEvent) {
            IAMEventSender.withType(EventType.CAMPAIGN_DISMISSED).withIamRef(this.splash.iamRef).send();
            Context context = getContext();
            if (context != null) {
                context.sendBroadcast(new Intent(InAppMessageBroadcasts.SPLASH_DISMISSED));
            }
        }
        dismissAllowingStateLoss();
    }
}
