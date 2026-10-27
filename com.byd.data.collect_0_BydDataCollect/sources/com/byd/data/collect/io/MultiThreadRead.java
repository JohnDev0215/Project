package com.byd.data.collect.io;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public class MultiThreadRead implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static RandomAccessFile f3153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile int f3154c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private CountDownLatch f3155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private File f3156e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3157f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private StringCallback f3158g;

    /* JADX WARN: Code restructure failed: missing block: B:17:0x004c, code lost:
    
        return;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r5 = this;
        L0:
            int r0 = r5.f3157f
            byte[] r0 = new byte[r0]
            monitor-enter(r5)
            int r1 = r5.f3154c     // Catch: java.lang.Throwable -> L9c
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L9c
            java.io.File r3 = r5.f3156e     // Catch: java.lang.Throwable -> L9c
            long r3 = r3.length()     // Catch: java.lang.Throwable -> L9c
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 < 0) goto L1b
            java.util.concurrent.CountDownLatch r0 = r5.f3155d     // Catch: java.lang.Throwable -> L9c
            if (r0 == 0) goto L19
            r0.countDown()     // Catch: java.lang.Throwable -> L9c
        L19:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L9c
            goto L71
        L1b:
            java.lang.String r1 = "MultiThreadRead"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            r2.<init>()     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            java.lang.String r3 = "read cursor begin offset = "
            r2.append(r3)     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            int r3 = r5.f3154c     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            r2.append(r3)     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            java.lang.String r2 = r2.toString()     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            d.a.a.f.b.b.a.k(r1, r2)     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            java.io.RandomAccessFile r1 = com.byd.data.collect.io.MultiThreadRead.f3153b     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            int r2 = r5.f3154c     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            long r2 = (long) r2     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            r1.seek(r2)     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            java.io.RandomAccessFile r1 = com.byd.data.collect.io.MultiThreadRead.f3153b     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            int r1 = r1.read(r0)     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            r2 = -1
            if (r1 != r2) goto L4d
            java.util.concurrent.CountDownLatch r0 = r5.f3155d     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            if (r0 == 0) goto L4b
            r0.countDown()     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
        L4b:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L9c
            return
        L4d:
            int r2 = r5.f3154c     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            int r2 = r2 + r1
            r5.f3154c = r2     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            com.byd.data.collect.io.StringCallback r2 = r5.f3158g     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            r2.d(r0, r1)     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L9c
            goto L5c
        L58:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L9c
        L5c:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L9c
            int r0 = r5.f3154c
            long r0 = (long) r0
            java.io.File r2 = r5.f3156e
            long r2 = r2.length()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L72
            java.util.concurrent.CountDownLatch r0 = r5.f3155d
            if (r0 == 0) goto L71
            r0.countDown()
        L71:
            return
        L72:
            java.lang.String r0 = "MultiThreadRead"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "threadId = "
            r1.append(r2)
            java.lang.Thread r2 = java.lang.Thread.currentThread()
            long r2 = r2.getId()
            r1.append(r2)
            java.lang.String r2 = "， offset ="
            r1.append(r2)
            int r2 = r5.f3154c
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            d.a.a.f.b.b.a.k(r0, r1)
            goto L0
        L9c:
            r0 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L9c
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.data.collect.io.MultiThreadRead.run():void");
    }
}
