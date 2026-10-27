package org.chromium.chrome.browser.download.settings;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.preference.c;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes.dex */
public class DownloadLocationPreferenceDialog extends c {
    @Override // androidx.preference.c
    public final void O0(View view) {
        ((ListView) view.findViewById(R$id.location_preference_list_view)).setAdapter((ListAdapter) ((DownloadLocationPreference) N0()).Y);
        super.O0(view);
    }

    @Override // androidx.preference.c
    public final void P0(boolean z) {
    }
}
