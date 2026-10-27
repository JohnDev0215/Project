package io.sentry;

import android.graphics.Bitmap;
import android.net.Uri;
import h8.AbstractC1387d;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: io.sentry.e1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class CallableC1462e1 implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16527a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f16528b;
    public final /* synthetic */ Object c;

    public /* synthetic */ CallableC1462e1(Object obj, int i7, Object obj2) {
        this.f16527a = i7;
        this.f16528b = obj;
        this.c = obj2;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        BufferedWriter bufferedWriter;
        switch (this.f16527a) {
            case 0:
                T t10 = (T) this.f16528b;
                R1 r12 = (R1) this.c;
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream2, C1474i1.d));
                    try {
                        t10.f(r12, bufferedWriter2);
                        byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        bufferedWriter2.close();
                        byteArrayOutputStream2.close();
                        return byteArray;
                    } finally {
                        try {
                            bufferedWriter2.close();
                            break;
                        } catch (Throwable th) {
                            th.addSuppressed(th);
                        }
                    }
                } finally {
                    try {
                        byteArrayOutputStream2.close();
                        break;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            case 1:
                T t11 = (T) this.f16528b;
                X0 x02 = (X0) this.c;
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, C1474i1.d));
                    try {
                        t11.f(x02, bufferedWriter);
                        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
                        bufferedWriter.close();
                        byteArrayOutputStream.close();
                        return byteArray2;
                    } finally {
                        try {
                            bufferedWriter.close();
                            break;
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                } finally {
                    try {
                        break;
                    } catch (Throwable th4) {
                    }
                }
            case 2:
                T t12 = (T) this.f16528b;
                io.sentry.clientreport.b bVar = (io.sentry.clientreport.b) this.c;
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, C1474i1.d));
                    try {
                        t12.f(bVar, bufferedWriter);
                        byte[] byteArray3 = byteArrayOutputStream.toByteArray();
                        bufferedWriter.close();
                        byteArrayOutputStream.close();
                        return byteArray3;
                    } finally {
                        try {
                            break;
                        } catch (Throwable th32) {
                        }
                    }
                } finally {
                    try {
                        break;
                    } catch (Throwable th42) {
                    }
                }
            case 3:
                k2.i iVar = (k2.i) this.f16528b;
                iVar.getClass();
                byte[] bArr = (byte[]) this.c;
                return AbstractC1387d.e(bArr.length, iVar.f17662n, bArr);
            default:
                Uri uri = (Uri) this.c;
                k2.i iVar2 = (k2.i) this.f16528b;
                k2.h hVarQ = iVar2.f17661m.q();
                int i7 = iVar2.f17662n;
                try {
                    k2.n nVar = (k2.n) hVarQ;
                    nVar.g(new k2.m(uri, 0L, 1, null, Collections.emptyMap(), 0L, -1L, null, 0));
                    byte[] bArrCopyOf = new byte[1024];
                    int iN = 0;
                    int i10 = 0;
                    while (iN != -1) {
                        if (i10 == bArrCopyOf.length) {
                            bArrCopyOf = Arrays.copyOf(bArrCopyOf, bArrCopyOf.length * 2);
                        }
                        iN = nVar.n(bArrCopyOf, i10, bArrCopyOf.length - i10);
                        if (iN != -1) {
                            i10 += iN;
                        }
                    }
                    byte[] bArrCopyOf2 = Arrays.copyOf(bArrCopyOf, i10);
                    Bitmap bitmapE = AbstractC1387d.e(bArrCopyOf2.length, i7, bArrCopyOf2);
                    nVar.close();
                    return bitmapE;
                } catch (Throwable th5) {
                    ((k2.n) hVarQ).close();
                    throw th5;
                }
        }
    }
}
