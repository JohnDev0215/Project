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
public class ResetMediaMethodSerializer extends JsonSerializer<ResetMediaMethod> {
    public static final ResetMediaMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ResetMediaMethodSerializer resetMediaMethodSerializer = new ResetMediaMethodSerializer();
        INSTANCE = resetMediaMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.ResetMediaMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ResetMediaMethod.class, resetMediaMethodSerializer);
    }

    private ResetMediaMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ResetMediaMethod resetMediaMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (resetMediaMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(resetMediaMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ResetMediaMethod resetMediaMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(resetMediaMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(resetMediaMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
