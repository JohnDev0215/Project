package Podcast.Touch.GalleryTemplateInterface.v2_0;

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
public class GalleryHeaderElementSerializer extends JsonSerializer<GalleryHeaderElement> {
    public static final GalleryHeaderElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GalleryHeaderElementSerializer galleryHeaderElementSerializer = new GalleryHeaderElementSerializer();
        INSTANCE = galleryHeaderElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryHeaderElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GalleryHeaderElement.class, galleryHeaderElementSerializer);
    }

    private GalleryHeaderElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GalleryHeaderElement galleryHeaderElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (galleryHeaderElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(galleryHeaderElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GalleryHeaderElement galleryHeaderElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("pageSubheader");
        SimpleSerializers.serializeString(galleryHeaderElement.getPageSubheader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageHeader");
        SimpleSerializers.serializeString(galleryHeaderElement.getPageHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("navBarHeader");
        SimpleSerializers.serializeString(galleryHeaderElement.getNavBarHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("showNavBarTextOnScroll");
        SimpleSerializers.serializeBoolean(galleryHeaderElement.isShowNavBarTextOnScroll(), jsonGenerator, serializerProvider);
    }
}
