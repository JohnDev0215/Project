package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import defpackage.di1;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: WebApkUpdateReportAbuseDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dq3 implements di1.a {
    public final Context e;
    public final bi1 f;
    public final String g;
    public final a h;

    /* JADX INFO: compiled from: WebApkUpdateReportAbuseDialog.java */
    public interface a {
    }

    public dq3(Context context, bi1 bi1Var, String str, String str2, ke0 ke0Var) {
        this.e = context;
        this.f = bi1Var;
        this.g = str;
        this.h = ke0Var;
    }

    @Override // di1.a
    public final void b(int i) {
        if (i == 1) {
            dp3 dp3Var = (dp3) ((ke0) this.h).f;
            dp3Var.f.b(3, dp3Var.g);
            String str = this.g;
            if (!zt1.e(str)) {
                Log.i("cr_UpdateReportAbuseDlg", "WebApk not found: " + str);
            } else {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + str));
                this.e.startActivity(intent);
            }
        }
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        bi1 bi1Var = this.f;
        if (i == 0) {
            bi1Var.b(1, propertyModel);
        } else {
            if (i == 1) {
                bi1Var.b(2, propertyModel);
                return;
            }
            Log.i("cr_UpdateReportAbuseDlg", "Unexpected button pressed in dialog: " + i);
        }
    }
}
