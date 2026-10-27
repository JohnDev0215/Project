package com.amazon.video.sdk.stream;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimedTextStream.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0080\b\u0018\u00002\u00020\u0001B]\b\u0007\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0002\u0012\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\"¢\u0006\u0004\b+\u0010,J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\u00020\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001f\u0010\u000b\u001a\u0004\b \u0010\rR \u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010\"8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*¨\u0006-"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextStreamData;", "Lcom/amazon/video/sdk/stream/TimedTextStream;", "", "toString", "", "hashCode", "", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "language", "getLanguage", "Lcom/amazon/video/sdk/stream/TimedTextType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/video/sdk/stream/TimedTextType;", "getType", "()Lcom/amazon/video/sdk/stream/TimedTextType;", "Lcom/amazon/video/sdk/stream/TimedTextSubtype;", "subtype", "Lcom/amazon/video/sdk/stream/TimedTextSubtype;", "getSubtype", "()Lcom/amazon/video/sdk/stream/TimedTextSubtype;", "Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "matcher", "Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "getMatcher", "()Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", ContextMappingConstants.LABEL, "getLabel", "", "Lcom/amazon/video/sdk/stream/TimedTextVariant;", "variants", "Ljava/util/List;", "getVariants", "()Ljava/util/List;", "activeVariant", "Lcom/amazon/video/sdk/stream/TimedTextVariant;", "getActiveVariant", "()Lcom/amazon/video/sdk/stream/TimedTextVariant;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/video/sdk/stream/TimedTextType;Lcom/amazon/video/sdk/stream/TimedTextSubtype;Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;Ljava/lang/String;Ljava/util/List;Lcom/amazon/video/sdk/stream/TimedTextVariant;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TimedTextStreamData implements TimedTextStream {
    private final TimedTextVariant activeVariant;
    private final String id;
    private final String label;
    private final String language;
    private final TimedTextStreamMatcher matcher;
    private final TimedTextSubtype subtype;
    private final TimedTextType type;
    private final List<TimedTextVariant> variants;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TimedTextStreamData(String id, String language, TimedTextType timedTextType, TimedTextSubtype timedTextSubtype, TimedTextStreamMatcher matcher, String str) {
        this(id, language, timedTextType, timedTextSubtype, matcher, str, null, null, 192, null);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(matcher, "matcher");
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimedTextStreamData)) {
            return false;
        }
        TimedTextStreamData timedTextStreamData = (TimedTextStreamData) other;
        return Intrinsics.areEqual(getId(), timedTextStreamData.getId()) && Intrinsics.areEqual(getLanguage(), timedTextStreamData.getLanguage()) && getType() == timedTextStreamData.getType() && getSubtype() == timedTextStreamData.getSubtype() && Intrinsics.areEqual(getMatcher(), timedTextStreamData.getMatcher()) && Intrinsics.areEqual(getLabel(), timedTextStreamData.getLabel()) && Intrinsics.areEqual(getVariants(), timedTextStreamData.getVariants()) && Intrinsics.areEqual(getActiveVariant(), timedTextStreamData.getActiveVariant());
    }

    public int hashCode() {
        return (((((((((((((getId().hashCode() * 31) + getLanguage().hashCode()) * 31) + (getType() == null ? 0 : getType().hashCode())) * 31) + (getSubtype() == null ? 0 : getSubtype().hashCode())) * 31) + getMatcher().hashCode()) * 31) + (getLabel() == null ? 0 : getLabel().hashCode())) * 31) + getVariants().hashCode()) * 31) + (getActiveVariant() != null ? getActiveVariant().hashCode() : 0);
    }

    public String toString() {
        return "TimedTextStreamData(id=" + getId() + ", language=" + getLanguage() + ", type=" + getType() + ", subtype=" + getSubtype() + ", matcher=" + getMatcher() + ", label=" + ((Object) getLabel()) + ", variants=" + getVariants() + ", activeVariant=" + getActiveVariant() + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public TimedTextStreamData(String id, String language, TimedTextType timedTextType, TimedTextSubtype timedTextSubtype, TimedTextStreamMatcher matcher, String str, List<? extends TimedTextVariant> variants, TimedTextVariant timedTextVariant) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(matcher, "matcher");
        Intrinsics.checkNotNullParameter(variants, "variants");
        this.id = id;
        this.language = language;
        this.type = timedTextType;
        this.subtype = timedTextSubtype;
        this.matcher = matcher;
        this.label = str;
        this.variants = variants;
        this.activeVariant = timedTextVariant;
    }

    @Override // com.amazon.video.sdk.stream.Stream
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.video.sdk.stream.TimedTextStream
    public String getLanguage() {
        return this.language;
    }

    public TimedTextType getType() {
        return this.type;
    }

    @Override // com.amazon.video.sdk.stream.TimedTextStream
    public TimedTextSubtype getSubtype() {
        return this.subtype;
    }

    public TimedTextStreamMatcher getMatcher() {
        return this.matcher;
    }

    public String getLabel() {
        return this.label;
    }

    public /* synthetic */ TimedTextStreamData(String str, String str2, TimedTextType timedTextType, TimedTextSubtype timedTextSubtype, TimedTextStreamMatcher timedTextStreamMatcher, String str3, List list, TimedTextVariant timedTextVariant, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, timedTextType, timedTextSubtype, timedTextStreamMatcher, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 128) != 0 ? null : timedTextVariant);
    }

    public List<TimedTextVariant> getVariants() {
        return this.variants;
    }

    @Override // com.amazon.video.sdk.stream.Stream
    public TimedTextVariant getActiveVariant() {
        return this.activeVariant;
    }
}
