package org.extra.relinker;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.extra.relinker.c;

/* JADX INFO: loaded from: classes3.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set<String> f3983a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b f3984b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.a f3985c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3986d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3987e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.d f3988f;

    public f() {
        this(new g(), new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, String str, String str2) throws Throwable {
        if (this.f3983a.contains(str) && !this.f3986d) {
            a("%s already loaded previously!", str);
            return;
        }
        try {
            this.f3984b.b(str);
            this.f3983a.add(str);
            a("%s (%s) was loaded normally!", str, str2);
        } catch (UnsatisfiedLinkError e2) {
            a("Loading the library normally failed: %s", Log.getStackTraceString(e2));
            a("%s (%s) was not loaded normally, re-linking...", str, str2);
            File fileB = b(context, str, str2);
            if (!fileB.exists() || this.f3986d) {
                if (this.f3986d) {
                    a("Forcing a re-link of %s (%s)...", str, str2);
                }
                a(context, str, str2);
                this.f3985c.a(context, this.f3984b.a(), this.f3984b.a(str), fileB, this);
            }
            try {
                if (this.f3987e) {
                    org.extra.relinker.a.f fVar = null;
                    try {
                        org.extra.relinker.a.f fVar2 = new org.extra.relinker.a.f(fileB);
                        try {
                            List<String> listB = fVar2.b();
                            fVar2.close();
                            Iterator<String> it = listB.iterator();
                            while (it.hasNext()) {
                                a(context, this.f3984b.c(it.next()));
                            }
                        } catch (Throwable th) {
                            th = th;
                            fVar = fVar2;
                            fVar.close();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (IOException unused) {
            }
            this.f3984b.d(fileB.getAbsolutePath());
            this.f3983a.add(str);
            a("%s (%s) was re-linked!", str, str2);
        }
    }

    public File b(Context context, String str, String str2) {
        String strA = this.f3984b.a(str);
        if (h.a(str2)) {
            return new File(a(context), strA);
        }
        return new File(a(context), strA + "." + str2);
    }

    public f(c.b bVar, c.a aVar) {
        this.f3983a = new HashSet();
        if (bVar == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        }
        if (aVar == null) {
            throw new IllegalArgumentException("Cannot pass null library installer");
        }
        this.f3984b = bVar;
        this.f3985c = aVar;
    }

    public void a(Context context, String str) {
        a(context, str, (String) null, (c.InterfaceC0108c) null);
    }

    public void a(Context context, String str, String str2, c.InterfaceC0108c interfaceC0108c) {
        if (context != null) {
            if (!h.a(str)) {
                a("Beginning load of %s...", str);
                if (interfaceC0108c == null) {
                    c(context, str, str2);
                    return;
                }
                try {
                    new Thread(new d(this, context, str, str2, interfaceC0108c)).start();
                    return;
                } catch (Error | Exception e2) {
                    interfaceC0108c.a(e2);
                    return;
                }
            }
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        throw new IllegalArgumentException("Given context is null");
    }

    public File a(Context context) {
        return context.getDir("lib", 0);
    }

    public void a(Context context, String str, String str2) {
        File fileA = a(context);
        File fileB = b(context, str, str2);
        File[] fileArrListFiles = fileA.listFiles(new e(this, this.f3984b.a(str)));
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (this.f3986d || !file.getAbsolutePath().equals(fileB.getAbsolutePath())) {
                try {
                    file.delete();
                } catch (SecurityException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void a(String str, Object... objArr) {
        a(String.format(Locale.US, str, objArr));
    }

    public void a(String str) {
        c.d dVar = this.f3988f;
        if (dVar != null) {
            dVar.a(str);
        }
    }
}
