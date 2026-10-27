package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ4\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/kca/model/SubscriptionInfo;", "", "", "remainingMinutes", "", "subscriptionType", "subscriptionPlan", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/stingray/kca/model/SubscriptionInfo;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class SubscriptionInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Integer f13214a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13215b;
    public final String c;

    public SubscriptionInfo() {
        this(null, null, null, 7, null);
    }

    public final SubscriptionInfo copy(@o(name = "remainingMinutes") Integer remainingMinutes, @o(name = "subscriptionType") String subscriptionType, @o(name = "subscriptionPlan") String subscriptionPlan) {
        return new SubscriptionInfo(remainingMinutes, subscriptionType, subscriptionPlan);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubscriptionInfo)) {
            return false;
        }
        SubscriptionInfo subscriptionInfo = (SubscriptionInfo) obj;
        return m.a(this.f13214a, subscriptionInfo.f13214a) && m.a(this.f13215b, subscriptionInfo.f13215b) && m.a(this.c, subscriptionInfo.c);
    }

    public final int hashCode() {
        Integer num = this.f13214a;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.f13215b;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.c;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SubscriptionInfo(remainingMinutes=");
        sb.append(this.f13214a);
        sb.append(", subscriptionType=");
        sb.append(this.f13215b);
        sb.append(", subscriptionPlan=");
        return a.l(sb, this.c, ")");
    }

    public SubscriptionInfo(@o(name = "remainingMinutes") Integer num, @o(name = "subscriptionType") String str, @o(name = "subscriptionPlan") String str2) {
        this.f13214a = num;
        this.f13215b = str;
        this.c = str2;
    }

    public /* synthetic */ SubscriptionInfo(Integer num, String str, String str2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : num, (i7 & 2) != 0 ? null : str, (i7 & 4) != 0 ? null : str2);
    }
}
