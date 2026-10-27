package Podcast.FollowInterface.v1_0;

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
public class FollowsLastSyncTimeClientStateSerializer extends JsonSerializer<FollowsLastSyncTimeClientState> {
    public static final FollowsLastSyncTimeClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FollowsLastSyncTimeClientStateSerializer followsLastSyncTimeClientStateSerializer = new FollowsLastSyncTimeClientStateSerializer();
        INSTANCE = followsLastSyncTimeClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.FollowInterface.v1_0.FollowsLastSyncTimeClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FollowsLastSyncTimeClientState.class, followsLastSyncTimeClientStateSerializer);
    }

    private FollowsLastSyncTimeClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FollowsLastSyncTimeClientState followsLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (followsLastSyncTimeClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(followsLastSyncTimeClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FollowsLastSyncTimeClientState followsLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(followsLastSyncTimeClientState.getLastSyncTime(), jsonGenerator, serializerProvider);
    }
}
