package com.google.gson.internal;

import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.r;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: Streams.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static com.google.gson.j a(com.google.gson.w.a aVar) {
        boolean z;
        try {
            try {
                aVar.j0();
                z = false;
                try {
                    return TypeAdapters.X.read(aVar);
                } catch (EOFException e2) {
                    e = e2;
                    if (z) {
                        return com.google.gson.l.f4595a;
                    }
                    throw new r(e);
                }
            } catch (com.google.gson.w.d e3) {
                throw new r(e3);
            } catch (IOException e4) {
                throw new com.google.gson.k(e4);
            } catch (NumberFormatException e5) {
                throw new r(e5);
            }
        } catch (EOFException e6) {
            e = e6;
            z = true;
        }
    }

    public static void b(com.google.gson.j jVar, com.google.gson.w.c cVar) {
        TypeAdapters.X.write(cVar, jVar);
    }

    public static Writer c(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    /* JADX INFO: compiled from: Streams.java */
    private static final class a extends Writer {

        /* JADX INFO: renamed from: b */
        private final Appendable f4583b;

        /* JADX INFO: renamed from: c */
        private final C0086a f4584c = new C0086a();

        /* JADX INFO: renamed from: com.google.gson.internal.k$a$a */
        /* JADX INFO: compiled from: Streams.java */
        static class C0086a implements CharSequence {

            /* JADX INFO: renamed from: b */
            char[] f4585b;

            C0086a() {
            }

            @Override // java.lang.CharSequence
            public char charAt(int i2) {
                return this.f4585b[i2];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f4585b.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i2, int i3) {
                return new String(this.f4585b, i2, i3 - i2);
            }
        }

        a(Appendable appendable) {
            this.f4583b = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) throws IOException {
            C0086a c0086a = this.f4584c;
            c0086a.f4585b = cArr;
            this.f4583b.append(c0086a, i2, i3 + i2);
        }

        @Override // java.io.Writer
        public void write(int i2) throws IOException {
            this.f4583b.append((char) i2);
        }
    }
}
