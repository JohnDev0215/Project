package SOACoreInterface.v1_0;

import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkOperationsClientStateDeserializer;
import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarksLastSyncTimeClientStateDeserializer;
import Podcast.BiteBookmarkInterface.v1_0.SetBiteBookmarksLastSyncTimeMethodDeserializer;
import Podcast.BookmarkInterface.v1_0.BookmarkOperationsClientStateDeserializer;
import Podcast.BookmarkInterface.v1_0.BookmarksLastSyncTimeClientStateDeserializer;
import Podcast.BookmarkInterface.v1_0.BookmarksTemplateDeserializer;
import Podcast.BookmarkInterface.v1_0.SetBookmarksLastSyncTimeMethodDeserializer;
import Podcast.BookmarkInterface.v1_0.SetBookmarksThresholdsMethodDeserializer;
import Podcast.BookmarkInterface.v1_0.SetOnReportOperationsMethodDeserializer;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowOperationsClientStateDeserializer;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowsLastSyncTimeClientStateDeserializer;
import Podcast.CategoryFollowInterface.v1_0.SetCategoryFollowsLastSyncTimeMethodDeserializer;
import Podcast.CompletedInterface.v1_0.CompletedLastSyncTimeClientStateDeserializer;
import Podcast.CompletedInterface.v1_0.CompletedOperationsClientStateDeserializer;
import Podcast.CompletedInterface.v1_0.SetCompletedLastSyncTimeMethodDeserializer;
import Podcast.DeeplinkInterface.v1_0.DeeplinkClientStateDeserializer;
import Podcast.DeeplinkInterface.v1_0.InvokeDeeplinkMethodDeserializer;
import Podcast.DeeplinkInterface.v1_0.NavigateToDeeplinkMethodDeserializer;
import Podcast.Desktop.AlertTemplateInterface.v1_0.NegativeButtonElementDeserializer;
import Podcast.Desktop.DefaultTemplateInterface.v1_0.DefaultTemplateDeserializer;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.DownloadElementDeserializer;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.EpisodeDetailTemplateDeserializer;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.HeaderElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.AddVerticalItemsToVisualListingElementMethodDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.CompactPillNavigatorDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalItemElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalSeeMoreItemElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsSeeMoreElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.GalleryTemplateDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInSeeMoreElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.TrailerElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.VisualListingElementDeserializer;
import Podcast.Desktop.NetworkInterface.v1_0.ShowErrorMessageMethodDeserializer;
import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FeaturedItemWriteCacheElementDeserializer;
import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FeaturedPinnedElementDeserializer;
import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FollowElementDeserializer;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.AddPodcastRowItemsMethodDeserializer;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowItemElementDeserializer;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowTemplateDeserializer;
import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElementDeserializer;
import Podcast.DownloadInterface.v1_0.DownloadOperationsClientStateDeserializer;
import Podcast.DownloadInterface.v1_0.DownloadsTemplateDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.CarPresetElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.GoToPodcastElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.PlayEpisodeElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.PodcastShowCarPresetElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.ReportElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.SaveElementDeserializer;
import Podcast.FeatureGatingInterface.v1_0.SetBooleanFeaturesMethodDeserializer;
import Podcast.FollowInterface.v1_0.FollowOperationsClientStateDeserializer;
import Podcast.FollowInterface.v1_0.FollowsLastSyncTimeClientStateDeserializer;
import Podcast.FollowInterface.v1_0.FollowsTemplateDeserializer;
import Podcast.FollowInterface.v1_0.FooterElementDeserializer;
import Podcast.FollowInterface.v1_0.RefinementBarElementDeserializer;
import Podcast.FollowInterface.v1_0.SetFollowsLastSyncTimeMethodDeserializer;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceClientStateDeserializer;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceElementDeserializer;
import Podcast.FollowPromptInterface.v1_0.WriteHidePromptPreferencesMethodDeserializer;
import Podcast.JumpBackInInterface.v1_0.InvalidateJumpBackInCacheMethodDeserializer;
import Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElementDeserializer;
import Podcast.JumpBackInInterface.v1_0.JumpBackInTemplateDeserializer;
import Podcast.JumpBackInInterface.v1_0.JumpBackInWriteCacheElementDeserializer;
import Podcast.PlaybackInterface.v1_0.BufferMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.ClearMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.EMPMediaMetadataElementDeserializer;
import Podcast.PlaybackInterface.v1_0.FastForwardElementDeserializer;
import Podcast.PlaybackInterface.v1_0.LocalEpisodeAlreadyPlayingClientStateDeserializer;
import Podcast.PlaybackInterface.v1_0.MediaActionsElementDeserializer;
import Podcast.PlaybackInterface.v1_0.MediaControlsElementDeserializer;
import Podcast.PlaybackInterface.v1_0.MediaEventsElementDeserializer;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElementDeserializer;
import Podcast.PlaybackInterface.v1_0.NavigateToPodcastMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.PlayLocalEpisodesMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.PlayMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.PlayPauseElementDeserializer;
import Podcast.PlaybackInterface.v1_0.PlaybackElementDeserializer;
import Podcast.PlaybackInterface.v1_0.PlaybackSpeedElementDeserializer;
import Podcast.PlaybackInterface.v1_0.ResetMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.RewindElementDeserializer;
import Podcast.PlaybackInterface.v1_0.SendPlaybackEventMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetEMPMediaMetadataMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetEMPPlaybackSessionMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetMediaMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetMediaOffsetMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetPlaybackCapabilitiesMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetTranscriptsMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SetWriteAndReportFrequenciesMethodDeserializer;
import Podcast.PlaybackInterface.v1_0.SleepTimerElementDeserializer;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricsOperationsClientStateDeserializer;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElementDeserializer;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElementDeserializer;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElementDeserializer;
import Podcast.SaveInterface.v1_0.SaveOperationsClientStateDeserializer;
import Podcast.SaveInterface.v1_0.SavesLastSyncTimeClientStateDeserializer;
import Podcast.SaveInterface.v1_0.SavesTemplateDeserializer;
import Podcast.SaveInterface.v1_0.SetSavesLastSyncTimeMethodDeserializer;
import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplateDeserializer;
import Podcast.Touch.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplateDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.AddCardsMethodDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardElementDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardNowPlayingTemplateDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.FollowButtonElementDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.NextCardMethodDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.PreviousCardMethodDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.SaveButtonElementDeserializer;
import Podcast.Touch.Certification.ListTemplateInterface.v1_0.ListItemElementDeserializer;
import Podcast.Touch.Certification.ListTemplateInterface.v1_0.ListTemplateDeserializer;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateDeserializer;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateShardElementDeserializer;
import Podcast.Touch.CommonButtonElementInterface.v1_0.PrimaryButtonElementDeserializer;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BorderedBadgeElementDeserializer;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplateDeserializer;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShowVisualShovelerMethodDeserializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateDeserializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateShardElementDeserializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateShardsElementDeserializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedElementDeserializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedSingleItemElementDeserializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.PodcastDetailsBookmarkElementDeserializer;
import Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodePlayElementDeserializer;
import Podcast.Touch.EpisodeGroupItemsInterface.v1_0.EpisodeGroupItemElementDeserializer;
import Podcast.Touch.EpisodeGroupItemsInterface.v1_0.HeaderRowItemElementDeserializer;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElementDeserializer;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementDeserializer;
import Podcast.Touch.EpisodeRowTemplateInterface.v1_0.EpisodeRowTemplateDeserializer;
import Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElementDeserializer;
import Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0.FeaturedBarkerItemElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.AddWidgetsMethodDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.BarkerItemElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.BookmarkElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.BookmarksElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveSeeMoreItemElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeFeaturedPlayElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.ExperienceVisualShovelerElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.FeatureBarkerElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.FollowsElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryHeaderElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.JumpBackInElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PillItemElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PillListingNavigatorElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastPlayElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.RegularBarkerElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.SavesElementDeserializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.ThumbnailNavigationWidgetElementDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AccentOutlineBadgeElementDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AddHorizontalItemsMethodDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.BadgeElementDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElementDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalRowTemplateDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.LightUnborderedBadgeElementDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.UnborderedBadgeElementDeserializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.AddLatestItemElementsMethodDeserializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.LatestItemElementDeserializer;
import Podcast.Touch.LatestTemplateInterface.v2_0.AddEpisodeRowItemsMethodDeserializer;
import Podcast.Touch.LatestTemplateInterface.v2_0.LatestTemplateDeserializer;
import Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElementDeserializer;
import Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethodDeserializer;
import Podcast.Touch.NotificationElementInterface.v1_0.NotificationElementDeserializer;
import Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethodDeserializer;
import Podcast.Touch.NowPlayingTemplateInterface.v1_0.NowPlayingTemplateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.AddGridElementsMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.CloseButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.CompactPillItemElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.FooterButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.LeadingButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCCategoriesTemplateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEndMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEpisodesTemplateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPodcastsTemplateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPreferencesClientStateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCSelectionsClientStateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateShardsElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTrailerPlaybackErrorMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCWriteCacheElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PlayButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PositiveButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.SetPTCPreferencesMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.ShowPTCTrailerMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.TrailingButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.WritePTCCacheMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.AddEpisodeItemsMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.EpisodeItemElementDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.PodcastDetailTemplateDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientStateDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ReplaceEpisodeItemsMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AboutElementDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ReplaceEpisodeRowItemsMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShareElementDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShowDownloadedEpisodesMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AddEpisodeGroupItemsMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ButtonElementDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ReplaceEpisodeGroupItemsMethodDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShowDownloadedEpisodeGroupsMethodDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonCloseButtonElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonMessageElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonPrimaryButtonElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonSecondaryButtonElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.TextWithLinkElementDeserializer;
import Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElementDeserializer;
import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElementDeserializer;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.AddVerticalItemsMethodDeserializer;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalGridTemplateDeserializer;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalItemElementDeserializer;
import Podcast.Touch.VerticalSeeMoreItemElementInterface.v1_0.VerticalSeeMoreItemElementDeserializer;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.AddVisualRowItemsMethodDeserializer;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowItemElementDeserializer;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowTemplateDeserializer;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerSeeMoreItemElementDeserializer;
import Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElementDeserializer;
import Podcast.Touch.WidgetsInterface.v1_0.VisualShovelerCompactElementDeserializer;
import Podcast.Touch.WidgetsInterface.v1_0.WidgetElementDeserializer;
import Podcast.UIMetricsInterface.v1_0.UIMetricsOperationsClientStateDeserializer;
import Podcast.UpsellInterface.v1_0.ShowUpsellMethodDeserializer;
import SOAAppSyncInterface.v1_0.ResolveOperationMethodDeserializer;
import SOAAppSyncInterface.v1_0.RetryOperationMethodDeserializer;
import SOAAppSyncInterface.v1_0.StartMethodDeserializer;
import SOAAppSyncInterface.v1_0.WriteLocalMethodDeserializer;
import SOAAppSyncInterface.v1_0.WriteMethodDeserializer;
import SOACacheInterface.v1_0.InvalidateCacheMethodDeserializer;
import SOACacheInterface.v1_0.WriteCacheElementDeserializer;
import SOACacheInterface.v1_0.WriteCacheMethodDeserializer;
import SOAInteractionInterface.v1_0.GlobalInvokeRemoteSkillMethodDeserializer;
import SOAInteractionInterface.v1_0.InvokeRemoteSkillMethodDeserializer;
import SOATemplateListInterface.v1_0.BindTemplateMethodDeserializer;
import SOATemplateListInterface.v1_0.ClearTemplatesMethodDeserializer;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethodDeserializer;
import SOATemplateListInterface.v1_0.CreateTemplateMethodDeserializer;
import SOATemplateListInterface.v1_0.InvalidateTemplatesMethodDeserializer;
import SOATemplateListInterface.v1_0.RemoveTemplateMethodDeserializer;
import SOATemplateListInterface.v1_0.ShardDeserializer;
import SOATemplateListInterface.v1_0.TemplateDeserializer;
import SOATemplateListInterface.v1_0.TemplateElementDeserializer;
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
public class SOAObjectDeserializer extends JsonDeserializer<SOAObject> {
    public static final SOAObjectDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SOAObjectDeserializer sOAObjectDeserializer = new SOAObjectDeserializer();
        INSTANCE = sOAObjectDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.SOAObjectDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SOAObject.class, sOAObjectDeserializer);
    }

    private SOAObjectDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SOAObject deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
                return TemplateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#UserIdentity".equals(text)) {
                return UserIdentityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#Identity".equals(text)) {
                return IdentityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOATemplateListInterface.v1_0#Shard".equals(text)) {
                return ShardDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#MediaEventsElement".equals(text)) {
                return MediaEventsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#EMPMediaMetadataElement".equals(text)) {
                return EMPMediaMetadataElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#MediaControlsElement".equals(text)) {
                return MediaControlsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#SleepTimerElement".equals(text)) {
                return SleepTimerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#MediaMetadataElement".equals(text)) {
                return MediaMetadataElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#PlaybackSpeedElement".equals(text)) {
                return PlaybackSpeedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#MediaActionsElement".equals(text)) {
                return MediaActionsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#RewindElement".equals(text)) {
                return RewindElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#PlayPauseElement".equals(text)) {
                return PlayPauseElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#FastForwardElement".equals(text)) {
                return FastForwardElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#PlaybackElement".equals(text)) {
                return PlaybackElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#Response".equals(text)) {
                return ResponseDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#AccountIdentity".equals(text)) {
                return AccountIdentityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
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
                return MethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#Preset".equals(text)) {
                return PresetDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#ButtonElement".equals(text)) {
                return ButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#ShareElement".equals(text)) {
                return ShareElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.ChromeTemplateInterface.v1_0#ChromeTemplateShardElement".equals(text)) {
                return ChromeTemplateShardElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v1_0#LatestItemElement".equals(text)) {
                return LatestItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#CloseButtonElement".equals(text)) {
                return CloseButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#FollowElement".equals(text)) {
                return FollowElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#AccentOutlineBadgeElement".equals(text)) {
                return AccentOutlineBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#UnborderedBadgeElement".equals(text)) {
                return UnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#LightUnborderedBadgeElement".equals(text)) {
                return LightUnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#BadgeElement".equals(text)) {
                return BadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#PodcastPlayElement".equals(text)) {
                return PodcastPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#AboutElement".equals(text)) {
                return AboutElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#HeaderElement".equals(text)) {
                return HeaderElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#EpisodeDescriptiveSeeMoreItemElement".equals(text)) {
                return EpisodeDescriptiveSeeMoreItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowItemsInterface.v1_0#ButtonElement".equals(text)) {
                return Podcast.Touch.EpisodeRowItemsInterface.v1_0.ButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#BarkerItemElement".equals(text)) {
                return BarkerItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VerticalGridTemplateInterface.v1_0#VerticalItemElement".equals(text)) {
                return VerticalItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0#EpisodePlayElement".equals(text)) {
                return EpisodePlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#CompactPillNavigator".equals(text)) {
                return CompactPillNavigatorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#FeaturedPinnedElement".equals(text)) {
                return FeaturedPinnedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0#ThumbnailNavigatorItemElement".equals(text)) {
                return ThumbnailNavigatorItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowItemsInterface.v1_0#EpisodeRowItemElement".equals(text)) {
                return EpisodeRowItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.RefinementOptionsInterface.v1_0#CheckBoxRefinementOptionElement".equals(text)) {
                return CheckBoxRefinementOptionElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#FeaturedPinnedElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FeaturedPinnedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#SaveButtonElement".equals(text)) {
                return SaveButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#AccentOutlineBadgeElement".equals(text)) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.AccentOutlineBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#BorderedBadgeElement".equals(text)) {
                return BorderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#LightUnborderedBadgeElement".equals(text)) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.LightUnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#UnborderedBadgeElement".equals(text)) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.UnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#BadgeElement".equals(text)) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.BadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.RefinementOptionsInterface.v1_0#CheckBoxRefinementOptionItemElement".equals(text)) {
                return CheckBoxRefinementOptionItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#ReportElement".equals(text)) {
                return ReportElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.LatestTemplateInterface.v1_0#LatestItemElement".equals(text)) {
                return Podcast.Desktop.LatestTemplateInterface.v1_0.LatestItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#RefinementBarElement".equals(text)) {
                return RefinementBarElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodeHorizontalSeeMoreItemElement".equals(text)) {
                return EpisodeHorizontalSeeMoreItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#BarkerItemElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.BarkerItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeGroupItemsInterface.v1_0#HeaderRowItemElement".equals(text)) {
                return HeaderRowItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#FollowButtonElement".equals(text)) {
                return FollowButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PositiveButtonElement".equals(text)) {
                return PositiveButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#GalleryHeaderElement".equals(text)) {
                return GalleryHeaderElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowItemsInterface.v1_0#CompletedElement".equals(text)) {
                return CompletedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodesTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Desktop.EpisodesTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#FollowElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FollowElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VerticalSeeMoreItemElementInterface.v1_0#VerticalSeeMoreItemElement".equals(text)) {
                return VerticalSeeMoreItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#ShareElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShareElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#ShareElement".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.ShareElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#PillItemElement".equals(text)) {
                return PillItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0#PodcastPlayElement".equals(text)) {
                return Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#VerticalItemElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PillItemElementInterface.v1_0#PillItemElement".equals(text)) {
                return Podcast.Touch.PillItemElementInterface.v1_0.PillItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetsInterface.v1_0#DescriptiveShowcaseElement".equals(text)) {
                return DescriptiveShowcaseElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetsInterface.v1_0#VisualShovelerCompactElement".equals(text)) {
                return VisualShovelerCompactElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetsInterface.v1_0#WidgetElement".equals(text)) {
                return WidgetElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#DownloadElement".equals(text)) {
                return DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#VerticalSeeMoreItemElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.VerticalSeeMoreItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#CardElement".equals(text)) {
                return CardElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#SaveElement".equals(text)) {
                return SaveElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#RefinementBarElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementBarElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#ShareElement".equals(text)) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShareElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastRowTemplateInterface.v1_0#FollowElement".equals(text)) {
                return Podcast.Desktop.PodcastRowTemplateInterface.v1_0.FollowElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.Certification.ListTemplateInterface.v1_0#ListItemElement".equals(text)) {
                return ListItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VisualRowTemplateInterface.v1_0#VisualRowItemElement".equals(text)) {
                return VisualRowItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#SaveElement".equals(text)) {
                return Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.SaveElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.MediaItemsInterface.v1_0#MediaItemElement".equals(text)) {
                return MediaItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VisualShovelerCompactElementInterface.v1_0#VisualShovelerSeeMoreItemElement".equals(text)) {
                return VisualShovelerSeeMoreItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodeHorizontalItemElement".equals(text)) {
                return EpisodeHorizontalItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#ShareElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ShareElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#RefinementBarElement".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementBarElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#JumpBackInSeeMoreElement".equals(text)) {
                return JumpBackInSeeMoreElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#DownloadElement".equals(text)) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#HeaderElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.HeaderElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#ButtonElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BookmarkElementInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactPillNavigatorElementInterface.v1_0#CompactPillNavigator".equals(text)) {
                return Podcast.Touch.CompactPillNavigatorElementInterface.v1_0.CompactPillNavigatorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#FeaturedPinnedElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedPinnedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v1_0#DownloadElement".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v1_0.DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#HeaderElement".equals(text)) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.HeaderElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonCloseButtonElement".equals(text)) {
                return RibbonCloseButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#ButtonElement".equals(text)) {
                return Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.ButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#ShareElement".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ShareElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#EpisodePlayElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodePlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#FollowElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FollowElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#HeaderElement".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.HeaderElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowItemsInterface.v1_0#DownloadElement".equals(text)) {
                return Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#VerticalItemElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.VerticalItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#DownloadElement".equals(text)) {
                return Podcast.EpisodeOptionsInterface.v1_0.DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VerticalGridTemplateInterface.v1_0#ButtonElement".equals(text)) {
                return Podcast.Touch.VerticalGridTemplateInterface.v1_0.ButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#AboutElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.AboutElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#PodcastDetailsBookmarkElement".equals(text)) {
                return PodcastDetailsBookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#TrailingButtonElement".equals(text)) {
                return TrailingButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#FollowElement".equals(text)) {
                return Podcast.EpisodeOptionsInterface.v1_0.FollowElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonPrimaryButtonElement".equals(text)) {
                return RibbonPrimaryButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#DownloadElement".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#SaveElement".equals(text)) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.SaveElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#AboutElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AboutElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonNotificationElement".equals(text)) {
                return RibbonNotificationElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.NotificationElementInterface.v1_0#NotificationElement".equals(text)) {
                return NotificationElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#PillItemElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.PillItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonSecondaryButtonElement".equals(text)) {
                return RibbonSecondaryButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v1_0#FeaturedPinnedElement".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedPinnedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#HeaderElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.HeaderElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0#FeaturedBarkerItemElement".equals(text)) {
                return FeaturedBarkerItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#DetailTemplateShardsElement".equals(text)) {
                return DetailTemplateShardsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastRowTemplateInterface.v1_0#ButtonElement".equals(text)) {
                return Podcast.Desktop.PodcastRowTemplateInterface.v1_0.ButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#FeaturedPinnedElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FeaturedPinnedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#VerticalSeeMoreItemElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalSeeMoreItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.SaveInterface.v1_0#RefinementBarElement".equals(text)) {
                return Podcast.SaveInterface.v1_0.RefinementBarElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#BorderedBadgeElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.BorderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#UnborderedBadgeElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.UnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#LightUnborderedBadgeElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.LightUnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#AccentOutlineBadgeElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AccentOutlineBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#BadgeElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.BadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#HorizontalItemElement".equals(text)) {
                return HorizontalItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#TrailerElement".equals(text)) {
                return TrailerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.RefinementOptionsInterface.v1_0#ToggleRefinementOptionElement".equals(text)) {
                return ToggleRefinementOptionElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#ButtonElement".equals(text)) {
                return Podcast.Touch.HorizontalRowTemplateInterface.v1_0.ButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.AlertTemplateInterface.v1_0#PositiveButtonElement".equals(text)) {
                return Podcast.Desktop.AlertTemplateInterface.v1_0.PositiveButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowItemsInterface.v1_0#UnborderedBadgeElement".equals(text)) {
                return Podcast.Touch.EpisodeRowItemsInterface.v1_0.UnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowItemsInterface.v1_0#BorderedBadgeElement".equals(text)) {
                return Podcast.Touch.EpisodeRowItemsInterface.v1_0.BorderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowItemsInterface.v1_0#AccentOutlineBadgeElement".equals(text)) {
                return Podcast.Touch.EpisodeRowItemsInterface.v1_0.AccentOutlineBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowItemsInterface.v1_0#LightUnborderedBadgeElement".equals(text)) {
                return Podcast.Touch.EpisodeRowItemsInterface.v1_0.LightUnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowItemsInterface.v1_0#BadgeElement".equals(text)) {
                return Podcast.Touch.EpisodeRowItemsInterface.v1_0.BadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#EpisodeOptionsElement".equals(text)) {
                return EpisodeOptionsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#FooterElement".equals(text)) {
                return FooterElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#ShareElement".equals(text)) {
                return Podcast.EpisodeOptionsInterface.v1_0.ShareElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v2_0#TrailerElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v2_0.TrailerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#FollowsElement".equals(text)) {
                return FollowsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#VisualShovelerCompactElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v1_0.VisualShovelerCompactElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#ExperienceVisualShovelerElement".equals(text)) {
                return ExperienceVisualShovelerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#JumpBackInElement".equals(text)) {
                return JumpBackInElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#PodcastFeaturedPlayElement".equals(text)) {
                return PodcastFeaturedPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#FeatureBarkerElement".equals(text)) {
                return FeatureBarkerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#ThumbnailNavigationWidgetElement".equals(text)) {
                return ThumbnailNavigationWidgetElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#SavesElement".equals(text)) {
                return SavesElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#EpisodeFeaturedPlayElement".equals(text)) {
                return EpisodeFeaturedPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#BookmarksElement".equals(text)) {
                return BookmarksElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#PillListingNavigatorElement".equals(text)) {
                return PillListingNavigatorElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#EpisodeDescriptiveShovelerElement".equals(text)) {
                return EpisodeDescriptiveShovelerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#RegularBarkerElement".equals(text)) {
                return RegularBarkerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#WidgetElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.DownloadInterface.v1_0#RefinementBarElement".equals(text)) {
                return Podcast.DownloadInterface.v1_0.RefinementBarElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#FollowElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FollowElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#RefinementBarElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementBarElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PlayButtonElement".equals(text)) {
                return PlayButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#DownloadElement".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.LatestTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Desktop.LatestTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalItemElementInterface.v1_0#HorizontalItemElement".equals(text)) {
                return Podcast.Touch.HorizontalItemElementInterface.v1_0.HorizontalItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CommonButtonElementInterface.v1_0#PrimaryButtonElement".equals(text)) {
                return PrimaryButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#ShareElement".equals(text)) {
                return Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.ShareElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0#EpisodeDescriptiveSeeMoreItemElement".equals(text)) {
                return Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveSeeMoreItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#CompactPillItemElement".equals(text)) {
                return CompactPillItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeRowItemsInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Touch.EpisodeRowItemsInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#CompletedElement".equals(text)) {
                return Podcast.EpisodeOptionsInterface.v1_0.CompletedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#AboutElement".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.AboutElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodePlayElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodePlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#RefinementBarElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.RefinementBarElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodesTemplateInterface.v1_0#EpisodeRowItemElement".equals(text)) {
                return Podcast.Desktop.EpisodesTemplateInterface.v1_0.EpisodeRowItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#DownloadElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.TemplateMetadataInterface.v1_0#TemplateMetadataElement".equals(text)) {
                return TemplateMetadataElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#EpisodeItemElement".equals(text)) {
                return EpisodeItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#HeaderElement".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.HeaderElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#CompactPillItemElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.CompactPillItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VisualRowTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Touch.VisualRowTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#HeaderElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.HeaderElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v2_0#FeaturedPinnedElement".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedPinnedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeGroupItemsInterface.v1_0#EpisodeGroupItemElement".equals(text)) {
                return EpisodeGroupItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.RibbonNotificationElementInterface.v1_0#TextWithLinkElement".equals(text)) {
                return TextWithLinkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#FollowElement".equals(text)) {
                return Podcast.Touch.HorizontalRowTemplateInterface.v1_0.FollowElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#CompactPillNavigator".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillNavigatorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#BorderedBadgeElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BorderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#LightUnborderedBadgeElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.LightUnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#UnborderedBadgeElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.UnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#AccentOutlineBadgeElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AccentOutlineBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#BadgeElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#AboutElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v1_0.AboutElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#FooterButtonElement".equals(text)) {
                return FooterButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#ButtonElement".equals(text)) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.ButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#PodcastPlayElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.PodcastPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PillItemElement".equals(text)) {
                return Podcast.Touch.PTCTemplateInterface.v1_0.PillItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VerticalGridTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Touch.VerticalGridTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonMessageElement".equals(text)) {
                return RibbonMessageElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#FeaturedPinnedElement".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedPinnedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowPromptInterface.v1_0#HidePromptPreferenceElement".equals(text)) {
                return HidePromptPreferenceElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#TrailerElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v1_0.TrailerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#GridItemElement".equals(text)) {
                return GridItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.JumpBackInInterface.v1_0#JumpBackInCacheItemElement".equals(text)) {
                return JumpBackInCacheItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCTemplateShardsElement".equals(text)) {
                return PTCTemplateShardsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#LeadingButtonElement".equals(text)) {
                return LeadingButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.AlertTemplateInterface.v1_0#NegativeButtonElement".equals(text)) {
                return NegativeButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#FeaturedSingleItemElement".equals(text)) {
                return FeaturedSingleItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#FollowElement".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.FollowElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#PlayEpisodeElement".equals(text)) {
                return PlayEpisodeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodeFeaturedPlayElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeFeaturedPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#SavesElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.SavesElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#RegularBarkerElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.RegularBarkerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#FeatureBarkerElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.FeatureBarkerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#VisualListingElement".equals(text)) {
                return VisualListingElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#FollowsElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodeDescriptiveShovelerElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#VisualShovelerCompactElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.VisualShovelerCompactElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#PodcastFeaturedPlayElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#JumpBackInElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#BookmarksElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.BookmarksElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#PillListingNavigatorElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.PillListingNavigatorElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#WidgetElement".equals(text)) {
                return Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#AboutElement".equals(text)) {
                return Podcast.Touch.PTCTemplateInterface.v1_0.AboutElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#FollowsSeeMoreElement".equals(text)) {
                return FollowsSeeMoreElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#EpisodeRowItemElement".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.EpisodeRowItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#AboutElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.AboutElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastRowTemplateInterface.v1_0#PodcastRowItemElement".equals(text)) {
                return PodcastRowItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#DetailTemplateShardElement".equals(text)) {
                return DetailTemplateShardElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.AlertTemplateInterface.v1_0#NegativeButtonElement".equals(text)) {
                return Podcast.Touch.AlertTemplateInterface.v1_0.NegativeButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v2_0#GalleryHeaderElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryHeaderElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#NegativeButtonElement".equals(text)) {
                return Podcast.Touch.PTCTemplateInterface.v1_0.NegativeButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.AlertTemplateInterface.v1_0#PositiveButtonElement".equals(text)) {
                return Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.LatestTemplateInterface.v1_0#DownloadElement".equals(text)) {
                return Podcast.Desktop.LatestTemplateInterface.v1_0.DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BadgeElementInterface.v1_0#BorderedBadgeElement".equals(text)) {
                return Podcast.Touch.BadgeElementInterface.v1_0.BorderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BadgeElementInterface.v1_0#UnborderedBadgeElement".equals(text)) {
                return Podcast.Touch.BadgeElementInterface.v1_0.UnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BadgeElementInterface.v1_0#AccentOutlineBadgeElement".equals(text)) {
                return Podcast.Touch.BadgeElementInterface.v1_0.AccentOutlineBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BadgeElementInterface.v1_0#LightUnborderedBadgeElement".equals(text)) {
                return Podcast.Touch.BadgeElementInterface.v1_0.LightUnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BadgeElementInterface.v1_0#BadgeElement".equals(text)) {
                return Podcast.Touch.BadgeElementInterface.v1_0.BadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#GoToPodcastElement".equals(text)) {
                return GoToPodcastElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#ListItemElement".equals(text)) {
                return Podcast.Touch.PTCTemplateInterface.v1_0.ListItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v2_0#AboutElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v2_0.AboutElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#FeaturedElement".equals(text)) {
                return FeaturedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.HorizontalItemElementInterface.v1_0#FollowElement".equals(text)) {
                return Podcast.Touch.HorizontalItemElementInterface.v1_0.FollowElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.CompactPillItemElementInterface.v1_0#CompactPillItemElement".equals(text)) {
                return Podcast.Touch.CompactPillItemElementInterface.v1_0.CompactPillItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VisualRowTemplateInterface.v1_0#DownloadElement".equals(text)) {
                return Podcast.Touch.VisualRowTemplateInterface.v1_0.DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#AboutElement".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.AboutElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.LatestTemplateInterface.v1_0#FeaturedPinnedElement".equals(text)) {
                return Podcast.Desktop.LatestTemplateInterface.v1_0.FeaturedPinnedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VerticalItemElementInterface.v1_0#VerticalItemElement".equals(text)) {
                return Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#PodcastShowCarPresetElement".equals(text)) {
                return PodcastShowCarPresetElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.EpisodeOptionsInterface.v1_0#CarPresetElement".equals(text)) {
                return CarPresetElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BarkerItemElementInterface.v1_0#BarkerItemElement".equals(text)) {
                return Podcast.Touch.BarkerItemElementInterface.v1_0.BarkerItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.EpisodesTemplateInterface.v1_0#DownloadElement".equals(text)) {
                return Podcast.Desktop.EpisodesTemplateInterface.v1_0.DownloadElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#CompactPillItemElement".equals(text)) {
                return Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillItemElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#BookmarkElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.BookmarkElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#SavesElement".equals(text)) {
                return Podcast.Touch.WidgetElementsInterface.v1_0.SavesElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0#PodcastFeaturedPlayElement".equals(text)) {
                return Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastFeaturedPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#BookmarksElement".equals(text)) {
                return Podcast.Touch.WidgetElementsInterface.v1_0.BookmarksElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0#ThumbnailNavigatorWidgetElement".equals(text)) {
                return ThumbnailNavigatorWidgetElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.FeaturedBarkerElementInterface.v1_0#FeaturedBarkerElement".equals(text)) {
                return FeaturedBarkerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DescriptiveShowcaseElement.v1_0#DescriptiveShowcaseElement".equals(text)) {
                return Podcast.Touch.DescriptiveShowcaseElement.v1_0.DescriptiveShowcaseElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#JumpBackInElement".equals(text)) {
                return Podcast.Touch.WidgetElementsInterface.v1_0.JumpBackInElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0#EpisodeFeaturedPlayElement".equals(text)) {
                return Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0#ExperienceVisualShovelerElement".equals(text)) {
                return Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#FollowsElement".equals(text)) {
                return Podcast.Touch.WidgetElementsInterface.v1_0.FollowsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PillListingNavigatorElementInterface.v1_0#PillListingNavigatorElement".equals(text)) {
                return Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0#EpisodeDescriptiveShovelerElement".equals(text)) {
                return Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.RegularBarkerElementInterface.v1_0#RegularBarkerElement".equals(text)) {
                return Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.VisualShovelerCompactElementInterface.v1_0#VisualShovelerCompactElement".equals(text)) {
                return Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.WidgetElementsInterface.v1_0#WidgetElement".equals(text)) {
                return Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOATemplateListInterface.v1_0#TemplateElement".equals(text)) {
                return TemplateElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CompletedInterface.v1_0#CompletedOperationsClientState".equals(text)) {
                return CompletedOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.DownloadInterface.v1_0#DownloadOperationsClientState".equals(text)) {
                return DownloadOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#FollowsLastSyncTimeClientState".equals(text)) {
                return FollowsLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#LocalEpisodeAlreadyPlayingClientState".equals(text)) {
                return LocalEpisodeAlreadyPlayingClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.SaveInterface.v1_0#SaveOperationsClientState".equals(text)) {
                return SaveOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#FollowOperationsClientState".equals(text)) {
                return FollowOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCPreferencesClientState".equals(text)) {
                return PTCPreferencesClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CompletedInterface.v1_0#CompletedLastSyncTimeClientState".equals(text)) {
                return CompletedLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BiteBookmarkInterface.v1_0#BiteBookmarksLastSyncTimeClientState".equals(text)) {
                return BiteBookmarksLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BookmarkInterface.v1_0#BookmarksLastSyncTimeClientState".equals(text)) {
                return BookmarksLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CategoryFollowInterface.v1_0#CategoryFollowsLastSyncTimeClientState".equals(text)) {
                return CategoryFollowsLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BookmarkInterface.v1_0#BookmarkOperationsClientState".equals(text)) {
                return BookmarkOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.DeeplinkInterface.v1_0#DeeplinkClientState".equals(text)) {
                return DeeplinkClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowPromptInterface.v1_0#HidePromptPreferenceClientState".equals(text)) {
                return HidePromptPreferenceClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCSelectionsClientState".equals(text)) {
                return PTCSelectionsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.UIMetricsInterface.v1_0#UIMetricsOperationsClientState".equals(text)) {
                return UIMetricsOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#RefinementOptionsClientState".equals(text)) {
                return RefinementOptionsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CategoryFollowInterface.v1_0#CategoryFollowOperationsClientState".equals(text)) {
                return CategoryFollowOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackMetricsInterface.v1_0#PlaybackMetricsOperationsClientState".equals(text)) {
                return PlaybackMetricsOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#RefinementOptionsClientState".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.SaveInterface.v1_0#SavesLastSyncTimeClientState".equals(text)) {
                return SavesLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BiteBookmarkInterface.v1_0#BiteBookmarkOperationsClientState".equals(text)) {
                return BiteBookmarkOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#RefinementOptionsClientState".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.RefinementOptionsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#RefinementOptionsClientState".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#ClientState".equals(text)) {
                return ClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#DeviceIdentity".equals(text)) {
                return DeviceIdentityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#ApplicationIdentity".equals(text)) {
                return ApplicationIdentityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#MultiThreadedQueue".equals(text)) {
                return MultiThreadedQueueDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#SingleThreadedQueue".equals(text)) {
                return SingleThreadedQueueDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#Queue".equals(text)) {
                return QueueDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#FeaturedItemWriteCacheElement".equals(text)) {
                return FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.LatestTemplateInterface.v1_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Desktop.LatestTemplateInterface.v1_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.JumpBackInInterface.v1_0#JumpBackInWriteCacheElement".equals(text)) {
                return JumpBackInWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v2_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v1_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCWriteCacheElement".equals(text)) {
                return PTCWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACacheInterface.v1_0#WriteCacheElement".equals(text)) {
                return WriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#RequestIdentity".equals(text)) {
                return RequestIdentityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#SOAObject".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SOAObject deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
