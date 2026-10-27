package h;

import e.q;
import e.s;
import e.u;
import e.v;
import e.y;
import e.z;
import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: RequestBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f5801a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5802b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HttpUrl f5803c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    public String f5804d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    public HttpUrl.Builder f5805e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final y.a f5806f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @Nullable
    public u f5807g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f5808h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @Nullable
    public v.a f5809i;

    @Nullable
    public q.a j;

    @Nullable
    public z k;

    /* JADX INFO: compiled from: RequestBuilder.java */
    public static class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final z f5810a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final u f5811b;

        public a(z zVar, u uVar) {
            this.f5810a = zVar;
            this.f5811b = uVar;
        }

        @Override // e.z
        public long a() throws IOException {
            return this.f5810a.a();
        }

        @Override // e.z
        public u b() {
            return this.f5811b;
        }

        @Override // e.z
        public void h(f.d dVar) throws IOException {
            this.f5810a.h(dVar);
        }
    }

    public k(String str, HttpUrl httpUrl, @Nullable String str2, @Nullable s sVar, @Nullable u uVar, boolean z, boolean z2, boolean z3) {
        this.f5802b = str;
        this.f5803c = httpUrl;
        this.f5804d = str2;
        y.a aVar = new y.a();
        this.f5806f = aVar;
        this.f5807g = uVar;
        this.f5808h = z;
        if (sVar != null) {
            aVar.e(sVar);
        }
        if (z2) {
            this.j = new q.a();
        } else if (z3) {
            v.a aVar2 = new v.a();
            this.f5809i = aVar2;
            aVar2.f(v.f3646e);
        }
    }

    public static String h(String str, boolean z) {
        int length = str.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt < 32 || iCodePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                f.c cVar = new f.c();
                cVar.l0(str, 0, iCharCount);
                i(cVar, str, iCharCount, length, z);
                return cVar.G();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str;
    }

    public static void i(f.c cVar, String str, int i2, int i3, boolean z) {
        f.c cVar2 = null;
        while (i2 < i3) {
            int iCodePointAt = str.codePointAt(i2);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt < 32 || iCodePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                    if (cVar2 == null) {
                        cVar2 = new f.c();
                    }
                    cVar2.m0(iCodePointAt);
                    while (!cVar2.z()) {
                        int i4 = cVar2.readByte() & 255;
                        cVar.A(37);
                        char[] cArr = f5801a;
                        cVar.A(cArr[(i4 >> 4) & 15]);
                        cVar.A(cArr[i4 & 15]);
                    }
                } else {
                    cVar.m0(iCodePointAt);
                }
            }
            i2 += Character.charCount(iCodePointAt);
        }
    }

    public void a(String str, String str2, boolean z) {
        if (z) {
            this.j.b(str, str2);
        } else {
            this.j.a(str, str2);
        }
    }

    public void b(String str, String str2) {
        if (!"Content-Type".equalsIgnoreCase(str)) {
            this.f5806f.a(str, str2);
            return;
        }
        u uVarC = u.c(str2);
        if (uVarC != null) {
            this.f5807g = uVarC;
            return;
        }
        throw new IllegalArgumentException("Malformed content type: " + str2);
    }

    public void c(s sVar, z zVar) {
        this.f5809i.c(sVar, zVar);
    }

    public void d(v.b bVar) {
        this.f5809i.d(bVar);
    }

    public void e(String str, String str2, boolean z) {
        String str3 = this.f5804d;
        if (str3 == null) {
            throw new AssertionError();
        }
        this.f5804d = str3.replace("{" + str + "}", h(str2, z));
    }

    public void f(String str, @Nullable String str2, boolean z) {
        String str3 = this.f5804d;
        if (str3 != null) {
            HttpUrl.Builder builderP = this.f5803c.p(str3);
            this.f5805e = builderP;
            if (builderP == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.f5803c + ", Relative: " + this.f5804d);
            }
            this.f5804d = null;
        }
        if (z) {
            this.f5805e.a(str, str2);
        } else {
            this.f5805e.b(str, str2);
        }
    }

    public y g() {
        HttpUrl httpUrlC;
        HttpUrl.Builder builder = this.f5805e;
        if (builder != null) {
            httpUrlC = builder.c();
        } else {
            httpUrlC = this.f5803c.C(this.f5804d);
            if (httpUrlC == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.f5803c + ", Relative: " + this.f5804d);
            }
        }
        z aVar = this.k;
        if (aVar == null) {
            q.a aVar2 = this.j;
            if (aVar2 != null) {
                aVar = aVar2.c();
            } else {
                v.a aVar3 = this.f5809i;
                if (aVar3 != null) {
                    aVar = aVar3.e();
                } else if (this.f5808h) {
                    aVar = z.f(null, new byte[0]);
                }
            }
        }
        u uVar = this.f5807g;
        if (uVar != null) {
            if (aVar != null) {
                aVar = new a(aVar, uVar);
            } else {
                this.f5806f.a("Content-Type", uVar.toString());
            }
        }
        return this.f5806f.h(httpUrlC).f(this.f5802b, aVar).b();
    }

    public void j(z zVar) {
        this.k = zVar;
    }

    public void k(Object obj) {
        this.f5804d = obj.toString();
    }
}
