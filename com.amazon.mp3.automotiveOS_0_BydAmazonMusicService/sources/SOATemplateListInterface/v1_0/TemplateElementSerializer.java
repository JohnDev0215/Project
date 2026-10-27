package SOATemplateListInterface.v1_0;

import Podcast.Desktop.AlertTemplateInterface.v1_0.NegativeButtonElement;
import Podcast.Desktop.AlertTemplateInterface.v1_0.NegativeButtonElementSerializer;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.DownloadElement;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.DownloadElementSerializer;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.HeaderElement;
import Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.HeaderElementSerializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.CompactPillNavigator;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.CompactPillNavigatorSerializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalItemElement;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalItemElementSerializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalSeeMoreItemElement;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalSeeMoreItemElementSerializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsSeeMoreElement;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsSeeMoreElementSerializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInSeeMoreElement;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInSeeMoreElementSerializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.TrailerElement;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.TrailerElementSerializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.VisualListingElement;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.VisualListingElementSerializer;
import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FeaturedPinnedElement;
import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FeaturedPinnedElementSerializer;
import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FollowElement;
import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FollowElementSerializer;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowItemElement;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowItemElementSerializer;
import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElement;
import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElementSerializer;
import Podcast.EpisodeOptionsInterface.v1_0.CarPresetElement;
import Podcast.EpisodeOptionsInterface.v1_0.CarPresetElementSerializer;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementSerializer;
import Podcast.EpisodeOptionsInterface.v1_0.GoToPodcastElement;
import Podcast.EpisodeOptionsInterface.v1_0.GoToPodcastElementSerializer;
import Podcast.EpisodeOptionsInterface.v1_0.PlayEpisodeElement;
import Podcast.EpisodeOptionsInterface.v1_0.PlayEpisodeElementSerializer;
import Podcast.EpisodeOptionsInterface.v1_0.PodcastShowCarPresetElement;
import Podcast.EpisodeOptionsInterface.v1_0.PodcastShowCarPresetElementSerializer;
import Podcast.EpisodeOptionsInterface.v1_0.ReportElement;
import Podcast.EpisodeOptionsInterface.v1_0.ReportElementSerializer;
import Podcast.EpisodeOptionsInterface.v1_0.SaveElement;
import Podcast.EpisodeOptionsInterface.v1_0.SaveElementSerializer;
import Podcast.FollowInterface.v1_0.FooterElement;
import Podcast.FollowInterface.v1_0.FooterElementSerializer;
import Podcast.FollowInterface.v1_0.RefinementBarElement;
import Podcast.FollowInterface.v1_0.RefinementBarElementSerializer;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceElement;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceElementSerializer;
import Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElement;
import Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElementSerializer;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElementSerializer;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElement;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElementSerializer;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElementSerializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardElement;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardElementSerializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.FollowButtonElement;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.FollowButtonElementSerializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.SaveButtonElement;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.SaveButtonElementSerializer;
import Podcast.Touch.Certification.ListTemplateInterface.v1_0.ListItemElement;
import Podcast.Touch.Certification.ListTemplateInterface.v1_0.ListItemElementSerializer;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateShardElement;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateShardElementSerializer;
import Podcast.Touch.CommonButtonElementInterface.v1_0.PrimaryButtonElement;
import Podcast.Touch.CommonButtonElementInterface.v1_0.PrimaryButtonElementSerializer;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BorderedBadgeElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BorderedBadgeElementSerializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateShardElement;
import Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateShardElementSerializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateShardsElement;
import Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateShardsElementSerializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedElement;
import Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedElementSerializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedSingleItemElement;
import Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedSingleItemElementSerializer;
import Podcast.Touch.DetailTemplateInterface.v1_0.PodcastDetailsBookmarkElement;
import Podcast.Touch.DetailTemplateInterface.v1_0.PodcastDetailsBookmarkElementSerializer;
import Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodePlayElement;
import Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodePlayElementSerializer;
import Podcast.Touch.EpisodeGroupItemsInterface.v1_0.EpisodeGroupItemElement;
import Podcast.Touch.EpisodeGroupItemsInterface.v1_0.EpisodeGroupItemElementSerializer;
import Podcast.Touch.EpisodeGroupItemsInterface.v1_0.HeaderRowItemElement;
import Podcast.Touch.EpisodeGroupItemsInterface.v1_0.HeaderRowItemElementSerializer;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElementSerializer;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementSerializer;
import Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElement;
import Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElementSerializer;
import Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0.FeaturedBarkerItemElement;
import Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0.FeaturedBarkerItemElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.BarkerItemElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.BarkerItemElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.BookmarkElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.BookmarkElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.BookmarksElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.BookmarksElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveSeeMoreItemElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveSeeMoreItemElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeFeaturedPlayElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeFeaturedPlayElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.ExperienceVisualShovelerElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.ExperienceVisualShovelerElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.FeatureBarkerElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.FeatureBarkerElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.FollowsElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.FollowsElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryHeaderElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryHeaderElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.JumpBackInElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.JumpBackInElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PillItemElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PillItemElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PillListingNavigatorElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PillListingNavigatorElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastPlayElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastPlayElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.RegularBarkerElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.RegularBarkerElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.SavesElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.SavesElementSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.ThumbnailNavigationWidgetElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.ThumbnailNavigationWidgetElementSerializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AccentOutlineBadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AccentOutlineBadgeElementSerializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.BadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.BadgeElementSerializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElementSerializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.LightUnborderedBadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.LightUnborderedBadgeElementSerializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.UnborderedBadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.UnborderedBadgeElementSerializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.LatestItemElement;
import Podcast.Touch.LatestTemplateInterface.v1_0.LatestItemElementSerializer;
import Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElement;
import Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElementSerializer;
import Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement;
import Podcast.Touch.NotificationElementInterface.v1_0.NotificationElementSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.CloseButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.CloseButtonElementSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.CompactPillItemElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.CompactPillItemElementSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.FooterButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.FooterButtonElementSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElementSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.LeadingButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.LeadingButtonElementSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateShardsElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateShardsElementSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PlayButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PlayButtonElementSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PositiveButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PositiveButtonElementSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.TrailingButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.TrailingButtonElementSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.EpisodeItemElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.EpisodeItemElementSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AboutElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AboutElementSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShareElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShareElementSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ButtonElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ButtonElementSerializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonCloseButtonElement;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonCloseButtonElementSerializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonMessageElement;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonMessageElementSerializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElement;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElementSerializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonPrimaryButtonElement;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonPrimaryButtonElementSerializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonSecondaryButtonElement;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonSecondaryButtonElementSerializer;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.TextWithLinkElement;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.TextWithLinkElementSerializer;
import Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElement;
import Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElementSerializer;
import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElement;
import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElementSerializer;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalItemElement;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalItemElementSerializer;
import Podcast.Touch.VerticalSeeMoreItemElementInterface.v1_0.VerticalSeeMoreItemElement;
import Podcast.Touch.VerticalSeeMoreItemElementInterface.v1_0.VerticalSeeMoreItemElementSerializer;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowItemElement;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowItemElementSerializer;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerSeeMoreItemElement;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerSeeMoreItemElementSerializer;
import Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement;
import Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElementSerializer;
import Podcast.Touch.WidgetsInterface.v1_0.VisualShovelerCompactElement;
import Podcast.Touch.WidgetsInterface.v1_0.VisualShovelerCompactElementSerializer;
import Podcast.Touch.WidgetsInterface.v1_0.WidgetElement;
import Podcast.Touch.WidgetsInterface.v1_0.WidgetElementSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class TemplateElementSerializer extends JsonSerializer<TemplateElement> {
    public static final TemplateElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TemplateElementSerializer templateElementSerializer = new TemplateElementSerializer();
        INSTANCE = templateElementSerializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.TemplateElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(TemplateElement.class, templateElementSerializer);
    }

    private TemplateElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull TemplateElement templateElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (templateElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (templateElement instanceof ButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#ButtonElement");
            ButtonElementSerializer.INSTANCE.serializeFields((ButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof ShareElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#ShareElement");
            ShareElementSerializer.INSTANCE.serializeFields((ShareElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof ChromeTemplateShardElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.ChromeTemplateInterface.v1_0#ChromeTemplateShardElement");
            ChromeTemplateShardElementSerializer.INSTANCE.serializeFields((ChromeTemplateShardElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#BookmarkElement");
            BookmarkElementSerializer.INSTANCE.serializeFields((BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof LatestItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v1_0#LatestItemElement");
            LatestItemElementSerializer.INSTANCE.serializeFields((LatestItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof CloseButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#CloseButtonElement");
            CloseButtonElementSerializer.INSTANCE.serializeFields((CloseButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FollowElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#FollowElement");
            FollowElementSerializer.INSTANCE.serializeFields((FollowElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof AccentOutlineBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#AccentOutlineBadgeElement");
            AccentOutlineBadgeElementSerializer.INSTANCE.serializeFields((AccentOutlineBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof UnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#UnborderedBadgeElement");
            UnborderedBadgeElementSerializer.INSTANCE.serializeFields((UnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof LightUnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#LightUnborderedBadgeElement");
            LightUnborderedBadgeElementSerializer.INSTANCE.serializeFields((LightUnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof BadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#BadgeElement");
            BadgeElementSerializer.INSTANCE.serializeFields((BadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PodcastPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#PodcastPlayElement");
            PodcastPlayElementSerializer.INSTANCE.serializeFields((PodcastPlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof AboutElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#AboutElement");
            AboutElementSerializer.INSTANCE.serializeFields((AboutElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof HeaderElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#HeaderElement");
            HeaderElementSerializer.INSTANCE.serializeFields((HeaderElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof EpisodeDescriptiveSeeMoreItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#EpisodeDescriptiveSeeMoreItemElement");
            EpisodeDescriptiveSeeMoreItemElementSerializer.INSTANCE.serializeFields((EpisodeDescriptiveSeeMoreItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeRowItemsInterface.v1_0.ButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#ButtonElement");
            Podcast.Touch.EpisodeRowItemsInterface.v1_0.ButtonElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeRowItemsInterface.v1_0.ButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof BarkerItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#BarkerItemElement");
            BarkerItemElementSerializer.INSTANCE.serializeFields((BarkerItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof VerticalItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VerticalGridTemplateInterface.v1_0#VerticalItemElement");
            VerticalItemElementSerializer.INSTANCE.serializeFields((VerticalItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof EpisodePlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0#EpisodePlayElement");
            EpisodePlayElementSerializer.INSTANCE.serializeFields((EpisodePlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof CompactPillNavigator) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#CompactPillNavigator");
            CompactPillNavigatorSerializer.INSTANCE.serializeFields((CompactPillNavigator) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FeaturedPinnedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#FeaturedPinnedElement");
            FeaturedPinnedElementSerializer.INSTANCE.serializeFields((FeaturedPinnedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof ThumbnailNavigatorItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0#ThumbnailNavigatorItemElement");
            ThumbnailNavigatorItemElementSerializer.INSTANCE.serializeFields((ThumbnailNavigatorItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof EpisodeRowItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#EpisodeRowItemElement");
            EpisodeRowItemElementSerializer.INSTANCE.serializeFields((EpisodeRowItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof CheckBoxRefinementOptionElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.RefinementOptionsInterface.v1_0#CheckBoxRefinementOptionElement");
            CheckBoxRefinementOptionElementSerializer.INSTANCE.serializeFields((CheckBoxRefinementOptionElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FeaturedPinnedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#FeaturedPinnedElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FeaturedPinnedElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FeaturedPinnedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof SaveButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#SaveButtonElement");
            SaveButtonElementSerializer.INSTANCE.serializeFields((SaveButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactDetailTemplateInterface.v1_0.AccentOutlineBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#AccentOutlineBadgeElement");
            Podcast.Touch.CompactDetailTemplateInterface.v1_0.AccentOutlineBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactDetailTemplateInterface.v1_0.AccentOutlineBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof BorderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#BorderedBadgeElement");
            BorderedBadgeElementSerializer.INSTANCE.serializeFields((BorderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactDetailTemplateInterface.v1_0.LightUnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#LightUnborderedBadgeElement");
            Podcast.Touch.CompactDetailTemplateInterface.v1_0.LightUnborderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactDetailTemplateInterface.v1_0.LightUnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactDetailTemplateInterface.v1_0.UnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#UnborderedBadgeElement");
            Podcast.Touch.CompactDetailTemplateInterface.v1_0.UnborderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactDetailTemplateInterface.v1_0.UnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactDetailTemplateInterface.v1_0.BadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#BadgeElement");
            Podcast.Touch.CompactDetailTemplateInterface.v1_0.BadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactDetailTemplateInterface.v1_0.BadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof CheckBoxRefinementOptionItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.RefinementOptionsInterface.v1_0#CheckBoxRefinementOptionItemElement");
            CheckBoxRefinementOptionItemElementSerializer.INSTANCE.serializeFields((CheckBoxRefinementOptionItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof ReportElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#ReportElement");
            ReportElementSerializer.INSTANCE.serializeFields((ReportElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.LatestTemplateInterface.v1_0.LatestItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.LatestTemplateInterface.v1_0#LatestItemElement");
            Podcast.Desktop.LatestTemplateInterface.v1_0.LatestItemElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.LatestTemplateInterface.v1_0.LatestItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof RefinementBarElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowInterface.v1_0#RefinementBarElement");
            RefinementBarElementSerializer.INSTANCE.serializeFields((RefinementBarElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof EpisodeHorizontalSeeMoreItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodeHorizontalSeeMoreItemElement");
            EpisodeHorizontalSeeMoreItemElementSerializer.INSTANCE.serializeFields((EpisodeHorizontalSeeMoreItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.BarkerItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#BarkerItemElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.BarkerItemElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.BarkerItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof HeaderRowItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeGroupItemsInterface.v1_0#HeaderRowItemElement");
            HeaderRowItemElementSerializer.INSTANCE.serializeFields((HeaderRowItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FollowButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#FollowButtonElement");
            FollowButtonElementSerializer.INSTANCE.serializeFields((FollowButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PositiveButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PositiveButtonElement");
            PositiveButtonElementSerializer.INSTANCE.serializeFields((PositiveButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#BookmarkElement");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof GalleryHeaderElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#GalleryHeaderElement");
            GalleryHeaderElementSerializer.INSTANCE.serializeFields((GalleryHeaderElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof CompletedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#CompletedElement");
            CompletedElementSerializer.INSTANCE.serializeFields((CompletedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.EpisodesTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodesTemplateInterface.v1_0#BookmarkElement");
            Podcast.Desktop.EpisodesTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.EpisodesTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FollowElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#FollowElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FollowElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FollowElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof VerticalSeeMoreItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VerticalSeeMoreItemElementInterface.v1_0#VerticalSeeMoreItemElement");
            VerticalSeeMoreItemElementSerializer.INSTANCE.serializeFields((VerticalSeeMoreItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShareElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#ShareElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShareElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShareElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.DetailTemplateInterface.v1_0.ShareElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#ShareElement");
            Podcast.Touch.DetailTemplateInterface.v1_0.ShareElementSerializer.INSTANCE.serializeFields((Podcast.Touch.DetailTemplateInterface.v1_0.ShareElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PillItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#PillItemElement");
            PillItemElementSerializer.INSTANCE.serializeFields((PillItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0#PodcastPlayElement");
            Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastPlayElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastPlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#VerticalItemElement");
            Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PillItemElementInterface.v1_0.PillItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PillItemElementInterface.v1_0#PillItemElement");
            Podcast.Touch.PillItemElementInterface.v1_0.PillItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PillItemElementInterface.v1_0.PillItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof DescriptiveShowcaseElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetsInterface.v1_0#DescriptiveShowcaseElement");
            DescriptiveShowcaseElementSerializer.INSTANCE.serializeFields((DescriptiveShowcaseElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof VisualShovelerCompactElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetsInterface.v1_0#VisualShovelerCompactElement");
            VisualShovelerCompactElementSerializer.INSTANCE.serializeFields((VisualShovelerCompactElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof WidgetElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetsInterface.v1_0#WidgetElement");
            WidgetElementSerializer.INSTANCE.serializeFields((WidgetElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#DownloadElement");
            DownloadElementSerializer.INSTANCE.serializeFields((DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.VerticalSeeMoreItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#VerticalSeeMoreItemElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.VerticalSeeMoreItemElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.VerticalSeeMoreItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof CardElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#CardElement");
            CardElementSerializer.INSTANCE.serializeFields((CardElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof SaveElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#SaveElement");
            SaveElementSerializer.INSTANCE.serializeFields((SaveElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementBarElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#RefinementBarElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementBarElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementBarElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShareElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#ShareElement");
            Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShareElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShareElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.PodcastRowTemplateInterface.v1_0.FollowElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastRowTemplateInterface.v1_0#FollowElement");
            Podcast.Desktop.PodcastRowTemplateInterface.v1_0.FollowElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastRowTemplateInterface.v1_0.FollowElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof ListItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.Certification.ListTemplateInterface.v1_0#ListItemElement");
            ListItemElementSerializer.INSTANCE.serializeFields((ListItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof VisualRowItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VisualRowTemplateInterface.v1_0#VisualRowItemElement");
            VisualRowItemElementSerializer.INSTANCE.serializeFields((VisualRowItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.SaveElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#SaveElement");
            Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.SaveElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.SaveElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof MediaItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.MediaItemsInterface.v1_0#MediaItemElement");
            MediaItemElementSerializer.INSTANCE.serializeFields((MediaItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof VisualShovelerSeeMoreItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VisualShovelerCompactElementInterface.v1_0#VisualShovelerSeeMoreItemElement");
            VisualShovelerSeeMoreItemElementSerializer.INSTANCE.serializeFields((VisualShovelerSeeMoreItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof EpisodeHorizontalItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodeHorizontalItemElement");
            EpisodeHorizontalItemElementSerializer.INSTANCE.serializeFields((EpisodeHorizontalItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ShareElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#ShareElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ShareElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ShareElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementBarElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#RefinementBarElement");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementBarElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementBarElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof JumpBackInSeeMoreElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#JumpBackInSeeMoreElement");
            JumpBackInSeeMoreElementSerializer.INSTANCE.serializeFields((JumpBackInSeeMoreElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactDetailTemplateInterface.v1_0.DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#DownloadElement");
            Podcast.Touch.CompactDetailTemplateInterface.v1_0.DownloadElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactDetailTemplateInterface.v1_0.DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.HeaderElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#HeaderElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.HeaderElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.HeaderElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#ButtonElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ButtonElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.BookmarkElementInterface.v1_0#BookmarkElement");
            Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactPillNavigatorElementInterface.v1_0.CompactPillNavigator) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactPillNavigatorElementInterface.v1_0#CompactPillNavigator");
            Podcast.Touch.CompactPillNavigatorElementInterface.v1_0.CompactPillNavigatorSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactPillNavigatorElementInterface.v1_0.CompactPillNavigator) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedPinnedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#FeaturedPinnedElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedPinnedElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedPinnedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.LatestTemplateInterface.v1_0.DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v1_0#DownloadElement");
            Podcast.Touch.LatestTemplateInterface.v1_0.DownloadElementSerializer.INSTANCE.serializeFields((Podcast.Touch.LatestTemplateInterface.v1_0.DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#BookmarkElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactDetailTemplateInterface.v1_0.HeaderElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#HeaderElement");
            Podcast.Touch.CompactDetailTemplateInterface.v1_0.HeaderElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactDetailTemplateInterface.v1_0.HeaderElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof RibbonCloseButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonCloseButtonElement");
            RibbonCloseButtonElementSerializer.INSTANCE.serializeFields((RibbonCloseButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.ButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#ButtonElement");
            Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.ButtonElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.ButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ShareElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#ShareElement");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ShareElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ShareElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodePlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#EpisodePlayElement");
            Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodePlayElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodePlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FollowElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#FollowElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FollowElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FollowElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.HeaderElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#HeaderElement");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.HeaderElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.HeaderElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#DownloadElement");
            Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#BookmarkElement");
            Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.VerticalItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#VerticalItemElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.VerticalItemElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.VerticalItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.EpisodeOptionsInterface.v1_0.DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#DownloadElement");
            Podcast.EpisodeOptionsInterface.v1_0.DownloadElementSerializer.INSTANCE.serializeFields((Podcast.EpisodeOptionsInterface.v1_0.DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.VerticalGridTemplateInterface.v1_0.ButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VerticalGridTemplateInterface.v1_0#ButtonElement");
            Podcast.Touch.VerticalGridTemplateInterface.v1_0.ButtonElementSerializer.INSTANCE.serializeFields((Podcast.Touch.VerticalGridTemplateInterface.v1_0.ButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.AboutElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#AboutElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.AboutElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.AboutElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PodcastDetailsBookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#PodcastDetailsBookmarkElement");
            PodcastDetailsBookmarkElementSerializer.INSTANCE.serializeFields((PodcastDetailsBookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof TrailingButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#TrailingButtonElement");
            TrailingButtonElementSerializer.INSTANCE.serializeFields((TrailingButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.EpisodeOptionsInterface.v1_0.FollowElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#FollowElement");
            Podcast.EpisodeOptionsInterface.v1_0.FollowElementSerializer.INSTANCE.serializeFields((Podcast.EpisodeOptionsInterface.v1_0.FollowElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof RibbonPrimaryButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonPrimaryButtonElement");
            RibbonPrimaryButtonElementSerializer.INSTANCE.serializeFields((RibbonPrimaryButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#DownloadElement");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.DownloadElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactDetailTemplateInterface.v1_0.SaveElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#SaveElement");
            Podcast.Touch.CompactDetailTemplateInterface.v1_0.SaveElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactDetailTemplateInterface.v1_0.SaveElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AboutElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#AboutElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AboutElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AboutElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof RibbonNotificationElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonNotificationElement");
            RibbonNotificationElementSerializer.INSTANCE.serializeFields((RibbonNotificationElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof NotificationElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.NotificationElementInterface.v1_0#NotificationElement");
            NotificationElementSerializer.INSTANCE.serializeFields((NotificationElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.PillItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#PillItemElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.PillItemElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.PillItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof RibbonSecondaryButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonSecondaryButtonElement");
            RibbonSecondaryButtonElementSerializer.INSTANCE.serializeFields((RibbonSecondaryButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedPinnedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v1_0#FeaturedPinnedElement");
            Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedPinnedElementSerializer.INSTANCE.serializeFields((Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedPinnedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.HeaderElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#HeaderElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.HeaderElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.HeaderElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FeaturedBarkerItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0#FeaturedBarkerItemElement");
            FeaturedBarkerItemElementSerializer.INSTANCE.serializeFields((FeaturedBarkerItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof DetailTemplateShardsElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#DetailTemplateShardsElement");
            DetailTemplateShardsElementSerializer.INSTANCE.serializeFields((DetailTemplateShardsElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.PodcastRowTemplateInterface.v1_0.ButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastRowTemplateInterface.v1_0#ButtonElement");
            Podcast.Desktop.PodcastRowTemplateInterface.v1_0.ButtonElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastRowTemplateInterface.v1_0.ButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FeaturedPinnedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#FeaturedPinnedElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FeaturedPinnedElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FeaturedPinnedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalSeeMoreItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#VerticalSeeMoreItemElement");
            Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalSeeMoreItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalSeeMoreItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.SaveInterface.v1_0.RefinementBarElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.SaveInterface.v1_0#RefinementBarElement");
            Podcast.SaveInterface.v1_0.RefinementBarElementSerializer.INSTANCE.serializeFields((Podcast.SaveInterface.v1_0.RefinementBarElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.BorderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#BorderedBadgeElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.BorderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.BorderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.UnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#UnborderedBadgeElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.UnborderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.UnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.LightUnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#LightUnborderedBadgeElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.LightUnborderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.LightUnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AccentOutlineBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#AccentOutlineBadgeElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AccentOutlineBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AccentOutlineBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.BadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#BadgeElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.BadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.BadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof HorizontalItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#HorizontalItemElement");
            HorizontalItemElementSerializer.INSTANCE.serializeFields((HorizontalItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof TrailerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#TrailerElement");
            TrailerElementSerializer.INSTANCE.serializeFields((TrailerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof ToggleRefinementOptionElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.RefinementOptionsInterface.v1_0#ToggleRefinementOptionElement");
            ToggleRefinementOptionElementSerializer.INSTANCE.serializeFields((ToggleRefinementOptionElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.HorizontalRowTemplateInterface.v1_0.ButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#ButtonElement");
            Podcast.Touch.HorizontalRowTemplateInterface.v1_0.ButtonElementSerializer.INSTANCE.serializeFields((Podcast.Touch.HorizontalRowTemplateInterface.v1_0.ButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.AlertTemplateInterface.v1_0.PositiveButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.AlertTemplateInterface.v1_0#PositiveButtonElement");
            Podcast.Desktop.AlertTemplateInterface.v1_0.PositiveButtonElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.AlertTemplateInterface.v1_0.PositiveButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeRowItemsInterface.v1_0.UnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#UnborderedBadgeElement");
            Podcast.Touch.EpisodeRowItemsInterface.v1_0.UnborderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeRowItemsInterface.v1_0.UnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeRowItemsInterface.v1_0.BorderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#BorderedBadgeElement");
            Podcast.Touch.EpisodeRowItemsInterface.v1_0.BorderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeRowItemsInterface.v1_0.BorderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeRowItemsInterface.v1_0.AccentOutlineBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#AccentOutlineBadgeElement");
            Podcast.Touch.EpisodeRowItemsInterface.v1_0.AccentOutlineBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeRowItemsInterface.v1_0.AccentOutlineBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeRowItemsInterface.v1_0.LightUnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#LightUnborderedBadgeElement");
            Podcast.Touch.EpisodeRowItemsInterface.v1_0.LightUnborderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeRowItemsInterface.v1_0.LightUnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeRowItemsInterface.v1_0.BadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#BadgeElement");
            Podcast.Touch.EpisodeRowItemsInterface.v1_0.BadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeRowItemsInterface.v1_0.BadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof EpisodeOptionsElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#EpisodeOptionsElement");
            EpisodeOptionsElementSerializer.INSTANCE.serializeFields((EpisodeOptionsElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FooterElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowInterface.v1_0#FooterElement");
            FooterElementSerializer.INSTANCE.serializeFields((FooterElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.EpisodeOptionsInterface.v1_0.ShareElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#ShareElement");
            Podcast.EpisodeOptionsInterface.v1_0.ShareElementSerializer.INSTANCE.serializeFields((Podcast.EpisodeOptionsInterface.v1_0.ShareElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v2_0.TrailerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v2_0#TrailerElement");
            Podcast.Touch.GalleryTemplateInterface.v2_0.TrailerElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v2_0.TrailerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FollowsElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#FollowsElement");
            FollowsElementSerializer.INSTANCE.serializeFields((FollowsElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v1_0.VisualShovelerCompactElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#VisualShovelerCompactElement");
            Podcast.Touch.GalleryTemplateInterface.v1_0.VisualShovelerCompactElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v1_0.VisualShovelerCompactElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof ExperienceVisualShovelerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#ExperienceVisualShovelerElement");
            ExperienceVisualShovelerElementSerializer.INSTANCE.serializeFields((ExperienceVisualShovelerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof JumpBackInElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#JumpBackInElement");
            JumpBackInElementSerializer.INSTANCE.serializeFields((JumpBackInElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PodcastFeaturedPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#PodcastFeaturedPlayElement");
            PodcastFeaturedPlayElementSerializer.INSTANCE.serializeFields((PodcastFeaturedPlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FeatureBarkerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#FeatureBarkerElement");
            FeatureBarkerElementSerializer.INSTANCE.serializeFields((FeatureBarkerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof ThumbnailNavigationWidgetElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#ThumbnailNavigationWidgetElement");
            ThumbnailNavigationWidgetElementSerializer.INSTANCE.serializeFields((ThumbnailNavigationWidgetElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof SavesElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#SavesElement");
            SavesElementSerializer.INSTANCE.serializeFields((SavesElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof EpisodeFeaturedPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#EpisodeFeaturedPlayElement");
            EpisodeFeaturedPlayElementSerializer.INSTANCE.serializeFields((EpisodeFeaturedPlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof BookmarksElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#BookmarksElement");
            BookmarksElementSerializer.INSTANCE.serializeFields((BookmarksElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PillListingNavigatorElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#PillListingNavigatorElement");
            PillListingNavigatorElementSerializer.INSTANCE.serializeFields((PillListingNavigatorElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof EpisodeDescriptiveShovelerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#EpisodeDescriptiveShovelerElement");
            EpisodeDescriptiveShovelerElementSerializer.INSTANCE.serializeFields((EpisodeDescriptiveShovelerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof RegularBarkerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#RegularBarkerElement");
            RegularBarkerElementSerializer.INSTANCE.serializeFields((RegularBarkerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#WidgetElement");
            Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.DownloadInterface.v1_0.RefinementBarElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.DownloadInterface.v1_0#RefinementBarElement");
            Podcast.DownloadInterface.v1_0.RefinementBarElementSerializer.INSTANCE.serializeFields((Podcast.DownloadInterface.v1_0.RefinementBarElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FollowElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#FollowElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FollowElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FollowElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementBarElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#RefinementBarElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementBarElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementBarElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PlayButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PlayButtonElement");
            PlayButtonElementSerializer.INSTANCE.serializeFields((PlayButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.DetailTemplateInterface.v1_0.DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#DownloadElement");
            Podcast.Touch.DetailTemplateInterface.v1_0.DownloadElementSerializer.INSTANCE.serializeFields((Podcast.Touch.DetailTemplateInterface.v1_0.DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.LatestTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.LatestTemplateInterface.v1_0#BookmarkElement");
            Podcast.Desktop.LatestTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.LatestTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.HorizontalItemElementInterface.v1_0.HorizontalItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalItemElementInterface.v1_0#HorizontalItemElement");
            Podcast.Touch.HorizontalItemElementInterface.v1_0.HorizontalItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.HorizontalItemElementInterface.v1_0.HorizontalItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PrimaryButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CommonButtonElementInterface.v1_0#PrimaryButtonElement");
            PrimaryButtonElementSerializer.INSTANCE.serializeFields((PrimaryButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.ShareElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#ShareElement");
            Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.ShareElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.ShareElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveSeeMoreItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0#EpisodeDescriptiveSeeMoreItemElement");
            Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveSeeMoreItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveSeeMoreItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof CompactPillItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#CompactPillItemElement");
            CompactPillItemElementSerializer.INSTANCE.serializeFields((CompactPillItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeRowItemsInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowItemsInterface.v1_0#BookmarkElement");
            Podcast.Touch.EpisodeRowItemsInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeRowItemsInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.EpisodeOptionsInterface.v1_0.CompletedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#CompletedElement");
            Podcast.EpisodeOptionsInterface.v1_0.CompletedElementSerializer.INSTANCE.serializeFields((Podcast.EpisodeOptionsInterface.v1_0.CompletedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.AboutElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#AboutElement");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.AboutElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.AboutElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodePlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodePlayElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodePlayElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodePlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.RefinementBarElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#RefinementBarElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.RefinementBarElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.RefinementBarElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.EpisodesTemplateInterface.v1_0.EpisodeRowItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodesTemplateInterface.v1_0#EpisodeRowItemElement");
            Podcast.Desktop.EpisodesTemplateInterface.v1_0.EpisodeRowItemElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.EpisodesTemplateInterface.v1_0.EpisodeRowItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#DownloadElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.DownloadElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.DetailTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#BookmarkElement");
            Podcast.Touch.DetailTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Touch.DetailTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof TemplateMetadataElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.TemplateMetadataInterface.v1_0#TemplateMetadataElement");
            TemplateMetadataElementSerializer.INSTANCE.serializeFields((TemplateMetadataElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof EpisodeItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#EpisodeItemElement");
            EpisodeItemElementSerializer.INSTANCE.serializeFields((EpisodeItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.DetailTemplateInterface.v1_0.HeaderElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#HeaderElement");
            Podcast.Touch.DetailTemplateInterface.v1_0.HeaderElementSerializer.INSTANCE.serializeFields((Podcast.Touch.DetailTemplateInterface.v1_0.HeaderElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.CompactPillItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#CompactPillItemElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.CompactPillItemElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.CompactPillItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.VisualRowTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VisualRowTemplateInterface.v1_0#BookmarkElement");
            Podcast.Touch.VisualRowTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Touch.VisualRowTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.HeaderElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#HeaderElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.HeaderElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.HeaderElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedPinnedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v2_0#FeaturedPinnedElement");
            Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedPinnedElementSerializer.INSTANCE.serializeFields((Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedPinnedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof EpisodeGroupItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeGroupItemsInterface.v1_0#EpisodeGroupItemElement");
            EpisodeGroupItemElementSerializer.INSTANCE.serializeFields((EpisodeGroupItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof TextWithLinkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.RibbonNotificationElementInterface.v1_0#TextWithLinkElement");
            TextWithLinkElementSerializer.INSTANCE.serializeFields((TextWithLinkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.HorizontalRowTemplateInterface.v1_0.FollowElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#FollowElement");
            Podcast.Touch.HorizontalRowTemplateInterface.v1_0.FollowElementSerializer.INSTANCE.serializeFields((Podcast.Touch.HorizontalRowTemplateInterface.v1_0.FollowElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillNavigator) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#CompactPillNavigator");
            Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillNavigatorSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillNavigator) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactDetailTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#BookmarkElement");
            Podcast.Touch.CompactDetailTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactDetailTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BorderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#BorderedBadgeElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BorderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BorderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.LightUnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#LightUnborderedBadgeElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.LightUnborderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.LightUnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.UnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#UnborderedBadgeElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.UnborderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.UnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AccentOutlineBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#AccentOutlineBadgeElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AccentOutlineBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AccentOutlineBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#BadgeElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v1_0.AboutElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#AboutElement");
            Podcast.Touch.GalleryTemplateInterface.v1_0.AboutElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v1_0.AboutElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FooterButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#FooterButtonElement");
            FooterButtonElementSerializer.INSTANCE.serializeFields((FooterButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactDetailTemplateInterface.v1_0.ButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#ButtonElement");
            Podcast.Touch.CompactDetailTemplateInterface.v1_0.ButtonElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactDetailTemplateInterface.v1_0.ButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.PodcastPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#PodcastPlayElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.PodcastPlayElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.PodcastPlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PTCTemplateInterface.v1_0.PillItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PillItemElement");
            Podcast.Touch.PTCTemplateInterface.v1_0.PillItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PTCTemplateInterface.v1_0.PillItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.VerticalGridTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VerticalGridTemplateInterface.v1_0#BookmarkElement");
            Podcast.Touch.VerticalGridTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Touch.VerticalGridTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof RibbonMessageElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonMessageElement");
            RibbonMessageElementSerializer.INSTANCE.serializeFields((RibbonMessageElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedPinnedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#FeaturedPinnedElement");
            Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedPinnedElementSerializer.INSTANCE.serializeFields((Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedPinnedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof HidePromptPreferenceElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowPromptInterface.v1_0#HidePromptPreferenceElement");
            HidePromptPreferenceElementSerializer.INSTANCE.serializeFields((HidePromptPreferenceElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v1_0.TrailerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#TrailerElement");
            Podcast.Touch.GalleryTemplateInterface.v1_0.TrailerElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v1_0.TrailerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof GridItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#GridItemElement");
            GridItemElementSerializer.INSTANCE.serializeFields((GridItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof JumpBackInCacheItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.JumpBackInInterface.v1_0#JumpBackInCacheItemElement");
            JumpBackInCacheItemElementSerializer.INSTANCE.serializeFields((JumpBackInCacheItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PTCTemplateShardsElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PTCTemplateShardsElement");
            PTCTemplateShardsElementSerializer.INSTANCE.serializeFields((PTCTemplateShardsElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof LeadingButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#LeadingButtonElement");
            LeadingButtonElementSerializer.INSTANCE.serializeFields((LeadingButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof NegativeButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.AlertTemplateInterface.v1_0#NegativeButtonElement");
            NegativeButtonElementSerializer.INSTANCE.serializeFields((NegativeButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FeaturedSingleItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#FeaturedSingleItemElement");
            FeaturedSingleItemElementSerializer.INSTANCE.serializeFields((FeaturedSingleItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.DetailTemplateInterface.v1_0.FollowElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#FollowElement");
            Podcast.Touch.DetailTemplateInterface.v1_0.FollowElementSerializer.INSTANCE.serializeFields((Podcast.Touch.DetailTemplateInterface.v1_0.FollowElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PlayEpisodeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#PlayEpisodeElement");
            PlayEpisodeElementSerializer.INSTANCE.serializeFields((PlayEpisodeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeFeaturedPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodeFeaturedPlayElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeFeaturedPlayElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeFeaturedPlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.SavesElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#SavesElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.SavesElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.SavesElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.RegularBarkerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#RegularBarkerElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.RegularBarkerElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.RegularBarkerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.FeatureBarkerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#FeatureBarkerElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.FeatureBarkerElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.FeatureBarkerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof VisualListingElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#VisualListingElement");
            VisualListingElementSerializer.INSTANCE.serializeFields((VisualListingElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#FollowsElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.FollowsElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodeDescriptiveShovelerElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.VisualShovelerCompactElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#VisualShovelerCompactElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.VisualShovelerCompactElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.VisualShovelerCompactElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#PodcastFeaturedPlayElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#JumpBackInElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.BookmarksElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#BookmarksElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.BookmarksElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.BookmarksElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.PillListingNavigatorElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#PillListingNavigatorElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.PillListingNavigatorElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.PillListingNavigatorElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#WidgetElement");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PTCTemplateInterface.v1_0.AboutElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#AboutElement");
            Podcast.Touch.PTCTemplateInterface.v1_0.AboutElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PTCTemplateInterface.v1_0.AboutElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FollowsSeeMoreElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#FollowsSeeMoreElement");
            FollowsSeeMoreElementSerializer.INSTANCE.serializeFields((FollowsSeeMoreElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.LatestTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v1_0#BookmarkElement");
            Podcast.Touch.LatestTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Touch.LatestTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.EpisodeRowItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#EpisodeRowItemElement");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.EpisodeRowItemElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.EpisodeRowItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.AboutElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#AboutElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.AboutElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.AboutElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PodcastRowItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastRowTemplateInterface.v1_0#PodcastRowItemElement");
            PodcastRowItemElementSerializer.INSTANCE.serializeFields((PodcastRowItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof DetailTemplateShardElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#DetailTemplateShardElement");
            DetailTemplateShardElementSerializer.INSTANCE.serializeFields((DetailTemplateShardElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.AlertTemplateInterface.v1_0.NegativeButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.AlertTemplateInterface.v1_0#NegativeButtonElement");
            Podcast.Touch.AlertTemplateInterface.v1_0.NegativeButtonElementSerializer.INSTANCE.serializeFields((Podcast.Touch.AlertTemplateInterface.v1_0.NegativeButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryHeaderElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v2_0#GalleryHeaderElement");
            Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryHeaderElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryHeaderElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PTCTemplateInterface.v1_0.NegativeButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#NegativeButtonElement");
            Podcast.Touch.PTCTemplateInterface.v1_0.NegativeButtonElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PTCTemplateInterface.v1_0.NegativeButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.AlertTemplateInterface.v1_0#PositiveButtonElement");
            Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElementSerializer.INSTANCE.serializeFields((Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.LatestTemplateInterface.v1_0.DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.LatestTemplateInterface.v1_0#DownloadElement");
            Podcast.Desktop.LatestTemplateInterface.v1_0.DownloadElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.LatestTemplateInterface.v1_0.DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.BadgeElementInterface.v1_0.BorderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.BadgeElementInterface.v1_0#BorderedBadgeElement");
            Podcast.Touch.BadgeElementInterface.v1_0.BorderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.BadgeElementInterface.v1_0.BorderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.BadgeElementInterface.v1_0.UnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.BadgeElementInterface.v1_0#UnborderedBadgeElement");
            Podcast.Touch.BadgeElementInterface.v1_0.UnborderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.BadgeElementInterface.v1_0.UnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.BadgeElementInterface.v1_0.AccentOutlineBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.BadgeElementInterface.v1_0#AccentOutlineBadgeElement");
            Podcast.Touch.BadgeElementInterface.v1_0.AccentOutlineBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.BadgeElementInterface.v1_0.AccentOutlineBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.BadgeElementInterface.v1_0.LightUnborderedBadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.BadgeElementInterface.v1_0#LightUnborderedBadgeElement");
            Podcast.Touch.BadgeElementInterface.v1_0.LightUnborderedBadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.BadgeElementInterface.v1_0.LightUnborderedBadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.BadgeElementInterface.v1_0.BadgeElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.BadgeElementInterface.v1_0#BadgeElement");
            Podcast.Touch.BadgeElementInterface.v1_0.BadgeElementSerializer.INSTANCE.serializeFields((Podcast.Touch.BadgeElementInterface.v1_0.BadgeElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof GoToPodcastElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#GoToPodcastElement");
            GoToPodcastElementSerializer.INSTANCE.serializeFields((GoToPodcastElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PTCTemplateInterface.v1_0.ListItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#ListItemElement");
            Podcast.Touch.PTCTemplateInterface.v1_0.ListItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PTCTemplateInterface.v1_0.ListItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v2_0.AboutElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v2_0#AboutElement");
            Podcast.Touch.GalleryTemplateInterface.v2_0.AboutElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v2_0.AboutElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FeaturedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#FeaturedElement");
            FeaturedElementSerializer.INSTANCE.serializeFields((FeaturedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.HorizontalItemElementInterface.v1_0.FollowElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalItemElementInterface.v1_0#FollowElement");
            Podcast.Touch.HorizontalItemElementInterface.v1_0.FollowElementSerializer.INSTANCE.serializeFields((Podcast.Touch.HorizontalItemElementInterface.v1_0.FollowElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.CompactPillItemElementInterface.v1_0.CompactPillItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactPillItemElementInterface.v1_0#CompactPillItemElement");
            Podcast.Touch.CompactPillItemElementInterface.v1_0.CompactPillItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.CompactPillItemElementInterface.v1_0.CompactPillItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.VisualRowTemplateInterface.v1_0.DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VisualRowTemplateInterface.v1_0#DownloadElement");
            Podcast.Touch.VisualRowTemplateInterface.v1_0.DownloadElementSerializer.INSTANCE.serializeFields((Podcast.Touch.VisualRowTemplateInterface.v1_0.DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.DetailTemplateInterface.v1_0.AboutElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#AboutElement");
            Podcast.Touch.DetailTemplateInterface.v1_0.AboutElementSerializer.INSTANCE.serializeFields((Podcast.Touch.DetailTemplateInterface.v1_0.AboutElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.LatestTemplateInterface.v1_0.FeaturedPinnedElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.LatestTemplateInterface.v1_0#FeaturedPinnedElement");
            Podcast.Desktop.LatestTemplateInterface.v1_0.FeaturedPinnedElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.LatestTemplateInterface.v1_0.FeaturedPinnedElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VerticalItemElementInterface.v1_0#VerticalItemElement");
            Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof PodcastShowCarPresetElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#PodcastShowCarPresetElement");
            PodcastShowCarPresetElementSerializer.INSTANCE.serializeFields((PodcastShowCarPresetElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof CarPresetElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.EpisodeOptionsInterface.v1_0#CarPresetElement");
            CarPresetElementSerializer.INSTANCE.serializeFields((CarPresetElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.BarkerItemElementInterface.v1_0.BarkerItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.BarkerItemElementInterface.v1_0#BarkerItemElement");
            Podcast.Touch.BarkerItemElementInterface.v1_0.BarkerItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.BarkerItemElementInterface.v1_0.BarkerItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Desktop.EpisodesTemplateInterface.v1_0.DownloadElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodesTemplateInterface.v1_0#DownloadElement");
            Podcast.Desktop.EpisodesTemplateInterface.v1_0.DownloadElementSerializer.INSTANCE.serializeFields((Podcast.Desktop.EpisodesTemplateInterface.v1_0.DownloadElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillItemElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#CompactPillItemElement");
            Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillItemElementSerializer.INSTANCE.serializeFields((Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillItemElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.BookmarkElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#BookmarkElement");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.BookmarkElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.BookmarkElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.WidgetElementsInterface.v1_0.SavesElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetElementsInterface.v1_0#SavesElement");
            Podcast.Touch.WidgetElementsInterface.v1_0.SavesElementSerializer.INSTANCE.serializeFields((Podcast.Touch.WidgetElementsInterface.v1_0.SavesElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastFeaturedPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0#PodcastFeaturedPlayElement");
            Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastFeaturedPlayElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastFeaturedPlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.WidgetElementsInterface.v1_0.BookmarksElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetElementsInterface.v1_0#BookmarksElement");
            Podcast.Touch.WidgetElementsInterface.v1_0.BookmarksElementSerializer.INSTANCE.serializeFields((Podcast.Touch.WidgetElementsInterface.v1_0.BookmarksElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof ThumbnailNavigatorWidgetElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0#ThumbnailNavigatorWidgetElement");
            ThumbnailNavigatorWidgetElementSerializer.INSTANCE.serializeFields((ThumbnailNavigatorWidgetElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof FeaturedBarkerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.FeaturedBarkerElementInterface.v1_0#FeaturedBarkerElement");
            FeaturedBarkerElementSerializer.INSTANCE.serializeFields((FeaturedBarkerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.DescriptiveShowcaseElement.v1_0.DescriptiveShowcaseElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DescriptiveShowcaseElement.v1_0#DescriptiveShowcaseElement");
            Podcast.Touch.DescriptiveShowcaseElement.v1_0.DescriptiveShowcaseElementSerializer.INSTANCE.serializeFields((Podcast.Touch.DescriptiveShowcaseElement.v1_0.DescriptiveShowcaseElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.WidgetElementsInterface.v1_0.JumpBackInElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetElementsInterface.v1_0#JumpBackInElement");
            Podcast.Touch.WidgetElementsInterface.v1_0.JumpBackInElementSerializer.INSTANCE.serializeFields((Podcast.Touch.WidgetElementsInterface.v1_0.JumpBackInElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0#EpisodeFeaturedPlayElement");
            Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0#ExperienceVisualShovelerElement");
            Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElementSerializer.INSTANCE.serializeFields((Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.WidgetElementsInterface.v1_0.FollowsElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetElementsInterface.v1_0#FollowsElement");
            Podcast.Touch.WidgetElementsInterface.v1_0.FollowsElementSerializer.INSTANCE.serializeFields((Podcast.Touch.WidgetElementsInterface.v1_0.FollowsElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PillListingNavigatorElementInterface.v1_0#PillListingNavigatorElement");
            Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElementSerializer.INSTANCE.serializeFields((Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0#EpisodeDescriptiveShovelerElement");
            Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElementSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.RegularBarkerElementInterface.v1_0#RegularBarkerElement");
            Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElementSerializer.INSTANCE.serializeFields((Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElement) templateElement, jsonGenerator, serializerProvider);
        } else if (templateElement instanceof Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VisualShovelerCompactElementInterface.v1_0#VisualShovelerCompactElement");
            Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElementSerializer.INSTANCE.serializeFields((Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElement) templateElement, jsonGenerator, serializerProvider);
        } else {
            boolean z = templateElement instanceof Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(templateElement, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.Touch.WidgetElementsInterface.v1_0#WidgetElement");
                Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElementSerializer.INSTANCE.serializeFields((Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement) templateElement, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(TemplateElement templateElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
