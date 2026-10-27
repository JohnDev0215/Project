package Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0;

import Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElementsSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class ThumbnailNavigatorWidgetElementSerializer extends JsonSerializer<ThumbnailNavigatorWidgetElement> {
    public static final ThumbnailNavigatorWidgetElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ThumbnailNavigatorWidgetElementSerializer thumbnailNavigatorWidgetElementSerializer = new ThumbnailNavigatorWidgetElementSerializer();
        INSTANCE = thumbnailNavigatorWidgetElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ThumbnailNavigatorWidgetElement.class, thumbnailNavigatorWidgetElementSerializer);
    }

    private ThumbnailNavigatorWidgetElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ThumbnailNavigatorWidgetElement thumbnailNavigatorWidgetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (thumbnailNavigatorWidgetElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(thumbnailNavigatorWidgetElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ThumbnailNavigatorWidgetElement thumbnailNavigatorWidgetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(thumbnailNavigatorWidgetElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(thumbnailNavigatorWidgetElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(thumbnailNavigatorWidgetElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(thumbnailNavigatorWidgetElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        ThumbnailNavigatorItemElementsSerializer.INSTANCE.serialize(thumbnailNavigatorWidgetElement.getItems(), jsonGenerator, serializerProvider);
    }
}
