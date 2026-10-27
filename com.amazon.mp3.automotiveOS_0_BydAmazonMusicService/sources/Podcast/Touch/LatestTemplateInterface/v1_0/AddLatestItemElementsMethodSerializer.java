package Podcast.Touch.LatestTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import SOACoreInterface.v1_0.QueueSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class AddLatestItemElementsMethodSerializer extends JsonSerializer<AddLatestItemElementsMethod> {
    public static final AddLatestItemElementsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddLatestItemElementsMethodSerializer addLatestItemElementsMethodSerializer = new AddLatestItemElementsMethodSerializer();
        INSTANCE = addLatestItemElementsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.LatestTemplateInterface.v1_0.AddLatestItemElementsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AddLatestItemElementsMethod.class, addLatestItemElementsMethodSerializer);
    }

    private AddLatestItemElementsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AddLatestItemElementsMethod addLatestItemElementsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addLatestItemElementsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addLatestItemElementsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AddLatestItemElementsMethod addLatestItemElementsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(addLatestItemElementsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        LatestItemElementsSerializer.INSTANCE.serialize(addLatestItemElementsMethod.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(addLatestItemElementsMethod.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(addLatestItemElementsMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        jsonSerializer.serialize(addLatestItemElementsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
    }
}
