package org.chromium.chrome.browser.privacy_guide;

import J.N;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.privacy_guide.MSBBFragment;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public class MSBBFragment extends Fragment {
    public static final /* synthetic */ int Z = 0;

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R$layout.privacy_guide_msbb_step, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R$id.msbb_switch);
        switchCompat.setChecked(N.Mfmn09fr(Profile.d()));
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: wa1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int i = MSBBFragment.Z;
                if (z) {
                    n3.X0("Settings.PrivacyGuide.ChangeMSBBOn");
                } else {
                    n3.X0("Settings.PrivacyGuide.ChangeMSBBOff");
                }
                N.MnEYaN9w(Profile.d(), z);
            }
        });
    }
}
