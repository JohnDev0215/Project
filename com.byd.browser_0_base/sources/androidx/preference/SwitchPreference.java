package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;
import defpackage.fh3;
import defpackage.n32;

/* JADX INFO: loaded from: classes.dex */
public class SwitchPreference extends TwoStatePreference {
    public final a X;
    public CharSequence Y;
    public CharSequence Z;

    public class a implements CompoundButton.OnCheckedChangeListener {
        public a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Boolean boolValueOf = Boolean.valueOf(z);
            SwitchPreference switchPreference = SwitchPreference.this;
            if (switchPreference.h(boolValueOf)) {
                switchPreference.Y(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, 0);
        this.X = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreference, i, 0);
        this.T = fh3.f(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_summaryOn, R$styleable.SwitchPreference_android_summaryOn);
        if (this.S) {
            s();
        }
        this.U = fh3.f(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_summaryOff, R$styleable.SwitchPreference_android_summaryOff);
        if (!this.S) {
            s();
        }
        this.Y = fh3.f(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_switchTextOn, R$styleable.SwitchPreference_android_switchTextOn);
        s();
        this.Z = fh3.f(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_switchTextOff, R$styleable.SwitchPreference_android_switchTextOff);
        s();
        this.W = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SwitchPreference_disableDependentsState, typedArrayObtainStyledAttributes.getBoolean(R$styleable.SwitchPreference_android_disableDependentsState, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public final void H(View view) {
        super.H(view);
        if (((AccessibilityManager) this.e.getSystemService("accessibility")).isEnabled()) {
            a0(view.findViewById(R.id.switch_widget));
            Z(view.findViewById(R.id.summary));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a0(View view) {
        boolean z = view instanceof Switch;
        if (z) {
            ((Switch) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.S);
        }
        if (z) {
            Switch r4 = (Switch) view;
            r4.setTextOn(this.Y);
            r4.setTextOff(this.Z);
            r4.setOnCheckedChangeListener(this.X);
        }
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        a0(n32Var.u(R.id.switch_widget));
        Z(n32Var.u(R.id.summary));
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, fh3.a(context, R$attr.switchPreferenceStyle, R.attr.switchPreferenceStyle));
    }

    public SwitchPreference(Context context) {
        this(context, null);
    }
}
