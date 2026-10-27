package Podcast.PlaybackInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class MediaControlsElementSerializer extends JsonSerializer<MediaControlsElement> {
    public static final MediaControlsElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MediaControlsElementSerializer mediaControlsElementSerializer = new MediaControlsElementSerializer();
        INSTANCE = mediaControlsElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.MediaControlsElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MediaControlsElement.class, mediaControlsElementSerializer);
    }

    private MediaControlsElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MediaControlsElement mediaControlsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (mediaControlsElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(mediaControlsElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MediaControlsElement mediaControlsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("rewind");
        RewindElementSerializer.INSTANCE.serialize(mediaControlsElement.getRewind(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("sleepTimer");
        SleepTimerElementSerializer.INSTANCE.serialize(mediaControlsElement.getSleepTimer(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playPause");
        PlayPauseElementSerializer.INSTANCE.serialize(mediaControlsElement.getPlayPause(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("fastForward");
        FastForwardElementSerializer.INSTANCE.serialize(mediaControlsElement.getFastForward(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackSpeed");
        PlaybackSpeedElementSerializer.INSTANCE.serialize(mediaControlsElement.getPlaybackSpeed(), jsonGenerator, serializerProvider);
    }
}
