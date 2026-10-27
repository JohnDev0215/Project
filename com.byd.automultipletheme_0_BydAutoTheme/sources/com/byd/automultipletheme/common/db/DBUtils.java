package com.byd.automultipletheme.common.db;

import android.database.ContentObserver;
import android.net.Uri;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.IntentString;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bJ\u001e\u0010\t\u001a\u00020\b2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fJ6\u0010\t\u001a\u00020\b2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fJ\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fJ\u0016\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fJ\u0006\u0010\u0013\u001a\u00020\bJ\u0006\u0010\u0014\u001a\u00020\bJ\u0006\u0010\u0015\u001a\u00020\bJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\bJ\u001e\u0010\u001b\u001a\u00020\b2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fJ6\u0010\u001b\u001a\u00020\b2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fJ\u001e\u0010\u001c\u001a\u00020\b2\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fJ\u000e\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\bJ\u000e\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\bJ2\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00170&J\u000e\u0010'\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/byd/automultipletheme/common/db/DBUtils;", "", "()V", "executorService", "Ljava/util/concurrent/ExecutorService;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "deleteAllCarouselRes", "", "deleteCarouselRes", DB.CAROUSEL_RES_PATH0, "", "wallpaperList", "Ljava/util/ArrayList;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "Lkotlin/collections/ArrayList;", "photoList", "getCarouselAssetsRes", "getCarouselRes", "getCarouselTime", "getCurrentWallpaperId", "getWallpaperId", "registerWallpaperResult", "", "contentObserver", "Landroid/database/ContentObserver;", "saveCarouselPath", "saveCarouselRes", "setCarouselRes", IntentString.DATA_LIST, "setCarouselTime", DB.CAROUSEL_TIME_TIME, "setCurrentWallpaperId", "resId", "setWallpaper", "path", IntentString.DATA_TYPE, "onComplete", "Lkotlin/Function1;", "unRegister", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DBUtils {
    public static final DBUtils INSTANCE = new DBUtils();
    private static ExecutorService executorService;
    private static final CoroutineScope scope;

    private DBUtils() {
    }

    static {
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewCachedThreadPool, "newCachedThreadPool()");
        executorService = executorServiceNewCachedThreadPool;
        scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain());
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.common.db.DBUtils$setWallpaper$1, reason: invalid class name */
    /* JADX INFO: compiled from: DBUtils.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.automultipletheme.common.db.DBUtils$setWallpaper$1", f = "DBUtils.kt", i = {}, l = {27}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Integer, Unit> $onComplete;
        final /* synthetic */ String $path;
        final /* synthetic */ int $resId;
        final /* synthetic */ int $type;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super Integer, Unit> function1, String str, int i, int i2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$onComplete = function1;
            this.$path = str;
            this.$type = i;
            this.$resId = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$onComplete, this.$path, this.$type, this.$resId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BuildersKt.withContext(Dispatchers.getIO(), new DBUtils$setWallpaper$1$result$1(this.$path, this.$type, this.$resId, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.$onComplete.invoke(Boxing.boxInt(((Number) obj).intValue()));
            return Unit.INSTANCE;
        }
    }

    public final void setWallpaper(String path, int type, int resId, Function1<? super Integer, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(onComplete, path, type, resId, null), 3, null);
    }

    public final void registerWallpaperResult(ContentObserver contentObserver) {
        Intrinsics.checkNotNullParameter(contentObserver, "contentObserver");
        Utils.getApp().getContentResolver().registerContentObserver(Uri.parse(DB.WALLPAPER_RESULT_URI), true, contentObserver);
    }

    public final void unRegister(ContentObserver contentObserver) {
        Intrinsics.checkNotNullParameter(contentObserver, "contentObserver");
        Utils.getApp().getContentResolver().unregisterContentObserver(contentObserver);
    }

    public final int getWallpaperId() throws ExecutionException, InterruptedException {
        Object obj = executorService.submit(new QueryWallpaperCallable()).get();
        Intrinsics.checkNotNullExpressionValue(obj, "result.get()");
        return ((Number) obj).intValue();
    }

    public final int getCarouselTime() throws ExecutionException, InterruptedException {
        Object obj = executorService.submit(new QueryCarouselTimeCallable()).get();
        Intrinsics.checkNotNullExpressionValue(obj, "result.get()");
        return ((Number) obj).intValue();
    }

    public final int setCarouselTime(int time) throws ExecutionException, InterruptedException {
        Object obj = executorService.submit(new UpdateCarouselTimeCallable(time)).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(U…TimeCallable(time)).get()");
        return ((Number) obj).intValue();
    }

    public final ArrayList<CarouselPicture> getCarouselRes() throws ExecutionException, InterruptedException {
        Object obj = executorService.submit(new QueryCarouselResCallable()).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(Q…ouselResCallable()).get()");
        return (ArrayList) obj;
    }

    public final ArrayList<CarouselPicture> getCarouselAssetsRes() throws ExecutionException, InterruptedException {
        Object obj = executorService.submit(new QueryAssetsCarouselResCallable()).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(Q…ouselResCallable()).get()");
        return (ArrayList) obj;
    }

    public final int setCarouselRes(ArrayList<CarouselPicture> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        Object obj = executorService.submit(new UpdateCarouselResCallable(list)).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(U…lResCallable(list)).get()");
        return ((Number) obj).intValue();
    }

    public final int saveCarouselRes(ArrayList<CarouselPicture> wallpaperList, ArrayList<CarouselPicture> photoList) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        Intrinsics.checkNotNullParameter(photoList, "photoList");
        Object obj = executorService.submit(new SaveCarouselResCallable(wallpaperList, photoList)).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(S…erList, photoList)).get()");
        return ((Number) obj).intValue();
    }

    public final int saveCarouselRes(ArrayList<CarouselPicture> wallpaperList) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        Object obj = executorService.submit(new SaveAllCarouselResCallable(wallpaperList)).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(S…ble(wallpaperList)).get()");
        return ((Number) obj).intValue();
    }

    public final int deleteCarouselRes(ArrayList<CarouselPicture> wallpaperList, ArrayList<CarouselPicture> photoList) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        Intrinsics.checkNotNullParameter(photoList, "photoList");
        Object obj = executorService.submit(new DeleteCarouselCallable(wallpaperList, photoList)).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(D…erList, photoList)).get()");
        return ((Number) obj).intValue();
    }

    public final int deleteCarouselRes(ArrayList<CarouselPicture> wallpaperList) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(wallpaperList, "wallpaperList");
        Object obj = executorService.submit(new DeleteCarouselWallpaperCallable(wallpaperList)).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(D…ble(wallpaperList)).get()");
        return ((Number) obj).intValue();
    }

    public final int deleteCarouselRes(String path0) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(path0, "path0");
        Object obj = executorService.submit(new DeleteCarouselResCallable(path0)).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(D…ResCallable(path0)).get()");
        return ((Number) obj).intValue();
    }

    public final int deleteAllCarouselRes() {
        Object obj = executorService.submit(new DeleteAllCarouselCallable()).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(D…CarouselCallable()).get()");
        return ((Number) obj).intValue();
    }

    public final int getCurrentWallpaperId() throws ExecutionException, InterruptedException {
        Object obj = executorService.submit(new QueryWallpaperIdCallable()).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(Q…lpaperIdCallable()).get()");
        return ((Number) obj).intValue();
    }

    public final int setCurrentWallpaperId(int resId) throws ExecutionException, InterruptedException {
        Object obj = executorService.submit(new UpdateWallpaperIdCallable(resId)).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(U…rIdCallable(resId)).get()");
        return ((Number) obj).intValue();
    }

    public final int saveCarouselPath() {
        Object obj = executorService.submit(new SaveCarouselResPathCallable()).get();
        Intrinsics.checkNotNullExpressionValue(obj, "executorService.submit(S…lResPathCallable()).get()");
        return ((Number) obj).intValue();
    }
}
