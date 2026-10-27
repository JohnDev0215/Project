package com.amazon.mp3.det.minidump;

import java.io.DataInput;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes3.dex */
public class RVA {
    private long virtualAddress;

    public void readExternal(DataInput dataInput, FileChannel fileChannel) throws IOException {
        this.virtualAddress = ((long) dataInput.readInt()) & 4294967295L;
    }

    public void navigate(FileChannel fileChannel) throws IOException {
        fileChannel.position(this.virtualAddress);
    }
}
