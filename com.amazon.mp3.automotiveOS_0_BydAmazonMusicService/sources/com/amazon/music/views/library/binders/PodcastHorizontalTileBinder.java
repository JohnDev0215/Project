package com.amazon.music.views.library.binders;

import android.content.Context;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.models.PodcastHorizontalTileModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.PodcastHorizontalTileView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: PodcastHorizontalTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000bR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/views/library/binders/PodcastHorizontalTileBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/PodcastHorizontalTileView;", "Lcom/amazon/music/views/library/models/PodcastHorizontalTileModel;", "Landroid/content/Context;", "context", "createView", "view", "model", "", "bind", "", "width", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class PodcastHorizontalTileBinder implements UniversalBinder<PodcastHorizontalTileView, PodcastHorizontalTileModel> {
    private final ClickListenerFactory clickListenerFactory;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PodcastHorizontalTileModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(PodcastHorizontalTileModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public PodcastHorizontalTileView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new PodcastHorizontalTileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(PodcastHorizontalTileView view, PodcastHorizontalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bind(view, model, -1);
    }

    public final void bind(PodcastHorizontalTileView view, PodcastHorizontalTileModel model, int width) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.updateViewWidth(width);
        view.setOnClickListener(this.clickListenerFactory.create(model.getMetadata()));
        view.setPodcastImage(model.getImageUrl());
        view.setImageOnClickListener(this.clickListenerFactory.create(model.getPlayMetadata()));
        view.setNumber(model.getNumber());
        view.setTitle(model.getTitle());
        view.setSubTitle(model.getSubTitle());
        view.showPlayOverlay(model.getShowPlayOverlay());
    }
}
