package com.amazon.mp3.library.mylibrary;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.SongsUtilKt;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.Outcome;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: MyLibraryDetailViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0004J\"\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00142\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0016J\u0014\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f0\tJ$\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00160\u00142\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0004J2\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00160\u00142\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00190\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/MyLibraryDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "TAG", "", "_isLibraryArtistsEmptyLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "isLibraryArtistsEmptyLiveData", "Landroidx/lifecycle/LiveData;", "()Landroidx/lifecycle/LiveData;", "myLikesPlaylist", "Lcom/amazon/music/platform/Outcome;", "Landroid/net/Uri;", "fetchMyLikesPlaylist", "", "context", "Landroid/content/Context;", PageUriUtils.SOURCE_QUERY_PARAM, "getMyLikesPlaylist", "Lrx/Observable;", "playlists", "", "getMyLikesPlaylistLiveData", "getPlaylists", "Lcom/amazon/mp3/library/item/Playlist;", "playlistsToUri", "updateLibraryArtistsEmptyLiveDataValue", "Lkotlinx/coroutines/Job;", "contentResolver", "Landroid/content/ContentResolver;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyLibraryDetailViewModel extends ViewModel {
    private final String TAG = Reflection.getOrCreateKotlinClass(MyLibraryDetailViewModel.class).getSimpleName();
    private final MutableLiveData<Boolean> _isLibraryArtistsEmptyLiveData;
    private final LiveData<Boolean> isLibraryArtistsEmptyLiveData;
    private final MutableLiveData<Outcome<Uri>> myLikesPlaylist;

    public MyLibraryDetailViewModel() {
        MutableLiveData<Outcome<Uri>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(null);
        this.myLikesPlaylist = mutableLiveData;
        MutableLiveData<Boolean> mutableLiveData2 = new MutableLiveData<>();
        this._isLibraryArtistsEmptyLiveData = mutableLiveData2;
        this.isLibraryArtistsEmptyLiveData = mutableLiveData2;
    }

    public final LiveData<Boolean> isLibraryArtistsEmptyLiveData() {
        return this.isLibraryArtistsEmptyLiveData;
    }

    public final Observable<Set<Playlist>> getPlaylists(final Context context, final String source) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(source, "source");
        Observable<Set<Playlist>> observableObserveOn = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailViewModel$$ExternalSyntheticLambda6
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MyLibraryDetailViewModel.m1317getPlaylists$lambda1(context, source);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "fromCallable {\n         …dSchedulers.mainThread())");
        return observableObserveOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getPlaylists$lambda-1, reason: not valid java name */
    public static final Set m1317getPlaylists$lambda1(Context context, String source) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(source, "$source");
        return PlaylistUtil.getPlaylists(context, source);
    }

    public final Observable<Set<Uri>> playlistsToUri(final Context context, final String source, final Set<? extends Playlist> playlists) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(playlists, "playlists");
        Observable<Set<Uri>> observableObserveOn = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailViewModel$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MyLibraryDetailViewModel.m1318playlistsToUri$lambda3(playlists, context, source);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "fromCallable {\n         …dSchedulers.mainThread())");
        return observableObserveOn;
    }

    public final Observable<Uri> getMyLikesPlaylist(final Context context, final Set<? extends Uri> playlists) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playlists, "playlists");
        Observable<Uri> observableObserveOn = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailViewModel$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MyLibraryDetailViewModel.m1316getMyLikesPlaylist$lambda5(playlists, context);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "fromCallable {\n         …dSchedulers.mainThread())");
        return observableObserveOn;
    }

    public final void fetchMyLikesPlaylist(final Context context, final String source) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(source, "source");
        getPlaylists(context, source).flatMap(new Func1() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailViewModel$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return MyLibraryDetailViewModel.m1312fetchMyLikesPlaylist$lambda6(this.f$0, context, source, (Set) obj);
            }
        }).flatMap(new Func1() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailViewModel$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return MyLibraryDetailViewModel.m1313fetchMyLikesPlaylist$lambda7(this.f$0, context, (Set) obj);
            }
        }).subscribe(new Action1() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailViewModel$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MyLibraryDetailViewModel.m1314fetchMyLikesPlaylist$lambda8(this.f$0, (Uri) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailViewModel$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MyLibraryDetailViewModel.m1315fetchMyLikesPlaylist$lambda9(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchMyLikesPlaylist$lambda-6, reason: not valid java name */
    public static final Observable m1312fetchMyLikesPlaylist$lambda6(MyLibraryDetailViewModel this$0, Context context, String source, Set playlists) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(source, "$source");
        Intrinsics.checkNotNullExpressionValue(playlists, "playlists");
        return this$0.playlistsToUri(context, source, playlists);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchMyLikesPlaylist$lambda-7, reason: not valid java name */
    public static final Observable m1313fetchMyLikesPlaylist$lambda7(MyLibraryDetailViewModel this$0, Context context, Set uriList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullExpressionValue(uriList, "uriList");
        return this$0.getMyLikesPlaylist(context, uriList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchMyLikesPlaylist$lambda-8, reason: not valid java name */
    public static final void m1314fetchMyLikesPlaylist$lambda8(MyLibraryDetailViewModel this$0, Uri myLikesPlaylistUri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MutableLiveData<Outcome<Uri>> mutableLiveData = this$0.myLikesPlaylist;
        Intrinsics.checkNotNullExpressionValue(myLikesPlaylistUri, "myLikesPlaylistUri");
        mutableLiveData.setValue(new Outcome.Success(myLikesPlaylistUri));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchMyLikesPlaylist$lambda-9, reason: not valid java name */
    public static final void m1315fetchMyLikesPlaylist$lambda9(MyLibraryDetailViewModel this$0, Throwable error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Error fetching playlists", error);
        MutableLiveData<Outcome<Uri>> mutableLiveData = this$0.myLikesPlaylist;
        Intrinsics.checkNotNullExpressionValue(error, "error");
        mutableLiveData.setValue(new Outcome.Failure(error));
    }

    public final LiveData<Outcome<Uri>> getMyLikesPlaylistLiveData() {
        return this.myLikesPlaylist;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.mylibrary.MyLibraryDetailViewModel$updateLibraryArtistsEmptyLiveDataValue$1, reason: invalid class name */
    /* JADX INFO: compiled from: MyLibraryDetailViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.library.mylibrary.MyLibraryDetailViewModel$updateLibraryArtistsEmptyLiveDataValue$1", f = "MyLibraryDetailViewModel.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, s = {})
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
            return MyLibraryDetailViewModel.this.new AnonymousClass1(this.$contentResolver, continuation);
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
                MutableLiveData mutableLiveData2 = MyLibraryDetailViewModel.this._isLibraryArtistsEmptyLiveData;
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getMyLikesPlaylist$lambda-5, reason: not valid java name */
    public static final Uri m1316getMyLikesPlaylist$lambda5(Set playlists, Context context) {
        Intrinsics.checkNotNullParameter(playlists, "$playlists");
        Intrinsics.checkNotNullParameter(context, "$context");
        Iterator it = playlists.iterator();
        while (it.hasNext()) {
            Uri uri = (Uri) it.next();
            if (!PlaylistUtil.canLikePlaylistTracks(context, uri)) {
                return uri;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playlistsToUri$lambda-3, reason: not valid java name */
    public static final Set m1318playlistsToUri$lambda3(Set playlists, Context context, String source) {
        Intrinsics.checkNotNullParameter(playlists, "$playlists");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(source, "$source");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(playlists, 10));
        Iterator it = playlists.iterator();
        while (it.hasNext()) {
            arrayList.add(MediaProvider.UdoPlaylistTracks.getContentUriFromLuid(context, source, ((Playlist) it.next()).getLuid()));
        }
        return CollectionsKt___CollectionsKt.toSet(arrayList);
    }
}
