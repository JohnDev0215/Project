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
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class SwitchPreference extends TwoStatePreference {
    private final a U;
    private CharSequence V;
    private CharSequence W;

    /* JADX INFO: compiled from: caradapter-bug */
    private class a implements CompoundButton.OnCheckedChangeListener {
        a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (SwitchPreference.this.d(Boolean.valueOf(z))) {
                SwitchPreference.this.F0(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.U = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreference, i, i2);
        I0(androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_summaryOn, R$styleable.SwitchPreference_android_summaryOn));
        H0(androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_summaryOff, R$styleable.SwitchPreference_android_summaryOff));
        M0(androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_switchTextOn, R$styleable.SwitchPreference_android_switchTextOn));
        L0(androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_switchTextOff, R$styleable.SwitchPreference_android_switchTextOff));
        G0(androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, R$styleable.SwitchPreference_disableDependentsState, R$styleable.SwitchPreference_android_disableDependentsState, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void N0(View view) {
        boolean z = view instanceof Switch;
        if (z) {
            ((Switch) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.P);
        }
        if (z) {
            Switch r4 = (Switch) view;
            r4.setTextOn(this.V);
            r4.setTextOff(this.W);
            r4.setOnCheckedChangeListener(this.U);
        }
    }

    private void O0(View view) {
        if (((AccessibilityManager) j().getSystemService("accessibility")).isEnabled()) {
            N0(view.findViewById(R.id.switch_widget));
            J0(view.findViewById(R.id.summary));
        }
    }

    public void L0(CharSequence charSequence) {
        this.W = charSequence;
        I();
    }

    @Override // androidx.preference.Preference
    public void M(f fVar) {
        super.M(fVar);
        N0(fVar.M(R.id.switch_widget));
        K0(fVar);
    }

    public void M0(CharSequence charSequence) {
        this.V = charSequence;
        I();
    }

    @Override // androidx.preference.Preference
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    protected void W(View view) {
        super.W(view);
        O0(view);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.core.content.res.f.a(context, R$attr.switchPreferenceStyle, R.attr.switchPreferenceStyle));
    }

    public SwitchPreference(Context context) {
        this(context, null);
    }
}
