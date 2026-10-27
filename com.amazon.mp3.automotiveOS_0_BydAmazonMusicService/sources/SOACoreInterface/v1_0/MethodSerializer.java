package SOACoreInterface.v1_0;

import Podcast.BiteBookmarkInterface.v1_0.SetBiteBookmarksLastSyncTimeMethod;
import Podcast.BiteBookmarkInterface.v1_0.SetBiteBookmarksLastSyncTimeMethodSerializer;
import Podcast.BookmarkInterface.v1_0.SetBookmarksLastSyncTimeMethod;
import Podcast.BookmarkInterface.v1_0.SetBookmarksLastSyncTimeMethodSerializer;
import Podcast.BookmarkInterface.v1_0.SetBookmarksThresholdsMethod;
import Podcast.BookmarkInterface.v1_0.SetBookmarksThresholdsMethodSerializer;
import Podcast.BookmarkInterface.v1_0.SetOnReportOperationsMethod;
import Podcast.BookmarkInterface.v1_0.SetOnReportOperationsMethodSerializer;
import Podcast.CategoryFollowInterface.v1_0.SetCategoryFollowsLastSyncTimeMethod;
import Podcast.CategoryFollowInterface.v1_0.SetCategoryFollowsLastSyncTimeMethodSerializer;
import Podcast.CompletedInterface.v1_0.SetCompletedLastSyncTimeMethod;
import Podcast.CompletedInterface.v1_0.SetCompletedLastSyncTimeMethodSerializer;
import Podcast.DeeplinkInterface.v1_0.InvokeDeeplinkMethod;
import Podcast.DeeplinkInterface.v1_0.InvokeDeeplinkMethodSerializer;
import Podcast.DeeplinkInterface.v1_0.NavigateToDeeplinkMethod;
import Podcast.DeeplinkInterface.v1_0.NavigateToDeeplinkMethodSerializer;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.AddVerticalItemsToVisualListingElementMethod;
import Podcast.Desktop.GalleryTemplateInterface.v1_0.AddVerticalItemsToVisualListingElementMethodSerializer;
import Podcast.Desktop.NetworkInterface.v1_0.ShowErrorMessageMethod;
import Podcast.Desktop.NetworkInterface.v1_0.ShowErrorMessageMethodSerializer;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.AddPodcastRowItemsMethod;
import Podcast.Desktop.PodcastRowTemplateInterface.v1_0.AddPodcastRowItemsMethodSerializer;
import Podcast.FeatureGatingInterface.v1_0.SetBooleanFeaturesMethod;
import Podcast.FeatureGatingInterface.v1_0.SetBooleanFeaturesMethodSerializer;
import Podcast.FollowInterface.v1_0.SetFollowsLastSyncTimeMethod;
import Podcast.FollowInterface.v1_0.SetFollowsLastSyncTimeMethodSerializer;
import Podcast.FollowPromptInterface.v1_0.WriteHidePromptPreferencesMethod;
import Podcast.FollowPromptInterface.v1_0.WriteHidePromptPreferencesMethodSerializer;
import Podcast.JumpBackInInterface.v1_0.InvalidateJumpBackInCacheMethod;
import Podcast.JumpBackInInterface.v1_0.InvalidateJumpBackInCacheMethodSerializer;
import Podcast.PlaybackInterface.v1_0.BufferMediaMethod;
import Podcast.PlaybackInterface.v1_0.BufferMediaMethodSerializer;
import Podcast.PlaybackInterface.v1_0.ClearMediaMethod;
import Podcast.PlaybackInterface.v1_0.ClearMediaMethodSerializer;
import Podcast.PlaybackInterface.v1_0.NavigateToPodcastMethod;
import Podcast.PlaybackInterface.v1_0.NavigateToPodcastMethodSerializer;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethod;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethodSerializer;
import Podcast.PlaybackInterface.v1_0.PlayLocalEpisodesMethod;
import Podcast.PlaybackInterface.v1_0.PlayLocalEpisodesMethodSerializer;
import Podcast.PlaybackInterface.v1_0.PlayMediaMethod;
import Podcast.PlaybackInterface.v1_0.PlayMediaMethodSerializer;
import Podcast.PlaybackInterface.v1_0.ResetMediaMethod;
import Podcast.PlaybackInterface.v1_0.ResetMediaMethodSerializer;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethod;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethodSerializer;
import Podcast.PlaybackInterface.v1_0.SendPlaybackEventMethod;
import Podcast.PlaybackInterface.v1_0.SendPlaybackEventMethodSerializer;
import Podcast.PlaybackInterface.v1_0.SetEMPMediaMetadataMethod;
import Podcast.PlaybackInterface.v1_0.SetEMPMediaMetadataMethodSerializer;
import Podcast.PlaybackInterface.v1_0.SetEMPPlaybackSessionMethod;
import Podcast.PlaybackInterface.v1_0.SetEMPPlaybackSessionMethodSerializer;
import Podcast.PlaybackInterface.v1_0.SetMediaMethod;
import Podcast.PlaybackInterface.v1_0.SetMediaMethodSerializer;
import Podcast.PlaybackInterface.v1_0.SetMediaOffsetMethod;
import Podcast.PlaybackInterface.v1_0.SetMediaOffsetMethodSerializer;
import Podcast.PlaybackInterface.v1_0.SetPlaybackCapabilitiesMethod;
import Podcast.PlaybackInterface.v1_0.SetPlaybackCapabilitiesMethodSerializer;
import Podcast.PlaybackInterface.v1_0.SetTranscriptsMethod;
import Podcast.PlaybackInterface.v1_0.SetTranscriptsMethodSerializer;
import Podcast.PlaybackInterface.v1_0.SetWriteAndReportFrequenciesMethod;
import Podcast.PlaybackInterface.v1_0.SetWriteAndReportFrequenciesMethodSerializer;
import Podcast.SaveInterface.v1_0.SetSavesLastSyncTimeMethod;
import Podcast.SaveInterface.v1_0.SetSavesLastSyncTimeMethodSerializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.AddCardsMethod;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.AddCardsMethodSerializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.NextCardMethod;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.NextCardMethodSerializer;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.PreviousCardMethod;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.PreviousCardMethodSerializer;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShowVisualShovelerMethod;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShowVisualShovelerMethodSerializer;
import Podcast.Touch.GalleryTemplateInterface.v1_0.AddWidgetsMethod;
import Podcast.Touch.GalleryTemplateInterface.v1_0.AddWidgetsMethodSerializer;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AddHorizontalItemsMethod;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AddHorizontalItemsMethodSerializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.AddLatestItemElementsMethod;
import Podcast.Touch.LatestTemplateInterface.v1_0.AddLatestItemElementsMethodSerializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethod;
import Podcast.Touch.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer;
import Podcast.Touch.LatestTemplateInterface.v2_0.AddEpisodeRowItemsMethod;
import Podcast.Touch.LatestTemplateInterface.v2_0.AddEpisodeRowItemsMethodSerializer;
import Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethod;
import Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethodSerializer;
import Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethod;
import Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethodSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.AddGridElementsMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.AddGridElementsMethodSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethodSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEndMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEndMethodSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTrailerPlaybackErrorMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTrailerPlaybackErrorMethodSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.SetPTCPreferencesMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.SetPTCPreferencesMethodSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.ShowPTCTrailerMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.ShowPTCTrailerMethodSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.WritePTCCacheMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.WritePTCCacheMethodSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.AddEpisodeItemsMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.AddEpisodeItemsMethodSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ReplaceEpisodeItemsMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ReplaceEpisodeItemsMethodSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ReplaceEpisodeRowItemsMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ReplaceEpisodeRowItemsMethodSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShowDownloadedEpisodesMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShowDownloadedEpisodesMethodSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AddEpisodeGroupItemsMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.AddEpisodeGroupItemsMethodSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ReplaceEpisodeGroupItemsMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ReplaceEpisodeGroupItemsMethodSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShowDownloadedEpisodeGroupsMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ShowDownloadedEpisodeGroupsMethodSerializer;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.AddVerticalItemsMethod;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.AddVerticalItemsMethodSerializer;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.AddVisualRowItemsMethod;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.AddVisualRowItemsMethodSerializer;
import Podcast.UpsellInterface.v1_0.ShowUpsellMethod;
import Podcast.UpsellInterface.v1_0.ShowUpsellMethodSerializer;
import SOAAppSyncInterface.v1_0.ResolveOperationMethod;
import SOAAppSyncInterface.v1_0.ResolveOperationMethodSerializer;
import SOAAppSyncInterface.v1_0.RetryOperationMethod;
import SOAAppSyncInterface.v1_0.RetryOperationMethodSerializer;
import SOAAppSyncInterface.v1_0.StartMethod;
import SOAAppSyncInterface.v1_0.StartMethodSerializer;
import SOAAppSyncInterface.v1_0.WriteLocalMethod;
import SOAAppSyncInterface.v1_0.WriteLocalMethodSerializer;
import SOAAppSyncInterface.v1_0.WriteMethod;
import SOAAppSyncInterface.v1_0.WriteMethodSerializer;
import SOACacheInterface.v1_0.InvalidateCacheMethod;
import SOACacheInterface.v1_0.InvalidateCacheMethodSerializer;
import SOACacheInterface.v1_0.WriteCacheMethod;
import SOACacheInterface.v1_0.WriteCacheMethodSerializer;
import SOAInteractionInterface.v1_0.GlobalInvokeRemoteSkillMethod;
import SOAInteractionInterface.v1_0.GlobalInvokeRemoteSkillMethodSerializer;
import SOAInteractionInterface.v1_0.InvokeRemoteSkillMethod;
import SOAInteractionInterface.v1_0.InvokeRemoteSkillMethodSerializer;
import SOATemplateListInterface.v1_0.BindTemplateMethod;
import SOATemplateListInterface.v1_0.BindTemplateMethodSerializer;
import SOATemplateListInterface.v1_0.ClearTemplatesMethod;
import SOATemplateListInterface.v1_0.ClearTemplatesMethodSerializer;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethodSerializer;
import SOATemplateListInterface.v1_0.CreateTemplateMethod;
import SOATemplateListInterface.v1_0.CreateTemplateMethodSerializer;
import SOATemplateListInterface.v1_0.InvalidateTemplatesMethod;
import SOATemplateListInterface.v1_0.InvalidateTemplatesMethodSerializer;
import SOATemplateListInterface.v1_0.RemoveTemplateMethod;
import SOATemplateListInterface.v1_0.RemoveTemplateMethodSerializer;
import SOAV2CompatibilityInterface.v1_0.ExtractResponseMethod;
import SOAV2CompatibilityInterface.v1_0.ExtractResponseMethodSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class MethodSerializer extends JsonSerializer<Method> {
    public static final MethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MethodSerializer methodSerializer = new MethodSerializer();
        INSTANCE = methodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.MethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Method.class, methodSerializer);
    }

    private MethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Method method, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (method == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (method instanceof SetOnReportOperationsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BookmarkInterface.v1_0#SetOnReportOperationsMethod");
            SetOnReportOperationsMethodSerializer.INSTANCE.serializeFields((SetOnReportOperationsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddHorizontalItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.HorizontalRowTemplateInterface.v1_0#AddHorizontalItemsMethod");
            AddHorizontalItemsMethodSerializer.INSTANCE.serializeFields((AddHorizontalItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.CategoryFollowInterface.v1_0.SetOnReportOperationsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CategoryFollowInterface.v1_0#SetOnReportOperationsMethod");
            Podcast.CategoryFollowInterface.v1_0.SetOnReportOperationsMethodSerializer.INSTANCE.serializeFields((Podcast.CategoryFollowInterface.v1_0.SetOnReportOperationsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetEMPPlaybackSessionMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#SetEMPPlaybackSessionMethod");
            SetEMPPlaybackSessionMethodSerializer.INSTANCE.serializeFields((SetEMPPlaybackSessionMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof WritePTCCacheMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#WritePTCCacheMethod");
            WritePTCCacheMethodSerializer.INSTANCE.serializeFields((WritePTCCacheMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof WriteCacheMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOACacheInterface.v1_0#WriteCacheMethod");
            WriteCacheMethodSerializer.INSTANCE.serializeFields((WriteCacheMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetBooleanFeaturesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FeatureGatingInterface.v1_0#SetBooleanFeaturesMethod");
            SetBooleanFeaturesMethodSerializer.INSTANCE.serializeFields((SetBooleanFeaturesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ShowDownloadedEpisodeGroupsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#ShowDownloadedEpisodeGroupsMethod");
            ShowDownloadedEpisodeGroupsMethodSerializer.INSTANCE.serializeFields((ShowDownloadedEpisodeGroupsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddVerticalItemsToVisualListingElementMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#AddVerticalItemsToVisualListingElementMethod");
            AddVerticalItemsToVisualListingElementMethodSerializer.INSTANCE.serializeFields((AddVerticalItemsToVisualListingElementMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof CreateAndBindTemplateMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOATemplateListInterface.v1_0#CreateAndBindTemplateMethod");
            CreateAndBindTemplateMethodSerializer.INSTANCE.serializeFields((CreateAndBindTemplateMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetCompletedLastSyncTimeMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CompletedInterface.v1_0#SetCompletedLastSyncTimeMethod");
            SetCompletedLastSyncTimeMethodSerializer.INSTANCE.serializeFields((SetCompletedLastSyncTimeMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ReplaceEpisodeGroupItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#ReplaceEpisodeGroupItemsMethod");
            ReplaceEpisodeGroupItemsMethodSerializer.INSTANCE.serializeFields((ReplaceEpisodeGroupItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ShowPTCTrailerMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#ShowPTCTrailerMethod");
            ShowPTCTrailerMethodSerializer.INSTANCE.serializeFields((ShowPTCTrailerMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetMediaOffsetMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#SetMediaOffsetMethod");
            SetMediaOffsetMethodSerializer.INSTANCE.serializeFields((SetMediaOffsetMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddVerticalItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VerticalGridTemplateInterface.v1_0#AddVerticalItemsMethod");
            AddVerticalItemsMethodSerializer.INSTANCE.serializeFields((AddVerticalItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ShowNotificationMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.NotificationElementInterface.v1_0#ShowNotificationMethod");
            ShowNotificationMethodSerializer.INSTANCE.serializeFields((ShowNotificationMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SendPlaybackEventMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#SendPlaybackEventMethod");
            SendPlaybackEventMethodSerializer.INSTANCE.serializeFields((SendPlaybackEventMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetMediaMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#SetMediaMethod");
            SetMediaMethodSerializer.INSTANCE.serializeFields((SetMediaMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof BufferMediaMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#BufferMediaMethod");
            BufferMediaMethodSerializer.INSTANCE.serializeFields((BufferMediaMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof CloseNotificationMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.NotificationElementInterface.v1_0#CloseNotificationMethod");
            CloseNotificationMethodSerializer.INSTANCE.serializeFields((CloseNotificationMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetFollowsLastSyncTimeMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowInterface.v1_0#SetFollowsLastSyncTimeMethod");
            SetFollowsLastSyncTimeMethodSerializer.INSTANCE.serializeFields((SetFollowsLastSyncTimeMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof WriteLocalMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOAAppSyncInterface.v1_0#WriteLocalMethod");
            WriteLocalMethodSerializer.INSTANCE.serializeFields((WriteLocalMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof PreviousCardMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#PreviousCardMethod");
            PreviousCardMethodSerializer.INSTANCE.serializeFields((PreviousCardMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ReplaceEpisodeItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#ReplaceEpisodeItemsMethod");
            ReplaceEpisodeItemsMethodSerializer.INSTANCE.serializeFields((ReplaceEpisodeItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof PlayLocalEpisodesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#PlayLocalEpisodesMethod");
            PlayLocalEpisodesMethodSerializer.INSTANCE.serializeFields((PlayLocalEpisodesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddWidgetsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.GalleryTemplateInterface.v1_0#AddWidgetsMethod");
            AddWidgetsMethodSerializer.INSTANCE.serializeFields((AddWidgetsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ResumeMediaMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#ResumeMediaMethod");
            ResumeMediaMethodSerializer.INSTANCE.serializeFields((ResumeMediaMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddEpisodeItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#AddEpisodeItemsMethod");
            AddEpisodeItemsMethodSerializer.INSTANCE.serializeFields((AddEpisodeItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof RemoveTemplateMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOATemplateListInterface.v1_0#RemoveTemplateMethod");
            RemoveTemplateMethodSerializer.INSTANCE.serializeFields((RemoveTemplateMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddEpisodeGroupItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#AddEpisodeGroupItemsMethod");
            AddEpisodeGroupItemsMethodSerializer.INSTANCE.serializeFields((AddEpisodeGroupItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ShowErrorMessageMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.NetworkInterface.v1_0#ShowErrorMessageMethod");
            ShowErrorMessageMethodSerializer.INSTANCE.serializeFields((ShowErrorMessageMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof PlayMediaMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#PlayMediaMethod");
            PlayMediaMethodSerializer.INSTANCE.serializeFields((PlayMediaMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ShowDownloadedEpisodesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#ShowDownloadedEpisodesMethod");
            ShowDownloadedEpisodesMethodSerializer.INSTANCE.serializeFields((ShowDownloadedEpisodesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ResolveOperationMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOAAppSyncInterface.v1_0#ResolveOperationMethod");
            ResolveOperationMethodSerializer.INSTANCE.serializeFields((ResolveOperationMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetEMPMediaMetadataMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#SetEMPMediaMetadataMethod");
            SetEMPMediaMetadataMethodSerializer.INSTANCE.serializeFields((SetEMPMediaMetadataMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ClearTemplatesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOATemplateListInterface.v1_0#ClearTemplatesMethod");
            ClearTemplatesMethodSerializer.INSTANCE.serializeFields((ClearTemplatesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ReplaceEpisodeRowItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#ReplaceEpisodeRowItemsMethod");
            ReplaceEpisodeRowItemsMethodSerializer.INSTANCE.serializeFields((ReplaceEpisodeRowItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ShowUpsellMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.UpsellInterface.v1_0#ShowUpsellMethod");
            ShowUpsellMethodSerializer.INSTANCE.serializeFields((ShowUpsellMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof PTCTrailerPlaybackErrorMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PTCTrailerPlaybackErrorMethod");
            PTCTrailerPlaybackErrorMethodSerializer.INSTANCE.serializeFields((PTCTrailerPlaybackErrorMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ShowDownloadedEpisodesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#ShowDownloadedEpisodesMethod");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ShowDownloadedEpisodesMethodSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ShowDownloadedEpisodesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddLatestItemElementsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v1_0#AddLatestItemElementsMethod");
            AddLatestItemElementsMethodSerializer.INSTANCE.serializeFields((AddLatestItemElementsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddEpisodeRowItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v2_0#AddEpisodeRowItemsMethod");
            AddEpisodeRowItemsMethodSerializer.INSTANCE.serializeFields((AddEpisodeRowItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetTranscriptsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#SetTranscriptsMethod");
            SetTranscriptsMethodSerializer.INSTANCE.serializeFields((SetTranscriptsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ShowVisualShovelerMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CompactDetailTemplateInterface.v1_0#ShowVisualShovelerMethod");
            ShowVisualShovelerMethodSerializer.INSTANCE.serializeFields((ShowVisualShovelerMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetPlaybackCapabilitiesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#SetPlaybackCapabilitiesMethod");
            SetPlaybackCapabilitiesMethodSerializer.INSTANCE.serializeFields((SetPlaybackCapabilitiesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddPodcastRowItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastRowTemplateInterface.v1_0#AddPodcastRowItemsMethod");
            AddPodcastRowItemsMethodSerializer.INSTANCE.serializeFields((AddPodcastRowItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ReplaceEpisodeRowItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#ReplaceEpisodeRowItemsMethod");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ReplaceEpisodeRowItemsMethodSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ReplaceEpisodeRowItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Desktop.LatestTemplateInterface.v1_0.AddLatestItemElementsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.LatestTemplateInterface.v1_0#AddLatestItemElementsMethod");
            Podcast.Desktop.LatestTemplateInterface.v1_0.AddLatestItemElementsMethodSerializer.INSTANCE.serializeFields((Podcast.Desktop.LatestTemplateInterface.v1_0.AddLatestItemElementsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ClearMediaMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#ClearMediaMethod");
            ClearMediaMethodSerializer.INSTANCE.serializeFields((ClearMediaMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof StartMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOAAppSyncInterface.v1_0#StartMethod");
            StartMethodSerializer.INSTANCE.serializeFields((StartMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof NextCardMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#NextCardMethod");
            NextCardMethodSerializer.INSTANCE.serializeFields((NextCardMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetBiteBookmarksLastSyncTimeMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BiteBookmarkInterface.v1_0#SetBiteBookmarksLastSyncTimeMethod");
            SetBiteBookmarksLastSyncTimeMethodSerializer.INSTANCE.serializeFields((SetBiteBookmarksLastSyncTimeMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.AddEpisodeRowItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#AddEpisodeRowItemsMethod");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.AddEpisodeRowItemsMethodSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.AddEpisodeRowItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Desktop.EpisodesTemplateInterface.v1_0.AddEpisodeRowItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.EpisodesTemplateInterface.v1_0#AddEpisodeRowItemsMethod");
            Podcast.Desktop.EpisodesTemplateInterface.v1_0.AddEpisodeRowItemsMethodSerializer.INSTANCE.serializeFields((Podcast.Desktop.EpisodesTemplateInterface.v1_0.AddEpisodeRowItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof BindTemplateMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOATemplateListInterface.v1_0#BindTemplateMethod");
            BindTemplateMethodSerializer.INSTANCE.serializeFields((BindTemplateMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.UIMetricsInterface.v1_0.SetOnReportOperationsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.UIMetricsInterface.v1_0#SetOnReportOperationsMethod");
            Podcast.UIMetricsInterface.v1_0.SetOnReportOperationsMethodSerializer.INSTANCE.serializeFields((Podcast.UIMetricsInterface.v1_0.SetOnReportOperationsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof PTCEndMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PTCEndMethod");
            PTCEndMethodSerializer.INSTANCE.serializeFields((PTCEndMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.FollowInterface.v1_0.SetOnReportOperationsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowInterface.v1_0#SetOnReportOperationsMethod");
            Podcast.FollowInterface.v1_0.SetOnReportOperationsMethodSerializer.INSTANCE.serializeFields((Podcast.FollowInterface.v1_0.SetOnReportOperationsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetCategoryFollowsLastSyncTimeMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CategoryFollowInterface.v1_0#SetCategoryFollowsLastSyncTimeMethod");
            SetCategoryFollowsLastSyncTimeMethodSerializer.INSTANCE.serializeFields((SetCategoryFollowsLastSyncTimeMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddCardsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#AddCardsMethod");
            AddCardsMethodSerializer.INSTANCE.serializeFields((AddCardsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ShowDownloadedEpisodesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#ShowDownloadedEpisodesMethod");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ShowDownloadedEpisodesMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ShowDownloadedEpisodesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddVisualRowItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.VisualRowTemplateInterface.v1_0#AddVisualRowItemsMethod");
            AddVisualRowItemsMethodSerializer.INSTANCE.serializeFields((AddVisualRowItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Touch.WidgetElementsInterface.v1_0.AddWidgetsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.WidgetElementsInterface.v1_0#AddWidgetsMethod");
            Podcast.Touch.WidgetElementsInterface.v1_0.AddWidgetsMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.WidgetElementsInterface.v1_0.AddWidgetsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ExtractResponseMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOAV2CompatibilityInterface.v1_0#ExtractResponseMethod");
            ExtractResponseMethodSerializer.INSTANCE.serializeFields((ExtractResponseMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof PauseMediaMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#PauseMediaMethod");
            PauseMediaMethodSerializer.INSTANCE.serializeFields((PauseMediaMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.CompletedInterface.v1_0.SetOnReportOperationsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CompletedInterface.v1_0#SetOnReportOperationsMethod");
            Podcast.CompletedInterface.v1_0.SetOnReportOperationsMethodSerializer.INSTANCE.serializeFields((Podcast.CompletedInterface.v1_0.SetOnReportOperationsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.DownloadInterface.v1_0.SetOnReportOperationsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.DownloadInterface.v1_0#SetOnReportOperationsMethod");
            Podcast.DownloadInterface.v1_0.SetOnReportOperationsMethodSerializer.INSTANCE.serializeFields((Podcast.DownloadInterface.v1_0.SetOnReportOperationsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Desktop.GalleryTemplateInterface.v1_0.AddWidgetsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.GalleryTemplateInterface.v1_0#AddWidgetsMethod");
            Podcast.Desktop.GalleryTemplateInterface.v1_0.AddWidgetsMethodSerializer.INSTANCE.serializeFields((Podcast.Desktop.GalleryTemplateInterface.v1_0.AddWidgetsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof CreateTemplateMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOATemplateListInterface.v1_0#CreateTemplateMethod");
            CreateTemplateMethodSerializer.INSTANCE.serializeFields((CreateTemplateMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof RetryOperationMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOAAppSyncInterface.v1_0#RetryOperationMethod");
            RetryOperationMethodSerializer.INSTANCE.serializeFields((RetryOperationMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetBookmarksLastSyncTimeMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BookmarkInterface.v1_0#SetBookmarksLastSyncTimeMethod");
            SetBookmarksLastSyncTimeMethodSerializer.INSTANCE.serializeFields((SetBookmarksLastSyncTimeMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetWriteAndReportFrequenciesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#SetWriteAndReportFrequenciesMethod");
            SetWriteAndReportFrequenciesMethodSerializer.INSTANCE.serializeFields((SetWriteAndReportFrequenciesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof InvokeDeeplinkMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.DeeplinkInterface.v1_0#InvokeDeeplinkMethod");
            InvokeDeeplinkMethodSerializer.INSTANCE.serializeFields((InvokeDeeplinkMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.PlaybackMetricsInterface.v1_0.SetOnReportOperationsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackMetricsInterface.v1_0#SetOnReportOperationsMethod");
            Podcast.PlaybackMetricsInterface.v1_0.SetOnReportOperationsMethodSerializer.INSTANCE.serializeFields((Podcast.PlaybackMetricsInterface.v1_0.SetOnReportOperationsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof InvokeRemoteSkillMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOAInteractionInterface.v1_0#InvokeRemoteSkillMethod");
            InvokeRemoteSkillMethodSerializer.INSTANCE.serializeFields((InvokeRemoteSkillMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof GlobalInvokeRemoteSkillMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOAInteractionInterface.v1_0#GlobalInvokeRemoteSkillMethod");
            GlobalInvokeRemoteSkillMethodSerializer.INSTANCE.serializeFields((GlobalInvokeRemoteSkillMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.BiteBookmarkInterface.v1_0.SetOnReportOperationsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BiteBookmarkInterface.v1_0#SetOnReportOperationsMethod");
            Podcast.BiteBookmarkInterface.v1_0.SetOnReportOperationsMethodSerializer.INSTANCE.serializeFields((Podcast.BiteBookmarkInterface.v1_0.SetOnReportOperationsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AddEpisodeRowItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#AddEpisodeRowItemsMethod");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AddEpisodeRowItemsMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AddEpisodeRowItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetSavesLastSyncTimeMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.SaveInterface.v1_0#SetSavesLastSyncTimeMethod");
            SetSavesLastSyncTimeMethodSerializer.INSTANCE.serializeFields((SetSavesLastSyncTimeMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetPTCPreferencesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#SetPTCPreferencesMethod");
            SetPTCPreferencesMethodSerializer.INSTANCE.serializeFields((SetPTCPreferencesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof NavigateToDeeplinkMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.DeeplinkInterface.v1_0#NavigateToDeeplinkMethod");
            NavigateToDeeplinkMethodSerializer.INSTANCE.serializeFields((NavigateToDeeplinkMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof ResetMediaMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#ResetMediaMethod");
            ResetMediaMethodSerializer.INSTANCE.serializeFields((ResetMediaMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.SaveInterface.v1_0.SetOnReportOperationsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.SaveInterface.v1_0#SetOnReportOperationsMethod");
            Podcast.SaveInterface.v1_0.SetOnReportOperationsMethodSerializer.INSTANCE.serializeFields((Podcast.SaveInterface.v1_0.SetOnReportOperationsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof NavigateToPodcastMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#NavigateToPodcastMethod");
            NavigateToPodcastMethodSerializer.INSTANCE.serializeFields((NavigateToPodcastMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof InvalidateTemplatesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOATemplateListInterface.v1_0#InvalidateTemplatesMethod");
            InvalidateTemplatesMethodSerializer.INSTANCE.serializeFields((InvalidateTemplatesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof SetBookmarksThresholdsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BookmarkInterface.v1_0#SetBookmarksThresholdsMethod");
            SetBookmarksThresholdsMethodSerializer.INSTANCE.serializeFields((SetBookmarksThresholdsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof WriteMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOAAppSyncInterface.v1_0#WriteMethod");
            WriteMethodSerializer.INSTANCE.serializeFields((WriteMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof WriteHidePromptPreferencesMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowPromptInterface.v1_0#WriteHidePromptPreferencesMethod");
            WriteHidePromptPreferencesMethodSerializer.INSTANCE.serializeFields((WriteHidePromptPreferencesMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Touch.EpisodeRowTemplateInterface.v1_0.AddEpisodeRowItemsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.EpisodeRowTemplateInterface.v1_0#AddEpisodeRowItemsMethod");
            Podcast.Touch.EpisodeRowTemplateInterface.v1_0.AddEpisodeRowItemsMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.EpisodeRowTemplateInterface.v1_0.AddEpisodeRowItemsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof AddGridElementsMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#AddGridElementsMethod");
            AddGridElementsMethodSerializer.INSTANCE.serializeFields((AddGridElementsMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof InvalidatePTCCacheMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#InvalidatePTCCacheMethod");
            InvalidatePTCCacheMethodSerializer.INSTANCE.serializeFields((InvalidatePTCCacheMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v1_0#InvalidateFeaturedItemMethod");
            InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((InvalidateFeaturedItemMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Touch.LatestTemplateInterface.v2_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.LatestTemplateInterface.v2_0#InvalidateFeaturedItemMethod");
            Podcast.Touch.LatestTemplateInterface.v2_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.LatestTemplateInterface.v2_0.InvalidateFeaturedItemMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof InvalidateJumpBackInCacheMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.JumpBackInInterface.v1_0#InvalidateJumpBackInCacheMethod");
            InvalidateJumpBackInCacheMethodSerializer.INSTANCE.serializeFields((InvalidateJumpBackInCacheMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Touch.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod");
            Podcast.Touch.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#InvalidateFeaturedItemMethod");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.InvalidateFeaturedItemMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod");
            Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Desktop.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Desktop.LatestTemplateInterface.v1_0#InvalidateFeaturedItemMethod");
            Podcast.Desktop.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Desktop.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#InvalidateFeaturedItemMethod");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.InvalidateFeaturedItemMethod) method, jsonGenerator, serializerProvider);
        } else if (method instanceof Podcast.Touch.DetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.DetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod");
            Podcast.Touch.DetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer.INSTANCE.serializeFields((Podcast.Touch.DetailTemplateInterface.v1_0.InvalidateFeaturedItemMethod) method, jsonGenerator, serializerProvider);
        } else {
            boolean z = method instanceof InvalidateCacheMethod;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(method, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("SOACacheInterface.v1_0#InvalidateCacheMethod");
                InvalidateCacheMethodSerializer.INSTANCE.serializeFields((InvalidateCacheMethod) method, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Method method, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
