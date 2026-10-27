package android.media;

import android.annotation.NonNull;
import android.media.AudioManager;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class AudioFocusRequest {

    public final /* synthetic */ class Builder {
        static {
            throw new NoClassDefFoundError();
        }

        public /* synthetic */ Builder(int i) {
        }

        public native /* synthetic */ AudioFocusRequest build();

        @NonNull
        public native /* synthetic */ Builder setAcceptsDelayedFocusGain(boolean z);

        @NonNull
        public native /* synthetic */ Builder setAudioAttributes(@NonNull AudioAttributes audioAttributes);

        @NonNull
        public native /* synthetic */ Builder setFocusGain(int i);

        @NonNull
        public native /* synthetic */ Builder setOnAudioFocusChangeListener(@NonNull AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener);

        @NonNull
        public native /* synthetic */ Builder setOnAudioFocusChangeListener(@NonNull AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, @NonNull Handler handler);

        @NonNull
        public native /* synthetic */ Builder setWillPauseWhenDucked(boolean z);
    }

    static {
        throw new NoClassDefFoundError();
    }
}
