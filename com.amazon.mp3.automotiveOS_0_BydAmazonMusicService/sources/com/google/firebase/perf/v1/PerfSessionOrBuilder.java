package com.google.firebase.perf.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public interface PerfSessionOrBuilder extends MessageLiteOrBuilder {
    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* synthetic */ MessageLite getDefaultInstanceForType();

    String getSessionId();

    ByteString getSessionIdBytes();

    SessionVerbosity getSessionVerbosity(int i);

    int getSessionVerbosityCount();

    List<SessionVerbosity> getSessionVerbosityList();

    boolean hasSessionId();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ boolean isInitialized();
}
