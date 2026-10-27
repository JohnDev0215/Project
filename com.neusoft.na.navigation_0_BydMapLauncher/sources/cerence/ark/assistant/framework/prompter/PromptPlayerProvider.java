package cerence.ark.assistant.framework.prompter;

import android.content.Context;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.audio.player.IAudioPlayer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PromptPlayerProvider {
    private static final String TAG = "PromptPlayerProvider";

    public abstract IAudioPlayer createPromptPlayer(String str);

    public static PromptPlayerProvider getProvider(Context context) {
        System.setProperty("java.class.path", System.getProperty("java.class.path") + System.getProperty("path.separator") + context.getFilesDir());
        Iterator<PromptPlayerProvider> providers = getProviders(context.getClassLoader());
        ArrayList arrayList = new ArrayList();
        while (providers.hasNext()) {
            arrayList.add(providers.next());
        }
        Logger.info(TAG, "PromptPlayerProvider num = " + arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Logger.info(TAG, "PromptPlayerProvider: " + ((PromptPlayerProvider) it.next()).getClass().getName());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (PromptPlayerProvider) arrayList.get(0);
    }

    public static Iterator<PromptPlayerProvider> getProviders() {
        return ServiceLoader.load(PromptPlayerProvider.class).iterator();
    }

    private static Iterator<PromptPlayerProvider> getProviders(ClassLoader classLoader) {
        return ServiceLoader.load(PromptPlayerProvider.class, classLoader).iterator();
    }
}
