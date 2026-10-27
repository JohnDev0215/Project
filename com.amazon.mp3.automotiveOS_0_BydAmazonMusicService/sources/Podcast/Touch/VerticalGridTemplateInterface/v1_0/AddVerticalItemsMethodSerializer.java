package Podcast.Touch.VerticalGridTemplateInterface.v1_0;

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
public class AddVerticalItemsMethodSerializer extends JsonSerializer<AddVerticalItemsMethod> {
    public static final AddVerticalItemsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddVerticalItemsMethodSerializer addVerticalItemsMethodSerializer = new AddVerticalItemsMethodSerializer();
        INSTANCE = addVerticalItemsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.VerticalGridTemplateInterface.v1_0.AddVerticalItemsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AddVerticalItemsMethod.class, addVerticalItemsMethodSerializer);
    }

    private AddVerticalItemsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AddVerticalItemsMethod addVerticalItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addVerticalItemsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addVerticalItemsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AddVerticalItemsMethod addVerticalItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onEndOfList");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(addVerticalItemsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(addVerticalItemsMethod.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        VerticalItemElementsSerializer.INSTANCE.serialize(addVerticalItemsMethod.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(addVerticalItemsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(addVerticalItemsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
