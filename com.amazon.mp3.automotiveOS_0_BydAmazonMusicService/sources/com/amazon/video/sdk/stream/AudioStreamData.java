package com.amazon.video.sdk.stream;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioStream.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0080\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0002\u0012\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b&\u0010'J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u001b\u0010\rR \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%¨\u0006("}, d2 = {"Lcom/amazon/video/sdk/stream/AudioStreamData;", "Lcom/amazon/video/sdk/stream/AudioStream;", "", "toString", "", "hashCode", "", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "language", "getLanguage", "Lcom/amazon/video/sdk/stream/AudioType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/video/sdk/stream/AudioType;", "getType", "()Lcom/amazon/video/sdk/stream/AudioType;", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "matcher", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "getMatcher", "()Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", ContextMappingConstants.LABEL, "getLabel", "", "Lcom/amazon/video/sdk/stream/AudioVariant;", "variants", "Ljava/util/List;", "getVariants", "()Ljava/util/List;", "activeVariant", "Lcom/amazon/video/sdk/stream/AudioVariant;", "getActiveVariant", "()Lcom/amazon/video/sdk/stream/AudioVariant;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/video/sdk/stream/AudioType;Lcom/amazon/video/sdk/stream/AudioStreamMatcher;Ljava/lang/String;Ljava/util/List;Lcom/amazon/video/sdk/stream/AudioVariant;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AudioStreamData implements AudioStream {
    private final AudioVariant activeVariant;
    private final String id;
    private final String label;
    private final String language;
    private final AudioStreamMatcher matcher;
    private final AudioType type;
    private final List<AudioVariant> variants;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioStreamData)) {
            return false;
        }
        AudioStreamData audioStreamData = (AudioStreamData) other;
        return Intrinsics.areEqual(getId(), audioStreamData.getId()) && Intrinsics.areEqual(getLanguage(), audioStreamData.getLanguage()) && getType() == audioStreamData.getType() && Intrinsics.areEqual(getMatcher(), audioStreamData.getMatcher()) && Intrinsics.areEqual(getLabel(), audioStreamData.getLabel()) && Intrinsics.areEqual(getVariants(), audioStreamData.getVariants()) && Intrinsics.areEqual(getActiveVariant(), audioStreamData.getActiveVariant());
    }

    public int hashCode() {
        return (((((((((((getId().hashCode() * 31) + getLanguage().hashCode()) * 31) + (getType() == null ? 0 : getType().hashCode())) * 31) + getMatcher().hashCode()) * 31) + (getLabel() == null ? 0 : getLabel().hashCode())) * 31) + getVariants().hashCode()) * 31) + (getActiveVariant() != null ? getActiveVariant().hashCode() : 0);
    }

    public String toString() {
        return "AudioStreamData(id=" + getId() + ", language=" + getLanguage() + ", type=" + getType() + ", matcher=" + getMatcher() + ", label=" + ((Object) getLabel()) + ", variants=" + getVariants() + ", activeVariant=" + getActiveVariant() + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AudioStreamData(String id, String language, AudioType audioType, AudioStreamMatcher matcher, String str, List<? extends AudioVariant> variants, AudioVariant audioVariant) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(matcher, "matcher");
        Intrinsics.checkNotNullParameter(variants, "variants");
        this.id = id;
        this.language = language;
        this.type = audioType;
        this.matcher = matcher;
        this.label = str;
        this.variants = variants;
        this.activeVariant = audioVariant;
    }

    @Override // com.amazon.video.sdk.stream.Stream
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.video.sdk.stream.AudioStream
    public String getLanguage() {
        return this.language;
    }

    @Override // com.amazon.video.sdk.stream.AudioStream
    public AudioType getType() {
        return this.type;
    }

    public AudioStreamMatcher getMatcher() {
        return this.matcher;
    }

    public String getLabel() {
        return this.label;
    }

    public /* synthetic */ AudioStreamData(String str, String str2, AudioType audioType, AudioStreamMatcher audioStreamMatcher, String str3, List list, AudioVariant audioVariant, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, audioType, audioStreamMatcher, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 64) != 0 ? null : audioVariant);
    }

    public List<AudioVariant> getVariants() {
        return this.variants;
    }

    @Override // com.amazon.video.sdk.stream.Stream
    public AudioVariant getActiveVariant() {
        return this.activeVariant;
    }
}
