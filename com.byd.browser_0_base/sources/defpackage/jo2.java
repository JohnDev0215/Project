package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import defpackage.zo2;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: SkinCompatDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public final class jo2 implements LayoutInflater.Factory2 {
    public final Context e;
    public zo2 f;
    public final CopyOnWriteArrayList g = new CopyOnWriteArrayList();

    public jo2(Context context) {
        this.e = context;
    }

    public final View a(View view, String str, Context context, AttributeSet attributeSet) {
        if (this.f == null) {
            this.f = new zo2();
        }
        Iterator it = no2.o.i.iterator();
        while (it.hasNext()) {
            Context contextB = ((lp2) it.next()).b(this.e, attributeSet);
            if (contextB != null) {
                context = contextB;
            }
        }
        zo2 zo2Var = this.f;
        zo2Var.getClass();
        Iterator it2 = no2.o.k.iterator();
        View view2 = null;
        View viewA = null;
        while (it2.hasNext() && (viewA = ((bp2) it2.next()).a(str, context, attributeSet)) == null) {
        }
        if (viewA == null) {
            Iterator it3 = no2.o.j.iterator();
            viewA = null;
            while (it3.hasNext() && (viewA = ((bp2) it3.next()).a(str, context, attributeSet)) == null) {
            }
        }
        if (viewA == null) {
            Object[] objArr = zo2Var.a;
            if ("view".equals(str)) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                objArr[0] = context;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i = 0;
                    while (true) {
                        String[] strArr = zo2.d;
                        if (i >= 3) {
                            break;
                        }
                        View viewA2 = zo2Var.a(context, str, strArr[i]);
                        if (viewA2 != null) {
                            objArr[0] = null;
                            objArr[1] = null;
                            view2 = viewA2;
                            break;
                        }
                        i++;
                    }
                } else {
                    View viewA3 = zo2Var.a(context, str, null);
                    objArr[0] = null;
                    objArr[1] = null;
                    view2 = viewA3;
                }
            } catch (Exception unused) {
            } finally {
                objArr[0] = view2;
                objArr[1] = view2;
            }
            viewA = view2;
        }
        if (viewA != null) {
            Context context2 = viewA.getContext();
            if ((context2 instanceof ContextWrapper) && viewA.hasOnClickListeners()) {
                TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, zo2.c);
                String string = typedArrayObtainStyledAttributes.getString(0);
                if (string != null) {
                    viewA.setOnClickListener(new zo2.a(viewA, string));
                }
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        return viewA;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewA = a(view, str, context, attributeSet);
        if (viewA == 0) {
            return null;
        }
        if (viewA instanceof ro2) {
            this.g.add(new WeakReference((ro2) viewA));
        }
        return viewA;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewA = a(null, str, context, attributeSet);
        if (viewA == 0) {
            return null;
        }
        if (viewA instanceof ro2) {
            this.g.add(new WeakReference((ro2) viewA));
        }
        return viewA;
    }
}
