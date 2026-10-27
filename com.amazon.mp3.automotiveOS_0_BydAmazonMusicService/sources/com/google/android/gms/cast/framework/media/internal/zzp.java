package com.google.android.gms.cast.framework.media.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.mediarouter.media.MediaItemMetadata;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.ReconnectionService;
import com.google.android.gms.cast.framework.media.MediaNotificationService;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzap;
import com.google.android.gms.internal.cast.zzcu;
import com.google.android.gms.internal.cast.zzcv;
import com.visualon.OSMPUtils.voMimeTypes;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzp implements RemoteMediaClient.Listener {
    private static final Logger zza = new Logger("MediaSessionManager");
    private final Context zzb;
    private final CastOptions zzc;
    private final zzap zzd;
    private final ComponentName zze;
    private final zzb zzf;
    private final zzb zzg;
    private final Handler zzh;
    private final Runnable zzi;
    private RemoteMediaClient zzj;
    private CastDevice zzk;

    @Nullable
    private String zzl;
    private MediaSessionCompat zzm;
    private MediaSessionCompat.Callback zzn;
    private boolean zzo;

    public zzp(Context context, CastOptions castOptions, zzap zzapVar) {
        this.zzb = context;
        this.zzc = castOptions;
        this.zzd = zzapVar;
        if (castOptions.getCastMediaOptions() == null || TextUtils.isEmpty(castOptions.getCastMediaOptions().getExpandedControllerActivityClassName())) {
            this.zze = null;
        } else {
            this.zze = new ComponentName(context, castOptions.getCastMediaOptions().getExpandedControllerActivityClassName());
        }
        zzb zzbVar = new zzb(context);
        this.zzf = zzbVar;
        zzbVar.zzc(new zzm(this));
        zzb zzbVar2 = new zzb(context);
        this.zzg = zzbVar2;
        zzbVar2.zzc(new zzn(this));
        this.zzh = new zzcv(Looper.getMainLooper());
        this.zzi = new Runnable() { // from class: com.google.android.gms.cast.framework.media.internal.zzl
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzf();
            }
        };
    }

    @Nullable
    public static Bitmap zza(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = width;
        int i = (int) (((9.0f * f) / 16.0f) + 0.5f);
        float f2 = (i - height) / 2;
        RectF rectF = new RectF(0.0f, f2, f, height + f2);
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, i, config);
        new Canvas(bitmapCreateBitmap).drawBitmap(bitmap, (Rect) null, rectF, (Paint) null);
        return bitmapCreateBitmap;
    }

    @Nullable
    private final Uri zzi(MediaMetadata mediaMetadata, int i) {
        WebImage webImageOnPickImage = this.zzc.getCastMediaOptions().getImagePicker() != null ? this.zzc.getCastMediaOptions().getImagePicker().onPickImage(mediaMetadata, i) : mediaMetadata.hasImages() ? mediaMetadata.getImages().get(0) : null;
        if (webImageOnPickImage == null) {
            return null;
        }
        return webImageOnPickImage.getUrl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzk(Bitmap bitmap, int i) {
        MediaSessionCompat mediaSessionCompat = this.zzm;
        if (mediaSessionCompat == null) {
            return;
        }
        if (i != 0) {
            if (i == 3) {
                mediaSessionCompat.setMetadata(zzj().putBitmap("android.media.metadata.ALBUM_ART", bitmap).build());
            }
        } else {
            if (bitmap != null) {
                mediaSessionCompat.setMetadata(zzj().putBitmap("android.media.metadata.DISPLAY_ICON", bitmap).build());
                return;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.eraseColor(0);
            this.zzm.setMetadata(zzj().putBitmap("android.media.metadata.DISPLAY_ICON", bitmapCreateBitmap).build());
        }
    }

    private final void zzl(boolean z) {
        if (this.zzc.getEnableReconnectionService()) {
            this.zzh.removeCallbacks(this.zzi);
            Intent intent = new Intent(this.zzb, (Class<?>) ReconnectionService.class);
            intent.setPackage(this.zzb.getPackageName());
            try {
                this.zzb.startService(intent);
            } catch (IllegalStateException unused) {
                if (z) {
                    this.zzh.postDelayed(this.zzi, 1000L);
                }
            }
        }
    }

    private final void zzm() {
        if (this.zzc.getCastMediaOptions().getNotificationOptions() == null) {
            return;
        }
        zza.d("Stopping notification service.", new Object[0]);
        if (Build.VERSION.SDK_INT >= 26) {
            MediaNotificationService.zzc();
            return;
        }
        Intent intent = new Intent(this.zzb, (Class<?>) MediaNotificationService.class);
        intent.setPackage(this.zzb.getPackageName());
        intent.setAction(MediaNotificationService.ACTION_UPDATE_NOTIFICATION);
        this.zzb.stopService(intent);
    }

    private final void zzn() {
        if (this.zzc.getEnableReconnectionService()) {
            this.zzh.removeCallbacks(this.zzi);
            Intent intent = new Intent(this.zzb, (Class<?>) ReconnectionService.class);
            intent.setPackage(this.zzb.getPackageName());
            this.zzb.stopService(intent);
        }
    }

    private final void zzo(int i, MediaInfo mediaInfo) {
        PendingIntent activity;
        MediaSessionCompat mediaSessionCompat = this.zzm;
        if (mediaSessionCompat == null) {
            return;
        }
        if (i == 0) {
            mediaSessionCompat.setPlaybackState(new PlaybackStateCompat.Builder().setState(0, 0L, 1.0f).build());
            this.zzm.setMetadata(new MediaMetadataCompat.Builder().build());
            return;
        }
        this.zzm.setPlaybackState(new PlaybackStateCompat.Builder().setState(i, this.zzj.isLiveStream() ? 0L : this.zzj.getApproximateStreamPosition(), 1.0f).setActions(true != this.zzj.isLiveStream() ? 768L : 512L).build());
        MediaSessionCompat mediaSessionCompat2 = this.zzm;
        if (this.zze == null) {
            activity = null;
        } else {
            Intent intent = new Intent();
            intent.setComponent(this.zze);
            activity = PendingIntent.getActivity(this.zzb, 0, intent, zzcu.zza | 134217728);
        }
        mediaSessionCompat2.setSessionActivity(activity);
        if (this.zzm == null) {
            return;
        }
        MediaMetadata metadata = mediaInfo.getMetadata();
        this.zzm.setMetadata(zzj().putString(MediaItemMetadata.KEY_TITLE, metadata.getString(MediaMetadata.KEY_TITLE)).putString("android.media.metadata.DISPLAY_TITLE", metadata.getString(MediaMetadata.KEY_TITLE)).putString("android.media.metadata.DISPLAY_SUBTITLE", metadata.getString(MediaMetadata.KEY_SUBTITLE)).putLong(MediaItemMetadata.KEY_DURATION, this.zzj.isLiveStream() ? 0L : mediaInfo.getStreamDuration()).build());
        Uri uriZzi = zzi(metadata, 0);
        if (uriZzi != null) {
            this.zzf.zzd(uriZzi);
        } else {
            zzk(null, 0);
        }
        Uri uriZzi2 = zzi(metadata, 3);
        if (uriZzi2 != null) {
            this.zzg.zzd(uriZzi2);
        } else {
            zzk(null, 3);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onAdBreakStatusUpdated() {
        zzg(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onMetadataUpdated() {
        zzg(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onPreloadStatusUpdated() {
        zzg(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onQueueStatusUpdated() {
        zzg(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onSendingRemoteMediaRequest() {
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onStatusUpdated() {
        zzg(false);
    }

    public final void zzd(RemoteMediaClient remoteMediaClient, @Nullable CastDevice castDevice) {
        CastOptions castOptions;
        if (this.zzo || (castOptions = this.zzc) == null || castOptions.getCastMediaOptions() == null || remoteMediaClient == null || castDevice == null) {
            return;
        }
        this.zzj = remoteMediaClient;
        remoteMediaClient.addListener(this);
        this.zzk = castDevice;
        if (!PlatformVersion.isAtLeastLollipop()) {
            ((AudioManager) this.zzb.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO)).requestAudioFocus(null, 3, 3);
        }
        ComponentName componentName = new ComponentName(this.zzb, this.zzc.getCastMediaOptions().getMediaIntentReceiverClassName());
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setComponent(componentName);
        PendingIntent broadcast = PendingIntent.getBroadcast(this.zzb, 0, intent, zzcu.zza);
        if (this.zzc.getCastMediaOptions().getMediaSessionEnabled()) {
            this.zzm = new MediaSessionCompat(this.zzb, "CastMediaSession", componentName, broadcast);
            zzo(0, null);
            CastDevice castDevice2 = this.zzk;
            if (castDevice2 != null && !TextUtils.isEmpty(castDevice2.getFriendlyName())) {
                this.zzm.setMetadata(new MediaMetadataCompat.Builder().putString(MediaItemMetadata.KEY_ALBUM_ARTIST, this.zzb.getResources().getString(R.string.cast_casting_to_device, this.zzk.getFriendlyName())).build());
            }
            zzo zzoVar = new zzo(this);
            this.zzn = zzoVar;
            this.zzm.setCallback(zzoVar);
            this.zzm.setActive(true);
            this.zzd.zzq(this.zzm);
        }
        this.zzo = true;
        zzg(false);
    }

    public final void zze(int i) {
        if (this.zzo) {
            this.zzo = false;
            RemoteMediaClient remoteMediaClient = this.zzj;
            if (remoteMediaClient != null) {
                remoteMediaClient.removeListener(this);
            }
            if (!PlatformVersion.isAtLeastLollipop()) {
                ((AudioManager) this.zzb.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO)).abandonAudioFocus(null);
            }
            this.zzd.zzq(null);
            this.zzf.zza();
            zzb zzbVar = this.zzg;
            if (zzbVar != null) {
                zzbVar.zza();
            }
            MediaSessionCompat mediaSessionCompat = this.zzm;
            if (mediaSessionCompat != null) {
                mediaSessionCompat.setSessionActivity(null);
                this.zzm.setCallback(null);
                this.zzm.setMetadata(new MediaMetadataCompat.Builder().build());
                zzo(0, null);
                this.zzm.setActive(false);
                this.zzm.release();
                this.zzm = null;
            }
            this.zzj = null;
            this.zzk = null;
            this.zzl = null;
            this.zzn = null;
            zzm();
            if (i == 0) {
                zzn();
            }
        }
    }

    final /* synthetic */ void zzf() {
        zzl(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzg(boolean r8) {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.internal.zzp.zzg(boolean):void");
    }

    public final void zzh(@Nullable String str) {
        this.zzl = str;
        zzg(false);
    }

    private final MediaMetadataCompat.Builder zzj() {
        MediaSessionCompat mediaSessionCompat = this.zzm;
        MediaMetadataCompat metadata = mediaSessionCompat == null ? null : mediaSessionCompat.getController().getMetadata();
        return metadata == null ? new MediaMetadataCompat.Builder() : new MediaMetadataCompat.Builder(metadata);
    }
}
