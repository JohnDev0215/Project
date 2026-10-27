package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ4\u0010\n\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/stingray/kca/model/PaymentInfoParameter;", "", "", "currency", "Lcom/stingray/kca/model/IntroPriceInfoParameter;", "introPriceInfo", "", "price", "<init>", "(Ljava/lang/String;Lcom/stingray/kca/model/IntroPriceInfoParameter;Ljava/lang/Float;)V", "copy", "(Ljava/lang/String;Lcom/stingray/kca/model/IntroPriceInfoParameter;Ljava/lang/Float;)Lcom/stingray/kca/model/PaymentInfoParameter;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class PaymentInfoParameter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13183a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final IntroPriceInfoParameter f13184b;
    public final Float c;

    public PaymentInfoParameter() {
        this(null, null, null, 7, null);
    }

    public final PaymentInfoParameter copy(@o(name = "currency") String currency, @o(name = "introPriceInfo") IntroPriceInfoParameter introPriceInfo, @o(name = "price") Float price) {
        return new PaymentInfoParameter(currency, introPriceInfo, price);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PaymentInfoParameter)) {
            return false;
        }
        PaymentInfoParameter paymentInfoParameter = (PaymentInfoParameter) obj;
        return m.a(this.f13183a, paymentInfoParameter.f13183a) && m.a(this.f13184b, paymentInfoParameter.f13184b) && m.a(this.c, paymentInfoParameter.c);
    }

    public final int hashCode() {
        String str = this.f13183a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        IntroPriceInfoParameter introPriceInfoParameter = this.f13184b;
        int iHashCode2 = (iHashCode + (introPriceInfoParameter == null ? 0 : introPriceInfoParameter.hashCode())) * 31;
        Float f10 = this.c;
        return iHashCode2 + (f10 != null ? f10.hashCode() : 0);
    }

    public final String toString() {
        return "PaymentInfoParameter(currency=" + this.f13183a + ", introPriceInfo=" + this.f13184b + ", price=" + this.c + ")";
    }

    public PaymentInfoParameter(@o(name = "currency") String str, @o(name = "introPriceInfo") IntroPriceInfoParameter introPriceInfoParameter, @o(name = "price") Float f10) {
        this.f13183a = str;
        this.f13184b = introPriceInfoParameter;
        this.c = f10;
    }

    public /* synthetic */ PaymentInfoParameter(String str, IntroPriceInfoParameter introPriceInfoParameter, Float f10, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : introPriceInfoParameter, (i7 & 4) != 0 ? null : f10);
    }
}
