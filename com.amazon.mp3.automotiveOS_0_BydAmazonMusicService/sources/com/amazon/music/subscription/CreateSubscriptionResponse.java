package com.amazon.music.subscription;

import com.amazon.stratus.Subscription;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class CreateSubscriptionResponse implements Comparable<CreateSubscriptionResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.CreateSubscriptionResponse");
    private GeocheckInfo geocheckInfo;
    private Subscription subscription;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CreateSubscriptionResponse createSubscriptionResponse) {
        if (createSubscriptionResponse == null) {
            return -1;
        }
        if (createSubscriptionResponse == this) {
            return 0;
        }
        GeocheckInfo geocheckInfo = getGeocheckInfo();
        GeocheckInfo geocheckInfo2 = createSubscriptionResponse.getGeocheckInfo();
        if (geocheckInfo != geocheckInfo2) {
            if (geocheckInfo == null) {
                return -1;
            }
            if (geocheckInfo2 == null) {
                return 1;
            }
            int iCompareTo = geocheckInfo.compareTo(geocheckInfo2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Subscription subscription = getSubscription();
        Subscription subscription2 = createSubscriptionResponse.getSubscription();
        if (subscription != subscription2) {
            if (subscription == null) {
                return -1;
            }
            if (subscription2 == null) {
                return 1;
            }
            int iCompareTo2 = subscription.compareTo(subscription2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CreateSubscriptionResponse)) {
            return false;
        }
        CreateSubscriptionResponse createSubscriptionResponse = (CreateSubscriptionResponse) obj;
        return internalEqualityCheck(getGeocheckInfo(), createSubscriptionResponse.getGeocheckInfo()) && internalEqualityCheck(getSubscription(), createSubscriptionResponse.getSubscription());
    }

    public GeocheckInfo getGeocheckInfo() {
        return this.geocheckInfo;
    }

    public Subscription getSubscription() {
        return this.subscription;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getGeocheckInfo(), getSubscription());
    }

    public void setGeocheckInfo(GeocheckInfo geocheckInfo) {
        this.geocheckInfo = geocheckInfo;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
