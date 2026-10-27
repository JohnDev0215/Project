package defpackage;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.security.NetworkSecurityPolicy;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: renamed from: nt, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1240nt {
    public static PointerIcon a(Bitmap bitmap, float f, float f2) {
        return PointerIcon.create(bitmap, f, f2);
    }

    public static JobInfo b(JobScheduler jobScheduler, int i) {
        return jobScheduler.getPendingJob(i);
    }

    public static boolean c(String str) {
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }

    public static PointerIcon d(View view, MotionEvent motionEvent, int i) {
        return view.onResolvePointerIcon(motionEvent, i);
    }

    public static void e(MediaCodec.CryptoInfo cryptoInfo, int i, int i2) {
        cryptoInfo.setPattern(new MediaCodec.CryptoInfo.Pattern(i, i2));
    }

    public static void f(View view, PointerIcon pointerIcon) {
        view.setPointerIcon(pointerIcon);
    }

    public static void g(Activity activity, boolean z, ComponentName componentName) throws PackageManager.NameNotFoundException {
        activity.setVrModeEnabled(z, componentName);
    }

    public static boolean h(WebViewClient webViewClient, WebView webView, WebResourceRequest webResourceRequest) {
        return webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    public static boolean i(View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i) {
        return view.startDragAndDrop(clipData, dragShadowBuilder, obj, i);
    }
}
