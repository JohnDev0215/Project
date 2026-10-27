package cerence.ark.assistant.framework.siri;

import android.content.Context;
import cerence.ark.assistant.common.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

/* JADX INFO: loaded from: classes.dex */
public abstract class EnhancedSiriAudioListenerProvider {
    private static final String TAG = "EnhancedSiriAudioListenerProvider";

    public abstract EnhancedSiriAudioListener createAudioListener();

    public static EnhancedSiriAudioListenerProvider getProvider(Context context) {
        System.setProperty("java.class.path", System.getProperty("java.class.path") + System.getProperty("path.separator") + context.getFilesDir());
        Iterator<EnhancedSiriAudioListenerProvider> providers = getProviders(context.getClassLoader());
        ArrayList arrayList = new ArrayList();
        while (providers.hasNext()) {
            arrayList.add(providers.next());
        }
        Logger.debug(TAG, "provider num = " + arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Logger.debug(TAG, "provider: " + ((EnhancedSiriAudioListenerProvider) it.next()).getClass().getName());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (EnhancedSiriAudioListenerProvider) arrayList.get(0);
    }

    public static Iterator<EnhancedSiriAudioListenerProvider> getProviders() {
        return ServiceLoader.load(EnhancedSiriAudioListenerProvider.class).iterator();
    }

    private static Iterator<EnhancedSiriAudioListenerProvider> getProviders(ClassLoader classLoader) {
        return ServiceLoader.load(EnhancedSiriAudioListenerProvider.class, classLoader).iterator();
    }
}
