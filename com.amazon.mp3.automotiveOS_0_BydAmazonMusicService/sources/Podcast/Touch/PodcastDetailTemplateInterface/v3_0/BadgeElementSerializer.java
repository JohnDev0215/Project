package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

import SOACoreInterface.v1_0.MethodsSerializer;
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
public class BadgeElementSerializer extends JsonSerializer<BadgeElement> {
    public static final BadgeElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BadgeElementSerializer badgeElementSerializer = new BadgeElementSerializer();
        INSTANCE = badgeElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v3_0.BadgeElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BadgeElement.class, badgeElementSerializer);
    }

    private BadgeElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BadgeElement badgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (badgeElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (badgeElement instanceof BorderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#BorderedBadgeElement");
            BorderedBadgeElementSerializer.INSTANCE.serializeFields((BorderedBadgeElement) badgeElement, jsonGenerator, serializerProvider);
        } else if (badgeElement instanceof UnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#UnborderedBadgeElement");
            UnborderedBadgeElementSerializer.INSTANCE.serializeFields((UnborderedBadgeElement) badgeElement, jsonGenerator, serializerProvider);
        } else if (badgeElement instanceof LightUnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#LightUnborderedBadgeElement");
            LightUnborderedBadgeElementSerializer.INSTANCE.serializeFields((LightUnborderedBadgeElement) badgeElement, jsonGenerator, serializerProvider);
        } else {
            boolean z = badgeElement instanceof AccentOutlineBadgeElement;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(badgeElement, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#AccentOutlineBadgeElement");
                AccentOutlineBadgeElementSerializer.INSTANCE.serializeFields((AccentOutlineBadgeElement) badgeElement, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BadgeElement badgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(badgeElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(badgeElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
