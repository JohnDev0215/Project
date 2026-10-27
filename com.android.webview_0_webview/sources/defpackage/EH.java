package defpackage;

import android.media.MediaFormat;
import android.os.Build;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class EH {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static void a(MediaFormat mediaFormat, boolean z) {
        int i;
        int i2;
        if (z) {
            mediaFormat.setInteger("max-width", mediaFormat.getInteger("width"));
            mediaFormat.setInteger("max-height", mediaFormat.getInteger("height"));
        }
        if (mediaFormat.containsKey("max-input-size")) {
            return;
        }
        int integer = mediaFormat.getInteger("height");
        if (z && mediaFormat.containsKey("max-height")) {
            integer = Math.max(integer, mediaFormat.getInteger("max-height"));
        }
        int integer2 = mediaFormat.getInteger("width");
        if (z && mediaFormat.containsKey("max-width")) {
            integer2 = Math.max(integer, mediaFormat.getInteger("max-width"));
        }
        String string = mediaFormat.getString("mime");
        byte b = -1;
        switch (string.hashCode()) {
            case -1662541442:
                if (string.equals("video/hevc")) {
                    b = 2;
                }
                break;
            case 1331836730:
                if (string.equals("video/avc")) {
                    b = 0;
                }
                break;
            case 1599127256:
                if (string.equals("video/x-vnd.on2.vp8")) {
                    b = 1;
                }
                break;
            case 1599127257:
                if (string.equals("video/x-vnd.on2.vp9")) {
                    b = 3;
                }
                break;
        }
        if (b != 0) {
            if (b != 1) {
                if (b == 2 || b == 3) {
                    i = integer2 * integer;
                    i2 = 4;
                    mediaFormat.setInteger("max-input-size", (i * 3) / (i2 * 2));
                }
                return;
            }
            i = integer2 * integer;
        } else {
            if ("BRAVIA 4K 2015".equals(Build.MODEL)) {
                return;
            }
            i = ((integer + 15) / 16) * ((integer2 + 15) / 16) * 16 * 16;
        }
        i2 = 2;
        mediaFormat.setInteger("max-input-size", (i * 3) / (i2 * 2));
    }

    public static void b(MediaFormat mediaFormat, byte[][] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i].length != 0) {
                mediaFormat.setByteBuffer(Z7.a("csd-", i), ByteBuffer.wrap(bArr[i]));
            }
        }
    }
}
