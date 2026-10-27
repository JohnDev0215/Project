package p;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes.dex */
public final class i01 implements ds1 {
    public static final rl0 b = rl0.a();
    public final com.google.protobuf.i a;

    public i01(com.google.protobuf.i iVar) {
        this.a = iVar;
    }

    public static void a(com.google.protobuf.i iVar) throws InvalidProtocolBufferException {
        if (iVar == null || iVar.isInitialized()) {
            return;
        }
        UninitializedMessageException uninitializedMessageExceptionNewUninitializedMessageException = iVar.newUninitializedMessageException();
        uninitializedMessageExceptionNewUninitializedMessageException.getClass();
        InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(uninitializedMessageExceptionNewUninitializedMessageException.getMessage());
        invalidProtocolBufferException.i(iVar);
        throw invalidProtocolBufferException;
    }

    public final com.google.protobuf.i b(byte[] bArr) throws InvalidProtocolBufferException {
        com.google.protobuf.i iVarD = com.google.protobuf.i.d(this.a, bArr, 0, bArr.length, b);
        a(iVarD);
        return iVarD;
    }
}
