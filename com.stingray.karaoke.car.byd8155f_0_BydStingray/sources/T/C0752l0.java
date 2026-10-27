package T;

/* JADX INFO: renamed from: T.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0752l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0750k0 f8266a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f8267b;
    public final G0 c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f8268e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8269f = true;

    public C0752l0(AbstractC0750k0 abstractC0750k0, Object obj, boolean z6, G0 g02, boolean z10) {
        this.f8266a = abstractC0750k0;
        this.f8267b = z6;
        this.c = g02;
        this.d = z10;
        this.f8268e = obj;
    }

    public final Object a() {
        if (this.f8267b) {
            return null;
        }
        Object obj = this.f8268e;
        if (obj != null) {
            return obj;
        }
        C0735d.z("Unexpected form of a provided value");
        throw null;
    }
}
