package com.amazon.music.views.library.binders;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.models.PodcastTileModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.PodcastTileView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: PodcastTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/views/library/binders/PodcastTileBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/PodcastTileView;", "Lcom/amazon/music/views/library/models/PodcastTileModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/music/views/library/click/ClickListenerFactory;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PodcastTileBinder implements UniversalBinder<PodcastTileView, PodcastTileModel> {
    private final ClickListenerFactory clickListenerFactory;
    private final LifecycleOwner lifecycleOwner;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PodcastTileModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(PodcastTileModel.class);
    }

    public PodcastTileBinder(StyleSheet styleSheet, LifecycleOwner lifecycleOwner, ClickListenerFactory clickListenerFactory) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
        this.styleSheet = styleSheet;
        this.lifecycleOwner = lifecycleOwner;
        this.clickListenerFactory = clickListenerFactory;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public PodcastTileView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new PodcastTileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(PodcastTileView view, PodcastTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.bind(model, this.lifecycleOwner, this.clickListenerFactory);
    }
}
