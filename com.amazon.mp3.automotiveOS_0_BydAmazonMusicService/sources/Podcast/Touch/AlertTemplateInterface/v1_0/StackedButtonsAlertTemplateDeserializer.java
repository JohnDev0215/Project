package Podcast.Touch.AlertTemplateInterface.v1_0;

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
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class StackedButtonsAlertTemplateDeserializer extends JsonDeserializer<StackedButtonsAlertTemplate> {
    public static final StackedButtonsAlertTemplateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        StackedButtonsAlertTemplateDeserializer stackedButtonsAlertTemplateDeserializer = new StackedButtonsAlertTemplateDeserializer();
        INSTANCE = stackedButtonsAlertTemplateDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(StackedButtonsAlertTemplate.class, stackedButtonsAlertTemplateDeserializer);
    }

    private StackedButtonsAlertTemplateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public StackedButtonsAlertTemplate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.AlertTemplateInterface.v1_0#StackedButtonsAlertTemplate".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public StackedButtonsAlertTemplate deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        StackedButtonsAlertTemplate stackedButtonsAlertTemplate = new StackedButtonsAlertTemplate();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.IMAGE.equals(currentName)) {
                stackedButtonsAlertTemplate.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("latencyMetricElement".equals(currentName)) {
                stackedButtonsAlertTemplate.setLatencyMetricElement(LatencyMetricElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                stackedButtonsAlertTemplate.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onCreated".equals(currentName)) {
                stackedButtonsAlertTemplate.setOnCreated(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                stackedButtonsAlertTemplate.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("pageType".equals(currentName)) {
                stackedButtonsAlertTemplate.setPageType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("negativeButton".equals(currentName)) {
                stackedButtonsAlertTemplate.setNegativeButton(NegativeButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onBound".equals(currentName)) {
                stackedButtonsAlertTemplate.setOnBound(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_SUBTITLE.equals(currentName)) {
                stackedButtonsAlertTemplate.setSubtitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onDismiss".equals(currentName)) {
                stackedButtonsAlertTemplate.setOnDismiss(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("cacheSeconds".equals(currentName)) {
                stackedButtonsAlertTemplate.setCacheSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("launchMode".equals(currentName)) {
                stackedButtonsAlertTemplate.setLaunchMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("positiveButton".equals(currentName)) {
                stackedButtonsAlertTemplate.setPositiveButton(PositiveButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onDismissed".equals(currentName)) {
                stackedButtonsAlertTemplate.setOnDismissed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return stackedButtonsAlertTemplate;
    }
}
