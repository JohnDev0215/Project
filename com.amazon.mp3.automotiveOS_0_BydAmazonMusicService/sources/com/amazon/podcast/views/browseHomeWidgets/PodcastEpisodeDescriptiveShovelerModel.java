package com.amazon.podcast.views.browseHomeWidgets;

import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemModel;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastEpisodeDescriptiveShovelerModel extends BaseViewContainerModel<BaseViewModel> {
    private List<EpisodeRowItemModel> models;
    private String seeMoreText;
    private String seeMoreUri;
    private String title;

    public PodcastEpisodeDescriptiveShovelerModel(final String viewId, final List<EpisodeRowItemModel> models, final int tileType, final List tags, final String title, final String seeMoreText, final String seeMoreUri) {
        super(viewId, models, tileType, tags);
        this.models = models;
        this.title = title;
        this.seeMoreText = seeMoreText;
        this.seeMoreUri = seeMoreUri;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewContainerModel
    public List<BaseViewModel> getModels() {
        return Collections.EMPTY_LIST;
    }

    public List<EpisodeRowItemModel> getEpisodeRowItemModels() {
        return this.models;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSeeMoreText() {
        return this.seeMoreText;
    }

    public String getSeeMoreUri() {
        return this.seeMoreUri;
    }
}
