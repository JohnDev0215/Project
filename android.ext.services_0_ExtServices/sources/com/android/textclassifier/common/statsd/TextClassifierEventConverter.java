package com.android.textclassifier.common.statsd;

import android.view.textclassifier.TextClassifierEvent;
import com.android.textclassifier.common.base.TcLog;
import com.android.textclassifier.common.logging.TextClassificationContext;
import com.android.textclassifier.common.logging.TextClassifierEvent;

/* JADX INFO: loaded from: classes.dex */
public final class TextClassifierEventConverter {
    public static TextClassifierEvent fromPlatform(android.view.textclassifier.TextClassifierEvent textClassifierEvent) {
        if (textClassifierEvent == null) {
            return null;
        }
        if (textClassifierEvent instanceof TextClassifierEvent.TextSelectionEvent) {
            return fromPlatform((TextClassifierEvent.TextSelectionEvent) textClassifierEvent);
        }
        if (textClassifierEvent instanceof TextClassifierEvent.TextLinkifyEvent) {
            return fromPlatform((TextClassifierEvent.TextLinkifyEvent) textClassifierEvent);
        }
        if (textClassifierEvent instanceof TextClassifierEvent.ConversationActionsEvent) {
            return fromPlatform((TextClassifierEvent.ConversationActionsEvent) textClassifierEvent);
        }
        if (textClassifierEvent instanceof TextClassifierEvent.LanguageDetectionEvent) {
            return fromPlatform((TextClassifierEvent.LanguageDetectionEvent) textClassifierEvent);
        }
        TcLog.w("TextClassifierEventConv", "Unexpected event: " + textClassifierEvent);
        return null;
    }

    private static TextClassifierEvent.TextSelectionEvent fromPlatform(TextClassifierEvent.TextSelectionEvent textSelectionEvent) {
        TextClassifierEvent.TextSelectionEvent.Builder builder = new TextClassifierEvent.TextSelectionEvent.Builder(textSelectionEvent.getEventType());
        copyCommonFields(textSelectionEvent, builder);
        builder.setRelativeWordStartIndex(textSelectionEvent.getRelativeWordStartIndex());
        builder.setRelativeWordEndIndex(textSelectionEvent.getRelativeWordEndIndex());
        builder.setRelativeSuggestedWordStartIndex(textSelectionEvent.getRelativeSuggestedWordStartIndex());
        builder.setRelativeSuggestedWordEndIndex(textSelectionEvent.getRelativeSuggestedWordEndIndex());
        return builder.build();
    }

    private static TextClassifierEvent.TextLinkifyEvent fromPlatform(TextClassifierEvent.TextLinkifyEvent textLinkifyEvent) {
        TextClassifierEvent.TextLinkifyEvent.Builder builder = new TextClassifierEvent.TextLinkifyEvent.Builder(textLinkifyEvent.getEventType());
        copyCommonFields(textLinkifyEvent, builder);
        return builder.build();
    }

    private static TextClassifierEvent.ConversationActionsEvent fromPlatform(TextClassifierEvent.ConversationActionsEvent conversationActionsEvent) {
        TextClassifierEvent.ConversationActionsEvent.Builder builder = new TextClassifierEvent.ConversationActionsEvent.Builder(conversationActionsEvent.getEventType());
        copyCommonFields(conversationActionsEvent, builder);
        return builder.build();
    }

    private static TextClassifierEvent.LanguageDetectionEvent fromPlatform(TextClassifierEvent.LanguageDetectionEvent languageDetectionEvent) {
        TextClassifierEvent.LanguageDetectionEvent.Builder builder = new TextClassifierEvent.LanguageDetectionEvent.Builder(languageDetectionEvent.getEventType());
        copyCommonFields(languageDetectionEvent, builder);
        return builder.build();
    }

    private static TextClassificationContext fromPlatform(android.view.textclassifier.TextClassificationContext textClassificationContext) {
        if (textClassificationContext == null) {
            return null;
        }
        TextClassificationContext.Builder builder = new TextClassificationContext.Builder(textClassificationContext.getPackageName(), textClassificationContext.getWidgetType());
        builder.setWidgetVersion(textClassificationContext.getWidgetVersion());
        return builder.build();
    }

    private static void copyCommonFields(android.view.textclassifier.TextClassifierEvent textClassifierEvent, TextClassifierEvent.Builder<?> builder) {
        builder.setActionIndices(textClassifierEvent.getActionIndices()).setEventContext(fromPlatform(textClassifierEvent.getEventContext())).setEntityTypes(textClassifierEvent.getEntityTypes()).setEventIndex(textClassifierEvent.getEventIndex()).setExtras(textClassifierEvent.getExtras()).setLocale(textClassifierEvent.getLocale() == null ? null : textClassifierEvent.getLocale().toLocale()).setModelName(textClassifierEvent.getModelName()).setResultId(textClassifierEvent.getResultId()).setScores(textClassifierEvent.getScores());
    }
}
