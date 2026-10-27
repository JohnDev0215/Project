package com.amazon.mp3.navigation.dialogtargetconverter;

import androidx.annotation.NonNull;
import com.amazon.mp3.dialog.dialogtarget.DefaultDialogTarget;
import com.amazon.mp3.dialog.dialogtarget.DialogTarget;
import com.amazon.mp3.dialog.fragment.HawkfireAllDevicesUpsellDialogFragment;
import com.amazon.mp3.dialog.fragment.HawkfireUpsellDialogFragment;
import com.amazon.mp3.dialog.fragment.HawkfireUpsellDialogFragmentV2;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.destination.DeeplinkTier;
import com.amazon.music.destination.SignupDestination;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class SignupDestinationDialogTargetConverter {
    public DialogTarget convert(final SignupDestination destination, @NonNull final WebTarget webTarget) {
        Class cls;
        if (destination.getTier() == DeeplinkTier.UNLIMITED_INDIVIDUAL && UserSubscriptionUtil.getUserSubscription().isHawkfireHome()) {
            cls = HawkfireAllDevicesUpsellDialogFragment.class;
        } else if (UserSubscriptionUtil.isNightwingOnly()) {
            cls = HawkfireUpsellDialogFragmentV2.class;
        } else {
            cls = HawkfireUpsellDialogFragment.class;
        }
        return new DefaultDialogTarget(cls, getBundleWithWebTarget(webTarget));
    }

    private Map<String, Serializable> getBundleWithWebTarget(final WebTarget webTarget) {
        HashMap map = new HashMap();
        map.put("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET", webTarget);
        return map;
    }
}
