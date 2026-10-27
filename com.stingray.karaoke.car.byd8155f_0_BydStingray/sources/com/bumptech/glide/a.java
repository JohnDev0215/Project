package com.bumptech.glide;

import A4.q;
import y4.C2741a;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Cloneable {
    public C2741a l;

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final a clone() {
        try {
            return (a) super.clone();
        } catch (CloneNotSupportedException e10) {
            throw new RuntimeException(e10);
        }
    }

    public final boolean b(Object obj) {
        if (obj instanceof a) {
            return q.b(this.l, ((a) obj).l);
        }
        return false;
    }

    public final int c() {
        C2741a c2741a = this.l;
        if (c2741a != null) {
            return c2741a.hashCode();
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && b(obj);
    }

    public final int hashCode() {
        return c();
    }
}
