package com.android.framework.protobuf;

/* JADX INFO: loaded from: classes4.dex */
interface MessageInfoFactory {
    boolean isSupported(Class<?> cls);

    MessageInfo messageInfoFor(Class<?> cls);
}
