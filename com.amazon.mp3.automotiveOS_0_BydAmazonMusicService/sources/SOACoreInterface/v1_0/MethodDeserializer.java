package SOACoreInterface.v1_0;

import Podcast.BiteBookmarkInterface.v1_0.SetBiteBookmarksLastSyncTimeMethodDeserializer;
import Podcast.BookmarkInterface.v1_0.SetBookmarksLastSyncTimeMethodDeserializer;
import Podcast.BookmarkInterface.v1_0.SetBookmarksThresholdsMethodDeserializer;
import Podcast.BookmarkInterface.v1_0.SetOnReportOperationsMethodDeserializer;
import Podcast.CategoryFollowInterface.v1_0.SetCategoryFollowsLastSyncTimeMethodDeserializer;
import Podcast.CompletedInterface.v1_0.SetCompletedLastSyncTimeMethodDeserializer;
import Podcast.DeeplinkInterface.v1_0.InvokeDeeplinkMethodDeserializer;
import Podcast.DeeplinkInterface.v1_0.NavigateToDeeplinkMethodDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.AddVerticalItemsToVisualListingElementMethodDeserializer;
import Podcast.Desktop.NetworkInterface.v1_0.ShowErrorMessageMethodDeserializer;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.AddPodcastRowItemsMethodDeserializer;
import Podcast.FeatureGatingInterface.v1_0.SetBooleanFeaturesMethodDeserializer;
import Podcast.FollowInterface.v1_0.SetFollowsLastSyncTimeMethodDeserializer;
import Podcast.FollowPromptInterface.v1_0.WriteHidePromptPreferencesMethodDeserializer;
import Podcast.JumpBackInInterface.v1_0.InvalidateJumpBackInCacheMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.BufferMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.ClearMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.NavigateToPodcastMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.PlayLocalEpisodesMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.PlayMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.ResetMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SendPlaybackEventMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetEMPMediaMetadataMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetEMPPlaybackSessionMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetMediaOffsetMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetPlaybackCapabilitiesMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetTranscriptsMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetWriteAndReportFrequenciesMethodDeserializer;
import Podcast.SaveInterface.v1_0.SetSavesLastSyncTimeMethodDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.AddCardsMethodDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.NextCardMethodDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.PreviousCardMethodDeserializer;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShowVisualShovelerMethodDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.AddWidgetsMethodDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AddHorizontalItemsMethodDeserializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.AddLatestItemElementsMethodDeserializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer;
import Podcast.Touch.LatestTemplateInterface.v2_0.AddEpisodeRowItemsMethodDeserializer;
import Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethodDeserializer;
import Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.AddGridElementsMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEndMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTrailerPlaybackErrorMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.SetPTCPreferencesMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.ShowPTCTrailerMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.WritePTCCacheMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.AddEpisodeItemsMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ReplaceEpisodeItemsMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ReplaceEpisodeRowItemsMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShowDownloadedEpisodesMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AddEpisodeGroupItemsMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ReplaceEpisodeGroupItemsMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShowDownloadedEpisodeGroupsMethodDeserializer;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.AddVerticalItemsMethodDeserializer;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.AddVisualRowItemsMethodDeserializer;
import Podcast.UpsellInterface.v1_0.ShowUpsellMethodDeserializer;
import SOAAppSyncInterface.v1_0.ResolveOperationMethodDeserializer;
import SOAAppSyncInterface.v1_0.RetryOperationMethodDeserializer;
import SOAAppSyncInterface.v1_0.StartMethodDeserializer;
import SOAAppSyncInterface.v1_0.WriteLocalMethodDeserializer;
import SOAAppSyncInterface.v1_0.WriteMethodDeserializer;
import SOACacheInterface.v1_0.InvalidateCacheMethodDeserializer;
import SOACacheInterface.v1_0.WriteCacheMethodDeserializer;
import SOAInteractionInterface.v1_0.GlobalInvokeRemoteSkillMethodDeserializer;
import SOAInteractionInterface.v1_0.InvokeRemoteSkillMethodDeserializer;
import SOATemplateListInterface.v1_0.BindTemplateMethodDeserializer;
import SOATemplateListInterface.v1_0.ClearTemplatesMethodDeserializer;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethodDeserializer;
import SOATemplateListInterface.v1_0.CreateTemplateMethodDeserializer;
import SOATemplateListInterface.v1_0.InvalidateTemplatesMethodDeserializer;
import SOATemplateListInterface.v1_0.RemoveTemplateMethodDeserializer;
import SOAV2CompatibilityInterface.v1_0.ExtractResponseMethodDeserializer;
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
public class MethodDeserializer extends JsonDeserializer<Method> {
    public static final MethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MethodDeserializer methodDeserializer = new MethodDeserializer();
        INSTANCE = methodDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.MethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Method.class, methodDeserializer);
    }

    private MethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Method deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.BookmarkInterface.v1_0#SetOnReportOperationsMethod".equals(text)) {
                return SetOnReportOperationsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#AddHorizontalItemsMethod".equals(text)) {
                return AddHorizontalItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CategoryFollowInterface.v1_0#SetOnReportOperationsMethod".equals(text)) {
                return Podcast.CategoryFollowInterface.v1_0.SetOnReportOperationsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#SetEMPPlaybackSessionMethod".equals(text)) {
                return SetEMPPlaybackSessionMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#WritePTCCacheMethod".equals(text)) {
                return WritePTCCacheMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACacheInterface.v1_0#WriteCacheMethod".equals(text)) {
                return WriteCacheMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FeatureGatingInterface.v1_0#SetBooleanFeaturesMethod".equals(text)) {
                return SetBooleanFeaturesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#ShowDownloadedEpisodeGroupsMethod".equals(text)) {
                return ShowDownloadedEpisodeGroupsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#AddVerticalItemsToVisualListingElementMethod".equals(text)) {
                return AddVerticalItemsToVisualListingElementMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOATemplateListInterface.v1_0#CreateAndBindTemplateMethod".equals(text)) {
                return CreateAndBindTemplateMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CompletedInterface.v1_0#SetCompletedLastSyncTimeMethod".equals(text)) {
                return SetCompletedLastSyncTimeMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#ReplaceEpisodeGroupItemsMethod".equals(text)) {
                return ReplaceEpisodeGroupItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#ShowPTCTrailerMethod".equals(text)) {
                return ShowPTCTrailerMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#SetMediaOffsetMethod".equals(text)) {
                return SetMediaOffsetMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VerticalGridTemplateInterface.v1_0#AddVerticalItemsMethod".equals(text)) {
                return AddVerticalItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.NotificationElementInterface.v1_0#ShowNotificationMethod".equals(text)) {
                return ShowNotificationMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#SendPlaybackEventMethod".equals(text)) {
                return SendPlaybackEventMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#SetMediaMethod".equals(text)) {
                return SetMediaMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#BufferMediaMethod".equals(text)) {
                return BufferMediaMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.NotificationElementInterface.v1_0#CloseNotificationMethod".equals(text)) {
                return CloseNotificationMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#SetFollowsLastSyncTimeMethod".equals(text)) {
                return SetFollowsLastSyncTimeMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOAAppSyncInterface.v1_0#WriteLocalMethod".equals(text)) {
                return WriteLocalMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#PreviousCardMethod".equals(text)) {
                return PreviousCardMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#ReplaceEpisodeItemsMethod".equals(text)) {
                return ReplaceEpisodeItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#PlayLocalEpisodesMethod".equals(text)) {
                return PlayLocalEpisodesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#AddWidgetsMethod".equals(text)) {
                return AddWidgetsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#ResumeMediaMethod".equals(text)) {
                return ResumeMediaMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#AddEpisodeItemsMethod".equals(text)) {
                return AddEpisodeItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOATemplateListInterface.v1_0#RemoveTemplateMethod".equals(text)) {
                return RemoveTemplateMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#AddEpisodeGroupItemsMethod".equals(text)) {
                return AddEpisodeGroupItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.NetworkInterface.v1_0#ShowErrorMessageMethod".equals(text)) {
                return ShowErrorMessageMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#PlayMediaMethod".equals(text)) {
                return PlayMediaMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#ShowDownloadedEpisodesMethod".equals(text)) {
                return ShowDownloadedEpisodesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOAAppSyncInterface.v1_0#ResolveOperationMethod".equals(text)) {
                return ResolveOperationMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#SetEMPMediaMetadataMethod".equals(text)) {
                return SetEMPMediaMetadataMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOATemplateListInterface.v1_0#ClearTemplatesMethod".equals(text)) {
                return ClearTemplatesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#ReplaceEpisodeRowItemsMethod".equals(text)) {
                return ReplaceEpisodeRowItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.UpsellInterface.v1_0#ShowUpsellMethod".equals(text)) {
                return ShowUpsellMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCTrailerPlaybackErrorMethod".equals(text)) {
                return PTCTrailerPlaybackErrorMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#ShowDownloadedEpisodesMethod".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ShowDownloadedEpisodesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v1_0#AddLatestItemElementsMethod".equals(text)) {
                return AddLatestItemElementsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v2_0#AddEpisodeRowItemsMethod".equals(text)) {
                return AddEpisodeRowItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#SetTranscriptsMethod".equals(text)) {
                return SetTranscriptsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#ShowVisualShovelerMethod".equals(text)) {
                return ShowVisualShovelerMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#SetPlaybackCapabilitiesMethod".equals(text)) {
                return SetPlaybackCapabilitiesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastRowTemplateInterface.v1_0#AddPodcastRowItemsMethod".equals(text)) {
                return AddPodcastRowItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#ReplaceEpisodeRowItemsMethod".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ReplaceEpisodeRowItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.LatestTemplateInterface.v1_0#AddLatestItemElementsMethod".equals(text)) {
                return Podcast.Desktop.LatestTemplateInterface.v1_0.AddLatestItemElementsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#ClearMediaMethod".equals(text)) {
                return ClearMediaMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOAAppSyncInterface.v1_0#StartMethod".equals(text)) {
                return StartMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#NextCardMethod".equals(text)) {
                return NextCardMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BiteBookmarkInterface.v1_0#SetBiteBookmarksLastSyncTimeMethod".equals(text)) {
                return SetBiteBookmarksLastSyncTimeMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#AddEpisodeRowItemsMethod".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.AddEpisodeRowItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodesTemplateInterface.v1_0#AddEpisodeRowItemsMethod".equals(text)) {
                return Podcast.Desktop.EpisodesTemplateInterface.v1_0.AddEpisodeRowItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOATemplateListInterface.v1_0#BindTemplateMethod".equals(text)) {
                return BindTemplateMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.UIMetricsInterface.v1_0#SetOnReportOperationsMethod".equals(text)) {
                return Podcast.UIMetricsInterface.v1_0.SetOnReportOperationsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCEndMethod".equals(text)) {
                return PTCEndMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#SetOnReportOperationsMethod".equals(text)) {
                return Podcast.FollowInterface.v1_0.SetOnReportOperationsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CategoryFollowInterface.v1_0#SetCategoryFollowsLastSyncTimeMethod".equals(text)) {
                return SetCategoryFollowsLastSyncTimeMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#AddCardsMethod".equals(text)) {
                return AddCardsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#ShowDownloadedEpisodesMethod".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ShowDownloadedEpisodesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VisualRowTemplateInterface.v1_0#AddVisualRowItemsMethod".equals(text)) {
                return AddVisualRowItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#AddWidgetsMethod".equals(text)) {
                return Podcast.Touch.WidgetElementsInterface.v1_0.AddWidgetsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOAV2CompatibilityInterface.v1_0#ExtractResponseMethod".equals(text)) {
                return ExtractResponseMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#PauseMediaMethod".equals(text)) {
                return PauseMediaMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CompletedInterface.v1_0#SetOnReportOperationsMethod".equals(text)) {
                return Podcast.CompletedInterface.v1_0.SetOnReportOperationsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.DownloadInterface.v1_0#SetOnReportOperationsMethod".equals(text)) {
                return Podcast.DownloadInterface.v1_0.SetOnReportOperationsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#AddWidgetsMethod".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.AddWidgetsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOATemplateListInterface.v1_0#CreateTemplateMethod".equals(text)) {
                return CreateTemplateMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOAAppSyncInterface.v1_0#RetryOperationMethod".equals(text)) {
                return RetryOperationMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BookmarkInterface.v1_0#SetBookmarksLastSyncTimeMethod".equals(text)) {
                return SetBookmarksLastSyncTimeMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#SetWriteAndReportFrequenciesMethod".equals(text)) {
                return SetWriteAndReportFrequenciesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.DeeplinkInterface.v1_0#InvokeDeeplinkMethod".equals(text)) {
                return InvokeDeeplinkMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackMetricsInterface.v1_0#SetOnReportOperationsMethod".equals(text)) {
                return Podcast.PlaybackMetricsInterface.v1_0.SetOnReportOperationsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOAInteractionInterface.v1_0#InvokeRemoteSkillMethod".equals(text)) {
                return InvokeRemoteSkillMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOAInteractionInterface.v1_0#GlobalInvokeRemoteSkillMethod".equals(text)) {
                return GlobalInvokeRemoteSkillMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BiteBookmarkInterface.v1_0#SetOnReportOperationsMethod".equals(text)) {
                return Podcast.BiteBookmarkInterface.v1_0.SetOnReportOperationsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#AddEpisodeRowItemsMethod".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AddEpisodeRowItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.SaveInterface.v1_0#SetSavesLastSyncTimeMethod".equals(text)) {
                return SetSavesLastSyncTimeMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#SetPTCPreferencesMethod".equals(text)) {
                return SetPTCPreferencesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.DeeplinkInterface.v1_0#NavigateToDeeplinkMethod".equals(text)) {
                return NavigateToDeeplinkMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#ResetMediaMethod".equals(text)) {
                return ResetMediaMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.SaveInterface.v1_0#SetOnReportOperationsMethod".equals(text)) {
                return Podcast.SaveInterface.v1_0.SetOnReportOperationsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#NavigateToPodcastMethod".equals(text)) {
                return NavigateToPodcastMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOATemplateListInterface.v1_0#InvalidateTemplatesMethod".equals(text)) {
                return InvalidateTemplatesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BookmarkInterface.v1_0#SetBookmarksThresholdsMethod".equals(text)) {
                return SetBookmarksThresholdsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOAAppSyncInterface.v1_0#WriteMethod".equals(text)) {
                return WriteMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowPromptInterface.v1_0#WriteHidePromptPreferencesMethod".equals(text)) {
                return WriteHidePromptPreferencesMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowTemplateInterface.v1_0#AddEpisodeRowItemsMethod".equals(text)) {
                return Podcast.Touch.EpisodeRowTemplateInterface.v1_0.AddEpisodeRowItemsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#AddGridElementsMethod".equals(text)) {
                return AddGridElementsMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#InvalidatePTCCacheMethod".equals(text)) {
                return InvalidatePTCCacheMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v1_0#InvalidateFeaturedItemMethod".equals(text)) {
                return InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v2_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v2_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.JumpBackInInterface.v1_0#InvalidateJumpBackInCacheMethod".equals(text)) {
                return InvalidateJumpBackInCacheMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.LatestTemplateInterface.v1_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Desktop.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACacheInterface.v1_0#InvalidateCacheMethod".equals(text)) {
                return InvalidateCacheMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#Method".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Method deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
