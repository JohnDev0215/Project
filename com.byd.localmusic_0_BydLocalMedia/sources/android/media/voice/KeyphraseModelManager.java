package android.media.voice;

import android.annotation.SystemApi;
import android.hardware.soundtrigger.SoundTrigger;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import com.android.internal.app.IVoiceInteractionManagerService;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class KeyphraseModelManager {
    private static final boolean DBG = false;
    private static final String TAG = "KeyphraseModelManager";
    private final IVoiceInteractionManagerService mVoiceInteractionManagerService;

    public KeyphraseModelManager(IVoiceInteractionManagerService iVoiceInteractionManagerService) {
        this.mVoiceInteractionManagerService = iVoiceInteractionManagerService;
    }

    public SoundTrigger.KeyphraseSoundModel getKeyphraseSoundModel(int i, Locale locale) {
        Objects.requireNonNull(locale);
        try {
            return this.mVoiceInteractionManagerService.getKeyphraseSoundModel(i, locale.toLanguageTag());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void updateKeyphraseSoundModel(SoundTrigger.KeyphraseSoundModel keyphraseSoundModel) {
        Objects.requireNonNull(keyphraseSoundModel);
        try {
            int iUpdateKeyphraseSoundModel = this.mVoiceInteractionManagerService.updateKeyphraseSoundModel(keyphraseSoundModel);
            if (iUpdateKeyphraseSoundModel == 0) {
            } else {
                throw new ServiceSpecificException(iUpdateKeyphraseSoundModel);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void deleteKeyphraseSoundModel(int i, Locale locale) {
        Objects.requireNonNull(locale);
        try {
            int iDeleteKeyphraseSoundModel = this.mVoiceInteractionManagerService.deleteKeyphraseSoundModel(i, locale.toLanguageTag());
            if (iDeleteKeyphraseSoundModel == 0) {
            } else {
                throw new ServiceSpecificException(iDeleteKeyphraseSoundModel);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
