package org.chromium.components.omnibox.action;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class HistoryClustersAction extends OmniboxPedal {
    public HistoryClustersAction(int i, String str, String str2) {
        super(i, str);
    }

    @CalledByNative
    public static HistoryClustersAction build(int i, String str, String str2, String str3, String str4, GURL gurl, String str5) {
        return new HistoryClustersAction(i, str, str5);
    }
}
