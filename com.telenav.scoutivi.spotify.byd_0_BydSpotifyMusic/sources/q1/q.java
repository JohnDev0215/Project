package q1;

import android.util.Log;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: GlideException.java */
/* JADX INFO: loaded from: classes.dex */
public final class q extends Exception {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final StackTraceElement[] f9096l = new StackTraceElement[0];
    private static final long serialVersionUID = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<Throwable> f9097e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private n1.f f9098g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private n1.a f9099h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Class<?> f9100i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private String f9101j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Exception f9102k;

    public q(String str) {
        this(str, (List<Throwable>) Collections.emptyList());
    }

    private void a(Throwable th, List<Throwable> list) {
        if (!(th instanceof q)) {
            list.add(th);
            return;
        }
        Iterator<Throwable> it = ((q) th).e().iterator();
        while (it.hasNext()) {
            a(it.next(), list);
        }
    }

    private static void b(List<Throwable> list, Appendable appendable) {
        try {
            c(list, appendable);
        } catch (IOException e7) {
            throw new RuntimeException(e7);
        }
    }

    private static void c(List<Throwable> list, Appendable appendable) throws IOException {
        int size = list.size();
        int i7 = 0;
        while (i7 < size) {
            int i8 = i7 + 1;
            appendable.append("Cause (").append(String.valueOf(i8)).append(" of ").append(String.valueOf(size)).append("): ");
            Throwable th = list.get(i7);
            if (th instanceof q) {
                ((q) th).h(appendable);
            } else {
                d(th, appendable);
            }
            i7 = i8;
        }
    }

    private static void d(Throwable th, Appendable appendable) {
        try {
            appendable.append(th.getClass().toString()).append(": ").append(th.getMessage()).append('\n');
        } catch (IOException unused) {
            throw new RuntimeException(th);
        }
    }

    private void h(Appendable appendable) {
        d(this, appendable);
        b(e(), new a(appendable));
    }

    public List<Throwable> e() {
        return this.f9097e;
    }

    public List<Throwable> f() {
        ArrayList arrayList = new ArrayList();
        a(this, arrayList);
        return arrayList;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        return this;
    }

    public void g(String str) {
        List<Throwable> listF = f();
        int size = listF.size();
        int i7 = 0;
        while (i7 < size) {
            StringBuilder sb = new StringBuilder();
            sb.append("Root cause (");
            int i8 = i7 + 1;
            sb.append(i8);
            sb.append(" of ");
            sb.append(size);
            sb.append(")");
            Log.i(str, sb.toString(), listF.get(i7));
            i7 = i8;
        }
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder sb = new StringBuilder(71);
        sb.append(this.f9101j);
        sb.append(this.f9100i != null ? ", " + this.f9100i : "");
        sb.append(this.f9099h != null ? ", " + this.f9099h : "");
        sb.append(this.f9098g != null ? ", " + this.f9098g : "");
        List<Throwable> listF = f();
        if (listF.isEmpty()) {
            return sb.toString();
        }
        if (listF.size() == 1) {
            sb.append("\nThere was 1 cause:");
        } else {
            sb.append("\nThere were ");
            sb.append(listF.size());
            sb.append(" causes:");
        }
        for (Throwable th : listF) {
            sb.append('\n');
            sb.append(th.getClass().getName());
            sb.append('(');
            sb.append(th.getMessage());
            sb.append(')');
        }
        sb.append("\n call GlideException#logRootCauses(String) for more detail");
        return sb.toString();
    }

    void i(n1.f fVar, n1.a aVar) {
        j(fVar, aVar, null);
    }

    void j(n1.f fVar, n1.a aVar, Class<?> cls) {
        this.f9098g = fVar;
        this.f9099h = aVar;
        this.f9100i = cls;
    }

    public void k(Exception exc) {
        this.f9102k = exc;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public q(String str, Throwable th) {
        this(str, (List<Throwable>) Collections.singletonList(th));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        h(printStream);
    }

    public q(String str, List<Throwable> list) {
        this.f9101j = str;
        setStackTrace(f9096l);
        this.f9097e = list;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        h(printWriter);
    }

    /* JADX INFO: compiled from: GlideException.java */
    private static final class a implements Appendable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Appendable f9103e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f9104g = true;

        a(Appendable appendable) {
            this.f9103e = appendable;
        }

        private CharSequence a(CharSequence charSequence) {
            return charSequence == null ? "" : charSequence;
        }

        @Override // java.lang.Appendable
        public Appendable append(char c7) throws IOException {
            if (this.f9104g) {
                this.f9104g = false;
                this.f9103e.append("  ");
            }
            this.f9104g = c7 == '\n';
            this.f9103e.append(c7);
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence charSequence) {
            CharSequence charSequenceA = a(charSequence);
            return append(charSequenceA, 0, charSequenceA.length());
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence charSequence, int i7, int i8) throws IOException {
            CharSequence charSequenceA = a(charSequence);
            boolean z6 = false;
            if (this.f9104g) {
                this.f9104g = false;
                this.f9103e.append("  ");
            }
            if (charSequenceA.length() > 0 && charSequenceA.charAt(i8 - 1) == '\n') {
                z6 = true;
            }
            this.f9104g = z6;
            this.f9103e.append(charSequenceA, i7, i8);
            return this;
        }
    }
}
