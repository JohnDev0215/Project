package Podcast.Touch.HorizontalRowTemplateInterface.v1_0;

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
public class HorizontalRowTemplateSerializer extends JsonSerializer<HorizontalRowTemplate> {
    public static final HorizontalRowTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        HorizontalRowTemplateSerializer horizontalRowTemplateSerializer = new HorizontalRowTemplateSerializer();
        INSTANCE = horizontalRowTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalRowTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(HorizontalRowTemplate.class, horizontalRowTemplateSerializer);
    }

    private HorizontalRowTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull HorizontalRowTemplate horizontalRowTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (horizontalRowTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(horizontalRowTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(HorizontalRowTemplate horizontalRowTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(horizontalRowTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("button");
        ButtonElementSerializer.INSTANCE.serialize(horizontalRowTemplate.getButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("itemIndexingEnabled");
        SimpleSerializers.serializeBoolean(horizontalRowTemplate.isItemIndexingEnabled(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(horizontalRowTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        jsonSerializer.serialize(horizontalRowTemplate.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(horizontalRowTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(horizontalRowTemplate.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImage");
        SimpleSerializers.serializeString(horizontalRowTemplate.getBackgroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        HorizontalItemElementsSerializer.INSTANCE.serialize(horizontalRowTemplate.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(horizontalRowTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(horizontalRowTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(horizontalRowTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(horizontalRowTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(horizontalRowTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
