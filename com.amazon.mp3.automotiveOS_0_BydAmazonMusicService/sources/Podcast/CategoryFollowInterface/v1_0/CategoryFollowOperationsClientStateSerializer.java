package Podcast.CategoryFollowInterface.v1_0;

import SOAAppSyncInterface.v1_0.OperationsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CategoryFollowOperationsClientStateSerializer extends JsonSerializer<CategoryFollowOperationsClientState> {
    public static final CategoryFollowOperationsClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CategoryFollowOperationsClientStateSerializer categoryFollowOperationsClientStateSerializer = new CategoryFollowOperationsClientStateSerializer();
        INSTANCE = categoryFollowOperationsClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.CategoryFollowInterface.v1_0.CategoryFollowOperationsClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CategoryFollowOperationsClientState.class, categoryFollowOperationsClientStateSerializer);
    }

    private CategoryFollowOperationsClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CategoryFollowOperationsClientState categoryFollowOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (categoryFollowOperationsClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(categoryFollowOperationsClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CategoryFollowOperationsClientState categoryFollowOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("operations");
        OperationsSerializer.INSTANCE.serialize(categoryFollowOperationsClientState.getOperations(), jsonGenerator, serializerProvider);
    }
}
