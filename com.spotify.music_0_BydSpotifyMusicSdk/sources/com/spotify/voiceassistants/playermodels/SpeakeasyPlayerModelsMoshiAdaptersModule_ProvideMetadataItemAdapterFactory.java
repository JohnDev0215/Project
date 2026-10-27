package com.spotify.voiceassistants.playermodels;

import p.ul0;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class SpeakeasyPlayerModelsMoshiAdaptersModule_ProvideMetadataItemAdapterFactory implements ul0 {
    private final SpeakeasyPlayerModelsMoshiAdaptersModule module;

    public SpeakeasyPlayerModelsMoshiAdaptersModule_ProvideMetadataItemAdapterFactory(SpeakeasyPlayerModelsMoshiAdaptersModule speakeasyPlayerModelsMoshiAdaptersModule) {
        this.module = speakeasyPlayerModelsMoshiAdaptersModule;
    }

    public static SpeakeasyPlayerModelsMoshiAdaptersModule_ProvideMetadataItemAdapterFactory create(SpeakeasyPlayerModelsMoshiAdaptersModule speakeasyPlayerModelsMoshiAdaptersModule) {
        return new SpeakeasyPlayerModelsMoshiAdaptersModule_ProvideMetadataItemAdapterFactory(speakeasyPlayerModelsMoshiAdaptersModule);
    }

    public static Object provideMetadataItemAdapter(SpeakeasyPlayerModelsMoshiAdaptersModule speakeasyPlayerModelsMoshiAdaptersModule) {
        Object objProvideMetadataItemAdapter = speakeasyPlayerModelsMoshiAdaptersModule.provideMetadataItemAdapter();
        vr2.b(objProvideMetadataItemAdapter);
        return objProvideMetadataItemAdapter;
    }

    @Override // p.xy1
    public Object get() {
        return provideMetadataItemAdapter(this.module);
    }
}
