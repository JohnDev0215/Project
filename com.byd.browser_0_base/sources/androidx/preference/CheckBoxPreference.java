package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import defpackage.fh3;
import defpackage.n32;

/* JADX INFO: loaded from: classes.dex */
public class CheckBoxPreference extends TwoStatePreference {
    public final a X;

    public class a implements CompoundButton.OnCheckedChangeListener {
        public a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Boolean boolValueOf = Boolean.valueOf(z);
            CheckBoxPreference checkBoxPreference = CheckBoxPreference.this;
            if (checkBoxPreference.h(boolValueOf)) {
                checkBoxPreference.Y(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    @Override // androidx.preference.Preference
    public final void H(View view) {
        super.H(view);
        if (((AccessibilityManager) this.e.getSystemService("accessibility")).isEnabled()) {
            a0(view.findViewById(R.id.checkbox));
            Z(view.findViewById(R.id.summary));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a0(View view) {
        boolean z = view instanceof CompoundButton;
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.S);
        }
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(this.X);
        }
    }

    @Override // androidx.preference.Preference
    public void y(n32 n32Var) {
        super.y(n32Var);
        a0(n32Var.u(R.id.checkbox));
        Z(n32Var.u(R.id.summary));
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.X = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CheckBoxPreference, i, i2);
        this.T = fh3.f(typedArrayObtainStyledAttributes, R$styleable.CheckBoxPreference_summaryOn, R$styleable.CheckBoxPreference_android_summaryOn);
        if (this.S) {
            s();
        }
        this.U = fh3.f(typedArrayObtainStyledAttributes, R$styleable.CheckBoxPreference_summaryOff, R$styleable.CheckBoxPreference_android_summaryOff);
        if (!this.S) {
            s();
        }
        this.W = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CheckBoxPreference_disableDependentsState, typedArrayObtainStyledAttributes.getBoolean(R$styleable.CheckBoxPreference_android_disableDependentsState, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, fh3.a(context, R$attr.checkBoxPreferenceStyle, R.attr.checkBoxPreferenceStyle));
    }

    public CheckBoxPreference(Context context) {
        this(context, null);
    }
}
