package com.amazon.music.alps.liveevent.binder;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.alps.liveevent.ktx.ViewKt;
import com.amazon.music.alps.liveevent.view.WaysToWatchView;
import com.amazon.music.alps.liveevent.viewmodel.NewWaysToWatchViewModel;
import com.amazon.music.alps.liveevent.viewmodel.VerticalIconLinkItemViewModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: NewWaysToWatchBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B'\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0002H\u0007J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\u0010\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/alps/liveevent/binder/NewWaysToWatchBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/alps/liveevent/view/WaysToWatchView;", "Lcom/amazon/music/alps/liveevent/viewmodel/NewWaysToWatchViewModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "verticalIconLinkItemBinder", "Lcom/amazon/music/alps/liveevent/binder/VerticalIconLinkItemBinder;", "onViewed", "Lkotlin/Function0;", "", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/alps/liveevent/binder/VerticalIconLinkItemBinder;Lkotlin/jvm/functions/Function0;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "view", "model", "bindStyle", "bindTitle", "bindWaysToWatch", "createView", "context", "Landroid/content/Context;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NewWaysToWatchBinder implements UniversalBinder<WaysToWatchView, NewWaysToWatchViewModel> {
    private final Function0<Unit> onViewed;
    private final StyleSheet styleSheet;
    private final VerticalIconLinkItemBinder verticalIconLinkItemBinder;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<NewWaysToWatchViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(NewWaysToWatchViewModel.class);
    }

    public NewWaysToWatchBinder(StyleSheet styleSheet, VerticalIconLinkItemBinder verticalIconLinkItemBinder, Function0<Unit> onViewed) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(verticalIconLinkItemBinder, "verticalIconLinkItemBinder");
        Intrinsics.checkNotNullParameter(onViewed, "onViewed");
        this.styleSheet = styleSheet;
        this.verticalIconLinkItemBinder = verticalIconLinkItemBinder;
        this.onViewed = onViewed;
    }

    public /* synthetic */ NewWaysToWatchBinder(StyleSheet styleSheet, VerticalIconLinkItemBinder verticalIconLinkItemBinder, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(styleSheet, verticalIconLinkItemBinder, (i & 4) != 0 ? new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.binder.NewWaysToWatchBinder.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        } : function0);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public WaysToWatchView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new WaysToWatchView(context);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(WaysToWatchView view, NewWaysToWatchViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bindStyle(view);
        bindTitle(view, model);
        bindWaysToWatch(view, model);
        this.onViewed.invoke();
    }

    @VisibleForTesting
    public final void bindStyle(WaysToWatchView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        ViewKt.applyFontStyle(view.getWaysToWatchTitle(), this.styleSheet, FontStyleKey.HEADLINE_4);
    }

    private final void bindTitle(WaysToWatchView view, NewWaysToWatchViewModel model) {
        String title = model.getTitle();
        if (title == null) {
            return;
        }
        view.getWaysToWatchTitle().setText(title);
    }

    private final void bindWaysToWatch(WaysToWatchView view, NewWaysToWatchViewModel model) {
        List<VerticalIconLinkItemViewModel> waysToWatchItems = model.getWaysToWatchItems();
        if (waysToWatchItems == null) {
            return;
        }
        for (VerticalIconLinkItemViewModel verticalIconLinkItemViewModel : waysToWatchItems) {
            if (verticalIconLinkItemViewModel.getActionType() == ActionType.GO_PRIME_VIDEO) {
                this.verticalIconLinkItemBinder.bind(view.getWatchOnPrimeVideos(), verticalIconLinkItemViewModel);
                view.getWatchOnPrimeVideos().setVisibility(0);
            } else if (verticalIconLinkItemViewModel.getActionType() == ActionType.GO_LIVE_STREAM) {
                this.verticalIconLinkItemBinder.bind(view.getWatchOnAmazonMusic(), verticalIconLinkItemViewModel);
                view.getWatchOnAmazonMusic().setVisibility(0);
            }
        }
    }
}
