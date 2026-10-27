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
import com.bydux.api.common.tools.util.ListUtils;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int[] f1080b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f1081c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Context f1082d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected g f1083e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected boolean f1084f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected String f1085g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private View[] f1086h;
    private HashMap<Integer, String> i;

    public ConstraintHelper(Context context) {
        super(context);
        this.f1080b = new int[32];
        this.f1084f = false;
        this.f1086h = null;
        this.i = new HashMap<>();
        this.f1082d = context;
        m(null);
    }

    private void e(String str) {
        if (str == null || str.length() == 0 || this.f1082d == null) {
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
        int i2 = this.f1081c + 1;
        int[] iArr = this.f1080b;
        if (i2 > iArr.length) {
            this.f1080b = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f1080b;
        int i3 = this.f1081c;
        iArr2[i3] = i;
        this.f1081c = i3 + 1;
    }

    private int[] i(View view, String str) {
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

    private int j(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.f1082d.getResources()) == null) {
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
        return iJ == 0 ? this.f1082d.getResources().getIdentifier(str, "id", this.f1082d.getPackageName()) : iJ;
    }

    protected void g() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        h((ConstraintLayout) parent);
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f1080b, this.f1081c);
    }

    protected void h(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i = 0; i < this.f1081c; i++) {
            View viewG = constraintLayout.g(this.f1080b[i]);
            if (viewG != null) {
                viewG.setVisibility(visibility);
                if (elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                    viewG.setTranslationZ(viewG.getTranslationZ() + elevation);
                }
            }
        }
    }

    protected View[] l(ConstraintLayout constraintLayout) {
        View[] viewArr = this.f1086h;
        if (viewArr == null || viewArr.length != this.f1081c) {
            this.f1086h = new View[this.f1081c];
        }
        for (int i = 0; i < this.f1081c; i++) {
            this.f1086h[i] = constraintLayout.g(this.f1080b[i]);
        }
        return this.f1086h;
    }

    protected void m(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.t1) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f1085g = string;
                    setIds(string);
                }
            }
        }
    }

    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        b.C0012b c0012b = aVar.f1142d;
        int[] iArr = c0012b.f0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = c0012b.g0;
            if (str != null && str.length() > 0) {
                b.C0012b c0012b2 = aVar.f1142d;
                c0012b2.f0 = i(this, c0012b2.g0);
            }
        }
        hVar.c();
        if (aVar.f1142d.f0 == null) {
            return;
        }
        int i = 0;
        while (true) {
            int[] iArr2 = aVar.f1142d.f0;
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
        String str = this.f1085g;
        if (str != null) {
            setIds(str);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.f1084f) {
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
        for (int i = 0; i < this.f1081c; i++) {
            gVar.a(sparseArray.get(this.f1080b[i]));
        }
    }

    protected void setIds(String str) {
        this.f1085g = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.f1081c = 0;
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
        this.f1085g = null;
        this.f1081c = 0;
        for (int i : iArr) {
            f(i);
        }
    }

    public void t(ConstraintLayout constraintLayout) {
        String str;
        int iJ;
        if (isInEditMode()) {
            setIds(this.f1085g);
        }
        g gVar = this.f1083e;
        if (gVar == null) {
            return;
        }
        gVar.c();
        for (int i = 0; i < this.f1081c; i++) {
            int i2 = this.f1080b[i];
            View viewG = constraintLayout.g(i2);
            if (viewG == null && (iJ = j(constraintLayout, (str = this.i.get(Integer.valueOf(i2))))) != 0) {
                this.f1080b[i] = iJ;
                this.i.put(Integer.valueOf(iJ), str);
                viewG = constraintLayout.g(iJ);
            }
            if (viewG != null) {
                this.f1083e.a(constraintLayout.k(viewG));
            }
        }
        this.f1083e.b(constraintLayout.f1089d);
    }

    public void u() {
        if (this.f1083e == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).n0 = (ConstraintWidget) this.f1083e;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1080b = new int[32];
        this.f1084f = false;
        this.f1086h = null;
        this.i = new HashMap<>();
        this.f1082d = context;
        m(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1080b = new int[32];
        this.f1084f = false;
        this.f1086h = null;
        this.i = new HashMap<>();
        this.f1082d = context;
        m(attributeSet);
    }
}
