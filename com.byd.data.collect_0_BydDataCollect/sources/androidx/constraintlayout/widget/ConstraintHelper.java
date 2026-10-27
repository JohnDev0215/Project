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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.c;
import b.e.b.k.i;
import b.e.b.k.j;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintHelper extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int[] f992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f993c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Context f994d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected i f995e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected boolean f996f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected String f997g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private View[] f998h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private HashMap<Integer, String> f999i;

    public ConstraintHelper(Context context) {
        super(context);
        this.f992b = new int[32];
        this.f996f = false;
        this.f998h = null;
        this.f999i = new HashMap<>();
        this.f994d = context;
        m(null);
    }

    private void e(String str) {
        if (str == null || str.length() == 0 || this.f994d == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iK = k(strTrim);
        if (iK != 0) {
            this.f999i.put(Integer.valueOf(iK), strTrim);
            f(iK);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    private void f(int i2) {
        if (i2 == getId()) {
            return;
        }
        int i3 = this.f993c + 1;
        int[] iArr = this.f992b;
        if (i3 > iArr.length) {
            this.f992b = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f992b;
        int i4 = this.f993c;
        iArr2[i4] = i2;
        this.f993c = i4 + 1;
    }

    private int[] i(View view, String str) {
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

    private int j(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.f994d.getResources()) == null) {
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
                iJ = e.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return iJ == 0 ? this.f994d.getResources().getIdentifier(str, "id", this.f994d.getPackageName()) : iJ;
    }

    protected void g() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        h((ConstraintLayout) parent);
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f992b, this.f993c);
    }

    protected void h(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i2 = 0; i2 < this.f993c; i2++) {
            View viewG = constraintLayout.g(this.f992b[i2]);
            if (viewG != null) {
                viewG.setVisibility(visibility);
                if (elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                    viewG.setTranslationZ(viewG.getTranslationZ() + elevation);
                }
            }
        }
    }

    protected View[] l(ConstraintLayout constraintLayout) {
        View[] viewArr = this.f998h;
        if (viewArr == null || viewArr.length != this.f993c) {
            this.f998h = new View[this.f993c];
        }
        for (int i2 = 0; i2 < this.f993c; i2++) {
            this.f998h[i2] = constraintLayout.g(this.f992b[i2]);
        }
        return this.f998h;
    }

    protected void m(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, f.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f997g = string;
                    setIds(string);
                }
            }
        }
    }

    public void n(c.a aVar, j jVar, ConstraintLayout.b bVar, SparseArray<b.e.b.k.e> sparseArray) {
        c.b bVar2 = aVar.f1073d;
        int[] iArr = bVar2.f0;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = bVar2.g0;
            if (str != null && str.length() > 0) {
                c.b bVar3 = aVar.f1073d;
                bVar3.f0 = i(this, bVar3.g0);
            }
        }
        jVar.c();
        if (aVar.f1073d.f0 == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            int[] iArr2 = aVar.f1073d.f0;
            if (i2 >= iArr2.length) {
                return;
            }
            b.e.b.k.e eVar = sparseArray.get(iArr2[i2]);
            if (eVar != null) {
                jVar.a(eVar);
            }
            i2++;
        }
    }

    public void o(b.e.b.k.e eVar, boolean z) {
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f997g;
        if (str != null) {
            setIds(str);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.f996f) {
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

    public void s(b.e.b.k.f fVar, i iVar, SparseArray<b.e.b.k.e> sparseArray) {
        iVar.c();
        for (int i2 = 0; i2 < this.f993c; i2++) {
            iVar.a(sparseArray.get(this.f992b[i2]));
        }
    }

    protected void setIds(String str) {
        this.f997g = str;
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.f993c = 0;
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
        this.f997g = null;
        this.f993c = 0;
        for (int i2 : iArr) {
            f(i2);
        }
    }

    public void t(ConstraintLayout constraintLayout) {
        String str;
        int iJ;
        if (isInEditMode()) {
            setIds(this.f997g);
        }
        i iVar = this.f995e;
        if (iVar == null) {
            return;
        }
        iVar.c();
        for (int i2 = 0; i2 < this.f993c; i2++) {
            int i3 = this.f992b[i2];
            View viewG = constraintLayout.g(i3);
            if (viewG == null && (iJ = j(constraintLayout, (str = this.f999i.get(Integer.valueOf(i3))))) != 0) {
                this.f992b[i2] = iJ;
                this.f999i.put(Integer.valueOf(iJ), str);
                viewG = constraintLayout.g(iJ);
            }
            if (viewG != null) {
                this.f995e.a(constraintLayout.h(viewG));
            }
        }
        this.f995e.b(constraintLayout.f1002d);
    }

    public void u() {
        if (this.f995e == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).n0 = (b.e.b.k.e) this.f995e;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f992b = new int[32];
        this.f996f = false;
        this.f998h = null;
        this.f999i = new HashMap<>();
        this.f994d = context;
        m(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f992b = new int[32];
        this.f996f = false;
        this.f998h = null;
        this.f999i = new HashMap<>();
        this.f994d = context;
        m(attributeSet);
    }
}
