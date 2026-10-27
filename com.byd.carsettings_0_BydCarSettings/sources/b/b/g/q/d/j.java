package b.b.g.q.d;

import android.animation.AnimatorInflater;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import b.b.g.r.u;
import b.b.g.r.z;
import com.byd.carsettings.R;
import com.byd.carsettings.WadeProtectRiskActivity;

/* JADX INFO: compiled from: BydWPConfirmDlg.java */
/* JADX INFO: loaded from: classes2.dex */
public class j extends b implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, b.b.o.b.a.a {
    public CheckBox i;
    public Button j;
    public Button k;
    public TextView l;
    public Context m;
    public r n;

    public j(Context context) {
        super(context);
        this.m = context;
    }

    public void A(r rVar) {
        this.n = rVar;
    }

    public void B() {
        y(b.b.o.b.b.a.a(this));
        k.a("comfortable_wading_protect_read_dialog");
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.dialog_wade_protect_risk_config_confirm;
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.dialog_wade_protect_risk_config_confirm;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.dialog_right_wade_protect_risk_config_confirm;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            u.c(b.b.a.a.a.b().a(), "wade_protect_checkbox_state", 1);
            this.j.setAlpha(1.0f);
            this.j.setStateListAnimator(AnimatorInflater.loadStateListAnimator(b.b.a.a.a.b().a(), R.drawable.anim_btn_scale_alpha_enable));
        } else {
            u.c(b.b.a.a.a.b().a(), "wade_protect_checkbox_state", 2);
            this.j.setAlpha(0.5f);
            this.j.setStateListAnimator(null);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.carsetting_dialog_btn_cancel /* 2131296657 */:
                l();
                u.c(b.b.a.a.a.b().a(), "wade_protect_checkbox_state", 2);
                x();
                break;
            case R.id.carsetting_dialog_btn_confirm /* 2131296658 */:
                z();
                break;
            case R.id.comfortable_wading_protect_risk_tips /* 2131296781 */:
                Intent intent = new Intent();
                intent.setClass(this.m, WadeProtectRiskActivity.class);
                this.m.startActivity(intent);
                break;
        }
    }

    @Override // b.b.g.q.d.b
    public void s(Dialog dialog) {
        boolean z = u.b(b.b.a.a.a.b().a(), "wade_protect_checkbox_state", 2) == 1;
        this.i.setChecked(z);
        if (z) {
            this.j.setAlpha(1.0f);
            this.j.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this.m, R.drawable.anim_btn_scale_alpha_enable));
        } else {
            this.j.setAlpha(0.5f);
            this.j.setStateListAnimator(null);
        }
    }

    @Override // b.b.g.q.d.b
    public void t(Dialog dialog) {
        b.b.a.h.l.E(this, this.i);
        b.b.a.h.l.F(this, this.j, this.k, this.l);
    }

    @Override // b.b.g.q.d.b
    public void u(Dialog dialog) {
        this.i = (CheckBox) dialog.findViewById(R.id.comfortable_wading_protect_cb);
        this.j = (Button) dialog.findViewById(R.id.carsetting_dialog_btn_confirm);
        this.k = (Button) dialog.findViewById(R.id.carsetting_dialog_btn_cancel);
        this.l = (TextView) dialog.findViewById(R.id.comfortable_wading_protect_risk_tips);
    }

    @Override // b.b.g.q.d.b
    public void w() {
        A(null);
        u.c(this.m, "wade_protect_checkbox_state", 2);
    }

    @Override // b.b.g.q.d.b
    public void x() {
        k.e("comfortable_wading_protect_read_dialog");
    }

    public final void z() {
        if (!this.i.isChecked()) {
            Context context = this.m;
            z.c(context, context.getString(R.string.vehiclesettings_comfortable_wading_protection_risk_read_and_confirm).concat(this.m.getString(R.string.vehiclesettings_comfortable_wading_protection_risk_title)));
            return;
        }
        b.b.g.n.q.m.p0().u2(7);
        b.b.g.r.e.i(50008);
        r rVar = this.n;
        if (rVar != null) {
            rVar.q(true);
        }
        x();
        u.c(this.m, "wade_protect_checkbox_state", 2);
        u.c(this.m, "wade_protect_risk_dialog_show", 1);
        l();
    }
}
