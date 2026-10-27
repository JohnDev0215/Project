package com.google.gson.internal;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.k.n;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: Streams.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static com.google.gson.i a(com.google.gson.stream.a aVar) throws JsonParseException {
        boolean z;
        try {
            try {
                aVar.x();
                z = false;
                try {
                    return n.U.b(aVar);
                } catch (EOFException e2) {
                    e = e2;
                    if (z) {
                        return com.google.gson.j.f4149a;
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

    public static void b(com.google.gson.i iVar, com.google.gson.stream.b bVar) throws IOException {
        n.U.d(bVar, iVar);
    }

    public static Writer c(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    /* JADX INFO: compiled from: Streams.java */
    private static final class a extends Writer {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Appendable f4047b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final C0075a f4048c = new C0075a();

        /* JADX INFO: renamed from: com.google.gson.internal.i$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Streams.java */
        static class C0075a implements CharSequence {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            char[] f4049b;

            C0075a() {
            }

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return this.f4049b[i];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f4049b.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return new String(this.f4049b, i, i2 - i);
            }
        }

        a(Appendable appendable) {
            this.f4047b = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            C0075a c0075a = this.f4048c;
            c0075a.f4049b = cArr;
            this.f4047b.append(c0075a, i, i2 + i);
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.f4047b.append((char) i);
        }
    }
}
