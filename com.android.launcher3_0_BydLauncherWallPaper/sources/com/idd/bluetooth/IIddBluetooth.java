package com.idd.bluetooth;

/* JADX INFO: loaded from: classes4.dex */
public interface IIddBluetooth {
    boolean close(String str, String str2);

    boolean connectSocket(String str, String str2);

    boolean createSocket(String str, String str2);

    boolean discovery(String str, boolean z, String str2);

    boolean listenSocket(String str, String str2);

    boolean open(String str, String str2);

    boolean recvSocket(String str, String str2, byte[] bArr, int i, int i2);

    boolean scan(String str, boolean z, String str2);

    boolean sendSocket(String str, String str2, byte[] bArr, int i, int i2);
}
