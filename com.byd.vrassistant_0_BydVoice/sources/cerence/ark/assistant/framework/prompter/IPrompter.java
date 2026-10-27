package cerence.ark.assistant.framework.prompter;

/* JADX INFO: loaded from: classes.dex */
public interface IPrompter {
    void pause();

    void play(String str, boolean z, PrompterCallback prompterCallback);

    void play(String str, boolean z, String str2, String str3, PrompterCallback prompterCallback);

    void resume();

    void stop();
}
