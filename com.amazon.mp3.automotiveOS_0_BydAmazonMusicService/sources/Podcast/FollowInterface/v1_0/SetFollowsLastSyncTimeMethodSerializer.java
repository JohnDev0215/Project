package Podcast.FollowInterface.v1_0;

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
public class SetFollowsLastSyncTimeMethodSerializer extends JsonSerializer<SetFollowsLastSyncTimeMethod> {
    public static final SetFollowsLastSyncTimeMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetFollowsLastSyncTimeMethodSerializer setFollowsLastSyncTimeMethodSerializer = new SetFollowsLastSyncTimeMethodSerializer();
        INSTANCE = setFollowsLastSyncTimeMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.FollowInterface.v1_0.SetFollowsLastSyncTimeMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetFollowsLastSyncTimeMethod.class, setFollowsLastSyncTimeMethodSerializer);
    }

    private SetFollowsLastSyncTimeMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetFollowsLastSyncTimeMethod setFollowsLastSyncTimeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setFollowsLastSyncTimeMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setFollowsLastSyncTimeMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetFollowsLastSyncTimeMethod setFollowsLastSyncTimeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(setFollowsLastSyncTimeMethod.getLastSyncTime(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setFollowsLastSyncTimeMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setFollowsLastSyncTimeMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
