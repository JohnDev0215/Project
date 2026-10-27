package b.b.g.r;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/* JADX INFO: compiled from: SearchScrollerUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class s {

    /* JADX INFO: compiled from: SearchScrollerUtils.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1832a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ScrollView f1833b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Rect f1834c;

        public a(View view, ScrollView scrollView, Rect rect) {
            this.f1832a = view;
            this.f1833b = scrollView;
            this.f1834c = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            int i2;
            Rect rect = new Rect();
            this.f1832a.getLocalVisibleRect(rect);
            if (rect.left != 0) {
                if (rect.top > this.f1834c.height()) {
                    this.f1833b.scrollBy(0, rect.bottom - this.f1834c.height());
                }
                if (rect.top >= 0 || (i = rect.bottom) >= 0) {
                    return;
                }
                this.f1833b.scrollBy(0, i + rect.height());
                return;
            }
            int i3 = rect.top;
            if (i3 == 0) {
                if (rect.bottom < this.f1832a.getHeight()) {
                    this.f1833b.scrollBy(0, this.f1832a.getHeight() - rect.bottom);
                }
            } else {
                if (i3 <= 0) {
                    if (i3 >= 0 || (i2 = rect.bottom) >= 0) {
                        return;
                    }
                    this.f1833b.scrollBy(0, i2 + rect.height());
                    return;
                }
                if (i3 < rect.height()) {
                    this.f1833b.scrollBy(0, -rect.top);
                }
                if (rect.top > this.f1834c.height()) {
                    this.f1833b.scrollBy(0, rect.bottom - this.f1834c.height());
                }
            }
        }
    }

    public static void a(ScrollView scrollView, int i, boolean z) {
        if (z && scrollView != null) {
            if (i == 0) {
                scrollView.smoothScrollTo(0, 0);
                return;
            }
            ViewGroup viewGroup = (ViewGroup) scrollView.getChildAt(0);
            View childAt = viewGroup != null ? viewGroup.getChildAt(i) : null;
            if (childAt != null && childAt.getVisibility() == 0) {
                scrollView.getHeight();
                Rect rect = new Rect();
                scrollView.getLocalVisibleRect(rect);
                childAt.post(new a(childAt, scrollView, rect));
            }
        }
    }
}
