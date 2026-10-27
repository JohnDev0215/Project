package com.amazon.mp3.cloudqueue;

import com.amazon.digitalmusicxp.models.Queue;
import com.amazon.digitalmusicxp.models.QueueSequenceSlice;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: CloudQueuePlaybackUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001R\u0014\u0010\u0004\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"com/amazon/mp3/cloudqueue/CloudQueuePlaybackUtil$Companion$startOnlineQueueInteraction$4", "", "Lcom/amazon/digitalmusicxp/models/Queue;", "Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "key", "getKey", "()Lcom/amazon/digitalmusicxp/models/Queue;", "value", "getValue", "()Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CloudQueuePlaybackUtil$Companion$startOnlineQueueInteraction$4 implements Map.Entry<Queue, QueueSequenceSlice>, KMappedMarker {
    final /* synthetic */ Queue $queue;
    final /* synthetic */ Ref.ObjectRef<QueueSequenceSlice> $queueSequenceSlice;

    @Override // java.util.Map.Entry
    public QueueSequenceSlice setValue(QueueSequenceSlice queueSequenceSlice) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    CloudQueuePlaybackUtil$Companion$startOnlineQueueInteraction$4(Queue queue, Ref.ObjectRef<QueueSequenceSlice> objectRef) {
        this.$queue = queue;
        this.$queueSequenceSlice = objectRef;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map.Entry
    public Queue getKey() {
        return this.$queue;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map.Entry
    public QueueSequenceSlice getValue() {
        return this.$queueSequenceSlice.element;
    }
}
