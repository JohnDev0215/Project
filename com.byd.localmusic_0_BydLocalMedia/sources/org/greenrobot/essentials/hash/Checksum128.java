package org.greenrobot.essentials.hash;

import java.math.BigInteger;
import java.util.zip.Checksum;

/* JADX INFO: loaded from: classes5.dex */
public interface Checksum128 extends Checksum {
    BigInteger getValueBigInteger();

    byte[] getValueBytesBigEndian();

    byte[] getValueBytesLittleEndian();

    String getValueHexString();

    long getValueHigh();
}
