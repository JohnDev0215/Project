package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

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
public class ShareElementSerializer extends JsonSerializer<ShareElement> {
    public static final ShareElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ShareElementSerializer shareElementSerializer = new ShareElementSerializer();
        INSTANCE = shareElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ShareElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ShareElement.class, shareElementSerializer);
    }

    private ShareElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ShareElement shareElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (shareElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(shareElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ShareElement shareElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(shareElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(shareElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("imageUrl");
        SimpleSerializers.serializeString(shareElement.getImageUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onShare");
        MethodsSerializer.INSTANCE.serialize(shareElement.getOnShare(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(shareElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_SUBTITLE);
        SimpleSerializers.serializeString(shareElement.getSubtitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(shareElement.getLabel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metricsEntityType");
        SimpleSerializers.serializeString(shareElement.getMetricsEntityType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("deeplinkUrl");
        SimpleSerializers.serializeString(shareElement.getDeeplinkUrl(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("prompt");
        SimpleSerializers.serializeString(shareElement.getPrompt(), jsonGenerator, serializerProvider);
    }
}
