package okhttp3;

import com.spotify.connectivity.httpwebgate.WebgateHelper;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlin.text.b;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.ByteString;
import p.de2;
import p.fo;
import p.ko;
import p.ng2;
import p.ow0;
import p.pd2;
import p.pw0;
import p.qo;
import p.ux2;
import p.v02;
import p.v03;
import p.w02;

/* JADX INFO: loaded from: classes.dex */
public final class Cache implements Closeable, Flushable {
    public static final Companion e = new Companion(0);
    public final DiskLruCache c;

    public static final class CacheResponseBody extends ResponseBody {
        public final DiskLruCache.Snapshot k;
        public final String l;
        public final String m;
        public final w02 n;

        public CacheResponseBody(DiskLruCache.Snapshot snapshot, String str, String str2) {
            this.k = snapshot;
            this.l = str;
            this.m = str2;
            this.n = v03.d(new pw0((de2) snapshot.k.get(1)) { // from class: okhttp3.Cache.CacheResponseBody.1
                @Override // p.pw0, java.io.Closeable, java.lang.AutoCloseable
                public final void close() {
                    this.k.close();
                    super.close();
                }
            });
        }

        @Override // okhttp3.ResponseBody
        public final long b() {
            String str = this.m;
            if (str == null) {
                return -1L;
            }
            byte[] bArr = Util.a;
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }

        @Override // okhttp3.ResponseBody
        public final MediaType c() {
            String str = this.l;
            if (str == null) {
                return null;
            }
            MediaType.d.getClass();
            return MediaType.Companion.b(str);
        }

        @Override // okhttp3.ResponseBody
        public final ko i() {
            return this.n;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        public static String a(HttpUrl httpUrl) {
            v03.j(httpUrl, "url");
            ByteString byteString = ByteString.k;
            return qo.p(httpUrl.i).c("MD5").f();
        }

        public static int b(w02 w02Var) throws IOException {
            try {
                long jC = w02Var.c();
                String strG0 = w02Var.g0(Long.MAX_VALUE);
                if (jC >= 0 && jC <= 2147483647L && strG0.length() <= 0) {
                    return (int) jC;
                }
                throw new IOException("expected an int but was \"" + jC + strG0 + '\"');
            } catch (NumberFormatException e) {
                throw new IOException(e.getMessage());
            }
        }

        public static Set c(Headers headers) {
            int size = headers.size();
            TreeSet treeSet = null;
            for (int i = 0; i < size; i++) {
                if (ng2.b0("Vary", headers.b(i), true)) {
                    String strD = headers.d(i);
                    if (treeSet == null) {
                        Comparator comparator = String.CASE_INSENSITIVE_ORDER;
                        v03.i(comparator, "CASE_INSENSITIVE_ORDER");
                        treeSet = new TreeSet(comparator);
                    }
                    Iterator it = b.y0(strD, new char[]{','}).iterator();
                    while (it.hasNext()) {
                        treeSet.add(b.G0((String) it.next()).toString());
                    }
                }
            }
            return treeSet == null ? EmptySet.c : treeSet;
        }
    }

    public final class RealCacheRequest implements CacheRequest {
        public final DiskLruCache.Editor a;
        public final pd2 b;
        public final AnonymousClass1 c;
        public boolean d;

        /* JADX WARN: Type inference failed for: r0v1, types: [okhttp3.Cache$RealCacheRequest$1] */
        public RealCacheRequest(DiskLruCache.Editor editor) {
            this.a = editor;
            pd2 pd2VarD = editor.d(1);
            this.b = pd2VarD;
            this.c = new ow0(pd2VarD) { // from class: okhttp3.Cache.RealCacheRequest.1
                @Override // p.ow0, p.pd2, java.io.Closeable, java.lang.AutoCloseable
                public final void close() {
                    Cache cache = cache;
                    RealCacheRequest realCacheRequest = this;
                    synchronized (cache) {
                        if (realCacheRequest.d) {
                            return;
                        }
                        realCacheRequest.d = true;
                        super.close();
                        this.a.b();
                    }
                }
            };
        }

        @Override // okhttp3.internal.cache.CacheRequest
        public final AnonymousClass1 a() {
            return this.c;
        }

