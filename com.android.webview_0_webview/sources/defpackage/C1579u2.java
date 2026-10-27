package defpackage;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: renamed from: u2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1579u2 extends C1469s2 {
    public final Class a;
    public final Constructor b;
    public final Method c;
    public final Method d;
    public final Method e;
    public final Method f;
    public final Method g;

    public C1579u2() throws NoSuchMethodException {
        Method methodO;
        Method method;
        Constructor<?> constructor;
        Method methodM;
        Method methodN;
        Method method2;
        Class<?> cls = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            constructor = cls2.getConstructor(new Class[0]);
            methodM = m(cls2);
            methodN = n(cls2);
            method2 = cls2.getMethod("freeze", new Class[0]);
            method = cls2.getMethod("abortCreation", new Class[0]);
            methodO = o(cls2);
            cls = cls2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            StringBuilder sbF = Z7.f("Unable to collect necessary methods for class ");
            sbF.append(e.getClass().getName());
            Log.e("TypefaceCompatApi26Impl", sbF.toString(), e);
            methodO = null;
            method = null;
            constructor = null;
            methodM = null;
            methodN = null;
            method2 = null;
        }
        this.a = cls;
        this.b = constructor;
        this.c = methodM;
        this.d = methodN;
        this.e = method2;
        this.f = method;
        this.g = methodO;
    }

    @Override // defpackage.AbstractC1854z2
    public Typeface a(Context context, C0749f2 c0749f2, Resources resources, int i) {
        if (!k()) {
            return super.a(context, c0749f2, resources, i);
        }
        Object objL = l();
        for (C0805g2 c0805g2 : c0749f2.a) {
            if (!h(context, objL, c0805g2.a, c0805g2.e, c0805g2.b, c0805g2.c ? 1 : 0, FontVariationAxis.fromFontVariationSettings(c0805g2.d))) {
                g(objL);
                return null;
            }
        }
        if (j(objL)) {
            return i(objL);
        }
        return null;
    }

    @Override // defpackage.C1469s2, defpackage.AbstractC1854z2
    public Typeface b(Context context, CancellationSignal cancellationSignal, O2[] o2Arr, int i) {
        if (o2Arr.length < 1) {
            return null;
        }
        if (k()) {
            Map mapD = Q2.d(context, o2Arr, cancellationSignal);
            Object objL = l();
            boolean z = false;
            for (O2 o2 : o2Arr) {
                ByteBuffer byteBuffer = (ByteBuffer) mapD.get(o2.a);
                if (byteBuffer != null) {
                    try {
                        if (!((Boolean) this.d.invoke(objL, byteBuffer, Integer.valueOf(o2.b), null, Integer.valueOf(o2.c), Integer.valueOf(o2.d ? 1 : 0))).booleanValue()) {
                            g(objL);
                            return null;
                        }
                        z = true;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (!z) {
                g(objL);
                return null;
            }
            if (j(objL)) {
                return Typeface.create(i(objL), i);
            }
            return null;
        }
        O2 o2F = f(o2Arr, i);
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(o2F.a, "r", cancellationSignal);
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                }
                return null;
            }
            try {
                Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(o2F.c).setItalic(o2F.d).build();
                parcelFileDescriptorOpenFileDescriptor.close();
                return typefaceBuild;
            } finally {
            }
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // defpackage.AbstractC1854z2
    public Typeface d(Context context, Resources resources, int i, String str, int i2) {
        if (!k()) {
            return super.d(context, resources, i, str, i2);
        }
        Object objL = l();
        if (!h(context, objL, str, 0, -1, -1, null)) {
            g(objL);
            return null;
        }
        if (j(objL)) {
            return i(objL);
        }
        return null;
    }

    public final void g(Object obj) {
        try {
            this.f.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean h(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.c.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Typeface i(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) this.a, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.g.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean j(Object obj) {
        try {
            return ((Boolean) this.e.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean k() {
        if (this.c == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.c != null;
    }

    public final Object l() {
        try {
            return this.b.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Method m(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    public Method n(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    public Method o(Class cls) throws NoSuchMethodException {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance((Class<?>) cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
