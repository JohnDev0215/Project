package f7;

import android.view.View;
import android.widget.ScrollView;

/* JADX INFO: compiled from: ScrollViewOverScrollDecorAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ScrollView f4979a;

    public c(ScrollView scrollView) {
        this.f4979a = scrollView;
    }

    @Override // f7.a
    public boolean a() {
        return !this.f4979a.canScrollVertically(1);
    }

    @Override // f7.a
    public boolean b() {
        return !this.f4979a.canScrollVertically(-1);
    }

    @Override // f7.a
    public View c() {
        return this.f4979a;
    }
}
