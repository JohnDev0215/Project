package u4;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: compiled from: CompositeException.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends RuntimeException {
    private static final long serialVersionUID = 3026362227162912146L;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<Throwable> f9657e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f9658g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Throwable f9659h;

    /* JADX INFO: renamed from: u4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CompositeException.java */
    static final class C0164a extends RuntimeException {
        private static final long serialVersionUID = 3875212506787802066L;

        C0164a() {
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "Chain of Causes for CompositeException In Order Received =>";
        }
    }

    /* JADX INFO: compiled from: CompositeException.java */
    static abstract class b {
        b() {
        }

        abstract void a(Object obj);
    }

    /* JADX INFO: compiled from: CompositeException.java */
    static final class c extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final PrintStream f9660a;

        c(PrintStream printStream) {
            this.f9660a = printStream;
        }

        @Override // u4.a.b
        void a(Object obj) {
            this.f9660a.println(obj);
        }
    }

    /* JADX INFO: compiled from: CompositeException.java */
    static final class d extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final PrintWriter f9661a;

        d(PrintWriter printWriter) {
            this.f9661a = printWriter;
        }

        @Override // u4.a.b
        void a(Object obj) {
            this.f9661a.println(obj);
        }
    }

    public a(Iterable<? extends Throwable> iterable) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        if (iterable != null) {
            for (Throwable th : iterable) {
                if (th instanceof a) {
                    linkedHashSet.addAll(((a) th).b());
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException("Throwable was null!"));
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException("errors was null"));
        }
        if (linkedHashSet.isEmpty()) {
            throw new IllegalArgumentException("errors is empty");
        }
        arrayList.addAll(linkedHashSet);
        List<Throwable> listUnmodifiableList = Collections.unmodifiableList(arrayList);
        this.f9657e = listUnmodifiableList;
        this.f9658g = listUnmodifiableList.size() + " exceptions occurred. ";
    }

    private void a(StringBuilder sb, Throwable th, String str) {
        sb.append(str);
        sb.append(th);
        sb.append('\n');
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            sb.append("\t\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        if (th.getCause() != null) {
            sb.append("\tCaused by: ");
            a(sb, th.getCause(), "");
        }
    }

    private List<Throwable> c(Throwable th) {
        ArrayList arrayList = new ArrayList();
        Throwable cause = th.getCause();
        if (cause != null && cause != th) {
            while (true) {
                arrayList.add(cause);
                Throwable cause2 = cause.getCause();
                if (cause2 == null || cause2 == cause) {
                    break;
                }
                cause = cause2;
            }
        }
        return arrayList;
    }

    private void e(b bVar) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(this);
        sb.append('\n');
        for (StackTraceElement stackTraceElement : getStackTrace()) {
            sb.append("\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        int i7 = 1;
        for (Throwable th : this.f9657e) {
            sb.append("  ComposedException ");
            sb.append(i7);
            sb.append(" :\n");
            a(sb, th, "\t");
            i7++;
        }
        bVar.a(sb.toString());
    }

    public List<Throwable> b() {
        return this.f9657e;
    }

    Throwable d(Throwable th) {
        Throwable cause = th.getCause();
        if (cause == null || th == cause) {
            return th;
        }
        while (true) {
            Throwable cause2 = cause.getCause();
            if (cause2 == null || cause2 == cause) {
                break;
            }
            cause = cause2;
        }
        return cause;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        if (this.f9659h == null) {
            C0164a c0164a = new C0164a();
            HashSet hashSet = new HashSet();
            Iterator<Throwable> it = this.f9657e.iterator();
            Throwable thD = c0164a;
            while (it.hasNext()) {
                Throwable next = it.next();
                if (!hashSet.contains(next)) {
                    hashSet.add(next);
                    for (Throwable th : c(next)) {
                        if (hashSet.contains(th)) {
                            next = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                        } else {
                            hashSet.add(th);
                        }
                    }
                    try {
                        thD.initCause(next);
                    } catch (Throwable unused) {
                    }
                    thD = d(thD);
                }
            }
            this.f9659h = c0164a;
        }
        return this.f9659h;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.f9658g;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        e(new c(printStream));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        e(new d(printWriter));
    }
}
