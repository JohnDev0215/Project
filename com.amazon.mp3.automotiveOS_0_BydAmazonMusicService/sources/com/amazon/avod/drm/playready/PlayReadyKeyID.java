package com.amazon.avod.drm.playready;

import com.amazon.avod.util.Base64;
import com.amazon.avod.util.DLog;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyKeyID {
    private final String mBase64KeyID;

    private PlayReadyKeyID(String str) {
        this.mBase64KeyID = str;
    }

    public static PlayReadyKeyID fromBase64(String str) {
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(base64ToByteArray(str) != null);
        return new PlayReadyKeyID(str);
    }

    private static byte[] base64ToByteArray(String str) {
        try {
            byte[] bArrDecode = Base64.decode(str);
            if (bArrDecode.length == 16) {
                return bArrDecode;
            }
            DLog.warnf("Base64 PlayReady Key ID \"%s\" decodes to %s bytes (should be %s)", str, Integer.valueOf(bArrDecode.length), 16);
            return null;
        } catch (IOException e) {
            DLog.warnf("Decoding base64-encoded PlayReady Key ID \"%s\": %s", str, e.getMessage());
            return null;
        }
    }

    public byte[] toByteArray() {
        return base64ToByteArray(this.mBase64KeyID);
    }

    public String toBase64() {
        return this.mBase64KeyID;
    }

    public String toPlayReadyPluginUri() {
        return "playready://" + this.mBase64KeyID + ".playready";
    }

    public String toString() {
        byte[] byteArray = toByteArray();
        Preconditions.checkState(byteArray != null);
        long[] jArr = new long[16];
        for (int i = 0; i < byteArray.length; i++) {
            jArr[i] = byteArray[i] & 255;
        }
        StringBuilder sb = new StringBuilder();
        Locale locale = Locale.US;
        sb.append(String.format(locale, "%08X-", Long.valueOf(jArr[0] | ((jArr[1] | ((jArr[2] | (jArr[3] << 8)) << 8)) << 8))));
        sb.append(String.format(locale, "%04X-", Long.valueOf(jArr[4] | (jArr[5] << 8))));
        sb.append(String.format(locale, "%04X-", Long.valueOf(jArr[6] | (jArr[7] << 8))));
        sb.append(String.format(locale, "%02X", Long.valueOf(jArr[8])));
        sb.append(String.format(locale, "%02X-", Long.valueOf(jArr[9])));
        sb.append(String.format(locale, "%02X", Long.valueOf(jArr[10])));
        sb.append(String.format(locale, "%02X", Long.valueOf(jArr[11])));
        sb.append(String.format(locale, "%02X", Long.valueOf(jArr[12])));
        sb.append(String.format(locale, "%02X", Long.valueOf(jArr[13])));
        sb.append(String.format(locale, "%02X", Long.valueOf(jArr[14])));
        sb.append(String.format(locale, "%02X", Long.valueOf(jArr[15])));
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.mBase64KeyID);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PlayReadyKeyID)) {
            return false;
        }
        return Objects.equal(this.mBase64KeyID, ((PlayReadyKeyID) obj).mBase64KeyID);
    }
}
