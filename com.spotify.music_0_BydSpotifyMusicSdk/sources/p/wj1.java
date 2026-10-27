package p;

import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes.dex */
public interface wj1 extends yj1 {
    int getSerializedSize();

    vj1 newBuilderForType();

    byte[] toByteArray();

    ByteString toByteString();

    void writeTo(com.google.protobuf.h hVar);
}
