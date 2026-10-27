package com.king.zxing;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: DecodeThread.java */
/* JADX INFO: loaded from: classes.dex */
final class k extends Thread {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f4626b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.king.zxing.camera.d f4627c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<DecodeHintType, Object> f4628d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler f4629e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CaptureHandler f4630f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final CountDownLatch f4631g = new CountDownLatch(1);

    k(Context context, com.king.zxing.camera.d dVar, CaptureHandler captureHandler, Collection<BarcodeFormat> collection, Map<DecodeHintType, Object> map, String str, com.google.zxing.j jVar) {
        this.f4626b = context;
        this.f4627c = dVar;
        this.f4630f = captureHandler;
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        this.f4628d = enumMap;
        if (map != null) {
            enumMap.putAll(map);
        }
        if (collection == null || collection.isEmpty()) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            collection = EnumSet.noneOf(BarcodeFormat.class);
            if (defaultSharedPreferences.getBoolean("preferences_decode_1D_product", true)) {
                collection.addAll(i.f4612b);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_1D_industrial", true)) {
                collection.addAll(i.f4613c);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_QR", true)) {
                collection.addAll(i.f4615e);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_Data_Matrix", true)) {
                collection.addAll(i.f4616f);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_Aztec", false)) {
                collection.addAll(i.f4617g);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_PDF417", false)) {
                collection.addAll(i.f4618h);
            }
        }
        enumMap.put(DecodeHintType.POSSIBLE_FORMATS, collection);
        if (str != null) {
            enumMap.put(DecodeHintType.CHARACTER_SET, str);
        }
        enumMap.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, jVar);
        Log.i("DecodeThread", "Hints: " + enumMap);
    }

    Handler a() {
        try {
            this.f4631g.await();
        } catch (InterruptedException unused) {
        }
        return this.f4629e;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.f4629e = new j(this.f4626b, this.f4627c, this.f4630f, this.f4628d);
        this.f4631g.countDown();
        Looper.loop();
    }
}
