package com.amazon.musicrelationshipservice.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class ObjectIdentifierSerializer extends JsonSerializer<ObjectIdentifier> {
    public static final ObjectIdentifierSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ObjectIdentifierSerializer objectIdentifierSerializer = new ObjectIdentifierSerializer();
        INSTANCE = objectIdentifierSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicrelationshipservice.model.ObjectIdentifierSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ObjectIdentifier.class, objectIdentifierSerializer);
    }

    private ObjectIdentifierSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ObjectIdentifier objectIdentifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (objectIdentifier == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(objectIdentifier, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ObjectIdentifier objectIdentifier, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(objectIdentifier.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        SimpleSerializers.serializeString(objectIdentifier.getType(), jsonGenerator, serializerProvider);
    }
}
