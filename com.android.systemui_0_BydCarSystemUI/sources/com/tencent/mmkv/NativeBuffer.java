package com.tencent.mmkv;

/* JADX INFO: loaded from: classes2.dex */
public final class NativeBuffer {
    public long pointer;
    public int size;

    public NativeBuffer(long ptr, int length) {
        this.pointer = ptr;
        this.size = length;
    }
}
