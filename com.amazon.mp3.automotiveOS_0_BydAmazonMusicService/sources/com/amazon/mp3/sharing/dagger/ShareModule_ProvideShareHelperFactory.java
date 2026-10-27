package com.amazon.mp3.sharing.dagger;

import android.content.Context;
import com.amazon.music.share.ShareHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class ShareModule_ProvideShareHelperFactory implements Factory<ShareHelper> {
    private final Provider<Context> contextProvider;
    private final ShareModule module;

    public ShareModule_ProvideShareHelperFactory(ShareModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public ShareHelper get() {
        return provideShareHelper(this.module, this.contextProvider.get());
    }

    public static ShareModule_ProvideShareHelperFactory create(ShareModule module, Provider<Context> contextProvider) {
        return new ShareModule_ProvideShareHelperFactory(module, contextProvider);
    }

    public static ShareHelper provideShareHelper(ShareModule instance, Context context) {
        return (ShareHelper) Preconditions.checkNotNullFromProvides(instance.provideShareHelper(context));
    }
}
