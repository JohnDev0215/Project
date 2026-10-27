package com.amazon.mp3;

import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class AmazonApplication_MembersInjector implements MembersInjector<AmazonApplication> {
    public static void injectMAndroidInjector(AmazonApplication instance, DispatchingAndroidInjector<Object> mAndroidInjector) {
        instance.mAndroidInjector = mAndroidInjector;
    }
}
