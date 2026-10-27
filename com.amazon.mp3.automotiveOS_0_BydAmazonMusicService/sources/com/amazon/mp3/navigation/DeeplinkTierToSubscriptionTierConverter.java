package com.amazon.mp3.navigation;

import com.amazon.mp3.account.SubscriptionTier;
import com.amazon.music.destination.DeeplinkTier;

/* JADX INFO: loaded from: classes3.dex */
public class DeeplinkTierToSubscriptionTierConverter {

    /* JADX INFO: renamed from: com.amazon.mp3.navigation.DeeplinkTierToSubscriptionTierConverter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$destination$DeeplinkTier;

        static {
            int[] iArr = new int[DeeplinkTier.values().length];
            $SwitchMap$com$amazon$music$destination$DeeplinkTier = iArr;
            try {
                iArr[DeeplinkTier.UNLIMITED_FAMILY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$destination$DeeplinkTier[DeeplinkTier.UNLIMITED_INDIVIDUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$destination$DeeplinkTier[DeeplinkTier.PRIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public SubscriptionTier convert(final DeeplinkTier tier) throws Exception {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$destination$DeeplinkTier[tier.ordinal()];
        if (i == 1) {
            return SubscriptionTier.UNLIMITED_FAMILY;
        }
        if (i == 2) {
            return SubscriptionTier.UNLIMITED_INDIVIDUAL;
        }
        if (i == 3) {
            return SubscriptionTier.PRIME;
        }
        throw new Exception("Deeplink Tier unrecognized, was:" + tier.toString());
    }
}
