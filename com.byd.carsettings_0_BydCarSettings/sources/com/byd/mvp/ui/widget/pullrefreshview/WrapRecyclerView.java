package com.byd.mvp.ui.widget.pullrefreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.f.c.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WrapRecyclerView extends RecyclerView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RecyclerView.Adapter f3856a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f3857b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f3858c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<View> f3859d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<View> f3860e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3861f;
    public a g;

    public interface a {
        void a();

        void b();
    }

    public WrapRecyclerView(Context context) {
        super(context);
        this.f3859d = new ArrayList();
        this.f3860e = new ArrayList();
    }

    public void a(View view) {
        this.f3860e.add(view);
        c cVar = this.f3857b;
        if (cVar != null) {
            cVar.i(view);
        }
    }

    public void b(View view) {
        this.f3860e.remove(view);
        c cVar = this.f3857b;
        if (cVar != null) {
            cVar.q(view);
            this.f3857b.notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public RecyclerView.Adapter getAdapter() {
        return this.f3856a;
    }

    public View getEmptyDataView() {
        return this.f3858c;
    }

    public int getFooterCount() {
        return this.f3860e.size();
    }

    public int getHeaderCount() {
        return this.f3859d.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        c cVar = this.f3857b;
        if (cVar != null) {
            cVar.s();
            this.f3857b = null;
        }
        c cVar2 = new c(adapter);
        this.f3857b = cVar2;
        cVar2.k(this, this.f3861f);
        View view = this.f3858c;
        if (view != null) {
            this.f3857b.g(view);
        }
        Iterator<View> it = this.f3859d.iterator();
        while (it.hasNext()) {
            this.f3857b.j(it.next());
        }
        Iterator<View> it2 = this.f3860e.iterator();
        while (it2.hasNext()) {
            this.f3857b.i(it2.next());
        }
        this.f3856a = adapter;
        super.setAdapter(this.f3857b);
    }

    public void setAdjustGrideSpanSize(boolean z) {
        this.f3861f = z;
        c cVar = this.f3857b;
        if (cVar != null) {
            cVar.k(this, z);
        }
    }

    public void setSRecyclerListener(a aVar) {
        this.g = aVar;
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3859d = new ArrayList();
        this.f3860e = new ArrayList();
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3859d = new ArrayList();
        this.f3860e = new ArrayList();
    }
}
