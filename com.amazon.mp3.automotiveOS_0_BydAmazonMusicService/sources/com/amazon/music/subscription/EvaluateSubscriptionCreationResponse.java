package com.amazon.music.subscription;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class EvaluateSubscriptionCreationResponse implements Comparable<EvaluateSubscriptionCreationResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.EvaluateSubscriptionCreationResponse");
    private String orderInitToken;
    private String purchaseHandleId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated EvaluateSubscriptionCreationResponse evaluateSubscriptionCreationResponse) {
        if (evaluateSubscriptionCreationResponse == null) {
            return -1;
        }
        if (evaluateSubscriptionCreationResponse == this) {
            return 0;
        }
        String orderInitToken = getOrderInitToken();
        String orderInitToken2 = evaluateSubscriptionCreationResponse.getOrderInitToken();
        if (orderInitToken != orderInitToken2) {
            if (orderInitToken == null) {
                return -1;
            }
            if (orderInitToken2 == null) {
                return 1;
            }
            int iCompareTo = orderInitToken.compareTo(orderInitToken2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String purchaseHandleId = getPurchaseHandleId();
        String purchaseHandleId2 = evaluateSubscriptionCreationResponse.getPurchaseHandleId();
        if (purchaseHandleId != purchaseHandleId2) {
            if (purchaseHandleId == null) {
                return -1;
            }
            if (purchaseHandleId2 == null) {
                return 1;
            }
            int iCompareTo2 = purchaseHandleId.compareTo(purchaseHandleId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EvaluateSubscriptionCreationResponse)) {
            return false;
        }
        EvaluateSubscriptionCreationResponse evaluateSubscriptionCreationResponse = (EvaluateSubscriptionCreationResponse) obj;
        return internalEqualityCheck(getOrderInitToken(), evaluateSubscriptionCreationResponse.getOrderInitToken()) && internalEqualityCheck(getPurchaseHandleId(), evaluateSubscriptionCreationResponse.getPurchaseHandleId());
    }

    public String getOrderInitToken() {
        return this.orderInitToken;
    }

    public String getPurchaseHandleId() {
        return this.purchaseHandleId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getOrderInitToken(), getPurchaseHandleId());
    }

    public void setOrderInitToken(String str) {
        this.orderInitToken = str;
    }

    public void setPurchaseHandleId(String str) {
        this.purchaseHandleId = str;
    }
}
