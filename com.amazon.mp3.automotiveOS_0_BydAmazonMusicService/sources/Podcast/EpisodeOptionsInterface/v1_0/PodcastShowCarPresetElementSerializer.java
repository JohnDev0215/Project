package Podcast.EpisodeOptionsInterface.v1_0;

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
public class PodcastShowCarPresetElementSerializer extends JsonSerializer<PodcastShowCarPresetElement> {
    public static final PodcastShowCarPresetElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastShowCarPresetElementSerializer podcastShowCarPresetElementSerializer = new PodcastShowCarPresetElementSerializer();
        INSTANCE = podcastShowCarPresetElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.EpisodeOptionsInterface.v1_0.PodcastShowCarPresetElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PodcastShowCarPresetElement.class, podcastShowCarPresetElementSerializer);
    }

    private PodcastShowCarPresetElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PodcastShowCarPresetElement podcastShowCarPresetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (podcastShowCarPresetElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(podcastShowCarPresetElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PodcastShowCarPresetElement podcastShowCarPresetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(podcastShowCarPresetElement.getId(), jsonGenerator, serializerProvider);
    }
}
