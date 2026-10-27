package cerence.ark.assistant.framework.audio.recorder;

import android.content.Context;
import cerence.ark.assistant.common.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

/* JADX INFO: loaded from: classes.dex */
public abstract class AudioRecorderProvider {
    private static final String TAG = "AudioRecorderProvider";

    public abstract IAudioRecorder createAudioRecorder();

    public static AudioRecorderProvider getProvider(Context context) {
        System.setProperty("java.class.path", System.getProperty("java.class.path") + System.getProperty("path.separator") + context.getFilesDir());
        Iterator<AudioRecorderProvider> providers = getProviders(context.getClassLoader());
        ArrayList arrayList = new ArrayList();
        while (providers.hasNext()) {
            arrayList.add(providers.next());
        }
        Logger.debug(TAG, "AudioRecorder provider num = " + arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Logger.debug(TAG, "AudioRecorder provider: " + ((AudioRecorderProvider) it.next()).getClass().getName());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (AudioRecorderProvider) arrayList.get(0);
    }

    public static Iterator<AudioRecorderProvider> getProviders() {
        return ServiceLoader.load(AudioRecorderProvider.class).iterator();
    }

    private static Iterator<AudioRecorderProvider> getProviders(ClassLoader classLoader) {
        return ServiceLoader.load(AudioRecorderProvider.class, classLoader).iterator();
    }
}
