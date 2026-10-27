package com.byd.mvp.ui.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d.e.a.a.b;
import b.a.d.e.a.a.c;
import b.a.d.e.a.a.d;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BydAdapter<T> extends RecyclerView.Adapter implements View.OnClickListener, b.a.d.e.a.a.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<T> f684b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f685c;
    public a f;
    public Resources g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f686d = null;
    public d e = null;
    public BydAdapter h = this;

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f687a;

        public BaseViewHolder(View view) {
            super(view);
            view.setTag(this);
        }

        public int a() {
            return this.f687a;
        }
    }

    public interface a {
        void a(int i, boolean z, BtnType btnType);
    }

    public BydAdapter(List<T> list) {
        this.f684b = list;
    }

    @Override // b.a.d.e.a.a.a
    public void a(int i, int i2) {
        T t = this.f684b.get(i);
        this.f684b.remove(i);
        this.f684b.add(i2, t);
        notifyItemMoved(i, i2);
        b bVar = this.f685c;
        if (bVar != null) {
            bVar.a(i, i2);
        }
    }

    @Override // b.a.d.e.a.a.a
    public void b(RecyclerView.ViewHolder viewHolder) {
        b bVar = this.f685c;
        if (bVar != null) {
            bVar.b(viewHolder);
        }
    }

    @Override // b.a.d.e.a.a.a
    public void c(RecyclerView.ViewHolder viewHolder) {
        b bVar = this.f685c;
        if (bVar != null) {
            bVar.c(viewHolder);
        }
    }

    public int d(int i) {
        return g();
    }

    public abstract BydAdapter<T>.BaseViewHolder e(View view);

    public BydAdapter<T>.BaseViewHolder f(int i, View view) {
        return e(view);
    }

    public abstract int g();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.f684b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public void h(a aVar) {
        this.f = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        c cVar = this.f686d;
        if (cVar != null) {
            cVar.a(((BaseViewHolder) view.getTag()).a());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(d(i), viewGroup, false);
        viewInflate.setOnClickListener(this);
        this.g = viewInflate.getResources();
        BydAdapter<T>.BaseViewHolder baseViewHolderF = f(i, viewInflate);
        viewInflate.setTag(baseViewHolderF);
        return baseViewHolderF;
    }

    @Override // b.a.d.e.a.a.a
    public void removeItem(int i) {
        b bVar = this.f685c;
        if (bVar != null) {
            bVar.d(i);
        }
    }

    public void setOnItemClickedListener(c cVar) {
        this.f686d = cVar;
    }

    public void setOnSubitemClickedListener(d dVar) {
        this.e = dVar;
    }
}
