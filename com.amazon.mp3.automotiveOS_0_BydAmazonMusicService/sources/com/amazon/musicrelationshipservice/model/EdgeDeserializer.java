package com.amazon.musicrelationshipservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class EdgeDeserializer extends JsonDeserializer<Edge> {
    public static final EdgeDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EdgeDeserializer edgeDeserializer = new EdgeDeserializer();
        INSTANCE = edgeDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicrelationshipservice.model.EdgeDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Edge.class, edgeDeserializer);
    }

    private EdgeDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Edge deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicrelationshipservice.model#Edge".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Edge deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Edge edge = new Edge();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("territory".equals(currentName)) {
                edge.setTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (PageUriUtils.SOURCE_QUERY_PARAM.equals(currentName)) {
                edge.setSource(ObjectIdentifierDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("lastUpdatedTime".equals(currentName)) {
                edge.setLastUpdatedTime(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("target".equals(currentName)) {
                edge.setTarget(ObjectIdentifierDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE.equals(currentName)) {
                edge.setType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("exception".equals(currentName)) {
                edge.setException(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return edge;
    }
}
