package cerence.ark.assistant.framework.speech;

import android.content.Context;
import cerence.ark.assistant.common.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

/* JADX INFO: loaded from: classes.dex */
public abstract class SpeechAudioListenerProvider {
    private static final String TAG = "SpeechAudioListenerProvider";

    public abstract SpeechAudioListener createSpeechAudioListener();

    public static SpeechAudioListenerProvider getProvider(Context context) {
        System.setProperty("java.class.path", System.getProperty("java.class.path") + System.getProperty("path.separator") + context.getFilesDir());
        Iterator<SpeechAudioListenerProvider> providers = getProviders(context.getClassLoader());
        ArrayList arrayList = new ArrayList();
        while (providers.hasNext()) {
            arrayList.add(providers.next());
        }
        Logger.debug(TAG, "provider num = " + arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Logger.debug(TAG, "provider: " + ((SpeechAudioListenerProvider) it.next()).getClass().getName());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (SpeechAudioListenerProvider) arrayList.get(0);
    }

    public static Iterator<SpeechAudioListenerProvider> getProviders() {
        return ServiceLoader.load(SpeechAudioListenerProvider.class).iterator();
    }

    private static Iterator<SpeechAudioListenerProvider> getProviders(ClassLoader classLoader) {
        return ServiceLoader.load(SpeechAudioListenerProvider.class, classLoader).iterator();
    }
}
