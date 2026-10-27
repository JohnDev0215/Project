package com.byd.cleardata.ui.g;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: loaded from: classes.dex */
public class j implements com.byd.cleardata.ui.g.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final RecyclerView f2908a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final a f2909b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f2910c = false;

    protected interface a {
        boolean a();

        boolean b();
    }

    protected class b implements a {
        protected b() {
        }

        @Override // com.byd.cleardata.ui.g.j.a
        public boolean a() {
            return !j.this.f2908a.canScrollHorizontally(1);
        }

        @Override // com.byd.cleardata.ui.g.j.a
        public boolean b() {
            return !j.this.f2908a.canScrollHorizontally(-1);
        }
    }

    protected class c implements a {
        protected c() {
        }

        @Override // com.byd.cleardata.ui.g.j.a
        public boolean a() {
            return !j.this.f2908a.canScrollVertically(1);
        }

        @Override // com.byd.cleardata.ui.g.j.a
        public boolean b() {
            return !j.this.f2908a.canScrollVertically(-1);
        }
    }

    public j(RecyclerView recyclerView) {
        this.f2908a = recyclerView;
        RecyclerView.o layoutManager = recyclerView.getLayoutManager();
        boolean z = layoutManager instanceof LinearLayoutManager;
        if (!z && !(layoutManager instanceof StaggeredGridLayoutManager)) {
            throw new IllegalArgumentException("Recycler views with custom layout managers are not supported by this adapter out of the box.Try implementing and providing an explicit 'impl' parameter to the other c'tors, or otherwise create a custom adapter subclass of your own.");
        }
        this.f2909b = (z ? ((LinearLayoutManager) layoutManager).L2() : ((StaggeredGridLayoutManager) layoutManager).M2()) == 0 ? new b() : new c();
    }

    @Override // com.byd.cleardata.ui.g.c
    public boolean a() {
        return !this.f2910c && this.f2909b.a();
    }

    @Override // com.byd.cleardata.ui.g.c
    public boolean b() {
        return !this.f2910c && this.f2909b.b();
    }

    @Override // com.byd.cleardata.ui.g.c
    public View c() {
        return this.f2908a;
    }
}
