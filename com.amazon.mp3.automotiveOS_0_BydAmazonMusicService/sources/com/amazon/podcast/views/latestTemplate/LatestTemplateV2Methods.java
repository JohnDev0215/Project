package com.amazon.podcast.views.latestTemplate;

import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateShardElement;
import Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedPinnedElement;
import Podcast.Touch.LatestTemplateInterface.v2_0.LatestTemplate;
import SOATemplateListInterface.v1_0.Shard;
import android.content.res.Resources;
import com.amazon.podcast.R$string;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import java.util.Collections;

/* JADX INFO: loaded from: classes5.dex */
public class LatestTemplateV2Methods {
    public static ChromeTemplateShardElement bootstrapLatestShard(final Resources resources) {
        return ChromeTemplateShardElement.builder().withHeader(resources.getString(R$string.podcast_latest_tab_label)).withShard(Shard.builder().withCreate(LatestTemplate.builder().withLaunchMode("standard").withOnBound(Collections.emptyList()).withFeaturedPinnedElement(FeaturedPinnedElement.builder().build()).withOnCreated(DeeplinkMethods.latest(resources)).withOnViewed(Collections.emptyList()).build()).build()).build();
    }
}
