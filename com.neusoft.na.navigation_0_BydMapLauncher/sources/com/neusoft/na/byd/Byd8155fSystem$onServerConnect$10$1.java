package com.neusoft.na.byd;

import android.hardware.bydauto.whud.BYDAutoWhudDevice;
import android.util.Log;
import api.notifications.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseNotifications;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNaviPresentationInfo;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNaviStatusChanged;
import api.structs.com.neusoft.common.NavigationStatus;
import com.byd.bydnaviapi.BydAutoNaviDevice;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import rx.Observable;
import rx.functions.Action1;

/* JADX INFO: compiled from: Byd8155fSystem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "naviStatusChanged", "Lapi/notifications/com/neusoft/common/commonsysapiinverse/NotifyNaviStatusChanged;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
final class Byd8155fSystem$onServerConnect$10$1 extends Lambda implements Function1<NotifyNaviStatusChanged, Unit> {
    final /* synthetic */ Byd8155fSystem this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Byd8155fSystem$onServerConnect$10$1(Byd8155fSystem byd8155fSystem) {
        super(1);
        this.this$0 = byd8155fSystem;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(NotifyNaviStatusChanged notifyNaviStatusChanged) {
        invoke2(notifyNaviStatusChanged);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(NotifyNaviStatusChanged notifyNaviStatusChanged) {
        Integer value = notifyNaviStatusChanged.getNaviStatusChanged().getValue();
        Log.d("Byd8155fSystem", "NotifyNaviStatusChanged: " + value);
        if (value != null && value.intValue() == 17) {
            this.this$0.onGuideChanged(false);
            if (this.this$0.presentationMapType == 1) {
                BydAutoNaviDevice bydAutoNaviDevice = this.this$0.bydAutoNaviDevice;
                if (bydAutoNaviDevice != null) {
                    bydAutoNaviDevice.sendNaviState(1, 2);
                }
                BYDAutoWhudDevice bYDAutoWhudDevice = this.this$0.bydAutoWhudDevice;
                if (bYDAutoWhudDevice != null) {
                    bYDAutoWhudDevice.setNaviState(4);
                }
                Log.i("Byd8155fSystem", "presentation sendNaviState guide stop.");
            } else {
                Log.i("Byd8155fSystem", "current not map focus, so do not sendNaviState guide stop.");
            }
        } else if (value != null && value.intValue() == 16) {
            this.this$0.onGuideChanged(true);
            if (this.this$0.presentationMapType == 1) {
                BydAutoNaviDevice bydAutoNaviDevice2 = this.this$0.bydAutoNaviDevice;
                if (bydAutoNaviDevice2 != null) {
                    bydAutoNaviDevice2.sendNaviState(1, 1);
                }
                BYDAutoWhudDevice bYDAutoWhudDevice2 = this.this$0.bydAutoWhudDevice;
                if (bYDAutoWhudDevice2 != null) {
                    bYDAutoWhudDevice2.setNaviState(2);
                }
                Log.i("Byd8155fSystem", "presentation sendNaviState guide start.");
            } else {
                Log.i("Byd8155fSystem", "current not map focus, so do not sendNaviState guide start.");
                BydAutoNaviDevice bydAutoNaviDevice3 = this.this$0.bydAutoNaviDevice;
                Integer numValueOf = bydAutoNaviDevice3 != null ? Integer.valueOf(bydAutoNaviDevice3.getInsNavDisplayMode()) : null;
                Log.i("Byd8155fSystem", "current insNavDisplayMode == " + numValueOf);
                if ((numValueOf != null && numValueOf.intValue() == 3) || (numValueOf != null && numValueOf.intValue() == 4)) {
                    BydAutoNaviDevice bydAutoNaviDevice4 = this.this$0.bydAutoNaviDevice;
                    Log.i("Byd8155fSystem", "[sendNaviPresentationState] naviPresentationState == 1, code == " + (bydAutoNaviDevice4 != null ? Integer.valueOf(bydAutoNaviDevice4.sendNaviPresentationState(1, 1)) : null));
                }
                BydAutoNaviDevice bydAutoNaviDevice5 = this.this$0.bydAutoNaviDevice;
                if (bydAutoNaviDevice5 != null) {
                    bydAutoNaviDevice5.requestMapFocus(1);
                }
            }
        } else if (Intrinsics.areEqual(value, NavigationStatus.NAVI_STAT_MUTE_ON.getValue())) {
            Log.i("Byd8155fSystem", "setVolumeGroupMute --> usageGroupId == " + this.this$0.usageGroupId + ", mute == false");
        } else if (Intrinsics.areEqual(value, NavigationStatus.NAVI_STAT_MUTE_OFF.getValue())) {
            Log.i("Byd8155fSystem", "setVolumeGroupMute --> usageGroupId == " + this.this$0.usageGroupId + ", mute == true");
        }
        if (value != null && value.intValue() == 8) {
            Log.i("Byd8155fSystem", "[NotifyNaviStatusChanged] --> NAVI_STAT_APP_START_FINISH");
            CommonSysApiInverseNotifications commonSysApiInverseNotifications = this.this$0.commonNotifications;
            if (commonSysApiInverseNotifications != null) {
                final Byd8155fSystem byd8155fSystem = this.this$0;
                Log.i("Byd8155fSystem", "register NotifyNaviPresentationInfo");
                IAttribute<NotifyNaviPresentationInfo> iAttributeNaviPresentationInfoAttribute = commonSysApiInverseNotifications.naviPresentationInfoAttribute();
                Intrinsics.checkNotNullExpressionValue(iAttributeNaviPresentationInfoAttribute, "it.naviPresentationInfoAttribute()");
                IAttribute<NotifyNaviPresentationInfo> iAttribute = iAttributeNaviPresentationInfoAttribute;
                iAttribute.signIn();
                byd8155fSystem.remoteAttributes.add(iAttribute);
                ArrayList arrayList = byd8155fSystem.remoteSubscriptions;
                Observable<NotifyNaviPresentationInfo> observable = iAttribute.getObservable();
                final Function1<NotifyNaviPresentationInfo, Unit> function1 = new Function1<NotifyNaviPresentationInfo, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$10$1$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(NotifyNaviPresentationInfo notifyNaviPresentationInfo) {
                        invoke2(notifyNaviPresentationInfo);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(NotifyNaviPresentationInfo notifyNaviPresentationInfo) {
                        Log.d("Byd8155fSystem", "NotifyNaviPresentationInfo --> activationStatus == " + notifyNaviPresentationInfo.getNaviPresentationInfo().getActivationStatus() + ", privacyPolicyStatus == " + notifyNaviPresentationInfo.getNaviPresentationInfo().getPrivacyPolicyStatus());
                        Byd8155fSystem byd8155fSystem2 = byd8155fSystem;
                        Boolean activationStatus = notifyNaviPresentationInfo.getNaviPresentationInfo().getActivationStatus();
                        Intrinsics.checkNotNullExpressionValue(activationStatus, "it.naviPresentationInfo.activationStatus");
                        boolean zBooleanValue = activationStatus.booleanValue();
                        Boolean privacyPolicyStatus = notifyNaviPresentationInfo.getNaviPresentationInfo().getPrivacyPolicyStatus();
                        Intrinsics.checkNotNullExpressionValue(privacyPolicyStatus, "it.naviPresentationInfo.privacyPolicyStatus");
                        byd8155fSystem2.sendMapActivateAndPrivacyPolicyToByd(zBooleanValue, privacyPolicyStatus.booleanValue());
                    }
                };
                arrayList.add(observable.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$10$1$$ExternalSyntheticLambda0
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        Byd8155fSystem$onServerConnect$10$1.invoke$lambda$1$lambda$0(function1, obj);
                    }
                }));
            }
        }
        Log.d("Byd8155fSystem", "NotifyNaviStatusChanged: done");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
