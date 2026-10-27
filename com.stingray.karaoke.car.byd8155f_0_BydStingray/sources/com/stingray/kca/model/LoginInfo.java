package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJp\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/stingray/kca/model/LoginInfo;", "", "", "token", "", "durationMs", "ipAddress", "ipCountry", "ipRegion", "ipCity", "refreshToken", "Lcom/stingray/kca/model/AccountInfo;", "accountInfo", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/stingray/kca/model/AccountInfo;)V", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/stingray/kca/model/AccountInfo;)Lcom/stingray/kca/model/LoginInfo;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class LoginInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13163a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f13164b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f13165e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f13166f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f13167g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AccountInfo f13168h;

    public LoginInfo() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    public final LoginInfo copy(@o(name = "token") String token, @o(name = "durationMs") Integer durationMs, @o(name = "ipAddress") String ipAddress, @o(name = "ipCountry") String ipCountry, @o(name = "ipRegion") String ipRegion, @o(name = "ipCity") String ipCity, @o(name = "refreshToken") String refreshToken, @o(name = "accountInfo") AccountInfo accountInfo) {
        return new LoginInfo(token, durationMs, ipAddress, ipCountry, ipRegion, ipCity, refreshToken, accountInfo);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoginInfo)) {
            return false;
        }
        LoginInfo loginInfo = (LoginInfo) obj;
        return m.a(this.f13163a, loginInfo.f13163a) && m.a(this.f13164b, loginInfo.f13164b) && m.a(this.c, loginInfo.c) && m.a(this.d, loginInfo.d) && m.a(this.f13165e, loginInfo.f13165e) && m.a(this.f13166f, loginInfo.f13166f) && m.a(this.f13167g, loginInfo.f13167g) && m.a(this.f13168h, loginInfo.f13168h);
    }

    public final int hashCode() {
        String str = this.f13163a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.f13164b;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.c;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.d;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.f13165e;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.f13166f;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.f13167g;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        AccountInfo accountInfo = this.f13168h;
        return iHashCode7 + (accountInfo != null ? accountInfo.hashCode() : 0);
    }

    public final String toString() {
        return "LoginInfo(token=" + this.f13163a + ", durationMs=" + this.f13164b + ", ipAddress=" + this.c + ", ipCountry=" + this.d + ", ipRegion=" + this.f13165e + ", ipCity=" + this.f13166f + ", refreshToken=" + this.f13167g + ", accountInfo=" + this.f13168h + ")";
    }

    public LoginInfo(@o(name = "token") String str, @o(name = "durationMs") Integer num, @o(name = "ipAddress") String str2, @o(name = "ipCountry") String str3, @o(name = "ipRegion") String str4, @o(name = "ipCity") String str5, @o(name = "refreshToken") String str6, @o(name = "accountInfo") AccountInfo accountInfo) {
        this.f13163a = str;
        this.f13164b = num;
        this.c = str2;
        this.d = str3;
        this.f13165e = str4;
        this.f13166f = str5;
        this.f13167g = str6;
        this.f13168h = accountInfo;
    }

    public /* synthetic */ LoginInfo(String str, Integer num, String str2, String str3, String str4, String str5, String str6, AccountInfo accountInfo, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : num, (i7 & 4) != 0 ? null : str2, (i7 & 8) != 0 ? null : str3, (i7 & 16) != 0 ? null : str4, (i7 & 32) != 0 ? null : str5, (i7 & 64) != 0 ? null : str6, (i7 & 128) != 0 ? null : accountInfo);
    }
}
