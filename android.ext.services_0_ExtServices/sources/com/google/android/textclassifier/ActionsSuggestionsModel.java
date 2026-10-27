package com.google.android.textclassifier;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class ActionsSuggestionsModel implements AutoCloseable {
    private long actionsModelPtr;
    private final AtomicBoolean isClosed;

    public static final class ActionSuggestionOptions {
    }

    private native void nativeCloseActionsModel(long j);

    private static native String nativeGetLocales(int i);

    private static native String nativeGetLocalesWithOffset(int i, long j, long j2);

    private static native String nativeGetName(int i);

    private static native String nativeGetNameWithOffset(int i, long j, long j2);

    private static native int nativeGetVersion(int i);

    private static native int nativeGetVersionWithOffset(int i, long j, long j2);

    private static native long nativeNewActionsModel(int i, byte[] bArr);

    private static native long nativeNewActionsModelFromPath(String str, byte[] bArr);

    private static native long nativeNewActionsModelWithOffset(int i, long j, long j2, byte[] bArr);

    private native ActionSuggestion[] nativeSuggestActions(long j, Conversation conversation, ActionSuggestionOptions actionSuggestionOptions, long j2, Object obj, String str, boolean z);

    static {
        System.loadLibrary("textclassifier");
    }

    public ActionsSuggestionsModel(int i, byte[] bArr) {
        this.isClosed = new AtomicBoolean(false);
        long jNativeNewActionsModel = nativeNewActionsModel(i, bArr);
        this.actionsModelPtr = jNativeNewActionsModel;
        if (jNativeNewActionsModel == 0) {
            throw new IllegalArgumentException("Couldn't initialize actions model from file descriptor.");
        }
    }

    public ActionsSuggestionsModel(int i) {
        this(i, (byte[]) null);
    }

    public ActionsSuggestionsModel(String str, byte[] bArr) {
        this.isClosed = new AtomicBoolean(false);
        long jNativeNewActionsModelFromPath = nativeNewActionsModelFromPath(str, bArr);
        this.actionsModelPtr = jNativeNewActionsModelFromPath;
        if (jNativeNewActionsModelFromPath == 0) {
            throw new IllegalArgumentException("Couldn't initialize actions model from given file.");
        }
    }

    public ActionsSuggestionsModel(String str) {
        this(str, (byte[]) null);
    }

    public ActionSuggestion[] suggestActions(Conversation conversation, ActionSuggestionOptions actionSuggestionOptions, AnnotatorModel annotatorModel) {
        return nativeSuggestActions(this.actionsModelPtr, conversation, actionSuggestionOptions, annotatorModel != null ? annotatorModel.getNativeAnnotatorPointer() : 0L, null, null, false);
    }

    public ActionSuggestion[] suggestActionsWithIntents(Conversation conversation, ActionSuggestionOptions actionSuggestionOptions, Object obj, String str, AnnotatorModel annotatorModel) {
        return nativeSuggestActions(this.actionsModelPtr, conversation, actionSuggestionOptions, annotatorModel != null ? annotatorModel.getNativeAnnotatorPointer() : 0L, obj, str, true);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.isClosed.compareAndSet(false, true)) {
            nativeCloseActionsModel(this.actionsModelPtr);
            this.actionsModelPtr = 0L;
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public static String getLocales(int i) {
        return nativeGetLocales(i);
    }

    public static int getVersion(int i) {
        return nativeGetVersion(i);
    }

    public static String getName(int i) {
        return nativeGetName(i);
    }

    public static final class ActionSuggestion {
        private final String actionType;
        private final NamedVariant[] entityData;
        private final RemoteActionTemplate[] remoteActionTemplates;
        private final String responseText;
        private final float score;
        private final byte[] serializedEntityData;

        public ActionSuggestion(String str, String str2, float f, NamedVariant[] namedVariantArr, byte[] bArr, RemoteActionTemplate[] remoteActionTemplateArr) {
            this.responseText = str;
            this.actionType = str2;
            this.score = f;
            this.entityData = namedVariantArr;
            this.serializedEntityData = bArr;
            this.remoteActionTemplates = remoteActionTemplateArr;
        }

        public String getResponseText() {
            return this.responseText;
        }

        public String getActionType() {
            return this.actionType;
        }

        public float getScore() {
            return this.score;
        }

        public NamedVariant[] getEntityData() {
            return this.entityData;
        }

        public byte[] getSerializedEntityData() {
            return this.serializedEntityData;
        }

        public RemoteActionTemplate[] getRemoteActionTemplates() {
            return this.remoteActionTemplates;
        }
    }

    public static final class ConversationMessage {
        private final String detectedTextLanguageTags;
        private final long referenceTimeMsUtc;
        private final String referenceTimezone;
        private final String text;
        private final int userId;

        public ConversationMessage(int i, String str, long j, String str2, String str3) {
            this.userId = i;
            this.text = str;
            this.referenceTimeMsUtc = j;
            this.referenceTimezone = str2;
            this.detectedTextLanguageTags = str3;
        }

        public int getUserId() {
            return this.userId;
        }

        public String getText() {
            return this.text;
        }

        public long getReferenceTimeMsUtc() {
            return this.referenceTimeMsUtc;
        }

        public String getReferenceTimezone() {
            return this.referenceTimezone;
        }

        public String getDetectedTextLanguageTags() {
            return this.detectedTextLanguageTags;
        }
    }

    public static final class Conversation {
        public final ConversationMessage[] conversationMessages;

        public Conversation(ConversationMessage[] conversationMessageArr) {
            this.conversationMessages = conversationMessageArr;
        }

        public ConversationMessage[] getConversationMessages() {
            return this.conversationMessages;
        }
    }
}
