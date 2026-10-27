package com.neusoft.na.byd;

import android.util.Log;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyDelPrivacyPolicyInfo;
import api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnDelPrivacyPolicyInfo;
import api.structs.com.neusoft.byd.DelPrivacyPolicyInfo;
import api.structs.com.neusoft.byd.ResponseStatus;
import com.neusoft.na.byd.util.ChangeHistoryUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: Byd8155fSystem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "delPrivacyPolicyInfo", "Lapi/notifications/com/neusoft/byd/bydnavapi/NotifyDelPrivacyPolicyInfo;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
final class Byd8155fSystem$onServerConnect$15$1 extends Lambda implements Function1<NotifyDelPrivacyPolicyInfo, Unit> {
    final /* synthetic */ ChangeHistoryUtil $util;
    final /* synthetic */ Byd8155fSystem this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Byd8155fSystem$onServerConnect$15$1(ChangeHistoryUtil changeHistoryUtil, Byd8155fSystem byd8155fSystem) {
        super(1);
        this.$util = changeHistoryUtil;
        this.this$0 = byd8155fSystem;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(NotifyDelPrivacyPolicyInfo notifyDelPrivacyPolicyInfo) {
        invoke2(notifyDelPrivacyPolicyInfo);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(NotifyDelPrivacyPolicyInfo notifyDelPrivacyPolicyInfo) {
        Log.i("Byd8155fSystem", "NotifyDelPrivacyPolicyInfo");
        DelPrivacyPolicyInfo delPrivacyPolicyInfo = notifyDelPrivacyPolicyInfo.getDelPrivacyPolicyInfo();
        final Integer tokenId = delPrivacyPolicyInfo.getTokenId();
        List<Integer> indexList = delPrivacyPolicyInfo.getIndexList();
        ChangeHistoryUtil changeHistoryUtil = this.$util;
        final Byd8155fSystem byd8155fSystem = this.this$0;
        changeHistoryUtil.delHistory(indexList, new ChangeHistoryUtil.IChangeHistoryListener() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$15$1$$ExternalSyntheticLambda0
            @Override // com.neusoft.na.byd.util.ChangeHistoryUtil.IChangeHistoryListener
            public final void onDone(boolean z) {
                Byd8155fSystem$onServerConnect$15$1.invoke$lambda$0(tokenId, byd8155fSystem, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(Integer num, Byd8155fSystem this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i("Byd8155fSystem", "NotifyDelPrivacyPolicyInfo " + z);
        ReqOnDelPrivacyPolicyInfo reqOnDelPrivacyPolicyInfo = new ReqOnDelPrivacyPolicyInfo(num, z ? ResponseStatus.E_SUCCESS : ResponseStatus.E_FAILURE);
        BydNavApiRequests bydNavApiRequests = this$0.bydSysApiRequests;
        if (bydNavApiRequests != null) {
            bydNavApiRequests.fireOnDelPrivacyPolicyInfo(reqOnDelPrivacyPolicyInfo);
        }
    }
}
