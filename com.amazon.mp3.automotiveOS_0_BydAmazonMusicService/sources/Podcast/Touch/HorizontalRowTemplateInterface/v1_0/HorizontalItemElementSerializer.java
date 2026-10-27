package Podcast.Touch.HorizontalRowTemplateInterface.v1_0;

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
public class HorizontalItemElementSerializer extends JsonSerializer<HorizontalItemElement> {
    public static final HorizontalItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        HorizontalItemElementSerializer horizontalItemElementSerializer = new HorizontalItemElementSerializer();
        INSTANCE = horizontalItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(HorizontalItemElement.class, horizontalItemElementSerializer);
    }

    private HorizontalItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull HorizontalItemElement horizontalItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (horizontalItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(horizontalItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(HorizontalItemElement horizontalItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("follow");
        FollowElementSerializer.INSTANCE.serialize(horizontalItemElement.getFollow(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.TERTIARY_TEXT);
        SimpleSerializers.serializeString(horizontalItemElement.getTertiaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("badge");
        BadgeElementSerializer.INSTANCE.serialize(horizontalItemElement.getBadge(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(horizontalItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(horizontalItemElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(horizontalItemElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(horizontalItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
