package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementSerializer;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class FeaturedItemWriteCacheElementSerializer extends JsonSerializer<FeaturedItemWriteCacheElement> {
    public static final FeaturedItemWriteCacheElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedItemWriteCacheElementSerializer featuredItemWriteCacheElementSerializer = new FeaturedItemWriteCacheElementSerializer();
        INSTANCE = featuredItemWriteCacheElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FeaturedItemWriteCacheElementSerializer", new Version(1, 0, 0, null, null, null));
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
        jsonGenerator.writeFieldName("podcastImage");
        SimpleSerializers.serializeString(featuredItemWriteCacheElement.getPodcastImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("item");
        EpisodeRowItemElementSerializer.INSTANCE.serialize(featuredItemWriteCacheElement.getItem(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onInvalidated");
        MethodsSerializer.INSTANCE.serialize(featuredItemWriteCacheElement.getOnInvalidated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(featuredItemWriteCacheElement.getPodcastId(), jsonGenerator, serializerProvider);
    }
}
