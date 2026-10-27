package com.amazon.music.alps.liveevent.binder;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.alps.liveevent.view.SponsorImageView;
import com.amazon.music.alps.liveevent.viewmodel.SponsoredImageItemViewModel;
import com.amazon.music.alps.liveevent.viewmodel.SponsoredImageViewModel;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: SponsorImageBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0018B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b¢\u0006\u0002\u0010\rJ\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/alps/liveevent/binder/SponsorImageBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/alps/liveevent/view/SponsorImageView;", "Lcom/amazon/music/alps/liveevent/viewmodel/SponsoredImageViewModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "sponsorImageItemBinder", "Lcom/amazon/music/alps/liveevent/binder/SponsorImageItemBinder;", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "onViewed", "Lkotlin/Function0;", "", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/alps/liveevent/binder/SponsorImageItemBinder;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lkotlin/jvm/functions/Function0;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "view", "model", "createView", "context", "Landroid/content/Context;", "Companion", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SponsorImageBinder implements UniversalBinder<SponsorImageView, SponsoredImageViewModel> {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final Function0<Unit> onViewed;
    private final SponsorImageItemBinder sponsorImageItemBinder;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<SponsoredImageViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(SponsoredImageViewModel.class);
    }

    public SponsorImageBinder(StyleSheet styleSheet, SponsorImageItemBinder sponsorImageItemBinder, BaseViewModelAdapterProvider adapterProvider, Function0<Unit> onViewed) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(sponsorImageItemBinder, "sponsorImageItemBinder");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        Intrinsics.checkNotNullParameter(onViewed, "onViewed");
        this.styleSheet = styleSheet;
        this.sponsorImageItemBinder = sponsorImageItemBinder;
        this.adapterProvider = adapterProvider;
        this.onViewed = onViewed;
    }

    public /* synthetic */ SponsorImageBinder(StyleSheet styleSheet, SponsorImageItemBinder sponsorImageItemBinder, BaseViewModelAdapterProvider baseViewModelAdapterProvider, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(styleSheet, sponsorImageItemBinder, baseViewModelAdapterProvider, (i & 8) != 0 ? new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.binder.SponsorImageBinder.1
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
    public SponsorImageView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SponsorImageView sponsorImageView = new SponsorImageView(context, this.styleSheet);
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        sponsorImageView.setSponsorImageContainerContentView(recyclerView);
        return sponsorImageView;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(SponsorImageView view, SponsoredImageViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        List<SponsoredImageItemViewModel> sponsoredImageItemList = model.getSponsoredImageItemList();
        if (sponsoredImageItemList == null) {
            return;
        }
        if (sponsoredImageItemList.size() > 4) {
            sponsoredImageItemList.subList(0, 4);
        }
        view.setSponsorImageAdapter(this.adapterProvider.getAdapter(sponsoredImageItemList, this.sponsorImageItemBinder));
        this.onViewed.invoke();
    }
}
