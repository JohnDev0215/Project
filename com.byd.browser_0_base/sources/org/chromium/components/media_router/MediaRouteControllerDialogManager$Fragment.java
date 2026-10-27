package org.chromium.components.media_router;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.mediarouter.app.MediaRouteControllerDialog;
import defpackage.od1;
import org.chromium.components.media_router.a;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteControllerDialogManager$Fragment extends od1 {
    public final a.C0132a r0;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            MediaRouteControllerDialogManager$Fragment.this.I0(false, false);
        }
    }

    public MediaRouteControllerDialogManager$Fragment() {
        Handler handler = new Handler();
        this.r0 = new a.C0132a();
        handler.post(new a());
    }

    @Override // defpackage.od1
    public final MediaRouteControllerDialog N0(Context context) {
        MediaRouteControllerDialog mediaRouteControllerDialog = new MediaRouteControllerDialog(context);
        mediaRouteControllerDialog.setCanceledOnTouchOutside(true);
        return mediaRouteControllerDialog;
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void o0() {
        FragmentActivity fragmentActivityD = D();
        a.C0132a c0132a = this.r0;
        c0132a.getClass();
        int systemUiVisibility = ((FrameLayout) fragmentActivityD.getWindow().getDecorView()).getSystemUiVisibility();
        c0132a.a = systemUiVisibility;
        c0132a.b = (systemUiVisibility & 1024) != 0;
        super.o0();
    }

    @Override // defpackage.r90, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
    }

    @Override // defpackage.od1, defpackage.r90, androidx.fragment.app.Fragment
    public final void p0() {
        super.p0();
        this.r0.a(D());
    }
}
