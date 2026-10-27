package c.a.k;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a extends RuntimeException {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<Throwable> f1811b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f1812c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Throwable f1813d;

    /* JADX INFO: renamed from: c.a.k.a$a, reason: collision with other inner class name */
    static final class C0058a extends RuntimeException {
        C0058a() {
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "Chain of Causes for CompositeException In Order Received =>";
        }
    }

    static abstract class b {
        b() {
        }

        abstract void a(Object obj);
    }

    static final class c extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final PrintStream f1814a;

        c(PrintStream printStream) {
            this.f1814a = printStream;
        }

        @Override // c.a.k.a.b
        void a(Object obj) {
            this.f1814a.println(obj);
        }
    }

    static final class d extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final PrintWriter f1815a;

        d(PrintWriter printWriter) {
            this.f1815a = printWriter;
        }

        @Override // c.a.k.a.b
        void a(Object obj) {
            this.f1815a.println(obj);
        }
    }

    public a(Iterable<? extends Throwable> iterable) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        if (iterable != null) {
            for (Object obj : iterable) {
                if (obj instanceof a) {
                    linkedHashSet.addAll(((a) obj).b());
                } else {
                    linkedHashSet.add(obj == null ? new NullPointerException("Throwable was null!") : obj);
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
        this.f1811b = listUnmodifiableList;
        this.f1812c = listUnmodifiableList.size() + " exceptions occurred. ";
    }

    public a(Throwable... thArr) {
        this(thArr == null ? Collections.singletonList(new NullPointerException("exceptions was null")) : Arrays.asList(thArr));
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

    private Throwable d(Throwable th) {
        Throwable cause = th.getCause();
        if (cause == null || this.f1813d == cause) {
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

    private void e(b bVar) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(this);
        sb.append('\n');
        for (StackTraceElement stackTraceElement : getStackTrace()) {
            sb.append("\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        int i = 1;
        for (Throwable th : this.f1811b) {
            sb.append("  ComposedException ");
            sb.append(i);
            sb.append(" :\n");
            a(sb, th, "\t");
            i++;
        }
        bVar.a(sb.toString());
    }

    public List<Throwable> b() {
        return this.f1811b;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        if (this.f1813d == null) {
            C0058a c0058a = new C0058a();
            HashSet hashSet = new HashSet();
            Iterator<Throwable> it = this.f1811b.iterator();
            Throwable thD = c0058a;
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
            this.f1813d = c0058a;
        }
        return this.f1813d;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.f1812c;
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
