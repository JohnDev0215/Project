package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.carsettings.SmartReportAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BydSmartReminderDlg.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends b implements View.OnClickListener, b.b.o.b.a.a {
    public Dialog i;
    public Context j;
    public ImageView k;
    public RecyclerView l;
    public List<Integer> m;
    public SmartReportAdapter n;
    public String o;

    public g(Context context, String str) {
        super(context);
        this.m = new ArrayList();
        this.j = context;
        this.o = str;
    }

    public final void A() {
        List<Integer> list = this.m;
        if (list != null && list.size() > 0) {
            this.m.clear();
        }
        for (int i = 0; i <= 11; i++) {
            z(i);
        }
    }

    public boolean B() {
        Dialog dialog = this.i;
        return dialog != null && dialog.isShowing();
    }

    public void C() {
        SmartReportAdapter smartReportAdapter = this.n;
        if (smartReportAdapter != null) {
            smartReportAdapter.notifyDataSetChanged();
        }
    }

    public void D() {
        this.i = y(b.b.o.b.b.a.a(this));
        k.a(this.o);
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.smart_reminder_dialog;
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.smart_reminder_dialog;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.smart_reminder_right_dialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.smart_reminder_close) {
            return;
        }
        List<Integer> list = this.m;
        if (list != null && list.size() > 0) {
            this.m.clear();
        }
        Dialog dialog = this.i;
        if (dialog != null) {
            dialog.dismiss();
            k.e(this.o);
            b.b.a.h.l.F(null, this.k);
            p(null);
            this.i = null;
        }
    }

    @Override // b.b.g.q.d.b
    public void s(Dialog dialog) {
        A();
        this.n = new SmartReportAdapter(this.m, this.j);
        this.l.setLayoutManager(new LinearLayoutManager(this.j, 1, false));
        this.l.setAdapter(this.n);
        this.l.setOverScrollMode(2);
    }

    @Override // b.b.g.q.d.b
    public void t(Dialog dialog) {
        this.i = dialog;
        b.b.a.h.l.F(this, this.k);
    }

    @Override // b.b.g.q.d.b
    public void u(Dialog dialog) {
        this.k = (ImageView) dialog.findViewById(R.id.smart_reminder_close);
        this.l = (RecyclerView) dialog.findViewById(R.id.smart_report_recyclerview);
    }

    @Override // b.b.g.q.d.b
    public void x() {
        super.x();
        k.e(this.o);
    }

    public final void z(int i) {
        if (i == 1) {
            if (BYDAutoBodyworkDevice.getInstance(b.b.a.a.a.b().a()).hasMessage(43) == 1) {
                this.m.add(Integer.valueOf(i));
                return;
            }
            return;
        }
        if (i == 4) {
            int iF = b.b.g.n.q.g.g().f();
            if (iF < 1 || iF > 7) {
                return;
            }
            this.m.add(Integer.valueOf(i));
            return;
        }
        if (i == 7) {
            boolean z = BYDAutoBodyworkDevice.getInstance(b.b.a.a.a.b().a()).hasMessage(1) == 1;
            if (b.b.g.n.q.b.c().g() || !z) {
                return;
            }
            this.m.add(Integer.valueOf(i));
            return;
        }
        if (i == 8) {
            if (BYDAutoBodyworkDevice.getInstance(b.b.a.a.a.b().a()).hasMessage(48) != 1 || b.b.g.n.q.b.c().g()) {
                return;
            }
            this.m.add(Integer.valueOf(i));
            return;
        }
        if (i != 10) {
            if (i != 11) {
                this.m.add(Integer.valueOf(i));
            }
        } else if (new b.b.g.d.c().b()) {
            this.m.add(Integer.valueOf(i));
        }
    }
}
