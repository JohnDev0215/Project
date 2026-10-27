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
import java.util.Arrays;
import java.util.HashMap;
import w.d;
import w.g;
import w.h;
import y.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f970b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f971c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f972d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g f973e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f974f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f975g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public View[] f976h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public HashMap<Integer, String> f977i;

    public ConstraintHelper(Context context) {
        super(context);
        this.f970b = new int[32];
        this.f974f = false;
        this.f976h = null;
        this.f977i = new HashMap<>();
        this.f972d = context;
        m(null);
    }

    public final void e(String str) {
        if (str == null || str.length() == 0 || this.f972d == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iK = k(strTrim);
        if (iK != 0) {
            this.f977i.put(Integer.valueOf(iK), strTrim);
            f(iK);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    public final void f(int i3) {
        if (i3 == getId()) {
            return;
        }
        int i4 = this.f971c + 1;
        int[] iArr = this.f970b;
        if (i4 > iArr.length) {
            this.f970b = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f970b;
        int i5 = this.f971c;
        iArr2[i5] = i3;
        this.f971c = i5 + 1;
    }

    public void g() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        h((ConstraintLayout) parent);
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f970b, this.f971c);
    }

    public void h(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i3 = 0; i3 < this.f971c; i3++) {
            View viewG = constraintLayout.g(this.f970b[i3]);
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
        int i3 = 0;
        for (String str2 : strArrSplit) {
            int iK = k(str2.trim());
            if (iK != 0) {
                iArr[i3] = iK;
                i3++;
            }
        }
        return i3 != strArrSplit.length ? Arrays.copyOf(iArr, i3) : iArr;
    }

    public final int j(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.f972d.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = constraintLayout.getChildAt(i3);
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
        return iJ == 0 ? this.f972d.getResources().getIdentifier(str, "id", this.f972d.getPackageName()) : iJ;
    }

    public View[] l(ConstraintLayout constraintLayout) {
        View[] viewArr = this.f976h;
        if (viewArr == null || viewArr.length != this.f971c) {
            this.f976h = new View[this.f971c];
        }
        for (int i3 = 0; i3 < this.f971c; i3++) {
            this.f976h[i3] = constraintLayout.g(this.f970b[i3]);
        }
        return this.f976h;
    }

    public void m(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f975g = string;
                    setIds(string);
                }
            }
        }
    }

    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        b.C0074b c0074b = aVar.f5769d;
        int[] iArr = c0074b.f5783f0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = c0074b.f5785g0;
            if (str != null && str.length() > 0) {
                b.C0074b c0074b2 = aVar.f5769d;
                c0074b2.f5783f0 = i(this, c0074b2.f5785g0);
            }
        }
        hVar.c();
        if (aVar.f5769d.f5783f0 == null) {
            return;
        }
        int i3 = 0;
        while (true) {
            int[] iArr2 = aVar.f5769d.f5783f0;
            if (i3 >= iArr2.length) {
                return;
            }
            ConstraintWidget constraintWidget = sparseArray.get(iArr2[i3]);
            if (constraintWidget != null) {
                hVar.a(constraintWidget);
            }
            i3++;
        }
    }

    public void o(ConstraintWidget constraintWidget, boolean z3) {
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f975g;
        if (str != null) {
            setIds(str);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        if (this.f974f) {
            super.onMeasure(i3, i4);
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

    public void s(d dVar, g gVar, SparseArray<ConstraintWidget> sparseArray) {
        gVar.c();
        for (int i3 = 0; i3 < this.f971c; i3++) {
            gVar.a(sparseArray.get(this.f970b[i3]));
        }
    }

    public void setIds(String str) {
        this.f975g = str;
        if (str == null) {
            return;
        }
        int i3 = 0;
        this.f971c = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i3);
            if (iIndexOf == -1) {
                e(str.substring(i3));
                return;
            } else {
                e(str.substring(i3, iIndexOf));
                i3 = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.f975g = null;
        this.f971c = 0;
        for (int i3 : iArr) {
            f(i3);
        }
    }

    public void t(ConstraintLayout constraintLayout) {
        String str;
        int iJ;
        if (isInEditMode()) {
            setIds(this.f975g);
        }
        g gVar = this.f973e;
        if (gVar == null) {
            return;
        }
        gVar.c();
        for (int i3 = 0; i3 < this.f971c; i3++) {
            int i4 = this.f970b[i3];
            View viewG = constraintLayout.g(i4);
            if (viewG == null && (iJ = j(constraintLayout, (str = this.f977i.get(Integer.valueOf(i4))))) != 0) {
                this.f970b[i3] = iJ;
                this.f977i.put(Integer.valueOf(iJ), str);
                viewG = constraintLayout.g(iJ);
            }
            if (viewG != null) {
                this.f973e.a(constraintLayout.k(viewG));
            }
        }
        this.f973e.b(constraintLayout.f980d);
    }

    public void u() {
        if (this.f973e == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).f1029n0 = (ConstraintWidget) this.f973e;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f970b = new int[32];
        this.f974f = false;
        this.f976h = null;
        this.f977i = new HashMap<>();
        this.f972d = context;
        m(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f970b = new int[32];
        this.f974f = false;
        this.f976h = null;
        this.f977i = new HashMap<>();
        this.f972d = context;
        m(attributeSet);
    }
}
