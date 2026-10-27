package com.amazon.music.casting.providers;

import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.CastingService;
import com.amazon.musicplayqueueservice.client.common.GetDeviceListResponse;
import com.android.volley.VolleyError;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class AmazonDeviceListProvider {
    private final CastingService service;

    public AmazonDeviceListProvider(CastingService castingService) {
        Validate.notNull(castingService, "service can't be null", new Object[0]);
        this.service = castingService;
    }

    public GetDeviceListResponse getDeviceList() throws CastingException {
        try {
            return this.service.getDeviceList();
        } catch (VolleyError e) {
            throw new CastingException(e);
        }
    }
}
