package com.amazon.music.views.library.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.ScrollStateListener;
import com.amazon.music.views.library.models.enums.RefinementListType;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExposedRefinementListView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000g\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001*\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u00101\u001a\u000200¢\u0006\u0004\b2\u00103J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\t\u001a\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006J\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bJ\u0010\u0010\u0010\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eJ\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\u0016\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0017R$\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001b\u0010&\u001a\u00020!8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010'R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010(R\u001e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010)R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010.\u001a\u00020-8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b.\u0010/¨\u00064"}, d2 = {"Lcom/amazon/music/views/library/views/ExposedRefinementListView;", "Landroidx/core/widget/NestedScrollView;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "init", "", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "refinements", "setRefinements", "clearFocusedState", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "model", "setFocusedState", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "adapter", "setAdapter", "getAdapter", "", "scrollOffset", "setScrollState", "visibility", "setVisibility", "Lcom/amazon/music/views/library/models/enums/RefinementListType;", "refinementListType", "updateStyle", "Lcom/amazon/music/views/library/models/ScrollStateListener;", "scrollStateListener", "Lcom/amazon/music/views/library/models/ScrollStateListener;", "getScrollStateListener", "()Lcom/amazon/music/views/library/models/ScrollStateListener;", "setScrollStateListener", "(Lcom/amazon/music/views/library/models/ScrollStateListener;)V", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView$delegate", "Lkotlin/Lazy;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "I", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "Ljava/util/List;", "com/amazon/music/views/library/views/ExposedRefinementListView$onScrollListener$1", "onScrollListener", "Lcom/amazon/music/views/library/views/ExposedRefinementListView$onScrollListener$1;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "itemDecoration", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ExposedRefinementListView extends NestedScrollView {
    private UniversalAdapter adapter;
    private RecyclerView.ItemDecoration itemDecoration;
    private final ExposedRefinementListView$onScrollListener$1 onScrollListener;

    /* JADX INFO: renamed from: recyclerView$delegate, reason: from kotlin metadata */
    private final Lazy recyclerView;
    private List<RefinementPillModel> refinements;
    private int scrollOffset;
    private ScrollStateListener scrollStateListener;

    /* JADX INFO: compiled from: ExposedRefinementListView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RefinementListType.values().length];
            iArr[RefinementListType.FILTER.ordinal()] = 1;
            iArr[RefinementListType.NAVIGATION.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.recyclerview.widget.RecyclerView$OnScrollListener, com.amazon.music.views.library.views.ExposedRefinementListView$onScrollListener$1] */
    public ExposedRefinementListView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.recyclerView = LazyKt__LazyJVMKt.lazy(new Function0<RecyclerView>() { // from class: com.amazon.music.views.library.views.ExposedRefinementListView$recyclerView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RecyclerView invoke() {
                return (RecyclerView) this.this$0.findViewById(R$id.refinement_list_view);
            }
        });
        ?? r4 = new RecyclerView.OnScrollListener() { // from class: com.amazon.music.views.library.views.ExposedRefinementListView$onScrollListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrolled(recyclerView, dx, dy);
                this.this$0.scrollOffset += dx;
                ScrollStateListener scrollStateListener = this.this$0.getScrollStateListener();
                if (scrollStateListener == null) {
                    return;
                }
                scrollStateListener.onScrollStateUpdated(this.this$0.scrollOffset);
            }
        };
        this.onScrollListener = r4;
        LayoutInflater.from(getContext()).inflate(R$layout.dmmviewlibrary_exposed_refinements_list_view, this);
        setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        getRecyclerView().addOnScrollListener(r4);
    }

    public final ScrollStateListener getScrollStateListener() {
        return this.scrollStateListener;
    }

    public final void setScrollStateListener(ScrollStateListener scrollStateListener) {
        this.scrollStateListener = scrollStateListener;
    }

    public final RecyclerView getRecyclerView() {
        Object value = this.recyclerView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-recyclerView>(...)");
        return (RecyclerView) value;
    }

    public final void init(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        updateStyle(styleSheet, RefinementListType.FILTER);
    }

    public final void setRefinements(List<RefinementPillModel> refinements) {
        if (refinements == null) {
            return;
        }
        if (Intrinsics.areEqual(this.refinements, refinements)) {
            UniversalAdapter universalAdapter = this.adapter;
            if (universalAdapter != null) {
                universalAdapter.notifyDataSetChanged();
            }
        } else {
            UniversalAdapter universalAdapter2 = this.adapter;
            if (universalAdapter2 != null) {
                universalAdapter2.clear();
            }
            UniversalAdapter universalAdapter3 = this.adapter;
            if (universalAdapter3 != null) {
                UniversalAdapter.addItems$default(universalAdapter3, refinements, null, false, 6, null);
            }
        }
        this.refinements = refinements;
    }

    public final void clearFocusedState() {
        List<RefinementPillModel> list = this.refinements;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((RefinementPillModel) it.next()).setFocused(false);
            }
        }
        setRefinements(this.refinements);
    }

    public final void setFocusedState(FilterCollectionModel model) {
        Integer numValueOf;
        Intrinsics.checkNotNullParameter(model, "model");
        List<RefinementPillModel> list = this.refinements;
        if (list == null) {
            numValueOf = null;
        } else {
            int i = 0;
            Iterator<RefinementPillModel> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual(it.next().getContent(), model)) {
                    break;
                } else {
                    i++;
                }
            }
            numValueOf = Integer.valueOf(i);
        }
        if (numValueOf == null || numValueOf.intValue() == -1) {
            return;
        }
        List<RefinementPillModel> list2 = this.refinements;
        RefinementPillModel refinementPillModel = list2 != null ? list2.get(numValueOf.intValue()) : null;
        if (refinementPillModel != null) {
            refinementPillModel.setFocused(true);
        }
        setRefinements(this.refinements);
    }

    public final void setAdapter(UniversalAdapter adapter) {
        getRecyclerView().setAdapter(adapter);
    }

    public final UniversalAdapter getAdapter() {
        if (getRecyclerView().getAdapter() == null) {
            return null;
        }
        RecyclerView.Adapter adapter = getRecyclerView().getAdapter();
        if (adapter != null) {
            return (UniversalAdapter) adapter;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
    }

    public final void setScrollState(int scrollOffset) {
        RecyclerView.LayoutManager layoutManager = getRecyclerView().getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager != null) {
            linearLayoutManager.scrollToPositionWithOffset(0, -scrollOffset);
        }
        this.scrollOffset = scrollOffset;
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        int i = visibility == 8 ? 0 : -2;
        if (getLayoutParams().height != i) {
            getLayoutParams().height = i;
            requestLayout();
        }
        super.setVisibility(visibility);
    }

    public final void updateStyle(StyleSheet styleSheet, RefinementListType refinementListType) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(refinementListType, "refinementListType");
        SpacerKey spacerKey = SpacerKey.SMALL;
        Integer spacerInPixels = styleSheet.getSpacerInPixels(spacerKey);
        if (spacerInPixels != null) {
            int iIntValue = spacerInPixels.intValue();
            setPadding(getPaddingLeft(), iIntValue, getPaddingRight(), iIntValue);
        }
        if (this.itemDecoration != null) {
            RecyclerView recyclerView = getRecyclerView();
            RecyclerView.ItemDecoration itemDecoration = this.itemDecoration;
            if (itemDecoration == null) {
                Intrinsics.throwUninitializedPropertyAccessException("itemDecoration");
                itemDecoration = null;
            }
            recyclerView.removeItemDecoration(itemDecoration);
        }
        this.itemDecoration = new ExposedRefinementListViewDecoration(styleSheet, refinementListType);
        RecyclerView recyclerView2 = getRecyclerView();
        RecyclerView.ItemDecoration itemDecoration2 = this.itemDecoration;
        if (itemDecoration2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemDecoration");
            itemDecoration2 = null;
        }
        recyclerView2.addItemDecoration(itemDecoration2);
        int i = WhenMappings.$EnumSwitchMapping$0[refinementListType.ordinal()];
        int iIntValue2 = 0;
        if (i == 1) {
            Integer spacerInPixels2 = styleSheet.getSpacerInPixels(spacerKey);
            if (spacerInPixels2 != null) {
                iIntValue2 = spacerInPixels2.intValue();
            }
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams == null) {
            return;
        }
        marginLayoutParams.bottomMargin = iIntValue2;
    }
}
