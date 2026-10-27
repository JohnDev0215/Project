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
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.h;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.b;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int[] f1028b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f1029c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Context f1030d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected androidx.constraintlayout.core.widgets.g f1031e;
    protected boolean f;
    protected String g;
    protected String h;
    private View[] i;
    protected HashMap<Integer, String> j;

    public ConstraintHelper(Context context) {
        super(context);
        this.f1028b = new int[32];
        this.f = false;
        this.i = null;
        this.j = new HashMap<>();
        this.f1030d = context;
        o(null);
    }

    private void e(String str) {
        if (str == null || str.length() == 0 || this.f1030d == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iM = m(strTrim);
        if (iM != 0) {
            this.j.put(Integer.valueOf(iM), strTrim);
            f(iM);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    private void f(int i) {
        if (i == getId()) {
            return;
        }
        int i2 = this.f1029c + 1;
        int[] iArr = this.f1028b;
        if (i2 > iArr.length) {
            this.f1028b = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f1028b;
        int i3 = this.f1029c;
        iArr2[i3] = i;
        this.f1029c = i3 + 1;
    }

    private void g(String str) {
        if (str == null || str.length() == 0 || this.f1030d == null) {
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
            if ((layoutParams instanceof ConstraintLayout.b) && strTrim.equals(((ConstraintLayout.b) layoutParams).c0)) {
                if (childAt.getId() == -1) {
                    Log.w("ConstraintHelper", "to use ConstraintTag view " + childAt.getClass().getSimpleName() + " must have an ID");
                } else {
                    f(childAt.getId());
                }
            }
        }
    }

    private int[] k(View view, String str) {
        String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
        view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        for (String str2 : strArrSplit) {
            int iM = m(str2.trim());
            if (iM != 0) {
                iArr[i] = iM;
                i++;
            }
        }
        return i != strArrSplit.length ? Arrays.copyOf(iArr, i) : iArr;
    }

    private int l(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.f1030d.getResources()) == null) {
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

    private int m(String str) {
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        int iL = 0;
        if (isInEditMode() && constraintLayout != null) {
            Object objF = constraintLayout.f(0, str);
            if (objF instanceof Integer) {
                iL = ((Integer) objF).intValue();
            }
        }
        if (iL == 0 && constraintLayout != null) {
            iL = l(constraintLayout, str);
        }
        if (iL == 0) {
            try {
                iL = d.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return iL == 0 ? this.f1030d.getResources().getIdentifier(str, "id", this.f1030d.getPackageName()) : iL;
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f1028b, this.f1029c);
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
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i = 0; i < this.f1029c; i++) {
            View viewK = constraintLayout.k(this.f1028b[i]);
            if (viewK != null) {
                viewK.setVisibility(visibility);
                if (elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                    viewK.setTranslationZ(viewK.getTranslationZ() + elevation);
                }
            }
        }
    }

    protected void j(ConstraintLayout constraintLayout) {
    }

    protected View[] n(ConstraintLayout constraintLayout) {
        View[] viewArr = this.i;
        if (viewArr == null || viewArr.length != this.f1029c) {
            this.i = new View[this.f1029c];
        }
        for (int i = 0; i < this.f1029c; i++) {
            this.i[i] = constraintLayout.k(this.f1028b[i]);
        }
        return this.i;
    }

    protected void o(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.x1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.V1) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.g = string;
                    setIds(string);
                } else if (index == e.W1) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.h = string2;
                    setReferenceTags(string2);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.g;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.h;
        if (str2 != null) {
            setReferenceTags(str2);
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

    public void p(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        b.C0020b c0020b = aVar.f1079e;
        int[] iArr = c0020b.l0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = c0020b.m0;
            if (str != null) {
                if (str.length() > 0) {
                    b.C0020b c0020b2 = aVar.f1079e;
                    c0020b2.l0 = k(this, c0020b2.m0);
                } else {
                    aVar.f1079e.l0 = null;
                }
            }
        }
        if (hVar == null) {
            return;
        }
        hVar.a();
        if (aVar.f1079e.l0 == null) {
            return;
        }
        int i = 0;
        while (true) {
            int[] iArr2 = aVar.f1079e.l0;
            if (i >= iArr2.length) {
                return;
            }
            ConstraintWidget constraintWidget = sparseArray.get(iArr2[i]);
            if (constraintWidget != null) {
                hVar.c(constraintWidget);
            }
            i++;
        }
    }

    public void q(ConstraintWidget constraintWidget, boolean z) {
    }

    public void r(ConstraintLayout constraintLayout) {
    }

    public void s(ConstraintLayout constraintLayout) {
    }

    protected void setIds(String str) {
        this.g = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.f1029c = 0;
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

    protected void setReferenceTags(String str) {
        this.h = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.f1029c = 0;
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
        this.g = null;
        this.f1029c = 0;
        for (int i : iArr) {
            f(i);
        }
    }

    @Override // android.view.View
    public void setTag(int i, Object obj) {
        super.setTag(i, obj);
        if (obj == null && this.g == null) {
            f(i);
        }
    }

    public void t(ConstraintLayout constraintLayout) {
    }

    public void u(androidx.constraintlayout.core.widgets.d dVar, androidx.constraintlayout.core.widgets.g gVar, SparseArray<ConstraintWidget> sparseArray) {
        gVar.a();
        for (int i = 0; i < this.f1029c; i++) {
            gVar.c(sparseArray.get(this.f1028b[i]));
        }
    }

    public void v(ConstraintLayout constraintLayout) {
        String str;
        int iL;
        if (isInEditMode()) {
            setIds(this.g);
        }
        androidx.constraintlayout.core.widgets.g gVar = this.f1031e;
        if (gVar == null) {
            return;
        }
        gVar.a();
        for (int i = 0; i < this.f1029c; i++) {
            int i2 = this.f1028b[i];
            View viewK = constraintLayout.k(i2);
            if (viewK == null && (iL = l(constraintLayout, (str = this.j.get(Integer.valueOf(i2))))) != 0) {
                this.f1028b[i] = iL;
                this.j.put(Integer.valueOf(iL), str);
                viewK = constraintLayout.k(iL);
            }
            if (viewK != null) {
                this.f1031e.c(constraintLayout.l(viewK));
            }
        }
        this.f1031e.b(constraintLayout.f1035e);
    }

    public void w() {
        if (this.f1031e == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).v0 = (ConstraintWidget) this.f1031e;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1028b = new int[32];
        this.f = false;
        this.i = null;
        this.j = new HashMap<>();
        this.f1030d = context;
        o(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1028b = new int[32];
        this.f = false;
        this.i = null;
        this.j = new HashMap<>();
        this.f1030d = context;
        o(attributeSet);
    }
}
