package com.google.android.gms.cast.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbs;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzw extends GmsClient {
    private static final Logger zze = new Logger("CastClientImpl");
    private static final Object zzf = new Object();
    private static final Object zzg = new Object();
    private Bundle zzA;
    private final Map zzB;
    private BaseImplementation.ResultHolder zzC;
    private BaseImplementation.ResultHolder zzD;
    private ApplicationMetadata zzh;
    private final CastDevice zzi;
    private final Cast.Listener zzj;
    private final Map zzk;
    private final long zzl;
    private final Bundle zzm;
    private zzv zzn;
    private String zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private double zzt;
    private com.google.android.gms.cast.zzar zzu;
    private int zzv;
    private int zzw;
    private final AtomicLong zzx;
    private String zzy;
    private String zzz;

    public zzw(Context context, Looper looper, ClientSettings clientSettings, CastDevice castDevice, long j, Cast.Listener listener, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 10, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzi = castDevice;
        this.zzj = listener;
        this.zzl = j;
        this.zzm = bundle;
        this.zzk = new HashMap();
        this.zzx = new AtomicLong(0L);
        this.zzB = new HashMap();
        zzY();
        zzp();
    }

    static /* bridge */ /* synthetic */ void zzH(zzw zzwVar, zza zzaVar) {
        boolean z;
        String strZza = zzaVar.zza();
        if (CastUtils.zzh(strZza, zzwVar.zzo)) {
            z = false;
        } else {
            zzwVar.zzo = strZza;
            z = true;
        }
        zze.d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(zzwVar.zzq));
        Cast.Listener listener = zzwVar.zzj;
        if (listener != null && (z || zzwVar.zzq)) {
            listener.onApplicationStatusChanged();
        }
        zzwVar.zzq = false;
    }

    static /* bridge */ /* synthetic */ void zzI(zzw zzwVar, zzy zzyVar) {
        boolean z;
        boolean z2;
        boolean z3;
        ApplicationMetadata applicationMetadataZze = zzyVar.zze();
        if (!CastUtils.zzh(applicationMetadataZze, zzwVar.zzh)) {
            zzwVar.zzh = applicationMetadataZze;
            zzwVar.zzj.onApplicationMetadataChanged(applicationMetadataZze);
        }
        double dZzb = zzyVar.zzb();
        if (Double.isNaN(dZzb) || Math.abs(dZzb - zzwVar.zzt) <= 1.0E-7d) {
            z = false;
        } else {
            zzwVar.zzt = dZzb;
            z = true;
        }
        boolean zZzg = zzyVar.zzg();
        if (zZzg != zzwVar.zzp) {
            zzwVar.zzp = zZzg;
            z = true;
        }
        Double.isNaN(zzyVar.zza());
        Logger logger = zze;
        logger.d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(zzwVar.zzr));
        Cast.Listener listener = zzwVar.zzj;
        if (listener != null && (z || zzwVar.zzr)) {
            listener.onVolumeChanged();
        }
        int iZzc = zzyVar.zzc();
        if (iZzc != zzwVar.zzv) {
            zzwVar.zzv = iZzc;
            z2 = true;
        } else {
            z2 = false;
        }
        logger.d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z2), Boolean.valueOf(zzwVar.zzr));
        Cast.Listener listener2 = zzwVar.zzj;
        if (listener2 != null && (z2 || zzwVar.zzr)) {
            listener2.onActiveInputStateChanged(zzwVar.zzv);
        }
        int iZzd = zzyVar.zzd();
        if (iZzd != zzwVar.zzw) {
            zzwVar.zzw = iZzd;
            z3 = true;
        } else {
            z3 = false;
        }
        logger.d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z3), Boolean.valueOf(zzwVar.zzr));
        Cast.Listener listener3 = zzwVar.zzj;
        if (listener3 != null && (z3 || zzwVar.zzr)) {
            listener3.onStandbyStateChanged(zzwVar.zzw);
        }
        if (!CastUtils.zzh(zzwVar.zzu, zzyVar.zzf())) {
            zzwVar.zzu = zzyVar.zzf();
        }
        zzwVar.zzr = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzY() {
        this.zzs = false;
        this.zzv = -1;
        this.zzw = -1;
        this.zzh = null;
        this.zzo = null;
        this.zzt = 0.0d;
        zzp();
        this.zzp = false;
        this.zzu = null;
    }

    private final void zzZ() {
        zze.d("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zzk) {
            this.zzk.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzaa(long j, int i) {
        BaseImplementation.ResultHolder resultHolder;
        synchronized (this.zzB) {
            resultHolder = (BaseImplementation.ResultHolder) this.zzB.remove(Long.valueOf(j));
        }
        if (resultHolder != null) {
            resultHolder.setResult(new Status(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzab(int i) {
        synchronized (zzg) {
            BaseImplementation.ResultHolder resultHolder = this.zzD;
            if (resultHolder != null) {
                resultHolder.setResult(new Status(i));
                this.zzD = null;
            }
        }
    }

    private final void zzac(BaseImplementation.ResultHolder resultHolder) {
        synchronized (zzf) {
            BaseImplementation.ResultHolder resultHolder2 = this.zzC;
            if (resultHolder2 != null) {
                resultHolder2.setResult(new zzq(new Status(2477), null, null, null, false));
            }
            this.zzC = resultHolder;
        }
    }

    private final void zzad(BaseImplementation.ResultHolder resultHolder) {
        synchronized (zzg) {
            if (this.zzD != null) {
                resultHolder.setResult(new Status(CastStatusCodes.INVALID_REQUEST));
            } else {
                this.zzD = resultHolder;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        return iInterfaceQueryLocalInterface instanceof zzae ? (zzae) iInterfaceQueryLocalInterface : new zzae(iBinder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        Logger logger = zze;
        logger.d("disconnect(); ServiceListener=%s, isConnected=%b", this.zzn, Boolean.valueOf(isConnected()));
        zzv zzvVar = this.zzn;
        this.zzn = null;
        if (zzvVar == null || zzvVar.zzq() == null) {
            logger.d("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        zzZ();
        try {
            try {
                ((zzae) getService()).zzf();
            } catch (RemoteException | IllegalStateException e) {
                zze.d(e, "Error while disconnecting the controller interface", new Object[0]);
            }
        } finally {
            super.disconnect();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Bundle getConnectionHint() {
        Bundle bundle = this.zzA;
        if (bundle == null) {
            return super.getConnectionHint();
        }
        this.zzA = null;
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle = new Bundle();
        zze.d("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzy, this.zzz);
        this.zzi.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzl);
        Bundle bundle2 = this.zzm;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        this.zzn = new zzv(this);
        bundle.putParcelable(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, new BinderWrapper(this.zzn));
        String str = this.zzy;
        if (str != null) {
            bundle.putString("last_application_id", str);
            String str2 = this.zzz;
            if (str2 != null) {
                bundle.putString("last_session_id", str2);
            }
        }
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12800000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        zzZ();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        zze.d("in onPostInitHandler; statusCode=%d", Integer.valueOf(i));
        if (i == 0 || i == 2300) {
            this.zzs = true;
            this.zzq = true;
            this.zzr = true;
        } else {
            this.zzs = false;
        }
        if (i == 2300) {
            Bundle bundle2 = new Bundle();
            this.zzA = bundle2;
            bundle2.putBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING, true);
            i = 0;
        }
        super.onPostInitHandler(i, iBinder, bundle, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzL(String str, String str2, zzbs zzbsVar, BaseImplementation.ResultHolder resultHolder) throws IllegalStateException, RemoteException {
        zzac(resultHolder);
        zzbs zzbsVar2 = new zzbs();
        zzae zzaeVar = (zzae) getService();
        if (zzW()) {
            zzaeVar.zzh(str, str2, zzbsVar2);
        } else {
            zzR(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzM(String str, LaunchOptions launchOptions, BaseImplementation.ResultHolder resultHolder) throws IllegalStateException, RemoteException {
        zzac(resultHolder);
        zzae zzaeVar = (zzae) getService();
        if (zzW()) {
            zzaeVar.zzi(str, launchOptions);
        } else {
            zzR(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzN(BaseImplementation.ResultHolder resultHolder) throws IllegalStateException, RemoteException {
        zzad(resultHolder);
        zzae zzaeVar = (zzae) getService();
        if (zzW()) {
            zzaeVar.zzj();
        } else {
            zzab(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzO(String str) throws RemoteException, IllegalArgumentException {
        Cast.MessageReceivedCallback messageReceivedCallback;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        synchronized (this.zzk) {
            messageReceivedCallback = (Cast.MessageReceivedCallback) this.zzk.remove(str);
        }
        if (messageReceivedCallback != null) {
            try {
                ((zzae) getService()).zzs(str);
            } catch (IllegalStateException e) {
                zze.d(e, "Error unregistering namespace (%s)", str);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzP() throws IllegalStateException, RemoteException {
        zzae zzaeVar = (zzae) getService();
        if (zzW()) {
            zzaeVar.zzm();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzQ(String str, String str2, BaseImplementation.ResultHolder resultHolder) throws IllegalStateException, RemoteException, IllegalArgumentException {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (str2.length() > 524288) {
            zze.w("Message send failed. Message exceeds maximum size", new Object[0]);
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
        CastUtils.throwIfInvalidNamespace(str);
        long jIncrementAndGet = this.zzx.incrementAndGet();
        try {
            this.zzB.put(Long.valueOf(jIncrementAndGet), resultHolder);
            zzae zzaeVar = (zzae) getService();
            if (zzW()) {
                zzaeVar.zzn(str, str2, jIncrementAndGet);
            } else {
                zzaa(jIncrementAndGet, CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
            }
        } catch (Throwable th) {
            this.zzB.remove(Long.valueOf(jIncrementAndGet));
            throw th;
        }
    }

    public final void zzR(int i) {
        synchronized (zzf) {
            BaseImplementation.ResultHolder resultHolder = this.zzC;
            if (resultHolder != null) {
                resultHolder.setResult(new zzq(new Status(i), null, null, null, false));
                this.zzC = null;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzS(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, RemoteException, IllegalArgumentException {
        CastUtils.throwIfInvalidNamespace(str);
        zzO(str);
        if (messageReceivedCallback != null) {
            synchronized (this.zzk) {
                this.zzk.put(str, messageReceivedCallback);
            }
            zzae zzaeVar = (zzae) getService();
            if (zzW()) {
                zzaeVar.zzl(str);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzT(boolean z) throws IllegalStateException, RemoteException {
        zzae zzaeVar = (zzae) getService();
        if (zzW()) {
            zzaeVar.zzo(z, this.zzt, this.zzp);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzU(double d) throws IllegalStateException, RemoteException, IllegalArgumentException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Volume cannot be ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        }
        zzae zzaeVar = (zzae) getService();
        if (zzW()) {
            zzaeVar.zzp(d, this.zzt, this.zzp);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzV(String str, BaseImplementation.ResultHolder resultHolder) throws IllegalStateException, RemoteException {
        zzad(resultHolder);
        zzae zzaeVar = (zzae) getService();
        if (zzW()) {
            zzaeVar.zzq(str);
        } else {
            zzab(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    @VisibleForTesting
    final boolean zzW() {
        zzv zzvVar;
        return (!this.zzs || (zzvVar = this.zzn) == null || zzvVar.zzr()) ? false : true;
    }

    public final boolean zzX() throws IllegalStateException {
        checkConnected();
        return this.zzp;
    }

    @VisibleForTesting
    final double zzp() {
        Preconditions.checkNotNull(this.zzi, "device should not be null");
        if (this.zzi.hasCapability(2048)) {
            return 0.02d;
        }
        return (!this.zzi.hasCapability(4) || this.zzi.hasCapability(1) || "Chromecast Audio".equals(this.zzi.getModelName())) ? 0.05d : 0.02d;
    }

    public final double zzq() throws IllegalStateException {
        checkConnected();
        return this.zzt;
    }

    public final int zzr() throws IllegalStateException {
        checkConnected();
        return this.zzv;
    }

    public final int zzs() throws IllegalStateException {
        checkConnected();
        return this.zzw;
    }

    public final ApplicationMetadata zzt() throws IllegalStateException {
        checkConnected();
        return this.zzh;
    }

    public final String zzz() throws IllegalStateException {
        checkConnected();
        return this.zzo;
    }
}
