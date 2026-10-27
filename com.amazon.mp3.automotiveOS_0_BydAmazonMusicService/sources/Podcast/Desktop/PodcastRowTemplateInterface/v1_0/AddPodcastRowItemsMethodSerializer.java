package Podcast.Desktop.PodcastRowTemplateInterface.v1_0;

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
public class AddPodcastRowItemsMethodSerializer extends JsonSerializer<AddPodcastRowItemsMethod> {
    public static final AddPodcastRowItemsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddPodcastRowItemsMethodSerializer addPodcastRowItemsMethodSerializer = new AddPodcastRowItemsMethodSerializer();
        INSTANCE = addPodcastRowItemsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.PodcastRowTemplateInterface.v1_0.AddPodcastRowItemsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AddPodcastRowItemsMethod.class, addPodcastRowItemsMethodSerializer);
    }

    private AddPodcastRowItemsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AddPodcastRowItemsMethod addPodcastRowItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addPodcastRowItemsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addPodcastRowItemsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AddPodcastRowItemsMethod addPodcastRowItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onEndOfList");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(addPodcastRowItemsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(addPodcastRowItemsMethod.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(addPodcastRowItemsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        PodcastRowItemElementsSerializer.INSTANCE.serialize(addPodcastRowItemsMethod.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(addPodcastRowItemsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
