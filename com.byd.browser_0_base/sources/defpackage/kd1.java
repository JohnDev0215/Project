package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.mediarouter.app.MediaRouteChooserDialog;
import androidx.mediarouter.media.g;

/* JADX INFO: compiled from: MediaRouteChooserDialogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class kd1 extends r90 {
    public MediaRouteChooserDialog p0;
    public g q0;

    public kd1() {
        this.f0 = true;
        Dialog dialog = this.k0;
        if (dialog != null) {
            dialog.setCancelable(true);
        }
    }

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        MediaRouteChooserDialog mediaRouteChooserDialogO0 = O0(K());
        this.p0 = mediaRouteChooserDialogO0;
        N0();
        mediaRouteChooserDialogO0.f(this.q0);
        return this.p0;
    }

    public final void N0() {
        if (this.q0 == null) {
            Bundle bundle = this.j;
            if (bundle != null) {
                Bundle bundle2 = bundle.getBundle("selector");
                g gVar = null;
                if (bundle2 != null) {
                    gVar = new g(null, bundle2);
                } else {
                    g gVar2 = g.c;
                }
                this.q0 = gVar;
            }
            if (this.q0 == null) {
                this.q0 = g.c;
            }
        }
    }

    public MediaRouteChooserDialog O0(Context context) {
        return new MediaRouteChooserDialog(context);
    }

    public final void P0(g gVar) {
        if (gVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        N0();
        if (this.q0.equals(gVar)) {
            return;
        }
        this.q0 = gVar;
        Bundle bundle = this.j;
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBundle("selector", gVar.a);
        C0(bundle);
        MediaRouteChooserDialog mediaRouteChooserDialog = this.p0;
        if (mediaRouteChooserDialog != null) {
            mediaRouteChooserDialog.f(gVar);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        this.H = true;
        MediaRouteChooserDialog mediaRouteChooserDialog = this.p0;
        if (mediaRouteChooserDialog == null) {
            return;
        }
        mediaRouteChooserDialog.getWindow().setLayout(qd1.a(mediaRouteChooserDialog.getContext()), -2);
    }
}
