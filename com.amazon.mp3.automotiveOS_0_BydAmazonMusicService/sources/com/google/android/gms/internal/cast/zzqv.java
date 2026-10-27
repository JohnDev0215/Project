package com.google.android.gms.internal.cast;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzqv extends LinkedHashMap {
    private static final zzqv zza;
    private boolean zzb;

    static {
        zzqv zzqvVar = new zzqv();
        zza = zzqvVar;
        zzqvVar.zzb = false;
    }

    private zzqv() {
        this.zzb = true;
    }

    private static int zze(Object obj) {
        if (obj instanceof byte[]) {
            return zzqc.zzb((byte[]) obj);
        }
        if (obj instanceof zzpv) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    private final void zzf() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzf();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        Iterator it = entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            Object value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(((value instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.equals((byte[]) value, (byte[]) obj2) : value.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        Iterator it = entrySet().iterator();
        int iZze = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            iZze += zze(entry.getValue()) ^ zze(entry.getKey());
        }
        return iZze;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        zzf();
        zzqc.zze(obj);
        zzqc.zze(obj2);
        return super.put(obj, obj2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        zzf();
        for (Object obj : map.keySet()) {
            zzqc.zze(obj);
            zzqc.zze(map.get(obj));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        zzf();
        return super.remove(obj);
    }

    public final zzqv zza() {
        return isEmpty() ? new zzqv() : new zzqv(this);
    }

    public final void zzb() {
        this.zzb = false;
    }

    public final void zzc(zzqv zzqvVar) {
        zzf();
        if (zzqvVar.isEmpty()) {
            return;
        }
        putAll(zzqvVar);
    }

    public final boolean zzd() {
        return this.zzb;
    }

    private zzqv(Map map) {
        super(map);
        this.zzb = true;
    }
}
