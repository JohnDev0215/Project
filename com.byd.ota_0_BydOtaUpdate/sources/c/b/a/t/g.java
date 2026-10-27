package c.b.a.t;

import c.b.a.k;
import c.b.a.l;
import c.b.a.t.i.n;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: Streams.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static k a(c.b.a.v.a aVar) throws JsonParseException {
        boolean z;
        try {
            try {
                aVar.b0();
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
                    return l.f2911a;
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

    public static void b(k kVar, c.b.a.v.b bVar) throws IOException {
        n.X.d(bVar, kVar);
    }

    public static Writer c(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    /* JADX INFO: compiled from: Streams.java */
    public static final class a extends Writer {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Appendable f2951c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final C0068a f2952d = new C0068a();

        /* JADX INFO: renamed from: c.b.a.t.g$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Streams.java */
        public static class C0068a implements CharSequence {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public char[] f2953c;

            @Override // java.lang.CharSequence
            public char charAt(int i2) {
                return this.f2953c[i2];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f2953c.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i2, int i3) {
                return new String(this.f2953c, i2, i3 - i2);
            }
        }

        public a(Appendable appendable) {
            this.f2951c = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) throws IOException {
            C0068a c0068a = this.f2952d;
            c0068a.f2953c = cArr;
            this.f2951c.append(c0068a, i2, i3 + i2);
        }

        @Override // java.io.Writer
        public void write(int i2) throws IOException {
            this.f2951c.append((char) i2);
        }
    }
}
