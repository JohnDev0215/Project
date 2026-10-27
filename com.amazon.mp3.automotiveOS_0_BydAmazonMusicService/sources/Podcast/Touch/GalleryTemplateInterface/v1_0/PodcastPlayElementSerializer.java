package Podcast.Touch.GalleryTemplateInterface.v1_0;

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
public class PodcastPlayElementSerializer extends JsonSerializer<PodcastPlayElement> {
    public static final PodcastPlayElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastPlayElementSerializer podcastPlayElementSerializer = new PodcastPlayElementSerializer();
        INSTANCE = podcastPlayElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastPlayElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PodcastPlayElement.class, podcastPlayElementSerializer);
    }

    private PodcastPlayElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PodcastPlayElement podcastPlayElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (podcastPlayElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(podcastPlayElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PodcastPlayElement podcastPlayElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("collectionId");
        SimpleSerializers.serializeString(podcastPlayElement.getCollectionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPlaySelected");
        MethodsSerializer.INSTANCE.serialize(podcastPlayElement.getOnPlaySelected(), jsonGenerator, serializerProvider);
    }
}
