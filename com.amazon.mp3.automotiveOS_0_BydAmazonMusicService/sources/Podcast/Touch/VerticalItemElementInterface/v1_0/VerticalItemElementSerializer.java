package Podcast.Touch.VerticalItemElementInterface.v1_0;

import Podcast.Touch.BadgeElementInterface.v1_0.BadgeElementSerializer;
import Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElementSerializer;
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
public class VerticalItemElementSerializer extends JsonSerializer<VerticalItemElement> {
    public static final VerticalItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        VerticalItemElementSerializer verticalItemElementSerializer = new VerticalItemElementSerializer();
        INSTANCE = verticalItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(VerticalItemElement.class, verticalItemElementSerializer);
    }

    private VerticalItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull VerticalItemElement verticalItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (verticalItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(verticalItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(VerticalItemElement verticalItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("altText");
        SimpleSerializers.serializeString(verticalItemElement.getAltText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(verticalItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("badge");
        BadgeElementSerializer.INSTANCE.serialize(verticalItemElement.getBadge(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isDisabled");
        SimpleSerializers.serializePrimitiveBoolean(verticalItemElement.isIsDisabled(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(verticalItemElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(verticalItemElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(verticalItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("bookmark");
        BookmarkElementSerializer.INSTANCE.serialize(verticalItemElement.getBookmark(), jsonGenerator, serializerProvider);
    }
}
