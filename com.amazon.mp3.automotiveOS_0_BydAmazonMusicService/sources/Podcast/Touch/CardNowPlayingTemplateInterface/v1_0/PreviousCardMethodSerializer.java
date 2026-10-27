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
public class PreviousCardMethodSerializer extends JsonSerializer<PreviousCardMethod> {
    public static final PreviousCardMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PreviousCardMethodSerializer previousCardMethodSerializer = new PreviousCardMethodSerializer();
        INSTANCE = previousCardMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.PreviousCardMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PreviousCardMethod.class, previousCardMethodSerializer);
    }

    private PreviousCardMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PreviousCardMethod previousCardMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (previousCardMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(previousCardMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PreviousCardMethod previousCardMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(previousCardMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(previousCardMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
