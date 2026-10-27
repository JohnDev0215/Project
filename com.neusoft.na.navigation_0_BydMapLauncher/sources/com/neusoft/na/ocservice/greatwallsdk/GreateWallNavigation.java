package com.neusoft.na.ocservice.greatwallsdk;

import android.content.Context;
import com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi;
import com.neusoft.na.ocservice.ecarxnavsdk.INotificationListener;
import com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl;

/* JADX INFO: loaded from: classes2.dex */
public class GreateWallNavigation {
    public static synchronized INavigationApi create(Context context, INotificationListener iNotificationListener) {
        return new NavigationApiOctaImpl(context, iNotificationListener);
    }
}
