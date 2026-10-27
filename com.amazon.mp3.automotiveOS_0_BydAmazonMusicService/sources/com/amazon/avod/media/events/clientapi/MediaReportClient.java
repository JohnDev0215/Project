package com.amazon.avod.media.events.clientapi;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaReportClient {
    @Nonnull
    BootstrapConfig bootstrap() throws ServerResponseException, InvalidRequestException;

    @Nonnull
    UpdateConfig submitReport(@Nonnull MediaReport mediaReport) throws ServerResponseException, InvalidRequestException;
}
