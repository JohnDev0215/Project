package Podcast.Touch.PTCTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementSerializer;
import SOACoreInterface.v1_0.Method;
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
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class PTCCategoriesTemplateSerializer extends JsonSerializer<PTCCategoriesTemplate> {
    public static final PTCCategoriesTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PTCCategoriesTemplateSerializer pTCCategoriesTemplateSerializer = new PTCCategoriesTemplateSerializer();
        INSTANCE = pTCCategoriesTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.PTCCategoriesTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PTCCategoriesTemplate.class, pTCCategoriesTemplateSerializer);
    }

    private PTCCategoriesTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PTCCategoriesTemplate pTCCategoriesTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pTCCategoriesTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pTCCategoriesTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PTCCategoriesTemplate pTCCategoriesTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(pTCCategoriesTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(pTCCategoriesTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(pTCCategoriesTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        PillItemsSerializer.INSTANCE.serialize(pTCCategoriesTemplate.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(pTCCategoriesTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(pTCCategoriesTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("trailingButton");
        TrailingButtonElementSerializer.INSTANCE.serialize(pTCCategoriesTemplate.getTrailingButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(pTCCategoriesTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_SUBTITLE);
        SimpleSerializers.serializeString(pTCCategoriesTemplate.getSubtitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("leadingButton");
        LeadingButtonElementSerializer.INSTANCE.serialize(pTCCategoriesTemplate.getLeadingButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(pTCCategoriesTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(pTCCategoriesTemplate.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(pTCCategoriesTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
