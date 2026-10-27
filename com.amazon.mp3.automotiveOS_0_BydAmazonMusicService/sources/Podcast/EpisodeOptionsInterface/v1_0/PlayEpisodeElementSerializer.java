package Podcast.EpisodeOptionsInterface.v1_0;

import SOACoreInterface.v1_0.MethodsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class PlayEpisodeElementSerializer extends JsonSerializer<PlayEpisodeElement> {
    public static final PlayEpisodeElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlayEpisodeElementSerializer playEpisodeElementSerializer = new PlayEpisodeElementSerializer();
        INSTANCE = playEpisodeElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.EpisodeOptionsInterface.v1_0.PlayEpisodeElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PlayEpisodeElement.class, playEpisodeElementSerializer);
    }

    private PlayEpisodeElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PlayEpisodeElement playEpisodeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (playEpisodeElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(playEpisodeElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PlayEpisodeElement playEpisodeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(playEpisodeElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
