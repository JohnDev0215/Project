package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
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
public class MediaActionsElementSerializer extends JsonSerializer<MediaActionsElement> {
    public static final MediaActionsElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MediaActionsElementSerializer mediaActionsElementSerializer = new MediaActionsElementSerializer();
        INSTANCE = mediaActionsElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.MediaActionsElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MediaActionsElement.class, mediaActionsElementSerializer);
    }

    private MediaActionsElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MediaActionsElement mediaActionsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (mediaActionsElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(mediaActionsElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MediaActionsElement mediaActionsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onPrevious");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(mediaActionsElement.getOnPrevious(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onNext");
        jsonSerializer.serialize(mediaActionsElement.getOnNext(), jsonGenerator, serializerProvider);
    }
}
