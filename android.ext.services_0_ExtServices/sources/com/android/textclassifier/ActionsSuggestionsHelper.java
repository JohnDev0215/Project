package com.android.textclassifier;

import android.app.Person;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Pair;
import android.view.textclassifier.ConversationAction;
import android.view.textclassifier.ConversationActions;
import com.android.textclassifier.ModelFileManager;
import com.android.textclassifier.common.base.TcLog;
import com.android.textclassifier.common.intent.LabeledIntent;
import com.android.textclassifier.common.intent.TemplateIntentFactory;
import com.android.textclassifier.common.logging.ResultIdUtils;
import com.google.android.textclassifier.ActionsSuggestionsModel;
import com.google.android.textclassifier.RemoteActionTemplate;
import com.google.common.base.Equivalence;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
final class ActionsSuggestionsHelper {
    public static ActionsSuggestionsModel.ConversationMessage[] toNativeMessages(List<ConversationActions.Message> list, Function<CharSequence, List<String>> function) {
        List list2 = (List) list.stream().filter(new Predicate() { // from class: com.android.textclassifier.-$$Lambda$ActionsSuggestionsHelper$2MPP2WsfNt7PQuIjole5G6PKxCA
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ActionsSuggestionsHelper.lambda$toNativeMessages$0((ConversationActions.Message) obj);
            }
        }).collect(Collectors.toCollection($$Lambda$OGSS2qx6njxlnp0dnKb4lA3jnw8.INSTANCE));
        if (list2.isEmpty()) {
            return new ActionsSuggestionsModel.ConversationMessage[0];
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        PersonEncoder personEncoder = new PersonEncoder();
        for (int size = list2.size() - 1; size >= 0; size--) {
            ConversationActions.Message message = (ConversationActions.Message) list2.get(size);
            arrayDeque.push(new ActionsSuggestionsModel.ConversationMessage(personEncoder.encode(message.getAuthor()), message.getText().toString(), message.getReferenceTime() == null ? 0L : message.getReferenceTime().toInstant().toEpochMilli(), message.getReferenceTime() == null ? null : message.getReferenceTime().getZone().getId(), String.join(",", function.apply(message.getText()))));
        }
        return (ActionsSuggestionsModel.ConversationMessage[]) arrayDeque.toArray(new ActionsSuggestionsModel.ConversationMessage[arrayDeque.size()]);
    }

    static /* synthetic */ boolean lambda$toNativeMessages$0(ConversationActions.Message message) {
        return !TextUtils.isEmpty(message.getText());
    }

