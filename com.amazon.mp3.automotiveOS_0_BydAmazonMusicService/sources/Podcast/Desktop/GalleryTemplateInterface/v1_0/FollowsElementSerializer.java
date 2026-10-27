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
public class FollowsElementSerializer extends JsonSerializer<FollowsElement> {
    public static final FollowsElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FollowsElementSerializer followsElementSerializer = new FollowsElementSerializer();
        INSTANCE = followsElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FollowsElement.class, followsElementSerializer);
    }

    private FollowsElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FollowsElement followsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (followsElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(followsElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FollowsElement followsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(PageUriUtils.DO_SEE_MORE_VALUE);
        FollowsSeeMoreElementSerializer.INSTANCE.serialize(followsElement.getSeeMore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(followsElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        MethodsSerializer.INSTANCE.serialize(followsElement.getOnViewed(), jsonGenerator, serializerProvider);
    }
}
