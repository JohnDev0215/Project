package com.android.car.settings.network;

import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.UiccSlotInfo;
import android.text.TextUtils;
import com.android.internal.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes5.dex */
public final class SubscriptionUtils {
    private SubscriptionUtils() {
    }

    public static List<SubscriptionInfo> getAvailableSubscriptions(SubscriptionManager subscriptionManager, TelephonyManager telephonyManager) {
        List<SubscriptionInfo> subscriptions = new ArrayList<>(CollectionUtils.emptyIfNull(subscriptionManager.getSelectableSubscriptionInfoList()));
        List<UiccSlotInfo> missing = new ArrayList<>();
        UiccSlotInfo[] slotsInfo = telephonyManager.getUiccSlotsInfo();
        for (int i = 0; slotsInfo != null && i < slotsInfo.length; i++) {
            UiccSlotInfo slotInfo = slotsInfo[i];
            if (slotInfo != null && isInactiveInsertedPSim(slotInfo)) {
                final int index = slotInfo.getLogicalSlotIdx();
                final String cardId = slotInfo.getCardId();
                boolean found = subscriptions.stream().anyMatch(new Predicate() { // from class: com.android.car.settings.network.-$$Lambda$SubscriptionUtils$YbIhsA2XNdNREzmDuF8U0zYkzjI
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return SubscriptionUtils.lambda$getAvailableSubscriptions$0(index, cardId, (SubscriptionInfo) obj);
                    }
                });
                if (!found) {
                    missing.add(slotInfo);
                }
            }
        }
        if (!missing.isEmpty()) {
            for (SubscriptionInfo info : subscriptionManager.getAllSubscriptionInfoList()) {
                Iterator<UiccSlotInfo> it = missing.iterator();
                while (true) {
                    if (it.hasNext()) {
                        UiccSlotInfo slotInfo2 = it.next();
                        if (info.getSimSlotIndex() == slotInfo2.getLogicalSlotIdx() && info.getCardString().equals(slotInfo2.getCardId())) {
                            subscriptions.add(info);
                            break;
                        }
                    }
                }
            }
        }
        Iterator<SubscriptionInfo> iter = subscriptions.iterator();
        while (iter.hasNext()) {
            if (TextUtils.isEmpty(iter.next().getMncString())) {
                iter.remove();
            }
        }
        return subscriptions;
    }

    static /* synthetic */ boolean lambda$getAvailableSubscriptions$0(int index, String cardId, SubscriptionInfo info) {
        return index == info.getSimSlotIndex() && cardId.equals(info.getCardString());
    }

    private static boolean isInactiveInsertedPSim(UiccSlotInfo slotInfo) {
        return (slotInfo == null || slotInfo.getIsEuicc() || slotInfo.getIsActive() || slotInfo.getCardStateInfo() != 2) ? false : true;
    }
}
