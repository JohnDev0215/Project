package com.amplifyframework.rx;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.IdentifyAction;
import com.amplifyframework.predictions.models.LanguageType;
import com.amplifyframework.predictions.options.IdentifyOptions;
import com.amplifyframework.predictions.options.InterpretOptions;
import com.amplifyframework.predictions.options.TextToSpeechOptions;
import com.amplifyframework.predictions.options.TranslateTextOptions;
import com.amplifyframework.predictions.result.IdentifyResult;
import com.amplifyframework.predictions.result.InterpretResult;
import com.amplifyframework.predictions.result.TextToSpeechResult;
import com.amplifyframework.predictions.result.TranslateTextResult;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes5.dex */
public interface RxPredictionsCategoryBehavior {
    Single<TextToSpeechResult> convertTextToSpeech(@NonNull String str);

    Single<TextToSpeechResult> convertTextToSpeech(@NonNull String str, @NonNull TextToSpeechOptions textToSpeechOptions);

    Single<IdentifyResult> identify(@NonNull IdentifyAction identifyAction, @NonNull Bitmap bitmap);

    Single<IdentifyResult> identify(@NonNull IdentifyAction identifyAction, @NonNull Bitmap bitmap, @NonNull IdentifyOptions identifyOptions);

    Single<InterpretResult> interpret(@NonNull String str);

    Single<InterpretResult> interpret(@NonNull String str, @NonNull InterpretOptions interpretOptions);

    Single<TranslateTextResult> translateText(@NonNull String str);

    Single<TranslateTextResult> translateText(@NonNull String str, @NonNull LanguageType languageType, @NonNull LanguageType languageType2);

    Single<TranslateTextResult> translateText(@NonNull String str, @NonNull LanguageType languageType, @NonNull LanguageType languageType2, @NonNull TranslateTextOptions translateTextOptions);

    Single<TranslateTextResult> translateText(@NonNull String str, @NonNull TranslateTextOptions translateTextOptions);
}
