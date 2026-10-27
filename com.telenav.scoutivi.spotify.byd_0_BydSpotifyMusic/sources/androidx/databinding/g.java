package androidx.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: DataBindingUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static e f2070a = new DataBinderMapperImpl();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static f f2071b = null;

    public static <T extends ViewDataBinding> T a(View view) {
        return (T) b(view, f2071b);
    }

    public static <T extends ViewDataBinding> T b(View view, f fVar) {
        T t6 = (T) g(view);
        if (t6 != null) {
            return t6;
        }
        Object tag = view.getTag();
        if (!(tag instanceof String)) {
            throw new IllegalArgumentException("View is not a binding layout");
        }
        int iD = f2070a.d((String) tag);
        if (iD != 0) {
            return (T) f2070a.b(fVar, view, iD);
        }
        throw new IllegalArgumentException("View is not a binding layout. Tag: " + tag);
    }

    static <T extends ViewDataBinding> T c(f fVar, View view, int i7) {
        return (T) f2070a.b(fVar, view, i7);
    }

    static <T extends ViewDataBinding> T d(f fVar, View[] viewArr, int i7) {
        return (T) f2070a.c(fVar, viewArr, i7);
    }

    private static <T extends ViewDataBinding> T e(f fVar, ViewGroup viewGroup, int i7, int i8) {
        int childCount = viewGroup.getChildCount();
        int i9 = childCount - i7;
        if (i9 == 1) {
            return (T) c(fVar, viewGroup.getChildAt(childCount - 1), i8);
        }
        View[] viewArr = new View[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            viewArr[i10] = viewGroup.getChildAt(i10 + i7);
        }
        return (T) d(fVar, viewArr, i8);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x004c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T extends androidx.databinding.ViewDataBinding> T f(android.view.View r9) {
        /*
        L0:
            r0 = 0
            if (r9 == 0) goto L5a
            androidx.databinding.ViewDataBinding r1 = androidx.databinding.ViewDataBinding.D(r9)
            if (r1 == 0) goto La
            return r1
        La:
            java.lang.Object r1 = r9.getTag()
            boolean r2 = r1 instanceof java.lang.String
            if (r2 == 0) goto L4d
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "layout"
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L4d
            java.lang.String r2 = "_0"
            boolean r2 = r1.endsWith(r2)
            if (r2 == 0) goto L4d
            r2 = 6
            char r2 = r1.charAt(r2)
            r3 = 7
            r4 = 47
            int r3 = r1.indexOf(r4, r3)
            r5 = 1
            r6 = -1
            r7 = 0
            if (r2 != r4) goto L3b
            if (r3 != r6) goto L38
            goto L39
        L38:
            r5 = r7
        L39:
            r7 = r5
            goto L4a
        L3b:
            r8 = 45
            if (r2 != r8) goto L4a
            if (r3 == r6) goto L4a
            int r3 = r3 + 1
            int r1 = r1.indexOf(r4, r3)
            if (r1 != r6) goto L38
            goto L39
        L4a:
            if (r7 == 0) goto L4d
            return r0
        L4d:
            android.view.ViewParent r9 = r9.getParent()
            boolean r1 = r9 instanceof android.view.View
            if (r1 == 0) goto L58
            android.view.View r9 = (android.view.View) r9
            goto L0
        L58:
            r9 = r0
            goto L0
        L5a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.databinding.g.f(android.view.View):androidx.databinding.ViewDataBinding");
    }

    public static <T extends ViewDataBinding> T g(View view) {
        return (T) ViewDataBinding.D(view);
    }

    public static f h() {
        return f2071b;
    }

    public static <T extends ViewDataBinding> T i(LayoutInflater layoutInflater, int i7, ViewGroup viewGroup, boolean z6) {
        return (T) j(layoutInflater, i7, viewGroup, z6, f2071b);
    }

    public static <T extends ViewDataBinding> T j(LayoutInflater layoutInflater, int i7, ViewGroup viewGroup, boolean z6, f fVar) {
        boolean z7 = viewGroup != null && z6;
        return z7 ? (T) e(fVar, viewGroup, z7 ? viewGroup.getChildCount() : 0, i7) : (T) c(fVar, layoutInflater.inflate(i7, viewGroup, z6), i7);
    }
}
