package com.amazon.music.subscription;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class RequestConfigSerializer extends JsonSerializer<RequestConfig> {
    public static final RequestConfigSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RequestConfigSerializer requestConfigSerializer = new RequestConfigSerializer();
        INSTANCE = requestConfigSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.RequestConfigSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RequestConfig.class, requestConfigSerializer);
    }

    private RequestConfigSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RequestConfig requestConfig, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (requestConfig == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(requestConfig, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RequestConfig requestConfig, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("payBounty");
        SimpleSerializers.serializeBoolean(requestConfig.isPayBounty(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("bountyContext");
        SimpleSerializers.serializeString(requestConfig.getBountyContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contextType");
        SimpleSerializers.serializeString(requestConfig.getContextType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isFamilySitewide2020HolidayPromo");
        SimpleSerializers.serializeBoolean(requestConfig.isIsFamilySitewide2020HolidayPromo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("keepFreeTrialEligAfterCancellation");
        SimpleSerializers.serializeBoolean(requestConfig.isKeepFreeTrialEligAfterCancellation(), jsonGenerator, serializerProvider);
    }
}
