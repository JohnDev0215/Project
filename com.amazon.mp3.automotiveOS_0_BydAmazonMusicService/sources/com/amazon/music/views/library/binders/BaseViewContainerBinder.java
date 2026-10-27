package com.amazon.music.views.library.binders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: BaseViewContainerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000*\u000e\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\b\u0001\u0010\u0005*\u00020\u00042\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u00062\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u0007B\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b*\u0010+JC\u0010\f\u001a\u00020\u000b22\u0010\n\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\t\u0012\u0006\b\u0001\u0012\u00020\u00020\u00060\b\"\u0012\u0012\u0006\b\u0001\u0012\u00020\t\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006H\u0004¢\u0006\u0004\b\f\u0010\rJ(\u0010\f\u001a\u00020\u000b2\u001e\u0010\n\u001a\u001a\u0012\u0014\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\t\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006\u0018\u00010\u000eH\u0004J'\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00028\u00012\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00028\u0001H\u0004¢\u0006\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u00178\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\u00020\u001c8\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R4\u0010#\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"\u0012\u0014\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\t\u0012\u0006\b\u0001\u0012\u00020\u00020\u00060!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R:\u0010\n\u001a\u001a\u0012\u0014\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\t\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006\u0018\u00010\u000e8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\n\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006,"}, d2 = {"Lcom/amazon/music/views/library/binders/BaseViewContainerBinder;", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Model", "Landroid/view/ViewGroup;", "ViewContainer", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "", "Landroid/view/View;", "binders", "", "buildBinders", "([Lcom/amazon/music/views/library/binders/UniversalBinder;)V", "", "view", "model", "", "payload", "handleStateChange", "(Landroid/view/ViewGroup;Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;Ljava/lang/Object;)V", "hideView", "(Landroid/view/ViewGroup;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "getContentViewedListener", "()Lcom/amazon/music/uicontentview/ContentViewedListener;", "Ljava/util/HashMap;", "Lkotlin/reflect/KClass;", "binderMap", "Ljava/util/HashMap;", "Ljava/util/List;", "getBinders", "()Ljava/util/List;", "setBinders", "(Ljava/util/List;)V", "<init>", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/uicontentview/ContentViewedListener;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class BaseViewContainerBinder<Model extends BaseViewContainerModel<BaseViewModel>, ViewContainer extends ViewGroup> implements UniversalBinder<ViewContainer, Model>, BaseStateChangeBinder<ViewContainer, Model> {
    private final HashMap<KClass<?>, UniversalBinder<? extends View, ? extends BaseViewModel>> binderMap;
    private List<? extends UniversalBinder<? extends View, ? extends BaseViewModel>> binders;
    private final ContentViewedListener contentViewedListener;
    private final StyleSheet styleSheet;

    public BaseViewContainerBinder(StyleSheet styleSheet, ContentViewedListener contentViewedListener) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        this.styleSheet = styleSheet;
        this.contentViewedListener = contentViewedListener;
        this.binderMap = new HashMap<>();
    }

    protected final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    protected final List<UniversalBinder<? extends View, ? extends BaseViewModel>> getBinders() {
        return this.binders;
    }

    protected final void setBinders(List<? extends UniversalBinder<? extends View, ? extends BaseViewModel>> list) {
        this.binders = list;
    }

    protected final void buildBinders(UniversalBinder<? extends View, ? extends BaseViewModel>... binders) {
        Intrinsics.checkNotNullParameter(binders, "binders");
        buildBinders(CollectionsKt__CollectionsKt.listOf(Arrays.copyOf(binders, binders.length)));
    }

    protected final void buildBinders(List<? extends UniversalBinder<? extends View, ? extends BaseViewModel>> binders) {
        if (binders == null) {
            return;
        }
        setBinders(binders);
        for (UniversalBinder<? extends View, ? extends BaseViewModel> universalBinder : binders) {
            this.binderMap.put((KClass<?>) universalBinder.getModelClass(), universalBinder);
        }
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(ViewContainer view, Model model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 5)) {
            bind(view, model);
        }
    }

    protected final void hideView(ViewContainer view) {
        Intrinsics.checkNotNullParameter(view, "view");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 0);
        layoutParams.setMargins(0, 0, 0, 0);
        view.setLayoutParams(layoutParams);
        view.setVisibility(8);
    }
}
