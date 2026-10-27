package com.amazon.music.media.playback.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import androidx.annotation.NonNull;
import com.amazon.music.media.playback.util.ListenerSet;
import com.amazon.music.media.playback.util.StrictMode;
import com.visualon.OSMPUtils.voMimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AudioFocusManager {
    private AudioManager audioManager;
    private boolean audioWillPlay;
    private final Context context;
    private boolean localPlayback;
    private boolean receiverRegistered;
    private AudioFocusRequest request;
    protected final Logger logger = LoggerFactory.getLogger(AudioFocusManager.class.getSimpleName());
    private final ListenerSet<Listener> listeners = new ListenerSet<>(Listener.class, ListenerSet.DispatchStrategy.SYNCHRONOUS, ListenerSet.DuplicateStrategy.ALLOW_DUPLICATES);
    private final IntentFilter intentFilter = new IntentFilter("android.media.AUDIO_BECOMING_NOISY");
    private int audioFocusStatus = -1;
    private final AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.amazon.music.media.playback.impl.AudioFocusManager.1
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (i == -3) {
                AudioFocusManager.this.logger.info("AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                AudioFocusManager.this.setAudioFocusStatus(i);
                ((Listener) AudioFocusManager.this.listeners.proxy()).onSystemDuck();
                return;
            }
            if (i == -2) {
                AudioFocusManager.this.logger.info("AUDIOFOCUS_LOSS_TRANSIENT");
                AudioFocusManager.this.setAudioFocusStatus(i);
                ((Listener) AudioFocusManager.this.listeners.proxy()).onSystemPause();
            } else {
                if (i == 1) {
                    AudioFocusManager.this.logger.info("AUDIOFOCUS_GAIN");
                    AudioFocusManager.this.setAudioFocusStatus(i);
                    ((Listener) AudioFocusManager.this.listeners.proxy()).onSystemNormal();
                    return;
                }
                AudioFocusManager.this.logger.info("AUDIOFOCUS_LOSS (or equivalent) - " + i);
                AudioFocusManager.this.setAudioFocusStatus(i);
                ((Listener) AudioFocusManager.this.listeners.proxy()).onSystemStop();
            }
        }
    };
    private final BroadcastReceiver audioBecomingNoisyReceiver = new BroadcastReceiver() { // from class: com.amazon.music.media.playback.impl.AudioFocusManager.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                AudioFocusManager.this.logger.info("ACTION_AUDIO_BECOMING_NOISY");
                ((Listener) AudioFocusManager.this.listeners.proxy()).onSystemStop();
            }
        }
    };

    public interface Listener {
        void onAudioFocusChanged(boolean z);

        void onSystemDuck();

        void onSystemNormal();

        void onSystemPause();

        void onSystemStop();
    }

    public AudioFocusManager(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public AudioManager getAudioManager() {
        if (this.audioManager == null) {
            try {
                AudioManager audioManager = (AudioManager) this.context.getApplicationContext().getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
                this.audioManager = audioManager;
                if (audioManager == null) {
                    throw new NullPointerException("Unable to obtain AudioManager!");
                }
            } catch (RuntimeException e) {
                StrictMode.crashIfStrict(e);
            }
        }
        return this.audioManager;
    }

    protected int doRequestAudioFocus(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        AudioManager audioManager = getAudioManager();
        int i = 1;
        if (audioManager == null) {
            return 1;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(i).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build()).setOnAudioFocusChangeListener(onAudioFocusChangeListener).setAcceptsDelayedFocusGain(true).setWillPauseWhenDucked(false).build();
            this.request = audioFocusRequestBuild;
            return audioManager.requestAudioFocus(audioFocusRequestBuild);
        }
        return audioManager.requestAudioFocus(onAudioFocusChangeListener, 3, 1);
    }

    protected void doAbandonAudioFocus(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        AudioFocusRequest audioFocusRequest;
        AudioManager audioManager = getAudioManager();
        if (audioManager != null) {
            if (Build.VERSION.SDK_INT >= 26 && (audioFocusRequest = this.request) != null) {
                audioManager.abandonAudioFocusRequest(audioFocusRequest);
                this.request = null;
            } else {
                audioManager.abandonAudioFocus(onAudioFocusChangeListener);
            }
        }
    }

    public void setLocalPlayback(boolean z) {
        if (this.localPlayback != z) {
            this.localPlayback = z;
            updateReceiverRegistered();
        }
    }

    public boolean requestAudioFocus() {
        if (hasAudioFocus()) {
            this.logger.info("requestAudioFocus() called, but already have focus.");
            return true;
        }
        int iDoRequestAudioFocus = doRequestAudioFocus(this.onAudioFocusChangeListener);
        boolean z = 1 == iDoRequestAudioFocus;
        boolean z2 = iDoRequestAudioFocus == 0;
        Logger logger = this.logger;
        StringBuilder sb = new StringBuilder();
        sb.append("requestAudioFocus() => ");
        sb.append(z ? "GRANTED" : z2 ? "FAILED" : "DELAYED");
        logger.info(sb.toString());
        if (z) {
            setAudioFocusStatus(1);
        } else if (z2) {
            setAudioFocusStatus(-1);
        } else {
            this.listeners.proxy().onSystemPause();
            setAudioFocusStatus(-2);
        }
        return !z2;
    }

    public void abandonAudioFocus() {
        this.logger.info("abandonAudioFocus()");
        doAbandonAudioFocus(this.onAudioFocusChangeListener);
        setAudioFocusStatus(-1);
    }

    public boolean hasAudioFocus() {
        int i = this.audioFocusStatus;
        return i == 1 || i == -3;
    }

    public int getAudioFocusStatus() {
        return this.audioFocusStatus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAudioFocusStatus(int i) {
        boolean zHasAudioFocus = hasAudioFocus();
        this.audioFocusStatus = i;
        boolean zHasAudioFocus2 = hasAudioFocus();
        if (zHasAudioFocus2 != zHasAudioFocus) {
            this.logger.info(zHasAudioFocus2 ? "gained audioFocus" : "lost audioFocus");
            this.listeners.proxy().onAudioFocusChanged(zHasAudioFocus2);
        }
    }

    public void setAudioWillPlay(boolean z) {
        if (this.audioWillPlay != z) {
            this.audioWillPlay = z;
            updateReceiverRegistered();
        }
    }

    private void updateReceiverRegistered() {
        boolean z = this.audioWillPlay && this.localPlayback;
        if (z && !this.receiverRegistered) {
            this.receiverRegistered = true;
            this.context.registerReceiver(this.audioBecomingNoisyReceiver, this.intentFilter);
        } else {
            if (z || !this.receiverRegistered) {
                return;
            }
            this.receiverRegistered = false;
            this.context.unregisterReceiver(this.audioBecomingNoisyReceiver);
        }
    }
}
