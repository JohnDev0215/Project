package com.amazon.mp3.auto;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.mp3.account.map.AccountDeregistration;
import com.amazon.mp3.account.map.PostAccountDeregister;
import com.amazon.mp3.util.Log;
import java.lang.ref.WeakReference;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: AutomotiveSignOutHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0011\u0012\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/auto/AutomotiveSignOutHelper;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "TAG$delegate", "Lkotlin/Lazy;", "isSignOutInProgress", "", "signUserOut", "", "context", "Landroid/content/Context;", "signOutCallback", "Lcom/amazon/mp3/auto/AutomotiveSignOutHelper$SignOutCallback;", "DeRegistrationCallback", "PostAccountDeRegistrationAsyncTask", "SignOutCallback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutomotiveSignOutHelper {
    public static final AutomotiveSignOutHelper INSTANCE = new AutomotiveSignOutHelper();

    /* JADX INFO: renamed from: TAG$delegate, reason: from kotlin metadata */
    private static final Lazy TAG = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.auto.AutomotiveSignOutHelper$TAG$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return Reflection.getOrCreateKotlinClass(AutomotiveSignOutHelper.class).getSimpleName();
        }
    });
    private static boolean isSignOutInProgress;

    /* JADX INFO: compiled from: AutomotiveSignOutHelper.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/auto/AutomotiveSignOutHelper$SignOutCallback;", "", "signOutCancel", "", "signOutComplete", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface SignOutCallback {
        void signOutCancel();

        void signOutComplete();
    }

    private AutomotiveSignOutHelper() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getTAG() {
        return (String) TAG.getValue();
    }

    public final void signUserOut(Context context, SignOutCallback signOutCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(signOutCallback, "signOutCallback");
        Log.debug(getTAG(), "signUserOut");
        if (isSignOutInProgress) {
            return;
        }
        isSignOutInProgress = true;
        AccountDeregistration.deregister(context, new DeRegistrationCallback(context, signOutCallback));
    }

    /* JADX INFO: compiled from: AutomotiveSignOutHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/auto/AutomotiveSignOutHelper$DeRegistrationCallback;", "Lcom/amazon/identity/auth/device/api/Callback;", "context", "Landroid/content/Context;", "signOutCallback", "Lcom/amazon/mp3/auto/AutomotiveSignOutHelper$SignOutCallback;", "(Landroid/content/Context;Lcom/amazon/mp3/auto/AutomotiveSignOutHelper$SignOutCallback;)V", "onError", "", "bundle", "Landroid/os/Bundle;", "onSuccess", "result", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class DeRegistrationCallback implements Callback {
        private final Context context;
        private final SignOutCallback signOutCallback;

        public DeRegistrationCallback(Context context, SignOutCallback signOutCallback) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(signOutCallback, "signOutCallback");
            this.context = context;
            this.signOutCallback = signOutCallback;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle result) {
            Log.debug(AutomotiveSignOutHelper.INSTANCE.getTAG(), "DeRegistrationCallback onSuccess");
            new PostAccountDeRegistrationAsyncTask(this.context, this.signOutCallback).execute(new Void[0]);
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            Log.error(AutomotiveSignOutHelper.INSTANCE.getTAG(), "Error occurred on deregistration async task");
            AutomotiveSignOutHelper.isSignOutInProgress = false;
            this.signOutCallback.signOutCancel();
        }
    }

    /* JADX INFO: compiled from: AutomotiveSignOutHelper.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J'\u0010\n\u001a\u0004\u0018\u00010\u00022\u0016\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\f\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0014R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/auto/AutomotiveSignOutHelper$PostAccountDeRegistrationAsyncTask;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "context", "Landroid/content/Context;", "signOutCallback", "Lcom/amazon/mp3/auto/AutomotiveSignOutHelper$SignOutCallback;", "(Landroid/content/Context;Lcom/amazon/mp3/auto/AutomotiveSignOutHelper$SignOutCallback;)V", "contextReference", "Ljava/lang/ref/WeakReference;", "doInBackground", "params", "", "([Ljava/lang/Void;)Ljava/lang/Void;", "onPostExecute", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class PostAccountDeRegistrationAsyncTask extends AsyncTask<Void, Void, Void> {
        private final WeakReference<Context> contextReference;
        private final SignOutCallback signOutCallback;

        public PostAccountDeRegistrationAsyncTask(Context context, SignOutCallback signOutCallback) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(signOutCallback, "signOutCallback");
            this.signOutCallback = signOutCallback;
            this.contextReference = new WeakReference<>(context);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            Intrinsics.checkNotNullParameter(params, "params");
            Log.debug(AutomotiveSignOutHelper.INSTANCE.getTAG(), "PostAccountDeRegistrationAsyncTask doInBackground");
            Context context = this.contextReference.get();
            if (context == null) {
                return null;
            }
            new PostAccountDeregister().onSignOut(context);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void params) {
            Log.debug(AutomotiveSignOutHelper.INSTANCE.getTAG(), "PostAccountDeRegistrationAsyncTask onPostExecute");
            AutomotiveSignOutHelper.isSignOutInProgress = false;
            this.signOutCallback.signOutComplete();
        }
    }
}
