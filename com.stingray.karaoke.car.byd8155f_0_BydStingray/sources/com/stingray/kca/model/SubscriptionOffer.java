package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ@\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/kca/model/SubscriptionOffer;", "", "", "productId", "name", "description", "promo", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/stingray/kca/model/SubscriptionOffer;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class SubscriptionOffer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13218a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13219b;
    public final String c;
    public final String d;

    public SubscriptionOffer() {
        this(null, null, null, null, 15, null);
    }

    public final SubscriptionOffer copy(@o(name = "productId") String productId, @o(name = "name") String name, @o(name = "description") String description, @o(name = "promo") String promo) {
        return new SubscriptionOffer(productId, name, description, promo);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubscriptionOffer)) {
            return false;
        }
        SubscriptionOffer subscriptionOffer = (SubscriptionOffer) obj;
        return m.a(this.f13218a, subscriptionOffer.f13218a) && m.a(this.f13219b, subscriptionOffer.f13219b) && m.a(this.c, subscriptionOffer.c) && m.a(this.d, subscriptionOffer.d);
    }

    public final int hashCode() {
        String str = this.f13218a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f13219b;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.c;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.d;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SubscriptionOffer(productId=");
        sb.append(this.f13218a);
        sb.append(", name=");
        sb.append(this.f13219b);
        sb.append(", description=");
        sb.append(this.c);
        sb.append(", promo=");
        return a.l(sb, this.d, ")");
    }

    public SubscriptionOffer(@o(name = "productId") String str, @o(name = "name") String str2, @o(name = "description") String str3, @o(name = "promo") String str4) {
        this.f13218a = str;
        this.f13219b = str2;
        this.c = str3;
        this.d = str4;
    }

    public /* synthetic */ SubscriptionOffer(String str, String str2, String str3, String str4, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : str2, (i7 & 4) != 0 ? null : str3, (i7 & 8) != 0 ? null : str4);
    }
}
