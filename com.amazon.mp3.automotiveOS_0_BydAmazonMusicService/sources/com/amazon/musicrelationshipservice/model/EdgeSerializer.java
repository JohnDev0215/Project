package com.amazon.musicrelationshipservice.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class EdgeSerializer extends JsonSerializer<Edge> {
    public static final EdgeSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EdgeSerializer edgeSerializer = new EdgeSerializer();
        INSTANCE = edgeSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicrelationshipservice.model.EdgeSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Edge.class, edgeSerializer);
    }

    private EdgeSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Edge edge, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (edge == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(edge, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Edge edge, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("territory");
        SimpleSerializers.serializeString(edge.getTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(PageUriUtils.SOURCE_QUERY_PARAM);
        ObjectIdentifierSerializer objectIdentifierSerializer = ObjectIdentifierSerializer.INSTANCE;
        objectIdentifierSerializer.serialize(edge.getSource(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lastUpdatedTime");
        SimpleSerializers.serializeString(edge.getLastUpdatedTime(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("target");
        objectIdentifierSerializer.serialize(edge.getTarget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        SimpleSerializers.serializeString(edge.getType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("exception");
        SimpleSerializers.serializeString(edge.getException(), jsonGenerator, serializerProvider);
    }
}
