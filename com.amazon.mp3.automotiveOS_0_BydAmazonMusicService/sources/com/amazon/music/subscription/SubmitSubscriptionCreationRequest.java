package com.amazon.music.subscription;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.stratus.PaymentInfo;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class SubmitSubscriptionCreationRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.SubmitSubscriptionCreationRequest");
    private String empBillingDetails;
    private String musicTerritory;
    private String orderInitToken;
    private PaymentInfo paymentInfo;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof SubmitSubscriptionCreationRequest)) {
            return 1;
        }
        SubmitSubscriptionCreationRequest submitSubscriptionCreationRequest = (SubmitSubscriptionCreationRequest) cirrusBaseRequestV2;
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = submitSubscriptionCreationRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String orderInitToken = getOrderInitToken();
        String orderInitToken2 = submitSubscriptionCreationRequest.getOrderInitToken();
        if (orderInitToken != orderInitToken2) {
            if (orderInitToken == null) {
                return -1;
            }
            if (orderInitToken2 == null) {
                return 1;
            }
            int iCompareTo2 = orderInitToken.compareTo(orderInitToken2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String empBillingDetails = getEmpBillingDetails();
        String empBillingDetails2 = submitSubscriptionCreationRequest.getEmpBillingDetails();
        if (empBillingDetails != empBillingDetails2) {
            if (empBillingDetails == null) {
                return -1;
            }
            if (empBillingDetails2 == null) {
                return 1;
            }
            int iCompareTo3 = empBillingDetails.compareTo(empBillingDetails2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        PaymentInfo paymentInfo = getPaymentInfo();
        PaymentInfo paymentInfo2 = submitSubscriptionCreationRequest.getPaymentInfo();
        if (paymentInfo != paymentInfo2) {
            if (paymentInfo == null) {
                return -1;
            }
            if (paymentInfo2 == null) {
                return 1;
            }
            int iCompareTo4 = paymentInfo.compareTo(paymentInfo2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof SubmitSubscriptionCreationRequest)) {
            return false;
        }
        SubmitSubscriptionCreationRequest submitSubscriptionCreationRequest = (SubmitSubscriptionCreationRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getMusicTerritory(), submitSubscriptionCreationRequest.getMusicTerritory()) && internalEqualityCheck(getOrderInitToken(), submitSubscriptionCreationRequest.getOrderInitToken()) && internalEqualityCheck(getEmpBillingDetails(), submitSubscriptionCreationRequest.getEmpBillingDetails()) && internalEqualityCheck(getPaymentInfo(), submitSubscriptionCreationRequest.getPaymentInfo());
    }

    public String getEmpBillingDetails() {
        return this.empBillingDetails;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getOrderInitToken() {
        return this.orderInitToken;
    }

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMusicTerritory(), getOrderInitToken(), getEmpBillingDetails(), getPaymentInfo());
    }

    public void setEmpBillingDetails(String str) {
        this.empBillingDetails = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setOrderInitToken(String str) {
        this.orderInitToken = str;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
