package com.byd.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d.e.a.a.a;
import b.a.d.e.a.a.b;
import b.a.d.e.a.a.c;
import com.byd.mvp.model.BydData;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PageCardAdapter<T extends BydData> extends RecyclerView.Adapter implements View.OnClickListener, a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<T> f689b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f690c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f691d;

    public static class PageCardViewHolder extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<b.a.d.d.c> f692a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f693b;

        public PageCardViewHolder(b.a.d.d.c cVar) {
            super(cVar.g());
            this.itemView.setTag(this);
            this.f692a = new WeakReference<>(cVar);
        }

        public int a() {
            return this.f693b;
        }

        public void b(BydData bydData, int i) {
            WeakReference<b.a.d.d.c> weakReference = this.f692a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f692a.get().m(bydData, i);
        }
    }

    @Override // b.a.d.e.a.a.a
    public void a(int i, int i2) {
        T t = this.f689b.get(i);
        this.f689b.remove(i);
        this.f689b.add(i2, t);
        notifyItemMoved(i, i2);
        b bVar = this.f690c;
        if (bVar != null) {
            bVar.a(i, i2);
        }
    }

    @Override // b.a.d.e.a.a.a
    public void b(RecyclerView.ViewHolder viewHolder) {
        b bVar = this.f690c;
        if (bVar != null) {
            bVar.b(viewHolder);
        }
    }

    @Override // b.a.d.e.a.a.a
    public void c(RecyclerView.ViewHolder viewHolder) {
        b bVar = this.f690c;
        if (bVar != null) {
            bVar.c(viewHolder);
        }
    }

    public b.a.d.d.c d(ViewGroup viewGroup, int i) {
        b.a.d.d.c cVarE = e(i);
        cVarE.j(viewGroup);
        return cVarE;
    }

    public abstract b.a.d.d.c e(int i);

    public PageCardViewHolder f(ViewGroup viewGroup, int i) {
        return new PageCardViewHolder(d(viewGroup, i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.f689b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((PageCardViewHolder) viewHolder).b(this.f689b.get(i), i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        c cVar = this.f691d;
        if (cVar != null) {
            cVar.a(((PageCardViewHolder) view.getTag()).a());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        PageCardViewHolder pageCardViewHolderF = f(viewGroup, i);
        pageCardViewHolderF.itemView.setOnClickListener(this);
        return pageCardViewHolderF;
    }

    @Override // b.a.d.e.a.a.a
    public void removeItem(int i) {
        b bVar = this.f690c;
        if (bVar != null) {
            bVar.d(i);
        }
    }

    public void setOnItemClickedListener(c cVar) {
        this.f691d = cVar;
    }
}
