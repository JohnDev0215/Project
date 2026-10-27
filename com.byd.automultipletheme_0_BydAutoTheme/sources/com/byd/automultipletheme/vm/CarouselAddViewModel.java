package com.byd.automultipletheme.vm;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.AssetsUtils;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.WallpaperManagerUtils;
import com.byd.automultipletheme.common.db.DB;
import com.byd.automultipletheme.common.db.DBUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: CarouselAddViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000eJ\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0006J\"\u0010\u001a\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u000e\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000eJ\u0014\u0010\u001f\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0016\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u00060\rj\b\u0012\u0004\u0012\u00020\u0006`\u000fJ\u0006\u0010!\u001a\u00020\u0015J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00150#J\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010%J\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010%J\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rj\n\u0012\u0004\u0012\u00020\u000e\u0018\u0001`\u000fJ$\u0010'\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00130\fJ$\u0010(\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00130\fJ\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000eH\u0002J\u0006\u0010,\u001a\u00020\u0018J\u0006\u0010-\u001a\u00020\u0018J\u0014\u0010.\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J!\u0010/\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u00101J\u0014\u00102\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\"\u00102\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0014\u00103\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u000e\u00104\u001a\u00020\u00182\u0006\u00105\u001a\u00020\u0015J\u000e\u00106\u001a\u00020\u00182\u0006\u00107\u001a\u00020\u0015J\u001e\u00108\u001a\u00020\u00182\u0016\u00109\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fJ&\u0010:\u001a\u00020\u00182\u001e\u0010;\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0013J&\u0010<\u001a\u00020\u00182\u001e\u0010;\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0013R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR'\u0010\u000b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R,\u0010\u0012\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00130\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010\u0016\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00130\fX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006="}, d2 = {"Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "localPhotoLiveData", "Landroidx/lifecycle/MediatorLiveData;", "", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "getLocalPhotoLiveData", "()Landroidx/lifecycle/MediatorLiveData;", "localWallpaperLiveData", "getLocalWallpaperLiveData", "numCount", "Landroidx/lifecycle/MutableLiveData;", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getNumCount", "()Landroidx/lifecycle/MutableLiveData;", "photoSelectedPair", "Lkotlin/Pair;", "selectCountLiveData", "", "wallpaperSelectedPair", "addNumCount", "", "name", "deleteCarouselRes", "carouselPicture", "wallpaperList", "photoList", "deleteNumCount", "deletePhotoRes", "getCarouselRes", "getCarouselTime", "getCountLiveData", "Landroidx/lifecycle/LiveData;", "getLocalPhotoList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocalWallpaperList", "getPhotoSelectedPair", "getWallpaperSelectedPair", "isBad", "", "path", "loadLocalPhotoLiveData", "loadLocalWallpaperLiveData", "saveAnotherPresetCarouselResources", "saveCarouselFiles", "orientation", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveCarouselRes", "saveCarouselResources", "setCarouselTime", DB.CAROUSEL_TIME_TIME, "setCountLiveData", "count", "setNumCount", IntentString.DATA_LIST, "setPhotoSelectedPair", "pair", "setWallpaperSelectedPair", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarouselAddViewModel extends ViewModel {
    private MutableLiveData<Integer> selectCountLiveData;
    private final MediatorLiveData<List<CarouselPicture>> localWallpaperLiveData = new MediatorLiveData<>();
    private final MediatorLiveData<List<CarouselPicture>> localPhotoLiveData = new MediatorLiveData<>();
    private final MutableLiveData<ArrayList<String>> numCount = new MutableLiveData<>();
    private MutableLiveData<Pair<List<CarouselPicture>, List<CarouselPicture>>> wallpaperSelectedPair = new MutableLiveData<>();
    private MutableLiveData<Pair<List<CarouselPicture>, List<CarouselPicture>>> photoSelectedPair = new MutableLiveData<>();

    public final MediatorLiveData<List<CarouselPicture>> getLocalWallpaperLiveData() {
        return this.localWallpaperLiveData;
    }

    public final MediatorLiveData<List<CarouselPicture>> getLocalPhotoLiveData() {
        return this.localPhotoLiveData;
    }

    public final MutableLiveData<ArrayList<String>> getNumCount() {
        return this.numCount;
    }

    public final MutableLiveData<Pair<List<CarouselPicture>, List<CarouselPicture>>> getWallpaperSelectedPair() {
        return this.wallpaperSelectedPair;
    }

    public final MutableLiveData<Pair<List<CarouselPicture>, List<CarouselPicture>>> getPhotoSelectedPair() {
        return this.photoSelectedPair;
    }

    public final void setWallpaperSelectedPair(Pair<? extends List<CarouselPicture>, ? extends List<CarouselPicture>> pair) {
        Intrinsics.checkNotNullParameter(pair, "pair");
        this.wallpaperSelectedPair.setValue(pair);
    }

    public final void setPhotoSelectedPair(Pair<? extends List<CarouselPicture>, ? extends List<CarouselPicture>> pair) {
        Intrinsics.checkNotNullParameter(pair, "pair");
        this.photoSelectedPair.setValue(pair);
    }

    public final void addNumCount(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ArrayList<String> value = this.numCount.getValue();
        if (value != null) {
            value.add(name);
        }
        MutableLiveData<ArrayList<String>> mutableLiveData = this.numCount;
        mutableLiveData.setValue(mutableLiveData.getValue());
        LogUtils.i("linkedListNumCountValue: ", this.numCount.getValue());
    }

    public final void deleteNumCount(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ArrayList<String> value = this.numCount.getValue();
        if (value != null) {
            value.remove(name);
        }
        MutableLiveData<ArrayList<String>> mutableLiveData = this.numCount;
        mutableLiveData.setValue(mutableLiveData.getValue());
    }

    /* JADX INFO: renamed from: getNumCount, reason: collision with other method in class */
    public final ArrayList<String> m105getNumCount() {
        return this.numCount.getValue();
    }

    public final void setNumCount(ArrayList<String> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.numCount.setValue(list);
    }

    public final LiveData<Integer> getCountLiveData() {
        if (this.selectCountLiveData == null) {
            MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
            this.selectCountLiveData = mutableLiveData;
            Intrinsics.checkNotNull(mutableLiveData);
            mutableLiveData.setValue(Integer.valueOf(getCarouselRes().size()));
        }
        MutableLiveData<Integer> mutableLiveData2 = this.selectCountLiveData;
        Intrinsics.checkNotNull(mutableLiveData2);
        return mutableLiveData2;
    }

    public final void setCountLiveData(int count) {
        MutableLiveData<Integer> mutableLiveData = this.selectCountLiveData;
        if (mutableLiveData != null) {
            mutableLiveData.setValue(Integer.valueOf(count));
        }
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$loadLocalWallpaperLiveData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$loadLocalWallpaperLiveData$1", f = "CarouselAddViewModel.kt", i = {}, l = {108}, m = "invokeSuspend", n = {}, s = {})
    static final class C00491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00491(Continuation<? super C00491> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CarouselAddViewModel.this.new C00491(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = CarouselAddViewModel.this.getLocalWallpaperList(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List<CarouselPicture> list = (List) obj;
            CarouselAddViewModel.this.getLocalWallpaperLiveData().setValue(list);
            LogUtils.i("localWallpaper: ", list);
            return Unit.INSTANCE;
        }
    }

    public final void loadLocalWallpaperLiveData() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C00491(null), 3, null);
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$getLocalWallpaperList$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$getLocalWallpaperList$2", f = "CarouselAddViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00472 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<CarouselPicture>>, Object> {
        int label;

        C00472(Continuation<? super C00472> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00472(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<CarouselPicture>> continuation) {
            return ((C00472) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ArrayList arrayList = new ArrayList();
                ArrayList<AutoWallpaper> assetsAutoWallpaper = AssetsUtils.INSTANCE.getAssetsAutoWallpaper();
                if (assetsAutoWallpaper.size() > 0) {
                    AutoWallpaper autoWallpaper = assetsAutoWallpaper.get(0);
                    Intrinsics.checkNotNullExpressionValue(autoWallpaper, "assetsList[0]");
                    AutoWallpaper autoWallpaper2 = autoWallpaper;
                    List<String> land = autoWallpaper2.getImgUrl().getLand();
                    List<String> port = autoWallpaper2.getImgUrl().getPort();
                    int size = land.size();
                    for (int i = 0; i < size; i++) {
                        arrayList.add(new CarouselPicture(port.get(i), port.get(i), land.get(i), land.get(i), false, null, 48, null));
                    }
                    int size2 = assetsAutoWallpaper.size();
                    for (int i2 = 2; i2 < size2; i2++) {
                        AutoWallpaper autoWallpaper3 = assetsAutoWallpaper.get(i2);
                        Intrinsics.checkNotNullExpressionValue(autoWallpaper3, "assetsList[i]");
                        AutoWallpaper autoWallpaper4 = autoWallpaper3;
                        List<String> land2 = autoWallpaper4.getImgUrl().getLand();
                        List<String> port2 = autoWallpaper4.getImgUrl().getPort();
                        arrayList.add(new CarouselPicture(port2.get(0), port2.get(0), land2.get(0), land2.get(0), false, null, 48, null));
                    }
                }
                return arrayList;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getLocalWallpaperList(Continuation<? super List<CarouselPicture>> continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new C00472(null), continuation);
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$loadLocalPhotoLiveData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$loadLocalPhotoLiveData$1", f = "CarouselAddViewModel.kt", i = {}, l = {154}, m = "invokeSuspend", n = {}, s = {})
    static final class C00481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00481(Continuation<? super C00481> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CarouselAddViewModel.this.new C00481(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = CarouselAddViewModel.this.getLocalPhotoList(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            CarouselAddViewModel.this.getLocalPhotoLiveData().setValue((List) obj);
            return Unit.INSTANCE;
        }
    }

    public final void loadLocalPhotoLiveData() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C00481(null), 3, null);
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$getLocalPhotoList$2, reason: invalid class name */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$getLocalPhotoList$2", f = "CarouselAddViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<CarouselPicture>>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CarouselAddViewModel.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<CarouselPicture>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorQuery = Utils.getApp().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "_display_name"}, "mime_type=? or mime_type=?", new String[]{"image/jpeg", "image/png"}, "date_modified desc");
                ArrayList arrayList = new ArrayList();
                if (cursorQuery != null) {
                    CarouselAddViewModel carouselAddViewModel = CarouselAddViewModel.this;
                    while (cursorQuery.moveToNext()) {
                        int columnIndex = cursorQuery.getColumnIndex("_data");
                        int columnIndex2 = cursorQuery.getColumnIndex("_display_name");
                        String path = cursorQuery.getString(columnIndex);
                        Intrinsics.checkNotNullExpressionValue(path, "path");
                        if (!carouselAddViewModel.isBad(path)) {
                            arrayList.add(new CarouselPicture(path, path, path, path, false, cursorQuery.getString(columnIndex2)));
                        }
                    }
                    cursorQuery.close();
                }
                LogUtils.i("loading Time cost: ", Boxing.boxLong(System.currentTimeMillis() - jCurrentTimeMillis));
                return arrayList;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getLocalPhotoList(Continuation<? super List<CarouselPicture>> continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new AnonymousClass2(null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isBad(String path) {
        if (!new File(path).exists()) {
            return true;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        return options.mCancel || options.outWidth <= 0 || options.outHeight <= 0;
    }

    public final ArrayList<CarouselPicture> getCarouselRes() throws ExecutionException, InterruptedException {
        ArrayList<CarouselPicture> carouselRes = DBUtils.INSTANCE.getCarouselRes();
        if (carouselRes.isEmpty()) {
            carouselRes = DBUtils.INSTANCE.getCarouselAssetsRes();
        }
        return carouselRes;
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$saveCarouselRes$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$saveCarouselRes$1", f = "CarouselAddViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<CarouselPicture> $photoList;
        final /* synthetic */ List<CarouselPicture> $wallpaperList;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00521(List<CarouselPicture> list, List<CarouselPicture> list2, Continuation<? super C00521> continuation) {
            super(2, continuation);
            this.$wallpaperList = list;
            this.$photoList = list2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00521(this.$wallpaperList, this.$photoList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DBUtils.INSTANCE.saveCarouselRes((ArrayList) this.$wallpaperList, (ArrayList) this.$photoList);
            return Unit.INSTANCE;
        }
    }

    public final void saveCarouselRes(List<CarouselPicture> wallpaperList, List<CarouselPicture> photoList) {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        Intrinsics.checkNotNullParameter(photoList, "photoList");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C00521(wallpaperList, photoList, null), 3, null);
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$saveCarouselRes$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$saveCarouselRes$2", f = "CarouselAddViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00532 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<CarouselPicture> $wallpaperList;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00532(List<CarouselPicture> list, Continuation<? super C00532> continuation) {
            super(2, continuation);
            this.$wallpaperList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00532(this.$wallpaperList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00532) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DBUtils.INSTANCE.saveCarouselRes((ArrayList) this.$wallpaperList);
            return Unit.INSTANCE;
        }
    }

    public final void saveCarouselRes(List<CarouselPicture> wallpaperList) {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C00532(wallpaperList, null), 3, null);
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$saveCarouselResources$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$saveCarouselResources$1", f = "CarouselAddViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<CarouselPicture> $wallpaperList;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00541(List<CarouselPicture> list, Continuation<? super C00541> continuation) {
            super(2, continuation);
            this.$wallpaperList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00541(this.$wallpaperList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (CarouselPicture carouselPicture : this.$wallpaperList) {
                try {
                    String landLight = carouselPicture.getLandLight();
                    Intrinsics.checkNotNull(landLight);
                    String resourceNameLand = FileUtils.getFileNameNoExtension(landLight);
                    AssetsUtils assetsUtils = AssetsUtils.INSTANCE;
                    String landLight2 = carouselPicture.getLandLight();
                    Intrinsics.checkNotNull(landLight2);
                    Bitmap bitmap = assetsUtils.getBitmap(landLight2);
                    Intrinsics.checkNotNullExpressionValue(resourceNameLand, "resourceNameLand");
                    linkedHashMap.put(resourceNameLand, bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            WallpaperManagerUtils.getInstance().setBitmapList(linkedHashMap, 0, 4, true);
            SPUtils.getInstance().put(Const.TYPE_OF_DIRECTION, Const.DIRECTION_LAND);
            linkedHashMap.clear();
            return Unit.INSTANCE;
        }
    }

    public final void saveCarouselResources(List<CarouselPicture> wallpaperList) {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C00541(wallpaperList, null), 2, null);
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$saveAnotherPresetCarouselResources$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$saveAnotherPresetCarouselResources$1", f = "CarouselAddViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00501 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<CarouselPicture> $wallpaperList;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00501(List<CarouselPicture> list, Continuation<? super C00501> continuation) {
            super(2, continuation);
            this.$wallpaperList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00501(this.$wallpaperList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00501) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (CarouselPicture carouselPicture : this.$wallpaperList) {
                try {
                    String portLight = carouselPicture.getPortLight();
                    Intrinsics.checkNotNull(portLight);
                    String resourceNamePort = FileUtils.getFileNameNoExtension(portLight);
                    AssetsUtils assetsUtils = AssetsUtils.INSTANCE;
                    String portLight2 = carouselPicture.getPortLight();
                    Intrinsics.checkNotNull(portLight2);
                    Bitmap bitmap = assetsUtils.getBitmap(portLight2);
                    Intrinsics.checkNotNullExpressionValue(resourceNamePort, "resourceNamePort");
                    linkedHashMap.put(resourceNamePort, bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            WallpaperManagerUtils.getInstance().setBitmapList(linkedHashMap, 1, 4, true);
            SPUtils.getInstance().put(Const.TYPE_OF_DIRECTION, Const.DIRECTION_PORT);
            linkedHashMap.clear();
            return Unit.INSTANCE;
        }
    }

    public final void saveAnotherPresetCarouselResources(List<CarouselPicture> wallpaperList) {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C00501(wallpaperList, null), 2, null);
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$saveCarouselFiles$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$saveCarouselFiles$2", f = "CarouselAddViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00512 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $orientation;
        final /* synthetic */ String $path;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00512(String str, String str2, Continuation<? super C00512> continuation) {
            super(2, continuation);
            this.$path = str;
            this.$orientation = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00512(this.$path, this.$orientation, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00512) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AssetsUtils.INSTANCE.getCarouselBitmapFromAssets(this.$path, this.$orientation);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object saveCarouselFiles(String str, String str2, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C00512(str, str2, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$deleteCarouselRes$1, reason: invalid class name */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$deleteCarouselRes$1", f = "CarouselAddViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<CarouselPicture> $photoList;
        final /* synthetic */ List<CarouselPicture> $wallpaperList;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<CarouselPicture> list, List<CarouselPicture> list2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$wallpaperList = list;
            this.$photoList = list2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$wallpaperList, this.$photoList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DBUtils.INSTANCE.deleteCarouselRes((ArrayList) this.$wallpaperList, (ArrayList) this.$photoList);
            return Unit.INSTANCE;
        }
    }

    public final void deleteCarouselRes(List<CarouselPicture> wallpaperList, List<CarouselPicture> photoList) {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        Intrinsics.checkNotNullParameter(photoList, "photoList");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(wallpaperList, photoList, null), 2, null);
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.vm.CarouselAddViewModel$deletePhotoRes$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CarouselAddViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.vm.CarouselAddViewModel$deletePhotoRes$1", f = "CarouselAddViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C00461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<CarouselPicture> $wallpaperList;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00461(List<CarouselPicture> list, Continuation<? super C00461> continuation) {
            super(2, continuation);
            this.$wallpaperList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00461(this.$wallpaperList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DBUtils.INSTANCE.deleteCarouselRes((ArrayList<CarouselPicture>) this.$wallpaperList);
            return Unit.INSTANCE;
        }
    }

    public final void deletePhotoRes(List<CarouselPicture> wallpaperList) {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C00461(wallpaperList, null), 2, null);
    }

    public final int deleteCarouselRes(CarouselPicture carouselPicture) {
        Intrinsics.checkNotNullParameter(carouselPicture, "carouselPicture");
        DBUtils dBUtils = DBUtils.INSTANCE;
        String portLight = carouselPicture.getPortLight();
        Intrinsics.checkNotNull(portLight);
        return dBUtils.deleteCarouselRes(portLight);
    }

    public final int getCarouselTime() {
        return DBUtils.INSTANCE.getCarouselTime();
    }

    public final void setCarouselTime(int time) throws ExecutionException, InterruptedException {
        DBUtils.INSTANCE.setCarouselTime(time);
    }
}
