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
public class FollowsSeeMoreElementSerializer extends JsonSerializer<FollowsSeeMoreElement> {
    public static final FollowsSeeMoreElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FollowsSeeMoreElementSerializer followsSeeMoreElementSerializer = new FollowsSeeMoreElementSerializer();
        INSTANCE = followsSeeMoreElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsSeeMoreElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FollowsSeeMoreElement.class, followsSeeMoreElementSerializer);
    }

    private FollowsSeeMoreElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FollowsSeeMoreElement followsSeeMoreElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (followsSeeMoreElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(followsSeeMoreElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FollowsSeeMoreElement followsSeeMoreElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(followsSeeMoreElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(followsSeeMoreElement.getText(), jsonGenerator, serializerProvider);
    }
}
