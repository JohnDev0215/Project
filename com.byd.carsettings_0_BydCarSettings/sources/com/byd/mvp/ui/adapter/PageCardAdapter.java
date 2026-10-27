package com.byd.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.c.a.a;
import b.b.m.f.c.a.b;
import b.b.m.f.c.a.e;
import com.byd.mvp.model.BydData;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PageCardAdapter<T extends BydData> extends RecyclerView.Adapter implements View.OnClickListener, a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<T> f3749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f3750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f3751c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PageCardAdapter f3752d = this;

    public static class PageCardViewHolder extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<b.b.m.e.e> f3753a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3754b;

        public PageCardViewHolder(b.b.m.e.e eVar) {
            super(eVar.c());
            this.itemView.setTag(this);
            this.f3753a = new WeakReference<>(eVar);
        }

        public void a(BydData bydData, int i) {
            WeakReference<b.b.m.e.e> weakReference = this.f3753a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f3753a.get().n(bydData, i);
        }

        public int getId() {
            return this.f3754b;
        }
    }

    public PageCardAdapter(List<T> list) {
        this.f3749a = list;
    }

    public b.b.m.e.e a(ViewGroup viewGroup, int i) {
        b.b.m.e.e eVarB = b(i);
        eVarB.h(viewGroup);
        return eVarB;
    }

    public abstract b.b.m.e.e b(int i);

    public PageCardViewHolder d(ViewGroup viewGroup, int i) {
        return new PageCardViewHolder(a(viewGroup, i));
    }

    @Override // b.b.m.f.c.a.a
    public void drag(int i, int i2) {
        T t = this.f3749a.get(i);
        this.f3749a.remove(i);
        this.f3749a.add(i2, t);
        notifyItemMoved(i, i2);
        b bVar = this.f3750b;
        if (bVar != null) {
            bVar.a(i, i2);
        }
    }

    @Override // b.b.m.f.c.a.a
    public void dragCompleted(RecyclerView.ViewHolder viewHolder) {
        b bVar = this.f3750b;
        if (bVar != null) {
            bVar.b(viewHolder);
        }
    }

    @Override // b.b.m.f.c.a.a
    public void dragStart(RecyclerView.ViewHolder viewHolder) {
        b bVar = this.f3750b;
        if (bVar != null) {
            bVar.c(viewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.f3749a;
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
        ((PageCardViewHolder) viewHolder).a(this.f3749a.get(i), i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        e eVar = this.f3751c;
        if (eVar != null) {
            eVar.onItemClicked(((PageCardViewHolder) view.getTag()).getId());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        PageCardViewHolder pageCardViewHolderD = d(viewGroup, i);
        pageCardViewHolderD.itemView.setOnClickListener(this);
        return pageCardViewHolderD;
    }

    @Override // b.b.m.f.c.a.a
    public void removeItem(int i) {
        b bVar = this.f3750b;
        if (bVar != null) {
            bVar.d(i);
        }
    }

    public void setOnDragItemCallback(b bVar) {
        this.f3750b = bVar;
    }

    public void setOnItemClickedListener(e eVar) {
        this.f3751c = eVar;
    }
}
