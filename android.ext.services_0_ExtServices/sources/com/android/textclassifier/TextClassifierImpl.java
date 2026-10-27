package com.android.textclassifier;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.util.ArrayMap;
import android.view.View;
import android.view.textclassifier.ConversationAction;
import android.view.textclassifier.ConversationActions;
import android.view.textclassifier.SelectionEvent;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassificationSessionId;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextClassifierEvent;
import android.view.textclassifier.TextLanguage;
import android.view.textclassifier.TextLinks;
import android.view.textclassifier.TextSelection;
import androidx.core.util.Pair;
import com.android.textclassifier.ModelFileManager;
import com.android.textclassifier.common.base.TcLog;
import com.android.textclassifier.common.intent.LabeledIntent;
import com.android.textclassifier.common.intent.TemplateIntentFactory;
import com.android.textclassifier.common.logging.ResultIdUtils;
import com.android.textclassifier.common.statsd.GenerateLinksLogger;
import com.android.textclassifier.common.statsd.SelectionEventConverter;
import com.android.textclassifier.common.statsd.TextClassificationSessionIdConverter;
import com.android.textclassifier.common.statsd.TextClassifierEventConverter;
import com.android.textclassifier.common.statsd.TextClassifierEventLogger;
import com.android.textclassifier.utils.IndentingPrintWriter;
import com.google.android.textclassifier.ActionsSuggestionsModel;
import com.google.android.textclassifier.AnnotatorModel;
import com.google.android.textclassifier.LangIdModel;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
final class TextClassifierImpl {
    private ModelFileManager.ModelFile actionModelInUse;
    private ActionsSuggestionsModel actionsImpl;
    private final ModelFileManager actionsModelFileManager;
    private AnnotatorModel annotatorImpl;
    private final ModelFileManager annotatorModelFileManager;
    private ModelFileManager.ModelFile annotatorModelInUse;
    private final Context context;
    private final TextClassifier fallback;
    private final GenerateLinksLogger generateLinksLogger;
    private LangIdModel langIdImpl;
    private final ModelFileManager langIdModelFileManager;
    private ModelFileManager.ModelFile langIdModelInUse;
    private final Object lock;
    private final TextClassifierSettings settings;
    private final TemplateIntentFactory templateIntentFactory;
    private final TextClassifierEventLogger textClassifierEventLogger;
    private static final File FACTORY_MODEL_DIR = new File("/etc/textclassifier/");
    private static final File ANNOTATOR_UPDATED_MODEL_FILE = new File("/data/misc/textclassifier/textclassifier.model");
    private static final File UPDATED_LANG_ID_MODEL_FILE = new File("/data/misc/textclassifier/lang_id.model");
    private static final File UPDATED_ACTIONS_MODEL = new File("/data/misc/textclassifier/actions_suggestions.model");

    static /* synthetic */ String lambda$new$0(Integer num) {
        return "*";
    }

