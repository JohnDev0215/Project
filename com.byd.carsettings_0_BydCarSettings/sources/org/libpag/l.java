package org.libpag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.hardware.HardwareBuffer;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c f4066a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static HandlerThread f4067b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile int f4068c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public PAGImageView f4069a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4070b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4071c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Bitmap f4072d;

        public static a a(PAGImageView pAGImageView, String str, int i, Bitmap bitmap) {
            a aVar = new a();
            aVar.f4069a = pAGImageView;
            aVar.f4070b = str;
            aVar.f4071c = i;
            aVar.f4072d = bitmap;
            return aVar;
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4073a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4074b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f4075c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4076d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f4077e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public PAGDecoder f4078f;

        public boolean a() {
            return this.f4073a > 0 && this.f4074b > 0;
        }

        public void b() {
            PAGDecoder pAGDecoder = this.f4078f;
            if (pAGDecoder != null) {
                pAGDecoder.release();
            }
            this.f4078f = null;
        }

        public void c() {
            b();
            this.f4073a = 0;
            this.f4074b = 0;
            this.f4075c = 0.0f;
            this.f4076d = 0;
            this.f4077e = 0L;
        }

        public boolean a(Context context, PAGComposition pAGComposition, String str, int i, int i2, float f2) {
            if ((pAGComposition == null && str == null) || i == 0) {
                return false;
            }
            if (pAGComposition == null) {
                pAGComposition = str.startsWith("assets://") ? PAGFile.Load(context.getAssets(), str.substring(9)) : PAGFile.Load(str);
            }
            if (pAGComposition == null) {
                return false;
            }
            float fMax = Math.max((i * 1.0f) / pAGComposition.width(), (i2 * 1.0f) / pAGComposition.height());
            if (f2 <= 0.0f) {
                this.f4075c = pAGComposition.frameRate();
            } else {
                this.f4075c = Math.min(pAGComposition.frameRate(), f2);
            }
            PAGDecoder pAGDecoderMake = PAGDecoder.Make(pAGComposition, this.f4075c, fMax);
            this.f4078f = pAGDecoderMake;
            this.f4073a = pAGDecoderMake.width();
            this.f4074b = this.f4078f.height();
            this.f4076d = this.f4078f.numFrames();
            this.f4077e = pAGComposition.duration();
            return true;
        }
    }

    public static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f4079a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private List<PAGImageView> f4080b;

        public c(Looper looper) {
            super(looper);
            this.f4079a = new Object();
            this.f4080b = new ArrayList();
        }

        public void a(PAGImageView pAGImageView) {
            synchronized (this.f4079a) {
                if (this.f4080b.isEmpty()) {
                    Message messageObtainMessage = obtainMessage();
                    messageObtainMessage.arg1 = 0;
                    sendMessage(messageObtainMessage);
                }
                this.f4080b.add(pAGImageView);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ArrayList arrayList;
            super.handleMessage(message);
            int i = message.arg1;
            if (i == 0) {
                synchronized (this.f4079a) {
                    arrayList = new ArrayList(this.f4080b);
                    this.f4080b.clear();
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    if (obj instanceof PAGImageView) {
                        PAGImageView pAGImageView = (PAGImageView) obj;
                        if (!arrayList2.contains(pAGImageView)) {
                            pAGImageView.b();
                            arrayList2.add(pAGImageView);
                        }
                    }
                }
                return;
            }
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                CacheManager cacheManagerA = CacheManager.a((Context) null);
                if (cacheManagerA != null) {
                    cacheManagerA.a();
                }
                new Handler(Looper.getMainLooper()).post(new m(this));
                return;
            }
            PAGImageView pAGImageView2 = (PAGImageView) message.obj;
            if (pAGImageView2.u.a()) {
                pAGImageView2.t = pAGImageView2.a();
                if (pAGImageView2.s != null || pAGImageView2.t == null) {
                    return;
                }
                pAGImageView2.s = pAGImageView2.v.a(pAGImageView2.t.f4035b, pAGImageView2.u.f4073a, pAGImageView2.u.f4074b, pAGImageView2.u.f4076d);
            }
        }
    }

    public static synchronized void a() {
        HandlerThread handlerThread;
        f4068c--;
        if (f4068c != 0) {
            return;
        }
        if (f4066a != null && (handlerThread = f4067b) != null) {
            if (handlerThread.isAlive()) {
                a(2, (Object) null);
            }
        }
    }

    public static double b(int i, int i2) {
        if (i2 <= 1 || i < 0) {
            return 0.0d;
        }
        if (i >= i2 - 1) {
            return 1.0d;
        }
        return ((((double) i) * 1.0d) + 0.1d) / ((double) i2);
    }

    public static synchronized void b() {
        f4068c++;
        if (f4067b == null) {
            HandlerThread handlerThread = new HandlerThread("pagImageView-renderer");
            f4067b = handlerThread;
            handlerThread.start();
        }
        if (f4066a == null) {
            f4066a = new c(f4067b.getLooper());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        HandlerThread handlerThread;
        if (f4068c == 0 && f4066a != null && (handlerThread = f4067b) != null && handlerThread.isAlive()) {
            f4066a.removeCallbacksAndMessages(null);
            if (Build.VERSION.SDK_INT > 18) {
                f4067b.quitSafely();
            } else {
                f4067b.quit();
            }
            f4067b = null;
            f4066a = null;
        }
    }

    public static void a(int i, Object obj) {
        c cVar = f4066a;
        if (cVar == null) {
            return;
        }
        Message messageObtainMessage = cVar.obtainMessage();
        messageObtainMessage.arg1 = i;
        if (obj != null) {
            messageObtainMessage.obj = obj;
        }
        f4066a.sendMessage(messageObtainMessage);
    }

    public static void a(PAGImageView pAGImageView) {
        c cVar = f4066a;
        if (cVar == null) {
            return;
        }
        cVar.a(pAGImageView);
    }

    public static Matrix a(int i, int i2, int i3, int i4, int i5) {
        Matrix matrix = new Matrix();
        if (i != 0 && i2 > 0 && i3 > 0 && i4 > 0 && i5 > 0) {
            float f2 = i4;
            float f3 = i2;
            float f4 = (f2 * 1.0f) / f3;
            float f5 = i5;
            float f6 = i3;
            float f7 = (1.0f * f5) / f6;
            if (i == 1) {
                matrix.setScale(f4, f7);
            } else if (i != 3) {
                float fMin = Math.min(f4, f7);
                matrix.setScale(fMin, fMin);
                if (f4 < f7) {
                    matrix.postTranslate(0.0f, (f5 - (f6 * fMin)) * 0.5f);
                } else {
                    matrix.postTranslate((f2 - (f3 * fMin)) * 0.5f, 0.0f);
                }
            } else {
                float fMax = Math.max(f4, f7);
                matrix.setScale(fMax, fMax);
                if (f4 > f7) {
                    matrix.postTranslate(0.0f, (f5 - (f6 * fMax)) * 0.5f);
                } else {
                    matrix.postTranslate((f2 - (f3 * fMax)) * 0.5f, 0.0f);
                }
            }
        }
        return matrix;
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new BigInteger(1, MessageDigest.getInstance("md5").digest(str.getBytes("utf-8"))).toString(16);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static double a(double d2, double d3) {
        return d2 - (((double) ((int) Math.floor(d2 / d3))) * d3);
    }

    public static int a(double d2, int i) {
        if (i <= 1) {
            return 0;
        }
        double dA = a(d2, 1.0d);
        if (dA <= 0.0d && d2 != 0.0d) {
            dA += 1.0d;
        }
        int iFloor = (int) Math.floor(dA * ((double) i));
        return iFloor == i ? i - 1 : iFloor;
    }

    public static Bitmap a(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return null;
        }
        if (Build.VERSION.SDK_INT > 29) {
            return Bitmap.wrapHardwareBuffer(HardwareBuffer.create(i, i2, 1, 1, 307L), ColorSpace.get(ColorSpace.Named.SRGB));
        }
        return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
    }
}
