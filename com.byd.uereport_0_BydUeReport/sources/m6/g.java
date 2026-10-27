package m6;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;
import k6.i;
import k6.j;
import n6.n;

/* JADX INFO: compiled from: Streams.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {
    public static i a(q6.a aVar) throws JsonParseException {
        boolean z7;
        try {
            try {
                aVar.R();
                z7 = false;
                try {
                    return n.X.b(aVar);
                } catch (EOFException e8) {
                    e = e8;
                    if (z7) {
                        return j.f5922a;
                    }
                    throw new JsonSyntaxException(e);
                }
            } catch (MalformedJsonException e9) {
                throw new JsonSyntaxException(e9);
            } catch (IOException e10) {
                throw new JsonIOException(e10);
            } catch (NumberFormatException e11) {
                throw new JsonSyntaxException(e11);
            }
        } catch (EOFException e12) {
            e = e12;
            z7 = true;
        }
    }

    public static void b(i iVar, q6.b bVar) throws IOException {
        n.X.d(bVar, iVar);
    }

    public static Writer c(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    /* JADX INFO: compiled from: Streams.java */
    public static final class a extends Writer {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Appendable f6425d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final C0090a f6426e = new C0090a();

        /* JADX INFO: renamed from: m6.g$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Streams.java */
        public static class C0090a implements CharSequence {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public char[] f6427d;

            @Override // java.lang.CharSequence
            public char charAt(int i8) {
                return this.f6427d[i8];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f6427d.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i8, int i9) {
                return new String(this.f6427d, i8, i9 - i8);
            }
        }

        public a(Appendable appendable) {
            this.f6425d = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i8, int i9) throws IOException {
            C0090a c0090a = this.f6426e;
            c0090a.f6427d = cArr;
            this.f6425d.append(c0090a, i8, i9 + i8);
        }

        @Override // java.io.Writer
        public void write(int i8) throws IOException {
            this.f6425d.append((char) i8);
        }
    }
}
