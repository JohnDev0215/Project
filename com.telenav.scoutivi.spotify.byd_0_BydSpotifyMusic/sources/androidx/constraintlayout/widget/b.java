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
import androidx.constraintlayout.widget.d;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: ConstraintHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends View {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int[] f1740e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected int f1741g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected Context f1742h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected r.i f1743i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected boolean f1744j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    protected String f1745k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    protected String f1746l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private View[] f1747m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    protected HashMap<Integer, String> f1748n;

    public b(Context context) {
        super(context);
        this.f1740e = new int[32];
        this.f1744j = false;
        this.f1747m = null;
        this.f1748n = new HashMap<>();
        this.f1742h = context;
        o(null);
    }

    private void e(String str) {
        if (str == null || str.length() == 0 || this.f1742h == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iM = m(strTrim);
        if (iM != 0) {
            this.f1748n.put(Integer.valueOf(iM), strTrim);
            f(iM);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    private void f(int i7) {
        if (i7 == getId()) {
            return;
        }
        int i8 = this.f1741g + 1;
        int[] iArr = this.f1740e;
        if (i8 > iArr.length) {
            this.f1740e = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f1740e;
        int i9 = this.f1741g;
        iArr2[i9] = i7;
        this.f1741g = i9 + 1;
    }

    private void g(String str) {
        if (str == null || str.length() == 0 || this.f1742h == null) {
            return;
        }
        String strTrim = str.trim();
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        if (constraintLayout == null) {
            Log.w("ConstraintHelper", "Parent not a ConstraintLayout");
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof ConstraintLayout.b) && strTrim.equals(((ConstraintLayout.b) layoutParams).f1677c0)) {
                if (childAt.getId() == -1) {
                    Log.w("ConstraintHelper", "to use ConstraintTag view " + childAt.getClass().getSimpleName() + " must have an ID");
                } else {
                    f(childAt.getId());
                }
            }
        }
    }

    private int[] k(View view, String str) {
        String[] strArrSplit = str.split(",");
        view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i7 = 0;
        for (String str2 : strArrSplit) {
            int iM = m(str2.trim());
            if (iM != 0) {
                iArr[i7] = iM;
                i7++;
            }
        }
        return i7 != strArrSplit.length ? Arrays.copyOf(iArr, i7) : iArr;
    }

    private int l(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.f1742h.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
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

    private int m(String str) {
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        int iL = 0;
        if (isInEditMode() && constraintLayout != null) {
            Object objG = constraintLayout.g(0, str);
            if (objG instanceof Integer) {
                iL = ((Integer) objG).intValue();
            }
        }
        if (iL == 0 && constraintLayout != null) {
            iL = l(constraintLayout, str);
        }
        if (iL == 0) {
            try {
                iL = h.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return iL == 0 ? this.f1742h.getResources().getIdentifier(str, "id", this.f1742h.getPackageName()) : iL;
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f1740e, this.f1741g);
    }

    protected void h() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        i((ConstraintLayout) parent);
    }

    protected void i(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = getElevation();
        for (int i7 = 0; i7 < this.f1741g; i7++) {
            View viewL = constraintLayout.l(this.f1740e[i7]);
            if (viewL != null) {
                viewL.setVisibility(visibility);
                if (elevation > 0.0f) {
                    viewL.setTranslationZ(viewL.getTranslationZ() + elevation);
                }
            }
        }
    }

    protected void j(ConstraintLayout constraintLayout) {
    }

    protected View[] n(ConstraintLayout constraintLayout) {
        View[] viewArr = this.f1747m;
        if (viewArr == null || viewArr.length != this.f1741g) {
            this.f1747m = new View[this.f1741g];
        }
        for (int i7 = 0; i7 < this.f1741g; i7++) {
            this.f1747m[i7] = constraintLayout.l(this.f1740e[i7]);
        }
        return this.f1747m;
    }

    protected void o(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, i.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == i.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f1745k = string;
                    setIds(string);
                } else if (index == i.ConstraintLayout_Layout_constraint_referenced_tags) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.f1746l = string2;
                    setReferenceTags(string2);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f1745k;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.f1746l;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        if (this.f1744j) {
            super.onMeasure(i7, i8);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void p(d.a aVar, r.j jVar, ConstraintLayout.b bVar, SparseArray<r.e> sparseArray) {
        d.b bVar2 = aVar.f1779e;
        int[] iArr = bVar2.f1817k0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = bVar2.f1819l0;
            if (str != null) {
                if (str.length() > 0) {
                    d.b bVar3 = aVar.f1779e;
                    bVar3.f1817k0 = k(this, bVar3.f1819l0);
                } else {
                    aVar.f1779e.f1817k0 = null;
                }
            }
        }
        if (jVar == null) {
            return;
        }
        jVar.b();
        if (aVar.f1779e.f1817k0 == null) {
            return;
        }
        int i7 = 0;
        while (true) {
            int[] iArr2 = aVar.f1779e.f1817k0;
            if (i7 >= iArr2.length) {
                return;
            }
            r.e eVar = sparseArray.get(iArr2[i7]);
            if (eVar != null) {
                jVar.c(eVar);
            }
            i7++;
        }
    }

    public void q(r.e eVar, boolean z6) {
    }

    public void r(ConstraintLayout constraintLayout) {
    }

    public void s(ConstraintLayout constraintLayout) {
    }

    protected void setIds(String str) {
        this.f1745k = str;
        if (str == null) {
            return;
        }
        int i7 = 0;
        this.f1741g = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i7);
            if (iIndexOf == -1) {
                e(str.substring(i7));
                return;
            } else {
                e(str.substring(i7, iIndexOf));
                i7 = iIndexOf + 1;
            }
        }
    }

    protected void setReferenceTags(String str) {
        this.f1746l = str;
        if (str == null) {
            return;
        }
        int i7 = 0;
        this.f1741g = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i7);
            if (iIndexOf == -1) {
                g(str.substring(i7));
                return;
            } else {
                g(str.substring(i7, iIndexOf));
                i7 = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.f1745k = null;
        this.f1741g = 0;
        for (int i7 : iArr) {
            f(i7);
        }
    }

    @Override // android.view.View
    public void setTag(int i7, Object obj) {
        super.setTag(i7, obj);
        if (obj == null && this.f1745k == null) {
            f(i7);
        }
    }

    public void t(ConstraintLayout constraintLayout) {
    }

    public void u(ConstraintLayout constraintLayout) {
        String str;
        int iL;
        if (isInEditMode()) {
            setIds(this.f1745k);
        }
        r.i iVar = this.f1743i;
        if (iVar == null) {
            return;
        }
        iVar.b();
        for (int i7 = 0; i7 < this.f1741g; i7++) {
            int i8 = this.f1740e[i7];
            View viewL = constraintLayout.l(i8);
            if (viewL == null && (iL = l(constraintLayout, (str = this.f1748n.get(Integer.valueOf(i8))))) != 0) {
                this.f1740e[i7] = iL;
                this.f1748n.put(Integer.valueOf(iL), str);
                viewL = constraintLayout.l(iL);
            }
            if (viewL != null) {
                this.f1743i.c(constraintLayout.p(viewL));
            }
        }
        this.f1743i.a(constraintLayout.f1652h);
    }

    public void v(r.f fVar, r.i iVar, SparseArray<r.e> sparseArray) {
        iVar.b();
        for (int i7 = 0; i7 < this.f1741g; i7++) {
            iVar.c(sparseArray.get(this.f1740e[i7]));
        }
    }

    public void w() {
        if (this.f1743i == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).f1715v0 = (r.e) this.f1743i;
        }
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1740e = new int[32];
        this.f1744j = false;
        this.f1747m = null;
        this.f1748n = new HashMap<>();
        this.f1742h = context;
        o(attributeSet);
    }
}
