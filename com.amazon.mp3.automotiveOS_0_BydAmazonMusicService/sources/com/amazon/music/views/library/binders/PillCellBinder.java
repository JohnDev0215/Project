package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.models.PillCellModel;
import com.amazon.music.views.library.models.base.ClearableContainer;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.models.enums.ViewType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.PillCellView;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: PillCellBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\u0010\u0010\u0014\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/views/library/binders/PillCellBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/PillCellView;", "Lcom/amazon/music/views/library/models/PillCellModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "clickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;)V", "context", "Landroid/content/Context;", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "bindStyle", "createView", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PillCellBinder implements UniversalBinder<PillCellView, PillCellModel> {
    private final BrushUriClickListenerProvider clickListenerProvider;
    private Context context;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PillCellModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(PillCellModel.class);
    }

    public PillCellBinder(StyleSheet styleSheet, BrushUriClickListenerProvider clickListenerProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(clickListenerProvider, "clickListenerProvider");
        this.styleSheet = styleSheet;
        this.clickListenerProvider = clickListenerProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public PillCellView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        return new PillCellView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(PillCellView view, final PillCellModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.setText(model.getTitle());
        bindStyle(view, model);
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.PillCellBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PillCellBinder.m2351bind$lambda1(this.f$0, model, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1, reason: not valid java name */
    public static final void m2351bind$lambda1(PillCellBinder this$0, PillCellModel model, View view) {
        WeakReference<ClearableContainer> clearableContainer;
        ClearableContainer clearableContainer2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.clickListenerProvider.onClick(model.getTarget(), model.getViewId(), model.getTargetType());
        if (model.getTargetType() != TargetType.CLEAR_VIEW || (clearableContainer = model.getClearableContainer()) == null || (clearableContainer2 = clearableContainer.get()) == null) {
            return;
        }
        clearableContainer2.clearModels();
    }

    private final void bindStyle(PillCellView view, PillCellModel model) {
        if (model.getViewType() == ViewType.CLEAR) {
            Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
            view.setMargins(-2, -1, (52 & 4) != 0 ? null : null, (52 & 8) != 0 ? null : Integer.valueOf(spacerInPixels == null ? 0 : spacerInPixels.intValue()), (52 & 16) != 0 ? null : null, (52 & 32) != 0 ? null : null);
        }
    }
}
