package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

/* JADX INFO: compiled from: QrCodeScanView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d82 implements View.OnClickListener {
    public final /* synthetic */ Context e;
    public final /* synthetic */ c82 f;

    public d82(c82 c82Var, Activity activity) {
        this.f = c82Var;
        this.e = activity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Context context = this.e;
        String packageName = context.getPackageName();
        this.f.getClass();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(new Uri.Builder().scheme("package").opaquePart(packageName).build());
        ((Activity) context).startActivity(intent);
    }
}
