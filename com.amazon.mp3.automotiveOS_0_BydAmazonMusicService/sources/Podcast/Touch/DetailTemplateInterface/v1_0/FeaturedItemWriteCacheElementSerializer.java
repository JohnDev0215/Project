package Podcast.Touch.DetailTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementSerializer;
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
public class FeaturedItemWriteCacheElementSerializer extends JsonSerializer<FeaturedItemWriteCacheElement> {
    public static final FeaturedItemWriteCacheElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedItemWriteCacheElementSerializer featuredItemWriteCacheElementSerializer = new FeaturedItemWriteCacheElementSerializer();
        INSTANCE = featuredItemWriteCacheElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedItemWriteCacheElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FeaturedItemWriteCacheElement.class, featuredItemWriteCacheElementSerializer);
    }

    private FeaturedItemWriteCacheElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FeaturedItemWriteCacheElement featuredItemWriteCacheElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (featuredItemWriteCacheElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(featuredItemWriteCacheElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FeaturedItemWriteCacheElement featuredItemWriteCacheElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("bookmark");
        BookmarkElementSerializer.INSTANCE.serialize(featuredItemWriteCacheElement.getBookmark(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(featuredItemWriteCacheElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isDisabled");
        SimpleSerializers.serializeBoolean(featuredItemWriteCacheElement.isIsDisabled(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.TERTIARY_TEXT);
        SimpleSerializers.serializeString(featuredItemWriteCacheElement.getTertiaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPlaySelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(featuredItemWriteCacheElement.getOnPlaySelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onInvalidated");
        jsonSerializer.serialize(featuredItemWriteCacheElement.getOnInvalidated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(featuredItemWriteCacheElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeOptions");
        EpisodeOptionsElementSerializer.INSTANCE.serialize(featuredItemWriteCacheElement.getEpisodeOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("download");
        DownloadElementSerializer.INSTANCE.serialize(featuredItemWriteCacheElement.getDownload(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(featuredItemWriteCacheElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(featuredItemWriteCacheElement.getLabel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(featuredItemWriteCacheElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastImage");
        SimpleSerializers.serializeString(featuredItemWriteCacheElement.getPodcastImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        jsonSerializer.serialize(featuredItemWriteCacheElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onImageSelected");
        jsonSerializer.serialize(featuredItemWriteCacheElement.getOnImageSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeId");
        SimpleSerializers.serializeString(featuredItemWriteCacheElement.getEpisodeId(), jsonGenerator, serializerProvider);
    }
}
