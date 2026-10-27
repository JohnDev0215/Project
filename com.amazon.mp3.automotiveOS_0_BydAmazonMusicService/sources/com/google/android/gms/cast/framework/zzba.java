package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzba extends zzaq {
    private final SessionManagerListener zza;
    private final Class zzb;

    public zzba(SessionManagerListener sessionManagerListener, Class cls) {
        this.zza = sessionManagerListener;
        this.zzb = cls;
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zzc(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zzb.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionEnded((Session) this.zzb.cast(session), i);
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zzb.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionEnding((Session) this.zzb.cast(session));
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zze(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zzb.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionResumeFailed((Session) this.zzb.cast(session), i);
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zzf(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zzb.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionResumed((Session) this.zzb.cast(session), z);
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zzg(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zzb.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionResuming((Session) this.zzb.cast(session), str);
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zzh(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zzb.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionStartFailed((Session) this.zzb.cast(session), i);
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zzi(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zzb.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionStarted((Session) this.zzb.cast(session), str);
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zzb.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionStarting((Session) this.zzb.cast(session));
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zzk(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zzb.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionSuspended((Session) this.zzb.cast(session), i);
    }
}
