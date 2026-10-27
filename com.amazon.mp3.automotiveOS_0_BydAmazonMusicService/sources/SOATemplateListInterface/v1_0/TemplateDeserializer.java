package SOATemplateListInterface.v1_0;

import Podcast.BookmarkInterface.v1_0.BookmarksTemplateDeserializer;
import Podcast.Desktop.DefaultTemplateInterface.v1_0.DefaultTemplateDeserializer;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.EpisodeDetailTemplateDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.GalleryTemplateDeserializer;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowTemplateDeserializer;
import Podcast.DownloadInterface.v1_0.DownloadsTemplateDeserializer;
import Podcast.FollowInterface.v1_0.FollowsTemplateDeserializer;
import Podcast.JumpBackInInterface.v1_0.JumpBackInTemplateDeserializer;
import Podcast.SaveInterface.v1_0.SavesTemplateDeserializer;
import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplateDeserializer;
import Podcast.Touch.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplateDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardNowPlayingTemplateDeserializer;
import Podcast.Touch.Certification.ListTemplateInterface.v1_0.ListTemplateDeserializer;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateDeserializer;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplateDeserializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateDeserializer;
import Podcast.Touch.EpisodeRowTemplateInterface.v1_0.EpisodeRowTemplateDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalRowTemplateDeserializer;
import Podcast.Touch.LatestTemplateInterface.v2_0.LatestTemplateDeserializer;
import Podcast.Touch.NowPlayingTemplateInterface.v1_0.NowPlayingTemplateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCCategoriesTemplateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEpisodesTemplateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPodcastsTemplateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.PodcastDetailTemplateDeserializer;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalGridTemplateDeserializer;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowTemplateDeserializer;
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
public class TemplateDeserializer extends JsonDeserializer<Template> {
    public static final TemplateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TemplateDeserializer templateDeserializer = new TemplateDeserializer();
        INSTANCE = templateDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.TemplateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Template.class, templateDeserializer);
    }

    private TemplateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Template deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#DetailTemplate".equals(text)) {
                return DetailTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowTemplateInterface.v1_0#EpisodeRowTemplate".equals(text)) {
                return EpisodeRowTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCEpisodesTemplate".equals(text)) {
                return PTCEpisodesTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCTemplate".equals(text)) {
                return PTCTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#PodcastDetailTemplate".equals(text)) {
                return PodcastDetailTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCCategoriesTemplate".equals(text)) {
                return PTCCategoriesTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.DefaultTemplateInterface.v1_0#DefaultTemplate".equals(text)) {
                return DefaultTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#PodcastDetailTemplate".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.PodcastDetailTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v2_0#LatestTemplate".equals(text)) {
                return LatestTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#CompactDetailTemplate".equals(text)) {
                return CompactDetailTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#EpisodeDetailTemplate".equals(text)) {
                return EpisodeDetailTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodesTemplateInterface.v1_0#EpisodeRowTemplate".equals(text)) {
                return Podcast.Desktop.EpisodesTemplateInterface.v1_0.EpisodeRowTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DefaultTemplateInterface.v1_0#DefaultTemplate".equals(text)) {
                return Podcast.Touch.DefaultTemplateInterface.v1_0.DefaultTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.Certification.ListTemplateInterface.v1_0#ListTemplate".equals(text)) {
                return ListTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.DownloadInterface.v1_0#DownloadsTemplate".equals(text)) {
                return DownloadsTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.LatestTemplateInterface.v1_0#LatestTemplate".equals(text)) {
                return Podcast.Desktop.LatestTemplateInterface.v1_0.LatestTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.JumpBackInInterface.v1_0#JumpBackInTemplate".equals(text)) {
                return JumpBackInTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#FollowsTemplate".equals(text)) {
                return FollowsTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#GalleryTemplate".equals(text)) {
                return GalleryTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastRowTemplateInterface.v1_0#PodcastRowTemplate".equals(text)) {
                return PodcastRowTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.AlertTemplateInterface.v1_0#StackedButtonsAlertTemplate".equals(text)) {
                return StackedButtonsAlertTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v2_0#GalleryTemplate".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.ChromeTemplateInterface.v1_0#ChromeTemplate".equals(text)) {
                return ChromeTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.AlertTemplateInterface.v1_0#AlertTemplate".equals(text)) {
                return AlertTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VisualRowTemplateInterface.v1_0#VisualRowTemplate".equals(text)) {
                return VisualRowTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#PodcastDetailTemplate".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.PodcastDetailTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#HorizontalRowTemplate".equals(text)) {
                return HorizontalRowTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.AlertTemplateInterface.v1_0#StackedButtonsAlertTemplate".equals(text)) {
                return Podcast.Desktop.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.AlertTemplateInterface.v1_0#AlertTemplate".equals(text)) {
                return Podcast.Desktop.AlertTemplateInterface.v1_0.AlertTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.SaveInterface.v1_0#SavesTemplate".equals(text)) {
                return SavesTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v1_0#LatestTemplate".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v1_0.LatestTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.NowPlayingTemplateInterface.v1_0#NowPlayingTemplate".equals(text)) {
                return NowPlayingTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#CardNowPlayingTemplate".equals(text)) {
                return CardNowPlayingTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#PodcastDetailTemplate".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.PodcastDetailTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BookmarkInterface.v1_0#BookmarksTemplate".equals(text)) {
                return BookmarksTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VerticalGridTemplateInterface.v1_0#VerticalGridTemplate".equals(text)) {
                return VerticalGridTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.NowPlayingTemplateInterface.v1_0#NowPlayingTemplate".equals(text)) {
                return Podcast.Desktop.NowPlayingTemplateInterface.v1_0.NowPlayingTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCPodcastsTemplate".equals(text)) {
                return PTCPodcastsTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#GalleryTemplate".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryTemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOATemplateListInterface.v1_0#Template".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Template deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
