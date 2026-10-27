package com.android.server.telecom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.telecom.Log;
import android.telecom.Logging.Session;
import android.telecom.PhoneAccountHandle;
import android.telecom.PhoneAccountSuggestion;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.internal.telecom.IPhoneAccountSuggestionCallback;
import com.android.internal.telecom.IPhoneAccountSuggestionService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes.dex */
public class PhoneAccountSuggestionHelper {
    private static final String TAG = "PhoneAccountSuggestionHelper";
    private static ComponentName sOverrideComponent;

    public static CompletableFuture<List<PhoneAccountSuggestion>> bindAndGetSuggestions(Context context, Uri uri, final List<PhoneAccountHandle> list) {
        if (uri == null) {
            return CompletableFuture.completedFuture(getDefaultSuggestions(list));
        }
        final String strExtractNetworkPortion = PhoneNumberUtils.extractNetworkPortion(uri.getSchemeSpecificPart());
        ServiceInfo suggestionServiceInfo = getSuggestionServiceInfo(context);
        if (suggestionServiceInfo == null) {
            return CompletableFuture.completedFuture(getDefaultSuggestions(list));
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(suggestionServiceInfo.packageName, suggestionServiceInfo.name));
        final CompletableFuture<List<PhoneAccountSuggestion>> completableFuture = new CompletableFuture<>();
        final Session sessionCreateSubsession = Log.createSubsession();
        if (!context.bindService(intent, new ServiceConnection() { // from class: com.android.server.telecom.PhoneAccountSuggestionHelper.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.continueSession(sessionCreateSubsession, "PASH.oSC");
                try {
                    try {
                        IPhoneAccountSuggestionService.Stub.asInterface(iBinder).onAccountSuggestionRequest(new IPhoneAccountSuggestionCallback.Stub() { // from class: com.android.server.telecom.PhoneAccountSuggestionHelper.1.1
                            public void suggestPhoneAccounts(String str, List<PhoneAccountSuggestion> list2) {
                                if (TextUtils.equals(strExtractNetworkPortion, str)) {
                                    if (list2 == null) {
                                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                        completableFuture.complete(PhoneAccountSuggestionHelper.getDefaultSuggestions(list));
                                    } else {
                                        AnonymousClass1 anonymousClass12 = AnonymousClass1.this;
                                        completableFuture.complete(PhoneAccountSuggestionHelper.addDefaultsToProvidedSuggestions(list2, list));
                                    }
                                }
                            }
                        }, strExtractNetworkPortion);
                    } catch (RemoteException unused) {
                        Log.w(PhoneAccountSuggestionHelper.TAG, "Cancelling suggestion process due to remote exception", new Object[0]);
                        completableFuture.complete(PhoneAccountSuggestionHelper.getDefaultSuggestions(list));
                    }
                } finally {
                    Log.endSession();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Log.continueSession(sessionCreateSubsession, "PASH.oSD");
                try {
                    if (!completableFuture.isDone()) {
                        Log.w(PhoneAccountSuggestionHelper.TAG, "Cancelling suggestion process due to service disconnect", new Object[0]);
                    }
                    completableFuture.complete(PhoneAccountSuggestionHelper.getDefaultSuggestions(list));
                } finally {
                    Log.endSession();
                }
            }
        }, 1)) {
            Log.i(TAG, "Cancelling suggestion process due to bind failure.", new Object[0]);
            completableFuture.complete(getDefaultSuggestions(list));
        }
        new Handler().postDelayed(new Runnable() { // from class: com.android.server.telecom.-$$Lambda$PhoneAccountSuggestionHelper$G2m7Jj7SlW9usQ0bnJmXhewZg1Q
            @Override // java.lang.Runnable
            public final void run() {
                PhoneAccountSuggestionHelper.lambda$bindAndGetSuggestions$0(sessionCreateSubsession, completableFuture, list);
            }
        }, Timeouts.getPhoneAccountSuggestionServiceTimeout(context.getContentResolver()));
        return completableFuture;
    }

    static /* synthetic */ void lambda$bindAndGetSuggestions$0(Session session, CompletableFuture completableFuture, List list) {
        Log.continueSession(session, "PASH.timeout");
        try {
            if (!completableFuture.isDone()) {
                Log.w(TAG, "Cancelling suggestion process due to timeout", new Object[0]);
            }
            completableFuture.complete(getDefaultSuggestions(list));
        } finally {
            Log.endSession();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<PhoneAccountSuggestion> addDefaultsToProvidedSuggestions(List<PhoneAccountSuggestion> list, List<PhoneAccountHandle> list2) {
        final List list3 = (List) list.stream().map($$Lambda$yRlpl3dkbrkzuO0fcR2P1LnNvk.INSTANCE).collect(Collectors.toList());
        return (List) Stream.concat(getDefaultSuggestions((List) list2.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$PhoneAccountSuggestionHelper$hH4FympF25hMkf0mZCbTBKZo1bU
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PhoneAccountSuggestionHelper.lambda$addDefaultsToProvidedSuggestions$1(list3, (PhoneAccountHandle) obj);
            }
        }).collect(Collectors.toList())).stream(), list.stream()).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$addDefaultsToProvidedSuggestions$1(List list, PhoneAccountHandle phoneAccountHandle) {
        return !list.contains(phoneAccountHandle);
    }

    private static ServiceInfo getSuggestionServiceInfo(Context context) {
        List<ResolveInfo> listQueryIntentServices;
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.telecom.PhoneAccountSuggestionService");
        ComponentName componentName = sOverrideComponent;
        if (componentName == null) {
            listQueryIntentServices = packageManager.queryIntentServices(intent, 1048576);
        } else {
            Log.i(TAG, "Using override component %s", new Object[]{componentName});
            intent.setComponent(sOverrideComponent);
            listQueryIntentServices = packageManager.queryIntentServices(intent, 131072);
        }
        if (listQueryIntentServices == null || listQueryIntentServices.size() == 0) {
            Log.i(TAG, "No acct suggestion services found. Using defaults.", new Object[0]);
            return null;
        }
        if (listQueryIntentServices.size() > 1) {
            Log.w(TAG, "More than acct suggestion service found, cannot get unique service", new Object[0]);
            return null;
        }
        return listQueryIntentServices.get(0).serviceInfo;
    }

    static void setOverrideServiceName(String str) throws Exception {
        try {
            sOverrideComponent = TextUtils.isEmpty(str) ? null : ComponentName.unflattenFromString(str);
        } catch (Exception e) {
            sOverrideComponent = null;
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<PhoneAccountSuggestion> getDefaultSuggestions(List<PhoneAccountHandle> list) {
        return (List) list.stream().map(new Function() { // from class: com.android.server.telecom.-$$Lambda$PhoneAccountSuggestionHelper$i2P1-ZVeQXtXzasxUGb7FehgS2A
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PhoneAccountSuggestionHelper.lambda$getDefaultSuggestions$2((PhoneAccountHandle) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ PhoneAccountSuggestion lambda$getDefaultSuggestions$2(PhoneAccountHandle phoneAccountHandle) {
        return new PhoneAccountSuggestion(phoneAccountHandle, 0, false);
    }
}