        @Override // okhttp3.internal.cache.CacheRequest
        public final void abort() {
            synchronized (Cache.this) {
                if (this.d) {
                    return;
                }
                this.d = true;
                Util.c(this.b);
                try {
                    this.a.a();
                } catch (IOException unused) {
                }
            }
        }
    }

    public Cache(File file) {
        FileSystem fileSystem = FileSystem.a;
        v03.j(fileSystem, "fileSystem");
        this.c = new DiskLruCache(fileSystem, file, TaskRunner.i);
    }

    public static void i(Response response, Response response2) {
        DiskLruCache.Editor editorI;
        Entry entry = new Entry(response2);
        ResponseBody responseBody = response.o;
        v03.g(responseBody, "null cannot be cast to non-null type okhttp3.Cache.CacheResponseBody");
        DiskLruCache.Snapshot snapshot = ((CacheResponseBody) responseBody).k;
        try {
            editorI = snapshot.l.i(snapshot.c, snapshot.e);
            if (editorI == null) {
                return;
            }
            try {
                entry.c(editorI);
                editorI.b();
            } catch (IOException unused) {
                if (editorI != null) {
                    try {
                        editorI.a();
                    } catch (IOException unused2) {
                    }
                }
            }
        } catch (IOException unused3) {
            editorI = null;
        }
    }

    public final Response b(Request request) {
        v03.j(request, "request");
        e.getClass();
        HttpUrl httpUrl = request.a;
        try {
            DiskLruCache.Snapshot snapshotQ = this.c.q(Companion.a(httpUrl));
            if (snapshotQ == null) {
                return null;
            }
            try {
                Entry entry = new Entry((de2) snapshotQ.k.get(0));
                Headers headers = entry.b;
                String str = entry.c;
                HttpUrl httpUrl2 = entry.a;
                Headers headers2 = entry.g;
                String strA = headers2.a("Content-Type");
                String strA2 = headers2.a("Content-Length");
                Request.Builder builder = new Request.Builder();
                v03.j(httpUrl2, "url");
                builder.a = httpUrl2;
                builder.f(str, null);
                builder.e(headers);
                Request requestB = builder.b();
                Response.Builder builder2 = new Response.Builder();
                builder2.a = requestB;
                Protocol protocol = entry.d;
                v03.j(protocol, "protocol");
                builder2.b = protocol;
                builder2.c = entry.e;
                String str2 = entry.f;
                v03.j(str2, "message");
                builder2.d = str2;
                builder2.c(headers2);
                builder2.g = new CacheResponseBody(snapshotQ, strA, strA2);
                builder2.e = entry.h;
                builder2.k = entry.i;
                builder2.l = entry.j;
                Response responseA = builder2.a();
                if (v03.b(httpUrl2, httpUrl) && v03.b(str, request.b)) {
                    Set<String> setC = Companion.c(responseA.n);
                    if (!(setC instanceof Collection) || !setC.isEmpty()) {
                        for (String str3 : setC) {
                            if (!v03.b(headers.e(str3), request.c.e(str3))) {
                            }
                        }
                    }
                    return responseA;
                }
                ResponseBody responseBody = responseA.o;
                if (responseBody != null) {
                    Util.c(responseBody);
                }
                return null;
            } catch (IOException unused) {
                Util.c(snapshotQ);
                return null;
            }
        } catch (IOException unused2) {
        }
    }

