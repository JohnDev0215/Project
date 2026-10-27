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
public class CompletedElementSerializer extends JsonSerializer<CompletedElement> {
    public static final CompletedElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CompletedElementSerializer completedElementSerializer = new CompletedElementSerializer();
        INSTANCE = completedElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.EpisodeOptionsInterface.v1_0.CompletedElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CompletedElement.class, completedElementSerializer);
    }

    private CompletedElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CompletedElement completedElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (completedElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(completedElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CompletedElement completedElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onMarkPlayed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(completedElement.getOnMarkPlayed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onMarkUnplayed");
        jsonSerializer.serialize(completedElement.getOnMarkUnplayed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(completedElement.getId(), jsonGenerator, serializerProvider);
    }
}
