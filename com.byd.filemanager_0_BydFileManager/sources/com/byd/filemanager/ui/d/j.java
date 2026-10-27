package com.byd.filemanager.ui.d;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements com.byd.filemanager.ui.d.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final RecyclerView f3813a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final a f3814b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f3815c = false;

    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    protected interface a {
        boolean a();

        boolean b();
    }

    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    protected class b implements a {
        protected b() {
        }

        @Override // com.byd.filemanager.ui.d.j.a
        public boolean a() {
            return !j.this.f3813a.canScrollHorizontally(1);
        }

        @Override // com.byd.filemanager.ui.d.j.a
        public boolean b() {
            return !j.this.f3813a.canScrollHorizontally(-1);
        }
    }

    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    protected class c implements a {
        protected c() {
        }

        @Override // com.byd.filemanager.ui.d.j.a
        public boolean a() {
            return !j.this.f3813a.canScrollVertically(1);
        }

        @Override // com.byd.filemanager.ui.d.j.a
        public boolean b() {
            return !j.this.f3813a.canScrollVertically(-1);
        }
    }

    public j(RecyclerView recyclerView) {
        this.f3813a = recyclerView;
        RecyclerView.o layoutManager = recyclerView.getLayoutManager();
        boolean z = layoutManager instanceof LinearLayoutManager;
        if (!z && !(layoutManager instanceof StaggeredGridLayoutManager)) {
            throw new IllegalArgumentException("Recycler views with custom layout managers are not supported by this adapter out of the box.Try implementing and providing an explicit 'impl' parameter to the other c'tors, or otherwise create a custom adapter subclass of your own.");
        }
        if ((z ? ((LinearLayoutManager) layoutManager).B2() : ((StaggeredGridLayoutManager) layoutManager).A2()) == 0) {
            this.f3814b = new b();
        } else {
            this.f3814b = new c();
        }
    }

    @Override // com.byd.filemanager.ui.d.c
    public boolean a() {
        return !this.f3815c && this.f3814b.a();
    }

    @Override // com.byd.filemanager.ui.d.c
    public boolean b() {
        return !this.f3815c && this.f3814b.b();
    }

    @Override // com.byd.filemanager.ui.d.c
    public View c() {
        return this.f3813a;
    }
}
