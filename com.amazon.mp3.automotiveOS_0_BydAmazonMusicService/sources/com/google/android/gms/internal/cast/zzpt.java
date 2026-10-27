package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzpq;
import com.google.android.gms.internal.cast.zzpt;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzpt<MessageType extends zzpt<MessageType, BuilderType>, BuilderType extends zzpq<MessageType, BuilderType>> extends zzol<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzse zzc = zzse.zzc();
    protected int zzd = -1;

    protected static void zzA(Class cls, zzpt zzptVar) {
        zzb.put(cls, zzptVar);
    }

    static zzpt zzt(Class cls) {
        Map map = zzb;
        zzpt zzptVar = (zzpt) map.get(cls);
        if (zzptVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzptVar = (zzpt) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzptVar == null) {
            zzptVar = (zzpt) ((zzpt) zzsn.zze(cls)).zzb(6, null, null);
            if (zzptVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzptVar);
        }
        return zzptVar;
    }

    protected static zzpy zzu() {
        return zzpu.zze();
    }

    protected static zzqa zzv() {
        return zzqq.zze();
    }

    protected static zzqb zzw() {
        return zzrk.zzd();
    }

    static Object zzy(Method method, Object obj, Object... objArr) {
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

    protected static Object zzz(zzrb zzrbVar, String str, Object[] objArr) {
        return new zzrl(zzrbVar, str, objArr);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzrj.zza().zzb(getClass()).zze(this, (zzpt) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int iZzb = zzrj.zza().zzb(getClass()).zzb(this);
        this.zza = iZzb;
        return iZzb;
    }

    public final String toString() {
        return zzrd.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.cast.zzrb
    public final void zzB(zzpg zzpgVar) throws IOException {
        zzrj.zza().zzb(getClass()).zzi(this, zzph.zza(zzpgVar));
    }

    protected abstract Object zzb(int i, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.cast.zzol
    final int zzn() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.cast.zzol
    final void zzp(int i) {
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.cast.zzrb
    public final int zzq() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int iZza = zzrj.zza().zzb(getClass()).zza(this);
        this.zzd = iZza;
        return iZza;
    }

    protected final zzpq zzr() {
        return (zzpq) zzb(5, null, null);
    }

    @Override // com.google.android.gms.internal.cast.zzrc
    public final /* synthetic */ zzrb zzs() {
        return (zzpt) zzb(6, null, null);
    }

    @Override // com.google.android.gms.internal.cast.zzrb
    public final /* synthetic */ zzra zzx() {
        zzpq zzpqVar = (zzpq) zzb(5, null, null);
        zzpqVar.zzo(this);
        return zzpqVar;
    }
}
