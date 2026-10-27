package p;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class yn2 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public wn2 c;
    public ViewGroup e;

    /* JADX WARN: Removed duplicated region for block: B:106:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01e3 A[EDGE_INSN: B:135:0x01e3->B:89:0x01e3 BREAK  A[LOOP:1: B:19:0x0087->B:88:0x01da], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0209  */
    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onPreDraw() {
        /*
            Method dump skipped, instruction units count: 683
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.yn2.onPreDraw():boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        ViewGroup viewGroup = this.e;
        viewGroup.getViewTreeObserver().removeOnPreDrawListener(this);
        viewGroup.removeOnAttachStateChangeListener(this);
        ArrayList arrayList = zn2.c;
        ViewGroup viewGroup2 = this.e;
        arrayList.remove(viewGroup2);
        ArrayList arrayList2 = (ArrayList) zn2.b().get(viewGroup2);
        if (arrayList2 != null && arrayList2.size() > 0) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                ((wn2) it.next()).B(viewGroup2);
            }
        }
        this.c.l(true);
    }
}
