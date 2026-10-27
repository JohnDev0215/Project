package com.android.server.telecom;

import android.content.Context;
import android.telecom.CallerInfoAsyncQuery;

/* JADX INFO: loaded from: classes.dex */
public interface CallerInfoAsyncQueryFactory {
    CallerInfoAsyncQuery startQuery(int i, Context context, String str, CallerInfoAsyncQuery.OnQueryCompleteListener onQueryCompleteListener, Object obj);
}
