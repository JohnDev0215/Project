package org.chromium.chrome.browser.history_clusters;

import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.history_clusters.HistoryCluster;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public final class ClusterVisit {
    public final GURL a;
    public final List<HistoryCluster.a> b;
    public final List<HistoryCluster.a> c;
    public final List<a> d;

    public static class a {
        public a(long j, GURL gurl) {
        }
    }

    public ClusterVisit(GURL gurl, String str, String str2, ArrayList arrayList, ArrayList arrayList2, GURL gurl2, long j, ArrayList arrayList3) {
        this.a = gurl;
        this.b = arrayList;
        this.c = arrayList2;
        this.d = arrayList3;
    }
}
