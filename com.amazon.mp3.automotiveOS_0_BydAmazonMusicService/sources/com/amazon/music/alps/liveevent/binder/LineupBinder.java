package com.amazon.music.alps.liveevent.binder;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.alps.liveevent.view.LineupView;
import com.amazon.music.alps.liveevent.viewmodel.LineupViewModel;
import com.amazon.music.alps.liveevent.viewmodel.SelectItemViewModel;
import com.amazon.music.views.library.binders.HorizontalTileBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.models.HorizontalTileModel;
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

/* JADX INFO: compiled from: LineupBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001dB?\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J\u0016\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0003J\u0010\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/alps/liveevent/binder/LineupBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/alps/liveevent/view/LineupView;", "Lcom/amazon/music/alps/liveevent/viewmodel/LineupViewModel;", "showMore", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "selectItemBinder", "Lcom/amazon/music/alps/liveevent/binder/SelectItemBinder;", "horizontalTileBinder", "Lcom/amazon/music/views/library/binders/HorizontalTileBinder;", "onViewed", "Lkotlin/Function0;", "", "(ZLcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lcom/amazon/music/alps/liveevent/binder/SelectItemBinder;Lcom/amazon/music/views/library/binders/HorizontalTileBinder;Lkotlin/jvm/functions/Function0;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "view", "model", "bindLineup", "createView", "context", "Landroid/content/Context;", "Companion", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LineupBinder implements UniversalBinder<LineupView, LineupViewModel> {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final HorizontalTileBinder horizontalTileBinder;
    private final Function0<Unit> onViewed;
    private final SelectItemBinder selectItemBinder;
    private final boolean showMore;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<LineupViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(LineupViewModel.class);
    }

    public LineupBinder(boolean z, StyleSheet styleSheet, BaseViewModelAdapterProvider adapterProvider, SelectItemBinder selectItemBinder, HorizontalTileBinder horizontalTileBinder, Function0<Unit> onViewed) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        Intrinsics.checkNotNullParameter(selectItemBinder, "selectItemBinder");
        Intrinsics.checkNotNullParameter(horizontalTileBinder, "horizontalTileBinder");
        Intrinsics.checkNotNullParameter(onViewed, "onViewed");
        this.showMore = z;
        this.styleSheet = styleSheet;
        this.adapterProvider = adapterProvider;
        this.selectItemBinder = selectItemBinder;
        this.horizontalTileBinder = horizontalTileBinder;
        this.onViewed = onViewed;
    }

    public /* synthetic */ LineupBinder(boolean z, StyleSheet styleSheet, BaseViewModelAdapterProvider baseViewModelAdapterProvider, SelectItemBinder selectItemBinder, HorizontalTileBinder horizontalTileBinder, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, styleSheet, baseViewModelAdapterProvider, selectItemBinder, horizontalTileBinder, (i & 32) != 0 ? new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.binder.LineupBinder.1
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
    public LineupView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        LineupView lineupView = new LineupView(context, this.styleSheet);
        RecyclerView recyclerView = new RecyclerView(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        lineupView.setItemListContentView(recyclerView);
        RecyclerView recyclerView2 = new RecyclerView(context);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
        linearLayoutManager2.setOrientation(1);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        lineupView.setItemDetailContentView(recyclerView2);
        return lineupView;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final LineupView view, final LineupViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        this.selectItemBinder.setItemClickHandler(new SelectItemClickHandler() { // from class: com.amazon.music.alps.liveevent.binder.LineupBinder$$ExternalSyntheticLambda0
            @Override // com.amazon.music.alps.liveevent.binder.SelectItemClickHandler
            public final void onClick(int i) {
                LineupBinder.m1866bind$lambda0(model, this, view, i);
            }
        });
        bindLineup(view, model);
        this.onViewed.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m1866bind$lambda0(LineupViewModel model, LineupBinder this$0, LineupView view, int i) {
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        List<SelectItemViewModel> lineupItemList = model.getLineupItemList();
        if (lineupItemList == null || lineupItemList.isEmpty()) {
            return;
        }
        List<List<HorizontalTileModel>> lineupDetailList = model.getLineupDetailList();
        if (lineupDetailList == null || lineupDetailList.isEmpty()) {
            return;
        }
        model.setSelectedIndex(Integer.valueOf(i));
        int size = model.getLineupItemList().size();
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + 1;
            model.getLineupItemList().get(i2).setSelected(Boolean.valueOf(i2 == i));
            i2 = i3;
        }
        this$0.bindLineup(view, model);
    }

    public final void bindLineup(LineupView view, LineupViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        List<SelectItemViewModel> lineupItemList = model.getLineupItemList();
        if (lineupItemList == null || lineupItemList.isEmpty()) {
            return;
        }
        List<List<HorizontalTileModel>> lineupDetailList = model.getLineupDetailList();
        if (lineupDetailList == null || lineupDetailList.isEmpty()) {
            return;
        }
        if (model.getLineupItemList().size() > 1) {
            view.setLineupItemsAdapter(this.adapterProvider.getAdapter(model.getLineupItemList(), this.selectItemBinder));
        }
        List<List<HorizontalTileModel>> lineupDetailList2 = model.getLineupDetailList();
        Integer selectedIndex = model.getSelectedIndex();
        Intrinsics.checkNotNull(selectedIndex);
        List<HorizontalTileModel> list = lineupDetailList2.get(selectedIndex.intValue());
        Intrinsics.checkNotNull(list);
        List<HorizontalTileModel> listSubList = list;
        if (this.showMore && listSubList.size() > 5) {
            listSubList = listSubList.subList(0, 5);
        }
        view.setLineupDetailListAdapter(this.adapterProvider.getAdapter(listSubList, this.horizontalTileBinder));
    }
}
