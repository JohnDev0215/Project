package com.byd.mvp.ui.widget.pullrefreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import l4.c;

/* JADX INFO: loaded from: classes2.dex */
public class WrapRecyclerView extends RecyclerView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RecyclerView.g f2995d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f2996e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f2997f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List<View> f2998g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public List<View> f2999h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3000i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public a f3001j;

    public interface a {
        void a();

        void b();
    }

    public WrapRecyclerView(Context context) {
        super(context);
        this.f2998g = new ArrayList();
        this.f2999h = new ArrayList();
    }

    public void a(View view) {
        this.f2999h.add(view);
        c cVar = this.f2996e;
        if (cVar != null) {
            cVar.g(view);
        }
    }

    public void c(View view) {
        this.f2999h.remove(view);
        c cVar = this.f2996e;
        if (cVar != null) {
            cVar.n(view);
            this.f2996e.notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public RecyclerView.g getAdapter() {
        return this.f2995d;
    }

    public View getEmptyDataView() {
        return this.f2997f;
    }

    public int getFooterCount() {
        return this.f2999h.size();
    }

    public int getHeaderCount() {
        return this.f2998g.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.g gVar) {
        c cVar = this.f2996e;
        if (cVar != null) {
            cVar.p();
            this.f2996e = null;
        }
        c cVar2 = new c(gVar);
        this.f2996e = cVar2;
        cVar2.i(this, this.f3000i);
        View view = this.f2997f;
        if (view != null) {
            this.f2996e.f(view);
        }
        Iterator<View> it = this.f2998g.iterator();
        while (it.hasNext()) {
            this.f2996e.h(it.next());
        }
        Iterator<View> it2 = this.f2999h.iterator();
        while (it2.hasNext()) {
            this.f2996e.g(it2.next());
        }
        this.f2995d = gVar;
        super.setAdapter(this.f2996e);
    }

    public void setAdjustGrideSpanSize(boolean z7) {
        this.f3000i = z7;
        c cVar = this.f2996e;
        if (cVar != null) {
            cVar.i(this, z7);
        }
    }

    public void setSRecyclerListener(a aVar) {
        this.f3001j = aVar;
    }

    public WrapRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2998g = new ArrayList();
        this.f2999h = new ArrayList();
    }

    public WrapRecyclerView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2998g = new ArrayList();
        this.f2999h = new ArrayList();
    }
}
