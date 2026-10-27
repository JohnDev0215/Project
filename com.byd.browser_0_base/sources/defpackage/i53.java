package defpackage;

import android.graphics.Rect;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Comparator;
import java.util.PriorityQueue;
import org.chromium.chrome.browser.tab.TabImpl;

/* JADX INFO: compiled from: TabViewManagerImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i53 implements Comparator<j53> {
    public static final int[] j = {0, 2, 1};
    public static final SparseIntArray k = new SparseIntArray();
    public TabImpl f;
    public View g;
    public ij h;
    public final Rect i = new Rect();
    public final PriorityQueue<j53> e = new PriorityQueue<>(3, this);

    static {
        int i = 0;
        while (true) {
            int[] iArr = j;
            if (i >= iArr.length) {
                return;
            }
            k.put(iArr[i], i);
            i++;
        }
    }

    public i53(TabImpl tabImpl) {
        this.f = tabImpl;
    }

    public final void a(j53 j53Var) {
        PriorityQueue<j53> priorityQueue = this.e;
        if (priorityQueue.contains(j53Var)) {
            return;
        }
        j53 j53VarPeek = priorityQueue.peek();
        priorityQueue.add(j53Var);
        d(j53VarPeek);
    }

    public final boolean b(j53 j53Var) {
        j53 j53VarPeek = this.e.peek();
        return j53VarPeek != null && j53VarPeek == j53Var;
    }

    public final void c(j53 j53Var) {
        PriorityQueue<j53> priorityQueue = this.e;
        j53 j53VarPeek = priorityQueue.peek();
        priorityQueue.remove(j53Var);
        d(j53VarPeek);
    }

    @Override // java.util.Comparator
    public final int compare(j53 j53Var, j53 j53Var2) {
        SparseIntArray sparseIntArray = k;
        return sparseIntArray.get(j53Var.q0()) - sparseIntArray.get(j53Var2.q0());
    }

    public final void d(j53 j53Var) {
        j53 j53VarPeek;
        View view;
        if (this.f == null || (j53VarPeek = this.e.peek()) == j53Var) {
            return;
        }
        if (j53VarPeek != null) {
            view = j53VarPeek.getView();
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
        } else {
            view = null;
        }
        this.g = view;
        if (this.f.M() != null && !this.f.M().d() && this.h == null) {
            ij ijVar = new ij(this.f.M().z2());
            this.h = ijVar;
            ijVar.p(new lj1(29, this));
            Rect rect = (Rect) this.h.f;
            if (rect != null) {
                this.i.set(rect);
                e();
            }
        }
        e();
        TabImpl tabImpl = this.f;
        tabImpl.i = this.g;
        tabImpl.U();
        if (j53Var != null) {
            j53Var.S();
        }
        if (j53VarPeek != null) {
            j53VarPeek.r();
        }
    }

    public final void e() {
        if (this.g == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        Rect rect = this.i;
        layoutParams.setMargins(rect.left, rect.top, rect.right, rect.bottom);
        this.g.setLayoutParams(layoutParams);
    }
}
