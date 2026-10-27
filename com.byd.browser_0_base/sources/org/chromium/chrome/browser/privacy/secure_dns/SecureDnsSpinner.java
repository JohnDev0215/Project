package org.chromium.chrome.browser.privacy.secure_dns;

import android.content.Context;
import android.util.AttributeSet;
import org.chromium.chrome.R$drawable;
import skin.support.widget.SkinCompatSpinner;

/* JADX INFO: loaded from: classes2.dex */
public class SecureDnsSpinner extends SkinCompatSpinner {
    public boolean q;
    public a r;

    public interface a {
    }

    public SecureDnsSpinner(Context context) {
        super(context);
        this.q = false;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        SecureDnsSpinner secureDnsSpinner;
        super.onWindowFocusChanged(z);
        if (this.q && z) {
            this.q = false;
            a aVar = this.r;
            if (aVar == null || (secureDnsSpinner = ((SecureDnsProviderPreference) aVar).Z) == null) {
                return;
            }
            secureDnsSpinner.setBackgroundResource(R$drawable.dns_spinner_bg_down);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatSpinner, android.widget.Spinner, android.view.View
    public final boolean performClick() {
        SecureDnsSpinner secureDnsSpinner;
        this.q = true;
        a aVar = this.r;
        if (aVar != null && (secureDnsSpinner = ((SecureDnsProviderPreference) aVar).Z) != null) {
            secureDnsSpinner.setBackgroundResource(R$drawable.dns_spinner_bg_up);
        }
        return super.performClick();
    }

    public void setSpinnerPopStatusListener(a aVar) {
        this.r = aVar;
    }

    public SecureDnsSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = false;
    }

    public SecureDnsSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q = false;
    }

    public void setSpinnerView(int i) {
    }
}
