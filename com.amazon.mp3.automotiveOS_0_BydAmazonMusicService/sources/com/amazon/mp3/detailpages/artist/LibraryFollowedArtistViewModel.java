package com.amazon.mp3.detailpages.artist;

import android.app.Application;
import android.content.ContentResolver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel;
import com.amazon.mp3.library.util.SongsUtilKt;
import com.amazon.music.views.library.models.PageGridViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: LibraryFollowedArtistViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0017\u0010\u0013\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/detailpages/artist/LibraryFollowedArtistViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseAndroidViewModel;", "application", "Landroid/app/Application;", "pageDataRepo", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "usePagination", "", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;Z)V", "_isLibraryArtistsEmptyLiveData", "Landroidx/lifecycle/MutableLiveData;", "isLibraryArtistsEmptyLiveData", "Landroidx/lifecycle/LiveData;", "()Landroidx/lifecycle/LiveData;", "fetchMorePage", "", "pageIndex", "", "pageSize", "isFirstPage", "(Ljava/lang/Integer;)Z", "updateLibraryArtistsEmptyLiveDataValue", "Lkotlinx/coroutines/Job;", "contentResolver", "Landroid/content/ContentResolver;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryFollowedArtistViewModel extends BaseAndroidViewModel {
    private final MutableLiveData<Boolean> _isLibraryArtistsEmptyLiveData;
    private final LiveData<Boolean> isLibraryArtistsEmptyLiveData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LibraryFollowedArtistViewModel(Application application, PageDataRepository pageDataRepo, boolean z) {
        super(application, pageDataRepo, z, false, 8, null);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepo, "pageDataRepo");
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        this._isLibraryArtistsEmptyLiveData = mutableLiveData;
        this.isLibraryArtistsEmptyLiveData = mutableLiveData;
    }

    public final LiveData<Boolean> isLibraryArtistsEmptyLiveData() {
        return this.isLibraryArtistsEmptyLiveData;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel, com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public void fetchMorePage(int pageIndex, int pageSize) {
        PageGridViewModel value = getPageData().getValue();
        if (value != null) {
            value.getPageIndex();
        }
        if (!StringUtils.isEmpty(value == null ? null : value.getNextPageToken()) || isFirstPage(Integer.valueOf(pageIndex))) {
            super.fetchMorePage(pageIndex, pageSize);
        }
    }

    private final boolean isFirstPage(Integer pageIndex) {
        return pageIndex == null || pageIndex.intValue() == 0;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.artist.LibraryFollowedArtistViewModel$updateLibraryArtistsEmptyLiveDataValue$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryFollowedArtistViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.artist.LibraryFollowedArtistViewModel$updateLibraryArtistsEmptyLiveDataValue$1", f = "LibraryFollowedArtistViewModel.kt", i = {}, l = {61}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ContentResolver $contentResolver;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ContentResolver contentResolver, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$contentResolver = contentResolver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LibraryFollowedArtistViewModel.this.new AnonymousClass1(this.$contentResolver, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableLiveData mutableLiveData;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableLiveData mutableLiveData2 = LibraryFollowedArtistViewModel.this._isLibraryArtistsEmptyLiveData;
                ContentResolver contentResolver = this.$contentResolver;
                this.L$0 = mutableLiveData2;
                this.label = 1;
                Object objIsLibraryArtistsEmpty = SongsUtilKt.isLibraryArtistsEmpty(contentResolver, this);
                if (objIsLibraryArtistsEmpty == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableLiveData = mutableLiveData2;
                obj = objIsLibraryArtistsEmpty;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableLiveData = (MutableLiveData) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            mutableLiveData.postValue(obj);
            return Unit.INSTANCE;
        }
    }

    public final Job updateLibraryArtistsEmptyLiveDataValue(ContentResolver contentResolver) {
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(contentResolver, null), 2, null);
    }
}
