package com.amazon.mp3.starlight.provider;

import com.amazon.mp3.prime.requester.HybridQueueType;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HybridQueueStateProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0016\u0012\u0016\b\u0002\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0016\u0012\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\u001d¢\u0006\u0004\b\"\u0010#J\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0004\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0002J\u0006\u0010\u0006\u001a\u00020\u0002J\u0006\u0010\b\u001a\u00020\u0007J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R%\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR%\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00168\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0018\u001a\u0004\b\u001c\u0010\u001aR\u001d\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006$"}, d2 = {"Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;", "", "", "isDefault", "isVideo", "isAudio", "isBackgroundAudio", "", "reset", "", "toString", "", "hashCode", "other", "equals", "Lcom/amazon/mp3/prime/requester/HybridQueueType;", "hybridQueueType", "Lcom/amazon/mp3/prime/requester/HybridQueueType;", "getHybridQueueType", "()Lcom/amazon/mp3/prime/requester/HybridQueueType;", "setHybridQueueType", "(Lcom/amazon/mp3/prime/requester/HybridQueueType;)V", "", "audioToVideoMap", "Ljava/util/Map;", "getAudioToVideoMap", "()Ljava/util/Map;", "videoToAudioMap", "getVideoToAudioMap", "", "asinsAlreadyRequested", "Ljava/util/Set;", "getAsinsAlreadyRequested", "()Ljava/util/Set;", "<init>", "(Lcom/amazon/mp3/prime/requester/HybridQueueType;Ljava/util/Map;Ljava/util/Map;Ljava/util/Set;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class HybridQueueStateProvider {
    private final Set<String> asinsAlreadyRequested;
    private final Map<String, String> audioToVideoMap;
    private HybridQueueType hybridQueueType;
    private final Map<String, String> videoToAudioMap;

    public HybridQueueStateProvider() {
        this(null, null, null, null, 15, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HybridQueueStateProvider)) {
            return false;
        }
        HybridQueueStateProvider hybridQueueStateProvider = (HybridQueueStateProvider) other;
        return Intrinsics.areEqual(this.hybridQueueType, hybridQueueStateProvider.hybridQueueType) && Intrinsics.areEqual(this.audioToVideoMap, hybridQueueStateProvider.audioToVideoMap) && Intrinsics.areEqual(this.videoToAudioMap, hybridQueueStateProvider.videoToAudioMap) && Intrinsics.areEqual(this.asinsAlreadyRequested, hybridQueueStateProvider.asinsAlreadyRequested);
    }

    public int hashCode() {
        return (((((this.hybridQueueType.hashCode() * 31) + this.audioToVideoMap.hashCode()) * 31) + this.videoToAudioMap.hashCode()) * 31) + this.asinsAlreadyRequested.hashCode();
    }

    public String toString() {
        return "HybridQueueStateProvider(hybridQueueType=" + this.hybridQueueType + ", audioToVideoMap=" + this.audioToVideoMap + ", videoToAudioMap=" + this.videoToAudioMap + ", asinsAlreadyRequested=" + this.asinsAlreadyRequested + ')';
    }

    public HybridQueueStateProvider(HybridQueueType hybridQueueType, Map<String, String> audioToVideoMap, Map<String, String> videoToAudioMap, Set<String> asinsAlreadyRequested) {
        Intrinsics.checkNotNullParameter(hybridQueueType, "hybridQueueType");
        Intrinsics.checkNotNullParameter(audioToVideoMap, "audioToVideoMap");
        Intrinsics.checkNotNullParameter(videoToAudioMap, "videoToAudioMap");
        Intrinsics.checkNotNullParameter(asinsAlreadyRequested, "asinsAlreadyRequested");
        this.hybridQueueType = hybridQueueType;
        this.audioToVideoMap = audioToVideoMap;
        this.videoToAudioMap = videoToAudioMap;
        this.asinsAlreadyRequested = asinsAlreadyRequested;
    }

    public /* synthetic */ HybridQueueStateProvider(HybridQueueType hybridQueueType, Map map, Map map2, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? HybridQueueType.DEFAULT_QUEUE.INSTANCE : hybridQueueType, (i & 2) != 0 ? new LinkedHashMap() : map, (i & 4) != 0 ? new LinkedHashMap() : map2, (i & 8) != 0 ? new LinkedHashSet() : set);
    }

    public final void setHybridQueueType(HybridQueueType hybridQueueType) {
        Intrinsics.checkNotNullParameter(hybridQueueType, "<set-?>");
        this.hybridQueueType = hybridQueueType;
    }

    public final Map<String, String> getAudioToVideoMap() {
        return this.audioToVideoMap;
    }

    public final Map<String, String> getVideoToAudioMap() {
        return this.videoToAudioMap;
    }

    public final Set<String> getAsinsAlreadyRequested() {
        return this.asinsAlreadyRequested;
    }

    public final boolean isDefault() {
        return Intrinsics.areEqual(this.hybridQueueType, HybridQueueType.DEFAULT_QUEUE.INSTANCE);
    }

    public final boolean isVideo() {
        return Intrinsics.areEqual(this.hybridQueueType, HybridQueueType.VIDEO_QUEUE.INSTANCE);
    }

    public final boolean isAudio() {
        return Intrinsics.areEqual(this.hybridQueueType, HybridQueueType.AUDIO_QUEUE.INSTANCE);
    }

    public final boolean isBackgroundAudio() {
        return Intrinsics.areEqual(this.hybridQueueType, HybridQueueType.BACKGROUND_AUDIO.INSTANCE);
    }

    public final void reset() {
        this.hybridQueueType = HybridQueueType.DEFAULT_QUEUE.INSTANCE;
    }
}
