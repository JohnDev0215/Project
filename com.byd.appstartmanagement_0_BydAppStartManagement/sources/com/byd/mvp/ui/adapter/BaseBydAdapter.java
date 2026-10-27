package com.byd.mvp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d.e.a.a.a;
import b.a.d.e.a.a.b;
import b.a.d.e.a.a.c;
import b.a.d.e.a.a.d;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseBydAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<T> f679b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f680c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f681d;
    public d e;

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f682a;

        public int a() {
            return this.f682a;
        }
    }

    @Override // b.a.d.e.a.a.a
    public void a(int i, int i2) {
        T t = this.f679b.get(i);
        this.f679b.remove(i);
        this.f679b.add(i2, t);
        notifyItemMoved(i, i2);
        b bVar = this.f680c;
        if (bVar != null) {
            bVar.a(i, i2);
        }
    }

    @Override // b.a.d.e.a.a.a
    public void b(RecyclerView.ViewHolder viewHolder) {
        b bVar = this.f680c;
        if (bVar != null) {
            bVar.b(viewHolder);
        }
    }

    public abstract void d(@NonNull RecyclerView.ViewHolder viewHolder, int i);

    public int e() {
        return h();
    }

    public abstract BaseBydAdapter<T>.BaseViewHolder f(View view);

    public BaseBydAdapter<T>.BaseViewHolder g(int i, View view) {
        return f(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.f679b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public abstract int h();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public BaseBydAdapter<T>.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(e(), viewGroup, false);
        viewInflate.setOnClickListener(this);
        BaseBydAdapter<T>.BaseViewHolder baseViewHolderG = g(i, viewInflate);
        viewInflate.setTag(baseViewHolderG);
        return baseViewHolderG;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        d(viewHolder, i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        c cVar = this.f681d;
        if (cVar != null) {
            cVar.a(((BaseViewHolder) view.getTag()).a());
        }
    }

    @Override // b.a.d.e.a.a.a
    public void removeItem(int i) {
        b bVar = this.f680c;
        if (bVar != null) {
            bVar.d(i);
        }
    }

    public void setOnItemClickedListener(c cVar) {
        this.f681d = cVar;
    }

    public void setOnSubitemClickedListener(d dVar) {
        this.e = dVar;
    }
}
