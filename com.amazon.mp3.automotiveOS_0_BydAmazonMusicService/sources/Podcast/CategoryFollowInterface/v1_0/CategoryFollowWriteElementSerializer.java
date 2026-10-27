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
public class CategoryFollowWriteElementSerializer extends JsonSerializer<CategoryFollowWriteElement> {
    public static final CategoryFollowWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CategoryFollowWriteElementSerializer categoryFollowWriteElementSerializer = new CategoryFollowWriteElementSerializer();
        INSTANCE = categoryFollowWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.CategoryFollowInterface.v1_0.CategoryFollowWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CategoryFollowWriteElement.class, categoryFollowWriteElementSerializer);
    }

    private CategoryFollowWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CategoryFollowWriteElement categoryFollowWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (categoryFollowWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(categoryFollowWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CategoryFollowWriteElement categoryFollowWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("followed");
        SimpleSerializers.serializePrimitiveBoolean(categoryFollowWriteElement.isFollowed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(categoryFollowWriteElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(categoryFollowWriteElement.getId(), jsonGenerator, serializerProvider);
    }
}
