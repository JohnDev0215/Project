package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SuppressLint({"UseSparseArrays"})
public final class zzbr extends GoogleApi implements zzr {
    public static final /* synthetic */ int zzf = 0;
    private static final Logger zzg = new Logger("CastClient");
    private static final Api.AbstractClientBuilder zzh;
    private static final Api zzi;

    @VisibleForTesting
    final zzbq zza;

    @Nullable
    @VisibleForTesting
    TaskCompletionSource zzb;

    @Nullable
    @VisibleForTesting
    TaskCompletionSource zzc;

    @VisibleForTesting
    final Map zzd;

    @VisibleForTesting
    final Map zze;
    private Handler zzj;
    private boolean zzk;
    private boolean zzl;
    private final AtomicLong zzm;
    private final Object zzn;
    private final Object zzo;

    @Nullable
    private ApplicationMetadata zzp;

    @Nullable
    private String zzq;
    private double zzr;
    private boolean zzs;
    private int zzt;
    private int zzu;

    @Nullable
    private zzar zzv;
    private final CastDevice zzw;
    private final Cast.Listener zzx;
    private final List zzy;
    private int zzz;

    static {
        zzbh zzbhVar = new zzbh();
        zzh = zzbhVar;
        zzi = new Api("Cast.API_CXLESS", zzbhVar, com.google.android.gms.cast.internal.zzai.zzb);
    }

