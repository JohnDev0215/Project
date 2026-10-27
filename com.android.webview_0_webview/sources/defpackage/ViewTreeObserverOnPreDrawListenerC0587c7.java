package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: c7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewTreeObserverOnPreDrawListenerC0587c7 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public Z6 s;
    public ViewGroup t;

    public ViewTreeObserverOnPreDrawListenerC0587c7(Z6 z6, ViewGroup viewGroup) {
        this.s = z6;
        this.t = viewGroup;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01f2 A[EDGE_INSN: B:139:0x01f2->B:95:0x01f2 BREAK  A[LOOP:1: B:19:0x0099->B:94:0x01e9], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01f7  */
    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onPreDraw() {
        /*
            Method dump skipped, instruction units count: 678
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ViewTreeObserverOnPreDrawListenerC0587c7.onPreDraw():boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.t.getViewTreeObserver().removeOnPreDrawListener(this);
        this.t.removeOnAttachStateChangeListener(this);
        AbstractC0643d7.c.remove(this.t);
        ArrayList arrayList = (ArrayList) AbstractC0643d7.a().get(this.t);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Z6) it.next()).x(this.t);
            }
        }
        this.s.i(true);
    }
}
