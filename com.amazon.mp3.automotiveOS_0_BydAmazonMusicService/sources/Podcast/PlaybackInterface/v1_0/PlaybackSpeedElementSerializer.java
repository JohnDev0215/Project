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
public class PlaybackSpeedElementSerializer extends JsonSerializer<PlaybackSpeedElement> {
    public static final PlaybackSpeedElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaybackSpeedElementSerializer playbackSpeedElementSerializer = new PlaybackSpeedElementSerializer();
        INSTANCE = playbackSpeedElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.PlaybackSpeedElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PlaybackSpeedElement.class, playbackSpeedElementSerializer);
    }

    private PlaybackSpeedElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PlaybackSpeedElement playbackSpeedElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (playbackSpeedElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(playbackSpeedElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PlaybackSpeedElement playbackSpeedElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(playbackSpeedElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
