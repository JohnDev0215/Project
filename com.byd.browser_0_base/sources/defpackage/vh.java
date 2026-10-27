package defpackage;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: compiled from: BottomAppBar.java */
/* JADX INFO: loaded from: classes.dex */
public final class vh extends FloatingActionButton.a {
    public final /* synthetic */ int a;
    public final /* synthetic */ BottomAppBar b;

    /* JADX INFO: compiled from: BottomAppBar.java */
    public class a extends FloatingActionButton.a {
        public a() {
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.a
        public final void b() {
            BottomAppBar bottomAppBar = vh.this.b;
            int i = BottomAppBar.v0;
            bottomAppBar.getClass();
        }
    }

    public vh(BottomAppBar bottomAppBar, int i) {
        this.b = bottomAppBar;
        this.a = i;
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.a
    public final void a(FloatingActionButton floatingActionButton) {
        int i = BottomAppBar.v0;
        floatingActionButton.setTranslationX(this.b.F(this.a));
        floatingActionButton.show(new a());
    }
}
