package dagger.android;

import dagger.MembersInjector;

/* JADX INFO: loaded from: classes7.dex */
public final class DaggerApplication_MembersInjector implements MembersInjector<DaggerApplication> {
    public static void injectAndroidInjector(DaggerApplication daggerApplication, DispatchingAndroidInjector<Object> dispatchingAndroidInjector) {
        daggerApplication.androidInjector = dispatchingAndroidInjector;
    }
}
