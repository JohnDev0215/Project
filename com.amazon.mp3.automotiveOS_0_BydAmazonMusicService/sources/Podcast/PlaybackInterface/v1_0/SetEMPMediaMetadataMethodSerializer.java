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
public class SetEMPMediaMetadataMethodSerializer extends JsonSerializer<SetEMPMediaMetadataMethod> {
    public static final SetEMPMediaMetadataMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetEMPMediaMetadataMethodSerializer setEMPMediaMetadataMethodSerializer = new SetEMPMediaMetadataMethodSerializer();
        INSTANCE = setEMPMediaMetadataMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SetEMPMediaMetadataMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetEMPMediaMetadataMethod.class, setEMPMediaMetadataMethodSerializer);
    }

    private SetEMPMediaMetadataMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetEMPMediaMetadataMethod setEMPMediaMetadataMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setEMPMediaMetadataMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setEMPMediaMetadataMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetEMPMediaMetadataMethod setEMPMediaMetadataMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setEMPMediaMetadataMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metadata");
        EMPMediaMetadataElementSerializer.INSTANCE.serialize(setEMPMediaMetadataMethod.getMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setEMPMediaMetadataMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
