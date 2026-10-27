package com.amazon.digitalmusicxp.enums;

import PlaybackInterface.v1_0.PlaybackEvent;
import kotlin.Metadata;

/* JADX INFO: compiled from: InitiateQueueResponseContextEnum.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/digitalmusicxp/enums/InitiateQueueResponseContextEnum;", "", "(Ljava/lang/String;I)V", "SUCCESS", "CUSTOMER_INELIGIBLE", "ERROR_MANAGING_PLAYBACK", PlaybackEvent.UPSELL, "ENTITIES_INELIGIBLE_FOR_TRANSFER", "QUEUE_DETACHED_FROM_DEVICE", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum InitiateQueueResponseContextEnum {
    SUCCESS,
    CUSTOMER_INELIGIBLE,
    ERROR_MANAGING_PLAYBACK,
    UPSELL,
    ENTITIES_INELIGIBLE_FOR_TRANSFER,
    QUEUE_DETACHED_FROM_DEVICE
}
