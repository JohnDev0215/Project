package Podcast.Touch.VerticalGridTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementDeserializer;
import SOACoreInterface.v1_0.MethodsDeserializer;
import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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

/* JADX INFO: loaded from: classes.dex */
public class VerticalGridTemplateDeserializer extends JsonDeserializer<VerticalGridTemplate> {
    public static final VerticalGridTemplateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        VerticalGridTemplateDeserializer verticalGridTemplateDeserializer = new VerticalGridTemplateDeserializer();
        INSTANCE = verticalGridTemplateDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalGridTemplateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(VerticalGridTemplate.class, verticalGridTemplateDeserializer);
    }

    private VerticalGridTemplateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public VerticalGridTemplate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.VerticalGridTemplateInterface.v1_0#VerticalGridTemplate".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public VerticalGridTemplate deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        VerticalGridTemplate verticalGridTemplate = new VerticalGridTemplate();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("header".equals(currentName)) {
                verticalGridTemplate.setHeader(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onEndOfList".equals(currentName)) {
                verticalGridTemplate.setOnEndOfList(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("latencyMetricElement".equals(currentName)) {
                verticalGridTemplate.setLatencyMetricElement(LatencyMetricElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("cacheSeconds".equals(currentName)) {
                verticalGridTemplate.setCacheSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("onCreated".equals(currentName)) {
                verticalGridTemplate.setOnCreated(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                verticalGridTemplate.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ITEMS.equals(currentName)) {
                verticalGridTemplate.setItems(VerticalItemElementsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("launchMode".equals(currentName)) {
                verticalGridTemplate.setLaunchMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("button".equals(currentName)) {
                verticalGridTemplate.setButton(ButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("pageType".equals(currentName)) {
                verticalGridTemplate.setPageType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onBound".equals(currentName)) {
                verticalGridTemplate.setOnBound(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onDismissed".equals(currentName)) {
                verticalGridTemplate.setOnDismissed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return verticalGridTemplate;
    }
}
