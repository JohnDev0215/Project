package io.sentry.android.replay;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: io.sentry.android.replay.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1450a extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1450a f16328n = new C1450a(0, 0);
    public static final C1450a o = new C1450a(0, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C1450a f16329p = new C1450a(0, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C1450a f16330q = new C1450a(0, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C1450a f16331r = new C1450a(0, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C1450a f16332s = new C1450a(0, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C1450a f16333t = new C1450a(0, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C1450a f16334u = new C1450a(0, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final C1450a f16335v = new C1450a(0, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C1450a f16336w = new C1450a(0, 9);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final C1450a f16337x = new C1450a(0, 10);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final C1450a f16338y = new C1450a(0, 11);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16339m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1450a(int i7, int i10) {
        super(i7);
        this.f16339m = i10;
    }

    /* JADX WARN: Type inference failed for: r6v12, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r6v17, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r6v25, types: [java.lang.Object, m7.f] */
    @Override // z7.InterfaceC2772a
    public final Object a() throws NoSuchFieldException {
        switch (this.f16339m) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                o oVar = o.f16415a;
                new Handler(Looper.getMainLooper()).postAtFrontOfQueue(new io.sentry.android.ndk.e(1));
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
                A7.m.e(bitmapCreateBitmap, "createBitmap(\n          …onfig.ARGB_8888\n        )");
                break;
            case 6:
                Class cls = (Class) x.f16460a.getValue();
                if (cls != null) {
                    Field declaredField = cls.getDeclaredField("mViews");
                    declaredField.setAccessible(true);
                }
                break;
            case 7:
                try {
                } catch (Throwable th) {
                    Log.w("WindowManagerSpy", th);
                    return null;
                }
                break;
            case 8:
                Class cls2 = (Class) x.f16460a.getValue();
                if (cls2 != null && (r6 = cls2.getMethod("getInstance", null)) != null) {
                    break;
                }
                break;
            case 9:
                break;
            case 10:
                int i7 = Build.VERSION.SDK_INT;
                try {
                } catch (Throwable th2) {
                    Log.d("WindowSpy", "Unexpected exception loading com.android.internal.policy.DecorView on API " + i7, th2);
                    return null;
                }
                break;
            default:
                Class cls3 = (Class) A.f16314a.getValue();
                if (cls3 != null) {
                    int i10 = Build.VERSION.SDK_INT;
                    try {
                        Field declaredField2 = cls3.getDeclaredField("mWindow");
                        declaredField2.setAccessible(true);
                    } catch (NoSuchFieldException e10) {
                        Log.d("WindowSpy", "Unexpected exception retrieving " + cls3 + "#mWindow on API " + i10, e10);
                        return null;
                    }
                }
                break;
        }
        return null;
    }
}
