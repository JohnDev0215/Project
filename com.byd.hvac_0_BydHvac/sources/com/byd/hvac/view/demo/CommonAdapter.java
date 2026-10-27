package com.byd.hvac.view.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.a.a.i.h.ta;
import com.byd.hvac.R;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class CommonAdapter extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1020a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f1021b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<ta> f1022c;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f1023a;

        public a(View view) {
            super(view);
            this.f1023a = (TextView) view.findViewById(R.id.title);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface b {
        void a(ta taVar, int i);
    }

    public CommonAdapter(Context context, List<ta> list) {
        this.f1020a = context;
        this.f1022c = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(ta taVar, int i, View view) {
        b bVar = this.f1021b;
        if (bVar != null) {
            bVar.a(taVar, i);
        }
    }

    public void c(b bVar) {
        this.f1021b = bVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ta> list = this.f1022c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof a) {
            final ta taVar = this.f1022c.get(i);
            a aVar = (a) viewHolder;
            aVar.f1023a.setText(taVar.b());
            aVar.f1023a.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f308a.b(taVar, i, view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(this.f1020a).inflate(R.layout.demo_item_common_recyclerview, viewGroup, false));
    }
}
