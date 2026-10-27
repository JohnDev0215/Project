package defpackage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ChromeThreadPoolExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public final class ts extends ThreadPoolExecutor {
    public static final int e;
    public static final int f;
    public static final a g;
    public static final ArrayBlockingQueue h;

    /* JADX INFO: compiled from: ChromeThreadPoolExecutor.java */
    public class a implements ThreadFactory {
        public final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(new wv(11, runnable), "CrAsyncTask #" + this.a.getAndIncrement());
        }
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        e = Math.max(2, Math.min(iAvailableProcessors - 1, 4));
        f = (iAvailableProcessors * 2) + 1;
        g = new a();
        h = new ArrayBlockingQueue(128);
    }

    public ts() {
        super(e, f, 30L, TimeUnit.SECONDS, h, g);
        allowCoreThreadTimeOut(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0060  */
    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void execute(java.lang.Runnable r10) {
        /*
            r9 = this;
            super.execute(r10)     // Catch: java.util.concurrent.RejectedExecutionException -> L4
            return
        L4:
            r10 = move-exception
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.concurrent.BlockingQueue r9 = r9.getQueue()
            r1 = 0
            java.lang.Runnable[] r2 = new java.lang.Runnable[r1]
            java.lang.Object[] r9 = r9.toArray(r2)
            java.lang.Runnable[] r9 = (java.lang.Runnable[]) r9
            int r2 = r9.length
            r3 = r1
        L19:
            if (r3 >= r2) goto L6c
            r4 = r9[r3]
            java.lang.Class r5 = r4.getClass()
            r6 = 1
            java.lang.Class<i9$b> r7 = i9.b.class
            if (r5 != r7) goto L30
            i9$b r4 = (i9.b) r4     // Catch: java.lang.Throwable -> L4b
            i9 r4 = defpackage.i9.this     // Catch: java.lang.Throwable -> L4b
            java.lang.Class r4 = r4.getClass()     // Catch: java.lang.Throwable -> L4b
        L2e:
            r5 = r4
            goto L4b
        L30:
            java.lang.Class r7 = r5.getEnclosingClass()     // Catch: java.lang.Throwable -> L4b
            java.lang.Class<android.os.AsyncTask> r8 = android.os.AsyncTask.class
            if (r7 != r8) goto L4b
            java.lang.String r7 = "this$0"
            java.lang.reflect.Field r7 = r5.getDeclaredField(r7)     // Catch: java.lang.Throwable -> L4b
            r7.setAccessible(r6)     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r4 = r7.get(r4)     // Catch: java.lang.Throwable -> L4b
            java.lang.Class r4 = r4.getClass()     // Catch: java.lang.Throwable -> L4b
            goto L2e
        L4b:
            java.lang.String r4 = r5.getName()
            boolean r5 = r0.containsKey(r4)
            if (r5 == 0) goto L60
            java.lang.Object r5 = r0.get(r4)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            goto L61
        L60:
            r5 = r1
        L61:
            int r5 = r5 + r6
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r0.put(r4, r5)
            int r3 = r3 + 1
            goto L19
        L6c:
            java.util.concurrent.RejectedExecutionException r9 = new java.util.concurrent.RejectedExecutionException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Prominent classes in AsyncTask: "
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L82:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto La9
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r5 = 32
            if (r4 <= r5) goto L82
            java.lang.Object r3 = r3.getKey()
            java.lang.String r3 = (java.lang.String) r3
            r2.append(r3)
            r2.append(r5)
            goto L82
        La9:
            int r0 = r2.length()
            if (r0 != 0) goto Lb2
            java.lang.String r0 = "NO CLASSES FOUND"
            goto Lb6
        Lb2:
            java.lang.String r0 = r2.toString()
        Lb6:
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r9.<init>(r0, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ts.execute(java.lang.Runnable):void");
    }
}
