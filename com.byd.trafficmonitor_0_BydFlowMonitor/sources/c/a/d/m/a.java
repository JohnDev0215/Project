package c.a.d.m;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemProperties;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import c.a.d.e;
import c.a.d.i;
import c.a.d.q.j;
import c.a.d.q.k;
import com.byd.trafficmonitor.model.TrafficInfo;
import com.byd.trafficmonitor.view.MyRecyclerView;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FlowWhereDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends e implements View.OnClickListener {
    public static String v = "data";
    public i A;
    public ArrayList<TrafficInfo> B;
    public b C;
    public ViewGroup w;
    public View x;
    public Button y;
    public MyRecyclerView z;

    /* JADX INFO: compiled from: FlowWhereDialog.java */
    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BydLogHelper.logD("traffic_monitor", "update rv list");
            List<TrafficInfo> listB = a.this.A.B();
            for (int i2 = 0; i2 < listB.size(); i2++) {
                TrafficInfo trafficInfo = listB.get(i2);
                if ("com.byd.browser".equals(trafficInfo.o())) {
                    if (SystemProperties.get("persist.sys.brower_status", "0").equals("1")) {
                        trafficInfo.r(TrafficInfo.f4414b);
                    } else {
                        trafficInfo.r(TrafficInfo.f4415c);
                    }
                    a.this.A.i(i2);
                    return;
                }
            }
        }
    }

    public static a w(ArrayList<TrafficInfo> arrayList) {
        a aVar = new a();
        Bundle bundle = new Bundle();
        bundle.putSerializable(v, arrayList);
        aVar.setArguments(bundle);
        return aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        e();
    }

    @Override // c.a.d.e, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        v(arguments);
        u();
    }

    @Override // c.a.d.e, androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        BydLogHelper.logD("traffic_monitor", "dialog onDismiss.");
        this.z = null;
        this.A = null;
        b.p.a.a.b(getContext()).e(this.C);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("data", this.B);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.w = viewGroup;
        viewGroup.setOnClickListener(this);
        this.x = view.findViewById(R.id.cl_dialog_container);
        Button button = (Button) view.findViewById(R.id.close);
        this.y = button;
        button.setOnClickListener(this);
        MyRecyclerView myRecyclerView = (MyRecyclerView) view.findViewById(R.id.rv_flow_where);
        this.z = myRecyclerView;
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList arrayList = (ArrayList) getArguments().getSerializable("data");
        i iVar = new i(getContext());
        this.A = iVar;
        iVar.w(arrayList);
        this.z.setAdapter(this.A);
        c.a.d.q.i.a(this.z, 0);
        new k(new j(this.z), 1.0f, 1.0f, -2.0f);
        t(this.w, this.x);
    }

    @Override // c.a.d.e
    public int q() {
        return c.a.d.s.b.b() ? R.layout.dialog_where_about_flow2_right_rudder : R.layout.dialog_where_about_flow2;
    }

    public final void u() {
        this.C = new b();
        b.p.a.a.b(getContext()).c(this.C, new IntentFilter("update_ui"));
    }

    public final void v(Bundle bundle) {
        this.B = (ArrayList) bundle.getSerializable(v);
    }
}
