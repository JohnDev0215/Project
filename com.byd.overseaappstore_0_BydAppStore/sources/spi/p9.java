package spi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;
import com.byd.overseaappstore.module.ability.R$string;
import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.generator.DownloadUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.req.DownloadUrlRefreshReq;
import com.byd.overseaappstore.module.ability.data.rsp.DownloadUrlRefreshRsp;
import com.byd.overseaappstore.module.ability.util.SettingUtil;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.storage.StorageState;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class p9 {

    @NotNull
    public final oa a;

    @NotNull
    public final ExecutorDialogRouterMgr b;

    @NotNull
    public final DataGenerator c;

    @NotNull
    public final Context d;

    @NotNull
    public final h7 e;

    @NotNull
    public final DownloadUiStateGenerator f;

    @Nullable
    public Toast g;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.ExecutorHciCheckPlugin", f = "ExecutorHciCheckPlugin.kt", i = {0, 0}, l = {187}, m = "checkForDownloadUrlRefresh", n = {"this", "optParamList"}, s = {"L$0", "L$1"})
    public static final class a extends ContinuationImpl {
        public p9 a;
        public List b;
        public /* synthetic */ Object c;
        public int e;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.c = obj;
            this.e |= Integer.MIN_VALUE;
            return p9.this.a(null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.ExecutorHciCheckPlugin$checkForDownloadUrlRefresh$sourceDataBody$1", f = "ExecutorHciCheckPlugin.kt", i = {}, l = {187, 187}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SourceDataBody<NetRspDataBody<List<? extends DownloadUrlRefreshRsp>>>>, Object> {
        public int a;
        public final /* synthetic */ DownloadUrlRefreshReq c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(DownloadUrlRefreshReq downloadUrlRefreshReq, Continuation<? super b> continuation) {
            super(2, continuation);
            this.c = downloadUrlRefreshReq;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return p9.this.new b(this.c, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super SourceDataBody<NetRspDataBody<List<? extends DownloadUrlRefreshRsp>>>> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                h7 h7Var = p9.this.e;
                DownloadUrlRefreshReq downloadUrlRefreshReq = this.c;
                this.a = 1;
                Objects.requireNonNull(h7Var);
                final Flow flow = FlowKt.flow(new f7(h7Var, downloadUrlRefreshReq, null));
                obj = FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<NetRspDataBody<List<? extends DownloadUrlRefreshRsp>>>>() { // from class: com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$$inlined$map$1

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$$inlined$map$1$2, reason: invalid class name */
                    public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                        public final /* synthetic */ FlowCollector a;

                        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$$inlined$map$1$2$1, reason: invalid class name */
                        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$$inlined$map$1$2", f = "DownloadRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
                        public static final class AnonymousClass1 extends ContinuationImpl {
                            public Object L$0;
                            public int label;
                            public /* synthetic */ Object result;

                            public AnonymousClass1(Continuation continuation) {
                                super(continuation);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            @Nullable
                            public final Object invokeSuspend(@NotNull Object obj) {
                                this.result = obj;
                                this.label |= Integer.MIN_VALUE;
                                return AnonymousClass2.this.emit(null, this);
                            }
                        }

                        public AnonymousClass2(FlowCollector flowCollector) {
                            this.a = flowCollector;
                        }

                        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @org.jetbrains.annotations.Nullable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public final java.lang.Object emit(java.lang.Object r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r18) {
                            /*
                                r16 = this;
                                r0 = r16
                                r1 = r18
                                boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                if (r2 == 0) goto L17
                                r2 = r1
                                com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                                int r3 = r2.label
                                r4 = -2147483648(0xffffffff80000000, float:-0.0)
                                r5 = r3 & r4
                                if (r5 == 0) goto L17
                                int r3 = r3 - r4
                                r2.label = r3
                                goto L1c
                            L17:
                                com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$$inlined$map$1$2$1
                                r2.<init>(r1)
                            L1c:
                                java.lang.Object r1 = r2.result
                                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                int r4 = r2.label
                                r5 = 1
                                if (r4 == 0) goto L35
                                if (r4 != r5) goto L2d
                                kotlin.ResultKt.throwOnFailure(r1)
                                goto L9c
                            L2d:
                                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                                r1.<init>(r2)
                                throw r1
                            L35:
                                kotlin.ResultKt.throwOnFailure(r1)
                                kotlinx.coroutines.flow.FlowCollector r1 = r0.a
                                r9 = r17
                                com.byd.overseaappstore.module.ability.data.NetRspDataBody r9 = (com.byd.overseaappstore.module.ability.data.NetRspDataBody) r9
                                com.byd.overseaappstore.module.ability.data.SourceDataBody r4 = new com.byd.overseaappstore.module.ability.data.SourceDataBody
                                if (r9 != 0) goto L4d
                                r11 = 0
                                r13 = 0
                                r14 = 5
                                r15 = 0
                                java.lang.String r12 = "no data"
                                r10 = r4
                                r10.<init>(r11, r12, r13, r14, r15)
                                goto L55
                            L4d:
                                r7 = 0
                                r8 = 0
                                r10 = 3
                                r11 = 0
                                r6 = r4
                                r6.<init>(r7, r8, r9, r10, r11)
                            L55:
                                int r6 = r4.getRet()
                                java.lang.Object r7 = r4.getApiData()
                                com.byd.overseaappstore.module.ability.data.NetRspDataBody r7 = (com.byd.overseaappstore.module.ability.data.NetRspDataBody) r7
                                if (r7 == 0) goto L72
                                java.lang.Object r7 = r7.getData()
                                java.util.List r7 = (java.util.List) r7
                                if (r7 == 0) goto L72
                                int r7 = r7.size()
                                java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
                                goto L73
                            L72:
                                r7 = 0
                            L73:
                                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                                r8.<init>()
                                java.lang.String r9 = "repo-vm refreshDownloadUrl ret = "
                                r8.append(r9)
                                r8.append(r6)
                                java.lang.String r6 = " size = "
                                r8.append(r6)
                                r8.append(r7)
                                java.lang.String r6 = r8.toString()
                                r7 = 0
                                java.lang.Object[] r7 = new java.lang.Object[r7]
                                spi.ct.a(r6, r7)
                                r2.label = r5
                                java.lang.Object r1 = r1.emit(r4, r2)
                                if (r1 != r3) goto L9c
                                return r3
                            L9c:
                                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                                return r1
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.DownloadRepository$refreshDownloadUrl$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    @Nullable
                    public final Object collect(@NotNull FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends DownloadUrlRefreshRsp>>>> flowCollector, @NotNull Continuation continuation) {
                        Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                    }
                }, new g7(h7Var, null)), Dispatchers.getIO());
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.a = 2;
            obj = FlowKt.first((Flow) obj, this);
            return obj == coroutine_suspended ? coroutine_suspended : obj;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.ExecutorHciCheckPlugin", f = "ExecutorHciCheckPlugin.kt", i = {0, 0, 0}, l = {135}, m = "checkForShowingWifiOnlyWorkAlert", n = {"this", "optParamList", "bizSrc"}, s = {"L$0", "L$1", "L$2"})
    public static final class c extends ContinuationImpl {
        public p9 a;
        public List b;
        public a2 c;
        public /* synthetic */ Object d;
        public int f;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.d = obj;
            this.f |= Integer.MIN_VALUE;
            return p9.this.c(null, null, this);
        }
    }

    public p9(@NotNull oa executorSoftwareStateMgr, @NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull DataGenerator dataGenerator, @NotNull Context context, @NotNull h7 downloadRepository, @NotNull DownloadUiStateGenerator downloadUiStateGenerator) {
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(downloadRepository, "downloadRepository");
        Intrinsics.checkNotNullParameter(downloadUiStateGenerator, "downloadUiStateGenerator");
        this.a = executorSoftwareStateMgr;
        this.b = executorDialogRouterMgr;
        this.c = dataGenerator;
        this.d = context;
        this.e = downloadRepository;
        this.f = downloadUiStateGenerator;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull java.util.List<com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam>> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.p9.a(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v8, types: [android.net.ConnectivityManager] */
    /* JADX WARN: Type inference failed for: r1v9 */
    public final boolean b() {
        Exception e;
        ?? r1;
        qj qjVar = qj.a;
        Context context = this.d;
        Objects.requireNonNull(qjVar);
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            r1 = (ConnectivityManager) context.getSystemService("connectivity");
            if (r1 != 0) {
                int i = Build.VERSION.SDK_INT;
                NetworkCapabilities networkCapabilities = r1.getNetworkCapabilities(r1.getActiveNetwork());
                try {
                    if (networkCapabilities != null) {
                        boolean z = networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(3);
                        ct.a("LibNetConfig executorHciWorkPreTreat checkNetwork ret:" + z + " sdk:" + i, new Object[0]);
                        r1 = z;
                    } else {
                        NetworkInfo activeNetworkInfo = r1.getActiveNetworkInfo();
                        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
                        ct.a("LibNetConfig executorHciWorkPreTreat checkNetwork ret:%s", Boolean.valueOf(z2));
                        r1 = z2;
                    }
                } catch (Exception e2) {
                    e = e2;
                    ct.b("LibNetConfig checkNetworkAvailable error msg:%s", e.getMessage());
                }
            } else {
                r1 = 0;
            }
        } catch (Exception e3) {
            e = e3;
            r1 = 0;
        }
        if (r1 == 1) {
            return true;
        }
        f(vm.a.e(this.d, R$string.net_no_connect_toast));
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(@org.jetbrains.annotations.Nullable java.util.List<com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam> r16, @org.jetbrains.annotations.NotNull spi.a2 r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r18) {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.p9.c(java.util.List, spi.a2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean d() {
        byte b2;
        Objects.requireNonNull(SettingUtil.a);
        if (Intrinsics.areEqual("mounted", Environment.getExternalStorageState())) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long blockSizeLong = statFs.getBlockSizeLong();
            long blockCountLong = statFs.getBlockCountLong();
            long availableBlocksLong = statFs.getAvailableBlocksLong();
            long j = availableBlocksLong * blockSizeLong;
            long j2 = blockSizeLong * blockCountLong;
            boolean z = j > ((long) (Math.pow(1024.0d, 3.0d) * ((double) 2)));
            ct.a("getStorageState ret:" + z + " availSpace:" + j + " byte allSpace:" + j2 + " byte blockSize:" + blockSizeLong + " blockCount:" + blockCountLong + " availCount:" + availableBlocksLong + " ", new Object[0]);
            b2 = z ? (byte) 0 : (byte) 1;
        } else {
            ct.a("getStorageState ret:false not mounted", new Object[0]);
            b2 = 2;
        }
        if (b2 == 0) {
            return true;
        }
        if (b2 == 1) {
            this.b.addBizTypeAndData(2, new StorageState(b2)).workNotify();
            return false;
        }
        f(vm.a.e(this.d, R$string.no_storage_dialog_title));
        return false;
    }

    public final boolean e(@NotNull SoftwareOptParam optParam) {
        Intrinsics.checkNotNullParameter(optParam, "optParam");
        int iB = this.a.b(optParam.getPackageName());
        if (iB == 3 || iB == 6 || iB == 10) {
            return true;
        }
        return d();
    }

    public final void f(String str) {
        Toast toast = this.g;
        if (toast != null) {
            toast.cancel();
        }
        Toast toastMakeText = Toast.makeText(this.d, str, 0);
        this.g = toastMakeText;
        if (toastMakeText != null) {
            toastMakeText.show();
        }
    }
}
