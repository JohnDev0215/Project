package defpackage;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.view.WindowManager;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.androidoverlay.DialogOverlayImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CA implements Runnable {
    public final /* synthetic */ AA s;
    public final /* synthetic */ Context t;
    public final /* synthetic */ C1211nI u;
    public final /* synthetic */ boolean v;
    public final /* synthetic */ DialogOverlayImpl w;

    public CA(DialogOverlayImpl dialogOverlayImpl, AA aa, Context context, C1211nI c1211nI, boolean z) {
        this.w = dialogOverlayImpl;
        this.s = aa;
        this.t = context;
        this.u = c1211nI;
        this.v = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        AA aa = this.s;
        Context context = this.t;
        C1211nI c1211nI = this.u;
        JA ja = this.w.w;
        boolean z = this.v;
        Objects.requireNonNull(aa);
        aa.f = Looper.myLooper();
        aa.a = ja;
        aa.e = z;
        Dialog dialog = new Dialog(context, R.style.Theme.NoDisplay);
        aa.b = dialog;
        dialog.requestWindowFeature(1);
        aa.b.setCancelable(false);
        boolean z2 = c1211nI.d;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 51;
        layoutParams.type = aa.e ? 1000 : 1001;
        layoutParams.flags = 568;
        if (z2) {
            layoutParams.flags = 8760;
        }
        try {
            layoutParams.getClass().getField("privateFlags").set(layoutParams, Integer.valueOf(((Integer) layoutParams.getClass().getField("privateFlags").get(layoutParams)).intValue() | 64));
        } catch (ExceptionInInitializerError | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NullPointerException | SecurityException unused) {
        }
        aa.d = layoutParams;
        aa.b(c1211nI.c);
        PostTask.a(AbstractC0538bE.a, new BA(this), 0L);
    }
}
