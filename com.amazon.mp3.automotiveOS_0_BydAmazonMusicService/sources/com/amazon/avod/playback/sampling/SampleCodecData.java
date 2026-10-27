package com.amazon.avod.playback.sampling;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SampleCodecData {
    private final byte[] mData;
    private final byte[] mPpsBytes;
    private final byte[] mSpsBytes;

    public byte[] getRawBytes() {
        return this.mData;
    }

    public byte[] getSpsBytes() {
        return this.mSpsBytes;
    }

    public byte[] getPpsBytes() {
        return this.mPpsBytes;
    }

    public SampleCodecData(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.mData = (byte[]) Preconditions.checkNotNull(bArr, "rawData");
        this.mSpsBytes = (byte[]) Preconditions.checkNotNull(bArr2, "spsBytes");
        this.mPpsBytes = (byte[]) Preconditions.checkNotNull(bArr3, "ppsBytes");
    }

    public static SampleCodecData fromHexString(@Nonnull String str) {
        byte[] bArrConvertHexStringToByteArray;
        byte[] bArr;
        Preconditions.checkNotNull(str, "codecPrivateData");
        byte[] bArrConvertHexStringToByteArray2 = convertHexStringToByteArray(str);
        String[] strArrSplit = str.split("00000001");
        if (strArrSplit.length < 3) {
            bArr = new byte[0];
            bArrConvertHexStringToByteArray = new byte[0];
        } else {
            byte[] bArrConvertHexStringToByteArray3 = convertHexStringToByteArray("00000001" + strArrSplit[1]);
            bArrConvertHexStringToByteArray = convertHexStringToByteArray("00000001" + strArrSplit[2]);
            bArr = bArrConvertHexStringToByteArray3;
        }
        return new SampleCodecData(bArrConvertHexStringToByteArray2, bArr, bArrConvertHexStringToByteArray);
    }

    private static byte[] convertHexStringToByteArray(@Nonnull String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 1; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i - 1), 16) << 4) + Character.digit(str.charAt(i), 16));
        }
        return bArr;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SampleCodecData)) {
            return false;
        }
        SampleCodecData sampleCodecData = (SampleCodecData) obj;
        return Arrays.equals(this.mData, sampleCodecData.getRawBytes()) && Arrays.equals(this.mPpsBytes, sampleCodecData.getPpsBytes()) && Arrays.equals(this.mSpsBytes, sampleCodecData.getSpsBytes());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.mData.length * 2);
        int i = 0;
        while (true) {
            byte[] bArr = this.mData;
            if (i >= bArr.length) {
                return sb.toString();
            }
            sb.append(String.format(Locale.US, "%02x", Byte.valueOf(bArr[i])));
            i++;
        }
    }
}
