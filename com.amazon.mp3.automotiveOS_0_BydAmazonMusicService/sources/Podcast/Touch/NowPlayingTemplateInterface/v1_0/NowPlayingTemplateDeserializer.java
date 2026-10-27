package Podcast.Touch.NowPlayingTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementDeserializer;
import SOACoreInterface.v1_0.MethodsDeserializer;
import com.amazon.cloud9.jackson.SimpleDeserializers;
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
public class NowPlayingTemplateDeserializer extends JsonDeserializer<NowPlayingTemplate> {
    public static final NowPlayingTemplateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        NowPlayingTemplateDeserializer nowPlayingTemplateDeserializer = new NowPlayingTemplateDeserializer();
        INSTANCE = nowPlayingTemplateDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.NowPlayingTemplateInterface.v1_0.NowPlayingTemplateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(NowPlayingTemplate.class, nowPlayingTemplateDeserializer);
    }

    private NowPlayingTemplateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public NowPlayingTemplate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.NowPlayingTemplateInterface.v1_0#NowPlayingTemplate".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public NowPlayingTemplate deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        NowPlayingTemplate nowPlayingTemplate = new NowPlayingTemplate();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("latencyMetricElement".equals(currentName)) {
                nowPlayingTemplate.setLatencyMetricElement(LatencyMetricElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("cacheSeconds".equals(currentName)) {
                nowPlayingTemplate.setCacheSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("onCreated".equals(currentName)) {
                nowPlayingTemplate.setOnCreated(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                nowPlayingTemplate.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("launchMode".equals(currentName)) {
                nowPlayingTemplate.setLaunchMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("pageType".equals(currentName)) {
                nowPlayingTemplate.setPageType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onBound".equals(currentName)) {
                nowPlayingTemplate.setOnBound(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onDismissed".equals(currentName)) {
                nowPlayingTemplate.setOnDismissed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return nowPlayingTemplate;
    }
}
