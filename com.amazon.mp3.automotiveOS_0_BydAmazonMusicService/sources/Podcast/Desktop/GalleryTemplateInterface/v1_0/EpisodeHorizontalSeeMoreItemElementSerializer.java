package Podcast.Desktop.GalleryTemplateInterface.v1_0;

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
public class EpisodeHorizontalSeeMoreItemElementSerializer extends JsonSerializer<EpisodeHorizontalSeeMoreItemElement> {
    public static final EpisodeHorizontalSeeMoreItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeHorizontalSeeMoreItemElementSerializer episodeHorizontalSeeMoreItemElementSerializer = new EpisodeHorizontalSeeMoreItemElementSerializer();
        INSTANCE = episodeHorizontalSeeMoreItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalSeeMoreItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EpisodeHorizontalSeeMoreItemElement.class, episodeHorizontalSeeMoreItemElementSerializer);
    }

    private EpisodeHorizontalSeeMoreItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EpisodeHorizontalSeeMoreItemElement episodeHorizontalSeeMoreItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (episodeHorizontalSeeMoreItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(episodeHorizontalSeeMoreItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EpisodeHorizontalSeeMoreItemElement episodeHorizontalSeeMoreItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(episodeHorizontalSeeMoreItemElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(episodeHorizontalSeeMoreItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
