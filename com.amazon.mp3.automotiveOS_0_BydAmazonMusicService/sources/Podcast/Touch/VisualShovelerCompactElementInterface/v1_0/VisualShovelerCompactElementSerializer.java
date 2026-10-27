package Podcast.Touch.VisualShovelerCompactElementInterface.v1_0;

import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemsSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
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
public class VisualShovelerCompactElementSerializer extends JsonSerializer<VisualShovelerCompactElement> {
    public static final VisualShovelerCompactElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        VisualShovelerCompactElementSerializer visualShovelerCompactElementSerializer = new VisualShovelerCompactElementSerializer();
        INSTANCE = visualShovelerCompactElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(VisualShovelerCompactElement.class, visualShovelerCompactElementSerializer);
    }

    private VisualShovelerCompactElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull VisualShovelerCompactElement visualShovelerCompactElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (visualShovelerCompactElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(visualShovelerCompactElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(VisualShovelerCompactElement visualShovelerCompactElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(PageUriUtils.DO_SEE_MORE_VALUE);
        VisualShovelerSeeMoreItemElementSerializer.INSTANCE.serialize(visualShovelerCompactElement.getSeeMore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(visualShovelerCompactElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(visualShovelerCompactElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(visualShovelerCompactElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        VerticalItemsSerializer.INSTANCE.serialize(visualShovelerCompactElement.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(visualShovelerCompactElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
    }
}
