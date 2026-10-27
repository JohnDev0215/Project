package com.byd.overseaappstore.broadcast;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.aa;
import spi.ct;
import spi.da;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/byd/overseaappstore/broadcast/SoftwareRemoveBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SoftwareRemoveBroadcastReceiver extends Hilt_SoftwareRemoveBroadcastReceiver {
    public ExecutorDialogRouterMgr c;
    public DataGenerator d;
    public aa e;

    @NotNull
    public final DataGenerator a() {
        DataGenerator dataGenerator = this.d;
        if (dataGenerator != null) {
            return dataGenerator;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dataGenerator");
        return null;
    }

    @NotNull
    public final ExecutorDialogRouterMgr b() {
        ExecutorDialogRouterMgr executorDialogRouterMgr = this.c;
        if (executorDialogRouterMgr != null) {
            return executorDialogRouterMgr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("executorDialogRouterMgr");
        return null;
    }

    @Override // com.byd.overseaappstore.broadcast.Hilt_SoftwareRemoveBroadcastReceiver, android.content.BroadcastReceiver
    public final void onReceive(@Nullable Context context, @Nullable Intent intent) {
        IUUState toIUUState$default;
        Uri data;
        super.onReceive(context, intent);
        String action = intent != null ? intent.getAction() : null;
        String receivePackageName = (intent == null || (data = intent.getData()) == null) ? null : data.getSchemeSpecificPart();
        Boolean boolValueOf = intent != null ? Boolean.valueOf(intent.getBooleanExtra("android.intent.extra.REPLACING", false)) : null;
        ct.a("broadcast software negative onReceive action:" + action + " extraReplace：" + boolValueOf + " pkn:" + receivePackageName, new Object[0]);
        if (context == null) {
            ct.b("broadcast software negative context isNull doNotify action:" + action + " extraReplace：" + boolValueOf + " pkn:" + receivePackageName, new Object[0]);
        } else {
            if (!(receivePackageName == null || receivePackageName.length() == 0)) {
                if (boolValueOf == null) {
                    ct.b("broadcast software negative extraReplace isNull doNotify action:" + action + " extraReplace：" + boolValueOf + " pkn:" + receivePackageName, new Object[0]);
                    toIUUState$default = DataGenerator.parseToIUUState$default(a(), receivePackageName, (short) 0, 8, 2, null);
                    b().addBizTypeAndData(3, toIUUState$default).workNotify();
                }
                if (!Intrinsics.areEqual(action, "android.intent.action.PACKAGE_FULLY_REMOVED")) {
                    ct.a("broadcast software negative otherAction no intervene action:" + action + " extraReplace：" + boolValueOf + " receivePkn:" + receivePackageName, new Object[0]);
                    return;
                }
                if (boolValueOf.booleanValue()) {
                    return;
                }
                ct.a("broadcast software negative remove do onUninstallFinish action:" + action + " extraReplace：" + boolValueOf + " receivePkn:" + receivePackageName, new Object[0]);
                aa aaVar = this.e;
                if (aaVar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("executorIuuMgr");
                    aaVar = null;
                }
                Objects.requireNonNull(aaVar);
                Intrinsics.checkNotNullParameter(receivePackageName, "receivePackageName");
                Intrinsics.checkNotNullParameter(context, "context");
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain())), null, null, new da(aaVar, receivePackageName, context, null), 3, null);
                return;
            }
            ct.b("broadcast software negative receivePackageName isNullOrEmpty doNotify action:" + action + " extraReplace：" + boolValueOf + " pkn:" + receivePackageName, new Object[0]);
        }
        toIUUState$default = DataGenerator.parseToIUUState$default(a(), "", (short) 0, 8, 2, null);
        b().addBizTypeAndData(3, toIUUState$default).workNotify();
    }
}
