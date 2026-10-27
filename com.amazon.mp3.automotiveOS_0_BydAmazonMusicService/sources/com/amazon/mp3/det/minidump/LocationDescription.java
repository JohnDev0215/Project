package com.amazon.mp3.det.minidump;

import java.io.DataInput;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes3.dex */
public class LocationDescription {
    private int dataSize;
    private final RVA rva = new RVA();

    public void readExternal(DataInput dataInput, FileChannel fileChannel) throws IOException {
        this.dataSize = dataInput.readInt();
        this.rva.readExternal(dataInput, fileChannel);
    }

    public int getDataSize() {
        return this.dataSize;
    }

    public RVA getRVA() {
        return this.rva;
    }
}
