package com.bumptech.glide.s;

import com.bumptech.glide.load.g;
import com.bumptech.glide.t.j;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class b implements g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f2530b;

    public b(Object obj) {
        this.f2530b = j.d(obj);
    }

    @Override // com.bumptech.glide.load.g
    public void a(MessageDigest messageDigest) {
        messageDigest.update(this.f2530b.toString().getBytes(g.f2011a));
    }

    @Override // com.bumptech.glide.load.g
    public boolean equals(Object obj) {
        if (obj instanceof b) {
            return this.f2530b.equals(((b) obj).f2530b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.g
    public int hashCode() {
        return this.f2530b.hashCode();
    }

    public String toString() {
        return "ObjectKey{object=" + this.f2530b + '}';
    }
}
