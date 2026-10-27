package android.view.textclassifier;

import android.view.textclassifier.ConversationActions;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextLanguage;
import android.view.textclassifier.TextLinks;
import android.view.textclassifier.TextSelection;
import com.android.internal.util.Preconditions;
import java.util.Objects;
import sun.misc.Cleaner;

/* JADX INFO: loaded from: classes3.dex */
final class TextClassificationSession implements TextClassifier {
    private static final String LOG_TAG = "TextClassificationSession";
    private final TextClassificationContext mClassificationContext;
    private final Cleaner mCleaner;
    private final TextClassifier mDelegate;
    private boolean mDestroyed;
    private final SelectionEventHelper mEventHelper;
    private final TextClassificationSessionId mSessionId = new TextClassificationSessionId();

    TextClassificationSession(TextClassificationContext textClassificationContext, TextClassifier textClassifier) {
        this.mClassificationContext = (TextClassificationContext) Objects.requireNonNull(textClassificationContext);
        this.mDelegate = (TextClassifier) Objects.requireNonNull(textClassifier);
        this.mEventHelper = new SelectionEventHelper(this.mSessionId, this.mClassificationContext);
        initializeRemoteSession();
        this.mCleaner = Cleaner.create(this, new CleanerRunnable(this.mEventHelper, this.mDelegate));
    }

    @Override // android.view.textclassifier.TextClassifier
    public TextSelection suggestSelection(TextSelection.Request request) {
        checkDestroyed();
        return this.mDelegate.suggestSelection(request);
    }

    private void initializeRemoteSession() {
        TextClassifier textClassifier = this.mDelegate;
        if (textClassifier instanceof SystemTextClassifier) {
            ((SystemTextClassifier) textClassifier).initializeRemoteSession(this.mClassificationContext, this.mSessionId);
        }
    }

    @Override // android.view.textclassifier.TextClassifier
    public TextClassification classifyText(TextClassification.Request request) {
        checkDestroyed();
        return this.mDelegate.classifyText(request);
    }

    @Override // android.view.textclassifier.TextClassifier
    public TextLinks generateLinks(TextLinks.Request request) {
        checkDestroyed();
        return this.mDelegate.generateLinks(request);
    }

    @Override // android.view.textclassifier.TextClassifier
    public ConversationActions suggestConversationActions(ConversationActions.Request request) {
        checkDestroyed();
        return this.mDelegate.suggestConversationActions(request);
    }

    @Override // android.view.textclassifier.TextClassifier
    public TextLanguage detectLanguage(TextLanguage.Request request) {
        checkDestroyed();
        return this.mDelegate.detectLanguage(request);
    }

    @Override // android.view.textclassifier.TextClassifier
    public int getMaxGenerateLinksTextLength() {
        checkDestroyed();
        return this.mDelegate.getMaxGenerateLinksTextLength();
    }

