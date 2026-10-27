package com.android.bluetooth.avrcpcontroller;

import android.bluetooth.BluetoothDevice;
import android.net.INetd;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import com.android.bluetooth.util.AesUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class AvrcpPlayer {
    public static final int FEATURE_ADD_TO_NOWPLAYING = 61;
    public static final int FEATURE_BIT_MASK_SIZE = 16;
    public static final int FEATURE_BROWSING = 59;
    public static final int FEATURE_COVER_ART = 68;
    public static final int FEATURE_FAST_FORWARD = 45;
    public static final int FEATURE_FORWARD = 47;
    public static final int FEATURE_NOW_PLAYING = 65;
    public static final int FEATURE_PAUSE = 42;
    public static final int FEATURE_PLAY = 40;
    public static final int FEATURE_PREVIOUS = 48;
    public static final int FEATURE_REWIND = 44;
    public static final int FEATURE_STOP = 41;
    public static final int INVALID_ID = -1;
    public static final int SEARCHING_BIT_VALUE = 16;
    public static final int SEARCHING_OCTECT_VALUE = 7;
    public static final int UIDS_UNIQUE_BIT_VALUE = 128;
    public static final int UIDS_UNIQUE_OCTECT_VALUE = 7;
    private long mAvailableActions;
    private PlayerApplicationSettings mCurrentPlayerApplicationSettings;
    private AvrcpItem mCurrentTrack;
    private BluetoothDevice mDevice;
    private int mId;
    private String mName;
    private float mPlaySpeed;
    private int mPlayStatus;
    private long mPlayTime;
    private long mPlayTimeUpdate;
    private PlaybackStateCompat mPlaybackStateCompat;
    private byte[] mPlayerFeatures;
    private int mPlayerType;
    private PlayerApplicationSettings mSupportedPlayerApplicationSettings;
    private static final String TAG = "AvrcpPlayer";
    private static final boolean DBG = Log.isLoggable(TAG, 3);

    AvrcpPlayer() {
        this.mPlayStatus = 0;
        this.mPlayTime = -1L;
        this.mPlayTimeUpdate = 0L;
        this.mPlaySpeed = 1.0f;
        this.mName = INetd.NEXTHOP_NONE;
        this.mPlayerFeatures = new byte[16];
        this.mAvailableActions = PlaybackStateCompat.ACTION_PREPARE;
        this.mSupportedPlayerApplicationSettings = new PlayerApplicationSettings();
        this.mDevice = null;
        this.mId = -1;
        this.mAvailableActions = 16439L;
        PlaybackStateCompat.Builder playbackStateBuilder = new PlaybackStateCompat.Builder().setActions(this.mAvailableActions);
        this.mPlaybackStateCompat = playbackStateBuilder.build();
    }

    AvrcpPlayer(BluetoothDevice device, int id, String name, byte[] playerFeatures, int playStatus, int playerType) {
        this.mPlayStatus = 0;
        this.mPlayTime = -1L;
        this.mPlayTimeUpdate = 0L;
        this.mPlaySpeed = 1.0f;
        this.mName = INetd.NEXTHOP_NONE;
        this.mPlayerFeatures = new byte[16];
        this.mAvailableActions = PlaybackStateCompat.ACTION_PREPARE;
        this.mSupportedPlayerApplicationSettings = new PlayerApplicationSettings();
        this.mDevice = device;
        this.mId = id;
        this.mName = name;
        this.mPlayStatus = playStatus;
        this.mPlayerType = playerType;
        this.mPlayerFeatures = Arrays.copyOf(playerFeatures, playerFeatures.length);
        PlaybackStateCompat.Builder playbackStateBuilder = new PlaybackStateCompat.Builder().setActions(this.mAvailableActions);
        this.mPlaybackStateCompat = playbackStateBuilder.build();
        updateAvailableActions();
    }

    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public int getId() {
        return this.mId;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }

    public void setPlayerFeatures(byte[] playerFeatures) {
        System.arraycopy(playerFeatures, 0, this.mPlayerFeatures, 0, 16);
    }

    public byte[] getPlayerFeatures() {
        return this.mPlayerFeatures;
    }

    public boolean isSearchingSupported() {
        return isFeatureSupported(7, 16);
    }

    private boolean isFeatureSupported(int octVal, int bitVal) {
        if (octVal >= 16) {
            return false;
        }
        byte flag = this.mPlayerFeatures[octVal];
        return (flag & bitVal) == bitVal;
    }

    public void setPlayTime(int playTime) {
        this.mPlayTime = playTime;
        this.mPlayTimeUpdate = SystemClock.elapsedRealtime();
        this.mPlaybackStateCompat = new PlaybackStateCompat.Builder(this.mPlaybackStateCompat).setState(this.mPlayStatus, this.mPlayTime, this.mPlaySpeed).build();
    }

    public long getPlayTime() {
        return this.mPlayTime;
    }

    public void setPlayStatus(int playStatus) {
        this.mPlayTime = (long) (this.mPlayTime + (this.mPlaySpeed * (SystemClock.elapsedRealtime() - this.mPlaybackStateCompat.getLastPositionUpdateTime())));
        this.mPlayStatus = playStatus;
        if (playStatus == 1 || playStatus == 2) {
            this.mPlaySpeed = 0.0f;
        } else if (playStatus == 3) {
            this.mPlaySpeed = 1.0f;
        } else if (playStatus == 4) {
            this.mPlaySpeed = 3.0f;
        } else if (playStatus == 5) {
            this.mPlaySpeed = -3.0f;
        }
        this.mPlaybackStateCompat = new PlaybackStateCompat.Builder(this.mPlaybackStateCompat).setState(this.mPlayStatus, this.mPlayTime, this.mPlaySpeed).build();
    }

    public void setSupportedPlayerApplicationSettings(PlayerApplicationSettings playerApplicationSettings) {
        this.mSupportedPlayerApplicationSettings = playerApplicationSettings;
        updateAvailableActions();
    }

    public void setCurrentPlayerApplicationSettings(PlayerApplicationSettings playerApplicationSettings) {
        Log.d(TAG, "Settings changed");
        this.mCurrentPlayerApplicationSettings = playerApplicationSettings;
        MediaSessionCompat session = BluetoothMediaBrowserService.getSession();
        session.setRepeatMode(this.mCurrentPlayerApplicationSettings.getSetting(2));
        session.setShuffleMode(this.mCurrentPlayerApplicationSettings.getSetting(3));
    }

    public int getPlayStatus() {
        return this.mPlayStatus;
    }

    public boolean supportsFeature(int featureId) {
        int byteNumber = featureId / 8;
        byte bitMask = (byte) (1 << (featureId % 8));
        return (this.mPlayerFeatures[byteNumber] & bitMask) == bitMask;
    }

    public boolean supportsSetting(int settingType, int settingValue) {
        return this.mSupportedPlayerApplicationSettings.supportsSetting(settingType, settingValue);
    }

    public PlaybackStateCompat getPlaybackState() {
        if (DBG) {
            Log.d(TAG, "getPlayBackState state " + this.mPlayStatus + " time " + this.mPlayTime);
        }
        return this.mPlaybackStateCompat;
    }

    public synchronized void updateCurrentTrack(AvrcpItem update) {
        if (update != null) {
            long trackNumber = update.getTrackNumber();
            this.mPlaybackStateCompat = new PlaybackStateCompat.Builder(this.mPlaybackStateCompat).setActiveQueueItemId(trackNumber - 1).build();
            this.mCurrentTrack = update;
        } else {
            this.mCurrentTrack = update;
        }
    }

    public synchronized boolean notifyImageDownload(String uuid, Uri imageUri) {
        if (DBG) {
            Log.d(TAG, "Got an image download -- uuid=" + AesUtil.encrypt(uuid) + ", uri=" + imageUri);
        }
        if (uuid != null && imageUri != null && this.mCurrentTrack != null) {
            if (!uuid.equals(this.mCurrentTrack.getCoverArtUuid())) {
                return false;
            }
            this.mCurrentTrack.setCoverArtLocation(imageUri);
            if (DBG) {
                Log.d(TAG, "Image UUID '" + AesUtil.encrypt(uuid) + "' was added to current track.");
            }
            return true;
        }
        return false;
    }

    public synchronized AvrcpItem getCurrentTrack() {
        return this.mCurrentTrack;
    }

    private void updateAvailableActions() {
        if (supportsFeature(40)) {
            this.mAvailableActions |= 4;
        }
        if (supportsFeature(41)) {
            this.mAvailableActions |= 1;
        }
        if (supportsFeature(42)) {
            this.mAvailableActions |= 2;
        }
        if (supportsFeature(44)) {
            this.mAvailableActions |= 8;
        }
        if (supportsFeature(45)) {
            this.mAvailableActions |= 64;
        }
        if (supportsFeature(47)) {
            this.mAvailableActions |= 32;
        }
        if (supportsFeature(48)) {
            this.mAvailableActions |= 16;
        }
        if (this.mSupportedPlayerApplicationSettings.supportsSetting(2)) {
            this.mAvailableActions |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        if (this.mSupportedPlayerApplicationSettings.supportsSetting(3)) {
            this.mAvailableActions |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
        }
        this.mPlaybackStateCompat = new PlaybackStateCompat.Builder(this.mPlaybackStateCompat).setActions(this.mAvailableActions).build();
        if (DBG) {
            Log.d(TAG, "Supported Actions = " + this.mAvailableActions);
        }
    }
}
