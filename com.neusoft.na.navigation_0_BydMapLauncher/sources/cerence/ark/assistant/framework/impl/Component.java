package cerence.ark.assistant.framework.impl;

/* JADX INFO: loaded from: classes2.dex */
class Component {
    public static final boolean DEPENDENCY_TEXT_TO_SPEECH_SERVICE = findClassByClassName();

    private Component() {
    }

    private static boolean findClassByClassName() {
        try {
            Class.forName("cerence.ark.assistant.api.CerenceTextToSpeechService");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
