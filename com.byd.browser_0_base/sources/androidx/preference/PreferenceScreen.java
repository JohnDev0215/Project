package androidx.preference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.PreferenceManager;
import defpackage.fh3;

/* JADX INFO: loaded from: classes.dex */
public final class PreferenceScreen extends PreferenceGroup {
    public boolean b0;

    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, fh3.a(context, R$attr.preferenceScreenStyle, R.attr.preferenceScreenStyle));
        this.b0 = true;
    }

    @Override // androidx.preference.Preference
    public final void z() {
        PreferenceManager.b bVar;
        if (this.q != null || this.r != null || b0() == 0 || (bVar = this.f.j) == null) {
            return;
        }
        bVar.B(this);
    }
}
