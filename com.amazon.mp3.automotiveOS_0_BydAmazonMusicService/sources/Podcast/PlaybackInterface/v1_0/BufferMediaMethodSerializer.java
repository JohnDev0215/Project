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
public class BufferMediaMethodSerializer extends JsonSerializer<BufferMediaMethod> {
    public static final BufferMediaMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BufferMediaMethodSerializer bufferMediaMethodSerializer = new BufferMediaMethodSerializer();
        INSTANCE = bufferMediaMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.BufferMediaMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BufferMediaMethod.class, bufferMediaMethodSerializer);
    }

    private BufferMediaMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BufferMediaMethod bufferMediaMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bufferMediaMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(bufferMediaMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BufferMediaMethod bufferMediaMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(bufferMediaMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("audioUri");
        SimpleSerializers.serializeString(bufferMediaMethod.getAudioUri(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ignorable");
        SimpleSerializers.serializeBoolean(bufferMediaMethod.isIgnorable(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("audioStreamFormat");
        SimpleSerializers.serializeString(bufferMediaMethod.getAudioStreamFormat(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(bufferMediaMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaId");
        SimpleSerializers.serializeString(bufferMediaMethod.getMediaId(), jsonGenerator, serializerProvider);
    }
}
