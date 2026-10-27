package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
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
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class PodcastFeaturedPlayElementSerializer extends JsonSerializer<PodcastFeaturedPlayElement> {
    public static final PodcastFeaturedPlayElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastFeaturedPlayElementSerializer podcastFeaturedPlayElementSerializer = new PodcastFeaturedPlayElementSerializer();
        INSTANCE = podcastFeaturedPlayElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PodcastFeaturedPlayElement.class, podcastFeaturedPlayElementSerializer);
    }

    private PodcastFeaturedPlayElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PodcastFeaturedPlayElement podcastFeaturedPlayElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (podcastFeaturedPlayElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(podcastFeaturedPlayElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PodcastFeaturedPlayElement podcastFeaturedPlayElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(PageUriUtils.DO_PLAY_VALUE);
        PodcastPlayElementSerializer.INSTANCE.serialize(podcastFeaturedPlayElement.getPlay(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(podcastFeaturedPlayElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(podcastFeaturedPlayElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.TERTIARY_TEXT);
        SimpleSerializers.serializeString(podcastFeaturedPlayElement.getTertiaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImage");
        SimpleSerializers.serializeString(podcastFeaturedPlayElement.getBackgroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(podcastFeaturedPlayElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("foregroundImage");
        SimpleSerializers.serializeString(podcastFeaturedPlayElement.getForegroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(podcastFeaturedPlayElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(podcastFeaturedPlayElement.getLabel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(podcastFeaturedPlayElement.getOnViewed(), jsonGenerator, serializerProvider);
    }
}
