package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import b.b.g.r.u;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: BydMoreVoiceSetDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends b implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public String i;
    public Context j;
    public Dialog k;
    public ImageView l;
    public BydSwitch m;
    public BydSwitch n;
    public BydSwitch o;
    public BydSwitch p;
    public b.b.g.n.o.b q;

    /* JADX INFO: compiled from: BydMoreVoiceSetDialog.java */
    public class a implements b.b.g.n.o.c {
        public a() {
        }

        @Override // b.b.g.n.o.c
        public void a(String str, int i) {
            str.hashCode();
            switch (str) {
                case "byd_take_photos":
                    f.this.p.setChecked(i == 1);
                    break;
                case "byd_bluetooth_dial":
                    f.this.n.setChecked(i == 1);
                    break;
                case "byd_screen_shot":
                    f.this.o.setChecked(i == 1);
                    break;
                case "byd_idler_wheel":
                    f.this.m.setChecked(i == 1);
                    break;
            }
        }
    }

    public f(Context context, String str) {
        super(context);
        this.j = context;
        this.i = str;
    }

    public boolean D() {
        Dialog dialog = this.k;
        return dialog != null && dialog.isShowing();
    }

    public void E() {
        this.k = y(R.layout.smart_reminder_more_voice_dialog);
        k.a(this.i);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.aircondition_roll_voice_sw /* 2131296359 */:
                u.c(this.j, "byd_idler_wheel", z ? 1 : 0);
                break;
            case R.id.bluetooth_dial_voice_sw /* 2131296442 */:
                u.c(this.j, "byd_bluetooth_dial", z ? 1 : 0);
                break;
            case R.id.screenshoot_voice_sw /* 2131298081 */:
                u.c(this.j, "byd_screen_shot", z ? 1 : 0);
                break;
            case R.id.take_photo_voice_sw /* 2131298299 */:
                u.c(this.j, "byd_take_photos", z ? 1 : 0);
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Dialog dialog;
        if (view.getId() != R.id.smart_reminder_close || (dialog = this.k) == null) {
            return;
        }
        dialog.dismiss();
        k.e(this.i);
        b.b.a.h.l.F(null, this.l);
        b.b.g.n.o.b bVar = this.q;
        if (bVar != null) {
            bVar.b();
        }
        p(null);
        this.k = null;
    }

    @Override // b.b.g.q.d.b
    public void s(Dialog dialog) {
        this.m.setChecked(u.a(this.j, "byd_idler_wheel") == 1, false);
        this.n.setChecked(u.a(this.j, "byd_bluetooth_dial") == 1, false);
        this.o.setChecked(u.a(this.j, "byd_screen_shot") == 1, false);
        this.p.setChecked(u.a(this.j, "byd_take_photos") == 1, false);
    }

    @Override // b.b.g.q.d.b
    public void t(Dialog dialog) {
        b.b.g.n.o.b bVar = new b.b.g.n.o.b(this.j);
        this.q = bVar;
        bVar.a(new a(), "byd_take_photos", "byd_screen_shot", "byd_idler_wheel", "byd_bluetooth_dial");
    }

    @Override // b.b.g.q.d.b
    public void u(Dialog dialog) {
        this.l = (ImageView) dialog.findViewById(R.id.smart_reminder_close);
        this.m = (BydSwitch) dialog.findViewById(R.id.aircondition_roll_voice_sw);
        this.n = (BydSwitch) dialog.findViewById(R.id.bluetooth_dial_voice_sw);
        this.o = (BydSwitch) dialog.findViewById(R.id.screenshoot_voice_sw);
        BydSwitch bydSwitch = (BydSwitch) dialog.findViewById(R.id.take_photo_voice_sw);
        this.p = bydSwitch;
        b.b.a.h.l.y(this, this.m, this.n, this.o, bydSwitch);
        b.b.a.h.l.F(this, this.l);
    }

    @Override // b.b.g.q.d.b
    public void x() {
        super.x();
        b.b.g.n.o.b bVar = this.q;
        if (bVar != null) {
            bVar.b();
        }
        k.e(this.i);
    }
}
