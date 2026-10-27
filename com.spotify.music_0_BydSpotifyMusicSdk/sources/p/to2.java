package p;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class to2 extends so2 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Class f191p;
    public final Constructor q;
    public final Method r;
    public final Method s;
    public final Method t;
    public final Method u;
    public final Method v;

    public to2() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method methodK;
        Method methodL;
        Method method2;
        Method methodM;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            methodK = K(cls);
            methodL = L(cls);
            method2 = cls.getMethod("freeze", new Class[0]);
            method = cls.getMethod("abortCreation", new Class[0]);
            methodM = M(cls);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(e.getClass().getName()), e);
            cls = null;
            method = null;
            constructor = null;
            methodK = null;
            methodL = null;
            method2 = null;
            methodM = null;
        }
        this.f191p = cls;
        this.q = constructor;
        this.r = methodK;
        this.s = methodL;
        this.t = method2;
        this.u = method;
        this.v = methodM;
    }

    public static Method K(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    public static Method L(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    public final void F(Object obj) {
        try {
            this.u.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    public final boolean G(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.r.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Typeface H(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) this.f191p, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.v.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean I(Object obj) {
        try {
            return ((Boolean) this.t.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public final Object J() {
        try {
            return this.q.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    public Method M(Class cls) throws NoSuchMethodException {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance((Class<?>) cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    @Override // p.so2, p.hk0
    public final Typeface l(Context context, zv0 zv0Var, Resources resources, int i) {
        if (this.r == null) {
            return super.l(context, zv0Var, resources, i);
        }
        Object objJ = J();
        if (objJ == null) {
            return null;
        }
        for (aw0 aw0Var : zv0Var.a) {
            if (!G(context, objJ, aw0Var.a, aw0Var.e, aw0Var.b, aw0Var.c ? 1 : 0, FontVariationAxis.fromFontVariationSettings(aw0Var.d))) {
                F(objJ);
                return null;
            }
        }
        if (I(objJ)) {
            return H(objJ);
        }
        return null;
    }

    @Override // p.hk0
    public final Typeface p(Context context, cw0[] cw0VarArr, int i) {
        Typeface typefaceH;
        if (cw0VarArr.length < 1) {
            return null;
        }
        if (this.r == null) {
            cw0 cw0VarX = x(i, cw0VarArr);
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(cw0VarX.a, "r", null);
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return null;
                }
                try {
                    Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(cw0VarX.c).setItalic(cw0VarX.d).build();
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return typefaceBuild;
                } finally {
                }
            } catch (IOException unused) {
                return null;
            }
        }
        HashMap map = new HashMap();
        for (cw0 cw0Var : cw0VarArr) {
            if (cw0Var.e == 0) {
                Uri uri = cw0Var.a;
                if (!map.containsKey(uri)) {
                    map.put(uri, f60.y(context, uri));
                }
            }
        }
        Map mapUnmodifiableMap = Collections.unmodifiableMap(map);
        Object objJ = J();
        if (objJ == null) {
            return null;
        }
        int length = cw0VarArr.length;
        int i2 = 0;
        boolean z = false;
        while (i2 < length) {
            cw0 cw0Var2 = cw0VarArr[i2];
            ByteBuffer byteBuffer = (ByteBuffer) mapUnmodifiableMap.get(cw0Var2.a);
            if (byteBuffer != null) {
                if (!((Boolean) this.s.invoke(objJ, byteBuffer, Integer.valueOf(cw0Var2.b), null, Integer.valueOf(cw0Var2.c), Integer.valueOf(cw0Var2.d ? 1 : 0))).booleanValue()) {
                    F(objJ);
                    return null;
                }
                z = true;
            }
            i2++;
            z = z;
        }
        if (!z) {
            F(objJ);
            return null;
        }
        if (I(objJ) && (typefaceH = H(objJ)) != null) {
            return Typeface.create(typefaceH, i);
        }
        return null;
    }

    @Override // p.hk0
    public final Typeface r(Context context, Resources resources, int i, String str, int i2) {
        if (this.r == null) {
            return super.r(context, resources, i, str, i2);
        }
        Object objJ = J();
        if (objJ == null) {
            return null;
        }
        if (!G(context, objJ, str, 0, -1, -1, null)) {
            F(objJ);
            return null;
        }
        if (I(objJ)) {
            return H(objJ);
        }
        return null;
    }
}
