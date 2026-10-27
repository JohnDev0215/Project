package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: BydDibarDlg.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends b implements View.OnClickListener {
    public Button i;
    public Dialog j;
    public String k;
    public Context l;

    public c(Context context, String str) {
        super(context);
        this.k = str;
        this.l = context;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.dibar_tray_btn_confirm) {
            k.e(this.k);
            b.b.a.h.l.F(null, this.i);
            this.j.dismiss();
            this.j = null;
        }
    }

    @Override // b.b.g.q.d.b
    public void s(Dialog dialog) {
    }

    @Override // b.b.g.q.d.b
    public void t(Dialog dialog) {
        this.j = dialog;
        b.b.a.h.l.F(this, this.i);
    }

    @Override // b.b.g.q.d.b
    public void u(Dialog dialog) {
        Button button = (Button) dialog.findViewById(R.id.dibar_tray_btn_confirm);
        this.i = button;
        Context context = this.l;
        button.setTextColor(context.getColor(b.b.a.h.b.a(context, R.attr.byd_vehicle_dialog_btn_confirm_text_color)));
    }

    @Override // b.b.g.q.d.b
    public void x() {
        super.x();
        k.e(this.k);
    }

    public void z(int i) {
        k.a(this.k);
        this.j = y(i);
    }
}
