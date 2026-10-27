package com.android.services.telephony.rcs;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.telephony.ims.RcsContactUceCapability;
import android.telephony.ims.aidl.IRcsUceControllerCallback;
import android.util.Log;
import com.android.ims.RcsFeatureManager;
import com.android.phone.R;
import com.android.service.ims.presence.ContactCapabilityResponse;
import com.android.service.ims.presence.PresencePublication;
import com.android.service.ims.presence.PresencePublisher;
import com.android.service.ims.presence.PresenceSubscriber;
import com.android.service.ims.presence.SubscribePublisher;
import com.android.services.telephony.rcs.RcsFeatureController;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class UserCapabilityExchangeImpl implements RcsFeatureController.Feature, SubscribePublisher, PresencePublisher {
    private final ConcurrentHashMap<Integer, IRcsUceControllerCallback> mPendingCapabilityRequests = new ConcurrentHashMap<>();
    private final PresencePublication mPresencePublication;
    private final PresenceSubscriber mPresenceSubscriber;
    private int mSlotId;
    private int mSubId;

    /* JADX INFO: Access modifiers changed from: private */
    public static int toUceError(int i) {
        if (i == -19) {
            return 13;
        }
        if (i == -5) {
            return 4;
        }
        if (i == -9) {
            return 8;
        }
        if (i == -8) {
            return 7;
        }
        if (i == -7) {
            return 6;
        }
        switch (i) {
            case -15:
                return 12;
            case -14:
                return 11;
            case -13:
                return 10;
            default:
                return 1;
        }
    }

    private static int toUcePublishState(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i != 3) {
            return i != 4 ? 6 : 5;
        }
        return 4;
    }

    public int getPublisherState() {
        return 0;
    }

    public int getStackStatusForCapabilityRequest() {
        return 0;
    }

    public int requestAvailability(String str, int i) {
        return 0;
    }

    public int requestCapability(String[] strArr, int i) {
        return 0;
    }

    public int requestPublication(RcsContactUceCapability rcsContactUceCapability, String str, int i) {
        return 0;
    }

    public void updatePublisherState(int i) {
    }

    UserCapabilityExchangeImpl(Context context, int i, int i2) {
        this.mSlotId = i;
        this.mSubId = i2;
        logi("created");
        String[] stringArray = context.getResources().getStringArray(R.array.config_volte_provision_error_on_publish_response);
        String[] stringArray2 = context.getResources().getStringArray(R.array.config_rcs_provision_error_on_publish_response);
        this.mPresencePublication = new PresencePublication((PresencePublisher) null, context, stringArray, stringArray2);
        this.mPresenceSubscriber = new PresenceSubscriber((SubscribePublisher) null, context, stringArray, stringArray2);
        onAssociatedSubscriptionUpdated(this.mSubId);
    }

    @Override // com.android.services.telephony.rcs.RcsFeatureController.Feature
    public void onRcsConnected(RcsFeatureManager rcsFeatureManager) {
        logi("onRcsConnected");
        this.mPresencePublication.updatePresencePublisher(this);
        this.mPresenceSubscriber.updatePresenceSubscriber(this);
    }

    @Override // com.android.services.telephony.rcs.RcsFeatureController.Feature
    public void onRcsDisconnected() {
        logi("onRcsDisconnected");
        this.mPresencePublication.removePresencePublisher();
        this.mPresenceSubscriber.removePresenceSubscriber();
    }

    @Override // com.android.services.telephony.rcs.RcsFeatureController.Feature
    public void onAssociatedSubscriptionUpdated(int i) {
        this.mPresencePublication.handleAssociatedSubscriptionChanged(i);
        this.mPresenceSubscriber.handleAssociatedSubscriptionChanged(i);
    }

    @Override // com.android.services.telephony.rcs.RcsFeatureController.Feature
    public void onDestroy() {
        onRcsDisconnected();
    }

    public int getUcePublishState() {
        return toUcePublishState(this.mPresencePublication.getPublishState());
    }

    public void requestCapabilities(List<Uri> list, IRcsUceControllerCallback iRcsUceControllerCallback) {
        int iRequestCapability = this.mPresenceSubscriber.requestCapability((List) list.stream().map(new Function() { // from class: com.android.services.telephony.rcs.-$$Lambda$UserCapabilityExchangeImpl$d0t3Svkqdv5KBgS2p9Qgdu1_W18
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return UserCapabilityExchangeImpl.getNumberFromUri((Uri) obj);
            }
        }).collect(Collectors.toList()), new ContactCapabilityResponse() { // from class: com.android.services.telephony.rcs.UserCapabilityExchangeImpl.1
            public void onSuccess(int i) {
                UserCapabilityExchangeImpl.this.logi("onSuccess called for reqId:" + i);
            }

            public void onError(int i, int i2) {
                IRcsUceControllerCallback iRcsUceControllerCallback2 = (IRcsUceControllerCallback) UserCapabilityExchangeImpl.this.mPendingCapabilityRequests.remove(Integer.valueOf(i));
                try {
                    if (iRcsUceControllerCallback2 != null) {
                        iRcsUceControllerCallback2.onError(UserCapabilityExchangeImpl.toUceError(i2));
                    } else {
                        UserCapabilityExchangeImpl.this.logw("onError called for unknown reqId:" + i);
                    }
                } catch (RemoteException unused) {
                    UserCapabilityExchangeImpl.this.logi("Calling back to dead service");
                }
            }

            public void onFinish(int i) {
                UserCapabilityExchangeImpl.this.logi("onFinish called for reqId:" + i);
            }

            public void onTimeout(int i) {
                IRcsUceControllerCallback iRcsUceControllerCallback2 = (IRcsUceControllerCallback) UserCapabilityExchangeImpl.this.mPendingCapabilityRequests.remove(Integer.valueOf(i));
                try {
                    if (iRcsUceControllerCallback2 != null) {
                        iRcsUceControllerCallback2.onError(10);
                    } else {
                        UserCapabilityExchangeImpl.this.logw("onTimeout called for unknown reqId:" + i);
                    }
                } catch (RemoteException unused) {
                    UserCapabilityExchangeImpl.this.logi("Calling back to dead service");
                }
            }

            public void onCapabilitiesUpdated(int i, List<RcsContactUceCapability> list2, boolean z) {
                IRcsUceControllerCallback iRcsUceControllerCallback2 = (IRcsUceControllerCallback) UserCapabilityExchangeImpl.this.mPendingCapabilityRequests.remove(Integer.valueOf(i));
                try {
                    if (iRcsUceControllerCallback2 != null) {
                        iRcsUceControllerCallback2.onCapabilitiesReceived(list2);
                    } else {
                        UserCapabilityExchangeImpl.this.logw("onCapabilitiesUpdated, unknown reqId:" + i);
                    }
                } catch (RemoteException unused) {
                    UserCapabilityExchangeImpl.this.logw("onCapabilitiesUpdated on dead service");
                }
            }
        });
        if (iRequestCapability < 0) {
            try {
                iRcsUceControllerCallback.onError(toUceError(iRequestCapability));
                return;
            } catch (RemoteException unused) {
                logi("Calling back to dead service");
            }
        }
        this.mPendingCapabilityRequests.put(Integer.valueOf(iRequestCapability), iRcsUceControllerCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getNumberFromUri(Uri uri) {
        String[] strArrSplit = uri.getSchemeSpecificPart().split("[@;:]");
        if (strArrSplit.length == 0) {
            return null;
        }
        return strArrSplit[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logi(String str) {
        StringBuilder logPrefix = getLogPrefix();
        logPrefix.append(str);
        Log.i("RcsUceImpl", logPrefix.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logw(String str) {
        StringBuilder logPrefix = getLogPrefix();
        logPrefix.append(str);
        Log.w("RcsUceImpl", logPrefix.toString());
    }

    private StringBuilder getLogPrefix() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.mSlotId);
        sb.append("->");
        sb.append(this.mSubId);
        sb.append("] ");
        return sb;
    }
}
