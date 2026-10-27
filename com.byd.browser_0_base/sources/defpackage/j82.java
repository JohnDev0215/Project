package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

/* JADX INFO: compiled from: QrCodeShareView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j82 implements View.OnClickListener {
    public final /* synthetic */ k82 e;

    public j82(k82 k82Var) {
        this.e = k82Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        k82 k82Var = this.e;
        String packageName = k82Var.a.getPackageName();
        k82Var.getClass();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(new Uri.Builder().scheme("package").opaquePart(packageName).build());
        ((Activity) k82Var.a).startActivity(intent);
    }
}
