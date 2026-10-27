package com.amazon.music.inappmessaging.ui.notification;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;
import java.io.Serializable;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public abstract class HtmlNotificationDialog extends DialogFragment {
    private static final String CLICKABLE_ID = "amznClickable";
    private Notification notification;
    private WebView webView;

    public abstract void navigateToUri(String uri, String refmarker);

    public abstract void onNotificationDismissed(String refmarker);

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Notification notification = (Notification) getArguments().getSerializable("com.amazon.music.messaging.NOTIFICATION");
        this.notification = notification;
        Validate.notNull(notification, "notification can't be null", new Object[0]);
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.html_notification_dialog);
        bindView(dialog);
        bindData();
        return dialog;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(final DialogInterface dialog) {
        super.onCancel(dialog);
        onNotificationDismissed(this.notification.dismissRefmarker);
    }

    private void bindView(final Dialog dialog) {
        this.webView = (WebView) dialog.findViewById(R.id.web_view);
    }

    private void bindData() {
        this.webView.loadUrl(this.notification.contentUrl);
        addClickableHandlers();
    }

    private void addClickableHandlers() {
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.addJavascriptInterface(new Object() { // from class: com.amazon.music.inappmessaging.ui.notification.HtmlNotificationDialog.1
            @JavascriptInterface
            public void click(String uri) {
                click(uri, null);
            }

            @JavascriptInterface
            public void click(String uri, String refmarker) {
                HtmlNotificationDialog.this.navigateToUri(uri, refmarker);
                HtmlNotificationDialog.this.dismiss();
            }

            @JavascriptInterface
            public void dismissNotification() {
                dismissNotification(null);
            }

            @JavascriptInterface
            public void dismissNotification(String refmarker) {
                HtmlNotificationDialog.this.onNotificationDismissed(refmarker);
                HtmlNotificationDialog.this.dismiss();
            }
        }, CLICKABLE_ID);
    }

    public static final class Notification implements Serializable {
        public final String contentUrl;
        public final String dismissRefmarker;

        public Notification(final String contentUrl, final String dismissRefmarker) {
            this.contentUrl = contentUrl;
            this.dismissRefmarker = dismissRefmarker;
        }
    }
}
