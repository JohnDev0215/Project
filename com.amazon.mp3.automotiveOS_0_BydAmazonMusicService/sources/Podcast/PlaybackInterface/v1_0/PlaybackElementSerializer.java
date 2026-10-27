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
public class PlaybackElementSerializer extends JsonSerializer<PlaybackElement> {
    public static final PlaybackElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaybackElementSerializer playbackElementSerializer = new PlaybackElementSerializer();
        INSTANCE = playbackElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.PlaybackElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PlaybackElement.class, playbackElementSerializer);
    }

    private PlaybackElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PlaybackElement playbackElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (playbackElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (playbackElement instanceof MediaEventsElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#MediaEventsElement");
            MediaEventsElementSerializer.INSTANCE.serializeFields((MediaEventsElement) playbackElement, jsonGenerator, serializerProvider);
        } else if (playbackElement instanceof EMPMediaMetadataElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#EMPMediaMetadataElement");
            EMPMediaMetadataElementSerializer.INSTANCE.serializeFields((EMPMediaMetadataElement) playbackElement, jsonGenerator, serializerProvider);
        } else if (playbackElement instanceof MediaControlsElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#MediaControlsElement");
            MediaControlsElementSerializer.INSTANCE.serializeFields((MediaControlsElement) playbackElement, jsonGenerator, serializerProvider);
        } else if (playbackElement instanceof SleepTimerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#SleepTimerElement");
            SleepTimerElementSerializer.INSTANCE.serializeFields((SleepTimerElement) playbackElement, jsonGenerator, serializerProvider);
        } else if (playbackElement instanceof MediaMetadataElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#MediaMetadataElement");
            MediaMetadataElementSerializer.INSTANCE.serializeFields((MediaMetadataElement) playbackElement, jsonGenerator, serializerProvider);
        } else if (playbackElement instanceof PlaybackSpeedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#PlaybackSpeedElement");
            PlaybackSpeedElementSerializer.INSTANCE.serializeFields((PlaybackSpeedElement) playbackElement, jsonGenerator, serializerProvider);
        } else if (playbackElement instanceof MediaActionsElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#MediaActionsElement");
            MediaActionsElementSerializer.INSTANCE.serializeFields((MediaActionsElement) playbackElement, jsonGenerator, serializerProvider);
        } else if (playbackElement instanceof RewindElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#RewindElement");
            RewindElementSerializer.INSTANCE.serializeFields((RewindElement) playbackElement, jsonGenerator, serializerProvider);
        } else if (playbackElement instanceof PlayPauseElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#PlayPauseElement");
            PlayPauseElementSerializer.INSTANCE.serializeFields((PlayPauseElement) playbackElement, jsonGenerator, serializerProvider);
        } else {
            boolean z = playbackElement instanceof FastForwardElement;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(playbackElement, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#FastForwardElement");
                FastForwardElementSerializer.INSTANCE.serializeFields((FastForwardElement) playbackElement, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PlaybackElement playbackElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
