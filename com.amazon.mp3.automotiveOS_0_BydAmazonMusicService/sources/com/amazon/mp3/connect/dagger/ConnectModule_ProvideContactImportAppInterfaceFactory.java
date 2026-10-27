package com.amazon.mp3.connect.dagger;

import android.content.Context;
import com.amazon.music.connect.contacts.ContactImportProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ConnectModule_ProvideContactImportAppInterfaceFactory implements Factory<ContactImportProvider> {
    private final Provider<Context> contextProvider;
    private final ConnectModule module;

    public ConnectModule_ProvideContactImportAppInterfaceFactory(ConnectModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public ContactImportProvider get() {
        return provideContactImportAppInterface(this.module, this.contextProvider.get());
    }

    public static ConnectModule_ProvideContactImportAppInterfaceFactory create(ConnectModule module, Provider<Context> contextProvider) {
        return new ConnectModule_ProvideContactImportAppInterfaceFactory(module, contextProvider);
    }

    public static ContactImportProvider provideContactImportAppInterface(ConnectModule instance, Context context) {
        return (ContactImportProvider) Preconditions.checkNotNullFromProvides(instance.provideContactImportAppInterface(context));
    }
}
