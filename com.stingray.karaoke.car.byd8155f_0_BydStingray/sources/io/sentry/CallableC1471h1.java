package io.sentry;

import h8.AbstractC1386c;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: io.sentry.h1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class CallableC1471h1 implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16546a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ long f16547b;
    public final /* synthetic */ T c;
    public final /* synthetic */ Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f16548e;

    public /* synthetic */ CallableC1471h1(C1422a c1422a, long j3, T t10, K k10) {
        this.d = c1422a;
        this.f16547b = j3;
        this.c = t10;
        this.f16548e = k10;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() throws io.sentry.exception.b {
        ByteArrayOutputStream byteArrayOutputStream;
        Object obj = this.f16548e;
        long j3 = this.f16547b;
        Object obj2 = this.d;
        T t10 = this.c;
        switch (this.f16546a) {
            case 0:
                C1422a c1422a = (C1422a) obj2;
                byte[] byteArray = c1422a.f15998a;
                String str = c1422a.c;
                if (byteArray == null) {
                    io.sentry.protocol.F f10 = c1422a.f15999b;
                    if (f10 != null) {
                        Charset charset = io.sentry.util.b.f16916a;
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        } catch (Throwable th) {
                            ((K) obj).s(EnumC1495p1.ERROR, "Could not serialize serializable", th);
                            byteArray = null;
                        }
                        try {
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, io.sentry.util.b.f16916a));
                            try {
                                t10.f(f10, bufferedWriter);
                                byteArray = byteArrayOutputStream.toByteArray();
                                bufferedWriter.close();
                                byteArrayOutputStream.close();
                                if (byteArray != null) {
                                    long length = byteArray.length;
                                    if (length > j3) {
                                        throw new io.sentry.exception.b(String.format("Dropping attachment with filename '%s', because the size of the passed bytes with %d bytes is bigger than the maximum allowed attachment size of %d bytes.", str, Long.valueOf(length), Long.valueOf(j3)));
                                    }
                                }
                            } finally {
                                try {
                                    break;
                                } catch (Throwable th2) {
                                }
                            }
                        } finally {
                            try {
                                break;
                            } catch (Throwable th3) {
                            }
                        }
                    }
                    throw new io.sentry.exception.b(android.hardware.bydauto.ac.tsimpl.a.i("Couldn't attach the attachment ", str, ".\nPlease check that either bytes, serializable or a path is set."));
                }
                long length2 = byteArray.length;
                if (length2 > j3) {
                    throw new io.sentry.exception.b(String.format("Dropping attachment with filename '%s', because the size of the passed bytes with %d bytes is bigger than the maximum allowed attachment size of %d bytes.", str, Long.valueOf(length2), Long.valueOf(j3)));
                }
                return byteArray;
            default:
                File file = (File) obj2;
                if (!file.exists()) {
                    throw new io.sentry.exception.b(android.hardware.bydauto.ac.tsimpl.a.i("Dropping profiling trace data, because the file '", file.getName(), "' doesn't exists"));
                }
                try {
                    String str2 = new String(io.sentry.vendor.a.a(AbstractC1386c.m(file.getPath(), j3)), "US-ASCII");
                    if (str2.isEmpty()) {
                        throw new io.sentry.exception.b("Profiling trace file is empty");
                    }
                    G0 g02 = (G0) obj;
                    g02.f15849M = str2;
                    try {
                        g02.f15860w = (List) g02.f15851m.call();
                        break;
                    } catch (Throwable unused) {
                    }
                    try {
                        try {
                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                            try {
                                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream2, C1474i1.d));
                                try {
                                    t10.f(g02, bufferedWriter2);
                                    byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                                    bufferedWriter2.close();
                                    byteArrayOutputStream2.close();
                                    return byteArray2;
                                } finally {
                                    try {
                                        break;
                                    } catch (Throwable th4) {
                                    }
                                }
                            } catch (Throwable th5) {
                                try {
                                    byteArrayOutputStream2.close();
                                    break;
                                } catch (Throwable th6) {
                                    th5.addSuppressed(th6);
                                }
                                throw th5;
                            }
                        } finally {
                            file.delete();
                        }
                    } catch (IOException e10) {
                        throw new io.sentry.exception.b("Failed to serialize profiling trace data\n" + e10.getMessage());
                    }
                } catch (UnsupportedEncodingException e11) {
                    throw new AssertionError(e11);
                }
        }
    }

    public /* synthetic */ CallableC1471h1(File file, long j3, G0 g02, T t10) {
        this.d = file;
        this.f16547b = j3;
        this.f16548e = g02;
        this.c = t10;
    }
}
