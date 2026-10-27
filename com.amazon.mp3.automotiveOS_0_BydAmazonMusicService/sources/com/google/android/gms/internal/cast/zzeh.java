package com.google.android.gms.internal.cast;

import com.amazon.digitalmusicplayback.AudioFormat;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzeh implements Map, Serializable {

    @CheckForNull
    private transient zzei zza;

    @CheckForNull
    private transient zzei zzb;

    @CheckForNull
    private transient zzea zzc;

    zzeh() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zzeh zzc(Iterable iterable) {
        zzeg zzegVar = new zzeg(iterable instanceof Collection ? iterable.size() : 4);
        zzegVar.zza(iterable);
        zzef zzefVar = zzegVar.zzc;
        if (zzefVar != null) {
            throw zzefVar.zza();
        }
        zzeq zzeqVarZzh = zzeq.zzh(zzegVar.zzb, zzegVar.zza, zzegVar);
        zzef zzefVar2 = zzegVar.zzc;
        if (zzefVar2 == null) {
            return zzeqVarZzh;
        }
        throw zzefVar2.zza();
    }

    public static zzeh zzd() {
        return zzeq.zza;
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @Override // java.util.Map
    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzes.zza(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzei zzeiVar = this.zzb;
        if (zzeiVar != null) {
            return zzeiVar;
        }
        zzei zzeiVarZzf = zzf();
        this.zzb = zzeiVarZzf;
        return zzeiVarZzf;
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size < 0) {
            StringBuilder sb = new StringBuilder(44);
            sb.append("size cannot be negative but was: ");
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder((int) Math.min(((long) size) * 8, AudioFormat.STEREO_RIGHT));
        sb2.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb2.append(", ");
            }
            sb2.append(entry.getKey());
            sb2.append('=');
            sb2.append(entry.getValue());
            z = false;
        }
        sb2.append('}');
        return sb2.toString();
    }

    abstract zzea zza();

    @Override // java.util.Map
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzea values() {
        zzea zzeaVar = this.zzc;
        if (zzeaVar != null) {
            return zzeaVar;
        }
        zzea zzeaVarZza = zza();
        this.zzc = zzeaVarZza;
        return zzeaVarZza;
    }

    abstract zzei zze();

    abstract zzei zzf();

    @Override // java.util.Map
    /* JADX INFO: renamed from: zzg, reason: merged with bridge method [inline-methods] */
    public final zzei entrySet() {
        zzei zzeiVar = this.zza;
        if (zzeiVar != null) {
            return zzeiVar;
        }
        zzei zzeiVarZze = zze();
        this.zza = zzeiVarZze;
        return zzeiVarZze;
    }
}
