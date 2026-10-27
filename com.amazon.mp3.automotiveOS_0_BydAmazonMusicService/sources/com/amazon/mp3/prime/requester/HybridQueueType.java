package com.amazon.mp3.prime.requester;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: HybridQueueType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/prime/requester/HybridQueueType;", "", "()V", "AUDIO_QUEUE", "BACKGROUND_AUDIO", "DEFAULT_QUEUE", "VIDEO_QUEUE", "Lcom/amazon/mp3/prime/requester/HybridQueueType$AUDIO_QUEUE;", "Lcom/amazon/mp3/prime/requester/HybridQueueType$VIDEO_QUEUE;", "Lcom/amazon/mp3/prime/requester/HybridQueueType$DEFAULT_QUEUE;", "Lcom/amazon/mp3/prime/requester/HybridQueueType$BACKGROUND_AUDIO;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class HybridQueueType {
    public /* synthetic */ HybridQueueType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: HybridQueueType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/prime/requester/HybridQueueType$AUDIO_QUEUE;", "Lcom/amazon/mp3/prime/requester/HybridQueueType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AUDIO_QUEUE extends HybridQueueType {
        public static final AUDIO_QUEUE INSTANCE = new AUDIO_QUEUE();

        private AUDIO_QUEUE() {
            super(null);
        }
    }

    private HybridQueueType() {
    }

    /* JADX INFO: compiled from: HybridQueueType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/prime/requester/HybridQueueType$VIDEO_QUEUE;", "Lcom/amazon/mp3/prime/requester/HybridQueueType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class VIDEO_QUEUE extends HybridQueueType {
        public static final VIDEO_QUEUE INSTANCE = new VIDEO_QUEUE();

        private VIDEO_QUEUE() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: HybridQueueType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/prime/requester/HybridQueueType$DEFAULT_QUEUE;", "Lcom/amazon/mp3/prime/requester/HybridQueueType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DEFAULT_QUEUE extends HybridQueueType {
        public static final DEFAULT_QUEUE INSTANCE = new DEFAULT_QUEUE();

        private DEFAULT_QUEUE() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: HybridQueueType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/prime/requester/HybridQueueType$BACKGROUND_AUDIO;", "Lcom/amazon/mp3/prime/requester/HybridQueueType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class BACKGROUND_AUDIO extends HybridQueueType {
        public static final BACKGROUND_AUDIO INSTANCE = new BACKGROUND_AUDIO();

        private BACKGROUND_AUDIO() {
            super(null);
        }
    }
}
