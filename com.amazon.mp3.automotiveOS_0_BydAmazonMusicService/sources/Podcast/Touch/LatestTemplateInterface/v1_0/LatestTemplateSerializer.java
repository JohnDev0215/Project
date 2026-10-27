package Podcast.Touch.LatestTemplateInterface.v1_0;

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
public class LatestTemplateSerializer extends JsonSerializer<LatestTemplate> {
    public static final LatestTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LatestTemplateSerializer latestTemplateSerializer = new LatestTemplateSerializer();
        INSTANCE = latestTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.LatestTemplateInterface.v1_0.LatestTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(LatestTemplate.class, latestTemplateSerializer);
    }

    private LatestTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull LatestTemplate latestTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (latestTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(latestTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(LatestTemplate latestTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(latestTemplate.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(latestTemplate.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(latestTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(latestTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        jsonSerializer.serialize(latestTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("featuredPinnedElement");
        FeaturedPinnedElementSerializer.INSTANCE.serialize(latestTemplate.getFeaturedPinnedElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(latestTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        LatestItemElementsSerializer.INSTANCE.serialize(latestTemplate.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(latestTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(latestTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(latestTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(latestTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
