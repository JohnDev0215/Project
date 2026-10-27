package Podcast.Touch.GalleryTemplateInterface.v1_0;

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
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class AddWidgetsMethodSerializer extends JsonSerializer<AddWidgetsMethod> {
    public static final AddWidgetsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddWidgetsMethodSerializer addWidgetsMethodSerializer = new AddWidgetsMethodSerializer();
        INSTANCE = addWidgetsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v1_0.AddWidgetsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AddWidgetsMethod.class, addWidgetsMethodSerializer);
    }

    private AddWidgetsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AddWidgetsMethod addWidgetsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addWidgetsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addWidgetsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AddWidgetsMethod addWidgetsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("widgets");
        WidgetsSerializer.INSTANCE.serialize(addWidgetsMethod.getWidgets(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(addWidgetsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        MethodsSerializer.INSTANCE.serialize(addWidgetsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(addWidgetsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
