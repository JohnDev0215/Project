package io.sentry.clientreport;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f16499a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f16500b;

    public c(String str, String str2) {
        this.f16499a = str;
        this.f16500b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return h6.e.i(this.f16499a, cVar.f16499a) && h6.e.i(this.f16500b, cVar.f16500b);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16499a, this.f16500b});
    }
}
