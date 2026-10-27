package com.neusoft.na.ocservice.ecarxnavsdk;

import android.content.Context;
import com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl;

/* JADX INFO: loaded from: classes2.dex */
public class Navigation {
    public static synchronized INavigationApi create(Context context, INotificationListener iNotificationListener) {
        return new NavigationApiOctaImpl(context, iNotificationListener);
    }
}
