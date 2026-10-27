package com.android.textclassifier.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.app.RemoteAction;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Process;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.LruCache;
import android.util.Pair;
import android.view.textclassifier.ConversationAction;
import android.view.textclassifier.ConversationActions;
import android.view.textclassifier.TextClassificationContext;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class SmartSuggestionsHelper {
    private final SmartSuggestionsConfig config;
    private final Context context;
    private final LruCache<String, SmartSuggestionsLogSession> sessionCache = new LruCache<String, SmartSuggestionsLogSession>(this, 20) { // from class: com.android.textclassifier.notification.SmartSuggestionsHelper.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public void entryRemoved(boolean z, String str, SmartSuggestionsLogSession smartSuggestionsLogSession, SmartSuggestionsLogSession smartSuggestionsLogSession2) {
            smartSuggestionsLogSession.destroy();
        }
    };
    private final TextClassificationContext textClassificationContext;
    private final TextClassificationManager textClassificationManager;
    private static final ImmutableList<String> HINTS = ImmutableList.of("notification");
    private static final SuggestConversationActionsResult EMPTY_SUGGEST_CONVERSATION_ACTION_RESULT = new SuggestConversationActionsResult(Optional.empty(), new ConversationActions(ImmutableList.of(), null));

    public SmartSuggestionsHelper(Context context, SmartSuggestionsConfig smartSuggestionsConfig) {
        this.context = context;
        this.textClassificationManager = (TextClassificationManager) context.getSystemService(TextClassificationManager.class);
        this.config = smartSuggestionsConfig;
        this.textClassificationContext = new TextClassificationContext.Builder(context.getPackageName(), "notification").build();
    }

    public SmartSuggestions onNotificationEnqueued(final StatusBarNotification statusBarNotification) {
        Notification.Action actionCreateNotificationActionFromRemoteAction;
        this.sessionCache.remove(statusBarNotification.getKey());
        SuggestConversationActionsResult suggestConversationActionsResultSuggestConversationActions = suggestConversationActions(statusBarNotification, this.config.shouldGenerateReplies() && isEligibleForReplyAdjustment(statusBarNotification), this.config.shouldGenerateActions() && isEligibleForActionAdjustment(statusBarNotification));
        final String id = suggestConversationActionsResultSuggestConversationActions.conversationActions.getId();
        final List<ConversationAction> conversationActions = suggestConversationActionsResultSuggestConversationActions.conversationActions.getConversationActions();
        final ArrayList arrayList = new ArrayList();
        final ArrayMap arrayMap = new ArrayMap();
        for (ConversationAction conversationAction : conversationActions) {
            CharSequence textReply = conversationAction.getTextReply();
            if (!TextUtils.isEmpty(textReply)) {
                arrayList.add(textReply);
                arrayMap.put(textReply, Float.valueOf(conversationAction.getConfidenceScore()));
            }
        }
        final ArrayList arrayList2 = new ArrayList();
        for (ConversationAction conversationAction2 : conversationActions) {
            if (TextUtils.isEmpty(conversationAction2.getTextReply())) {
                RemoteAction action = conversationAction2.getAction();
                if (action == null) {
                    actionCreateNotificationActionFromRemoteAction = createNotificationActionWithoutRemoteAction(conversationAction2);
                } else {
                    actionCreateNotificationActionFromRemoteAction = createNotificationActionFromRemoteAction(action, conversationAction2.getType(), conversationAction2.getConfidenceScore());
                }
                if (actionCreateNotificationActionFromRemoteAction != null) {
                    arrayList2.add(actionCreateNotificationActionFromRemoteAction);
                }
            }
        }
        suggestConversationActionsResultSuggestConversationActions.textClassifier.ifPresent(new Consumer() { // from class: com.android.textclassifier.notification.-$$Lambda$SmartSuggestionsHelper$nqAehbuWudtq1R0SqQHGETrZ0XQ
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onNotificationEnqueued$0$SmartSuggestionsHelper(id, arrayMap, conversationActions, statusBarNotification, arrayList2, arrayList, (TextClassifier) obj);
            }
        });
        return new SmartSuggestions(arrayList, arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onNotificationEnqueued$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onNotificationEnqueued$0$SmartSuggestionsHelper(String str, Map map, List list, StatusBarNotification statusBarNotification, ArrayList arrayList, ArrayList arrayList2, TextClassifier textClassifier) {
        if (TextUtils.isEmpty(str)) {
            textClassifier.destroy();
            return;
        }
        SmartSuggestionsLogSession smartSuggestionsLogSession = new SmartSuggestionsLogSession(str, map, textClassifier, this.textClassificationContext);
        smartSuggestionsLogSession.onSuggestionsGenerated(list);
        if (!list.isEmpty() && suggestionsMightBeUsedInNotification(statusBarNotification, !arrayList.isEmpty(), !arrayList2.isEmpty())) {
            this.sessionCache.put(statusBarNotification.getKey(), smartSuggestionsLogSession);
        } else {
            smartSuggestionsLogSession.destroy();
        }
    }

    private Notification.Action createNotificationActionWithoutRemoteAction(ConversationAction conversationAction) {
        if ("copy".equals(conversationAction.getType())) {
            return createCopyCodeAction(conversationAction);
        }
        return null;
    }

    private Notification.Action createCopyCodeAction(ConversationAction conversationAction) {
        Bundle bundle = (Bundle) conversationAction.getExtras().getParcelable("entities-extras");
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("text");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        String string2 = this.context.getString(R$string.tc_notif_copy_code_desc, string);
        Intent intent = new Intent(this.context, (Class<?>) CopyCodeActivity.class);
        intent.putExtra("android.intent.extra.TEXT", string);
        return createNotificationActionFromRemoteAction(new RemoteAction(Icon.createWithResource(this.context, R$drawable.tc_notif_ic_menu_copy_material), string, string2, PendingIntent.getActivity(this.context, string.hashCode(), intent, 134217728)), "copy", conversationAction.getConfidenceScore());
    }

    private static boolean suggestionsMightBeUsedInNotification(StatusBarNotification statusBarNotification, boolean z, boolean z2) {
        boolean allowGeneratedReplies;
        boolean z3;
        Notification notification = statusBarNotification.getNotification();
        boolean z4 = !notification.getContextualActions().isEmpty();
        Pair<RemoteInput, Notification.Action> pairFindRemoteInputActionPair = notification.findRemoteInputActionPair(true);
        if (pairFindRemoteInputActionPair != null) {
            RemoteInput remoteInput = (RemoteInput) pairFindRemoteInputActionPair.first;
            Notification.Action action = (Notification.Action) pairFindRemoteInputActionPair.second;
            CharSequence[] choices = remoteInput.getChoices();
            z3 = choices != null && choices.length > 0;
            allowGeneratedReplies = action.getAllowGeneratedReplies();
        } else {
            allowGeneratedReplies = false;
            z3 = false;
        }
        if (z3 || z4) {
            return false;
        }
        if (z && notification.getAllowSystemGeneratedContextualActions()) {
            return true;
        }
        return z2 && allowGeneratedReplies;
    }

    private SuggestConversationActionsResult suggestConversationActions(StatusBarNotification statusBarNotification, boolean z, boolean z2) {
        if (!z && !z2) {
            return EMPTY_SUGGEST_CONVERSATION_ACTION_RESULT;
        }
        ImmutableList<ConversationActions.Message> immutableListExtractMessages = extractMessages(statusBarNotification.getNotification());
        if (immutableListExtractMessages.isEmpty()) {
            return EMPTY_SUGGEST_CONVERSATION_ACTION_RESULT;
        }
        if (arePersonsEqual(ConversationActions.Message.PERSON_USER_SELF, ((ConversationActions.Message) Iterables.getLast(immutableListExtractMessages)).getAuthor())) {
            return EMPTY_SUGGEST_CONVERSATION_ACTION_RESULT;
        }
        TextClassifier.EntityConfig.Builder builder = new TextClassifier.EntityConfig.Builder();
        if (!z) {
            builder.setExcludedTypes(ImmutableList.of("text_reply"));
        } else if (!z2) {
            builder.setIncludedTypes(ImmutableList.of("text_reply")).includeTypesFromTextClassifier(false);
        }
        Bundle bundle = new Bundle();
        bundle.putString("notificationKey", statusBarNotification.getKey());
        ConversationActions.Request requestBuild = new ConversationActions.Request.Builder(immutableListExtractMessages).setMaxSuggestions(this.config.getMaxSuggestions()).setHints(HINTS).setExtras(bundle).setTypeConfig(builder.build()).build();
        TextClassifier textClassifierCreateTextClassificationSession = createTextClassificationSession();
        return new SuggestConversationActionsResult(Optional.of(textClassifierCreateTextClassificationSession), textClassifierCreateTextClassificationSession.suggestConversationActions(requestBuild));
    }

    public void onNotificationExpansionChanged(StatusBarNotification statusBarNotification, boolean z) {
        SmartSuggestionsLogSession smartSuggestionsLogSession = this.sessionCache.get(statusBarNotification.getKey());
        if (smartSuggestionsLogSession == null) {
            return;
        }
        smartSuggestionsLogSession.onNotificationExpansionChanged(z);
    }

    public void onNotificationDirectReplied(String str) {
        SmartSuggestionsLogSession smartSuggestionsLogSession = this.sessionCache.get(str);
        if (smartSuggestionsLogSession == null) {
            return;
        }
        smartSuggestionsLogSession.onDirectReplied();
    }

    public void onSuggestedReplySent(String str, CharSequence charSequence, int i) {
        SmartSuggestionsLogSession smartSuggestionsLogSession = this.sessionCache.get(str);
        if (smartSuggestionsLogSession == null) {
            return;
        }
        smartSuggestionsLogSession.onSuggestedReplySent(charSequence, i);
    }

    public void onActionClicked(String str, Notification.Action action, int i) {
        SmartSuggestionsLogSession smartSuggestionsLogSession = this.sessionCache.get(str);
        if (smartSuggestionsLogSession == null) {
            return;
        }
        smartSuggestionsLogSession.onActionClicked(action, i);
    }

    private Notification.Action createNotificationActionFromRemoteAction(RemoteAction remoteAction, String str, float f) {
        Icon iconCreateWithResource;
        if (remoteAction.shouldShowIcon()) {
            iconCreateWithResource = remoteAction.getIcon();
        } else {
            iconCreateWithResource = Icon.createWithResource(this.context, R$drawable.tc_notif_ic_action_open);
        }
        Bundle bundle = new Bundle();
        bundle.putString("action_type", str);
        bundle.putFloat("action_score", f);
        return new Notification.Action.Builder(iconCreateWithResource, remoteAction.getTitle(), remoteAction.getActionIntent()).setContextual(true).addExtras(bundle).build();
    }

    private static boolean isEligibleForActionAdjustment(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        if (Process.myUserHandle().equals(statusBarNotification.getUser()) && (statusBarNotification.getNotification().flags & 610) == 0 && !TextUtils.isEmpty(packageName) && !packageName.equals("android")) {
            return NotificationUtils.isMessaging(statusBarNotification);
        }
        return false;
    }

    private static boolean isEligibleForReplyAdjustment(StatusBarNotification statusBarNotification) {
        if (!Process.myUserHandle().equals(statusBarNotification.getUser())) {
            return false;
        }
        String packageName = statusBarNotification.getPackageName();
        return !TextUtils.isEmpty(packageName) && !packageName.equals("android") && NotificationUtils.isMessaging(statusBarNotification) && NotificationUtils.hasInlineReply(statusBarNotification);
    }

    private ImmutableList<ConversationActions.Message> extractMessages(Notification notification) {
        List<Notification.MessagingStyle.Message> messagesFromBundleArray = Notification.MessagingStyle.Message.getMessagesFromBundleArray(notification.extras.getParcelableArray("android.messages"));
        if (messagesFromBundleArray == null || messagesFromBundleArray.isEmpty()) {
            return ImmutableList.of(new ConversationActions.Message.Builder(ConversationActions.Message.PERSON_USER_OTHERS).setText(notification.extras.getCharSequence("android.text")).build());
        }
        Person person = (Person) notification.extras.getParcelable("android.messagingUser");
        if (person == null) {
            Log.w("SmartSuggestionsHelper", "EXTRA_MESSAGING_PERSON is missing, failed to extract messages.");
            return ImmutableList.of();
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        for (int size = messagesFromBundleArray.size() - 1; size >= 0; size--) {
            Notification.MessagingStyle.Message message = messagesFromBundleArray.get(size);
            if (message != null) {
                Person senderPerson = message.getSenderPerson();
                if (senderPerson == null || arePersonsEqual(person, senderPerson)) {
                    senderPerson = ConversationActions.Message.PERSON_USER_SELF;
                }
                arrayDeque.push(new ConversationActions.Message.Builder(senderPerson).setText(message.getText()).setReferenceTime(Instant.ofEpochMilli(message.getTimestamp()).atZone(ZoneOffset.systemDefault())).build());
                if (arrayDeque.size() >= this.config.getMaxMessagesToExtract()) {
                    break;
                }
            }
        }
        return ImmutableList.copyOf((Collection) new ArrayList(arrayDeque));
    }

    TextClassifier createTextClassificationSession() {
        return this.textClassificationManager.createTextClassificationSession(this.textClassificationContext);
    }

    private static boolean arePersonsEqual(Person person, Person person2) {
        return Objects.equals(person.getKey(), person2.getKey()) && TextUtils.equals(person.getName(), person2.getName()) && Objects.equals(person.getUri(), person2.getUri());
    }

    private static class SuggestConversationActionsResult {
        final ConversationActions conversationActions;
        final Optional<TextClassifier> textClassifier;

        SuggestConversationActionsResult(Optional<TextClassifier> optional, ConversationActions conversationActions) {
            this.textClassifier = optional;
            this.conversationActions = conversationActions;
        }
    }
}
