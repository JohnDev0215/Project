package Podcast.Touch.Certification.ListTemplateInterface.v1_0;

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
public class ListItemElementSerializer extends JsonSerializer<ListItemElement> {
    public static final ListItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ListItemElementSerializer listItemElementSerializer = new ListItemElementSerializer();
        INSTANCE = listItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.Certification.ListTemplateInterface.v1_0.ListItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ListItemElement.class, listItemElementSerializer);
    }

    private ListItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ListItemElement listItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (listItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(listItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ListItemElement listItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(listItemElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(listItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
