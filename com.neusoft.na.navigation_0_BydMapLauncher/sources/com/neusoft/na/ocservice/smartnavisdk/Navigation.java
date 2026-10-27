package com.neusoft.na.ocservice.smartnavisdk;

import android.content.Context;
import com.neusoft.na.ocservice.smartnavisdk.impl.NavigationApiOctaImpl;

/* JADX INFO: loaded from: classes2.dex */
public class Navigation {
    public static synchronized INavigationApi create(Context context, INotificationListener iNotificationListener) {
        return new NavigationApiOctaImpl(context, iNotificationListener);
    }
}
