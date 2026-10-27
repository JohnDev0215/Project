package q;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Iterator;
import r.j0;

/* JADX INFO: renamed from: q.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeObserverOnGlobalLayoutListenerC2146c implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AbstractC2153j f21018m;

    public /* synthetic */ ViewTreeObserverOnGlobalLayoutListenerC2146c(AbstractC2153j abstractC2153j, int i7) {
        this.l = i7;
        this.f21018m = abstractC2153j;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        switch (this.l) {
            case 0:
                ViewOnKeyListenerC2148e viewOnKeyListenerC2148e = (ViewOnKeyListenerC2148e) this.f21018m;
                if (viewOnKeyListenerC2148e.j()) {
                    ArrayList arrayList = viewOnKeyListenerC2148e.f21036s;
                    if (arrayList.size() > 0 && !((C2147d) arrayList.get(0)).f21019a.f21504F) {
                        View view = viewOnKeyListenerC2148e.f21043z;
                        if (view != null && view.isShown()) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ((C2147d) it.next()).f21019a.c();
                            }
                        } else {
                            viewOnKeyListenerC2148e.dismiss();
                        }
                        break;
                    }
                }
                break;
            default:
                ViewOnKeyListenerC2160q viewOnKeyListenerC2160q = (ViewOnKeyListenerC2160q) this.f21018m;
                if (viewOnKeyListenerC2160q.j()) {
                    j0 j0Var = viewOnKeyListenerC2160q.f21107s;
                    if (!j0Var.f21504F) {
                        View view2 = viewOnKeyListenerC2160q.f21112x;
                        if (view2 != null && view2.isShown()) {
                            j0Var.c();
                        } else {
                            viewOnKeyListenerC2160q.dismiss();
                        }
                    }
                }
                break;
        }
    }
}
