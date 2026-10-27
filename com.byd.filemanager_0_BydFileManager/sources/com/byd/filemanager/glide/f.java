package com.byd.filemanager.glide;

import java.util.Objects;

/* JADX INFO: compiled from: VideoPath.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3567a;

    public f(String str) {
        this.f3567a = str;
    }

    public String a() {
        return this.f3567a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || f.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f3567a, ((f) obj).f3567a);
    }

    public int hashCode() {
        return Objects.hash(this.f3567a);
    }
}
