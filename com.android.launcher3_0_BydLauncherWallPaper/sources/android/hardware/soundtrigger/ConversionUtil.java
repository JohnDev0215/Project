package android.hardware.soundtrigger;

import android.hardware.soundtrigger.SoundTrigger;
import android.media.AudioFormat;
import android.media.audio.common.AudioConfig;
import android.media.audio.common.AudioFormat;
import android.media.soundtrigger_middleware.ConfidenceLevel;
import android.media.soundtrigger_middleware.ModelParameterRange;
import android.media.soundtrigger_middleware.Phrase;
import android.media.soundtrigger_middleware.PhraseRecognitionEvent;
import android.media.soundtrigger_middleware.PhraseRecognitionExtra;
import android.media.soundtrigger_middleware.PhraseSoundModel;
import android.media.soundtrigger_middleware.RecognitionConfig;
import android.media.soundtrigger_middleware.RecognitionEvent;
import android.media.soundtrigger_middleware.SoundModel;
import android.media.soundtrigger_middleware.SoundTriggerModuleDescriptor;
import android.media.soundtrigger_middleware.SoundTriggerModuleProperties;
import android.os.SharedMemory;
import android.system.ErrnoException;
import java.io.FileDescriptor;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
class ConversionUtil {
    public static int aidl2apiAudioCapabilities(int i) {
        int i2 = (i & 1) != 0 ? 1 : 0;
        return (i & 2) != 0 ? i2 | 2 : i2;
    }

    public static int aidl2apiChannelInMask(int i) {
        return i;
    }

