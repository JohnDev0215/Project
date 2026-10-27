package org.chromium.chrome.browser.history_clusters;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class HistoryCluster {
    public final List<ClusterVisit> a;
    public final List<a> b;
    public final List<String> c;

    public static class a {
        public a(int i, int i2) {
        }
    }

    public HistoryCluster(List list, String str, String str2, ArrayList arrayList, long j, List list2) {
        this.a = list;
        this.b = arrayList;
        this.c = list2;
        for (int i = 0; i < this.a.size(); i++) {
            this.a.get(i).getClass();
        }
    }
}
