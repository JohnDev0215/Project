package com.google.protobuf;

/* JADX INFO: loaded from: classes8.dex */
interface MessageInfo {
    MessageLite getDefaultInstance();

    ProtoSyntax getSyntax();

    boolean isMessageSetWireFormat();
}
