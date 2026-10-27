package com.amazon.podcast.views.contextMenu;

import Podcast.EpisodeOptionsInterface.v1_0.CarPresetElement;
import Podcast.EpisodeOptionsInterface.v1_0.CompletedElement;
import Podcast.EpisodeOptionsInterface.v1_0.DownloadElement;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.EpisodeOptionsInterface.v1_0.FollowElement;
import Podcast.EpisodeOptionsInterface.v1_0.GoToPodcastElement;
import Podcast.EpisodeOptionsInterface.v1_0.PlayEpisodeElement;
import Podcast.EpisodeOptionsInterface.v1_0.ReportElement;
import Podcast.EpisodeOptionsInterface.v1_0.SaveElement;
import Podcast.EpisodeOptionsInterface.v1_0.ShareElement;
import SOATemplateListInterface.v1_0.TemplateElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.views.EmberTextView;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class ContextMenuItemsAdapter extends RecyclerView.Adapter<ContextMenuItemViewHolder> {
    private static final Logger logger = LoggerFactory.getLogger("ContextMenuItemsAdapter");
    private Context context;
    private EmberTextView dismiss;
    private EpisodeOptionsElement episodeOptionsElement;
    private boolean isPopUp;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private List<TemplateElement> options;
    private String ownerId;
    private UiMetricAttributes$PageType pageType;
    private final Resources resources;

    private enum ViewType {
        goToPodcast(0),
        play(1),
        follow(2),
        save(3),
        download(4),
        share(5),
        markAsPlayed(6),
        carModePreset(7),
        report(8);

        private final int value;

        ViewType(final int value) {
            this.value = value;
        }
    }

    public ContextMenuItemsAdapter(final Resources resources, final Context context) {
        this.resources = resources;
        this.context = context;
    }

    public void bind(final EpisodeOptionsElement optionsElement, final MethodsDispatcher methodsDispatcher, final String ownerId, final LifecycleOwner lifecycleOwner, final EmberTextView dismiss, final boolean isPopUp, final UiMetricAttributes$PageType pageType) {
        this.episodeOptionsElement = optionsElement;
        this.options = toArrayOptions(optionsElement);
        this.methodsDispatcher = methodsDispatcher;
        this.ownerId = ownerId;
        this.lifecycleOwner = lifecycleOwner;
        this.dismiss = dismiss;
        this.isPopUp = isPopUp;
        this.pageType = pageType;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public ContextMenuItemViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_context_menu_item, viewGroup, false);
        if (this.isPopUp) {
            viewInflate.getLayoutParams().height = this.resources.getDimensionPixelSize(R$dimen.podcast_medium_list_item_height);
        }
        if (ViewType.goToPodcast.value != viewType) {
            if (ViewType.play.value != viewType) {
                if (ViewType.follow.value != viewType) {
                    if (ViewType.save.value != viewType) {
                        if (ViewType.download.value != viewType) {
                            if (ViewType.share.value != viewType) {
                                if (ViewType.markAsPlayed.value != viewType) {
                                    if (ViewType.carModePreset.value != viewType) {
                                        if (ViewType.report.value == viewType) {
                                            return new ReportItemViewHolder(viewInflate, this.resources, this.context);
                                        }
                                        logger.error("View type does not match any options in EpisodeOptionsElement: " + viewType);
                                        return null;
                                    }
                                    return new CarModePresetItemViewHolder(viewInflate, this.resources, this.context);
                                }
                                return new MarkAsPlayedViewHolder(viewInflate, this.resources, this.context);
                            }
                            return new ShareItemViewHolder(viewInflate, this.resources, this.context);
                        }
                        return new DownloadItemViewHolder(viewInflate, this.resources, this.context);
                    }
                    return new SaveItemViewHolder(viewInflate, this.resources, this.context);
                }
                return new FollowItemViewHolder(viewInflate, this.resources, this.context);
            }
            return new PlayItemViewHolder(viewInflate, this.resources);
        }
        return new GoToPodcastItemViewHolder(viewInflate, this.resources);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(final int position) {
        TemplateElement templateElement = this.options.get(position);
        if (templateElement instanceof GoToPodcastElement) {
            return ViewType.goToPodcast.value;
        }
        if (templateElement instanceof PlayEpisodeElement) {
            return ViewType.play.value;
        }
        if (templateElement instanceof FollowElement) {
            return ViewType.follow.value;
        }
        if (templateElement instanceof SaveElement) {
            return ViewType.save.value;
        }
        if (templateElement instanceof DownloadElement) {
            return ViewType.download.value;
        }
        if (templateElement instanceof CompletedElement) {
            return ViewType.markAsPlayed.value;
        }
        if (templateElement instanceof CarPresetElement) {
            return ViewType.carModePreset.value;
        }
        return templateElement instanceof ReportElement ? ViewType.report.value : ViewType.share.value;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final ContextMenuItemViewHolder contextMenuItemViewHolder, final int position) {
        TemplateElement templateElement = this.options.get(position);
        if (templateElement instanceof GoToPodcastElement) {
            ((GoToPodcastItemViewHolder) contextMenuItemViewHolder).bind(this.episodeOptionsElement, this.methodsDispatcher, this.ownerId, this.dismiss, this.isPopUp);
            return;
        }
        if (templateElement instanceof PlayEpisodeElement) {
            ((PlayItemViewHolder) contextMenuItemViewHolder).bind(this.episodeOptionsElement, this.methodsDispatcher, this.ownerId, this.dismiss, this.isPopUp);
            return;
        }
        if (templateElement instanceof FollowElement) {
            ((FollowItemViewHolder) contextMenuItemViewHolder).bind(this.episodeOptionsElement, this.methodsDispatcher, this.ownerId, this.lifecycleOwner, this.dismiss, this.isPopUp);
            return;
        }
        if (templateElement instanceof SaveElement) {
            ((SaveItemViewHolder) contextMenuItemViewHolder).bind(this.episodeOptionsElement, this.methodsDispatcher, this.ownerId, this.lifecycleOwner, this.dismiss, this.isPopUp);
            return;
        }
        if (templateElement instanceof DownloadElement) {
            ((DownloadItemViewHolder) contextMenuItemViewHolder).bind(this.episodeOptionsElement, this.methodsDispatcher, this.ownerId, this.lifecycleOwner, this.dismiss, this.isPopUp, this.pageType);
            return;
        }
        if (templateElement instanceof ShareElement) {
            ((ShareItemViewHolder) contextMenuItemViewHolder).bind(this.episodeOptionsElement, this.methodsDispatcher, this.ownerId, this.dismiss, this.isPopUp);
            return;
        }
        if (templateElement instanceof CompletedElement) {
            ((MarkAsPlayedViewHolder) contextMenuItemViewHolder).bind(this.episodeOptionsElement, this.methodsDispatcher, this.ownerId, this.lifecycleOwner, this.dismiss, this.isPopUp);
        } else if (templateElement instanceof CarPresetElement) {
            ((CarModePresetItemViewHolder) contextMenuItemViewHolder).bind(this.episodeOptionsElement, this.lifecycleOwner, this.dismiss, this.isPopUp, this.pageType);
        } else if (templateElement instanceof ReportElement) {
            ((ReportItemViewHolder) contextMenuItemViewHolder).bind(this.episodeOptionsElement, this.methodsDispatcher, this.ownerId, this.dismiss, this.isPopUp);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.options.size();
    }

    private List<TemplateElement> toArrayOptions(final EpisodeOptionsElement episodeOptionsElement) {
        ArrayList arrayList = new ArrayList();
        if (episodeOptionsElement.getPlayEpisode() != null) {
            arrayList.add(episodeOptionsElement.getPlayEpisode());
        }
        if (episodeOptionsElement.getDownload() != null) {
            arrayList.add(episodeOptionsElement.getDownload());
        }
        if (episodeOptionsElement.getSave() != null) {
            arrayList.add(episodeOptionsElement.getSave());
        }
        if (episodeOptionsElement.getCarPreset() != null) {
            arrayList.add(episodeOptionsElement.getCarPreset());
        }
        if (episodeOptionsElement.getCompleted() != null) {
            if (!(!isEpisodePlaybackCompleted(episodeOptionsElement) && isMarkedAsPlayed(episodeOptionsElement))) {
                arrayList.add(episodeOptionsElement.getCompleted());
            }
        }
        if (episodeOptionsElement.getGoToPodcast() != null) {
            arrayList.add(episodeOptionsElement.getGoToPodcast());
        }
        if (episodeOptionsElement.getFollow() != null) {
            arrayList.add(episodeOptionsElement.getFollow());
        }
        if (episodeOptionsElement.getShare() != null) {
            arrayList.add(episodeOptionsElement.getShare());
        }
        if (episodeOptionsElement.getReport() != null) {
            arrayList.add(episodeOptionsElement.getReport());
        }
        return arrayList;
    }

    private boolean isEpisodePlaybackCompleted(final EpisodeOptionsElement episodeOptionsElement) {
        Playback playback = Podcast.getPlayback();
        String strMediaId = playback.getMedia().mediaId();
        String id = episodeOptionsElement.getCompleted().getId();
        if (strMediaId != null && strMediaId.equals(id) && playback.getPlaybackState() == 3) {
            return ((double) playback.getPlaybackPosition()) / ((double) playback.getPlaybackDuration()) > 0.95d;
        }
        return true;
    }

    private boolean isMarkedAsPlayed(final EpisodeOptionsElement episodeOptionsElement) {
        return Podcast.getPlayback().isCompleted(episodeOptionsElement.getCompleted().getId());
    }
}
