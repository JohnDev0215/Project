package com.google.android.material.textfield;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.ListPopupWindow;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$layout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.gv;
import defpackage.in3;
import defpackage.om3;
import defpackage.r83;
import defpackage.rc1;
import defpackage.uc1;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    public final ListPopupWindow i;
    public final AccessibilityManager j;
    public final Rect k;
    public final int l;
    public final float m;
    public int n;
    public ColorStateList o;

    public class a implements AdapterView.OnItemClickListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Object item;
            MaterialAutoCompleteTextView materialAutoCompleteTextView = MaterialAutoCompleteTextView.this;
            if (i < 0) {
                ListPopupWindow listPopupWindow = materialAutoCompleteTextView.i;
                item = !listPopupWindow.b() ? null : listPopupWindow.g.getSelectedItem();
            } else {
                item = materialAutoCompleteTextView.getAdapter().getItem(i);
            }
            MaterialAutoCompleteTextView.a(materialAutoCompleteTextView, item);
            AdapterView.OnItemClickListener onItemClickListener = materialAutoCompleteTextView.getOnItemClickListener();
            ListPopupWindow listPopupWindow2 = materialAutoCompleteTextView.i;
            if (onItemClickListener != null) {
                if (view == null || i < 0) {
                    view = listPopupWindow2.b() ? listPopupWindow2.g.getSelectedView() : null;
                    i = !listPopupWindow2.b() ? -1 : listPopupWindow2.g.getSelectedItemPosition();
                    j = !listPopupWindow2.b() ? Long.MIN_VALUE : listPopupWindow2.g.getSelectedItemId();
                }
                onItemClickListener.onItemClick(listPopupWindow2.g, view, i, j);
            }
            listPopupWindow2.dismiss();
        }
    }

    public class b<T> extends ArrayAdapter<String> {
        public ColorStateList e;
        public ColorStateList f;

        public b(Context context, int i, String[] strArr) {
            super(context, i, strArr);
            a();
        }

        public final void a() {
            ColorStateList colorStateList;
            MaterialAutoCompleteTextView materialAutoCompleteTextView = MaterialAutoCompleteTextView.this;
            ColorStateList colorStateList2 = materialAutoCompleteTextView.o;
            ColorStateList colorStateList3 = null;
            if (colorStateList2 != null) {
                int[] iArr = {R.attr.state_pressed};
                colorStateList = new ColorStateList(new int[][]{iArr, new int[0]}, new int[]{colorStateList2.getColorForState(iArr, 0), 0});
            } else {
                colorStateList = null;
            }
            this.f = colorStateList;
            if (materialAutoCompleteTextView.n != 0) {
                if (materialAutoCompleteTextView.o != null) {
                    int[] iArr2 = {R.attr.state_hovered, -16842919};
                    int[] iArr3 = {R.attr.state_selected, -16842919};
                    colorStateList3 = new ColorStateList(new int[][]{iArr3, iArr2, new int[0]}, new int[]{gv.f(materialAutoCompleteTextView.o.getColorForState(iArr3, 0), materialAutoCompleteTextView.n), gv.f(materialAutoCompleteTextView.o.getColorForState(iArr2, 0), materialAutoCompleteTextView.n), materialAutoCompleteTextView.n});
                }
            }
            this.e = colorStateList3;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            if (view2 instanceof TextView) {
                TextView textView = (TextView) view2;
                MaterialAutoCompleteTextView materialAutoCompleteTextView = MaterialAutoCompleteTextView.this;
                Drawable rippleDrawable = null;
                if (materialAutoCompleteTextView.getText().toString().contentEquals(textView.getText())) {
                    if (materialAutoCompleteTextView.n != 0) {
                        ColorDrawable colorDrawable = new ColorDrawable(materialAutoCompleteTextView.n);
                        if (this.f != null) {
                            colorDrawable.setTintList(this.e);
                            rippleDrawable = new RippleDrawable(this.f, colorDrawable, null);
                        } else {
                            rippleDrawable = colorDrawable;
                        }
                    }
                }
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.d.n(textView, rippleDrawable);
            }
            return view2;
        }
    }

    public MaterialAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public static void a(MaterialAutoCompleteTextView materialAutoCompleteTextView, Object obj) {
        materialAutoCompleteTextView.setText(materialAutoCompleteTextView.convertSelectionToString(obj), false);
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
        return (textInputLayoutB == null || !textInputLayoutB.G) ? super.getHint() : textInputLayoutB.getHint();
    }

    public float getPopupElevation() {
        return this.m;
    }

    public int getSimpleItemSelectedColor() {
        return this.n;
    }

    public ColorStateList getSimpleItemSelectedRippleColor() {
        return this.o;
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout textInputLayoutB = b();
        if (textInputLayoutB != null && textInputLayoutB.G && super.getHint() == null && Build.MANUFACTURER.toLowerCase(Locale.ENGLISH).equals("meizu")) {
            setHint("");
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            int measuredWidth = getMeasuredWidth();
            ListAdapter adapter = getAdapter();
            TextInputLayout textInputLayoutB = b();
            int measuredWidth2 = 0;
            if (adapter != null && textInputLayoutB != null) {
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
                ListPopupWindow listPopupWindow = this.i;
                int iMin = Math.min(adapter.getCount(), Math.max(0, !listPopupWindow.b() ? -1 : listPopupWindow.g.getSelectedItemPosition()) + 15);
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
                Drawable drawableP = listPopupWindow.p();
                if (drawableP != null) {
                    Rect rect = this.k;
                    drawableP.getPadding(rect);
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
        this.i.v(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(onItemSelectedListener);
        this.i.setOnItemSelectedListener(getOnItemSelectedListener());
    }

    @Override // android.widget.TextView
    public void setRawInputType(int i) {
        super.setRawInputType(i);
        TextInputLayout textInputLayoutB = b();
        if (textInputLayoutB != null) {
            textInputLayoutB.r();
        }
    }

    public void setSimpleItemSelectedColor(int i) {
        this.n = i;
        if (getAdapter() instanceof b) {
            ((b) getAdapter()).a();
        }
    }

    public void setSimpleItemSelectedRippleColor(ColorStateList colorStateList) {
        this.o = colorStateList;
        if (getAdapter() instanceof b) {
            ((b) getAdapter()).a();
        }
    }

    public void setSimpleItems(int i) {
        setSimpleItems(getResources().getStringArray(i));
    }

    @Override // android.widget.AutoCompleteTextView
    public final void showDropDown() {
        AccessibilityManager accessibilityManager = this.j;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.showDropDown();
        } else {
            this.i.a();
        }
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    public void setSimpleItems(String[] strArr) {
        setAdapter(new b(getContext(), this.l, strArr));
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(uc1.a(context, attributeSet, i, 0), attributeSet, i);
        this.k = new Rect();
        Context context2 = getContext();
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.MaterialAutoCompleteTextView, i, R$style.Widget_AppCompat_AutoCompleteTextView, new int[0]);
        int i2 = R$styleable.MaterialAutoCompleteTextView_android_inputType;
        if (typedArrayD.hasValue(i2) && typedArrayD.getInt(i2, 0) == 0) {
            setKeyListener(null);
        }
        this.l = typedArrayD.getResourceId(R$styleable.MaterialAutoCompleteTextView_simpleItemLayout, R$layout.mtrl_auto_complete_simple_item);
        this.m = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialAutoCompleteTextView_android_popupElevation, R$dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        this.n = typedArrayD.getColor(R$styleable.MaterialAutoCompleteTextView_simpleItemSelectedColor, 0);
        this.o = rc1.a(context2, typedArrayD, R$styleable.MaterialAutoCompleteTextView_simpleItemSelectedRippleColor);
        this.j = (AccessibilityManager) context2.getSystemService("accessibility");
        ListPopupWindow listPopupWindow = new ListPopupWindow(context2);
        this.i = listPopupWindow;
        listPopupWindow.C = true;
        PopupWindow popupWindow = listPopupWindow.D;
        popupWindow.setFocusable(true);
        listPopupWindow.s = this;
        popupWindow.setInputMethodMode(2);
        listPopupWindow.v(getAdapter());
        listPopupWindow.setOnItemClickListener(new a());
        int i3 = R$styleable.MaterialAutoCompleteTextView_simpleItems;
        if (typedArrayD.hasValue(i3)) {
            setSimpleItems(typedArrayD.getResourceId(i3, 0));
        }
        typedArrayD.recycle();
    }
}
