package cerence.ark.assistant.framework.audio.player;

import android.content.Context;
import cerence.ark.assistant.common.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

/* JADX INFO: loaded from: classes.dex */
public abstract class AudioPlayerProvider {
    private static final String TAG = "AudioPlayerProvider";

    public abstract IAudioPlayer createAudioPlayer();

    public static AudioPlayerProvider getProvider(Context context) {
        System.setProperty("java.class.path", System.getProperty("java.class.path") + System.getProperty("path.separator") + context.getFilesDir());
        Iterator<AudioPlayerProvider> providers = getProviders(context.getClassLoader());
        ArrayList arrayList = new ArrayList();
        while (providers.hasNext()) {
            arrayList.add(providers.next());
        }
        Logger.info(TAG, "AudioPlayer provider num = " + arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Logger.info(TAG, "AudioPlayer provider: " + ((AudioPlayerProvider) it.next()).getClass().getName());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (AudioPlayerProvider) arrayList.get(0);
    }

    public static Iterator<AudioPlayerProvider> getProviders() {
        return ServiceLoader.load(AudioPlayerProvider.class).iterator();
    }

    private static Iterator<AudioPlayerProvider> getProviders(ClassLoader classLoader) {
        return ServiceLoader.load(AudioPlayerProvider.class, classLoader).iterator();
    }
}
