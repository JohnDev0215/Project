package defpackage;

import android.app.Activity;
import android.view.View;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: QrCodeShareCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g82 implements w72 {
    public final k82 a;
    public final i82 b;

    public g82(Activity activity, eu1 eu1Var, String str, WindowAndroid windowAndroid) {
        PropertyModel propertyModel = new PropertyModel(m82.f);
        i82 i82Var = new i82(activity, propertyModel, eu1Var, str, windowAndroid);
        this.b = i82Var;
        k82 k82Var = new k82(activity, new vt(22, i82Var));
        this.a = k82Var;
        j.b(propertyModel, k82Var, new l82());
    }

    @Override // defpackage.w72
    public final void a(WindowAndroid windowAndroid) {
        i82 i82Var = this.b;
        if (i82Var != null) {
            i82Var.c = windowAndroid;
            i82Var.a();
        }
    }

    @Override // defpackage.w72
    public final View getView() {
        return this.a.b;
    }

    @Override // defpackage.w72
    public final void onPause() {
        this.b.b.l(m82.e, false);
    }

    @Override // defpackage.w72
    public final void onResume() {
        i82 i82Var = this.b;
        i82Var.a();
        i82Var.b.l(m82.e, true);
        n3.X0("SharingQRCode.TabVisible.Share");
    }

    @Override // defpackage.w72
    public final void onDestroy() {
    }
}
