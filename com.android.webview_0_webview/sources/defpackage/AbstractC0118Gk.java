package defpackage;

import android.content.Context;
import android.content.res.TypedArray;

/* JADX INFO: renamed from: Gk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0118Gk {
    public static final int[] a = {AbstractC0245Nm.R1};
    public static final int[] b = {AbstractC0245Nm.V1};

    public static void a(Context context, int[] iArr, String str) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(0);
        typedArrayObtainStyledAttributes.recycle();
        if (!zHasValue) {
            throw new IllegalArgumentException(Z7.d("The style on this component requires your app theme to be ", str, " (or a descendant)."));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.res.TypedArray b(android.content.Context r8, android.util.AttributeSet r9, int[] r10, int r11, int r12, int... r13) {
        /*
            int[] r0 = defpackage.AbstractC0678dn.G0
            android.content.res.TypedArray r1 = r8.obtainStyledAttributes(r9, r0, r11, r12)
            r2 = 0
            boolean r3 = r1.getBoolean(r2, r2)
            r1.recycle()
            if (r3 == 0) goto L17
            int[] r1 = defpackage.AbstractC0118Gk.b
            java.lang.String r3 = "Theme.MaterialComponents"
            a(r8, r1, r3)
        L17:
            int[] r1 = defpackage.AbstractC0118Gk.a
            java.lang.String r3 = "Theme.AppCompat"
            a(r8, r1, r3)
            android.content.res.TypedArray r0 = r8.obtainStyledAttributes(r9, r0, r11, r12)
            r1 = 1
            boolean r3 = r0.getBoolean(r1, r2)
            if (r3 != 0) goto L2d
            r0.recycle()
            goto L59
        L2d:
            r3 = -1
            int r4 = r13.length
            if (r4 != 0) goto L39
            r13 = 2
            int r13 = r0.getResourceId(r13, r3)
            if (r13 == r3) goto L54
            goto L53
        L39:
            android.content.res.TypedArray r4 = r8.obtainStyledAttributes(r9, r10, r11, r12)
            int r5 = r13.length
            r6 = 0
        L3f:
            if (r6 >= r5) goto L50
            r7 = r13[r6]
            int r7 = r4.getResourceId(r7, r3)
            if (r7 != r3) goto L4d
            r4.recycle()
            goto L54
        L4d:
            int r6 = r6 + 1
            goto L3f
        L50:
            r4.recycle()
        L53:
            r2 = 1
        L54:
            r0.recycle()
            if (r2 == 0) goto L5e
        L59:
            android.content.res.TypedArray r8 = r8.obtainStyledAttributes(r9, r10, r11, r12)
            return r8
        L5e:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant)."
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC0118Gk.b(android.content.Context, android.util.AttributeSet, int[], int, int, int[]):android.content.res.TypedArray");
    }
}
