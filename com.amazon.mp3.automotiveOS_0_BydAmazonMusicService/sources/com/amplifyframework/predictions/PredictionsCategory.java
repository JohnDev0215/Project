package com.amplifyframework.predictions;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.category.Category;
import com.amplifyframework.core.category.CategoryType;
import com.amplifyframework.predictions.models.IdentifyAction;
import com.amplifyframework.predictions.models.LanguageType;
import com.amplifyframework.predictions.operation.IdentifyOperation;
import com.amplifyframework.predictions.operation.InterpretOperation;
import com.amplifyframework.predictions.operation.TextToSpeechOperation;
import com.amplifyframework.predictions.operation.TranslateTextOperation;
import com.amplifyframework.predictions.options.IdentifyOptions;
import com.amplifyframework.predictions.options.InterpretOptions;
import com.amplifyframework.predictions.options.TextToSpeechOptions;
import com.amplifyframework.predictions.options.TranslateTextOptions;
import com.amplifyframework.predictions.result.IdentifyResult;
import com.amplifyframework.predictions.result.InterpretResult;
import com.amplifyframework.predictions.result.TextToSpeechResult;
import com.amplifyframework.predictions.result.TranslateTextResult;

/* JADX INFO: loaded from: classes5.dex */
public final class PredictionsCategory extends Category<PredictionsPlugin<?>> implements PredictionsCategoryBehavior {
    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public CategoryType getCategoryType() {
        return CategoryType.PREDICTIONS;
    }

    @Override // com.amplifyframework.predictions.PredictionsCategoryBehavior
    @NonNull
    public TextToSpeechOperation<?> convertTextToSpeech(@NonNull String str, @NonNull Consumer<TextToSpeechResult> consumer, @NonNull Consumer<PredictionsException> consumer2) {
        return getSelectedPlugin().convertTextToSpeech(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.predictions.PredictionsCategoryBehavior
    @NonNull
    public TextToSpeechOperation<?> convertTextToSpeech(@NonNull String str, @NonNull TextToSpeechOptions textToSpeechOptions, @NonNull Consumer<TextToSpeechResult> consumer, @NonNull Consumer<PredictionsException> consumer2) {
        return getSelectedPlugin().convertTextToSpeech(str, textToSpeechOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.predictions.PredictionsCategoryBehavior
    @NonNull
    public TranslateTextOperation<?> translateText(@NonNull String str, @NonNull Consumer<TranslateTextResult> consumer, @NonNull Consumer<PredictionsException> consumer2) {
        return getSelectedPlugin().translateText(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.predictions.PredictionsCategoryBehavior
    @NonNull
    public TranslateTextOperation<?> translateText(@NonNull String str, @NonNull TranslateTextOptions translateTextOptions, @NonNull Consumer<TranslateTextResult> consumer, @NonNull Consumer<PredictionsException> consumer2) {
        return getSelectedPlugin().translateText(str, translateTextOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.predictions.PredictionsCategoryBehavior
    @NonNull
    public TranslateTextOperation<?> translateText(@NonNull String str, @NonNull LanguageType languageType, @NonNull LanguageType languageType2, @NonNull Consumer<TranslateTextResult> consumer, @NonNull Consumer<PredictionsException> consumer2) {
        return getSelectedPlugin().translateText(str, languageType, languageType2, consumer, consumer2);
    }

    @Override // com.amplifyframework.predictions.PredictionsCategoryBehavior
    @NonNull
    public TranslateTextOperation<?> translateText(@NonNull String str, @NonNull LanguageType languageType, @NonNull LanguageType languageType2, @NonNull TranslateTextOptions translateTextOptions, @NonNull Consumer<TranslateTextResult> consumer, @NonNull Consumer<PredictionsException> consumer2) {
        return getSelectedPlugin().translateText(str, languageType, languageType2, translateTextOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.predictions.PredictionsCategoryBehavior
    @NonNull
    public IdentifyOperation<?> identify(@NonNull IdentifyAction identifyAction, @NonNull Bitmap bitmap, @NonNull Consumer<IdentifyResult> consumer, @NonNull Consumer<PredictionsException> consumer2) {
        return getSelectedPlugin().identify(identifyAction, bitmap, consumer, consumer2);
    }

    @Override // com.amplifyframework.predictions.PredictionsCategoryBehavior
    @NonNull
    public IdentifyOperation<?> identify(@NonNull IdentifyAction identifyAction, @NonNull Bitmap bitmap, @NonNull IdentifyOptions identifyOptions, @NonNull Consumer<IdentifyResult> consumer, @NonNull Consumer<PredictionsException> consumer2) {
        return getSelectedPlugin().identify(identifyAction, bitmap, identifyOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.predictions.PredictionsCategoryBehavior
    @NonNull
    public InterpretOperation<?> interpret(@NonNull String str, @NonNull Consumer<InterpretResult> consumer, @NonNull Consumer<PredictionsException> consumer2) {
        return getSelectedPlugin().interpret(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.predictions.PredictionsCategoryBehavior
    @NonNull
    public InterpretOperation<?> interpret(@NonNull String str, @NonNull InterpretOptions interpretOptions, @NonNull Consumer<InterpretResult> consumer, @NonNull Consumer<PredictionsException> consumer2) {
        return getSelectedPlugin().interpret(str, interpretOptions, consumer, consumer2);
    }
}
