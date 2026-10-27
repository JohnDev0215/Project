package com.neusoft.na.ocservice.Broadcast;

import kotlin.Metadata;
import org.bouncycastle.i18n.TextBundle;

/* JADX INFO: compiled from: MotsBroadcastCallBack.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0005H&J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H&J\b\u0010\u0016\u001a\u00020\u0003H&J\u0012\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H&J\b\u0010\u001a\u001a\u00020\u0003H&J\b\u0010\u001b\u001a\u00020\u0003H&J\b\u0010\u001c\u001a\u00020\u0003H&¨\u0006\u001d"}, d2 = {"Lcom/neusoft/na/ocservice/Broadcast/MotsBroadcastCallBack;", "", "ARStatus", "", "boolean", "", "activationPrivacyPolicyStatusChanged", "activationStatus", "privacyPolicyStatus", "appSwitchToBackground", "closeActivity", "closePresentation", "exitWithoutRestarting", "goneMask", "hmiRequestFocus", "killActivity", "naviHMIFullScreenPageChanged", "isNaviHMIFullScreenPage", "presentationTest", "key", "", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "showBackDoor", "showMessage", TextBundle.TEXT_ENTRY, "", "timeChanged", "timeZoneChanged", "webViewPrivacyDetails", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface MotsBroadcastCallBack {
    void ARStatus(boolean z);

    void activationPrivacyPolicyStatusChanged(boolean activationStatus, boolean privacyPolicyStatus);

    void appSwitchToBackground();

    void closeActivity();

    void closePresentation();

    void exitWithoutRestarting();

    void goneMask();

    void hmiRequestFocus();

    void killActivity();

    void naviHMIFullScreenPageChanged(boolean isNaviHMIFullScreenPage);

    void presentationTest(int key, int value);

    void showBackDoor();

    void showMessage(String text);

    void timeChanged();

    void timeZoneChanged();

    void webViewPrivacyDetails();
}
