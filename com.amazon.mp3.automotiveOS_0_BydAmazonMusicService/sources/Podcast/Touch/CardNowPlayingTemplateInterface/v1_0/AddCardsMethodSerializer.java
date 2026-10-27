package Podcast.Touch.CardNowPlayingTemplateInterface.v1_0;

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
public class AddCardsMethodSerializer extends JsonSerializer<AddCardsMethod> {
    public static final AddCardsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddCardsMethodSerializer addCardsMethodSerializer = new AddCardsMethodSerializer();
        INSTANCE = addCardsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.AddCardsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AddCardsMethod.class, addCardsMethodSerializer);
    }

    private AddCardsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AddCardsMethod addCardsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addCardsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addCardsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AddCardsMethod addCardsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(addCardsMethod.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(addCardsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        jsonSerializer.serialize(addCardsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(addCardsMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cards");
        CardElementsSerializer.INSTANCE.serialize(addCardsMethod.getCards(), jsonGenerator, serializerProvider);
    }
}
