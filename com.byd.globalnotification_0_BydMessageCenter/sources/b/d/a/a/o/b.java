package b.d.a.a.o;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public class b extends FloatingActionButton.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2020a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ BottomAppBar f2021b;

    public class a extends FloatingActionButton.a {
        public a() {
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.a
        public void b(FloatingActionButton floatingActionButton) {
            BottomAppBar.w(b.this.f2021b);
        }
    }

    public b(BottomAppBar bottomAppBar, int i) {
        this.f2021b = bottomAppBar;
        this.f2020a = i;
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.a
    public void a(FloatingActionButton floatingActionButton) {
        floatingActionButton.setTranslationX(this.f2021b.C(this.f2020a));
        floatingActionButton.n(new a(), true);
    }
}
