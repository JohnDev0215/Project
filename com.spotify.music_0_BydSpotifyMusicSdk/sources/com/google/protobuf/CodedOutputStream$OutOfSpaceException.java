package com.google.protobuf;

import java.io.IOException;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public class CodedOutputStream$OutOfSpaceException extends IOException {
    private static final long serialVersionUID = -6947486886997889499L;

    public CodedOutputStream$OutOfSpaceException(String str, IndexOutOfBoundsException indexOutOfBoundsException) {
        super(u4.x("CodedOutputStream was writing to a flat byte array and ran out of space.: ", str), indexOutOfBoundsException);
    }

    public CodedOutputStream$OutOfSpaceException(IndexOutOfBoundsException indexOutOfBoundsException) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", indexOutOfBoundsException);
    }
}
