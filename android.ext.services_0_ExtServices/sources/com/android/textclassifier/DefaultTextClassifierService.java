package com.android.textclassifier;

import android.os.CancellationSignal;
import android.service.textclassifier.TextClassifierService;
import android.view.textclassifier.ConversationActions;
import android.view.textclassifier.SelectionEvent;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassificationSessionId;
import android.view.textclassifier.TextClassifierEvent;
import android.view.textclassifier.TextLanguage;
import android.view.textclassifier.TextLinks;
import android.view.textclassifier.TextSelection;
import com.android.textclassifier.common.base.TcLog;
import com.android.textclassifier.utils.IndentingPrintWriter;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultTextClassifierService extends TextClassifierService {
    private final ListeningExecutorService lowPriorityExecutor;
    private final ListeningExecutorService normPriorityExecutor;
    private TextClassifierImpl textClassifier;

    public DefaultTextClassifierService() {
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        threadFactoryBuilder.setNameFormat("tcs-norm-prio-executor");
        threadFactoryBuilder.setPriority(5);
        this.normPriorityExecutor = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2, threadFactoryBuilder.build()));
        ThreadFactoryBuilder threadFactoryBuilder2 = new ThreadFactoryBuilder();
        threadFactoryBuilder2.setNameFormat("tcs-low-prio-executor");
        threadFactoryBuilder2.setPriority(4);
        this.lowPriorityExecutor = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor(threadFactoryBuilder2.build()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate() {
        super.onCreate();
        this.textClassifier = new TextClassifierImpl(this, new TextClassifierSettings());
    }

    public void onSuggestSelection(TextClassificationSessionId textClassificationSessionId, final TextSelection.Request request, CancellationSignal cancellationSignal, TextClassifierService.Callback<TextSelection> callback) {
        handleRequestAsync(new Callable() { // from class: com.android.textclassifier.-$$Lambda$DefaultTextClassifierService$w-rc-EC-vdc0nPUCi2eXlf71Smw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$onSuggestSelection$0$DefaultTextClassifierService(request);
            }
        }, callback, cancellationSignal);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onSuggestSelection$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ TextSelection lambda$onSuggestSelection$0$DefaultTextClassifierService(TextSelection.Request request) throws Exception {
        return this.textClassifier.suggestSelection(request);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onClassifyText$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ TextClassification lambda$onClassifyText$1$DefaultTextClassifierService(TextClassification.Request request) throws Exception {
        return this.textClassifier.classifyText(request);
    }

    public void onClassifyText(TextClassificationSessionId textClassificationSessionId, final TextClassification.Request request, CancellationSignal cancellationSignal, TextClassifierService.Callback<TextClassification> callback) {
        handleRequestAsync(new Callable() { // from class: com.android.textclassifier.-$$Lambda$DefaultTextClassifierService$kes_Pfblp-H2B-k6w462oJ3Jec0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$onClassifyText$1$DefaultTextClassifierService(request);
            }
        }, callback, cancellationSignal);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onGenerateLinks$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ TextLinks lambda$onGenerateLinks$2$DefaultTextClassifierService(TextLinks.Request request) throws Exception {
        return this.textClassifier.generateLinks(request);
    }

    public void onGenerateLinks(TextClassificationSessionId textClassificationSessionId, final TextLinks.Request request, CancellationSignal cancellationSignal, TextClassifierService.Callback<TextLinks> callback) {
        handleRequestAsync(new Callable() { // from class: com.android.textclassifier.-$$Lambda$DefaultTextClassifierService$yXvzteEXitEHOLvc8n1LDN1uTOg
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$onGenerateLinks$2$DefaultTextClassifierService(request);
            }
        }, callback, cancellationSignal);
    }

    public void onSuggestConversationActions(TextClassificationSessionId textClassificationSessionId, final ConversationActions.Request request, CancellationSignal cancellationSignal, TextClassifierService.Callback<ConversationActions> callback) {
        handleRequestAsync(new Callable() { // from class: com.android.textclassifier.-$$Lambda$DefaultTextClassifierService$pSD1VXsSpo8bxujd321iT-wCcUQ
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$onSuggestConversationActions$3$DefaultTextClassifierService(request);
            }
        }, callback, cancellationSignal);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onSuggestConversationActions$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ ConversationActions lambda$onSuggestConversationActions$3$DefaultTextClassifierService(ConversationActions.Request request) throws Exception {
        return this.textClassifier.suggestConversationActions(request);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onDetectLanguage$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ TextLanguage lambda$onDetectLanguage$4$DefaultTextClassifierService(TextLanguage.Request request) throws Exception {
        return this.textClassifier.detectLanguage(request);
    }

    public void onDetectLanguage(TextClassificationSessionId textClassificationSessionId, final TextLanguage.Request request, CancellationSignal cancellationSignal, TextClassifierService.Callback<TextLanguage> callback) {
        handleRequestAsync(new Callable() { // from class: com.android.textclassifier.-$$Lambda$DefaultTextClassifierService$8W0T12VmVMoXcPndlReYOGXd2o4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$onDetectLanguage$4$DefaultTextClassifierService(request);
            }
        }, callback, cancellationSignal);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onSelectionEvent$5, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onSelectionEvent$5$DefaultTextClassifierService(SelectionEvent selectionEvent) {
        this.textClassifier.onSelectionEvent(selectionEvent);
    }

    public void onSelectionEvent(TextClassificationSessionId textClassificationSessionId, final SelectionEvent selectionEvent) {
        handleEvent(new Runnable() { // from class: com.android.textclassifier.-$$Lambda$DefaultTextClassifierService$pGow8bhHsbHzP7zc7oT6V_td0jw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSelectionEvent$5$DefaultTextClassifierService(selectionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onTextClassifierEvent$6, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onTextClassifierEvent$6$DefaultTextClassifierService(TextClassificationSessionId textClassificationSessionId, TextClassifierEvent textClassifierEvent) {
        this.textClassifier.onTextClassifierEvent(textClassificationSessionId, textClassifierEvent);
    }

    public void onTextClassifierEvent(final TextClassificationSessionId textClassificationSessionId, final TextClassifierEvent textClassifierEvent) {
        handleEvent(new Runnable() { // from class: com.android.textclassifier.-$$Lambda$DefaultTextClassifierService$SgQiLpMuMlFzwS2yH533CFu21w4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onTextClassifierEvent$6$DefaultTextClassifierService(textClassificationSessionId, textClassifierEvent);
            }
        });
    }

    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter);
        this.textClassifier.dump(indentingPrintWriter);
        indentingPrintWriter.flush();
    }

    private <T> void handleRequestAsync(Callable<T> callable, final TextClassifierService.Callback<T> callback, CancellationSignal cancellationSignal) {
        final ListenableFuture<T> listenableFutureSubmit = this.normPriorityExecutor.submit((Callable) callable);
        Futures.addCallback(listenableFutureSubmit, new FutureCallback<T>(this) { // from class: com.android.textclassifier.DefaultTextClassifierService.1
            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(T t) {
                callback.onSuccess(t);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable th) {
                TcLog.e("default_tcs", "onFailure: ", th);
                callback.onFailure(th.getMessage());
            }
        }, MoreExecutors.directExecutor());
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: com.android.textclassifier.-$$Lambda$DefaultTextClassifierService$uayK-PvjyPV4tI4hMS5w5SeT0rA
            @Override // android.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                listenableFutureSubmit.cancel(true);
            }
        });
    }

    private void handleEvent(final Runnable runnable) {
        Futures.addCallback(this.lowPriorityExecutor.submit(new Callable() { // from class: com.android.textclassifier.-$$Lambda$DefaultTextClassifierService$yNSEFy3JVW1IOjoC_clSd0dxJhU
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DefaultTextClassifierService.lambda$handleEvent$8(runnable);
            }
        }), new FutureCallback<Void>(this) { // from class: com.android.textclassifier.DefaultTextClassifierService.2
            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(Void r1) {
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable th) {
                TcLog.e("default_tcs", "onFailure: ", th);
            }
        }, MoreExecutors.directExecutor());
    }

    static /* synthetic */ Void lambda$handleEvent$8(Runnable runnable) throws Exception {
        runnable.run();
        return null;
    }
}
