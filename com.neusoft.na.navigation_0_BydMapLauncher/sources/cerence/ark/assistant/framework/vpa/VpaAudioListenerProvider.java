package cerence.ark.assistant.framework.vpa;

import android.content.Context;
import cerence.ark.assistant.common.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VpaAudioListenerProvider {
    private static final String TAG = "VpaAudioListenerProvider";

    public abstract VpaAudioListener createAudioListener();

    public static VpaAudioListenerProvider getProvider(Context context) {
        System.setProperty("java.class.path", System.getProperty("java.class.path") + System.getProperty("path.separator") + context.getFilesDir());
        Iterator<VpaAudioListenerProvider> providers = getProviders(context.getClassLoader());
        ArrayList arrayList = new ArrayList();
        while (providers.hasNext()) {
            arrayList.add(providers.next());
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Logger.info(TAG, "provider: " + ((VpaAudioListenerProvider) it.next()).getClass().getName());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (VpaAudioListenerProvider) arrayList.get(0);
    }

    public static Iterator<VpaAudioListenerProvider> getProviders() {
        return ServiceLoader.load(VpaAudioListenerProvider.class).iterator();
    }

    private static Iterator<VpaAudioListenerProvider> getProviders(ClassLoader classLoader) {
        return ServiceLoader.load(VpaAudioListenerProvider.class, classLoader).iterator();
    }
}
