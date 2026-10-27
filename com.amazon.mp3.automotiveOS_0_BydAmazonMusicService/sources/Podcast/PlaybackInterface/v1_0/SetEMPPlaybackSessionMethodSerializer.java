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
public class SetEMPPlaybackSessionMethodSerializer extends JsonSerializer<SetEMPPlaybackSessionMethod> {
    public static final SetEMPPlaybackSessionMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetEMPPlaybackSessionMethodSerializer setEMPPlaybackSessionMethodSerializer = new SetEMPPlaybackSessionMethodSerializer();
        INSTANCE = setEMPPlaybackSessionMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SetEMPPlaybackSessionMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetEMPPlaybackSessionMethod.class, setEMPPlaybackSessionMethodSerializer);
    }

    private SetEMPPlaybackSessionMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetEMPPlaybackSessionMethod setEMPPlaybackSessionMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setEMPPlaybackSessionMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setEMPPlaybackSessionMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetEMPPlaybackSessionMethod setEMPPlaybackSessionMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("playbackSessionId");
        SimpleSerializers.serializeString(setEMPPlaybackSessionMethod.getPlaybackSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isAlexaAppInitiatedPlayback");
        SimpleSerializers.serializeBoolean(setEMPPlaybackSessionMethod.isIsAlexaAppInitiatedPlayback(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setEMPPlaybackSessionMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setEMPPlaybackSessionMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
