package defpackage;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: renamed from: zr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ViewOnClickListenerC1897zr implements View.OnClickListener {
    public final Dialog s;

    public ViewOnClickListenerC1897zr(Dialog dialog) {
        this.s = dialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.s.show();
    }
}
