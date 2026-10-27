package com.bumptech.glide;

import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.b;
import com.bumptech.glide.d;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.data.c;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.gif.GifDrawable;
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
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import spi.ai;
import spi.au;
import spi.bd;
import spi.bg;
import spi.bn;
import spi.bu;
import spi.bv;
import spi.cd;
import spi.d0;
import spi.dd;
import spi.dn;
import spi.e;
import spi.ef;
import spi.eh;
import spi.fh;
import spi.g4;
import spi.g8;
import spi.gn;
import spi.hd;
import spi.hs;
import spi.i8;
import spi.ih;
import spi.ii;
import spi.is;
import spi.ji;
import spi.ld;
import spi.lf;
import spi.li;
import spi.m0;
import spi.md;
import spi.ms;
import spi.n2;
import spi.nf;
import spi.ni;
import spi.nu;
import spi.o2;
import spi.oi;
import spi.ou;
import spi.p0;
import spi.p1;
import spi.p2;
import spi.p5;
import spi.pl;
import spi.pm;
import spi.pu;
import spi.q1;
import spi.q2;
import spi.qa;
import spi.qm;
import spi.r1;
import spi.r2;
import spi.r7;
import spi.rm;
import spi.s1;
import spi.s2;
import spi.sb;
import spi.sd;
import spi.t1;
import spi.t2;
import spi.tf;
import spi.tu;
import spi.vb;
import spi.w1;
import spi.x1;
import spi.xk;
import spi.xm;
import spi.xt;
import spi.z5;
import spi.zt;

/* JADX INFO: loaded from: classes.dex */
public final class a implements ComponentCallbacks2 {

    @GuardedBy("Glide.class")
    public static volatile a i;
    public static volatile boolean j;
    public final w1 a;
    public final ni b;
    public final c c;
    public final Registry d;
    public final m0 e;
    public final rm f;
    public final g4 g;

    @GuardedBy("managers")
    public final List<qm> h = new ArrayList();

