package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class SessionManager {
    private static final Logger zza = new Logger("SessionManager");
    private final zzap zzb;
    private final Context zzc;

    public SessionManager(zzap zzapVar, Context context) {
        this.zzb = zzapVar;
        this.zzc = context;
    }

    public void addSessionManagerListener(@NonNull SessionManagerListener<Session> sessionManagerListener) throws NullPointerException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        addSessionManagerListener(sessionManagerListener, Session.class);
    }

    public void endCurrentSession(boolean z) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            zza.i("End session for %s", this.zzc.getPackageName());
            this.zzb.zzj(true, z);
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "endCurrentSession", zzap.class.getSimpleName());
        }
    }

    @Nullable
    public CastSession getCurrentCastSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Session currentSession = getCurrentSession();
        if (currentSession == null || !(currentSession instanceof CastSession)) {
            return null;
        }
        return (CastSession) currentSession;
    }

    @Nullable
    public Session getCurrentSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return (Session) ObjectWrapper.unwrap(this.zzb.zzf());
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "getWrappedCurrentSession", zzap.class.getSimpleName());
            return null;
        }
    }

    public void removeSessionManagerListener(@NonNull SessionManagerListener<Session> sessionManagerListener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        removeSessionManagerListener(sessionManagerListener, Session.class);
    }

    public void startSession(@NonNull Intent intent) {
        try {
            zza.i("Start session for %s", this.zzc.getPackageName());
            Bundle extras = intent.getExtras();
            if (extras != null && extras.getString("CAST_INTENT_TO_CAST_ROUTE_ID_KEY") != null) {
                String string = extras.getString("CAST_INTENT_TO_CAST_DEVICE_NAME_KEY");
                if (!extras.getBoolean("CAST_INTENT_TO_CAST_NO_TOAST_KEY")) {
                    Toast.makeText(this.zzc, this.zzc.getString(R.string.cast_connecting_to_device, string), 0).show();
                }
                this.zzb.zzm(new Bundle(extras));
                intent.removeExtra("CAST_INTENT_TO_CAST_ROUTE_ID_KEY");
            }
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "startSession", zzap.class.getSimpleName());
        }
    }

    final int zza() {
        try {
            return this.zzb.zze();
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "addCastStateListener", zzap.class.getSimpleName());
            return 1;
        }
    }

    @Nullable
    public final IObjectWrapper zzb() {
        try {
            return this.zzb.zzg();
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "getWrappedThis", zzap.class.getSimpleName());
            return null;
        }
    }

    final void zzc(CastStateListener castStateListener) throws NullPointerException {
        Preconditions.checkNotNull(castStateListener);
        try {
            this.zzb.zzh(new zzs(castStateListener));
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "addCastStateListener", zzap.class.getSimpleName());
        }
    }

    final void zzd(CastStateListener castStateListener) {
        try {
            this.zzb.zzk(new zzs(castStateListener));
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "removeCastStateListener", zzap.class.getSimpleName());
        }
    }

    public <T extends Session> void removeSessionManagerListener(@NonNull SessionManagerListener<T> sessionManagerListener, @NonNull Class cls) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (sessionManagerListener == null) {
            return;
        }
        try {
            this.zzb.zzl(new zzba(sessionManagerListener, cls));
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "removeSessionManagerListener", zzap.class.getSimpleName());
        }
    }

    public <T extends Session> void addSessionManagerListener(@NonNull SessionManagerListener<T> sessionManagerListener, @NonNull Class<T> cls) throws NullPointerException {
        if (sessionManagerListener == null) {
            throw new NullPointerException("SessionManagerListener can't be null");
        }
        Preconditions.checkNotNull(cls);
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            this.zzb.zzi(new zzba(sessionManagerListener, cls));
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "addSessionManagerListener", zzap.class.getSimpleName());
        }
    }
}
