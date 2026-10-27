package com.amazon.music.featuregating.configuration.dataclasses;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Marketplace.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0005"}, d2 = {"getMarketplaceName", "", "", "Lcom/amazon/music/featuregating/configuration/dataclasses/Marketplace;", "id", "MPFeatureGating_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class MarketplaceKt {
    public static final String getMarketplaceName(List<Marketplace> list, String id) {
        Object next;
        Intrinsics.checkNotNullParameter(id, "id");
        if (list == null) {
            return null;
        }
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((Marketplace) next).getId(), id)) {
                break;
            }
        }
        Marketplace marketplace = (Marketplace) next;
        if (marketplace != null) {
            return marketplace.getName();
        }
        return null;
    }
}
