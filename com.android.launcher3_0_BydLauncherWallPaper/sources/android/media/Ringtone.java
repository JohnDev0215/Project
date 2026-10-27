package android.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.R;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class Ringtone {
    private static final boolean LOGD = true;
    private static final String MEDIA_SELECTION = "mime_type LIKE 'audio/%' OR mime_type IN ('application/ogg', 'application/x-flac')";
    private static final String TAG = "Ringtone";
    private final boolean mAllowRemote;
    private final AudioManager mAudioManager;
    private final Context mContext;
    private MediaPlayer mLocalPlayer;
    private final IRingtonePlayer mRemotePlayer;
    private final Binder mRemoteToken;
    private String mTitle;
    private Uri mUri;
    private VolumeShaper mVolumeShaper;
    private VolumeShaper.Configuration mVolumeShaperConfig;
    private static final String[] MEDIA_COLUMNS = {"_id", "title"};
    private static final ArrayList<Ringtone> sActiveRingtones = new ArrayList<>();
    private final MyOnCompletionListener mCompletionListener = new MyOnCompletionListener();
    private AudioAttributes mAudioAttributes = new AudioAttributes.Builder().setUsage(6).setContentType(4).build();
    private boolean mIsLooping = false;
    private float mVolume = 1.0f;
    private final Object mPlaybackSettingsLock = new Object();

    public Ringtone(Context context, boolean z) {
        this.mContext = context;
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        this.mAudioManager = audioManager;
        this.mAllowRemote = z;
        this.mRemotePlayer = z ? audioManager.getRingtonePlayer() : null;
        this.mRemoteToken = z ? new Binder() : null;
    }

    @Deprecated
    public void setStreamType(int i) {
        PlayerBase.deprecateStreamTypeForPlayback(i, TAG, "setStreamType()");
        setAudioAttributes(new AudioAttributes.Builder().setInternalLegacyStreamType(i).build());
    }

    @Deprecated
    public int getStreamType() {
        return AudioAttributes.toLegacyStreamType(this.mAudioAttributes);
    }

    public void setAudioAttributes(AudioAttributes audioAttributes) throws IllegalArgumentException {
        if (audioAttributes == null) {
            throw new IllegalArgumentException("Invalid null AudioAttributes for Ringtone");
        }
        this.mAudioAttributes = audioAttributes;
        setUri(this.mUri, this.mVolumeShaperConfig);
    }

    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public void setLooping(boolean z) {
        synchronized (this.mPlaybackSettingsLock) {
            this.mIsLooping = z;
            applyPlaybackProperties_sync();
        }
    }

    public boolean isLooping() {
        boolean z;
        synchronized (this.mPlaybackSettingsLock) {
            z = this.mIsLooping;
        }
        return z;
    }

    public void setVolume(float f) {
        synchronized (this.mPlaybackSettingsLock) {
            if (f < 0.0f) {
                f = 0.0f;
            }
            if (f > 1.0f) {
                f = 1.0f;
            }
            this.mVolume = f;
            applyPlaybackProperties_sync();
        }
    }

    public float getVolume() {
        float f;
        synchronized (this.mPlaybackSettingsLock) {
            f = this.mVolume;
        }
        return f;
    }

    private void applyPlaybackProperties_sync() {
        IRingtonePlayer iRingtonePlayer;
        MediaPlayer mediaPlayer = this.mLocalPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(this.mVolume);
            this.mLocalPlayer.setLooping(this.mIsLooping);
        } else {
            if (this.mAllowRemote && (iRingtonePlayer = this.mRemotePlayer) != null) {
                try {
                    iRingtonePlayer.setPlaybackProperties(this.mRemoteToken, this.mVolume, this.mIsLooping);
                    return;
                } catch (RemoteException e) {
                    Log.w(TAG, "Problem setting playback properties: ", e);
                    return;
                }
            }
            Log.w(TAG, "Neither local nor remote player available when applying playback properties");
        }
    }

    public String getTitle(Context context) throws Throwable {
        String str = this.mTitle;
        if (str != null) {
            return str;
        }
        String title = getTitle(context, this.mUri, true, this.mAllowRemote);
        this.mTitle = title;
        return title;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getTitle(android.content.Context r8, android.net.Uri r9, boolean r10, boolean r11) throws java.lang.Throwable {
        /*
            android.content.ContentResolver r0 = r8.getContentResolver()
            if (r9 == 0) goto L98
            java.lang.String r1 = r9.getAuthority()
            java.lang.String r1 = android.content.ContentProvider.getAuthorityWithoutUserId(r1)
            java.lang.String r2 = "settings"
            boolean r2 = r2.equals(r1)
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L35
            if (r10 == 0) goto L9f
            int r9 = android.media.RingtoneManager.getDefaultType(r9)
            android.net.Uri r9 = android.media.RingtoneManager.getActualDefaultRingtoneUri(r8, r9)
            r10 = 0
            java.lang.String r9 = getTitle(r8, r9, r10, r11)
            r11 = 17041172(0x1040714, float:2.424965E-38)
            java.lang.Object[] r0 = new java.lang.Object[r6]
            r0[r10] = r9
            java.lang.String r7 = r8.getString(r11, r0)
            goto L9f
        L35:
            java.lang.String r10 = "media"
            boolean r10 = r10.equals(r1)     // Catch: java.lang.Throwable -> L6b java.lang.SecurityException -> L6d
            if (r10 == 0) goto L64
            if (r11 == 0) goto L42
            r3 = r7
            goto L46
        L42:
            java.lang.String r10 = "mime_type LIKE 'audio/%' OR mime_type IN ('application/ogg', 'application/x-flac')"
            r3 = r10
        L46:
            java.lang.String[] r2 = android.media.Ringtone.MEDIA_COLUMNS     // Catch: java.lang.Throwable -> L6b java.lang.SecurityException -> L6d
            r4 = 0
            r5 = 0
            r1 = r9
            android.database.Cursor r10 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L6b java.lang.SecurityException -> L6d
            if (r10 == 0) goto L65
            int r0 = r10.getCount()     // Catch: java.lang.SecurityException -> L6e java.lang.Throwable -> L7d
            if (r0 != r6) goto L65
            r10.moveToFirst()     // Catch: java.lang.SecurityException -> L6e java.lang.Throwable -> L7d
            java.lang.String r8 = r10.getString(r6)     // Catch: java.lang.SecurityException -> L6e java.lang.Throwable -> L7d
            if (r10 == 0) goto L63
            r10.close()
        L63:
            return r8
        L64:
            r10 = r7
        L65:
            if (r10 == 0) goto L91
        L67:
            r10.close()
            goto L91
        L6b:
            r8 = move-exception
            goto L88
        L6d:
            r10 = r7
        L6e:
            if (r11 == 0) goto L80
            java.lang.String r11 = "audio"
            java.lang.Object r11 = r8.getSystemService(r11)     // Catch: java.lang.Throwable -> L7d
            android.media.AudioManager r11 = (android.media.AudioManager) r11     // Catch: java.lang.Throwable -> L7d
            android.media.IRingtonePlayer r11 = r11.getRingtonePlayer()     // Catch: java.lang.Throwable -> L7d
            goto L81
        L7d:
            r8 = move-exception
            r7 = r10
            goto L88
        L80:
            r11 = r7
        L81:
            if (r11 == 0) goto L8e
            java.lang.String r7 = r11.getTitle(r9)     // Catch: java.lang.Throwable -> L7d android.os.RemoteException -> L8e
            goto L8e
        L88:
            if (r7 == 0) goto L8d
            r7.close()
        L8d:
            throw r8
        L8e:
            if (r10 == 0) goto L91
            goto L67
        L91:
            if (r7 != 0) goto L9f
            java.lang.String r7 = r9.getLastPathSegment()
            goto L9f
        L98:
            r9 = 17041176(0x1040718, float:2.424966E-38)
            java.lang.String r7 = r8.getString(r9)
        L9f:
            if (r7 != 0) goto Lac
            r9 = 17041177(0x1040719, float:2.4249663E-38)
            java.lang.String r7 = r8.getString(r9)
            if (r7 != 0) goto Lac
            java.lang.String r7 = ""
        Lac:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.Ringtone.getTitle(android.content.Context, android.net.Uri, boolean, boolean):java.lang.String");
    }

    public void setUri(Uri uri) {
        setUri(uri, null);
    }

    public void setUri(Uri uri, VolumeShaper.Configuration configuration) {
        this.mVolumeShaperConfig = configuration;
        destroyLocalPlayer();
        this.mUri = uri;
        if (uri == null) {
            return;
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mLocalPlayer = mediaPlayer;
        try {
            mediaPlayer.setDataSource(this.mContext, this.mUri);
            this.mLocalPlayer.setAudioAttributes(this.mAudioAttributes);
            synchronized (this.mPlaybackSettingsLock) {
                applyPlaybackProperties_sync();
            }
            VolumeShaper.Configuration configuration2 = this.mVolumeShaperConfig;
            if (configuration2 != null) {
                this.mVolumeShaper = this.mLocalPlayer.createVolumeShaper(configuration2);
            }
            this.mLocalPlayer.prepare();
        } catch (IOException | SecurityException e) {
            destroyLocalPlayer();
            if (!this.mAllowRemote) {
                Log.w(TAG, "Remote playback not allowed: " + e);
            }
        }
        if (this.mLocalPlayer != null) {
            Log.d(TAG, "Successfully created local player");
        } else {
            Log.d(TAG, "Problem opening; delegating to remote player");
        }
    }

    public Uri getUri() {
        return this.mUri;
    }

    public void play() {
        boolean z;
        float f;
        if (this.mLocalPlayer != null) {
            if (this.mAudioManager.getStreamVolume(AudioAttributes.toLegacyStreamType(this.mAudioAttributes)) != 0) {
                startLocalPlayer();
                return;
            }
            return;
        }
        if (this.mAllowRemote && this.mRemotePlayer != null) {
            Uri canonicalUri = this.mUri.getCanonicalUri();
            synchronized (this.mPlaybackSettingsLock) {
                z = this.mIsLooping;
                f = this.mVolume;
            }
            try {
                this.mRemotePlayer.playWithVolumeShaping(this.mRemoteToken, canonicalUri, this.mAudioAttributes, f, z, this.mVolumeShaperConfig);
                return;
            } catch (RemoteException e) {
                if (playFallbackRingtone()) {
                    return;
                }
                Log.w(TAG, "Problem playing ringtone: " + e);
                return;
            }
        }
        if (playFallbackRingtone()) {
            return;
        }
        Log.w(TAG, "Neither local nor remote playback available");
    }

    public void stop() {
        IRingtonePlayer iRingtonePlayer;
        if (this.mLocalPlayer != null) {
            destroyLocalPlayer();
            return;
        }
        if (!this.mAllowRemote || (iRingtonePlayer = this.mRemotePlayer) == null) {
            return;
        }
        try {
            iRingtonePlayer.stop(this.mRemoteToken);
        } catch (RemoteException e) {
            Log.w(TAG, "Problem stopping ringtone: " + e);
        }
    }

    private void destroyLocalPlayer() {
        MediaPlayer mediaPlayer = this.mLocalPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(null);
            this.mLocalPlayer.reset();
            this.mLocalPlayer.release();
            this.mLocalPlayer = null;
            this.mVolumeShaper = null;
            ArrayList<Ringtone> arrayList = sActiveRingtones;
            synchronized (arrayList) {
                arrayList.remove(this);
            }
        }
    }

    private void startLocalPlayer() {
        if (this.mLocalPlayer == null) {
            return;
        }
        ArrayList<Ringtone> arrayList = sActiveRingtones;
        synchronized (arrayList) {
            arrayList.add(this);
        }
        this.mLocalPlayer.setOnCompletionListener(this.mCompletionListener);
        this.mLocalPlayer.start();
        VolumeShaper volumeShaper = this.mVolumeShaper;
        if (volumeShaper != null) {
            volumeShaper.apply(VolumeShaper.Operation.PLAY);
        }
    }

    public boolean isPlaying() {
        IRingtonePlayer iRingtonePlayer;
        MediaPlayer mediaPlayer = this.mLocalPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        if (this.mAllowRemote && (iRingtonePlayer = this.mRemotePlayer) != null) {
            try {
                return iRingtonePlayer.isPlaying(this.mRemoteToken);
            } catch (RemoteException e) {
                Log.w(TAG, "Problem checking ringtone: " + e);
                return false;
            }
        }
        Log.w(TAG, "Neither local nor remote playback available");
        return false;
    }

    private boolean playFallbackRingtone() {
        if (this.mAudioManager.getStreamVolume(AudioAttributes.toLegacyStreamType(this.mAudioAttributes)) == 0) {
            return false;
        }
        int defaultType = RingtoneManager.getDefaultType(this.mUri);
        if (defaultType == -1 || RingtoneManager.getActualDefaultRingtoneUri(this.mContext, defaultType) != null) {
            try {
                AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = this.mContext.getResources().openRawResourceFd(R.raw.fallbackring);
                if (assetFileDescriptorOpenRawResourceFd != null) {
                    this.mLocalPlayer = new MediaPlayer();
                    if (assetFileDescriptorOpenRawResourceFd.getDeclaredLength() < 0) {
                        this.mLocalPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor());
                    } else {
                        this.mLocalPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getDeclaredLength());
                    }
                    this.mLocalPlayer.setAudioAttributes(this.mAudioAttributes);
                    synchronized (this.mPlaybackSettingsLock) {
                        applyPlaybackProperties_sync();
                    }
                    VolumeShaper.Configuration configuration = this.mVolumeShaperConfig;
                    if (configuration != null) {
                        this.mVolumeShaper = this.mLocalPlayer.createVolumeShaper(configuration);
                    }
                    this.mLocalPlayer.prepare();
                    startLocalPlayer();
                    assetFileDescriptorOpenRawResourceFd.close();
                    return true;
                }
                Log.e(TAG, "Could not load fallback ringtone");
                return false;
            } catch (Resources.NotFoundException unused) {
                Log.e(TAG, "Fallback ringtone does not exist");
                return false;
            } catch (IOException unused2) {
                destroyLocalPlayer();
                Log.e(TAG, "Failed to open fallback ringtone");
                return false;
            }
        }
        Log.w(TAG, "not playing fallback for " + this.mUri);
        return false;
    }

    void setTitle(String str) {
        this.mTitle = str;
    }

    protected void finalize() {
        MediaPlayer mediaPlayer = this.mLocalPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {
        MyOnCompletionListener() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            synchronized (Ringtone.sActiveRingtones) {
                Ringtone.sActiveRingtones.remove(Ringtone.this);
            }
            mediaPlayer.setOnCompletionListener(null);
        }
    }
}
