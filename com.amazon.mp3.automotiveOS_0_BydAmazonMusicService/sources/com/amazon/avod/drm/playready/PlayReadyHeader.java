package com.amazon.avod.drm.playready;

import com.amazon.avod.util.Base64;
import com.amazon.avod.util.DLog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyHeader {
    private static final int KID_START_TAG_LENGTH = 5;
    private final String mHeaderAsBase64;
    private final PlayReadyKeyID mKeyID;
    private final String mXMLString;

    public static PlayReadyHeader fromBase64(String str) {
        try {
            byte[] bArrDecode = Base64.decode(str);
            if (bArrDecode.length < 10) {
                DLog.errorf("PlayReady header too small (%s bytes), base64Form: %s", Integer.valueOf(bArrDecode.length), str);
                return null;
            }
            String str2 = new String(bArrDecode, 10, bArrDecode.length - 10, "UTF-16LE");
            int iIndexOf = str2.indexOf("<KID>");
            int iIndexOf2 = str2.indexOf("</KID>");
            if (iIndexOf >= 0 && iIndexOf2 >= 0) {
                int i = KID_START_TAG_LENGTH;
                if (iIndexOf2 >= iIndexOf + i) {
                    PlayReadyKeyID playReadyKeyIDFromBase64 = PlayReadyKeyID.fromBase64(str2.substring(iIndexOf + i, iIndexOf2));
                    if (playReadyKeyIDFromBase64 == null) {
                        DLog.errorf("Could not decode Key ID in PlayReady header, base64Form: %s", str);
                        return null;
                    }
                    return new PlayReadyHeader(str, playReadyKeyIDFromBase64, str2);
                }
            }
            DLog.errorf("Could not find Key ID in PlayReady header, base64Form: %s", str);
            return null;
        } catch (UnsupportedEncodingException e) {
            DLog.exceptionf(e, String.format(Locale.US, "Impossible error (UTF-16LE is a valid encoding), base64Form: %s", str), new Object[0]);
            return null;
        } catch (IOException e2) {
            e = e2;
            DLog.warnf("Error parsing PlayReady header object: %s, base64Form: %s", e.getMessage(), str);
            return null;
        } catch (IllegalArgumentException e3) {
            e = e3;
            DLog.warnf("Error parsing PlayReady header object: %s, base64Form: %s", e.getMessage(), str);
            return null;
        }
    }

    private PlayReadyHeader(String str, @Nonnull PlayReadyKeyID playReadyKeyID, String str2) {
        this.mHeaderAsBase64 = str;
        this.mKeyID = playReadyKeyID;
        this.mXMLString = str2;
    }

    public String asBase64() {
        return this.mHeaderAsBase64;
    }

    @Nonnull
    public PlayReadyKeyID getKeyID() {
        return this.mKeyID;
    }

    public String getKeyIDAsString() {
        return this.mKeyID.toString();
    }

    public String getPlayreadyUriFromKeyID() {
        String playReadyPluginUri = this.mKeyID.toPlayReadyPluginUri();
        DLog.devf("Generated DRM plugin URI from PlayReady key ID: %s", playReadyPluginUri);
        return playReadyPluginUri;
    }

    public String getPlayreadyUriFromHeader() {
        String str = "playready://" + asBase64() + ".playready";
        DLog.devf("Generated DRM plugin URI from PlayReady header: %s", str);
        return str;
    }

    public String toString() {
        return this.mXMLString;
    }
}
