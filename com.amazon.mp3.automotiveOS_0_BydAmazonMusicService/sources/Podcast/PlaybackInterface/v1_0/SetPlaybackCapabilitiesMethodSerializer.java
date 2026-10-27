package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.QueueSerializer;
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
public class SetPlaybackCapabilitiesMethodSerializer extends JsonSerializer<SetPlaybackCapabilitiesMethod> {
    public static final SetPlaybackCapabilitiesMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetPlaybackCapabilitiesMethodSerializer setPlaybackCapabilitiesMethodSerializer = new SetPlaybackCapabilitiesMethodSerializer();
        INSTANCE = setPlaybackCapabilitiesMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SetPlaybackCapabilitiesMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetPlaybackCapabilitiesMethod.class, setPlaybackCapabilitiesMethodSerializer);
    }

    private SetPlaybackCapabilitiesMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetPlaybackCapabilitiesMethod setPlaybackCapabilitiesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setPlaybackCapabilitiesMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setPlaybackCapabilitiesMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetPlaybackCapabilitiesMethod setPlaybackCapabilitiesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("miniplayer");
        SimpleSerializers.serializeBoolean(setPlaybackCapabilitiesMethod.isMiniplayer(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rewind");
        SimpleSerializers.serializeBoolean(setPlaybackCapabilitiesMethod.isRewind(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("scrubber");
        SimpleSerializers.serializeBoolean(setPlaybackCapabilitiesMethod.isScrubber(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackSpeed");
        SimpleSerializers.serializeBoolean(setPlaybackCapabilitiesMethod.isPlaybackSpeed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lockScreen");
        SimpleSerializers.serializeBoolean(setPlaybackCapabilitiesMethod.isLockScreen(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setPlaybackCapabilitiesMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("sleepTimer");
        SimpleSerializers.serializeBoolean(setPlaybackCapabilitiesMethod.isSleepTimer(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("fastForward");
        SimpleSerializers.serializeBoolean(setPlaybackCapabilitiesMethod.isFastForward(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("transcript");
        SimpleSerializers.serializeBoolean(setPlaybackCapabilitiesMethod.isTranscript(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setPlaybackCapabilitiesMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
