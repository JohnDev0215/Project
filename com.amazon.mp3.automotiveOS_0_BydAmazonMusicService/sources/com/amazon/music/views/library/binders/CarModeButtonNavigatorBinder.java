package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.models.CarModeButtonNavigatorModel;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.CarModeButtonNavigatorView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: CarModeButtonNavigatorBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/views/library/binders/CarModeButtonNavigatorBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/CarModeButtonNavigatorView;", "Lcom/amazon/music/views/library/models/CarModeButtonNavigatorModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/SeeMoreProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeButtonNavigatorBinder implements UniversalBinder<CarModeButtonNavigatorView, CarModeButtonNavigatorModel> {
    private final SeeMoreProvider seeMoreProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<CarModeButtonNavigatorModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(CarModeButtonNavigatorModel.class);
    }

    public CarModeButtonNavigatorBinder(StyleSheet styleSheet, SeeMoreProvider seeMoreProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.seeMoreProvider = seeMoreProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public CarModeButtonNavigatorView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new CarModeButtonNavigatorView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(CarModeButtonNavigatorView view, final CarModeButtonNavigatorModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.setTitle(model.getText());
        view.setMoreButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CarModeButtonNavigatorBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CarModeButtonNavigatorBinder.m2277bind$lambda0(this.f$0, model, view2);
            }
        });
        if (model.getIsLastItem()) {
            view.hideDivider();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m2277bind$lambda0(CarModeButtonNavigatorBinder this$0, CarModeButtonNavigatorModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        SeeMoreProvider seeMoreProvider = this$0.seeMoreProvider;
        if (seeMoreProvider == null) {
            return;
        }
        seeMoreProvider.onSeeMoreClick(model.getTarget(), model.getViewReferenceId(), null);
    }
}
