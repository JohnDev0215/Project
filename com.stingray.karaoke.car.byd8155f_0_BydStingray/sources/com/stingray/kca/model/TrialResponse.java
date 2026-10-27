package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import java.time.OffsetDateTime;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0003\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\u000bJF\u0010\f\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0010\b\u0003\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/stingray/kca/model/TrialResponse;", "", "Ljava/time/OffsetDateTime;", "endTime", "", "maxItemCount", "resetTime", "", "", "trialItemList", "<init>", "(Ljava/time/OffsetDateTime;Ljava/lang/Integer;Ljava/time/OffsetDateTime;Ljava/util/List;)V", "copy", "(Ljava/time/OffsetDateTime;Ljava/lang/Integer;Ljava/time/OffsetDateTime;Ljava/util/List;)Lcom/stingray/kca/model/TrialResponse;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class TrialResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final OffsetDateTime f13227a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f13228b;
    public final OffsetDateTime c;
    public final List d;

    public TrialResponse() {
        this(null, null, null, null, 15, null);
    }

    public final TrialResponse copy(@o(name = "endTime") OffsetDateTime endTime, @o(name = "maxItemCount") Integer maxItemCount, @o(name = "resetTime") OffsetDateTime resetTime, @o(name = "trialItemList") List<String> trialItemList) {
        return new TrialResponse(endTime, maxItemCount, resetTime, trialItemList);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TrialResponse)) {
            return false;
        }
        TrialResponse trialResponse = (TrialResponse) obj;
        return m.a(this.f13227a, trialResponse.f13227a) && m.a(this.f13228b, trialResponse.f13228b) && m.a(this.c, trialResponse.c) && m.a(this.d, trialResponse.d);
    }

    public final int hashCode() {
        OffsetDateTime offsetDateTime = this.f13227a;
        int iHashCode = (offsetDateTime == null ? 0 : offsetDateTime.hashCode()) * 31;
        Integer num = this.f13228b;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        OffsetDateTime offsetDateTime2 = this.c;
        int iHashCode3 = (iHashCode2 + (offsetDateTime2 == null ? 0 : offsetDateTime2.hashCode())) * 31;
        List list = this.d;
        return iHashCode3 + (list != null ? list.hashCode() : 0);
    }

    public final String toString() {
        return "TrialResponse(endTime=" + this.f13227a + ", maxItemCount=" + this.f13228b + ", resetTime=" + this.c + ", trialItemList=" + this.d + ")";
    }

    public TrialResponse(@o(name = "endTime") OffsetDateTime offsetDateTime, @o(name = "maxItemCount") Integer num, @o(name = "resetTime") OffsetDateTime offsetDateTime2, @o(name = "trialItemList") List<String> list) {
        this.f13227a = offsetDateTime;
        this.f13228b = num;
        this.c = offsetDateTime2;
        this.d = list;
    }

    public /* synthetic */ TrialResponse(OffsetDateTime offsetDateTime, Integer num, OffsetDateTime offsetDateTime2, List list, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : offsetDateTime, (i7 & 2) != 0 ? null : num, (i7 & 4) != 0 ? null : offsetDateTime2, (i7 & 8) != 0 ? null : list);
    }
}
