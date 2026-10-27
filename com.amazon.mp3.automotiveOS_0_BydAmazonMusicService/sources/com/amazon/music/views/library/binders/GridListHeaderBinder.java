package com.amazon.music.views.library.binders;

import android.content.Context;
import com.amazon.music.views.library.models.GridListHeaderModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.BaseHeaderView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: GridListHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/views/library/binders/GridListHeaderBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/BaseHeaderView;", "Lcom/amazon/music/views/library/models/GridListHeaderModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GridListHeaderBinder implements UniversalBinder<BaseHeaderView, GridListHeaderModel> {
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<GridListHeaderModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(GridListHeaderModel.class);
    }

    public GridListHeaderBinder(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public BaseHeaderView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BaseHeaderView baseHeaderView = new BaseHeaderView(context, this.styleSheet);
        baseHeaderView.applyGridListHeaderLayout();
        return baseHeaderView;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(BaseHeaderView view, GridListHeaderModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        String title = model.getTitle();
        if (title != null) {
            view.setHeaderText(title);
        }
        String secondaryTitle = model.getSecondaryTitle();
        if (secondaryTitle != null) {
            view.setSecondaryHeaderText(secondaryTitle);
        }
        String tertiaryTitle = model.getTertiaryTitle();
        if (tertiaryTitle == null) {
            return;
        }
        view.setTertiaryHeaderText(tertiaryTitle);
    }
}
