package d.a.a.a.a.j;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public class b implements d.a.a.a.a.j.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView f3924a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f3925b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3926c = false;

    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    public interface a {
        boolean a();

        boolean b();
    }

    /* JADX INFO: renamed from: d.a.a.a.a.j.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    public class C0105b implements a {
        public C0105b() {
        }

        @Override // d.a.a.a.a.j.b.a
        public boolean a() {
            return !b.this.f3924a.canScrollHorizontally(1);
        }

        @Override // d.a.a.a.a.j.b.a
        public boolean b() {
            return !b.this.f3924a.canScrollHorizontally(-1);
        }
    }

    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    public class c implements a {
        public c() {
        }

        @Override // d.a.a.a.a.j.b.a
        public boolean a() {
            return !b.this.f3924a.canScrollVertically(1);
        }

        @Override // d.a.a.a.a.j.b.a
        public boolean b() {
            return !b.this.f3924a.canScrollVertically(-1);
        }
    }

    public b(RecyclerView recyclerView) {
        this.f3924a = recyclerView;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        boolean z = layoutManager instanceof LinearLayoutManager;
        if (!z && !(layoutManager instanceof StaggeredGridLayoutManager)) {
            throw new IllegalArgumentException("Recycler views with custom layout managers are not supported by this adapter out of the box.Try implementing and providing an explicit 'impl' parameter to the other c'tors, or otherwise create a custom adapter subclass of your own.");
        }
        if ((z ? ((LinearLayoutManager) layoutManager).getOrientation() : ((StaggeredGridLayoutManager) layoutManager).getOrientation()) == 0) {
            this.f3925b = new C0105b();
        } else {
            this.f3925b = new c();
        }
    }

    @Override // d.a.a.a.a.j.a
    public boolean a() {
        return !this.f3926c && this.f3925b.a();
    }

    @Override // d.a.a.a.a.j.a
    public boolean b() {
        return !this.f3926c && this.f3925b.b();
    }

    @Override // d.a.a.a.a.j.a
    public View c() {
        return this.f3924a;
    }
}
