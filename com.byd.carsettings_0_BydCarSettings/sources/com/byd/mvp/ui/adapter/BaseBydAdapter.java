package com.byd.mvp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.c.a.a;
import b.b.m.f.c.a.b;
import b.b.m.f.c.a.e;
import b.b.m.f.c.a.g;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseBydAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<T> f3743a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f3744b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f3745c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f3746d;

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3747a;

        public int getId() {
            return this.f3747a;
        }
    }

    public abstract void a(@NonNull RecyclerView.ViewHolder viewHolder, int i);

    public int b() {
        return loadLayoutId();
    }

    public abstract BaseBydAdapter<T>.BaseViewHolder d(View view);

    @Override // b.b.m.f.c.a.a
    public void drag(int i, int i2) {
        T t = this.f3743a.get(i);
        this.f3743a.remove(i);
        this.f3743a.add(i2, t);
        notifyItemMoved(i, i2);
        b bVar = this.f3744b;
        if (bVar != null) {
            bVar.a(i, i2);
        }
    }

    @Override // b.b.m.f.c.a.a
    public void dragCompleted(RecyclerView.ViewHolder viewHolder) {
        b bVar = this.f3744b;
        if (bVar != null) {
            bVar.b(viewHolder);
        }
    }

    public BaseBydAdapter<T>.BaseViewHolder e(int i, View view) {
        return d(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public BaseBydAdapter<T>.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(b(), viewGroup, false);
        viewInflate.setOnClickListener(this);
        BaseBydAdapter<T>.BaseViewHolder baseViewHolderE = e(i, viewInflate);
        viewInflate.setTag(baseViewHolderE);
        return baseViewHolderE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.f3743a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public abstract int loadLayoutId();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        a(viewHolder, i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        e eVar = this.f3745c;
        if (eVar != null) {
            eVar.onItemClicked(((BaseViewHolder) view.getTag()).getId());
        }
    }

    @Override // b.b.m.f.c.a.a
    public void removeItem(int i) {
        b bVar = this.f3744b;
        if (bVar != null) {
            bVar.d(i);
        }
    }

    public void setOnItemClickedListener(e eVar) {
        this.f3745c = eVar;
    }

    public void setOnSubitemClickedListener(g gVar) {
        this.f3746d = gVar;
    }
}
