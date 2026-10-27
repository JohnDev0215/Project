package android.hardware.soundtrigger;

import android.hardware.soundtrigger.SoundTrigger;
import android.media.soundtrigger_middleware.ISoundTriggerCallback;
import android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService;
import android.media.soundtrigger_middleware.ISoundTriggerModule;
import android.media.soundtrigger_middleware.PhraseRecognitionEvent;
import android.media.soundtrigger_middleware.RecognitionEvent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class SoundTriggerModule {
    private static final int EVENT_RECOGNITION = 1;
    private static final int EVENT_SERVICE_DIED = 2;
    private static final int EVENT_SERVICE_STATE_CHANGE = 3;
    private static final String TAG = "SoundTriggerModule";
    private EventHandlerDelegate mEventHandlerDelegate;
    private int mId;
    private ISoundTriggerModule mService;

    SoundTriggerModule(ISoundTriggerMiddlewareService iSoundTriggerMiddlewareService, int i, SoundTrigger.StatusListener statusListener, Looper looper) throws RemoteException {
        this.mId = i;
        EventHandlerDelegate eventHandlerDelegate = new EventHandlerDelegate(statusListener, looper);
        this.mEventHandlerDelegate = eventHandlerDelegate;
        ISoundTriggerModule iSoundTriggerModuleAttach = iSoundTriggerMiddlewareService.attach(i, eventHandlerDelegate);
        this.mService = iSoundTriggerModuleAttach;
        iSoundTriggerModuleAttach.asBinder().linkToDeath(this.mEventHandlerDelegate, 0);
    }

    protected void finalize() {
        detach();
    }

    public synchronized void detach() {
        try {
            ISoundTriggerModule iSoundTriggerModule = this.mService;
            if (iSoundTriggerModule != null) {
                iSoundTriggerModule.asBinder().unlinkToDeath(this.mEventHandlerDelegate, 0);
                this.mService.detach();
                this.mService = null;
            }
        } catch (Exception e) {
            SoundTrigger.handleException(e);
        }
    }

    public synchronized int loadSoundModel(SoundTrigger.SoundModel soundModel, int[] iArr) {
        try {
            if (soundModel instanceof SoundTrigger.GenericSoundModel) {
                iArr[0] = this.mService.loadModel(ConversionUtil.api2aidlGenericSoundModel((SoundTrigger.GenericSoundModel) soundModel));
                return 0;
            }
            if (soundModel instanceof SoundTrigger.KeyphraseSoundModel) {
                iArr[0] = this.mService.loadPhraseModel(ConversionUtil.api2aidlPhraseSoundModel((SoundTrigger.KeyphraseSoundModel) soundModel));
                return 0;
            }
            return SoundTrigger.STATUS_BAD_VALUE;
        } catch (Exception e) {
            return SoundTrigger.handleException(e);
        }
    }

    public synchronized int unloadSoundModel(int i) {
        try {
            this.mService.unloadModel(i);
        } catch (Exception e) {
            return SoundTrigger.handleException(e);
        }
        return 0;
    }

    public synchronized int startRecognition(int i, SoundTrigger.RecognitionConfig recognitionConfig) {
        try {
            this.mService.startRecognition(i, ConversionUtil.api2aidlRecognitionConfig(recognitionConfig));
        } catch (Exception e) {
            return SoundTrigger.handleException(e);
        }
        return 0;
    }

    public synchronized int stopRecognition(int i) {
        try {
            this.mService.stopRecognition(i);
        } catch (Exception e) {
            return SoundTrigger.handleException(e);
        }
        return 0;
    }

    public synchronized int getModelState(int i) {
        try {
            this.mService.forceRecognitionEvent(i);
        } catch (Exception e) {
            return SoundTrigger.handleException(e);
        }
        return 0;
    }

    public synchronized int setParameter(int i, int i2, int i3) {
        try {
            this.mService.setModelParameter(i, ConversionUtil.api2aidlModelParameter(i2), i3);
        } catch (Exception e) {
            return SoundTrigger.handleException(e);
        }
        return 0;
    }

    public synchronized int getParameter(int i, int i2) {
        try {
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
        return this.mService.getModelParameter(i, ConversionUtil.api2aidlModelParameter(i2));
    }

    public synchronized SoundTrigger.ModelParamRange queryParameter(int i, int i2) {
        try {
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
        return ConversionUtil.aidl2apiModelParameterRange(this.mService.queryModelParameterSupport(i, ConversionUtil.api2aidlModelParameter(i2)));
    }

    private class EventHandlerDelegate extends ISoundTriggerCallback.Stub implements IBinder.DeathRecipient {
        private final Handler mHandler;

        EventHandlerDelegate(final SoundTrigger.StatusListener statusListener, Looper looper) {
            this.mHandler = new Handler(looper) { // from class: android.hardware.soundtrigger.SoundTriggerModule.EventHandlerDelegate.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    int i = message.what;
                    if (i == 1) {
                        statusListener.onRecognition((SoundTrigger.RecognitionEvent) message.obj);
                        return;
                    }
                    if (i == 2) {
                        statusListener.onServiceDied();
                    } else if (i == 3) {
                        statusListener.onServiceStateChange(((Integer) message.obj).intValue());
                    } else {
                        Log.e(SoundTriggerModule.TAG, "Unknown message: " + message.toString());
                    }
                }
            };
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public synchronized void onRecognition(int i, RecognitionEvent recognitionEvent) throws RemoteException {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, ConversionUtil.aidl2apiRecognitionEvent(i, recognitionEvent)));
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public synchronized void onPhraseRecognition(int i, PhraseRecognitionEvent phraseRecognitionEvent) throws RemoteException {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, ConversionUtil.aidl2apiPhraseRecognitionEvent(i, phraseRecognitionEvent)));
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public synchronized void onRecognitionAvailabilityChange(boolean z) throws RemoteException {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(z ? 0 : 1)));
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public synchronized void onModuleDied() {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
        }

        @Override // android.os.IBinder.DeathRecipient
        public synchronized void binderDied() {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
        }
    }
}
