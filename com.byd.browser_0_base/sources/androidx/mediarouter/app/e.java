package androidx.mediarouter.app;

import android.view.View;
import androidx.mediarouter.app.MediaRouteDynamicControllerDialog;

/* JADX INFO: compiled from: MediaRouteDynamicControllerDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements View.OnClickListener {
    public final /* synthetic */ MediaRouteDynamicControllerDialog.h.c e;

    public e(MediaRouteDynamicControllerDialog.h.c cVar) {
        this.e = cVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0041  */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onClick(android.view.View r4) {
        /*
            r3 = this;
            androidx.mediarouter.app.MediaRouteDynamicControllerDialog$h$c r3 = r3.e
            androidx.mediarouter.app.MediaRouteDynamicControllerDialog$h r4 = androidx.mediarouter.app.MediaRouteDynamicControllerDialog.h.this
            androidx.mediarouter.app.MediaRouteDynamicControllerDialog r4 = androidx.mediarouter.app.MediaRouteDynamicControllerDialog.this
            androidx.mediarouter.media.h r4 = r4.j
            androidx.mediarouter.media.h$h r0 = r3.D
            r4.getClass()
            if (r0 == 0) goto L5c
            androidx.mediarouter.media.h.b()
            androidx.mediarouter.media.h$d r4 = androidx.mediarouter.media.h.c()
            androidx.mediarouter.media.MediaRouteProvider$e r1 = r4.s
            boolean r1 = r1 instanceof androidx.mediarouter.media.MediaRouteProvider.b
            if (r1 == 0) goto L54
            androidx.mediarouter.media.h$h r1 = r4.r
            androidx.mediarouter.media.h$h$a r1 = r1.b(r0)
            r2 = 0
            if (r1 == 0) goto L41
            androidx.mediarouter.media.MediaRouteProvider$b$b r1 = r1.a
            if (r1 == 0) goto L2f
            boolean r1 = r1.e
            if (r1 == 0) goto L2f
            r1 = 1
            goto L30
        L2f:
            r1 = r2
        L30:
            if (r1 != 0) goto L33
            goto L41
        L33:
            androidx.mediarouter.media.MediaRouteProvider$e r4 = r4.s
            androidx.mediarouter.media.MediaRouteProvider$b r4 = (androidx.mediarouter.media.MediaRouteProvider.b) r4
            java.lang.String r0 = r0.b
            java.util.List r0 = java.util.Collections.singletonList(r0)
            r4.p(r0)
            goto L48
        L41:
            java.lang.String r4 = "MediaRouter"
            java.lang.String r0 = "Ignoring attempt to transfer to a non-transferable route."
            android.util.Log.w(r4, r0)
        L48:
            android.widget.ImageView r4 = r3.z
            r0 = 4
            r4.setVisibility(r0)
            android.widget.ProgressBar r3 = r3.A
            r3.setVisibility(r2)
            return
        L54:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "There is no currently selected dynamic group route."
            r3.<init>(r4)
            throw r3
        L5c:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r4 = "route must not be null"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.e.onClick(android.view.View):void");
    }
}
