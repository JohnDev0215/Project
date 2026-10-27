package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes7.dex */
public abstract class ByteOutput {
    public abstract void writeLazy(ByteBuffer value) throws IOException;

    public abstract void writeLazy(byte[] value, int offset, int length) throws IOException;
}
