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
import b.e.b.j.g;
import b.e.b.j.h;
import b.e.c.b;
import b.e.c.d;
import b.e.c.e;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {
    public String E3;
    public View[] F3;
    public HashMap<Integer, String> G3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f393c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f394d;
    public Context s;
    public g x;
    public boolean y;

    public ConstraintHelper(Context context) {
        super(context);
        this.f393c = new int[32];
        this.y = false;
        this.F3 = null;
        this.G3 = new HashMap<>();
        this.s = context;
        m(null);
    }

    public final void e(String str) {
        if (str == null || str.length() == 0 || this.s == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iK = k(strTrim);
        if (iK != 0) {
            this.G3.put(Integer.valueOf(iK), strTrim);
            f(iK);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    public final void f(int i2) {
        if (i2 == getId()) {
            return;
        }
        int i3 = this.f394d + 1;
        int[] iArr = this.f393c;
        if (i3 > iArr.length) {
            this.f393c = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f393c;
        int i4 = this.f394d;
        iArr2[i4] = i2;
        this.f394d = i4 + 1;
    }

    public void g() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        h((ConstraintLayout) parent);
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f393c, this.f394d);
    }

    public void h(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i2 = 0; i2 < this.f394d; i2++) {
            View viewG = constraintLayout.g(this.f393c[i2]);
            if (viewG != null) {
                viewG.setVisibility(visibility);
                if (elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                    viewG.setTranslationZ(viewG.getTranslationZ() + elevation);
                }
            }
        }
    }

    public final int[] i(View view, String str) {
        String[] strArrSplit = str.split(",");
        view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i2 = 0;
        for (String str2 : strArrSplit) {
            int iK = k(str2.trim());
            if (iK != 0) {
                iArr[i2] = iK;
                i2++;
            }
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    public final int j(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.s.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
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
                iJ = d.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return iJ == 0 ? this.s.getResources().getIdentifier(str, "id", this.s.getPackageName()) : iJ;
    }

    public View[] l(ConstraintLayout constraintLayout) {
        View[] viewArr = this.F3;
        if (viewArr == null || viewArr.length != this.f394d) {
            this.F3 = new View[this.f394d];
        }
        for (int i2 = 0; i2 < this.f394d; i2++) {
            this.F3[i2] = constraintLayout.g(this.f393c[i2]);
        }
        return this.F3;
    }

    public void m(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.t1) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.E3 = string;
                    setIds(string);
                }
            }
        }
    }

    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        b.C0018b c0018b = aVar.f1479d;
        int[] iArr = c0018b.f0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = c0018b.g0;
            if (str != null && str.length() > 0) {
                b.C0018b c0018b2 = aVar.f1479d;
                c0018b2.f0 = i(this, c0018b2.g0);
            }
        }
        hVar.c();
        if (aVar.f1479d.f0 == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            int[] iArr2 = aVar.f1479d.f0;
            if (i2 >= iArr2.length) {
                return;
            }
            ConstraintWidget constraintWidget = sparseArray.get(iArr2[i2]);
            if (constraintWidget != null) {
                hVar.a(constraintWidget);
            }
            i2++;
        }
    }

    public void o(ConstraintWidget constraintWidget, boolean z) {
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.E3;
        if (str != null) {
            setIds(str);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.y) {
            super.onMeasure(i2, i3);
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

    public void s(b.e.b.j.d dVar, g gVar, SparseArray<ConstraintWidget> sparseArray) {
        gVar.c();
        for (int i2 = 0; i2 < this.f394d; i2++) {
            gVar.a(sparseArray.get(this.f393c[i2]));
        }
    }

    public void setIds(String str) {
        this.E3 = str;
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.f394d = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i2);
            if (iIndexOf == -1) {
                e(str.substring(i2));
                return;
            } else {
                e(str.substring(i2, iIndexOf));
                i2 = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.E3 = null;
        this.f394d = 0;
        for (int i2 : iArr) {
            f(i2);
        }
    }

    public void t(ConstraintLayout constraintLayout) {
        String str;
        int iJ;
        if (isInEditMode()) {
            setIds(this.E3);
        }
        g gVar = this.x;
        if (gVar == null) {
            return;
        }
        gVar.c();
        for (int i2 = 0; i2 < this.f394d; i2++) {
            int i3 = this.f393c[i2];
            View viewG = constraintLayout.g(i3);
            if (viewG == null && (iJ = j(constraintLayout, (str = this.G3.get(Integer.valueOf(i3))))) != 0) {
                this.f393c[i2] = iJ;
                this.G3.put(Integer.valueOf(iJ), str);
                viewG = constraintLayout.g(iJ);
            }
            if (viewG != null) {
                this.x.a(constraintLayout.k(viewG));
            }
        }
        this.x.b(constraintLayout.s);
    }

    public void u() {
        if (this.x == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).n0 = (ConstraintWidget) this.x;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f393c = new int[32];
        this.y = false;
        this.F3 = null;
        this.G3 = new HashMap<>();
        this.s = context;
        m(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f393c = new int[32];
        this.y = false;
        this.F3 = null;
        this.G3 = new HashMap<>();
        this.s = context;
        m(attributeSet);
    }
}
