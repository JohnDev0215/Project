package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: renamed from: g6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0809g6 extends AbstractC0750f3 {
    public final RecyclerView c;
    public final AbstractC0750f3 d = new C0753f6(this);

    public C0809g6(RecyclerView recyclerView) {
        this.c = recyclerView;
    }

    @Override // defpackage.AbstractC0750f3
    public void a(View view, AccessibilityEvent accessibilityEvent) {
        W5 w5;
        AbstractC0750f3.b.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName(RecyclerView.class.getName());
        if (!(view instanceof RecyclerView) || d() || (w5 = ((RecyclerView) view).B) == null) {
            return;
        }
        w5.H(accessibilityEvent);
    }

    @Override // defpackage.AbstractC0750f3
    public void b(View view, C1525t3 c1525t3) {
        W5 w5;
        super.b(view, c1525t3);
        c1525t3.a.setClassName(RecyclerView.class.getName());
        if (d() || (w5 = this.c.B) == null) {
            return;
        }
        RecyclerView recyclerView = w5.b;
        C0474a6 c0474a6 = recyclerView.s;
        C0586c6 c0586c6 = recyclerView.r0;
        if (recyclerView.canScrollVertically(-1) || w5.b.canScrollHorizontally(-1)) {
            c1525t3.a.addAction(8192);
            c1525t3.a.setScrollable(true);
        }
        if (w5.b.canScrollVertically(1) || w5.b.canScrollHorizontally(1)) {
            c1525t3.a.addAction(4096);
            c1525t3.a.setScrollable(true);
        }
        c1525t3.a.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(w5.z(c0474a6, c0586c6), w5.r(c0474a6, c0586c6), w5.C(), w5.A()));
    }

    @Override // defpackage.AbstractC0750f3
    public boolean c(View view, int i, Bundle bundle) {
        W5 w5;
        if (super.c(view, i, bundle)) {
            return true;
        }
        if (d() || (w5 = this.c.B) == null) {
            return false;
        }
        C0474a6 c0474a6 = w5.b.s;
        return w5.V(i);
    }

    public boolean d() {
        return this.c.t();
    }
}
