package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ4\u0010\n\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/stingray/kca/model/SubscriptionStoreInfo;", "", "", "purchaseData", "Lcom/stingray/kca/model/PaymentInfoParameter;", "paymentInfo", "Lcom/stingray/kca/model/AppsflyerParameter;", "appsflyerParameter", "<init>", "(Ljava/lang/String;Lcom/stingray/kca/model/PaymentInfoParameter;Lcom/stingray/kca/model/AppsflyerParameter;)V", "copy", "(Ljava/lang/String;Lcom/stingray/kca/model/PaymentInfoParameter;Lcom/stingray/kca/model/AppsflyerParameter;)Lcom/stingray/kca/model/SubscriptionStoreInfo;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class SubscriptionStoreInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13222a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final PaymentInfoParameter f13223b;
    public final AppsflyerParameter c;

    public SubscriptionStoreInfo() {
        this(null, null, null, 7, null);
    }

    public final SubscriptionStoreInfo copy(@o(name = "purchaseData") String purchaseData, @o(name = "paymentInfo") PaymentInfoParameter paymentInfo, @o(name = "appsflyerParameter") AppsflyerParameter appsflyerParameter) {
        return new SubscriptionStoreInfo(purchaseData, paymentInfo, appsflyerParameter);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubscriptionStoreInfo)) {
            return false;
        }
        SubscriptionStoreInfo subscriptionStoreInfo = (SubscriptionStoreInfo) obj;
        return m.a(this.f13222a, subscriptionStoreInfo.f13222a) && m.a(this.f13223b, subscriptionStoreInfo.f13223b) && m.a(this.c, subscriptionStoreInfo.c);
    }

    public final int hashCode() {
        String str = this.f13222a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        PaymentInfoParameter paymentInfoParameter = this.f13223b;
        int iHashCode2 = (iHashCode + (paymentInfoParameter == null ? 0 : paymentInfoParameter.hashCode())) * 31;
        AppsflyerParameter appsflyerParameter = this.c;
        return iHashCode2 + (appsflyerParameter != null ? appsflyerParameter.hashCode() : 0);
    }

    public final String toString() {
        return "SubscriptionStoreInfo(purchaseData=" + this.f13222a + ", paymentInfo=" + this.f13223b + ", appsflyerParameter=" + this.c + ")";
    }

    public SubscriptionStoreInfo(@o(name = "purchaseData") String str, @o(name = "paymentInfo") PaymentInfoParameter paymentInfoParameter, @o(name = "appsflyerParameter") AppsflyerParameter appsflyerParameter) {
        this.f13222a = str;
        this.f13223b = paymentInfoParameter;
        this.c = appsflyerParameter;
    }

    public /* synthetic */ SubscriptionStoreInfo(String str, PaymentInfoParameter paymentInfoParameter, AppsflyerParameter appsflyerParameter, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : paymentInfoParameter, (i7 & 4) != 0 ? null : appsflyerParameter);
    }
}
