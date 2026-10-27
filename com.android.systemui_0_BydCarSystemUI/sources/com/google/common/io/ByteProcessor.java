package com.google.common.io;

import com.google.errorprone.annotations.DoNotMock;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
@DoNotMock("Implement it normally")
public interface ByteProcessor<T> {
    T getResult();

    boolean processBytes(byte[] bArr, int i, int i2) throws IOException;
}
