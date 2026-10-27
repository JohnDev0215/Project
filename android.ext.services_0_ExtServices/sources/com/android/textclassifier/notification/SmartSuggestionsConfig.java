package com.android.textclassifier.notification;

/* JADX INFO: loaded from: classes.dex */
public interface SmartSuggestionsConfig {
    int getMaxMessagesToExtract();

    int getMaxSuggestions();

    boolean shouldGenerateActions();

    boolean shouldGenerateReplies();
}
