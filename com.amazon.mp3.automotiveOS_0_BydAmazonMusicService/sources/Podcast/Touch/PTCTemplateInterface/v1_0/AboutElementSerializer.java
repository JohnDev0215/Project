package Podcast.Touch.PTCTemplateInterface.v1_0;

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
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class AboutElementSerializer extends JsonSerializer<AboutElement> {
    public static final AboutElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AboutElementSerializer aboutElementSerializer = new AboutElementSerializer();
        INSTANCE = aboutElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.AboutElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AboutElement.class, aboutElementSerializer);
    }

    private AboutElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AboutElement aboutElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (aboutElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(aboutElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AboutElement aboutElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(aboutElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        MethodsSerializer.INSTANCE.serialize(aboutElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("negativeButton");
        NegativeButtonElementSerializer.INSTANCE.serialize(aboutElement.getNegativeButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(aboutElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(aboutElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_SUBTITLE);
        SimpleSerializers.serializeString(aboutElement.getSubtitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pills");
        CompactPillItemsSerializer.INSTANCE.serialize(aboutElement.getPills(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playButton");
        PlayButtonElementSerializer.INSTANCE.serialize(aboutElement.getPlayButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("positiveButton");
        PositiveButtonElementSerializer.INSTANCE.serialize(aboutElement.getPositiveButton(), jsonGenerator, serializerProvider);
    }
}
