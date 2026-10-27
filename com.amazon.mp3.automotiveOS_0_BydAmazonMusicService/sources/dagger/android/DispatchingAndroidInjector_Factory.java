package dagger.android;

import dagger.android.AndroidInjector;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes7.dex */
public final class DispatchingAndroidInjector_Factory<T> implements Factory<DispatchingAndroidInjector<T>> {
    public static <T> DispatchingAndroidInjector<T> newInstance(Map<Class<?>, Provider<AndroidInjector.Factory<?>>> map, Map<String, Provider<AndroidInjector.Factory<?>>> map2) {
        return new DispatchingAndroidInjector<>(map, map2);
    }
}
