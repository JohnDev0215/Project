package com.byd.cleardata.glide;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2663a;

    public f(String str) {
        this.f2663a = str;
    }

    public String a() {
        return this.f2663a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || f.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f2663a, ((f) obj).f2663a);
    }

    public int hashCode() {
        return Objects.hash(this.f2663a);
    }
}
