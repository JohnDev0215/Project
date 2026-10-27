package org.chromium.chrome.browser.history_clusters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.history_clusters.ClusterVisit;
import org.chromium.chrome.browser.history_clusters.HistoryCluster;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class HistoryClustersBridge {
    public HistoryClustersBridge(long j) {
    }

    @CalledByNative
    public static HistoryCluster buildCluster(ClusterVisit[] clusterVisitArr, String str, String str2, int[] iArr, int[] iArr2, long j, String[] strArr) {
        List listAsList = Arrays.asList(clusterVisitArr);
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            arrayList.add(new HistoryCluster.a(iArr[i], iArr2[i]));
        }
        return new HistoryCluster(listAsList, str, str2, arrayList, j, Arrays.asList(strArr));
    }

    @CalledByNative
    public static HistoryClustersResult buildClusterResult(HistoryCluster[] historyClusterArr, String[] strArr, int[] iArr, String str, boolean z, boolean z2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < strArr.length; i++) {
            linkedHashMap.put(strArr[i], Integer.valueOf(iArr[i]));
        }
        return new HistoryClustersResult(Arrays.asList(historyClusterArr), linkedHashMap, str, z, z2);
    }

    @CalledByNative
    public static ClusterVisit buildClusterVisit(float f, GURL gurl, String str, String str2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, GURL gurl2, long j, long[] jArr, GURL[] gurlArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            arrayList.add(new HistoryCluster.a(iArr[i], iArr2[i]));
        }
        ArrayList arrayList2 = new ArrayList(iArr3.length);
        for (int i2 = 0; i2 < iArr3.length; i2++) {
            arrayList2.add(new HistoryCluster.a(iArr3[i2], iArr4[i2]));
        }
        ArrayList arrayList3 = new ArrayList(jArr.length);
        for (int i3 = 0; i3 < jArr.length; i3++) {
            arrayList3.add(new ClusterVisit.a(jArr[i3], gurlArr[i3]));
        }
        return new ClusterVisit(gurl, str2, str, arrayList, arrayList2, gurl2, j, arrayList3);
    }

    @CalledByNative
    public static HistoryClustersBridge create(long j) {
        return new HistoryClustersBridge(j);
    }
}
