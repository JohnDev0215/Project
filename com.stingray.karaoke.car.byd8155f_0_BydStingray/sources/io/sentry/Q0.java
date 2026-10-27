package io.sentry;

import android.os.Bundle;
import android.os.ResultReceiver;
import h8.AbstractC1387d;
import java.io.File;
import k3.C1679y0;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Q0 implements k3.P0 {
    public final /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f15924m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f15925n;
    public final /* synthetic */ Object o;

    public /* synthetic */ Q0(Object obj, Object obj2, Object obj3, Object obj4) {
        this.l = obj;
        this.f15924m = obj2;
        this.f15925n = obj3;
        this.o = obj4;
    }

    public void a() {
        File file = (File) this.o;
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        String str = (String) this.f15924m;
        K k10 = (K) this.l;
        k10.k(enumC1495p1, "Started processing cached files from %s", str);
        AbstractC1487n abstractC1487n = (AbstractC1487n) this.f15925n;
        K k11 = abstractC1487n.f16621b;
        try {
            k11.k(enumC1495p1, "Processing dir. %s", file.getAbsolutePath());
            if (!file.exists()) {
                k11.k(EnumC1495p1.WARNING, "Directory '%s' doesn't exist. No cached events to send.", file.getAbsolutePath());
            } else if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    File[] fileArrListFiles2 = file.listFiles(new C1481l(abstractC1487n, 0));
                    k11.k(enumC1495p1, "Processing %d items from cache dir %s", Integer.valueOf(fileArrListFiles2 != null ? fileArrListFiles2.length : 0), file.getAbsolutePath());
                    int length = fileArrListFiles.length;
                    int i7 = 0;
                    while (true) {
                        if (i7 >= length) {
                            break;
                        }
                        File file2 = fileArrListFiles[i7];
                        if (file2.isFile()) {
                            String absolutePath = file2.getAbsolutePath();
                            Y1 y12 = abstractC1487n.d;
                            if (!y12.contains(absolutePath)) {
                                io.sentry.transport.m mVarD = abstractC1487n.f16620a.d();
                                if (mVarD != null && mVarD.g(EnumC1469h.All)) {
                                    k11.k(EnumC1495p1.INFO, "DirectoryProcessor, rate limiting active.", new Object[0]);
                                    break;
                                } else {
                                    k11.k(EnumC1495p1.DEBUG, "Processing file: %s", absolutePath);
                                    abstractC1487n.b(file2, AbstractC1387d.c(new C1484m(abstractC1487n.c, abstractC1487n.f16621b, absolutePath, y12)));
                                    Thread.sleep(100L);
                                }
                            } else {
                                k11.k(EnumC1495p1.DEBUG, "File '%s' has already been processed so it will not be processed again.", absolutePath);
                            }
                        } else {
                            k11.k(EnumC1495p1.DEBUG, "File %s is not a File.", file2.getAbsolutePath());
                        }
                        i7++;
                    }
                } else {
                    k11.k(EnumC1495p1.ERROR, "Cache dir %s is null.", file.getAbsolutePath());
                }
            } else {
                k11.k(EnumC1495p1.ERROR, "Cache dir %s is not a directory.", file.getAbsolutePath());
            }
        } catch (Throwable th) {
            k11.p(EnumC1495p1.ERROR, th, "Failed processing '%s'", file.getAbsolutePath());
        }
        k10.k(EnumC1495p1.DEBUG, "Finished processing cached files from %s", str);
    }

    @Override // k3.P0
    public void d(C1679y0 c1679y0) {
        k3.Q0 q02 = (k3.Q0) this.l;
        Bundle bundle = (Bundle) this.f15925n;
        if (bundle == null) {
            q02.getClass();
            bundle = Bundle.EMPTY;
        }
        h5.y yVarL = q02.f17843f.l(c1679y0, (k3.y1) this.f15924m, bundle);
        ResultReceiver resultReceiver = (ResultReceiver) this.o;
        if (resultReceiver != null) {
            ((h5.v) yVarL).b(new A2.m(yVarL, 24, resultReceiver), h5.r.l);
        }
    }
}
