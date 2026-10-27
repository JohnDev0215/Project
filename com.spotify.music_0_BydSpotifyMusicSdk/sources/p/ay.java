package p;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ay extends View {
    public int[] c;
    public int e;
    public Context k;
    public al l;
    public String m;
    public String n;
    public HashMap o;

    /* JADX WARN: Removed duplicated region for block: B:27:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.lang.String r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L80
            int r0 = r6.length()
            if (r0 != 0) goto La
            goto L80
        La:
            android.content.Context r0 = r5.k
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
            if (r3 == 0) goto L4d
            if (r1 == 0) goto L4d
            boolean r3 = r6 instanceof java.lang.String
            if (r3 == 0) goto L41
            java.util.HashMap r3 = r1.u
            if (r3 == 0) goto L41
            boolean r3 = r3.containsKey(r6)
            if (r3 == 0) goto L41
            java.util.HashMap r3 = r1.u
            java.lang.Object r3 = r3.get(r6)
            goto L42
        L41:
            r3 = r2
        L42:
            boolean r4 = r3 instanceof java.lang.Integer
            if (r4 == 0) goto L4d
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            goto L4e
        L4d:
            r3 = 0
        L4e:
            if (r3 != 0) goto L56
            if (r1 == 0) goto L56
            int r3 = r5.d(r1, r6)
        L56:
            if (r3 != 0) goto L62
            java.lang.Class<p.qz1> r1 = p.qz1.class
            java.lang.reflect.Field r1 = r1.getField(r6)     // Catch: java.lang.Exception -> L62
            int r3 = r1.getInt(r2)     // Catch: java.lang.Exception -> L62
        L62:
            if (r3 != 0) goto L72
            android.content.res.Resources r1 = r0.getResources()
            java.lang.String r2 = "id"
            java.lang.String r0 = r0.getPackageName()
            int r3 = r1.getIdentifier(r6, r2, r0)
        L72:
            if (r3 == 0) goto L80
            java.util.HashMap r0 = r5.o
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            r0.put(r1, r6)
            r5.b(r3)
        L80:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ay.a(java.lang.String):void");
    }

    public final void b(int i) {
        if (i == getId()) {
            return;
        }
        int i2 = this.e + 1;
        int[] iArr = this.c;
        if (i2 > iArr.length) {
            this.c = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.c;
        int i3 = this.e;
        iArr2[i3] = i;
        this.e = i3 + 1;
    }

    public final void c(String str) {
        if (str == null || str.length() == 0 || this.k == null) {
            return;
        }
        String strTrim = str.trim();
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        if (constraintLayout == null) {
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof dy) && strTrim.equals(((dy) layoutParams).Y) && childAt.getId() != -1) {
                b(childAt.getId());
            }
        }
    }

    public final int d(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        String resourceEntryName;
        if (str == null || constraintLayout == null || (resources = this.k.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
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
        if (this.l == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof dy) {
            ((dy) layoutParams).p0 = this.l;
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.c, this.e);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.m;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.n;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setIds(String str) {
        this.m = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.e = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                a(str.substring(i));
                return;
            } else {
                a(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void setReferenceTags(String str) {
        this.n = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.e = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                c(str.substring(i));
                return;
            } else {
                c(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.m = null;
        this.e = 0;
        for (int i : iArr) {
            b(i);
        }
    }

    @Override // android.view.View
    public final void setTag(int i, Object obj) {
        super.setTag(i, obj);
        if (obj == null && this.m == null) {
            b(i);
        }
    }
}
