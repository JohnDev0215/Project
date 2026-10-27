package Podcast.CategoryFollowInterface.v1_0;

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
public class CategoryFollowsLastSyncTimeClientStateSerializer extends JsonSerializer<CategoryFollowsLastSyncTimeClientState> {
    public static final CategoryFollowsLastSyncTimeClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CategoryFollowsLastSyncTimeClientStateSerializer categoryFollowsLastSyncTimeClientStateSerializer = new CategoryFollowsLastSyncTimeClientStateSerializer();
        INSTANCE = categoryFollowsLastSyncTimeClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.CategoryFollowInterface.v1_0.CategoryFollowsLastSyncTimeClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CategoryFollowsLastSyncTimeClientState.class, categoryFollowsLastSyncTimeClientStateSerializer);
    }

    private CategoryFollowsLastSyncTimeClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CategoryFollowsLastSyncTimeClientState categoryFollowsLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (categoryFollowsLastSyncTimeClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(categoryFollowsLastSyncTimeClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CategoryFollowsLastSyncTimeClientState categoryFollowsLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(categoryFollowsLastSyncTimeClientState.getLastSyncTime(), jsonGenerator, serializerProvider);
    }
}
