package com.amazon.podcast.views;

import Podcast.DownloadInterface.v1_0.DownloadsTemplate;
import Podcast.FollowInterface.v1_0.FollowsTemplate;
import Podcast.JumpBackInInterface.v1_0.JumpBackInTemplate;
import Podcast.SaveInterface.v1_0.SavesTemplate;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplate;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplate;
import Podcast.Touch.EpisodeRowTemplateInterface.v1_0.EpisodeRowTemplate;
import Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalRowTemplate;
import Podcast.Touch.LatestTemplateInterface.v2_0.LatestTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCCategoriesTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEpisodesTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPodcastsTemplate;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.PodcastDetailTemplate;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalGridTemplate;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowTemplate;
import SOATemplateListInterface.v1_0.Template;
import android.view.View;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.views.chromeTemplate.ChromeTemplateView;
import com.amazon.podcast.views.compactDetailTemplate.CompactDetailTemplateView;
import com.amazon.podcast.views.compactDetailTemplate.CompactDetailTemplateViewV0;
import com.amazon.podcast.views.defaultTemplate.DefaultTemplateView;
import com.amazon.podcast.views.downloadsTemplate.DownloadsTemplateViewV2;
import com.amazon.podcast.views.episodeRowTemplate.EpisodeRowTemplateView;
import com.amazon.podcast.views.followsTemplate.FollowsTemplateView;
import com.amazon.podcast.views.galleryTemplate.GalleryTemplateView;
import com.amazon.podcast.views.galleryTemplate.GalleryTemplateViewV2;
import com.amazon.podcast.views.horizontalRowTemplate.HorizontalRowTemplateView;
import com.amazon.podcast.views.jumpBackInTemplate.JumpBackInTemplateViewV2;
import com.amazon.podcast.views.latestTemplate.LatestTemplateViewV2;
import com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateView;
import com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0;
import com.amazon.podcast.views.ptcCategoriesTemplate.PTCCategoriesTemplateView;
import com.amazon.podcast.views.ptcEpisodesTemplate.PTCEpisodesTemplateView;
import com.amazon.podcast.views.ptcPodcastsTemplate.PTCPodcastsTemplateView;
import com.amazon.podcast.views.savesTemplate.SavesTemplateViewV2;
import com.amazon.podcast.views.verticalGridTemplate.VerticalGridTemplateView;
import com.amazon.podcast.views.visualRowTemplate.VisualRowTemplateView;

/* JADX INFO: loaded from: classes5.dex */
public final class TemplateFactory {
    public static View fromTemplate(final Template template, final TemplateContext templateContext) {
        boolean zIsNewDetailPageLayoutsFeatureEnabled = Podcast.getFeatureGatePreferences().isNewDetailPageLayoutsFeatureEnabled();
        if (template instanceof GalleryTemplate) {
            return new GalleryTemplateView(templateContext);
        }
        if (template instanceof Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryTemplate) {
            return new GalleryTemplateViewV2(templateContext);
        }
        if (template instanceof VisualRowTemplate) {
            return new VisualRowTemplateView(templateContext);
        }
        if (template instanceof HorizontalRowTemplate) {
            return new HorizontalRowTemplateView(templateContext);
        }
        if (template instanceof CompactDetailTemplate) {
            if (zIsNewDetailPageLayoutsFeatureEnabled) {
                return new CompactDetailTemplateView(templateContext);
            }
            return new CompactDetailTemplateViewV0(templateContext);
        }
        if (template instanceof VerticalGridTemplate) {
            return new VerticalGridTemplateView(templateContext);
        }
        if (template instanceof FollowsTemplate) {
            return new FollowsTemplateView(templateContext);
        }
        if (template instanceof JumpBackInTemplate) {
            return new JumpBackInTemplateViewV2(templateContext);
        }
        if (template instanceof ChromeTemplate) {
            return new ChromeTemplateView(templateContext);
        }
        if (template instanceof DownloadsTemplate) {
            return new DownloadsTemplateViewV2(templateContext);
        }
        if (template instanceof SavesTemplate) {
            return new SavesTemplateViewV2(templateContext);
        }
        if (template instanceof LatestTemplate) {
            return new LatestTemplateViewV2(templateContext);
        }
        if (template instanceof PodcastDetailTemplate) {
            if (zIsNewDetailPageLayoutsFeatureEnabled) {
                return new PodcastDetailTemplateView(templateContext);
            }
            return new PodcastDetailTemplateViewV0(templateContext);
        }
        if (template instanceof PTCCategoriesTemplate) {
            return new PTCCategoriesTemplateView(templateContext);
        }
        if (template instanceof PTCPodcastsTemplate) {
            return new PTCPodcastsTemplateView(templateContext);
        }
        if (template instanceof PTCEpisodesTemplate) {
            return new PTCEpisodesTemplateView(templateContext);
        }
        if (template instanceof EpisodeRowTemplate) {
            return new EpisodeRowTemplateView(templateContext);
        }
        return new DefaultTemplateView(templateContext);
    }
}
