package com.amazon.mp3.recents.usecase;

import com.amazon.mp3.recents.converters.RecentsConverterFactory;
import com.amazon.mp3.recents.converters.RecentsPageGridConverter;
import com.amazon.mp3.recents.data.RecentsPageData;
import com.amazon.mp3.recents.repository.RecentsRepository;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.functions.Func1;

/* JADX INFO: compiled from: RecentsModelUseCase.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/recents/usecase/RecentsModelUseCase;", "", "recentsRepository", "Lcom/amazon/mp3/recents/repository/RecentsRepository;", "recentsConverterFactory", "Lcom/amazon/mp3/recents/converters/RecentsConverterFactory;", "(Lcom/amazon/mp3/recents/repository/RecentsRepository;Lcom/amazon/mp3/recents/converters/RecentsConverterFactory;)V", "getInitPageData", "Lrx/Observable;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsModelUseCase {
    private final RecentsConverterFactory recentsConverterFactory;
    private final RecentsRepository recentsRepository;

    public RecentsModelUseCase(RecentsRepository recentsRepository, RecentsConverterFactory recentsConverterFactory) {
        Intrinsics.checkNotNullParameter(recentsRepository, "recentsRepository");
        Intrinsics.checkNotNullParameter(recentsConverterFactory, "recentsConverterFactory");
        this.recentsRepository = recentsRepository;
        this.recentsConverterFactory = recentsConverterFactory;
    }

    public final Observable<PageGridViewModel> getInitPageData() {
        Observable map = this.recentsRepository.fetchRecentsPageGridData().map(new Func1() { // from class: com.amazon.mp3.recents.usecase.RecentsModelUseCase$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return RecentsModelUseCase.m1612getInitPageData$lambda0(this.f$0, (RecentsPageData) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "recentsRepository.fetchR…hildrenModels)\n        })");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitPageData$lambda-0, reason: not valid java name */
    public static final PageGridViewModel m1612getInitPageData$lambda0(RecentsModelUseCase this$0, RecentsPageData recentsPageData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RecentsPageGridConverter recentsConverter = this$0.recentsConverterFactory.getRecentsConverter();
        Intrinsics.checkNotNullExpressionValue(recentsPageData, "recentsPageData");
        return recentsConverter.convert2(recentsPageData, (List<? extends BaseViewModel>) recentsConverter.getChildrenModels(recentsPageData));
    }
}
