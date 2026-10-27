package com.android.textclassifier.notification;

import android.app.Notification;
import android.view.textclassifier.ConversationAction;
import android.view.textclassifier.TextClassificationContext;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextClassifierEvent;
import com.google.android.textclassifier.AnnotatorModel;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes.dex */
final class SmartSuggestionsLogSession {
    private boolean isSeenEventLogged;
    private final ImmutableMap<CharSequence, Float> repliesScores;
    private final String resultId;
    private final TextClassificationContext textClassificationContext;
    private final TextClassifier textClassifier;

    SmartSuggestionsLogSession(String str, Map<CharSequence, Float> map, TextClassifier textClassifier, TextClassificationContext textClassificationContext) {
        this.resultId = str;
        this.repliesScores = ImmutableMap.copyOf((Map) map);
        this.textClassifier = textClassifier;
        this.textClassificationContext = textClassificationContext;
    }

    void onNotificationExpansionChanged(boolean z) {
        if (z && !this.isSeenEventLogged) {
            this.isSeenEventLogged = true;
            this.textClassifier.onTextClassifierEvent(createTextClassifierEventBuilder(6, this.resultId).build());
        }
    }

    void onActionClicked(Notification.Action action, int i) {
        String string;
        if (i == 1 && (string = action.getExtras().getString("action_type")) != null) {
            this.textClassifier.onTextClassifierEvent(((TextClassifierEvent.ConversationActionsEvent.Builder) createTextClassifierEventBuilder(13, this.resultId).setEntityTypes(string)).build());
        }
    }

    void onSuggestedReplySent(CharSequence charSequence, int i) {
        if (i != 1) {
            return;
        }
        this.textClassifier.onTextClassifierEvent(((TextClassifierEvent.ConversationActionsEvent.Builder) ((TextClassifierEvent.ConversationActionsEvent.Builder) createTextClassifierEventBuilder(13, this.resultId).setEntityTypes("text_reply")).setScores(this.repliesScores.getOrDefault(charSequence, Float.valueOf(AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS)).floatValue())).build());
    }

    void onDirectReplied() {
        this.textClassifier.onTextClassifierEvent(createTextClassifierEventBuilder(19, this.resultId).build());
    }

    void onSuggestionsGenerated(List<ConversationAction> list) {
        this.textClassifier.onTextClassifierEvent(((TextClassifierEvent.ConversationActionsEvent.Builder) createTextClassifierEventBuilder(20, this.resultId).setEntityTypes((String[]) list.stream().map(new Function() { // from class: com.android.textclassifier.notification.-$$Lambda$GAGfzMbcczN3XjrMYlSN9rpKxHs
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ConversationAction) obj).getType();
            }
        }).toArray(new IntFunction() { // from class: com.android.textclassifier.notification.-$$Lambda$SmartSuggestionsLogSession$Nme9AGABTW6ttsuf-I1x1v3RdM8
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SmartSuggestionsLogSession.lambda$onSuggestionsGenerated$0(i);
            }
        }))).build());
    }

    static /* synthetic */ String[] lambda$onSuggestionsGenerated$0(int i) {
        return new String[i];
    }

    void destroy() {
        this.textClassifier.destroy();
    }

    private TextClassifierEvent.ConversationActionsEvent.Builder createTextClassifierEventBuilder(int i, String str) {
        return (TextClassifierEvent.ConversationActionsEvent.Builder) ((TextClassifierEvent.ConversationActionsEvent.Builder) new TextClassifierEvent.ConversationActionsEvent.Builder(i).setEventContext(this.textClassificationContext)).setResultId(str);
    }
}
