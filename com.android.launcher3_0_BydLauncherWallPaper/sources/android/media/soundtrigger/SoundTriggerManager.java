package android.media.soundtrigger;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.hardware.soundtrigger.SoundTrigger;
import android.media.soundtrigger.SoundTriggerDetector;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Slog;
import com.android.internal.app.ISoundTriggerService;
import com.android.internal.util.Preconditions;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class SoundTriggerManager {
    private static final boolean DBG = false;
    public static final String EXTRA_MESSAGE_TYPE = "android.media.soundtrigger.MESSAGE_TYPE";
    public static final String EXTRA_RECOGNITION_EVENT = "android.media.soundtrigger.RECOGNITION_EVENT";
    public static final String EXTRA_STATUS = "android.media.soundtrigger.STATUS";
    public static final int FLAG_MESSAGE_TYPE_RECOGNITION_ERROR = 1;
    public static final int FLAG_MESSAGE_TYPE_RECOGNITION_EVENT = 0;
    public static final int FLAG_MESSAGE_TYPE_RECOGNITION_PAUSED = 2;
    public static final int FLAG_MESSAGE_TYPE_RECOGNITION_RESUMED = 3;
    public static final int FLAG_MESSAGE_TYPE_UNKNOWN = -1;
    private static final String TAG = "SoundTriggerManager";
    private final Context mContext;
    private final HashMap<UUID, SoundTriggerDetector> mReceiverInstanceMap = new HashMap<>();
    private final ISoundTriggerService mSoundTriggerService;

    public SoundTriggerManager(Context context, ISoundTriggerService iSoundTriggerService) {
        this.mSoundTriggerService = iSoundTriggerService;
        this.mContext = context;
    }

    public void updateModel(Model model) {
        if (this.mSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
        }
        try {
            this.mSoundTriggerService.updateSoundModel(model.getGenericSoundModel());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Model getModel(UUID uuid) {
        ISoundTriggerService iSoundTriggerService = this.mSoundTriggerService;
        if (iSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
            return null;
        }
        try {
            SoundTrigger.GenericSoundModel soundModel = iSoundTriggerService.getSoundModel(new ParcelUuid(uuid));
            if (soundModel == null) {
                return null;
            }
            return new Model(soundModel);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void deleteModel(UUID uuid) {
        if (this.mSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
        }
        try {
            this.mSoundTriggerService.deleteSoundModel(new ParcelUuid(uuid));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public SoundTriggerDetector createSoundTriggerDetector(UUID uuid, SoundTriggerDetector.Callback callback, Handler handler) {
        if (uuid == null) {
            return null;
        }
        if (this.mSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
            return null;
        }
        this.mReceiverInstanceMap.get(uuid);
        SoundTriggerDetector soundTriggerDetector = new SoundTriggerDetector(this.mSoundTriggerService, uuid, callback, handler);
        this.mReceiverInstanceMap.put(uuid, soundTriggerDetector);
        return soundTriggerDetector;
    }

    public static class Model {
        private SoundTrigger.GenericSoundModel mGenericSoundModel;

        Model(SoundTrigger.GenericSoundModel genericSoundModel) {
            this.mGenericSoundModel = genericSoundModel;
        }

        public static Model create(UUID uuid, UUID uuid2, byte[] bArr, int i) {
            Objects.requireNonNull(uuid);
            Objects.requireNonNull(uuid2);
            return new Model(new SoundTrigger.GenericSoundModel(uuid, uuid2, bArr, i));
        }

        public static Model create(UUID uuid, UUID uuid2, byte[] bArr) {
            return create(uuid, uuid2, bArr, -1);
        }

        public UUID getModelUuid() {
            return this.mGenericSoundModel.getUuid();
        }

        public UUID getVendorUuid() {
            return this.mGenericSoundModel.getVendorUuid();
        }

        public int getVersion() {
            return this.mGenericSoundModel.getVersion();
        }

        public byte[] getModelData() {
            return this.mGenericSoundModel.getData();
        }

        SoundTrigger.GenericSoundModel getGenericSoundModel() {
            return this.mGenericSoundModel;
        }
    }

    public int loadSoundModel(SoundTrigger.SoundModel soundModel) {
        if (soundModel == null) {
            return Integer.MIN_VALUE;
        }
        if (this.mSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
            return Integer.MIN_VALUE;
        }
        try {
            int type = soundModel.getType();
            if (type == 0) {
                return this.mSoundTriggerService.loadKeyphraseSoundModel((SoundTrigger.KeyphraseSoundModel) soundModel);
            }
            if (type == 1) {
                return this.mSoundTriggerService.loadGenericSoundModel((SoundTrigger.GenericSoundModel) soundModel);
            }
            Slog.e(TAG, "Unkown model type");
            return Integer.MIN_VALUE;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int startRecognition(UUID uuid, Bundle bundle, ComponentName componentName, SoundTrigger.RecognitionConfig recognitionConfig) {
        Preconditions.checkNotNull(uuid);
        Preconditions.checkNotNull(componentName);
        Preconditions.checkNotNull(recognitionConfig);
        Preconditions.checkNotNull(this.mSoundTriggerService, "Feature android.software.soundtrigger not available");
        try {
            return this.mSoundTriggerService.startRecognitionForService(new ParcelUuid(uuid), bundle, componentName, recognitionConfig);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int stopRecognition(UUID uuid) {
        if (uuid == null) {
            return Integer.MIN_VALUE;
        }
        ISoundTriggerService iSoundTriggerService = this.mSoundTriggerService;
        if (iSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
            return Integer.MIN_VALUE;
        }
        try {
            return iSoundTriggerService.stopRecognitionForService(new ParcelUuid(uuid));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int unloadSoundModel(UUID uuid) {
        if (uuid == null) {
            return Integer.MIN_VALUE;
        }
        ISoundTriggerService iSoundTriggerService = this.mSoundTriggerService;
        if (iSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
            return Integer.MIN_VALUE;
        }
        try {
            return iSoundTriggerService.unloadSoundModel(new ParcelUuid(uuid));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isRecognitionActive(UUID uuid) {
        if (uuid == null) {
            return false;
        }
        ISoundTriggerService iSoundTriggerService = this.mSoundTriggerService;
        if (iSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
            return false;
        }
        try {
            return iSoundTriggerService.isRecognitionActive(new ParcelUuid(uuid));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getDetectionServiceOperationsTimeout() {
        try {
            return Settings.Global.getInt(this.mContext.getContentResolver(), Settings.Global.SOUND_TRIGGER_DETECTION_SERVICE_OP_TIMEOUT);
        } catch (Settings.SettingNotFoundException unused) {
            return Integer.MAX_VALUE;
        }
    }

    public int getModelState(UUID uuid) {
        if (uuid == null) {
            return Integer.MIN_VALUE;
        }
        ISoundTriggerService iSoundTriggerService = this.mSoundTriggerService;
        if (iSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
            return Integer.MIN_VALUE;
        }
        try {
            return iSoundTriggerService.getModelState(new ParcelUuid(uuid));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public SoundTrigger.ModuleProperties getModuleProperties() {
        ISoundTriggerService iSoundTriggerService = this.mSoundTriggerService;
        if (iSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
            return null;
        }
        try {
            return iSoundTriggerService.getModuleProperties();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int setParameter(UUID uuid, int i, int i2) {
        Preconditions.checkNotNull(this.mSoundTriggerService, "Feature android.software.soundtrigger not available");
        try {
            return this.mSoundTriggerService.setParameter(new ParcelUuid(uuid), i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getParameter(UUID uuid, int i) {
        Preconditions.checkNotNull(this.mSoundTriggerService, "Feature android.software.soundtrigger not available");
        try {
            return this.mSoundTriggerService.getParameter(new ParcelUuid(uuid), i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public SoundTrigger.ModelParamRange queryParameter(UUID uuid, int i) {
        ISoundTriggerService iSoundTriggerService = this.mSoundTriggerService;
        if (iSoundTriggerService == null) {
            Slog.w(TAG, "Feature android.software.soundtrigger not available");
            return null;
        }
        try {
            return iSoundTriggerService.queryParameter(new ParcelUuid(uuid), i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
