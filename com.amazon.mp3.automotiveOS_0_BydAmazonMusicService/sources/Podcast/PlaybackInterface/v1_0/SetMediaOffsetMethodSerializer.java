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
public class SetMediaOffsetMethodSerializer extends JsonSerializer<SetMediaOffsetMethod> {
    public static final SetMediaOffsetMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetMediaOffsetMethodSerializer setMediaOffsetMethodSerializer = new SetMediaOffsetMethodSerializer();
        INSTANCE = setMediaOffsetMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SetMediaOffsetMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetMediaOffsetMethod.class, setMediaOffsetMethodSerializer);
    }

    private SetMediaOffsetMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetMediaOffsetMethod setMediaOffsetMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setMediaOffsetMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setMediaOffsetMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetMediaOffsetMethod setMediaOffsetMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setMediaOffsetMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaId");
        SimpleSerializers.serializeString(setMediaOffsetMethod.getMediaId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setMediaOffsetMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rewindOffsetMilliseconds");
        SimpleSerializers.serializeLong(setMediaOffsetMethod.getRewindOffsetMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ignorable");
        SimpleSerializers.serializeBoolean(setMediaOffsetMethod.isIgnorable(), jsonGenerator, serializerProvider);
    }
}
