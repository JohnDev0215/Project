package com.amazon.music.platform.metrics.event;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FlexWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0010\t\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001:\u00012B\u0089\u0001\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010%\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010'\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b0\u00101J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0013\u0010\rR\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\rR\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0018\u001a\u0004\b\u001c\u0010\u001aR\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0018\u001a\u0004\b\u001e\u0010\u001aR\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u0018\u001a\u0004\b \u0010\u001aR\u0019\u0010!\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b!\u0010\u000b\u001a\u0004\b\"\u0010\rR\u0019\u0010#\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u0019\u0010%\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b%\u0010\u000b\u001a\u0004\b&\u0010\rR\u0019\u0010'\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b'\u0010\u000b\u001a\u0004\b(\u0010\rR\u001a\u0010)\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b)\u0010\u000b\u001a\u0004\b*\u0010\rR\u001a\u0010,\u001a\u00020+8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/¨\u00063"}, d2 = {"Lcom/amazon/music/platform/metrics/event/FlexWrapper;", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "", "toString", "", "hashCode", "", "other", "", "equals", "flexEventName", "Ljava/lang/String;", "getFlexEventName", "()Ljava/lang/String;", "str1", "getStr1", "str2", "getStr2", "str3", "getStr3", "str4", "getStr4", "", "num1", "Ljava/lang/Float;", "getNum1", "()Ljava/lang/Float;", "num2", "getNum2", "num3", "getNum3", "num4", "getNum4", "hcStr1", "getHcStr1", "hcStr2", "getHcStr2", "hcStr3", "getHcStr3", "hcStr4", "getHcStr4", "metricsEventName", "getMetricsEventName", "", "timeStamp", "J", "getTimeStamp", "()J", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Builder", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class FlexWrapper implements MetricsWrapper {
    private final String flexEventName;
    private final String hcStr1;
    private final String hcStr2;
    private final String hcStr3;
    private final String hcStr4;
    private final Float num1;
    private final Float num2;
    private final Float num3;
    private final Float num4;
    private final String str1;
    private final String str2;
    private final String str3;
    private final String str4;
    private final String metricsEventName = "flexEvent";
    private final long timeStamp = PlatformProviderUtilKt.getCurrentEpochTimeMillis();

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlexWrapper)) {
            return false;
        }
        FlexWrapper flexWrapper = (FlexWrapper) other;
        return Intrinsics.areEqual(this.flexEventName, flexWrapper.flexEventName) && Intrinsics.areEqual(this.str1, flexWrapper.str1) && Intrinsics.areEqual(this.str2, flexWrapper.str2) && Intrinsics.areEqual(this.str3, flexWrapper.str3) && Intrinsics.areEqual(this.str4, flexWrapper.str4) && Intrinsics.areEqual((Object) this.num1, (Object) flexWrapper.num1) && Intrinsics.areEqual((Object) this.num2, (Object) flexWrapper.num2) && Intrinsics.areEqual((Object) this.num3, (Object) flexWrapper.num3) && Intrinsics.areEqual((Object) this.num4, (Object) flexWrapper.num4) && Intrinsics.areEqual(this.hcStr1, flexWrapper.hcStr1) && Intrinsics.areEqual(this.hcStr2, flexWrapper.hcStr2) && Intrinsics.areEqual(this.hcStr3, flexWrapper.hcStr3) && Intrinsics.areEqual(this.hcStr4, flexWrapper.hcStr4);
    }

    public int hashCode() {
        String str = this.flexEventName;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.str1;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.str2;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.str3;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.str4;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Float f = this.num1;
        int iHashCode6 = (iHashCode5 + (f == null ? 0 : f.hashCode())) * 31;
        Float f2 = this.num2;
        int iHashCode7 = (iHashCode6 + (f2 == null ? 0 : f2.hashCode())) * 31;
        Float f3 = this.num3;
        int iHashCode8 = (iHashCode7 + (f3 == null ? 0 : f3.hashCode())) * 31;
        Float f4 = this.num4;
        int iHashCode9 = (iHashCode8 + (f4 == null ? 0 : f4.hashCode())) * 31;
        String str6 = this.hcStr1;
        int iHashCode10 = (iHashCode9 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.hcStr2;
        int iHashCode11 = (iHashCode10 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.hcStr3;
        int iHashCode12 = (iHashCode11 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.hcStr4;
        return iHashCode12 + (str9 != null ? str9.hashCode() : 0);
    }

    public String toString() {
        return "FlexWrapper(flexEventName=" + this.flexEventName + ", str1=" + this.str1 + ", str2=" + this.str2 + ", str3=" + this.str3 + ", str4=" + this.str4 + ", num1=" + this.num1 + ", num2=" + this.num2 + ", num3=" + this.num3 + ", num4=" + this.num4 + ", hcStr1=" + this.hcStr1 + ", hcStr2=" + this.hcStr2 + ", hcStr3=" + this.hcStr3 + ", hcStr4=" + this.hcStr4 + ')';
    }

    public FlexWrapper(String str, String str2, String str3, String str4, String str5, Float f, Float f2, Float f3, Float f4, String str6, String str7, String str8, String str9) {
        this.flexEventName = str;
        this.str1 = str2;
        this.str2 = str3;
        this.str3 = str4;
        this.str4 = str5;
        this.num1 = f;
        this.num2 = f2;
        this.num3 = f3;
        this.num4 = f4;
        this.hcStr1 = str6;
        this.hcStr2 = str7;
        this.hcStr3 = str8;
        this.hcStr4 = str9;
        PlatformProviderUtilKt.freeze(this);
    }

    public final String getFlexEventName() {
        return this.flexEventName;
    }

    public final String getStr1() {
        return this.str1;
    }

    public final String getStr2() {
        return this.str2;
    }

    public final String getStr3() {
        return this.str3;
    }

    public final String getStr4() {
        return this.str4;
    }

    public final Float getNum1() {
        return this.num1;
    }

    public final Float getNum2() {
        return this.num2;
    }

    public final Float getNum3() {
        return this.num3;
    }

    public final Float getNum4() {
        return this.num4;
    }

    public final String getHcStr1() {
        return this.hcStr1;
    }

    public final String getHcStr2() {
        return this.hcStr2;
    }

    public final String getHcStr3() {
        return this.hcStr3;
    }

    public final String getHcStr4() {
        return this.hcStr4;
    }

    /* JADX INFO: compiled from: FlexWrapper.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0016\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0017\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0018\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0019\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0015\u0010\u001a\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001c\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001d\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001e\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001f\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004J\u0010\u0010 \u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004J\u0010\u0010!\u001a\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\"\u001a\u00020\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\f\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\r\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u000e\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/music/platform/metrics/event/FlexWrapper$Builder;", "", "()V", "flexEventName", "", "hcStr1", "hcStr2", "hcStr3", "hcStr4", "num1", "", "Ljava/lang/Float;", "num2", "num3", "num4", "str1", "str2", "str3", "str4", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/platform/metrics/event/FlexWrapper;", "withHcStr1", "withHcStr2", "withHcStr3", "withHcStr4", "withName", "withNum1", "(Ljava/lang/Float;)Lcom/amazon/music/platform/metrics/event/FlexWrapper$Builder;", "withNum2", "withNum3", "withNum4", "withStr1", "withStr2", "withStr3", "withStr4", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Builder {
        private String flexEventName;
        private String hcStr1;
        private String hcStr2;
        private String hcStr3;
        private String hcStr4;
        private Float num1;
        private Float num2;
        private Float num3;
        private Float num4;
        private String str1;
        private String str2;
        private String str3;
        private String str4;

        public final FlexWrapper build() {
            return new FlexWrapper(this.flexEventName, this.str1, this.str2, this.str3, this.str4, this.num1, this.num2, this.num3, this.num4, this.hcStr1, this.hcStr2, this.hcStr3, this.hcStr4);
        }

        public final Builder withStr1(String str1) {
            this.str1 = str1;
            return this;
        }

        public final Builder withStr2(String str2) {
            this.str2 = str2;
            return this;
        }

        public final Builder withStr3(String str3) {
            this.str3 = str3;
            return this;
        }

        public final Builder withStr4(String str4) {
            this.str4 = str4;
            return this;
        }

        public final Builder withNum1(Float num1) {
            this.num1 = num1;
            return this;
        }

        public final Builder withNum2(Float num2) {
            this.num2 = num2;
            return this;
        }

        public final Builder withNum3(Float num3) {
            this.num3 = num3;
            return this;
        }

        public final Builder withNum4(Float num4) {
            this.num4 = num4;
            return this;
        }

        public final Builder withHcStr1(String hcStr1) {
            this.hcStr1 = hcStr1;
            return this;
        }

        public final Builder withHcStr2(String hcStr2) {
            this.hcStr2 = hcStr2;
            return this;
        }

        public final Builder withHcStr3(String hcStr3) {
            this.hcStr3 = hcStr3;
            return this;
        }

        public final Builder withHcStr4(String hcStr4) {
            this.hcStr4 = hcStr4;
            return this;
        }

        public final Builder withName(String flexEventName) {
            this.flexEventName = flexEventName;
            return this;
        }
    }
}
