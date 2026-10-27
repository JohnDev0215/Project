package com.amazon.avod.qos;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class WanFragmentDownloadCounter {
    private int mFragmentsDownloadedOnWan = 0;
    private int mFragmentsDownloadedTotal = 0;

    public String getNotes() {
        int i = this.mFragmentsDownloadedOnWan;
        if (i == 0) {
            return null;
        }
        return String.format(Locale.US, "Downloaded %s out of %s fragments over WAN", Integer.valueOf(i), Integer.valueOf(this.mFragmentsDownloadedTotal));
    }

    public void onFragmentDownloaded(boolean z) {
        if (z) {
            this.mFragmentsDownloadedOnWan++;
        }
        this.mFragmentsDownloadedTotal++;
    }
}
