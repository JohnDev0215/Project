package com.amplifyframework.rx;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.predictions.PredictionsCategoryBehavior;
import com.amplifyframework.predictions.PredictionsException;
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
import com.amplifyframework.rx.RxAdapters;
import io.reactivex.rxjava3.core.Single;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class RxPredictionsBinding implements RxPredictionsCategoryBehavior {
    private final PredictionsCategoryBehavior delegate;

    RxPredictionsBinding() {
        this(Amplify.Predictions);
    }

    @VisibleForTesting
    RxPredictionsBinding(@NonNull PredictionsCategoryBehavior predictionsCategoryBehavior) {
        Objects.requireNonNull(predictionsCategoryBehavior);
        this.delegate = predictionsCategoryBehavior;
    }

    @Override // com.amplifyframework.rx.RxPredictionsCategoryBehavior
    public Single<TextToSpeechResult> convertTextToSpeech(@NonNull final String str) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxPredictionsBinding$$ExternalSyntheticLambda7
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$convertTextToSpeech$0(str, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$convertTextToSpeech$0(String str, Consumer consumer, Consumer consumer2) {
        this.delegate.convertTextToSpeech(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxPredictionsCategoryBehavior
    public Single<TextToSpeechResult> convertTextToSpeech(@NonNull final String str, @NonNull final TextToSpeechOptions textToSpeechOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxPredictionsBinding$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$convertTextToSpeech$1(str, textToSpeechOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$convertTextToSpeech$1(String str, TextToSpeechOptions textToSpeechOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.convertTextToSpeech(str, textToSpeechOptions, consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$translateText$2(String str, Consumer consumer, Consumer consumer2) {
        this.delegate.translateText(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxPredictionsCategoryBehavior
    public Single<TranslateTextResult> translateText(@NonNull final String str) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxPredictionsBinding$$ExternalSyntheticLambda9
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$translateText$2(str, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$translateText$3(String str, TranslateTextOptions translateTextOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.translateText(str, translateTextOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxPredictionsCategoryBehavior
    public Single<TranslateTextResult> translateText(@NonNull final String str, @NonNull final TranslateTextOptions translateTextOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxPredictionsBinding$$ExternalSyntheticLambda6
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$translateText$3(str, translateTextOptions, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxPredictionsCategoryBehavior
    public Single<TranslateTextResult> translateText(@NonNull final String str, @NonNull final LanguageType languageType, @NonNull final LanguageType languageType2) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxPredictionsBinding$$ExternalSyntheticLambda3
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$translateText$4(str, languageType, languageType2, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$translateText$4(String str, LanguageType languageType, LanguageType languageType2, Consumer consumer, Consumer consumer2) {
        this.delegate.translateText(str, languageType, languageType2, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxPredictionsCategoryBehavior
    public Single<TranslateTextResult> translateText(@NonNull final String str, @NonNull final LanguageType languageType, @NonNull final LanguageType languageType2, @NonNull final TranslateTextOptions translateTextOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxPredictionsBinding$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$translateText$5(str, languageType, languageType2, translateTextOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$translateText$5(String str, LanguageType languageType, LanguageType languageType2, TranslateTextOptions translateTextOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.translateText(str, languageType, languageType2, translateTextOptions, consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$identify$6(IdentifyAction identifyAction, Bitmap bitmap, Consumer consumer, Consumer consumer2) {
        this.delegate.identify(identifyAction, bitmap, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxPredictionsCategoryBehavior
    public Single<IdentifyResult> identify(@NonNull final IdentifyAction identifyAction, @NonNull final Bitmap bitmap) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxPredictionsBinding$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$identify$6(identifyAction, bitmap, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxPredictionsCategoryBehavior
    public Single<IdentifyResult> identify(@NonNull final IdentifyAction identifyAction, @NonNull final Bitmap bitmap, @NonNull final IdentifyOptions identifyOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxPredictionsBinding$$ExternalSyntheticLambda4
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$identify$7(identifyAction, bitmap, identifyOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$identify$7(IdentifyAction identifyAction, Bitmap bitmap, IdentifyOptions identifyOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.identify(identifyAction, bitmap, identifyOptions, consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$interpret$8(String str, Consumer consumer, Consumer consumer2) {
        this.delegate.interpret(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxPredictionsCategoryBehavior
    public Single<InterpretResult> interpret(@NonNull final String str) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxPredictionsBinding$$ExternalSyntheticLambda5
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$interpret$8(str, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$interpret$9(String str, InterpretOptions interpretOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.interpret(str, interpretOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxPredictionsCategoryBehavior
    public Single<InterpretResult> interpret(@NonNull final String str, @NonNull final InterpretOptions interpretOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxPredictionsBinding$$ExternalSyntheticLambda2
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$interpret$9(str, interpretOptions, consumer, consumer2);
            }
        });
    }

    private static <T> Single<T> toSingle(RxAdapters.VoidBehaviors.ResultEmitter<T, PredictionsException> resultEmitter) {
        return RxAdapters.VoidBehaviors.toSingle(resultEmitter);
    }
}
