package android.provider;

import android.app.job.JobInfo;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.LruCache;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes3.dex */
public class FontsContract {
    private static final long SYNC_FONT_FETCH_TIMEOUT_MS = 500;
    private static final String TAG = "FontsContract";
    private static final int THREAD_RENEWAL_THRESHOLD_MS = 10000;
    private static volatile Context sContext;
    private static Handler sHandler;
    private static Set<String> sInQueueSet;
    private static HandlerThread sThread;
    private static final Object sLock = new Object();
    private static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    private static final Runnable sReplaceDispatcherThreadRunnable = new Runnable() { // from class: android.provider.FontsContract.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (FontsContract.sLock) {
                if (FontsContract.sThread != null) {
                    FontsContract.sThread.quitSafely();
                    HandlerThread unused = FontsContract.sThread = null;
                    Handler unused2 = FontsContract.sHandler = null;
                }
            }
        }
    };
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator() { // from class: android.provider.-$$Lambda$FontsContract$3FDNQd-WsglsyDhif-aHVbzkfrA
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FontsContract.lambda$static$13((byte[]) obj, (byte[]) obj2);
        }
    };

    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        @Retention(RetentionPolicy.SOURCE)
        @interface FontRequestFailReason {
        }

        public void onTypefaceRequestFailed(int i) {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }
    }

    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";

        private Columns() {
        }
    }

    private FontsContract() {
    }

    public static void setApplicationContextForResources(Context context) {
        sContext = context.getApplicationContext();
    }

    public static class FontInfo {
        private final FontVariationAxis[] mAxes;
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        public FontInfo(Uri uri, int i, FontVariationAxis[] fontVariationAxisArr, int i2, boolean z, int i3) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = i;
            this.mAxes = fontVariationAxisArr;
            this.mWeight = i2;
            this.mItalic = z;
            this.mResultCode = i3;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public FontVariationAxis[] getAxes() {
            return this.mAxes;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_REJECTED = 3;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @Retention(RetentionPolicy.SOURCE)
        @interface FontResultStatus {
        }

        public FontFamilyResult(int i, FontInfo[] fontInfoArr) {
            this.mStatusCode = i;
            this.mFonts = fontInfoArr;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    public static Typeface getFontSync(final FontRequest fontRequest) {
        final String identifier = fontRequest.getIdentifier();
        Typeface typeface = sTypefaceCache.get(identifier);
        if (typeface != null) {
            return typeface;
        }
        synchronized (sLock) {
            Typeface typeface2 = sTypefaceCache.get(identifier);
            if (typeface2 != null) {
                return typeface2;
            }
            if (sHandler == null) {
                HandlerThread handlerThread = new HandlerThread("fonts", 10);
                sThread = handlerThread;
                handlerThread.start();
                sHandler = new Handler(sThread.getLooper());
            }
            final ReentrantLock reentrantLock = new ReentrantLock();
            final Condition conditionNewCondition = reentrantLock.newCondition();
            final AtomicReference atomicReference = new AtomicReference();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            final AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
            sHandler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$rqfIZKvP1frnI9vP1hVA8jQN_RE
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    FontsContract.lambda$getFontSync$0(fontRequest, identifier, atomicReference, reentrantLock, atomicBoolean2, atomicBoolean, conditionNewCondition);
                }
            });
            sHandler.removeCallbacks(sReplaceDispatcherThreadRunnable);
            sHandler.postDelayed(sReplaceDispatcherThreadRunnable, JobInfo.MIN_BACKOFF_MILLIS);
            long nanos = TimeUnit.MILLISECONDS.toNanos(SYNC_FONT_FETCH_TIMEOUT_MS);
            reentrantLock.lock();
            try {
                if (!atomicBoolean.get()) {
                    return (Typeface) atomicReference.get();
                }
                do {
                    try {
                        nanos = conditionNewCondition.awaitNanos(nanos);
                    } catch (InterruptedException unused) {
                    }
                    if (!atomicBoolean.get()) {
                        return (Typeface) atomicReference.get();
                    }
                } while (nanos > 0);
                atomicBoolean2.set(true);
                Log.w(TAG, "Remote font fetch timed out: " + fontRequest.getProviderAuthority() + "/" + fontRequest.getQuery());
                return null;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    static /* synthetic */ void lambda$getFontSync$0(FontRequest fontRequest, String str, AtomicReference atomicReference, Lock lock, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, Condition condition) throws Throwable {
        try {
            FontFamilyResult fontFamilyResultFetchFonts = fetchFonts(sContext, null, fontRequest);
            if (fontFamilyResultFetchFonts.getStatusCode() == 0) {
                Typeface typefaceBuildTypeface = buildTypeface(sContext, null, fontFamilyResultFetchFonts.getFonts());
                if (typefaceBuildTypeface != null) {
                    sTypefaceCache.put(str, typefaceBuildTypeface);
                }
                atomicReference.set(typefaceBuildTypeface);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        lock.lock();
        try {
            if (!atomicBoolean.get()) {
                atomicBoolean2.set(false);
                condition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void requestFonts(final Context context, final FontRequest fontRequest, Handler handler, final CancellationSignal cancellationSignal, final FontRequestCallback fontRequestCallback) {
        final Handler handler2 = new Handler();
        final Typeface typeface = sTypefaceCache.get(fontRequest.getIdentifier());
        if (typeface != null) {
            handler2.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$p_tsXYYYpEH0-EJSp2uPrJ33dkU
                @Override // java.lang.Runnable
                public final void run() {
                    fontRequestCallback.onTypefaceRetrieved(typeface);
                }
            });
        } else {
            handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$dFs2m4XF5xdir4W3T-ncUQAVX8k
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    FontsContract.lambda$requestFonts$12(context, cancellationSignal, fontRequest, handler2, fontRequestCallback);
                }
            });
        }
    }

    static /* synthetic */ void lambda$requestFonts$12(Context context, CancellationSignal cancellationSignal, FontRequest fontRequest, Handler handler, final FontRequestCallback fontRequestCallback) throws Throwable {
        try {
            FontFamilyResult fontFamilyResultFetchFonts = fetchFonts(context, cancellationSignal, fontRequest);
            final Typeface typeface = sTypefaceCache.get(fontRequest.getIdentifier());
            if (typeface != null) {
                handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$xDMhIK5JxjXFDIXBeQbZ_hdXTBc
                    @Override // java.lang.Runnable
                    public final void run() {
                        fontRequestCallback.onTypefaceRetrieved(typeface);
                    }
                });
                return;
            }
            if (fontFamilyResultFetchFonts.getStatusCode() != 0) {
                int statusCode = fontFamilyResultFetchFonts.getStatusCode();
                if (statusCode == 1) {
                    handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$YhiTIVckhFBdgNR2V1bGY3Q1Nqg
                        @Override // java.lang.Runnable
                        public final void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-2);
                        }
                    });
                    return;
                } else if (statusCode == 2) {
                    handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$FCawscMFN_8Qxcb2EdA5gdE-O2k
                        @Override // java.lang.Runnable
                        public final void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-3);
                        }
                    });
                    return;
                } else {
                    handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$DV4gvjPxJzdQvcfoIJqGrzFtTQs
                        @Override // java.lang.Runnable
                        public final void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-3);
                        }
                    });
                    return;
                }
            }
            FontInfo[] fonts = fontFamilyResultFetchFonts.getFonts();
            if (fonts == null || fonts.length == 0) {
                handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$LJ3jfZobcxq5xTMmb88GlM1r9Jk
                    @Override // java.lang.Runnable
                    public final void run() {
                        fontRequestCallback.onTypefaceRequestFailed(1);
                    }
                });
                return;
            }
            for (FontInfo fontInfo : fonts) {
                if (fontInfo.getResultCode() != 0) {
                    final int resultCode = fontInfo.getResultCode();
                    if (resultCode < 0) {
                        handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$Qvl9aVA7txTF3tFcFbbKD_nWpuM
                            @Override // java.lang.Runnable
                            public final void run() {
                                fontRequestCallback.onTypefaceRequestFailed(-3);
                            }
                        });
                        return;
                    } else {
                        handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$rvEOORTXb3mMYTLkoH9nlHQr9Iw
                            @Override // java.lang.Runnable
                            public final void run() {
                                fontRequestCallback.onTypefaceRequestFailed(resultCode);
                            }
                        });
                        return;
                    }
                }
            }
            final Typeface typefaceBuildTypeface = buildTypeface(context, cancellationSignal, fonts);
            if (typefaceBuildTypeface == null) {
                handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$rqmVfWYeZ5NL5MtBx5LOdhNAOP4
                    @Override // java.lang.Runnable
                    public final void run() {
                        fontRequestCallback.onTypefaceRequestFailed(-3);
                    }
                });
            } else {
                sTypefaceCache.put(fontRequest.getIdentifier(), typefaceBuildTypeface);
                handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$gJeQYFM3pOm-NcWmWnWDAEk3vlM
                    @Override // java.lang.Runnable
                    public final void run() {
                        fontRequestCallback.onTypefaceRetrieved(typefaceBuildTypeface);
                    }
                });
            }
        } catch (PackageManager.NameNotFoundException unused) {
            handler.post(new Runnable() { // from class: android.provider.-$$Lambda$FontsContract$bLFahJqnd9gkPbDqB-OCiChzm_E
                @Override // java.lang.Runnable
                public final void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-1);
                }
            });
        }
    }

    public static FontFamilyResult fetchFonts(Context context, CancellationSignal cancellationSignal, FontRequest fontRequest) throws PackageManager.NameNotFoundException {
        if (context.isRestricted()) {
            return new FontFamilyResult(3, null);
        }
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest);
        if (provider == null) {
            return new FontFamilyResult(1, null);
        }
        try {
            return new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
        } catch (IllegalArgumentException unused) {
            return new FontFamilyResult(2, null);
        }
    }

    public static Typeface buildTypeface(Context context, CancellationSignal cancellationSignal, FontInfo[] fontInfoArr) throws Throwable {
        int i;
        if (context.isRestricted()) {
            return null;
        }
        Map<Uri, ByteBuffer> mapPrepareFontData = prepareFontData(context, fontInfoArr, cancellationSignal);
        if (mapPrepareFontData.isEmpty()) {
            return null;
        }
        int length = fontInfoArr.length;
        int i2 = 0;
        FontFamily.Builder builder = null;
        while (true) {
            i = 1;
            if (i2 >= length) {
                break;
            }
            FontInfo fontInfo = fontInfoArr[i2];
            ByteBuffer byteBuffer = mapPrepareFontData.get(fontInfo.getUri());
            if (byteBuffer != null) {
                try {
                    Font.Builder weight = new Font.Builder(byteBuffer).setWeight(fontInfo.getWeight());
                    if (!fontInfo.isItalic()) {
                        i = 0;
                    }
                    Font fontBuild = weight.setSlant(i).setTtcIndex(fontInfo.getTtcIndex()).setFontVariationSettings(fontInfo.getAxes()).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(fontBuild);
                    } else {
                        builder.addFont(fontBuild);
                    }
                } catch (IOException unused) {
                } catch (IllegalArgumentException unused2) {
                    return null;
                }
            }
            i2++;
        }
        if (builder == null) {
            return null;
        }
        FontFamily fontFamilyBuild = builder.build();
        FontStyle fontStyle = new FontStyle(400, 0);
        Font font = fontFamilyBuild.getFont(0);
        int matchScore = fontStyle.getMatchScore(font.getStyle());
        while (i < fontFamilyBuild.getSize()) {
            Font font2 = fontFamilyBuild.getFont(i);
            int matchScore2 = fontStyle.getMatchScore(font2.getStyle());
            if (matchScore2 < matchScore) {
                font = font2;
                matchScore = matchScore2;
            }
            i++;
        }
        return new Typeface.CustomFallbackBuilder(fontFamilyBuild).setStyle(font.getStyle()).build();
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0018  */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.Map<android.net.Uri, java.nio.ByteBuffer> prepareFontData(android.content.Context r17, android.provider.FontsContract.FontInfo[] r18, android.os.CancellationSignal r19) throws java.lang.Throwable {
        /*
            r1 = r18
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            android.content.ContentResolver r3 = r17.getContentResolver()
            int r4 = r1.length
            r0 = 0
            r5 = r0
        Le:
            if (r5 >= r4) goto L77
            r0 = r1[r5]
            int r6 = r0.getResultCode()
            if (r6 == 0) goto L1b
        L18:
            r8 = r19
            goto L74
        L1b:
            android.net.Uri r6 = r0.getUri()
            boolean r0 = r2.containsKey(r6)
            if (r0 == 0) goto L26
            goto L18
        L26:
            r7 = 0
            java.lang.String r0 = "r"
            r8 = r19
            android.os.ParcelFileDescriptor r9 = r3.openFileDescriptor(r6, r0, r8)     // Catch: java.io.IOException -> L71
            if (r9 == 0) goto L69
            java.io.FileInputStream r10 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L69
            java.io.FileDescriptor r0 = r9.getFileDescriptor()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L69
            r10.<init>(r0)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L69
            java.nio.channels.FileChannel r11 = r10.getChannel()     // Catch: java.lang.Throwable -> L4f
            long r15 = r11.size()     // Catch: java.lang.Throwable -> L4f
            java.nio.channels.FileChannel$MapMode r12 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.lang.Throwable -> L4f
            r13 = 0
            java.nio.MappedByteBuffer r7 = r11.map(r12, r13, r15)     // Catch: java.lang.Throwable -> L4f
            r10.close()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L69
            goto L69
        L4f:
            r0 = move-exception
            r11 = r0
            r10.close()     // Catch: java.lang.Throwable -> L55
            goto L5a
        L55:
            r0 = move-exception
            r10 = r0
            r11.addSuppressed(r10)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L69
        L5a:
            throw r11     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L69
        L5b:
            r0 = move-exception
            r10 = r0
            if (r9 == 0) goto L68
            r9.close()     // Catch: java.lang.Throwable -> L63
            goto L68
        L63:
            r0 = move-exception
            r9 = r0
            r10.addSuppressed(r9)     // Catch: java.io.IOException -> L71
        L68:
            throw r10     // Catch: java.io.IOException -> L71
        L69:
            if (r9 == 0) goto L71
            r9.close()     // Catch: java.io.IOException -> L71
            goto L71
        L6f:
            r8 = r19
        L71:
            r2.put(r6, r7)
        L74:
            int r5 = r5 + 1
            goto Le
        L77:
            java.util.Map r0 = java.util.Collections.unmodifiableMap(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.provider.FontsContract.prepareFontData(android.content.Context, android.provider.FontsContract$FontInfo[], android.os.CancellationSignal):java.util.Map");
    }

    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
        if (providerInfoResolveContentProvider.applicationInfo.isSystemApp()) {
            return providerInfoResolveContentProvider;
        }
        List<byte[]> listConvertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listConvertToByteArrayList, sByteArrayComparator);
        List<List<byte[]>> certificates = fontRequest.getCertificates();
        for (int i = 0; i < certificates.size(); i++) {
            ArrayList arrayList = new ArrayList(certificates.get(i));
            Collections.sort(arrayList, sByteArrayComparator);
            if (equalsByteArrayList(listConvertToByteArrayList, arrayList)) {
                return providerInfoResolveContentProvider;
            }
        }
        return null;
    }

    static /* synthetic */ int lambda$static$13(byte[] bArr, byte[] bArr2) {
        int length;
        int length2;
        if (bArr.length != bArr2.length) {
            length = bArr.length;
            length2 = bArr2.length;
        } else {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    length = bArr[i];
                    length2 = bArr2[i];
                }
            }
            return 0;
        }
        return length - length2;
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    public static FontInfo[] getFontFromProvider(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        int i;
        Uri uriWithAppendedId;
        int i2;
        boolean z;
        ArrayList arrayList = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str).appendPath(ContentResolver.SCHEME_FILE).build();
        int i3 = 0;
        Cursor cursorQuery = context.getContentResolver().query(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{fontRequest.getQuery()}, null, cancellationSignal);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() > 0) {
                    int columnIndex = cursorQuery.getColumnIndex("result_code");
                    ArrayList arrayList2 = new ArrayList();
                    int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_id");
                    int columnIndex2 = cursorQuery.getColumnIndex("file_id");
                    int columnIndex3 = cursorQuery.getColumnIndex("font_ttc_index");
                    int columnIndex4 = cursorQuery.getColumnIndex("font_variation_settings");
                    int columnIndex5 = cursorQuery.getColumnIndex("font_weight");
                    int columnIndex6 = cursorQuery.getColumnIndex("font_italic");
                    while (cursorQuery.moveToNext()) {
                        int i4 = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : i3;
                        int i5 = columnIndex3 != -1 ? cursorQuery.getInt(columnIndex3) : i3;
                        String string = columnIndex4 != -1 ? cursorQuery.getString(columnIndex4) : null;
                        if (columnIndex2 == -1) {
                            i = columnIndex6;
                            uriWithAppendedId = ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndexOrThrow));
                        } else {
                            i = columnIndex6;
                            uriWithAppendedId = ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex2));
                        }
                        Uri uri = uriWithAppendedId;
                        int i6 = i;
                        if (columnIndex5 == -1 || i6 == -1) {
                            i2 = 400;
                            z = false;
                        } else {
                            i2 = cursorQuery.getInt(columnIndex5);
                            z = cursorQuery.getInt(i6) == 1;
                        }
                        arrayList2.add(new FontInfo(uri, i5, FontVariationAxis.fromFontVariationSettings(string), i2, z, i4));
                        columnIndex6 = i6;
                        i3 = 0;
                    }
                    arrayList = arrayList2;
                }
            } finally {
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return (FontInfo[]) arrayList.toArray(new FontInfo[0]);
    }
}
