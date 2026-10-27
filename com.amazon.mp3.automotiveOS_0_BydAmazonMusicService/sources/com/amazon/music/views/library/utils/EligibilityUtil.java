package com.amazon.music.views.library.utils;

import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.factory.ContentEligibilityContext;
import com.amazon.music.platform.data.eligibility.factory.ContentEligibilityFactory;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EligibilityUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JL\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000f\u001a\u00020\bJ\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f*\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\fJ\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\f*\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/views/library/utils/EligibilityUtil;", "", "()V", "getEligibility", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "entityItemIdentifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "isOwned", "", "isDownloaded", "hasPlaymodeEligibility", "contentTiers", "", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "onDemandTiers", "isAvailable", "getOnDemandTiers", "Lcom/amazon/music/ContentAccessType;", "toSubscriptionType", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EligibilityUtil {
    public static final EligibilityUtil INSTANCE = new EligibilityUtil();

    /* JADX INFO: compiled from: EligibilityUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContentAccessType.values().length];
            iArr[ContentAccessType.HAWKFIRE.ordinal()] = 1;
            iArr[ContentAccessType.SONIC_RUSH.ordinal()] = 2;
            iArr[ContentAccessType.PRIME.ordinal()] = 3;
            iArr[ContentAccessType.NIGHTWING.ordinal()] = 4;
            iArr[ContentAccessType.SONIC_RUSH_ONDEMAND_PLAYABLE.ordinal()] = 5;
            iArr[ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private EligibilityUtil() {
    }

    public final Set<SubscriptionType> toSubscriptionType(Set<? extends ContentAccessType> set) {
        HashSet hashSet = new HashSet();
        if (set != null) {
            Iterator<? extends ContentAccessType> it = set.iterator();
            while (it.hasNext()) {
                int i = WhenMappings.$EnumSwitchMapping$0[it.next().ordinal()];
                if (i == 1) {
                    hashSet.add(SubscriptionType.MUSIC_UNLIMITED);
                } else if (i == 2) {
                    hashSet.add(SubscriptionType.SONIC_RUSH);
                } else if (i == 3) {
                    hashSet.add(SubscriptionType.PRIME);
                } else if (i == 4) {
                    hashSet.add(SubscriptionType.FREE);
                }
            }
        }
        return hashSet;
    }

    public final Set<SubscriptionType> getOnDemandTiers(Set<? extends ContentAccessType> set) {
        HashSet hashSet = new HashSet();
        if (set != null) {
            Iterator<? extends ContentAccessType> it = set.iterator();
            while (it.hasNext()) {
                int i = WhenMappings.$EnumSwitchMapping$0[it.next().ordinal()];
                if (i == 5) {
                    hashSet.add(SubscriptionType.SONIC_RUSH);
                } else if (i == 6) {
                    hashSet.add(SubscriptionType.FREE);
                }
            }
        }
        return hashSet;
    }

    public final ContentEligibility getEligibility(EntityItemIdentifier entityItemIdentifier, boolean isOwned, boolean isDownloaded, boolean hasPlaymodeEligibility, Set<? extends SubscriptionType> contentTiers, Set<? extends SubscriptionType> onDemandTiers, boolean isAvailable) {
        Intrinsics.checkNotNullParameter(entityItemIdentifier, "entityItemIdentifier");
        Intrinsics.checkNotNullParameter(contentTiers, "contentTiers");
        Intrinsics.checkNotNullParameter(onDemandTiers, "onDemandTiers");
        SubscriptionType subscriptionType = SubscriptionType.FREE;
        boolean zContains = contentTiers.contains(subscriptionType);
        SubscriptionType subscriptionType2 = SubscriptionType.SONIC_RUSH;
        boolean z = false;
        return ContentEligibilityFactory.INSTANCE.createFrom(new ContentEligibilityContext(entityItemIdentifier, z, isOwned, zContains, contentTiers.contains(SubscriptionType.PRIME), contentTiers.contains(subscriptionType2), contentTiers.contains(SubscriptionType.MUSIC_UNLIMITED), onDemandTiers.contains(subscriptionType), onDemandTiers.contains(subscriptionType2), isDownloaded, isAvailable, false, 2050, null));
    }
}
