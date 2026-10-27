package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int[] f1102b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f1103c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Context f1104d;
    protected a.e.b.k.h e;
    protected boolean f;
    protected String g;
    private View[] h;
    private HashMap<Integer, String> i;

    public c(Context context) {
        super(context);
        this.f1102b = new int[32];
        this.f = false;
        this.h = null;
        this.i = new HashMap<>();
        this.f1104d = context;
        g(null);
    }

    private void a(String str) {
        if (str == null || str.length() == 0 || this.f1104d == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iF = f(strTrim);
        if (iF != 0) {
            this.i.put(Integer.valueOf(iF), strTrim);
            b(iF);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    private void b(int i) {
        if (i == getId()) {
            return;
        }
        int i2 = this.f1103c + 1;
        int[] iArr = this.f1102b;
        if (i2 > iArr.length) {
            this.f1102b = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f1102b;
        int i3 = this.f1103c;
        iArr2[i3] = i;
        this.f1103c = i3 + 1;
    }

    private int e(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.f1104d.getResources()) == null) {
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

    private int f(String str) {
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        int iE = 0;
        if (isInEditMode() && constraintLayout != null) {
            Object objE = constraintLayout.e(0, str);
            if (objE instanceof Integer) {
                iE = ((Integer) objE).intValue();
            }
        }
        if (iE == 0 && constraintLayout != null) {
            iE = e(constraintLayout, str);
        }
        if (iE == 0) {
            try {
                iE = i.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return iE == 0 ? this.f1104d.getResources().getIdentifier(str, "id", this.f1104d.getPackageName()) : iE;
    }

    protected void c() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        d((ConstraintLayout) parent);
    }

    protected void d(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
        for (int i = 0; i < this.f1103c; i++) {
            View viewG = constraintLayout.g(this.f1102b[i]);
            if (viewG != null) {
                viewG.setVisibility(visibility);
                if (elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                    viewG.setTranslationZ(viewG.getTranslationZ() + elevation);
                }
            }
        }
    }

    protected void g(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, j.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == j.m1) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.g = string;
                    setIds(string);
                }
            }
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f1102b, this.f1103c);
    }

    public void h(a.e.b.k.e eVar, boolean z) {
    }

    public void i(ConstraintLayout constraintLayout) {
    }

    public void j(ConstraintLayout constraintLayout) {
    }

    public void k(ConstraintLayout constraintLayout) {
    }

    public void l(ConstraintLayout constraintLayout) {
        String str;
        int iE;
        if (isInEditMode()) {
            setIds(this.g);
        }
        a.e.b.k.h hVar = this.e;
        if (hVar == null) {
            return;
        }
        hVar.c();
        for (int i = 0; i < this.f1103c; i++) {
            int i2 = this.f1102b[i];
            View viewG = constraintLayout.g(i2);
            if (viewG == null && (iE = e(constraintLayout, (str = this.i.get(Integer.valueOf(i2))))) != 0) {
                this.f1102b[i] = iE;
                this.i.put(Integer.valueOf(iE), str);
                viewG = constraintLayout.g(iE);
            }
            if (viewG != null) {
                this.e.a(constraintLayout.h(viewG));
            }
        }
        this.e.b(constraintLayout.f1083d);
    }

    public void m() {
        if (this.e == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).n0 = (a.e.b.k.e) this.e;
        }
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

    protected void setIds(String str) {
        this.g = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.f1103c = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                a(str.substring(i));
                return;
            } else {
                a(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.g = null;
        this.f1103c = 0;
        for (int i : iArr) {
            b(i);
        }
    }
}
