package org.chromium.chrome.browser.privacy_guide;

import J.N;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.fragment.app.Fragment;
import defpackage.n3;
import defpackage.oj2;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;

/* JADX INFO: loaded from: classes2.dex */
public class CookiesFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    public RadioButtonWithDescription Z;
    public RadioButtonWithDescription a0;

    public static void H0(int i) {
        if (i == 1) {
            n3.X0("Settings.PrivacyGuide.ChangeCookiesBlock3P");
        } else if (i == 2) {
            n3.X0("Settings.PrivacyGuide.ChangeCookiesBlock3PIncognito");
        }
        N.MPBZLcVx(oj2.k(Profile.d()).a, "profile.cookie_controls_mode", i);
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R$layout.privacy_guide_cookies_step, viewGroup, false);
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        N.MM1KTgoi(Profile.d(), 0, true);
        if (i == R$id.block_third_party_incognito) {
            H0(2);
        } else if (i == R$id.block_third_party) {
            H0(1);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        ((RadioGroup) view.findViewById(R$id.cookies_radio_button)).setOnCheckedChangeListener(this);
        this.Z = (RadioButtonWithDescription) view.findViewById(R$id.block_third_party_incognito);
        this.a0 = (RadioButtonWithDescription) view.findViewById(R$id.block_third_party);
        N.MJSt3Ocq(Profile.d(), 0);
        int iA = a.a();
        if (iA != 0) {
            if (iA == 1) {
                this.a0.setChecked(true);
            } else {
                if (iA != 2) {
                    return;
                }
                this.Z.setChecked(true);
            }
        }
    }
}
