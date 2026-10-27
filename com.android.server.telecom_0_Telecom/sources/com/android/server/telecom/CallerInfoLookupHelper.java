package com.android.server.telecom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.telecom.CallerInfo;
import android.telecom.CallerInfoAsyncQuery;
import android.telecom.Log;
import android.telecom.Logging.Runnable;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import android.util.Pair;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.ContactsAsyncHelper;
import com.android.server.telecom.TelecomSystem;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
public class CallerInfoLookupHelper {
    private final CallerInfoAsyncQueryFactory mCallerInfoAsyncQueryFactory;
    private final ContactsAsyncHelper mContactsAsyncHelper;
    private final Context mContext;
    private final TelecomSystem.SyncRoot mLock;
    private final Map<Uri, CallerInfoQueryInfo> mQueryEntries = new HashMap();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private static class CallerInfoQueryInfo {
        public CallerInfo callerInfo;
        public boolean imageQueryPending = false;
        public List<OnQueryCompleteListener> listeners = new LinkedList();
    }

    public interface OnQueryCompleteListener {
        void onCallerInfoQueryComplete(Uri uri, CallerInfo callerInfo);

        void onContactPhotoQueryComplete(Uri uri, CallerInfo callerInfo);
    }

    public CallerInfoLookupHelper(Context context, CallerInfoAsyncQueryFactory callerInfoAsyncQueryFactory, ContactsAsyncHelper contactsAsyncHelper, TelecomSystem.SyncRoot syncRoot) {
        this.mCallerInfoAsyncQueryFactory = callerInfoAsyncQueryFactory;
        this.mContactsAsyncHelper = contactsAsyncHelper;
        this.mContext = context;
        this.mLock = syncRoot;
    }

    public CompletableFuture<Pair<Uri, CallerInfo>> startLookup(Uri uri) {
        final CompletableFuture<Pair<Uri, CallerInfo>> completableFuture = new CompletableFuture<>();
        if (TextUtils.isEmpty(uri.getSchemeSpecificPart())) {
            Log.d(this, "onCallerInfoQueryComplete - no number; end early", new Object[0]);
            completableFuture.complete(new Pair<>(uri, null));
            return completableFuture;
        }
        startLookup(uri, new OnQueryCompleteListener() { // from class: com.android.server.telecom.CallerInfoLookupHelper.1
            @Override // com.android.server.telecom.CallerInfoLookupHelper.OnQueryCompleteListener
            public void onContactPhotoQueryComplete(Uri uri2, CallerInfo callerInfo) {
            }

            @Override // com.android.server.telecom.CallerInfoLookupHelper.OnQueryCompleteListener
            public void onCallerInfoQueryComplete(Uri uri2, CallerInfo callerInfo) {
                Log.d(CallerInfoLookupHelper.this, "onCallerInfoQueryComplete - found info for %s", new Object[]{Log.piiHandle(uri2)});
                completableFuture.complete(new Pair(uri2, callerInfo));
            }
        });
        return completableFuture;
    }

