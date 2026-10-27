package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import b.b.g.r.u;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: BydMasTipsDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends b implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, b.b.o.b.a.a {
    public Dialog i;
    public ImageView j;
    public BydSwitch k;
    public Context l;

    public d(Context context) {
        super(context);
        this.l = context;
    }

    public boolean A() {
        Dialog dialog = this.i;
        return dialog != null && dialog.isShowing();
    }

    public void B() {
        k.a("carseat_massage_tips_mode");
        y(b.b.o.b.b.a.a(this));
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.dialog_carseat_rtl_massage_tips_layout;
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.dialog_carseat_massage_tips_layout;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.dialog_carsetting_right_carseat_massage_tips_layout;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        u.c(this.l, "massage_switch", z ? 1 : 2);
        u.c(this.l, "main_cb_tips", z ? 1 : 2);
        u.c(this.l, "passenger_cb_tips", z ? 1 : 2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.carseat_massage_close_imge) {
            u.c(this.l, "massage_dialog", 1);
            k.e("carseat_massage_tips_mode");
            l();
            z();
        }
    }

    @Override // b.b.g.q.d.b
    public void s(Dialog dialog) {
        this.k.setChecked(u.a(this.l, "massage_switch") == 1, false);
    }

    @Override // b.b.g.q.d.b
    public void t(Dialog dialog) {
        if (this.i != null) {
            u.c(this.l, "massage_dialog", 2);
        }
    }

    @Override // b.b.g.q.d.b
    public void u(Dialog dialog) {
        this.i = dialog;
        this.j = (ImageView) dialog.findViewById(R.id.carseat_massage_close_imge);
        this.k = (BydSwitch) dialog.findViewById(R.id.carseat_massage_toggle);
        b.b.a.h.l.F(this, this.j);
        b.b.a.h.l.y(this, this.k);
    }

    @Override // b.b.g.q.d.b
    public void x() {
        super.x();
        u.c(this.l, "massage_dialog", 1);
        z();
        k.e("carseat_massage_tips_mode");
        this.i = null;
    }

    public final void z() {
        b.b.a.h.l.F(this, this.j);
        b.b.a.h.l.y(this, this.k);
        this.i.setOnDismissListener(null);
    }
}
