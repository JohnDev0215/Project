package com.google.protobuf;

import com.google.protobuf.ByteString;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import p.k72;
import p.r1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements wj1 {
    protected int memoizedHashCode;

    @Deprecated
    public static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
        r1.addAll((Iterable) iterable, (List) collection);
    }

    public static void checkByteStringIsUtf8(ByteString byteString) {
        if (!byteString.i()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    public final String a(String str) {
        return "Serializing " + getClass().getName() + " to a " + str + " threw an IOException (should never happen).";
    }

    public abstract int getSerializedSize(k72 k72Var);

    public UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException();
    }

    @Override // p.wj1
    public byte[] toByteArray() {
        try {
            int serializedSize = getSerializedSize();
            byte[] bArr = new byte[serializedSize];
            Logger logger = h.h;
            f fVar = new f(bArr, serializedSize);
            writeTo(fVar);
            if (fVar.l0() == 0) {
                return bArr;
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (IOException e) {
            throw new RuntimeException(a("byte array"), e);
        }
    }

    @Override // p.wj1
    public ByteString toByteString() {
        try {
            int serializedSize = getSerializedSize();
            ByteString byteString = ByteString.c;
            byte[] bArr = new byte[serializedSize];
            Logger logger = h.h;
            f fVar = new f(bArr, serializedSize);
            writeTo(fVar);
            if (fVar.l0() == 0) {
                return new ByteString.LiteralByteString(bArr);
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (IOException e) {
            throw new RuntimeException(a("ByteString"), e);
        }
    }

    public void writeDelimitedTo(OutputStream outputStream) {
        int serializedSize = getSerializedSize();
        int iN = h.N(serializedSize) + serializedSize;
        if (iN > 4096) {
            iN = 4096;
        }
        g gVar = new g(outputStream, iN);
        gVar.i0(serializedSize);
        writeTo(gVar);
        if (gVar.l > 0) {
            gVar.q0();
        }
    }

    public void writeTo(OutputStream outputStream) {
        int serializedSize = getSerializedSize();
        Logger logger = h.h;
        if (serializedSize > 4096) {
            serializedSize = 4096;
        }
        g gVar = new g(outputStream, serializedSize);
        writeTo(gVar);
        if (gVar.l > 0) {
            gVar.q0();
        }
    }

    public static <T> void addAll(Iterable<T> iterable, List<? super T> list) {
        r1.addAll((Iterable) iterable, (List) list);
    }
}
