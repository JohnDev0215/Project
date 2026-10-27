package com.byd.carsettings.adapter.carhealthy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OilListAdapter extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2620a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<b.b.g.m.c.a> f2621b;

    public static class a extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2622a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2623b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2624c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ProgressBar f2625d;

        public a(View view) {
            super(view);
            this.f2622a = (TextView) view.findViewById(R.id.tv_oil_item);
            this.f2624c = (TextView) view.findViewById(R.id.tv_oil_percent);
            this.f2623b = (TextView) view.findViewById(R.id.tv_oil_advise);
            this.f2625d = (ProgressBar) view.findViewById(R.id.progress_oil);
        }
    }

    public OilListAdapter(Context context, List<b.b.g.m.c.a> list) {
        this.f2620a = context;
        this.f2621b = list;
    }

    public void a(List<b.b.g.m.c.a> list) {
        this.f2621b = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<b.b.g.m.c.a> list = this.f2621b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        b.b.g.m.c.a aVar = this.f2621b.get(i);
        a aVar2 = (a) viewHolder;
        int i2 = aVar.f886a;
        boolean z = aVar.f889d != 4 ? i2 <= 10 : i2 <= 3;
        aVar2.f2623b.setVisibility(z ? 0 : 8);
        int i3 = R.color.carmsg_serious;
        if (i2 < 0) {
            aVar2.f2624c.setTextColor(this.f2620a.getColor(R.color.carmsg_serious));
            aVar2.f2625d.setProgressDrawable(ContextCompat.getDrawable(this.f2620a, R.drawable.seekbar_background_oil_serious));
            aVar2.f2625d.setProgress(i2 == -999 ? 0 : 1);
        } else {
            TextView textView = aVar2.f2624c;
            Context context = this.f2620a;
            if (!z) {
                i3 = R.color.text_color_main;
            }
            textView.setTextColor(context.getColor(i3));
            aVar2.f2625d.setProgressDrawable(ContextCompat.getDrawable(this.f2620a, z ? R.drawable.seekbar_background_oil_warn : R.drawable.seekbar_background_oil));
            aVar2.f2625d.setProgress(i2);
        }
        aVar2.f2622a.setText(aVar.f887b);
        TextView textView2 = aVar2.f2624c;
        StringBuilder sb = new StringBuilder();
        sb.append(i2 == -999 ? "--" : Integer.valueOf(i2));
        sb.append("%");
        textView2.setText(sb.toString());
        aVar2.itemView.setVisibility(i2 == -999 ? 8 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(this.f2620a).inflate(R.layout.item_oil_result, viewGroup, false));
    }
}
