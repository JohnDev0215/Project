package spi;

import java.io.File;
import spi.g6;

/* JADX INFO: loaded from: classes.dex */
public class n6 implements g6.a {
    public final long a;
    public final c b;

    public class a implements c {
        public final /* synthetic */ String a;

        public a(String str) {
            this.a = str;
        }

        @Override // spi.n6.c
        public final File a() {
            return new File(this.a);
        }
    }

    public class b implements c {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        public b(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // spi.n6.c
        public final File a() {
            return new File(this.a, this.b);
        }
    }

    public interface c {
        File a();
    }

    public n6(String str, long j) {
        this(new a(str), j);
    }

    public n6(String str, String str2, long j) {
        this(new b(str, str2), j);
    }

    public n6(c cVar, long j) {
        this.a = j;
        this.b = cVar;
    }
}
