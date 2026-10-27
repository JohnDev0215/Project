package org.chromium.components.commerce.core;

import android.text.TextUtils;

/* JADX INFO: compiled from: CommerceSubscription.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public final int a;
    public final int b;
    public final String c;
    public final int d;

    /* JADX INFO: renamed from: org.chromium.components.commerce.core.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CommerceSubscription.java */
    public static class C0129a {
        public C0129a(long j, String str, String str2) {
        }
    }

    public a(int i, int i2, String str, int i3, C0129a c0129a) {
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = i3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return aVar.a == this.a && aVar.b == this.b && TextUtils.equals(aVar.c, this.c) && aVar.d == this.d;
    }

    public final int hashCode() {
        int i = (((1591 + this.a) * 47) + this.b) * 53;
        String str = this.c;
        return ((i + (str == null ? 0 : str.hashCode())) * 59) + this.d;
    }
}
