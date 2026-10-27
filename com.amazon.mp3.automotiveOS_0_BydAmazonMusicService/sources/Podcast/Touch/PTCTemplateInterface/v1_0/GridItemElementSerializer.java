package Podcast.Touch.PTCTemplateInterface.v1_0;

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
public class GridItemElementSerializer extends JsonSerializer<GridItemElement> {
    public static final GridItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GridItemElementSerializer gridItemElementSerializer = new GridItemElementSerializer();
        INSTANCE = gridItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GridItemElement.class, gridItemElementSerializer);
    }

    private GridItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GridItemElement gridItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (gridItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(gridItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GridItemElement gridItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(gridItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemDeselected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(gridItemElement.getOnItemDeselected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastShowVariantId");
        SimpleSerializers.serializeString(gridItemElement.getPodcastShowVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        jsonSerializer.serialize(gridItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("about");
        AboutElementSerializer.INSTANCE.serialize(gridItemElement.getAbout(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onAboutSelected");
        jsonSerializer.serialize(gridItemElement.getOnAboutSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(gridItemElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("altText");
        SimpleSerializers.serializeString(gridItemElement.getAltText(), jsonGenerator, serializerProvider);
    }
}
