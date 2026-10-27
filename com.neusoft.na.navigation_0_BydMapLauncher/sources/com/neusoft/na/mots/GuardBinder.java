package com.neusoft.na.mots;

import android.content.Context;
import android.util.Log;
import com.neusoft.nts.common.IGuardInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GuardBinder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\fH\u0016J\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0012R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, d2 = {"Lcom/neusoft/na/mots/GuardBinder;", "Lcom/neusoft/nts/common/IGuardInterface$Stub;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "getContext", "()Landroid/content/Context;", "mActive", "", "getMActive", "()Z", "setMActive", "(Z)V", "active", "", "onDestroy", "restartApp", "startConditionMonitoring", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class GuardBinder extends IGuardInterface.Stub {
    private final String TAG;
    private final Context context;
    private boolean mActive;

    public GuardBinder(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "GuardBinder";
        Log.i("GuardBinder", "init");
        ConditionMonitoring companion = ConditionMonitoring.INSTANCE.getInstance(context);
        if (companion != null) {
            companion.startMonitoring();
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getMActive() {
        return this.mActive;
    }

    public final void setMActive(boolean z) {
        this.mActive = z;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void onDestroy() {
        Log.i(this.TAG, "onDestroy");
        ConditionMonitoring companion = ConditionMonitoring.INSTANCE.getInstance(this.context);
        if (companion != null) {
            companion.stopMonitoring();
        }
    }

    public final void startConditionMonitoring() {
        Log.i(this.TAG, "startConditionMonitoring");
        ConditionMonitoring companion = ConditionMonitoring.INSTANCE.getInstance(this.context);
        if (companion != null) {
            companion.startMonitoring();
        }
    }

    @Override // com.neusoft.nts.common.IGuardInterface
    public void active(boolean active) {
        Log.i(this.TAG, "active:" + active);
        this.mActive = active;
        ConditionMonitoring companion = ConditionMonitoring.INSTANCE.getInstance(this.context);
        if (companion != null) {
            companion.mainWindowActive(active);
        }
    }

    public final void restartApp() throws InterruptedException {
        Log.i(this.TAG, "restartApp");
        ConditionMonitoring companion = ConditionMonitoring.INSTANCE.getInstance(this.context);
        if (companion != null) {
            companion.killActivity();
        }
    }
}
