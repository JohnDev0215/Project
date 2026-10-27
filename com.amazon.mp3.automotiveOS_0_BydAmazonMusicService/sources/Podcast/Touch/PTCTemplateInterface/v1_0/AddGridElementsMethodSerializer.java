package Podcast.Touch.PTCTemplateInterface.v1_0;

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
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class AddGridElementsMethodSerializer extends JsonSerializer<AddGridElementsMethod> {
    public static final AddGridElementsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddGridElementsMethodSerializer addGridElementsMethodSerializer = new AddGridElementsMethodSerializer();
        INSTANCE = addGridElementsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.AddGridElementsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AddGridElementsMethod.class, addGridElementsMethodSerializer);
    }

    private AddGridElementsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AddGridElementsMethod addGridElementsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addGridElementsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addGridElementsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AddGridElementsMethod addGridElementsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onEndOfList");
        MethodsSerializer.INSTANCE.serialize(addGridElementsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(addGridElementsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        GridElementsSerializer.INSTANCE.serialize(addGridElementsMethod.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(addGridElementsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
