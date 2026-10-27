package c.a.d.q;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements c.a.d.q.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView f3666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f3667b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3668c = false;

    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    public interface a {
        boolean a();

        boolean b();
    }

    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    public class b implements a {
        public b() {
        }

        @Override // c.a.d.q.j.a
        public boolean a() {
            return !j.this.f3666a.canScrollHorizontally(1);
        }

        @Override // c.a.d.q.j.a
        public boolean b() {
            return !j.this.f3666a.canScrollHorizontally(-1);
        }
    }

    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    public class c implements a {
        public c() {
        }

        @Override // c.a.d.q.j.a
        public boolean a() {
            return !j.this.f3666a.canScrollVertically(1);
        }

        @Override // c.a.d.q.j.a
        public boolean b() {
            return !j.this.f3666a.canScrollVertically(-1);
        }
    }

    public j(RecyclerView recyclerView) {
        this.f3666a = recyclerView;
        RecyclerView.o layoutManager = recyclerView.getLayoutManager();
        boolean z = layoutManager instanceof LinearLayoutManager;
        if (!z && !(layoutManager instanceof StaggeredGridLayoutManager)) {
            throw new IllegalArgumentException("Recycler views with custom layout managers are not supported by this adapter out of the box.Try implementing and providing an explicit 'impl' parameter to the other c'tors, or otherwise create a custom adapter subclass of your own.");
        }
        if ((z ? ((LinearLayoutManager) layoutManager).p2() : ((StaggeredGridLayoutManager) layoutManager).q2()) == 0) {
            this.f3667b = new b();
        } else {
            this.f3667b = new c();
        }
    }

    @Override // c.a.d.q.c
    public boolean a() {
        return !this.f3668c && this.f3667b.a();
    }

    @Override // c.a.d.q.c
    public boolean b() {
        return !this.f3668c && this.f3667b.b();
    }

    @Override // c.a.d.q.c
    public View c() {
        return this.f3666a;
    }
}
