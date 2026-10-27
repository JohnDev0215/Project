package com.byd.mvp.ui.adapter.callback;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import b.b.m.f.c.a.a;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class RecycleCallback<T> extends ItemTouchHelper.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3755a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f3756b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3757c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f3758d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WeakReference<RecyclerView.ViewHolder> f3759e;

    public RecycleCallback(a aVar) {
        this.f3758d = aVar;
    }

    public void a(boolean z) {
        this.f3755a = z;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int orientation;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int i = 48;
        int i2 = 15;
        if (layoutManager instanceof GridLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            i2 = orientation == 1 ? 3 : 48;
        } else {
            orientation = 0;
            i2 = 0;
        }
        if (orientation == 1) {
            int i3 = this.f3757c;
            if (i3 == 1) {
                i = 16;
            } else if (i3 == 2) {
                i = 32;
            }
        } else {
            i = 3;
        }
        if (!this.f3755a) {
            i2 = 0;
        }
        return ItemTouchHelper.Callback.makeMovementFlags(i2, this.f3756b ? i : 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        if (!this.f3755a || this.f3758d == null) {
            return false;
        }
        b.b.m.c.a.a("RecycleCallback", ", RecycleCallback----onMove viewHolder = " + viewHolder);
        int adapterPosition = viewHolder.getAdapterPosition();
        int adapterPosition2 = viewHolder2.getAdapterPosition();
        b.b.m.c.a.a("RecycleCallback", ", RecycleCallback----onMove from = " + adapterPosition + ", to = " + adapterPosition2);
        this.f3758d.drag(adapterPosition, adapterPosition2);
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
        super.onSelectedChanged(viewHolder, i);
        b.b.m.c.a.a("RecycleCallback", ", RecycleCallback---- actionState = " + i + ", viewHolder = " + viewHolder);
        if (!this.f3755a || this.f3758d == null) {
            return;
        }
        if (i != 2) {
            if (i == 0) {
                if (viewHolder == null) {
                    viewHolder = this.f3759e.get();
                }
                this.f3758d.dragCompleted(viewHolder);
                return;
            }
            return;
        }
        b.b.m.c.a.a("RecycleCallback", ", RecycleCallback----viewHolder = " + viewHolder);
        this.f3759e = new WeakReference<>(viewHolder);
        this.f3758d.dragStart(viewHolder);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        boolean z = this.f3756b;
        b.b.m.c.a.a("RecycleCallback", ", RecycleCallback----onSwiped viewHolder = " + viewHolder);
        this.f3758d.removeItem(viewHolder.getAdapterPosition());
    }
}
