package org.chromium.chrome.browser.browsing_data;

import J.N;
import android.view.View;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment;

/* JADX INFO: loaded from: classes.dex */
public class BrowsingDataCounterBridge {
    public long a;
    public final a b;

    public interface a {
    }

    public BrowsingDataCounterBridge(a aVar, int i, int i2) {
        this.b = aVar;
        this.a = N.MfPmZbvq(this, i, i2);
    }

    @CalledByNative
    public final void onBrowsingDataCounterFinished(String str) {
        View view;
        ClearBrowsingDataFragment.a aVar = (ClearBrowsingDataFragment.a) this.b;
        ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference = aVar.g;
        clearBrowsingDataCheckBoxPreference.R(str);
        if (!aVar.i || (view = clearBrowsingDataCheckBoxPreference.Y) == null) {
            return;
        }
        view.announceForAccessibility(str);
    }
}