    public static String createResultId(Context context, List<ConversationActions.Message> list, Optional<ModelFileManager.ModelFile> optional, Optional<ModelFileManager.ModelFile> optional2, Optional<ModelFileManager.ModelFile> optional3) {
        return ResultIdUtils.createId(Objects.hash(list.stream().mapToInt(new ToIntFunction() { // from class: com.android.textclassifier.-$$Lambda$ActionsSuggestionsHelper$vSgOiD9c0JzSjFw9oLpnUJ3Bm2A
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ActionsSuggestionsHelper.hashMessage((ConversationActions.Message) obj);
            }
        }), context.getPackageName(), Long.valueOf(System.currentTimeMillis())), ModelFileManager.ModelFile.toModelInfos(optional, optional2, optional3));
    }

    public static LabeledIntent.Result createLabeledIntentResult(Context context, TemplateIntentFactory templateIntentFactory, ActionsSuggestionsModel.ActionSuggestion actionSuggestion) {
        RemoteActionTemplate[] remoteActionTemplates = actionSuggestion.getRemoteActionTemplates();
        if (remoteActionTemplates == null) {
            TcLog.w("ActionsSuggestions", "createRemoteAction: Missing template for type " + actionSuggestion.getActionType());
            return null;
        }
        ImmutableList<LabeledIntent> immutableListCreate = templateIntentFactory.create(remoteActionTemplates);
        if (immutableListCreate.isEmpty()) {
            return null;
        }
        return immutableListCreate.get(0).resolve(context, createTitleChooser(actionSuggestion.getActionType()));
    }

    public static LabeledIntent.TitleChooser createTitleChooser(String str) {
        if ("open_url".equals(str)) {
            return new LabeledIntent.TitleChooser() { // from class: com.android.textclassifier.-$$Lambda$ActionsSuggestionsHelper$SkW1Ih0E_5ISCwQJkfKE93HV144
                @Override // com.android.textclassifier.common.intent.LabeledIntent.TitleChooser
                public final CharSequence chooseTitle(LabeledIntent labeledIntent, ResolveInfo resolveInfo) {
                    return ActionsSuggestionsHelper.lambda$createTitleChooser$1(labeledIntent, resolveInfo);
                }
            };
        }
        return null;
    }

    static /* synthetic */ CharSequence lambda$createTitleChooser$1(LabeledIntent labeledIntent, ResolveInfo resolveInfo) {
        if (resolveInfo.handleAllWebDataURI) {
            return labeledIntent.titleWithEntity;
        }
        if ("android".equals(resolveInfo.activityInfo.packageName)) {
            return labeledIntent.titleWithEntity;
        }
        return labeledIntent.titleWithoutEntity;
    }

    public static List<ConversationAction> removeActionsWithDuplicates(List<ConversationAction> list) {
        ArrayMap arrayMap = new ArrayMap();
        Iterator<ConversationAction> it = list.iterator();
        while (it.hasNext()) {
            Pair<String, String> representation = getRepresentation(it.next());
            if (representation != null) {
                arrayMap.put(representation, Integer.valueOf(((Integer) arrayMap.getOrDefault(representation, 0)).intValue() + 1));
            }
        }
        ArrayList arrayList = new ArrayList();
        for (ConversationAction conversationAction : list) {
            Pair<String, String> representation2 = getRepresentation(conversationAction);
            if (representation2 == null || ((Integer) arrayMap.getOrDefault(representation2, 0)).intValue() == 1) {
                arrayList.add(conversationAction);
            }
        }
        return arrayList;
    }

    private static Pair<String, String> getRepresentation(ConversationAction conversationAction) {
        if (conversationAction.getAction() == null) {
            return null;
        }
        ComponentName component = ExtrasUtils.getActionIntent(conversationAction.getExtras()).getComponent();
        return new Pair<>(conversationAction.getAction().getTitle().toString(), component != null ? component.getPackageName() : null);
    }

    private static final class PersonEncoder {
        private static final Equivalence<Person> EQUIVALENCE;
        private static final Equivalence.Wrapper<Person> PERSON_USER_SELF;
        private int nextUserId;
        private final Map<Equivalence.Wrapper<Person>, Integer> personToUserIdMap;

        private PersonEncoder() {
            this.personToUserIdMap = new ArrayMap();
            this.nextUserId = 1;
        }

        static {
            PersonEquivalence personEquivalence = new PersonEquivalence();
            EQUIVALENCE = personEquivalence;
            PERSON_USER_SELF = personEquivalence.wrap(ConversationActions.Message.PERSON_USER_SELF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int encode(Person person) {
            Object objWrap = EQUIVALENCE.wrap(person);
            if (PERSON_USER_SELF.equals(objWrap)) {
                return 0;
            }
            Integer numValueOf = this.personToUserIdMap.get(objWrap);
            if (numValueOf == null) {
                this.personToUserIdMap.put((Equivalence.Wrapper<Person>) objWrap, Integer.valueOf(this.nextUserId));
                numValueOf = Integer.valueOf(this.nextUserId);
                this.nextUserId++;
            }
            return numValueOf.intValue();
        }

        private static final class PersonEquivalence extends Equivalence<Person> {
            private PersonEquivalence() {
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.common.base.Equivalence
            public boolean doEquivalent(Person person, Person person2) {
                return Objects.equals(person.getKey(), person2.getKey()) && TextUtils.equals(person.getName(), person2.getName()) && Objects.equals(person.getUri(), person2.getUri());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.common.base.Equivalence
            public int doHash(Person person) {
                return Objects.hash(person.getKey(), person.getName(), person.getUri());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int hashMessage(ConversationActions.Message message) {
        return Objects.hash(message.getAuthor(), message.getText(), message.getReferenceTime());
    }
}
