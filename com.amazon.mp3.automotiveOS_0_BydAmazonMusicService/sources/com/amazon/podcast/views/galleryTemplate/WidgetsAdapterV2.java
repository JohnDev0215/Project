package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElement;
import Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElement;
import Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElement;
import Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElement;
import Podcast.Touch.GalleryTemplateInterface.v2_0.AboutElement;
import Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElement;
import Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastFeaturedPlayElement;
import Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElement;
import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElement;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.BookmarksElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.FollowsElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.JumpBackInElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.SavesElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.WidgetElement;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.TemplateContext;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
final class WidgetsAdapterV2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AboutElement aboutElement;
    private Integer aboutPosition;
    private final int barkerImageHeight;
    private final int barkerImageWidth;
    private int barkerItemThreshold;
    private final RoundedCornersTransformation barkerItemTransformation;
    private FeaturePlayViewHolder featurePlayViewHolder;
    private final int featuredHorizontalImageItemDimen;
    private final int featuredHorizontalItemWidth;
    private String header;
    private Integer headerPosition;
    private HeaderViewHolder headerViewHolder;
    private boolean isActionBarHidden;
    private boolean isLayoutComplete;
    private Integer itemCount;
    private final LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private Resources resources;
    private int screenWidth;
    private String subheader;
    private final TemplateContext templateContext;
    private List<WidgetElement> widgets;

    private enum ViewType {
        unknown(-1),
        visualShovelerCompact(0),
        featuredBarker(1),
        header(2),
        pillListingNavigator(3),
        about(4),
        follows(5),
        bookmarks(6),
        saves(7),
        regularBarker(8),
        jumpBackIn(9),
        featurePlay(10),
        episodeDescriptiveShoveler(11),
        thumbnailNavigator(12),
        experienceVisualShoveler(13);

        private final int value;

        ViewType(final int value) {
            this.value = value;
        }
    }

    public WidgetsAdapterV2(final TemplateContext templateContext, final LifecycleOwner lifecycleOwner, final String ownerId, final Resources resources, final MethodsDispatcher methodsDispatcher) {
        this.templateContext = templateContext;
        this.lifecycleOwner = lifecycleOwner;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.resources = resources;
        this.screenWidth = resources.getDisplayMetrics().widthPixels;
        this.barkerItemThreshold = resources.getDimensionPixelSize(R$dimen.podcast_small_barker_item_threshold);
        int barkerItemWidth = getBarkerItemWidth(this.screenWidth);
        this.barkerImageWidth = barkerItemWidth;
        int barkerItemHeight = getBarkerItemHeight(this.barkerItemThreshold, this.screenWidth, barkerItemWidth);
        this.barkerImageHeight = barkerItemHeight;
        this.featuredHorizontalImageItemDimen = getFeaturedHorizontalItemImageDimen(this.barkerItemThreshold, this.screenWidth, barkerItemHeight);
        this.featuredHorizontalItemWidth = getFeaturedHorizontalItemWidth(this.barkerItemThreshold, this.screenWidth, barkerItemWidth);
        this.barkerItemTransformation = new RoundedCornersTransformation(resources.getDimensionPixelSize(R$dimen.podcast_barker_item_image_corner_radius));
        this.itemCount = 0;
        this.isLayoutComplete = false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder experienceVisualShovelerViewHolder;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        if (ViewType.featuredBarker.value != i) {
            if (ViewType.regularBarker.value != i) {
                if (ViewType.visualShovelerCompact.value != i) {
                    if (ViewType.follows.value != i) {
                        if (ViewType.bookmarks.value != i) {
                            if (ViewType.jumpBackIn.value != i) {
                                if (ViewType.saves.value != i) {
                                    if (ViewType.pillListingNavigator.value != i) {
                                        if (ViewType.about.value != i) {
                                            if (ViewType.featurePlay.value != i) {
                                                if (ViewType.episodeDescriptiveShoveler.value != i) {
                                                    if (ViewType.thumbnailNavigator.value != i) {
                                                        if (ViewType.experienceVisualShoveler.value == i) {
                                                            experienceVisualShovelerViewHolder = new ExperienceVisualShovelerViewHolder(new ExperienceVisualShovelerView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.lifecycleOwner));
                                                        } else {
                                                            HeaderViewHolder headerViewHolder = new HeaderViewHolder(layoutInflaterFrom.inflate(R$layout.podcast_gallery_template_header, viewGroup, false));
                                                            this.headerViewHolder = headerViewHolder;
                                                            experienceVisualShovelerViewHolder = headerViewHolder;
                                                        }
                                                    } else {
                                                        experienceVisualShovelerViewHolder = new DeeplinkThumbnailNavigatorViewHolder(new DeeplinkThumbnailNavigatorView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher));
                                                    }
                                                } else {
                                                    experienceVisualShovelerViewHolder = new EpisodeDescriptiveShovelerViewHolder(new EpisodeDescriptiveShovelerView(viewGroup.getContext(), this.templateContext, this.ownerId, this.methodsDispatcher, this.lifecycleOwner));
                                                }
                                            } else {
                                                if (isScreenSizeGreaterThanMD()) {
                                                    this.featurePlayViewHolder = new FeaturePlayViewHolder(layoutInflaterFrom.inflate(R$layout.podcast_feature_play_item_wide_screen, viewGroup, false), this.lifecycleOwner, this.methodsDispatcher, this.ownerId, this.resources, Boolean.TRUE, Boolean.valueOf(this.isActionBarHidden));
                                                } else {
                                                    this.featurePlayViewHolder = new FeaturePlayViewHolder(layoutInflaterFrom.inflate(R$layout.podcast_feature_play_item, viewGroup, false), this.lifecycleOwner, this.methodsDispatcher, this.ownerId, this.resources, Boolean.FALSE, Boolean.valueOf(this.isActionBarHidden));
                                                }
                                                return this.featurePlayViewHolder;
                                            }
                                        } else {
                                            experienceVisualShovelerViewHolder = new AboutViewHolder(layoutInflaterFrom.inflate(R$layout.podcast_about_view, viewGroup, false), this.ownerId, this.methodsDispatcher);
                                        }
                                    } else {
                                        experienceVisualShovelerViewHolder = new PillListingNavigatorViewHolder(new PillListingNavigatorView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher));
                                    }
                                } else {
                                    experienceVisualShovelerViewHolder = new SavesVisualShovelerCompactViewHolder(this.lifecycleOwner, new SavesVisualShovelerCompactView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.lifecycleOwner));
                                }
                            } else {
                                experienceVisualShovelerViewHolder = new JumpBackInVisualShovelerCompactViewHolder(this.lifecycleOwner, new JumpBackInVisualShovelerCompactView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.lifecycleOwner));
                            }
                        } else {
                            experienceVisualShovelerViewHolder = new BookmarksVisualShovelerCompactViewHolder(this.lifecycleOwner, new BookmarksVisualShovelerCompactView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.lifecycleOwner));
                        }
                    } else {
                        experienceVisualShovelerViewHolder = new FollowsVisualShovelerCompactViewHolder(this.lifecycleOwner, new FollowsVisualShovelerCompactView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.lifecycleOwner));
                    }
                } else {
                    experienceVisualShovelerViewHolder = new VisualShovelerCompactViewHolder(new VisualShovelerCompactView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.lifecycleOwner));
                }
                return experienceVisualShovelerViewHolder;
            }
            return new RegularBarkerViewHolder(layoutInflaterFrom.inflate(R$layout.podcast_regular_barker_view, viewGroup, false), this.barkerImageWidth, this.barkerImageHeight, this.screenWidth, this.barkerItemThreshold, this.resources, this.barkerItemTransformation, this.ownerId, this.methodsDispatcher);
        }
        return new FeaturedBarkerViewHolder(layoutInflaterFrom.inflate(R$layout.podcast_featured_barker_view, viewGroup, false), this.barkerImageWidth, this.barkerImageHeight, this.featuredHorizontalImageItemDimen, this.featuredHorizontalItemWidth, this.screenWidth, this.barkerItemThreshold, this.resources, this.barkerItemTransformation, this.ownerId, this.methodsDispatcher);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        int widgetPosition = getWidgetPosition(position);
        if (viewHolder instanceof FeaturedBarkerViewHolder) {
            ((FeaturedBarkerViewHolder) viewHolder).bind((FeaturedBarkerElement) this.widgets.get(widgetPosition));
            return;
        }
        if (viewHolder instanceof RegularBarkerViewHolder) {
            ((RegularBarkerViewHolder) viewHolder).bind((RegularBarkerElement) this.widgets.get(widgetPosition));
            return;
        }
        if (viewHolder instanceof VisualShovelerCompactViewHolder) {
            ((VisualShovelerCompactViewHolder) viewHolder).bind((VisualShovelerCompactElement) this.widgets.get(widgetPosition));
            return;
        }
        if (viewHolder instanceof FollowsVisualShovelerCompactViewHolder) {
            ((FollowsVisualShovelerCompactViewHolder) viewHolder).bind((FollowsElement) this.widgets.get(widgetPosition));
            return;
        }
        if (viewHolder instanceof BookmarksVisualShovelerCompactViewHolder) {
            ((BookmarksVisualShovelerCompactViewHolder) viewHolder).bind((BookmarksElement) this.widgets.get(widgetPosition));
            return;
        }
        if (viewHolder instanceof JumpBackInVisualShovelerCompactViewHolder) {
            ((JumpBackInVisualShovelerCompactViewHolder) viewHolder).bind((JumpBackInElement) this.widgets.get(widgetPosition));
            return;
        }
        if (viewHolder instanceof SavesVisualShovelerCompactViewHolder) {
            ((SavesVisualShovelerCompactViewHolder) viewHolder).bind((SavesElement) this.widgets.get(widgetPosition));
            return;
        }
        if (viewHolder instanceof PillListingNavigatorViewHolder) {
            ((PillListingNavigatorViewHolder) viewHolder).bind((PillListingNavigatorElement) this.widgets.get(widgetPosition));
            return;
        }
        if (viewHolder instanceof AboutViewHolder) {
            ((AboutViewHolder) viewHolder).bind(this.aboutElement);
            return;
        }
        if (viewHolder instanceof FeaturePlayViewHolder) {
            WidgetElement widgetElement = this.widgets.get(widgetPosition);
            if (widgetElement instanceof EpisodeFeaturedPlayElement) {
                ((FeaturePlayViewHolder) viewHolder).bind((EpisodeFeaturedPlayElement) widgetElement);
                return;
            } else {
                if (widgetElement instanceof PodcastFeaturedPlayElement) {
                    ((FeaturePlayViewHolder) viewHolder).bind((PodcastFeaturedPlayElement) widgetElement);
                    return;
                }
                return;
            }
        }
        if (viewHolder instanceof EpisodeDescriptiveShovelerViewHolder) {
            ((EpisodeDescriptiveShovelerViewHolder) viewHolder).bind((EpisodeDescriptiveShovelerElement) this.widgets.get(widgetPosition));
        } else if (viewHolder instanceof DeeplinkThumbnailNavigatorViewHolder) {
            ((DeeplinkThumbnailNavigatorViewHolder) viewHolder).bind((ThumbnailNavigatorWidgetElement) this.widgets.get(widgetPosition));
        } else if (viewHolder instanceof ExperienceVisualShovelerViewHolder) {
            ((ExperienceVisualShovelerViewHolder) viewHolder).bind((ExperienceVisualShovelerElement) this.widgets.get(widgetPosition));
        } else {
            ((HeaderViewHolder) viewHolder).bind(this.header, this.subheader);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemCount.intValue();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(final int position) {
        Integer num = this.headerPosition;
        if (num != null && num.intValue() == position) {
            return ViewType.header.value;
        }
        Integer num2 = this.aboutPosition;
        if (num2 != null && num2.intValue() == position) {
            return ViewType.about.value;
        }
        WidgetElement widgetElement = this.widgets.get(getWidgetPosition(position));
        if (widgetElement instanceof VisualShovelerCompactElement) {
            return ViewType.visualShovelerCompact.value;
        }
        if (widgetElement instanceof FollowsElement) {
            return ViewType.follows.value;
        }
        if (widgetElement instanceof BookmarksElement) {
            return ViewType.bookmarks.value;
        }
        if (widgetElement instanceof JumpBackInElement) {
            return ViewType.jumpBackIn.value;
        }
        if (widgetElement instanceof SavesElement) {
            return ViewType.saves.value;
        }
        if (widgetElement instanceof FeaturedBarkerElement) {
            return ViewType.featuredBarker.value;
        }
        if (widgetElement instanceof RegularBarkerElement) {
            return ViewType.regularBarker.value;
        }
        if (widgetElement instanceof PillListingNavigatorElement) {
            return ViewType.pillListingNavigator.value;
        }
        if (!(widgetElement instanceof EpisodeFeaturedPlayElement) && !(widgetElement instanceof PodcastFeaturedPlayElement)) {
            if (widgetElement instanceof EpisodeDescriptiveShovelerElement) {
                return ViewType.episodeDescriptiveShoveler.value;
            }
            if (widgetElement instanceof ThumbnailNavigatorWidgetElement) {
                return ViewType.thumbnailNavigator.value;
            }
            return widgetElement instanceof ExperienceVisualShovelerElement ? ViewType.experienceVisualShoveler.value : ViewType.unknown.value;
        }
        return ViewType.featurePlay.value;
    }

    public void bind(final List<WidgetElement> widgets, final String header, final String subheader, final AboutElement about) {
        ArrayList arrayList = new ArrayList(widgets);
        this.widgets = arrayList;
        this.itemCount = Integer.valueOf(arrayList.size());
        bindHeader(header, subheader);
        bindAbout(about);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(final RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder.getAdapterPosition() <= 2 || this.isLayoutComplete) {
            if (viewHolder instanceof FeaturedBarkerViewHolder) {
                ((FeaturedBarkerViewHolder) viewHolder).handleOnViewed();
                return;
            }
            if (viewHolder instanceof RegularBarkerViewHolder) {
                ((RegularBarkerViewHolder) viewHolder).handleOnViewed();
                return;
            }
            if (viewHolder instanceof VisualShovelerCompactViewHolder) {
                ((VisualShovelerCompactViewHolder) viewHolder).handleOnViewed();
                return;
            }
            if (viewHolder instanceof FollowsVisualShovelerCompactViewHolder) {
                ((FollowsVisualShovelerCompactViewHolder) viewHolder).handleOnViewed();
                return;
            }
            if (viewHolder instanceof BookmarksVisualShovelerCompactViewHolder) {
                ((BookmarksVisualShovelerCompactViewHolder) viewHolder).handleOnViewed();
                return;
            }
            if (viewHolder instanceof JumpBackInVisualShovelerCompactViewHolder) {
                ((JumpBackInVisualShovelerCompactViewHolder) viewHolder).handleOnViewed();
                return;
            }
            if (viewHolder instanceof SavesVisualShovelerCompactViewHolder) {
                ((SavesVisualShovelerCompactViewHolder) viewHolder).handleOnViewed();
                return;
            }
            if (viewHolder instanceof PillListingNavigatorViewHolder) {
                ((PillListingNavigatorViewHolder) viewHolder).handleOnViewed();
                return;
            }
            if (viewHolder instanceof FeaturePlayViewHolder) {
                ((FeaturePlayViewHolder) viewHolder).handleOnViewed();
                return;
            }
            if (viewHolder instanceof EpisodeDescriptiveShovelerViewHolder) {
                ((EpisodeDescriptiveShovelerViewHolder) viewHolder).handleOnViewed();
            } else if (viewHolder instanceof DeeplinkThumbnailNavigatorViewHolder) {
                ((DeeplinkThumbnailNavigatorViewHolder) viewHolder).handleOnViewed();
            } else if (viewHolder instanceof ExperienceVisualShovelerViewHolder) {
                ((ExperienceVisualShovelerViewHolder) viewHolder).handleOnViewed();
            }
        }
    }

    public void setIsLayoutComplete(boolean isComplete) {
        this.isLayoutComplete = isComplete;
    }

    public void setIsActionBarHidden(boolean isHidden) {
        this.isActionBarHidden = isHidden;
    }

    void addItems(final List<WidgetElement> items) {
        if (this.widgets == null || items == null || items.isEmpty()) {
            return;
        }
        int iIntValue = this.itemCount.intValue();
        this.widgets.addAll(items);
        this.itemCount = Integer.valueOf(this.itemCount.intValue() + items.size());
        notifyItemRangeInserted(iIntValue, items.size());
    }

    private void bindHeader(final String header, final String subheader) {
        if (StringUtils.isBlank(header) && StringUtils.isBlank(subheader)) {
            return;
        }
        this.header = header;
        this.subheader = subheader;
        this.headerPosition = 0;
        this.itemCount = Integer.valueOf(this.itemCount.intValue() + 1);
    }

    private void bindAbout(final AboutElement about) {
        if (about == null) {
            return;
        }
        this.aboutElement = about;
        this.aboutPosition = Integer.valueOf(this.headerPosition == null ? 0 : 1);
        this.itemCount = Integer.valueOf(this.itemCount.intValue() + 1);
    }

    private int getBarkerItemWidth(final int screenWidth) {
        return screenWidth - ((this.resources.getDimensionPixelSize(R$dimen.margin) * 2) + (this.resources.getDimensionPixelSize(R$dimen.gutter) * 2));
    }

    private int getBarkerItemHeight(final int featuredBarkerThreshold, final int screenWidth, final int barkerImageWidth) {
        if (screenWidth > featuredBarkerThreshold) {
            return barkerImageWidth / 4;
        }
        return (barkerImageWidth * 9) / 16;
    }

    private int getFeaturedHorizontalItemImageDimen(final int featuredBarkerThreshold, final int screenWidth, final int barkerImageHeight) {
        if (screenWidth > featuredBarkerThreshold) {
            return this.resources.getDimensionPixelSize(R$dimen.podcast_featured_horizontal_item_image_dimen);
        }
        return (barkerImageHeight * 2) / 5;
    }

    private int getFeaturedHorizontalItemWidth(final int featuredBarkerThreshold, final int screenWidth, final int barkerImageWidth) {
        if (screenWidth > featuredBarkerThreshold) {
            return barkerImageWidth / 2;
        }
        return barkerImageWidth - (this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_small) * 2);
    }

    private boolean isScreenSizeGreaterThanMD() {
        DisplayMetrics displayMetrics = this.resources.getDisplayMetrics();
        return ((float) displayMetrics.widthPixels) / displayMetrics.density >= 480.0f;
    }

    private int getWidgetPosition(final int position) {
        return (this.headerPosition == null || this.aboutPosition == null) ? (this.header == null && this.aboutPosition == null) ? position : position - 1 : position - 2;
    }
}
