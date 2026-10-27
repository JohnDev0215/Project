package com.byd.carsettings.adapter.carhealthy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DtcInformationListAdapter extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2615a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<b.b.g.m.c.a> f2616b;

    public class a extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2617a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ImageView f2618b;

        public a(View view) {
            super(view);
            this.f2617a = (TextView) view.findViewById(R.id.tv_desc);
            this.f2618b = (ImageView) view.findViewById(R.id.iv_point);
        }
    }

    public DtcInformationListAdapter(Context context) {
        this.f2615a = context;
    }

    public void a(List<b.b.g.m.c.a> list) {
        this.f2616b = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<b.b.g.m.c.a> list = this.f2616b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        a aVar = (a) viewHolder;
        int i2 = this.f2616b.get(i).f886a;
        if (i2 == 0) {
            aVar.f2617a.setTextColor(ContextCompat.getColor(this.f2615a, R.color.carmsg_serious));
            aVar.f2618b.setImageDrawable(ContextCompat.getDrawable(this.f2615a, R.drawable.circle_serious_small));
        } else if (i2 == 1) {
            aVar.f2617a.setTextColor(ContextCompat.getColor(this.f2615a, R.color.carmsg_warn));
            aVar.f2618b.setImageDrawable(ContextCompat.getDrawable(this.f2615a, R.drawable.circle_warn_small));
        } else {
            aVar.f2617a.setTextColor(ContextCompat.getColor(this.f2615a, R.color.carmsg_normal));
            aVar.f2618b.setImageDrawable(ContextCompat.getDrawable(this.f2615a, R.drawable.circle_normal_small));
        }
        aVar.f2617a.setText(this.f2616b.get(i).f887b);
        if (i == this.f2616b.size() - 1) {
            aVar.itemView.setPadding(0, this.f2615a.getResources().getDimensionPixelSize(R.dimen.dp_12), 0, this.f2615a.getResources().getDimensionPixelSize(R.dimen.dp_17));
        } else {
            aVar.itemView.setPadding(0, this.f2615a.getResources().getDimensionPixelSize(R.dimen.dp_12), 0, this.f2615a.getResources().getDimensionPixelSize(R.dimen.dp_12));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(this.f2615a).inflate(R.layout.item_dtc_desc, viewGroup, false));
    }
}
