package com.amazon.avod.media.playback;

import com.amazon.avod.event.AdEventTransport;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusCommunicationService implements QOSCommunicationService {
    private AdEventTransport mAdEventTransport;

    static class SingletonHolder {
        private static final AloysiusCommunicationService INSTANCE = new AloysiusCommunicationService();

        SingletonHolder() {
        }
    }

    private AloysiusCommunicationService() {
        this.mAdEventTransport = new AdEventTransport();
    }

    @Nonnull
    public static AloysiusCommunicationService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override // com.amazon.avod.media.playback.QOSCommunicationService
    @Nonnull
    public AdEventTransport getEventTransport() {
        return this.mAdEventTransport;
    }
}