    public void startLookup(final Uri uri, OnQueryCompleteListener onQueryCompleteListener) {
        if (uri == null) {
            onQueryCompleteListener.onCallerInfoQueryComplete(uri, null);
            return;
        }
        final String schemeSpecificPart = uri.getSchemeSpecificPart();
        if (TextUtils.isEmpty(schemeSpecificPart)) {
            onQueryCompleteListener.onCallerInfoQueryComplete(uri, null);
            return;
        }
        synchronized (this.mLock) {
            if (this.mQueryEntries.containsKey(uri)) {
                CallerInfoQueryInfo callerInfoQueryInfo = this.mQueryEntries.get(uri);
                if (callerInfoQueryInfo.callerInfo != null) {
                    Log.i(this, "Caller info already exists for handle %s; using cached value", new Object[]{Log.piiHandle(uri)});
                    onQueryCompleteListener.onCallerInfoQueryComplete(uri, callerInfoQueryInfo.callerInfo);
                    if (!callerInfoQueryInfo.imageQueryPending && (callerInfoQueryInfo.callerInfo.cachedPhoto != null || callerInfoQueryInfo.callerInfo.cachedPhotoIcon != null)) {
                        onQueryCompleteListener.onContactPhotoQueryComplete(uri, callerInfoQueryInfo.callerInfo);
                    } else if (callerInfoQueryInfo.imageQueryPending) {
                        Log.i(this, "There is a pending photo query for handle %s. Adding to listeners for this query.", new Object[]{Log.piiHandle(uri)});
                        callerInfoQueryInfo.listeners.add(onQueryCompleteListener);
                    }
                } else {
                    Log.i(this, "There is a previously incomplete query for handle %s. Adding to listeners for this query.", new Object[]{Log.piiHandle(uri)});
                    callerInfoQueryInfo.listeners.add(onQueryCompleteListener);
                }
                return;
            }
            CallerInfoQueryInfo callerInfoQueryInfo2 = new CallerInfoQueryInfo();
            callerInfoQueryInfo2.listeners.add(onQueryCompleteListener);
            this.mQueryEntries.put(uri, callerInfoQueryInfo2);
            this.mHandler.post(new Runnable("CILH.sL", null) { // from class: com.android.server.telecom.CallerInfoLookupHelper.2
                public void loggedRun() {
                    Session sessionCreateSubsession = Log.createSubsession();
                    try {
                        if (CallerInfoLookupHelper.this.mCallerInfoAsyncQueryFactory.startQuery(0, CallerInfoLookupHelper.this.mContext, schemeSpecificPart, CallerInfoLookupHelper.this.makeCallerInfoQueryListener(uri), sessionCreateSubsession) == null) {
                            Log.w(this, "Lookup failed for %s.", new Object[]{Log.piiHandle(uri)});
                        }
                    } finally {
                        Log.cancelSubsession(sessionCreateSubsession);
                    }
                }
            }.prepare());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CallerInfoAsyncQuery.OnQueryCompleteListener makeCallerInfoQueryListener(final Uri uri) {
        return new CallerInfoAsyncQuery.OnQueryCompleteListener() { // from class: com.android.server.telecom.-$$Lambda$CallerInfoLookupHelper$LoLKPo2w-9FDM1ZXkaWws6xzBjI
            public final void onQueryComplete(int i, Object obj, CallerInfo callerInfo) {
                this.f$0.lambda$makeCallerInfoQueryListener$0$CallerInfoLookupHelper(uri, i, obj, callerInfo);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$makeCallerInfoQueryListener$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$makeCallerInfoQueryListener$0$CallerInfoLookupHelper(Uri uri, int i, Object obj, CallerInfo callerInfo) {
        synchronized (this.mLock) {
            Log.continueSession((Session) obj, "CILH.oQC");
            try {
                if (this.mQueryEntries.containsKey(uri)) {
                    Log.i(this, "CI query for handle %s has completed; notifying all listeners.", new Object[]{Log.piiHandle(uri)});
                    CallerInfoQueryInfo callerInfoQueryInfo = this.mQueryEntries.get(uri);
                    Iterator<OnQueryCompleteListener> it = callerInfoQueryInfo.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onCallerInfoQueryComplete(uri, callerInfo);
                    }
                    if (callerInfo.getContactDisplayPhotoUri() == null) {
                        Log.i(this, "There is no photo for this contact, skipping photo query", new Object[0]);
                        this.mQueryEntries.remove(uri);
                    } else {
                        callerInfoQueryInfo.callerInfo = callerInfo;
                        callerInfoQueryInfo.imageQueryPending = true;
                        startPhotoLookup(uri, callerInfo.getContactDisplayPhotoUri());
                    }
                } else {
                    Log.i(this, "CI query for handle %s has completed, but there are no listeners left.", new Object[]{Log.piiHandle(uri)});
                }
            } finally {
                Log.endSession();
            }
        }
    }

    private void startPhotoLookup(final Uri uri, final Uri uri2) {
        this.mHandler.post(new Runnable("CILH.sPL", null) { // from class: com.android.server.telecom.CallerInfoLookupHelper.3
            public void loggedRun() {
                Session sessionCreateSubsession = Log.createSubsession();
                try {
                    CallerInfoLookupHelper.this.mContactsAsyncHelper.startObtainPhotoAsync(0, CallerInfoLookupHelper.this.mContext, uri2, CallerInfoLookupHelper.this.makeContactPhotoListener(uri), sessionCreateSubsession);
                } catch (Throwable th) {
                    Log.cancelSubsession(sessionCreateSubsession);
                    throw th;
                }
            }
        }.prepare());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContactsAsyncHelper.OnImageLoadCompleteListener makeContactPhotoListener(final Uri uri) {
        return new ContactsAsyncHelper.OnImageLoadCompleteListener() { // from class: com.android.server.telecom.-$$Lambda$CallerInfoLookupHelper$1QeDSz843ksAT6nXcWPzInR5VPg
            @Override // com.android.server.telecom.ContactsAsyncHelper.OnImageLoadCompleteListener
            public final void onImageLoadComplete(int i, Drawable drawable, Bitmap bitmap, Object obj) {
                this.f$0.lambda$makeContactPhotoListener$1$CallerInfoLookupHelper(uri, i, drawable, bitmap, obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$makeContactPhotoListener$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$makeContactPhotoListener$1$CallerInfoLookupHelper(Uri uri, int i, Drawable drawable, Bitmap bitmap, Object obj) {
        synchronized (this.mLock) {
            Log.continueSession((Session) obj, "CLIH.oILC");
            try {
                if (this.mQueryEntries.containsKey(uri)) {
                    CallerInfoQueryInfo callerInfoQueryInfo = this.mQueryEntries.get(uri);
                    if (callerInfoQueryInfo.callerInfo == null) {
                        Log.w(this, "Photo query finished, but the CallerInfo object previously looked up was not cached.", new Object[0]);
                        this.mQueryEntries.remove(uri);
                        return;
                    }
                    callerInfoQueryInfo.callerInfo.cachedPhoto = drawable;
                    callerInfoQueryInfo.callerInfo.cachedPhotoIcon = bitmap;
                    Iterator<OnQueryCompleteListener> it = callerInfoQueryInfo.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onContactPhotoQueryComplete(uri, callerInfoQueryInfo.callerInfo);
                    }
                    this.mQueryEntries.remove(uri);
                } else {
                    Log.i(this, "Photo query for handle %s has completed, but there are no listeners left.", new Object[]{Log.piiHandle(uri)});
                }
            } finally {
                Log.endSession();
            }
        }
    }

    @VisibleForTesting
    public Map<Uri, CallerInfoQueryInfo> getCallerInfoEntries() {
        return this.mQueryEntries;
    }

    @VisibleForTesting
    public Handler getHandler() {
        return this.mHandler;
    }
}
