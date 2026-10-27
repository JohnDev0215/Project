package f;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;

/* JADX INFO: compiled from: FormBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class u extends f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final z f5502a = z.c("application/x-www-form-urlencoded");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<String> f5503b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<String> f5504c;

    /* JADX INFO: compiled from: FormBody.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<String> f5505a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<String> f5506b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Nullable
        private final Charset f5507c;

        public a() {
            this(null);
        }

        public a a(String str, String str2) {
            Objects.requireNonNull(str, "name == null");
            Objects.requireNonNull(str2, "value == null");
            this.f5505a.add(x.c(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.f5507c));
            this.f5506b.add(x.c(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.f5507c));
            return this;
        }

        public a b(String str, String str2) {
            Objects.requireNonNull(str, "name == null");
            Objects.requireNonNull(str2, "value == null");
            this.f5505a.add(x.c(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.f5507c));
            this.f5506b.add(x.c(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.f5507c));
            return this;
        }

        public u c() {
            return new u(this.f5505a, this.f5506b);
        }

        public a(@Nullable Charset charset) {
            this.f5505a = new ArrayList();
            this.f5506b = new ArrayList();
            this.f5507c = charset;
        }
    }

    u(List<String> list, List<String> list2) {
        this.f5503b = f.k0.e.s(list);
        this.f5504c = f.k0.e.s(list2);
    }

    private long n(@Nullable BufferedSink bufferedSink, boolean z) {
        Buffer buffer = z ? new Buffer() : bufferedSink.b();
        int size = this.f5503b.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                buffer.J(38);
            }
            buffer.D(this.f5503b.get(i2));
            buffer.J(61);
            buffer.D(this.f5504c.get(i2));
        }
        if (!z) {
            return 0L;
        }
        long jG0 = buffer.g0();
        buffer.R();
        return jG0;
    }

    @Override // f.f0
    public long a() {
        return n(null, true);
    }

    @Override // f.f0
    public z b() {
        return f5502a;
    }

    @Override // f.f0
    public void j(BufferedSink bufferedSink) {
        n(bufferedSink, false);
    }

    public String k(int i2) {
        return this.f5503b.get(i2);
    }

    public String l(int i2) {
        return this.f5504c.get(i2);
    }

    public int m() {
        return this.f5503b.size();
    }
}
