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
import b0.b;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.Arrays;
import java.util.HashMap;
import z.d;
import z.g;
import z.h;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f1061d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1062e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f1063f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g f1064g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1065h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f1066i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View[] f1067j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public HashMap<Integer, String> f1068k;

    public ConstraintHelper(Context context) {
        super(context);
        this.f1061d = new int[32];
        this.f1065h = false;
        this.f1067j = null;
        this.f1068k = new HashMap<>();
        this.f1063f = context;
        m(null);
    }

    public final void e(String str) {
        if (str == null || str.length() == 0 || this.f1063f == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iK = k(strTrim);
        if (iK != 0) {
            this.f1068k.put(Integer.valueOf(iK), strTrim);
            f(iK);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    public final void f(int i8) {
        if (i8 == getId()) {
            return;
        }
        int i9 = this.f1062e + 1;
        int[] iArr = this.f1061d;
        if (i9 > iArr.length) {
            this.f1061d = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f1061d;
        int i10 = this.f1062e;
        iArr2[i10] = i8;
        this.f1062e = i10 + 1;
    }

    public void g() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        h((ConstraintLayout) parent);
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f1061d, this.f1062e);
    }

    public void h(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i8 = 0; i8 < this.f1062e; i8++) {
            View viewG = constraintLayout.g(this.f1061d[i8]);
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
        int i8 = 0;
        for (String str2 : strArrSplit) {
            int iK = k(str2.trim());
            if (iK != 0) {
                iArr[i8] = iK;
                i8++;
            }
        }
        return i8 != strArrSplit.length ? Arrays.copyOf(iArr, i8) : iArr;
    }

    public final int j(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.f1063f.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = constraintLayout.getChildAt(i8);
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
        return iJ == 0 ? this.f1063f.getResources().getIdentifier(str, "id", this.f1063f.getPackageName()) : iJ;
    }

    public View[] l(ConstraintLayout constraintLayout) {
        View[] viewArr = this.f1067j;
        if (viewArr == null || viewArr.length != this.f1062e) {
            this.f1067j = new View[this.f1062e];
        }
        for (int i8 = 0; i8 < this.f1062e; i8++) {
            this.f1067j[i8] = constraintLayout.g(this.f1061d[i8]);
        }
        return this.f1067j;
    }

    public void m(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f1066i = string;
                    setIds(string);
                }
            }
        }
    }

    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        b.C0006b c0006b = aVar.f1945d;
        int[] iArr = c0006b.f1959f0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = c0006b.f1961g0;
            if (str != null && str.length() > 0) {
                b.C0006b c0006b2 = aVar.f1945d;
                c0006b2.f1959f0 = i(this, c0006b2.f1961g0);
            }
        }
        hVar.c();
        if (aVar.f1945d.f1959f0 == null) {
            return;
        }
        int i8 = 0;
        while (true) {
            int[] iArr2 = aVar.f1945d.f1959f0;
            if (i8 >= iArr2.length) {
                return;
            }
            ConstraintWidget constraintWidget = sparseArray.get(iArr2[i8]);
            if (constraintWidget != null) {
                hVar.a(constraintWidget);
            }
            i8++;
        }
    }

    public void o(ConstraintWidget constraintWidget, boolean z7) {
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f1066i;
        if (str != null) {
            setIds(str);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        if (this.f1065h) {
            super.onMeasure(i8, i9);
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
        for (int i8 = 0; i8 < this.f1062e; i8++) {
            gVar.a(sparseArray.get(this.f1061d[i8]));
        }
    }

    public void setIds(String str) {
        this.f1066i = str;
        if (str == null) {
            return;
        }
        int i8 = 0;
        this.f1062e = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i8);
            if (iIndexOf == -1) {
                e(str.substring(i8));
                return;
            } else {
                e(str.substring(i8, iIndexOf));
                i8 = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.f1066i = null;
        this.f1062e = 0;
        for (int i8 : iArr) {
            f(i8);
        }
    }

    public void t(ConstraintLayout constraintLayout) {
        String str;
        int iJ;
        if (isInEditMode()) {
            setIds(this.f1066i);
        }
        g gVar = this.f1064g;
        if (gVar == null) {
            return;
        }
        gVar.c();
        for (int i8 = 0; i8 < this.f1062e; i8++) {
            int i9 = this.f1061d[i8];
            View viewG = constraintLayout.g(i9);
            if (viewG == null && (iJ = j(constraintLayout, (str = this.f1068k.get(Integer.valueOf(i9))))) != 0) {
                this.f1061d[i8] = iJ;
                this.f1068k.put(Integer.valueOf(iJ), str);
                viewG = constraintLayout.g(iJ);
            }
            if (viewG != null) {
                this.f1064g.a(constraintLayout.k(viewG));
            }
        }
        this.f1064g.b(constraintLayout.f1071f);
    }

    public void u() {
        if (this.f1064g == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).f1120n0 = (ConstraintWidget) this.f1064g;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1061d = new int[32];
        this.f1065h = false;
        this.f1067j = null;
        this.f1068k = new HashMap<>();
        this.f1063f = context;
        m(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f1061d = new int[32];
        this.f1065h = false;
        this.f1067j = null;
        this.f1068k = new HashMap<>();
        this.f1063f = context;
        m(attributeSet);
    }
}
