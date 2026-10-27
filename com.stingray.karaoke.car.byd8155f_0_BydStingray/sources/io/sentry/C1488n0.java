package io.sentry;

import io.sentry.protocol.C1496a;
import io.sentry.protocol.C1497b;
import io.sentry.protocol.C1498c;
import io.sentry.protocol.C1499d;
import io.sentry.protocol.C1501f;
import io.sentry.protocol.C1502g;
import io.sentry.protocol.DebugImage;
import io.sentry.protocol.EnumC1500e;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: io.sentry.n0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1488n0 implements T {
    public static final Charset c = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F1 f16622a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f16623b;

    public C1488n0(F1 f12) {
        this.f16622a = f12;
        HashMap map = new HashMap();
        this.f16623b = map;
        map.put(C1496a.class, new io.sentry.clientreport.a(4));
        map.put(C1457d.class, new C1454c(0));
        map.put(C1497b.class, new io.sentry.clientreport.a(5));
        map.put(C1498c.class, new io.sentry.clientreport.a(6));
        map.put(DebugImage.class, new io.sentry.clientreport.a(7));
        map.put(C1499d.class, new io.sentry.clientreport.a(8));
        map.put(C1501f.class, new io.sentry.clientreport.a(9));
        map.put(EnumC1500e.class, new io.sentry.clientreport.a(10));
        map.put(io.sentry.protocol.h.class, new io.sentry.clientreport.a(12));
        map.put(io.sentry.protocol.i.class, new io.sentry.clientreport.a(13));
        map.put(io.sentry.protocol.j.class, new io.sentry.clientreport.a(14));
        map.put(io.sentry.protocol.k.class, new io.sentry.clientreport.a(15));
        map.put(io.sentry.protocol.l.class, new io.sentry.clientreport.a(16));
        map.put(io.sentry.protocol.m.class, new io.sentry.clientreport.a(17));
        map.put(G0.class, new C1454c(1));
        map.put(H0.class, new C1454c(2));
        map.put(io.sentry.profilemeasurements.a.class, new io.sentry.clientreport.a(2));
        map.put(io.sentry.profilemeasurements.b.class, new io.sentry.clientreport.a(3));
        map.put(io.sentry.protocol.n.class, new io.sentry.clientreport.a(18));
        map.put(L0.class, new C1454c(3));
        map.put(io.sentry.rrweb.a.class, new io.sentry.protocol.D(3));
        map.put(io.sentry.rrweb.c.class, new io.sentry.protocol.D(4));
        map.put(io.sentry.rrweb.g.class, new io.sentry.protocol.D(6));
        map.put(io.sentry.rrweb.i.class, new io.sentry.protocol.D(8));
        map.put(io.sentry.rrweb.j.class, new io.sentry.protocol.D(10));
        map.put(io.sentry.rrweb.k.class, new io.sentry.protocol.D(11));
        map.put(io.sentry.rrweb.l.class, new io.sentry.protocol.D(12));
        map.put(io.sentry.protocol.q.class, new io.sentry.clientreport.a(19));
        map.put(io.sentry.protocol.r.class, new io.sentry.clientreport.a(20));
        map.put(C1459d1.class, new C1454c(5));
        map.put(C1477j1.class, new C1454c(6));
        map.put(C1480k1.class, new C1454c(7));
        map.put(io.sentry.protocol.s.class, new io.sentry.clientreport.a(21));
        map.put(EnumC1492o1.class, new C1454c(8));
        map.put(EnumC1495p1.class, new C1454c(9));
        map.put(C1505q1.class, new C1454c(10));
        map.put(io.sentry.protocol.u.class, new io.sentry.clientreport.a(23));
        map.put(io.sentry.protocol.v.class, new io.sentry.clientreport.a(24));
        map.put(H1.class, new C1454c(11));
        map.put(io.sentry.protocol.w.class, new io.sentry.clientreport.a(25));
        map.put(io.sentry.protocol.x.class, new io.sentry.clientreport.a(26));
        map.put(io.sentry.protocol.y.class, new io.sentry.clientreport.a(27));
        map.put(V0.class, new C1454c(4));
        map.put(io.sentry.protocol.z.class, new io.sentry.clientreport.a(28));
        map.put(io.sentry.protocol.A.class, new io.sentry.clientreport.a(29));
        map.put(R1.class, new C1454c(13));
        map.put(T1.class, new C1454c(14));
        map.put(V1.class, new C1454c(15));
        map.put(W1.class, new C1454c(16));
        map.put(io.sentry.protocol.E.class, new io.sentry.protocol.D(0));
        map.put(C1502g.class, new io.sentry.clientreport.a(11));
        map.put(f2.class, new C1454c(19));
        map.put(io.sentry.clientreport.b.class, new io.sentry.clientreport.a(0));
        map.put(io.sentry.protocol.G.class, new io.sentry.protocol.D(2));
        map.put(io.sentry.protocol.F.class, new io.sentry.protocol.D(1));
    }

    @Override // io.sentry.T
    public final Object a(Reader reader, Class cls) {
        F1 f12 = this.f16622a;
        try {
            C1482l0 c1482l0 = new C1482l0(reader);
            try {
                InterfaceC1452b0 interfaceC1452b0 = (InterfaceC1452b0) this.f16623b.get(cls);
                if (interfaceC1452b0 != null) {
                    Object objCast = cls.cast(interfaceC1452b0.a(c1482l0, f12.getLogger()));
                    c1482l0.close();
                    return objCast;
                }
                if (!cls.isArray() && !Collection.class.isAssignableFrom(cls) && !String.class.isAssignableFrom(cls) && !Map.class.isAssignableFrom(cls)) {
                    c1482l0.close();
                    return null;
                }
                Object objN = c1482l0.N();
                c1482l0.close();
                return objN;
            } finally {
            }
        } catch (Exception e10) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Error when deserializing", e10);
            return null;
        }
    }

    @Override // io.sentry.T
    public final String b(ConcurrentHashMap concurrentHashMap) {
        return g(concurrentHashMap, false);
    }

    @Override // io.sentry.T
    public final C1489n1 c(BufferedInputStream bufferedInputStream) {
        F1 f12 = this.f16622a;
        try {
            return f12.getEnvelopeReader().a(bufferedInputStream);
        } catch (IOException e10) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Error deserializing envelope.", e10);
            return null;
        }
    }

    @Override // io.sentry.T
    public final Object d(BufferedReader bufferedReader, Class cls, C1454c c1454c) {
        F1 f12 = this.f16622a;
        try {
            C1482l0 c1482l0 = new C1482l0(bufferedReader);
            try {
                if (!Collection.class.isAssignableFrom(cls)) {
                    Object objN = c1482l0.N();
                    c1482l0.close();
                    return objN;
                }
                if (c1454c == null) {
                    Object objN2 = c1482l0.N();
                    c1482l0.close();
                    return objN2;
                }
                ArrayList arrayListC = c1482l0.C(f12.getLogger(), c1454c);
                c1482l0.close();
                return arrayListC;
            } finally {
            }
        } catch (Throwable th) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Error when deserializing", th);
            return null;
        }
    }

    @Override // io.sentry.T
    public final void e(C1489n1 c1489n1, OutputStream outputStream) throws IOException {
        F1 f12 = this.f16622a;
        h6.e.q(c1489n1, "The SentryEnvelope object is required.");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(outputStream), c));
        try {
            ((C1459d1) c1489n1.f16624m).serialize(new C1489n1(bufferedWriter, f12.getMaxDepth()), f12.getLogger());
            bufferedWriter.write("\n");
            for (C1474i1 c1474i1 : (Collection) c1489n1.f16625n) {
                try {
                    byte[] bArrD = c1474i1.d();
                    c1474i1.f16555a.serialize(new C1489n1(bufferedWriter, f12.getMaxDepth()), f12.getLogger());
                    bufferedWriter.write("\n");
                    bufferedWriter.flush();
                    outputStream.write(bArrD);
                    bufferedWriter.write("\n");
                } catch (Exception e10) {
                    f12.getLogger().s(EnumC1495p1.ERROR, "Failed to create envelope item. Dropping it.", e10);
                }
            }
        } finally {
            bufferedWriter.flush();
        }
    }

    @Override // io.sentry.T
    public final void f(Object obj, BufferedWriter bufferedWriter) throws IOException {
        h6.e.q(obj, "The entity is required.");
        F1 f12 = this.f16622a;
        K logger = f12.getLogger();
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        if (logger.m(enumC1495p1)) {
            f12.getLogger().k(enumC1495p1, "Serializing object: %s", g(obj, f12.isEnablePrettySerializationOutput()));
        }
        C1489n1 c1489n1 = new C1489n1(bufferedWriter, f12.getMaxDepth());
        ((e7.f) c1489n1.f16625n).o(c1489n1, f12.getLogger(), obj);
        bufferedWriter.flush();
    }

    public final String g(Object obj, boolean z6) throws IOException {
        StringWriter stringWriter = new StringWriter();
        F1 f12 = this.f16622a;
        C1489n1 c1489n1 = new C1489n1(stringWriter, f12.getMaxDepth());
        if (z6) {
            io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) c1489n1.f16624m;
            cVar.getClass();
            cVar.o = "\t";
            cVar.f16975p = ": ";
        }
        ((e7.f) c1489n1.f16625n).o(c1489n1, f12.getLogger(), obj);
        return stringWriter.toString();
    }
}
