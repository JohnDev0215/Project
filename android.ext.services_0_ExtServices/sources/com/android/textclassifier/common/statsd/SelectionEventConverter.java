package com.android.textclassifier.common.statsd;

import android.view.textclassifier.SelectionEvent;
import android.view.textclassifier.TextClassificationContext;
import android.view.textclassifier.TextClassifierEvent;

/* JADX INFO: loaded from: classes.dex */
public final class SelectionEventConverter {
    private static int convertEventType(int i) {
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    i2 = 4;
                    if (i != 4) {
                        i2 = 5;
                        if (i != 5) {
                            if (i == 200) {
                                return 17;
                            }
                            if (i == 201) {
                                return 18;
                            }
                            switch (i) {
                                case 100:
                                    return 8;
                                case 101:
                                    return 9;
                                case 102:
                                    return 10;
                                case 103:
                                    return 11;
                                case 104:
                                    return 12;
                                case 105:
                                    return 13;
                                case 106:
                                    return 14;
                                case 107:
                                    return 15;
                                case 108:
                                    return 16;
                                default:
                                    return 0;
                            }
                        }
                    }
                }
            }
        }
        return i2;
    }

    public static TextClassifierEvent toTextClassifierEvent(SelectionEvent selectionEvent) {
        TextClassificationContext textClassificationContextBuild = (selectionEvent.getPackageName() == null || selectionEvent.getWidgetType() == null) ? null : new TextClassificationContext.Builder(selectionEvent.getPackageName(), selectionEvent.getWidgetType()).setWidgetVersion(selectionEvent.getWidgetVersion()).build();
        if (selectionEvent.getInvocationMethod() == 2) {
            return ((TextClassifierEvent.TextLinkifyEvent.Builder) ((TextClassifierEvent.TextLinkifyEvent.Builder) ((TextClassifierEvent.TextLinkifyEvent.Builder) ((TextClassifierEvent.TextLinkifyEvent.Builder) new TextClassifierEvent.TextLinkifyEvent.Builder(convertEventType(selectionEvent.getEventType())).setEventContext(textClassificationContextBuild)).setResultId(selectionEvent.getResultId())).setEventIndex(selectionEvent.getEventIndex())).setEntityTypes(selectionEvent.getEntityType())).build();
        }
        if (selectionEvent.getInvocationMethod() == 1) {
            return ((TextClassifierEvent.TextSelectionEvent.Builder) ((TextClassifierEvent.TextSelectionEvent.Builder) ((TextClassifierEvent.TextSelectionEvent.Builder) ((TextClassifierEvent.TextSelectionEvent.Builder) new TextClassifierEvent.TextSelectionEvent.Builder(convertEventType(selectionEvent.getEventType())).setEventContext(textClassificationContextBuild)).setResultId(selectionEvent.getResultId())).setEventIndex(selectionEvent.getEventIndex())).setEntityTypes(selectionEvent.getEntityType())).setRelativeWordStartIndex(selectionEvent.getStart()).setRelativeWordEndIndex(selectionEvent.getEnd()).setRelativeSuggestedWordStartIndex(selectionEvent.getSmartStart()).setRelativeSuggestedWordEndIndex(selectionEvent.getSmartEnd()).build();
        }
        return null;
    }
}
