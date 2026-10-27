package c.b.b.r;

import c.b.b.i;
import c.b.b.j;
import c.b.b.r.i.n;
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
    public static i a(c.b.b.t.a aVar) throws JsonParseException {
        boolean z;
        try {
            try {
                aVar.k0();
                z = false;
                try {
                    return n.X.b(aVar);
                } catch (EOFException e2) {
                    e = e2;
                    if (z) {
                        return j.f4169a;
                    }
                    throw new JsonSyntaxException(e);
                }
            } catch (MalformedJsonException e3) {
                throw new JsonSyntaxException(e3);
            } catch (IOException e4) {
                throw new JsonIOException(e4);
            } catch (NumberFormatException e5) {
                throw new JsonSyntaxException(e5);
            }
        } catch (EOFException e6) {
            e = e6;
            z = true;
        }
    }

    public static void b(i iVar, c.b.b.t.b bVar) throws IOException {
        n.X.d(bVar, iVar);
    }

    public static Writer c(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    /* JADX INFO: compiled from: Streams.java */
    public static final class a extends Writer {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Appendable f4214b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final C0096a f4215c = new C0096a();

        /* JADX INFO: renamed from: c.b.b.r.g$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Streams.java */
        public static class C0096a implements CharSequence {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public char[] f4216b;

            @Override // java.lang.CharSequence
            public char charAt(int i2) {
                return this.f4216b[i2];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f4216b.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i2, int i3) {
                return new String(this.f4216b, i2, i3 - i2);
            }
        }

        public a(Appendable appendable) {
            this.f4214b = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) throws IOException {
            C0096a c0096a = this.f4215c;
            c0096a.f4216b = cArr;
            this.f4214b.append(c0096a, i2, i3 + i2);
        }

        @Override // java.io.Writer
        public void write(int i2) throws IOException {
            this.f4214b.append((char) i2);
        }
    }
}
