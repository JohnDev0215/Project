package com.amazon.music.casting.providers;

import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.CastingService;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponse;
import com.android.volley.VolleyError;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ConnectionInfoProvider {
    private final CastingService service;

    public ConnectionInfoProvider(CastingService castingService) {
        Validate.notNull(castingService, "service can't be null", new Object[0]);
        this.service = castingService;
    }

    public GetConnectionInfoResponse getConnectionInfo() throws CastingException {
        try {
            return this.service.getConnectionInfo();
        } catch (VolleyError e) {
            throw new CastingException(e);
        }
    }
}
