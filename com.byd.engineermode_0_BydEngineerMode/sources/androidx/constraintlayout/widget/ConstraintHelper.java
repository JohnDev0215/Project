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
import androidx.constraintlayout.solver.widgets.g;
import androidx.constraintlayout.solver.widgets.h;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.b;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int[] f710b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f711c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Context f712d;
    protected g e;
    protected boolean f;
    protected String g;
    private View[] h;
    private HashMap<Integer, String> i;

    public ConstraintHelper(Context context) {
        super(context);
        this.f710b = new int[32];
        this.f = false;
        this.h = null;
        this.i = new HashMap<>();
        this.f712d = context;
        m(null);
    }

    private void e(String str) {
        if (str == null || str.length() == 0 || this.f712d == null) {
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

    private void f(int i) {
        if (i == getId()) {
            return;
        }
        int i2 = this.f711c + 1;
        int[] iArr = this.f710b;
        if (i2 > iArr.length) {
            this.f710b = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f710b;
        int i3 = this.f711c;
        iArr2[i3] = i;
        this.f711c = i3 + 1;
    }

    private int[] i(View view, String str) {
        String[] strArrSplit = str.split(",");
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

    private int j(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.f712d.getResources()) == null) {
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

    private int k(String str) {
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
        return iJ == 0 ? this.f712d.getResources().getIdentifier(str, "id", this.f712d.getPackageName()) : iJ;
    }

    protected void g() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        h((ConstraintLayout) parent);
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f710b, this.f711c);
    }

    protected void h(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i = 0; i < this.f711c; i++) {
            View viewG = constraintLayout.g(this.f710b[i]);
            if (viewG != null) {
                viewG.setVisibility(visibility);
                if (elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                    viewG.setTranslationZ(viewG.getTranslationZ() + elevation);
                }
            }
        }
    }

    protected View[] l(ConstraintLayout constraintLayout) {
        View[] viewArr = this.h;
        if (viewArr == null || viewArr.length != this.f711c) {
            this.h = new View[this.f711c];
        }
        for (int i = 0; i < this.f711c; i++) {
            this.h[i] = constraintLayout.g(this.f710b[i]);
        }
        return this.h;
    }

    protected void m(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.t1) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.g = string;
                    setIds(string);
                }
            }
        }
    }

    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        b.C0011b c0011b = aVar.f749d;
        int[] iArr = c0011b.f0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = c0011b.g0;
            if (str != null && str.length() > 0) {
                b.C0011b c0011b2 = aVar.f749d;
                c0011b2.f0 = i(this, c0011b2.g0);
            }
        }
        hVar.c();
        if (aVar.f749d.f0 == null) {
            return;
        }
        int i = 0;
        while (true) {
            int[] iArr2 = aVar.f749d.f0;
            if (i >= iArr2.length) {
                return;
            }
            ConstraintWidget constraintWidget = sparseArray.get(iArr2[i]);
            if (constraintWidget != null) {
                hVar.a(constraintWidget);
            }
            i++;
        }
    }

    public void o(ConstraintWidget constraintWidget, boolean z) {
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
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
    protected void onMeasure(int i, int i2) {
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

    public void s(androidx.constraintlayout.solver.widgets.d dVar, g gVar, SparseArray<ConstraintWidget> sparseArray) {
        gVar.c();
        for (int i = 0; i < this.f711c; i++) {
            gVar.a(sparseArray.get(this.f710b[i]));
        }
    }

    protected void setIds(String str) {
        this.g = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.f711c = 0;
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
        this.f711c = 0;
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
        g gVar = this.e;
        if (gVar == null) {
            return;
        }
        gVar.c();
        for (int i = 0; i < this.f711c; i++) {
            int i2 = this.f710b[i];
            View viewG = constraintLayout.g(i2);
            if (viewG == null && (iJ = j(constraintLayout, (str = this.i.get(Integer.valueOf(i2))))) != 0) {
                this.f710b[i] = iJ;
                this.i.put(Integer.valueOf(iJ), str);
                viewG = constraintLayout.g(iJ);
            }
            if (viewG != null) {
                this.e.a(constraintLayout.k(viewG));
            }
        }
        this.e.b(constraintLayout.f715d);
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
        this.f710b = new int[32];
        this.f = false;
        this.h = null;
        this.i = new HashMap<>();
        this.f712d = context;
        m(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f710b = new int[32];
        this.f = false;
        this.h = null;
        this.i = new HashMap<>();
        this.f712d = context;
        m(attributeSet);
    }
}
