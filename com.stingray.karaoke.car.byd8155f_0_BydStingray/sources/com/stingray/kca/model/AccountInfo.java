package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJL\u0010\u000b\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/stingray/kca/model/AccountInfo;", "", "", "suid", "loginId", "emailAddress", "userToken", "Lcom/stingray/kca/model/SubscriptionInfo;", "subscriptionInfo", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/stingray/kca/model/SubscriptionInfo;)V", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/stingray/kca/model/SubscriptionInfo;)Lcom/stingray/kca/model/AccountInfo;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class AccountInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13043a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13044b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SubscriptionInfo f13045e;

    public AccountInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public final AccountInfo copy(@o(name = "suid") String suid, @o(name = "loginId") String loginId, @o(name = "emailAddress") String emailAddress, @o(name = "userToken") String userToken, @o(name = "subscriptionInfo") SubscriptionInfo subscriptionInfo) {
        return new AccountInfo(suid, loginId, emailAddress, userToken, subscriptionInfo);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccountInfo)) {
            return false;
        }
        AccountInfo accountInfo = (AccountInfo) obj;
        return m.a(this.f13043a, accountInfo.f13043a) && m.a(this.f13044b, accountInfo.f13044b) && m.a(this.c, accountInfo.c) && m.a(this.d, accountInfo.d) && m.a(this.f13045e, accountInfo.f13045e);
    }

    public final int hashCode() {
        String str = this.f13043a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f13044b;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.c;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.d;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        SubscriptionInfo subscriptionInfo = this.f13045e;
        return iHashCode4 + (subscriptionInfo != null ? subscriptionInfo.hashCode() : 0);
    }

    public final String toString() {
        return "AccountInfo(suid=" + this.f13043a + ", loginId=" + this.f13044b + ", emailAddress=" + this.c + ", userToken=" + this.d + ", subscriptionInfo=" + this.f13045e + ")";
    }

    public AccountInfo(@o(name = "suid") String str, @o(name = "loginId") String str2, @o(name = "emailAddress") String str3, @o(name = "userToken") String str4, @o(name = "subscriptionInfo") SubscriptionInfo subscriptionInfo) {
        this.f13043a = str;
        this.f13044b = str2;
        this.c = str3;
        this.d = str4;
        this.f13045e = subscriptionInfo;
    }

    public /* synthetic */ AccountInfo(String str, String str2, String str3, String str4, SubscriptionInfo subscriptionInfo, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : str2, (i7 & 4) != 0 ? null : str3, (i7 & 8) != 0 ? null : str4, (i7 & 16) != 0 ? null : subscriptionInfo);
    }
}
