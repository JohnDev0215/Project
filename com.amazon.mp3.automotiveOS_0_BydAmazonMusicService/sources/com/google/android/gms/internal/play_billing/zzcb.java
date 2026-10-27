package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzbx;
import com.google.android.gms.internal.play_billing.zzcb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzcb<MessageType extends zzcb<MessageType, BuilderType>, BuilderType extends zzbx<MessageType, BuilderType>> extends zzak<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzeh zzc = zzeh.zzc();

    static zzcb zzg(Class cls) {
        Map map = zzb;
        zzcb zzcbVar = (zzcb) map.get(cls);
        if (zzcbVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzcbVar = (zzcb) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzcbVar == null) {
            zzcbVar = (zzcb) ((zzcb) zzeq.zze(cls)).zzt(6, null, null);
            if (zzcbVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzcbVar);
        }
        return zzcbVar;
    }

    protected static zzcb zzi(zzcb zzcbVar, byte[] bArr, zzbn zzbnVar) throws zzci {
        zzcb zzcbVarZzv = zzv(zzcbVar, bArr, 0, bArr.length, zzbnVar);
        if (zzcbVarZzv == null || zzcbVarZzv.zzr()) {
            return zzcbVarZzv;
        }
        zzci zzciVarZza = new zzef(zzcbVarZzv).zza();
        zzciVarZza.zzf(zzcbVarZzv);
        throw zzciVarZza;
    }

    static Object zzk(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static Object zzl(zzdf zzdfVar, String str, Object[] objArr) {
        return new zzdo(zzdfVar, str, objArr);
    }

    protected static void zzo(Class cls, zzcb zzcbVar) {
        zzcbVar.zzn();
        zzb.put(cls, zzcbVar);
    }

    private final int zzu(zzdp zzdpVar) {
        if (zzdpVar != null) {
            return zzdpVar.zza(this);
        }
        return zzdn.zza().zzb(getClass()).zza(this);
    }

    private static zzcb zzv(zzcb zzcbVar, byte[] bArr, int i, int i2, zzbn zzbnVar) throws zzci {
        zzcb zzcbVarZzh = zzcbVar.zzh();
        try {
            zzdp zzdpVarZzb = zzdn.zza().zzb(zzcbVarZzh.getClass());
            zzdpVarZzb.zzh(zzcbVarZzh, bArr, 0, i2, new zzan(zzbnVar));
            zzdpVarZzb.zzf(zzcbVarZzh);
            return zzcbVarZzh;
        } catch (zzci e) {
            e.zzf(zzcbVarZzh);
            throw e;
        } catch (zzef e2) {
            zzci zzciVarZza = e2.zza();
            zzciVarZza.zzf(zzcbVarZzh);
            throw zzciVarZza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzci) {
                throw ((zzci) e3.getCause());
            }
            zzci zzciVar = new zzci(e3);
            zzciVar.zzf(zzcbVarZzh);
            throw zzciVar;
        } catch (IndexOutOfBoundsException unused) {
            zzci zzciVarZzg = zzci.zzg();
            zzciVarZzg.zzf(zzcbVarZzh);
            throw zzciVarZzg;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzdn.zza().zzb(getClass()).zzj(this, (zzcb) obj);
    }

    public final int hashCode() {
        if (zzs()) {
            return zzc();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int iZzc = zzc();
        this.zza = iZzc;
        return iZzc;
    }

    public final String toString() {
        return zzdh.zza(this, super.toString());
    }

    final int zzc() {
        return zzdn.zza().zzb(getClass()).zzb(this);
    }

    protected final zzbx zze() {
        return (zzbx) zzt(5, null, null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdg
    public final /* synthetic */ zzdf zzf() {
        return (zzcb) zzt(6, null, null);
    }

    final zzcb zzh() {
        return (zzcb) zzt(4, null, null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdf
    public final /* synthetic */ zzde zzj() {
        return (zzbx) zzt(5, null, null);
    }

    protected final void zzm() {
        zzdn.zza().zzb(getClass()).zzf(this);
        zzn();
    }

    final void zzn() {
        this.zzd &= Integer.MAX_VALUE;
    }

    final void zzp(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdf
    public final void zzq(zzbi zzbiVar) throws IOException {
        zzdn.zza().zzb(getClass()).zzi(this, zzbj.zza(zzbiVar));
    }

    final boolean zzs() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    protected abstract Object zzt(int i, Object obj, Object obj2);

    public final boolean zzr() {
        byte bByteValue = ((Byte) zzt(1, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzk = zzdn.zza().zzb(getClass()).zzk(this);
        zzt(2, true != zZzk ? null : this, null);
        return zZzk;
    }

    @Override // com.google.android.gms.internal.play_billing.zzak
    final int zza(zzdp zzdpVar) {
        if (zzs()) {
            int iZzu = zzu(zzdpVar);
            if (iZzu >= 0) {
                return iZzu;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + iZzu);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int iZzu2 = zzu(zzdpVar);
        if (iZzu2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | iZzu2;
            return iZzu2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + iZzu2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdf
    public final int zzd() {
        int iZzu;
        if (zzs()) {
            iZzu = zzu(null);
            if (iZzu < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + iZzu);
            }
        } else {
            iZzu = this.zzd & Integer.MAX_VALUE;
            if (iZzu == Integer.MAX_VALUE) {
                iZzu = zzu(null);
                if (iZzu < 0) {
                    throw new IllegalStateException("serialized size must be non-negative, was " + iZzu);
                }
                this.zzd = (this.zzd & Integer.MIN_VALUE) | iZzu;
            }
        }
        return iZzu;
    }
}
