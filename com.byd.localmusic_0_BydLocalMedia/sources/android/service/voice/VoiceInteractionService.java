package android.service.voice;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.soundtrigger.KeyphraseEnrollmentInfo;
import android.media.voice.KeyphraseModelManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.voice.AlwaysOnHotwordDetector;
import android.service.voice.IVoiceInteractionService;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.app.IVoiceActionCheckCallback;
import com.android.internal.app.IVoiceInteractionManagerService;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public class VoiceInteractionService extends Service {
    public static final String SERVICE_INTERFACE = "android.service.voice.VoiceInteractionService";
    public static final String SERVICE_META_DATA = "android.voice_interaction";
    static final String TAG = VoiceInteractionService.class.getSimpleName();
    private AlwaysOnHotwordDetector mHotwordDetector;
    private KeyphraseEnrollmentInfo mKeyphraseEnrollmentInfo;
    IVoiceInteractionManagerService mSystemService;
    IVoiceInteractionService mInterface = new AnonymousClass1();
    private final Object mLock = new Object();
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() { // from class: android.service.voice.-$$Lambda$VoiceInteractionService$yug9azdKctkf1nIeEMtq_56D-GE
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            this.f$0.lambda$new$0$VoiceInteractionService();
        }
    };

    public void onLaunchVoiceAssistFromKeyguard() {
    }

    public void onShutdown() {
    }

    /* JADX INFO: renamed from: android.service.voice.VoiceInteractionService$1, reason: invalid class name */
    class AnonymousClass1 extends IVoiceInteractionService.Stub {
        AnonymousClass1() {
        }

        @Override // android.service.voice.IVoiceInteractionService
        public void ready() {
            Handler.getMain().executeOrSendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: android.service.voice.-$$Lambda$SpnCJ0NiI1Uo14qQ5iHFyV2F2mY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((VoiceInteractionService) obj).onReady();
                }
            }, VoiceInteractionService.this));
        }

        @Override // android.service.voice.IVoiceInteractionService
        public void shutdown() {
            Handler.getMain().executeOrSendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: android.service.voice.-$$Lambda$VoiceInteractionService$1$ILMD_OnlN3EpU4AqKW9H-tgCoMg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((VoiceInteractionService) obj).onShutdownInternal();
                }
            }, VoiceInteractionService.this));
        }

        @Override // android.service.voice.IVoiceInteractionService
        public void soundModelsChanged() {
            Handler.getMain().executeOrSendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: android.service.voice.-$$Lambda$VoiceInteractionService$1$WnZueQJxACwCZWfYsmNtGrcNbEc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((VoiceInteractionService) obj).onSoundModelsChangedInternal();
                }
            }, VoiceInteractionService.this));
        }

        @Override // android.service.voice.IVoiceInteractionService
        public void launchVoiceAssistFromKeyguard() {
            Handler.getMain().executeOrSendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: android.service.voice.-$$Lambda$2vcT7tC5Khx2oNbQI6Zvwrft_YM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((VoiceInteractionService) obj).onLaunchVoiceAssistFromKeyguard();
                }
            }, VoiceInteractionService.this));
        }

        @Override // android.service.voice.IVoiceInteractionService
        public void getActiveServiceSupportedActions(List<String> list, IVoiceActionCheckCallback iVoiceActionCheckCallback) {
            Handler.getMain().executeOrSendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.service.voice.-$$Lambda$VoiceInteractionService$1$gKwKkiuvnPnBCMXtKcZDpBR3098
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((VoiceInteractionService) obj).onHandleVoiceActionCheck((List) obj2, (IVoiceActionCheckCallback) obj3);
                }
            }, VoiceInteractionService.this, list, iVoiceActionCheckCallback));
        }
    }

    public static boolean isActiveService(Context context, ComponentName componentName) {
        ComponentName componentNameUnflattenFromString;
        String string = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.VOICE_INTERACTION_SERVICE);
        if (string == null || string.isEmpty() || (componentNameUnflattenFromString = ComponentName.unflattenFromString(string)) == null) {
            return false;
        }
        return componentNameUnflattenFromString.equals(componentName);
    }

    public void setDisabledShowContext(int i) {
        try {
            this.mSystemService.setDisabledShowContext(i);
        } catch (RemoteException unused) {
        }
    }

    public int getDisabledShowContext() {
        try {
            return this.mSystemService.getDisabledShowContext();
        } catch (RemoteException unused) {
            return 0;
        }
    }

    public void showSession(Bundle bundle, int i) {
        IVoiceInteractionManagerService iVoiceInteractionManagerService = this.mSystemService;
        if (iVoiceInteractionManagerService == null) {
            throw new IllegalStateException("Not available until onReady() is called");
        }
        try {
            iVoiceInteractionManagerService.showSession(bundle, i);
        } catch (RemoteException unused) {
        }
    }

    public Set<String> onGetSupportedVoiceActions(Set<String> set) {
        return Collections.emptySet();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (SERVICE_INTERFACE.equals(intent.getAction())) {
            return this.mInterface.asBinder();
        }
        return null;
    }

    public void onReady() {
        IVoiceInteractionManagerService iVoiceInteractionManagerServiceAsInterface = IVoiceInteractionManagerService.Stub.asInterface(ServiceManager.getService(Context.VOICE_INTERACTION_MANAGER_SERVICE));
        this.mSystemService = iVoiceInteractionManagerServiceAsInterface;
        Objects.requireNonNull(iVoiceInteractionManagerServiceAsInterface);
        try {
            this.mSystemService.asBinder().linkToDeath(this.mDeathRecipient, 0);
        } catch (RemoteException unused) {
            Log.wtf(TAG, "unable to link to death with system service");
        }
        this.mKeyphraseEnrollmentInfo = new KeyphraseEnrollmentInfo(getPackageManager());
    }

    public /* synthetic */ void lambda$new$0$VoiceInteractionService() {
        Log.e(TAG, "system service binder died shutting down");
        Handler.getMain().executeOrSendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: android.service.voice.-$$Lambda$VoiceInteractionService$V4h-zKzsPhikYYwTTwjkcep67fc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((VoiceInteractionService) obj).onShutdownInternal();
            }
        }, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onShutdownInternal() {
        onShutdown();
        safelyShutdownHotwordDetector();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSoundModelsChangedInternal() {
        synchronized (this) {
            if (this.mHotwordDetector != null) {
                this.mHotwordDetector.onSoundModelsChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHandleVoiceActionCheck(List<String> list, IVoiceActionCheckCallback iVoiceActionCheckCallback) {
        if (iVoiceActionCheckCallback != null) {
            try {
                iVoiceActionCheckCallback.onComplete(new ArrayList(onGetSupportedVoiceActions(new ArraySet(list))));
            } catch (RemoteException unused) {
            }
        }
    }

    public final AlwaysOnHotwordDetector createAlwaysOnHotwordDetector(String str, Locale locale, AlwaysOnHotwordDetector.Callback callback) {
        AlwaysOnHotwordDetector alwaysOnHotwordDetector;
        if (this.mSystemService == null) {
            throw new IllegalStateException("Not available until onReady() is called");
        }
        synchronized (this.mLock) {
            safelyShutdownHotwordDetector();
            alwaysOnHotwordDetector = new AlwaysOnHotwordDetector(str, locale, callback, this.mKeyphraseEnrollmentInfo, this.mSystemService);
            this.mHotwordDetector = alwaysOnHotwordDetector;
        }
        return alwaysOnHotwordDetector;
    }

    @SystemApi
    public final KeyphraseModelManager createKeyphraseModelManager() {
        KeyphraseModelManager keyphraseModelManager;
        if (this.mSystemService == null) {
            throw new IllegalStateException("Not available until onReady() is called");
        }
        synchronized (this.mLock) {
            keyphraseModelManager = new KeyphraseModelManager(this.mSystemService);
        }
        return keyphraseModelManager;
    }

    protected final KeyphraseEnrollmentInfo getKeyphraseEnrollmentInfo() {
        return this.mKeyphraseEnrollmentInfo;
    }

    public final boolean isKeyphraseAndLocaleSupportedForHotword(String str, Locale locale) {
        KeyphraseEnrollmentInfo keyphraseEnrollmentInfo = this.mKeyphraseEnrollmentInfo;
        return (keyphraseEnrollmentInfo == null || keyphraseEnrollmentInfo.getKeyphraseMetadata(str, locale) == null) ? false : true;
    }

    private void safelyShutdownHotwordDetector() {
        synchronized (this.mLock) {
            if (this.mHotwordDetector == null) {
                return;
            }
            try {
                this.mHotwordDetector.stopRecognition();
            } catch (Exception unused) {
            }
            try {
                this.mHotwordDetector.invalidate();
            } catch (Exception unused2) {
            }
            this.mHotwordDetector = null;
        }
    }

    public final void setUiHints(Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Hints must be non-null");
        }
        try {
            this.mSystemService.setUiHints(bundle);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("VOICE INTERACTION");
        synchronized (this.mLock) {
            printWriter.println("  AlwaysOnHotwordDetector");
            if (this.mHotwordDetector == null) {
                printWriter.println("    NULL");
            } else {
                this.mHotwordDetector.dump("    ", printWriter);
            }
        }
    }
}
