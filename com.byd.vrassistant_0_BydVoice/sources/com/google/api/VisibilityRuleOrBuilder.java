package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface VisibilityRuleOrBuilder extends MessageOrBuilder {
    String getRestriction();

    ByteString getRestrictionBytes();

    String getSelector();

    ByteString getSelectorBytes();
}
