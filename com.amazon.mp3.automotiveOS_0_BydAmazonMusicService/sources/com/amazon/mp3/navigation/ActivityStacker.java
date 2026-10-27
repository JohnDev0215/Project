package com.amazon.mp3.navigation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class ActivityStacker {
    private static final LinkedBlockingQueue<String> sStackedIntentQueue = new LinkedBlockingQueue<>(50);

    public Intent chainIntents(final Intent firstActivityOnStackIntent, final Intent secondActivityOnStackIntent) {
        validateActivityIntentsCanBeStacked(firstActivityOnStackIntent, secondActivityOnStackIntent);
        firstActivityOnStackIntent.putExtras(copyActivityIntentInfoIntoBundle(secondActivityOnStackIntent.getComponent().getClassName(), secondActivityOnStackIntent.getExtras()));
        return firstActivityOnStackIntent;
    }

    private void validateActivityIntentsCanBeStacked(final Intent firstActivityIntent, final Intent secondActivityIntent) {
        Validate.notNull(firstActivityIntent);
        Validate.notNull(secondActivityIntent);
        if (intentContainsNextActivityClassName(firstActivityIntent)) {
            throw new IllegalArgumentException("Cannot chain activities provided because the first activity's intent alreadyhas a second activity intent inside of it.");
        }
        String activityClassNameFromIntent = getActivityClassNameFromIntent(firstActivityIntent);
        String activityClassNameFromIntent2 = getActivityClassNameFromIntent(secondActivityIntent);
        if (TextUtils.isEmpty(activityClassNameFromIntent) || TextUtils.isEmpty(activityClassNameFromIntent2)) {
            throw new IllegalArgumentException("Both intents provided must contain a valid activity class name");
        }
        if (activityClassNameFromIntent.equals(activityClassNameFromIntent2)) {
            throw new IllegalArgumentException(String.format("Cannot chain two intents for the same activity together. Input: %s", activityClassNameFromIntent));
        }
    }

    private boolean intentContainsNextActivityClassName(final Intent intent) {
        return intent.getStringExtra("com.amazon.mp3.navigator.EXTRA_ACTIVITY_CLASS_NAME") != null;
    }

    private String getActivityClassNameFromIntent(final Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null) {
            return null;
        }
        return component.getClassName();
    }

    private Bundle copyActivityIntentInfoIntoBundle(final String activityClassName, final Bundle activityIntentExtras) {
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.mp3.navigator.EXTRA_ACTIVITY_CLASS_NAME", activityClassName);
        if (activityIntentExtras != null) {
            bundle.putBundle("com.amazon.mp3.navigator.EXTRA_ACTIVITY_BUNDLE_KEY", activityIntentExtras);
        }
        String string = UUID.randomUUID().toString();
        bundle.putString("com.amazon.mp3.navigator.EXTRA_STACKED_INTENT_UUID_KEY", string);
        addUuid(string);
        return bundle;
    }

    public boolean shouldGetNextActivityIntent(final Intent currentActivitysIntent) {
        return intentContainsNextActivityClassName(currentActivitysIntent) && isStackedIntentRecognized(currentActivitysIntent);
    }

    private boolean isStackedIntentRecognized(final Intent currentActivitysIntent) {
        String stringExtra = currentActivitysIntent.getStringExtra("com.amazon.mp3.navigator.EXTRA_STACKED_INTENT_UUID_KEY");
        if (stringExtra == null) {
            return false;
        }
        return containsUuid(stringExtra);
    }

    public Intent getNextActivityIntent(final Context context, final Intent currentActivitysIntent) throws GetNextActivityIntentException {
        if (!shouldGetNextActivityIntent(currentActivitysIntent)) {
            throw new GetNextActivityIntentException("You must only call getNextActivityIntentif shouldGetNextActivityIntent is true!");
        }
        String stringExtra = currentActivitysIntent.getStringExtra("com.amazon.mp3.navigator.EXTRA_ACTIVITY_CLASS_NAME");
        Bundle bundleExtra = currentActivitysIntent.getBundleExtra("com.amazon.mp3.navigator.EXTRA_ACTIVITY_BUNDLE_KEY");
        currentActivitysIntent.removeExtra("com.amazon.mp3.navigator.EXTRA_ACTIVITY_BUNDLE_KEY");
        currentActivitysIntent.removeExtra("com.amazon.mp3.navigator.EXTRA_ACTIVITY_CLASS_NAME");
        removeUuid(currentActivitysIntent.getStringExtra("com.amazon.mp3.navigator.EXTRA_STACKED_INTENT_UUID_KEY"));
        return rebuildIntentFromClassNameAndExtras(context, stringExtra, bundleExtra);
    }

    private Intent rebuildIntentFromClassNameAndExtras(final Context context, final String activityClassName, final Bundle extras) throws GetNextActivityIntentException {
        try {
            Intent intent = new Intent(context, Class.forName(activityClassName));
            if (extras != null) {
                intent.putExtras(extras);
            }
            return intent;
        } catch (ClassNotFoundException e) {
            throw new GetNextActivityIntentException("Unable rebuild intent because invalid class name was provided", e);
        }
    }

    public class GetNextActivityIntentException extends Exception {
        GetNextActivityIntentException(final String message) {
            super(message);
        }

        GetNextActivityIntentException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }

    private static synchronized void addUuid(final String uuid) {
        LinkedBlockingQueue<String> linkedBlockingQueue = sStackedIntentQueue;
        if (!linkedBlockingQueue.offer(uuid)) {
            linkedBlockingQueue.poll();
            linkedBlockingQueue.offer(uuid);
        }
    }

    private static synchronized boolean containsUuid(final String uuid) {
        return sStackedIntentQueue.contains(uuid);
    }

    private static synchronized boolean removeUuid(final String uuid) {
        return sStackedIntentQueue.remove(uuid);
    }
}
