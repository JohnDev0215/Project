package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.a;
import defpackage.iu0;
import defpackage.ku0;
import defpackage.qx;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {
    public int[] e;
    public int f;
    public final Context g;
    public ku0 h;
    public boolean i;
    public String j;
    public String k;
    public View[] l;
    public final HashMap<Integer, String> m;

    public ConstraintHelper(Context context) {
        super(context);
        this.e = new int[32];
        this.i = false;
        this.l = null;
        this.m = new HashMap<>();
        this.g = context;
        n(null);
    }

    public final void e(String str) {
        if (str == null || str.length() == 0 || this.g == null) {
            return;
        }
        String strTrim = str.trim();
        int iL = l(strTrim);
        if (iL != 0) {
            this.m.put(Integer.valueOf(iL), strTrim);
            f(iL);
        } else {
            Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
        }
    }

    public final void f(int i) {
        if (i == getId()) {
            return;
        }
        int i2 = this.f + 1;
        int[] iArr = this.e;
        if (i2 > iArr.length) {
            this.e = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.e;
        int i3 = this.f;
        iArr2[i3] = i;
        this.f = i3 + 1;
    }

    public final void g(String str) {
        if (str == null || str.length() == 0 || this.g == null) {
            return;
        }
        String strTrim = str.trim();
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        if (constraintLayout == null) {
            Log.w("ConstraintHelper", "Parent not a ConstraintLayout");
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof ConstraintLayout.LayoutParams) && strTrim.equals(((ConstraintLayout.LayoutParams) layoutParams).Y)) {
                if (childAt.getId() == -1) {
                    Log.w("ConstraintHelper", "to use ConstraintTag view " + childAt.getClass().getSimpleName() + " must have an ID");
                } else {
                    f(childAt.getId());
                }
            }
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.e, this.f);
    }

    public final void h() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        i((ConstraintLayout) parent);
    }

    public final void i(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = getElevation();
        for (int i = 0; i < this.f; i++) {
            View viewC = constraintLayout.c(this.e[i]);
            if (viewC != null) {
                viewC.setVisibility(visibility);
                if (elevation > 0.0f) {
                    viewC.setTranslationZ(viewC.getTranslationZ() + elevation);
                }
            }
        }
    }

    public final int k(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        String resourceEntryName;
        if (str == null || constraintLayout == null || (resources = this.g.getResources()) == null) {
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

    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int l(java.lang.String r5) {
        /*
            r4 = this;
            android.view.ViewParent r0 = r4.getParent()
            boolean r0 = r0 instanceof androidx.constraintlayout.widget.ConstraintLayout
            r1 = 0
            if (r0 == 0) goto L10
            android.view.ViewParent r0 = r4.getParent()
            androidx.constraintlayout.widget.ConstraintLayout r0 = (androidx.constraintlayout.widget.ConstraintLayout) r0
            goto L11
        L10:
            r0 = r1
        L11:
            boolean r2 = r4.isInEditMode()
            if (r2 == 0) goto L3a
            if (r0 == 0) goto L3a
            boolean r2 = r5 instanceof java.lang.String
            if (r2 == 0) goto L2e
            java.util.HashMap<java.lang.String, java.lang.Integer> r2 = r0.q
            if (r2 == 0) goto L2e
            boolean r2 = r2.containsKey(r5)
            if (r2 == 0) goto L2e
            java.util.HashMap<java.lang.String, java.lang.Integer> r2 = r0.q
            java.lang.Object r2 = r2.get(r5)
            goto L2f
        L2e:
            r2 = r1
        L2f:
            boolean r3 = r2 instanceof java.lang.Integer
            if (r3 == 0) goto L3a
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            goto L3b
        L3a:
            r2 = 0
        L3b:
            if (r2 != 0) goto L43
            if (r0 == 0) goto L43
            int r2 = r4.k(r0, r5)
        L43:
            if (r2 != 0) goto L4f
            java.lang.Class<androidx.constraintlayout.widget.R$id> r0 = androidx.constraintlayout.widget.R$id.class
            java.lang.reflect.Field r0 = r0.getField(r5)     // Catch: java.lang.Exception -> L4f
            int r2 = r0.getInt(r1)     // Catch: java.lang.Exception -> L4f
        L4f:
            if (r2 != 0) goto L61
            android.content.Context r4 = r4.g
            android.content.res.Resources r0 = r4.getResources()
            java.lang.String r1 = "id"
            java.lang.String r4 = r4.getPackageName()
            int r2 = r0.getIdentifier(r5, r1, r4)
        L61:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintHelper.l(java.lang.String):int");
    }

    public final View[] m(ConstraintLayout constraintLayout) {
        View[] viewArr = this.l;
        if (viewArr == null || viewArr.length != this.f) {
            this.l = new View[this.f];
        }
        for (int i = 0; i < this.f; i++) {
            this.l[i] = constraintLayout.c(this.e[i]);
        }
        return this.l;
    }

    public void n(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.j = string;
                    setIds(string);
                } else if (index == R$styleable.ConstraintLayout_Layout_constraint_referenced_tags) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.k = string2;
                    setReferenceTags(string2);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void o(a.C0004a c0004a, ku0 ku0Var, Constraints.LayoutParams layoutParams, SparseArray sparseArray) {
        a.b bVar = c0004a.e;
        int[] iArr = bVar.j0;
        int i = 0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = bVar.k0;
            if (str != null) {
                if (str.length() > 0) {
                    String[] strArrSplit = bVar.k0.split(",");
                    int[] iArrCopyOf = new int[strArrSplit.length];
                    int i2 = 0;
                    for (String str2 : strArrSplit) {
                        int iL = l(str2.trim());
                        if (iL != 0) {
                            iArrCopyOf[i2] = iL;
                            i2++;
                        }
                    }
                    if (i2 != strArrSplit.length) {
                        iArrCopyOf = Arrays.copyOf(iArrCopyOf, i2);
                    }
                    bVar.j0 = iArrCopyOf;
                } else {
                    bVar.j0 = null;
                }
            }
        }
        ku0Var.b();
        if (bVar.j0 == null) {
            return;
        }
        while (true) {
            int[] iArr2 = bVar.j0;
            if (i >= iArr2.length) {
                return;
            }
            qx qxVar = (qx) sparseArray.get(iArr2[i]);
            if (qxVar != null) {
                ku0Var.a(qxVar);
            }
            i++;
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.j;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.k;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.i) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void s(iu0 iu0Var, SparseArray sparseArray) {
        iu0Var.b();
        for (int i = 0; i < this.f; i++) {
            iu0Var.a((qx) sparseArray.get(this.e[i]));
        }
    }

    public void setIds(String str) {
        this.j = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.f = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                e(str.substring(i));
                return;
            } else {
                e(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void setReferenceTags(String str) {
        this.k = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.f = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                g(str.substring(i));
                return;
            } else {
                g(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.j = null;
        this.f = 0;
        for (int i : iArr) {
            f(i);
        }
    }

    @Override // android.view.View
    public final void setTag(int i, Object obj) {
        super.setTag(i, obj);
        if (obj == null && this.j == null) {
            f(i);
        }
    }

    public final void t() {
        if (this.h == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).q0 = this.h;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new int[32];
        this.i = false;
        this.l = null;
        this.m = new HashMap<>();
        this.g = context;
        n(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new int[32];
        this.i = false;
        this.l = null;
        this.m = new HashMap<>();
        this.g = context;
        n(attributeSet);
    }

    public void q() {
    }

    public void j(ConstraintLayout constraintLayout) {
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    public void r(ConstraintLayout constraintLayout) {
    }

    public void p(qx qxVar, boolean z) {
    }
}
