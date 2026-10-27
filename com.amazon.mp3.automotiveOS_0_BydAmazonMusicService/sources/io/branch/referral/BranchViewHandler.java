package io.branch.referral;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
public class BranchViewHandler {
    private static BranchViewHandler thisInstance_;
    private Dialog branchViewDialog_;
    private boolean isBranchViewAccepted_;
    private boolean isBranchViewDialogShowing_;
    private String parentActivityClassName_;
    private boolean webViewLoadError_;
    private BranchView openOrInstallPendingBranchView_ = null;
    private boolean loadingHtmlInBackGround_ = false;

    public interface IBranchViewEvents {
        void onBranchViewAccepted(String str, String str2);

        void onBranchViewCancelled(String str, String str2);

        void onBranchViewError(int i, String str, String str2);

        void onBranchViewVisible(String str, String str2);
    }

    private BranchViewHandler() {
    }

    public static BranchViewHandler getInstance() {
        if (thisInstance_ == null) {
            thisInstance_ = new BranchViewHandler();
        }
        return thisInstance_;
    }

    public boolean showPendingBranchView(Context context) {
        boolean zShowBranchView = showBranchView(this.openOrInstallPendingBranchView_, context, null);
        if (zShowBranchView) {
            this.openOrInstallPendingBranchView_ = null;
        }
        return zShowBranchView;
    }

    public boolean showBranchView(JSONObject jSONObject, String str, Context context, IBranchViewEvents iBranchViewEvents) {
        return showBranchView(new BranchView(jSONObject, str), context, iBranchViewEvents);
    }

