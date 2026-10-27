package io.sentry;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: io.sentry.i1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1474i1 {
    public static final Charset d = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1477j1 f16555a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Callable f16556b;
    public byte[] c;

    public C1474i1(C1477j1 c1477j1, byte[] bArr) {
        this.f16555a = c1477j1;
        this.c = bArr;
        this.f16556b = null;
    }

    public static C1474i1 a(T t10, io.sentry.clientreport.b bVar) {
        h6.e.q(t10, "ISerializer is required.");
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(new CallableC1462e1(t10, 2, bVar), 24);
        return new C1474i1(new C1477j1(EnumC1492o1.resolve(bVar), new CallableC1465f1(cVar, 4), "application/json", (String) null, (String) null), new CallableC1465f1(cVar, 5));
    }

    public static C1474i1 b(T t10, R1 r12) {
        h6.e.q(t10, "ISerializer is required.");
        h6.e.q(r12, "Session is required.");
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(new CallableC1462e1(t10, 0, r12), 24);
        return new C1474i1(new C1477j1(EnumC1492o1.Session, new CallableC1465f1(cVar, 7), "application/json", (String) null, (String) null), new CallableC1465f1(cVar, 9));
    }

    public static byte[] f(LinkedHashMap linkedHashMap) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write((byte) (linkedHashMap.size() | 128));
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                byte[] bytes = ((String) entry.getKey()).getBytes(d);
                int length = bytes.length;
                byteArrayOutputStream.write(-39);
                byteArrayOutputStream.write((byte) length);
                byteArrayOutputStream.write(bytes);
                byte[] bArr = (byte[]) entry.getValue();
                int length2 = bArr.length;
                byteArrayOutputStream.write(-58);
                byteArrayOutputStream.write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(length2).array());
                byteArrayOutputStream.write(bArr);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final io.sentry.clientreport.b c(T t10) throws IOException {
        C1477j1 c1477j1 = this.f16555a;
        if (c1477j1 == null || c1477j1.f16585n != EnumC1492o1.ClientReport) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(d()), d));
        try {
            io.sentry.clientreport.b bVar = (io.sentry.clientreport.b) t10.a(bufferedReader, io.sentry.clientreport.b.class);
            bufferedReader.close();
            return bVar;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final byte[] d() {
        Callable callable;
        if (this.c == null && (callable = this.f16556b) != null) {
            this.c = (byte[]) callable.call();
        }
        return this.c;
    }

    public final io.sentry.protocol.A e(T t10) throws IOException {
        C1477j1 c1477j1 = this.f16555a;
        if (c1477j1 == null || c1477j1.f16585n != EnumC1492o1.Transaction) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(d()), d));
        try {
            io.sentry.protocol.A a10 = (io.sentry.protocol.A) t10.a(bufferedReader, io.sentry.protocol.A.class);
            bufferedReader.close();
            return a10;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public C1474i1(C1477j1 c1477j1, Callable callable) {
        this.f16555a = c1477j1;
        this.f16556b = callable;
        this.c = null;
    }
}