    public static int aidl2apiEncoding(int i) {
        if (i == 167772160) {
            return 6;
        }
        if (i == 167772161) {
            return 18;
        }
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
            case 4:
            case 5:
            case 6:
                return 4;
            default:
                switch (i) {
                    case 16777216:
                        return 9;
                    case 67108866:
                        return 10;
                    case 67108880:
                        return 11;
                    case 67109120:
                        return 12;
                    case 67109376:
                        return 15;
                    case 67109632:
                        return 16;
                    case AudioFormat.AC3 /* 150994944 */:
                        return 5;
                    case AudioFormat.DTS /* 184549376 */:
                        return 7;
                    case AudioFormat.DTS_HD /* 201326592 */:
                        return 8;
                    case AudioFormat.IEC61937 /* 218103808 */:
                        return 13;
                    case AudioFormat.DOLBY_TRUEHD /* 234881024 */:
                        return 14;
                    case AudioFormat.AC4 /* 570425344 */:
                        return 17;
                    default:
                        switch (i) {
                            case AudioFormat.MAT /* 603979776 */:
                            case 603979777:
                            case 603979778:
                            case 603979779:
                                return 19;
                            default:
                                return 0;
                        }
                }
        }
    }

    public static int aidl2apiRecognitionModes(int i) {
        int i2 = (i & 1) != 0 ? 1 : 0;
        if ((i & 2) != 0) {
            i2 |= 2;
        }
        if ((i & 4) != 0) {
            i2 |= 4;
        }
        return (i & 8) != 0 ? i2 | 8 : i2;
    }

    public static int api2aidlAudioCapabilities(int i) {
        int i2 = (i & 1) != 0 ? 1 : 0;
        return (i & 2) != 0 ? i2 | 2 : i2;
    }

    public static int api2aidlModelParameter(int i) {
        return i != 0 ? -1 : 0;
    }

    public static int api2aidlRecognitionModes(int i) {
        int i2 = (i & 1) != 0 ? 1 : 0;
        if ((i & 2) != 0) {
            i2 |= 2;
        }
        if ((i & 4) != 0) {
            i2 |= 4;
        }
        return (i & 8) != 0 ? i2 | 8 : i2;
    }

    ConversionUtil() {
    }

    public static SoundTrigger.ModuleProperties aidl2apiModuleDescriptor(SoundTriggerModuleDescriptor soundTriggerModuleDescriptor) {
        SoundTriggerModuleProperties soundTriggerModuleProperties = soundTriggerModuleDescriptor.properties;
        return new SoundTrigger.ModuleProperties(soundTriggerModuleDescriptor.handle, soundTriggerModuleProperties.implementor, soundTriggerModuleProperties.description, soundTriggerModuleProperties.uuid, soundTriggerModuleProperties.version, soundTriggerModuleProperties.supportedModelArch, soundTriggerModuleProperties.maxSoundModels, soundTriggerModuleProperties.maxKeyPhrases, soundTriggerModuleProperties.maxUsers, aidl2apiRecognitionModes(soundTriggerModuleProperties.recognitionModes), soundTriggerModuleProperties.captureTransition, soundTriggerModuleProperties.maxBufferMs, soundTriggerModuleProperties.concurrentCapture, soundTriggerModuleProperties.powerConsumptionMw, soundTriggerModuleProperties.triggerInEvent, aidl2apiAudioCapabilities(soundTriggerModuleProperties.audioCapabilities));
    }

    public static SoundModel api2aidlGenericSoundModel(SoundTrigger.GenericSoundModel genericSoundModel) {
        return api2aidlSoundModel(genericSoundModel);
    }

    public static SoundModel api2aidlSoundModel(SoundTrigger.SoundModel soundModel) {
        SoundModel soundModel2 = new SoundModel();
        soundModel2.type = soundModel.getType();
        soundModel2.uuid = api2aidlUuid(soundModel.getUuid());
        soundModel2.vendorUuid = api2aidlUuid(soundModel.getVendorUuid());
        soundModel2.data = byteArrayToSharedMemory(soundModel.getData(), "SoundTrigger SoundModel");
        soundModel2.dataSize = soundModel.getData().length;
        return soundModel2;
    }

    public static String api2aidlUuid(UUID uuid) {
        return uuid.toString();
    }

    public static PhraseSoundModel api2aidlPhraseSoundModel(SoundTrigger.KeyphraseSoundModel keyphraseSoundModel) {
        PhraseSoundModel phraseSoundModel = new PhraseSoundModel();
        phraseSoundModel.common = api2aidlSoundModel(keyphraseSoundModel);
        phraseSoundModel.phrases = new Phrase[keyphraseSoundModel.getKeyphrases().length];
        for (int i = 0; i < keyphraseSoundModel.getKeyphrases().length; i++) {
            phraseSoundModel.phrases[i] = api2aidlPhrase(keyphraseSoundModel.getKeyphrases()[i]);
        }
        return phraseSoundModel;
    }

    public static Phrase api2aidlPhrase(SoundTrigger.Keyphrase keyphrase) {
        Phrase phrase = new Phrase();
        phrase.id = keyphrase.getId();
        phrase.recognitionModes = api2aidlRecognitionModes(keyphrase.getRecognitionModes());
        phrase.users = Arrays.copyOf(keyphrase.getUsers(), keyphrase.getUsers().length);
        phrase.locale = keyphrase.getLocale().toLanguageTag();
        phrase.text = keyphrase.getText();
        return phrase;
    }

    public static RecognitionConfig api2aidlRecognitionConfig(SoundTrigger.RecognitionConfig recognitionConfig) {
        RecognitionConfig recognitionConfig2 = new RecognitionConfig();
        recognitionConfig2.captureRequested = recognitionConfig.captureRequested;
        recognitionConfig2.phraseRecognitionExtras = new PhraseRecognitionExtra[recognitionConfig.keyphrases.length];
        for (int i = 0; i < recognitionConfig.keyphrases.length; i++) {
            recognitionConfig2.phraseRecognitionExtras[i] = api2aidlPhraseRecognitionExtra(recognitionConfig.keyphrases[i]);
        }
        recognitionConfig2.data = Arrays.copyOf(recognitionConfig.data, recognitionConfig.data.length);
        recognitionConfig2.audioCapabilities = api2aidlAudioCapabilities(recognitionConfig.audioCapabilities);
        return recognitionConfig2;
    }

    public static PhraseRecognitionExtra api2aidlPhraseRecognitionExtra(SoundTrigger.KeyphraseRecognitionExtra keyphraseRecognitionExtra) {
        PhraseRecognitionExtra phraseRecognitionExtra = new PhraseRecognitionExtra();
        phraseRecognitionExtra.id = keyphraseRecognitionExtra.id;
        phraseRecognitionExtra.recognitionModes = api2aidlRecognitionModes(keyphraseRecognitionExtra.recognitionModes);
        phraseRecognitionExtra.confidenceLevel = keyphraseRecognitionExtra.coarseConfidenceLevel;
        phraseRecognitionExtra.levels = new ConfidenceLevel[keyphraseRecognitionExtra.confidenceLevels.length];
        for (int i = 0; i < keyphraseRecognitionExtra.confidenceLevels.length; i++) {
            phraseRecognitionExtra.levels[i] = api2aidlConfidenceLevel(keyphraseRecognitionExtra.confidenceLevels[i]);
        }
        return phraseRecognitionExtra;
    }

    public static SoundTrigger.KeyphraseRecognitionExtra aidl2apiPhraseRecognitionExtra(PhraseRecognitionExtra phraseRecognitionExtra) {
        SoundTrigger.ConfidenceLevel[] confidenceLevelArr = new SoundTrigger.ConfidenceLevel[phraseRecognitionExtra.levels.length];
        for (int i = 0; i < phraseRecognitionExtra.levels.length; i++) {
            confidenceLevelArr[i] = aidl2apiConfidenceLevel(phraseRecognitionExtra.levels[i]);
        }
        return new SoundTrigger.KeyphraseRecognitionExtra(phraseRecognitionExtra.id, aidl2apiRecognitionModes(phraseRecognitionExtra.recognitionModes), phraseRecognitionExtra.confidenceLevel, confidenceLevelArr);
    }

    public static ConfidenceLevel api2aidlConfidenceLevel(SoundTrigger.ConfidenceLevel confidenceLevel) {
        ConfidenceLevel confidenceLevel2 = new ConfidenceLevel();
        confidenceLevel2.levelPercent = confidenceLevel.confidenceLevel;
        confidenceLevel2.userId = confidenceLevel.userId;
        return confidenceLevel2;
    }

    public static SoundTrigger.ConfidenceLevel aidl2apiConfidenceLevel(ConfidenceLevel confidenceLevel) {
        return new SoundTrigger.ConfidenceLevel(confidenceLevel.userId, confidenceLevel.levelPercent);
    }

    public static SoundTrigger.RecognitionEvent aidl2apiRecognitionEvent(int i, RecognitionEvent recognitionEvent) {
        return new SoundTrigger.GenericRecognitionEvent(recognitionEvent.status, i, recognitionEvent.captureAvailable, recognitionEvent.captureSession, recognitionEvent.captureDelayMs, recognitionEvent.capturePreambleMs, recognitionEvent.triggerInData, aidl2apiAudioFormatWithDefault(recognitionEvent.audioConfig), recognitionEvent.data);
    }

    public static SoundTrigger.RecognitionEvent aidl2apiPhraseRecognitionEvent(int i, PhraseRecognitionEvent phraseRecognitionEvent) {
        SoundTrigger.KeyphraseRecognitionExtra[] keyphraseRecognitionExtraArr = new SoundTrigger.KeyphraseRecognitionExtra[phraseRecognitionEvent.phraseExtras.length];
        for (int i2 = 0; i2 < phraseRecognitionEvent.phraseExtras.length; i2++) {
            keyphraseRecognitionExtraArr[i2] = aidl2apiPhraseRecognitionExtra(phraseRecognitionEvent.phraseExtras[i2]);
        }
        return new SoundTrigger.KeyphraseRecognitionEvent(phraseRecognitionEvent.common.status, i, phraseRecognitionEvent.common.captureAvailable, phraseRecognitionEvent.common.captureSession, phraseRecognitionEvent.common.captureDelayMs, phraseRecognitionEvent.common.capturePreambleMs, phraseRecognitionEvent.common.triggerInData, aidl2apiAudioFormatWithDefault(phraseRecognitionEvent.common.audioConfig), phraseRecognitionEvent.common.data, keyphraseRecognitionExtraArr);
    }

    public static android.media.AudioFormat aidl2apiAudioFormat(AudioConfig audioConfig) {
        AudioFormat.Builder builder = new AudioFormat.Builder();
        builder.setSampleRate(audioConfig.sampleRateHz);
        builder.setChannelMask(aidl2apiChannelInMask(audioConfig.channelMask));
        builder.setEncoding(aidl2apiEncoding(audioConfig.format));
        return builder.build();
    }

    public static android.media.AudioFormat aidl2apiAudioFormatWithDefault(AudioConfig audioConfig) {
        if (audioConfig != null) {
            return aidl2apiAudioFormat(audioConfig);
        }
        return new AudioFormat.Builder().build();
    }

    public static SoundTrigger.ModelParamRange aidl2apiModelParameterRange(ModelParameterRange modelParameterRange) {
        if (modelParameterRange == null) {
            return null;
        }
        return new SoundTrigger.ModelParamRange(modelParameterRange.minInclusive, modelParameterRange.maxInclusive);
    }

    private static FileDescriptor byteArrayToSharedMemory(byte[] bArr, String str) {
        if (bArr.length == 0) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        try {
            SharedMemory sharedMemoryCreate = SharedMemory.create(str, bArr.length);
            ByteBuffer byteBufferMapReadWrite = sharedMemoryCreate.mapReadWrite();
            byteBufferMapReadWrite.put(bArr);
            SharedMemory.unmap(byteBufferMapReadWrite);
            return sharedMemoryCreate.getFileDescriptor();
        } catch (ErrnoException e) {
            throw new RuntimeException(e);
        }
    }
}
