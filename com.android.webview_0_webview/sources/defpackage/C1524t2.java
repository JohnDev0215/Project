package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/* JADX INFO: renamed from: t2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1524t2 extends AbstractC1854z2 {
    public static final Class a;
    public static final Constructor b;
    public static final Method c;
    public static final Method d;

    static {
        Class<?> cls;
        Method method;
        Method method2;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi24Impl", e.getClass().getName(), e);
            cls = null;
            method = null;
            method2 = null;
        }
        b = constructor;
        a = cls;
        c = method2;
        d = method;
    }

    public static boolean g(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) c.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Typeface h(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) a, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) d.invoke(null, objNewInstance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object i() {
        try {
            return b.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // defpackage.AbstractC1854z2
    public Typeface a(Context context, C0749f2 c0749f2, Resources resources, int i) {
        MappedByteBuffer map;
        Object objI = i();
        for (C0805g2 c0805g2 : c0749f2.a) {
            int i2 = c0805g2.f;
            File fileC = A2.c(context);
            if (fileC == null) {
                map = null;
            } else {
                try {
                    if (A2.a(fileC, resources, i2)) {
                        try {
                            FileInputStream fileInputStream = new FileInputStream(fileC);
                            try {
                                FileChannel channel = fileInputStream.getChannel();
                                map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                                fileInputStream.close();
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } finally {
                                }
                            }
                        } catch (IOException unused) {
                            map = null;
                        }
                    } else {
                        map = null;
                    }
                } finally {
                    fileC.delete();
                }
            }
            if (map == null || !g(objI, map, c0805g2.e, c0805g2.b, c0805g2.c)) {
                return null;
            }
        }
        return h(objI);
    }

    @Override // defpackage.AbstractC1854z2
    public Typeface b(Context context, CancellationSignal cancellationSignal, O2[] o2Arr, int i) {
        Object objI = i();
        C1633v1 c1633v1 = new C1633v1();
        for (O2 o2 : o2Arr) {
            Uri uri = o2.a;
            ByteBuffer byteBufferD = (ByteBuffer) c1633v1.get(uri);
            if (byteBufferD == null) {
                byteBufferD = A2.d(context, cancellationSignal, uri);
                c1633v1.put(uri, byteBufferD);
            }
            if (!g(objI, byteBufferD, o2.b, o2.c, o2.d)) {
                return null;
            }
        }
        return Typeface.create(h(objI), i);
    }
}
