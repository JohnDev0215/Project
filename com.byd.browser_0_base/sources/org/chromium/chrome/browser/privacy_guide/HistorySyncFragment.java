package org.chromium.chrome.browser.privacy_guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import defpackage.n3;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;

/* JADX INFO: loaded from: classes2.dex */
public class HistorySyncFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R$layout.privacy_guide_history_sync_step, viewGroup, false);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            n3.X0("Settings.PrivacyGuide.ChangeHistorySyncOn");
        } else {
            n3.X0("Settings.PrivacyGuide.ChangeHistorySyncOff");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R$id.history_sync_switch);
        switchCompat.setChecked(false);
        switchCompat.setOnCheckedChangeListener(this);
    }
}