    private boolean showBranchView(BranchView branchView, Context context, IBranchViewEvents iBranchViewEvents) {
        if (this.isBranchViewDialogShowing_ || this.loadingHtmlInBackGround_) {
            if (iBranchViewEvents != null) {
                iBranchViewEvents.onBranchViewError(-200, "Unable to create a Branch view. A Branch view is already showing", branchView.branchViewAction_);
            }
            return false;
        }
        this.isBranchViewDialogShowing_ = false;
        this.isBranchViewAccepted_ = false;
        if (context != null && branchView != null) {
            if (branchView.isAvailable(context)) {
                if (!TextUtils.isEmpty(branchView.webViewHtml_)) {
                    createAndShowBranchView(branchView, context, iBranchViewEvents);
                } else {
                    this.loadingHtmlInBackGround_ = true;
                    new loadBranchViewTask(branchView, context, iBranchViewEvents).execute(new Void[0]);
                }
                return true;
            }
            if (iBranchViewEvents != null) {
                iBranchViewEvents.onBranchViewError(-203, "Unable to create this Branch view. Reached maximum usage limit ", branchView.branchViewAction_);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAndShowBranchView(final BranchView branchView, Context context, final IBranchViewEvents iBranchViewEvents) {
        if (context == null || branchView == null) {
            return;
        }
        final WebView webView = new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setLayerType(2, null);
        this.webViewLoadError_ = false;
        if (TextUtils.isEmpty(branchView.webViewHtml_)) {
            return;
        }
        webView.loadDataWithBaseURL(null, branchView.webViewHtml_, "text/html", "utf-8", null);
        webView.setWebViewClient(new WebViewClient() { // from class: io.branch.referral.BranchViewHandler.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                boolean zHandleUserActionRedirect = BranchViewHandler.this.handleUserActionRedirect(str);
                if (zHandleUserActionRedirect) {
                    if (BranchViewHandler.this.branchViewDialog_ != null) {
                        BranchViewHandler.this.branchViewDialog_.dismiss();
                    }
                } else {
                    webView2.loadUrl(str);
                }
                return zHandleUserActionRedirect;
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                super.onPageStarted(webView2, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView2, int i, String str, String str2) {
                super.onReceivedError(webView2, i, str, str2);
                BranchViewHandler.this.webViewLoadError_ = true;
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                super.onPageFinished(webView2, str);
                BranchViewHandler.this.openBranchViewDialog(branchView, iBranchViewEvents, webView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openBranchViewDialog(final BranchView branchView, final IBranchViewEvents iBranchViewEvents, WebView webView) {
        if (!this.webViewLoadError_ && Branch.getInstance() != null && Branch.getInstance().currentActivityReference_ != null) {
            Activity activity = Branch.getInstance().currentActivityReference_.get();
            if (activity != null) {
                branchView.updateUsageCount(activity.getApplicationContext(), branchView.branchViewID_);
                this.parentActivityClassName_ = activity.getClass().getName();
                RelativeLayout relativeLayout = new RelativeLayout(activity);
                relativeLayout.setVisibility(8);
                relativeLayout.addView(webView, new RelativeLayout.LayoutParams(-1, -1));
                relativeLayout.setBackgroundColor(0);
                Dialog dialog = this.branchViewDialog_;
                if (dialog != null && dialog.isShowing()) {
                    if (iBranchViewEvents != null) {
                        iBranchViewEvents.onBranchViewError(-200, "Unable to create a Branch view. A Branch view is already showing", branchView.branchViewAction_);
                        return;
                    }
                    return;
                }
                Dialog dialog2 = new Dialog(activity, R.style.Theme.Black.NoTitleBar.Fullscreen);
                this.branchViewDialog_ = dialog2;
                dialog2.setContentView(relativeLayout);
                relativeLayout.setVisibility(0);
                webView.setVisibility(0);
                this.branchViewDialog_.show();
                showViewWithAlphaAnimation(relativeLayout);
                showViewWithAlphaAnimation(webView);
                this.isBranchViewDialogShowing_ = true;
                if (iBranchViewEvents != null) {
                    iBranchViewEvents.onBranchViewVisible(branchView.branchViewAction_, branchView.branchViewID_);
                }
                this.branchViewDialog_.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: io.branch.referral.BranchViewHandler.2
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        BranchViewHandler.this.isBranchViewDialogShowing_ = false;
                        BranchViewHandler.this.branchViewDialog_ = null;
                        if (iBranchViewEvents != null) {
                            if (BranchViewHandler.this.isBranchViewAccepted_) {
                                iBranchViewEvents.onBranchViewAccepted(branchView.branchViewAction_, branchView.branchViewID_);
                            } else {
                                iBranchViewEvents.onBranchViewCancelled(branchView.branchViewAction_, branchView.branchViewID_);
                            }
                        }
                    }
                });
                return;
            }
            return;
        }
        this.isBranchViewDialogShowing_ = false;
        if (iBranchViewEvents != null) {
            iBranchViewEvents.onBranchViewError(-202, "Unable to create a Branch view due to a temporary network error", branchView.branchViewAction_);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleUserActionRedirect(String str) {
        try {
            URI uri = new URI(str);
            if (!uri.getScheme().equalsIgnoreCase("branch-cta")) {
                return false;
            }
            if (uri.getHost().equalsIgnoreCase("accept")) {
                this.isBranchViewAccepted_ = true;
            } else {
                if (!uri.getHost().equalsIgnoreCase("cancel")) {
                    return false;
                }
                this.isBranchViewAccepted_ = false;
            }
            return true;
        } catch (URISyntaxException unused) {
            return false;
        }
    }

    private void showViewWithAlphaAnimation(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(500L);
        alphaAnimation.setStartOffset(10L);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setFillAfter(true);
        view.setVisibility(0);
        view.startAnimation(alphaAnimation);
    }

    public boolean markInstallOrOpenBranchViewPending(JSONObject jSONObject, String str) {
        Activity activity;
        BranchView branchView = new BranchView(jSONObject, str);
        if (Branch.getInstance().currentActivityReference_ == null || (activity = Branch.getInstance().currentActivityReference_.get()) == null || !branchView.isAvailable(activity)) {
            return false;
        }
        this.openOrInstallPendingBranchView_ = new BranchView(jSONObject, str);
        return true;
    }

    public boolean isInstallOrOpenBranchViewPending(Context context) {
        BranchView branchView = this.openOrInstallPendingBranchView_;
        return branchView != null && branchView.isAvailable(context);
    }

    private class loadBranchViewTask extends AsyncTask<Void, Void, Boolean> {
        private final BranchView branchView;
        private final IBranchViewEvents callback;
        private final Context context;

        public loadBranchViewTask(BranchView branchView, Context context, IBranchViewEvents iBranchViewEvents) {
            this.branchView = branchView;
            this.context = context;
            this.callback = iBranchViewEvents;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            int responseCode;
            int i = -1;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(this.branchView.webViewUrl_).openConnection()));
                httpURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.GET);
                httpURLConnection.connect();
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i2 = inputStream.read(bArr);
                            if (i2 == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, i2);
                        }
                        this.branchView.webViewHtml_ = byteArrayOutputStream.toString(ParserUtil.UTF_8);
                        byteArrayOutputStream.close();
                        inputStream.close();
                    } catch (Exception unused) {
                        i = responseCode;
                        responseCode = i;
                    }
                }
            } catch (Exception unused2) {
            }
            return Boolean.valueOf(responseCode == 200);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (bool.booleanValue()) {
                BranchViewHandler.this.createAndShowBranchView(this.branchView, this.context, this.callback);
            } else {
                IBranchViewEvents iBranchViewEvents = this.callback;
                if (iBranchViewEvents != null) {
                    iBranchViewEvents.onBranchViewError(-202, "Unable to create a Branch view due to a temporary network error", this.branchView.branchViewAction_);
                }
            }
            BranchViewHandler.this.loadingHtmlInBackGround_ = false;
        }
    }

