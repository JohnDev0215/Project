package p;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class j40 {
    public static final Charset d = Charset.forName("UTF-8");
    public static final int e = 15;
    public static final i40 f = new i40();
    public static final nv0 g = new nv0(3);
    public static final b30 h = new b30(2);
    public final AtomicInteger a = new AtomicInteger(0);
    public final gu0 b;
    public final com.google.firebase.crashlytics.internal.settings.a c;

    public j40(gu0 gu0Var, com.google.firebase.crashlytics.internal.settings.a aVar) {
        this.b = gu0Var;
        this.c = aVar;
    }

    public static void a(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((File) it.next()).delete();
        }
    }

    public static String e(File file) {
        byte[] bArr = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            try {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    String str = new String(byteArrayOutputStream.toByteArray(), d);
                    fileInputStream.close();
                    return str;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static void f(File file, String str) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), d);
        try {
            outputStreamWriter.write(str);
            outputStreamWriter.close();
        } catch (Throwable th) {
            try {
                outputStreamWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final ArrayList b() {
        ArrayList arrayList = new ArrayList();
        gu0 gu0Var = this.b;
        arrayList.addAll(gu0.p(((File) gu0Var.e).listFiles()));
        arrayList.addAll(gu0.p(((File) gu0Var.f).listFiles()));
        nv0 nv0Var = g;
        Collections.sort(arrayList, nv0Var);
        List listP = gu0.p(((File) gu0Var.d).listFiles());
        Collections.sort(listP, nv0Var);
        arrayList.addAll(listP);
        return arrayList;
    }

    public final NavigableSet c() {
        return new TreeSet(gu0.p(((File) this.b.c).list())).descendingSet();
    }

    public final void d(nh nhVar, String str, boolean z) {
        gu0 gu0Var = this.b;
        int i = this.c.b().a.b;
        f.getClass();
        try {
            f(gu0Var.l(str, u4.j("event", String.format(Locale.US, "%010d", Integer.valueOf(this.a.getAndIncrement())), z ? "_" : "")), i40.a.t(nhVar));
        } catch (IOException unused) {
        }
        b30 b30Var = new b30(1);
        gu0Var.getClass();
        File file = new File((File) gu0Var.c, str);
        file.mkdirs();
        List<File> listP = gu0.p(file.listFiles(b30Var));
        Collections.sort(listP, new nv0(2));
        int size = listP.size();
        for (File file2 : listP) {
            if (size <= i) {
                return;
            }
            gu0.o(file2);
            size--;
        }
    }
}
