package android.media;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MediaActionSound {
    public static final int FOCUS_COMPLETE = 1;
    private static final int NUM_MEDIA_SOUND_STREAMS = 1;
    public static final int SHUTTER_CLICK = 0;
    private static final String[] SOUND_DIRS = {"/product/media/audio/ui/", "/system/media/audio/ui/"};
    private static final String[] SOUND_FILES = {"camera_click.ogg", "camera_focus.ogg", "VideoRecord.ogg", "VideoStop.ogg"};
    public static final int START_VIDEO_RECORDING = 2;
    private static final int STATE_LOADED = 3;
    private static final int STATE_LOADING = 1;
    private static final int STATE_LOADING_PLAY_REQUESTED = 2;
    private static final int STATE_NOT_LOADED = 0;
    public static final int STOP_VIDEO_RECORDING = 3;
    private static final String TAG = "MediaActionSound";
    private SoundPool.OnLoadCompleteListener mLoadCompleteListener = new SoundPool.OnLoadCompleteListener() { // from class: android.media.MediaActionSound.1
        @Override // android.media.SoundPool.OnLoadCompleteListener
        public void onLoadComplete(SoundPool soundPool, int i, int i2) {
            int i3 = 0;
            for (SoundState soundState : MediaActionSound.this.mSounds) {
                if (soundState.id == i) {
                    synchronized (soundState) {
                        if (i2 != 0) {
                            soundState.state = 0;
                            soundState.id = 0;
                            Log.e(MediaActionSound.TAG, "OnLoadCompleteListener() error: " + i2 + " loading sound: " + soundState.name);
                            return;
                        }
                        int i4 = soundState.state;
                        if (i4 == 1) {
                            soundState.state = 3;
                        } else if (i4 == 2) {
                            i3 = soundState.id;
                            soundState.state = 3;
                        } else {
                            Log.e(MediaActionSound.TAG, "OnLoadCompleteListener() called in wrong state: " + soundState.state + " for sound: " + soundState.name);
                        }
                        int i5 = i3;
                        if (i5 != 0) {
                            soundPool.play(i5, 1.0f, 1.0f, 0, 0, 1.0f);
                            return;
                        }
                        return;
                    }
                }
            }
        }
    };
    private SoundPool mSoundPool;
    private SoundState[] mSounds;

    private class SoundState {
        public final int name;
        public int id = 0;
        public int state = 0;

        public SoundState(int i) {
            this.name = i;
        }
    }

    public MediaActionSound() {
        SoundPool soundPoolBuild = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(13).setFlags(1).setContentType(4).build()).build();
        this.mSoundPool = soundPoolBuild;
        soundPoolBuild.setOnLoadCompleteListener(this.mLoadCompleteListener);
        this.mSounds = new SoundState[SOUND_FILES.length];
        int i = 0;
        while (true) {
            SoundState[] soundStateArr = this.mSounds;
            if (i >= soundStateArr.length) {
                return;
            }
            soundStateArr[i] = new SoundState(i);
            i++;
        }
    }

    private int loadSound(SoundState soundState) {
        String str = SOUND_FILES[soundState.name];
        for (String str2 : SOUND_DIRS) {
            int iLoad = this.mSoundPool.load(str2 + str, 1);
            if (iLoad > 0) {
                soundState.state = 1;
                soundState.id = iLoad;
                return iLoad;
            }
        }
        return 0;
    }

    public void load(int i) {
        if (i < 0 || i >= SOUND_FILES.length) {
            throw new RuntimeException("Unknown sound requested: " + i);
        }
        SoundState soundState = this.mSounds[i];
        synchronized (soundState) {
            if (soundState.state == 0) {
                if (loadSound(soundState) <= 0) {
                    Log.e(TAG, "load() error loading sound: " + i);
                }
            } else {
                Log.e(TAG, "load() called in wrong state: " + soundState + " for sound: " + i);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0069 A[Catch: all -> 0x006e, TryCatch #0 {, blocks: (B:7:0x000c, B:13:0x0016, B:19:0x006c, B:14:0x0038, B:18:0x0069, B:15:0x0048, B:17:0x0051), top: B:26:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void play(int r9) {
        /*
            r8 = this;
            if (r9 < 0) goto L71
            java.lang.String[] r0 = android.media.MediaActionSound.SOUND_FILES
            int r0 = r0.length
            if (r9 >= r0) goto L71
            android.media.MediaActionSound$SoundState[] r0 = r8.mSounds
            r0 = r0[r9]
            monitor-enter(r0)
            int r1 = r0.state     // Catch: java.lang.Throwable -> L6e
            if (r1 == 0) goto L48
            r2 = 1
            if (r1 == r2) goto L69
            r2 = 3
            if (r1 == r2) goto L38
            java.lang.String r1 = "MediaActionSound"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6e
            r2.<init>()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r3 = "play() called in wrong state: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L6e
            int r3 = r0.state     // Catch: java.lang.Throwable -> L6e
            r2.append(r3)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r3 = " for sound: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L6e
            r2.append(r9)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r9 = r2.toString()     // Catch: java.lang.Throwable -> L6e
            android.util.Log.e(r1, r9)     // Catch: java.lang.Throwable -> L6e
            goto L6c
        L38:
            android.media.SoundPool r1 = r8.mSoundPool     // Catch: java.lang.Throwable -> L6e
            int r2 = r0.id     // Catch: java.lang.Throwable -> L6e
            r3 = 1065353216(0x3f800000, float:1.0)
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            r6 = 0
            r7 = 1065353216(0x3f800000, float:1.0)
            r1.play(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L6e
            goto L6c
        L48:
            r8.loadSound(r0)     // Catch: java.lang.Throwable -> L6e
            int r1 = r8.loadSound(r0)     // Catch: java.lang.Throwable -> L6e
            if (r1 > 0) goto L69
            java.lang.String r1 = "MediaActionSound"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6e
            r2.<init>()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r3 = "play() error loading sound: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L6e
            r2.append(r9)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r9 = r2.toString()     // Catch: java.lang.Throwable -> L6e
            android.util.Log.e(r1, r9)     // Catch: java.lang.Throwable -> L6e
            goto L6c
        L69:
            r9 = 2
            r0.state = r9     // Catch: java.lang.Throwable -> L6e
        L6c:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6e
            return
        L6e:
            r9 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6e
            throw r9
        L71:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown sound requested: "
            r1.append(r2)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r0.<init>(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.MediaActionSound.play(int):void");
    }

    public void release() {
        if (this.mSoundPool != null) {
            for (SoundState soundState : this.mSounds) {
                synchronized (soundState) {
                    soundState.state = 0;
                    soundState.id = 0;
                }
            }
            this.mSoundPool.release();
            this.mSoundPool = null;
        }
    }
}
