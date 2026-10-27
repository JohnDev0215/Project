package com.android.car.ui.recyclerview;

import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import spi.f8;
import spi.m9;
import spi.n9;
import spi.w2;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ContentLimitingAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements w2 {
    public Integer a;
    public RecyclerView c;

    @NonNull
    public m9 b = new f8();
    public boolean d = false;

    @Override // spi.w2
    public final void a(int i) {
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        this.b.e();
        if (i < 0) {
            this.d = false;
            f8 f8Var = new f8();
            this.b = f8Var;
            f8Var.a = h();
            this.b.f();
            return;
        }
        this.d = true;
        n9 n9Var = new n9(this, i);
        this.b = n9Var;
        n9Var.a(h(), d());
        this.b.f();
        int iF = f();
        if (iF < 0 || (recyclerView = this.c) == null || (layoutManager = recyclerView.getLayoutManager()) == null) {
            return;
        }
        layoutManager.scrollToPosition(iF);
    }

    @Override // spi.w2
    public final void b(@StringRes int i) {
        Integer num = this.a;
        if (num == null || num.intValue() != i) {
            this.a = Integer.valueOf(i);
            this.b.c();
        }
    }

    public int d() {
        return 0;
    }

    public int e(int i) {
        return super.getItemViewType(i);
    }

    public int f() {
        return -1;
    }

    public int g() {
        return Integer.MAX_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.d ? this.b.b() : h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        return this.b.d(i) == -1 ? g() : e(this.b.d(i));
    }

    public abstract int h();

    public abstract void i(T t, int i);

    public abstract T j(@NonNull ViewGroup viewGroup, int i);

    public boolean k(@NonNull T t) {
        return super.onFailedToRecycleView(t);
    }

    public void l(@NonNull T t) {
    }

    public void m(@NonNull T t) {
    }

    public void n(@NonNull T t) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        this.c = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ScrollingLimitedViewHolder) {
            ((ScrollingLimitedViewHolder) viewHolder).a(this.a);
            return;
        }
        int iD = this.b.d(i);
        if (iD == -1) {
            StringBuilder sbL = spi.c.l("onBindViewHolder invalid position ", i, " ");
            sbL.append(this.b.toString());
            Log.e("ContentLimitingAdapter", sbL.toString());
            return;
        }
        int iH = h();
        if (iD >= 0 && iD < iH) {
            i(viewHolder, iD);
            return;
        }
        StringBuilder sbM = spi.c.m("onBindViewHolder pos: ", i, " gave index: ", iD, " out of bounds size: ");
        sbM.append(iH);
        sbM.append(" ");
        sbM.append(this.b.toString());
        Log.e("ContentLimitingAdapter", sbM.toString());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return i == g() ? ScrollingLimitedViewHolder.b(viewGroup) : j(viewGroup, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder viewHolder) {
        return !(viewHolder instanceof ScrollingLimitedViewHolder) ? k(viewHolder) : super.onFailedToRecycleView(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder instanceof ScrollingLimitedViewHolder) {
            return;
        }
        l(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        if (viewHolder instanceof ScrollingLimitedViewHolder) {
            return;
        }
        m(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        if (viewHolder instanceof ScrollingLimitedViewHolder) {
            return;
        }
        n(viewHolder);
    }
}
