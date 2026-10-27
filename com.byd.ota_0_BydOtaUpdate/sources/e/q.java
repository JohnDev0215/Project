package e;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: FormBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class q extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final u f3625a = u.c("application/x-www-form-urlencoded");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<String> f3626b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<String> f3627c;

    /* JADX INFO: compiled from: FormBody.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f3628a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<String> f3629b = new ArrayList();

        public a a(String str, String str2) {
            this.f3628a.add(HttpUrl.b(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            this.f3629b.add(HttpUrl.b(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            return this;
        }

        public a b(String str, String str2) {
            this.f3628a.add(HttpUrl.b(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            this.f3629b.add(HttpUrl.b(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            return this;
        }

        public q c() {
            return new q(this.f3628a, this.f3629b);
        }
    }

    public q(List<String> list, List<String> list2) {
        this.f3626b = e.d0.c.n(list);
        this.f3627c = e.d0.c.n(list2);
    }

    @Override // e.z
    public long a() {
        return i(null, true);
    }

    @Override // e.z
    public u b() {
        return f3625a;
    }

    @Override // e.z
    public void h(f.d dVar) throws IOException {
        i(dVar, false);
    }

    public final long i(@Nullable f.d dVar, boolean z) {
        f.c cVar = z ? new f.c() : dVar.a();
        int size = this.f3626b.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                cVar.A(38);
            }
            cVar.w(this.f3626b.get(i2));
            cVar.A(61);
            cVar.w(this.f3627c.get(i2));
        }
        if (!z) {
            return 0L;
        }
        long jY = cVar.Y();
        cVar.H();
        return jY;
    }
}
