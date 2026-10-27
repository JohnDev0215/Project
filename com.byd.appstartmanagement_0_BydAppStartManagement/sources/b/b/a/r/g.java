package b.b.a.r;

import b.b.a.i;
import b.b.a.j;
import b.b.a.r.i.n;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: Streams.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {
    public static i a(b.b.a.t.a aVar) throws JsonParseException {
        boolean z;
        try {
            try {
                aVar.y();
                z = false;
                try {
                    return n.X.b(aVar);
                } catch (EOFException e) {
                    e = e;
                    if (z) {
                        return j.f387a;
                    }
                    throw new JsonSyntaxException(e);
                }
            } catch (MalformedJsonException e2) {
                throw new JsonSyntaxException(e2);
            } catch (IOException e3) {
                throw new JsonIOException(e3);
            } catch (NumberFormatException e4) {
                throw new JsonSyntaxException(e4);
            }
        } catch (EOFException e5) {
            e = e5;
            z = true;
        }
    }

    public static void b(i iVar, b.b.a.t.b bVar) throws IOException {
        n.X.d(bVar, iVar);
    }

    public static Writer c(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    /* JADX INFO: compiled from: Streams.java */
    public static final class a extends Writer {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Appendable f426b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final C0029a f427c = new C0029a();

        /* JADX INFO: renamed from: b.b.a.r.g$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Streams.java */
        public static class C0029a implements CharSequence {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public char[] f428b;

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return this.f428b[i];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f428b.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return new String(this.f428b, i, i2 - i);
            }
        }

        public a(Appendable appendable) {
            this.f426b = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            C0029a c0029a = this.f427c;
            c0029a.f428b = cArr;
            this.f426b.append(c0029a, i, i2 + i);
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.f426b.append((char) i);
        }
    }
}
