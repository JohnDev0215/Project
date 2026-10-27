package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J(\u0010\b\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/stingray/kca/model/IntroPriceInfoParameter;", "", "", "introPrice", "", "introPriceCycles", "<init>", "(Ljava/lang/Float;Ljava/lang/Integer;)V", "copy", "(Ljava/lang/Float;Ljava/lang/Integer;)Lcom/stingray/kca/model/IntroPriceInfoParameter;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class IntroPriceInfoParameter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Float f13103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f13104b;

    /* JADX WARN: Multi-variable type inference failed */
    public IntroPriceInfoParameter() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public final IntroPriceInfoParameter copy(@o(name = "introPrice") Float introPrice, @o(name = "introPriceCycles") Integer introPriceCycles) {
        return new IntroPriceInfoParameter(introPrice, introPriceCycles);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntroPriceInfoParameter)) {
            return false;
        }
        IntroPriceInfoParameter introPriceInfoParameter = (IntroPriceInfoParameter) obj;
        return m.a(this.f13103a, introPriceInfoParameter.f13103a) && m.a(this.f13104b, introPriceInfoParameter.f13104b);
    }

    public final int hashCode() {
        Float f10 = this.f13103a;
        int iHashCode = (f10 == null ? 0 : f10.hashCode()) * 31;
        Integer num = this.f13104b;
        return iHashCode + (num != null ? num.hashCode() : 0);
    }

    public final String toString() {
        return "IntroPriceInfoParameter(introPrice=" + this.f13103a + ", introPriceCycles=" + this.f13104b + ")";
    }

    public IntroPriceInfoParameter(@o(name = "introPrice") Float f10, @o(name = "introPriceCycles") Integer num) {
        this.f13103a = f10;
        this.f13104b = num;
    }

    public /* synthetic */ IntroPriceInfoParameter(Float f10, Integer num, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : f10, (i7 & 2) != 0 ? null : num);
    }
}
