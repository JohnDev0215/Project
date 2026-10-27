package android.inputmethodservice;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.Region;
import android.inputmethodservice.AbstractInputMethodService;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.provider.Settings;
import android.provider.SettingsStringUtil;
import android.text.Layout;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.util.Log;
import android.util.PrintWriterPrinter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InlineSuggestionsRequest;
import android.view.inputmethod.InlineSuggestionsResponse;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.WindowMetricsHelper;
import com.android.internal.R;
import com.android.internal.inputmethod.IInputContentUriToken;
import com.android.internal.inputmethod.IInputMethodPrivilegedOperations;
import com.android.internal.inputmethod.InputMethodPrivilegedOperations;
import com.android.internal.inputmethod.InputMethodPrivilegedOperationsRegistry;
import com.android.internal.view.IInlineSuggestionsRequestCallback;
import com.android.internal.view.InlineSuggestionsRequestInfo;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public class InputMethodService extends AbstractInputMethodService {
    public static final int BACK_DISPOSITION_ADJUST_NOTHING = 3;
    public static final int BACK_DISPOSITION_DEFAULT = 0;
    private static final int BACK_DISPOSITION_MAX = 3;
    private static final int BACK_DISPOSITION_MIN = 0;

    @Deprecated
    public static final int BACK_DISPOSITION_WILL_DISMISS = 2;

    @Deprecated
    public static final int BACK_DISPOSITION_WILL_NOT_DISMISS = 1;
    static final boolean DEBUG = false;
    public static final int IME_ACTIVE = 1;
    public static final int IME_INVISIBLE = 4;
    public static final int IME_VISIBLE = 2;
    static final int MOVEMENT_DOWN = -1;
    static final int MOVEMENT_UP = -2;
    static final String TAG = "InputMethodService";
    private boolean mAutomotiveHideNavBarForKeyboard;
    int mBackDisposition;
    boolean mCanPreRender;
    FrameLayout mCandidatesFrame;
    boolean mCandidatesViewStarted;
    int mCandidatesVisibility;
    CompletionInfo[] mCurCompletions;
    private IBinder mCurHideInputToken;
    private IBinder mCurShowInputToken;
    boolean mDecorViewVisible;
    boolean mDecorViewWasVisible;
    ViewGroup mExtractAccessories;
    View mExtractAction;
    ExtractEditText mExtractEditText;
    FrameLayout mExtractFrame;
    View mExtractView;
    boolean mExtractViewHidden;
    ExtractedText mExtractedText;
    int mExtractedToken;
    boolean mFullscreenApplied;
    ViewGroup mFullscreenArea;
    InputMethodManager mImm;
    boolean mInShowWindow;
    LayoutInflater mInflater;
    boolean mInitialized;
    private InlineSuggestionSessionController mInlineSuggestionSessionController;
    InputBinding mInputBinding;
    InputConnection mInputConnection;
    EditorInfo mInputEditorInfo;
    FrameLayout mInputFrame;
    boolean mInputStarted;
    View mInputView;
    boolean mInputViewStarted;
    private boolean mIsAutomotive;
    boolean mIsFullscreen;
    boolean mIsInputViewShown;
    boolean mIsPreRendered;
    boolean mLastShowInputRequested;
    private boolean mNotifyUserActionSent;
    View mRootView;
    private SettingsObserver mSettingsObserver;
    int mShowInputFlags;
    boolean mShowInputRequested;
    InputConnection mStartedInputConnection;
    int mStatusIcon;
    TypedArray mThemeAttrs;
    IBinder mToken;
    boolean mViewsCreated;
    SoftInputWindow mWindow;
    boolean mWindowVisible;
    private InputMethodPrivilegedOperations mPrivOps = new InputMethodPrivilegedOperations();
    int mTheme = 0;
    private Object mLock = new Object();
    final Insets mTmpInsets = new Insets();
    final int[] mTmpLocation = new int[2];
    final ViewTreeObserver.OnComputeInternalInsetsListener mInsetsComputer = new ViewTreeObserver.OnComputeInternalInsetsListener() { // from class: android.inputmethodservice.-$$Lambda$InputMethodService$yal4L7_5kz_RCz1mLo_FamIBkXg
        @Override // android.view.ViewTreeObserver.OnComputeInternalInsetsListener
        public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
            this.f$0.lambda$new$0$InputMethodService(internalInsetsInfo);
        }
    };
    final View.OnClickListener mActionClickListener = new View.OnClickListener() { // from class: android.inputmethodservice.-$$Lambda$InputMethodService$8LwxYMylY5JOr6twpiYYnL0_i2Q
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f$0.lambda$new$1$InputMethodService(view);
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface BackDispositionMode {
    }

    public static final class Insets {
        public static final int TOUCHABLE_INSETS_CONTENT = 1;
        public static final int TOUCHABLE_INSETS_FRAME = 0;
        public static final int TOUCHABLE_INSETS_REGION = 3;
        public static final int TOUCHABLE_INSETS_VISIBLE = 2;
        public int contentTopInsets;
        public int touchableInsets;
        public final Region touchableRegion = new Region();
        public int visibleTopInsets;
    }

    private int getIconForImeAction(int i) {
        switch (i & 255) {
            case 2:
                return R.drawable.ic_input_extract_action_go;
            case 3:
                return R.drawable.ic_input_extract_action_search;
            case 4:
                return R.drawable.ic_input_extract_action_send;
            case 5:
                return R.drawable.ic_input_extract_action_next;
            case 6:
                return R.drawable.ic_input_extract_action_done;
            case 7:
                return R.drawable.ic_input_extract_action_previous;
            default:
                return R.drawable.ic_input_extract_action_return;
        }
    }

    public void onAppPrivateCommand(String str, Bundle bundle) {
    }

    public void onBindInput() {
    }

    public View onCreateCandidatesView() {
        return null;
    }

    public InlineSuggestionsRequest onCreateInlineSuggestionsRequest(Bundle bundle) {
        return null;
    }

    public View onCreateInputView() {
        return null;
    }

    protected void onCurrentInputMethodSubtypeChanged(InputMethodSubtype inputMethodSubtype) {
    }

    public void onDisplayCompletions(CompletionInfo[] completionInfoArr) {
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return false;
    }

    public void onInitializeInterface() {
    }

    public boolean onInlineSuggestionsResponse(InlineSuggestionsResponse inlineSuggestionsResponse) {
        return false;
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return false;
    }

    public void onStartCandidatesView(EditorInfo editorInfo, boolean z) {
    }

    public void onStartInput(EditorInfo editorInfo, boolean z) {
    }

    public void onStartInputView(EditorInfo editorInfo, boolean z) {
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        return false;
    }

    public void onUnbindInput() {
    }

    @Deprecated
    public void onUpdateCursor(Rect rect) {
    }

    public void onUpdateCursorAnchorInfo(CursorAnchorInfo cursorAnchorInfo) {
    }

    @Deprecated
    public void onViewClicked(boolean z) {
    }

    public void onWindowHidden() {
    }

    public void onWindowShown() {
    }

    public /* synthetic */ void lambda$new$0$InputMethodService(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        onComputeInsets(this.mTmpInsets);
        if (!this.mViewsCreated) {
            this.mTmpInsets.visibleTopInsets = 0;
        }
        if (isExtractViewShown()) {
            View decorView = getWindow().getWindow().getDecorView();
            Rect rect = internalInsetsInfo.contentInsets;
            Rect rect2 = internalInsetsInfo.visibleInsets;
            int height = decorView.getHeight();
            rect2.top = height;
            rect.top = height;
            internalInsetsInfo.touchableRegion.setEmpty();
            internalInsetsInfo.setTouchableInsets(0);
        } else {
            internalInsetsInfo.contentInsets.top = this.mTmpInsets.contentTopInsets;
            internalInsetsInfo.visibleInsets.top = this.mTmpInsets.visibleTopInsets;
            internalInsetsInfo.touchableRegion.set(this.mTmpInsets.touchableRegion);
            internalInsetsInfo.setTouchableInsets(this.mTmpInsets.touchableInsets);
        }
        if (this.mInputFrame != null) {
            setImeExclusionRect(this.mTmpInsets.visibleTopInsets);
        }
    }

    public /* synthetic */ void lambda$new$1$InputMethodService(View view) {
        EditorInfo currentInputEditorInfo = getCurrentInputEditorInfo();
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputEditorInfo == null || currentInputConnection == null) {
            return;
        }
        if (currentInputEditorInfo.actionId != 0) {
            currentInputConnection.performEditorAction(currentInputEditorInfo.actionId);
        } else if ((currentInputEditorInfo.imeOptions & 255) != 1) {
            currentInputConnection.performEditorAction(currentInputEditorInfo.imeOptions & 255);
        }
    }

    public class InputMethodImpl extends AbstractInputMethodService.AbstractInputMethodImpl {
        private boolean mSystemCallingHideSoftInput;
        private boolean mSystemCallingShowSoftInput;

        public InputMethodImpl() {
            super();
        }

        @Override // android.view.inputmethod.InputMethod
        public final void initializeInternal(IBinder iBinder, int i, IInputMethodPrivilegedOperations iInputMethodPrivilegedOperations) {
            if (!InputMethodPrivilegedOperationsRegistry.isRegistered(iBinder)) {
                InputMethodService.this.mPrivOps.set(iInputMethodPrivilegedOperations);
                InputMethodPrivilegedOperationsRegistry.put(iBinder, InputMethodService.this.mPrivOps);
                updateInputMethodDisplay(i);
                attachToken(iBinder);
                return;
            }
            Log.w(InputMethod.TAG, "The token has already registered, ignore this initialization.");
        }

        @Override // android.view.inputmethod.InputMethod
        public void onCreateInlineSuggestionsRequest(InlineSuggestionsRequestInfo inlineSuggestionsRequestInfo, IInlineSuggestionsRequestCallback iInlineSuggestionsRequestCallback) {
            InputMethodService.this.mInlineSuggestionSessionController.onMakeInlineSuggestionsRequest(inlineSuggestionsRequestInfo, iInlineSuggestionsRequestCallback);
        }

        @Override // android.view.inputmethod.InputMethod
        public void attachToken(IBinder iBinder) {
            if (InputMethodService.this.mToken != null) {
                throw new IllegalStateException("attachToken() must be called at most once. token=" + iBinder);
            }
            InputMethodService.this.mToken = iBinder;
            InputMethodService.this.mWindow.setToken(iBinder);
        }

        @Override // android.view.inputmethod.InputMethod
        public void updateInputMethodDisplay(int i) {
            InputMethodService.this.updateDisplay(i);
        }

        @Override // android.view.inputmethod.InputMethod
        public void bindInput(InputBinding inputBinding) {
            InputMethodService.this.mInputBinding = inputBinding;
            InputMethodService.this.mInputConnection = inputBinding.getConnection();
            InputMethodService.this.reportFullscreenMode();
            InputMethodService.this.initialize();
            InputMethodService.this.onBindInput();
        }

        @Override // android.view.inputmethod.InputMethod
        public void unbindInput() {
            InputMethodService.this.onUnbindInput();
            InputMethodService.this.mInputBinding = null;
            InputMethodService.this.mInputConnection = null;
        }

        @Override // android.view.inputmethod.InputMethod
        public void startInput(InputConnection inputConnection, EditorInfo editorInfo) {
            InputMethodService.this.doStartInput(inputConnection, editorInfo, false);
        }

        @Override // android.view.inputmethod.InputMethod
        public void restartInput(InputConnection inputConnection, EditorInfo editorInfo) {
            InputMethodService.this.doStartInput(inputConnection, editorInfo, true);
        }

        @Override // android.view.inputmethod.InputMethod
        public final void dispatchStartInputWithToken(InputConnection inputConnection, EditorInfo editorInfo, boolean z, IBinder iBinder, boolean z2) {
            InputMethodService.this.mPrivOps.reportStartInput(iBinder);
            InputMethodService.this.mCanPreRender = z2;
            if (z) {
                restartInput(inputConnection, editorInfo);
            } else {
                startInput(inputConnection, editorInfo);
            }
        }

        @Override // android.view.inputmethod.InputMethod
        public void hideSoftInputWithToken(int i, ResultReceiver resultReceiver, IBinder iBinder) {
            this.mSystemCallingHideSoftInput = true;
            InputMethodService.this.mCurHideInputToken = iBinder;
            hideSoftInput(i, resultReceiver);
            InputMethodService.this.mCurHideInputToken = null;
            this.mSystemCallingHideSoftInput = false;
        }

        @Override // android.view.inputmethod.InputMethod
        public void hideSoftInput(int i, ResultReceiver resultReceiver) {
            boolean zIsInputViewShown;
            boolean zIsInputViewShown2;
            if (InputMethodService.this.getApplicationInfo().targetSdkVersion >= 30 && !this.mSystemCallingHideSoftInput) {
                Log.e(InputMethod.TAG, "IME shouldn't call hideSoftInput on itself. Use requestHideSelf(int) itself");
                return;
            }
            int i2 = 1;
            if (InputMethodService.this.mIsPreRendered) {
                zIsInputViewShown = InputMethodService.this.mDecorViewVisible && InputMethodService.this.mWindowVisible;
            } else {
                zIsInputViewShown = InputMethodService.this.isInputViewShown();
            }
            InputMethodService.this.applyVisibilityInInsetsConsumerIfNecessary(false);
            if (InputMethodService.this.mIsPreRendered) {
                InputMethodService inputMethodService = InputMethodService.this;
                inputMethodService.setImeWindowStatus(5, inputMethodService.mBackDisposition);
                InputMethodService.this.onPreRenderedWindowVisibilityChanged(false);
            } else {
                InputMethodService.this.mShowInputFlags = 0;
                InputMethodService.this.mShowInputRequested = false;
                InputMethodService.this.doHideWindow();
            }
            if (InputMethodService.this.mIsPreRendered) {
                zIsInputViewShown2 = InputMethodService.this.mDecorViewVisible && InputMethodService.this.mWindowVisible;
            } else {
                zIsInputViewShown2 = InputMethodService.this.isInputViewShown();
            }
            boolean z = zIsInputViewShown2 != zIsInputViewShown;
            if (resultReceiver != null) {
                if (z) {
                    i2 = 3;
                } else if (zIsInputViewShown) {
                    i2 = 0;
                }
                resultReceiver.send(i2, null);
            }
        }

        @Override // android.view.inputmethod.InputMethod
        public void showSoftInputWithToken(int i, ResultReceiver resultReceiver, IBinder iBinder) {
            this.mSystemCallingShowSoftInput = true;
            InputMethodService.this.mCurShowInputToken = iBinder;
            showSoftInput(i, resultReceiver);
            InputMethodService.this.mCurShowInputToken = null;
            this.mSystemCallingShowSoftInput = false;
        }

        @Override // android.view.inputmethod.InputMethod
        public void showSoftInput(int i, ResultReceiver resultReceiver) {
            boolean zIsInputViewShown;
            boolean zIsInputViewShown2;
            if (InputMethodService.this.getApplicationInfo().targetSdkVersion >= 30 && !this.mSystemCallingShowSoftInput) {
                Log.e(InputMethod.TAG, " IME shouldn't call showSoftInput on itself. Use requestShowSelf(int) itself");
                return;
            }
            int i2 = 0;
            if (InputMethodService.this.mIsPreRendered) {
                zIsInputViewShown = InputMethodService.this.mDecorViewVisible && InputMethodService.this.mWindowVisible;
            } else {
                zIsInputViewShown = InputMethodService.this.isInputViewShown();
            }
            if (InputMethodService.this.dispatchOnShowInputRequested(i, false)) {
                if (InputMethodService.this.mIsPreRendered) {
                    InputMethodService.this.onPreRenderedWindowVisibilityChanged(true);
                } else {
                    InputMethodService.this.showWindow(true);
                }
                InputMethodService.this.applyVisibilityInInsetsConsumerIfNecessary(true);
            }
            InputMethodService inputMethodService = InputMethodService.this;
            inputMethodService.setImeWindowStatus(inputMethodService.mapToImeWindowStatus(), InputMethodService.this.mBackDisposition);
            if (InputMethodService.this.mIsPreRendered) {
                zIsInputViewShown2 = InputMethodService.this.mDecorViewVisible && InputMethodService.this.mWindowVisible;
            } else {
                zIsInputViewShown2 = InputMethodService.this.isInputViewShown();
            }
            boolean z = zIsInputViewShown2 != zIsInputViewShown;
            if (resultReceiver != null) {
                if (z) {
                    i2 = 2;
                } else if (!zIsInputViewShown) {
                    i2 = 1;
                }
                resultReceiver.send(i2, null);
            }
        }

        @Override // android.view.inputmethod.InputMethod
        public void changeInputMethodSubtype(InputMethodSubtype inputMethodSubtype) {
            InputMethodService.this.dispatchOnCurrentInputMethodSubtypeChanged(inputMethodSubtype);
        }

        @Override // android.view.inputmethod.InputMethod
        public void setCurrentShowInputToken(IBinder iBinder) {
            InputMethodService.this.mCurShowInputToken = iBinder;
        }

        @Override // android.view.inputmethod.InputMethod
        public void setCurrentHideInputToken(IBinder iBinder) {
            InputMethodService.this.mCurHideInputToken = iBinder;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IBinder getHostInputToken() {
        View view = this.mRootView;
        ViewRootImpl viewRootImpl = view != null ? view.getViewRootImpl() : null;
        if (viewRootImpl == null) {
            return null;
        }
        return viewRootImpl.getInputToken();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyImeHidden() {
        requestHideSelf(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeImeSurface() {
        if (this.mShowInputRequested || this.mWindowVisible) {
            return;
        }
        this.mWindow.hide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImeWindowStatus(int i, int i2) {
        this.mPrivOps.setImeWindowStatus(i, i2);
    }

    private void setImeExclusionRect(int i) {
        View rootView = this.mInputFrame.getRootView();
        android.graphics.Insets insetsIgnoringVisibility = rootView.getRootWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemGestures());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Rect(0, i, insetsIgnoringVisibility.left, rootView.getHeight()));
        arrayList.add(new Rect(rootView.getWidth() - insetsIgnoringVisibility.right, i, rootView.getWidth(), rootView.getHeight()));
        rootView.setSystemGestureExclusionRects(arrayList);
    }

    public class InputMethodSessionImpl extends AbstractInputMethodService.AbstractInputMethodSessionImpl {
        public InputMethodSessionImpl() {
            super();
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void finishInput() {
            if (isEnabled()) {
                InputMethodService.this.doFinishInput();
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void displayCompletions(CompletionInfo[] completionInfoArr) {
            if (isEnabled()) {
                InputMethodService.this.mCurCompletions = completionInfoArr;
                InputMethodService.this.onDisplayCompletions(completionInfoArr);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void updateExtractedText(int i, ExtractedText extractedText) {
            if (isEnabled()) {
                InputMethodService.this.onUpdateExtractedText(i, extractedText);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void updateSelection(int i, int i2, int i3, int i4, int i5, int i6) {
            if (isEnabled()) {
                InputMethodService.this.onUpdateSelection(i, i2, i3, i4, i5, i6);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void viewClicked(boolean z) {
            if (isEnabled()) {
                InputMethodService.this.onViewClicked(z);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void updateCursor(Rect rect) {
            if (isEnabled()) {
                InputMethodService.this.onUpdateCursor(rect);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void appPrivateCommand(String str, Bundle bundle) {
            if (isEnabled()) {
                InputMethodService.this.onAppPrivateCommand(str, bundle);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void toggleSoftInput(int i, int i2) {
            InputMethodService.this.onToggleSoftInput(i, i2);
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void updateCursorAnchorInfo(CursorAnchorInfo cursorAnchorInfo) {
            if (isEnabled()) {
                InputMethodService.this.onUpdateCursorAnchorInfo(cursorAnchorInfo);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public final void notifyImeHidden() {
            InputMethodService.this.notifyImeHidden();
        }

        @Override // android.view.inputmethod.InputMethodSession
        public final void removeImeSurface() {
            InputMethodService.this.removeImeSurface();
        }
    }

    private static final class SettingsObserver extends ContentObserver {
        private final InputMethodService mService;
        private int mShowImeWithHardKeyboard;

        @Retention(RetentionPolicy.SOURCE)
        private @interface ShowImeWithHardKeyboardType {
            public static final int FALSE = 1;
            public static final int TRUE = 2;
            public static final int UNKNOWN = 0;
        }

        private SettingsObserver(InputMethodService inputMethodService) {
            super(new Handler(inputMethodService.getMainLooper()));
            this.mShowImeWithHardKeyboard = 0;
            this.mService = inputMethodService;
        }

        public static SettingsObserver createAndRegister(InputMethodService inputMethodService) {
            SettingsObserver settingsObserver = new SettingsObserver(inputMethodService);
            inputMethodService.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.SHOW_IME_WITH_HARD_KEYBOARD), false, settingsObserver);
            return settingsObserver;
        }

        void unregister() {
            this.mService.getContentResolver().unregisterContentObserver(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldShowImeWithHardKeyboard() {
            if (this.mShowImeWithHardKeyboard == 0) {
                this.mShowImeWithHardKeyboard = Settings.Secure.getInt(this.mService.getContentResolver(), Settings.Secure.SHOW_IME_WITH_HARD_KEYBOARD, 0) != 0 ? 2 : 1;
            }
            int i = this.mShowImeWithHardKeyboard;
            if (i == 1) {
                return false;
            }
            if (i == 2) {
                return true;
            }
            Log.e(InputMethodService.TAG, "Unexpected mShowImeWithHardKeyboard=" + this.mShowImeWithHardKeyboard);
            return false;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            if (Settings.Secure.getUriFor(Settings.Secure.SHOW_IME_WITH_HARD_KEYBOARD).equals(uri)) {
                this.mShowImeWithHardKeyboard = Settings.Secure.getInt(this.mService.getContentResolver(), Settings.Secure.SHOW_IME_WITH_HARD_KEYBOARD, 0) != 0 ? 2 : 1;
                this.mService.resetStateForNewConfiguration();
            }
        }

        public String toString() {
            return "SettingsObserver{mShowImeWithHardKeyboard=" + this.mShowImeWithHardKeyboard + "}";
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.mWindow != null) {
            throw new IllegalStateException("Must be called before onCreate()");
        }
        this.mTheme = i;
    }

    @Deprecated
    public boolean enableHardwareAcceleration() {
        if (this.mWindow != null) {
            throw new IllegalStateException("Must be called before onCreate()");
        }
        return ActivityManager.isHighEndGfx();
    }

    @Override // android.app.Service
    public void onCreate() {
        int iSelectSystemTheme = Resources.selectSystemTheme(this.mTheme, getApplicationInfo().targetSdkVersion, 16973908, 16973951, 16974142, 16974142);
        this.mTheme = iSelectSystemTheme;
        super.setTheme(iSelectSystemTheme);
        super.onCreate();
        this.mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        this.mSettingsObserver = SettingsObserver.createAndRegister(this);
        this.mIsAutomotive = isAutomotive();
        this.mAutomotiveHideNavBarForKeyboard = getApplicationContext().getResources().getBoolean(R.bool.config_automotiveHideNavBarForKeyboard);
        this.mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SoftInputWindow softInputWindow = new SoftInputWindow(this, InputMethod.TAG, this.mTheme, null, null, this.mDispatcherState, 2011, 80, false);
        this.mWindow = softInputWindow;
        softInputWindow.getWindow().getAttributes().setFitInsetsTypes(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
        this.mWindow.getWindow().getAttributes().setFitInsetsSides(WindowInsets.Side.all() & (-9));
        if (this.mIsAutomotive && this.mAutomotiveHideNavBarForKeyboard) {
            this.mWindow.getWindow().setDecorFitsSystemWindows(false);
        }
        this.mWindow.getWindow().getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: android.inputmethodservice.-$$Lambda$InputMethodService$GBhczkDRMQBmmgzTq9YWhQpYuZ0
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                return view.onApplyWindowInsets(new WindowInsets.Builder(windowInsets).setInsets(WindowInsets.Type.navigationBars(), windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars())).build());
            }
        });
        this.mWindow.getWindow().setFlags(Integer.MIN_VALUE, Integer.MIN_VALUE);
        initViews();
        this.mWindow.getWindow().setLayout(-1, -2);
        this.mInlineSuggestionSessionController = new InlineSuggestionSessionController(new Function() { // from class: android.inputmethodservice.-$$Lambda$93LvBH-xpo30jYlKAmMGQH5DqMA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.onCreateInlineSuggestionsRequest((Bundle) obj);
            }
        }, new Supplier() { // from class: android.inputmethodservice.-$$Lambda$InputMethodService$nMoGsSshcBNZe61VPESespVJMGQ
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.getHostInputToken();
            }
        }, new Consumer() { // from class: android.inputmethodservice.-$$Lambda$zR9KAwBPHr9NbVT4QQ3slw18kRU
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.onInlineSuggestionsResponse((InlineSuggestionsResponse) obj);
            }
        });
    }

    void initialize() {
        if (this.mInitialized) {
            return;
        }
        this.mInitialized = true;
        onInitializeInterface();
    }

    void initViews() {
        this.mInitialized = false;
        this.mViewsCreated = false;
        this.mShowInputRequested = false;
        this.mShowInputFlags = 0;
        this.mThemeAttrs = obtainStyledAttributes(android.R.styleable.InputMethodService);
        View viewInflate = this.mInflater.inflate(R.layout.input_method, (ViewGroup) null);
        this.mRootView = viewInflate;
        this.mWindow.setContentView(viewInflate);
        this.mRootView.getViewTreeObserver().removeOnComputeInternalInsetsListener(this.mInsetsComputer);
        this.mRootView.getViewTreeObserver().addOnComputeInternalInsetsListener(this.mInsetsComputer);
        if (Settings.Global.getInt(getContentResolver(), Settings.Global.FANCY_IME_ANIMATIONS, 0) != 0) {
            this.mWindow.getWindow().setWindowAnimations(R.style.Animation_InputMethodFancy);
        }
        this.mFullscreenArea = (ViewGroup) this.mRootView.findViewById(R.id.fullscreenArea);
        this.mExtractViewHidden = false;
        this.mExtractFrame = (FrameLayout) this.mRootView.findViewById(16908316);
        this.mExtractView = null;
        this.mExtractEditText = null;
        this.mExtractAccessories = null;
        this.mExtractAction = null;
        this.mFullscreenApplied = false;
        this.mCandidatesFrame = (FrameLayout) this.mRootView.findViewById(16908317);
        this.mInputFrame = (FrameLayout) this.mRootView.findViewById(16908318);
        this.mInputView = null;
        this.mIsInputViewShown = false;
        this.mExtractFrame.setVisibility(8);
        int candidatesHiddenVisibility = getCandidatesHiddenVisibility();
        this.mCandidatesVisibility = candidatesHiddenVisibility;
        this.mCandidatesFrame.setVisibility(candidatesHiddenVisibility);
        this.mInputFrame.setVisibility(8);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mRootView.getViewTreeObserver().removeOnComputeInternalInsetsListener(this.mInsetsComputer);
        doFinishInput();
        this.mWindow.dismissForDestroyIfNecessary();
        SettingsObserver settingsObserver = this.mSettingsObserver;
        if (settingsObserver != null) {
            settingsObserver.unregister();
            this.mSettingsObserver = null;
        }
        IBinder iBinder = this.mToken;
        if (iBinder != null) {
            InputMethodPrivilegedOperationsRegistry.remove(iBinder);
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetStateForNewConfiguration();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetStateForNewConfiguration() {
        boolean z = this.mDecorViewVisible;
        int i = this.mShowInputFlags;
        boolean z2 = this.mShowInputRequested;
        CompletionInfo[] completionInfoArr = this.mCurCompletions;
        initViews();
        this.mInputViewStarted = false;
        this.mCandidatesViewStarted = false;
        if (this.mInputStarted) {
            doStartInput(getCurrentInputConnection(), getCurrentInputEditorInfo(), true);
        }
        if (z) {
            if (z2) {
                if (dispatchOnShowInputRequested(i, true)) {
                    showWindow(true);
                    if (completionInfoArr != null) {
                        this.mCurCompletions = completionInfoArr;
                        onDisplayCompletions(completionInfoArr);
                    }
                } else {
                    doHideWindow();
                }
            } else if (this.mCandidatesVisibility == 0) {
                showWindow(false);
            } else {
                doHideWindow();
            }
            setImeWindowStatus((onEvaluateInputViewShown() ? 2 : 0) | 1, this.mBackDisposition);
        }
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public AbstractInputMethodService.AbstractInputMethodImpl onCreateInputMethodInterface() {
        return new InputMethodImpl();
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public AbstractInputMethodService.AbstractInputMethodSessionImpl onCreateInputMethodSessionInterface() {
        return new InputMethodSessionImpl();
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    public Dialog getWindow() {
        return this.mWindow;
    }

    public void setBackDisposition(int i) {
        if (i == this.mBackDisposition) {
            return;
        }
        if (i > 3 || i < 0) {
            Log.e(TAG, "Invalid back disposition value (" + i + ") specified.");
        } else {
            this.mBackDisposition = i;
            setImeWindowStatus(mapToImeWindowStatus(), this.mBackDisposition);
        }
    }

    public int getBackDisposition() {
        return this.mBackDisposition;
    }

    public int getMaxWidth() {
        return WindowMetricsHelper.getBoundsExcludingNavigationBarAndCutout(((WindowManager) getSystemService(WindowManager.class)).getCurrentWindowMetrics()).width();
    }

    public InputBinding getCurrentInputBinding() {
        return this.mInputBinding;
    }

    public InputConnection getCurrentInputConnection() {
        InputConnection inputConnection = this.mStartedInputConnection;
        return inputConnection != null ? inputConnection : this.mInputConnection;
    }

    public final boolean switchToPreviousInputMethod() {
        return this.mPrivOps.switchToPreviousInputMethod();
    }

    public final boolean switchToNextInputMethod(boolean z) {
        return this.mPrivOps.switchToNextInputMethod(z);
    }

    public final boolean shouldOfferSwitchingToNextInputMethod() {
        return this.mPrivOps.shouldOfferSwitchingToNextInputMethod();
    }

    public boolean getCurrentInputStarted() {
        return this.mInputStarted;
    }

    public EditorInfo getCurrentInputEditorInfo() {
        return this.mInputEditorInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportFullscreenMode() {
        this.mPrivOps.reportFullscreenMode(this.mIsFullscreen);
    }

    public void updateFullscreenMode() {
        View viewOnCreateExtractTextView;
        boolean z = this.mShowInputRequested && onEvaluateFullscreenMode();
        boolean z2 = this.mLastShowInputRequested != this.mShowInputRequested;
        if (this.mIsFullscreen != z || !this.mFullscreenApplied) {
            this.mIsFullscreen = z;
            reportFullscreenMode();
            this.mFullscreenApplied = true;
            initialize();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mFullscreenArea.getLayoutParams();
            if (z) {
                this.mFullscreenArea.setBackgroundDrawable(this.mThemeAttrs.getDrawable(0));
                layoutParams.height = 0;
                layoutParams.weight = 1.0f;
            } else {
                this.mFullscreenArea.setBackgroundDrawable(null);
                layoutParams.height = -2;
                layoutParams.weight = 0.0f;
            }
            ((ViewGroup) this.mFullscreenArea.getParent()).updateViewLayout(this.mFullscreenArea, layoutParams);
            if (z) {
                if (this.mExtractView == null && (viewOnCreateExtractTextView = onCreateExtractTextView()) != null) {
                    setExtractView(viewOnCreateExtractTextView);
                }
                startExtractingText(false);
            }
            updateExtractFrameVisibility();
            z2 = true;
        }
        if (z2) {
            onConfigureWindow(this.mWindow.getWindow(), z, true ^ this.mShowInputRequested);
            this.mLastShowInputRequested = this.mShowInputRequested;
        }
    }

    public void onConfigureWindow(Window window, boolean z, boolean z2) {
        int i = this.mWindow.getWindow().getAttributes().height;
        this.mWindow.getWindow().setLayout(-1, z ? -1 : -2);
    }

    public boolean isFullscreenMode() {
        return this.mIsFullscreen;
    }

    public boolean onEvaluateFullscreenMode() {
        if (getResources().getConfiguration().orientation != 2) {
            return false;
        }
        EditorInfo editorInfo = this.mInputEditorInfo;
        return editorInfo == null || (editorInfo.imeOptions & 33554432) == 0;
    }

    public void setExtractViewShown(boolean z) {
        if (this.mExtractViewHidden == z) {
            this.mExtractViewHidden = !z;
            updateExtractFrameVisibility();
        }
    }

    public boolean isExtractViewShown() {
        return this.mIsFullscreen && !this.mExtractViewHidden;
    }

    void updateExtractFrameVisibility() {
        int i;
        if (isFullscreenMode()) {
            i = this.mExtractViewHidden ? 4 : 0;
            this.mExtractFrame.setVisibility(i);
        } else {
            this.mExtractFrame.setVisibility(8);
            i = 0;
        }
        updateCandidatesVisibility(this.mCandidatesVisibility == 0);
        if (this.mDecorViewWasVisible && this.mFullscreenArea.getVisibility() != i) {
            int resourceId = this.mThemeAttrs.getResourceId(i != 0 ? 2 : 1, 0);
            if (resourceId != 0) {
                this.mFullscreenArea.startAnimation(AnimationUtils.loadAnimation(this, resourceId));
            }
        }
        this.mFullscreenArea.setVisibility(i);
    }

    public void onComputeInsets(Insets insets) {
        int[] iArr = this.mTmpLocation;
        if (this.mInputFrame.getVisibility() == 0) {
            this.mInputFrame.getLocationInWindow(iArr);
        } else {
            iArr[1] = getWindow().getWindow().getDecorView().getHeight();
        }
        if (isFullscreenMode()) {
            insets.contentTopInsets = getWindow().getWindow().getDecorView().getHeight();
        } else {
            insets.contentTopInsets = iArr[1];
        }
        if (this.mCandidatesFrame.getVisibility() == 0) {
            this.mCandidatesFrame.getLocationInWindow(iArr);
        }
        insets.visibleTopInsets = iArr[1];
        insets.touchableInsets = 2;
        insets.touchableRegion.setEmpty();
    }

    public void updateInputViewShown() {
        boolean z = this.mShowInputRequested && onEvaluateInputViewShown();
        if (this.mIsInputViewShown == z || !this.mDecorViewVisible) {
            return;
        }
        this.mIsInputViewShown = z;
        this.mInputFrame.setVisibility(z ? 0 : 8);
        if (this.mInputView == null) {
            initialize();
            View viewOnCreateInputView = onCreateInputView();
            if (viewOnCreateInputView != null) {
                setInputView(viewOnCreateInputView);
            }
        }
    }

    public boolean isShowInputRequested() {
        return this.mShowInputRequested;
    }

    public boolean isInputViewShown() {
        return this.mCanPreRender ? this.mWindowVisible : this.mIsInputViewShown && this.mDecorViewVisible;
    }

    public boolean onEvaluateInputViewShown() {
        SettingsObserver settingsObserver = this.mSettingsObserver;
        if (settingsObserver != null) {
            if (settingsObserver.shouldShowImeWithHardKeyboard()) {
                return true;
            }
            Configuration configuration = getResources().getConfiguration();
            return configuration.keyboard == 1 || configuration.hardKeyboardHidden == 2;
        }
        Log.w(TAG, "onEvaluateInputViewShown: mSettingsObserver must not be null here.");
        return false;
    }

    public void setCandidatesViewShown(boolean z) {
        updateCandidatesVisibility(z);
        if (this.mShowInputRequested || this.mDecorViewVisible == z) {
            return;
        }
        if (z) {
            showWindow(false);
        } else {
            doHideWindow();
        }
    }

    void updateCandidatesVisibility(boolean z) {
        int candidatesHiddenVisibility = z ? 0 : getCandidatesHiddenVisibility();
        if (this.mCandidatesVisibility != candidatesHiddenVisibility) {
            this.mCandidatesFrame.setVisibility(candidatesHiddenVisibility);
            this.mCandidatesVisibility = candidatesHiddenVisibility;
        }
    }

    public int getCandidatesHiddenVisibility() {
        return isExtractViewShown() ? 8 : 4;
    }

    public void showStatusIcon(int i) {
        this.mStatusIcon = i;
        this.mPrivOps.updateStatusIcon(getPackageName(), i);
    }

    public void hideStatusIcon() {
        this.mStatusIcon = 0;
        this.mPrivOps.updateStatusIcon(null, 0);
    }

    public void switchInputMethod(String str) {
        this.mPrivOps.setInputMethod(str);
    }

    public final void switchInputMethod(String str, InputMethodSubtype inputMethodSubtype) {
        this.mPrivOps.setInputMethodAndSubtype(str, inputMethodSubtype);
    }

    public void setExtractView(View view) {
        this.mExtractFrame.removeAllViews();
        this.mExtractFrame.addView(view, new FrameLayout.LayoutParams(-1, -1));
        this.mExtractView = view;
        if (view != null) {
            ExtractEditText extractEditText = (ExtractEditText) view.findViewById(16908325);
            this.mExtractEditText = extractEditText;
            extractEditText.setIME(this);
            View viewFindViewById = view.findViewById(R.id.inputExtractAction);
            this.mExtractAction = viewFindViewById;
            if (viewFindViewById != null) {
                this.mExtractAccessories = (ViewGroup) view.findViewById(R.id.inputExtractAccessories);
            }
            startExtractingText(false);
            return;
        }
        this.mExtractEditText = null;
        this.mExtractAccessories = null;
        this.mExtractAction = null;
    }

    public void setCandidatesView(View view) {
        this.mCandidatesFrame.removeAllViews();
        this.mCandidatesFrame.addView(view, new FrameLayout.LayoutParams(-1, -2));
    }

    public void setInputView(View view) {
        this.mInputFrame.removeAllViews();
        this.mInputFrame.addView(view, new FrameLayout.LayoutParams(-1, -2));
        this.mInputView = view;
    }

    public View onCreateExtractTextView() {
        return this.mInflater.inflate(R.layout.input_method_extract_view, (ViewGroup) null);
    }

    public void onFinishInputView(boolean z) {
        InputConnection currentInputConnection;
        if (z || (currentInputConnection = getCurrentInputConnection()) == null) {
            return;
        }
        currentInputConnection.finishComposingText();
    }

    public void onFinishCandidatesView(boolean z) {
        InputConnection currentInputConnection;
        if (z || (currentInputConnection = getCurrentInputConnection()) == null) {
            return;
        }
        currentInputConnection.finishComposingText();
    }

    public boolean onShowInputRequested(int i, boolean z) {
        if (!onEvaluateInputViewShown()) {
            return false;
        }
        if ((i & 1) == 0) {
            if (!z && onEvaluateFullscreenMode()) {
                return false;
            }
            if (!this.mSettingsObserver.shouldShowImeWithHardKeyboard() && getResources().getConfiguration().keyboard != 1) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dispatchOnShowInputRequested(int i, boolean z) {
        boolean zOnShowInputRequested = onShowInputRequested(i, z);
        this.mInlineSuggestionSessionController.notifyOnShowInputRequested(zOnShowInputRequested);
        if (zOnShowInputRequested) {
            this.mShowInputFlags = i;
        } else {
            this.mShowInputFlags = 0;
        }
        return zOnShowInputRequested;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showWindow(boolean z) {
        if (this.mInShowWindow) {
            Log.w(TAG, "Re-entrance in to showWindow");
            return;
        }
        boolean z2 = this.mDecorViewVisible;
        this.mDecorViewWasVisible = z2;
        this.mInShowWindow = true;
        int i = (z2 ? 1 : 0) | (isInputViewShown() ? (this.mIsPreRendered && !this.mWindowVisible) != false ? 4 : 2 : 0);
        startViews(prepareWindow(z));
        int iMapToImeWindowStatus = mapToImeWindowStatus();
        if (i != iMapToImeWindowStatus) {
            setImeWindowStatus(iMapToImeWindowStatus, this.mBackDisposition);
        }
        onWindowShown();
        boolean z3 = this.mCanPreRender;
        this.mIsPreRendered = z3;
        if (z3) {
            onPreRenderedWindowVisibilityChanged(true);
        } else {
            this.mWindowVisible = true;
        }
        if ((i & 1) == 0) {
            this.mWindow.show();
        }
        maybeNotifyPreRendered();
        this.mDecorViewWasVisible = true;
        this.mInShowWindow = false;
    }

    private void maybeNotifyPreRendered() {
        if (this.mCanPreRender && this.mIsPreRendered) {
            this.mPrivOps.reportPreRendered(getCurrentInputEditorInfo());
        }
    }

    private boolean prepareWindow(boolean z) {
        boolean z2;
        this.mDecorViewVisible = true;
        if (!this.mShowInputRequested && this.mInputStarted && z) {
            this.mShowInputRequested = true;
            z2 = true;
        } else {
            z2 = false;
        }
        initialize();
        updateFullscreenMode();
        updateInputViewShown();
        if (!this.mViewsCreated) {
            this.mViewsCreated = true;
            initialize();
            View viewOnCreateCandidatesView = onCreateCandidatesView();
            if (viewOnCreateCandidatesView != null) {
                setCandidatesView(viewOnCreateCandidatesView);
            }
        }
        return z2;
    }

    private void startViews(boolean z) {
        if (this.mShowInputRequested) {
            if (!this.mInputViewStarted) {
                this.mInputViewStarted = true;
                this.mInlineSuggestionSessionController.notifyOnStartInputView();
                onStartInputView(this.mInputEditorInfo, false);
            }
        } else if (!this.mCandidatesViewStarted) {
            this.mCandidatesViewStarted = true;
            onStartCandidatesView(this.mInputEditorInfo, false);
        }
        if (z) {
            startExtractingText(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPreRenderedWindowVisibilityChanged(boolean z) {
        this.mWindowVisible = z;
        this.mShowInputFlags = z ? this.mShowInputFlags : 0;
        this.mShowInputRequested = z;
        this.mDecorViewVisible = z;
        if (z) {
            onWindowShown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyVisibilityInInsetsConsumerIfNecessary(boolean z) {
        if (isVisibilityAppliedUsingInsetsConsumer()) {
            this.mPrivOps.applyImeVisibility(z ? this.mCurShowInputToken : this.mCurHideInputToken, z);
        }
    }

    private boolean isVisibilityAppliedUsingInsetsConsumer() {
        return ViewRootImpl.sNewInsetsMode > 0;
    }

    private void finishViews(boolean z) {
        if (this.mInputViewStarted) {
            this.mInlineSuggestionSessionController.notifyOnFinishInputView();
            onFinishInputView(z);
        } else if (this.mCandidatesViewStarted) {
            onFinishCandidatesView(z);
        }
        this.mInputViewStarted = false;
        this.mCandidatesViewStarted = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHideWindow() {
        setImeWindowStatus(0, this.mBackDisposition);
        hideWindow();
    }

    public void hideWindow() {
        this.mIsPreRendered = false;
        this.mWindowVisible = false;
        finishViews(false);
        if (this.mDecorViewVisible) {
            if (isVisibilityAppliedUsingInsetsConsumer()) {
                View view = this.mInputView;
                if (view != null) {
                    view.dispatchWindowVisibilityChanged(8);
                }
            } else {
                this.mWindow.hide();
            }
            this.mDecorViewVisible = false;
            onWindowHidden();
            this.mDecorViewWasVisible = false;
        }
        updateFullscreenMode();
    }

    void doFinishInput() {
        finishViews(true);
        if (this.mInputStarted) {
            this.mInlineSuggestionSessionController.notifyOnFinishInput();
            onFinishInput();
        }
        this.mInputStarted = false;
        this.mStartedInputConnection = null;
        this.mCurCompletions = null;
    }

    void doStartInput(InputConnection inputConnection, EditorInfo editorInfo, boolean z) {
        if (!z) {
            doFinishInput();
        }
        this.mInputStarted = true;
        this.mStartedInputConnection = inputConnection;
        this.mInputEditorInfo = editorInfo;
        initialize();
        this.mInlineSuggestionSessionController.notifyOnStartInput(editorInfo == null ? null : editorInfo.packageName, editorInfo != null ? editorInfo.autofillId : null);
        onStartInput(editorInfo, z);
        boolean z2 = this.mDecorViewVisible;
        if (z2) {
            if (this.mShowInputRequested) {
                this.mInputViewStarted = true;
                this.mInlineSuggestionSessionController.notifyOnStartInputView();
                onStartInputView(this.mInputEditorInfo, z);
                startExtractingText(true);
                return;
            }
            if (this.mCandidatesVisibility == 0) {
                this.mCandidatesViewStarted = true;
                onStartCandidatesView(this.mInputEditorInfo, z);
                return;
            }
            return;
        }
        if (this.mCanPreRender && this.mInputEditorInfo != null && this.mStartedInputConnection != null) {
            if (this.mInShowWindow) {
                Log.w(TAG, "Re-entrance in to showWindow");
                return;
            }
            this.mDecorViewWasVisible = z2;
            this.mInShowWindow = true;
            startViews(prepareWindow(true));
            this.mIsPreRendered = true;
            onPreRenderedWindowVisibilityChanged(false);
            this.mWindow.show();
            maybeNotifyPreRendered();
            this.mDecorViewWasVisible = true;
            this.mInShowWindow = false;
            return;
        }
        this.mIsPreRendered = false;
    }

    public void onFinishInput() {
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputConnection != null) {
            currentInputConnection.finishComposingText();
        }
    }

    public void onUpdateExtractedText(int i, ExtractedText extractedText) {
        ExtractEditText extractEditText;
        if (this.mExtractedToken != i || extractedText == null || (extractEditText = this.mExtractEditText) == null) {
            return;
        }
        this.mExtractedText = extractedText;
        extractEditText.setExtractedText(extractedText);
    }

    public void onUpdateSelection(int i, int i2, int i3, int i4, int i5, int i6) {
        ExtractedText extractedText;
        ExtractEditText extractEditText = this.mExtractEditText;
        if (extractEditText == null || !isFullscreenMode() || (extractedText = this.mExtractedText) == null) {
            return;
        }
        int i7 = extractedText.startOffset;
        extractEditText.startInternalChanges();
        int i8 = i3 - i7;
        int i9 = i4 - i7;
        int length = extractEditText.getText().length();
        if (i8 < 0) {
            i8 = 0;
        } else if (i8 > length) {
            i8 = length;
        }
        if (i9 < 0) {
            i9 = 0;
        } else if (i9 > length) {
            i9 = length;
        }
        extractEditText.setSelection(i8, i9);
        extractEditText.finishInternalChanges();
    }

    public void requestHideSelf(int i) {
        this.mPrivOps.hideMySoftInput(i);
    }

    public final void requestShowSelf(int i) {
        this.mPrivOps.showMySoftInput(i);
    }

    private boolean handleBack(boolean z) {
        if (this.mShowInputRequested) {
            if (z) {
                requestHideSelf(0);
            }
            return true;
        }
        if (!this.mDecorViewVisible) {
            return false;
        }
        if (this.mCandidatesVisibility == 0) {
            if (z) {
                setCandidatesViewShown(false);
            }
        } else if (z) {
            doHideWindow();
        }
        return true;
    }

    private ExtractEditText getExtractEditTextIfVisible() {
        if (isExtractViewShown() && isInputViewShown()) {
            return this.mExtractEditText;
        }
        return null;
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            ExtractEditText extractEditTextIfVisible = getExtractEditTextIfVisible();
            if (extractEditTextIfVisible != null && extractEditTextIfVisible.handleBackInTextActionModeIfNeeded(keyEvent)) {
                return true;
            }
            if (!handleBack(false)) {
                return false;
            }
            keyEvent.startTracking();
            return true;
        }
        return doMovementKey(i, keyEvent, -1);
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return doMovementKey(i, keyEvent, i2);
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            ExtractEditText extractEditTextIfVisible = getExtractEditTextIfVisible();
            if (extractEditTextIfVisible != null && extractEditTextIfVisible.handleBackInTextActionModeIfNeeded(keyEvent)) {
                return true;
            }
            if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                return handleBack(true);
            }
        }
        return doMovementKey(i, keyEvent, -2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onToggleSoftInput(int i, int i2) {
        if (isInputViewShown()) {
            requestHideSelf(i2);
        } else {
            requestShowSelf(i);
        }
    }

    void reportExtractedMovement(int i, int i2) {
        int i3 = 0;
        switch (i) {
            case 19:
                i2 = -i2;
            case 20:
                i3 = i2;
                i2 = 0;
                break;
            case 21:
                i2 = -i2;
                break;
            case 22:
                break;
            default:
                i2 = 0;
                break;
        }
        onExtractedCursorMovement(i2, i3);
    }

    boolean doMovementKey(int i, KeyEvent keyEvent, int i2) {
        ExtractEditText extractEditTextIfVisible = getExtractEditTextIfVisible();
        if (extractEditTextIfVisible != null) {
            MovementMethod movementMethod = extractEditTextIfVisible.getMovementMethod();
            Layout layout = extractEditTextIfVisible.getLayout();
            if (movementMethod != null && layout != null) {
                if (i2 == -1) {
                    if (movementMethod.onKeyDown(extractEditTextIfVisible, extractEditTextIfVisible.getText(), i, keyEvent)) {
                        reportExtractedMovement(i, 1);
                        return true;
                    }
                } else if (i2 == -2) {
                    if (movementMethod.onKeyUp(extractEditTextIfVisible, extractEditTextIfVisible.getText(), i, keyEvent)) {
                        return true;
                    }
                } else if (movementMethod.onKeyOther(extractEditTextIfVisible, extractEditTextIfVisible.getText(), keyEvent)) {
                    reportExtractedMovement(i, i2);
                } else {
                    KeyEvent keyEventChangeAction = KeyEvent.changeAction(keyEvent, 0);
                    if (movementMethod.onKeyDown(extractEditTextIfVisible, extractEditTextIfVisible.getText(), i, keyEventChangeAction)) {
                        KeyEvent keyEventChangeAction2 = KeyEvent.changeAction(keyEvent, 1);
                        movementMethod.onKeyUp(extractEditTextIfVisible, extractEditTextIfVisible.getText(), i, keyEventChangeAction2);
                        while (true) {
                            i2--;
                            if (i2 <= 0) {
                                break;
                            }
                            movementMethod.onKeyDown(extractEditTextIfVisible, extractEditTextIfVisible.getText(), i, keyEventChangeAction);
                            movementMethod.onKeyUp(extractEditTextIfVisible, extractEditTextIfVisible.getText(), i, keyEventChangeAction2);
                        }
                        reportExtractedMovement(i, i2);
                    }
                }
            }
            switch (i) {
            }
            return true;
        }
        return false;
    }

    public void sendDownUpKeyEvents(int i) {
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputConnection == null) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        currentInputConnection.sendKeyEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, 0, i, 0, 0, -1, 0, 6));
        currentInputConnection.sendKeyEvent(new KeyEvent(jUptimeMillis, SystemClock.uptimeMillis(), 1, i, 0, 0, -1, 0, 6));
    }

    public boolean sendDefaultEditorAction(boolean z) {
        EditorInfo currentInputEditorInfo = getCurrentInputEditorInfo();
        if (currentInputEditorInfo == null) {
            return false;
        }
        if ((z && (currentInputEditorInfo.imeOptions & 1073741824) != 0) || (currentInputEditorInfo.imeOptions & 255) == 1) {
            return false;
        }
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputConnection != null) {
            currentInputConnection.performEditorAction(currentInputEditorInfo.imeOptions & 255);
        }
        return true;
    }

    public void sendKeyChar(char c) {
        if (c == '\n') {
            if (sendDefaultEditorAction(true)) {
                return;
            }
            sendDownUpKeyEvents(66);
        } else {
            if (c >= '0' && c <= '9') {
                sendDownUpKeyEvents((c - '0') + 7);
                return;
            }
            InputConnection currentInputConnection = getCurrentInputConnection();
            if (currentInputConnection != null) {
                currentInputConnection.commitText(String.valueOf(c), 1);
            }
        }
    }

    public void onExtractedSelectionChanged(int i, int i2) {
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputConnection != null) {
            currentInputConnection.setSelection(i, i2);
        }
    }

    public void onExtractedDeleteText(int i, int i2) {
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputConnection != null) {
            currentInputConnection.finishComposingText();
            currentInputConnection.setSelection(i, i);
            currentInputConnection.deleteSurroundingText(0, i2 - i);
        }
    }

    public void onExtractedReplaceText(int i, int i2, CharSequence charSequence) {
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputConnection != null) {
            currentInputConnection.setComposingRegion(i, i2);
            currentInputConnection.commitText(charSequence, 1);
        }
    }

    public void onExtractedSetSpan(Object obj, int i, int i2, int i3) {
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputConnection == null || !currentInputConnection.setSelection(i, i2)) {
            return;
        }
        CharSequence selectedText = currentInputConnection.getSelectedText(1);
        if (selectedText instanceof Spannable) {
            ((Spannable) selectedText).setSpan(obj, 0, selectedText.length(), i3);
            currentInputConnection.setComposingRegion(i, i2);
            currentInputConnection.commitText(selectedText, 1);
        }
    }

    public void onExtractedTextClicked() {
        ExtractEditText extractEditText = this.mExtractEditText;
        if (extractEditText != null && extractEditText.hasVerticalScrollBar()) {
            setCandidatesViewShown(false);
        }
    }

    public void onExtractedCursorMovement(int i, int i2) {
        ExtractEditText extractEditText = this.mExtractEditText;
        if (extractEditText == null || i2 == 0 || !extractEditText.hasVerticalScrollBar()) {
            return;
        }
        setCandidatesViewShown(false);
    }

    public boolean onExtractTextContextMenuItem(int i) {
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputConnection == null) {
            return true;
        }
        currentInputConnection.performContextMenuAction(i);
        return true;
    }

    public CharSequence getTextForImeAction(int i) {
        switch (i & 255) {
            case 1:
                return null;
            case 2:
                return getText(R.string.ime_action_go);
            case 3:
                return getText(R.string.ime_action_search);
            case 4:
                return getText(R.string.ime_action_send);
            case 5:
                return getText(R.string.ime_action_next);
            case 6:
                return getText(R.string.ime_action_done);
            case 7:
                return getText(R.string.ime_action_previous);
            default:
                return getText(R.string.ime_action_default);
        }
    }

    public void onUpdateExtractingVisibility(EditorInfo editorInfo) {
        if (editorInfo.inputType == 0 || (editorInfo.imeOptions & 268435456) != 0) {
            setExtractViewShown(false);
        } else {
            setExtractViewShown(true);
        }
    }

    public void onUpdateExtractingViews(EditorInfo editorInfo) {
        if (isExtractViewShown() && this.mExtractAccessories != null) {
            boolean z = true;
            if (editorInfo.actionLabel == null && ((editorInfo.imeOptions & 255) == 1 || (editorInfo.imeOptions & 536870912) != 0 || editorInfo.inputType == 0)) {
                z = false;
            }
            if (z) {
                this.mExtractAccessories.setVisibility(0);
                View view = this.mExtractAction;
                if (view != null) {
                    if (view instanceof ImageButton) {
                        ((ImageButton) view).setImageResource(getIconForImeAction(editorInfo.imeOptions));
                        if (editorInfo.actionLabel != null) {
                            this.mExtractAction.setContentDescription(editorInfo.actionLabel);
                        } else {
                            this.mExtractAction.setContentDescription(getTextForImeAction(editorInfo.imeOptions));
                        }
                    } else if (editorInfo.actionLabel != null) {
                        ((TextView) this.mExtractAction).setText(editorInfo.actionLabel);
                    } else {
                        ((TextView) this.mExtractAction).setText(getTextForImeAction(editorInfo.imeOptions));
                    }
                    this.mExtractAction.setOnClickListener(this.mActionClickListener);
                    return;
                }
                return;
            }
            this.mExtractAccessories.setVisibility(8);
            View view2 = this.mExtractAction;
            if (view2 != null) {
                view2.setOnClickListener(null);
            }
        }
    }

    public void onExtractingInputChanged(EditorInfo editorInfo) {
        if (editorInfo.inputType == 0) {
            requestHideSelf(2);
        }
    }

    void startExtractingText(boolean z) {
        ExtractEditText extractEditText = this.mExtractEditText;
        if (extractEditText != null && getCurrentInputStarted() && isFullscreenMode()) {
            this.mExtractedToken++;
            ExtractedTextRequest extractedTextRequest = new ExtractedTextRequest();
            extractedTextRequest.token = this.mExtractedToken;
            extractedTextRequest.flags = 1;
            extractedTextRequest.hintMaxLines = 10;
            extractedTextRequest.hintMaxChars = 10000;
            InputConnection currentInputConnection = getCurrentInputConnection();
            ExtractedText extractedText = currentInputConnection == null ? null : currentInputConnection.getExtractedText(extractedTextRequest, 1);
            this.mExtractedText = extractedText;
            if (extractedText == null || currentInputConnection == null) {
                Log.e(TAG, "Unexpected null in startExtractingText : mExtractedText = " + this.mExtractedText + ", input connection = " + currentInputConnection);
            }
            EditorInfo currentInputEditorInfo = getCurrentInputEditorInfo();
            try {
                extractEditText.startInternalChanges();
                onUpdateExtractingVisibility(currentInputEditorInfo);
                onUpdateExtractingViews(currentInputEditorInfo);
                int i = currentInputEditorInfo.inputType;
                if ((i & 15) == 1 && (262144 & i) != 0) {
                    i |= 131072;
                }
                extractEditText.setInputType(i);
                extractEditText.setHint(currentInputEditorInfo.hintText);
                if (this.mExtractedText != null) {
                    extractEditText.setEnabled(true);
                    extractEditText.setExtractedText(this.mExtractedText);
                } else {
                    extractEditText.setEnabled(false);
                    extractEditText.setText("");
                }
                if (z) {
                    onExtractingInputChanged(currentInputEditorInfo);
                }
            } finally {
                extractEditText.finishInternalChanges();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnCurrentInputMethodSubtypeChanged(InputMethodSubtype inputMethodSubtype) {
        synchronized (this.mLock) {
            this.mNotifyUserActionSent = false;
        }
        onCurrentInputMethodSubtypeChanged(inputMethodSubtype);
    }

    @Deprecated
    public int getInputMethodWindowRecommendedHeight() {
        Log.w(TAG, "getInputMethodWindowRecommendedHeight() is deprecated and now always returns 0. Do not use this method.");
        return 0;
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public final void exposeContent(InputContentInfo inputContentInfo, InputConnection inputConnection) {
        if (inputConnection != null && getCurrentInputConnection() == inputConnection) {
            exposeContentInternal(inputContentInfo, getCurrentInputEditorInfo());
        }
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public final void notifyUserActionIfNecessary() {
        synchronized (this.mLock) {
            if (this.mNotifyUserActionSent) {
                return;
            }
            this.mPrivOps.notifyUserAction();
            this.mNotifyUserActionSent = true;
        }
    }

    private void exposeContentInternal(InputContentInfo inputContentInfo, EditorInfo editorInfo) {
        Uri contentUri = inputContentInfo.getContentUri();
        IInputContentUriToken iInputContentUriTokenCreateInputContentUriToken = this.mPrivOps.createInputContentUriToken(contentUri, editorInfo.packageName);
        if (iInputContentUriTokenCreateInputContentUriToken == null) {
            Log.e(TAG, "createInputContentAccessToken failed. contentUri=" + contentUri.toString() + " packageName=" + editorInfo.packageName);
        } else {
            inputContentInfo.setUriToken(iInputContentUriTokenCreateInputContentUriToken);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int mapToImeWindowStatus() {
        int i = 2;
        if (!isInputViewShown()) {
            i = 0;
        } else if (this.mCanPreRender && !this.mWindowVisible) {
            i = 4;
        }
        return 1 | i;
    }

    private boolean isAutomotive() {
        return getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_AUTOMOTIVE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.inputmethodservice.AbstractInputMethodService, android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        PrintWriterPrinter printWriterPrinter = new PrintWriterPrinter(printWriter);
        printWriterPrinter.println("Input method service state for " + this + SettingsStringUtil.DELIMITER);
        printWriterPrinter.println("  mViewsCreated=" + this.mViewsCreated);
        printWriterPrinter.println("  mDecorViewVisible=" + this.mDecorViewVisible + " mDecorViewWasVisible=" + this.mDecorViewWasVisible + " mWindowVisible=" + this.mWindowVisible + " mInShowWindow=" + this.mInShowWindow);
        printWriterPrinter.println("  Configuration=" + getResources().getConfiguration());
        printWriterPrinter.println("  mToken=" + this.mToken);
        printWriterPrinter.println("  mInputBinding=" + this.mInputBinding);
        printWriterPrinter.println("  mInputConnection=" + this.mInputConnection);
        printWriterPrinter.println("  mStartedInputConnection=" + this.mStartedInputConnection);
        printWriterPrinter.println("  mInputStarted=" + this.mInputStarted + " mInputViewStarted=" + this.mInputViewStarted + " mCandidatesViewStarted=" + this.mCandidatesViewStarted);
        if (this.mInputEditorInfo != null) {
            printWriterPrinter.println("  mInputEditorInfo:");
            this.mInputEditorInfo.dump(printWriterPrinter, "    ");
        } else {
            printWriterPrinter.println("  mInputEditorInfo: null");
        }
        printWriterPrinter.println("  mShowInputRequested=" + this.mShowInputRequested + " mLastShowInputRequested=" + this.mLastShowInputRequested + " mCanPreRender=" + this.mCanPreRender + " mIsPreRendered=" + this.mIsPreRendered + " mShowInputFlags=0x" + Integer.toHexString(this.mShowInputFlags));
        printWriterPrinter.println("  mCandidatesVisibility=" + this.mCandidatesVisibility + " mFullscreenApplied=" + this.mFullscreenApplied + " mIsFullscreen=" + this.mIsFullscreen + " mExtractViewHidden=" + this.mExtractViewHidden);
        if (this.mExtractedText != null) {
            printWriterPrinter.println("  mExtractedText:");
            printWriterPrinter.println("    text=" + this.mExtractedText.text.length() + " chars startOffset=" + this.mExtractedText.startOffset);
            printWriterPrinter.println("    selectionStart=" + this.mExtractedText.selectionStart + " selectionEnd=" + this.mExtractedText.selectionEnd + " flags=0x" + Integer.toHexString(this.mExtractedText.flags));
        } else {
            printWriterPrinter.println("  mExtractedText: null");
        }
        printWriterPrinter.println("  mExtractedToken=" + this.mExtractedToken);
        printWriterPrinter.println("  mIsInputViewShown=" + this.mIsInputViewShown + " mStatusIcon=" + this.mStatusIcon);
        printWriterPrinter.println("Last computed insets:");
        printWriterPrinter.println("  contentTopInsets=" + this.mTmpInsets.contentTopInsets + " visibleTopInsets=" + this.mTmpInsets.visibleTopInsets + " touchableInsets=" + this.mTmpInsets.touchableInsets + " touchableRegion=" + this.mTmpInsets.touchableRegion);
        printWriterPrinter.println(" mSettingsObserver=" + this.mSettingsObserver);
    }
}
