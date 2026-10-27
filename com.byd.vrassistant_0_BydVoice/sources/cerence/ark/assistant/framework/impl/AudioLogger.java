package cerence.ark.assistant.framework.impl;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import cerence.ark.assistant.common.LogFileLimiter;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.dm.data.SpeechAudioType;
import cerence.ark.assistant.framework.AudioChunkPool;
import cerence.ark.assistant.library.Utils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class AudioLogger {
    private static final int MAX_MIC_AUDIO_SIZE_PER_FILE = 10485760;
    private static final int MIC_MAX_CHUNK_COUNT = 10;
    private static final int PROMPTER_MAX_CHUNK_COUNT = 50;
    private static final String REGEX_AUDIO_FILE = ".*\\.pcm";
    private static final String TAG = "AudioLogger";
    private AudioLogConfig mAudioLogConfig;
    private FileOutput mDialogPrompterFileOutput;
    private Handler mHandler;
    private LogFileLimiter mLogFileLimiter;
    private AudioChunkPool mMicChunkPool;
    private FileOutput mMicFileOutput;
    private AudioChunkPool mPrompterChunkPool;
    private FileOutput mSiriFileOutput;
    private FileOutput mSpeechFileOutput;
    private final SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS'.pcm'", Locale.US);
    private final HashMap<String, FileOutput> mPrompterFileOutputMap = new HashMap<>(16);

    public void initialize(AudioLogConfig audioLogConfig, int i, int i2) {
        if (audioLogConfig == null) {
            return;
        }
        this.mAudioLogConfig = audioLogConfig;
        if (audioLogConfig.isSaveInputAudio() || this.mAudioLogConfig.isSaveOutputAudio()) {
            HandlerThread handlerThread = new HandlerThread("audio_logger");
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper());
            final LogFileLimiter logFileLimiter = new LogFileLimiter(this.mAudioLogConfig.getAudioDir(), Pattern.compile(REGEX_AUDIO_FILE), this.mAudioLogConfig.getMaximumSize());
            this.mLogFileLimiter = logFileLimiter;
            Handler handler = this.mHandler;
            Objects.requireNonNull(logFileLimiter);
            handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    logFileLimiter.scanLogFiles();
                }
            });
        }
        if (this.mAudioLogConfig.isSaveInputAudio()) {
            this.mMicChunkPool = new AudioChunkPool(i, 10);
        }
        if (this.mAudioLogConfig.isSaveOutputAudio()) {
            this.mPrompterChunkPool = new AudioChunkPool(i2, 50);
        }
    }

    public void saveMicAudio(byte[] bArr, final String str) {
        AudioLogConfig audioLogConfig;
        Handler handler = this.mHandler;
        if (handler == null || (audioLogConfig = this.mAudioLogConfig) == null || !audioLogConfig.isSaveInputAudio()) {
            return;
        }
        final byte[] audioChunk = this.mMicChunkPool.getAudioChunk(bArr);
        handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m53x3ad7d94b(str, audioChunk);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$saveMicAudio$0$cerence-ark-assistant-framework-impl-AudioLogger, reason: not valid java name */
    /* synthetic */ void m53x3ad7d94b(String str, byte[] bArr) {
        try {
            try {
                FileOutput fileOutput = this.mMicFileOutput;
                if (fileOutput == null) {
                    File fileCreateMicAudioFile = createMicAudioFile(str);
                    if (fileCreateMicAudioFile != null) {
                        this.mMicFileOutput = new FileOutput(fileCreateMicAudioFile);
                        this.mMicFileOutput.write(bArr);
                    }
                    return;
                }
                if (fileOutput.getFile().exists() && this.mMicFileOutput.getFile().length() >= 10485760) {
                    closeMicFileStreamImpl();
                    File fileCreateMicAudioFile2 = createMicAudioFile(str);
                    if (fileCreateMicAudioFile2 == null) {
                        return;
                    } else {
                        this.mMicFileOutput = new FileOutput(fileCreateMicAudioFile2);
                    }
                }
                this.mMicFileOutput.write(bArr);
            } catch (Exception e) {
                Logger.error(TAG, "saveMicAudio: exception", e);
            }
        } finally {
            this.mMicChunkPool.returnAudioChunk(bArr);
        }
    }

    public void saveSpeechAudio(final SpeechAudioType speechAudioType, final byte[] bArr) {
        AudioLogConfig audioLogConfig;
        Handler handler = this.mHandler;
        if (handler == null || (audioLogConfig = this.mAudioLogConfig) == null || !audioLogConfig.isSaveInputAudio()) {
            return;
        }
        handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m55x31d5dc79(speechAudioType, bArr);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$saveSpeechAudio$1$cerence-ark-assistant-framework-impl-AudioLogger, reason: not valid java name */
    /* synthetic */ void m55x31d5dc79(SpeechAudioType speechAudioType, byte[] bArr) {
        try {
            try {
                if (this.mSpeechFileOutput == null) {
                    File fileCreateSpeechAudioFile = createSpeechAudioFile(speechAudioType.name().toLowerCase());
                    if (fileCreateSpeechAudioFile != null) {
                        this.mSpeechFileOutput = new FileOutput(fileCreateSpeechAudioFile);
                    } else {
                        if (bArr.length == 0) {
                            closeSpeechFileStreamImpl();
                            return;
                        }
                        return;
                    }
                }
                this.mSpeechFileOutput.write(bArr);
                if (bArr.length != 0) {
                    return;
                }
            } catch (Exception e) {
                Logger.error(TAG, "saveSpeechAudio: exception", e);
                if (bArr.length != 0) {
                    return;
                }
            }
            closeSpeechFileStreamImpl();
        } catch (Throwable th) {
            if (bArr.length == 0) {
                closeSpeechFileStreamImpl();
            }
            throw th;
        }
    }

    public void saveEnhancedSiriAudio(byte[] bArr, final String str) {
        AudioLogConfig audioLogConfig;
        Handler handler = this.mHandler;
        if (handler == null || (audioLogConfig = this.mAudioLogConfig) == null || !audioLogConfig.isSaveInputAudio()) {
            return;
        }
        int length = bArr.length;
        final byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m52x533572d9(str, bArr2);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$saveEnhancedSiriAudio$2$cerence-ark-assistant-framework-impl-AudioLogger, reason: not valid java name */
    /* synthetic */ void m52x533572d9(String str, byte[] bArr) {
        try {
            FileOutput fileOutput = this.mSiriFileOutput;
            if (fileOutput == null) {
                File fileCreateEnhancedSiriAudioFile = createEnhancedSiriAudioFile(str);
                if (fileCreateEnhancedSiriAudioFile == null) {
                    return;
                } else {
                    this.mSiriFileOutput = new FileOutput(fileCreateEnhancedSiriAudioFile);
                }
            } else if (fileOutput.getFile().exists() && this.mSiriFileOutput.getFile().length() >= 10485760) {
                closeSiriFileStreamImpl();
                File fileCreateEnhancedSiriAudioFile2 = createEnhancedSiriAudioFile(str);
                if (fileCreateEnhancedSiriAudioFile2 == null) {
                    return;
                } else {
                    this.mSiriFileOutput = new FileOutput(fileCreateEnhancedSiriAudioFile2);
                }
            }
            this.mSiriFileOutput.write(bArr);
        } catch (Exception e) {
            Logger.error(TAG, "saveEnhanceSiriAudio: exception", e);
        }
    }

    public void saveDialogPrompterAudio(byte[] bArr, final String str, final int i) {
        AudioLogConfig audioLogConfig;
        Handler handler = this.mHandler;
        if (handler == null || (audioLogConfig = this.mAudioLogConfig) == null || !audioLogConfig.isSaveOutputAudio()) {
            return;
        }
        final byte[] audioChunk = this.mPrompterChunkPool.getAudioChunk(bArr);
        handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m51x80ac2b4e(str, i, audioChunk);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$saveDialogPrompterAudio$3$cerence-ark-assistant-framework-impl-AudioLogger, reason: not valid java name */
    /* synthetic */ void m51x80ac2b4e(String str, int i, byte[] bArr) {
        try {
            try {
                if (this.mDialogPrompterFileOutput == null) {
                    File fileCreateDialogPrompterAudioFile = createDialogPrompterAudioFile(str, i);
                    if (fileCreateDialogPrompterAudioFile == null) {
                        return;
                    } else {
                        this.mDialogPrompterFileOutput = new FileOutput(fileCreateDialogPrompterAudioFile);
                    }
                }
                this.mDialogPrompterFileOutput.write(bArr);
            } catch (Exception e) {
                Logger.error(TAG, "saveDialogPrompterAudio: exception", e);
            }
        } finally {
            this.mPrompterChunkPool.returnAudioChunk(bArr);
        }
    }

    public void savePrompterAudio(byte[] bArr, final String str, final int i) {
        AudioLogConfig audioLogConfig;
        Handler handler = this.mHandler;
        if (handler == null || (audioLogConfig = this.mAudioLogConfig) == null || !audioLogConfig.isSaveOutputAudio()) {
            return;
        }
        final byte[] audioChunk = this.mPrompterChunkPool.getAudioChunk(bArr);
        handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m54xdcd0bb85(str, i, audioChunk);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$savePrompterAudio$4$cerence-ark-assistant-framework-impl-AudioLogger, reason: not valid java name */
    /* synthetic */ void m54xdcd0bb85(String str, int i, byte[] bArr) {
        try {
            try {
                FileOutput fileOutput = this.mPrompterFileOutputMap.get(str);
                if (fileOutput == null) {
                    File fileCreatePrompterAudioFile = createPrompterAudioFile(str, i);
                    if (fileCreatePrompterAudioFile == null) {
                        return;
                    }
                    fileOutput = new FileOutput(fileCreatePrompterAudioFile);
                    this.mPrompterFileOutputMap.put(str, fileOutput);
                }
                fileOutput.write(bArr);
            } catch (Exception e) {
                Logger.error(TAG, "savePrompterAudio: exception", e);
            }
        } finally {
            this.mPrompterChunkPool.returnAudioChunk(bArr);
        }
    }

    public void closeMicFileStream() {
        Handler handler = this.mHandler;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.closeMicFileStreamImpl();
            }
        });
    }

    public void closeSiriFileStream() {
        Handler handler = this.mHandler;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.closeSiriFileStreamImpl();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeMicFileStreamImpl() {
        FileOutput fileOutput = this.mMicFileOutput;
        if (fileOutput != null) {
            try {
                fileOutput.close();
                this.mLogFileLimiter.addNewFile(this.mMicFileOutput.getFile());
            } catch (IOException e) {
                Logger.error(TAG, "closeMicFileStreamImpl: exception", e);
            }
        }
        this.mMicFileOutput = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeSiriFileStreamImpl() {
        FileOutput fileOutput = this.mSiriFileOutput;
        if (fileOutput != null) {
            try {
                fileOutput.close();
                this.mLogFileLimiter.addNewFile(this.mSiriFileOutput.getFile());
            } catch (IOException e) {
                Logger.error(TAG, "closeSiriFileStreamImpl: exception", e);
            }
        }
        this.mSiriFileOutput = null;
    }

    public void closeSpeechFileStream() {
        Handler handler = this.mHandler;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.closeSpeechFileStreamImpl();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeSpeechFileStreamImpl() {
        FileOutput fileOutput = this.mSpeechFileOutput;
        if (fileOutput != null) {
            try {
                fileOutput.close();
                this.mLogFileLimiter.addNewFile(this.mSpeechFileOutput.getFile());
            } catch (IOException e) {
                Logger.error(TAG, "closeSpeechFileStreamImpl: exception", e);
            }
        }
        this.mSpeechFileOutput = null;
    }

    public void closeDialogPrompterFileStream() {
        Handler handler = this.mHandler;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.closeDialogPrompterFileStreamImpl();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeDialogPrompterFileStreamImpl() {
        FileOutput fileOutput = this.mDialogPrompterFileOutput;
        if (fileOutput != null) {
            try {
                fileOutput.close();
                this.mLogFileLimiter.addNewFile(this.mDialogPrompterFileOutput.getFile());
            } catch (IOException e) {
                Logger.error(TAG, "closeDialogPrompterFileStreamImpl: exception", e);
            }
        }
        this.mDialogPrompterFileOutput = null;
    }

    public void closePrompterFileStream(final String str) {
        Handler handler = this.mHandler;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m49x5e8e5f67(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: closePrompterFileStreamImpl, reason: merged with bridge method [inline-methods] */
    public void m49x5e8e5f67(String str) {
        FileOutput fileOutputRemove = this.mPrompterFileOutputMap.remove(str);
        if (fileOutputRemove != null) {
            try {
                fileOutputRemove.close();
                this.mLogFileLimiter.addNewFile(fileOutputRemove.getFile());
            } catch (IOException e) {
                Logger.error(TAG, "closePrompterFileStreamImpl: exception", e);
            }
        }
    }

    private File createMicAudioFile(String str) {
        Object[] objArr = new Object[3];
        objArr[0] = this.mAudioLogConfig.getAudioDir();
        if (TextUtils.isEmpty(str)) {
            str = "mic";
        }
        objArr[1] = str;
        objArr[2] = this.mDateFormat.format(new Date());
        File file = new File(String.format("%s/%s/dialog_%s", objArr));
        if (!Utils.createOrExistsFile(file)) {
            Logger.error(TAG, "create mic file failed");
            return null;
        }
        Logger.debug(TAG, String.format("mic audio new file: %s", file.getAbsolutePath()));
        return file;
    }

    private File createSpeechAudioFile(String str) {
        File file = new File(String.format("%s/%s/%s_%s", this.mAudioLogConfig.getAudioDir(), str, str, this.mDateFormat.format(new Date())));
        if (Utils.createOrExistsFile(file)) {
            return file;
        }
        Logger.error(TAG, "create speech audio file failed");
        return null;
    }

    private File createEnhancedSiriAudioFile(String str) {
        File file = new File(String.format("%s/%s/siri_%s", this.mAudioLogConfig.getAudioDir(), str, this.mDateFormat.format(new Date())));
        if (!Utils.createOrExistsFile(file)) {
            Logger.error(TAG, "create enhance siri file failed");
            return null;
        }
        Logger.debug(TAG, String.format("enhance siri audio new file: %s", file.getAbsolutePath()));
        return file;
    }

    private File createDialogPrompterAudioFile(String str, int i) {
        Object[] objArr = new Object[4];
        objArr[0] = this.mAudioLogConfig.getAudioDir();
        if (TextUtils.isEmpty(str)) {
            str = "dialog_prompter";
        }
        objArr[1] = str;
        objArr[2] = Integer.valueOf(i);
        objArr[3] = this.mDateFormat.format(new Date());
        File file = new File(String.format("%s/%s/dialog_prompter_%s_%s", objArr));
        if (!Utils.createOrExistsFile(file)) {
            Logger.error(TAG, "create dialog prompter file failed");
            return null;
        }
        Logger.debug(TAG, String.format("dialog prompter audio new file: %s", file.getAbsolutePath()));
        return file;
    }

    private File createPrompterAudioFile(String str, int i) {
        Object[] objArr = new Object[4];
        objArr[0] = this.mAudioLogConfig.getAudioDir();
        if (TextUtils.isEmpty(str)) {
            str = "prompter";
        }
        objArr[1] = str;
        objArr[2] = Integer.valueOf(i);
        objArr[3] = this.mDateFormat.format(new Date());
        File file = new File(String.format("%s/%s/prompter_%s_%s", objArr));
        if (!Utils.createOrExistsFile(file)) {
            Logger.error(TAG, "create prompter file failed");
            return null;
        }
        Logger.debug(TAG, String.format("prompter audio new file: %s", file.getAbsolutePath()));
        return file;
    }

    public void destroy() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.AudioLogger$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m50xd7b75991();
                }
            });
            handler.getLooper().quitSafely();
            this.mHandler = null;
        }
    }

    /* JADX INFO: renamed from: lambda$destroy$6$cerence-ark-assistant-framework-impl-AudioLogger, reason: not valid java name */
    /* synthetic */ void m50xd7b75991() {
        closeMicFileStreamImpl();
        closeDialogPrompterFileStreamImpl();
        closeSpeechFileStreamImpl();
        closeSiriFileStreamImpl();
        Iterator<String> it = this.mPrompterFileOutputMap.keySet().iterator();
        while (it.hasNext()) {
            m49x5e8e5f67(it.next());
        }
    }

    private static class FileOutput {
        private final File mFile;
        private final OutputStream mOutputStream;

        FileOutput(File file) throws FileNotFoundException {
            this.mFile = file;
            this.mOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        }

        File getFile() {
            return this.mFile;
        }

        void write(byte[] bArr) throws IOException {
            this.mOutputStream.write(bArr, 0, bArr.length);
        }

        void close() throws IOException {
            this.mOutputStream.close();
        }
    }
}
