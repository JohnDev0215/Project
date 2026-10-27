package com.neusoft.na.byd;

import android.content.ComponentName;
import android.content.Intent;
import android.hardware.bydauto.whud.BYDAutoWhudDevice;
import android.util.Log;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyRevokePrivacyPolicy;
import api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnRevokePrivacyPolicy;
import api.structs.com.neusoft.byd.PrivacyPolicyInfo;
import api.structs.com.neusoft.byd.ResponseStatus;
import api.structs.com.neusoft.byd.RevokePrivacyPolicyInfo;
import com.byd.bydnaviapi.BydAutoNaviDevice;
import com.neusoft.na.byd.util.ChangeHistoryUtil;
import com.neusoft.na.common.FileUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: Byd8155fSystem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "revokePrivacyPolicy", "Lapi/notifications/com/neusoft/byd/bydnavapi/NotifyRevokePrivacyPolicy;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
final class Byd8155fSystem$onServerConnect$15$2 extends Lambda implements Function1<NotifyRevokePrivacyPolicy, Unit> {
    final /* synthetic */ ChangeHistoryUtil $util;
    final /* synthetic */ Byd8155fSystem this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Byd8155fSystem$onServerConnect$15$2(Byd8155fSystem byd8155fSystem, ChangeHistoryUtil changeHistoryUtil) {
        super(1);
        this.this$0 = byd8155fSystem;
        this.$util = changeHistoryUtil;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(NotifyRevokePrivacyPolicy notifyRevokePrivacyPolicy) {
        invoke2(notifyRevokePrivacyPolicy);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(NotifyRevokePrivacyPolicy notifyRevokePrivacyPolicy) {
        Log.i("Byd8155fSystem", "NotifyRevokePrivacyPolicyAttribute");
        Intent intent = new Intent(Byd8155fSystem.ACTION_WIDGET_RESET);
        intent.setComponent(new ComponentName("com.neusoft.na.navigation", "com.neusoft.na.byd.BydAppWidget"));
        this.this$0.context.sendBroadcast(intent);
        FileUtils.getInstance(this.this$0.context).updatePrivacyStatus(false);
        RevokePrivacyPolicyInfo revokePrivacyPolicy = notifyRevokePrivacyPolicy.getRevokePrivacyPolicy();
        final Integer tokenId = revokePrivacyPolicy.getTokenId();
        PrivacyPolicyInfo privacyPolicyInfo = revokePrivacyPolicy.getPrivacyPolicyInfo();
        ChangeHistoryUtil changeHistoryUtil = this.$util;
        Integer index = privacyPolicyInfo.getIndex();
        Intrinsics.checkNotNullExpressionValue(index, "privacyInfo.index");
        int iIntValue = index.intValue();
        long jLongValue = privacyPolicyInfo.getTimestamp().longValue();
        Boolean status = privacyPolicyInfo.getStatus();
        Intrinsics.checkNotNullExpressionValue(status, "privacyInfo.status");
        boolean zBooleanValue = status.booleanValue();
        String version = privacyPolicyInfo.getVersion();
        String vinCode = privacyPolicyInfo.getVinCode();
        String countryCode = privacyPolicyInfo.getCountryCode();
        Boolean isSync = privacyPolicyInfo.getIsSync();
        Intrinsics.checkNotNullExpressionValue(isSync, "privacyInfo.isSync");
        boolean zBooleanValue2 = isSync.booleanValue();
        final Byd8155fSystem byd8155fSystem = this.this$0;
        changeHistoryUtil.saveChangeHistory(iIntValue, jLongValue, zBooleanValue, version, vinCode, countryCode, zBooleanValue2, new ChangeHistoryUtil.IChangeHistoryListener() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$15$2$$ExternalSyntheticLambda0
            @Override // com.neusoft.na.byd.util.ChangeHistoryUtil.IChangeHistoryListener
            public final void onDone(boolean z) {
                Byd8155fSystem$onServerConnect$15$2.invoke$lambda$0(tokenId, byd8155fSystem, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(Integer num, Byd8155fSystem this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i("Byd8155fSystem", "NotifyRevokePrivacyPolicyAttribute: " + z);
        ReqOnRevokePrivacyPolicy reqOnRevokePrivacyPolicy = new ReqOnRevokePrivacyPolicy(num, z ? ResponseStatus.E_SUCCESS : ResponseStatus.E_FAILURE);
        BydNavApiRequests bydNavApiRequests = this$0.bydSysApiRequests;
        if (bydNavApiRequests != null) {
            bydNavApiRequests.fireOnRevokePrivacyPolicy(reqOnRevokePrivacyPolicy);
        }
        this$0.onGuideChanged(false);
        if (this$0.presentationMapType == 1) {
            BydAutoNaviDevice bydAutoNaviDevice = this$0.bydAutoNaviDevice;
            if (bydAutoNaviDevice != null) {
                bydAutoNaviDevice.sendNaviState(1, 2);
            }
            BYDAutoWhudDevice bYDAutoWhudDevice = this$0.bydAutoWhudDevice;
            if (bYDAutoWhudDevice != null) {
                bYDAutoWhudDevice.setNaviState(4);
            }
            Log.i("Byd8155fSystem", "presentation sendNaviState guide stop.");
        } else {
            Log.i("Byd8155fSystem", "current not map focus, so do not sendNaviState guide stop.");
        }
        Intent intent = new Intent("com.neusoft.na.mots.exitWithoutRestarting");
        intent.putExtra("closePresentation", true);
        this$0.context.sendBroadcast(intent);
    }
}
