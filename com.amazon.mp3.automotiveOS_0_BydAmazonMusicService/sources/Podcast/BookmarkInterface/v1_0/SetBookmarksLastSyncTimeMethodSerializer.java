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
public class SetBookmarksLastSyncTimeMethodSerializer extends JsonSerializer<SetBookmarksLastSyncTimeMethod> {
    public static final SetBookmarksLastSyncTimeMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetBookmarksLastSyncTimeMethodSerializer setBookmarksLastSyncTimeMethodSerializer = new SetBookmarksLastSyncTimeMethodSerializer();
        INSTANCE = setBookmarksLastSyncTimeMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.BookmarkInterface.v1_0.SetBookmarksLastSyncTimeMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetBookmarksLastSyncTimeMethod.class, setBookmarksLastSyncTimeMethodSerializer);
    }

    private SetBookmarksLastSyncTimeMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetBookmarksLastSyncTimeMethod setBookmarksLastSyncTimeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setBookmarksLastSyncTimeMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setBookmarksLastSyncTimeMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetBookmarksLastSyncTimeMethod setBookmarksLastSyncTimeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setBookmarksLastSyncTimeMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setBookmarksLastSyncTimeMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(setBookmarksLastSyncTimeMethod.getLastSyncTime(), jsonGenerator, serializerProvider);
    }
}
