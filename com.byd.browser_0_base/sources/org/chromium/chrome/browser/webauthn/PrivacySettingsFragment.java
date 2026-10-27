package org.chromium.chrome.browser.webauthn;

import J.N;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacySettingsFragment extends Fragment implements View.OnClickListener {
    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        D().setTitle(R$string.cablev2_paask_title);
        View viewInflate = layoutInflater.inflate(R$layout.cablev2_settings, viewGroup, false);
        viewInflate.findViewById(R$id.unlink_button).setOnClickListener(this);
        return viewInflate;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        N.MV9JyCNy();
        Toast.b(0, D(), N().getString(R$string.cablev2_unlink_confirmation)).e();
    }
}
