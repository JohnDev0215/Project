package com.amazon.music.casting.providers;

import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.CastingService;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequest;
import com.amazon.musicplayqueueservice.client.common.TransferQueueResponse;
import com.android.volley.VolleyError;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class TransferQueueProvider {
    private final CastingService service;

    public TransferQueueProvider(CastingService castingService) {
        Validate.notNull(castingService, "service can't be null", new Object[0]);
        this.service = castingService;
    }

    public TransferQueueResponse transferQueue(TransferQueueRequest transferQueueRequest) throws CastingException {
        Validate.notNull(transferQueueRequest, "request can't be null", new Object[0]);
        try {
            return this.service.transferQueue(transferQueueRequest);
        } catch (VolleyError e) {
            throw new CastingException(e);
        }
    }
}
