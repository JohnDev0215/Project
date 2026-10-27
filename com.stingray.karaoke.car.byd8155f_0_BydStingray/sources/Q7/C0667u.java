package Q7;

import o8.C2061f;

/* JADX INFO: renamed from: Q7.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0667u extends S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2061f f7207a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final I8.d f7208b;

    public C0667u(C2061f c2061f, I8.d dVar) {
        A7.m.f(dVar, "underlyingType");
        this.f7207a = c2061f;
        this.f7208b = dVar;
    }

    public final String toString() {
        return "InlineClassRepresentation(underlyingPropertyName=" + this.f7207a + ", underlyingType=" + this.f7208b + ')';
    }
}
