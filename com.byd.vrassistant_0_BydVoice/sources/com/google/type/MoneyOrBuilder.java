package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes3.dex */
public interface MoneyOrBuilder extends MessageOrBuilder {
    String getCurrencyCode();

    ByteString getCurrencyCodeBytes();

    int getNanos();

    long getUnits();
}
