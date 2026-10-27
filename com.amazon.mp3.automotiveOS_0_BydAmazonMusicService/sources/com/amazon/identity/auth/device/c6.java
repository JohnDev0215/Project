package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class c6<T> implements pa<c6<T>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final T f261a;

    public c6(T t) {
        this.f261a = t;
    }

    @Override // com.amazon.identity.auth.device.pa
    public pa a() {
        try {
            return new c6(aa.a(this.f261a));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
