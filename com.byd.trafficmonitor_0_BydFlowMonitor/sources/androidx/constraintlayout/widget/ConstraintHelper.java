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
import b.f.b.j.g;
import b.f.b.j.h;
import b.f.c.b;
import b.f.c.d;
import b.f.c.e;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f700b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f701c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f702d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g f703e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f704f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f705g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public View[] f706h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public HashMap<Integer, String> f707i;

    public ConstraintHelper(Context context) {
        super(context);
        this.f700b = new int[32];
        this.f704f = false;
        this.f706h = null;
        this.f707i = new HashMap<>();
        this.f702d = context;
        m(null);
    }

    public final void e(String str) {
        if (str == null || str.length() == 0 || this.f702d == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iK = k(strTrim);
        if (iK != 0) {
            this.f707i.put(Integer.valueOf(iK), strTrim);
            f(iK);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    public final void f(int i2) {
        if (i2 == getId()) {
            return;
        }
        int i3 = this.f701c + 1;
        int[] iArr = this.f700b;
        if (i3 > iArr.length) {
            this.f700b = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f700b;
        int i4 = this.f701c;
        iArr2[i4] = i2;
        this.f701c = i4 + 1;
    }

    public void g() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        h((ConstraintLayout) parent);
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f700b, this.f701c);
    }

    public void h(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i2 = 0; i2 < this.f701c; i2++) {
            View viewG = constraintLayout.g(this.f700b[i2]);
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
        if (str == null || constraintLayout == null || (resources = this.f702d.getResources()) == null) {
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
        return iJ == 0 ? this.f702d.getResources().getIdentifier(str, "id", this.f702d.getPackageName()) : iJ;
    }

    public View[] l(ConstraintLayout constraintLayout) {
        View[] viewArr = this.f706h;
        if (viewArr == null || viewArr.length != this.f701c) {
            this.f706h = new View[this.f701c];
        }
        for (int i2 = 0; i2 < this.f701c; i2++) {
            this.f706h[i2] = constraintLayout.g(this.f700b[i2]);
        }
        return this.f706h;
    }

    public void m(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f705g = string;
                    setIds(string);
                }
            }
        }
    }

    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        b.C0029b c0029b = aVar.f2291d;
        int[] iArr = c0029b.f0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = c0029b.g0;
            if (str != null && str.length() > 0) {
                b.C0029b c0029b2 = aVar.f2291d;
                c0029b2.f0 = i(this, c0029b2.g0);
            }
        }
        hVar.c();
        if (aVar.f2291d.f0 == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            int[] iArr2 = aVar.f2291d.f0;
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
        String str = this.f705g;
        if (str != null) {
            setIds(str);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.f704f) {
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

    public void s(b.f.b.j.d dVar, g gVar, SparseArray<ConstraintWidget> sparseArray) {
        gVar.c();
        for (int i2 = 0; i2 < this.f701c; i2++) {
            gVar.a(sparseArray.get(this.f700b[i2]));
        }
    }

    public void setIds(String str) {
        this.f705g = str;
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.f701c = 0;
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
        this.f705g = null;
        this.f701c = 0;
        for (int i2 : iArr) {
            f(i2);
        }
    }

    public void t(ConstraintLayout constraintLayout) {
        String str;
        int iJ;
        if (isInEditMode()) {
            setIds(this.f705g);
        }
        g gVar = this.f703e;
        if (gVar == null) {
            return;
        }
        gVar.c();
        for (int i2 = 0; i2 < this.f701c; i2++) {
            int i3 = this.f700b[i2];
            View viewG = constraintLayout.g(i3);
            if (viewG == null && (iJ = j(constraintLayout, (str = this.f707i.get(Integer.valueOf(i3))))) != 0) {
                this.f700b[i2] = iJ;
                this.f707i.put(Integer.valueOf(iJ), str);
                viewG = constraintLayout.g(iJ);
            }
            if (viewG != null) {
                this.f703e.a(constraintLayout.k(viewG));
            }
        }
        this.f703e.b(constraintLayout.f710d);
    }

    public void u() {
        if (this.f703e == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).n0 = (ConstraintWidget) this.f703e;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f700b = new int[32];
        this.f704f = false;
        this.f706h = null;
        this.f707i = new HashMap<>();
        this.f702d = context;
        m(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f700b = new int[32];
        this.f704f = false;
        this.f706h = null;
        this.f707i = new HashMap<>();
        this.f702d = context;
        m(attributeSet);
    }
}
