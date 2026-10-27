package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.models.GenreDetailHeaderModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.GenreClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.GenreDetailHeaderView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: GenreDetailHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/views/library/binders/GenreDetailHeaderBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/GenreDetailHeaderView;", "Lcom/amazon/music/views/library/models/GenreDetailHeaderModel;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "genreClickListenerProvider", "Lcom/amazon/music/views/library/providers/GenreClickListenerProvider;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/GenreClickListenerProvider;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "handleStateChange", "payload", "", "setStationIconState", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GenreDetailHeaderBinder implements BaseStateChangeBinder<GenreDetailHeaderView, GenreDetailHeaderModel> {
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final Context context;
    private final GenreClickListenerProvider genreClickListenerProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<GenreDetailHeaderModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(GenreDetailHeaderModel.class);
    }

    public GenreDetailHeaderBinder(Context context, StyleSheet styleSheet, GenreClickListenerProvider genreClickListenerProvider, ContentEnabilityProvider contentEnabilityProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "contentEnabilityProvider");
        this.context = context;
        this.styleSheet = styleSheet;
        this.genreClickListenerProvider = genreClickListenerProvider;
        this.contentEnabilityProvider = contentEnabilityProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public GenreDetailHeaderView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new GenreDetailHeaderView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(GenreDetailHeaderView view, GenreDetailHeaderModel model, Object payload) {
        StationMetadata stationMetadata;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (!Intrinsics.areEqual(payload, (Object) 1) || model.getPlayState() == null || (stationMetadata = model.getStationMetadata()) == null || stationMetadata.getKey() == null) {
            return;
        }
        view.setPlayStateAndShowStationEquilizerButtonView(model.getPlayState());
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(GenreDetailHeaderView view, GenreDetailHeaderModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        String primaryTitle = model.getPrimaryTitle();
        if (primaryTitle != null) {
            view.setHeaderText(primaryTitle);
        }
        setStationIconState(view, model);
    }

    private final void setStationIconState(GenreDetailHeaderView view, final GenreDetailHeaderModel model) {
        StationMetadata stationMetadata = model.getStationMetadata();
        if (stationMetadata == null || stationMetadata.getKey() == null) {
            return;
        }
        view.setStationEligibility(this.contentEnabilityProvider.shouldEnableContent(model.getStationMetadata()));
        view.setStationButtonClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.GenreDetailHeaderBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GenreDetailHeaderBinder.m2317setStationIconState$lambda3$lambda2(this.f$0, model, view2);
            }
        });
        view.setAndShowStationButton(true, model.getStationMetadata());
        view.setPlayStateAndShowStationEquilizerButtonView(model.getPlayState());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setStationIconState$lambda-3$lambda-2, reason: not valid java name */
    public static final void m2317setStationIconState$lambda3$lambda2(GenreDetailHeaderBinder this$0, GenreDetailHeaderModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        GenreClickListenerProvider genreClickListenerProvider = this$0.genreClickListenerProvider;
        if (genreClickListenerProvider == null) {
            return;
        }
        genreClickListenerProvider.onStationActionIconClick(this$0.context, model.getStationMetadata());
    }
}
