package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: AutofillUiUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class mb implements ComponentCallbacks {
    public final /* synthetic */ PopupWindow e;

    public mb(PopupWindow popupWindow) {
        this.e = popupWindow;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        PopupWindow popupWindow;
        if (configuration.orientation != 2 || (popupWindow = this.e) == null) {
            return;
        }
        popupWindow.dismiss();
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
