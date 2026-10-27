package cerence.ark.assistant.api.dynamic.observer;

import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import cerence.ark.assistant.api.dynamic.ContentType;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class MusicObserver extends BaseContentObserver {
    private static final String TAG = "MusicObserver";

    public MusicObserver() {
        super(ContentType.MUSIC);
    }

    @Override // cerence.ark.assistant.api.dynamic.observer.BaseContentObserver
    protected Uri getUri() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (!Utils.checkSelfPermission("android.permission.READ_MEDIA_AUDIO")) {
                Logger.error(TAG, "registerObserver: the android.permission.READ_MEDIA_AUDIO was denied in Android 13 or later.");
                return null;
            }
        } else if (!Utils.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            Logger.error(TAG, "registerObserver: the android.permission.READ_EXTERNAL_STORAGE was denied.");
            return null;
        }
        return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }
}
