package com.amazon.nimblymusicservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class NimblyHideFromMostRecentSerializer extends JsonSerializer<NimblyHideFromMostRecent> {
    public static final NimblyHideFromMostRecentSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        NimblyHideFromMostRecentSerializer nimblyHideFromMostRecentSerializer = new NimblyHideFromMostRecentSerializer();
        INSTANCE = nimblyHideFromMostRecentSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.NimblyHideFromMostRecentSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(NimblyHideFromMostRecent.class, nimblyHideFromMostRecentSerializer);
    }

    private NimblyHideFromMostRecentSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull NimblyHideFromMostRecent nimblyHideFromMostRecent, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (nimblyHideFromMostRecent == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(nimblyHideFromMostRecent, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(NimblyHideFromMostRecent nimblyHideFromMostRecent, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(TetheredMessageKey.timestamp);
        SimpleSerializers.serializeDate(nimblyHideFromMostRecent.getTimestamp(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(PageUriUtils.ENTITY_TYPE_QUERY_PARAM);
        SimpleSerializers.serializeString(nimblyHideFromMostRecent.getEntityType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ENTITY_ID);
        SimpleSerializers.serializeString(nimblyHideFromMostRecent.getEntityId(), jsonGenerator, serializerProvider);
    }
}
