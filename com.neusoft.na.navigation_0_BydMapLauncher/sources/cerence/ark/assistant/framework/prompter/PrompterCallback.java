package cerence.ark.assistant.framework.prompter;

/* JADX INFO: loaded from: classes2.dex */
public interface PrompterCallback {
    void onError(PrompterError prompterError);

    void onPlayStateChanged(PlayState playState);

    void onPromptCreated();

    void onPromptDestroyed();
}
