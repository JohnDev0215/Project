package com.vivid.spotify.rootui;

import java.util.Objects;

/* JADX INFO: compiled from: TitleItem.java */
/* JADX INFO: loaded from: classes.dex */
class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5517a;

    public y(String str) {
        this.f5517a = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f5517a, ((y) obj).f5517a);
    }

    public int hashCode() {
        return Objects.hash(this.f5517a);
    }
}
