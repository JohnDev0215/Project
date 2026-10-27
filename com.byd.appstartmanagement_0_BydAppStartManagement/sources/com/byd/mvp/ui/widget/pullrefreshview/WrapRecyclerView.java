package com.byd.mvp.ui.widget.pullrefreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d.e.c.b.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WrapRecyclerView extends RecyclerView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView.Adapter f759b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f760c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f761d;
    public List<View> e;
    public List<View> f;
    public boolean g;
    public a h;

    public interface a {
        void a();

        void b();
    }

    public WrapRecyclerView(Context context) {
        super(context);
        this.e = new ArrayList();
        this.f = new ArrayList();
    }

    public void a(View view) {
        this.f.add(view);
        c cVar = this.f760c;
        if (cVar != null) {
            cVar.j(view);
        }
    }

    public void b(View view) {
        this.f.remove(view);
        c cVar = this.f760c;
        if (cVar != null) {
            cVar.q(view);
            this.f760c.notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public RecyclerView.Adapter getAdapter() {
        return this.f759b;
    }

    public View getEmptyDataView() {
        return this.f761d;
    }

    public int getFooterCount() {
        return this.f.size();
    }

    public int getHeaderCount() {
        return this.e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        c cVar = this.f760c;
        if (cVar != null) {
            cVar.s();
            this.f760c = null;
        }
        c cVar2 = new c(adapter);
        this.f760c = cVar2;
        cVar2.l(this, this.g);
        View view = this.f761d;
        if (view != null) {
            this.f760c.i(view);
        }
        Iterator<View> it = this.e.iterator();
        while (it.hasNext()) {
            this.f760c.k(it.next());
        }
        Iterator<View> it2 = this.f.iterator();
        while (it2.hasNext()) {
            this.f760c.j(it2.next());
        }
        this.f759b = adapter;
        super.setAdapter(this.f760c);
    }

    public void setAdjustGrideSpanSize(boolean z) {
        this.g = z;
        c cVar = this.f760c;
        if (cVar != null) {
            cVar.l(this, z);
        }
    }

    public void setSRecyclerListener(a aVar) {
        this.h = aVar;
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new ArrayList();
        this.f = new ArrayList();
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new ArrayList();
        this.f = new ArrayList();
    }
}
