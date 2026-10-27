package d.a.a.a.a.j;

import android.view.View;
import android.widget.ScrollView;

/* JADX INFO: compiled from: ScrollViewOverScrollDecorAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ScrollView f3929a;

    public c(ScrollView scrollView) {
        this.f3929a = scrollView;
    }

    @Override // d.a.a.a.a.j.a
    public boolean a() {
        return !this.f3929a.canScrollVertically(1);
    }

    @Override // d.a.a.a.a.j.a
    public boolean b() {
        return !this.f3929a.canScrollVertically(-1);
    }

    @Override // d.a.a.a.a.j.a
    public View c() {
        return this.f3929a;
    }
}
