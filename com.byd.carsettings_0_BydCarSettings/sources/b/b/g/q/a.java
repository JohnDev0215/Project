package b.b.g.q;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Window;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.ctrl.BydTimer;
import com.bydux.api.common.views.window.BydDialog;

/* JADX INFO: compiled from: BydDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f1423b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BydTimer f1424c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Dialog f1425d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1422a = getClass().getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BydDialog.BydDialogListener f1426e = null;

    public a(Context context) {
        this.f1423b = context;
    }

    public void l() {
        BydLogHelper.logV(this.f1422a, "closeDialog", null);
        Dialog dialog = this.f1425d;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.f1425d.dismiss();
            BydDialog.BydDialogListener bydDialogListener = this.f1426e;
            if (bydDialogListener != null) {
                bydDialogListener.onDialogDismiss();
            }
            TextUtils.isEmpty(n());
        }
        this.f1425d = null;
        o();
    }

    public String n() {
        return null;
    }

    public final void o() {
        BydTimer bydTimer = this.f1424c;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
    }

    public void p(BydDialog.BydDialogListener bydDialogListener) {
        this.f1426e = bydDialogListener;
    }

    public Dialog q(int i) {
        return r(i, 0, -1);
    }

    public Dialog r(int i, int i2, int i3) {
        BydLogHelper.logV(this.f1422a, "showDialog", "winType=" + i2);
        if (this.f1425d == null) {
            Dialog dialog = new Dialog(this.f1423b, R.style.byd_dialog);
            this.f1425d = dialog;
            Window window = dialog.getWindow();
            if (i2 > 0) {
                window.setType(i2);
            }
            if (i3 == 0) {
                window.setWindowAnimations(R.style.byd_dialog_anim);
            } else if (i3 > 0) {
                window.setWindowAnimations(i3);
            }
            if (window.getLayoutInflater().getFactory() == null) {
                BydLogHelper.logV(this.f1422a, "showDialog", "dialog has no factory before");
            }
            this.f1425d.setContentView(i);
        }
        this.f1425d.show();
        TextUtils.isEmpty(n());
        return this.f1425d;
    }
}
