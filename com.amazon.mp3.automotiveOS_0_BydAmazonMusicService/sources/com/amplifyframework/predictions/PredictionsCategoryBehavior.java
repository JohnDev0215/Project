package com.amplifyframework.predictions;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.amplifyframework.core.Consumer;
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
public interface PredictionsCategoryBehavior {
    @NonNull
    TextToSpeechOperation<?> convertTextToSpeech(@NonNull String str, @NonNull Consumer<TextToSpeechResult> consumer, @NonNull Consumer<PredictionsException> consumer2);

    @NonNull
    TextToSpeechOperation<?> convertTextToSpeech(@NonNull String str, @NonNull TextToSpeechOptions textToSpeechOptions, @NonNull Consumer<TextToSpeechResult> consumer, @NonNull Consumer<PredictionsException> consumer2);

    @NonNull
    IdentifyOperation<?> identify(@NonNull IdentifyAction identifyAction, @NonNull Bitmap bitmap, @NonNull Consumer<IdentifyResult> consumer, @NonNull Consumer<PredictionsException> consumer2);

    @NonNull
    IdentifyOperation<?> identify(@NonNull IdentifyAction identifyAction, @NonNull Bitmap bitmap, @NonNull IdentifyOptions identifyOptions, @NonNull Consumer<IdentifyResult> consumer, @NonNull Consumer<PredictionsException> consumer2);

    @NonNull
    InterpretOperation<?> interpret(@NonNull String str, @NonNull Consumer<InterpretResult> consumer, @NonNull Consumer<PredictionsException> consumer2);

    @NonNull
    InterpretOperation<?> interpret(@NonNull String str, @NonNull InterpretOptions interpretOptions, @NonNull Consumer<InterpretResult> consumer, @NonNull Consumer<PredictionsException> consumer2);

    @NonNull
    TranslateTextOperation<?> translateText(@NonNull String str, @NonNull Consumer<TranslateTextResult> consumer, @NonNull Consumer<PredictionsException> consumer2);

    @NonNull
    TranslateTextOperation<?> translateText(@NonNull String str, @NonNull LanguageType languageType, @NonNull LanguageType languageType2, @NonNull Consumer<TranslateTextResult> consumer, @NonNull Consumer<PredictionsException> consumer2);

    @NonNull
    TranslateTextOperation<?> translateText(@NonNull String str, @NonNull LanguageType languageType, @NonNull LanguageType languageType2, @NonNull TranslateTextOptions translateTextOptions, @NonNull Consumer<TranslateTextResult> consumer, @NonNull Consumer<PredictionsException> consumer2);

    @NonNull
    TranslateTextOperation<?> translateText(@NonNull String str, @NonNull TranslateTextOptions translateTextOptions, @NonNull Consumer<TranslateTextResult> consumer, @NonNull Consumer<PredictionsException> consumer2);
}
