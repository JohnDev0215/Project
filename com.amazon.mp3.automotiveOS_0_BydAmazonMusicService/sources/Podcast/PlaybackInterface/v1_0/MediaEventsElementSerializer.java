package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class MediaEventsElementSerializer extends JsonSerializer<MediaEventsElement> {
    public static final MediaEventsElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MediaEventsElementSerializer mediaEventsElementSerializer = new MediaEventsElementSerializer();
        INSTANCE = mediaEventsElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.MediaEventsElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MediaEventsElement.class, mediaEventsElementSerializer);
    }

    private MediaEventsElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MediaEventsElement mediaEventsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (mediaEventsElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(mediaEventsElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MediaEventsElement mediaEventsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onFinished");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(mediaEventsElement.getOnFinished(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSleepTimerSet");
        jsonSerializer.serialize(mediaEventsElement.getOnSleepTimerSet(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onFastForward");
        jsonSerializer.serialize(mediaEventsElement.getOnFastForward(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onStopped");
        jsonSerializer.serialize(mediaEventsElement.getOnStopped(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onError");
        jsonSerializer.serialize(mediaEventsElement.getOnError(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCompletedThresholdElapsed");
        jsonSerializer.serialize(mediaEventsElement.getOnCompletedThresholdElapsed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPlaybackSpeedModified");
        jsonSerializer.serialize(mediaEventsElement.getOnPlaybackSpeedModified(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSleepTimerElapsed");
        jsonSerializer.serialize(mediaEventsElement.getOnSleepTimerElapsed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onWriteFrequencyReached");
        jsonSerializer.serialize(mediaEventsElement.getOnWriteFrequencyReached(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPostScrubbed");
        jsonSerializer.serialize(mediaEventsElement.getOnPostScrubbed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPaused");
        jsonSerializer.serialize(mediaEventsElement.getOnPaused(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onWriteAndReportFrequencyReached");
        jsonSerializer.serialize(mediaEventsElement.getOnWriteAndReportFrequencyReached(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onRewind");
        jsonSerializer.serialize(mediaEventsElement.getOnRewind(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onResumed");
        jsonSerializer.serialize(mediaEventsElement.getOnResumed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onRebuffered");
        jsonSerializer.serialize(mediaEventsElement.getOnRebuffered(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPreScrubbed");
        jsonSerializer.serialize(mediaEventsElement.getOnPreScrubbed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onScrubbed");
        jsonSerializer.serialize(mediaEventsElement.getOnScrubbed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onStarted");
        jsonSerializer.serialize(mediaEventsElement.getOnStarted(), jsonGenerator, serializerProvider);
    }
}
