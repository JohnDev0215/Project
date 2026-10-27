package com.bugsnag.android;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.core.os.EnvironmentCompat;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ConnectivityCompat.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012>\u0010\u0015\u001a:\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000fj\u0004\u0018\u0001`\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016R\u0016\u0010\t\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/bugsnag/android/ConnectivityCompat;", "Lcom/bugsnag/android/Connectivity;", "", "registerForNetworkChanges", "", "hasNetworkConnection", "", "retrieveNetworkAccessState", "Landroid/net/ConnectivityManager;", "cm", "Landroid/net/ConnectivityManager;", "connectivity", "Lcom/bugsnag/android/Connectivity;", "Landroid/content/Context;", "context", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "hasConnection", "networkState", "Lcom/bugsnag/android/NetworkChangeCallback;", "callback", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function2;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class ConnectivityCompat implements Connectivity {
    private final ConnectivityManager cm;
    private final Connectivity connectivity;

    public ConnectivityCompat(Context context, Function2<? super Boolean, ? super String, Unit> function2) {
        Connectivity connectivityApi24;
        ConnectivityManager connectivityManagerFrom = ContextExtensionsKt.getConnectivityManagerFrom(context);
        this.cm = connectivityManagerFrom;
        if (connectivityManagerFrom == null) {
            connectivityApi24 = UnknownConnectivity.INSTANCE;
        } else {
            connectivityApi24 = new ConnectivityApi24(connectivityManagerFrom, function2);
        }
        this.connectivity = connectivityApi24;
    }

    @Override // com.bugsnag.android.Connectivity
    public void registerForNetworkChanges() {
        try {
            Result.Companion companion = Result.INSTANCE;
            this.connectivity.registerForNetworkChanges();
            Result.m2810constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
    }

    @Override // com.bugsnag.android.Connectivity
    public boolean hasNetworkConnection() {
        Object objM2810constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(Boolean.valueOf(this.connectivity.hasNetworkConnection()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2813exceptionOrNullimpl(objM2810constructorimpl) != null) {
            objM2810constructorimpl = Boolean.TRUE;
        }
        return ((Boolean) objM2810constructorimpl).booleanValue();
    }

    @Override // com.bugsnag.android.Connectivity
    public String retrieveNetworkAccessState() {
        Object objM2810constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(this.connectivity.retrieveNetworkAccessState());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM2810constructorimpl = Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2813exceptionOrNullimpl(objM2810constructorimpl) != null) {
            objM2810constructorimpl = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return (String) objM2810constructorimpl;
    }
}
