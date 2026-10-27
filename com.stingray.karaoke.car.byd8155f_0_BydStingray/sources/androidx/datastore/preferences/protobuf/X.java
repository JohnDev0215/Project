package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes.dex */
public final class X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0957u f11821a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f11822b;
    public final Object[] c;
    public final int d;

    public X(AbstractC0957u abstractC0957u, String str, Object[] objArr) {
        this.f11821a = abstractC0957u;
        this.f11822b = str;
        this.c = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.d = cCharAt;
            return;
        }
        int i7 = cCharAt & 8191;
        int i10 = 1;
        int i11 = 13;
        while (true) {
            int i12 = i10 + 1;
            char cCharAt2 = str.charAt(i10);
            if (cCharAt2 < 55296) {
                this.d = i7 | (cCharAt2 << i11);
                return;
            } else {
                i7 |= (cCharAt2 & 8191) << i11;
                i11 += 13;
                i10 = i12;
            }
        }
    }

    public final AbstractC0937a a() {
        return this.f11821a;
    }

    public final Object[] b() {
        return this.c;
    }

    public final String c() {
        return this.f11822b;
    }

    public final int d() {
        return (this.d & 1) == 1 ? 1 : 2;
    }
}
