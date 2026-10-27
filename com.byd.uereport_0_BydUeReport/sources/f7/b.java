package f7;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements f7.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView f4974a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f4975b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4976c = false;

    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    public interface a {
        boolean a();

        boolean b();
    }

    /* JADX INFO: renamed from: f7.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    public class C0043b implements a {
        public C0043b() {
        }

        @Override // f7.b.a
        public boolean a() {
            return !b.this.f4974a.canScrollHorizontally(1);
        }

        @Override // f7.b.a
        public boolean b() {
            return !b.this.f4974a.canScrollHorizontally(-1);
        }
    }

    /* JADX INFO: compiled from: RecyclerViewOverScrollDecorAdapter.java */
    public class c implements a {
        public c() {
        }

        @Override // f7.b.a
        public boolean a() {
            return !b.this.f4974a.canScrollVertically(1);
        }

        @Override // f7.b.a
        public boolean b() {
            return !b.this.f4974a.canScrollVertically(-1);
        }
    }

    public b(RecyclerView recyclerView) {
        this.f4974a = recyclerView;
        RecyclerView.o layoutManager = recyclerView.getLayoutManager();
        boolean z7 = layoutManager instanceof LinearLayoutManager;
        if (!z7 && !(layoutManager instanceof StaggeredGridLayoutManager)) {
            throw new IllegalArgumentException("Recycler views with custom layout managers are not supported by this adapter out of the box.Try implementing and providing an explicit 'impl' parameter to the other c'tors, or otherwise create a custom adapter subclass of your own.");
        }
        if ((z7 ? ((LinearLayoutManager) layoutManager).getOrientation() : ((StaggeredGridLayoutManager) layoutManager).getOrientation()) == 0) {
            this.f4975b = new C0043b();
        } else {
            this.f4975b = new c();
        }
    }

    @Override // f7.a
    public boolean a() {
        return !this.f4976c && this.f4975b.a();
    }

    @Override // f7.a
    public boolean b() {
        return !this.f4976c && this.f4975b.b();
    }

    @Override // f7.a
    public View c() {
        return this.f4974a;
    }
}
