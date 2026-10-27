package com.amazon.avod.qos.internal.event;

import com.amazon.avod.events.EventManager;
import com.amazon.avod.events.PlayerEventType;
import com.amazon.avod.media.framework.MediaComponent;

/* JADX INFO: loaded from: classes2.dex */
public class QosEventModelMediaComponent implements MediaComponent {
    private final QOSBatchedEventModelFactory mBatchedFactory;
    private final EventManager mEventManager;
    private final QOSImmediateEventModelFactory mImmediateFactory;
    private final QOSEventModelFactory mModelFactory;

    public QosEventModelMediaComponent() {
        this(EventManager.getInstance(), new QOSBatchedEventModelFactory(), new QOSImmediateEventModelFactory(), new QOSEventModelFactory());
    }

    public QosEventModelMediaComponent(EventManager eventManager, QOSBatchedEventModelFactory qOSBatchedEventModelFactory, QOSImmediateEventModelFactory qOSImmediateEventModelFactory, QOSEventModelFactory qOSEventModelFactory) {
        this.mEventManager = eventManager;
        this.mBatchedFactory = qOSBatchedEventModelFactory;
        this.mImmediateFactory = qOSImmediateEventModelFactory;
        this.mModelFactory = qOSEventModelFactory;
    }

    @Override // com.amazon.avod.media.framework.MediaComponent
    public void initialize() {
        this.mEventManager.registerEventType(PlayerEventType.QOS, this.mModelFactory);
        this.mEventManager.registerEventType(PlayerEventType.QOS_BATCHED, this.mBatchedFactory);
        this.mEventManager.registerEventType(PlayerEventType.QOS_IMMEDIATE, this.mImmediateFactory);
    }
}
