package androidx.preference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import defpackage.fh3;
import defpackage.n32;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceCategory extends PreferenceGroup {
    public PreferenceCategory(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.preference.Preference
    public final boolean V() {
        return !super.q();
    }

    @Override // androidx.preference.Preference
    public final boolean q() {
        return false;
    }

    @Override // androidx.preference.Preference
    public void y(n32 n32Var) {
        super.y(n32Var);
        n32Var.e.setAccessibilityHeading(true);
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, fh3.a(context, R$attr.preferenceCategoryStyle, R.attr.preferenceCategoryStyle));
    }

    public PreferenceCategory(Context context) {
        this(context, null);
    }
}
