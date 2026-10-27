package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class JumpBackInElementSerializer extends JsonSerializer<JumpBackInElement> {
    public static final JumpBackInElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        JumpBackInElementSerializer jumpBackInElementSerializer = new JumpBackInElementSerializer();
        INSTANCE = jumpBackInElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(JumpBackInElement.class, jumpBackInElementSerializer);
    }

    private JumpBackInElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull JumpBackInElement jumpBackInElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (jumpBackInElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(jumpBackInElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(JumpBackInElement jumpBackInElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(jumpBackInElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(PageUriUtils.DO_SEE_MORE_VALUE);
        JumpBackInSeeMoreElementSerializer.INSTANCE.serialize(jumpBackInElement.getSeeMore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        MethodsSerializer.INSTANCE.serialize(jumpBackInElement.getOnViewed(), jsonGenerator, serializerProvider);
    }
}
