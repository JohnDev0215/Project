package Podcast.UIMetricsInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class UIMetricWriteElementSerializer extends JsonSerializer<UIMetricWriteElement> {
    public static final UIMetricWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        UIMetricWriteElementSerializer uIMetricWriteElementSerializer = new UIMetricWriteElementSerializer();
        INSTANCE = uIMetricWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.UIMetricsInterface.v1_0.UIMetricWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(UIMetricWriteElement.class, uIMetricWriteElementSerializer);
    }

    private UIMetricWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull UIMetricWriteElement uIMetricWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (uIMetricWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(uIMetricWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(UIMetricWriteElement uIMetricWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("signalType");
        SimpleSerializers.serializeString(uIMetricWriteElement.getSignalType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.CONTENT_SRC);
        SimpleSerializers.serializeString(uIMetricWriteElement.getContentSrc(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("firstViewableIndex");
        SimpleSerializers.serializeInteger(uIMetricWriteElement.getFirstViewableIndex(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("numItemsAccessible");
        SimpleSerializers.serializeInteger(uIMetricWriteElement.getNumItemsAccessible(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("containerInstId");
        SimpleSerializers.serializeString(uIMetricWriteElement.getContainerInstId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("detailPageItemIdType");
        SimpleSerializers.serializeString(uIMetricWriteElement.getDetailPageItemIdType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("entityPos");
        SimpleSerializers.serializeInteger(uIMetricWriteElement.getEntityPos(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("actionType");
        SimpleSerializers.serializeString(uIMetricWriteElement.getActionType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("detailPageItemId");
        SimpleSerializers.serializeString(uIMetricWriteElement.getDetailPageItemId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("refMarker");
        SimpleSerializers.serializeString(uIMetricWriteElement.getRefMarker(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(PageUriUtils.ENTITY_TYPE_QUERY_PARAM);
        SimpleSerializers.serializeString(uIMetricWriteElement.getEntityType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lastViewableIndex");
        SimpleSerializers.serializeInteger(uIMetricWriteElement.getLastViewableIndex(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("containerIndex");
        SimpleSerializers.serializeInteger(uIMetricWriteElement.getContainerIndex(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ENTITY_ID_TYPE);
        SimpleSerializers.serializeString(uIMetricWriteElement.getEntityIdType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("containerType");
        SimpleSerializers.serializeString(uIMetricWriteElement.getContainerType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ENTITY_ID);
        SimpleSerializers.serializeString(uIMetricWriteElement.getEntityId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("referer");
        SimpleSerializers.serializeString(uIMetricWriteElement.getReferer(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(uIMetricWriteElement.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contentName");
        SimpleSerializers.serializeString(uIMetricWriteElement.getContentName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("interactionType");
        SimpleSerializers.serializeString(uIMetricWriteElement.getInteractionType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(uIMetricWriteElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metricsPreset");
        SimpleSerializers.serializeString(uIMetricWriteElement.getMetricsPreset(), jsonGenerator, serializerProvider);
    }
}
