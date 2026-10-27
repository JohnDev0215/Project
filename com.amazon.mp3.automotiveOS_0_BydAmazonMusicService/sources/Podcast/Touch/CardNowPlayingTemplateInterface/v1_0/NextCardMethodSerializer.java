package Podcast.Touch.CardNowPlayingTemplateInterface.v1_0;

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
public class NextCardMethodSerializer extends JsonSerializer<NextCardMethod> {
    public static final NextCardMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        NextCardMethodSerializer nextCardMethodSerializer = new NextCardMethodSerializer();
        INSTANCE = nextCardMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.NextCardMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(NextCardMethod.class, nextCardMethodSerializer);
    }

    private NextCardMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull NextCardMethod nextCardMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (nextCardMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(nextCardMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(NextCardMethod nextCardMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(nextCardMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(nextCardMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
