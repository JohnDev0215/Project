package com.amazon.music.explore.views.amp;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.amazon.music.explore.R;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmpPlaybackWebView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\bH\u0016J\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/explore/views/amp/AmpPlaybackWebView;", "Landroid/webkit/WebView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "bindWithUrl", "", ImagesContract.URL, "", "onPause", "setDebug", "isDebug", "", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AmpPlaybackWebView extends WebView {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AmpPlaybackWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        setLayerType(2, null);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setMediaPlaybackRequiresUserGesture(false);
        WebSettings settings = getSettings();
        settings.setUserAgentString(Intrinsics.stringPlus(settings.getUserAgentString(), "AmazonMusic"));
        setBackgroundColor(getResources().getColor(R.color.black));
    }

    public /* synthetic */ AmpPlaybackWebView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    @Override // android.webkit.WebView
    public void onPause() {
        super.onPause();
    }

    public final void bindWithUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        if (getOriginalUrl() == null || !Intrinsics.areEqual(getOriginalUrl(), url)) {
            loadUrl(url);
        }
    }

    public final void setDebug(boolean isDebug) {
        WebView.setWebContentsDebuggingEnabled(isDebug);
    }
}
