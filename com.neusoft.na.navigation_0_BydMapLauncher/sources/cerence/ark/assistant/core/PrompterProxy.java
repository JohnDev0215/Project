package cerence.ark.assistant.core;

import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.prompter.PlayState;
import cerence.ark.assistant.framework.prompter.PromptDataType;
import cerence.ark.assistant.framework.prompter.PrompterCallback;
import cerence.ark.assistant.framework.prompter.PrompterError;
import cerence.ark.assistant.framework.prompter.PrompterParameterType;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringSplitter;
import cerence.ark.assistant.service.IPrompterCallback;
import cerence.ark.assistant.service.IPrompterService;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public final class PrompterProxy implements IPrompter {
    private static final String TAG = "PrompterProxy";
    private final PrompterInstance mPrompterInstance;
    private IPrompterService mPrompterService;

    public PrompterProxy(PrompterInstance prompterInstance, IPrompterService iPrompterService) {
        this.mPrompterInstance = prompterInstance;
        this.mPrompterService = iPrompterService;
    }

    public IPrompterService getPrompterService() {
        return this.mPrompterService;
    }

    public void release() {
        this.mPrompterService = null;
    }

    @Override // cerence.ark.assistant.framework.prompter.BasePrompter
    public PrompterInstance getPrompterInstance() {
        return this.mPrompterInstance;
    }

    @Override // cerence.ark.assistant.framework.prompter.BasePrompter
    public Result<Void, ErrorCode> setPrompterParameter(PrompterParameterType prompterParameterType, int i) {
        IPrompterService iPrompterService = this.mPrompterService;
        if (iPrompterService == null) {
            Logger.error(TAG, "setPrompterParameter: The prompter is destroyed");
            return Utils.buildErrorResult(ErrorCode.UNKNOWN);
        }
        Logger.info(TAG, Utils.format("setPrompterParameter: instance: %s, type: %s, value: %s", this.mPrompterInstance, prompterParameterType, Integer.valueOf(i)));
        try {
            return Utils.convertToResult(iPrompterService.setPrompterParameter(prompterParameterType, i));
        } catch (Exception e) {
            Logger.error(TAG, "setPrompterParameter: exception", e);
            return Utils.buildErrorResult(ErrorCode.UNKNOWN);
        }
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public Result<Void, ErrorCode> prepareVoice(String str, String str2) {
        IPrompterService iPrompterService = this.mPrompterService;
        if (iPrompterService == null) {
            Logger.error(TAG, "prepareVoice: The prompter is destroyed");
            return Utils.buildErrorResult(ErrorCode.UNKNOWN);
        }
        try {
            return Utils.convertToResult(iPrompterService.prepareVoice(str, str2));
        } catch (Exception e) {
            Logger.error(TAG, "prepareVoice: exception", e);
            return Utils.buildErrorResult(ErrorCode.UNKNOWN);
        }
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public int play(String str, boolean z, String str2, String str3, PrompterCallback prompterCallback) {
        return play(str, PromptDataType.TEXT, z, null, str2, str3, prompterCallback);
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public int play(String str, PromptDataType promptDataType, boolean z, String str2, PrompterCallback prompterCallback) {
        return play(str, promptDataType, z, null, str2, null, prompterCallback);
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public int play(String str, final PromptDataType promptDataType, final boolean z, final AudioPlaybackMode audioPlaybackMode, final String str2, final String str3, final PrompterCallback prompterCallback) {
        final AtomicInteger atomicInteger = new AtomicInteger(-1);
        final IPrompterService iPrompterService = this.mPrompterService;
        if (iPrompterService == null) {
            Logger.error(TAG, "play: The prompter is destroyed");
            return atomicInteger.get();
        }
        final IPrompterCallback.Stub stub = new IPrompterCallback.Stub() { // from class: cerence.ark.assistant.core.PrompterProxy.1
            @Override // cerence.ark.assistant.service.IPrompterCallback
            public void onPromptCreated() {
                PrompterCallback prompterCallback2 = prompterCallback;
                if (prompterCallback2 != null) {
                    prompterCallback2.onPromptCreated();
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterCallback
            public void onPlayStateChanged(PlayState playState) {
                PrompterCallback prompterCallback2 = prompterCallback;
                if (prompterCallback2 != null) {
                    prompterCallback2.onPlayStateChanged(playState);
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterCallback
            public void onError(PrompterError prompterError) {
                PrompterCallback prompterCallback2 = prompterCallback;
                if (prompterCallback2 != null) {
                    prompterCallback2.onError(prompterError);
                }
            }

            @Override // cerence.ark.assistant.service.IPrompterCallback
            public void onPromptDestroyed() {
                PrompterCallback prompterCallback2 = prompterCallback;
                if (prompterCallback2 != null) {
                    prompterCallback2.onPromptDestroyed();
                }
            }
        };
        new StringSplitter().split(str, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.core.PrompterProxy$$ExternalSyntheticLambda0
            @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
            public final void onSplit(Object obj, String str4, int i, int i2) {
                PrompterProxy.lambda$play$0(iPrompterService, promptDataType, z, audioPlaybackMode, str2, str3, stub, atomicInteger, (String) obj, str4, i, i2);
            }
        });
        return atomicInteger.get();
    }

    static /* synthetic */ void lambda$play$0(IPrompterService iPrompterService, PromptDataType promptDataType, boolean z, AudioPlaybackMode audioPlaybackMode, String str, String str2, IPrompterCallback.Stub stub, AtomicInteger atomicInteger, String str3, String str4, int i, int i2) {
        try {
            atomicInteger.set(iPrompterService.play(str3, str4, i, i2, promptDataType, z, audioPlaybackMode, str, str2, stub));
        } catch (Exception e) {
            Logger.error(TAG, "play: exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public void pause() {
        IPrompterService iPrompterService = this.mPrompterService;
        if (iPrompterService == null) {
            Logger.error(TAG, "pause: The prompter is destroyed");
            return;
        }
        try {
            iPrompterService.pause();
        } catch (Exception e) {
            Logger.error(TAG, "pause: exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public void resume() {
        IPrompterService iPrompterService = this.mPrompterService;
        if (iPrompterService == null) {
            Logger.error(TAG, "resume: The prompter is destroyed");
            return;
        }
        try {
            iPrompterService.resume();
        } catch (Exception e) {
            Logger.error(TAG, "resume: exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public void stop() {
        IPrompterService iPrompterService = this.mPrompterService;
        if (iPrompterService == null) {
            Logger.error(TAG, "stop: The prompter is destroyed");
            return;
        }
        try {
            iPrompterService.stop();
        } catch (Exception e) {
            Logger.error(TAG, "stop: exception", e);
        }
    }
}
