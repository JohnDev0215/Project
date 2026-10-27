package com.bumptech.glide.l;

import com.bumptech.glide.load.c;
import com.bumptech.glide.m.j;
import java.security.MessageDigest;

/* JADX INFO: compiled from: ObjectKey.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f2720b;

    public b(Object obj) {
        this.f2720b = j.d(obj);
    }

    @Override // com.bumptech.glide.load.c
    public void b(MessageDigest messageDigest) {
        messageDigest.update(this.f2720b.toString().getBytes(c.f2733a));
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (obj instanceof b) {
            return this.f2720b.equals(((b) obj).f2720b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return this.f2720b.hashCode();
    }

    public String toString() {
        return "ObjectKey{object=" + this.f2720b + '}';
    }
}
