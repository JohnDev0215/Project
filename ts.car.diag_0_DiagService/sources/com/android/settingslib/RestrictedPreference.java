package com.android.settingslib;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.f;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class RestrictedPreference extends TwoTargetPreference {
    b S;

    public RestrictedPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.S = new b(context, this, attributeSet);
    }

    @Override // com.android.settingslib.TwoTargetPreference
    protected int E0() {
        return R$layout.restricted_icon;
    }

    @Override // com.android.settingslib.TwoTargetPreference
    protected boolean G0() {
        return !H0();
    }

    public boolean H0() {
        return this.S.a();
    }

    @Override // com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void M(f fVar) {
        super.M(fVar);
        this.S.b(fVar);
        View viewM = fVar.M(R$id.restricted_icon);
        if (viewM != null) {
            viewM.setVisibility(H0() ? 0 : 8);
        }
    }

    @Override // androidx.preference.Preference
    public void V() {
        if (this.S.c()) {
            return;
        }
        super.V();
    }

    public RestrictedPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public RestrictedPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.core.content.res.f.a(context, R$attr.preferenceStyle, R.attr.preferenceStyle));
    }
}
