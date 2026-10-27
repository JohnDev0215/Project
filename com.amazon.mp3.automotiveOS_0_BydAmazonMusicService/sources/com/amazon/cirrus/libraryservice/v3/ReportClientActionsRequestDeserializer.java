package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContextDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class ReportClientActionsRequestDeserializer extends JsonDeserializer<ReportClientActionsRequest> {
    public static final ReportClientActionsRequestDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ReportClientActionsRequestDeserializer reportClientActionsRequestDeserializer = new ReportClientActionsRequestDeserializer();
        INSTANCE = reportClientActionsRequestDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.ReportClientActionsRequestDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ReportClientActionsRequest.class, reportClientActionsRequestDeserializer);
    }

    private ReportClientActionsRequestDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ReportClientActionsRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("com.amazon.cirrus.libraryservice.v3#ReportClientActionsRequest".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ReportClientActionsRequest deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ReportClientActionsRequest reportClientActionsRequest = new ReportClientActionsRequest();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (Splash.PARAMS_DEVICE_TYPE.equals(currentName)) {
                reportClientActionsRequest.setDeviceType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("musicRequestIdentityContextToken".equals(currentName)) {
                reportClientActionsRequest.setMusicRequestIdentityContextToken(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("customerId".equals(currentName)) {
                reportClientActionsRequest.setCustomerId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("musicRequestIdentityContext".equals(currentName)) {
                reportClientActionsRequest.setMusicRequestIdentityContext(MusicRequestIdentityContextDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_DEVICE_ID.equals(currentName)) {
                reportClientActionsRequest.setDeviceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("clientActionList".equals(currentName)) {
                reportClientActionsRequest.setClientActionList(ClientActionListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return reportClientActionsRequest;
    }
}
