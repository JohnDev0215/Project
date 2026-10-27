package b.c.a.r;

import b.c.a.i;
import b.c.a.j;
import b.c.a.r.i.n;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: Streams.java */
/* JADX INFO: loaded from: classes3.dex */
public final class g {
    public static i a(b.c.a.t.a aVar) throws JsonParseException {
        boolean z;
        try {
            try {
                aVar.E();
                z = false;
            } catch (EOFException e2) {
                e = e2;
                z = true;
            }
            try {
                return n.X.b(aVar);
            } catch (EOFException e3) {
                e = e3;
                if (z) {
                    return j.f2198a;
                }
                throw new JsonSyntaxException(e);
            }
        } catch (MalformedJsonException e4) {
            throw new JsonSyntaxException(e4);
        } catch (IOException e5) {
            throw new JsonIOException(e5);
        } catch (NumberFormatException e6) {
            throw new JsonSyntaxException(e6);
        }
    }

    public static void b(i iVar, b.c.a.t.b bVar) throws IOException {
        n.X.d(bVar, iVar);
    }

    public static Writer c(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    /* JADX INFO: compiled from: Streams.java */
    public static final class a extends Writer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Appendable f2242a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final C0091a f2243b = new C0091a();

        /* JADX INFO: renamed from: b.c.a.r.g$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Streams.java */
        public static class C0091a implements CharSequence {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public char[] f2244a;

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return this.f2244a[i];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f2244a.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return new String(this.f2244a, i, i2 - i);
            }
        }

        public a(Appendable appendable) {
            this.f2242a = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            C0091a c0091a = this.f2243b;
            c0091a.f2244a = cArr;
            this.f2242a.append(c0091a, i, i2 + i);
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.f2242a.append((char) i);
        }
    }
}
