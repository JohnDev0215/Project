package Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0;

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
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class ThumbnailNavigatorItemElementSerializer extends JsonSerializer<ThumbnailNavigatorItemElement> {
    public static final ThumbnailNavigatorItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ThumbnailNavigatorItemElementSerializer thumbnailNavigatorItemElementSerializer = new ThumbnailNavigatorItemElementSerializer();
        INSTANCE = thumbnailNavigatorItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ThumbnailNavigatorItemElement.class, thumbnailNavigatorItemElementSerializer);
    }

    private ThumbnailNavigatorItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ThumbnailNavigatorItemElement thumbnailNavigatorItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (thumbnailNavigatorItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(thumbnailNavigatorItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ThumbnailNavigatorItemElement thumbnailNavigatorItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(thumbnailNavigatorItemElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(thumbnailNavigatorItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(thumbnailNavigatorItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
