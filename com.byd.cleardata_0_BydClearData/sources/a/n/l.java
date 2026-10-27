package a.n;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewGroup f564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Runnable f565b;

    public static l b(ViewGroup viewGroup) {
        return (l) viewGroup.getTag(j.f548c);
    }

    static void c(ViewGroup viewGroup, l lVar) {
        viewGroup.setTag(j.f548c, lVar);
    }

    public void a() {
        Runnable runnable;
        if (b(this.f564a) != this || (runnable = this.f565b) == null) {
            return;
        }
        runnable.run();
    }
}
