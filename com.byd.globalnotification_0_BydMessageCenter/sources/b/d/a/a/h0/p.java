package b.d.a.a.h0;

import a.b.p.k0;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Filterable;
import android.widget.ListAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class p extends a.b.p.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final k0 f1942d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AccessibilityManager f1943e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Rect f1944f;

    /* JADX WARN: Illegal instructions before constructor call */
    public p(Context context, AttributeSet attributeSet) {
        int i = b.d.a.a.b.autoCompleteTextViewStyle;
        super(b.d.a.a.i0.a.a.a(context, attributeSet, i, 0), attributeSet, i);
        this.f1944f = new Rect();
        Context context2 = getContext();
        TypedArray typedArrayD = b.d.a.a.z.k.d(context2, attributeSet, b.d.a.a.k.MaterialAutoCompleteTextView, i, b.d.a.a.j.Widget_AppCompat_AutoCompleteTextView, new int[0]);
        if (typedArrayD.hasValue(b.d.a.a.k.MaterialAutoCompleteTextView_android_inputType) && typedArrayD.getInt(b.d.a.a.k.MaterialAutoCompleteTextView_android_inputType, 0) == 0) {
            setKeyListener(null);
        }
        this.f1943e = (AccessibilityManager) context2.getSystemService("accessibility");
        k0 k0Var = new k0(context2, null, a.b.a.listPopupWindowStyle, 0);
        this.f1942d = k0Var;
        k0Var.s(true);
        k0 k0Var2 = this.f1942d;
        k0Var2.r = this;
        k0Var2.B.setInputMethodMode(2);
        this.f1942d.o(getAdapter());
        this.f1942d.s = new o(this);
        typedArrayD.recycle();
    }

    public static void a(p pVar, Object obj) {
        pVar.setText(pVar.convertSelectionToString(obj), false);
    }

    public final TextInputLayout b() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout textInputLayoutB = b();
        return (textInputLayoutB == null || !textInputLayoutB.C) ? super.getHint() : textInputLayoutB.getHint();
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout textInputLayoutB = b();
        if (textInputLayoutB != null && textInputLayoutB.C && super.getHint() == null && Build.MANUFACTURER.toLowerCase(Locale.ENGLISH).equals("meizu")) {
            setHint("");
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            int measuredWidth = getMeasuredWidth();
            ListAdapter adapter = getAdapter();
            TextInputLayout textInputLayoutB = b();
            int measuredWidth2 = 0;
            if (adapter != null && textInputLayoutB != null) {
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
                k0 k0Var = this.f1942d;
                int iMin = Math.min(adapter.getCount(), Math.max(0, !k0Var.a() ? -1 : k0Var.f305c.getSelectedItemPosition()) + 15);
                View view = null;
                int iMax = 0;
                for (int iMax2 = Math.max(0, iMin - 15); iMax2 < iMin; iMax2++) {
                    int itemViewType = adapter.getItemViewType(iMax2);
                    if (itemViewType != measuredWidth2) {
                        view = null;
                        measuredWidth2 = itemViewType;
                    }
                    view = adapter.getView(iMax2, view, textInputLayoutB);
                    if (view.getLayoutParams() == null) {
                        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                    }
                    view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                    iMax = Math.max(iMax, view.getMeasuredWidth());
                }
                Drawable drawableN = this.f1942d.n();
                if (drawableN != null) {
                    drawableN.getPadding(this.f1944f);
                    Rect rect = this.f1944f;
                    iMax += rect.left + rect.right;
                }
                measuredWidth2 = textInputLayoutB.getEndIconView().getMeasuredWidth() + iMax;
            }
            setMeasuredDimension(Math.min(Math.max(measuredWidth, measuredWidth2), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public <T extends ListAdapter & Filterable> void setAdapter(T t) {
        super.setAdapter(t);
        this.f1942d.o(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public void showDropDown() {
        AccessibilityManager accessibilityManager = this.f1943e;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.showDropDown();
        } else {
            this.f1942d.i();
        }
    }
}