    TextClassifierImpl(Context context, TextClassifierSettings textClassifierSettings, TextClassifier textClassifier) {
        this.lock = new Object();
        this.textClassifierEventLogger = new TextClassifierEventLogger();
        Preconditions.checkNotNull(context);
        this.context = context;
        Preconditions.checkNotNull(textClassifier);
        this.fallback = textClassifier;
        Preconditions.checkNotNull(textClassifierSettings);
        TextClassifierSettings textClassifierSettings2 = textClassifierSettings;
        this.settings = textClassifierSettings2;
        this.generateLinksLogger = new GenerateLinksLogger(textClassifierSettings2.getGenerateLinksLogSampleRate());
        this.annotatorModelFileManager = new ModelFileManager(new ModelFileManager.ModelFileSupplierImpl(FACTORY_MODEL_DIR, "textclassifier\\.(.*)\\.model", ANNOTATOR_UPDATED_MODEL_FILE, new Function() { // from class: com.android.textclassifier.-$$Lambda$jJq8RXuVdjYF3lPq-77PEw1NJLM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(AnnotatorModel.getVersion(((Integer) obj).intValue()));
            }
        }, new Function() { // from class: com.android.textclassifier.-$$Lambda$NxwbyZSxofZ4Z5SQhfXmtLQ1nxk
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AnnotatorModel.getLocales(((Integer) obj).intValue());
            }
        }));
        this.langIdModelFileManager = new ModelFileManager(new ModelFileManager.ModelFileSupplierImpl(FACTORY_MODEL_DIR, "lang_id.model", UPDATED_LANG_ID_MODEL_FILE, new Function() { // from class: com.android.textclassifier.-$$Lambda$0biFK4yZBmWN1EO2wtnXskzuEcE
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(LangIdModel.getVersion(((Integer) obj).intValue()));
            }
        }, new Function() { // from class: com.android.textclassifier.-$$Lambda$TextClassifierImpl$GgWuU910BaKzzHu6MQGYGCHOgkg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TextClassifierImpl.lambda$new$0((Integer) obj);
            }
        }));
        this.actionsModelFileManager = new ModelFileManager(new ModelFileManager.ModelFileSupplierImpl(FACTORY_MODEL_DIR, "actions_suggestions\\.(.*)\\.model", UPDATED_ACTIONS_MODEL, new Function() { // from class: com.android.textclassifier.-$$Lambda$9N8WImc0VBjy2oxI_Gk5_Pbye_A
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(ActionsSuggestionsModel.getVersion(((Integer) obj).intValue()));
            }
        }, new Function() { // from class: com.android.textclassifier.-$$Lambda$XeE_KI7QgMKzF9vYRSoFWAolyuA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ActionsSuggestionsModel.getLocales(((Integer) obj).intValue());
            }
        }));
        this.templateIntentFactory = new TemplateIntentFactory();
    }

    TextClassifierImpl(Context context, TextClassifierSettings textClassifierSettings) {
        this(context, textClassifierSettings, TextClassifier.NO_OP);
    }

    TextSelection suggestSelection(TextSelection.Request request) {
        Preconditions.checkNotNull(request);
        checkMainThread();
        try {
            int endIndex = request.getEndIndex() - request.getStartIndex();
            String string = request.getText().toString();
            if (string.length() > 0 && endIndex <= this.settings.getSuggestSelectionMaxRangeLength()) {
                String strConcatenateLocales = concatenateLocales(request.getDefaultLocales());
                String strJoin = String.join(",", lambda$suggestConversationActions$1(getLangIdImpl(), request.getText()));
                ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.systemDefault());
                AnnotatorModel annotatorImpl = getAnnotatorImpl(request.getDefaultLocales());
                int[] iArrSuggestSelection = annotatorImpl.suggestSelection(string, request.getStartIndex(), request.getEndIndex(), new AnnotatorModel.SelectionOptions(strConcatenateLocales, strJoin));
                int i = iArrSuggestSelection[0];
                int i2 = iArrSuggestSelection[1];
                if (i < i2 && i >= 0 && i2 <= string.length() && i <= request.getStartIndex() && i2 >= request.getEndIndex()) {
                    TextSelection.Builder builder = new TextSelection.Builder(i, i2);
                    AnnotatorModel.ClassificationResult[] classificationResultArrClassifyText = annotatorImpl.classifyText(string, i, i2, new AnnotatorModel.ClassificationOptions(zonedDateTimeNow.toInstant().toEpochMilli(), zonedDateTimeNow.getZone().getId(), strConcatenateLocales, strJoin), null, null);
                    int length = classificationResultArrClassifyText.length;
                    for (int i3 = 0; i3 < length; i3++) {
                        builder.setEntityType(classificationResultArrClassifyText[i3].getCollection(), classificationResultArrClassifyText[i3].getScore());
                    }
                    return builder.setId(createAnnotatorId(string, request.getStartIndex(), request.getEndIndex())).build();
                }
                TcLog.d("TextClassifierImpl", "Got bad indices for input text. Ignoring result.");
            }
        } catch (Throwable th) {
            TcLog.e("TextClassifierImpl", "Error suggesting selection for text. No changes to selection suggested.", th);
        }
        return this.fallback.suggestSelection(request);
    }

    TextClassification classifyText(TextClassification.Request request) {
        ZonedDateTime zonedDateTimeNow;
        Preconditions.checkNotNull(request);
        checkMainThread();
        try {
            Optional<LangIdModel> langIdImpl = getLangIdImpl();
            ImmutableList<String> immutableListLambda$suggestConversationActions$1 = lambda$suggestConversationActions$1(langIdImpl, request.getText());
            int endIndex = request.getEndIndex() - request.getStartIndex();
            String string = request.getText().toString();
            if (string.length() > 0 && endIndex <= this.settings.getClassifyTextMaxRangeLength()) {
                String strConcatenateLocales = concatenateLocales(request.getDefaultLocales());
                if (request.getReferenceTime() != null) {
                    zonedDateTimeNow = request.getReferenceTime();
                } else {
                    zonedDateTimeNow = ZonedDateTime.now(ZoneId.systemDefault());
                }
                AnnotatorModel.ClassificationResult[] classificationResultArrClassifyText = getAnnotatorImpl(request.getDefaultLocales()).classifyText(string, request.getStartIndex(), request.getEndIndex(), new AnnotatorModel.ClassificationOptions(zonedDateTimeNow.toInstant().toEpochMilli(), zonedDateTimeNow.getZone().getId(), strConcatenateLocales, String.join(",", immutableListLambda$suggestConversationActions$1), AnnotatorModel.AnnotationUsecase.SMART.getValue(), LocaleList.getDefault().toLanguageTags()), this.context, getResourceLocalesString());
                if (classificationResultArrClassifyText.length > 0) {
                    return createClassificationResult(classificationResultArrClassifyText, string, request.getStartIndex(), request.getEndIndex(), langIdImpl);
                }
            }
        } catch (Throwable th) {
            TcLog.e("TextClassifierImpl", "Error getting text classification info.", th);
        }
        return this.fallback.classifyText(request);
    }

    TextLinks generateLinks(TextLinks.Request request) {
        Collection<String> entityListDefault;
        String callingPackageName;
        Optional<ResultIdUtils.ModelInfo> optionalTransform;
        Optional<ResultIdUtils.ModelInfo> optionalTransform2;
        Preconditions.checkNotNull(request);
        Preconditions.checkArgument(request.getText().length() <= getMaxGenerateLinksTextLength(), "text.length() cannot be greater than %s", getMaxGenerateLinksTextLength());
        checkMainThread();
        String string = request.getText().toString();
        TextLinks.Builder builder = new TextLinks.Builder(string);
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.systemDefault());
            if (request.getEntityConfig() != null) {
                entityListDefault = request.getEntityConfig().resolveEntityListModifications(getEntitiesForHints(request.getEntityConfig().getHints()));
            } else {
                entityListDefault = this.settings.getEntityListDefault();
            }
            String strConcatenateLocales = concatenateLocales(request.getDefaultLocales());
            ImmutableList<String> immutableListLambda$suggestConversationActions$1 = lambda$suggestConversationActions$1(getLangIdImpl(), request.getText());
            AnnotatorModel annotatorImpl = getAnnotatorImpl(request.getDefaultLocales());
            boolean zIsSerializedEntityDataEnabled = ExtrasUtils.isSerializedEntityDataEnabled(request);
            for (AnnotatorModel.AnnotatedSpan annotatedSpan : annotatorImpl.annotate(string, new AnnotatorModel.AnnotationOptions(zonedDateTimeNow.toInstant().toEpochMilli(), zonedDateTimeNow.getZone().getId(), strConcatenateLocales, String.join(",", immutableListLambda$suggestConversationActions$1), entityListDefault, AnnotatorModel.AnnotationUsecase.SMART.getValue(), zIsSerializedEntityDataEnabled))) {
                AnnotatorModel.ClassificationResult[] classification = annotatedSpan.getClassification();
                if (classification.length != 0 && entityListDefault.contains(classification[0].getCollection())) {
                    ArrayMap arrayMap = new ArrayMap();
                    for (int i = 0; i < classification.length; i++) {
                        arrayMap.put(classification[i].getCollection(), Float.valueOf(classification[i].getScore()));
                    }
                    Bundle bundle = new Bundle();
                    if (zIsSerializedEntityDataEnabled) {
                        ExtrasUtils.putEntities(bundle, classification);
                    }
                    builder.addLink(annotatedSpan.getStartIndex(), annotatedSpan.getEndIndex(), arrayMap, bundle);
                }
            }
            TextLinks textLinksBuild = builder.build();
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            if (request.getCallingPackageName() == null) {
                callingPackageName = this.context.getPackageName();
            } else {
                callingPackageName = request.getCallingPackageName();
            }
            String str = callingPackageName;
            synchronized (this.lock) {
                optionalTransform = Optional.fromNullable(this.annotatorModelInUse).transform($$Lambda$2YrDcjRKLOb8HwDLV5g9t6JgsSg.INSTANCE);
                optionalTransform2 = Optional.fromNullable(this.langIdModelInUse).transform($$Lambda$2YrDcjRKLOb8HwDLV5g9t6JgsSg.INSTANCE);
            }
            this.generateLinksLogger.logGenerateLinks(request.getText(), textLinksBuild, str, jCurrentTimeMillis2 - jCurrentTimeMillis, optionalTransform, optionalTransform2);
            return textLinksBuild;
        } catch (Throwable th) {
            TcLog.e("TextClassifierImpl", "Error getting links info.", th);
            return this.fallback.generateLinks(request);
        }
    }

    int getMaxGenerateLinksTextLength() {
        return this.settings.getGenerateLinksMaxTextLength();
    }

    private Collection<String> getEntitiesForHints(Collection<String> collection) {
        boolean zContains = collection.contains("android.text_is_editable");
        if (zContains == collection.contains("android.text_is_not_editable")) {
            return this.settings.getEntityListDefault();
        }
        if (zContains) {
            return this.settings.getEntityListEditable();
        }
        return this.settings.getEntityListNotEditable();
    }

    void onSelectionEvent(SelectionEvent selectionEvent) {
        TextClassifierEvent textClassifierEvent = SelectionEventConverter.toTextClassifierEvent(selectionEvent);
        if (textClassifierEvent == null) {
            return;
        }
        onTextClassifierEvent(selectionEvent.getSessionId(), textClassifierEvent);
    }

    void onTextClassifierEvent(TextClassificationSessionId textClassificationSessionId, TextClassifierEvent textClassifierEvent) {
        this.textClassifierEventLogger.writeEvent(TextClassificationSessionIdConverter.fromPlatform(textClassificationSessionId), TextClassifierEventConverter.fromPlatform(textClassifierEvent));
    }

    TextLanguage detectLanguage(TextLanguage.Request request) {
        Preconditions.checkNotNull(request);
        checkMainThread();
        try {
            TextLanguage.Builder builder = new TextLanguage.Builder();
            Optional<LangIdModel> langIdImpl = getLangIdImpl();
            if (langIdImpl.isPresent()) {
                LangIdModel.LanguageResult[] languageResultArrDetectLanguages = langIdImpl.get().detectLanguages(request.getText().toString());
                for (int i = 0; i < languageResultArrDetectLanguages.length; i++) {
                    builder.putLocale(ULocale.forLanguageTag(languageResultArrDetectLanguages[i].getLanguage()), languageResultArrDetectLanguages[i].getScore());
                }
                return builder.build();
            }
        } catch (Throwable th) {
            TcLog.e("TextClassifierImpl", "Error detecting text language.", th);
        }
        return this.fallback.detectLanguage(request);
    }

    ConversationActions suggestConversationActions(ConversationActions.Request request) {
        Preconditions.checkNotNull(request);
        checkMainThread();
        try {
            ActionsSuggestionsModel actionsImpl = getActionsImpl();
            if (actionsImpl == null) {
                return this.fallback.suggestConversationActions(request);
            }
            final Optional<LangIdModel> langIdImpl = getLangIdImpl();
            ActionsSuggestionsModel.ConversationMessage[] nativeMessages = ActionsSuggestionsHelper.toNativeMessages(request.getConversation(), new Function() { // from class: com.android.textclassifier.-$$Lambda$TextClassifierImpl$vIDgmWc1v5udtaerRtDyTyU4pws
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f$0.lambda$suggestConversationActions$1$TextClassifierImpl(langIdImpl, (CharSequence) obj);
                }
            });
            if (nativeMessages.length == 0) {
                return this.fallback.suggestConversationActions(request);
            }
            return createConversationActionResult(request, actionsImpl.suggestActionsWithIntents(new ActionsSuggestionsModel.Conversation(nativeMessages), null, this.context, getResourceLocalesString(), getAnnotatorImpl(LocaleList.getDefault())));
        } catch (Throwable th) {
            TcLog.e("TextClassifierImpl", "Error suggesting conversation actions.", th);
            return this.fallback.suggestConversationActions(request);
        }
    }

    private ConversationActions createConversationActionResult(ConversationActions.Request request, ActionsSuggestionsModel.ActionSuggestion[] actionSuggestionArr) {
        ConversationActions conversationActions;
        Collection<String> collectionResolveActionTypesFromRequest = resolveActionTypesFromRequest(request);
        ArrayList arrayList = new ArrayList();
        for (ActionsSuggestionsModel.ActionSuggestion actionSuggestion : actionSuggestionArr) {
            String actionType = actionSuggestion.getActionType();
            if (collectionResolveActionTypesFromRequest.contains(actionType)) {
                LabeledIntent.Result resultCreateLabeledIntentResult = ActionsSuggestionsHelper.createLabeledIntentResult(this.context, this.templateIntentFactory, actionSuggestion);
                RemoteAction remoteAction = null;
                Bundle bundle = new Bundle();
                if (resultCreateLabeledIntentResult != null) {
                    remoteAction = resultCreateLabeledIntentResult.remoteAction.toRemoteAction();
                    ExtrasUtils.putActionIntent(bundle, stripPackageInfoFromIntent(resultCreateLabeledIntentResult.resolvedIntent));
                }
                ExtrasUtils.putSerializedEntityData(bundle, actionSuggestion.getSerializedEntityData());
                ExtrasUtils.putEntitiesExtras(bundle, TemplateIntentFactory.nameVariantsToBundle(actionSuggestion.getEntityData()));
                arrayList.add(new ConversationAction.Builder(actionType).setConfidenceScore(actionSuggestion.getScore()).setTextReply(actionSuggestion.getResponseText()).setAction(remoteAction).setExtras(bundle).build());
            }
        }
        List<ConversationAction> listRemoveActionsWithDuplicates = ActionsSuggestionsHelper.removeActionsWithDuplicates(arrayList);
        if (request.getMaxSuggestions() >= 0 && listRemoveActionsWithDuplicates.size() > request.getMaxSuggestions()) {
            listRemoveActionsWithDuplicates = listRemoveActionsWithDuplicates.subList(0, request.getMaxSuggestions());
        }
        synchronized (this.lock) {
            conversationActions = new ConversationActions(listRemoveActionsWithDuplicates, ActionsSuggestionsHelper.createResultId(this.context, request.getConversation(), Optional.fromNullable(this.actionModelInUse), Optional.fromNullable(this.annotatorModelInUse), Optional.fromNullable(this.langIdModelInUse)));
        }
        return conversationActions;
    }

    private Collection<String> resolveActionTypesFromRequest(ConversationActions.Request request) {
        List<String> inAppConversationActionTypes;
        if (request.getHints().contains("notification")) {
            inAppConversationActionTypes = this.settings.getNotificationConversationActionTypes();
        } else {
            inAppConversationActionTypes = this.settings.getInAppConversationActionTypes();
        }
        return request.getTypeConfig().resolveEntityListModifications(inAppConversationActionTypes);
    }

    private AnnotatorModel getAnnotatorImpl(LocaleList localeList) throws FileNotFoundException {
        AnnotatorModel annotatorModel;
        synchronized (this.lock) {
            if (localeList == null) {
                try {
                    localeList = LocaleList.getDefault();
                } catch (Throwable th) {
                    throw th;
                }
            }
            ModelFileManager.ModelFile modelFileFindBestModelFile = this.annotatorModelFileManager.findBestModelFile(localeList);
            if (modelFileFindBestModelFile == null) {
                throw new FileNotFoundException("No annotator model for " + localeList.toLanguageTags());
            }
            if (this.annotatorImpl == null || !Objects.equals(this.annotatorModelInUse, modelFileFindBestModelFile)) {
                TcLog.d("TextClassifierImpl", "Loading " + modelFileFindBestModelFile);
                ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(new File(modelFileFindBestModelFile.getPath()), 268435456);
                if (parcelFileDescriptorOpen != null) {
                    try {
                        this.annotatorImpl = new AnnotatorModel(parcelFileDescriptorOpen.getFd());
                        Optional<LangIdModel> langIdImpl = getLangIdImpl();
                        if (langIdImpl.isPresent()) {
                            this.annotatorImpl.setLangIdModel(langIdImpl.get());
                        }
                        this.annotatorModelInUse = modelFileFindBestModelFile;
                    } finally {
                        maybeCloseAndLogError(parcelFileDescriptorOpen);
                    }
                }
            }
            annotatorModel = this.annotatorImpl;
        }
        return annotatorModel;
    }

    private Optional<LangIdModel> getLangIdImpl() {
        synchronized (this.lock) {
            ModelFileManager.ModelFile modelFileFindBestModelFile = this.langIdModelFileManager.findBestModelFile(null);
            if (modelFileFindBestModelFile == null) {
                return Optional.absent();
            }
            if (this.langIdImpl == null || !Objects.equals(this.langIdModelInUse, modelFileFindBestModelFile)) {
                TcLog.d("TextClassifierImpl", "Loading " + modelFileFindBestModelFile);
                try {
                    ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(new File(modelFileFindBestModelFile.getPath()), 268435456);
                    if (parcelFileDescriptorOpen != null) {
                        try {
                            this.langIdImpl = new LangIdModel(parcelFileDescriptorOpen.getFd());
                            this.langIdModelInUse = modelFileFindBestModelFile;
                        } finally {
                            maybeCloseAndLogError(parcelFileDescriptorOpen);
                        }
                    }
                } catch (FileNotFoundException e) {
                    TcLog.e("TextClassifierImpl", "Failed to open the LangID model file", e);
                    return Optional.absent();
                }
            }
            return Optional.of(this.langIdImpl);
        }
    }

    private ActionsSuggestionsModel getActionsImpl() throws FileNotFoundException {
        synchronized (this.lock) {
            ModelFileManager.ModelFile modelFileFindBestModelFile = this.actionsModelFileManager.findBestModelFile(LocaleList.getDefault());
            if (modelFileFindBestModelFile == null) {
                return null;
            }
            if (this.actionsImpl == null || !Objects.equals(this.actionModelInUse, modelFileFindBestModelFile)) {
                TcLog.d("TextClassifierImpl", "Loading " + modelFileFindBestModelFile);
                ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(new File(modelFileFindBestModelFile.getPath()), 268435456);
                try {
                    if (parcelFileDescriptorOpen == null) {
                        TcLog.d("TextClassifierImpl", "Failed to read the model file: " + modelFileFindBestModelFile.getPath());
                        return null;
                    }
                    this.actionsImpl = new ActionsSuggestionsModel(parcelFileDescriptorOpen.getFd());
                    this.actionModelInUse = modelFileFindBestModelFile;
                } finally {
                    maybeCloseAndLogError(parcelFileDescriptorOpen);
                }
            }
            return this.actionsImpl;
        }
    }

    private String createAnnotatorId(String str, int i, int i2) {
        String strCreateId;
        synchronized (this.lock) {
            strCreateId = ResultIdUtils.createId(this.context, str, i, i2, ModelFileManager.ModelFile.toModelInfos(Optional.fromNullable(this.annotatorModelInUse), Optional.fromNullable(this.langIdModelInUse)));
        }
        return strCreateId;
    }

    private static String concatenateLocales(LocaleList localeList) {
        return localeList == null ? "" : localeList.toLanguageTags();
    }

    private TextClassification createClassificationResult(AnnotatorModel.ClassificationResult[] classificationResultArr, String str, int i, int i2, Optional<LangIdModel> optional) {
        ImmutableList<LabeledIntent> immutableListCreate;
        TextClassification.Builder text = new TextClassification.Builder().setText(str.substring(i, i2));
        int length = classificationResultArr.length;
        AnnotatorModel.ClassificationResult classificationResult = length > 0 ? classificationResultArr[0] : null;
        for (int i3 = 0; i3 < length; i3++) {
            text.setEntityType(classificationResultArr[i3].getCollection(), classificationResultArr[i3].getScore());
            if (classificationResultArr[i3].getScore() > classificationResult.getScore()) {
                classificationResult = classificationResultArr[i3];
            }
        }
        if (classificationResult == null) {
            immutableListCreate = ImmutableList.of();
        } else {
            immutableListCreate = this.templateIntentFactory.create(classificationResult.getRemoteActionTemplates());
        }
        $$Lambda$TextClassifierImpl$JRO8dEkAfMFOqu4iyLbagDQA_o __lambda_textclassifierimpl_jro8dekafmfoqu4iylbagdqa_o = new LabeledIntent.TitleChooser() { // from class: com.android.textclassifier.-$$Lambda$TextClassifierImpl$JRO8dEkAfMFOqu4-iyLbagDQA_o
            @Override // com.android.textclassifier.common.intent.LabeledIntent.TitleChooser
            public final CharSequence chooseTitle(LabeledIntent labeledIntent, ResolveInfo resolveInfo) {
                return labeledIntent.titleWithoutEntity;
            }
        };
        final ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<LabeledIntent> it = immutableListCreate.iterator();
        boolean z = true;
        while (it.hasNext()) {
            LabeledIntent next = it.next();
            LabeledIntent.Result resultResolve = next.resolve(this.context, __lambda_textclassifierimpl_jro8dekafmfoqu4iylbagdqa_o);
            if (resultResolve != null) {
                Intent intent = resultResolve.resolvedIntent;
                RemoteAction remoteAction = resultResolve.remoteAction.toRemoteAction();
                if (z) {
                    text.setIcon(remoteAction.getIcon().loadDrawable(this.context));
                    text.setLabel(remoteAction.getTitle().toString());
                    text.setIntent(intent);
                    text.setOnClickListener(createIntentOnClickListener(createPendingIntent(this.context, intent, next.requestCode)));
                    z = false;
                }
                text.addAction(remoteAction);
                arrayList.add(intent);
            }
        }
        Bundle bundle = new Bundle();
        Optional optional2 = (Optional) optional.transform(new com.google.common.base.Function() { // from class: com.android.textclassifier.-$$Lambda$TextClassifierImpl$Qwks3xU-K0S5rmi-r5zY7rcmhrA
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return TextClassifierImpl.maybeCreateExtrasForTranslate(arrayList, (LangIdModel) obj);
            }
        }).or(Optional.absent());
        if (optional2.isPresent()) {
            ExtrasUtils.putForeignLanguageExtra(bundle, (Bundle) optional2.get());
        }
        if (arrayList.stream().anyMatch(new Predicate() { // from class: com.android.textclassifier.-$$Lambda$L_UQMPjXwBN0ch4zL2dD82nf9RI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((Intent) obj);
            }
        })) {
            ExtrasUtils.putActionsIntents(bundle, (ArrayList) arrayList.stream().map(new Function() { // from class: com.android.textclassifier.-$$Lambda$TextClassifierImpl$Lko6ClGZ6OU9F7efqKl9WVqP3kc
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TextClassifierImpl.stripPackageInfoFromIntent((Intent) obj);
                }
            }).collect(Collectors.toCollection($$Lambda$OGSS2qx6njxlnp0dnKb4lA3jnw8.INSTANCE)));
        }
        ExtrasUtils.putEntities(bundle, classificationResultArr);
        text.setExtras(bundle);
        return text.setId(createAnnotatorId(str, i, i2)).build();
    }

    private static View.OnClickListener createIntentOnClickListener(final PendingIntent pendingIntent) {
        Preconditions.checkNotNull(pendingIntent);
        return new View.OnClickListener() { // from class: com.android.textclassifier.-$$Lambda$TextClassifierImpl$wlF5Lmygug2HifzLlH779V8jReI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextClassifierImpl.lambda$createIntentOnClickListener$4(pendingIntent, view);
            }
        };
    }

    static /* synthetic */ void lambda$createIntentOnClickListener$4(PendingIntent pendingIntent, View view) {
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            TcLog.e("TextClassifierImpl", "Error sending PendingIntent", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Optional<Bundle> maybeCreateExtrasForTranslate(List<Intent> list, LangIdModel langIdModel) {
        Optional optionalFirst = FluentIterable.from(list).filter(new com.google.common.base.Predicate() { // from class: com.android.textclassifier.-$$Lambda$rm5za8qxlTZUvnydaEE0tVLEw0A
            @Override // com.google.common.base.Predicate
            public final boolean apply(Object obj) {
                return Objects.nonNull((Intent) obj);
            }
        }).filter(new com.google.common.base.Predicate() { // from class: com.android.textclassifier.-$$Lambda$TextClassifierImpl$_9AKIndHE_v2Sz1V8d6jhA82Gc4
            @Override // com.google.common.base.Predicate
            public final boolean apply(Object obj) {
                return "android.intent.action.TRANSLATE".equals(((Intent) obj).getAction());
            }
        }).first();
        if (!optionalFirst.isPresent()) {
            return Optional.absent();
        }
        Pair<String, Float> topLanguage = ExtrasUtils.getTopLanguage((Intent) optionalFirst.get());
        if (topLanguage == null) {
            return Optional.absent();
        }
        return Optional.of(ExtrasUtils.createForeignLanguageExtra(topLanguage.first, topLanguage.second.floatValue(), langIdModel.getVersion()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: detectLanguageTags, reason: merged with bridge method [inline-methods] */
    public ImmutableList<String> lambda$suggestConversationActions$1$TextClassifierImpl(Optional<LangIdModel> optional, final CharSequence charSequence) {
        return (ImmutableList) optional.transform(new com.google.common.base.Function() { // from class: com.android.textclassifier.-$$Lambda$TextClassifierImpl$AY5eoUbVCLXhR9opqmynv0zcSU0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$detectLanguageTags$6$TextClassifierImpl(charSequence, (LangIdModel) obj);
            }
        }).or(ImmutableList.of());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$detectLanguageTags$6, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ ImmutableList lambda$detectLanguageTags$6$TextClassifierImpl(CharSequence charSequence, LangIdModel langIdModel) {
        return ImmutableList.copyOf((Collection) detectLanguages(langIdModel, charSequence, getLangIdThreshold(langIdModel)).getEntities());
    }

    private static EntityConfidence detectLanguages(LangIdModel langIdModel, CharSequence charSequence, float f) {
        LangIdModel.LanguageResult[] languageResultArrDetectLanguages = langIdModel.detectLanguages(charSequence.toString());
        ArrayMap arrayMap = new ArrayMap();
        for (LangIdModel.LanguageResult languageResult : languageResultArrDetectLanguages) {
            if (languageResult.getScore() >= f) {
                arrayMap.put(languageResult.getLanguage(), Float.valueOf(languageResult.getScore()));
            }
        }
        return new EntityConfidence(arrayMap);
    }

    private float getLangIdThreshold(LangIdModel langIdModel) {
        if (this.settings.getLangIdThresholdOverride() >= AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS) {
            return this.settings.getLangIdThresholdOverride();
        }
        return langIdModel.getLangIdThreshold();
    }

    void dump(IndentingPrintWriter indentingPrintWriter) {
        synchronized (this.lock) {
            indentingPrintWriter.println("TextClassifierImpl:");
            indentingPrintWriter.increaseIndent();
            indentingPrintWriter.println("Annotator model file(s):");
            indentingPrintWriter.increaseIndent();
            UnmodifiableIterator<ModelFileManager.ModelFile> it = this.annotatorModelFileManager.listModelFiles().iterator();
            while (it.hasNext()) {
                indentingPrintWriter.println(it.next().toString());
            }
            indentingPrintWriter.decreaseIndent();
            indentingPrintWriter.println("LangID model file(s):");
            indentingPrintWriter.increaseIndent();
            UnmodifiableIterator<ModelFileManager.ModelFile> it2 = this.langIdModelFileManager.listModelFiles().iterator();
            while (it2.hasNext()) {
                indentingPrintWriter.println(it2.next().toString());
            }
            indentingPrintWriter.decreaseIndent();
            indentingPrintWriter.println("Actions model file(s):");
            indentingPrintWriter.increaseIndent();
            UnmodifiableIterator<ModelFileManager.ModelFile> it3 = this.actionsModelFileManager.listModelFiles().iterator();
            while (it3.hasNext()) {
                indentingPrintWriter.println(it3.next().toString());
            }
            indentingPrintWriter.decreaseIndent();
            indentingPrintWriter.printPair("mFallback", this.fallback);
            indentingPrintWriter.decreaseIndent();
            indentingPrintWriter.println();
            this.settings.dump(indentingPrintWriter);
        }
    }

    private String getResourceLocalesString() {
        try {
            return this.context.getResources().getConfiguration().getLocales().toLanguageTags();
        } catch (NullPointerException unused) {
            return LocaleList.getDefault().toLanguageTags();
        }
    }

    private static void maybeCloseAndLogError(ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor == null) {
            return;
        }
        try {
            parcelFileDescriptor.close();
        } catch (IOException e) {
            TcLog.e("TextClassifierImpl", "Error closing file.", e);
        }
    }

    private static void checkMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            TcLog.e("TextClassifierImpl", "TextClassifier called on main thread", new Exception());
        }
    }

    private static PendingIntent createPendingIntent(Context context, Intent intent, int i) {
        return PendingIntent.getActivity(context, i, intent, 134217728);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Intent stripPackageInfoFromIntent(Intent intent) {
        if (intent == null) {
            return null;
        }
        Intent intent2 = new Intent(intent);
        intent2.setPackage(null);
        intent2.setComponent(null);
        return intent2;
    }
}
