package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

import Podcast.Touch.EpisodeGroupItemsInterface.v1_0.EpisodeGroupItemElementsSerializer;
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
public class AddEpisodeGroupItemsMethodSerializer extends JsonSerializer<AddEpisodeGroupItemsMethod> {
    public static final AddEpisodeGroupItemsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddEpisodeGroupItemsMethodSerializer addEpisodeGroupItemsMethodSerializer = new AddEpisodeGroupItemsMethodSerializer();
        INSTANCE = addEpisodeGroupItemsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AddEpisodeGroupItemsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AddEpisodeGroupItemsMethod.class, addEpisodeGroupItemsMethodSerializer);
    }

    private AddEpisodeGroupItemsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AddEpisodeGroupItemsMethod addEpisodeGroupItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addEpisodeGroupItemsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addEpisodeGroupItemsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AddEpisodeGroupItemsMethod addEpisodeGroupItemsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onEndOfList");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(addEpisodeGroupItemsMethod.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(addEpisodeGroupItemsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(addEpisodeGroupItemsMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        EpisodeGroupItemElementsSerializer.INSTANCE.serialize(addEpisodeGroupItemsMethod.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(addEpisodeGroupItemsMethod.getOnViewed(), jsonGenerator, serializerProvider);
    }
}
