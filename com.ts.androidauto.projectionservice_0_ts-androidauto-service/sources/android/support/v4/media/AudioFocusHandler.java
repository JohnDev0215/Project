package android.support.v4.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.support.v4.util.ObjectsCompat;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AudioFocusHandler {
    private static final boolean DEBUG = false;
    private static final String TAG = "AudioFocusHandler";
    private final AudioFocusHandlerImpl mImpl;

    interface AudioFocusHandlerImpl {
        void close();

        boolean onPauseRequested();

        boolean onPlayRequested();

        void onPlayerStateChanged(int i);

        void sendIntent(Intent intent);
    }

    AudioFocusHandler(Context context, MediaSession2 session) {
        this.mImpl = new AudioFocusHandlerImplBase(context, session);
    }

    public boolean onPlayRequested() {
        return this.mImpl.onPlayRequested();
    }

    public boolean onPauseRequested() {
        return this.mImpl.onPauseRequested();
    }

    public void onPlayerStateChanged(int playerState) {
        this.mImpl.onPlayerStateChanged(playerState);
    }

    public void close() {
        this.mImpl.close();
    }

    public void sendIntent(Intent intent) {
        this.mImpl.sendIntent(intent);
    }

    private static class AudioFocusHandlerImplBase implements AudioFocusHandlerImpl {
        private static final float VOLUME_DUCK_FACTOR = 0.2f;
        private AudioAttributesCompat mAudioAttributes;
        private final AudioManager.OnAudioFocusChangeListener mAudioFocusListener;
        private final AudioManager mAudioManager;
        private final BroadcastReceiver mBecomingNoisyIntentReceiver;
        private boolean mHasAudioFocus;
        private boolean mHasRegisteredReceiver;
        private final IntentFilter mIntentFilter = new IntentFilter("android.media.AUDIO_BECOMING_NOISY");
        private final Object mLock = new Object();
        private boolean mResumeWhenAudioFocusGain;
        private final MediaSession2 mSession;

        AudioFocusHandlerImplBase(Context context, MediaSession2 session) {
            this.mBecomingNoisyIntentReceiver = new NoisyIntentReceiver();
            this.mAudioFocusListener = new AudioFocusListener();
            this.mSession = session;
            this.mAudioManager = (AudioManager) context.getSystemService("audio");
        }

        private void updateAudioAttributesIfNeeded() {
            AudioAttributesCompat attributes;
            if (this.mSession.getVolumeProvider() != null) {
                attributes = null;
            } else {
                BaseMediaPlayer player = this.mSession.getPlayer();
                attributes = player == null ? null : player.getAudioAttributes();
            }
            synchronized (this.mLock) {
                if (ObjectsCompat.equals(attributes, this.mAudioAttributes)) {
                    return;
                }
                this.mAudioAttributes = attributes;
                if (this.mHasAudioFocus) {
                    boolean zRequestAudioFocusLocked = requestAudioFocusLocked();
                    this.mHasAudioFocus = zRequestAudioFocusLocked;
                    if (!zRequestAudioFocusLocked) {
                        Log.w(AudioFocusHandler.TAG, "Failed to regain audio focus.");
                    }
                }
            }
        }

        @Override // android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImpl
        public boolean onPlayRequested() {
            updateAudioAttributesIfNeeded();
            synchronized (this.mLock) {
                if (!requestAudioFocusLocked()) {
                    return AudioFocusHandler.DEBUG;
                }
                return true;
            }
        }

        @Override // android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImpl
        public boolean onPauseRequested() {
            synchronized (this.mLock) {
                this.mResumeWhenAudioFocusGain = AudioFocusHandler.DEBUG;
            }
            return true;
        }

        @Override // android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImpl
        public void onPlayerStateChanged(int playerState) {
            synchronized (this.mLock) {
                if (playerState == 0) {
                    abandonAudioFocusLocked();
                } else if (playerState == 1) {
                    updateAudioAttributesIfNeeded();
                    unregisterReceiverLocked();
                } else if (playerState == 2) {
                    updateAudioAttributesIfNeeded();
                    registerReceiverLocked();
                } else if (playerState == 3) {
                    abandonAudioFocusLocked();
                    unregisterReceiverLocked();
                }
            }
        }

        @Override // android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImpl
        public void close() {
            synchronized (this.mLock) {
                unregisterReceiverLocked();
                abandonAudioFocusLocked();
            }
        }

        @Override // android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImpl
        public void sendIntent(Intent intent) {
            this.mBecomingNoisyIntentReceiver.onReceive(this.mSession.getContext(), intent);
        }

        private boolean requestAudioFocusLocked() {
            int focusGain = convertAudioAttributesToFocusGainLocked();
            if (focusGain == 0) {
                return true;
            }
            int audioFocusRequestResult = this.mAudioManager.requestAudioFocus(this.mAudioFocusListener, this.mAudioAttributes.getVolumeControlStream(), focusGain);
            if (audioFocusRequestResult == 1) {
                this.mHasAudioFocus = true;
            } else {
                Log.w(AudioFocusHandler.TAG, "requestAudioFocus(" + focusGain + ") failed (return=" + audioFocusRequestResult + ") playback wouldn't start.");
                this.mHasAudioFocus = AudioFocusHandler.DEBUG;
            }
            this.mResumeWhenAudioFocusGain = AudioFocusHandler.DEBUG;
            return this.mHasAudioFocus;
        }

        private void abandonAudioFocusLocked() {
            if (!this.mHasAudioFocus) {
                return;
            }
            this.mAudioManager.abandonAudioFocus(this.mAudioFocusListener);
            this.mHasAudioFocus = AudioFocusHandler.DEBUG;
            this.mResumeWhenAudioFocusGain = AudioFocusHandler.DEBUG;
        }

        private void registerReceiverLocked() {
            if (this.mHasRegisteredReceiver) {
                return;
            }
            this.mSession.getContext().registerReceiver(this.mBecomingNoisyIntentReceiver, this.mIntentFilter);
            this.mHasRegisteredReceiver = true;
        }

        private void unregisterReceiverLocked() {
            if (!this.mHasRegisteredReceiver) {
                return;
            }
            this.mSession.getContext().unregisterReceiver(this.mBecomingNoisyIntentReceiver);
            this.mHasRegisteredReceiver = AudioFocusHandler.DEBUG;
        }

        private int convertAudioAttributesToFocusGainLocked() {
            AudioAttributesCompat audioAttributesCompat = this.mAudioAttributes;
            if (audioAttributesCompat == null) {
                return 0;
            }
            switch (audioAttributesCompat.getUsage()) {
            }
            return 0;
        }

        private class NoisyIntentReceiver extends BroadcastReceiver {
            private NoisyIntentReceiver() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                BaseMediaPlayer player;
                synchronized (AudioFocusHandlerImplBase.this.mLock) {
                    if (AudioFocusHandlerImplBase.this.mHasRegisteredReceiver) {
                        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                            synchronized (AudioFocusHandlerImplBase.this.mLock) {
                                if (AudioFocusHandlerImplBase.this.mAudioAttributes == null) {
                                    return;
                                }
                                int usage = AudioFocusHandlerImplBase.this.mAudioAttributes.getUsage();
                                if (usage == 1) {
                                    AudioFocusHandlerImplBase.this.mSession.pause();
                                } else if (usage == 14 && (player = AudioFocusHandlerImplBase.this.mSession.getPlayer()) != null) {
                                    player.setPlayerVolume(player.getPlayerVolume() * AudioFocusHandlerImplBase.VOLUME_DUCK_FACTOR);
                                }
                            }
                        }
                    }
                }
            }
        }

        private class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
            private float mPlayerDuckingVolume;
            private float mPlayerVolumeBeforeDucking;

            private AudioFocusListener() {
            }

            /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:38:0x0066
                	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
                	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
                	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
                	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
                */
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int r7) {
                /*
                    Method dump skipped, instruction units count: 243
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImplBase.AudioFocusListener.onAudioFocusChange(int):void");
            }
        }
    }
}
