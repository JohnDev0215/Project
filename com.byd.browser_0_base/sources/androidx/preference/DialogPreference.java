package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.preference.PreferenceManager;
import defpackage.fh3;

/* JADX INFO: loaded from: classes.dex */
public abstract class DialogPreference extends Preference {
    public final CharSequence S;
    public CharSequence T;
    public final Drawable U;
    public final String V;
    public final String W;
    public int X;

    public interface a {
        <T extends Preference> T f(CharSequence charSequence);
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.DialogPreference, i, i2);
        String strF = fh3.f(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_dialogTitle, R$styleable.DialogPreference_android_dialogTitle);
        this.S = strF;
        if (strF == null) {
            this.S = this.l;
        }
        this.T = fh3.f(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_dialogMessage, R$styleable.DialogPreference_android_dialogMessage);
        int i3 = R$styleable.DialogPreference_dialogIcon;
        int i4 = R$styleable.DialogPreference_android_dialogIcon;
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(i3);
        this.U = drawable == null ? typedArrayObtainStyledAttributes.getDrawable(i4) : drawable;
        this.V = fh3.f(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_positiveButtonText, R$styleable.DialogPreference_android_positiveButtonText);
        this.W = fh3.f(typedArrayObtainStyledAttributes, R$styleable.DialogPreference_negativeButtonText, R$styleable.DialogPreference_android_negativeButtonText);
        this.X = typedArrayObtainStyledAttributes.getResourceId(R$styleable.DialogPreference_dialogLayout, typedArrayObtainStyledAttributes.getResourceId(R$styleable.DialogPreference_android_dialogLayout, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public void z() {
        PreferenceManager.a aVar = this.f.i;
        if (aVar != null) {
            aVar.y(this);
        }
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DialogPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, fh3.a(context, R$attr.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }

    public DialogPreference(Context context) {
        this(context, null);
    }
}
