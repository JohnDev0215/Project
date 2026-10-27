package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.GalleryTemplateInterface.v2_0.AboutElement;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$id;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.views.ExpandableTextView;
import com.amazon.podcast.views.Strings;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
final class AboutViewHolder extends RecyclerView.ViewHolder {
    private ExpandableTextView description;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private CompactPillListingView pillListingView;
    private TrailerButton trailerButton;

    public AboutViewHolder(final View itemView, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        super(itemView);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.trailerButton = (TrailerButton) itemView.findViewById(R$id.about_view_trailer_button);
        this.description = (ExpandableTextView) itemView.findViewById(R$id.about_view_description);
        CompactPillListingView compactPillListingView = (CompactPillListingView) itemView.findViewById(R$id.about_view_pills_listing);
        this.pillListingView = compactPillListingView;
        compactPillListingView.setOwnerId(ownerId);
        this.pillListingView.setMethodsDispatcher(methodsDispatcher);
    }

    public void bind(final AboutElement element) {
        if (element.getTrailer() == null) {
            this.trailerButton.setVisibility(8);
        } else {
            this.trailerButton.setVisibility(0);
            this.trailerButton.bind(element.getTrailer(), this.ownerId, this.methodsDispatcher);
        }
        if (!StringUtils.isBlank(element.getDescription())) {
            this.description.setVisibility(0);
            this.description.setText(Strings.convertToSpannable(element.getDescription(), this.description));
            this.description.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.AboutViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    AboutViewHolder.this.methodsDispatcher.dispatch(AboutViewHolder.this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_SHOW_ABOUT, UiMetricAttributes$ActionType.EXPAND_READ_MORE_READ_LESS, UiMetricAttributes$ContentName.PODCAST_SHOW_ABOUT));
                }
            });
        } else {
            this.description.setVisibility(8);
        }
        this.pillListingView.bind(element.getCompactPillNavigator().getItems());
    }
}
