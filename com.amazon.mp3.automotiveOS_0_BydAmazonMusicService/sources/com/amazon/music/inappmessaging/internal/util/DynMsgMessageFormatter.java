package com.amazon.music.inappmessaging.internal.util;

import android.icu.text.MessageFormat;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import rx.exceptions.Exceptions;

/* JADX INFO: loaded from: classes4.dex */
public class DynMsgMessageFormatter {
    private static String FALLBACK_STR = "Fallback";
    private static String NULL_STR = "null";
    private static String TAG = "DynMsgMessageFormatter";
    private static final Pattern placeholderPattern = Pattern.compile("\\{([^}]+)\\}");

    public static void formatDynamicMessage(@NonNull final DynamicMessage dynamicMessage, @NonNull final DynamicMessageEvent event) throws IllegalArgumentException {
        try {
            formatMessage(dynamicMessage.templateAttributes, event);
        } catch (IllegalArgumentException e) {
            DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.CONTEXTUAL_FALLBACK_UNAVAILABLE).withErrorMessage(e.toString()).withTrigger(event.getEvent()).send();
            throw Exceptions.propagate(e);
        }
    }

    public static void formatMessage(final Map<String, String> templateAttributes, final DynamicMessageEvent event) throws IllegalArgumentException {
        try {
            Map<String, Object> mapConstructMessageFormatValueMap = constructMessageFormatValueMap(event.getContextualValueMap());
            for (Map.Entry<String, String> entry : templateAttributes.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    Set<String> argumentNames = new MessageFormat(value).getArgumentNames();
                    if (argumentNames != null && !argumentNames.isEmpty()) {
                        value = validateContentFormat(argumentNames, mapConstructMessageFormatValueMap.keySet()) ? MessageFormat.format(value, mapConstructMessageFormatValueMap) : fetchFallbackMessage(templateAttributes, entry.getKey(), event.getEvent());
                    }
                    templateAttributes.put(entry.getKey(), value);
                }
            }
        } catch (Exception e) {
            String str = "Exception occurs when formatting contextual message: " + e.toString();
            Log.e(TAG, str);
            throw new IllegalArgumentException(str);
        }
    }

    public static boolean templateAttributesContainPlaceholder(final Map<String, String> templateAttributes) {
        Iterator<Map.Entry<String, String>> it = templateAttributes.entrySet().iterator();
        while (it.hasNext()) {
            String value = it.next().getValue();
            if (value != null && placeholderPattern.matcher(value).find()) {
                return true;
            }
        }
        return false;
    }

    private static Map<String, Object> constructMessageFormatValueMap(final Map<String, String> contextualParamMap) {
        HashMap map = new HashMap();
        for (Map.Entry<String, String> entry : contextualParamMap.entrySet()) {
            String value = entry.getValue();
            if (value != null && !NULL_STR.equals(value)) {
                map.put(entry.getKey(), value);
            } else {
                Log.i(TAG, "Contextual Param: " + entry.getKey() + " is not included in value map since value is null");
            }
        }
        return map;
    }

    private static boolean validateContentFormat(final Set<String> contentArguments, final Set<String> availableKeys) {
        for (String str : contentArguments) {
            if (!availableKeys.contains(str)) {
                Log.i(TAG, "Client does not provide sufficient contextual value for: " + str);
                return false;
            }
        }
        return true;
    }

    private static String fetchFallbackMessage(final Map<String, String> templateAttributes, final String contextualAttribute, final String eventName) throws IllegalArgumentException {
        String str = templateAttributes.get(contextualAttribute + FALLBACK_STR);
        if (str != null) {
            DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.CONTEXTUAL_FALLBACK).withErrorMessage("Switching to use fallback message for attribute: " + contextualAttribute + " in event: " + eventName).withTrigger(eventName).send();
            return str;
        }
        throw new IllegalArgumentException("Contextual Fallback Message for " + contextualAttribute + " is not available");
    }
}
