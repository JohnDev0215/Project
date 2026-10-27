package SOATemplateListInterface.v1_0;

import Podcast.Desktop.AlertTemplateInterface.v1_0.NegativeButtonElementDeserializer;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.DownloadElementDeserializer;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.HeaderElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.CompactPillNavigatorDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalItemElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalSeeMoreItemElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsSeeMoreElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInSeeMoreElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.TrailerElementDeserializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.VisualListingElementDeserializer;
import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FeaturedPinnedElementDeserializer;
import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FollowElementDeserializer;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowItemElementDeserializer;
import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.CarPresetElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.GoToPodcastElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.PlayEpisodeElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.PodcastShowCarPresetElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.ReportElementDeserializer;
import Podcast.EpisodeOptionsInterface.v1_0.SaveElementDeserializer;
import Podcast.FollowInterface.v1_0.FooterElementDeserializer;
import Podcast.FollowInterface.v1_0.RefinementBarElementDeserializer;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceElementDeserializer;
import Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElementDeserializer;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElementDeserializer;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElementDeserializer;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElementDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardElementDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.FollowButtonElementDeserializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.SaveButtonElementDeserializer;
import Podcast.Touch.Certification.ListTemplateInterface.v1_0.ListItemElementDeserializer;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateShardElementDeserializer;
import Podcast.Touch.CommonButtonElementInterface.v1_0.PrimaryButtonElementDeserializer;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BorderedBadgeElementDeserializer;
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
import Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElementDeserializer;
import Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0.FeaturedBarkerItemElementDeserializer;
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
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.BadgeElementDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElementDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.LightUnborderedBadgeElementDeserializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.UnborderedBadgeElementDeserializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.LatestItemElementDeserializer;
import Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElementDeserializer;
import Podcast.Touch.NotificationElementInterface.v1_0.NotificationElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.CloseButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.CompactPillItemElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.FooterButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.LeadingButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateShardsElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PlayButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PositiveButtonElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.TrailingButtonElementDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.EpisodeItemElementDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AboutElementDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShareElementDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ButtonElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonCloseButtonElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonMessageElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonPrimaryButtonElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonSecondaryButtonElementDeserializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.TextWithLinkElementDeserializer;
import Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElementDeserializer;
import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElementDeserializer;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalItemElementDeserializer;
import Podcast.Touch.VerticalSeeMoreItemElementInterface.v1_0.VerticalSeeMoreItemElementDeserializer;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowItemElementDeserializer;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerSeeMoreItemElementDeserializer;
import Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElementDeserializer;
import Podcast.Touch.WidgetsInterface.v1_0.VisualShovelerCompactElementDeserializer;
import Podcast.Touch.WidgetsInterface.v1_0.WidgetElementDeserializer;
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
public class TemplateElementDeserializer extends JsonDeserializer<TemplateElement> {
    public static final TemplateElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TemplateElementDeserializer templateElementDeserializer = new TemplateElementDeserializer();
        INSTANCE = templateElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.TemplateElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(TemplateElement.class, templateElementDeserializer);
    }

    private TemplateElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public TemplateElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public TemplateElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
