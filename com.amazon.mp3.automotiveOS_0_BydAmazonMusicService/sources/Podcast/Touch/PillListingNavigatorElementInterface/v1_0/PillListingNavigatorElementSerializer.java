package Podcast.Touch.PillListingNavigatorElementInterface.v1_0;

import Podcast.Touch.PillItemElementInterface.v1_0.PillItemElementsSerializer;
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
public class PillListingNavigatorElementSerializer extends JsonSerializer<PillListingNavigatorElement> {
    public static final PillListingNavigatorElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PillListingNavigatorElementSerializer pillListingNavigatorElementSerializer = new PillListingNavigatorElementSerializer();
        INSTANCE = pillListingNavigatorElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PillListingNavigatorElement.class, pillListingNavigatorElementSerializer);
    }

    private PillListingNavigatorElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PillListingNavigatorElement pillListingNavigatorElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pillListingNavigatorElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pillListingNavigatorElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PillListingNavigatorElement pillListingNavigatorElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        PillItemElementsSerializer.INSTANCE.serialize(pillListingNavigatorElement.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(pillListingNavigatorElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(pillListingNavigatorElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(pillListingNavigatorElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(pillListingNavigatorElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
    }
}
