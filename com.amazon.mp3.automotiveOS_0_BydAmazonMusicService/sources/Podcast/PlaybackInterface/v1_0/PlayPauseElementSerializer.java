package Podcast.PlaybackInterface.v1_0;

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
public class PlayPauseElementSerializer extends JsonSerializer<PlayPauseElement> {
    public static final PlayPauseElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlayPauseElementSerializer playPauseElementSerializer = new PlayPauseElementSerializer();
        INSTANCE = playPauseElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.PlayPauseElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PlayPauseElement.class, playPauseElementSerializer);
    }

    private PlayPauseElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PlayPauseElement playPauseElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (playPauseElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(playPauseElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PlayPauseElement playPauseElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(playPauseElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
