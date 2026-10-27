package com.byd.overseaappstore.module.ability.data.generator.interceptor;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareExtraRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareIntermediaryRsp;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.e;
import spi.i9;
import spi.oa;
import spi.oq;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u001c\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u0001B+\b\u0007\u0012\u0006\u0010&\u001a\u00020%\u0012\u0006\u0010(\u001a\u00020'\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\b\b\u0001\u0010#\u001a\u00020\"¢\u0006\u0004\b)\u0010*J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0002J#\u0010\n\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ#\u0010\f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000bJ#\u0010\r\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000bJ#\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000bJ#\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u000bJ\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0002J8\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0004H\u0002J3\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0007H\u0016R\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/DbDataSoftwareStateInterceptor;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareStateInterceptor;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataWrapper;", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareInterceptorRetData;", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "softwareDataWrapper", "", "treeLogicRet", "logicTree0Work", "logicTree1Work", "(Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataWrapper;BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logicTree2Work", "logicTree3Work", "logicTree4Work", "logicTree5Work", "logicTreeUnknown", "", "state", "", "primePackageName", "", "sourceType", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareExtraRsp;", "extraRspList", "Lspi/oq;", "checkToGenerateSoftwareExtraLocalListToInsert", "f", "handle", "(Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataWrapper;Ljava/util/List;BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateDefaultRetData", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lspi/oa;", "executorSoftwareStateMgr", "Lspi/i9;", "executorDbMgr", "<init>", "(Lspi/oa;Lspi/i9;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Landroid/content/Context;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class DbDataSoftwareStateInterceptor extends SoftwareStateInterceptor<SoftwareDataWrapper, SoftwareInterceptorRetData, List<? extends SoftwareDeviceInstalledRsp>> {

    @NotNull
    private final Context context;

    @NotNull
    private final DataGenerator dataGenerator;

    @NotNull
    private final i9 executorDbMgr;

    @NotNull
    private final oa executorSoftwareStateMgr;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor$logicTree1Work$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor", f = "DbDataSoftwareStateInterceptor.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, l = {103, 150, 153, 182}, m = "logicTree1Work", n = {"this", "poolSoftwareStateData", "remoteSoftware", "remotePackageName", "treeLogicRet", "remoteVersionCode", "this", "poolSoftwareStateData", "remotePackageName", "syncRemoteSoftwareStateData", "syncSoftwareExtraLocalList", "treeLogicRet", "poolSoftwareStateData", "remotePackageName", "syncRemoteSoftwareStateData", "treeLogicRet", "this", "remotePackageName", "ftSoftwareStateData"}, s = {"L$0", "L$1", "L$2", "L$3", "B$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "B$0", "L$0", "L$1", "L$2", "B$0", "L$0", "L$1", "L$2"})
    public static final class AnonymousClass1 extends ContinuationImpl {
        public byte B$0;
        public int I$0;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public int label;
        public /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DbDataSoftwareStateInterceptor.this.logicTree1Work(null, (byte) 0, this);
        }
    }

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor$logicTree2Work$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor", f = "DbDataSoftwareStateInterceptor.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, l = {209, 223, 224}, m = "logicTree2Work", n = {"this", "remoteSoftware", "remotePackageName", "treeLogicRet", "remoteVersionCode", "this", "remoteSoftware", "remotePackageName", "remoteVersionCode", "this", "remoteSoftware", "remoteVersionCode"}, s = {"L$0", "L$1", "L$2", "B$0", "I$0", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "I$0"})
    public static final class C01931 extends ContinuationImpl {
        public byte B$0;
        public int I$0;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public C01931(Continuation<? super C01931> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DbDataSoftwareStateInterceptor.this.logicTree2Work(null, (byte) 0, this);
        }
    }

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor$logicTree3Work$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor", f = "DbDataSoftwareStateInterceptor.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 5, 5}, l = {259, 298, 301, 345, 348, 376}, m = "logicTree3Work", n = {"this", "poolSoftwareStateData", "remoteSoftware", "remotePackageName", "treeLogicRet", "remoteVersionCode", "this", "poolSoftwareStateData", "remotePackageName", "ftSoftwareStateData", "ftSoftwareExtraLocalList", "treeLogicRet", "ftSoftwareStateData", "this", "poolSoftwareStateData", "remotePackageName", "syncRemoteSoftwareStateData", "syncSoftwareExtraLocalList", "treeLogicRet", "syncRemoteSoftwareStateData", "this", "remotePackageName", "ftSoftwareStateData"}, s = {"L$0", "L$1", "L$2", "L$3", "B$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "B$0", "L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "B$0", "L$0", "L$0", "L$1", "L$2"})
    public static final class C01941 extends ContinuationImpl {
        public byte B$0;
        public int I$0;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public int label;
        public /* synthetic */ Object result;

        public C01941(Continuation<? super C01941> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DbDataSoftwareStateInterceptor.this.logicTree3Work(null, (byte) 0, this);
        }
    }

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor$logicTree4Work$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor", f = "DbDataSoftwareStateInterceptor.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4}, l = {408, 463, 466, 547, 550}, m = "logicTree4Work", n = {"this", "findDeviceInstalled", "remoteSoftware", "remotePackageName", "treeLogicRet", "remoteVersionCode", "this", "remotePackageName", "ftSoftwareStateData", "ftSoftwareExtraLocalList", "treeLogicRet", "remoteVersionCode", "findRemoteVersion", "this", "remotePackageName", "ftSoftwareStateData", "this", "remotePackageName", "ftSoftwareStateData", "ftSoftwareExtraLocalList", "treeLogicRet", "remoteVersionCode", "findRemoteVersion", "this", "remotePackageName", "ftSoftwareStateData"}, s = {"L$0", "L$1", "L$2", "L$3", "B$0", "I$0", "L$0", "L$1", "L$2", "L$3", "B$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "B$0", "I$0", "I$1", "L$0", "L$1", "L$2"})
    public static final class C01951 extends ContinuationImpl {
        public byte B$0;
        public int I$0;
        public int I$1;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;

        public C01951(Continuation<? super C01951> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DbDataSoftwareStateInterceptor.this.logicTree4Work(null, (byte) 0, this);
        }
    }

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor$logicTree5Work$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor", f = "DbDataSoftwareStateInterceptor.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10}, l = {589, 653, 656, 705, 708, 768, 771, 843, 846, 922, 925}, m = "logicTree5Work", n = {"this", "findDeviceInstalled", "poolSoftwareStateData", "remoteSoftware", "remotePackageName", "treeLogicRet", "remoteVersionCode", "this", "poolSoftwareStateData", "remotePackageName", "ftSoftwareStateData", "ftSoftwareExtraLocalList", "treeLogicRet", "remoteVersionCode", "findRemoteVersion", "this", "remotePackageName", "ftSoftwareStateData", "this", "findDeviceInstalled", "poolSoftwareStateData", "remotePackageName", "ftSoftwareStateData", "ftSoftwareExtraLocalList", "treeLogicRet", "remoteVersionCode", "this", "remotePackageName", "ftSoftwareStateData", "this", "poolSoftwareStateData", "remotePackageName", "ftSoftwareStateData", "ftSoftwareExtraLocalList", "treeLogicRet", "remoteVersionCode", "findRemoteVersion", "this", "remotePackageName", "ftSoftwareStateData", "this", "poolSoftwareStateData", "remotePackageName", "ftSoftwareStateData", "ftSoftwareExtraLocalList", "treeLogicRet", "remoteVersionCode", "findRemoteVersion", "this", "remotePackageName", "ftSoftwareStateData", "this", "poolSoftwareStateData", "remotePackageName", "ftSoftwareStateData", "ftSoftwareExtraLocalList", "treeLogicRet", "remoteVersionCode", "findRemoteVersion", "this", "remotePackageName", "ftSoftwareStateData"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "B$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "B$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "B$0", "I$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "B$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "B$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "B$0", "I$0", "I$1", "L$0", "L$1", "L$2"})
    public static final class C01961 extends ContinuationImpl {
        public byte B$0;
        public int I$0;
        public int I$1;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public int label;
        public /* synthetic */ Object result;

        public C01961(Continuation<? super C01961> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DbDataSoftwareStateInterceptor.this.logicTree5Work(null, (byte) 0, this);
        }
    }

    public DbDataSoftwareStateInterceptor(@NotNull oa executorSoftwareStateMgr, @NotNull i9 executorDbMgr, @NotNull DataGenerator dataGenerator, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(context, "context");
        this.executorSoftwareStateMgr = executorSoftwareStateMgr;
        this.executorDbMgr = executorDbMgr;
        this.dataGenerator = dataGenerator;
        this.context = context;
    }

    private final List<oq> checkToGenerateSoftwareExtraLocalListToInsert(int state, String primePackageName, short sourceType, List<SoftwareExtraRsp> extraRspList) {
        switch (state) {
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return this.dataGenerator.parseToSoftwareExtraLocalList(primePackageName, sourceType, extraRspList);
            default:
                return null;
        }
    }

    private final SoftwareInterceptorRetData logicTree0Work(SoftwareDataWrapper softwareDataWrapper, byte treeLogicRet) {
        softwareDataWrapper.getSoftwareDeviceInstalledRspList();
        softwareDataWrapper.getFindSoftwareDeviceInstalledRsp();
        softwareDataWrapper.getPoolSoftwareStateData();
        SoftwareIntermediaryRsp remoteSoftware = softwareDataWrapper.getRemoteSoftware();
        String packageName = softwareDataWrapper.getRemoteSoftware().getPackageName();
        softwareDataWrapper.getRemoteSoftware().getVersionCode();
        SoftwareInterceptorRetData toSoftwareInterceptorRetData = this.dataGenerator.parseToSoftwareInterceptorRetData(new SoftwareStateData(0, 0), remoteSoftware.getDownloadUrl(), remoteSoftware.getTotalSize(), remoteSoftware.getVersionCode());
        StringBuilder sbL = e.l("handleRemote logicTree0Work treeLogicRet:", treeLogicRet, " state:", toSoftwareInterceptorRetData.getSoftwareStateData().getState(), " pkn:");
        sbL.append(packageName);
        ct.a(sbL.toString(), new Object[0]);
        return toSoftwareInterceptorRetData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:59:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object logicTree1Work(com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper r30, byte r31, kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData> r32) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 737
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor.logicTree1Work(com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper, byte, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:43:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0160 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object logicTree2Work(com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper r17, byte r18, kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData> r19) {
        /*
            Method dump skipped, instruction units count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor.logicTree2Work(com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper, byte, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x03b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object logicTree3Work(com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper r33, byte r34, kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData> r35) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1022
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor.logicTree3Work(com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper, byte, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0449  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object logicTree4Work(com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper r40, byte r41, kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData> r42) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1164
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor.logicTree4Work(com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataWrapper, byte, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /*  JADX ERROR: ConcurrentModificationException in pass: ConstructorVisitor
        java.util.ConcurrentModificationException
        	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
        	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
        	at jadx.core.dex.visitors.ConstructorVisitor.insertPhiInsn(ConstructorVisitor.java:139)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:91)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public final java.lang.Object logicTree5Work(
    /*  JADX ERROR: ConcurrentModificationException in pass: ConstructorVisitor
        java.util.ConcurrentModificationException
        	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
        	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
        	at jadx.core.dex.visitors.ConstructorVisitor.insertPhiInsn(ConstructorVisitor.java:139)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:91)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r47v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    private final SoftwareInterceptorRetData logicTreeUnknown(SoftwareDataWrapper softwareDataWrapper, byte treeLogicRet) {
        SoftwareInterceptorRetData softwareInterceptorRetDataGenerateDefaultRetData = generateDefaultRetData(treeLogicRet);
        StringBuilder sbH = xi.h("handleRemote logicTreeUnknown error state:", softwareInterceptorRetDataGenerateDefaultRetData.getSoftwareStateData().getState(), " pkn:", softwareDataWrapper.getRemoteSoftware().getPackageName(), " treeLogicRet:");
        sbH.append((int) treeLogicRet);
        ct.b(sbH.toString(), new Object[0]);
        return softwareInterceptorRetDataGenerateDefaultRetData;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor
    @NotNull
    public SoftwareInterceptorRetData generateDefaultRetData(byte treeLogicRet) {
        SoftwareInterceptorRetData softwareInterceptorRetData = new SoftwareInterceptorRetData(new SoftwareStateData(0, 0), new SoftwareDtFixInfo(null, 0L, 0, 7, null));
        ct.a(e.c("handleRemote generateDefaultRetData treeLogicRet:", treeLogicRet), new Object[0]);
        return softwareInterceptorRetData;
    }

    @Nullable
    /* JADX INFO: renamed from: handle, reason: avoid collision after fix types in other method */
    public Object handle2(@NotNull SoftwareDataWrapper softwareDataWrapper, @Nullable List<SoftwareDeviceInstalledRsp> list, byte b, @NotNull Continuation<? super SoftwareInterceptorRetData> continuation) {
        return b == 0 ? logicTree0Work(softwareDataWrapper, b) : b == 1 ? logicTree1Work(softwareDataWrapper, b, continuation) : b == 2 ? logicTree2Work(softwareDataWrapper, b, continuation) : b == 3 ? logicTree3Work(softwareDataWrapper, b, continuation) : b == 4 ? logicTree4Work(softwareDataWrapper, b, continuation) : b == 5 ? logicTree5Work(softwareDataWrapper, b, continuation) : logicTreeUnknown(softwareDataWrapper, b);
    }

    @Override // com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareStateInterceptor
    public /* bridge */ /* synthetic */ Object handle(SoftwareDataWrapper softwareDataWrapper, List<? extends SoftwareDeviceInstalledRsp> list, byte b, Continuation<? super SoftwareInterceptorRetData> continuation) {
        return handle2(softwareDataWrapper, (List<SoftwareDeviceInstalledRsp>) list, b, continuation);
    }
}
