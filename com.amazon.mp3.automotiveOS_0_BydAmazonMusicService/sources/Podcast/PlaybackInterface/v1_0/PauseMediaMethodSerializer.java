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
public class PauseMediaMethodSerializer extends JsonSerializer<PauseMediaMethod> {
    public static final PauseMediaMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PauseMediaMethodSerializer pauseMediaMethodSerializer = new PauseMediaMethodSerializer();
        INSTANCE = pauseMediaMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.PauseMediaMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PauseMediaMethod.class, pauseMediaMethodSerializer);
    }

    private PauseMediaMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PauseMediaMethod pauseMediaMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pauseMediaMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pauseMediaMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PauseMediaMethod pauseMediaMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(pauseMediaMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(pauseMediaMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
