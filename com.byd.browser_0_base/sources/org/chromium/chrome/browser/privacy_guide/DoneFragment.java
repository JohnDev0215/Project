package org.chromium.chrome.browser.privacy_guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import defpackage.by1;
import defpackage.vt;
import defpackage.z7;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.privacy_guide.a;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class DoneFragment extends Fragment {
    public static final /* synthetic */ int b0 = 0;
    public a.InterfaceC0110a Z;
    public SettingsLauncher a0;

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R$layout.privacy_guide_done, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        ((ChromeImageButton) view.findViewById(R$id.ps_button)).setOnClickListener(new by1(16, this));
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        if (z7.v(profileD).b(0)) {
            ((ChromeImageButton) view.findViewById(R$id.waa_button)).setOnClickListener(new vt(18, this));
        } else {
            view.findViewById(R$id.waa_heading).setVisibility(8);
            view.findViewById(R$id.waa_explanation).setVisibility(8);
        }
    }
}
