package com.amazon.mp3.util.extensions;

import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentAccessType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u001a\f\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0003\u001a\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¨\u0006\u0007"}, d2 = {"toCatalogStatusTiers", "Lcom/amazon/mp3/prime/browse/metadata/CatalogStatusTiers;", "", "Lcom/amazon/music/ContentAccessType;", "toSubscriptionType", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "toSubscriptionTypes", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ContentAccessTypeKt {

    /* JADX INFO: compiled from: ContentAccessType.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContentAccessType.values().length];
            iArr[ContentAccessType.NIGHTWING.ordinal()] = 1;
            iArr[ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE.ordinal()] = 2;
            iArr[ContentAccessType.PRIME.ordinal()] = 3;
            iArr[ContentAccessType.SONIC_RUSH.ordinal()] = 4;
            iArr[ContentAccessType.HAWKFIRE.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final SubscriptionType toSubscriptionType(ContentAccessType contentAccessType) {
        Intrinsics.checkNotNullParameter(contentAccessType, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[contentAccessType.ordinal()];
        if (i == 1 || i == 2) {
            return SubscriptionType.FREE;
        }
        if (i == 3) {
            return SubscriptionType.PRIME;
        }
        if (i == 4) {
            return SubscriptionType.SONIC_RUSH;
        }
        if (i != 5) {
            return null;
        }
        return SubscriptionType.MUSIC_UNLIMITED;
    }

    public static final CatalogStatusTiers toCatalogStatusTiers(Set<? extends ContentAccessType> set) {
        return new CatalogStatusTiers(set != null && set.contains(ContentAccessType.PRIME), set != null && set.contains(ContentAccessType.HAWKFIRE), set != null && set.contains(ContentAccessType.NIGHTWING), set != null && set.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE), set != null && set.contains(ContentAccessType.SONIC_RUSH), set != null && set.contains(ContentAccessType.SONIC_RUSH_ONDEMAND_PLAYABLE), false, 64, null);
    }

    public static final Set<SubscriptionType> toSubscriptionTypes(Set<? extends ContentAccessType> set) {
        Set<SubscriptionType> set2;
        if (set == null) {
            set2 = null;
        } else {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                SubscriptionType subscriptionType = toSubscriptionType((ContentAccessType) it.next());
                if (subscriptionType != null) {
                    arrayList.add(subscriptionType);
                }
            }
            set2 = CollectionsKt___CollectionsKt.toSet(arrayList);
        }
        return set2 == null ? SetsKt__SetsKt.emptySet() : set2;
    }
}
