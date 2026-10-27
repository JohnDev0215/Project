package b.b.a;

import android.content.Context;
import android.util.Log;
import b.b.a.c;
import b.b.a.g.i;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Set<String> f606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final c.b f607b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final c.a f608c;
    protected boolean d;
    protected boolean e;
    protected c.d f;

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f609a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f610b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f611c;
        final /* synthetic */ c.InterfaceC0021c d;

        a(Context context, String str, String str2, c.InterfaceC0021c interfaceC0021c) {
            this.f609a = context;
            this.f610b = str;
            this.f611c = str2;
            this.d = interfaceC0021c;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                d.this.g(this.f609a, this.f610b, this.f611c);
                this.d.b();
            } catch (b.b.a.b | UnsatisfiedLinkError e) {
                this.d.a(e);
            }
        }
    }

    class b implements FilenameFilter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f612a;

        b(String str) {
            this.f612a = str;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.startsWith(this.f612a);
        }
    }

    protected d() {
        this(new e(), new b.b.a.a());
    }

    protected d(c.b bVar, c.a aVar) {
        this.f606a = new HashSet();
        if (bVar == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        }
        if (aVar == null) {
            throw new IllegalArgumentException("Cannot pass null library installer");
        }
        this.f607b = bVar;
        this.f608c = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(Context context, String str, String str2) throws Throwable {
        i iVar;
        if (this.f606a.contains(str) && !this.d) {
            i("%s already loaded previously!", str);
            return;
        }
        try {
            this.f607b.c(str);
            this.f606a.add(str);
            i("%s (%s) was loaded normally!", str, str2);
        } catch (UnsatisfiedLinkError e) {
            i("Loading the library normally failed: %s", Log.getStackTraceString(e));
            i("%s (%s) was not loaded normally, re-linking...", str, str2);
            File fileD = d(context, str, str2);
            if (!fileD.exists() || this.d) {
                if (this.d) {
                    i("Forcing a re-link of %s (%s)...", str, str2);
                }
                b(context, str, str2);
                this.f608c.a(context, this.f607b.d(), this.f607b.a(str), fileD, this);
            }
            try {
                if (this.e) {
                    i iVar2 = null;
                    try {
                        iVar = new i(fileD);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        List<String> listL = iVar.l();
                        iVar.close();
                        Iterator<String> it = listL.iterator();
                        while (it.hasNext()) {
                            e(context, this.f607b.b(it.next()));
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        iVar2 = iVar;
                        if (iVar2 != null) {
                            iVar2.close();
                        }
                        throw th;
                    }
                }
            } catch (IOException unused) {
            }
            this.f607b.e(fileD.getAbsolutePath());
            this.f606a.add(str);
            i("%s (%s) was re-linked!", str, str2);
        }
    }

    protected void b(Context context, String str, String str2) {
        File fileC = c(context);
        File fileD = d(context, str, str2);
        File[] fileArrListFiles = fileC.listFiles(new b(this.f607b.a(str)));
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (this.d || !file.getAbsolutePath().equals(fileD.getAbsolutePath())) {
                file.delete();
            }
        }
    }

    protected File c(Context context) {
        return context.getDir("lib", 0);
    }

    protected File d(Context context, String str, String str2) {
        String strA = this.f607b.a(str);
        if (f.a(str2)) {
            return new File(c(context), strA);
        }
        return new File(c(context), strA + "." + str2);
    }

    public void e(Context context, String str) {
        f(context, str, null, null);
    }

    public void f(Context context, String str, String str2, c.InterfaceC0021c interfaceC0021c) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        }
        if (f.a(str)) {
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        i("Beginning load of %s...", str);
        if (interfaceC0021c == null) {
            g(context, str, str2);
        } else {
            new Thread(new a(context, str, str2, interfaceC0021c)).start();
        }
    }

    public void h(String str) {
        c.d dVar = this.f;
        if (dVar != null) {
            dVar.a(str);
        }
    }

    public void i(String str, Object... objArr) {
        h(String.format(Locale.US, str, objArr));
    }
}
