package Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0;

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
public class EpisodeDescriptiveSeeMoreItemElementSerializer extends JsonSerializer<EpisodeDescriptiveSeeMoreItemElement> {
    public static final EpisodeDescriptiveSeeMoreItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeDescriptiveSeeMoreItemElementSerializer episodeDescriptiveSeeMoreItemElementSerializer = new EpisodeDescriptiveSeeMoreItemElementSerializer();
        INSTANCE = episodeDescriptiveSeeMoreItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveSeeMoreItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EpisodeDescriptiveSeeMoreItemElement.class, episodeDescriptiveSeeMoreItemElementSerializer);
    }

    private EpisodeDescriptiveSeeMoreItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EpisodeDescriptiveSeeMoreItemElement episodeDescriptiveSeeMoreItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (episodeDescriptiveSeeMoreItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(episodeDescriptiveSeeMoreItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EpisodeDescriptiveSeeMoreItemElement episodeDescriptiveSeeMoreItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(episodeDescriptiveSeeMoreItemElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(episodeDescriptiveSeeMoreItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
