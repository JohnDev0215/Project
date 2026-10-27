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
public class EpisodePlayElementSerializer extends JsonSerializer<EpisodePlayElement> {
    public static final EpisodePlayElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodePlayElementSerializer episodePlayElementSerializer = new EpisodePlayElementSerializer();
        INSTANCE = episodePlayElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodePlayElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EpisodePlayElement.class, episodePlayElementSerializer);
    }

    private EpisodePlayElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EpisodePlayElement episodePlayElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (episodePlayElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(episodePlayElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EpisodePlayElement episodePlayElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("mediaId");
        SimpleSerializers.serializeString(episodePlayElement.getMediaId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPlaySelected");
        MethodsSerializer.INSTANCE.serialize(episodePlayElement.getOnPlaySelected(), jsonGenerator, serializerProvider);
    }
}
