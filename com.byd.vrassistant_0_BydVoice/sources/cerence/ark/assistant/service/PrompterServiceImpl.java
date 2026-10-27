package cerence.ark.assistant.service;

import android.media.AudioAttributes;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.Framework;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.prompter.PlayState;
import cerence.ark.assistant.framework.prompter.PrompterCallback;
import cerence.ark.assistant.framework.prompter.PrompterError;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringAssembler;
import cerence.ark.assistant.service.IPrompterService;

/* JADX INFO: loaded from: classes.dex */
class PrompterServiceImpl extends IPrompterService.Stub {
    private static final String TAG = "PrompterServiceImpl";
    private final Framework mFramework;
    private IPrompter mPrompter;
    private String mPrompterName;
    private StringAssembler mStringAssembler;

    public PrompterServiceImpl(Framework framework) {
        this.mFramework = framework;
    }

    public boolean createPrompter(String str, AudioAttributes audioAttributes) {
        this.mPrompterName = str;
        if (this.mPrompter != null) {
            Logger.warn(TAG, "prompter already created");
            return true;
        }
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, "createPrompter: framework is null");
            return false;
        }
        IPrompter iPrompterCreatePrompter = framework.createPrompter(str, audioAttributes);
        if (iPrompterCreatePrompter == null) {
            return false;
        }
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

    public String getPrompterName() {
        return this.mPrompterName;
    }

    @Override // cerence.ark.assistant.service.IPrompterService
    public void play(String str, boolean z, boolean z2, boolean z3, String str2, String str3, final IPrompterCallback iPrompterCallback) {
        IPrompter iPrompter = this.mPrompter;
        if (iPrompter == null) {
            Logger.warn(TAG, "play: prompter is null");
            return;
        }
        if (z) {
            this.mStringAssembler = new StringAssembler();
        }
        if (z2) {
            String strAssemble = this.mStringAssembler.assemble(str);
            this.mStringAssembler = null;
            iPrompter.play(strAssemble, z3, str2, str3, new PrompterCallback() { // from class: cerence.ark.assistant.service.PrompterServiceImpl.1
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
            });
            return;
        }
        this.mStringAssembler.assemble(str);
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
