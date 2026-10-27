package com.amazon.mp3.playback.casting;

import android.os.Handler;
import androidx.media.VolumeProviderCompat;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.playback.service.volume.VolumeController;
import com.amazon.mp3.util.Log;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.casting.session.things.NowPlayingThingShadow;

/* JADX INFO: loaded from: classes3.dex */
public class CastingVolumeController {
    private static final String TAG = "CastingVolumeController";
    private static CastingVolumeController sInstance;
    private int mLastDirection;
    private final VolumeController mLocalVolumeController;
    private final CastingSessionManager mSessionManager = CastingSessionManager.getInstance();
    private VolumeProviderCompat mVolumeProviderCompat;
    private static Handler handler = new Handler();
    private static boolean canChange = true;

    public static synchronized CastingVolumeController get() {
        if (sInstance == null) {
            sInstance = new CastingVolumeController();
        }
        return sInstance;
    }

    private CastingVolumeController() {
        VolumeController volumeController = VolumeController.get();
        this.mLocalVolumeController = volumeController;
        this.mVolumeProviderCompat = new VolumeProviderCompat(1, volumeController.getMaximumVolume(), volumeController.getCurrentVolume()) { // from class: com.amazon.mp3.playback.casting.CastingVolumeController.1
            @Override // androidx.media.VolumeProviderCompat
            public void onAdjustVolume(int delta) {
                if (delta > 0) {
                    CastingVolumeController.this.onVolumeUp();
                } else if (delta < 0) {
                    CastingVolumeController.this.onVolumeDown();
                }
                super.onAdjustVolume(delta);
                CastingVolumeController.this.mLastDirection = delta;
            }
        };
    }

    public void onVolumeUp() {
        if (!CastingUtil.isCastingToAlexa()) {
            Log.error(TAG, "Casting Volume Controller used when not actively casting");
            return;
        }
        if (canChange) {
            this.mSessionManager.onVolumeUp();
            canChange = false;
            handler.postDelayed(new Runnable() { // from class: com.amazon.mp3.playback.casting.CastingVolumeController.2
                @Override // java.lang.Runnable
                public void run() {
                    boolean unused = CastingVolumeController.canChange = true;
                }
            }, 300L);
            if (supportsDirectionalVolume()) {
                int currentVolume = this.mVolumeProviderCompat.getCurrentVolume();
                if (currentVolume < this.mVolumeProviderCompat.getMaxVolume()) {
                    currentVolume++;
                }
                this.mVolumeProviderCompat.setCurrentVolume(currentVolume);
            }
        }
    }

    public void onVolumeDown() {
        if (!CastingUtil.isCastingToAlexa()) {
            Log.error(TAG, "Casting Volume Controller used when not actively casting");
            return;
        }
        if (canChange) {
            this.mSessionManager.onVolumeDown();
            canChange = false;
            handler.postDelayed(new Runnable() { // from class: com.amazon.mp3.playback.casting.CastingVolumeController.3
                @Override // java.lang.Runnable
                public void run() {
                    boolean unused = CastingVolumeController.canChange = true;
                }
            }, 300L);
            if (supportsDirectionalVolume()) {
                int currentVolume = this.mVolumeProviderCompat.getCurrentVolume();
                if (currentVolume > 0) {
                    currentVolume--;
                }
                this.mVolumeProviderCompat.setCurrentVolume(currentVolume);
            }
        }
    }

    public VolumeProviderCompat getCastingVolumeProvider() {
        return this.mVolumeProviderCompat;
    }

    private boolean supportsDirectionalVolume() {
        NowPlayingThingShadow.State.Transport[] availableFeatures = this.mSessionManager.getAvailableFeatures();
        if (availableFeatures != null) {
            for (NowPlayingThingShadow.State.Transport transport : availableFeatures) {
                if (transport.equals(NowPlayingThingShadow.State.Transport.VOLUME_DIRECTIONAL)) {
                    return true;
                }
            }
        }
        return false;
    }
}
