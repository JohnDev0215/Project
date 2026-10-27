package com.android.bluetooth.pbapclient;

import com.android.vcard.VCardEntry;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class PullRequest {
    protected List<VCardEntry> mEntries;
    public String path;

    public abstract void onPullComplete();

    public String toString() {
        return "PullRequest: { path=" + this.path + " }";
    }

    public void setResults(List<VCardEntry> results) {
        this.mEntries = results;
    }
}
