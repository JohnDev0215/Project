package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CompactPillNavigatorSerializer extends JsonSerializer<CompactPillNavigator> {
    public static final CompactPillNavigatorSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CompactPillNavigatorSerializer compactPillNavigatorSerializer = new CompactPillNavigatorSerializer();
        INSTANCE = compactPillNavigatorSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.CompactPillNavigatorSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CompactPillNavigator.class, compactPillNavigatorSerializer);
    }

    private CompactPillNavigatorSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CompactPillNavigator compactPillNavigator, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (compactPillNavigator == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(compactPillNavigator, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CompactPillNavigator compactPillNavigator, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        CompactPillItemElementsSerializer.INSTANCE.serialize(compactPillNavigator.getItems(), jsonGenerator, serializerProvider);
    }
}
