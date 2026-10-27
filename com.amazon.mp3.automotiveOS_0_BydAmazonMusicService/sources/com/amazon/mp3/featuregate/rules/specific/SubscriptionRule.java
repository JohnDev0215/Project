package com.amazon.mp3.featuregate.rules.specific;

import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.subscription.UserSubscription;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubscriptionRule.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B'\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0004H\u0002J\b\u0010\f\u001a\u00020\u0006H\u0016R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/featuregate/rules/specific/SubscriptionRule;", "Lcom/amazon/music/platform/featuregate/Rule;", "subscription", "", "Lcom/amazon/mp3/featuregate/rules/specific/SubscriptionType;", "condition", "", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "(Ljava/util/List;ZLcom/amazon/music/subscription/UserSubscription;)V", "allowedSubscription", "checkSubscription", "isSatisfied", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SubscriptionRule implements Rule {
    private final List<SubscriptionType> allowedSubscription;
    private final boolean condition;
    private final UserSubscription userSubscription;

    /* JADX INFO: compiled from: SubscriptionRule.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SubscriptionType.values().length];
            iArr[SubscriptionType.PRIME_ONLY.ordinal()] = 1;
            iArr[SubscriptionType.PRIME.ordinal()] = 2;
            iArr[SubscriptionType.AMU_ALL_DEVICES.ordinal()] = 3;
            iArr[SubscriptionType.AMU.ordinal()] = 4;
            iArr[SubscriptionType.AMU_SDP.ordinal()] = 5;
            iArr[SubscriptionType.KATANA.ordinal()] = 6;
            iArr[SubscriptionType.AMU_ALL_DEVICES_NO_PRIME.ordinal()] = 7;
            iArr[SubscriptionType.NIGHTWING_ONLY.ordinal()] = 8;
            iArr[SubscriptionType.INDIA_FULL_CATALOG_ACCESS.ordinal()] = 9;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SubscriptionRule(List<? extends SubscriptionType> subscription, boolean z, UserSubscription userSubscription) {
        Intrinsics.checkNotNullParameter(subscription, "subscription");
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        this.userSubscription = userSubscription;
        this.allowedSubscription = subscription;
        this.condition = z;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ SubscriptionRule(List list, boolean z, UserSubscription userSubscription, int i, DefaultConstructorMarker defaultConstructorMarker) {
        z = (i & 2) != 0 ? true : z;
        if ((i & 4) != 0) {
            userSubscription = UserSubscriptionUtil.getUserSubscription();
            Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        }
        this(list, z, userSubscription);
    }

    @Override // com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied */
    public boolean getValue() {
        boolean z;
        while (true) {
            for (SubscriptionType subscriptionType : this.allowedSubscription) {
                z = z || checkSubscription(subscriptionType);
            }
            return z;
        }
    }

    private final boolean checkSubscription(SubscriptionType subscription) {
        switch (WhenMappings.$EnumSwitchMapping$0[subscription.ordinal()]) {
            case 1:
                return this.userSubscription.isPrimeOnly() == this.condition;
            case 2:
                return this.userSubscription.isPrime() == this.condition;
            case 3:
                return this.userSubscription.isHawkfireAllDevices() == this.condition;
            case 4:
                return (this.userSubscription.isHawkfireAllDevices() || this.userSubscription.isHawkfireHome()) == this.condition;
            case 5:
                return this.userSubscription.isHawkfireHome() == this.condition;
            case 6:
                return this.userSubscription.isKatana() == this.condition;
            case 7:
                return (this.userSubscription.isHawkfireAllDevices() && !this.userSubscription.isPrime()) == this.condition;
            case 8:
                return this.userSubscription.isNightwingOnly() == this.condition;
            case 9:
                return (Intrinsics.areEqual(AccountDetailUtil.getMusicTerritoryOfResidence(), MusicTerritory.INDIA.value) && this.userSubscription.isPrime()) == this.condition;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
