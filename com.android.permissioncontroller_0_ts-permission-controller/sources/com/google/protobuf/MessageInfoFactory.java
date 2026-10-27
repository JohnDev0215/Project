package com.google.protobuf;

/* JADX INFO: loaded from: classes8.dex */
interface MessageInfoFactory {
    boolean isSupported(Class<?> cls);

    MessageInfo messageInfoFor(Class<?> cls);
}
