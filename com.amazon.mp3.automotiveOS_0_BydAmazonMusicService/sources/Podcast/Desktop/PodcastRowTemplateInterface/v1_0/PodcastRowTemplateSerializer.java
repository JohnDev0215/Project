package Podcast.Desktop.PodcastRowTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElementSerializer;
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
public class PodcastRowTemplateSerializer extends JsonSerializer<PodcastRowTemplate> {
    public static final PodcastRowTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastRowTemplateSerializer podcastRowTemplateSerializer = new PodcastRowTemplateSerializer();
        INSTANCE = podcastRowTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PodcastRowTemplate.class, podcastRowTemplateSerializer);
    }

    private PodcastRowTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PodcastRowTemplate podcastRowTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (podcastRowTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(podcastRowTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PodcastRowTemplate podcastRowTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(podcastRowTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        PodcastRowItemElementsSerializer.INSTANCE.serialize(podcastRowTemplate.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("itemIndexingEnabled");
        SimpleSerializers.serializeBoolean(podcastRowTemplate.isItemIndexingEnabled(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(podcastRowTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(podcastRowTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metadata");
        TemplateMetadataElementSerializer.INSTANCE.serialize(podcastRowTemplate.getMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(podcastRowTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(podcastRowTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(podcastRowTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(podcastRowTemplate.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImage");
        SimpleSerializers.serializeString(podcastRowTemplate.getBackgroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("button");
        ButtonElementSerializer.INSTANCE.serialize(podcastRowTemplate.getButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(podcastRowTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        jsonSerializer.serialize(podcastRowTemplate.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(podcastRowTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
