package g4;

import android.util.Log;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class t extends Exception {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final StackTraceElement[] f14952q = new StackTraceElement[0];
    public final List l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public e4.e f14953m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14954n;
    public Class o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f14955p;

    public t(String str) {
        this(str, Collections.emptyList());
    }

    public static void a(Throwable th, ArrayList arrayList) {
        if (!(th instanceof t)) {
            arrayList.add(th);
            return;
        }
        Iterator it = ((t) th).l.iterator();
        while (it.hasNext()) {
            a((Throwable) it.next(), arrayList);
        }
    }

    public static void b(List list, s sVar) throws IOException {
        int size = list.size();
        int i7 = 0;
        while (i7 < size) {
            sVar.append("Cause (");
            int i10 = i7 + 1;
            sVar.append(String.valueOf(i10));
            sVar.append(" of ");
            sVar.append(String.valueOf(size));
            sVar.append("): ");
            Throwable th = (Throwable) list.get(i7);
            if (th instanceof t) {
                ((t) th).e(sVar);
            } else {
                c(th, sVar);
            }
            i7 = i10;
        }
    }

    public static void c(Throwable th, Appendable appendable) {
        try {
            appendable.append(th.getClass().toString()).append(": ").append(th.getMessage()).append('\n');
        } catch (IOException unused) {
            throw new RuntimeException(th);
        }
    }

    public final void d() {
        ArrayList arrayList = new ArrayList();
        a(this, arrayList);
        int size = arrayList.size();
        int i7 = 0;
        while (i7 < size) {
            StringBuilder sb = new StringBuilder("Root cause (");
            int i10 = i7 + 1;
            sb.append(i10);
            sb.append(" of ");
            sb.append(size);
            sb.append(")");
            Log.i("Glide", sb.toString(), (Throwable) arrayList.get(i7));
            i7 = i10;
        }
    }

    public final void e(Appendable appendable) {
        c(this, appendable);
        try {
            b(this.l, new s(appendable));
        } catch (IOException e10) {
            throw new RuntimeException(e10);
        }
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        return this;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        StringBuilder sb = new StringBuilder(71);
        sb.append(this.f14955p);
        sb.append(this.o != null ? ", " + this.o : "");
        int i7 = this.f14954n;
        sb.append(i7 != 0 ? ", ".concat(android.hardware.bydauto.ac.tsimpl.a.w(i7)) : "");
        sb.append(this.f14953m != null ? ", " + this.f14953m : "");
        ArrayList<Throwable> arrayList = new ArrayList();
        a(this, arrayList);
        if (arrayList.isEmpty()) {
            return sb.toString();
        }
        if (arrayList.size() == 1) {
            sb.append("\nThere was 1 root cause:");
        } else {
            sb.append("\nThere were ");
            sb.append(arrayList.size());
            sb.append(" root causes:");
        }
        for (Throwable th : arrayList) {
            sb.append('\n');
            sb.append(th.getClass().getName());
            sb.append('(');
            sb.append(th.getMessage());
            sb.append(')');
        }
        sb.append("\n call GlideException#logRootCauses(String) for more detail");
        return sb.toString();
    }

    @Override // java.lang.Throwable
    public final void printStackTrace() {
        e(System.err);
    }

    public t(String str, List list) {
        this.f14955p = str;
        setStackTrace(f14952q);
        this.l = list;
    }

    @Override // java.lang.Throwable
    public final void printStackTrace(PrintStream printStream) {
        e(printStream);
    }

    @Override // java.lang.Throwable
    public final void printStackTrace(PrintWriter printWriter) {
        e(printWriter);
    }
}
