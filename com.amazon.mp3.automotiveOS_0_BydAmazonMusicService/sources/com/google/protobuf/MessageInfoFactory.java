package com.google.protobuf;

/* JADX INFO: loaded from: classes7.dex */
interface MessageInfoFactory {
    boolean isSupported(Class<?> clazz);

    MessageInfo messageInfoFor(Class<?> clazz);
}
