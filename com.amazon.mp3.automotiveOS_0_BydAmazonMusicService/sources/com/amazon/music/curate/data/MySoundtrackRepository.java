package com.amazon.music.curate.data;

import android.content.Context;
import android.view.ContextMenu;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.amazon.music.curate.R;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.model.VisualRowItem;
import com.amazon.music.curate.model.station;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MySoundtrackRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J`\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\f2!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00110\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/curate/data/MySoundtrackRepository;", "", "overflowMenuProvider", "Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "(Lcom/amazon/music/curate/provider/OverflowMenuProvider;)V", "getMySoundtrack", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/amazon/music/curate/model/VisualModel;", "context", "Landroid/content/Context;", "onClickAction", "Lkotlin/Function1;", "Lcom/amazon/music/curate/model/Station;", "Lkotlin/ParameterName;", "name", "station", "", "onMoreClickAction", "Landroid/view/View;", "view", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MySoundtrackRepository {
    private final OverflowMenuProvider overflowMenuProvider;

    public MySoundtrackRepository(OverflowMenuProvider overflowMenuProvider) {
        Intrinsics.checkNotNullParameter(overflowMenuProvider, "overflowMenuProvider");
        this.overflowMenuProvider = overflowMenuProvider;
    }

    public final LiveData<PagedList<VisualModel>> getMySoundtrack(final Context context, final Function1<? super station, Unit> onClickAction, final Function1<? super View, Unit> onMoreClickAction) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onClickAction, "onClickAction");
        Intrinsics.checkNotNullParameter(onMoreClickAction, "onMoreClickAction");
        return new LivePagedListBuilder(new MySoundtrackDataSourceFactory(context).map(new Function1<station, VisualModel>() { // from class: com.amazon.music.curate.data.MySoundtrackRepository$getMySoundtrack$dataSourceFactory$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VisualModel invoke(final station mySoundtrack) {
                Intrinsics.checkNotNullParameter(mySoundtrack, "mySoundtrack");
                VisualRowItem.Builder builder = new VisualRowItem.Builder(mySoundtrack.getStationKey(), mySoundtrack.getImageUrl(), mySoundtrack.getTitle());
                Context context2 = context;
                Intrinsics.checkNotNull(context2);
                String string = context2.getResources().getString(R.string.station_label);
                Intrinsics.checkNotNullExpressionValue(string, "context!!.resources.getS…g(R.string.station_label)");
                VisualRowItem.Builder builderSecondaryText = builder.secondaryText(string);
                final Function1<station, Unit> function1 = onClickAction;
                VisualRowItem.Builder builderOnClick = builderSecondaryText.onClick(new Function0<Unit>() { // from class: com.amazon.music.curate.data.MySoundtrackRepository$getMySoundtrack$dataSourceFactory$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function1.invoke(mySoundtrack);
                    }
                });
                final Function1<View, Unit> function12 = onMoreClickAction;
                VisualRowItem.Builder builderOnMoreClick = builderOnClick.onMoreClick(new Function1<View, Unit>() { // from class: com.amazon.music.curate.data.MySoundtrackRepository$getMySoundtrack$dataSourceFactory$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view) {
                        invoke2(view);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(View it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        function12.invoke(it);
                    }
                });
                final MySoundtrackRepository mySoundtrackRepository = this;
                return builderOnMoreClick.onShowContextMenu(new Function2<ContextMenu, FragmentActivity, Unit>() { // from class: com.amazon.music.curate.data.MySoundtrackRepository$getMySoundtrack$dataSourceFactory$1.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    /* JADX INFO: renamed from: invoke */
                    public /* bridge */ /* synthetic */ Unit mo4invoke(ContextMenu contextMenu, FragmentActivity fragmentActivity) {
                        invoke2(contextMenu, fragmentActivity);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ContextMenu menu, FragmentActivity activity) {
                        Intrinsics.checkNotNullParameter(menu, "menu");
                        Intrinsics.checkNotNullParameter(activity, "activity");
                        mySoundtrackRepository.overflowMenuProvider.showOverflowMenu(mySoundtrack, menu, activity);
                    }
                }).build();
            }
        }), 1).build();
    }
}