    public final CacheRequest c(Response response) {
        DiskLruCache.Editor editorI;
        Request request = response.c;
        String str = request.b;
        HttpMethod.a.getClass();
        if (HttpMethod.a(str)) {
            try {
                Companion companion = e;
                HttpUrl httpUrl = request.a;
                companion.getClass();
                String strA = Companion.a(httpUrl);
                DiskLruCache diskLruCache = this.c;
                synchronized (diskLruCache) {
                    v03.j(strA, "key");
                    diskLruCache.E();
                    diskLruCache.b();
                    DiskLruCache.J0(strA);
                    DiskLruCache.Entry entry = (DiskLruCache.Entry) diskLruCache.s.get(strA);
                    if (entry != null) {
                        diskLruCache.H0(entry);
                        if (diskLruCache.q <= diskLruCache.m) {
                            diskLruCache.y = false;
                        }
                    }
                }
            } catch (IOException unused) {
            }
            return null;
        }
        if (!v03.b(str, com.spotify.cosmos.cosmos.Request.GET)) {
            return null;
        }
        e.getClass();
        if (Companion.c(response.n).contains("*")) {
            return null;
        }
        Entry entry2 = new Entry(response);
        try {
            editorI = this.c.i(Companion.a(request.a), DiskLruCache.I);
            if (editorI == null) {
                return null;
            }
            try {
                entry2.c(editorI);
                return new RealCacheRequest(editorI);
            } catch (IOException unused2) {
                if (editorI != null) {
                    try {
                        editorI.a();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            }
        } catch (IOException unused4) {
            editorI = null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.c.close();
    }

    @Override // java.io.Flushable
    public final void flush() {
        this.c.flush();
    }

    public static final class Entry {
        public static final String k;
        public static final String l;
        public final HttpUrl a;
        public final Headers b;
        public final String c;
        public final Protocol d;
        public final int e;
        public final String f;
        public final Headers g;
        public final Handshake h;
        public final long i;
        public final long j;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(int i) {
                this();
            }
        }

        static {
            new Companion(0);
            Platform.a.getClass();
            Platform.b.getClass();
            k = "OkHttp-Sent-Millis";
            Platform.b.getClass();
            l = "OkHttp-Received-Millis";
        }

        public Entry(de2 de2Var) {
            HttpUrl httpUrlC;
            TlsVersion tlsVersionA;
            v03.j(de2Var, "rawSource");
            try {
                w02 w02VarD = v03.d(de2Var);
                String strG0 = w02VarD.g0(Long.MAX_VALUE);
                HttpUrl.k.getClass();
                try {
                    httpUrlC = HttpUrl.Companion.c(strG0);
                } catch (IllegalArgumentException unused) {
                    httpUrlC = null;
                }
                if (httpUrlC == null) {
                    IOException iOException = new IOException("Cache corruption for ".concat(strG0));
                    Platform.a.getClass();
                    Platform.b.getClass();
                    Platform.i(5, "cache corruption", iOException);
                    throw iOException;
                }
                this.a = httpUrlC;
                this.c = w02VarD.g0(Long.MAX_VALUE);
                Headers.Builder builder = new Headers.Builder();
                Cache.e.getClass();
                int iB = Companion.b(w02VarD);
                for (int i = 0; i < iB; i++) {
                    builder.b(w02VarD.g0(Long.MAX_VALUE));
                }
                this.b = builder.e();
                StatusLine.Companion companion = StatusLine.d;
                String strG02 = w02VarD.g0(Long.MAX_VALUE);
                companion.getClass();
                StatusLine statusLineA = StatusLine.Companion.a(strG02);
                this.d = statusLineA.a;
                this.e = statusLineA.b;
                this.f = statusLineA.c;
                Headers.Builder builder2 = new Headers.Builder();
                Cache.e.getClass();
                int iB2 = Companion.b(w02VarD);
                for (int i2 = 0; i2 < iB2; i2++) {
                    builder2.b(w02VarD.g0(Long.MAX_VALUE));
                }
                String str = k;
                String strF = builder2.f(str);
                String str2 = l;
                String strF2 = builder2.f(str2);
                builder2.g(str);
                builder2.g(str2);
                this.i = strF != null ? Long.parseLong(strF) : 0L;
                this.j = strF2 != null ? Long.parseLong(strF2) : 0L;
                this.g = builder2.e();
                if (v03.b(this.a.a, WebgateHelper.DEFAULT_WEBGATE_PROTOCOL)) {
                    String strG03 = w02VarD.g0(Long.MAX_VALUE);
                    if (strG03.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + strG03 + '\"');
                    }
                    CipherSuite cipherSuiteB = CipherSuite.b.b(w02VarD.g0(Long.MAX_VALUE));
                    List listA = a(w02VarD);
                    List listA2 = a(w02VarD);
                    if (w02VarD.Q()) {
                        tlsVersionA = TlsVersion.SSL_3_0;
                    } else {
                        TlsVersion.Companion companion2 = TlsVersion.c;
                        String strG04 = w02VarD.g0(Long.MAX_VALUE);
                        companion2.getClass();
                        tlsVersionA = TlsVersion.Companion.a(strG04);
                    }
                    Handshake.e.getClass();
                    this.h = Handshake.Companion.b(tlsVersionA, cipherSuiteB, listA, listA2);
                } else {
                    this.h = null;
                }
                ux2.e(de2Var, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    ux2.e(de2Var, th);
                    throw th2;
                }
            }
        }

        public static List a(w02 w02Var) throws IOException {
            Cache.e.getClass();
            int iB = Companion.b(w02Var);
            if (iB == -1) {
                return EmptyList.c;
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(iB);
                for (int i = 0; i < iB; i++) {
                    String strG0 = w02Var.g0(Long.MAX_VALUE);
                    fo foVar = new fo();
                    ByteString byteString = ByteString.k;
                    ByteString byteStringN = qo.n(strG0);
                    if (byteStringN == null) {
                        throw new IOException("Corrupt certificate in cache entry");
                    }
                    foVar.T0(byteStringN);
                    arrayList.add(certificateFactory.generateCertificate(foVar.C0()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        public static void b(v02 v02Var, List list) throws IOException {
            try {
                v02Var.t0(list.size());
                v02Var.R(10);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    byte[] encoded = ((Certificate) it.next()).getEncoded();
                    ByteString byteString = ByteString.k;
                    v03.i(encoded, "bytes");
                    v02Var.s0(qo.s(encoded).a());
                    v02Var.R(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public final void c(DiskLruCache.Editor editor) {
            HttpUrl httpUrl = this.a;
            Handshake handshake = this.h;
            Headers headers = this.g;
            Headers headers2 = this.b;
            v02 v02VarC = v03.c(editor.d(0));
            try {
                v02VarC.s0(httpUrl.i);
                v02VarC.R(10);
                v02VarC.s0(this.c);
                v02VarC.R(10);
                v02VarC.t0(headers2.size());
                v02VarC.R(10);
                int size = headers2.size();
                for (int i = 0; i < size; i++) {
                    v02VarC.s0(headers2.b(i));
                    v02VarC.s0(": ");
                    v02VarC.s0(headers2.d(i));
                    v02VarC.R(10);
                }
                v02VarC.s0(new StatusLine(this.d, this.e, this.f).toString());
                v02VarC.R(10);
                v02VarC.t0(headers.size() + 2);
                v02VarC.R(10);
                int size2 = headers.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    v02VarC.s0(headers.b(i2));
                    v02VarC.s0(": ");
                    v02VarC.s0(headers.d(i2));
                    v02VarC.R(10);
                }
                v02VarC.s0(k);
                v02VarC.s0(": ");
                v02VarC.t0(this.i);
                v02VarC.R(10);
                v02VarC.s0(l);
                v02VarC.s0(": ");
                v02VarC.t0(this.j);
                v02VarC.R(10);
                if (v03.b(httpUrl.a, WebgateHelper.DEFAULT_WEBGATE_PROTOCOL)) {
                    v02VarC.R(10);
                    v03.h(handshake);
                    v02VarC.s0(handshake.b.a);
                    v02VarC.R(10);
                    b(v02VarC, handshake.a());
                    b(v02VarC, handshake.c);
                    v02VarC.s0(handshake.a.a());
                    v02VarC.R(10);
                }
                ux2.e(v02VarC, null);
            } finally {
            }
        }

        public Entry(Response response) {
            Headers headersE;
            Request request = response.c;
            this.a = request.a;
            Cache.e.getClass();
            Response response2 = response.f96p;
            v03.h(response2);
            Headers headers = response2.c.c;
            Headers headers2 = response.n;
            Set setC = Companion.c(headers2);
            if (setC.isEmpty()) {
                headersE = Util.b;
            } else {
                Headers.Builder builder = new Headers.Builder();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    String strB = headers.b(i);
                    if (setC.contains(strB)) {
                        builder.a(strB, headers.d(i));
                    }
                }
                headersE = builder.e();
            }
            this.b = headersE;
            this.c = request.b;
            this.d = response.e;
            this.e = response.l;
            this.f = response.k;
            this.g = headers2;
            this.h = response.m;
            this.i = response.s;
            this.j = response.t;
        }
    }
}
