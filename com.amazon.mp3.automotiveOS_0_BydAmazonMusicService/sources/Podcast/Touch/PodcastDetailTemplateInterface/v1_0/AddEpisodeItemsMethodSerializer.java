package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

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
public class AddEpisodeItemsMethodSerializer extends JsonSerializer<AddEpisodeItemsMethod> {
    public static final AddEpisodeItemsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddEpisodeItemsMethodSerializer addEpisodeItemsMethodSerializer = new AddEpisodeItemsMethodSerializer();
        INSTANCE = addEpisodeItemsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.AddEpisodeItemsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AddEpisodeItemsMethod.class, addEpisodeItemsMethodSerializer);
    }

    private AddEpisodeItemsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AddEpisodeItemsMethod addEpisodeItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addEpisodeItemsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addEpisodeItemsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AddEpisodeItemsMethod addEpisodeItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(addEpisodeItemsMethod.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        jsonSerializer.serialize(addEpisodeItemsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(addEpisodeItemsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(addEpisodeItemsMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        EpisodeItemsSerializer.INSTANCE.serialize(addEpisodeItemsMethod.getItems(), jsonGenerator, serializerProvider);
    }
}
