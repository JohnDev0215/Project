package r1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: renamed from: r1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2270b extends View {
    public int[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f21583m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Context f21584n;
    public o1.a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f21585p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f21586q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public HashMap f21587r;

    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.lang.String r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L97
            int r0 = r6.length()
            if (r0 != 0) goto La
            goto L97
        La:
            android.content.Context r0 = r5.f21584n
            if (r0 != 0) goto Lf
            return
        Lf:
            java.lang.String r6 = r6.trim()
            android.view.ViewParent r1 = r5.getParent()
            boolean r1 = r1 instanceof androidx.constraintlayout.widget.ConstraintLayout
            r2 = 0
            if (r1 == 0) goto L23
            android.view.ViewParent r1 = r5.getParent()
            androidx.constraintlayout.widget.ConstraintLayout r1 = (androidx.constraintlayout.widget.ConstraintLayout) r1
            goto L24
        L23:
            r1 = r2
        L24:
            boolean r3 = r5.isInEditMode()
            if (r3 == 0) goto L4b
            if (r1 == 0) goto L4b
            if (r6 == 0) goto L3f
            java.util.HashMap r3 = r1.f11712x
            if (r3 == 0) goto L3f
            boolean r3 = r3.containsKey(r6)
            if (r3 == 0) goto L3f
            java.util.HashMap r3 = r1.f11712x
            java.lang.Object r3 = r3.get(r6)
            goto L40
        L3f:
            r3 = r2
        L40:
            boolean r4 = r3 instanceof java.lang.Integer
            if (r4 == 0) goto L4b
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            goto L4c
        L4b:
            r3 = 0
        L4c:
            if (r3 != 0) goto L54
            if (r1 == 0) goto L54
            int r3 = r5.d(r1, r6)
        L54:
            if (r3 != 0) goto L60
            java.lang.Class<r1.o> r1 = r1.o.class
            java.lang.reflect.Field r1 = r1.getField(r6)     // Catch: java.lang.Exception -> L60
            int r3 = r1.getInt(r2)     // Catch: java.lang.Exception -> L60
        L60:
            if (r3 != 0) goto L70
            android.content.res.Resources r1 = r0.getResources()
            java.lang.String r2 = "id"
            java.lang.String r0 = r0.getPackageName()
            int r3 = r1.getIdentifier(r6, r2, r0)
        L70:
            if (r3 == 0) goto L7f
            java.util.HashMap r0 = r5.f21587r
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            r0.put(r1, r6)
            r5.b(r3)
            goto L97
        L7f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "Could not find id of \""
            r5.<init>(r0)
            r5.append(r6)
            java.lang.String r6 = "\""
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "ConstraintHelper"
            h6.n.t(r6, r5)
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: r1.AbstractC2270b.a(java.lang.String):void");
    }

    public final void b(int i7) {
        if (i7 == getId()) {
            return;
        }
        int i10 = this.f21583m + 1;
        int[] iArr = this.l;
        if (i10 > iArr.length) {
            this.l = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.l;
        int i11 = this.f21583m;
        iArr2[i11] = i7;
        this.f21583m = i11 + 1;
    }

    public final void c(String str) {
        if (str == null || str.length() == 0 || this.f21584n == null) {
            return;
        }
        String strTrim = str.trim();
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        if (constraintLayout == null) {
            h6.n.t("ConstraintHelper", "Parent not a ConstraintLayout");
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof C2272d) && strTrim.equals(((C2272d) layoutParams).f21612Y)) {
                if (childAt.getId() == -1) {
                    h6.n.t("ConstraintHelper", "to use ConstraintTag view " + childAt.getClass().getSimpleName() + " must have an ID");
                } else {
                    b(childAt.getId());
                }
            }
        }
    }

    public final int d(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        String resourceEntryName;
        if (str == null || constraintLayout == null || (resources = this.f21584n.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            if (childAt.getId() != -1) {
                try {
                    resourceEntryName = resources.getResourceEntryName(childAt.getId());
                } catch (Resources.NotFoundException unused) {
                    resourceEntryName = null;
                }
                if (str.equals(resourceEntryName)) {
                    return childAt.getId();
                }
            }
        }
        return 0;
    }

    public final void e() {
        if (this.o == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof C2272d) {
            ((C2272d) layoutParams).f21641p0 = this.o;
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.l, this.f21583m);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f21585p;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.f21586q;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        setMeasuredDimension(0, 0);
    }

    public void setIds(String str) {
        this.f21585p = str;
        if (str == null) {
            return;
        }
        int i7 = 0;
        this.f21583m = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i7);
            if (iIndexOf == -1) {
                a(str.substring(i7));
                return;
            } else {
                a(str.substring(i7, iIndexOf));
                i7 = iIndexOf + 1;
            }
        }
    }

    public void setReferenceTags(String str) {
        this.f21586q = str;
        if (str == null) {
            return;
        }
        int i7 = 0;
        this.f21583m = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i7);
            if (iIndexOf == -1) {
                c(str.substring(i7));
                return;
            } else {
                c(str.substring(i7, iIndexOf));
                i7 = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.f21585p = null;
        this.f21583m = 0;
        for (int i7 : iArr) {
            b(i7);
        }
    }

    @Override // android.view.View
    public final void setTag(int i7, Object obj) {
        super.setTag(i7, obj);
        if (obj == null && this.f21585p == null) {
            b(i7);
        }
    }
}
