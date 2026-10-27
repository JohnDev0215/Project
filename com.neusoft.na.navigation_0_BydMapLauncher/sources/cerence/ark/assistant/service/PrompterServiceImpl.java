package cerence.ark.assistant.service;

import android.media.AudioAttributes;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.Framework;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.prompter.PlayState;
import cerence.ark.assistant.framework.prompter.PromptDataType;
import cerence.ark.assistant.framework.prompter.PrompterCallback;
import cerence.ark.assistant.framework.prompter.PrompterError;
import cerence.ark.assistant.framework.prompter.PrompterParameterType;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.IAssemblerListener;
import cerence.ark.assistant.service.IPrompterService;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
class PrompterServiceImpl extends IPrompterService.Stub {
    private static final String TAG = "PrompterServiceImpl";
    private final Framework mFramework;
    private IPrompter mPrompter;
    private PrompterInstance mPrompterInstance;

    public PrompterServiceImpl(Framework framework) {
        this.mFramework = framework;
    }

    public boolean createPrompter(PrompterInstance prompterInstance, AudioAttributes audioAttributes) {
        if (this.mPrompter != null) {
            Logger.warn(TAG, "prompter already created");
            return true;
        }
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, "createPrompter: framework is null");
            return false;
        }
        IPrompter iPrompterCreatePrompter = framework.createPrompter(prompterInstance, audioAttributes);
        if (iPrompterCreatePrompter == null) {
            return false;
        }
        this.mPrompterInstance = prompterInstance;
        this.mPrompter = iPrompterCreatePrompter;
        return true;
    }

    public void destroyPrompter() {
        IPrompter iPrompter = this.mPrompter;
        if (iPrompter == null) {
            return;
        }
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, "destroyPrompter: framework is null");
        } else {
            framework.destroyPrompter(iPrompter);
            this.mPrompter = null;
        }
    }

    public PrompterInstance getPrompterInstance() {
        return this.mPrompterInstance;
    }

    @Override // cerence.ark.assistant.service.IPrompterService
    public RemoteResult setPrompterParameter(PrompterParameterType prompterParameterType, int i) {
        IPrompter iPrompter = this.mPrompter;
        if (iPrompter == null) {
            Logger.warn(TAG, "setPrompterParameter: prompter is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iPrompter.setPrompterParameter(prompterParameterType, i));
    }

    @Override // cerence.ark.assistant.service.IPrompterService
    public RemoteResult prepareVoice(String str, String str2) {
        IPrompter iPrompter = this.mPrompter;
        if (iPrompter == null) {
            Logger.warn(TAG, "prepareVoice: prompter is null");
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(iPrompter.prepareVoice(str, str2));
    }

    @Override // cerence.ark.assistant.service.IPrompterService
    public int play(String str, String str2, int i, int i2, final PromptDataType promptDataType, final boolean z, final AudioPlaybackMode audioPlaybackMode, final String str3, final String str4, final IPrompterCallback iPrompterCallback) {
        final AtomicInteger atomicInteger = new AtomicInteger(-1);
        final PrompterCallback prompterCallback = new PrompterCallback() { // from class: cerence.ark.assistant.service.PrompterServiceImpl.1
            @Override // cerence.ark.assistant.framework.prompter.PrompterCallback
            public void onPromptCreated() {
                IPrompterCallback iPrompterCallback2 = iPrompterCallback;
                if (iPrompterCallback2 != null) {
                    try {
                        iPrompterCallback2.onPromptCreated();
                    } catch (Exception e) {
                        Logger.error(PrompterServiceImpl.TAG, "onPromptCreated: callback exception", e);
                    }
                }
            }

            @Override // cerence.ark.assistant.framework.prompter.PrompterCallback
            public void onPlayStateChanged(PlayState playState) {
                IPrompterCallback iPrompterCallback2 = iPrompterCallback;
                if (iPrompterCallback2 != null) {
                    try {
                        iPrompterCallback2.onPlayStateChanged(playState);
                    } catch (Exception e) {
                        Logger.error(PrompterServiceImpl.TAG, "play: callback onPlayStateChanged exception", e);
                    }
                }
            }

            @Override // cerence.ark.assistant.framework.prompter.PrompterCallback
            public void onError(PrompterError prompterError) {
                IPrompterCallback iPrompterCallback2 = iPrompterCallback;
                if (iPrompterCallback2 != null) {
                    try {
                        iPrompterCallback2.onError(prompterError);
                    } catch (Exception e) {
                        Logger.error(PrompterServiceImpl.TAG, "play: callback onError exception", e);
                    }
                }
            }

            @Override // cerence.ark.assistant.framework.prompter.PrompterCallback
            public void onPromptDestroyed() {
                IPrompterCallback iPrompterCallback2 = iPrompterCallback;
                if (iPrompterCallback2 != null) {
                    try {
                        iPrompterCallback2.onPromptDestroyed();
                    } catch (Exception e) {
                        Logger.error(PrompterServiceImpl.TAG, "onPromptDestroyed: callback exception", e);
                    }
                }
            }
        };
        Utils.processStringAssembler(str, str2, i, i2, new IAssemblerListener<String>() { // from class: cerence.ark.assistant.service.PrompterServiceImpl.2
            @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
            public void onFinal(String str5) {
                IPrompter iPrompter = PrompterServiceImpl.this.mPrompter;
                if (iPrompter == null) {
                    Logger.warn(PrompterServiceImpl.TAG, "play: prompter is null");
                } else {
                    atomicInteger.set(iPrompter.play(str5, promptDataType, z, audioPlaybackMode, str3, str4, prompterCallback));
                }
            }

            @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
            public void onError() {
                IPrompterCallback iPrompterCallback2 = iPrompterCallback;
                if (iPrompterCallback2 != null) {
                    try {
                        iPrompterCallback2.onError(PrompterError.OTHER_ERROR);
                    } catch (Exception e) {
                        Logger.error(PrompterServiceImpl.TAG, "play: callback onError exception", e);
                    }
                }
            }
        });
        return atomicInteger.get();
    }

    @Override // cerence.ark.assistant.service.IPrompterService
    public void pause() {
        IPrompter iPrompter = this.mPrompter;
        if (iPrompter == null) {
            Logger.warn(TAG, "pause: prompter is null");
        } else {
            iPrompter.pause();
        }
    }

    @Override // cerence.ark.assistant.service.IPrompterService
    public void resume() {
        IPrompter iPrompter = this.mPrompter;
        if (iPrompter == null) {
            Logger.warn(TAG, "resume: prompter is null");
        } else {
            iPrompter.resume();
        }
    }

    @Override // cerence.ark.assistant.service.IPrompterService
    public void stop() {
        IPrompter iPrompter = this.mPrompter;
        if (iPrompter == null) {
            Logger.warn(TAG, "stop: prompter is null");
        } else {
            iPrompter.stop();
        }
    }
}
