package b.d.a.a.o;

import androidx.appcompat.widget.ActionMenuView;
import com.google.android.material.bottomappbar.BottomAppBar;

/* JADX INFO: loaded from: classes.dex */
public class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ActionMenuView f2029a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f2030b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f2031c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ BottomAppBar f2032d;

    public e(BottomAppBar bottomAppBar, ActionMenuView actionMenuView, int i, boolean z) {
        this.f2032d = bottomAppBar;
        this.f2029a = actionMenuView;
        this.f2030b = i;
        this.f2031c = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f2029a.setTranslationX(this.f2032d.B(r0, this.f2030b, this.f2031c));
    }
}
