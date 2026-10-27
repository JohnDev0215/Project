package net.bytebuddy.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class StreamDrainer {
    public static final StreamDrainer DEFAULT = new StreamDrainer();
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final int END_OF_STREAM = -1;
    private static final int FROM_BEGINNING = 0;
    private final int bufferSize;

    protected boolean canEqual(Object obj) {
        return obj instanceof StreamDrainer;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StreamDrainer)) {
            return false;
        }
        StreamDrainer streamDrainer = (StreamDrainer) obj;
        return streamDrainer.canEqual(this) && this.bufferSize == streamDrainer.bufferSize;
    }

    public int hashCode() {
        return 59 + this.bufferSize;
    }

    public StreamDrainer() {
        this(1024);
    }

    public StreamDrainer(int i) {
        this.bufferSize = i;
    }

    public byte[] drain(InputStream inputStream) throws IOException {
        int i;
        ArrayList<byte[]> arrayList = new ArrayList();
        byte[] bArr = new byte[this.bufferSize];
        int i2 = 0;
        do {
            i = inputStream.read(bArr, i2, this.bufferSize - i2);
            i2 += i > 0 ? i : 0;
            if (i2 == this.bufferSize) {
                arrayList.add(bArr);
                bArr = new byte[this.bufferSize];
                i2 = 0;
            }
        } while (i != -1);
        byte[] bArr2 = new byte[(arrayList.size() * this.bufferSize) + i2];
        int i3 = 0;
        for (byte[] bArr3 : arrayList) {
            int i4 = this.bufferSize;
            System.arraycopy(bArr3, 0, bArr2, i3 * i4, i4);
            i3++;
        }
        System.arraycopy(bArr, 0, bArr2, i3 * this.bufferSize, i2);
        return bArr2;
    }
}
