package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.Exception.NativeException;

/* JADX INFO: loaded from: classes2.dex */
public interface Marshaller<T extends ClientInput> {
    ClientRequest marshal(T t) throws NativeException;
}
