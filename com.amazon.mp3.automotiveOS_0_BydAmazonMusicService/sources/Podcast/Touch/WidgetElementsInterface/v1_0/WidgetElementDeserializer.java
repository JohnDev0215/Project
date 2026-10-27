package Podcast.Touch.WidgetElementsInterface.v1_0;

import Podcast.Touch.DescriptiveShowcaseElement.v1_0.DescriptiveShowcaseElementDeserializer;
import Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElementDeserializer;
import Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElementDeserializer;
import Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElementDeserializer;
import Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElementDeserializer;
import Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElementDeserializer;
import Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastFeaturedPlayElementDeserializer;
import Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElementDeserializer;
import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElementDeserializer;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElementDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class WidgetElementDeserializer extends JsonDeserializer<WidgetElement> {
    public static final WidgetElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WidgetElementDeserializer widgetElementDeserializer = new WidgetElementDeserializer();
        INSTANCE = widgetElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(WidgetElement.class, widgetElementDeserializer);
    }

    private WidgetElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public WidgetElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#SavesElement".equals(text)) {
                return SavesElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0#PodcastFeaturedPlayElement".equals(text)) {
                return PodcastFeaturedPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#BookmarksElement".equals(text)) {
                return BookmarksElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0#ThumbnailNavigatorWidgetElement".equals(text)) {
                return ThumbnailNavigatorWidgetElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.FeaturedBarkerElementInterface.v1_0#FeaturedBarkerElement".equals(text)) {
                return FeaturedBarkerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DescriptiveShowcaseElement.v1_0#DescriptiveShowcaseElement".equals(text)) {
                return DescriptiveShowcaseElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#JumpBackInElement".equals(text)) {
                return JumpBackInElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0#EpisodeFeaturedPlayElement".equals(text)) {
                return EpisodeFeaturedPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0#ExperienceVisualShovelerElement".equals(text)) {
                return ExperienceVisualShovelerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#FollowsElement".equals(text)) {
                return FollowsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PillListingNavigatorElementInterface.v1_0#PillListingNavigatorElement".equals(text)) {
                return PillListingNavigatorElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0#EpisodeDescriptiveShovelerElement".equals(text)) {
                return EpisodeDescriptiveShovelerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.RegularBarkerElementInterface.v1_0#RegularBarkerElement".equals(text)) {
                return RegularBarkerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VisualShovelerCompactElementInterface.v1_0#VisualShovelerCompactElement".equals(text)) {
                return VisualShovelerCompactElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#WidgetElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public WidgetElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
