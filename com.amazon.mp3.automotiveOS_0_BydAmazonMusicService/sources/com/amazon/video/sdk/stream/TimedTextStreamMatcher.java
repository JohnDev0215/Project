package com.amazon.video.sdk.stream;

import com.amazon.avod.json.JacksonCache;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamMatcher.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001d\b\u0007\u0012\b\b\u0001\u0010\t\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001a\u0010\t\u001a\u00020\u00028\u0017X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0007¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "", "", "toString", "", "hashCode", "other", "", "equals", "language", "Ljava/lang/String;", "getLanguage", "()Ljava/lang/String;", "Lcom/amazon/video/sdk/stream/TimedTextSubtype;", "subType", "Lcom/amazon/video/sdk/stream/TimedTextSubtype;", "getSubType", "()Lcom/amazon/video/sdk/stream/TimedTextSubtype;", "<init>", "(Ljava/lang/String;Lcom/amazon/video/sdk/stream/TimedTextSubtype;)V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TimedTextStreamMatcher {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String language;
    private final TimedTextSubtype subType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimedTextStreamMatcher)) {
            return false;
        }
        TimedTextStreamMatcher timedTextStreamMatcher = (TimedTextStreamMatcher) other;
        return Intrinsics.areEqual(getLanguage(), timedTextStreamMatcher.getLanguage()) && this.subType == timedTextStreamMatcher.subType;
    }

    public int hashCode() {
        return (getLanguage().hashCode() * 31) + this.subType.hashCode();
    }

    public String toString() {
        return "TimedTextStreamMatcher(language=" + getLanguage() + ", subType=" + this.subType + ')';
    }

    @JsonCreator
    public TimedTextStreamMatcher(@JsonProperty("language") String language, @JsonProperty("subType") TimedTextSubtype subType) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(subType, "subType");
        this.language = language;
        this.subType = subType;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return this.language;
    }

    public /* synthetic */ TimedTextStreamMatcher(String str, TimedTextSubtype timedTextSubtype, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? TimedTextSubtype.Dialog : timedTextSubtype);
    }

    @JsonProperty("subType")
    public final TimedTextSubtype getSubType() {
        return this.subType;
    }

    /* JADX INFO: compiled from: StreamMatcher.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00072\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher$Companion;", "", "()V", "fromJSON", "", "Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "s", "", "toJSON", "matchers", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<TimedTextStreamMatcher> fromJSON(String s) {
            if (s == null) {
                return null;
            }
            try {
                return (List) JacksonCache.OBJECT_MAPPER.readValue(s, new TypeReference<List<? extends TimedTextStreamMatcher>>() { // from class: com.amazon.video.sdk.stream.TimedTextStreamMatcher$Companion$fromJSON$1
                });
            } catch (Exception e) {
                throw new IllegalArgumentException("s failed to deserialize", e);
            }
        }

        public final String toJSON(List<TimedTextStreamMatcher> matchers) {
            if (matchers == null) {
                return null;
            }
            try {
                return JacksonCache.OBJECT_MAPPER.writeValueAsString(matchers);
            } catch (Exception e) {
                throw new IllegalArgumentException("matchers failed to serialize", e);
            }
        }
    }
}
