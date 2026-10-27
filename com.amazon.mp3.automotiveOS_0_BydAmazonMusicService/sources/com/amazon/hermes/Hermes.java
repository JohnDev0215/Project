package com.amazon.hermes;

import android.content.Context;
import com.android.volley.CacheStateManager;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes2.dex */
public class Hermes {
    private static Hermes instance;
    private final CacheStateManager cacheManager;
    private final ObjectMapper mapper;
    private final RequestQueue requestQueue;

    public static final synchronized Hermes ignite(RequestQueue requestQueue, ObjectMapper objectMapper) {
        Hermes hermes;
        if (instance == null) {
            Validate.notNull(requestQueue, "RequestQueue can't be null", new Object[0]);
            Validate.notNull(objectMapper, "ObjectMapper can't be null", new Object[0]);
            hermes = new Hermes(requestQueue, objectMapper);
            instance = hermes;
        } else {
            throw new IllegalStateException("Hermes has already been ignited");
        }
        return hermes;
    }

    public static final synchronized Hermes ignite(Context context, ObjectMapper objectMapper) {
        Hermes hermes;
        if (instance == null) {
            Validate.notNull(context, "Context can't be null", new Object[0]);
            Validate.notNull(objectMapper, "ObjectMapper can't be null", new Object[0]);
            hermes = new Hermes(Volley.newRequestQueue(context, new OkHttpStack()), objectMapper);
            instance = hermes;
        } else {
            throw new IllegalStateException("Hermes has already been ignited");
        }
        return hermes;
    }

    private Hermes(RequestQueue requestQueue, ObjectMapper objectMapper) {
        this.requestQueue = requestQueue;
        this.cacheManager = new CacheStateManager(requestQueue.getCache());
        this.mapper = objectMapper;
    }

    public static Hermes get() {
        Hermes hermes = instance;
        if (hermes != null) {
            return hermes;
        }
        throw new IllegalStateException("Hermes needs to be ignited using #ignite");
    }

    public RequestQueue getRequestQueue() {
        return this.requestQueue;
    }

    public ObjectMapper getObjectMapper() {
        return this.mapper;
    }

    public CacheStateManager getCacheManager() {
        return this.cacheManager;
    }
}
