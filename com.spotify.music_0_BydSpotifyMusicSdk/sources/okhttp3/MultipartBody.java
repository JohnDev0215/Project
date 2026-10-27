package okhttp3;

import com.spotify.connectivity.productstate.RxProductState;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okhttp3.MediaType;
import okio.ByteString;
import p.fo;
import p.io;
import p.qo;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class MultipartBody extends RequestBody {
    public static final MediaType f;
    public static final MediaType g;
    public static final byte[] h;
    public static final byte[] i;
    public static final byte[] j;
    public final ByteString b;
    public final List c;
    public final MediaType d;
    public long e;

    public static final class Builder {
        public final ByteString a;
        public MediaType b;
        public final ArrayList c;

        public Builder() {
            String string = UUID.randomUUID().toString();
            v03.i(string, "randomUUID().toString()");
            ByteString byteString = ByteString.k;
            this.a = qo.p(string);
            this.b = MultipartBody.f;
            this.c = new ArrayList();
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public static final class Part {
        public static final Companion c = new Companion(0);
        public final Headers a;
        public final RequestBody b;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(int i) {
                this();
            }
        }

        public Part(Headers headers, RequestBody requestBody) {
            this.a = headers;
            this.b = requestBody;
        }
    }

    static {
        new Companion(0);
        MediaType.d.getClass();
        f = MediaType.Companion.a("multipart/mixed");
        MediaType.Companion.a("multipart/alternative");
        MediaType.Companion.a("multipart/digest");
        MediaType.Companion.a("multipart/parallel");
        g = MediaType.Companion.a("multipart/form-data");
        h = new byte[]{58, 32};
        i = new byte[]{13, 10};
        j = new byte[]{45, 45};
    }

    public MultipartBody(ByteString byteString, MediaType mediaType, List list) {
        v03.j(byteString, "boundaryByteString");
        v03.j(mediaType, RxProductState.Keys.KEY_TYPE);
        this.b = byteString;
        this.c = list;
        MediaType.Companion companion = MediaType.d;
        String str = mediaType + "; boundary=" + byteString.s();
        companion.getClass();
        this.d = MediaType.Companion.a(str);
        this.e = -1L;
    }

    @Override // okhttp3.RequestBody
    public final long a() throws EOFException {
        long j2 = this.e;
        if (j2 != -1) {
            return j2;
        }
        long jD = d(null, true);
        this.e = jD;
        return jD;
    }

    @Override // okhttp3.RequestBody
    public final MediaType b() {
        return this.d;
    }

    @Override // okhttp3.RequestBody
    public final void c(io ioVar) throws EOFException {
        d(ioVar, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long d(io ioVar, boolean z) throws EOFException {
        fo foVar;
        io foVar2;
        if (z) {
            foVar2 = new fo();
            foVar = foVar2;
        } else {
            foVar = 0;
            foVar2 = ioVar;
        }
        List list = this.c;
        int size = list.size();
        long j2 = 0;
        int i2 = 0;
        while (true) {
            ByteString byteString = this.b;
            byte[] bArr = j;
            byte[] bArr2 = i;
            if (i2 >= size) {
                v03.h(foVar2);
                foVar2.V(bArr);
                foVar2.a0(byteString);
                foVar2.V(bArr);
                foVar2.V(bArr2);
                if (!z) {
                    return j2;
                }
                v03.h(foVar);
                long j3 = j2 + foVar.e;
                foVar.X();
                return j3;
            }
            Part part = (Part) list.get(i2);
            Headers headers = part.a;
            v03.h(foVar2);
            foVar2.V(bArr);
            foVar2.a0(byteString);
            foVar2.V(bArr2);
            if (headers != null) {
                int size2 = headers.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    foVar2.s0(headers.b(i3)).V(h).s0(headers.d(i3)).V(bArr2);
                }
            }
            RequestBody requestBody = part.b;
            MediaType mediaTypeB = requestBody.b();
            if (mediaTypeB != null) {
                foVar2.s0("Content-Type: ").s0(mediaTypeB.a).V(bArr2);
            }
            long jA = requestBody.a();
            if (jA != -1) {
                foVar2.s0("Content-Length: ").t0(jA).V(bArr2);
            } else if (z) {
                v03.h(foVar);
                foVar.X();
                return -1L;
            }
            foVar2.V(bArr2);
            if (z) {
                j2 += jA;
            } else {
                requestBody.c(foVar2);
            }
            foVar2.V(bArr2);
            i2++;
        }
    }
}
