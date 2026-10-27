package Podcast.Touch.WidgetElementsInterface.v1_0;

import Podcast.Touch.DescriptiveShowcaseElement.v1_0.DescriptiveShowcaseElement;
import Podcast.Touch.DescriptiveShowcaseElement.v1_0.DescriptiveShowcaseElementSerializer;
import Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElement;
import Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElementSerializer;
import Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElement;
import Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElementSerializer;
import Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElement;
import Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElementSerializer;
import Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElement;
import Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElementSerializer;
import Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElement;
import Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElementSerializer;
import Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastFeaturedPlayElement;
import Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastFeaturedPlayElementSerializer;
import Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElement;
import Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElementSerializer;
import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElement;
import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElementSerializer;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElement;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElementSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class WidgetElementSerializer extends JsonSerializer<WidgetElement> {
    public static final WidgetElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WidgetElementSerializer widgetElementSerializer = new WidgetElementSerializer();
        INSTANCE = widgetElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(WidgetElement.class, widgetElementSerializer);
    }

    private WidgetElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull WidgetElement widgetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (widgetElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (widgetElement instanceof SavesElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetElementsInterface.v1_0#SavesElement");
            SavesElementSerializer.INSTANCE.serializeFields((SavesElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof PodcastFeaturedPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0#PodcastFeaturedPlayElement");
            PodcastFeaturedPlayElementSerializer.INSTANCE.serializeFields((PodcastFeaturedPlayElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof BookmarksElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetElementsInterface.v1_0#BookmarksElement");
            BookmarksElementSerializer.INSTANCE.serializeFields((BookmarksElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof ThumbnailNavigatorWidgetElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0#ThumbnailNavigatorWidgetElement");
            ThumbnailNavigatorWidgetElementSerializer.INSTANCE.serializeFields((ThumbnailNavigatorWidgetElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof FeaturedBarkerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.FeaturedBarkerElementInterface.v1_0#FeaturedBarkerElement");
            FeaturedBarkerElementSerializer.INSTANCE.serializeFields((FeaturedBarkerElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof DescriptiveShowcaseElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DescriptiveShowcaseElement.v1_0#DescriptiveShowcaseElement");
            DescriptiveShowcaseElementSerializer.INSTANCE.serializeFields((DescriptiveShowcaseElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof JumpBackInElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetElementsInterface.v1_0#JumpBackInElement");
            JumpBackInElementSerializer.INSTANCE.serializeFields((JumpBackInElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof EpisodeFeaturedPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0#EpisodeFeaturedPlayElement");
            EpisodeFeaturedPlayElementSerializer.INSTANCE.serializeFields((EpisodeFeaturedPlayElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof ExperienceVisualShovelerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0#ExperienceVisualShovelerElement");
            ExperienceVisualShovelerElementSerializer.INSTANCE.serializeFields((ExperienceVisualShovelerElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof FollowsElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetElementsInterface.v1_0#FollowsElement");
            FollowsElementSerializer.INSTANCE.serializeFields((FollowsElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof PillListingNavigatorElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PillListingNavigatorElementInterface.v1_0#PillListingNavigatorElement");
            PillListingNavigatorElementSerializer.INSTANCE.serializeFields((PillListingNavigatorElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof EpisodeDescriptiveShovelerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0#EpisodeDescriptiveShovelerElement");
            EpisodeDescriptiveShovelerElementSerializer.INSTANCE.serializeFields((EpisodeDescriptiveShovelerElement) widgetElement, jsonGenerator, serializerProvider);
        } else if (widgetElement instanceof RegularBarkerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.RegularBarkerElementInterface.v1_0#RegularBarkerElement");
            RegularBarkerElementSerializer.INSTANCE.serializeFields((RegularBarkerElement) widgetElement, jsonGenerator, serializerProvider);
        } else {
            boolean z = widgetElement instanceof VisualShovelerCompactElement;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(widgetElement, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.Touch.VisualShovelerCompactElementInterface.v1_0#VisualShovelerCompactElement");
                VisualShovelerCompactElementSerializer.INSTANCE.serializeFields((VisualShovelerCompactElement) widgetElement, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(WidgetElement widgetElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
