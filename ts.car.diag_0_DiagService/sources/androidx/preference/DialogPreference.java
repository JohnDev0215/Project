package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogPreference extends Preference {
    private CharSequence P;
    private CharSequence Q;
    private Drawable R;
    private CharSequence S;
    private CharSequence T;
    private int U;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        @Nullable
        <T extends Preference> T d(@NonNull CharSequence charSequence);
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.DialogPreference, i, i2);
        String strO = androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_dialogTitle, R$styleable.DialogPreference_android_dialogTitle);
        this.P = strO;
        if (strO == null) {
            this.P = A();
        }
        this.Q = androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_dialogMessage, R$styleable.DialogPreference_android_dialogMessage);
        this.R = androidx.core.content.res.f.c(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_dialogIcon, R$styleable.DialogPreference_android_dialogIcon);
        this.S = androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_positiveButtonText, R$styleable.DialogPreference_android_positiveButtonText);
        this.T = androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_negativeButtonText, R$styleable.DialogPreference_android_negativeButtonText);
        this.U = androidx.core.content.res.f.n(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_dialogLayout, R$styleable.DialogPreference_android_dialogLayout, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public Drawable E0() {
        return this.R;
    }

    public int F0() {
        return this.U;
    }

    public CharSequence G0() {
        return this.Q;
    }

    public CharSequence H0() {
        return this.P;
    }

    public CharSequence I0() {
        return this.T;
    }

    public CharSequence J0() {
        return this.S;
    }

    @Override // androidx.preference.Preference
    protected void N() {
        x().o(this);
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DialogPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.core.content.res.f.a(context, R$attr.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }
}
