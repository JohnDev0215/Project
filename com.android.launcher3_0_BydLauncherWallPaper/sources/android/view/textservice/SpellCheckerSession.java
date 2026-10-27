package android.view.textservice;

import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.textservice.ISpellCheckerSession;
import com.android.internal.textservice.ISpellCheckerSessionListener;
import com.android.internal.textservice.ITextServicesSessionListener;
import dalvik.system.CloseGuard;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: classes3.dex */
public class SpellCheckerSession {
    private static final boolean DBG = false;
    private static final int MSG_ON_GET_SUGGESTION_MULTIPLE = 1;
    private static final int MSG_ON_GET_SUGGESTION_MULTIPLE_FOR_SENTENCE = 2;
    public static final String SERVICE_META_DATA = "android.view.textservice.scs";
    private static final String TAG = "SpellCheckerSession";
    private final CloseGuard mGuard;
    private final Handler mHandler;
    private final InternalListener mInternalListener;
    private final SpellCheckerInfo mSpellCheckerInfo;
    private final SpellCheckerSessionListener mSpellCheckerSessionListener;
    private final SpellCheckerSessionListenerImpl mSpellCheckerSessionListenerImpl;
    private final TextServicesManager mTextServicesManager;

    public interface SpellCheckerSessionListener {
        void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr);

