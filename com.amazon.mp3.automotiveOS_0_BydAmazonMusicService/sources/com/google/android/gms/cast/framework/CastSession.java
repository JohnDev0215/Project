package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.mediarouter.media.MediaTransferReceiver;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.cast.zzbr;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class CastSession extends Session {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = new Logger("CastSession");
    private final Context zzc;
    private final Set zzd;

    @Nullable
    private final zzad zze;
    private final CastOptions zzf;
    private final com.google.android.gms.cast.framework.media.internal.zzp zzg;

    @Nullable
    private com.google.android.gms.cast.zzr zzh;

    @Nullable
    private RemoteMediaClient zzi;

    @Nullable
    private CastDevice zzj;

    @Nullable
    private Cast.ApplicationConnectionResult zzk;

    @Nullable
    private com.google.android.gms.internal.cast.zzax zzl;

    @Nullable
    private String zzm;
    private final zzg zzn;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CastSession(Context context, String str, String str2, CastOptions castOptions, com.google.android.gms.cast.framework.media.internal.zzp zzpVar) {
        super(context, str, str2);
        zzg zzgVar = new Object() { // from class: com.google.android.gms.cast.framework.zzg
        };
        this.zzd = new HashSet();
        this.zzc = context.getApplicationContext();
        this.zzf = castOptions;
        this.zzg = zzpVar;
        this.zzn = zzgVar;
        this.zze = com.google.android.gms.internal.cast.zzm.zzb(context, castOptions, zzo(), new zzn(this, null));
    }

    static /* bridge */ /* synthetic */ void zzh(CastSession castSession, int i) {
        castSession.zzg.zze(i);
        com.google.android.gms.cast.zzr zzrVar = castSession.zzh;
        if (zzrVar != null) {
            zzrVar.zzf();
            castSession.zzh = null;
        }
        castSession.zzj = null;
        RemoteMediaClient remoteMediaClient = castSession.zzi;
        if (remoteMediaClient != null) {
            remoteMediaClient.zzo(null);
            castSession.zzi = null;
        }
        castSession.zzk = null;
    }

    static /* bridge */ /* synthetic */ void zzi(CastSession castSession, String str, Task task) {
        if (castSession.zze == null) {
            return;
        }
        try {
            if (task.isSuccessful()) {
                Cast.ApplicationConnectionResult applicationConnectionResult = (Cast.ApplicationConnectionResult) task.getResult();
                castSession.zzk = applicationConnectionResult;
                if (applicationConnectionResult.getStatus() != null && applicationConnectionResult.getStatus().isSuccess()) {
                    zzb.d("%s() -> success result", str);
                    RemoteMediaClient remoteMediaClient = new RemoteMediaClient(new com.google.android.gms.cast.internal.zzap(null));
                    castSession.zzi = remoteMediaClient;
                    remoteMediaClient.zzo(castSession.zzh);
                    castSession.zzi.zzn();
                    castSession.zzg.zzd(castSession.zzi, castSession.getCastDevice());
                    castSession.zze.zzf((ApplicationMetadata) Preconditions.checkNotNull(applicationConnectionResult.getApplicationMetadata()), applicationConnectionResult.getApplicationStatus(), (String) Preconditions.checkNotNull(applicationConnectionResult.getSessionId()), applicationConnectionResult.getWasLaunched());
                    return;
                }
                if (applicationConnectionResult.getStatus() != null) {
                    zzb.d("%s() -> failure result", str);
                    castSession.zze.zzg(applicationConnectionResult.getStatus().getStatusCode());
                    return;
                }
            } else {
                Exception exception = task.getException();
                if (exception instanceof ApiException) {
                    castSession.zze.zzg(((ApiException) exception).getStatusCode());
                    return;
                }
            }
            castSession.zze.zzg(2476);
        } catch (RemoteException e) {
            zzb.d(e, "Unable to call %s on %s.", "methods", zzad.class.getSimpleName());
        }
    }

    static /* bridge */ /* synthetic */ void zzj(final CastSession castSession) {
        com.google.android.gms.cast.zzr zzrVar = castSession.zzh;
        if (zzrVar == null) {
            return;
        }
        final String[] strArr = {"com.google.android.gms.cast.CLIENT_INFO_PLAYBACK_SESSION_NAME"};
        final zzbr zzbrVar = (zzbr) zzrVar;
        Task taskDoRead = zzbrVar.doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzav
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzbr zzbrVar2 = zzbrVar;
                String[] strArr2 = strArr;
                ((com.google.android.gms.cast.internal.zzae) ((com.google.android.gms.cast.internal.zzx) obj).getService()).zzg(new zzbi(zzbrVar2, (TaskCompletionSource) obj2), strArr2);
            }
        }).setFeatures(com.google.android.gms.cast.zzat.zzm).setMethodKey(8433).setAutoResolveMissingFeatures(false).build());
        if (taskDoRead != null) {
            taskDoRead.addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.gms.cast.framework.zzj
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    this.zza.zzl((Bundle) obj);
                }
            });
        }
    }

    private final void zzp(Bundle bundle) {
        CastDevice fromBundle = CastDevice.getFromBundle(bundle);
        this.zzj = fromBundle;
        if (fromBundle == null) {
            if (isResuming()) {
                notifyFailedToResumeSession(2153);
                return;
            } else {
                notifyFailedToStartSession(2151);
                return;
            }
        }
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        zzo zzoVar = null;
        byte b = 0;
        if (zzrVar != null) {
            zzrVar.zzf();
            this.zzh = null;
        }
        zzb.d("Acquiring a connection to Google Play Services for %s", this.zzj);
        CastDevice castDevice = (CastDevice) Preconditions.checkNotNull(this.zzj);
        Bundle bundle2 = new Bundle();
        CastOptions castOptions = this.zzf;
        CastMediaOptions castMediaOptions = castOptions == null ? null : castOptions.getCastMediaOptions();
        NotificationOptions notificationOptions = castMediaOptions == null ? null : castMediaOptions.getNotificationOptions();
        boolean z = castMediaOptions != null && castMediaOptions.zza();
        Intent intent = new Intent(this.zzc, (Class<?>) MediaTransferReceiver.class);
        intent.setPackage(this.zzc.getPackageName());
        boolean z2 = !this.zzc.getPackageManager().queryBroadcastReceivers(intent, 0).isEmpty();
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_FRAMEWORK_NOTIFICATION_ENABLED", notificationOptions != null);
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_REMOTE_CONTROL_NOTIFICATION_ENABLED", z);
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_ALWAYS_FOLLOW_SESSION_ENABLED", z2);
        Cast.CastOptions.Builder builder = new Cast.CastOptions.Builder(castDevice, new zzp(this, zzoVar));
        builder.zzc(bundle2);
        com.google.android.gms.cast.zzr zzrVarZza = Cast.zza(this.zzc, builder.build());
        zzrVarZza.zzk(new zzr(this, b == true ? 1 : 0));
        this.zzh = zzrVarZza;
        zzrVarZza.zze();
    }

    public void addCastListener(@NonNull Cast.Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzd.add(listener);
        }
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void end(boolean z) {
        zzad zzadVar = this.zze;
        if (zzadVar != null) {
            try {
                zzadVar.zze(z, 0);
            } catch (RemoteException e) {
                zzb.d(e, "Unable to call %s on %s.", "disconnectFromDevice", zzad.class.getSimpleName());
            }
            notifySessionEnded(0);
            com.google.android.gms.internal.cast.zzax zzaxVar = this.zzl;
            if (zzaxVar != null) {
                zzaxVar.zzd();
            }
        }
    }

    public int getActiveInputState() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        if (zzrVar != null) {
            return zzrVar.zzb();
        }
        return -1;
    }

    @Nullable
    public Cast.ApplicationConnectionResult getApplicationConnectionResult() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzk;
    }

    @Nullable
    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        if (zzrVar != null) {
            return zzrVar.zzd();
        }
        return null;
    }

    @Nullable
    public String getApplicationStatus() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        if (zzrVar != null) {
            return zzrVar.zzj();
        }
        return null;
    }

    @Nullable
    public CastDevice getCastDevice() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzj;
    }

    @Nullable
    public RemoteMediaClient getRemoteMediaClient() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzi;
    }

    @Override // com.google.android.gms.cast.framework.Session
    public long getSessionRemainingTimeMs() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        RemoteMediaClient remoteMediaClient = this.zzi;
        if (remoteMediaClient == null) {
            return 0L;
        }
        return remoteMediaClient.getStreamDuration() - this.zzi.getApproximateStreamPosition();
    }

    public int getStandbyState() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        if (zzrVar != null) {
            return zzrVar.zzc();
        }
        return -1;
    }

    public double getVolume() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        if (zzrVar != null) {
            return zzrVar.zza();
        }
        return 0.0d;
    }

    public boolean isMute() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        return zzrVar != null && zzrVar.zzl();
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void onResuming(@NonNull Bundle bundle) {
        this.zzj = CastDevice.getFromBundle(bundle);
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void onStarting(@Nullable Bundle bundle) {
        this.zzj = CastDevice.getFromBundle(bundle);
    }

    public void removeCastListener(@NonNull Cast.Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzd.remove(listener);
        }
    }

    public void removeMessageReceivedCallbacks(@NonNull String str) throws IOException, IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        if (zzrVar != null) {
            zzrVar.zzg(str);
        }
    }

    public void requestStatus() throws IllegalStateException, IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        if (zzrVar != null) {
            ((zzbr) zzrVar).doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzay
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) throws RemoteException {
                    int i = zzbr.zzf;
                    ((com.google.android.gms.cast.internal.zzae) ((com.google.android.gms.cast.internal.zzx) obj).getService()).zzm();
                    ((TaskCompletionSource) obj2).setResult(null);
                }
            }).setMethodKey(8404).build());
        }
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void resume(@NonNull Bundle bundle) {
        zzp(bundle);
    }

    @NonNull
    public PendingResult<Status> sendMessage(@NonNull String str, @NonNull String str2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        return zzrVar == null ? PendingResults.immediatePendingResult(new Status(17)) : com.google.android.gms.internal.cast.zzau.zza(zzrVar.zzh(str, str2), new com.google.android.gms.internal.cast.zzat() { // from class: com.google.android.gms.cast.framework.zzi
        }, new com.google.android.gms.internal.cast.zzat() { // from class: com.google.android.gms.cast.framework.zzh
        });
    }

    public void setMessageReceivedCallbacks(@NonNull String str, @NonNull Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        if (zzrVar != null) {
            zzrVar.zzi(str, messageReceivedCallback);
        }
    }

    public void setMute(final boolean z) throws IllegalStateException, IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        if (zzrVar != null) {
            final zzbr zzbrVar = (zzbr) zzrVar;
            zzbrVar.doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzau
                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) throws RemoteException {
                    zzbrVar.zzK(z, (com.google.android.gms.cast.internal.zzx) obj, (TaskCompletionSource) obj2);
                }
            }).setMethodKey(8412).build());
        }
    }

    public void setVolume(final double d) throws IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzr zzrVar = this.zzh;
        if (zzrVar != null) {
            if (!Double.isInfinite(d) && !Double.isNaN(d)) {
                final zzbr zzbrVar = (zzbr) zzrVar;
                zzbrVar.doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzba
                    @Override // com.google.android.gms.common.api.internal.RemoteCall
                    public final void accept(Object obj, Object obj2) throws RemoteException {
                        zzbrVar.zzL(d, (com.google.android.gms.cast.internal.zzx) obj, (TaskCompletionSource) obj2);
                    }
                }).setMethodKey(8411).build());
            } else {
                StringBuilder sb = new StringBuilder(41);
                sb.append("Volume cannot be ");
                sb.append(d);
                throw new IllegalArgumentException(sb.toString());
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void start(@NonNull Bundle bundle) {
        zzp(bundle);
    }

    public final com.google.android.gms.cast.framework.media.internal.zzp zzd() {
        return this.zzg;
    }

    @Nullable
    public final String zzf() {
        String str = this.zzm;
        if (str != null) {
            return str;
        }
        CastDevice castDevice = this.zzj;
        if (castDevice != null) {
            return castDevice.getFriendlyName();
        }
        return null;
    }

    public final void zzk(@Nullable com.google.android.gms.internal.cast.zzax zzaxVar) {
        this.zzl = zzaxVar;
    }

    final /* synthetic */ void zzl(Bundle bundle) {
        String string = bundle.getString("com.google.android.gms.cast.CLIENT_INFO_PLAYBACK_SESSION_NAME");
        this.zzm = string;
        zzb.d("playback session is updated to name: %s", string);
        com.google.android.gms.cast.framework.media.internal.zzp zzpVar = this.zzg;
        if (zzpVar != null) {
            zzpVar.zzh(this.zzm);
        }
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected final void zzm(@NonNull Bundle bundle) {
        CastDevice fromBundle = CastDevice.getFromBundle(bundle);
        if (fromBundle == null || fromBundle.equals(this.zzj)) {
            return;
        }
        this.zzj = fromBundle;
        zzb.d("update to device: %s", fromBundle);
    }
}
