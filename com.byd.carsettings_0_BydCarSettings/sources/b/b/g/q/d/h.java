package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.view.pickview.BydPickerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BydTimeChooseDlg.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends b.b.g.q.d.b implements View.OnClickListener {
    public Dialog i;
    public List<String> j;
    public BydPickerView k;
    public Button l;
    public Button m;
    public String n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public BydPickerView.b t;
    public Context u;
    public b v;
    public int w;

    /* JADX INFO: compiled from: BydTimeChooseDlg.java */
    public class a extends BydPickerView.b {
        public a() {
        }

        @Override // com.byd.carsettings.ui.view.pickview.BydPickerView.b
        public int b() {
            return h.this.j.size();
        }

        @Override // com.byd.carsettings.ui.view.pickview.BydPickerView.b
        public String c(int i) {
            k.f1464b.put(h.this.n, Integer.valueOf(h.this.k.getSelectedItemPosition()));
            return (String) h.this.j.get(i);
        }
    }

    /* JADX INFO: compiled from: BydTimeChooseDlg.java */
    public interface b {
        void a(int i);

        void onCancel();
    }

    public h(Context context, int i, int i2, int i3) {
        super(context);
        this.j = new ArrayList();
        this.q = 12;
        this.r = 1;
        this.s = 1;
        this.o = i;
        this.p = i2;
        this.u = context;
        this.w = i3;
    }

    public final void C() {
        b.b.a.h.l.F(null, this.l, this.m);
        p(null);
        if (this.v != null) {
            this.v = null;
        }
    }

    public final void D(int i, int i2) {
        if (i == 2) {
            this.r = 1;
            this.q = 24;
            this.s = 1;
        } else {
            this.r = 1;
            this.q = 12;
            this.s = 1;
        }
        List<String> list = this.j;
        if (list != null && list.size() > 0) {
            this.j.clear();
        }
        for (int i3 = 0; i3 <= (this.q - this.r) / this.s; i3++) {
            this.j.add((this.r + (this.s * i3)) + "");
        }
        BydPickerView.b bVar = this.t;
        if (bVar == null) {
            this.t = new a();
        } else {
            bVar.d();
        }
        BydPickerView bydPickerView = this.k;
        if (bydPickerView != null) {
            bydPickerView.setAdapter(this.t);
            this.k.setUnitText(BydApp.getContext().getString(R.string.vehiclesettings_compression_refrigerator_work_delay_power_off_set_unit));
            int i4 = this.w;
            if (i4 != -1) {
                this.k.setSelectedItemPosition(i4, false);
            } else {
                this.k.setSelectedItemPosition(i2 - 1, false);
            }
            b.b.a.h.a.b(this, "speed unit is : " + i2 + " unit value is : " + i);
        }
    }

    public final void E(int i) {
        int i2 = i + 1;
        List<String> list = this.j;
        if (list != null && list.size() > 1) {
            if (this.p == 2) {
                b.b.g.n.q.m.p0().k2(i2);
            } else {
                b.b.g.n.q.m.p0().l2(i2);
            }
        }
        b bVar = this.v;
        if (bVar != null) {
            bVar.a(i2);
        }
    }

    public Dialog F(String str, int i, int i2, int i3) {
        this.o = i;
        this.p = i2;
        this.w = i3;
        int iC = b.b.o.b.b.a.c();
        if (iC == 0 || iC == 1) {
            this.i = y(R.layout.dialog_instrument_layout);
        } else {
            this.i = y(R.layout.dialog_right_instrument_layout);
        }
        this.n = str;
        k.a(str);
        return this.i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Dialog dialog;
        if (view.getId() == R.id.carsetting_dialog_btn_confirm) {
            E(this.k.getSelectedItemPosition());
            k.e(this.n);
            Dialog dialog2 = this.i;
            if (dialog2 == null || !dialog2.isShowing()) {
                return;
            }
            this.i.dismiss();
            C();
            return;
        }
        if (view.getId() == R.id.carsetting_dialog_btn_cancel && (dialog = this.i) != null && dialog.isShowing()) {
            b bVar = this.v;
            if (bVar != null) {
                bVar.onCancel();
            }
            k.e(this.n);
            this.i.dismiss();
            C();
        }
    }

    @Override // b.b.g.q.d.b
    public void s(Dialog dialog) {
        D(this.p, this.o);
    }

    public void setOnSpeedSelectedListener(b bVar) {
        this.v = bVar;
    }

    @Override // b.b.g.q.d.b
    public void t(Dialog dialog) {
        b.b.a.h.l.F(this, this.l, this.m);
    }

    @Override // b.b.g.q.d.b
    public void u(Dialog dialog) {
        this.k = (BydPickerView) dialog.findViewById(R.id.instrument_wheelView_h);
        this.l = (Button) dialog.findViewById(R.id.carsetting_dialog_btn_confirm);
        this.m = (Button) dialog.findViewById(R.id.carsetting_dialog_btn_cancel);
    }

    @Override // b.b.g.q.d.b
    public void x() {
        super.x();
        k.e(this.n);
    }
}
