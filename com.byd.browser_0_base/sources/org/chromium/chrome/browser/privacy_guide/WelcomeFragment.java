package org.chromium.chrome.browser.privacy_guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import org.chromium.chrome.R$layout;

/* JADX INFO: loaded from: classes2.dex */
public class WelcomeFragment extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R$layout.privacy_guide_welcome, viewGroup, false);
    }
}
