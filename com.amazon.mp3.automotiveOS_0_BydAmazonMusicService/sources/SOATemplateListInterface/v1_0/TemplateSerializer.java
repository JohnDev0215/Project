package SOATemplateListInterface.v1_0;

import Podcast.BookmarkInterface.v1_0.BookmarksTemplate;
import Podcast.BookmarkInterface.v1_0.BookmarksTemplateSerializer;
import Podcast.Desktop.DefaultTemplateInterface.v1_0.DefaultTemplate;
import Podcast.Desktop.DefaultTemplateInterface.v1_0.DefaultTemplateSerializer;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.EpisodeDetailTemplate;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.EpisodeDetailTemplateSerializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.GalleryTemplate;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.GalleryTemplateSerializer;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowTemplate;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowTemplateSerializer;
import Podcast.DownloadInterface.v1_0.DownloadsTemplate;
import Podcast.DownloadInterface.v1_0.DownloadsTemplateSerializer;
import Podcast.FollowInterface.v1_0.FollowsTemplate;
import Podcast.FollowInterface.v1_0.FollowsTemplateSerializer;
import Podcast.JumpBackInInterface.v1_0.JumpBackInTemplate;
import Podcast.JumpBackInInterface.v1_0.JumpBackInTemplateSerializer;
import Podcast.SaveInterface.v1_0.SavesTemplate;
import Podcast.SaveInterface.v1_0.SavesTemplateSerializer;
import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplateSerializer;
import Podcast.Touch.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplateSerializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardNowPlayingTemplate;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardNowPlayingTemplateSerializer;
import Podcast.Touch.Certification.ListTemplateInterface.v1_0.ListTemplate;
import Podcast.Touch.Certification.ListTemplateInterface.v1_0.ListTemplateSerializer;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplate;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateSerializer;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplate;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplateSerializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplate;
import Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateSerializer;
import Podcast.Touch.EpisodeRowTemplateInterface.v1_0.EpisodeRowTemplate;
import Podcast.Touch.EpisodeRowTemplateInterface.v1_0.EpisodeRowTemplateSerializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalRowTemplate;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalRowTemplateSerializer;
import Podcast.Touch.LatestTemplateInterface.v2_0.LatestTemplate;
import Podcast.Touch.LatestTemplateInterface.v2_0.LatestTemplateSerializer;
import Podcast.Touch.NowPlayingTemplateInterface.v1_0.NowPlayingTemplate;
import Podcast.Touch.NowPlayingTemplateInterface.v1_0.NowPlayingTemplateSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCCategoriesTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCCategoriesTemplateSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEpisodesTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEpisodesTemplateSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPodcastsTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPodcastsTemplateSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.PodcastDetailTemplate;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.PodcastDetailTemplateSerializer;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalGridTemplate;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalGridTemplateSerializer;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowTemplate;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowTemplateSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class TemplateSerializer extends JsonSerializer<Template> {
    public static final TemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TemplateSerializer templateSerializer = new TemplateSerializer();
        INSTANCE = templateSerializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.TemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Template.class, templateSerializer);
    }

    private TemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Template template, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (template == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (template instanceof DetailTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#DetailTemplate");
            DetailTemplateSerializer.INSTANCE.serializeFields((DetailTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof EpisodeRowTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowTemplateInterface.v1_0#EpisodeRowTemplate");
            EpisodeRowTemplateSerializer.INSTANCE.serializeFields((EpisodeRowTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof PTCEpisodesTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PTCEpisodesTemplate");
            PTCEpisodesTemplateSerializer.INSTANCE.serializeFields((PTCEpisodesTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof PTCTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PTCTemplate");
            PTCTemplateSerializer.INSTANCE.serializeFields((PTCTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof PodcastDetailTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#PodcastDetailTemplate");
            PodcastDetailTemplateSerializer.INSTANCE.serializeFields((PodcastDetailTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof PTCCategoriesTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PTCCategoriesTemplate");
            PTCCategoriesTemplateSerializer.INSTANCE.serializeFields((PTCCategoriesTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof DefaultTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.DefaultTemplateInterface.v1_0#DefaultTemplate");
            DefaultTemplateSerializer.INSTANCE.serializeFields((DefaultTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.PodcastDetailTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#PodcastDetailTemplate");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.PodcastDetailTemplateSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.PodcastDetailTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof LatestTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v2_0#LatestTemplate");
            LatestTemplateSerializer.INSTANCE.serializeFields((LatestTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof CompactDetailTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#CompactDetailTemplate");
            CompactDetailTemplateSerializer.INSTANCE.serializeFields((CompactDetailTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof EpisodeDetailTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#EpisodeDetailTemplate");
            EpisodeDetailTemplateSerializer.INSTANCE.serializeFields((EpisodeDetailTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Desktop.EpisodesTemplateInterface.v1_0.EpisodeRowTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodesTemplateInterface.v1_0#EpisodeRowTemplate");
            Podcast.Desktop.EpisodesTemplateInterface.v1_0.EpisodeRowTemplateSerializer.INSTANCE.serializeFields((Podcast.Desktop.EpisodesTemplateInterface.v1_0.EpisodeRowTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Touch.DefaultTemplateInterface.v1_0.DefaultTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DefaultTemplateInterface.v1_0#DefaultTemplate");
            Podcast.Touch.DefaultTemplateInterface.v1_0.DefaultTemplateSerializer.INSTANCE.serializeFields((Podcast.Touch.DefaultTemplateInterface.v1_0.DefaultTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof ListTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.Certification.ListTemplateInterface.v1_0#ListTemplate");
            ListTemplateSerializer.INSTANCE.serializeFields((ListTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof DownloadsTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.DownloadInterface.v1_0#DownloadsTemplate");
            DownloadsTemplateSerializer.INSTANCE.serializeFields((DownloadsTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Desktop.LatestTemplateInterface.v1_0.LatestTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.LatestTemplateInterface.v1_0#LatestTemplate");
            Podcast.Desktop.LatestTemplateInterface.v1_0.LatestTemplateSerializer.INSTANCE.serializeFields((Podcast.Desktop.LatestTemplateInterface.v1_0.LatestTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof JumpBackInTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.JumpBackInInterface.v1_0#JumpBackInTemplate");
            JumpBackInTemplateSerializer.INSTANCE.serializeFields((JumpBackInTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof FollowsTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowInterface.v1_0#FollowsTemplate");
            FollowsTemplateSerializer.INSTANCE.serializeFields((FollowsTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof GalleryTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#GalleryTemplate");
            GalleryTemplateSerializer.INSTANCE.serializeFields((GalleryTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof PodcastRowTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastRowTemplateInterface.v1_0#PodcastRowTemplate");
            PodcastRowTemplateSerializer.INSTANCE.serializeFields((PodcastRowTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof StackedButtonsAlertTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.AlertTemplateInterface.v1_0#StackedButtonsAlertTemplate");
            StackedButtonsAlertTemplateSerializer.INSTANCE.serializeFields((StackedButtonsAlertTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v2_0#GalleryTemplate");
            Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryTemplateSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof ChromeTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.ChromeTemplateInterface.v1_0#ChromeTemplate");
            ChromeTemplateSerializer.INSTANCE.serializeFields((ChromeTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof AlertTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.AlertTemplateInterface.v1_0#AlertTemplate");
            AlertTemplateSerializer.INSTANCE.serializeFields((AlertTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof VisualRowTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VisualRowTemplateInterface.v1_0#VisualRowTemplate");
            VisualRowTemplateSerializer.INSTANCE.serializeFields((VisualRowTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.PodcastDetailTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#PodcastDetailTemplate");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.PodcastDetailTemplateSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.PodcastDetailTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof HorizontalRowTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#HorizontalRowTemplate");
            HorizontalRowTemplateSerializer.INSTANCE.serializeFields((HorizontalRowTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Desktop.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.AlertTemplateInterface.v1_0#StackedButtonsAlertTemplate");
            Podcast.Desktop.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplateSerializer.INSTANCE.serializeFields((Podcast.Desktop.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Desktop.AlertTemplateInterface.v1_0.AlertTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.AlertTemplateInterface.v1_0#AlertTemplate");
            Podcast.Desktop.AlertTemplateInterface.v1_0.AlertTemplateSerializer.INSTANCE.serializeFields((Podcast.Desktop.AlertTemplateInterface.v1_0.AlertTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof SavesTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.SaveInterface.v1_0#SavesTemplate");
            SavesTemplateSerializer.INSTANCE.serializeFields((SavesTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Touch.LatestTemplateInterface.v1_0.LatestTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v1_0#LatestTemplate");
            Podcast.Touch.LatestTemplateInterface.v1_0.LatestTemplateSerializer.INSTANCE.serializeFields((Podcast.Touch.LatestTemplateInterface.v1_0.LatestTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof NowPlayingTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.NowPlayingTemplateInterface.v1_0#NowPlayingTemplate");
            NowPlayingTemplateSerializer.INSTANCE.serializeFields((NowPlayingTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof CardNowPlayingTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#CardNowPlayingTemplate");
            CardNowPlayingTemplateSerializer.INSTANCE.serializeFields((CardNowPlayingTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.PodcastDetailTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#PodcastDetailTemplate");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.PodcastDetailTemplateSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.PodcastDetailTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof BookmarksTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BookmarkInterface.v1_0#BookmarksTemplate");
            BookmarksTemplateSerializer.INSTANCE.serializeFields((BookmarksTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof VerticalGridTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VerticalGridTemplateInterface.v1_0#VerticalGridTemplate");
            VerticalGridTemplateSerializer.INSTANCE.serializeFields((VerticalGridTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof Podcast.Desktop.NowPlayingTemplateInterface.v1_0.NowPlayingTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.NowPlayingTemplateInterface.v1_0#NowPlayingTemplate");
            Podcast.Desktop.NowPlayingTemplateInterface.v1_0.NowPlayingTemplateSerializer.INSTANCE.serializeFields((Podcast.Desktop.NowPlayingTemplateInterface.v1_0.NowPlayingTemplate) template, jsonGenerator, serializerProvider);
        } else if (template instanceof PTCPodcastsTemplate) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PTCPodcastsTemplate");
            PTCPodcastsTemplateSerializer.INSTANCE.serializeFields((PTCPodcastsTemplate) template, jsonGenerator, serializerProvider);
        } else {
            boolean z = template instanceof Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(template, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#GalleryTemplate");
                Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryTemplateSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryTemplate) template, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Template template, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
