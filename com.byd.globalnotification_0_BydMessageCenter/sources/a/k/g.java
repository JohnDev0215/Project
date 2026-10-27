package a.k;

import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d f917a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static f f918b = null;

    public static <T extends ViewDataBinding> T a(f fVar, View view, int i) {
        return (T) f917a.c(fVar, view, i);
    }

    public static <T extends ViewDataBinding> T b(f fVar, ViewGroup viewGroup, int i, int i2) {
        int childCount = viewGroup.getChildCount();
        int i3 = childCount - i;
        if (i3 == 1) {
            return (T) a(fVar, viewGroup.getChildAt(childCount - 1), i2);
        }
        View[] viewArr = new View[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            viewArr[i4] = viewGroup.getChildAt(i4 + i);
        }
        return (T) f917a.d(fVar, viewArr, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x004c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T extends androidx.databinding.ViewDataBinding> T c(android.view.View r9) {
        /*
        L0:
            r0 = 0
            if (r9 == 0) goto L5a
            androidx.databinding.ViewDataBinding r1 = androidx.databinding.ViewDataBinding.m(r9)
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
        throw new UnsupportedOperationException("Method not decompiled: a.k.g.c(android.view.View):androidx.databinding.ViewDataBinding");
    }

    public static <T extends ViewDataBinding> T d(View view) {
        return (T) ViewDataBinding.m(view);
    }
}
