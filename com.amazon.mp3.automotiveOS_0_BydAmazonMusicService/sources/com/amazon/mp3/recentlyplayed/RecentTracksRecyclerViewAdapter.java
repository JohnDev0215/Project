package com.amazon.mp3.recentlyplayed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.util.SuggestedSongsViewModel;
import com.amazon.mp3.playback.view.lyrics.TextViewMarkerDecorator;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.ImageUrlModifier;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecentTracksRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final boolean mLyricsSupported = AmazonApplication.getCapabilities().isLyricsSupported();
    private final BauhausTextStyler mStyler;
    private final SuggestedSongsViewModel mSuggestedSongsViewModel;

    public RecentTracksRecyclerViewAdapter(final FragmentActivity activity, final SuggestedSongsViewModel suggestedSongsViewModel) {
        this.mSuggestedSongsViewModel = suggestedSongsViewModel;
        suggestedSongsViewModel.getSuggestedTracks().observe(activity, new Observer<List<RecentTrack>>() { // from class: com.amazon.mp3.recentlyplayed.RecentTracksRecyclerViewAdapter.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable final List<RecentTrack> recentTracks) {
                RecentTracksRecyclerViewAdapter.this.notifyDataSetChanged();
                if (RecentTracksRecyclerViewAdapter.this.mSuggestedSongsViewModel.getSuggestedTracks().getValue().isEmpty()) {
                    return;
                }
                RecentTracksRecyclerViewAdapter.this.mSuggestedSongsViewModel.getSuggestedTracks().removeObserver(this);
            }
        });
        this.mStyler = new BauhausTextStyler();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        List<RecentTrack> value = this.mSuggestedSongsViewModel.getAddedTracks().getValue();
        if (position == 0 && value.isEmpty()) {
            return 0;
        }
        if (position < value.size()) {
            return 2;
        }
        if (position != value.size()) {
            return (value.isEmpty() && position == value.size() + 1) ? 3 : 1;
        }
        return 3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int i = R.layout.new_playlist_suggested_songs_empty_view;
        if (viewType != 0) {
            if (viewType == 1 || viewType == 2) {
                i = R.layout.new_playlist_suggested_songs_track_line;
            } else if (viewType == 3) {
                i = R.layout.new_playlist_suggested_songs_text_view;
            }
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(i, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final RecentTrack recentTrack;
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        if (viewHolder.getItemViewType() == 0) {
            if (isBauhausLibraryEnabled()) {
                TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.suggested_songs_empty_text_view);
                textView.setText(this.mStyler.styleText(textView.getText()));
                return;
            }
            return;
        }
        if (viewHolder.getItemViewType() == 3) {
            if (isBauhausLibraryEnabled()) {
                TextView textView2 = (TextView) viewHolder.itemView.findViewById(R.id.header);
                textView2.setText(this.mStyler.styleText(textView2.getText()));
                return;
            }
            return;
        }
        List<RecentTrack> value = this.mSuggestedSongsViewModel.getAddedTracks().getValue();
        if (position < value.size()) {
            recentTrack = value.get(position);
        } else {
            recentTrack = this.mSuggestedSongsViewModel.getSuggestedTracks().getValue().get(value.isEmpty() ? position - 2 : (position - 1) - value.size());
        }
        viewHolder.mRecentTrack = recentTrack;
        if (isBauhausLibraryEnabled()) {
            viewHolder.mArtistName.setText(this.mStyler.styleText(recentTrack.getArtistName()));
            viewHolder.mTrackTitle.setText(this.mStyler.styleText(recentTrack.getTitle()));
        } else {
            viewHolder.mArtistName.setText(recentTrack.getArtistName());
            viewHolder.mTrackTitle.setText(recentTrack.getTitle());
        }
        if (viewHolder.mTrackTitle != null) {
            if (isBauhausLibraryEnabled()) {
                viewHolder.mTrackTitle.setText(this.mStyler.styleText(recentTrack.getTitle()));
            } else {
                viewHolder.mTrackTitle.setText(recentTrack.getTitle());
            }
        }
        if (viewHolder.mArtistName != null) {
            if (isBauhausLibraryEnabled()) {
                viewHolder.mArtistName.setText(this.mStyler.styleText(recentTrack.getArtistName()));
            } else {
                viewHolder.mArtistName.setText(recentTrack.getArtistName());
            }
        }
        if (viewHolder.getItemViewType() == 1 && (linearLayout2 = viewHolder.mAddButton) != null) {
            linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.recentlyplayed.RecentTracksRecyclerViewAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onBindViewHolder$0(viewHolder, view);
                }
            });
            viewHolder.mAddButton.setVisibility(0);
        }
        if (viewHolder.getItemViewType() == 2 && (linearLayout = viewHolder.mAddButton) != null) {
            linearLayout.setVisibility(8);
        }
        if (viewHolder.mAlbumArtwork.getWidth() > 0 && viewHolder.mAlbumArtwork.getHeight() > 0) {
            initArtwork(recentTrack, viewHolder.mAlbumArtwork);
        } else {
            viewHolder.mAlbumArtwork.post(new Runnable() { // from class: com.amazon.mp3.recentlyplayed.RecentTracksRecyclerViewAdapter$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onBindViewHolder$1(recentTrack, viewHolder);
                }
            });
        }
        TextView textView3 = viewHolder.mTrackTitle;
        if (textView3 != null) {
            TextViewMarkerDecorator textViewMarkerDecorator = new TextViewMarkerDecorator(textView3);
            textViewMarkerDecorator.showLyricsMarker(recentTrack.isHasLyrics() && this.mLyricsSupported);
            textViewMarkerDecorator.decorate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(ViewHolder viewHolder, View view) {
        viewHolder.mAddButton.setOnClickListener(null);
        onAddButtonClick(viewHolder.getAdapterPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(RecentTrack recentTrack, ViewHolder viewHolder) {
        initArtwork(recentTrack, viewHolder.mAlbumArtwork);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<RecentTrack> value = this.mSuggestedSongsViewModel.getAddedTracks().getValue();
        List<RecentTrack> value2 = this.mSuggestedSongsViewModel.getSuggestedTracks().getValue();
        if (value.isEmpty()) {
            return value2.size() + 2;
        }
        return value2.size() + value.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        final LinearLayout mAddButton;

        @Nullable
        ImageView mAddToLibraryButton;
        public ImageView mAlbumArtwork;
        public TextView mArtistName;

        @Nullable
        ImageView mOverflowButton;

        @Nullable
        RecentTrack mRecentTrack;
        TextView mTrackTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mRecentTrack = null;
            this.mTrackTitle = (TextView) itemView.findViewById(R.id.TrackTitle);
            this.mArtistName = (TextView) itemView.findViewById(R.id.ArtistName);
            this.mAlbumArtwork = (ImageView) itemView.findViewById(R.id.AlbumArtwork);
            this.mAddToLibraryButton = (ImageView) itemView.findViewById(R.id.AddButton);
            this.mOverflowButton = (ImageView) itemView.findViewById(R.id.overflow_button_open);
            ImageView imageView = this.mAddToLibraryButton;
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            ImageView imageView2 = this.mOverflowButton;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            this.mAddButton = (LinearLayout) itemView.findViewById(R.id.add_suggested_song_button);
        }
    }

    private void onAddButtonClick(int position) {
        if (getItemViewType(0) == 0) {
            notifyItemRemoved(0);
            position--;
        }
        int size = (position - this.mSuggestedSongsViewModel.getAddedTracks().getValue().size()) - 1;
        RecentTrack recentTrackAddTrack = this.mSuggestedSongsViewModel.addTrack(size);
        notifyItemMoved(position, this.mSuggestedSongsViewModel.getAddedTracks().getValue().size() - 1);
        UserInteractionAppEvent.builder(ActionType.PLAYLIST_EMPTY_ADD.getMetricsValue()).withEntityId(recentTrackAddTrack.getAsin()).withEntityIdType(EntityIdType.ASIN).withInteractionType(InteractionType.TAP).withEntityPos(size).publish();
    }

    private void initArtwork(final RecentTrack recentTrack, final ImageView imageView) {
        String artworkImageUrl = recentTrack.getArtworkImageUrl();
        if (artworkImageUrl != null) {
            boolean z = imageView.getWidth() > 0 && imageView.getHeight() > 0;
            if (z) {
                artworkImageUrl = ImageUrlModifier.with(artworkImageUrl).scaleToWidth(imageView.getWidth()).scaleToHeight(imageView.getHeight()).toUrl();
            }
            imageView.setTag(artworkImageUrl);
            RequestCreator requestCreatorPlaceholder = Picasso.get().load(artworkImageUrl).placeholder(R.drawable.placeholder_sm);
            if (z) {
                requestCreatorPlaceholder.resize(imageView.getWidth(), imageView.getHeight());
            }
            requestCreatorPlaceholder.into(imageView);
        }
    }

    private boolean isBauhausLibraryEnabled() {
        return AmazonApplication.getCapabilities().isBauhausLibraryEnabled();
    }
}
