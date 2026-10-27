package V2;

import p2.C2091f;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9334a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f9335b;
    public boolean c;

    public C2091f a() {
        if (this.f9334a || !(this.f9335b || this.c)) {
            return new C2091f(this);
        }
        throw new IllegalStateException("Secondary offload attribute fields are true but primary isFormatSupported is false");
    }

    public boolean b() {
        return (this.c || this.f9335b) && this.f9334a;
    }
}
