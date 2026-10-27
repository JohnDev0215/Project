package io.objectbox.sync;

/* JADX INFO: loaded from: classes5.dex */
public interface ObjectsMessageBuilder {
    ObjectsMessageBuilder addBytes(long j, byte[] bArr, boolean z);

    ObjectsMessageBuilder addString(long j, String str);

    boolean send();
}
