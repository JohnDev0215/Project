package Podcast.Touch.AlertTemplateInterface.v1_0;

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
public class StackedButtonsAlertTemplateSerializer extends JsonSerializer<StackedButtonsAlertTemplate> {
    public static final StackedButtonsAlertTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        StackedButtonsAlertTemplateSerializer stackedButtonsAlertTemplateSerializer = new StackedButtonsAlertTemplateSerializer();
        INSTANCE = stackedButtonsAlertTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(StackedButtonsAlertTemplate.class, stackedButtonsAlertTemplateSerializer);
    }

    private StackedButtonsAlertTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull StackedButtonsAlertTemplate stackedButtonsAlertTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (stackedButtonsAlertTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(stackedButtonsAlertTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(StackedButtonsAlertTemplate stackedButtonsAlertTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(stackedButtonsAlertTemplate.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(stackedButtonsAlertTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(stackedButtonsAlertTemplate.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(stackedButtonsAlertTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(stackedButtonsAlertTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(stackedButtonsAlertTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("negativeButton");
        NegativeButtonElementSerializer.INSTANCE.serialize(stackedButtonsAlertTemplate.getNegativeButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(stackedButtonsAlertTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_SUBTITLE);
        SimpleSerializers.serializeString(stackedButtonsAlertTemplate.getSubtitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismiss");
        jsonSerializer.serialize(stackedButtonsAlertTemplate.getOnDismiss(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(stackedButtonsAlertTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(stackedButtonsAlertTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("positiveButton");
        PositiveButtonElementSerializer.INSTANCE.serialize(stackedButtonsAlertTemplate.getPositiveButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(stackedButtonsAlertTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
