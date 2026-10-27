package com.byd.base.view.recyclerview;

import android.util.SparseArray;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.j.d.b;

/* JADX INFO: loaded from: classes.dex */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseArray<View> f2507a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BaseListRecyclerAdapter f2508b;

    public class a extends b.b.a.j.d.a {
        public a() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            if (BaseViewHolder.this.f2508b.o() != null) {
                b bVarO = BaseViewHolder.this.f2508b.o();
                BaseViewHolder baseViewHolder = BaseViewHolder.this;
                bVarO.r(baseViewHolder.f2508b, view, baseViewHolder.getAdapterPosition());
            }
        }
    }

    public BaseViewHolder(@NonNull View view, BaseListRecyclerAdapter baseListRecyclerAdapter) {
        super(view);
        e(baseListRecyclerAdapter);
        this.f2507a = new SparseArray<>();
    }

    public BaseViewHolder a(@IdRes int... iArr) {
        if (iArr != null && iArr.length > 0) {
            for (int i : iArr) {
                b(i);
            }
        }
        return this;
    }

    public BaseViewHolder b(@IdRes int i) {
        View viewD;
        if (this.f2508b != null && (viewD = d(i)) != null) {
            if (!viewD.isClickable()) {
                viewD.setClickable(true);
            }
            viewD.setOnClickListener(new a());
        }
        return this;
    }

    public void c(Object obj) {
    }

    public <T extends View> T d(@IdRes int i) {
        T t = (T) this.f2507a.get(i);
        if (t != null) {
            return t;
        }
        T t2 = (T) this.itemView.findViewById(i);
        this.f2507a.put(i, t2);
        return t2;
    }

    public final void e(BaseListRecyclerAdapter baseListRecyclerAdapter) {
        this.f2508b = baseListRecyclerAdapter;
    }
}
