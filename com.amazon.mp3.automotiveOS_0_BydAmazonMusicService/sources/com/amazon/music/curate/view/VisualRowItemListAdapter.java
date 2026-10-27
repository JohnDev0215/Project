package com.amazon.music.curate.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.R;
import com.amazon.music.curate.model.AddButtonStyle;
import com.amazon.music.curate.model.DetailPageHeader;
import com.amazon.music.curate.model.EmptyDetailPageHeader;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.model.VisualRowHeader;
import com.amazon.music.curate.model.VisualRowItem;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.DownloadStatus;
import com.amazon.music.curate.provider.PlaybackItem;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.PlaybackStatus;
import com.squareup.picasso.Picasso;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: VisualRowItemListAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 42\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u000545678B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0018\u0010 \u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u001a\u0010$\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\u0018\u0010(\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020)2\u0006\u0010&\u001a\u00020*H\u0002J\u0018\u0010+\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J\u0010\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u00020!2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u00100\u001a\u00020%2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u00101\u001a\u00020)2\u0006\u0010-\u001a\u00020.H\u0002J\u0018\u00102\u001a\u00020\u00032\u0006\u0010-\u001a\u00020.2\u0006\u00103\u001a\u00020\u0018H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/amazon/music/curate/view/VisualRowItemListAdapter;", "Landroidx/paging/PagedListAdapter;", "Lcom/amazon/music/curate/model/VisualModel;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "fragment", "Landroidx/fragment/app/Fragment;", "playbackProvider", "Lcom/amazon/music/curate/provider/PlaybackProvider;", "downloadProvider", "Lcom/amazon/music/curate/provider/DownloadProvider;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/curate/provider/PlaybackProvider;Lcom/amazon/music/curate/provider/DownloadProvider;)V", "currentPlaybackStatus", "Lcom/amazon/music/curate/provider/PlaybackStatus;", "currentlyPlayingTrackId", "", "playbackItem", "Landroidx/lifecycle/LiveData;", "Lcom/amazon/music/curate/provider/PlaybackItem;", "roundedCornerImageTransformation", "Lcom/amazon/music/curate/view/RoundedCornerImageTransformation;", "roundedCornersGradientTransformation", "Lcom/amazon/music/curate/view/RoundedCornersGradientTransformation;", "thumbnailTransformation", "getItemViewType", "", "position", "onBindEmptyHeaderView", "", "holder", "Lcom/amazon/music/curate/view/VisualRowItemListAdapter$EmptyHeaderViewHolder;", "emptyDetailPageHeader", "Lcom/amazon/music/curate/model/EmptyDetailPageHeader;", "onBindHeaderView", "Lcom/amazon/music/curate/view/VisualRowItemListAdapter$HeaderViewHolder;", "detailPageHeader", "Lcom/amazon/music/curate/model/DetailPageHeader;", "onBindItemViewHolder", "Lcom/amazon/music/curate/view/VisualRowItemListAdapter$ViewHolder;", "model", "Lcom/amazon/music/curate/model/VisualRowItem;", "onBindRowHeaderViewHolder", "Lcom/amazon/music/curate/view/VisualRowItemListAdapter$RowHeaderViewHolder;", "Lcom/amazon/music/curate/model/VisualRowHeader;", "onBindViewHolder", "onCreateEmptyHeaderViewHolder", "parent", "Landroid/view/ViewGroup;", "onCreateHeaderViewHolder", "onCreateItemViewHolder", "onCreateRowHeaderViewHolder", "onCreateViewHolder", "viewType", "Companion", "EmptyHeaderViewHolder", "HeaderViewHolder", "RowHeaderViewHolder", "ViewHolder", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VisualRowItemListAdapter extends PagedListAdapter<VisualModel, RecyclerView.ViewHolder> {
    private static final Logger LOG;
    private PlaybackStatus currentPlaybackStatus;
    private String currentlyPlayingTrackId;
    private final DownloadProvider downloadProvider;
    private final Fragment fragment;
    private final LiveData<PlaybackItem> playbackItem;
    private final PlaybackProvider playbackProvider;
    private final RoundedCornerImageTransformation roundedCornerImageTransformation;
    private final RoundedCornersGradientTransformation roundedCornersGradientTransformation;
    private final RoundedCornerImageTransformation thumbnailTransformation;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindHeaderView$lambda-5$lambda-4, reason: not valid java name */
    public static final void m1974onBindHeaderView$lambda5$lambda4(View view) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VisualRowItemListAdapter(Fragment fragment, PlaybackProvider playbackProvider, DownloadProvider downloadProvider) {
        super(new DiffUtil.ItemCallback<VisualModel>() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter.1
            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areItemsTheSame(VisualModel item1, VisualModel item2) {
                Intrinsics.checkNotNullParameter(item1, "item1");
                Intrinsics.checkNotNullParameter(item2, "item2");
                if (!Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(item1.getClass()), Reflection.getOrCreateKotlinClass(item2.getClass()))) {
                    return false;
                }
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(item1.getClass());
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(VisualRowItem.class))) {
                    return Intrinsics.areEqual(((VisualRowItem) item1).getId(), ((VisualRowItem) item2).getId());
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(VisualRowHeader.class))) {
                    return Intrinsics.areEqual(((VisualRowHeader) item1).getTitle(), ((VisualRowHeader) item2).getTitle());
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(DetailPageHeader.class))) {
                    return Intrinsics.areEqual(((DetailPageHeader) item1).getId(), ((DetailPageHeader) item2).getId());
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(EmptyDetailPageHeader.class))) {
                    return true;
                }
                throw new IllegalArgumentException(Intrinsics.stringPlus("VisualRowItemListAdapter does not support type ", Reflection.getOrCreateKotlinClass(item1.getClass())));
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areContentsTheSame(VisualModel item1, VisualModel item2) {
                Intrinsics.checkNotNullParameter(item1, "item1");
                Intrinsics.checkNotNullParameter(item2, "item2");
                return Intrinsics.areEqual(item1, item2);
            }
        });
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        Intrinsics.checkNotNullParameter(downloadProvider, "downloadProvider");
        this.fragment = fragment;
        this.playbackProvider = playbackProvider;
        this.downloadProvider = downloadProvider;
        LiveData<PlaybackItem> playbackItem = playbackProvider.getPlaybackItem();
        this.playbackItem = playbackItem;
        this.thumbnailTransformation = new RoundedCornerImageTransformation(fragment.getResources().getDimension(R.dimen.image_corner_radius_small));
        int dimensionPixelSize = fragment.getResources().getDimensionPixelSize(R.dimen.spacer_epic);
        Resources resources = fragment.getResources();
        int i = R.dimen.image_corner_radius_medium;
        this.roundedCornersGradientTransformation = new RoundedCornersGradientTransformation(dimensionPixelSize, resources.getDimension(i));
        this.roundedCornerImageTransformation = new RoundedCornerImageTransformation(fragment.getResources().getDimension(i));
        playbackItem.observe(fragment, new Observer() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda13
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VisualRowItemListAdapter.m1969_init_$lambda0(this.f$0, (PlaybackItem) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1969_init_$lambda0(VisualRowItemListAdapter this$0, PlaybackItem playbackItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.currentlyPlayingTrackId = playbackItem == null ? null : playbackItem.getTrackId();
        this$0.currentPlaybackStatus = playbackItem != null ? playbackItem.getStatus() : null;
    }

    static {
        Logger logger = LoggerFactory.getLogger(VisualRowItemListAdapter.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(VisualRowItemL…r::class.java.simpleName)");
        LOG = logger;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        VisualModel item = getItem(position);
        if (item == null) {
            return 2;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(item.getClass());
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(DetailPageHeader.class))) {
            return 0;
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(VisualRowHeader.class))) {
            return 1;
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(VisualRowItem.class))) {
            return 2;
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(EmptyDetailPageHeader.class))) {
            return 3;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Invalid model type ", Reflection.getOrCreateKotlinClass(item.getClass())));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 0) {
            return onCreateHeaderViewHolder(parent);
        }
        if (viewType == 1) {
            return onCreateRowHeaderViewHolder(parent);
        }
        if (viewType == 2) {
            return onCreateItemViewHolder(parent);
        }
        if (viewType == 3) {
            return onCreateEmptyHeaderViewHolder(parent);
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Invalid view type int ", Integer.valueOf(viewType)));
    }

    private final EmptyHeaderViewHolder onCreateEmptyHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_detail_header_view, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new EmptyHeaderViewHolder(view);
    }

    private final HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_header_view, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new HeaderViewHolder(view);
    }

    private final RowHeaderViewHolder onCreateRowHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visual_row_header, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new RowHeaderViewHolder(view);
    }

    private final ViewHolder onCreateItemViewHolder(ViewGroup parent) {
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.visual_row_item, parent, false);
        if (viewInflate == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.View");
        }
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        VisualModel item = getItem(position);
        if (item == null) {
            onBindItemViewHolder((ViewHolder) holder, null);
            return;
        }
        int itemViewType = getItemViewType(position);
        if (itemViewType == 0) {
            onBindHeaderView((HeaderViewHolder) holder, (DetailPageHeader) item);
            return;
        }
        if (itemViewType == 1) {
            onBindRowHeaderViewHolder((RowHeaderViewHolder) holder, (VisualRowHeader) item);
        } else if (itemViewType == 2) {
            onBindItemViewHolder((ViewHolder) holder, (VisualRowItem) item);
        } else {
            if (itemViewType == 3) {
                onBindEmptyHeaderView((EmptyHeaderViewHolder) holder, (EmptyDetailPageHeader) item);
                return;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("Invalid view type int ", Integer.valueOf(itemViewType)));
        }
    }

    private final void onBindEmptyHeaderView(EmptyHeaderViewHolder holder, EmptyDetailPageHeader emptyDetailPageHeader) {
        View view = holder.getView();
        View viewFindViewById = view.findViewById(R.id.empty_detail_header_text);
        Intrinsics.checkNotNull(viewFindViewById);
        ((TextView) viewFindViewById).setText(emptyDetailPageHeader.getTitle());
        View viewFindViewById2 = view.findViewById(R.id.empty_detail_sub_text);
        Intrinsics.checkNotNull(viewFindViewById2);
        ((TextView) viewFindViewById2).setText(emptyDetailPageHeader.getSubTitle());
    }

    private final void onBindHeaderView(HeaderViewHolder holder, final DetailPageHeader detailPageHeader) {
        View view = holder.getView();
        View viewFindViewById = view.findViewById(R.id.play_button);
        Intrinsics.checkNotNull(viewFindViewById);
        ImageView imageView = (ImageView) viewFindViewById;
        imageView.setEnabled(detailPageHeader.getOnPlayButtonClick() != null);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisualRowItemListAdapter.m1970onBindHeaderView$lambda1(detailPageHeader, view2);
            }
        });
        View viewFindViewById2 = view.findViewById(R.id.shuffle_button);
        Intrinsics.checkNotNull(viewFindViewById2);
        ImageView imageView2 = (ImageView) viewFindViewById2;
        imageView2.setEnabled(detailPageHeader.getOnShuffleButtonClick() != null);
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisualRowItemListAdapter.m1971onBindHeaderView$lambda2(detailPageHeader, view2);
            }
        });
        View viewFindViewById3 = view.findViewById(R.id.download_button);
        Intrinsics.checkNotNull(viewFindViewById3);
        final ImageView imageView3 = (ImageView) viewFindViewById3;
        imageView3.setEnabled(detailPageHeader.getOnDownloadButtonClick() != null);
        DownloadProvider downloadProvider = this.downloadProvider;
        String id = detailPageHeader.getId();
        Context context = this.fragment.getContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(context, "fragment.context!!");
        downloadProvider.getUserPlaylistDownloadStatus(id, context).observe(this.fragment, new Observer() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VisualRowItemListAdapter.m1972onBindHeaderView$lambda5(imageView3, detailPageHeader, (DownloadStatus) obj);
            }
        });
        View viewFindViewById4 = view.findViewById(R.id.edit_button);
        Intrinsics.checkNotNull(viewFindViewById4);
        ImageView imageView4 = (ImageView) viewFindViewById4;
        imageView4.setEnabled(detailPageHeader.getOnEditButtonClick() != null);
        imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisualRowItemListAdapter.m1975onBindHeaderView$lambda6(detailPageHeader, view2);
            }
        });
        View viewFindViewById5 = view.findViewById(R.id.share_button);
        Intrinsics.checkNotNull(viewFindViewById5);
        ImageView imageView5 = (ImageView) viewFindViewById5;
        imageView5.setVisibility(detailPageHeader.getOnShareButtonClick() != null ? 0 : 8);
        imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisualRowItemListAdapter.m1976onBindHeaderView$lambda7(detailPageHeader, view2);
            }
        });
        View viewFindViewById6 = view.findViewById(R.id.station_button);
        Intrinsics.checkNotNull(viewFindViewById6);
        ImageView imageView6 = (ImageView) viewFindViewById6;
        imageView6.setEnabled(detailPageHeader.getOnStationButtonClick() != null);
        imageView6.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisualRowItemListAdapter.m1977onBindHeaderView$lambda8(detailPageHeader, view2);
            }
        });
        View viewFindViewById7 = view.findViewById(R.id.more_button);
        Intrinsics.checkNotNull(viewFindViewById7);
        ImageView imageView7 = (ImageView) viewFindViewById7;
        imageView7.setVisibility(detailPageHeader.getOnMoreButtonClick() != null ? 0 : 8);
        imageView7.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisualRowItemListAdapter.m1978onBindHeaderView$lambda9(detailPageHeader, view2);
            }
        });
        View viewFindViewById8 = view.findViewById(R.id.label_text);
        Intrinsics.checkNotNull(viewFindViewById8);
        ((TextView) viewFindViewById8).setText(detailPageHeader.getLabelText());
        View viewFindViewById9 = view.findViewById(R.id.headline_text);
        Intrinsics.checkNotNull(viewFindViewById9);
        ((TextView) viewFindViewById9).setText(detailPageHeader.getHeadlineText());
        View viewFindViewById10 = view.findViewById(R.id.primary_text);
        Intrinsics.checkNotNull(viewFindViewById10);
        TextView textView = (TextView) viewFindViewById10;
        textView.setText(detailPageHeader.getPrimaryText());
        textView.setVisibility(detailPageHeader.getPrimaryText() != null ? 0 : 8);
        View viewFindViewById11 = view.findViewById(R.id.secondary_text);
        Intrinsics.checkNotNull(viewFindViewById11);
        TextView textView2 = (TextView) viewFindViewById11;
        textView2.setText(detailPageHeader.getSecondaryText());
        textView2.setVisibility(detailPageHeader.getSecondaryText() != null ? 0 : 8);
        View viewFindViewById12 = view.findViewById(R.id.tertiary_text);
        Intrinsics.checkNotNull(viewFindViewById12);
        TextView textView3 = (TextView) viewFindViewById12;
        textView3.setText(detailPageHeader.getTertiaryText());
        textView3.setVisibility(detailPageHeader.getTertiaryText() != null ? 0 : 8);
        View viewFindViewById13 = view.findViewById(R.id.playlist_image);
        Intrinsics.checkNotNull(viewFindViewById13);
        ImageView imageView8 = (ImageView) viewFindViewById13;
        if (detailPageHeader.getImageUrl() == null) {
            imageView8.setVisibility(8);
            return;
        }
        imageView8.setVisibility(0);
        String string = this.fragment.getResources().getString(R.string.detail_page_header_type);
        Intrinsics.checkNotNullExpressionValue(string, "fragment.resources.getSt….detail_page_header_type)");
        String string2 = this.fragment.getResources().getString(R.string.detail_page_header_type_portrait);
        Intrinsics.checkNotNullExpressionValue(string2, "fragment.resources.getSt…age_header_type_portrait)");
        ImageLoader.INSTANCE.loadImage(detailPageHeader.getImageUrl()).withTransformation(Intrinsics.areEqual(string, string2) ? this.roundedCornersGradientTransformation : this.roundedCornerImageTransformation).into(imageView8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindHeaderView$lambda-1, reason: not valid java name */
    public static final void m1970onBindHeaderView$lambda1(DetailPageHeader detailPageHeader, View view) {
        Intrinsics.checkNotNullParameter(detailPageHeader, "$detailPageHeader");
        Function0<Unit> onPlayButtonClick = detailPageHeader.getOnPlayButtonClick();
        if (onPlayButtonClick == null) {
            return;
        }
        onPlayButtonClick.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindHeaderView$lambda-2, reason: not valid java name */
    public static final void m1971onBindHeaderView$lambda2(DetailPageHeader detailPageHeader, View view) {
        Intrinsics.checkNotNullParameter(detailPageHeader, "$detailPageHeader");
        Function0<Unit> onShuffleButtonClick = detailPageHeader.getOnShuffleButtonClick();
        if (onShuffleButtonClick == null) {
            return;
        }
        onShuffleButtonClick.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindHeaderView$lambda-5, reason: not valid java name */
    public static final void m1972onBindHeaderView$lambda5(ImageView downloadButton, final DetailPageHeader detailPageHeader, DownloadStatus downloadStatus) {
        Intrinsics.checkNotNullParameter(downloadButton, "$downloadButton");
        Intrinsics.checkNotNullParameter(detailPageHeader, "$detailPageHeader");
        downloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VisualRowItemListAdapter.m1973onBindHeaderView$lambda5$lambda3(detailPageHeader, view);
            }
        });
        if (DownloadStatus.DOWNLOADED.equals(downloadStatus)) {
            LOG.debug("Playlist downloaded");
            downloadButton.setImageResource(R.drawable.ic_action_downloaded);
            downloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda15
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VisualRowItemListAdapter.m1974onBindHeaderView$lambda5$lambda4(view);
                }
            });
        } else if (DownloadStatus.DOWNLOAD_CORRUPTED.equals(downloadStatus) || DownloadStatus.ERROR_DOWNLOADING.equals(downloadStatus)) {
            LOG.debug("Playlist download failed");
            downloadButton.setImageResource(R.drawable.ic_action_downloadfailed);
        } else {
            LOG.debug("Playlist not downloaded");
            downloadButton.setImageResource(R.drawable.ic_action_download);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindHeaderView$lambda-5$lambda-3, reason: not valid java name */
    public static final void m1973onBindHeaderView$lambda5$lambda3(DetailPageHeader detailPageHeader, View view) {
        Intrinsics.checkNotNullParameter(detailPageHeader, "$detailPageHeader");
        Function0<Unit> onDownloadButtonClick = detailPageHeader.getOnDownloadButtonClick();
        if (onDownloadButtonClick == null) {
            return;
        }
        onDownloadButtonClick.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindHeaderView$lambda-6, reason: not valid java name */
    public static final void m1975onBindHeaderView$lambda6(DetailPageHeader detailPageHeader, View view) {
        Intrinsics.checkNotNullParameter(detailPageHeader, "$detailPageHeader");
        Function0<Unit> onEditButtonClick = detailPageHeader.getOnEditButtonClick();
        if (onEditButtonClick == null) {
            return;
        }
        onEditButtonClick.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindHeaderView$lambda-7, reason: not valid java name */
    public static final void m1976onBindHeaderView$lambda7(DetailPageHeader detailPageHeader, View view) {
        Intrinsics.checkNotNullParameter(detailPageHeader, "$detailPageHeader");
        Function0<Unit> onShareButtonClick = detailPageHeader.getOnShareButtonClick();
        if (onShareButtonClick == null) {
            return;
        }
        onShareButtonClick.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindHeaderView$lambda-8, reason: not valid java name */
    public static final void m1977onBindHeaderView$lambda8(DetailPageHeader detailPageHeader, View view) {
        Intrinsics.checkNotNullParameter(detailPageHeader, "$detailPageHeader");
        Function0<Unit> onStationButtonClick = detailPageHeader.getOnStationButtonClick();
        if (onStationButtonClick == null) {
            return;
        }
        onStationButtonClick.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindHeaderView$lambda-9, reason: not valid java name */
    public static final void m1978onBindHeaderView$lambda9(DetailPageHeader detailPageHeader, View view) {
        Intrinsics.checkNotNullParameter(detailPageHeader, "$detailPageHeader");
        Function0<Unit> onMoreButtonClick = detailPageHeader.getOnMoreButtonClick();
        if (onMoreButtonClick == null) {
            return;
        }
        onMoreButtonClick.invoke();
    }

    private final void onBindRowHeaderViewHolder(RowHeaderViewHolder holder, final VisualRowHeader model) {
        View view = holder.getView();
        ((TextView) view.findViewById(R.id.title)).setText(model.getTitle());
        TextView textView = (TextView) view.findViewById(R.id.sub_title);
        if (model.getSubTitle() != null) {
            textView.setVisibility(0);
            textView.setText(model.getSubTitle());
        } else {
            textView.setVisibility(8);
        }
        View viewFindViewById = view.findViewById(R.id.button);
        Intrinsics.checkNotNull(viewFindViewById);
        TextView textView2 = (TextView) viewFindViewById;
        if (model.getButton() != null) {
            textView2.setVisibility(0);
            textView2.setText(model.getButton().getText());
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda12
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    VisualRowItemListAdapter.m1984onBindRowHeaderViewHolder$lambda10(model, view2);
                }
            });
        } else {
            textView2.setVisibility(8);
            textView2.setOnClickListener(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindRowHeaderViewHolder$lambda-10, reason: not valid java name */
    public static final void m1984onBindRowHeaderViewHolder$lambda10(VisualRowHeader model, View view) {
        Intrinsics.checkNotNullParameter(model, "$model");
        model.getButton().getOnClick().invoke();
    }

    private final void onBindItemViewHolder(ViewHolder holder, final VisualRowItem model) {
        VisualRowItem.AddButton addButton;
        final View view = holder.getView();
        View viewFindViewById = view.findViewById(R.id.visual_row_item);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        }
        ((ConstraintLayout) viewFindViewById).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisualRowItemListAdapter.m1979onBindItemViewHolder$lambda11(model, view2);
            }
        });
        View viewFindViewById2 = view.findViewById(R.id.index_text);
        Intrinsics.checkNotNull(viewFindViewById2);
        TextView textView = (TextView) viewFindViewById2;
        if ((model == null ? null : model.getIndexText()) != null) {
            textView.setText(model.getIndexText());
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        View viewFindViewById3 = view.findViewById(R.id.primary_text);
        Intrinsics.checkNotNull(viewFindViewById3);
        TextView textView2 = (TextView) viewFindViewById3;
        textView2.setText(model == null ? null : model.getPrimaryText());
        View viewFindViewById4 = view.findViewById(R.id.secondary_text);
        Intrinsics.checkNotNull(viewFindViewById4);
        TextView textView3 = (TextView) viewFindViewById4;
        textView3.setText(model == null ? null : model.getSecondaryText());
        View viewFindViewById5 = view.findViewById(R.id.add_button);
        Intrinsics.checkNotNull(viewFindViewById5);
        final ImageView imageView = (ImageView) viewFindViewById5;
        imageView.setVisibility((model == null ? null : model.getAddButton()) != null ? 0 : 8);
        if (((model == null || (addButton = model.getAddButton()) == null) ? null : addButton.getStyle()) == AddButtonStyle.ADD) {
            imageView.setImageResource(R.drawable.ic_action_add);
        } else {
            imageView.setImageResource(R.drawable.ic_action_add_inline);
        }
        View viewFindViewById6 = view.findViewById(R.id.download_status_icon);
        Intrinsics.checkNotNull(viewFindViewById6);
        final ImageView imageView2 = (ImageView) viewFindViewById6;
        if (model != null && model.getShowDownloadStatus()) {
            DownloadProvider downloadProvider = this.downloadProvider;
            String id = model.getId();
            Intrinsics.checkNotNull(id);
            Context context = this.fragment.getContext();
            Intrinsics.checkNotNull(context);
            Intrinsics.checkNotNullExpressionValue(context, "fragment.context!!");
            downloadProvider.getTrackDownloadStatus(id, context).observe(this.fragment, new Observer() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    VisualRowItemListAdapter.m1980onBindItemViewHolder$lambda12(imageView2, (DownloadStatus) obj);
                }
            });
        } else {
            imageView2.setVisibility(8);
        }
        View viewFindViewById7 = view.findViewById(R.id.thumbnail_image);
        Intrinsics.checkNotNull(viewFindViewById7);
        final ImageView imageView3 = (ImageView) viewFindViewById7;
        imageView.setEnabled(true);
        imageView3.setEnabled(true);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisualRowItemListAdapter.m1981onBindItemViewHolder$lambda13(model, imageView, imageView3, view2);
            }
        });
        View viewFindViewById8 = view.findViewById(R.id.more_button);
        Intrinsics.checkNotNull(viewFindViewById8);
        ImageView imageView4 = (ImageView) viewFindViewById8;
        imageView4.setVisibility((model == null ? null : model.getOnMoreClick()) != null ? 0 : 8);
        imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisualRowItemListAdapter.m1982onBindItemViewHolder$lambda14(model, view, view2);
            }
        });
        view.setTag(model);
        if (model != null) {
            if (model.getThumbnailImageUrl() != null) {
                Context context2 = this.fragment.getContext();
                Intrinsics.checkNotNull(context2);
                int dimensionPixelSize = context2.getResources().getDimensionPixelSize(R.dimen.visual_row_item_thumbnail_size);
                ImageLoader.INSTANCE.loadImage(model.getThumbnailImageUrl()).withTransformation(this.thumbnailTransformation).withSize(dimensionPixelSize, dimensionPixelSize).into(imageView3);
            }
            if (model.getOnShowContextMenu() != null) {
                holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() { // from class: com.amazon.music.curate.view.VisualRowItemListAdapter$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnCreateContextMenuListener
                    public final void onCreateContextMenu(ContextMenu contextMenu, View view2, ContextMenu.ContextMenuInfo contextMenuInfo) {
                        VisualRowItemListAdapter.m1983onBindItemViewHolder$lambda16$lambda15(model, this, contextMenu, view2, contextMenuInfo);
                    }
                });
            }
            textView2.setBackgroundResource(0);
            textView3.setBackgroundResource(0);
            return;
        }
        Picasso.get().cancelRequest(imageView3);
        holder.itemView.setOnCreateContextMenuListener(null);
        imageView4.setVisibility(8);
        int i = R.color.color_secondary_glass_2;
        imageView3.setImageResource(i);
        textView2.setBackgroundResource(i);
        textView3.setBackgroundResource(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindItemViewHolder$lambda-11, reason: not valid java name */
    public static final void m1979onBindItemViewHolder$lambda11(VisualRowItem visualRowItem, View view) {
        Function0<Unit> onClick;
        if (visualRowItem == null || (onClick = visualRowItem.getOnClick()) == null) {
            return;
        }
        onClick.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindItemViewHolder$lambda-12, reason: not valid java name */
    public static final void m1980onBindItemViewHolder$lambda12(ImageView downloadStatusIcon, DownloadStatus downloadStatus) {
        Intrinsics.checkNotNullParameter(downloadStatusIcon, "$downloadStatusIcon");
        if (DownloadStatus.DOWNLOADED.equals(downloadStatus)) {
            downloadStatusIcon.setImageResource(R.drawable.ic_download_downloaded);
            downloadStatusIcon.setVisibility(0);
        } else {
            downloadStatusIcon.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindItemViewHolder$lambda-13, reason: not valid java name */
    public static final void m1981onBindItemViewHolder$lambda13(VisualRowItem visualRowItem, ImageView addButton, ImageView thumbnailImage, View view) {
        VisualRowItem.AddButton addButton2;
        Function0<Unit> click;
        Intrinsics.checkNotNullParameter(addButton, "$addButton");
        Intrinsics.checkNotNullParameter(thumbnailImage, "$thumbnailImage");
        if (visualRowItem != null && (addButton2 = visualRowItem.getAddButton()) != null && (click = addButton2.getClick()) != null) {
            click.invoke();
        }
        addButton.setEnabled(false);
        thumbnailImage.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindItemViewHolder$lambda-14, reason: not valid java name */
    public static final void m1982onBindItemViewHolder$lambda14(VisualRowItem visualRowItem, View view, View view2) {
        Function1<View, Unit> onMoreClick;
        Intrinsics.checkNotNullParameter(view, "$view");
        if (visualRowItem == null || (onMoreClick = visualRowItem.getOnMoreClick()) == null) {
            return;
        }
        onMoreClick.invoke(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindItemViewHolder$lambda-16$lambda-15, reason: not valid java name */
    public static final void m1983onBindItemViewHolder$lambda16$lambda15(VisualRowItem visualRowItem, VisualRowItemListAdapter this$0, ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function2<ContextMenu, FragmentActivity, Unit> onShowContextMenu = visualRowItem.getOnShowContextMenu();
        Intrinsics.checkNotNullExpressionValue(menu, "menu");
        FragmentActivity fragmentActivityRequireActivity = this$0.fragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "fragment.requireActivity()");
        onShowContextMenu.mo4invoke(menu, fragmentActivityRequireActivity);
    }

    /* JADX INFO: compiled from: VisualRowItemListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/curate/view/VisualRowItemListAdapter$EmptyHeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "getView", "()Landroid/view/View;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class EmptyHeaderViewHolder extends RecyclerView.ViewHolder {
        private final View view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EmptyHeaderViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
        }

        public final View getView() {
            return this.view;
        }
    }

    /* JADX INFO: compiled from: VisualRowItemListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/curate/view/VisualRowItemListAdapter$HeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "getView", "()Landroid/view/View;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final View view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HeaderViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
        }

        public final View getView() {
            return this.view;
        }
    }

    /* JADX INFO: compiled from: VisualRowItemListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/curate/view/VisualRowItemListAdapter$RowHeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "getView", "()Landroid/view/View;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class RowHeaderViewHolder extends RecyclerView.ViewHolder {
        private final View view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RowHeaderViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
        }

        public final View getView() {
            return this.view;
        }
    }

    /* JADX INFO: compiled from: VisualRowItemListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/curate/view/VisualRowItemListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "getView", "()Landroid/view/View;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
        }

        public final View getView() {
            return this.view;
        }
    }
}
