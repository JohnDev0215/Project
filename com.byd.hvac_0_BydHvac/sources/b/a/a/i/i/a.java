package b.a.a.i.i;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.byd.hvac.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Dialog f912a;

    public void a() {
        try {
            Dialog dialog = this.f912a;
            if (dialog != null && dialog.isShowing()) {
                this.f912a.dismiss();
            }
        } catch (RuntimeException e) {
            BydLogHelper.logD("CarDialog", "---dismiss--mDialog--e = " + e.toString());
        }
        this.f912a = null;
    }

    public boolean b() {
        Dialog dialog = this.f912a;
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    public void c(Activity activity, View view, int i, int i2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.dialog);
        builder.setView(view);
        AlertDialog alertDialogCreate = builder.create();
        this.f912a = alertDialogCreate;
        alertDialogCreate.getWindow().setWindowAnimations(-1);
        this.f912a.setCanceledOnTouchOutside(false);
        this.f912a.show();
        this.f912a.getWindow().setLayout(i, i2);
        this.f912a.getWindow().setGravity(17);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        Dialog dialog = this.f912a;
        if (dialog != null) {
            dialog.setOnDismissListener(onDismissListener);
        }
    }
}
