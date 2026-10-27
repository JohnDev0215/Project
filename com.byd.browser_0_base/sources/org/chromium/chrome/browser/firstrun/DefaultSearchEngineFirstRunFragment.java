package org.chromium.chrome.browser.firstrun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import defpackage.hn0;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.locale.LocaleManager;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSearchEngineFirstRunFragment extends Fragment implements hn0 {
    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R$layout.default_search_engine_first_run_fragment, viewGroup, false);
        ((Button) viewInflate.findViewById(R$id.button_primary)).setEnabled(false);
        LocaleManager.getInstance().a.getClass();
        return viewInflate;
    }

    @Override // defpackage.hn0
    public final void h() {
        View view = this.f12J;
        if (view == null) {
            return;
        }
        view.findViewById(R$id.chooser_title).sendAccessibilityEvent(8);
    }
}
