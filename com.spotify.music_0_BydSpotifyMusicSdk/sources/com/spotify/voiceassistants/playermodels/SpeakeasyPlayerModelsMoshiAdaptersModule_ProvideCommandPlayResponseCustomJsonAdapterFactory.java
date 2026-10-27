package com.spotify.voiceassistants.playermodels;

import p.ul0;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class SpeakeasyPlayerModelsMoshiAdaptersModule_ProvideCommandPlayResponseCustomJsonAdapterFactory implements ul0 {
    private final SpeakeasyPlayerModelsMoshiAdaptersModule module;

    public SpeakeasyPlayerModelsMoshiAdaptersModule_ProvideCommandPlayResponseCustomJsonAdapterFactory(SpeakeasyPlayerModelsMoshiAdaptersModule speakeasyPlayerModelsMoshiAdaptersModule) {
        this.module = speakeasyPlayerModelsMoshiAdaptersModule;
    }

    public static SpeakeasyPlayerModelsMoshiAdaptersModule_ProvideCommandPlayResponseCustomJsonAdapterFactory create(SpeakeasyPlayerModelsMoshiAdaptersModule speakeasyPlayerModelsMoshiAdaptersModule) {
        return new SpeakeasyPlayerModelsMoshiAdaptersModule_ProvideCommandPlayResponseCustomJsonAdapterFactory(speakeasyPlayerModelsMoshiAdaptersModule);
    }

    public static Object provideCommandPlayResponseCustomJsonAdapter(SpeakeasyPlayerModelsMoshiAdaptersModule speakeasyPlayerModelsMoshiAdaptersModule) {
        Object objProvideCommandPlayResponseCustomJsonAdapter = speakeasyPlayerModelsMoshiAdaptersModule.provideCommandPlayResponseCustomJsonAdapter();
        vr2.b(objProvideCommandPlayResponseCustomJsonAdapter);
        return objProvideCommandPlayResponseCustomJsonAdapter;
    }

    @Override // p.xy1
    public Object get() {
        return provideCommandPlayResponseCustomJsonAdapter(this.module);
    }
}
