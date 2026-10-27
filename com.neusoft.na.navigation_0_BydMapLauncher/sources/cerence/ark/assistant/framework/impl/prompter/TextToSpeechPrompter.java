package cerence.ark.assistant.framework.impl.prompter;

import android.media.AudioAttributes;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.dm.data.DmInitParams;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;

/* JADX INFO: loaded from: classes2.dex */
public final class TextToSpeechPrompter extends PrompterImpl {
    @Override // cerence.ark.assistant.framework.impl.prompter.PrompterImpl
    protected boolean needInitializeAudioPlayer(AudioPlaybackMode audioPlaybackMode) {
        return false;
    }

    public TextToSpeechPrompter(PrompterInstance prompterInstance, AudioAttributes audioAttributes, AudioPlaybackMode audioPlaybackMode, NativeDialogManager nativeDialogManager) {
        super(prompterInstance, audioAttributes, audioPlaybackMode, nativeDialogManager);
    }

    @Override // cerence.ark.assistant.framework.impl.prompter.PrompterImpl
    protected DmInitParams.AudioPlaybackMode getDmPlaybackMode(AudioPlaybackMode audioPlaybackMode) {
        return DmInitParams.AudioPlaybackMode.APP;
    }
}
