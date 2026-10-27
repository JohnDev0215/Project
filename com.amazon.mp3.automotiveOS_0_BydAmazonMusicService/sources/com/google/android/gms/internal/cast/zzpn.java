package com.google.android.gms.internal.cast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzpn {
    private static final zzpn zzb = new zzpn(true);
    final zzrz zza = new zzrp(16);
    private boolean zzc;
    private boolean zzd;

    private zzpn() {
    }

    public static zzpn zza() {
        throw null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void zzd(com.google.android.gms.internal.cast.zzpm r4, java.lang.Object r5) {
        /*
            com.google.android.gms.internal.cast.zzss r0 = r4.zzb()
            com.google.android.gms.internal.cast.zzqc.zze(r5)
            com.google.android.gms.internal.cast.zzss r1 = com.google.android.gms.internal.cast.zzss.zza
            com.google.android.gms.internal.cast.zzst r1 = com.google.android.gms.internal.cast.zzst.INT
            com.google.android.gms.internal.cast.zzst r0 = r0.zza()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L41;
                case 1: goto L3e;
                case 2: goto L3b;
                case 3: goto L38;
                case 4: goto L35;
                case 5: goto L32;
                case 6: goto L29;
                case 7: goto L20;
                case 8: goto L17;
                default: goto L16;
            }
        L16:
            goto L46
        L17:
            boolean r0 = r5 instanceof com.google.android.gms.internal.cast.zzrb
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof com.google.android.gms.internal.cast.zzqg
            if (r0 == 0) goto L46
            goto L45
        L20:
            boolean r0 = r5 instanceof java.lang.Integer
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof com.google.android.gms.internal.cast.zzpv
            if (r0 == 0) goto L46
            goto L45
        L29:
            boolean r0 = r5 instanceof com.google.android.gms.internal.cast.zzoy
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof byte[]
            if (r0 == 0) goto L46
            goto L45
        L32:
            boolean r0 = r5 instanceof java.lang.String
            goto L43
        L35:
            boolean r0 = r5 instanceof java.lang.Boolean
            goto L43
        L38:
            boolean r0 = r5 instanceof java.lang.Double
            goto L43
        L3b:
            boolean r0 = r5 instanceof java.lang.Float
            goto L43
        L3e:
            boolean r0 = r5 instanceof java.lang.Long
            goto L43
        L41:
            boolean r0 = r5 instanceof java.lang.Integer
        L43:
            if (r0 == 0) goto L46
        L45:
            return
        L46:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            int r3 = r4.zza()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            r2 = 1
            com.google.android.gms.internal.cast.zzss r4 = r4.zzb()
            com.google.android.gms.internal.cast.zzst r4 = r4.zza()
            r1[r2] = r4
            r4 = 2
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getName()
            r1[r4] = r5
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r4 = java.lang.String.format(r4, r1)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzpn.zzd(com.google.android.gms.internal.cast.zzpm, java.lang.Object):void");
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzpn zzpnVar = new zzpn();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry entryZzg = this.zza.zzg(i);
            zzpnVar.zzc((zzpm) entryZzg.getKey(), entryZzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzpnVar.zzc((zzpm) entry.getKey(), entry.getValue());
        }
        zzpnVar.zzd = this.zzd;
        return zzpnVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzpn) {
            return this.zza.equals(((zzpn) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzc) {
            return;
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzc(zzpm zzpmVar, Object obj) {
        if (!zzpmVar.zzc()) {
            zzd(zzpmVar, obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(zzpmVar, arrayList.get(i));
            }
            obj = arrayList;
        }
        if (obj instanceof zzqg) {
            this.zzd = true;
        }
        this.zza.put(zzpmVar, obj);
    }

    private zzpn(boolean z) {
        zzb();
        zzb();
    }
}