    /* JADX INFO: renamed from: com.bumptech.glide.a$a, reason: collision with other inner class name */
    public interface InterfaceC0013a {
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public a(@NonNull Context context, @NonNull i8 i8Var, @NonNull ni niVar, @NonNull w1 w1Var, @NonNull m0 m0Var, @NonNull rm rmVar, @NonNull g4 g4Var, int i2, @NonNull InterfaceC0013a interfaceC0013a, @NonNull Map<Class<?>, xt<?, ?>> map, @NonNull List<pm<Object>> list, d dVar) {
        bn o2Var;
        bn cVar;
        Object obj;
        Object obj2;
        MemoryCategory memoryCategory = MemoryCategory.NORMAL;
        this.a = w1Var;
        this.e = m0Var;
        this.b = niVar;
        this.f = rmVar;
        this.g = g4Var;
        Resources resources = context.getResources();
        Registry registry = new Registry();
        this.d = registry;
        DefaultImageHeaderParser defaultImageHeaderParser = new DefaultImageHeaderParser();
        lf lfVar = registry.g;
        synchronized (lfVar) {
            lfVar.a.add(defaultImageHeaderParser);
        }
        int i3 = Build.VERSION.SDK_INT;
        qa qaVar = new qa();
        lf lfVar2 = registry.g;
        synchronized (lfVar2) {
            lfVar2.a.add(qaVar);
        }
        List<ImageHeaderParser> listE = registry.e();
        s2 s2Var = new s2(context, listE, w1Var, m0Var);
        bv bvVar = new bv(w1Var, new bv.g());
        com.bumptech.glide.load.resource.bitmap.a aVar = new com.bumptech.glide.load.resource.bitmap.a(registry.e(), resources.getDisplayMetrics(), w1Var, m0Var);
        if (dVar.a(b.c.class)) {
            cVar = new tf();
            o2Var = new p2();
        } else {
            o2Var = new o2(aVar);
            cVar = new com.bumptech.glide.load.resource.bitmap.c(aVar, m0Var);
        }
        if (dVar.a(b.C0014b.class)) {
            obj = String.class;
            obj2 = Integer.class;
            registry.d("Animation", InputStream.class, Drawable.class, d0.c(listE, m0Var));
            registry.d("Animation", ByteBuffer.class, Drawable.class, d0.a(listE, m0Var));
        } else {
            obj = String.class;
            obj2 = Integer.class;
        }
        dn dnVar = new dn(context);
        gn.c cVar2 = new gn.c(resources);
        gn.d dVar2 = new gn.d(resources);
        gn.b bVar = new gn.b(resources);
        gn.a aVar2 = new gn.a(resources);
        t1 t1Var = new t1(m0Var);
        p1 p1Var = new p1();
        cd cdVar = new cd();
        ContentResolver contentResolver = context.getContentResolver();
        q2 q2Var = new q2();
        g8 g8Var = registry.b;
        synchronized (g8Var) {
            g8Var.a.add(new g8.a<>(ByteBuffer.class, q2Var));
        }
        hs hsVar = new hs(m0Var);
        g8 g8Var2 = registry.b;
        synchronized (g8Var2) {
            g8Var2.a.add(new g8.a<>(InputStream.class, hsVar));
        }
        registry.d("Bitmap", ByteBuffer.class, Bitmap.class, o2Var);
        registry.d("Bitmap", InputStream.class, Bitmap.class, cVar);
        registry.d("Bitmap", ParcelFileDescriptor.class, Bitmap.class, new xk(aVar));
        registry.d("Bitmap", ParcelFileDescriptor.class, Bitmap.class, bvVar);
        registry.d("Bitmap", AssetFileDescriptor.class, Bitmap.class, new bv(w1Var, new bv.c(null)));
        bu.a<?> aVar3 = bu.a.a;
        registry.a(Bitmap.class, Bitmap.class, aVar3);
        registry.d("Bitmap", Bitmap.class, Bitmap.class, new zt());
        registry.c(Bitmap.class, t1Var);
        registry.d("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new q1(resources, o2Var));
        registry.d("BitmapDrawable", InputStream.class, BitmapDrawable.class, new q1(resources, cVar));
        registry.d("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new q1(resources, bvVar));
        registry.c(BitmapDrawable.class, new r1(w1Var, t1Var));
        registry.d("Animation", InputStream.class, GifDrawable.class, new is(listE, s2Var, m0Var));
        registry.d("Animation", ByteBuffer.class, GifDrawable.class, s2Var);
        registry.c(GifDrawable.class, new dd());
        registry.a(bd.class, bd.class, aVar3);
        registry.d("Bitmap", bd.class, Bitmap.class, new hd(w1Var));
        registry.d("legacy_append", Uri.class, Drawable.class, dnVar);
        registry.d("legacy_append", Uri.class, Bitmap.class, new xm(dnVar, w1Var));
        registry.h(new t2.a());
        registry.a(File.class, ByteBuffer.class, new r2.b());
        registry.a(File.class, InputStream.class, new vb.e());
        registry.d("legacy_append", File.class, File.class, new sb());
        registry.a(File.class, ParcelFileDescriptor.class, new vb.b());
        registry.a(File.class, File.class, aVar3);
        registry.h(new c.a(m0Var));
        registry.h(new ParcelFileDescriptorRewinder.a());
        Class cls = Integer.TYPE;
        registry.a(cls, InputStream.class, cVar2);
        registry.a(cls, ParcelFileDescriptor.class, bVar);
        Object obj3 = obj2;
        registry.a(obj3, InputStream.class, cVar2);
        registry.a(obj3, ParcelFileDescriptor.class, bVar);
        registry.a(obj3, Uri.class, dVar2);
        registry.a(cls, AssetFileDescriptor.class, aVar2);
        registry.a(obj3, AssetFileDescriptor.class, aVar2);
        registry.a(cls, Uri.class, dVar2);
        Object obj4 = obj;
        registry.a(obj4, InputStream.class, new p5.c());
        registry.a(Uri.class, InputStream.class, new p5.c());
        registry.a(obj4, InputStream.class, new ms.c());
        registry.a(obj4, ParcelFileDescriptor.class, new ms.b());
        registry.a(obj4, AssetFileDescriptor.class, new ms.a());
        registry.a(Uri.class, InputStream.class, new p0.c(context.getAssets()));
        registry.a(Uri.class, AssetFileDescriptor.class, new p0.b(context.getAssets()));
        registry.a(Uri.class, InputStream.class, new ji.a(context));
        registry.a(Uri.class, InputStream.class, new li.a(context));
        if (i3 >= 29) {
            registry.a(Uri.class, InputStream.class, new pl.c(context));
            registry.a(Uri.class, ParcelFileDescriptor.class, new pl.b(context));
        }
        registry.a(Uri.class, InputStream.class, new nu.d(contentResolver));
        registry.a(Uri.class, ParcelFileDescriptor.class, new nu.b(contentResolver));
        registry.a(Uri.class, AssetFileDescriptor.class, new nu.a(contentResolver));
        registry.a(Uri.class, InputStream.class, new pu.a());
        registry.a(URL.class, InputStream.class, new ou.a());
        registry.a(Uri.class, File.class, new ii.a(context));
        registry.a(sd.class, InputStream.class, new ef.a());
        registry.a(byte[].class, ByteBuffer.class, new n2.a());
        registry.a(byte[].class, InputStream.class, new n2.d());
        registry.a(Uri.class, Uri.class, aVar3);
        registry.a(Drawable.class, Drawable.class, aVar3);
        registry.d("legacy_append", Drawable.class, Drawable.class, new au());
        registry.i(Bitmap.class, BitmapDrawable.class, new s1(resources));
        registry.i(Bitmap.class, byte[].class, p1Var);
        registry.i(Drawable.class, byte[].class, new r7(w1Var, p1Var, cdVar));
        registry.i(GifDrawable.class, byte[].class, cdVar);
        bv bvVar2 = new bv(w1Var, new bv.d());
        registry.b(ByteBuffer.class, Bitmap.class, bvVar2);
        registry.b(ByteBuffer.class, BitmapDrawable.class, new q1(resources, bvVar2));
        this.c = new c(context, m0Var, registry, new nf(), interfaceC0013a, map, list, i8Var, dVar, i2);
    }

    @GuardedBy("Glide.class")
    public static void a(@NonNull Context context, @Nullable GeneratedAppGlideModule generatedAppGlideModule) {
        if (j) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        j = true;
        b bVar = new b();
        Context applicationContext = context.getApplicationContext();
        Collections.emptyList();
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.c();
        }
        ai aiVar = new ai(applicationContext);
        if (Log.isLoggable("ManifestParser", 3)) {
            Log.d("ManifestParser", "Loading Glide modules");
        }
        ArrayList<md> arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = aiVar.a.getPackageManager().getApplicationInfo(aiVar.a.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                if (Log.isLoggable("ManifestParser", 2)) {
                    Log.v("ManifestParser", "Got app info metadata: " + applicationInfo.metaData);
                }
                for (String str : applicationInfo.metaData.keySet()) {
                    if ("GlideModule".equals(applicationInfo.metaData.get(str))) {
                        arrayList.add(ai.a(str));
                        if (Log.isLoggable("ManifestParser", 3)) {
                            Log.d("ManifestParser", "Loaded Glide module: " + str);
                        }
                    }
                }
                if (Log.isLoggable("ManifestParser", 3)) {
                    Log.d("ManifestParser", "Finished loading Glide modules");
                }
            } else if (Log.isLoggable("ManifestParser", 3)) {
                Log.d("ManifestParser", "Got null app info metadata");
            }
            if (generatedAppGlideModule != null && !generatedAppGlideModule.d().isEmpty()) {
                Set<Class<?>> setD = generatedAppGlideModule.d();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    md mdVar = (md) it.next();
                    if (setD.contains(mdVar.getClass())) {
                        if (Log.isLoggable("Glide", 3)) {
                            Log.d("Glide", "AppGlideModule excludes manifest GlideModule: " + mdVar);
                        }
                        it.remove();
                    }
                }
            }
            if (Log.isLoggable("Glide", 3)) {
                for (md mdVar2 : arrayList) {
                    StringBuilder sbK = e.k("Discovered GlideModule from manifest: ");
                    sbK.append(mdVar2.getClass());
                    Log.d("Glide", sbK.toString());
                }
            }
            bVar.n = generatedAppGlideModule != null ? generatedAppGlideModule.e() : null;
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((md) it2.next()).a(applicationContext, bVar);
            }
            if (generatedAppGlideModule != null) {
                generatedAppGlideModule.a(applicationContext, bVar);
            }
            if (bVar.g == null) {
                int i2 = ld.c;
                ld.b bVar2 = new ld.b(false);
                int iA = ld.a();
                bVar2.b = iA;
                bVar2.c = iA;
                bVar2.f = BYDAutoMultimediaDevice.PARAM_SOURCE;
                bVar.g = bVar2.a();
            }
            if (bVar.h == null) {
                int i3 = ld.c;
                ld.b bVar3 = new ld.b(true);
                bVar3.b = 1;
                bVar3.c = 1;
                bVar3.f = "disk-cache";
                bVar.h = bVar3.a();
            }
            if (bVar.o == null) {
                int i4 = ld.a() < 4 ? 1 : 2;
                ld.b bVar4 = new ld.b(true);
                bVar4.b = i4;
                bVar4.c = i4;
                bVar4.f = "animation";
                bVar.o = bVar4.a();
            }
            if (bVar.j == null) {
                bVar.j = new oi(new oi.a(applicationContext));
            }
            if (bVar.k == null) {
                bVar.k = new z5();
            }
            if (bVar.d == null) {
                int i5 = bVar.j.a;
                if (i5 > 0) {
                    bVar.d = new fh(i5);
                } else {
                    bVar.d = new x1();
                }
            }
            if (bVar.e == null) {
                bVar.e = new eh(bVar.j.d);
            }
            if (bVar.f == null) {
                bVar.f = new ih(bVar.j.b);
            }
            if (bVar.i == null) {
                bVar.i = new bg(applicationContext);
            }
            if (bVar.c == null) {
                bVar.c = new i8(bVar.f, bVar.i, bVar.h, bVar.g, new ld(new ThreadPoolExecutor(0, Integer.MAX_VALUE, ld.b, TimeUnit.MILLISECONDS, new SynchronousQueue(), new ld.d(new ld.c(), "source-unlimited", ld.e.b, false))), bVar.o, false);
            }
            List<pm<Object>> list = bVar.p;
            bVar.p = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
            d.a aVar = bVar.b;
            Objects.requireNonNull(aVar);
            d dVar = new d(aVar);
            a aVar2 = new a(applicationContext, bVar.c, bVar.f, bVar.d, bVar.e, new rm(bVar.n, dVar), bVar.k, bVar.l, bVar.m, bVar.a, bVar.p, dVar);
            for (md mdVar3 : arrayList) {
                try {
                    mdVar3.b(applicationContext, aVar2, aVar2.d);
                } catch (AbstractMethodError e) {
                    StringBuilder sbK2 = e.k("Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: ");
                    sbK2.append(mdVar3.getClass().getName());
                    throw new IllegalStateException(sbK2.toString(), e);
                }
            }
            if (generatedAppGlideModule != null) {
                generatedAppGlideModule.b(applicationContext, aVar2, aVar2.d);
            }
            applicationContext.registerComponentCallbacks(aVar2);
            i = aVar2;
            j = false;
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e2);
        }
    }

    @NonNull
    public static a b(@NonNull Context context) {
        if (i == null) {
            GeneratedAppGlideModule generatedAppGlideModule = null;
            try {
                generatedAppGlideModule = (GeneratedAppGlideModule) GeneratedAppGlideModuleImpl.class.getDeclaredConstructor(Context.class).newInstance(context.getApplicationContext().getApplicationContext());
            } catch (ClassNotFoundException unused) {
                if (Log.isLoggable("Glide", 5)) {
                    Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
                }
            } catch (IllegalAccessException e) {
                c(e);
                throw null;
            } catch (InstantiationException e2) {
                c(e2);
                throw null;
            } catch (NoSuchMethodException e3) {
                c(e3);
                throw null;
            } catch (InvocationTargetException e4) {
                c(e4);
                throw null;
            }
            synchronized (a.class) {
                if (i == null) {
                    a(context, generatedAppGlideModule);
                }
            }
        }
        return i;
    }

    public static void c(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    @NonNull
    public static qm e(@NonNull Context context) {
        Objects.requireNonNull(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return b(context).f.b(context);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<spi.qm>] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.ArrayList, java.util.List<spi.qm>] */
    public final void d(qm qmVar) {
        synchronized (this.h) {
            if (!this.h.contains(qmVar)) {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
            this.h.remove(qmVar);
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        tu.a();
        this.b.b();
        this.a.b();
        this.e.b();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<spi.qm>] */
    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i2) {
        tu.a();
        synchronized (this.h) {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                Objects.requireNonNull((qm) it.next());
            }
        }
        this.b.a(i2);
        this.a.a(i2);
        this.e.a(i2);
    }
}
