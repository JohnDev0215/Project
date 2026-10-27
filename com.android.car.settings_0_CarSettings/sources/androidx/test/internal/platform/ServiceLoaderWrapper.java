package androidx.test.internal.platform;

import android.os.StrictMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/* JADX INFO: loaded from: classes5.dex */
public final class ServiceLoaderWrapper {

    public interface Factory<T> {
        T create();
    }

    private ServiceLoaderWrapper() {
    }

    public static <T> List<T> loadService(Class<T> serviceClass) {
        StrictMode.ThreadPolicy oldPolicy = StrictMode.allowThreadDiskReads();
        ArrayList arrayList = new ArrayList();
        Iterator it = ServiceLoader.load(serviceClass).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        StrictMode.setThreadPolicy(oldPolicy);
        return arrayList;
    }

    public static <T> T loadSingleService(Class<T> serviceClass, Factory<T> defaultImplFactory) {
        List<T> impls = loadService(serviceClass);
        if (impls.isEmpty()) {
            return defaultImplFactory.create();
        }
        if (impls.size() == 1) {
            return impls.get(0);
        }
        String strValueOf = String.valueOf(serviceClass.getName());
        throw new IllegalStateException(strValueOf.length() != 0 ? "Found more than one implementation for ".concat(strValueOf) : new String("Found more than one implementation for "));
    }
}
