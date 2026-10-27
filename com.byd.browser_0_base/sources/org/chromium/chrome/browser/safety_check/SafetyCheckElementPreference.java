package org.chromium.chrome.browser.safety_check;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import defpackage.dm0;
import defpackage.n32;
import defpackage.td2;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.safety_check.SafetyCheckElementPreference;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;

/* JADX INFO: loaded from: classes2.dex */
public class SafetyCheckElementPreference extends ChromeBasePreference {
    public View Y;
    public ImageView Z;
    public Callback<Void> a0;

    public SafetyCheckElementPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.K = R$layout.safety_check_status;
        this.a0 = null;
    }

    public final void Z() {
        ImageView imageView = this.Z;
        if (imageView == null || this.Y == null) {
            this.a0 = new td2(20, this);
        } else {
            imageView.setVisibility(8);
            this.Y.setVisibility(8);
        }
    }

    public final void a0() {
        ImageView imageView = this.Z;
        if (imageView == null || this.Y == null) {
            this.a0 = new dm0(26, this);
        } else {
            imageView.setVisibility(8);
            this.Y.setVisibility(0);
        }
    }

    public final void b0(final int i) {
        ImageView imageView = this.Z;
        if (imageView == null || this.Y == null) {
            this.a0 = new Callback() { // from class: xx1
                @Override // org.chromium.base.Callback
                public final void onResult(Object obj) {
                    ((SafetyCheckElementPreference) this).b0(i);
                }
            };
            return;
        }
        imageView.setImageResource(i);
        this.Y.setVisibility(8);
        this.Z.setVisibility(0);
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeBasePreference, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        this.Y = n32Var.u(R$id.progress);
        this.Z = (ImageView) n32Var.u(R$id.status_view);
        Callback<Void> callback = this.a0;
        if (callback != null) {
            callback.onResult(null);
        }
        this.a0 = null;
    }
}
