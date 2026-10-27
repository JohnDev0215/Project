package com.bumptech.glide.load.n;

import android.util.Log;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class q extends Exception {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final StackTraceElement[] f2179b = new StackTraceElement[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<Throwable> f2180c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.bumptech.glide.load.g f2181d;
    private com.bumptech.glide.load.a e;
    private Class<?> f;
    private String g;
    private Exception h;

    private static final class a implements Appendable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Appendable f2182b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2183c = true;

        a(Appendable appendable) {
            this.f2182b = appendable;
        }

        private CharSequence a(CharSequence charSequence) {
            return charSequence == null ? "" : charSequence;
        }

        @Override // java.lang.Appendable
        public Appendable append(char c2) throws IOException {
            if (this.f2183c) {
                this.f2183c = false;
                this.f2182b.append("  ");
            }
            this.f2183c = c2 == '\n';
            this.f2182b.append(c2);
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence charSequence) {
            CharSequence charSequenceA = a(charSequence);
            return append(charSequenceA, 0, charSequenceA.length());
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
            CharSequence charSequenceA = a(charSequence);
            boolean z = false;
            if (this.f2183c) {
                this.f2183c = false;
                this.f2182b.append("  ");
            }
            if (charSequenceA.length() > 0 && charSequenceA.charAt(i2 - 1) == '\n') {
                z = true;
            }
            this.f2183c = z;
            this.f2182b.append(charSequenceA, i, i2);
            return this;
        }
    }

    public q(String str) {
        this(str, (List<Throwable>) Collections.emptyList());
    }

    public q(String str, Throwable th) {
        this(str, (List<Throwable>) Collections.singletonList(th));
    }

    public q(String str, List<Throwable> list) {
        this.g = str;
        setStackTrace(f2179b);
        this.f2180c = list;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void c(List<Throwable> list, Appendable appendable) throws IOException {
        int size = list.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            appendable.append("Cause (").append(String.valueOf(i2)).append(" of ").append(String.valueOf(size)).append("): ");
            Throwable th = list.get(i);
            if (th instanceof q) {
                ((q) th).h(appendable);
            } else {
                d(th, appendable);
            }
            i = i2;
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
        return this.f2180c;
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
        int i = 0;
        while (i < size) {
            StringBuilder sb = new StringBuilder();
            sb.append("Root cause (");
            int i2 = i + 1;
            sb.append(i2);
            sb.append(" of ");
            sb.append(size);
            sb.append(")");
            Log.i(str, sb.toString(), listF.get(i));
            i = i2;
        }
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String str;
        StringBuilder sb = new StringBuilder(71);
        sb.append(this.g);
        sb.append(this.f != null ? ", " + this.f : "");
        sb.append(this.e != null ? ", " + this.e : "");
        sb.append(this.f2181d != null ? ", " + this.f2181d : "");
        List<Throwable> listF = f();
        if (listF.isEmpty()) {
            return sb.toString();
        }
        if (listF.size() == 1) {
            str = "\nThere was 1 cause:";
        } else {
            sb.append("\nThere were ");
            sb.append(listF.size());
            str = " causes:";
        }
        sb.append(str);
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

    void i(com.bumptech.glide.load.g gVar, com.bumptech.glide.load.a aVar) {
        j(gVar, aVar, null);
    }

    void j(com.bumptech.glide.load.g gVar, com.bumptech.glide.load.a aVar, Class<?> cls) {
        this.f2181d = gVar;
        this.e = aVar;
        this.f = cls;
    }

    public void k(Exception exc) {
        this.h = exc;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        h(printStream);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        h(printWriter);
    }
}
