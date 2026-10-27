package com.amazon.music.casting.providers;

import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.CastingService;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.CreateQueueResponse;
import com.android.volley.VolleyError;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class CreateQueueProvider {
    private final CastingService service;

    public CreateQueueProvider(CastingService castingService) {
        Validate.notNull(castingService, "service can't be null", new Object[0]);
        this.service = castingService;
    }

    public CreateQueueResponse createQueue(CreateQueueRequest createQueueRequest) throws CastingException {
        Validate.notNull(createQueueRequest, "request can't be null", new Object[0]);
        try {
            return this.service.createQueue(createQueueRequest);
        } catch (VolleyError e) {
            throw new CastingException(e);
        }
    }
}
