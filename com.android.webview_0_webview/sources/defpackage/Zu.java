package defpackage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Zu extends ThreadPoolExecutor {
    public static final int a;
    public static final int b;
    public static final int c;
    public static final ThreadFactory d;
    public static final BlockingQueue e;

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        a = iAvailableProcessors;
        b = Math.max(2, Math.min(iAvailableProcessors - 1, 4));
        c = (a * 2) + 1;
        d = new Yu();
        e = new ArrayBlockingQueue(128);
    }

    public Zu() {
        super(b, c, 30L, TimeUnit.SECONDS, (BlockingQueue<Runnable>) e, d);
        allowCoreThreadTimeOut(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0060  */
    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute(java.lang.Runnable r10) {
        /*
            r9 = this;
            super.execute(r10)     // Catch: java.util.concurrent.RejectedExecutionException -> L4
            return
        L4:
            r10 = move-exception
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.concurrent.BlockingQueue r1 = r9.getQueue()
            r2 = 0
            java.lang.Runnable[] r2 = new java.lang.Runnable[r2]
            java.lang.Object[] r1 = r1.toArray(r2)
            java.lang.Runnable[] r1 = (java.lang.Runnable[]) r1
            int r2 = r1.length
            r3 = 0
        L19:
            if (r3 >= r2) goto L6c
            r4 = r1[r3]
            java.lang.Class r5 = r4.getClass()
            r6 = 1
            java.lang.Class<Ru> r7 = defpackage.Ru.class
            if (r5 != r7) goto L30
            Ru r4 = (defpackage.Ru) r4     // Catch: java.lang.Throwable -> L4a
            Su r4 = r4.s     // Catch: java.lang.Throwable -> L4a
            java.lang.Class r4 = r4.getClass()     // Catch: java.lang.Throwable -> L4a
        L2e:
            r5 = r4
            goto L4b
        L30:
            java.lang.Class r7 = r5.getEnclosingClass()     // Catch: java.lang.Throwable -> L4a
            java.lang.Class<android.os.AsyncTask> r8 = android.os.AsyncTask.class
            if (r7 != r8) goto L4b
            java.lang.String r7 = "this$0"
            java.lang.reflect.Field r7 = r5.getDeclaredField(r7)     // Catch: java.lang.Throwable -> L4a
            r7.setAccessible(r6)     // Catch: java.lang.Throwable -> L4a
            java.lang.Object r4 = r7.get(r4)     // Catch: java.lang.Throwable -> L4a
            java.lang.Class r4 = r4.getClass()     // Catch: java.lang.Throwable -> L4a
            goto L2e
        L4a:
        L4b:
            java.lang.String r4 = r5.getName()
            boolean r5 = r0.containsKey(r4)
            if (r5 == 0) goto L60
            java.lang.Object r5 = r0.get(r4)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            goto L61
        L60:
            r5 = 0
        L61:
            int r5 = r5 + r6
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r0.put(r4, r5)
            int r3 = r3 + 1
            goto L19
        L6c:
            java.util.concurrent.RejectedExecutionException r1 = new java.util.concurrent.RejectedExecutionException
            java.lang.String r2 = "Prominent classes in AsyncTask: "
            java.lang.StringBuilder r2 = defpackage.Z7.f(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L81:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto La8
            java.lang.Object r4 = r0.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r4.getValue()
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            r6 = 32
            if (r5 <= r6) goto L81
            java.lang.Object r4 = r4.getKey()
            java.lang.String r4 = (java.lang.String) r4
            r3.append(r4)
            r3.append(r6)
            goto L81
        La8:
            int r0 = r3.length()
            if (r0 != 0) goto Lb1
            java.lang.String r0 = "NO CLASSES FOUND"
            goto Lb5
        Lb1:
            java.lang.String r0 = r3.toString()
        Lb5:
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0, r10)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Zu.execute(java.lang.Runnable):void");
    }
}
