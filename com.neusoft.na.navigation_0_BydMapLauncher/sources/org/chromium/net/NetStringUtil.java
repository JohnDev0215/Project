package org.chromium.net;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.text.Normalizer;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("net::android")
public class NetStringUtil {
    private static String convertToUnicode(ByteBuffer byteBuffer, String str) {
        try {
            return Charset.forName(str).newDecoder().decode(byteBuffer).toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private static String convertToUnicodeAndNormalize(ByteBuffer byteBuffer, String str) {
        String strConvertToUnicode = convertToUnicode(byteBuffer, str);
        if (strConvertToUnicode == null) {
            return null;
        }
        return Normalizer.normalize(strConvertToUnicode, Normalizer.Form.NFC);
    }

    private static String convertToUnicodeWithSubstitutions(ByteBuffer byteBuffer, String str) {
        try {
            CharsetDecoder charsetDecoderNewDecoder = Charset.forName(str).newDecoder();
            charsetDecoderNewDecoder.onMalformedInput(CodingErrorAction.REPLACE);
            charsetDecoderNewDecoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
            charsetDecoderNewDecoder.replaceWith("�");
            return charsetDecoderNewDecoder.decode(byteBuffer).toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
