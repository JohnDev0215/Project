package com.dee.app.contacts.common.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
public final class JsonUtils {
    private static final ContactsLogger LOG;
    private static final ObjectMapper mObjectMapper;

    private JsonUtils() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        mObjectMapper = objectMapper;
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        LOG = ContactsLogger.getLogger("TAG", JsonUtils.class);
    }

    public static String toJson(Object obj) {
        try {
            return mObjectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException unused) {
            LOG.e("Failed to serialize class: " + obj.getClass().getSimpleName() + " to JSON String");
            return null;
        }
    }
}
