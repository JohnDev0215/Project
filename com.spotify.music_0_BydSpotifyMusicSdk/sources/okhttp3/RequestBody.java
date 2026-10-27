package okhttp3;

import okhttp3.internal.Util;
import p.io;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class RequestBody {
    public static final Companion a = new Companion(0);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        /* JADX WARN: Type inference failed for: r0v5, types: [okhttp3.RequestBody$Companion$toRequestBody$2] */
        public static RequestBody$Companion$toRequestBody$2 a(final byte[] bArr, final MediaType mediaType, final int i, final int i2) {
            v03.j(bArr, "<this>");
            long length = bArr.length;
            long j = i;
            long j2 = i2;
            byte[] bArr2 = Util.a;
            if ((j | j2) < 0 || j > length || length - j < j2) {
                throw new ArrayIndexOutOfBoundsException();
            }
            return new RequestBody() { // from class: okhttp3.RequestBody$Companion$toRequestBody$2
                @Override // okhttp3.RequestBody
                public final long a() {
                    return i2;
                }

                @Override // okhttp3.RequestBody
                public final MediaType b() {
                    return mediaType;
                }

                @Override // okhttp3.RequestBody
                public final void c(io ioVar) {
                    ioVar.l(bArr, i, i2);
                }
            };
        }
    }

    public long a() {
        return -1L;
    }

    public abstract MediaType b();

    public abstract void c(io ioVar);
}
