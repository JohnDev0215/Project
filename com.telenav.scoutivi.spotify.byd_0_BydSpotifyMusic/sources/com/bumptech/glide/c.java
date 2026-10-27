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
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.load.ImageHeaderParser;
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
import o1.k;
import u1.a;
import u1.b;
import u1.d;
import u1.e;
import u1.f;
import u1.k;
import u1.s;
import u1.t;
import u1.u;
import u1.v;
import u1.w;
import u1.x;
import v1.a;
import v1.b;
import v1.c;
import v1.d;
import v1.e;
import x1.a0;
import x1.m;
import x1.p;
import x1.s;
import x1.x;
import x1.z;
import y1.a;

/* JADX INFO: compiled from: Glide.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements ComponentCallbacks2 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static volatile c f3935q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static volatile boolean f3936r;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final q1.k f3937e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final r1.d f3938g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final s1.h f3939h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final e f3940i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final i f3941j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final r1.b f3942k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final d2.l f3943l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final d2.d f3944m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final a f3946o;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final List<k> f3945n = new ArrayList();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private f f3947p = f.NORMAL;

    /* JADX INFO: compiled from: Glide.java */
    public interface a {
        g2.f a();
    }

    c(Context context, q1.k kVar, s1.h hVar, r1.d dVar, r1.b bVar, d2.l lVar, d2.d dVar2, int i7, a aVar, Map<Class<?>, l<?, ?>> map, List<g2.e<Object>> list, boolean z6, boolean z7, int i8, int i9) {
        n1.k gVar;
        n1.k xVar;
        this.f3937e = kVar;
        this.f3938g = dVar;
        this.f3942k = bVar;
        this.f3939h = hVar;
        this.f3943l = lVar;
        this.f3944m = dVar2;
        this.f3946o = aVar;
        Resources resources = context.getResources();
        i iVar = new i();
        this.f3941j = iVar;
        iVar.o(new x1.k());
        iVar.o(new p());
        List<ImageHeaderParser> listG = iVar.g();
        b2.a aVar2 = new b2.a(context, listG, dVar, bVar);
        n1.k<ParcelFileDescriptor, Bitmap> kVarG = a0.g(dVar);
        if (z7) {
            xVar = new s();
            gVar = new x1.h();
        } else {
            m mVar = new m(iVar.g(), resources.getDisplayMetrics(), dVar, bVar);
            gVar = new x1.g(mVar);
            xVar = new x(mVar, bVar);
        }
        z1.e eVar = new z1.e(context);
        s.c cVar = new s.c(resources);
        s.d dVar3 = new s.d(resources);
        s.b bVar2 = new s.b(resources);
        s.a aVar3 = new s.a(resources);
        x1.c cVar2 = new x1.c(bVar);
        c2.a aVar4 = new c2.a();
        c2.d dVar4 = new c2.d();
        ContentResolver contentResolver = context.getContentResolver();
        i iVarQ = iVar.c(ByteBuffer.class, new u1.c()).c(InputStream.class, new t(bVar)).e("Bitmap", ByteBuffer.class, Bitmap.class, gVar).e("Bitmap", InputStream.class, Bitmap.class, xVar).e("Bitmap", ParcelFileDescriptor.class, Bitmap.class, kVarG).e("Bitmap", AssetFileDescriptor.class, Bitmap.class, a0.c(dVar)).b(Bitmap.class, Bitmap.class, v.a.a()).e("Bitmap", Bitmap.class, Bitmap.class, new z()).d(Bitmap.class, cVar2).e("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new x1.a(resources, gVar)).e("BitmapDrawable", InputStream.class, BitmapDrawable.class, new x1.a(resources, xVar)).e("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new x1.a(resources, kVarG)).d(BitmapDrawable.class, new x1.b(dVar, cVar2)).e("Gif", InputStream.class, b2.c.class, new b2.j(listG, aVar2, bVar)).e("Gif", ByteBuffer.class, b2.c.class, aVar2).d(b2.c.class, new b2.d()).b(l1.a.class, l1.a.class, v.a.a()).e("Bitmap", l1.a.class, Bitmap.class, new b2.h(dVar)).a(Uri.class, Drawable.class, eVar).a(Uri.class, Bitmap.class, new x1.v(eVar, dVar)).q(new a.C0178a()).b(File.class, ByteBuffer.class, new d.b()).b(File.class, InputStream.class, new f.e()).a(File.class, File.class, new a2.a()).b(File.class, ParcelFileDescriptor.class, new f.b()).b(File.class, File.class, v.a.a()).q(new k.a(bVar));
        Class cls = Integer.TYPE;
        iVarQ.b(cls, InputStream.class, cVar).b(cls, ParcelFileDescriptor.class, bVar2).b(Integer.class, InputStream.class, cVar).b(Integer.class, ParcelFileDescriptor.class, bVar2).b(Integer.class, Uri.class, dVar3).b(cls, AssetFileDescriptor.class, aVar3).b(Integer.class, AssetFileDescriptor.class, aVar3).b(cls, Uri.class, dVar3).b(String.class, InputStream.class, new e.c()).b(Uri.class, InputStream.class, new e.c()).b(String.class, InputStream.class, new u.c()).b(String.class, ParcelFileDescriptor.class, new u.b()).b(String.class, AssetFileDescriptor.class, new u.a()).b(Uri.class, InputStream.class, new b.a()).b(Uri.class, InputStream.class, new a.c(context.getAssets())).b(Uri.class, ParcelFileDescriptor.class, new a.b(context.getAssets())).b(Uri.class, InputStream.class, new c.a(context)).b(Uri.class, InputStream.class, new d.a(context)).b(Uri.class, InputStream.class, new w.d(contentResolver)).b(Uri.class, ParcelFileDescriptor.class, new w.b(contentResolver)).b(Uri.class, AssetFileDescriptor.class, new w.a(contentResolver)).b(Uri.class, InputStream.class, new x.a()).b(URL.class, InputStream.class, new e.a()).b(Uri.class, File.class, new k.a(context)).b(u1.g.class, InputStream.class, new a.C0165a()).b(byte[].class, ByteBuffer.class, new b.a()).b(byte[].class, InputStream.class, new b.d()).b(Uri.class, Uri.class, v.a.a()).b(Drawable.class, Drawable.class, v.a.a()).a(Drawable.class, Drawable.class, new z1.f()).p(Bitmap.class, BitmapDrawable.class, new c2.b(resources)).p(Bitmap.class, byte[].class, aVar4).p(Drawable.class, byte[].class, new c2.c(dVar, aVar4, dVar4)).p(b2.c.class, byte[].class, dVar4);
        this.f3940i = new e(context, bVar, iVar, new h2.f(), aVar, map, list, kVar, z6, i7);
    }

    private static void a(Context context, GeneratedAppGlideModule generatedAppGlideModule) {
        if (f3936r) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        f3936r = true;
        m(context, generatedAppGlideModule);
        f3936r = false;
    }

    public static c c(Context context) {
        if (f3935q == null) {
            GeneratedAppGlideModule generatedAppGlideModuleD = d(context.getApplicationContext());
            synchronized (c.class) {
                if (f3935q == null) {
                    a(context, generatedAppGlideModuleD);
                }
            }
        }
        return f3935q;
    }

    private static GeneratedAppGlideModule d(Context context) {
        try {
            return (GeneratedAppGlideModule) GeneratedAppGlideModuleImpl.class.getDeclaredConstructor(Context.class).newInstance(context.getApplicationContext());
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("Glide", 5)) {
                Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            return null;
        } catch (IllegalAccessException e7) {
            q(e7);
            return null;
        } catch (InstantiationException e8) {
            q(e8);
            return null;
        } catch (NoSuchMethodException e9) {
            q(e9);
            return null;
        } catch (InvocationTargetException e10) {
            q(e10);
            return null;
        }
    }

    private static d2.l l(Context context) {
        k2.j.e(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return c(context).k();
    }

    private static void m(Context context, GeneratedAppGlideModule generatedAppGlideModule) {
        n(context, new d(), generatedAppGlideModule);
    }

    private static void n(Context context, d dVar, GeneratedAppGlideModule generatedAppGlideModule) {
        Context applicationContext = context.getApplicationContext();
        List<e2.b> listEmptyList = Collections.emptyList();
        if (generatedAppGlideModule == null || generatedAppGlideModule.c()) {
            listEmptyList = new e2.d(applicationContext).a();
        }
        if (generatedAppGlideModule != null && !generatedAppGlideModule.d().isEmpty()) {
            Set<Class<?>> setD = generatedAppGlideModule.d();
            Iterator<e2.b> it = listEmptyList.iterator();
            while (it.hasNext()) {
                e2.b next = it.next();
                if (setD.contains(next.getClass())) {
                    if (Log.isLoggable("Glide", 3)) {
                        Log.d("Glide", "AppGlideModule excludes manifest GlideModule: " + next);
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            Iterator<e2.b> it2 = listEmptyList.iterator();
            while (it2.hasNext()) {
                Log.d("Glide", "Discovered GlideModule from manifest: " + it2.next().getClass());
            }
        }
        dVar.d(generatedAppGlideModule != null ? generatedAppGlideModule.e() : null);
        Iterator<e2.b> it3 = listEmptyList.iterator();
        while (it3.hasNext()) {
            it3.next().b(applicationContext, dVar);
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.b(applicationContext, dVar);
        }
        c cVarA = dVar.a(applicationContext);
        for (e2.b bVar : listEmptyList) {
            try {
                bVar.a(applicationContext, cVarA, cVarA.f3941j);
            } catch (AbstractMethodError e7) {
                throw new IllegalStateException("Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: " + bVar.getClass().getName(), e7);
            }
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.a(applicationContext, cVarA, cVarA.f3941j);
        }
        applicationContext.registerComponentCallbacks(cVarA);
        f3935q = cVarA;
    }

    private static void q(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    public static k t(Context context) {
        return l(context).j(context);
    }

    public static k u(View view) {
        return l(view.getContext()).k(view);
    }

    public void b() {
        k2.k.a();
        this.f3939h.b();
        this.f3938g.b();
        this.f3942k.b();
    }

    public r1.b e() {
        return this.f3942k;
    }

    public r1.d f() {
        return this.f3938g;
    }

    d2.d g() {
        return this.f3944m;
    }

    public Context h() {
        return this.f3940i.getBaseContext();
    }

    e i() {
        return this.f3940i;
    }

    public i j() {
        return this.f3941j;
    }

    public d2.l k() {
        return this.f3943l;
    }

    void o(k kVar) {
        synchronized (this.f3945n) {
            if (this.f3945n.contains(kVar)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            this.f3945n.add(kVar);
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
    public void onTrimMemory(int i7) {
        r(i7);
    }

    boolean p(h2.h<?> hVar) {
        synchronized (this.f3945n) {
            Iterator<k> it = this.f3945n.iterator();
            while (it.hasNext()) {
                if (it.next().z(hVar)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void r(int i7) {
        k2.k.a();
        Iterator<k> it = this.f3945n.iterator();
        while (it.hasNext()) {
            it.next().onTrimMemory(i7);
        }
        this.f3939h.a(i7);
        this.f3938g.a(i7);
        this.f3942k.a(i7);
    }

    void s(k kVar) {
        synchronized (this.f3945n) {
            if (!this.f3945n.contains(kVar)) {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
            this.f3945n.remove(kVar);
        }
    }
}
