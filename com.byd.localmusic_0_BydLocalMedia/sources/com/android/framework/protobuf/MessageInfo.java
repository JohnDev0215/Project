package com.android.framework.protobuf;

/* JADX INFO: loaded from: classes4.dex */
interface MessageInfo {
    MessageLite getDefaultInstance();

    ProtoSyntax getSyntax();

    boolean isMessageSetWireFormat();
}
