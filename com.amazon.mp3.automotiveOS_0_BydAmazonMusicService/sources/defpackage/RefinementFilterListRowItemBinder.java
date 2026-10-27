package defpackage;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.binders.RefinementRowItemBinder;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.RefinementFilterListRowItemModel;
import com.amazon.music.views.library.providers.SimpleClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.RefinementFilterListRowItemView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: RefinementFilterListRowItemBinder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0019\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"LRefinementFilterListRowItemBinder;", "Lcom/amazon/music/views/library/binders/RefinementRowItemBinder;", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "Lcom/amazon/music/views/library/views/RefinementFilterListRowItemView;", "Lcom/amazon/music/views/library/models/RefinementFilterListRowItemModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "clickListenerProvider", "Lcom/amazon/music/views/library/providers/SimpleClickListenerProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/SimpleClickListenerProvider;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementFilterListRowItemBinder extends RefinementRowItemBinder<FilterCollectionModel, RefinementFilterListRowItemView, RefinementFilterListRowItemModel> {
    private final SimpleClickListenerProvider clickListenerProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<RefinementFilterListRowItemModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(RefinementFilterListRowItemModel.class);
    }

    public RefinementFilterListRowItemBinder(StyleSheet styleSheet, SimpleClickListenerProvider simpleClickListenerProvider) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.clickListenerProvider = simpleClickListenerProvider;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public RefinementFilterListRowItemView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new RefinementFilterListRowItemView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.RefinementRowItemBinder
    public void bind(RefinementFilterListRowItemView view, final RefinementFilterListRowItemModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        super.bind(view, model);
        view.setOnClickListener(new View.OnClickListener() { // from class: RefinementFilterListRowItemBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RefinementFilterListRowItemBinder.m0bind$lambda0(this.f$0, model, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m0bind$lambda0(RefinementFilterListRowItemBinder this$0, RefinementFilterListRowItemModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        SimpleClickListenerProvider simpleClickListenerProvider = this$0.clickListenerProvider;
        if (simpleClickListenerProvider == null) {
            return;
        }
        simpleClickListenerProvider.onClick(model.getContent());
    }
}
