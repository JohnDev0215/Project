package Podcast.Touch.DetailTemplateInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class FeaturedElementSerializer extends JsonSerializer<FeaturedElement> {
    public static final FeaturedElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedElementSerializer featuredElementSerializer = new FeaturedElementSerializer();
        INSTANCE = featuredElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FeaturedElement.class, featuredElementSerializer);
    }

    private FeaturedElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FeaturedElement featuredElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (featuredElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(featuredElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FeaturedElement featuredElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastBookmark");
        PodcastDetailsBookmarkElementSerializer.INSTANCE.serialize(featuredElement.getPodcastBookmark(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("alternate");
        FeaturedSingleItemElementSerializer.INSTANCE.serialize(featuredElement.getAlternate(), jsonGenerator, serializerProvider);
    }
}
