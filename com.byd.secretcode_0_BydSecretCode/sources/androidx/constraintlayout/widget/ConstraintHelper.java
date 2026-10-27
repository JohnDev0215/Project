package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bydux.api.common.tools.util.ListUtils;
import defpackage.a6;
import defpackage.q6;
import defpackage.w5;
import defpackage.z5;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {
    public int[] b;
    public int c;
    public Context d;
    public z5 e;
    public boolean f;
    public String g;
    public View[] h;
    public HashMap<Integer, String> i;

    public ConstraintHelper(Context context) {
        super(context);
        this.b = new int[32];
        this.f = false;
        this.h = null;
        this.i = new HashMap<>();
        this.d = context;
        m(null);
    }

    public final void e(String str) {
        if (str == null || str.length() == 0 || this.d == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iK = k(strTrim);
        if (iK != 0) {
            this.i.put(Integer.valueOf(iK), strTrim);
            f(iK);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    public final void f(int i) {
        if (i == getId()) {
            return;
        }
        int i2 = this.c + 1;
        int[] iArr = this.b;
        if (i2 > iArr.length) {
            this.b = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.b;
        int i3 = this.c;
        iArr2[i3] = i;
        this.c = i3 + 1;
    }

    public void g() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        h((ConstraintLayout) parent);
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.b, this.c);
    }

    public void h(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i = 0; i < this.c; i++) {
            View viewG = constraintLayout.g(this.b[i]);
            if (viewG != null) {
                viewG.setVisibility(visibility);
                if (elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                    viewG.setTranslationZ(viewG.getTranslationZ() + elevation);
                }
            }
        }
    }

    public final int[] i(View view, String str) {
        String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
        view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        for (String str2 : strArrSplit) {
            int iK = k(str2.trim());
            if (iK != 0) {
                iArr[i] = iK;
                i++;
            }
        }
        return i != strArrSplit.length ? Arrays.copyOf(iArr, i) : iArr;
    }

    public final int j(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.d.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            if (childAt.getId() != -1) {
                String resourceEntryName = null;
                try {
                    resourceEntryName = resources.getResourceEntryName(childAt.getId());
                } catch (Resources.NotFoundException unused) {
                }
                if (str.equals(resourceEntryName)) {
                    return childAt.getId();
                }
            }
        }
        return 0;
    }

    public final int k(String str) {
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        int iJ = 0;
        if (isInEditMode() && constraintLayout != null) {
            Object objE = constraintLayout.e(0, str);
            if (objE instanceof Integer) {
                iJ = ((Integer) objE).intValue();
            }
        }
        if (iJ == 0 && constraintLayout != null) {
            iJ = j(constraintLayout, str);
        }
        if (iJ == 0) {
            try {
                iJ = R$id.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return iJ == 0 ? this.d.getResources().getIdentifier(str, "id", this.d.getPackageName()) : iJ;
    }

    public View[] l(ConstraintLayout constraintLayout) {
        View[] viewArr = this.h;
        if (viewArr == null || viewArr.length != this.c) {
            this.h = new View[this.c];
        }
        for (int i = 0; i < this.c; i++) {
            this.h[i] = constraintLayout.g(this.b[i]);
        }
        return this.h;
    }

    public void m(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.g = string;
                    setIds(string);
                }
            }
        }
    }

    public void n(q6.a aVar, a6 a6Var, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        q6.b bVar2 = aVar.d;
        int[] iArr = bVar2.f0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = bVar2.g0;
            if (str != null && str.length() > 0) {
                q6.b bVar3 = aVar.d;
                bVar3.f0 = i(this, bVar3.g0);
            }
        }
        a6Var.c();
        if (aVar.d.f0 == null) {
            return;
        }
        int i = 0;
        while (true) {
            int[] iArr2 = aVar.d.f0;
            if (i >= iArr2.length) {
                return;
            }
            ConstraintWidget constraintWidget = sparseArray.get(iArr2[i]);
            if (constraintWidget != null) {
                a6Var.a(constraintWidget);
            }
            i++;
        }
    }

    public void o(ConstraintWidget constraintWidget, boolean z) {
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.g;
        if (str != null) {
            setIds(str);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.f) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void p(ConstraintLayout constraintLayout) {
    }

    public void q(ConstraintLayout constraintLayout) {
    }

    public void r(ConstraintLayout constraintLayout) {
    }

    public void s(w5 w5Var, z5 z5Var, SparseArray<ConstraintWidget> sparseArray) {
        z5Var.c();
        for (int i = 0; i < this.c; i++) {
            z5Var.a(sparseArray.get(this.b[i]));
        }
    }

    public void setIds(String str) {
        this.g = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.c = 0;
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

    public void setReferencedIds(int[] iArr) {
        this.g = null;
        this.c = 0;
        for (int i : iArr) {
            f(i);
        }
    }

    public void t(ConstraintLayout constraintLayout) {
        String str;
        int iJ;
        if (isInEditMode()) {
            setIds(this.g);
        }
        z5 z5Var = this.e;
        if (z5Var == null) {
            return;
        }
        z5Var.c();
        for (int i = 0; i < this.c; i++) {
            int i2 = this.b[i];
            View viewG = constraintLayout.g(i2);
            if (viewG == null && (iJ = j(constraintLayout, (str = this.i.get(Integer.valueOf(i2))))) != 0) {
                this.b[i] = iJ;
                this.i.put(Integer.valueOf(iJ), str);
                viewG = constraintLayout.g(iJ);
            }
            if (viewG != null) {
                this.e.a(constraintLayout.k(viewG));
            }
        }
        this.e.b(constraintLayout.d);
    }

    public void u() {
        if (this.e == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).n0 = (ConstraintWidget) this.e;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new int[32];
        this.f = false;
        this.h = null;
        this.i = new HashMap<>();
        this.d = context;
        m(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new int[32];
        this.f = false;
        this.h = null;
        this.i = new HashMap<>();
        this.d = context;
        m(attributeSet);
    }
}
