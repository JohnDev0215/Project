package org.chromium.chrome.browser.autofill.settings;

import android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import defpackage.n32;
import org.chromium.chrome.R$dimen;

/* JADX INFO: loaded from: classes.dex */
public class AndroidPaymentAppPreference extends Preference {
    public AndroidPaymentAppPreference(Context context) {
        super(context, null);
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        int dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R$dimen.payments_favicon_size);
        View viewU = n32Var.u(R.id.icon);
        ViewGroup.LayoutParams layoutParams = viewU.getLayoutParams();
        layoutParams.width = dimensionPixelSize;
        layoutParams.height = dimensionPixelSize;
        viewU.setLayoutParams(layoutParams);
    }
}
