package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Hw extends AbstractC1491sO {
    public final Context c;
    public Dialog d;
    public MO e;

    public Hw(Context context) {
        this.c = context;
    }

    @Override // defpackage.AbstractC1491sO
    public void b(JO jo) {
        Dialog dialog = new Dialog(this.c, jo.b(AbstractC1711wO.p) ? AbstractC0622cn.E7 : AbstractC0622cn.F7);
        this.d = dialog;
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener(this) { // from class: Ew
            public final Hw s;

            {
                this.s = this;
            }

            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                this.s.c(5);
            }
        });
        this.d.setCanceledOnTouchOutside(false);
        C0683dt c0683dtX = C0683dt.x();
        try {
            ModalDialogView modalDialogView = (ModalDialogView) LayoutInflater.from(this.d.getContext()).inflate(AbstractC0425Xm.r0, (ViewGroup) null);
            c0683dtX.close();
            this.e = new MO(jo, modalDialogView, new Gw(this, null), true);
            c0683dtX = C0683dt.x();
            try {
                this.d.setContentView(modalDialogView);
                c0683dtX.close();
                try {
                    this.d.show();
                    modalDialogView.announceForAccessibility(AbstractC1491sO.d(jo));
                } catch (WindowManager.BadTokenException unused) {
                    c(9);
                }
            } finally {
                try {
                    c0683dtX.close();
                } catch (Throwable th) {
                    AbstractC0279Pk.a.a(th, th);
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    @Override // defpackage.AbstractC1491sO
    public void e(JO jo) {
        MO mo = this.e;
        if (mo != null) {
            OO oo = mo.b;
            oo.a.g(mo.d);
            this.e = null;
        }
        Dialog dialog = this.d;
        if (dialog != null) {
            dialog.dismiss();
            this.d = null;
        }
    }
}
