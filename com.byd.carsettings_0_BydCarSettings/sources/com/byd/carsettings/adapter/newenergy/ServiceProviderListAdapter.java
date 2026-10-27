package com.byd.carsettings.adapter.newenergy;

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
public class ServiceProviderListAdapter extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2782a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.g.n.n.b f2783b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<b.b.g.m.e.a> f2784c;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.ViewHolder f2785a;

        public a(RecyclerView.ViewHolder viewHolder) {
            this.f2785a = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int i = 0;
            while (i < ServiceProviderListAdapter.this.f2784c.size()) {
                ((b.b.g.m.e.a) ServiceProviderListAdapter.this.f2784c.get(i)).f933b = i == this.f2785a.getAdapterPosition();
                i++;
            }
            ServiceProviderListAdapter.this.notifyDataSetChanged();
        }
    }

    public static class b extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2787a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ImageView f2788b;

        public b(View view) {
            super(view);
            this.f2787a = (TextView) view.findViewById(R.id.tv_service_name);
            this.f2788b = (ImageView) view.findViewById(R.id.iv_service_name);
        }
    }

    public ServiceProviderListAdapter(Context context, b.b.g.n.n.b bVar) {
        this.f2782a = context;
        this.f2783b = bVar;
    }

    public b.b.g.m.e.a b() {
        List<b.b.g.m.e.a> list = this.f2784c;
        if (list == null) {
            return null;
        }
        for (b.b.g.m.e.a aVar : list) {
            if (aVar.f933b) {
                return aVar;
            }
        }
        return null;
    }

    public void d(List<b.b.g.m.e.a> list) {
        this.f2784c = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<b.b.g.m.e.a> list = this.f2784c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        b bVar = (b) viewHolder;
        b.b.g.m.e.a aVar = this.f2784c.get(i);
        bVar.f2787a.setText(aVar.f932a);
        bVar.f2788b.setImageDrawable(ContextCompat.getDrawable(this.f2782a, aVar.f933b ? R.drawable.radio_pitch_regular : R.drawable.radio_normal_regular));
        bVar.itemView.setOnClickListener(new a(viewHolder));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.f2782a).inflate(R.layout.item_service_provider, viewGroup, false));
    }
}
