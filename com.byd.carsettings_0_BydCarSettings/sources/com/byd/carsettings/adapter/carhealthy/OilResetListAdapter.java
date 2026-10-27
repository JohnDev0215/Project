package com.byd.carsettings.adapter.carhealthy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.n.q.g;
import b.b.g.q.d.l;
import b.b.g.q.d.m;
import b.b.g.r.z;
import com.byd.carsettings.R;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OilResetListAdapter extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2632a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.g.n.g.b f2633b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<b.b.g.m.c.a> f2634c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public l f2635d;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.g.m.c.a f2636a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.ViewHolder f2637b;

        public a(b.b.g.m.c.a aVar, RecyclerView.ViewHolder viewHolder) {
            this.f2636a = aVar;
            this.f2637b = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2636a.f888c == 0) {
                m.f1481c.put("ConsumptionManageFragment_label_oil_record_reset", Integer.valueOf(this.f2637b.getAdapterPosition()));
                OilResetListAdapter.this.g(this.f2637b.getAdapterPosition());
            }
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2639a;

        public b(int i) {
            this.f2639a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z = false;
            if (!b.b.g.n.q.b.c().g() ? g.g().c() == 1 : g.g().b() == 1) {
                z = true;
            }
            if (!z) {
                z.b(OilResetListAdapter.this.f2632a, R.string.carhealthy_oil_p);
                return;
            }
            OilResetListAdapter.this.f2635d.f();
            OilResetListAdapter.this.f2633b.o(((b.b.g.m.c.a) OilResetListAdapter.this.f2634c.get(this.f2639a)).f889d);
            ((b.b.g.m.c.a) OilResetListAdapter.this.f2634c.get(this.f2639a)).f888c = 1;
            OilResetListAdapter.this.notifyItemChanged(this.f2639a);
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OilResetListAdapter.this.f2635d.f();
        }
    }

    public static class d extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2642a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2643b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public LinearLayout f2644c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f2645d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ProgressBar f2646e;

        public d(View view) {
            super(view);
            this.f2642a = (TextView) view.findViewById(R.id.tv_oil_item);
            this.f2643b = (TextView) view.findViewById(R.id.tv_oil_percent);
            this.f2644c = (LinearLayout) view.findViewById(R.id.ll_reset);
            this.f2645d = (TextView) view.findViewById(R.id.tv_reset);
            this.f2646e = (ProgressBar) view.findViewById(R.id.progressbar_resetting);
        }
    }

    public OilResetListAdapter(Context context, b.b.g.n.g.b bVar) {
        this.f2632a = context;
        this.f2633b = bVar;
    }

    public void f(List<b.b.g.m.c.a> list) {
        this.f2634c = list;
        notifyDataSetChanged();
    }

    public void g(int i) {
        String string = this.f2634c.get(i).f889d == 0 ? this.f2632a.getResources().getString(R.string.carhealthy_oil_engine_reset) : this.f2634c.get(i).f889d == 1 ? this.f2632a.getResources().getString(R.string.carhealthy_oil_transmission_reset) : this.f2634c.get(i).f889d == 2 ? this.f2632a.getResources().getString(R.string.carhealthy_oil_brake_reset) : this.f2634c.get(i).f889d == 3 ? this.f2632a.getResources().getString(R.string.carhealthy_oil_blade_battery_reset) : this.f2634c.get(i).f889d == 4 ? this.f2632a.getResources().getString(R.string.carhealthy_oil_motor_elec_reset) : "";
        View viewInflate = LayoutInflater.from(this.f2632a).inflate(R.layout.dialog_carhealthy_oillife_reset, (ViewGroup) null);
        Context context = this.f2632a;
        l lVarJ = m.j(context, "ConsumptionManageFragment_label_oil_record_reset", string, context.getResources().getString(R.string.dilink_reset), this.f2632a.getResources().getString(R.string.dialog_btn_cancel), new b(i), new c(), false, true, viewInflate);
        this.f2635d = lVarJ;
        lVarJ.C();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<b.b.g.m.c.a> list = this.f2634c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        d dVar = (d) viewHolder;
        b.b.g.m.c.a aVar = this.f2634c.get(i);
        dVar.f2642a.setText(aVar.f887b);
        TextView textView = dVar.f2643b;
        StringBuilder sb = new StringBuilder();
        int i2 = aVar.f886a;
        sb.append(i2 == -999 ? "--" : Integer.valueOf(i2));
        sb.append("%");
        textView.setText(sb.toString());
        boolean z = aVar.f888c == 1;
        dVar.f2645d.setVisibility(z ? 8 : 0);
        dVar.f2646e.setVisibility(z ? 0 : 8);
        dVar.f2644c.setOnClickListener(new a(aVar, viewHolder));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(this.f2632a).inflate(R.layout.item_oil_reset, viewGroup, false));
    }
}
