package com.android.settingslib.widget.apppreference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.preference.Preference;
import androidx.preference.f;
import com.android.settingslib.widget.R$layout;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class AppPreference extends Preference {
    private int P;
    private boolean Q;

    public AppPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m0(R$layout.preference_app);
    }

    @Override // androidx.preference.Preference
    public void M(f fVar) {
        super.M(fVar);
        ProgressBar progressBar = (ProgressBar) fVar.M(R.id.progress);
        if (!this.Q) {
            progressBar.setVisibility(8);
        } else {
            progressBar.setProgress(this.P);
            progressBar.setVisibility(0);
        }
    }
}
