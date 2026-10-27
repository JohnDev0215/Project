package Podcast.BookmarkInterface.v1_0;

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
public class SetBookmarksThresholdsMethodSerializer extends JsonSerializer<SetBookmarksThresholdsMethod> {
    public static final SetBookmarksThresholdsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetBookmarksThresholdsMethodSerializer setBookmarksThresholdsMethodSerializer = new SetBookmarksThresholdsMethodSerializer();
        INSTANCE = setBookmarksThresholdsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.BookmarkInterface.v1_0.SetBookmarksThresholdsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetBookmarksThresholdsMethod.class, setBookmarksThresholdsMethodSerializer);
    }

    private SetBookmarksThresholdsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetBookmarksThresholdsMethod setBookmarksThresholdsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setBookmarksThresholdsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setBookmarksThresholdsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetBookmarksThresholdsMethod setBookmarksThresholdsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("inProgressUpperBoundPercentage");
        SimpleSerializers.serializeInteger(setBookmarksThresholdsMethod.getInProgressUpperBoundPercentage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setBookmarksThresholdsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("inProgressLowerBoundMilliseconds");
        SimpleSerializers.serializeLong(setBookmarksThresholdsMethod.getInProgressLowerBoundMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setBookmarksThresholdsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
