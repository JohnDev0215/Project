package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class O6 extends C4 {
    public static boolean v(Z6 z6) {
        return (C4.k(z6.w) && C4.k(null) && C4.k(null)) ? false : true;
    }

    @Override // defpackage.C4
    public void a(Object obj, View view) {
        if (obj != null) {
            ((Z6) obj).b(view);
        }
    }

    @Override // defpackage.C4
    public void b(Object obj, ArrayList arrayList) {
        Z6 z6 = (Z6) obj;
        if (z6 == null) {
            return;
        }
        int i = 0;
        if (z6 instanceof C0866h7) {
            C0866h7 c0866h7 = (C0866h7) z6;
            int size = c0866h7.a0.size();
            while (i < size) {
                b(c0866h7.I(i), arrayList);
                i++;
            }
            return;
        }
        if (v(z6) || !C4.k(z6.x)) {
            return;
        }
        int size2 = arrayList.size();
        while (i < size2) {
            z6.b((View) arrayList.get(i));
            i++;
        }
    }

    @Override // defpackage.C4
    public void c(ViewGroup viewGroup, Object obj) {
        Z6 z6 = (Z6) obj;
        if (AbstractC0643d7.c.contains(viewGroup) || !AbstractC1251o3.u(viewGroup)) {
            return;
        }
        AbstractC0643d7.c.add(viewGroup);
        Z6 z6Clone = z6.clone();
        ArrayList arrayList = (ArrayList) AbstractC0643d7.a().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Z6) it.next()).u(viewGroup);
            }
        }
        if (z6Clone != null) {
            z6Clone.h(viewGroup, true);
        }
        viewGroup.setTag(AbstractC0371Um.b5, null);
        if (z6Clone != null) {
            ViewTreeObserverOnPreDrawListenerC0587c7 viewTreeObserverOnPreDrawListenerC0587c7 = new ViewTreeObserverOnPreDrawListenerC0587c7(z6Clone, viewGroup);
            viewGroup.addOnAttachStateChangeListener(viewTreeObserverOnPreDrawListenerC0587c7);
            viewGroup.getViewTreeObserver().addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC0587c7);
        }
    }

    @Override // defpackage.C4
    public boolean e(Object obj) {
        return obj instanceof Z6;
    }

    @Override // defpackage.C4
    public Object g(Object obj) {
        if (obj != null) {
            return ((Z6) obj).clone();
        }
        return null;
    }

    @Override // defpackage.C4
    public Object l(Object obj, Object obj2, Object obj3) {
        C0866h7 c0866h7 = new C0866h7();
        if (obj != null) {
            c0866h7.H((Z6) obj);
        }
        if (obj2 != null) {
            c0866h7.H((Z6) obj2);
        }
        if (obj3 != null) {
            c0866h7.H((Z6) obj3);
        }
        return c0866h7;
    }

    @Override // defpackage.C4
    public void m(Object obj, View view) {
        if (obj != null) {
            ((Z6) obj).w(view);
        }
    }

    @Override // defpackage.C4
    public void n(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        Z6 z6 = (Z6) obj;
        int i = 0;
        if (z6 instanceof C0866h7) {
            C0866h7 c0866h7 = (C0866h7) z6;
            int size = c0866h7.a0.size();
            while (i < size) {
                n(c0866h7.I(i), arrayList, arrayList2);
                i++;
            }
            return;
        }
        if (v(z6)) {
            return;
        }
        ArrayList arrayList3 = z6.x;
        if (arrayList3.size() != arrayList.size() || !arrayList3.containsAll(arrayList)) {
            return;
        }
        int size2 = arrayList2 == null ? 0 : arrayList2.size();
        while (i < size2) {
            z6.b((View) arrayList2.get(i));
            i++;
        }
        int size3 = arrayList.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                return;
            } else {
                z6.w((View) arrayList.get(size3));
            }
        }
    }

    @Override // defpackage.C4
    public void o(Object obj, View view, ArrayList arrayList) {
        ((Z6) obj).a(new L6(this, view, arrayList));
    }

    @Override // defpackage.C4
    public void p(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3) {
        ((Z6) obj).a(new M6(this, obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // defpackage.C4
    public void q(Object obj, Rect rect) {
        if (obj != null) {
            ((Z6) obj).A(new N6(this, rect));
        }
    }

    @Override // defpackage.C4
    public void r(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            j(view, rect);
            ((Z6) obj).A(new K6(this, rect));
        }
    }

    @Override // defpackage.C4
    public void s(Object obj, View view, ArrayList arrayList) {
        C0866h7 c0866h7 = (C0866h7) obj;
        ArrayList arrayList2 = c0866h7.x;
        arrayList2.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            C4.d(arrayList2, (View) arrayList.get(i));
        }
        arrayList2.add(view);
        arrayList.add(view);
        b(c0866h7, arrayList);
    }

    @Override // defpackage.C4
    public void t(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        C0866h7 c0866h7 = (C0866h7) obj;
        if (c0866h7 != null) {
            c0866h7.x.clear();
            c0866h7.x.addAll(arrayList2);
            n(c0866h7, arrayList, arrayList2);
        }
    }

    @Override // defpackage.C4
    public Object u(Object obj) {
        if (obj == null) {
            return null;
        }
        C0866h7 c0866h7 = new C0866h7();
        c0866h7.H((Z6) obj);
        return c0866h7;
    }
}
