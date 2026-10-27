package Podcast.Desktop.PodcastDetailTemplateInterface.v1_0;

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
public class AddEpisodeRowItemsMethodSerializer extends JsonSerializer<AddEpisodeRowItemsMethod> {
    public static final AddEpisodeRowItemsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddEpisodeRowItemsMethodSerializer addEpisodeRowItemsMethodSerializer = new AddEpisodeRowItemsMethodSerializer();
        INSTANCE = addEpisodeRowItemsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.AddEpisodeRowItemsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AddEpisodeRowItemsMethod.class, addEpisodeRowItemsMethodSerializer);
    }

    private AddEpisodeRowItemsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AddEpisodeRowItemsMethod addEpisodeRowItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addEpisodeRowItemsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addEpisodeRowItemsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AddEpisodeRowItemsMethod addEpisodeRowItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        EpisodeRowItemsSerializer.INSTANCE.serialize(addEpisodeRowItemsMethod.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(addEpisodeRowItemsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(addEpisodeRowItemsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(addEpisodeRowItemsMethod.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(addEpisodeRowItemsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
