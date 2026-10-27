package com.byd.cleardata.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.b;
import com.byd.cleardata.i.c;

/* JADX INFO: loaded from: classes.dex */
public class EmptyRecyclerView extends RecyclerView {
    private View F0;
    private View G0;
    private boolean H0;
    private final RecyclerView.i I0;

    class a extends RecyclerView.i {
        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            c.b("onChanged");
            EmptyRecyclerView.this.z1();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void d(int i, int i2) {
            c.b("onItemRangeInserted");
            EmptyRecyclerView.this.z1();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void e(int i, int i2) {
            c.b("onItemRangeRemoved");
            EmptyRecyclerView.this.z1();
        }
    }

    public EmptyRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.I0 = new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z1() {
        if (this.F0 == null || getAdapter() == null) {
            return;
        }
        boolean z = getAdapter().c() == 0;
        c.g("FileContext.getInstance().getMenuIndex() = " + b.h().j() + ", emptyViewVisible = " + z + ", emptyView = " + this.F0 + ", clearCacheView = " + this.G0);
        if (this.H0) {
            this.F0.setVisibility(8);
            this.G0.setVisibility(8);
        } else if (this.G0 == null || b.h().j() != 1) {
            this.F0.setVisibility(z ? 0 : 8);
            this.G0.setVisibility(8);
            setVisibility(z ? 8 : 0);
        } else {
            this.F0.setVisibility(8);
            setVisibility(8);
            this.G0.setVisibility(z ? 0 : 8);
        }
    }

    public void A1(View view, View view2) {
        this.F0 = view;
        this.G0 = view2;
        z1();
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        return 0.0f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.g gVar) {
        RecyclerView.g adapter = getAdapter();
        if (adapter != null) {
            adapter.w(this.I0);
        }
        super.setAdapter(gVar);
        if (gVar != null) {
            gVar.u(this.I0);
        }
        z1();
    }

    public void setLoading(boolean z) {
        this.H0 = z;
        z1();
    }
}
