package org.chromium.chrome.browser.safety_check;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.d;
import defpackage.qj2;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes2.dex */
public class SafetyCheckSettingsFragment extends d {
    public static final /* synthetic */ int k0 = 0;
    public ButtonCompat h0;
    public TextView i0;
    public boolean j0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        qj2.a(this, R$xml.safety_check_preferences);
        D().setTitle(O(R$string.prefs_safety_check));
        Bundle bundle2 = this.j;
        this.j0 = bundle2 != null && bundle2.containsKey("SafetyCheckSettingsFragment.safetyCheckImmediateRun") && this.j.getBoolean("SafetyCheckSettingsFragment.safetyCheckImmediateRun");
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LinearLayout linearLayout = (LinearLayout) super.e0(layoutInflater, viewGroup, bundle);
        LinearLayout linearLayout2 = (LinearLayout) layoutInflater.inflate(R$layout.safety_check_bottom_elements, (ViewGroup) linearLayout, false);
        this.h0 = (ButtonCompat) linearLayout2.findViewById(R$id.safety_check_button);
        this.i0 = (TextView) linearLayout2.findViewById(R$id.safety_check_timestamp);
        linearLayout.addView(linearLayout2);
        return linearLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public final void l0() {
        this.H = true;
        this.j0 = false;
    }
}
