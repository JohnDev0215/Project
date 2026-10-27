package com.amazon.music.curate.view;

import android.content.Context;
import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import com.amazon.music.curate.data.MySoundtrackRepository;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.model.station;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MySoundtrackViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J)\u0010\u001b\u001a\u00020\u00152!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00150\u0010J)\u0010\u001d\u001a\u00020\u00152!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00150\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR+\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/curate/view/MySoundtrackViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "repository", "Lcom/amazon/music/curate/data/MySoundtrackRepository;", "(Landroid/content/Context;Lcom/amazon/music/curate/data/MySoundtrackRepository;)V", "getContext", "()Landroid/content/Context;", "mySoundtrack", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/amazon/music/curate/model/VisualModel;", "getMySoundtrack", "()Landroidx/lifecycle/LiveData;", "onClickAction", "Lkotlin/Function1;", "Lcom/amazon/music/curate/model/Station;", "Lkotlin/ParameterName;", "name", "stationKey", "", "onMoreClick", "Landroid/view/View;", "view", "getRepository", "()Lcom/amazon/music/curate/data/MySoundtrackRepository;", "setOnItemClick", "station", "setOnMoreClick", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MySoundtrackViewModel extends ViewModel {
    private final Context context;
    private final LiveData<PagedList<VisualModel>> mySoundtrack;
    private Function1<? super station, Unit> onClickAction;
    private Function1<? super View, Unit> onMoreClick;
    private final MySoundtrackRepository repository;

    public MySoundtrackViewModel(Context context, MySoundtrackRepository repository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.context = context;
        this.repository = repository;
        this.mySoundtrack = repository.getMySoundtrack(context, new Function1<station, Unit>() { // from class: com.amazon.music.curate.view.MySoundtrackViewModel$mySoundtrack$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(station stationVar) {
                invoke2(stationVar);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(station it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function1 function1 = this.this$0.onClickAction;
                if (function1 == null) {
                    return;
                }
                function1.invoke(it);
            }
        }, new Function1<View, Unit>() { // from class: com.amazon.music.curate.view.MySoundtrackViewModel$mySoundtrack$2
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
                Function1 function1 = this.this$0.onMoreClick;
                if (function1 == null) {
                    return;
                }
                function1.invoke(it);
            }
        });
    }

    public final Context getContext() {
        return this.context;
    }

    public final MySoundtrackRepository getRepository() {
        return this.repository;
    }

    public final LiveData<PagedList<VisualModel>> getMySoundtrack() {
        return this.mySoundtrack;
    }

    public final void setOnItemClick(Function1<? super station, Unit> onClickAction) {
        Intrinsics.checkNotNullParameter(onClickAction, "onClickAction");
        this.onClickAction = onClickAction;
    }

    public final void setOnMoreClick(Function1<? super View, Unit> onMoreClick) {
        Intrinsics.checkNotNullParameter(onMoreClick, "onMoreClick");
        this.onMoreClick = onMoreClick;
    }
}
