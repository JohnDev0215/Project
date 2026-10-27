package Podcast.EpisodeOptionsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
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
public class FollowElementSerializer extends JsonSerializer<FollowElement> {
    public static final FollowElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FollowElementSerializer followElementSerializer = new FollowElementSerializer();
        INSTANCE = followElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.EpisodeOptionsInterface.v1_0.FollowElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FollowElement.class, followElementSerializer);
    }

    private FollowElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FollowElement followElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (followElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(followElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FollowElement followElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onUnFollow");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(followElement.getOnUnFollow(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("descriptions");
        FollowStatusMapSerializer.INSTANCE.serialize(followElement.getDescriptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onFollow");
        jsonSerializer.serialize(followElement.getOnFollow(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(followElement.getId(), jsonGenerator, serializerProvider);
    }
}
