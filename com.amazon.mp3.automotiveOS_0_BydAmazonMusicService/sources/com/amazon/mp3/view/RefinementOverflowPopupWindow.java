package com.amazon.mp3.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.view.RefinementOverflowContainer;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.RefinementOverflowView;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementOverflowPopupWindow.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ(\u0010 \u001a\u00020\u001c2\u000e\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\"2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000b\"\u0004\b\u001a\u0010\r¨\u0006("}, d2 = {"Lcom/amazon/mp3/view/RefinementOverflowPopupWindow;", "Landroid/widget/PopupWindow;", "Lcom/amazon/mp3/view/RefinementOverflowContainer;", "context", "Landroid/content/Context;", "refinementCallback", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "(Landroid/content/Context;Lcom/amazon/music/views/library/providers/RefinementCallback;)V", "currentFilterCollection", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "getCurrentFilterCollection", "()Lcom/amazon/music/views/library/models/FilterCollectionModel;", "setCurrentFilterCollection", "(Lcom/amazon/music/views/library/models/FilterCollectionModel;)V", "offsetY", "", "getRefinementCallback", "()Lcom/amazon/music/views/library/providers/RefinementCallback;", "refinementOverflowView", "Lcom/amazon/music/views/library/views/RefinementOverflowView;", "getRefinementOverflowView", "()Lcom/amazon/music/views/library/views/RefinementOverflowView;", "refinementOverflowView$delegate", "Lkotlin/Lazy;", "singleCollectionModel", "getSingleCollectionModel", "setSingleCollectionModel", "initOverflowView", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "initStyleSheet", "show", "filters", "", "Lcom/amazon/music/views/library/models/FilterModel;", "title", "", "anchor", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementOverflowPopupWindow extends PopupWindow implements RefinementOverflowContainer {
    private final Context context;
    private FilterCollectionModel currentFilterCollection;
    private int offsetY;
    private final RefinementCallback refinementCallback;

    /* JADX INFO: renamed from: refinementOverflowView$delegate, reason: from kotlin metadata */
    private final Lazy refinementOverflowView;
    private FilterCollectionModel singleCollectionModel;

    @Override // com.amazon.music.views.library.providers.SimpleClickListenerProvider
    public void onClick(Object obj) {
        RefinementOverflowContainer.DefaultImpls.onClick(this, obj);
    }

    @Override // com.amazon.music.views.library.providers.StateChangeProvider
    public void onResetState(String str) {
        RefinementOverflowContainer.DefaultImpls.onResetState(this, str);
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public RefinementCallback getRefinementCallback() {
        return this.refinementCallback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefinementOverflowPopupWindow(Context context, RefinementCallback refinementCallback) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(refinementCallback, "refinementCallback");
        this.context = context;
        this.refinementCallback = refinementCallback;
        this.refinementOverflowView = LazyKt__LazyJVMKt.lazy(new Function0<RefinementOverflowView>() { // from class: com.amazon.mp3.view.RefinementOverflowPopupWindow$refinementOverflowView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RefinementOverflowView invoke() {
                return (RefinementOverflowView) this.this$0.getContentView().findViewById(R.id.overflow_view);
            }
        });
        setContentView(LayoutInflater.from(context).inflate(R.layout.refinement_overflow_popup_window, (ViewGroup) null));
        setWidth(-2);
        setHeight(-2);
        setOutsideTouchable(true);
        setBackgroundDrawable(AppCompatResources.getDrawable(context, R.drawable.refinement_popup_window_background));
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public FilterCollectionModel getCurrentFilterCollection() {
        return this.currentFilterCollection;
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public void setCurrentFilterCollection(FilterCollectionModel filterCollectionModel) {
        this.currentFilterCollection = filterCollectionModel;
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public FilterCollectionModel getSingleCollectionModel() {
        return this.singleCollectionModel;
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public void setSingleCollectionModel(FilterCollectionModel filterCollectionModel) {
        this.singleCollectionModel = filterCollectionModel;
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public RefinementOverflowView getRefinementOverflowView() {
        Object value = this.refinementOverflowView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-refinementOverflowView>(...)");
        return (RefinementOverflowView) value;
    }

    public final void initStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        initOverflowView(this.context, styleSheet);
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        this.offsetY = spacerInPixels == null ? 0 : spacerInPixels.intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void initOverflowView(Context context, StyleSheet styleSheet) {
        if (context instanceof LifecycleOwner) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) context;
            getRefinementOverflowView().init(new BaseViewModelAdapterProvider(lifecycleOwner, null, LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), Feature.use_optimized_observable_browse_views_factory.isEnabled()), styleSheet, this);
            getRefinementOverflowView().setResetButtonClickListener(this);
        }
    }

    public final void show(List<? extends FilterModel> filters, String title, View anchor) {
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(title, "title");
        getRefinementOverflowView().display(filters, title);
        setCurrentFilterCollection(null);
        showAsDropDown(anchor, 0, this.offsetY);
    }
}
