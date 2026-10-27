package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.mediarouter.app.MediaRouteControllerDialog;
import androidx.mediarouter.media.g;

/* JADX INFO: compiled from: MediaRouteControllerDialogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class od1 extends r90 {
    public MediaRouteControllerDialog p0;
    public g q0;

    public od1() {
        this.f0 = true;
        Dialog dialog = this.k0;
        if (dialog != null) {
            dialog.setCancelable(true);
        }
    }

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        MediaRouteControllerDialog mediaRouteControllerDialogN0 = N0(K());
        this.p0 = mediaRouteControllerDialogN0;
        return mediaRouteControllerDialogN0;
    }

    public MediaRouteControllerDialog N0(Context context) {
        return new MediaRouteControllerDialog(context);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        this.H = true;
        MediaRouteControllerDialog mediaRouteControllerDialog = this.p0;
        if (mediaRouteControllerDialog != null) {
            mediaRouteControllerDialog.s();
        }
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public void p0() {
        super.p0();
        MediaRouteControllerDialog mediaRouteControllerDialog = this.p0;
        if (mediaRouteControllerDialog != null) {
            mediaRouteControllerDialog.k(false);
        }
    }
}
