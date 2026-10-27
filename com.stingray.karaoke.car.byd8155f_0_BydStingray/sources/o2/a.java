package O2;

import H7.H;
import e2.K;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import l9.d;

/* JADX INFO: loaded from: classes.dex */
public final class a extends d {
    public static final Pattern d = Pattern.compile("(.+?)='(.*?)';", 32);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharsetDecoder f5062b = StandardCharsets.UTF_8.newDecoder();
    public final CharsetDecoder c = StandardCharsets.ISO_8859_1.newDecoder();

    @Override // l9.d
    public final K O(K2.a aVar, ByteBuffer byteBuffer) {
        String string;
        CharsetDecoder charsetDecoder = this.c;
        CharsetDecoder charsetDecoder2 = this.f5062b;
        String str = null;
        try {
            string = charsetDecoder2.decode(byteBuffer).toString();
        } catch (CharacterCodingException unused) {
            try {
                String string2 = charsetDecoder.decode(byteBuffer).toString();
                charsetDecoder.reset();
                byteBuffer.rewind();
                string = string2;
            } catch (CharacterCodingException unused2) {
                charsetDecoder.reset();
                byteBuffer.rewind();
                string = null;
            } catch (Throwable th) {
                charsetDecoder.reset();
                byteBuffer.rewind();
                throw th;
            }
        } finally {
            charsetDecoder2.reset();
            byteBuffer.rewind();
        }
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.get(bArr);
        if (string == null) {
            return new K(new c(null, null, bArr));
        }
        Matcher matcher = d.matcher(string);
        String str2 = null;
        for (int iEnd = 0; matcher.find(iEnd); iEnd = matcher.end()) {
            String strGroup = matcher.group(1);
            String strGroup2 = matcher.group(2);
            if (strGroup != null) {
                String strR = H.R(strGroup);
                strR.getClass();
                if (strR.equals("streamurl")) {
                    str2 = strGroup2;
                } else if (strR.equals("streamtitle")) {
                    str = strGroup2;
                }
            }
        }
        return new K(new c(str, str2, bArr));
    }
}
