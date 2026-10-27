package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import defpackage.fh3;
import defpackage.n32;

/* JADX INFO: loaded from: classes.dex */
public class SwitchPreferenceCompat extends TwoStatePreference {
    public final a X;
    public CharSequence Y;
    public CharSequence Z;

    public class a implements CompoundButton.OnCheckedChangeListener {
        public a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Boolean boolValueOf = Boolean.valueOf(z);
            SwitchPreferenceCompat switchPreferenceCompat = SwitchPreferenceCompat.this;
            if (switchPreferenceCompat.h(boolValueOf)) {
                switchPreferenceCompat.Y(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.X = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreferenceCompat, i, i2);
        this.T = fh3.f(typedArrayObtainStyledAttributes, R$styleable.SwitchPreferenceCompat_summaryOn, R$styleable.SwitchPreferenceCompat_android_summaryOn);
        if (this.S) {
            s();
        }
        this.U = fh3.f(typedArrayObtainStyledAttributes, R$styleable.SwitchPreferenceCompat_summaryOff, R$styleable.SwitchPreferenceCompat_android_summaryOff);
        if (!this.S) {
            s();
        }
        this.Y = fh3.f(typedArrayObtainStyledAttributes, R$styleable.SwitchPreferenceCompat_switchTextOn, R$styleable.SwitchPreferenceCompat_android_switchTextOn);
        s();
        this.Z = fh3.f(typedArrayObtainStyledAttributes, R$styleable.SwitchPreferenceCompat_switchTextOff, R$styleable.SwitchPreferenceCompat_android_switchTextOff);
        s();
        this.W = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SwitchPreferenceCompat_disableDependentsState, typedArrayObtainStyledAttributes.getBoolean(R$styleable.SwitchPreferenceCompat_android_disableDependentsState, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public final void H(View view) {
        super.H(view);
        if (((AccessibilityManager) this.e.getSystemService("accessibility")).isEnabled()) {
            a0(view.findViewById(R$id.switchWidget));
            Z(view.findViewById(R.id.summary));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a0(View view) {
        boolean z = view instanceof SwitchCompat;
        if (z) {
            ((SwitchCompat) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.S);
        }
        if (z) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.setTextOn(this.Y);
            switchCompat.setTextOff(this.Z);
            switchCompat.setOnCheckedChangeListener(this.X);
        }
    }

    @Override // androidx.preference.Preference
    public void y(n32 n32Var) {
        super.y(n32Var);
        a0(n32Var.u(R$id.switchWidget));
        Z(n32Var.u(R.id.summary));
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchPreferenceCompatStyle);
    }

    public SwitchPreferenceCompat(Context context) {
        this(context, null);
    }
}
