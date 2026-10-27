package com.google.android.gms.internal.cast;

import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzds extends zzdn {
    private final Object zza;

    zzds(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzds) {
            return this.zza.equals(((zzds) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        String string = this.zza.toString();
        StringBuilder sb = new StringBuilder(string.length() + 13);
        sb.append("Optional.of(");
        sb.append(string);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.cast.zzdn
    public final Object zza() {
        return this.zza;
    }
}
