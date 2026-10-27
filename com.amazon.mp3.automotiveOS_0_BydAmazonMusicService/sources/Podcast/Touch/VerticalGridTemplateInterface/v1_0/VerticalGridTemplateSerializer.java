package Podcast.Touch.VerticalGridTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class VerticalGridTemplateSerializer extends JsonSerializer<VerticalGridTemplate> {
    public static final VerticalGridTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        VerticalGridTemplateSerializer verticalGridTemplateSerializer = new VerticalGridTemplateSerializer();
        INSTANCE = verticalGridTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalGridTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(VerticalGridTemplate.class, verticalGridTemplateSerializer);
    }

    private VerticalGridTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull VerticalGridTemplate verticalGridTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (verticalGridTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(verticalGridTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(VerticalGridTemplate verticalGridTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(verticalGridTemplate.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(verticalGridTemplate.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(verticalGridTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(verticalGridTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        jsonSerializer.serialize(verticalGridTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(verticalGridTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        VerticalItemElementsSerializer.INSTANCE.serialize(verticalGridTemplate.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(verticalGridTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("button");
        ButtonElementSerializer.INSTANCE.serialize(verticalGridTemplate.getButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(verticalGridTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(verticalGridTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(verticalGridTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
