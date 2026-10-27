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
public class SetTranscriptsMethodSerializer extends JsonSerializer<SetTranscriptsMethod> {
    public static final SetTranscriptsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetTranscriptsMethodSerializer setTranscriptsMethodSerializer = new SetTranscriptsMethodSerializer();
        INSTANCE = setTranscriptsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SetTranscriptsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetTranscriptsMethod.class, setTranscriptsMethodSerializer);
    }

    private SetTranscriptsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetTranscriptsMethod setTranscriptsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setTranscriptsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setTranscriptsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetTranscriptsMethod setTranscriptsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("transcriptsFileUrl");
        SimpleSerializers.serializeString(setTranscriptsMethod.getTranscriptsFileUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setTranscriptsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setTranscriptsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
