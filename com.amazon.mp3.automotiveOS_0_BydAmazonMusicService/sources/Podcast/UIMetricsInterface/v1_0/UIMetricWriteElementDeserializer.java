package Podcast.UIMetricsInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
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
public class UIMetricWriteElementDeserializer extends JsonDeserializer<UIMetricWriteElement> {
    public static final UIMetricWriteElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        UIMetricWriteElementDeserializer uIMetricWriteElementDeserializer = new UIMetricWriteElementDeserializer();
        INSTANCE = uIMetricWriteElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.UIMetricsInterface.v1_0.UIMetricWriteElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(UIMetricWriteElement.class, uIMetricWriteElementDeserializer);
    }

    private UIMetricWriteElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public UIMetricWriteElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.UIMetricsInterface.v1_0#UIMetricWriteElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public UIMetricWriteElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        UIMetricWriteElement uIMetricWriteElement = new UIMetricWriteElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("signalType".equals(currentName)) {
                uIMetricWriteElement.setSignalType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_SRC.equals(currentName)) {
                uIMetricWriteElement.setContentSrc(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("firstViewableIndex".equals(currentName)) {
                uIMetricWriteElement.setFirstViewableIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("numItemsAccessible".equals(currentName)) {
                uIMetricWriteElement.setNumItemsAccessible(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("containerInstId".equals(currentName)) {
                uIMetricWriteElement.setContainerInstId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("detailPageItemIdType".equals(currentName)) {
                uIMetricWriteElement.setDetailPageItemIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("entityPos".equals(currentName)) {
                uIMetricWriteElement.setEntityPos(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("actionType".equals(currentName)) {
                uIMetricWriteElement.setActionType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("detailPageItemId".equals(currentName)) {
                uIMetricWriteElement.setDetailPageItemId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("refMarker".equals(currentName)) {
                uIMetricWriteElement.setRefMarker(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (PageUriUtils.ENTITY_TYPE_QUERY_PARAM.equals(currentName)) {
                uIMetricWriteElement.setEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("lastViewableIndex".equals(currentName)) {
                uIMetricWriteElement.setLastViewableIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("containerIndex".equals(currentName)) {
                uIMetricWriteElement.setContainerIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID_TYPE.equals(currentName)) {
                uIMetricWriteElement.setEntityIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("containerType".equals(currentName)) {
                uIMetricWriteElement.setContainerType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID.equals(currentName)) {
                uIMetricWriteElement.setEntityId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("referer".equals(currentName)) {
                uIMetricWriteElement.setReferer(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("pageType".equals(currentName)) {
                uIMetricWriteElement.setPageType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentName".equals(currentName)) {
                uIMetricWriteElement.setContentName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("interactionType".equals(currentName)) {
                uIMetricWriteElement.setInteractionType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                uIMetricWriteElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("metricsPreset".equals(currentName)) {
                uIMetricWriteElement.setMetricsPreset(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return uIMetricWriteElement;
    }
}
