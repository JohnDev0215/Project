package Podcast.Touch.GalleryTemplateInterface.v2_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementSerializer;
import Podcast.Touch.WidgetElementsInterface.v1_0.WidgetsSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
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
public class GalleryTemplateSerializer extends JsonSerializer<GalleryTemplate> {
    public static final GalleryTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GalleryTemplateSerializer galleryTemplateSerializer = new GalleryTemplateSerializer();
        INSTANCE = galleryTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GalleryTemplate.class, galleryTemplateSerializer);
    }

    private GalleryTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GalleryTemplate galleryTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (galleryTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(galleryTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GalleryTemplate galleryTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(galleryTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(galleryTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(galleryTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(galleryTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(galleryTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("about");
        AboutElementSerializer.INSTANCE.serialize(galleryTemplate.getAbout(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(galleryTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("headers");
        GalleryHeaderElementSerializer.INSTANCE.serialize(galleryTemplate.getHeaders(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("widgets");
        WidgetsSerializer.INSTANCE.serialize(galleryTemplate.getWidgets(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImage");
        SimpleSerializers.serializeString(galleryTemplate.getBackgroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(galleryTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        jsonSerializer.serialize(galleryTemplate.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(galleryTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
