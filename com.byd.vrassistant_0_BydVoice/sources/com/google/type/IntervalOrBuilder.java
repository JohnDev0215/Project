package com.google.type;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;

/* JADX INFO: loaded from: classes3.dex */
public interface IntervalOrBuilder extends MessageOrBuilder {
    Timestamp getEndTime();

    TimestampOrBuilder getEndTimeOrBuilder();

    Timestamp getStartTime();

    TimestampOrBuilder getStartTimeOrBuilder();

    boolean hasEndTime();

    boolean hasStartTime();
}
