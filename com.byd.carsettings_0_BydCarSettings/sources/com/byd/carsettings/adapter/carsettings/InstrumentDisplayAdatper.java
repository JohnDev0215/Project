package com.byd.carsettings.adapter.carsettings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.l;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.carsettings.viewholder.InsViewHolder;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class InstrumentDisplayAdatper extends RecyclerView.Adapter<InsViewHolder> implements b.b.o.b.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2648a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<b.b.g.c.a.a.a> f2649b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2650c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f2651d;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ InsViewHolder f2652a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2653b;

        public a(InsViewHolder insViewHolder, int i) {
            this.f2652a = insViewHolder;
            this.f2653b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f2652a.f2668a.setBackground(ContextCompat.getDrawable(InstrumentDisplayAdatper.this.f2648a, R.drawable.vehicle_selector_dialog_blue));
            this.f2652a.f2668a.setTextColor(ContextCompat.getColor(InstrumentDisplayAdatper.this.f2648a, R.color.btn_only_selected));
            InstrumentDisplayAdatper.this.f(this.f2653b);
            b bVar = InstrumentDisplayAdatper.this.f2651d;
            if (bVar != null) {
                bVar.a(this.f2653b);
            }
            InstrumentDisplayAdatper.this.notifyDataSetChanged();
        }
    }

    public interface b {
        void a(int i);
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.instrument_rtl_display_item;
    }

    public void d() {
        List<b.b.g.c.a.a.a> list = this.f2649b;
        if (list != null) {
            list.clear();
        }
    }

    public void e(Context context, List<b.b.g.c.a.a.a> list) {
        this.f2648a = context;
        List<b.b.g.c.a.a.a> list2 = this.f2649b;
        if (list2 != null) {
            list2.clear();
            this.f2649b.addAll(list);
        }
    }

    public final void f(int i) {
        for (int i2 = 0; i2 < this.f2649b.size(); i2++) {
            if (i2 == i) {
                this.f2649b.get(i2).f(true);
            } else {
                this.f2649b.get(i2).f(false);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull InsViewHolder insViewHolder, int i) {
        insViewHolder.f2668a.setText(this.f2649b.get(i).d());
        if (this.f2649b.get(i).e()) {
            insViewHolder.f2668a.setBackground(ContextCompat.getDrawable(this.f2648a, R.drawable.vehicle_selector_dialog_blue));
            insViewHolder.f2668a.setTextColor(ContextCompat.getColor(this.f2648a, R.color.btn_only_selected));
        } else {
            insViewHolder.f2668a.setBackground(ContextCompat.getDrawable(this.f2648a, R.drawable.vehicle_selector_dialog_white));
            insViewHolder.f2668a.setTextColor(ContextCompat.getColor(this.f2648a, R.color.btn_only_unselected));
        }
        insViewHolder.f2668a.setOnClickListener(new a(insViewHolder, i));
        boolean z = this.f2650c;
        l.H(z, z ? 1.0f : 0.5f, insViewHolder.f2668a);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2649b.size();
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.instrument_display_item;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public InsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new InsViewHolder(LayoutInflater.from(this.f2648a).inflate(b.b.o.b.b.a.a(this), viewGroup, false));
    }

    public void j(boolean z) {
        this.f2650c = z;
    }

    public void k(List<b.b.g.c.a.a.a> list) {
        BydLogHelper.logD(getClass().getSimpleName(), "milesRv upDateData ：" + list.size());
        List<b.b.g.c.a.a.a> list2 = this.f2649b;
        if (list2 != null) {
            list2.clear();
            this.f2649b.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.instrument_display_item;
    }

    public void setListener(b bVar) {
        this.f2651d = bVar;
    }
}
