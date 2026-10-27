package com.amazon.musicensembleservice.brush;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
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
public class PageResponseDeserializer extends JsonDeserializer<PageResponse> {
    public static final PageResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PageResponseDeserializer pageResponseDeserializer = new PageResponseDeserializer();
        INSTANCE = pageResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.PageResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PageResponse.class, pageResponseDeserializer);
    }

    private PageResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PageResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#PageResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PageResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PageResponse pageResponse = new PageResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("uri".equals(currentName)) {
                pageResponse.setUri(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MAPWebViewEventHelper.KEY_ERRORS.equals(currentName)) {
                pageResponse.setErrors(LogMessagesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("content".equals(currentName)) {
                pageResponse.setContent(ContentDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("header".equals(currentName)) {
                pageResponse.setHeader(HeaderDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                pageResponse.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("entity".equals(currentName)) {
                pageResponse.setEntity(EntityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("info".equals(currentName)) {
                pageResponse.setInfo(LogMessagesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("subTitle".equals(currentName)) {
                pageResponse.setSubTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("warnings".equals(currentName)) {
                pageResponse.setWarnings(LogMessagesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                pageResponse.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("filters".equals(currentName)) {
                pageResponse.setFilters(FiltersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("campaignIds".equals(currentName)) {
                pageResponse.setCampaignIds(CampaignIdMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return pageResponse;
    }
}
