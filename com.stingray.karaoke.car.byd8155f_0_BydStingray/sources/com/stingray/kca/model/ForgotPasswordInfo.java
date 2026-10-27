package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J(\u0010\u0007\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/stingray/kca/model/ForgotPasswordInfo;", "", "", "emailExists", "captchaRequired", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "copy", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/stingray/kca/model/ForgotPasswordInfo;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class ForgotPasswordInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Boolean f13095a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Boolean f13096b;

    /* JADX WARN: Multi-variable type inference failed */
    public ForgotPasswordInfo() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public final ForgotPasswordInfo copy(@o(name = "emailExists") Boolean emailExists, @o(name = "captchaRequired") Boolean captchaRequired) {
        return new ForgotPasswordInfo(emailExists, captchaRequired);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ForgotPasswordInfo)) {
            return false;
        }
        ForgotPasswordInfo forgotPasswordInfo = (ForgotPasswordInfo) obj;
        return m.a(this.f13095a, forgotPasswordInfo.f13095a) && m.a(this.f13096b, forgotPasswordInfo.f13096b);
    }

    public final int hashCode() {
        Boolean bool = this.f13095a;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.f13096b;
        return iHashCode + (bool2 != null ? bool2.hashCode() : 0);
    }

    public final String toString() {
        return "ForgotPasswordInfo(emailExists=" + this.f13095a + ", captchaRequired=" + this.f13096b + ")";
    }

    public ForgotPasswordInfo(@o(name = "emailExists") Boolean bool, @o(name = "captchaRequired") Boolean bool2) {
        this.f13095a = bool;
        this.f13096b = bool2;
    }

    public /* synthetic */ ForgotPasswordInfo(Boolean bool, Boolean bool2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : bool, (i7 & 2) != 0 ? null : bool2);
    }
}
