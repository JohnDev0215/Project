package com.android.textclassifier.common.statsd;

import android.util.StatsEvent;
import android.util.StatsLog;
import com.android.textclassifier.common.base.TcLog;
import com.android.textclassifier.common.logging.ResultIdUtils;
import com.android.textclassifier.common.logging.TextClassificationContext;
import com.android.textclassifier.common.logging.TextClassificationSessionId;
import com.android.textclassifier.common.logging.TextClassifierEvent;
import com.google.android.textclassifier.AnnotatorModel;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Hashing;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class TextClassifierEventLogger {
    public void writeEvent(TextClassificationSessionId textClassificationSessionId, TextClassifierEvent textClassifierEvent) {
        Preconditions.checkNotNull(textClassifierEvent);
        if (TcLog.ENABLE_FULL_LOGGING) {
            TcLog.v("TCEventLogger", String.format("TextClassifierEventLogger.writeEvent: sessionId=%s,event=%s", textClassificationSessionId, textClassifierEvent));
        }
        if (textClassifierEvent instanceof TextClassifierEvent.TextSelectionEvent) {
            logTextSelectionEvent(textClassificationSessionId, (TextClassifierEvent.TextSelectionEvent) textClassifierEvent);
            return;
        }
        if (textClassifierEvent instanceof TextClassifierEvent.TextLinkifyEvent) {
            logTextLinkifyEvent(textClassificationSessionId, (TextClassifierEvent.TextLinkifyEvent) textClassifierEvent);
            return;
        }
        if (textClassifierEvent instanceof TextClassifierEvent.ConversationActionsEvent) {
            logConversationActionsEvent(textClassificationSessionId, (TextClassifierEvent.ConversationActionsEvent) textClassifierEvent);
            return;
        }
        if (textClassifierEvent instanceof TextClassifierEvent.LanguageDetectionEvent) {
            logLanguageDetectionEvent(textClassificationSessionId, (TextClassifierEvent.LanguageDetectionEvent) textClassifierEvent);
            return;
        }
        TcLog.w("TCEventLogger", "Unexpected events, category=" + textClassifierEvent.getEventCategory());
    }

    private static void logTextSelectionEvent(TextClassificationSessionId textClassificationSessionId, TextClassifierEvent.TextSelectionEvent textSelectionEvent) {
        ImmutableList<String> modelNames = getModelNames(textSelectionEvent);
        StatsLog.write(StatsEvent.newBuilder().setAtomId(219).writeString(textClassificationSessionId == null ? null : textClassificationSessionId.getValue()).writeInt(getEventType(textSelectionEvent)).writeString((String) getItemAt(modelNames, 0, null)).writeInt(getWidgetType(textSelectionEvent)).writeInt(textSelectionEvent.getEventIndex()).writeString((String) getItemAt(textSelectionEvent.getEntityTypes(), 0)).writeInt(textSelectionEvent.getRelativeWordStartIndex()).writeInt(textSelectionEvent.getRelativeWordEndIndex()).writeInt(textSelectionEvent.getRelativeSuggestedWordStartIndex()).writeInt(textSelectionEvent.getRelativeSuggestedWordEndIndex()).writeString(getPackageName(textSelectionEvent)).writeString((String) getItemAt(modelNames, 1, null)).usePooledBuffer().build());
    }

    private static int getEventType(TextClassifierEvent.TextSelectionEvent textSelectionEvent) {
        if (textSelectionEvent.getEventType() == 5 && ResultIdUtils.isFromDefaultTextClassifier(textSelectionEvent.getResultId())) {
            return textSelectionEvent.getRelativeWordEndIndex() - textSelectionEvent.getRelativeWordStartIndex() > 1 ? 4 : 3;
        }
        return textSelectionEvent.getEventType();
    }

    private static void logTextLinkifyEvent(TextClassificationSessionId textClassificationSessionId, TextClassifierEvent.TextLinkifyEvent textLinkifyEvent) {
        ImmutableList<String> modelNames = getModelNames(textLinkifyEvent);
        StatsLog.write(StatsEvent.newBuilder().setAtomId(220).writeString(textClassificationSessionId == null ? null : textClassificationSessionId.getValue()).writeInt(textLinkifyEvent.getEventType()).writeString((String) getItemAt(modelNames, 0, null)).writeInt(getWidgetType(textLinkifyEvent)).writeInt(textLinkifyEvent.getEventIndex()).writeString((String) getItemAt(textLinkifyEvent.getEntityTypes(), 0)).writeInt(0).writeInt(0).writeInt(0).writeLong(0L).writeString(getPackageName(textLinkifyEvent)).writeString((String) getItemAt(modelNames, 1, null)).usePooledBuffer().build());
    }

    private static void logConversationActionsEvent(TextClassificationSessionId textClassificationSessionId, TextClassifierEvent.ConversationActionsEvent conversationActionsEvent) {
        String value;
        String strNullToEmpty = Strings.nullToEmpty(conversationActionsEvent.getResultId());
        ImmutableList<String> modelNames = ResultIdUtils.getModelNames(strNullToEmpty);
        StatsEvent.Builder atomId = StatsEvent.newBuilder().setAtomId(221);
        if (textClassificationSessionId == null) {
            value = Hashing.goodFastHash(64).hashString(strNullToEmpty, Charsets.UTF_8).toString();
        } else {
            value = textClassificationSessionId.getValue();
        }
        StatsLog.write(atomId.writeString(value).writeInt(conversationActionsEvent.getEventType()).writeString((String) getItemAt(modelNames, 0, null)).writeInt(getWidgetType(conversationActionsEvent)).writeString((String) getItemAt(conversationActionsEvent.getEntityTypes(), 0)).writeString((String) getItemAt(conversationActionsEvent.getEntityTypes(), 1)).writeString((String) getItemAt(conversationActionsEvent.getEntityTypes(), 2)).writeFloat(getFloatAt(conversationActionsEvent.getScores(), 0)).writeString(getPackageName(conversationActionsEvent)).writeString((String) getItemAt(modelNames, 1, null)).writeString((String) getItemAt(modelNames, 2, null)).usePooledBuffer().build());
    }

    private static void logLanguageDetectionEvent(TextClassificationSessionId textClassificationSessionId, TextClassifierEvent.LanguageDetectionEvent languageDetectionEvent) {
        StatsLog.write(StatsEvent.newBuilder().setAtomId(222).writeString(textClassificationSessionId == null ? null : textClassificationSessionId.getValue()).writeInt(languageDetectionEvent.getEventType()).writeString((String) getItemAt(getModelNames(languageDetectionEvent), 0, null)).writeInt(getWidgetType(languageDetectionEvent)).writeString((String) getItemAt(languageDetectionEvent.getEntityTypes(), 0)).writeFloat(getFloatAt(languageDetectionEvent.getScores(), 0)).writeInt(getIntAt(languageDetectionEvent.getActionIndices(), 0)).writeString(getPackageName(languageDetectionEvent)).usePooledBuffer().build());
    }

    private static <T> T getItemAt(List<T> list, int i, T t) {
        return (list != null && i < list.size()) ? list.get(i) : t;
    }

    private static <T> T getItemAt(T[] tArr, int i) {
        if (tArr != null && i < tArr.length) {
            return tArr[i];
        }
        return null;
    }

    private static float getFloatAt(float[] fArr, int i) {
        return (fArr != null && i < fArr.length) ? fArr[i] : AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS;
    }

    private static int getIntAt(int[] iArr, int i) {
        if (iArr != null && i < iArr.length) {
            return iArr[i];
        }
        return 0;
    }

    private static ImmutableList<String> getModelNames(TextClassifierEvent textClassifierEvent) {
        if (textClassifierEvent.getModelName() != null) {
            return ImmutableList.of(textClassifierEvent.getModelName());
        }
        return ResultIdUtils.getModelNames(textClassifierEvent.getResultId());
    }

    private static String getPackageName(TextClassifierEvent textClassifierEvent) {
        TextClassificationContext eventContext = textClassifierEvent.getEventContext();
        if (eventContext == null) {
            return null;
        }
        return eventContext.getPackageName();
    }

    private static int getWidgetType(TextClassifierEvent textClassifierEvent) {
        TextClassificationContext eventContext = textClassifierEvent.getEventContext();
        if (eventContext == null) {
            return 0;
        }
        switch (eventContext.getWidgetType()) {
        }
        return 0;
    }
}
