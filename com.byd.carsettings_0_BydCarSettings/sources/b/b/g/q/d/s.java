package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.WadeProtectAdapter;

/* JADX INFO: compiled from: WadeProtectExceptionListDlg.java */
/* JADX INFO: loaded from: classes2.dex */
public class s extends b implements View.OnClickListener {
    public RecyclerView i;
    public Context j;
    public WadeProtectAdapter k;
    public Button l;

    public s(Context context) {
        super(context);
        this.j = context;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        l();
        k.e("comfortable_wading_protect_car_state_exception_item_dialog");
    }

    @Override // b.b.g.q.d.b
    public void s(Dialog dialog) {
        this.k = new WadeProtectAdapter(this.j);
        this.i.setLayoutManager(new LinearLayoutManager(this.j));
        this.i.setAdapter(this.k);
    }

    @Override // b.b.g.q.d.b
    public void t(Dialog dialog) {
        b.b.a.h.l.F(this, this.l);
    }

    @Override // b.b.g.q.d.b
    public void u(Dialog dialog) {
        this.i = (RecyclerView) dialog.findViewById(R.id.wade_protect_list_rv);
        this.l = (Button) dialog.findViewById(R.id.carsetting_dialog_btn_confirm);
    }

    @Override // b.b.g.q.d.b
    public void x() {
        WadeProtectAdapter wadeProtectAdapter = this.k;
        if (wadeProtectAdapter != null) {
            wadeProtectAdapter.a();
        }
    }

    public void z() {
        y(R.layout.carsetting_dialog_wade_protect_exception_list);
        k.a("comfortable_wading_protect_car_state_exception_item_dialog");
    }
}
