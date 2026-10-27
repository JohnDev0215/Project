package com.amazon.avod.content.config;

import com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: ClusterModelParserUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0004\u001a\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0006\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/avod/content/config/ClusterModelParserUtils;", "", "", HeuristicsConfigStore.SETTINGS_ID_KEY, "parseClusterLabel", "clusterLabel", "parseClusterModelVersion", "", "", "DEFAULT_NETWORK_STATS", "Ljava/util/List;", "<init>", "()V", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0})
public final class ClusterModelParserUtils {
    private static final List<Double> DEFAULT_NETWORK_STATS;
    public static final ClusterModelParserUtils INSTANCE = new ClusterModelParserUtils();

    private ClusterModelParserUtils() {
    }

    static {
        Double dValueOf = Double.valueOf(-1.0d);
        DEFAULT_NETWORK_STATS = CollectionsKt__CollectionsKt.listOf((Object[]) new Double[]{dValueOf, dValueOf, dValueOf, dValueOf});
    }

    public final String parseClusterLabel(String settingsId) {
        if (settingsId == null) {
            return "Legacy";
        }
        List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) settingsId, new String[]{"--"}, false, 0, 6, (Object) null);
        return (StringsKt__StringsJVMKt.isBlank(settingsId) || listSplit$default.size() <= 1 || StringsKt__StringsJVMKt.isBlank((CharSequence) listSplit$default.get(1))) ? "Legacy" : (String) listSplit$default.get(1);
    }

    public final String parseClusterModelVersion(String clusterLabel) {
        if (clusterLabel == null) {
            return "v4";
        }
        List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) clusterLabel, new String[]{"-"}, false, 0, 6, (Object) null);
        return (Intrinsics.areEqual(clusterLabel, "Legacy") || StringsKt__StringsJVMKt.isBlank(clusterLabel) || listSplit$default.size() <= 1) ? "v4" : (String) listSplit$default.get(1);
    }
}
