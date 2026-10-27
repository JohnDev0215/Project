package Podcast.Desktop.PodcastDetailTemplateInterface.v1_0;

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
public class PodcastDetailTemplateSerializer extends JsonSerializer<PodcastDetailTemplate> {
    public static final PodcastDetailTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastDetailTemplateSerializer podcastDetailTemplateSerializer = new PodcastDetailTemplateSerializer();
        INSTANCE = podcastDetailTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.PodcastDetailTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PodcastDetailTemplate.class, podcastDetailTemplateSerializer);
    }

    private PodcastDetailTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PodcastDetailTemplate podcastDetailTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (podcastDetailTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(podcastDetailTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PodcastDetailTemplate podcastDetailTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(podcastDetailTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        EpisodeRowItemsSerializer.INSTANCE.serialize(podcastDetailTemplate.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(podcastDetailTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        jsonSerializer.serialize(podcastDetailTemplate.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(podcastDetailTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metadata");
        TemplateMetadataElementSerializer.INSTANCE.serialize(podcastDetailTemplate.getMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(podcastDetailTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(podcastDetailTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImage");
        SimpleSerializers.serializeString(podcastDetailTemplate.getBackgroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        HeaderElementSerializer.INSTANCE.serialize(podcastDetailTemplate.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("follow");
        FollowElementSerializer.INSTANCE.serialize(podcastDetailTemplate.getFollow(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(podcastDetailTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("featuredPinnedElement");
        FeaturedPinnedElementSerializer.INSTANCE.serialize(podcastDetailTemplate.getFeaturedPinnedElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("refinementBarElement");
        RefinementBarElementSerializer.INSTANCE.serialize(podcastDetailTemplate.getRefinementBarElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("share");
        ShareElementSerializer.INSTANCE.serialize(podcastDetailTemplate.getShare(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(podcastDetailTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("about");
        AboutElementSerializer.INSTANCE.serialize(podcastDetailTemplate.getAbout(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(podcastDetailTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
