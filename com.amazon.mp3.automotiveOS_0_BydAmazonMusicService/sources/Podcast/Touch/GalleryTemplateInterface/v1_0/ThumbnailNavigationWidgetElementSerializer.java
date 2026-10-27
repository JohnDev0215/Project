package Podcast.Touch.GalleryTemplateInterface.v1_0;

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
public class ThumbnailNavigationWidgetElementSerializer extends JsonSerializer<ThumbnailNavigationWidgetElement> {
    public static final ThumbnailNavigationWidgetElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ThumbnailNavigationWidgetElementSerializer thumbnailNavigationWidgetElementSerializer = new ThumbnailNavigationWidgetElementSerializer();
        INSTANCE = thumbnailNavigationWidgetElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v1_0.ThumbnailNavigationWidgetElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ThumbnailNavigationWidgetElement.class, thumbnailNavigationWidgetElementSerializer);
    }

    private ThumbnailNavigationWidgetElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ThumbnailNavigationWidgetElement thumbnailNavigationWidgetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (thumbnailNavigationWidgetElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(thumbnailNavigationWidgetElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ThumbnailNavigationWidgetElement thumbnailNavigationWidgetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        ThumbnailNavigatorItemElementsSerializer.INSTANCE.serialize(thumbnailNavigationWidgetElement.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(thumbnailNavigationWidgetElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(thumbnailNavigationWidgetElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(thumbnailNavigationWidgetElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(thumbnailNavigationWidgetElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
    }
}
