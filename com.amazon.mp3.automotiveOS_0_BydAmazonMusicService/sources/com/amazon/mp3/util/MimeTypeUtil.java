package com.amazon.mp3.util;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.music.media.playback.Playback;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class MimeTypeUtil {
    private static final Set<String> sUnsupportedTypes;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("audio/x-mpegurl");
        hashSet.add("audio/amr");
        sUnsupportedTypes = Collections.unmodifiableSet(hashSet);
    }

    public static boolean isUnsupported(final Uri uri) {
        return uri == null || !Playback.getInstance().getPlaybackConfig().getLocalPlayer(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(AmazonApplication.getApplication().getApplicationContext())).canPlay(uri);
    }

    public static boolean isUnsupported(final File file) {
        return file == null || isUnsupported(Uri.fromFile(file));
    }

    public static boolean isUnsupported(final String path) {
        if (path == null) {
            return false;
        }
        if (path.startsWith(File.separator)) {
            return isUnsupported(new File(path));
        }
        return isUnsupported(Uri.parse(path));
    }

    public static Set<String> getUnsupportedTypes() {
        return sUnsupportedTypes;
    }
}
