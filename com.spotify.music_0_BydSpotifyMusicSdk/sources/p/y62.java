package p;

import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes.dex */
public final class y62 implements h00 {
    public static final y62 c = new y62();
    public static final MediaType e;

    static {
        MediaType.d.getClass();
        e = MediaType.Companion.a("text/plain; charset=UTF-8");
    }

    @Override // p.h00
    public final Object convert(Object obj) {
        String strValueOf = String.valueOf(obj);
        RequestBody.a.getClass();
        Charset charset = cs.a;
        MediaType mediaTypeB = e;
        if (mediaTypeB != null) {
            Charset charsetA = mediaTypeB.a(null);
            if (charsetA == null) {
                MediaType.d.getClass();
                mediaTypeB = MediaType.Companion.b(mediaTypeB + "; charset=utf-8");
            } else {
                charset = charsetA;
            }
        }
        byte[] bytes = strValueOf.getBytes(charset);
        v03.i(bytes, "this as java.lang.String).getBytes(charset)");
        return RequestBody.Companion.a(bytes, mediaTypeB, 0, bytes.length);
    }
}
