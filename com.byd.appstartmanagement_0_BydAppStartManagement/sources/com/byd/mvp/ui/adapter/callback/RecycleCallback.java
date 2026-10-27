package com.byd.mvp.ui.adapter.callback;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import b.a.d.e.a.a.a;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class RecycleCallback<T> extends ItemTouchHelper.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f694a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f695b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f696c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f697d;
    public WeakReference<RecyclerView.ViewHolder> e;

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
            int i3 = this.f696c;
            if (i3 == 1) {
                i = 16;
            } else if (i3 == 2) {
                i = 32;
            }
        } else {
            i = 3;
        }
        if (!this.f694a) {
            i2 = 0;
        }
        return ItemTouchHelper.Callback.makeMovementFlags(i2, this.f695b ? i : 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        if (!this.f694a || this.f697d == null) {
            return false;
        }
        b.a.d.b.a.a("RecycleCallback", ", RecycleCallback----onMove viewHolder = " + viewHolder);
        int adapterPosition = viewHolder.getAdapterPosition();
        int adapterPosition2 = viewHolder2.getAdapterPosition();
        b.a.d.b.a.a("RecycleCallback", ", RecycleCallback----onMove from = " + adapterPosition + ", to = " + adapterPosition2);
        this.f697d.a(adapterPosition, adapterPosition2);
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
        super.onSelectedChanged(viewHolder, i);
        b.a.d.b.a.a("RecycleCallback", ", RecycleCallback---- actionState = " + i + ", viewHolder = " + viewHolder);
        if (!this.f694a || this.f697d == null) {
            return;
        }
        if (i != 2) {
            if (i == 0) {
                if (viewHolder == null) {
                    viewHolder = this.e.get();
                }
                this.f697d.b(viewHolder);
                return;
            }
            return;
        }
        b.a.d.b.a.a("RecycleCallback", ", RecycleCallback----viewHolder = " + viewHolder);
        this.e = new WeakReference<>(viewHolder);
        this.f697d.c(viewHolder);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        boolean z = this.f695b;
        b.a.d.b.a.a("RecycleCallback", ", RecycleCallback----onSwiped viewHolder = " + viewHolder);
        this.f697d.removeItem(viewHolder.getAdapterPosition());
    }
}
