package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.Exception.CoralException;
import com.amazon.CoralAndroidClient.Exception.NativeException;

/* JADX INFO: loaded from: classes2.dex */
public interface Unmarshaller {
    CoralException UnmarshalException(String str) throws NativeException;

    ClientOutput UnmarshalOutput(String str) throws NativeException;
}
