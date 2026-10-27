package Podcast.PlaybackInterface.v1_0;

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
public class NavigateToPodcastMethodSerializer extends JsonSerializer<NavigateToPodcastMethod> {
    public static final NavigateToPodcastMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        NavigateToPodcastMethodSerializer navigateToPodcastMethodSerializer = new NavigateToPodcastMethodSerializer();
        INSTANCE = navigateToPodcastMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.NavigateToPodcastMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(NavigateToPodcastMethod.class, navigateToPodcastMethodSerializer);
    }

    private NavigateToPodcastMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull NavigateToPodcastMethod navigateToPodcastMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (navigateToPodcastMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(navigateToPodcastMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(NavigateToPodcastMethod navigateToPodcastMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(navigateToPodcastMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(navigateToPodcastMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
