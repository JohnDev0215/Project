package com.bumptech.glide.n;

import com.bumptech.glide.load.c;
import com.bumptech.glide.o.j;
import java.security.MessageDigest;

/* JADX INFO: compiled from: ObjectKey.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f3444b;

    public b(Object obj) {
        this.f3444b = j.d(obj);
    }

    @Override // com.bumptech.glide.load.c
    public void a(MessageDigest messageDigest) {
        messageDigest.update(this.f3444b.toString().getBytes(c.f2926a));
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (obj instanceof b) {
            return this.f3444b.equals(((b) obj).f3444b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return this.f3444b.hashCode();
    }

    public String toString() {
        return "ObjectKey{object=" + this.f3444b + '}';
    }
}
