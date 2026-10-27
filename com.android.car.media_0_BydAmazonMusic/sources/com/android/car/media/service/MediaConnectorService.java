package com.android.car.media.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.media.main.R$drawable;
import com.android.car.media.main.R$string;
import com.android.car.media.service.MediaConnectorService;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class MediaConnectorService extends LifecycleService {
    public static final /* synthetic */ int a = 0;

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final void onCreate() {
        super.onCreate();
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("com.android.car.media.service", "MediaConnectorService", 0));
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final int onStartCommand(Intent intent, int i, final int i2) {
        super.onStartCommand(intent, i, i2);
        final boolean booleanExtra = intent.getBooleanExtra("com.android.car.media.autoplay", false);
        final PlaybackViewModel playbackViewModelA = PlaybackViewModel.a(getApplication(), 0);
        playbackViewModelA.j.observe(this, new Observer() { // from class: spi.z6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MediaConnectorService mediaConnectorService = this.a;
                int i3 = i2;
                PlaybackViewModel playbackViewModel = playbackViewModelA;
                boolean z = booleanExtra;
                PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                int i4 = MediaConnectorService.a;
                Objects.requireNonNull(mediaConnectorService);
                if (eVar != null) {
                    if (eVar.c.getState() == 3) {
                        mediaConnectorService.stopSelf(i3);
                        return;
                    }
                    if ((eVar.c.getActions() & PlaybackStateCompat.ACTION_PREPARE) != 0) {
                        if (playbackViewModel.i.getValue() != null) {
                            playbackViewModel.i.getValue().d();
                        } else {
                            Log.e("MediaConnectorService", "prepare: playbackViewModel.getPlaybackController().getValue() == null !!!");
                        }
                        if (!z) {
                            mediaConnectorService.stopSelf(i3);
                        }
                    }
                    if (!z || (eVar.c.getActions() & 4) == 0) {
                        return;
                    }
                    if (playbackViewModel.i.getValue() != null) {
                        playbackViewModel.i.getValue().c();
                    } else {
                        Log.e("MediaConnectorService", "play: playbackViewModel.getPlaybackController().getValue() == null !!!");
                    }
                    mediaConnectorService.stopSelf(i3);
                }
            }
        });
        startForeground(1, new NotificationCompat.Builder(this, "com.android.car.media.service").setSmallIcon(R$drawable.ic_music).setContentTitle(getResources().getString(R$string.service_notification_title)).build());
        return 2;
    }
}
