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
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.engine.y.h;
import com.bumptech.glide.load.i.k;
import com.bumptech.glide.load.i.m;
import com.bumptech.glide.load.j.a;
import com.bumptech.glide.load.j.b;
import com.bumptech.glide.load.j.d;
import com.bumptech.glide.load.j.e;
import com.bumptech.glide.load.j.f;
import com.bumptech.glide.load.j.k;
import com.bumptech.glide.load.j.s;
import com.bumptech.glide.load.j.t;
import com.bumptech.glide.load.j.u;
import com.bumptech.glide.load.j.v;
import com.bumptech.glide.load.j.w;
import com.bumptech.glide.load.j.x;
import com.bumptech.glide.load.j.y.a;
import com.bumptech.glide.load.j.y.b;
import com.bumptech.glide.load.j.y.c;
import com.bumptech.glide.load.j.y.d;
import com.bumptech.glide.load.j.y.e;
import com.bumptech.glide.load.j.y.f;
import com.bumptech.glide.load.k.d.a;
import com.bumptech.glide.load.k.g.j;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.n;
import com.bumptech.glide.load.resource.bitmap.p;
import com.bumptech.glide.load.resource.bitmap.q;
import com.bumptech.glide.load.resource.bitmap.r;
import com.bumptech.glide.load.resource.bitmap.u;
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
public class b implements ComponentCallbacks2 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile b f2642b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile boolean f2643c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final i f2644d;
    private final com.bumptech.glide.load.engine.x.e e;
    private final h f;
    private final d g;
    private final Registry h;
    private final com.bumptech.glide.load.engine.x.b i;
    private final k j;
    private final com.bumptech.glide.manager.d k;
    private final a m;
    private final List<f> l = new ArrayList();
    private MemoryCategory n = MemoryCategory.NORMAL;

    /* JADX INFO: compiled from: Glide.java */
    public interface a {
        com.bumptech.glide.request.e a();
    }

    b(Context context, i iVar, h hVar, com.bumptech.glide.load.engine.x.e eVar, com.bumptech.glide.load.engine.x.b bVar, k kVar, com.bumptech.glide.manager.d dVar, int i, a aVar, Map<Class<?>, g<?, ?>> map, List<com.bumptech.glide.request.d<Object>> list, boolean z, boolean z2) {
        com.bumptech.glide.load.f fVar;
        com.bumptech.glide.load.f rVar;
        this.f2644d = iVar;
        this.e = eVar;
        this.i = bVar;
        this.f = hVar;
        this.j = kVar;
        this.k = dVar;
        this.m = aVar;
        Resources resources = context.getResources();
        Registry registry = new Registry();
        this.h = registry;
        registry.o(new DefaultImageHeaderParser());
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 27) {
            registry.o(new com.bumptech.glide.load.resource.bitmap.k());
        }
        List<ImageHeaderParser> listG = registry.g();
        com.bumptech.glide.load.k.g.a aVar2 = new com.bumptech.glide.load.k.g.a(context, listG, eVar, bVar);
        com.bumptech.glide.load.f<ParcelFileDescriptor, Bitmap> fVarH = u.h(eVar);
        com.bumptech.glide.load.resource.bitmap.h hVar2 = new com.bumptech.glide.load.resource.bitmap.h(registry.g(), resources.getDisplayMetrics(), eVar, bVar);
        if (!z2 || i2 < 28) {
            fVar = new com.bumptech.glide.load.resource.bitmap.f(hVar2);
            rVar = new r(hVar2, bVar);
        } else {
            rVar = new n();
            fVar = new com.bumptech.glide.load.resource.bitmap.g();
        }
        com.bumptech.glide.load.k.e.d dVar2 = new com.bumptech.glide.load.k.e.d(context);
        s.c cVar = new s.c(resources);
        s.d dVar3 = new s.d(resources);
        s.b bVar2 = new s.b(resources);
        s.a aVar3 = new s.a(resources);
        com.bumptech.glide.load.resource.bitmap.c cVar2 = new com.bumptech.glide.load.resource.bitmap.c(bVar);
        com.bumptech.glide.load.k.h.a aVar4 = new com.bumptech.glide.load.k.h.a();
        com.bumptech.glide.load.k.h.d dVar4 = new com.bumptech.glide.load.k.h.d();
        ContentResolver contentResolver = context.getContentResolver();
        registry.a(ByteBuffer.class, new com.bumptech.glide.load.j.c()).a(InputStream.class, new t(bVar)).e("Bitmap", ByteBuffer.class, Bitmap.class, fVar).e("Bitmap", InputStream.class, Bitmap.class, rVar);
        if (m.c()) {
            registry.e("Bitmap", ParcelFileDescriptor.class, Bitmap.class, new p(hVar2));
        }
        registry.e("Bitmap", ParcelFileDescriptor.class, Bitmap.class, fVarH).e("Bitmap", AssetFileDescriptor.class, Bitmap.class, u.c(eVar)).d(Bitmap.class, Bitmap.class, v.a.a()).e("Bitmap", Bitmap.class, Bitmap.class, new com.bumptech.glide.load.resource.bitmap.t()).b(Bitmap.class, cVar2).e("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, fVar)).e("BitmapDrawable", InputStream.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, rVar)).e("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, fVarH)).b(BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.b(eVar, cVar2)).e("Gif", InputStream.class, com.bumptech.glide.load.k.g.c.class, new j(listG, aVar2, bVar)).e("Gif", ByteBuffer.class, com.bumptech.glide.load.k.g.c.class, aVar2).b(com.bumptech.glide.load.k.g.c.class, new com.bumptech.glide.load.k.g.d()).d(com.bumptech.glide.i.a.class, com.bumptech.glide.i.a.class, v.a.a()).e("Bitmap", com.bumptech.glide.i.a.class, Bitmap.class, new com.bumptech.glide.load.k.g.h(eVar)).c(Uri.class, Drawable.class, dVar2).c(Uri.class, Bitmap.class, new q(dVar2, eVar)).p(new a.C0101a()).d(File.class, ByteBuffer.class, new d.b()).d(File.class, InputStream.class, new f.e()).c(File.class, File.class, new com.bumptech.glide.load.k.f.a()).d(File.class, ParcelFileDescriptor.class, new f.b()).d(File.class, File.class, v.a.a()).p(new k.a(bVar));
        if (m.c()) {
            registry.p(new m.a());
        }
        Class cls = Integer.TYPE;
        registry.d(cls, InputStream.class, cVar).d(cls, ParcelFileDescriptor.class, bVar2).d(Integer.class, InputStream.class, cVar).d(Integer.class, ParcelFileDescriptor.class, bVar2).d(Integer.class, Uri.class, dVar3).d(cls, AssetFileDescriptor.class, aVar3).d(Integer.class, AssetFileDescriptor.class, aVar3).d(cls, Uri.class, dVar3).d(String.class, InputStream.class, new e.c()).d(Uri.class, InputStream.class, new e.c()).d(String.class, InputStream.class, new u.c()).d(String.class, ParcelFileDescriptor.class, new u.b()).d(String.class, AssetFileDescriptor.class, new u.a()).d(Uri.class, InputStream.class, new b.a()).d(Uri.class, InputStream.class, new a.c(context.getAssets())).d(Uri.class, ParcelFileDescriptor.class, new a.b(context.getAssets())).d(Uri.class, InputStream.class, new c.a(context)).d(Uri.class, InputStream.class, new d.a(context));
        if (i2 >= 29) {
            registry.d(Uri.class, InputStream.class, new e.c(context));
            registry.d(Uri.class, ParcelFileDescriptor.class, new e.b(context));
        }
        registry.d(Uri.class, InputStream.class, new w.d(contentResolver)).d(Uri.class, ParcelFileDescriptor.class, new w.b(contentResolver)).d(Uri.class, AssetFileDescriptor.class, new w.a(contentResolver)).d(Uri.class, InputStream.class, new x.a()).d(URL.class, InputStream.class, new f.a()).d(Uri.class, File.class, new k.a(context)).d(com.bumptech.glide.load.j.g.class, InputStream.class, new a.C0098a()).d(byte[].class, ByteBuffer.class, new b.a()).d(byte[].class, InputStream.class, new b.d()).d(Uri.class, Uri.class, v.a.a()).d(Drawable.class, Drawable.class, v.a.a()).c(Drawable.class, Drawable.class, new com.bumptech.glide.load.k.e.e()).q(Bitmap.class, BitmapDrawable.class, new com.bumptech.glide.load.k.h.b(resources)).q(Bitmap.class, byte[].class, aVar4).q(Drawable.class, byte[].class, new com.bumptech.glide.load.k.h.c(eVar, aVar4, dVar4)).q(com.bumptech.glide.load.k.g.c.class, byte[].class, dVar4);
        if (i2 >= 23) {
            com.bumptech.glide.load.f<ByteBuffer, Bitmap> fVarD = com.bumptech.glide.load.resource.bitmap.u.d(eVar);
            registry.c(ByteBuffer.class, Bitmap.class, fVarD);
            registry.c(ByteBuffer.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, fVarD));
        }
        this.g = new d(context, bVar, registry, new com.bumptech.glide.request.h.b(), aVar, map, list, iVar, z, i);
    }

    private static void a(Context context, GeneratedAppGlideModule generatedAppGlideModule) {
        if (f2643c) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        f2643c = true;
        m(context, generatedAppGlideModule);
        f2643c = false;
    }

    public static b c(Context context) {
        if (f2642b == null) {
            GeneratedAppGlideModule generatedAppGlideModuleD = d(context.getApplicationContext());
            synchronized (b.class) {
                if (f2642b == null) {
                    a(context, generatedAppGlideModuleD);
                }
            }
        }
        return f2642b;
    }

    private static GeneratedAppGlideModule d(Context context) {
        try {
            return (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(Context.class).newInstance(context.getApplicationContext());
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("Glide", 5)) {
                Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            return null;
        } catch (IllegalAccessException e) {
            q(e);
            return null;
        } catch (InstantiationException e2) {
            q(e2);
            return null;
        } catch (NoSuchMethodException e3) {
            q(e3);
            return null;
        } catch (InvocationTargetException e4) {
            q(e4);
            return null;
        }
    }

    private static com.bumptech.glide.manager.k l(Context context) {
        com.bumptech.glide.m.j.e(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return c(context).k();
    }

    private static void m(Context context, GeneratedAppGlideModule generatedAppGlideModule) {
        n(context, new c(), generatedAppGlideModule);
    }

    private static void n(Context context, c cVar, GeneratedAppGlideModule generatedAppGlideModule) {
        Context applicationContext = context.getApplicationContext();
        List<com.bumptech.glide.j.b> listEmptyList = Collections.emptyList();
        if (generatedAppGlideModule == null || generatedAppGlideModule.c()) {
            listEmptyList = new com.bumptech.glide.j.d(applicationContext).a();
        }
        if (generatedAppGlideModule != null && !generatedAppGlideModule.d().isEmpty()) {
            Set<Class<?>> setD = generatedAppGlideModule.d();
            Iterator<com.bumptech.glide.j.b> it = listEmptyList.iterator();
            while (it.hasNext()) {
                com.bumptech.glide.j.b next = it.next();
                if (setD.contains(next.getClass())) {
                    if (Log.isLoggable("Glide", 3)) {
                        Log.d("Glide", "AppGlideModule excludes manifest GlideModule: " + next);
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            Iterator<com.bumptech.glide.j.b> it2 = listEmptyList.iterator();
            while (it2.hasNext()) {
                Log.d("Glide", "Discovered GlideModule from manifest: " + it2.next().getClass());
            }
        }
        cVar.b(generatedAppGlideModule != null ? generatedAppGlideModule.e() : null);
        Iterator<com.bumptech.glide.j.b> it3 = listEmptyList.iterator();
        while (it3.hasNext()) {
            it3.next().a(applicationContext, cVar);
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.b(applicationContext, cVar);
        }
        b bVarA = cVar.a(applicationContext);
        for (com.bumptech.glide.j.b bVar : listEmptyList) {
            try {
                bVar.b(applicationContext, bVarA, bVarA.h);
            } catch (AbstractMethodError e) {
                throw new IllegalStateException("Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: " + bVar.getClass().getName(), e);
            }
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.a(applicationContext, bVarA, bVarA.h);
        }
        applicationContext.registerComponentCallbacks(bVarA);
        f2642b = bVarA;
    }

    private static void q(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    public static f t(Context context) {
        return l(context).e(context);
    }

    public void b() {
        com.bumptech.glide.m.k.a();
        this.f.b();
        this.e.b();
        this.i.b();
    }

    public com.bumptech.glide.load.engine.x.b e() {
        return this.i;
    }

    public com.bumptech.glide.load.engine.x.e f() {
        return this.e;
    }

    com.bumptech.glide.manager.d g() {
        return this.k;
    }

    public Context h() {
        return this.g.getBaseContext();
    }

    d i() {
        return this.g;
    }

    public Registry j() {
        return this.h;
    }

    public com.bumptech.glide.manager.k k() {
        return this.j;
    }

    void o(f fVar) {
        synchronized (this.l) {
            if (this.l.contains(fVar)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            this.l.add(fVar);
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

    boolean p(com.bumptech.glide.request.h.d<?> dVar) {
        synchronized (this.l) {
            Iterator<f> it = this.l.iterator();
            while (it.hasNext()) {
                if (it.next().x(dVar)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void r(int i) {
        com.bumptech.glide.m.k.a();
        Iterator<f> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().onTrimMemory(i);
        }
        this.f.a(i);
        this.e.a(i);
        this.i.a(i);
    }

    void s(f fVar) {
        synchronized (this.l) {
            if (!this.l.contains(fVar)) {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
            this.l.remove(fVar);
        }
    }
}
