package android.app;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyInvalidatedCache<Query, Result> {
    private static final boolean DEBUG = false;
    private static final long NONCE_DISABLED = -1;
    private static final long NONCE_UNSET = 0;
    private static final String TAG = "PropertyInvalidatedCache";
    private static final boolean VERIFY = false;
    private final LinkedHashMap<Query, Result> mCache;
    private final int mMaxEntries;
    private volatile SystemProperties.Handle mPropertyHandle;
    private final String mPropertyName;
    private static final HashMap<String, Long> sInvalidates = new HashMap<>();
    private static boolean sEnabled = true;
    private static final Object sCorkLock = new Object();
    private static final HashMap<String, Integer> sCorks = new HashMap<>();
    private static final WeakHashMap<PropertyInvalidatedCache, Void> sCaches = new WeakHashMap<>();
    private long mHits = 0;
    private long mMisses = 0;
    private final Object mLock = new Object();
    private long mLastSeenNonce = 0;
    private boolean mDisabled = false;

    protected Result maybeCheckConsistency(Query query, Result result) {
        return result;
    }

    protected abstract Result recompute(Query query);

    protected Result refresh(Result result, Query query) {
        return result;
    }

    public PropertyInvalidatedCache(final int i, String str) {
        this.mPropertyName = str;
        this.mMaxEntries = i;
        this.mCache = new LinkedHashMap<Query, Result>(2, 0.75f, true) { // from class: android.app.PropertyInvalidatedCache.1
            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry entry) {
                return size() > i;
            }
        };
        synchronized (sCorkLock) {
            sCaches.put(this, null);
            sInvalidates.put(str, 0L);
        }
    }

    public final void clear() {
        synchronized (this.mLock) {
            this.mCache.clear();
        }
    }

    protected boolean debugCompareQueryResults(Result result, Result result2) {
        if (result2 != null) {
            return Objects.equals(result, result2);
        }
        return true;
    }

    private long getCurrentNonce() {
        SystemProperties.Handle handleFind = this.mPropertyHandle;
        if (handleFind == null) {
            handleFind = SystemProperties.find(this.mPropertyName);
            if (handleFind == null) {
                return 0L;
            }
            this.mPropertyHandle = handleFind;
        }
        return handleFind.getLong(0L);
    }

    public final void disableLocal() {
        synchronized (this.mLock) {
            this.mDisabled = true;
            this.mCache.clear();
        }
    }

    public final boolean isDisabledLocal() {
        return this.mDisabled || !sEnabled;
    }

    public Result query(Query query) {
        Result result;
        long currentNonce = !isDisabledLocal() ? getCurrentNonce() : -1L;
        while (currentNonce != -1 && currentNonce != 0) {
            synchronized (this.mLock) {
                if (currentNonce == this.mLastSeenNonce) {
                    result = this.mCache.get(query);
                    if (result != null) {
                        this.mHits++;
                    }
                } else {
                    this.mCache.clear();
                    this.mLastSeenNonce = currentNonce;
                    result = null;
                }
            }
            if (result != null) {
                Result resultRefresh = refresh(result, query);
                if (resultRefresh != result) {
                    long currentNonce2 = getCurrentNonce();
                    if (currentNonce == currentNonce2) {
                        synchronized (this.mLock) {
                            if (currentNonce == this.mLastSeenNonce) {
                                if (resultRefresh == null) {
                                    this.mCache.remove(query);
                                } else {
                                    this.mCache.put(query, resultRefresh);
                                }
                            }
                        }
                        return maybeCheckConsistency(query, resultRefresh);
                    }
                    currentNonce = currentNonce2;
                } else {
                    return maybeCheckConsistency(query, result);
                }
            } else {
                Result resultRecompute = recompute(query);
                synchronized (this.mLock) {
                    if (this.mLastSeenNonce == currentNonce && resultRecompute != null) {
                        this.mCache.put(query, resultRecompute);
                    }
                    this.mMisses++;
                }
                return maybeCheckConsistency(query, resultRecompute);
            }
        }
        return recompute(query);
    }

    private static final class NoPreloadHolder {
        private static final AtomicLong sNextNonce = new AtomicLong(new Random().nextLong());

        private NoPreloadHolder() {
        }

        public static long next() {
            return sNextNonce.getAndIncrement();
        }
    }

    public final void disableSystemWide() {
        disableSystemWide(this.mPropertyName);
    }

    public static void disableSystemWide(String str) {
        if (sEnabled) {
            SystemProperties.set(str, Long.toString(-1L));
        }
    }

    public final void invalidateCache() {
        invalidateCache(this.mPropertyName);
    }

    public static void invalidateCache(String str) {
        if (sEnabled) {
            synchronized (sCorkLock) {
                Integer num = sCorks.get(str);
                if (num == null || num.intValue() <= 0) {
                    invalidateCacheLocked(str);
                }
            }
        }
    }

    private static void invalidateCacheLocked(String str) {
        if (SystemProperties.getLong(str, 0L) == -1) {
            return;
        }
        while (true) {
            long next = NoPreloadHolder.next();
            if (next != 0 && next != -1) {
                SystemProperties.set(str, Long.toString(next));
                HashMap<String, Long> map = sInvalidates;
                map.put(str, Long.valueOf(map.getOrDefault(str, 0L).longValue() + 1));
                return;
            }
        }
    }

    public static void corkInvalidations(String str) {
        synchronized (sCorkLock) {
            HashMap<String, Integer> map = sCorks;
            int iIntValue = map.getOrDefault(str, 0).intValue();
            if (iIntValue == 0) {
                long j = SystemProperties.getLong(str, 0L);
                if (j != 0 && j != -1) {
                    SystemProperties.set(str, Long.toString(0L));
                }
            }
            map.put(str, Integer.valueOf(iIntValue + 1));
        }
    }

    public static void uncorkInvalidations(String str) {
        synchronized (sCorkLock) {
            HashMap<String, Integer> map = sCorks;
            int iIntValue = map.getOrDefault(str, 0).intValue();
            if (iIntValue < 1) {
                throw new AssertionError("cork underflow: " + str);
            }
            if (iIntValue == 1) {
                map.remove(str);
                invalidateCacheLocked(str);
            } else {
                map.put(str, Integer.valueOf(iIntValue - 1));
            }
        }
    }

    public static final class AutoCorker {
        public static final int DEFAULT_AUTO_CORK_DELAY_MS = 2000;
        private final int mAutoCorkDelayMs;
        private Handler mHandler;
        private final Object mLock;
        private final String mPropertyName;
        private long mUncorkDeadlineMs;

        public AutoCorker(String str) {
            this(str, 2000);
        }

        public AutoCorker(String str, int i) {
            this.mLock = new Object();
            this.mUncorkDeadlineMs = -1L;
            this.mPropertyName = str;
            this.mAutoCorkDelayMs = i;
        }

        public void autoCork() {
            if (Looper.getMainLooper() == null) {
                PropertyInvalidatedCache.invalidateCache(this.mPropertyName);
                return;
            }
            synchronized (this.mLock) {
                boolean z = this.mUncorkDeadlineMs >= 0;
                this.mUncorkDeadlineMs = SystemClock.uptimeMillis() + ((long) this.mAutoCorkDelayMs);
                if (!z) {
                    getHandlerLocked().sendEmptyMessageAtTime(0, this.mUncorkDeadlineMs);
                    PropertyInvalidatedCache.corkInvalidations(this.mPropertyName);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleMessage(Message message) {
            synchronized (this.mLock) {
                if (this.mUncorkDeadlineMs < 0) {
                    return;
                }
                long jUptimeMillis = SystemClock.uptimeMillis();
                if (this.mUncorkDeadlineMs > jUptimeMillis) {
                    this.mUncorkDeadlineMs = jUptimeMillis + ((long) this.mAutoCorkDelayMs);
                    getHandlerLocked().sendEmptyMessageAtTime(0, this.mUncorkDeadlineMs);
                } else {
                    this.mUncorkDeadlineMs = -1L;
                    PropertyInvalidatedCache.uncorkInvalidations(this.mPropertyName);
                }
            }
        }

        private Handler getHandlerLocked() {
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.getMainLooper()) { // from class: android.app.PropertyInvalidatedCache.AutoCorker.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        AutoCorker.this.handleMessage(message);
                    }
                };
            }
            return this.mHandler;
        }
    }

    public String cacheName() {
        return this.mPropertyName;
    }

    public String queryToString(Query query) {
        return Objects.toString(query);
    }

    public static void disableForTestMode() {
        Log.d(TAG, "disabling all caches in the process");
        sEnabled = false;
    }

    public static ArrayList<PropertyInvalidatedCache> getActiveCaches() {
        ArrayList<PropertyInvalidatedCache> arrayList;
        synchronized (sCorkLock) {
            arrayList = new ArrayList<>(sCaches.keySet());
        }
        return arrayList;
    }

    public static ArrayList<Map.Entry<String, Integer>> getActiveCorks() {
        ArrayList<Map.Entry<String, Integer>> arrayList;
        synchronized (sCorkLock) {
            arrayList = new ArrayList<>(sCorks.entrySet());
        }
        return arrayList;
    }

    private void dumpContents(PrintWriter printWriter, String[] strArr) {
        long jLongValue;
        synchronized (sCorkLock) {
            jLongValue = sInvalidates.getOrDefault(this.mPropertyName, 0L).longValue();
        }
        synchronized (this.mLock) {
            printWriter.println(String.format("  Cache Property Name: %s", cacheName()));
            printWriter.println(String.format("    Hits: %d, Misses: %d, Invalidates: %d", Long.valueOf(this.mHits), Long.valueOf(this.mMisses), Long.valueOf(jLongValue)));
            printWriter.println(String.format("    Last Observed Nonce: %d", Long.valueOf(this.mLastSeenNonce)));
            printWriter.println(String.format("    Current Size: %d, Max Size: %d", Integer.valueOf(this.mCache.entrySet().size()), Integer.valueOf(this.mMaxEntries)));
            Object[] objArr = new Object[1];
            objArr[0] = this.mDisabled ? "false" : "true";
            printWriter.println(String.format("    Enabled: %s", objArr));
            Set<Map.Entry<Query, Result>> setEntrySet = this.mCache.entrySet();
            if (setEntrySet.size() == 0) {
                printWriter.println("");
                return;
            }
            printWriter.println("");
            printWriter.println("    Contents:");
            for (Map.Entry<Query, Result> entry : setEntrySet) {
                printWriter.println(String.format("      Key: %s\n      Value: %s\n", Objects.toString(entry.getKey()), Objects.toString(entry.getValue())));
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|49|2|(5:43|3|48|4|(4:6|7|8|9)(7:10|1c|(3:21|22|19)|53|23|24|54))|35|46|36|40|(1:(0))) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void dumpCacheInfo(java.io.FileDescriptor r10, java.lang.String[] r11) {
        /*
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.io.IOException -> L8e
            r0.<init>(r10)     // Catch: java.io.IOException -> L8e
            com.android.internal.util.FastPrintWriter r10 = new com.android.internal.util.FastPrintWriter     // Catch: java.lang.Throwable -> L84
            r10.<init>(r0)     // Catch: java.lang.Throwable -> L84
            boolean r1 = android.app.PropertyInvalidatedCache.sEnabled     // Catch: java.lang.Throwable -> L7a
            if (r1 != 0) goto L1a
            java.lang.String r11 = "  Caching is disabled in this process."
            r10.println(r11)     // Catch: java.lang.Throwable -> L7a
            r10.close()     // Catch: java.lang.Throwable -> L84
            r0.close()     // Catch: java.io.IOException -> L8e
            return
        L1a:
            java.lang.Object r1 = android.app.PropertyInvalidatedCache.sCorkLock     // Catch: java.lang.Throwable -> L7a
            monitor-enter(r1)     // Catch: java.lang.Throwable -> L7a
            java.util.ArrayList r2 = getActiveCaches()     // Catch: java.lang.Throwable -> L77
            java.util.ArrayList r3 = getActiveCorks()     // Catch: java.lang.Throwable -> L77
            int r4 = r3.size()     // Catch: java.lang.Throwable -> L77
            r5 = 0
            if (r4 <= 0) goto L5a
            java.lang.String r4 = "  Corking Status:"
            r10.println(r4)     // Catch: java.lang.Throwable -> L77
            r4 = r5
        L32:
            int r6 = r3.size()     // Catch: java.lang.Throwable -> L77
            if (r4 >= r6) goto L5a
            java.lang.Object r6 = r3.get(r4)     // Catch: java.lang.Throwable -> L77
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch: java.lang.Throwable -> L77
            java.lang.String r7 = "    Property Name: %s Count: %d"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L77
            java.lang.Object r9 = r6.getKey()     // Catch: java.lang.Throwable -> L77
            r8[r5] = r9     // Catch: java.lang.Throwable -> L77
            java.lang.Object r6 = r6.getValue()     // Catch: java.lang.Throwable -> L77
            r9 = 1
            r8[r9] = r6     // Catch: java.lang.Throwable -> L77
            java.lang.String r6 = java.lang.String.format(r7, r8)     // Catch: java.lang.Throwable -> L77
            r10.println(r6)     // Catch: java.lang.Throwable -> L77
            int r4 = r4 + 1
            goto L32
        L5a:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L77
        L5b:
            int r1 = r2.size()     // Catch: java.lang.Throwable -> L7a
            if (r5 >= r1) goto L70
            java.lang.Object r1 = r2.get(r5)     // Catch: java.lang.Throwable -> L7a
            android.app.PropertyInvalidatedCache r1 = (android.app.PropertyInvalidatedCache) r1     // Catch: java.lang.Throwable -> L7a
            r1.dumpContents(r10, r11)     // Catch: java.lang.Throwable -> L7a
            r10.flush()     // Catch: java.lang.Throwable -> L7a
            int r5 = r5 + 1
            goto L5b
        L70:
            r10.close()     // Catch: java.lang.Throwable -> L84
            r0.close()     // Catch: java.io.IOException -> L8e
            goto L95
        L77:
            r11 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L77
            throw r11     // Catch: java.lang.Throwable -> L7a
        L7a:
            r11 = move-exception
            r10.close()     // Catch: java.lang.Throwable -> L7f
            goto L83
        L7f:
            r10 = move-exception
            r11.addSuppressed(r10)     // Catch: java.lang.Throwable -> L84
        L83:
            throw r11     // Catch: java.lang.Throwable -> L84
        L84:
            r10 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L89
            goto L8d
        L89:
            r11 = move-exception
            r10.addSuppressed(r11)     // Catch: java.io.IOException -> L8e
        L8d:
            throw r10     // Catch: java.io.IOException -> L8e
        L8e:
            java.lang.String r10 = "PropertyInvalidatedCache"
            java.lang.String r11 = "Failed to dump PropertyInvalidatedCache instances"
            android.util.Log.e(r10, r11)
        L95:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.PropertyInvalidatedCache.dumpCacheInfo(java.io.FileDescriptor, java.lang.String[]):void");
    }
}
