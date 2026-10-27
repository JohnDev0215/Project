package cerence.ark.assistant.core;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.prompter.PlayState;
import cerence.ark.assistant.framework.prompter.PrompterCallback;
import cerence.ark.assistant.framework.prompter.PrompterError;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringSplitter;
import cerence.ark.assistant.service.IPrompterCallback;
import cerence.ark.assistant.service.IPrompterService;

/* JADX INFO: loaded from: classes.dex */
public final class PrompterProxy implements IPrompter {
    private static final String TAG = "PrompterProxy";
    private final String mPrompterName;
    private IPrompterService mPrompterService;

    public PrompterProxy(String str, IPrompterService iPrompterService) {
        this.mPrompterName = str;
        this.mPrompterService = iPrompterService;
    }

    public String getPrompterName() {
        return this.mPrompterName;
    }

    public IPrompterService getPrompterService() {
        return this.mPrompterService;
    }

    public void release() {
        this.mPrompterService = null;
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public void play(String str, boolean z, PrompterCallback prompterCallback) {
        play(str, z, null, null, prompterCallback);
    }

    @Override // cerence.ark.assistant.framework.prompter.IPrompter
    public void play(String str, final boolean z, final String str2, final String str3, final PrompterCallback prompterCallback) {
        final IPrompterService iPrompterService = this.mPrompterService;
        if (iPrompterService == null) {
            Logger.error(TAG, "play: The prompter is destroyed");
        } else {
            new StringSplitter().split(str, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.core.PrompterProxy$$ExternalSyntheticLambda0
                @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                public final void onSplit(Object obj, boolean z2, boolean z3) {
                    this.f$0.m43lambda$play$0$cerencearkassistantcorePrompterProxy(iPrompterService, z, str2, str3, prompterCallback, (String) obj, z2, z3);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$play$0$cerence-ark-assistant-core-PrompterProxy, reason: not valid java name */
    /* synthetic */ void m43lambda$play$0$cerencearkassistantcorePrompterProxy(IPrompterService iPrompterService, boolean z, String str, String str2, final PrompterCallback prompterCallback, String str3, boolean z2, boolean z3) {
        try {
            try {
                iPrompterService.play(str3, z2, z3, z, str, str2, new IPrompterCallback.Stub() { // from class: cerence.ark.assistant.core.PrompterProxy.1
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
                });
            } catch (Exception e) {
                e = e;
                Logger.error(TAG, "play: exception", e);
            }
        } catch (Exception e2) {
            e = e2;
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
