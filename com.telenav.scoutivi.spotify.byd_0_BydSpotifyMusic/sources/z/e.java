package z;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import e0.f;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;
import y.a;

/* JADX INFO: compiled from: TypefaceCompatApi26Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected final Class<?> f10034g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected final Constructor<?> f10035h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected final Method f10036i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected final Method f10037j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    protected final Method f10038k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    protected final Method f10039l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    protected final Method f10040m;

    public e() {
        Method methodR;
        Constructor<?> constructorT;
        Method methodP;
        Method methodQ;
        Method methodU;
        Method methodO;
        Class<?> cls = null;
        try {
            Class<?> clsS = s();
            constructorT = t(clsS);
            methodP = p(clsS);
            methodQ = q(clsS);
            methodU = u(clsS);
            methodO = o(clsS);
            methodR = r(clsS);
            cls = clsS;
        } catch (ClassNotFoundException | NoSuchMethodException e7) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + e7.getClass().getName(), e7);
            methodR = null;
            constructorT = null;
            methodP = null;
            methodQ = null;
            methodU = null;
            methodO = null;
        }
        this.f10034g = cls;
        this.f10035h = constructorT;
        this.f10036i = methodP;
        this.f10037j = methodQ;
        this.f10038k = methodU;
        this.f10039l = methodO;
        this.f10040m = methodR;
    }

    private Object i() {
        try {
            return this.f10035h.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    private void j(Object obj) {
        try {
            this.f10039l.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    private boolean k(Context context, Object obj, String str, int i7, int i8, int i9, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.f10036i.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i9), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean l(Object obj, ByteBuffer byteBuffer, int i7, int i8, int i9) {
        try {
            return ((Boolean) this.f10037j.invoke(obj, byteBuffer, Integer.valueOf(i7), null, Integer.valueOf(i8), Integer.valueOf(i9))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean m(Object obj) {
        try {
            return ((Boolean) this.f10038k.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean n() {
        if (this.f10036i == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.f10036i != null;
    }

    @Override // z.d, z.h
    public Typeface a(Context context, a.b bVar, Resources resources, int i7) {
        if (!n()) {
            return super.a(context, bVar, resources, i7);
        }
        Object objI = i();
        if (objI == null) {
            return null;
        }
        for (a.c cVar : bVar.a()) {
            if (!k(context, objI, cVar.a(), cVar.c(), cVar.e(), cVar.f() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(cVar.d()))) {
                j(objI);
                return null;
            }
        }
        if (m(objI)) {
            return g(objI);
        }
        return null;
    }

    @Override // z.h
    public Typeface b(Context context, CancellationSignal cancellationSignal, f.b[] bVarArr, int i7) {
        Typeface typefaceG;
        if (bVarArr.length < 1) {
            return null;
        }
        if (!n()) {
            f.b bVarE = e(bVarArr, i7);
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(bVarE.d(), "r", cancellationSignal);
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return null;
                }
                try {
                    Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(bVarE.e()).setItalic(bVarE.f()).build();
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return typefaceBuild;
                } finally {
                }
            } catch (IOException unused) {
                return null;
            }
        }
        Map<Uri, ByteBuffer> mapF = i.f(context, bVarArr, cancellationSignal);
        Object objI = i();
        if (objI == null) {
            return null;
        }
        boolean z6 = false;
        for (f.b bVar : bVarArr) {
            ByteBuffer byteBuffer = mapF.get(bVar.d());
            if (byteBuffer != null) {
                if (!l(objI, byteBuffer, bVar.c(), bVar.e(), bVar.f() ? 1 : 0)) {
                    j(objI);
                    return null;
                }
                z6 = true;
            }
        }
        if (!z6) {
            j(objI);
            return null;
        }
        if (m(objI) && (typefaceG = g(objI)) != null) {
            return Typeface.create(typefaceG, i7);
        }
        return null;
    }

    @Override // z.h
    public Typeface c(Context context, Resources resources, int i7, String str, int i8) {
        if (!n()) {
            return super.c(context, resources, i7, str, i8);
        }
        Object objI = i();
        if (objI == null) {
            return null;
        }
        if (!k(context, objI, str, 0, -1, -1, null)) {
            j(objI);
            return null;
        }
        if (m(objI)) {
            return g(objI);
        }
        return null;
    }

    protected Typeface g(Object obj) {
        throw null;
    }

    protected Method o(Class<?> cls) {
        return cls.getMethod("abortCreation", new Class[0]);
    }

    protected Method p(Class<?> cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    protected Method q(Class<?> cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    protected Method r(Class<?> cls) {
        throw null;
    }

    protected Class<?> s() {
        return Class.forName("android.graphics.FontFamily");
    }

    protected Constructor<?> t(Class<?> cls) {
        return cls.getConstructor(new Class[0]);
    }

    protected Method u(Class<?> cls) {
        return cls.getMethod("freeze", new Class[0]);
    }
}