    @Override // android.view.textclassifier.TextClassifier
    public void onSelectionEvent(SelectionEvent selectionEvent) {
        try {
            if (this.mEventHelper.sanitizeEvent(selectionEvent)) {
                this.mDelegate.onSelectionEvent(selectionEvent);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error reporting text classifier selection event", e);
        }
    }

    @Override // android.view.textclassifier.TextClassifier
    public void onTextClassifierEvent(TextClassifierEvent textClassifierEvent) {
        try {
            textClassifierEvent.mHiddenTempSessionId = this.mSessionId;
            this.mDelegate.onTextClassifierEvent(textClassifierEvent);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error reporting text classifier event", e);
        }
    }

    @Override // android.view.textclassifier.TextClassifier
    public void destroy() {
        this.mCleaner.clean();
        this.mDestroyed = true;
    }

    @Override // android.view.textclassifier.TextClassifier
    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    private void checkDestroyed() {
        if (this.mDestroyed) {
            throw new IllegalStateException("This TextClassification session has been destroyed");
        }
    }

    private static final class SelectionEventHelper {
        private final TextClassificationContext mContext;
        private int mInvocationMethod = 0;
        private SelectionEvent mPrevEvent;
        private final TextClassificationSessionId mSessionId;
        private SelectionEvent mSmartEvent;
        private SelectionEvent mStartEvent;

        SelectionEventHelper(TextClassificationSessionId textClassificationSessionId, TextClassificationContext textClassificationContext) {
            this.mSessionId = (TextClassificationSessionId) Objects.requireNonNull(textClassificationSessionId);
            this.mContext = (TextClassificationContext) Objects.requireNonNull(textClassificationContext);
        }

        boolean sanitizeEvent(SelectionEvent selectionEvent) {
            SelectionEvent selectionEvent2;
            updateInvocationMethod(selectionEvent);
            modifyAutoSelectionEventType(selectionEvent);
            if (selectionEvent.getEventType() != 1 && this.mStartEvent == null) {
                Log.d(TextClassificationSession.LOG_TAG, "Selection session not yet started. Ignoring event");
                return false;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            int eventType = selectionEvent.getEventType();
            if (eventType == 1) {
                Preconditions.checkArgument(selectionEvent.getAbsoluteEnd() == selectionEvent.getAbsoluteStart() + 1);
                selectionEvent.setSessionId(this.mSessionId);
                this.mStartEvent = selectionEvent;
            } else if (eventType == 2) {
                SelectionEvent selectionEvent3 = this.mPrevEvent;
                if (selectionEvent3 != null && selectionEvent3.getAbsoluteStart() == selectionEvent.getAbsoluteStart() && this.mPrevEvent.getAbsoluteEnd() == selectionEvent.getAbsoluteEnd()) {
                    return false;
                }
            } else if (eventType == 3 || eventType == 4 || eventType == 5) {
                this.mSmartEvent = selectionEvent;
            } else if ((eventType == 100 || eventType == 107) && (selectionEvent2 = this.mPrevEvent) != null) {
                selectionEvent.setEntityType(selectionEvent2.getEntityType());
            }
            selectionEvent.setEventTime(jCurrentTimeMillis);
            SelectionEvent selectionEvent4 = this.mStartEvent;
            if (selectionEvent4 != null) {
                selectionEvent.setSessionId(selectionEvent4.getSessionId()).setDurationSinceSessionStart(jCurrentTimeMillis - this.mStartEvent.getEventTime()).setStart(selectionEvent.getAbsoluteStart() - this.mStartEvent.getAbsoluteStart()).setEnd(selectionEvent.getAbsoluteEnd() - this.mStartEvent.getAbsoluteStart());
            }
            SelectionEvent selectionEvent5 = this.mSmartEvent;
            if (selectionEvent5 != null) {
                selectionEvent.setResultId(selectionEvent5.getResultId()).setSmartStart(this.mSmartEvent.getAbsoluteStart() - this.mStartEvent.getAbsoluteStart()).setSmartEnd(this.mSmartEvent.getAbsoluteEnd() - this.mStartEvent.getAbsoluteStart());
            }
            SelectionEvent selectionEvent6 = this.mPrevEvent;
            if (selectionEvent6 != null) {
                selectionEvent.setDurationSincePreviousEvent(jCurrentTimeMillis - selectionEvent6.getEventTime()).setEventIndex(this.mPrevEvent.getEventIndex() + 1);
            }
            this.mPrevEvent = selectionEvent;
            return true;
        }

        void endSession() {
            this.mPrevEvent = null;
            this.mSmartEvent = null;
            this.mStartEvent = null;
        }

        private void updateInvocationMethod(SelectionEvent selectionEvent) {
            selectionEvent.setTextClassificationSessionContext(this.mContext);
            if (selectionEvent.getInvocationMethod() == 0) {
                selectionEvent.setInvocationMethod(this.mInvocationMethod);
            } else {
                this.mInvocationMethod = selectionEvent.getInvocationMethod();
            }
        }

        private void modifyAutoSelectionEventType(SelectionEvent selectionEvent) {
            int eventType = selectionEvent.getEventType();
            if (eventType == 3 || eventType == 4 || eventType == 5) {
                if (SelectionSessionLogger.isPlatformLocalTextClassifierSmartSelection(selectionEvent.getResultId())) {
                    if (selectionEvent.getAbsoluteEnd() - selectionEvent.getAbsoluteStart() > 1) {
                        selectionEvent.setEventType(4);
                        return;
                    } else {
                        selectionEvent.setEventType(3);
                        return;
                    }
                }
                selectionEvent.setEventType(5);
            }
        }
    }

    private static class CleanerRunnable implements Runnable {
        private final TextClassifier mDelegate;
        private final SelectionEventHelper mEventHelper;

        CleanerRunnable(SelectionEventHelper selectionEventHelper, TextClassifier textClassifier) {
            this.mEventHelper = (SelectionEventHelper) Objects.requireNonNull(selectionEventHelper);
            this.mDelegate = (TextClassifier) Objects.requireNonNull(textClassifier);
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mEventHelper.endSession();
            this.mDelegate.destroy();
        }
    }
}
