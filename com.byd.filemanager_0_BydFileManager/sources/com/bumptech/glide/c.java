package com.bumptech.glide;

import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.data.k;
import com.bumptech.glide.load.i.a;
import com.bumptech.glide.load.i.b;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.i.e;
import com.bumptech.glide.load.i.f;
import com.bumptech.glide.load.i.k;
import com.bumptech.glide.load.i.s;
import com.bumptech.glide.load.i.u;
import com.bumptech.glide.load.i.v;
import com.bumptech.glide.load.i.w;
import com.bumptech.glide.load.i.x;
import com.bumptech.glide.load.i.y.a;
import com.bumptech.glide.load.i.y.b;
import com.bumptech.glide.load.i.y.c;
import com.bumptech.glide.load.i.y.d;
import com.bumptech.glide.load.i.y.e;
import com.bumptech.glide.load.i.y.f;
import com.bumptech.glide.load.j.d.a;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.load.resource.bitmap.m;
import com.bumptech.glide.load.resource.bitmap.p;
import com.bumptech.glide.load.resource.bitmap.r;
import com.bumptech.glide.load.resource.bitmap.t;
import com.bumptech.glide.load.resource.bitmap.w;
import com.bumptech.glide.manager.k;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: Glide.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements ComponentCallbacks2 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile c f2840b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile boolean f2841c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.i f2842d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.e f2843e;
    private final com.bumptech.glide.load.engine.y.h f;
    private final e g;
    private final Registry h;
    private final com.bumptech.glide.load.engine.x.b i;
    private final k j;
    private final com.bumptech.glide.manager.d k;
    private final a m;
    private final List<h> l = new ArrayList();
    private MemoryCategory n = MemoryCategory.NORMAL;

    /* JADX INFO: compiled from: Glide.java */
    public interface a {
        com.bumptech.glide.request.e a();
    }

    c(Context context, com.bumptech.glide.load.engine.i iVar, com.bumptech.glide.load.engine.y.h hVar, com.bumptech.glide.load.engine.x.e eVar, com.bumptech.glide.load.engine.x.b bVar, k kVar, com.bumptech.glide.manager.d dVar, int i, a aVar, Map<Class<?>, i<?, ?>> map, List<com.bumptech.glide.request.d<Object>> list, boolean z, boolean z2) {
        com.bumptech.glide.load.f gVar;
        com.bumptech.glide.load.f tVar;
        this.f2842d = iVar;
        this.f2843e = eVar;
        this.i = bVar;
        this.f = hVar;
        this.j = kVar;
        this.k = dVar;
        this.m = aVar;
        Resources resources = context.getResources();
        Registry registry = new Registry();
        this.h = registry;
        registry.p(new DefaultImageHeaderParser());
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 27) {
            registry.p(new m());
        }
        List<ImageHeaderParser> listG = registry.g();
        com.bumptech.glide.load.j.g.a aVar2 = new com.bumptech.glide.load.j.g.a(context, listG, eVar, bVar);
        com.bumptech.glide.load.f<ParcelFileDescriptor, Bitmap> fVarH = w.h(eVar);
        j jVar = new j(registry.g(), resources.getDisplayMetrics(), eVar, bVar);
        if (!z2 || i2 < 28) {
            gVar = new com.bumptech.glide.load.resource.bitmap.g(jVar);
            tVar = new t(jVar, bVar);
        } else {
            tVar = new p();
            gVar = new com.bumptech.glide.load.resource.bitmap.h();
        }
        com.bumptech.glide.load.j.e.d dVar2 = new com.bumptech.glide.load.j.e.d(context);
        s.c cVar = new s.c(resources);
        s.d dVar3 = new s.d(resources);
        s.b bVar2 = new s.b(resources);
        s.a aVar3 = new s.a(resources);
        com.bumptech.glide.load.resource.bitmap.c cVar2 = new com.bumptech.glide.load.resource.bitmap.c(bVar);
        com.bumptech.glide.load.j.h.a aVar4 = new com.bumptech.glide.load.j.h.a();
        com.bumptech.glide.load.j.h.d dVar4 = new com.bumptech.glide.load.j.h.d();
        ContentResolver contentResolver = context.getContentResolver();
        registry.a(ByteBuffer.class, new com.bumptech.glide.load.i.c()).a(InputStream.class, new com.bumptech.glide.load.i.t(bVar)).e("Bitmap", ByteBuffer.class, Bitmap.class, gVar).e("Bitmap", InputStream.class, Bitmap.class, tVar);
        if (ParcelFileDescriptorRewinder.c()) {
            registry.e("Bitmap", ParcelFileDescriptor.class, Bitmap.class, new r(jVar));
        }
        registry.e("Bitmap", ParcelFileDescriptor.class, Bitmap.class, fVarH).e("Bitmap", AssetFileDescriptor.class, Bitmap.class, w.c(eVar)).d(Bitmap.class, Bitmap.class, v.a.a()).e("Bitmap", Bitmap.class, Bitmap.class, new com.bumptech.glide.load.resource.bitmap.v()).b(Bitmap.class, cVar2).e("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, gVar)).e("BitmapDrawable", InputStream.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, tVar)).e("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, fVarH)).b(BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.b(eVar, cVar2)).e("Gif", InputStream.class, com.bumptech.glide.load.j.g.c.class, new com.bumptech.glide.load.j.g.j(listG, aVar2, bVar)).e("Gif", ByteBuffer.class, com.bumptech.glide.load.j.g.c.class, aVar2).b(com.bumptech.glide.load.j.g.c.class, new com.bumptech.glide.load.j.g.d()).d(com.bumptech.glide.k.a.class, com.bumptech.glide.k.a.class, v.a.a()).e("Bitmap", com.bumptech.glide.k.a.class, Bitmap.class, new com.bumptech.glide.load.j.g.h(eVar)).c(Uri.class, Drawable.class, dVar2).c(Uri.class, Bitmap.class, new com.bumptech.glide.load.resource.bitmap.s(dVar2, eVar)).q(new a.C0077a()).d(File.class, ByteBuffer.class, new d.b()).d(File.class, InputStream.class, new f.e()).c(File.class, File.class, new com.bumptech.glide.load.j.f.a()).d(File.class, ParcelFileDescriptor.class, new f.b()).d(File.class, File.class, v.a.a()).q(new k.a(bVar));
        if (ParcelFileDescriptorRewinder.c()) {
            registry.q(new ParcelFileDescriptorRewinder.a());
        }
        Class cls = Integer.TYPE;
        registry.d(cls, InputStream.class, cVar).d(cls, ParcelFileDescriptor.class, bVar2).d(Integer.class, InputStream.class, cVar).d(Integer.class, ParcelFileDescriptor.class, bVar2).d(Integer.class, Uri.class, dVar3).d(cls, AssetFileDescriptor.class, aVar3).d(Integer.class, AssetFileDescriptor.class, aVar3).d(cls, Uri.class, dVar3).d(String.class, InputStream.class, new e.c()).d(Uri.class, InputStream.class, new e.c()).d(String.class, InputStream.class, new u.c()).d(String.class, ParcelFileDescriptor.class, new u.b()).d(String.class, AssetFileDescriptor.class, new u.a()).d(Uri.class, InputStream.class, new b.a()).d(Uri.class, InputStream.class, new a.c(context.getAssets())).d(Uri.class, ParcelFileDescriptor.class, new a.b(context.getAssets())).d(Uri.class, InputStream.class, new c.a(context)).d(Uri.class, InputStream.class, new d.a(context));
        if (i2 >= 29) {
            registry.d(Uri.class, InputStream.class, new e.c(context));
            registry.d(Uri.class, ParcelFileDescriptor.class, new e.b(context));
        }
        registry.d(Uri.class, InputStream.class, new w.d(contentResolver)).d(Uri.class, ParcelFileDescriptor.class, new w.b(contentResolver)).d(Uri.class, AssetFileDescriptor.class, new w.a(contentResolver)).d(Uri.class, InputStream.class, new x.a()).d(URL.class, InputStream.class, new f.a()).d(Uri.class, File.class, new k.a(context)).d(com.bumptech.glide.load.i.g.class, InputStream.class, new a.C0074a()).d(byte[].class, ByteBuffer.class, new b.a()).d(byte[].class, InputStream.class, new b.d()).d(Uri.class, Uri.class, v.a.a()).d(Drawable.class, Drawable.class, v.a.a()).c(Drawable.class, Drawable.class, new com.bumptech.glide.load.j.e.e()).r(Bitmap.class, BitmapDrawable.class, new com.bumptech.glide.load.j.h.b(resources)).r(Bitmap.class, byte[].class, aVar4).r(Drawable.class, byte[].class, new com.bumptech.glide.load.j.h.c(eVar, aVar4, dVar4)).r(com.bumptech.glide.load.j.g.c.class, byte[].class, dVar4);
        if (i2 >= 23) {
            com.bumptech.glide.load.f<ByteBuffer, Bitmap> fVarD = com.bumptech.glide.load.resource.bitmap.w.d(eVar);
            registry.c(ByteBuffer.class, Bitmap.class, fVarD);
            registry.c(ByteBuffer.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, fVarD));
        }
        this.g = new e(context, bVar, registry, new com.bumptech.glide.request.h.c(), aVar, map, list, iVar, z, i);
    }

    private static void a(Context context, GeneratedAppGlideModule generatedAppGlideModule) {
        if (f2841c) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        f2841c = true;
        m(context, generatedAppGlideModule);
        f2841c = false;
    }

    public static c c(Context context) {
        if (f2840b == null) {
            GeneratedAppGlideModule generatedAppGlideModuleD = d(context.getApplicationContext());
            synchronized (c.class) {
                if (f2840b == null) {
                    a(context, generatedAppGlideModuleD);
                }
            }
        }
        return f2840b;
    }

    private static GeneratedAppGlideModule d(Context context) {
        try {
            return (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(Context.class).newInstance(context.getApplicationContext());
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("Glide", 5)) {
                Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            return null;
        } catch (IllegalAccessException e2) {
            q(e2);
            return null;
        } catch (InstantiationException e3) {
            q(e3);
            return null;
        } catch (NoSuchMethodException e4) {
            q(e4);
            return null;
        } catch (InvocationTargetException e5) {
            q(e5);
            return null;
        }
    }

    private static com.bumptech.glide.manager.k l(Context context) {
        com.bumptech.glide.o.j.e(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return c(context).k();
    }

    private static void m(Context context, GeneratedAppGlideModule generatedAppGlideModule) {
        n(context, new d(), generatedAppGlideModule);
    }

    private static void n(Context context, d dVar, GeneratedAppGlideModule generatedAppGlideModule) {
        Context applicationContext = context.getApplicationContext();
        List<com.bumptech.glide.l.b> listEmptyList = Collections.emptyList();
        if (generatedAppGlideModule == null || generatedAppGlideModule.c()) {
            listEmptyList = new com.bumptech.glide.l.d(applicationContext).a();
        }
        if (generatedAppGlideModule != null && !generatedAppGlideModule.d().isEmpty()) {
            Set<Class<?>> setD = generatedAppGlideModule.d();
            Iterator<com.bumptech.glide.l.b> it = listEmptyList.iterator();
            while (it.hasNext()) {
                com.bumptech.glide.l.b next = it.next();
                if (setD.contains(next.getClass())) {
                    if (Log.isLoggable("Glide", 3)) {
                        Log.d("Glide", "AppGlideModule excludes manifest GlideModule: " + next);
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            Iterator<com.bumptech.glide.l.b> it2 = listEmptyList.iterator();
            while (it2.hasNext()) {
                Log.d("Glide", "Discovered GlideModule from manifest: " + it2.next().getClass());
            }
        }
        dVar.i(generatedAppGlideModule != null ? generatedAppGlideModule.e() : null);
        Iterator<com.bumptech.glide.l.b> it3 = listEmptyList.iterator();
        while (it3.hasNext()) {
            it3.next().b(applicationContext, dVar);
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.b(applicationContext, dVar);
        }
        c cVarA = dVar.a(applicationContext);
        for (com.bumptech.glide.l.b bVar : listEmptyList) {
            try {
                bVar.a(applicationContext, cVarA, cVarA.h);
            } catch (AbstractMethodError e2) {
                throw new IllegalStateException("Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: " + bVar.getClass().getName(), e2);
            }
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.a(applicationContext, cVarA, cVarA.h);
        }
        applicationContext.registerComponentCallbacks(cVarA);
        f2840b = cVarA;
    }

    private static void q(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    public static h t(Context context) {
        return l(context).e(context);
    }

    public void b() {
        com.bumptech.glide.o.k.a();
        this.f.b();
        this.f2843e.b();
        this.i.b();
    }

    public com.bumptech.glide.load.engine.x.b e() {
        return this.i;
    }

    public com.bumptech.glide.load.engine.x.e f() {
        return this.f2843e;
    }

    com.bumptech.glide.manager.d g() {
        return this.k;
    }

    public Context h() {
        return this.g.getBaseContext();
    }

    e i() {
        return this.g;
    }

    public Registry j() {
        return this.h;
    }

    public com.bumptech.glide.manager.k k() {
        return this.j;
    }

    void o(h hVar) {
        synchronized (this.l) {
            if (this.l.contains(hVar)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            this.l.add(hVar);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        b();
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        r(i);
    }

    boolean p(com.bumptech.glide.request.h.e<?> eVar) {
        synchronized (this.l) {
            Iterator<h> it = this.l.iterator();
            while (it.hasNext()) {
                if (it.next().A(eVar)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void r(int i) {
        com.bumptech.glide.o.k.a();
        Iterator<h> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().onTrimMemory(i);
        }
        this.f.a(i);
        this.f2843e.a(i);
        this.i.a(i);
    }

    void s(h hVar) {
        synchronized (this.l) {
            if (!this.l.contains(hVar)) {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
            this.l.remove(hVar);
        }
    }
}
