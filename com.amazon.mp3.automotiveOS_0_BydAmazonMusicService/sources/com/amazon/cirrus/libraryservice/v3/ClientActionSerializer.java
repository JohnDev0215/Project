package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class ClientActionSerializer extends JsonSerializer<ClientAction> {
    public static final ClientActionSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ClientActionSerializer clientActionSerializer = new ClientActionSerializer();
        INSTANCE = clientActionSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.ClientActionSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ClientAction.class, clientActionSerializer);
    }

    private ClientActionSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ClientAction clientAction, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (clientAction == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(clientAction, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ClientAction clientAction, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("additionalDetails");
        JsonSerializer<Map<String, String>> jsonSerializer = ClientActionDetailsSerializer.INSTANCE;
        jsonSerializer.serialize(clientAction.getAdditionalDetails(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("actionName");
        SimpleSerializers.serializeString(clientAction.getActionName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(TetheredMessageKey.timestamp);
        SimpleSerializers.serializeDate(clientAction.getTimestamp(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metricsContext");
        jsonSerializer.serialize(clientAction.getMetricsContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.CONTENT_INFO);
        ContentInfoListSerializer.INSTANCE.serialize(clientAction.getContentInfo(), jsonGenerator, serializerProvider);
    }
}
