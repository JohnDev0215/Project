package com.amazon.avod.playback.sampling;

import com.amazon.avod.playback.drm.EncryptedBufferRegion;
import java.nio.ByteBuffer;
import java.util.Locale;
import javax.annotation.Nullable;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes2.dex */
public class SampleEncryptionInfo {
    private static final byte[] ZERO_INITIALIZATION_VECTOR = {0, 0, 0, 0, 0, 0, 0, 0};
    private EncryptedBufferRegion[] mEncryptedRegions;
    private byte[] mInitializationVector;
    private ByteBuffer mInitializationVectorByteBuffer;
    private int mInitializationVectorLength;

    public static SampleEncryptionInfo generateClearTextEncryptionInfo(int i) {
        EncryptedBufferRegion[] encryptedBufferRegionArr = {new EncryptedBufferRegion(i, 0)};
        byte[] bArr = ZERO_INITIALIZATION_VECTOR;
        return new SampleEncryptionInfo(encryptedBufferRegionArr, bArr, bArr.length);
    }

    public SampleEncryptionInfo() {
    }

    public SampleEncryptionInfo(EncryptedBufferRegion[] encryptedBufferRegionArr, byte[] bArr, int i) {
        initialize(encryptedBufferRegionArr, bArr, i);
    }

    public final void initialize(EncryptedBufferRegion[] encryptedBufferRegionArr, byte[] bArr, int i) {
        setEncryptedRegions(encryptedBufferRegionArr);
        this.mInitializationVector = bArr;
        this.mInitializationVectorLength = i;
        if (bArr != null) {
            this.mInitializationVectorByteBuffer = ByteBuffer.wrap(bArr);
        } else {
            this.mInitializationVectorByteBuffer = null;
        }
    }

    @Nullable
    public EncryptedBufferRegion[] getEncryptedRegions() {
        return this.mEncryptedRegions;
    }

    public void setEncryptedRegions(@Nullable EncryptedBufferRegion[] encryptedBufferRegionArr) {
        this.mEncryptedRegions = encryptedBufferRegionArr;
    }

    public int getInitializationVectorLength() {
        return this.mInitializationVectorLength;
    }

    public ByteBuffer getInitializationVectorByteBuffer() {
        return this.mInitializationVectorByteBuffer;
    }

    public byte[] getInitializationVector() {
        return this.mInitializationVector;
    }

    public String toString() {
        int i;
        StringBuilder sb = new StringBuilder();
        if (!hasInitializationVector()) {
            sb.append("Uninitialized SampleEncryptionInfo object!");
            return sb.toString();
        }
        sb.append("IV Data: ");
        int length = this.mInitializationVector.length - 1;
        while (true) {
            if (length < 0) {
                break;
            }
            sb.append(String.format(Locale.US, "%02X ", Byte.valueOf(this.mInitializationVector[length])));
            length--;
        }
        byte[] bArr = this.mInitializationVector;
        int i2 = (bArr[bArr.length - 1] & ByteCompanionObject.MIN_VALUE) >> 7;
        int i3 = (bArr[bArr.length - 1] & 64) >> 6;
        Locale locale = Locale.US;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(i2);
        objArr[1] = Integer.valueOf(i3);
        objArr[2] = i3 == 0 ? "requires secure rendering" : "may be rendered insecurely";
        sb.append(String.format(locale, "(top 2 bits %d%d; %s)", objArr));
        sb.append(", encrypted regions:");
        for (EncryptedBufferRegion encryptedBufferRegion : this.mEncryptedRegions) {
            sb.append(" ");
            sb.append(encryptedBufferRegion.toString());
        }
        return sb.toString();
    }

    public boolean hasInitializationVector() {
        return getInitializationVector() != null && getInitializationVector().length > 0;
    }
}
