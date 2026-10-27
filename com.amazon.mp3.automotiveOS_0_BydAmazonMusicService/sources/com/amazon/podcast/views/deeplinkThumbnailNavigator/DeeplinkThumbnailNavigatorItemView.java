package com.amazon.podcast.views.deeplinkThumbnailNavigator;

import Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes5.dex */
public class DeeplinkThumbnailNavigatorItemView extends ConstraintLayout {
    private final Context context;
    private ImageView imageView;
    private final int itemHeight;
    private final int itemWidth;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;
    private final RoundedCornersTransformation roundedCornersTransformation;
    private EmberTextView titleView;

    public DeeplinkThumbnailNavigatorItemView(Context context, String ownerId, MethodsDispatcher methodsDispatcher, int itemWidth, int itemHeight) {
        super(context);
        this.context = context;
        this.resources = context.getResources();
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.itemWidth = itemWidth;
        this.itemHeight = itemHeight;
        this.roundedCornersTransformation = new RoundedCornersTransformation(getResources().getDimensionPixelSize(R$dimen.podcast_corner_radius_medium));
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_deeplink_thumbnail_navigator_item, this);
        this.titleView = (EmberTextView) findViewById(R$id.thumbnail_navigator_item_title);
        this.imageView = (ImageView) findViewById(R$id.thumbnail_navigator_item_image);
    }

    public void bind(final ThumbnailNavigatorItemElement element) {
        this.titleView.setText(element.getTitle());
        Picasso.get().load(element.getImage()).placeholder(R$drawable.artwork_placeholder).transform(this.roundedCornersTransformation).resize(this.itemWidth, this.itemHeight).into(this.imageView);
        this.imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.deeplinkThumbnailNavigator.DeeplinkThumbnailNavigatorItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeeplinkThumbnailNavigatorItemView.this.methodsDispatcher.dispatch(DeeplinkThumbnailNavigatorItemView.this.ownerId, element.getOnItemSelected());
            }
        });
    }
}