    private class BranchView {
        private String branchViewAction_;
        private String branchViewID_;
        private int num_of_use_;
        private String webViewHtml_;
        private String webViewUrl_;

        private BranchView(JSONObject jSONObject, String str) {
            this.branchViewID_ = "";
            this.num_of_use_ = 1;
            this.webViewUrl_ = "";
            this.webViewHtml_ = "";
            try {
                this.branchViewAction_ = str;
                Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.BranchViewID;
                if (jSONObject.has(defines$Jsonkey.getKey())) {
                    this.branchViewID_ = jSONObject.getString(defines$Jsonkey.getKey());
                }
                Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.BranchViewNumOfUse;
                if (jSONObject.has(defines$Jsonkey2.getKey())) {
                    this.num_of_use_ = jSONObject.getInt(defines$Jsonkey2.getKey());
                }
                Defines$Jsonkey defines$Jsonkey3 = Defines$Jsonkey.BranchViewUrl;
                if (jSONObject.has(defines$Jsonkey3.getKey())) {
                    this.webViewUrl_ = jSONObject.getString(defines$Jsonkey3.getKey());
                }
                Defines$Jsonkey defines$Jsonkey4 = Defines$Jsonkey.BranchViewHtml;
                if (jSONObject.has(defines$Jsonkey4.getKey())) {
                    this.webViewHtml_ = jSONObject.getString(defines$Jsonkey4.getKey());
                }
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isAvailable(Context context) {
            int branchViewUsageCount = PrefHelper.getInstance(context).getBranchViewUsageCount(this.branchViewID_);
            int i = this.num_of_use_;
            return i > branchViewUsageCount || i == -1;
        }

        public void updateUsageCount(Context context, String str) {
            PrefHelper.getInstance(context).updateBranchViewUsageCount(str);
        }
    }

    public void onCurrentActivityDestroyed(Activity activity) {
        String str = this.parentActivityClassName_;
        if (str == null || !str.equalsIgnoreCase(activity.getClass().getName())) {
            return;
        }
        this.isBranchViewDialogShowing_ = false;
    }
}
