package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\b\u0010\tJ@\u0010\n\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/stingray/kca/model/ScoringDetailResponse;", "", "", "points", "", "accuracy", "streak", "scorePercentage", "<init>", "(Ljava/lang/Integer;Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/Float;)V", "copy", "(Ljava/lang/Integer;Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/Float;)Lcom/stingray/kca/model/ScoringDetailResponse;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class ScoringDetailResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Integer f13202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Float f13203b;
    public final Integer c;
    public final Float d;

    public ScoringDetailResponse() {
        this(null, null, null, null, 15, null);
    }

    public final ScoringDetailResponse copy(@o(name = "points") Integer points, @o(name = "accuracy") Float accuracy, @o(name = "streak") Integer streak, @o(name = "scorePercentage") Float scorePercentage) {
        return new ScoringDetailResponse(points, accuracy, streak, scorePercentage);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScoringDetailResponse)) {
            return false;
        }
        ScoringDetailResponse scoringDetailResponse = (ScoringDetailResponse) obj;
        return m.a(this.f13202a, scoringDetailResponse.f13202a) && m.a(this.f13203b, scoringDetailResponse.f13203b) && m.a(this.c, scoringDetailResponse.c) && m.a(this.d, scoringDetailResponse.d);
    }

    public final int hashCode() {
        Integer num = this.f13202a;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Float f10 = this.f13203b;
        int iHashCode2 = (iHashCode + (f10 == null ? 0 : f10.hashCode())) * 31;
        Integer num2 = this.c;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Float f11 = this.d;
        return iHashCode3 + (f11 != null ? f11.hashCode() : 0);
    }

    public final String toString() {
        return "ScoringDetailResponse(points=" + this.f13202a + ", accuracy=" + this.f13203b + ", streak=" + this.c + ", scorePercentage=" + this.d + ")";
    }

    public ScoringDetailResponse(@o(name = "points") Integer num, @o(name = "accuracy") Float f10, @o(name = "streak") Integer num2, @o(name = "scorePercentage") Float f11) {
        this.f13202a = num;
        this.f13203b = f10;
        this.c = num2;
        this.d = f11;
    }

    public /* synthetic */ ScoringDetailResponse(Integer num, Float f10, Integer num2, Float f11, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : num, (i7 & 2) != 0 ? null : f10, (i7 & 4) != 0 ? null : num2, (i7 & 8) != 0 ? null : f11);
    }
}
