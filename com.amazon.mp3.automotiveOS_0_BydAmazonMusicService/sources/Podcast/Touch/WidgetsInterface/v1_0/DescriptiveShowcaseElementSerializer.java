package Podcast.Touch.WidgetsInterface.v1_0;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElementsSerializer;
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
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class DescriptiveShowcaseElementSerializer extends JsonSerializer<DescriptiveShowcaseElement> {
    public static final DescriptiveShowcaseElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DescriptiveShowcaseElementSerializer descriptiveShowcaseElementSerializer = new DescriptiveShowcaseElementSerializer();
        INSTANCE = descriptiveShowcaseElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DescriptiveShowcaseElement.class, descriptiveShowcaseElementSerializer);
    }

    private DescriptiveShowcaseElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DescriptiveShowcaseElement descriptiveShowcaseElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (descriptiveShowcaseElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(descriptiveShowcaseElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DescriptiveShowcaseElement descriptiveShowcaseElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(MediaTrack.ROLE_SUBTITLE);
        SimpleSerializers.serializeString(descriptiveShowcaseElement.getSubtitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        HorizontalItemElementsSerializer.INSTANCE.serialize(descriptiveShowcaseElement.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(descriptiveShowcaseElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(descriptiveShowcaseElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(descriptiveShowcaseElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(descriptiveShowcaseElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
    }
}
