package androidx.preference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.RestrictTo;
import androidx.preference.d;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class PreferenceScreen extends PreferenceGroup {
    private boolean T;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, androidx.core.content.res.f.a(context, R$attr.preferenceScreenStyle, R.attr.preferenceScreenStyle));
        this.T = true;
    }

    @Override // androidx.preference.PreferenceGroup
    protected boolean J0() {
        return false;
    }

    public boolean M0() {
        return this.T;
    }

    @Override // androidx.preference.Preference
    protected void N() {
        d.b bVarD;
        if (o() != null || l() != null || I0() == 0 || (bVarD = x().d()) == null) {
            return;
        }
        bVarD.b(this);
    }
}
