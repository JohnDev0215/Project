package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import SOACoreInterface.v1_0.QueueSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
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
public class PTCEndMethodSerializer extends JsonSerializer<PTCEndMethod> {
    public static final PTCEndMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PTCEndMethodSerializer pTCEndMethodSerializer = new PTCEndMethodSerializer();
        INSTANCE = pTCEndMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.PTCEndMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PTCEndMethod.class, pTCEndMethodSerializer);
    }

    private PTCEndMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PTCEndMethod pTCEndMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pTCEndMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pTCEndMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PTCEndMethod pTCEndMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onTrailerNotPlayed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(pTCEndMethod.getOnTrailerNotPlayed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(pTCEndMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onTrailerPlayed");
        jsonSerializer.serialize(pTCEndMethod.getOnTrailerPlayed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(pTCEndMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
