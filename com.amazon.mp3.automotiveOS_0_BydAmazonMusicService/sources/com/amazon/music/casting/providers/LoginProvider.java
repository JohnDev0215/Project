package com.amazon.music.casting.providers;

import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.CastingService;
import com.amazon.musicplayqueueservice.client.common.LoginResponse;
import com.android.volley.VolleyError;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class LoginProvider {
    private final CastingService service;

    public LoginProvider(CastingService castingService) {
        Validate.notNull(castingService, "service can't be null", new Object[0]);
        this.service = castingService;
    }

    public LoginResponse login() throws CastingException {
        try {
            return this.service.login();
        } catch (VolleyError e) {
            throw new CastingException(e);
        }
    }
}
