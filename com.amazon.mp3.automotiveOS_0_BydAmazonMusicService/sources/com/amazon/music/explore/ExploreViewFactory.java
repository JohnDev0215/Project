package com.amazon.music.explore;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.music.explore.widgets.listeners.ExploreClickListenerFactory;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.binders.ArtworkFrameBinder;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.binders.VerticalTileBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreViewFactory {
    private final VerticalTileBinder verticalTileBinder;

    public ExploreViewFactory(@NonNull Context context, @NonNull StyleSheet styleSheet, @NonNull UserSubscription userSubscription) {
        ImageBinder imageBinder = new ImageBinder(context, styleSheet, PicassoImageLoader.builder(context, Picasso.get()).withTag(context).build());
        this.verticalTileBinder = new VerticalTileBinder(styleSheet, userSubscription, new ArtworkFrameBinder(context, styleSheet, imageBinder, null, null), null, new ExploreClickListenerFactory(), null, imageBinder);
    }

    @NonNull
    public VerticalTileBinder getVerticalTileBinder() {
        return this.verticalTileBinder;
    }
}