    zzbr(Context context, Cast.CastOptions castOptions) {
        super(context, (Api<Cast.CastOptions>) zzi, castOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zza = new zzbq(this);
        this.zzn = new Object();
        this.zzo = new Object();
        this.zzy = Collections.synchronizedList(new ArrayList());
        Preconditions.checkNotNull(context, "context cannot be null");
        Preconditions.checkNotNull(castOptions, "CastOptions cannot be null");
        this.zzx = castOptions.zzb;
        this.zzw = castOptions.zza;
        this.zzd = new HashMap();
        this.zze = new HashMap();
        this.zzm = new AtomicLong(0L);
        this.zzz = 1;
        zzm();
    }

    static /* bridge */ /* synthetic */ void zzC(zzbr zzbrVar, Cast.ApplicationConnectionResult applicationConnectionResult) {
        synchronized (zzbrVar.zzn) {
            TaskCompletionSource taskCompletionSource = zzbrVar.zzb;
            if (taskCompletionSource != null) {
                taskCompletionSource.setResult(applicationConnectionResult);
            }
            zzbrVar.zzb = null;
        }
    }

    static /* bridge */ /* synthetic */ void zzD(zzbr zzbrVar, long j, int i) {
        TaskCompletionSource taskCompletionSource;
        synchronized (zzbrVar.zzd) {
            Map map = zzbrVar.zzd;
            Long lValueOf = Long.valueOf(j);
            taskCompletionSource = (TaskCompletionSource) map.get(lValueOf);
            zzbrVar.zzd.remove(lValueOf);
        }
        if (taskCompletionSource != null) {
            if (i == 0) {
                taskCompletionSource.setResult(null);
            } else {
                taskCompletionSource.setException(zzO(i));
            }
        }
    }

    static /* bridge */ /* synthetic */ void zzE(zzbr zzbrVar, int i) {
        synchronized (zzbrVar.zzo) {
            TaskCompletionSource taskCompletionSource = zzbrVar.zzc;
            if (taskCompletionSource == null) {
                return;
            }
            if (i == 0) {
                taskCompletionSource.setResult(new Status(0));
            } else {
                taskCompletionSource.setException(zzO(i));
            }
            zzbrVar.zzc = null;
        }
    }

    private static ApiException zzO(int i) {
        return ApiExceptionUtil.fromStatus(new Status(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Task zzP(com.google.android.gms.cast.internal.zzag zzagVar) {
        return doUnregisterEventListener((ListenerHolder.ListenerKey) Preconditions.checkNotNull(registerListener(zzagVar, "castDeviceControllerListenerKey").getListenerKey(), "Key must not be null"), 8415);
    }

    private final void zzQ() {
        Preconditions.checkState(this.zzz == 2, "Not connected to device");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzR() {
        zzg.d("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zze) {
            this.zze.clear();
        }
    }

    private final void zzS(TaskCompletionSource taskCompletionSource) {
        synchronized (this.zzn) {
            if (this.zzb != null) {
                zzT(2477);
            }
            this.zzb = taskCompletionSource;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzT(int i) {
        synchronized (this.zzn) {
            TaskCompletionSource taskCompletionSource = this.zzb;
            if (taskCompletionSource != null) {
                taskCompletionSource.setException(zzO(i));
            }
            this.zzb = null;
        }
    }

    private final void zzU() {
        Preconditions.checkState(this.zzz != 1, "Not active connection");
    }

    static /* bridge */ /* synthetic */ Handler zzn(zzbr zzbrVar) {
        if (zzbrVar.zzj == null) {
            zzbrVar.zzj = new com.google.android.gms.internal.cast.zzcv(zzbrVar.getLooper());
        }
        return zzbrVar.zzj;
    }

    static /* bridge */ /* synthetic */ void zzx(zzbr zzbrVar) {
        zzbrVar.zzt = -1;
        zzbrVar.zzu = -1;
        zzbrVar.zzp = null;
        zzbrVar.zzq = null;
        zzbrVar.zzr = 0.0d;
        zzbrVar.zzm();
        zzbrVar.zzs = false;
        zzbrVar.zzv = null;
    }

    static /* bridge */ /* synthetic */ void zzy(zzbr zzbrVar, com.google.android.gms.cast.internal.zza zzaVar) {
        boolean z;
        String strZza = zzaVar.zza();
        if (CastUtils.zzh(strZza, zzbrVar.zzq)) {
            z = false;
        } else {
            zzbrVar.zzq = strZza;
            z = true;
        }
        zzg.d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(zzbrVar.zzl));
        Cast.Listener listener = zzbrVar.zzx;
        if (listener != null && (z || zzbrVar.zzl)) {
            listener.onApplicationStatusChanged();
        }
        zzbrVar.zzl = false;
    }

    static /* bridge */ /* synthetic */ void zzz(zzbr zzbrVar, com.google.android.gms.cast.internal.zzy zzyVar) {
        boolean z;
        boolean z2;
        boolean z3;
        ApplicationMetadata applicationMetadataZze = zzyVar.zze();
        if (!CastUtils.zzh(applicationMetadataZze, zzbrVar.zzp)) {
            zzbrVar.zzp = applicationMetadataZze;
            zzbrVar.zzx.onApplicationMetadataChanged(applicationMetadataZze);
        }
        double dZzb = zzyVar.zzb();
        if (Double.isNaN(dZzb) || Math.abs(dZzb - zzbrVar.zzr) <= 1.0E-7d) {
            z = false;
        } else {
            zzbrVar.zzr = dZzb;
            z = true;
        }
        boolean zZzg = zzyVar.zzg();
        if (zZzg != zzbrVar.zzs) {
            zzbrVar.zzs = zZzg;
            z = true;
        }
        Logger logger = zzg;
        logger.d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(zzbrVar.zzk));
        Cast.Listener listener = zzbrVar.zzx;
        if (listener != null && (z || zzbrVar.zzk)) {
            listener.onVolumeChanged();
        }
        Double.isNaN(zzyVar.zza());
        int iZzc = zzyVar.zzc();
        if (iZzc != zzbrVar.zzt) {
            zzbrVar.zzt = iZzc;
            z2 = true;
        } else {
            z2 = false;
        }
        logger.d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z2), Boolean.valueOf(zzbrVar.zzk));
        Cast.Listener listener2 = zzbrVar.zzx;
        if (listener2 != null && (z2 || zzbrVar.zzk)) {
            listener2.onActiveInputStateChanged(zzbrVar.zzt);
        }
        int iZzd = zzyVar.zzd();
        if (iZzd != zzbrVar.zzu) {
            zzbrVar.zzu = iZzd;
            z3 = true;
        } else {
            z3 = false;
        }
        logger.d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z3), Boolean.valueOf(zzbrVar.zzk));
        Cast.Listener listener3 = zzbrVar.zzx;
        if (listener3 != null && (z3 || zzbrVar.zzk)) {
            listener3.onStandbyStateChanged(zzbrVar.zzu);
        }
        if (!CastUtils.zzh(zzbrVar.zzv, zzyVar.zzf())) {
            zzbrVar.zzv = zzyVar.zzf();
        }
        zzbrVar.zzk = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zzF(String str, String str2, zzbs zzbsVar, com.google.android.gms.cast.internal.zzx zzxVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzQ();
        ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zzh(str, str2, null);
        zzS(taskCompletionSource);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zzG(String str, LaunchOptions launchOptions, com.google.android.gms.cast.internal.zzx zzxVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzQ();
        ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zzi(str, launchOptions);
        zzS(taskCompletionSource);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zzH(Cast.MessageReceivedCallback messageReceivedCallback, String str, com.google.android.gms.cast.internal.zzx zzxVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzU();
        if (messageReceivedCallback != null) {
            ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zzs(str);
        }
        taskCompletionSource.setResult(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zzI(String str, String str2, String str3, com.google.android.gms.cast.internal.zzx zzxVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        long jIncrementAndGet = this.zzm.incrementAndGet();
        zzQ();
        try {
            this.zzd.put(Long.valueOf(jIncrementAndGet), taskCompletionSource);
            ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zzn(str2, str3, jIncrementAndGet);
        } catch (RemoteException e) {
            this.zzd.remove(Long.valueOf(jIncrementAndGet));
            taskCompletionSource.setException(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zzJ(String str, Cast.MessageReceivedCallback messageReceivedCallback, com.google.android.gms.cast.internal.zzx zzxVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzU();
        ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zzs(str);
        if (messageReceivedCallback != null) {
            ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zzl(str);
        }
        taskCompletionSource.setResult(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zzK(boolean z, com.google.android.gms.cast.internal.zzx zzxVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zzo(z, this.zzr, this.zzs);
        taskCompletionSource.setResult(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zzL(double d, com.google.android.gms.cast.internal.zzx zzxVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zzp(d, this.zzr, this.zzs);
        taskCompletionSource.setResult(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zzM(String str, com.google.android.gms.cast.internal.zzx zzxVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzQ();
        ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zzq(str);
        synchronized (this.zzo) {
            if (this.zzc != null) {
                taskCompletionSource.setException(zzO(CastStatusCodes.INVALID_REQUEST));
            } else {
                this.zzc = taskCompletionSource;
            }
        }
    }

    @Override // com.google.android.gms.cast.zzr
    public final double zza() {
        zzQ();
        return this.zzr;
    }

    @Override // com.google.android.gms.cast.zzr
    public final int zzb() {
        zzQ();
        return this.zzt;
    }

    @Override // com.google.android.gms.cast.zzr
    public final int zzc() {
        zzQ();
        return this.zzu;
    }

    @Override // com.google.android.gms.cast.zzr
    @Nullable
    public final ApplicationMetadata zzd() {
        zzQ();
        return this.zzp;
    }

    @Override // com.google.android.gms.cast.zzr
    public final Task zze() {
        ListenerHolder listenerHolderRegisterListener = registerListener(this.zza, "castDeviceControllerListenerKey");
        RegistrationMethods.Builder builder = RegistrationMethods.builder();
        return doRegisterEventListener(builder.withHolder(listenerHolderRegisterListener).register(new RemoteCall() { // from class: com.google.android.gms.cast.zzaz
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                com.google.android.gms.cast.internal.zzx zzxVar = (com.google.android.gms.cast.internal.zzx) obj;
                ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zzk(this.zza.zza);
                ((com.google.android.gms.cast.internal.zzae) zzxVar.getService()).zze();
                ((TaskCompletionSource) obj2).setResult(null);
            }
        }).unregister(new RemoteCall() { // from class: com.google.android.gms.cast.zzaw
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                int i = zzbr.zzf;
                ((com.google.android.gms.cast.internal.zzae) ((com.google.android.gms.cast.internal.zzx) obj).getService()).zzr();
                ((TaskCompletionSource) obj2).setResult(Boolean.TRUE);
            }
        }).setFeatures(zzat.zzb).setMethodKey(8428).build());
    }

    @Override // com.google.android.gms.cast.zzr
    public final Task zzf() {
        Task taskDoWrite = doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzax
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                int i = zzbr.zzf;
                ((com.google.android.gms.cast.internal.zzae) ((com.google.android.gms.cast.internal.zzx) obj).getService()).zzf();
                ((TaskCompletionSource) obj2).setResult(null);
            }
        }).setMethodKey(8403).build());
        zzR();
        zzP(this.zza);
        return taskDoWrite;
    }

    @Override // com.google.android.gms.cast.zzr
    public final Task zzg(final String str) {
        final Cast.MessageReceivedCallback messageReceivedCallback;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        synchronized (this.zze) {
            messageReceivedCallback = (Cast.MessageReceivedCallback) this.zze.remove(str);
        }
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzbb
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzH(messageReceivedCallback, str, (com.google.android.gms.cast.internal.zzx) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(8414).build());
    }

    @Override // com.google.android.gms.cast.zzr
    public final Task zzh(final String str, final String str2) {
        CastUtils.throwIfInvalidNamespace(str);
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (str2.length() <= 524288) {
            final String str3 = null;
            return doWrite(TaskApiCall.builder().run(new RemoteCall(str3, str, str2) { // from class: com.google.android.gms.cast.zzbg
                public final /* synthetic */ String zzb;
                public final /* synthetic */ String zzc;

                {
                    this.zzb = str;
                    this.zzc = str2;
                }

                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) throws RemoteException {
                    this.zza.zzI(null, this.zzb, this.zzc, (com.google.android.gms.cast.internal.zzx) obj, (TaskCompletionSource) obj2);
                }
            }).setMethodKey(8405).build());
        }
        zzg.w("Message send failed. Message exceeds maximum size", new Object[0]);
        throw new IllegalArgumentException("Message exceeds maximum size524288");
    }

    @Override // com.google.android.gms.cast.zzr
    public final Task zzi(final String str, final Cast.MessageReceivedCallback messageReceivedCallback) {
        CastUtils.throwIfInvalidNamespace(str);
        if (messageReceivedCallback != null) {
            synchronized (this.zze) {
                this.zze.put(str, messageReceivedCallback);
            }
        }
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.cast.zzbd
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zzJ(str, messageReceivedCallback, (com.google.android.gms.cast.internal.zzx) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(8413).build());
    }

    @Override // com.google.android.gms.cast.zzr
    @Nullable
    public final String zzj() {
        zzQ();
        return this.zzq;
    }

    @Override // com.google.android.gms.cast.zzr
    public final void zzk(zzq zzqVar) {
        Preconditions.checkNotNull(zzqVar);
        this.zzy.add(zzqVar);
    }

    @Override // com.google.android.gms.cast.zzr
    public final boolean zzl() {
        zzQ();
        return this.zzs;
    }

    @VisibleForTesting
    final double zzm() {
        if (this.zzw.hasCapability(2048)) {
            return 0.02d;
        }
        return (!this.zzw.hasCapability(4) || this.zzw.hasCapability(1) || "Chromecast Audio".equals(this.zzw.getModelName())) ? 0.05d : 0.02d;
    }
}
