package a.b.a.a.f;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import androidx.annotation.RequiresApi;
import java.util.List;

/* JADX INFO: compiled from: MediaControllerCompatApi21.java */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
public class c {

    /* JADX INFO: compiled from: MediaControllerCompatApi21.java */
    public interface a {
        void a(CharSequence charSequence);

        void b();

        void c(Bundle bundle);

        void d(List<?> list);

        void e(Object obj);

        void f(int i, int i2, int i3, int i4, int i5);

        void g(Object obj);

        void h(String str, Bundle bundle);
    }

    /* JADX INFO: compiled from: MediaControllerCompatApi21.java */
    public static class b<T extends a> extends MediaController.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final T f54a;

        public b(T t) {
            this.f54a = t;
        }

        @Override // android.media.session.MediaController.Callback
        public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
            this.f54a.f(playbackInfo.getPlaybackType(), C0005c.b(playbackInfo), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume());
        }

        @Override // android.media.session.MediaController.Callback
        public void onExtrasChanged(Bundle bundle) {
            MediaSessionCompat.a(bundle);
            this.f54a.c(bundle);
        }

        @Override // android.media.session.MediaController.Callback
        public void onMetadataChanged(MediaMetadata mediaMetadata) {
            this.f54a.e(mediaMetadata);
        }

        @Override // android.media.session.MediaController.Callback
        public void onPlaybackStateChanged(PlaybackState playbackState) {
            this.f54a.g(playbackState);
        }

        @Override // android.media.session.MediaController.Callback
        public void onQueueChanged(List<MediaSession.QueueItem> list) {
            this.f54a.d(list);
        }

        @Override // android.media.session.MediaController.Callback
        public void onQueueTitleChanged(CharSequence charSequence) {
            this.f54a.a(charSequence);
        }

        @Override // android.media.session.MediaController.Callback
        public void onSessionDestroyed() {
            this.f54a.b();
        }

        @Override // android.media.session.MediaController.Callback
        public void onSessionEvent(String str, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            this.f54a.h(str, bundle);
        }
    }

    /* JADX INFO: renamed from: a.b.a.a.f.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaControllerCompatApi21.java */
    public static class C0005c {
        public static AudioAttributes a(Object obj) {
            return ((MediaController.PlaybackInfo) obj).getAudioAttributes();
        }

        public static int b(Object obj) {
            return c(a(obj));
        }

        public static int c(AudioAttributes audioAttributes) {
            if ((audioAttributes.getFlags() & 1) == 1) {
                return 7;
            }
            if ((audioAttributes.getFlags() & 4) == 4) {
                return 6;
            }
            int usage = audioAttributes.getUsage();
            if (usage == 13) {
                return 1;
            }
            switch (usage) {
                case 2:
                    return 0;
                case 3:
                    return 8;
                case 4:
                    return 4;
                case 5:
                case 7:
                case 8:
                case 9:
                case 10:
                    return 5;
                case 6:
                    return 2;
                default:
                    return 3;
            }
        }
    }

    public static Object a(a aVar) {
        return new b(aVar);
    }

    public static boolean b(Object obj, KeyEvent keyEvent) {
        return ((MediaController) obj).dispatchMediaButtonEvent(keyEvent);
    }

    public static Object c(Context context, Object obj) {
        return new MediaController(context, (MediaSession.Token) obj);
    }

    public static void d(Object obj, String str, Bundle bundle, ResultReceiver resultReceiver) {
        ((MediaController) obj).sendCommand(str, bundle, resultReceiver);
    }
}
