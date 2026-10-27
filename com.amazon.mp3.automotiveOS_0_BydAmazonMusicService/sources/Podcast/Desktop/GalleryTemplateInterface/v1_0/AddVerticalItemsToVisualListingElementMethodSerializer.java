package Podcast.Desktop.GalleryTemplateInterface.v1_0;

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
public class AddVerticalItemsToVisualListingElementMethodSerializer extends JsonSerializer<AddVerticalItemsToVisualListingElementMethod> {
    public static final AddVerticalItemsToVisualListingElementMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddVerticalItemsToVisualListingElementMethodSerializer addVerticalItemsToVisualListingElementMethodSerializer = new AddVerticalItemsToVisualListingElementMethodSerializer();
        INSTANCE = addVerticalItemsToVisualListingElementMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.AddVerticalItemsToVisualListingElementMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AddVerticalItemsToVisualListingElementMethod.class, addVerticalItemsToVisualListingElementMethodSerializer);
    }

    private AddVerticalItemsToVisualListingElementMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AddVerticalItemsToVisualListingElementMethod addVerticalItemsToVisualListingElementMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addVerticalItemsToVisualListingElementMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addVerticalItemsToVisualListingElementMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AddVerticalItemsToVisualListingElementMethod addVerticalItemsToVisualListingElementMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        VerticalItemsSerializer.INSTANCE.serialize(addVerticalItemsToVisualListingElementMethod.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(addVerticalItemsToVisualListingElementMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(addVerticalItemsToVisualListingElementMethod.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(addVerticalItemsToVisualListingElementMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfWidget");
        jsonSerializer.serialize(addVerticalItemsToVisualListingElementMethod.getOnEndOfWidget(), jsonGenerator, serializerProvider);
    }
}
