package a.f.b;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f630b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f631c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a.f.a.i.g f632d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f633e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f634f;
    public String g;
    public View[] h;
    public HashMap<Integer, String> i;

    public b(Context context) {
        super(context);
        this.f629a = new int[32];
        this.f633e = false;
        this.h = null;
        this.i = new HashMap<>();
        this.f631c = context;
        g(null);
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f629a = new int[32];
        this.f633e = false;
        this.h = null;
        this.i = new HashMap<>();
        this.f631c = context;
        g(attributeSet);
    }

    public final void a(String str) {
        if (str == null || str.length() == 0 || this.f631c == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        int identifier = 0;
        if (isInEditMode() && constraintLayout != null) {
            Object objC = constraintLayout.c(0, strTrim);
            if (objC instanceof Integer) {
                identifier = ((Integer) objC).intValue();
            }
        }
        if (identifier == 0 && constraintLayout != null) {
            identifier = f(constraintLayout, strTrim);
        }
        if (identifier == 0) {
            try {
                identifier = h.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
            }
        }
        if (identifier == 0) {
            identifier = this.f631c.getResources().getIdentifier(strTrim, "id", this.f631c.getPackageName());
        }
        if (identifier != 0) {
            this.i.put(Integer.valueOf(identifier), strTrim);
            b(identifier);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    public final void b(int i) {
        if (i == getId()) {
            return;
        }
        int i2 = this.f630b + 1;
        int[] iArr = this.f629a;
        if (i2 > iArr.length) {
            this.f629a = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f629a;
        int i3 = this.f630b;
        iArr2[i3] = i;
        this.f630b = i3 + 1;
    }

    public final void c(String str) {
        if (str == null || str.length() == 0 || this.f631c == null) {
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
            if ((layoutParams instanceof ConstraintLayout.a) && strTrim.equals(((ConstraintLayout.a) layoutParams).Y)) {
                if (childAt.getId() == -1) {
                    StringBuilder sbD = b.a.a.a.a.d("to use ConstraintTag view ");
                    sbD.append(childAt.getClass().getSimpleName());
                    sbD.append(" must have an ID");
                    Log.w("ConstraintHelper", sbD.toString());
                } else {
                    b(childAt.getId());
                }
            }
        }
    }

    public void d() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        e((ConstraintLayout) parent);
    }

    public void e(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = getElevation();
        for (int i = 0; i < this.f630b; i++) {
            View viewD = constraintLayout.d(this.f629a[i]);
            if (viewD != null) {
                viewD.setVisibility(visibility);
                if (elevation > 0.0f) {
                    viewD.setTranslationZ(viewD.getTranslationZ() + elevation);
                }
            }
        }
    }

    public final int f(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.f631c.getResources()) == null) {
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

    public void g(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, i.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == i.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f634f = string;
                    setIds(string);
                } else if (index == i.ConstraintLayout_Layout_constraint_referenced_tags) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.g = string2;
                    setReferenceTags(string2);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f629a, this.f630b);
    }

    public void h(a.f.a.i.d dVar, boolean z) {
    }

    public void i() {
    }

    public void j() {
    }

    public void k() {
    }

    public void l() {
        if (this.f632d == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.a) {
            ((ConstraintLayout.a) layoutParams).r0 = (a.f.a.i.d) this.f632d;
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f634f;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.g;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.f633e) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void setIds(String str) {
        this.f634f = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.f630b = 0;
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

    public void setReferenceTags(String str) {
        this.g = str;
        if (str == null) {
            return;
        }
        int i = 0;
        this.f630b = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                c(str.substring(i));
                return;
            } else {
                c(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.f634f = null;
        this.f630b = 0;
        for (int i : iArr) {
            b(i);
        }
    }

    @Override // android.view.View
    public void setTag(int i, Object obj) {
        super.setTag(i, obj);
        if (obj == null && this.f634f == null) {
            b(i);
        }
    }
}