        void onGetSuggestions(SuggestionsInfo[] suggestionsInfoArr);
    }

    public SpellCheckerSession(SpellCheckerInfo spellCheckerInfo, TextServicesManager textServicesManager, SpellCheckerSessionListener spellCheckerSessionListener) {
        CloseGuard closeGuard = CloseGuard.get();
        this.mGuard = closeGuard;
        Handler handler = new Handler() { // from class: android.view.textservice.SpellCheckerSession.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    SpellCheckerSession.this.handleOnGetSuggestionsMultiple((SuggestionsInfo[]) message.obj);
                } else {
                    if (i != 2) {
                        return;
                    }
                    SpellCheckerSession.this.handleOnGetSentenceSuggestionsMultiple((SentenceSuggestionsInfo[]) message.obj);
                }
            }
        };
        this.mHandler = handler;
        if (spellCheckerInfo == null || spellCheckerSessionListener == null || textServicesManager == null) {
            throw null;
        }
        this.mSpellCheckerInfo = spellCheckerInfo;
        SpellCheckerSessionListenerImpl spellCheckerSessionListenerImpl = new SpellCheckerSessionListenerImpl(handler);
        this.mSpellCheckerSessionListenerImpl = spellCheckerSessionListenerImpl;
        this.mInternalListener = new InternalListener(spellCheckerSessionListenerImpl);
        this.mTextServicesManager = textServicesManager;
        this.mSpellCheckerSessionListener = spellCheckerSessionListener;
        closeGuard.open("finishSession");
    }

    public boolean isSessionDisconnected() {
        return this.mSpellCheckerSessionListenerImpl.isDisconnected();
    }

    public SpellCheckerInfo getSpellChecker() {
        return this.mSpellCheckerInfo;
    }

    public void cancel() {
        this.mSpellCheckerSessionListenerImpl.cancel();
    }

    public void close() {
        this.mGuard.close();
        this.mSpellCheckerSessionListenerImpl.close();
        this.mTextServicesManager.finishSpellCheckerService(this.mSpellCheckerSessionListenerImpl);
    }

    public void getSentenceSuggestions(TextInfo[] textInfoArr, int i) {
        this.mSpellCheckerSessionListenerImpl.getSentenceSuggestionsMultiple(textInfoArr, i);
    }

    @Deprecated
    public void getSuggestions(TextInfo textInfo, int i) {
        getSuggestions(new TextInfo[]{textInfo}, i, false);
    }

    @Deprecated
    public void getSuggestions(TextInfo[] textInfoArr, int i, boolean z) {
        this.mSpellCheckerSessionListenerImpl.getSuggestionsMultiple(textInfoArr, i, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnGetSuggestionsMultiple(SuggestionsInfo[] suggestionsInfoArr) {
        this.mSpellCheckerSessionListener.onGetSuggestions(suggestionsInfoArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnGetSentenceSuggestionsMultiple(SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr) {
        this.mSpellCheckerSessionListener.onGetSentenceSuggestions(sentenceSuggestionsInfoArr);
    }

    private static final class SpellCheckerSessionListenerImpl extends ISpellCheckerSessionListener.Stub {
        private static final int STATE_CLOSED_AFTER_CONNECTION = 2;
        private static final int STATE_CLOSED_BEFORE_CONNECTION = 3;
        private static final int STATE_CONNECTED = 1;
        private static final int STATE_WAIT_CONNECTION = 0;
        private static final int TASK_CANCEL = 1;
        private static final int TASK_CLOSE = 3;
        private static final int TASK_GET_SUGGESTIONS_MULTIPLE = 2;
        private static final int TASK_GET_SUGGESTIONS_MULTIPLE_FOR_SENTENCE = 4;
        private Handler mAsyncHandler;
        private Handler mHandler;
        private ISpellCheckerSession mISpellCheckerSession;
        private final Queue<SpellCheckerParams> mPendingTasks = new LinkedList();
        private int mState = 0;
        private HandlerThread mThread;

        private static String taskToString(int i) {
            return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "Unexpected task=" + i : "TASK_GET_SUGGESTIONS_MULTIPLE_FOR_SENTENCE" : "TASK_CLOSE" : "TASK_GET_SUGGESTIONS_MULTIPLE" : "TASK_CANCEL";
        }

        private static String stateToString(int i) {
            return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "Unexpected state=" + i : "STATE_CLOSED_BEFORE_CONNECTION" : "STATE_CLOSED_AFTER_CONNECTION" : "STATE_CONNECTED" : "STATE_WAIT_CONNECTION";
        }

        public SpellCheckerSessionListenerImpl(Handler handler) {
            this.mHandler = handler;
        }

        private static class SpellCheckerParams {
            public final boolean mSequentialWords;
            public ISpellCheckerSession mSession;
            public final int mSuggestionsLimit;
            public final TextInfo[] mTextInfos;
            public final int mWhat;

            public SpellCheckerParams(int i, TextInfo[] textInfoArr, int i2, boolean z) {
                this.mWhat = i;
                this.mTextInfos = textInfoArr;
                this.mSuggestionsLimit = i2;
                this.mSequentialWords = z;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void processTask(ISpellCheckerSession iSpellCheckerSession, SpellCheckerParams spellCheckerParams, boolean z) {
            if (z || this.mAsyncHandler == null) {
                int i = spellCheckerParams.mWhat;
                if (i == 1) {
                    try {
                        iSpellCheckerSession.onCancel();
                    } catch (RemoteException e) {
                        Log.e(SpellCheckerSession.TAG, "Failed to cancel " + e);
                    }
                } else if (i == 2) {
                    try {
                        iSpellCheckerSession.onGetSuggestionsMultiple(spellCheckerParams.mTextInfos, spellCheckerParams.mSuggestionsLimit, spellCheckerParams.mSequentialWords);
                    } catch (RemoteException e2) {
                        Log.e(SpellCheckerSession.TAG, "Failed to get suggestions " + e2);
                    }
                } else if (i == 3) {
                    try {
                        iSpellCheckerSession.onClose();
                    } catch (RemoteException e3) {
                        Log.e(SpellCheckerSession.TAG, "Failed to close " + e3);
                    }
                } else if (i == 4) {
                    try {
                        iSpellCheckerSession.onGetSentenceSuggestionsMultiple(spellCheckerParams.mTextInfos, spellCheckerParams.mSuggestionsLimit);
                    } catch (RemoteException e4) {
                        Log.e(SpellCheckerSession.TAG, "Failed to get suggestions " + e4);
                    }
                }
            } else {
                spellCheckerParams.mSession = iSpellCheckerSession;
                Handler handler = this.mAsyncHandler;
                handler.sendMessage(Message.obtain(handler, 1, spellCheckerParams));
            }
            if (spellCheckerParams.mWhat == 3) {
                synchronized (this) {
                    processCloseLocked();
                }
            }
        }

        private void processCloseLocked() {
            this.mISpellCheckerSession = null;
            HandlerThread handlerThread = this.mThread;
            if (handlerThread != null) {
                handlerThread.quit();
            }
            this.mHandler = null;
            this.mPendingTasks.clear();
            this.mThread = null;
            this.mAsyncHandler = null;
            int i = this.mState;
            if (i == 0) {
                this.mState = 3;
            } else if (i == 1) {
                this.mState = 2;
            } else {
                Log.e(SpellCheckerSession.TAG, "processCloseLocked is called unexpectedly. mState=" + stateToString(this.mState));
            }
        }

        public void onServiceConnected(ISpellCheckerSession iSpellCheckerSession) {
            synchronized (this) {
                int i = this.mState;
                if (i != 0) {
                    if (i != 3) {
                        Log.e(SpellCheckerSession.TAG, "ignoring onServiceConnected due to unexpected mState=" + stateToString(this.mState));
                    }
                } else {
                    if (iSpellCheckerSession == null) {
                        Log.e(SpellCheckerSession.TAG, "ignoring onServiceConnected due to session=null");
                        return;
                    }
                    this.mISpellCheckerSession = iSpellCheckerSession;
                    if ((iSpellCheckerSession.asBinder() instanceof Binder) && this.mThread == null) {
                        HandlerThread handlerThread = new HandlerThread("SpellCheckerSession", 10);
                        this.mThread = handlerThread;
                        handlerThread.start();
                        this.mAsyncHandler = new Handler(this.mThread.getLooper()) { // from class: android.view.textservice.SpellCheckerSession.SpellCheckerSessionListenerImpl.1
                            @Override // android.os.Handler
                            public void handleMessage(Message message) {
                                SpellCheckerParams spellCheckerParams = (SpellCheckerParams) message.obj;
                                SpellCheckerSessionListenerImpl.this.processTask(spellCheckerParams.mSession, spellCheckerParams, true);
                            }
                        };
                    }
                    this.mState = 1;
                    while (!this.mPendingTasks.isEmpty()) {
                        processTask(iSpellCheckerSession, this.mPendingTasks.poll(), false);
                    }
                }
            }
        }

        public void cancel() {
            processOrEnqueueTask(new SpellCheckerParams(1, null, 0, false));
        }

        public void getSuggestionsMultiple(TextInfo[] textInfoArr, int i, boolean z) {
            processOrEnqueueTask(new SpellCheckerParams(2, textInfoArr, i, z));
        }

        public void getSentenceSuggestionsMultiple(TextInfo[] textInfoArr, int i) {
            processOrEnqueueTask(new SpellCheckerParams(4, textInfoArr, i, false));
        }

        public void close() {
            processOrEnqueueTask(new SpellCheckerParams(3, null, 0, false));
        }

        public boolean isDisconnected() {
            boolean z;
            synchronized (this) {
                z = true;
                if (this.mState == 1) {
                    z = false;
                }
            }
            return z;
        }

        private void processOrEnqueueTask(SpellCheckerParams spellCheckerParams) {
            int i;
            synchronized (this) {
                if (spellCheckerParams.mWhat == 3 && ((i = this.mState) == 2 || i == 3)) {
                    return;
                }
                int i2 = this.mState;
                if (i2 != 0 && i2 != 1) {
                    Log.e(SpellCheckerSession.TAG, "ignoring processOrEnqueueTask due to unexpected mState=" + stateToString(this.mState) + " scp.mWhat=" + taskToString(spellCheckerParams.mWhat));
                    return;
                }
                if (i2 == 0) {
                    if (spellCheckerParams.mWhat == 3) {
                        processCloseLocked();
                        return;
                    }
                    SpellCheckerParams spellCheckerParams2 = null;
                    if (spellCheckerParams.mWhat == 1) {
                        while (!this.mPendingTasks.isEmpty()) {
                            SpellCheckerParams spellCheckerParamsPoll = this.mPendingTasks.poll();
                            if (spellCheckerParamsPoll.mWhat == 3) {
                                spellCheckerParams2 = spellCheckerParamsPoll;
                            }
                        }
                    }
                    this.mPendingTasks.offer(spellCheckerParams);
                    if (spellCheckerParams2 != null) {
                        this.mPendingTasks.offer(spellCheckerParams2);
                    }
                    return;
                }
                processTask(this.mISpellCheckerSession, spellCheckerParams, false);
            }
        }

        @Override // com.android.internal.textservice.ISpellCheckerSessionListener
        public void onGetSuggestions(SuggestionsInfo[] suggestionsInfoArr) {
            synchronized (this) {
                Handler handler = this.mHandler;
                if (handler != null) {
                    handler.sendMessage(Message.obtain(handler, 1, suggestionsInfoArr));
                }
            }
        }

        @Override // com.android.internal.textservice.ISpellCheckerSessionListener
        public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr) {
            synchronized (this) {
                Handler handler = this.mHandler;
                if (handler != null) {
                    handler.sendMessage(Message.obtain(handler, 2, sentenceSuggestionsInfoArr));
                }
            }
        }
    }

    private static final class InternalListener extends ITextServicesSessionListener.Stub {
        private final SpellCheckerSessionListenerImpl mParentSpellCheckerSessionListenerImpl;

        public InternalListener(SpellCheckerSessionListenerImpl spellCheckerSessionListenerImpl) {
            this.mParentSpellCheckerSessionListenerImpl = spellCheckerSessionListenerImpl;
        }

        @Override // com.android.internal.textservice.ITextServicesSessionListener
        public void onServiceConnected(ISpellCheckerSession iSpellCheckerSession) {
            this.mParentSpellCheckerSessionListenerImpl.onServiceConnected(iSpellCheckerSession);
        }
    }

    protected void finalize() throws Throwable {
        try {
            CloseGuard closeGuard = this.mGuard;
            if (closeGuard != null) {
                closeGuard.warnIfOpen();
                close();
            }
        } finally {
            super.finalize();
        }
    }

    public ITextServicesSessionListener getTextServicesSessionListener() {
        return this.mInternalListener;
    }

    public ISpellCheckerSessionListener getSpellCheckerSessionListener() {
        return this.mSpellCheckerSessionListenerImpl;
    }
}
