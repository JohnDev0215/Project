package Podcast.Touch.EpisodeRowItemsInterface.v1_0;

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
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeRowItemsInterface.v1_0.BadgeElementSerializer", new Version(1, 0, 0, null, null, null));
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
        if (badgeElement instanceof UnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#UnborderedBadgeElement");
            UnborderedBadgeElementSerializer.INSTANCE.serializeFields((UnborderedBadgeElement) badgeElement, jsonGenerator, serializerProvider);
        } else if (badgeElement instanceof BorderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#BorderedBadgeElement");
            BorderedBadgeElementSerializer.INSTANCE.serializeFields((BorderedBadgeElement) badgeElement, jsonGenerator, serializerProvider);
        } else if (badgeElement instanceof AccentOutlineBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#AccentOutlineBadgeElement");
            AccentOutlineBadgeElementSerializer.INSTANCE.serializeFields((AccentOutlineBadgeElement) badgeElement, jsonGenerator, serializerProvider);
        } else {
            boolean z = badgeElement instanceof LightUnborderedBadgeElement;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(badgeElement, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#LightUnborderedBadgeElement");
                LightUnborderedBadgeElementSerializer.INSTANCE.serializeFields((LightUnborderedBadgeElement) badgeElement, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BadgeElement badgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
