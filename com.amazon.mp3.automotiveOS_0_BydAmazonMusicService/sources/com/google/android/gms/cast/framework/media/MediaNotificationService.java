package com.google.android.gms.cast.framework.media;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.media.app.NotificationCompat;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzcu;
import com.google.android.gms.internal.cast.zzkk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class MediaNotificationService extends Service {

    @NonNull
    public static final String ACTION_UPDATE_NOTIFICATION = "com.google.android.gms.cast.framework.action.UPDATE_NOTIFICATION";
    private static final Logger zza = new Logger("MediaNotificationService");

    @Nullable
    private static Runnable zzb;
    private NotificationOptions zzc;

    @Nullable
    private ImagePicker zzd;
    private ComponentName zze;

    @Nullable
    private ComponentName zzf;
    private List zzg = new ArrayList();

    @Nullable
    private int[] zzh;
    private long zzi;
    private com.google.android.gms.cast.framework.media.internal.zzb zzj;
    private ImageHints zzk;
    private Resources zzl;
    private zzm zzm;
    private zzn zzn;
    private NotificationManager zzo;
    private Notification zzp;
    private CastContext zzq;

    public static boolean isNotificationOptionsValid(@NonNull CastOptions castOptions) {
        NotificationOptions notificationOptions;
        CastMediaOptions castMediaOptions = castOptions.getCastMediaOptions();
        if (castMediaOptions == null || (notificationOptions = castMediaOptions.getNotificationOptions()) == null) {
            return false;
        }
        zzg zzgVarZzm = notificationOptions.zzm();
        if (zzgVarZzm == null) {
            return true;
        }
        List listZze = zze(zzgVarZzm);
        int[] iArrZzi = zzi(zzgVarZzm);
        int size = listZze == null ? 0 : listZze.size();
        if (listZze == null || listZze.isEmpty()) {
            zza.e(NotificationActionsProvider.class.getSimpleName().concat(" doesn't provide any action."), new Object[0]);
        } else if (listZze.size() > 5) {
            zza.e(NotificationActionsProvider.class.getSimpleName().concat(" provides more than 5 actions."), new Object[0]);
        } else {
            if (iArrZzi != null && (iArrZzi.length) != 0) {
                for (int i : iArrZzi) {
                    if (i < 0 || i >= size) {
                        zza.e(NotificationActionsProvider.class.getSimpleName().concat("provides a compact view action whose index is out of bounds."), new Object[0]);
                    }
                }
                return true;
            }
            zza.e(NotificationActionsProvider.class.getSimpleName().concat(" doesn't provide any actions for compact view."), new Object[0]);
        }
        return false;
    }

    public static void zzc() {
        Runnable runnable = zzb;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.core.app.NotificationCompat.Action zzd(java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 606
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.MediaNotificationService.zzd(java.lang.String):androidx.core.app.NotificationCompat$Action");
    }

    @Nullable
    private static List zze(zzg zzgVar) {
        try {
            return zzgVar.zzf();
        } catch (RemoteException e) {
            zza.e(e, "Unable to call %s on %s.", "getNotificationActions", zzg.class.getSimpleName());
            return null;
        }
    }

    private final void zzf(zzg zzgVar) {
        NotificationCompat.Action actionZzd;
        int[] iArrZzi = zzi(zzgVar);
        this.zzh = iArrZzi == null ? null : (int[]) iArrZzi.clone();
        List<NotificationAction> listZze = zze(zzgVar);
        this.zzg = new ArrayList();
        if (listZze == null) {
            return;
        }
        for (NotificationAction notificationAction : listZze) {
            String action = notificationAction.getAction();
            if (action.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK) || action.equals(MediaIntentReceiver.ACTION_SKIP_NEXT) || action.equals(MediaIntentReceiver.ACTION_SKIP_PREV) || action.equals(MediaIntentReceiver.ACTION_FORWARD) || action.equals(MediaIntentReceiver.ACTION_REWIND) || action.equals(MediaIntentReceiver.ACTION_STOP_CASTING) || action.equals(MediaIntentReceiver.ACTION_DISCONNECT)) {
                actionZzd = zzd(notificationAction.getAction());
            } else {
                Intent intent = new Intent(notificationAction.getAction());
                intent.setComponent(this.zze);
                actionZzd = new NotificationCompat.Action.Builder(notificationAction.getIconResId(), notificationAction.getContentDescription(), PendingIntent.getBroadcast(this, 0, intent, zzcu.zza)).build();
            }
            if (actionZzd != null) {
                this.zzg.add(actionZzd);
            }
        }
    }

    private final void zzg() {
        this.zzg = new ArrayList();
        Iterator<String> it = this.zzc.getActions().iterator();
        while (it.hasNext()) {
            NotificationCompat.Action actionZzd = zzd(it.next());
            if (actionZzd != null) {
                this.zzg.add(actionZzd);
            }
        }
        this.zzh = (int[]) this.zzc.getCompatActionIndices().clone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzh() {
        if (this.zzm == null) {
            return;
        }
        zzn zznVar = this.zzn;
        PendingIntent pendingIntent = null;
        NotificationCompat.Builder visibility = new NotificationCompat.Builder(this, "cast_media_notification").setLargeIcon(zznVar == null ? null : zznVar.zzb).setSmallIcon(this.zzc.getSmallIconDrawableResId()).setContentTitle(this.zzm.zzd).setContentText(this.zzl.getString(this.zzc.getCastingToDeviceStringResId(), this.zzm.zze)).setOngoing(true).setShowWhen(false).setVisibility(1);
        ComponentName componentName = this.zzf;
        if (componentName != null) {
            Intent intent = new Intent();
            intent.putExtra("targetActivity", componentName);
            intent.setAction(componentName.flattenToString());
            intent.setComponent(componentName);
            TaskStackBuilder taskStackBuilderCreate = TaskStackBuilder.create(this);
            taskStackBuilderCreate.addNextIntentWithParentStack(intent);
            pendingIntent = taskStackBuilderCreate.getPendingIntent(1, zzcu.zza | 134217728);
        }
        if (pendingIntent != null) {
            visibility.setContentIntent(pendingIntent);
        }
        zzg zzgVarZzm = this.zzc.zzm();
        if (zzgVarZzm != null) {
            zza.i("actionsProvider != null", new Object[0]);
            zzf(zzgVarZzm);
        } else {
            zza.i("actionsProvider == null", new Object[0]);
            zzg();
        }
        Iterator it = this.zzg.iterator();
        while (it.hasNext()) {
            visibility.addAction((NotificationCompat.Action) it.next());
        }
        NotificationCompat.MediaStyle mediaStyle = new NotificationCompat.MediaStyle();
        int[] iArr = this.zzh;
        if (iArr != null) {
            mediaStyle.setShowActionsInCompactView(iArr);
        }
        MediaSessionCompat.Token token = this.zzm.zza;
        if (token != null) {
            mediaStyle.setMediaSession(token);
        }
        visibility.setStyle(mediaStyle);
        Notification notificationBuild = visibility.build();
        this.zzp = notificationBuild;
        startForeground(1, notificationBuild);
    }

    @Nullable
    private static int[] zzi(zzg zzgVar) {
        try {
            return zzgVar.zzg();
        } catch (RemoteException e) {
            zza.e(e, "Unable to call %s on %s.", "getCompactViewActionIndices", zzg.class.getSimpleName());
            return null;
        }
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@NonNull Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.zzo = (NotificationManager) getSystemService("notification");
        CastContext sharedInstance = CastContext.getSharedInstance(this);
        this.zzq = sharedInstance;
        CastMediaOptions castMediaOptions = (CastMediaOptions) Preconditions.checkNotNull(sharedInstance.getCastOptions().getCastMediaOptions());
        this.zzc = (NotificationOptions) Preconditions.checkNotNull(castMediaOptions.getNotificationOptions());
        this.zzd = castMediaOptions.getImagePicker();
        this.zzl = getResources();
        this.zze = new ComponentName(getApplicationContext(), castMediaOptions.getMediaIntentReceiverClassName());
        if (TextUtils.isEmpty(this.zzc.getTargetActivityClassName())) {
            this.zzf = null;
        } else {
            this.zzf = new ComponentName(getApplicationContext(), this.zzc.getTargetActivityClassName());
        }
        this.zzi = this.zzc.getSkipStepMs();
        int dimensionPixelSize = this.zzl.getDimensionPixelSize(this.zzc.zze());
        this.zzk = new ImageHints(1, dimensionPixelSize, dimensionPixelSize);
        this.zzj = new com.google.android.gms.cast.framework.media.internal.zzb(getApplicationContext(), this.zzk);
        if (PlatformVersion.isAtLeastO()) {
            NotificationChannel notificationChannel = new NotificationChannel("cast_media_notification", "Cast", 2);
            notificationChannel.setShowBadge(false);
            this.zzo.createNotificationChannel(notificationChannel);
        }
        com.google.android.gms.internal.cast.zzl.zzd(zzkk.CAF_NOTIFICATION_SERVICE);
    }

    @Override // android.app.Service
    public void onDestroy() {
        com.google.android.gms.cast.framework.media.internal.zzb zzbVar = this.zzj;
        if (zzbVar != null) {
            zzbVar.zza();
        }
        zzb = null;
        this.zzo.cancel(1);
    }

    @Override // android.app.Service
    public int onStartCommand(@NonNull Intent intent, int i, final int i2) {
        zzm zzmVar;
        MediaInfo mediaInfo = (MediaInfo) Preconditions.checkNotNull((MediaInfo) intent.getParcelableExtra("extra_media_info"));
        MediaMetadata mediaMetadata = (MediaMetadata) Preconditions.checkNotNull(mediaInfo.getMetadata());
        int intExtra = intent.getIntExtra("extra_remote_media_client_player_state", 0);
        String stringExtra = intent.getStringExtra("extra_playback_session_name");
        CastDevice castDevice = (CastDevice) Preconditions.checkNotNull((CastDevice) intent.getParcelableExtra("extra_cast_device"));
        boolean z = intExtra == 2;
        int streamType = mediaInfo.getStreamType();
        String string = mediaMetadata.getString(MediaMetadata.KEY_TITLE);
        if (stringExtra == null) {
            stringExtra = castDevice.getFriendlyName();
        }
        zzm zzmVar2 = new zzm(z, streamType, string, stringExtra, (MediaSessionCompat.Token) intent.getParcelableExtra("extra_media_session_token"), intent.getBooleanExtra("extra_can_skip_next", false), intent.getBooleanExtra("extra_can_skip_prev", false));
        if (intent.getBooleanExtra("extra_media_notification_force_update", false) || (zzmVar = this.zzm) == null || zzmVar2.zzb != zzmVar.zzb || zzmVar2.zzc != zzmVar.zzc || !CastUtils.zzh(zzmVar2.zzd, zzmVar.zzd) || !CastUtils.zzh(zzmVar2.zze, zzmVar.zze) || zzmVar2.zzf != zzmVar.zzf || zzmVar2.zzg != zzmVar.zzg) {
            this.zzm = zzmVar2;
            zzh();
        }
        ImagePicker imagePicker = this.zzd;
        zzn zznVar = new zzn(imagePicker != null ? imagePicker.onPickImage(mediaMetadata, this.zzk) : mediaMetadata.hasImages() ? mediaMetadata.getImages().get(0) : null);
        zzn zznVar2 = this.zzn;
        if (zznVar2 == null || !CastUtils.zzh(zznVar.zza, zznVar2.zza)) {
            this.zzj.zzc(new zzl(this, zznVar));
            this.zzj.zzd(zznVar.zza);
        }
        startForeground(1, this.zzp);
        zzb = new Runnable() { // from class: com.google.android.gms.cast.framework.media.zzk
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.stopSelf(i2);
            }
        };
        return 2;
    }
}
