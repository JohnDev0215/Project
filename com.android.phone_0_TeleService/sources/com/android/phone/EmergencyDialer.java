package com.android.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.ServiceState;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DialerKeyListener;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import com.android.phone.EmergencyInfoGroup;
import com.android.phone.EmergencyShortcutButton;
import com.android.phone.ShortcutViewUtils;
import com.android.phone.common.dialpad.DialpadKeyButton;
import com.android.phone.common.util.ViewUtil;
import com.android.phone.common.widget.ResizingTextEditText;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class EmergencyDialer extends Activity implements View.OnClickListener, View.OnLongClickListener, View.OnKeyListener, TextWatcher, DialpadKeyButton.OnPressedListener, WallpaperManager.OnColorsChangedListener, EmergencyShortcutButton.OnConfirmClickListener, EmergencyInfoGroup.OnConfirmClickListener {
    private static final int[] DIALER_KEYS = {R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.star, R.id.zero, R.id.pound};
    private ColorDrawable mBackgroundDrawable;
    private boolean mDTMFToneEnabled;
    private float mDefaultDigitsTextSize;
    private View mDelete;
    private View mDialButton;
    private View mDialpadView;
    ResizingTextEditText mDigits;
    private EmergencyActionGroup mEmergencyActionGroup;
    private EmergencyInfoGroup mEmergencyInfoGroup;
    private List<EmergencyShortcutButton> mEmergencyShortcutButtonList;
    private View mEmergencyShortcutView;
    private int mEntryType;
    private boolean mIsWfcEmergencyCallingWarningEnabled;
    private String mLastNumber;
    private EccShortcutAdapter mShortcutAdapter;
    private ShortcutViewUtils.Config mShortcutViewConfig;
    private boolean mSupportsDarkText;
    private ToneGenerator mToneGenerator;
    private DataSetObserver mShortcutDataSetObserver = null;
    private Object mToneGeneratorLock = new Object();
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.phone.EmergencyDialer.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                EmergencyDialer.this.finishAndRemoveTask();
            }
        }
    };
    private View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate(this) { // from class: com.android.phone.EmergencyDialer.2
        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() == 32 && view.getVisibility() == 8) {
                return;
            }
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }
    };

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected void maybeAddNumberFormatting() {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        maybeChangeHintSize();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (SpecialCharSequenceMgr.handleCharsForLockedDevice(this, editable.toString(), this)) {
            this.mDigits.getText().clear();
        }
        updateDialAndDeleteButtonStateEnabledAttr();
        updateTtsSpans();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        String numberFromIntent;
        super.onCreate(bundle);
        this.mEntryType = getIntent().getIntExtra("com.android.phone.EmergencyDialer.extra.ENTRY_TYPE", 0);
        Log.d("EmergencyDialer", "Launched from " + entryTypeToString(this.mEntryType));
        setShowWhenLocked(true);
        setTurnScreenOn(true);
        PersistableBundle configForSubId = ((CarrierConfigManager) getSystemService(CarrierConfigManager.class)).getConfigForSubId(SubscriptionManager.getDefaultVoiceSubscriptionId());
        this.mShortcutViewConfig = new ShortcutViewUtils.Config(this, configForSubId, this.mEntryType);
        Log.d("EmergencyDialer", "Enable emergency dialer shortcut: " + this.mShortcutViewConfig.isEnabled());
        if (this.mShortcutViewConfig.isEnabled()) {
            updateTheme(false);
        } else {
            updateTheme(supportsDarkText(getWallpaperManager().getWallpaperColors(2)));
        }
        setContentView(R.layout.emergency_dialer);
        ResizingTextEditText resizingTextEditText = (ResizingTextEditText) findViewById(R.id.digits);
        this.mDigits = resizingTextEditText;
        resizingTextEditText.setKeyListener(DialerKeyListener.getInstance());
        this.mDigits.setOnClickListener(this);
        this.mDigits.setOnKeyListener(this);
        this.mDigits.setLongClickable(false);
        this.mDigits.setInputType(0);
        this.mDefaultDigitsTextSize = this.mDigits.getScaledTextSize();
        maybeAddNumberFormatting();
        this.mBackgroundDrawable = new ColorDrawable();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getSize(new Point());
        this.mBackgroundDrawable.setAlpha(this.mShortcutViewConfig.isEnabled() ? 217 : 230);
        getWindow().setBackgroundDrawable(this.mBackgroundDrawable);
        if (findViewById(R.id.one) != null) {
            setupKeypad();
        }
        View viewFindViewById = findViewById(R.id.deleteButton);
        this.mDelete = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        this.mDelete.setOnLongClickListener(this);
        this.mDialButton = findViewById(R.id.floating_action_button);
        if (configForSubId.getBoolean("show_onscreen_dial_button_bool")) {
            this.mDialButton.setOnClickListener(this);
        } else {
            this.mDialButton.setVisibility(8);
        }
        this.mIsWfcEmergencyCallingWarningEnabled = configForSubId.getInt("emergency_notification_delay_int") > -1;
        maybeShowWfcEmergencyCallingWarning();
        ViewUtil.setupFloatingActionButton(this.mDialButton, getResources());
        if (bundle != null) {
            super.onRestoreInstanceState(bundle);
        }
        Uri data = getIntent().getData();
        if (data != null && "tel".equals(data.getScheme()) && (numberFromIntent = PhoneNumberUtils.getNumberFromIntent(getIntent(), this)) != null) {
            this.mDigits.setText(numberFromIntent);
        }
        synchronized (this.mToneGeneratorLock) {
            if (this.mToneGenerator == null) {
                try {
                    this.mToneGenerator = new ToneGenerator(8, 80);
                } catch (RuntimeException e) {
                    Log.w("EmergencyDialer", "Exception caught while creating local tone generator: " + e);
                    this.mToneGenerator = null;
                }
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(this.mBroadcastReceiver, intentFilter);
        this.mEmergencyActionGroup = (EmergencyActionGroup) findViewById(R.id.emergency_action_group);
        this.mEmergencyInfoGroup = (EmergencyInfoGroup) findViewById(R.id.emergency_info_button);
        if (this.mShortcutViewConfig.isEnabled()) {
            setupEmergencyShortcutsView();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        DataSetObserver dataSetObserver;
        super.onDestroy();
        synchronized (this.mToneGeneratorLock) {
            if (this.mToneGenerator != null) {
                this.mToneGenerator.release();
                this.mToneGenerator = null;
            }
        }
        unregisterReceiver(this.mBroadcastReceiver);
        EccShortcutAdapter eccShortcutAdapter = this.mShortcutAdapter;
        if (eccShortcutAdapter == null || (dataSetObserver = this.mShortcutDataSetObserver) == null) {
            return;
        }
        eccShortcutAdapter.unregisterDataSetObserver(dataSetObserver);
        this.mShortcutDataSetObserver = null;
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        this.mLastNumber = bundle.getString("lastNumber");
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("lastNumber", this.mLastNumber);
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.mDigits.addTextChangedListener(this);
    }

    private void setupKeypad() {
        for (int i : DIALER_KEYS) {
            ((DialpadKeyButton) findViewById(i)).setOnPressedListener(this);
        }
        findViewById(R.id.zero).setOnLongClickListener(this);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        View view;
        if (this.mShortcutViewConfig.isEnabled() && (view = this.mDialpadView) != null && view.getVisibility() == 0) {
            switchView(this.mEmergencyShortcutView, this.mDialpadView, true);
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 5) {
            if (TextUtils.isEmpty(this.mDigits.getText().toString())) {
                finish();
                return true;
            }
            placeCall();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void keyPressed(int i) {
        this.mDigits.performHapticFeedback(1);
        this.mDigits.onKeyDown(i, new KeyEvent(0, i));
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (view.getId() != R.id.digits || i != 66 || keyEvent.getAction() != 1) {
            return false;
        }
        placeCall();
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        onPreTouchEvent(motionEvent);
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        onPostTouchEvent(motionEvent);
        return zDispatchTouchEvent;
    }

    @Override // com.android.phone.EmergencyShortcutButton.OnConfirmClickListener
    public void onConfirmClick(EmergencyShortcutButton emergencyShortcutButton) {
        if (emergencyShortcutButton == null) {
            return;
        }
        String phoneNumber = emergencyShortcutButton.getPhoneNumber();
        if (!TextUtils.isEmpty(phoneNumber)) {
            placeCall(phoneNumber, 2, this.mShortcutViewConfig.getPhoneInfo());
        } else {
            Log.d("EmergencyDialer", "emergency number is empty");
        }
    }

    @Override // com.android.phone.EmergencyInfoGroup.OnConfirmClickListener
    public void onConfirmClick(EmergencyInfoGroup emergencyInfoGroup) {
        Intent intent;
        if (emergencyInfoGroup == null || (intent = (Intent) emergencyInfoGroup.getTag(R.id.tag_intent)) == null) {
            return;
        }
        startActivity(intent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.deleteButton) {
            keyPressed(67);
            return;
        }
        if (view.getId() == R.id.floating_action_button) {
            view.performHapticFeedback(1);
            placeCall();
        } else if (view.getId() == R.id.digits) {
            if (this.mDigits.length() != 0) {
                this.mDigits.setCursorVisible(true);
            }
        } else if (view.getId() == R.id.floating_action_button_dialpad) {
            this.mDigits.getText().clear();
            switchView(this.mDialpadView, this.mEmergencyShortcutView, true);
        }
    }

    @Override // com.android.phone.common.dialpad.DialpadKeyButton.OnPressedListener
    public void onPressed(View view, boolean z) {
        if (z) {
            if (view.getId() == R.id.one) {
                playTone(1);
                keyPressed(8);
                return;
            }
            if (view.getId() == R.id.two) {
                playTone(2);
                keyPressed(9);
                return;
            }
            if (view.getId() == R.id.three) {
                playTone(3);
                keyPressed(10);
                return;
            }
            if (view.getId() == R.id.four) {
                playTone(4);
                keyPressed(11);
                return;
            }
            if (view.getId() == R.id.five) {
                playTone(5);
                keyPressed(12);
                return;
            }
            if (view.getId() == R.id.six) {
                playTone(6);
                keyPressed(13);
                return;
            }
            if (view.getId() == R.id.seven) {
                playTone(7);
                keyPressed(14);
                return;
            }
            if (view.getId() == R.id.eight) {
                playTone(8);
                keyPressed(15);
                return;
            }
            if (view.getId() == R.id.nine) {
                playTone(9);
                keyPressed(16);
                return;
            }
            if (view.getId() == R.id.zero) {
                playTone(0);
                keyPressed(7);
            } else if (view.getId() == R.id.pound) {
                playTone(11);
                keyPressed(18);
            } else if (view.getId() == R.id.star) {
                playTone(10);
                keyPressed(17);
            }
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        int id = view.getId();
        if (id == R.id.deleteButton) {
            this.mDigits.getText().clear();
            return true;
        }
        if (id != R.id.zero) {
            return false;
        }
        removePreviousDigitIfPossible();
        keyPressed(81);
        return true;
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.mShortcutViewConfig.isEnabled()) {
            this.mBackgroundDrawable.setColor(-16777216);
            updateTheme(false);
        } else {
            WallpaperManager wallpaperManager = getWallpaperManager();
            if (wallpaperManager.isWallpaperSupported()) {
                wallpaperManager.addOnColorsChangedListener(this, null);
            }
            WallpaperColors wallpaperColors = wallpaperManager.getWallpaperColors(2);
            this.mBackgroundDrawable.setColor(getPrimaryColor(wallpaperColors));
            updateTheme(supportsDarkText(wallpaperColors));
        }
        if (this.mShortcutViewConfig.isEnabled()) {
            updateLocationAndEccInfo();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mDTMFToneEnabled = Settings.System.getInt(getContentResolver(), "dtmf_tone", 1) == 1;
        synchronized (this.mToneGeneratorLock) {
            if (this.mToneGenerator == null) {
                try {
                    this.mToneGenerator = new ToneGenerator(8, 80);
                } catch (RuntimeException e) {
                    Log.w("EmergencyDialer", "Exception caught while creating local tone generator: " + e);
                    this.mToneGenerator = null;
                }
            }
        }
        updateDialAndDeleteButtonStateEnabledAttr();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        WallpaperManager wallpaperManager = getWallpaperManager();
        if (wallpaperManager.isWallpaperSupported()) {
            wallpaperManager.removeOnColorsChangedListener(this);
        }
    }

    private void updateTheme(boolean z) {
        int i;
        if (this.mSupportsDarkText == z) {
            return;
        }
        this.mSupportsDarkText = z;
        if (this.mBackgroundDrawable != null) {
            recreate();
            return;
        }
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
        if (z) {
            i = systemUiVisibility | 16 | 8192;
            setTheme(R.style.EmergencyDialerThemeDark);
        } else {
            i = systemUiVisibility & 16 & 8192;
            setTheme(R.style.EmergencyDialerTheme);
        }
        getWindow().getDecorView().setSystemUiVisibility(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void placeCall() {
        /*
            r5 = this;
            com.android.phone.common.widget.ResizingTextEditText r0 = r5.mDigits
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            r5.mLastNumber = r0
            java.lang.String r0 = android.telephony.PhoneNumberUtils.convertToEmergencyNumber(r5, r0)
            r5.mLastNumber = r0
            com.android.phone.EccShortcutAdapter r1 = r5.mShortcutAdapter
            r2 = 0
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L26
            boolean r0 = r1.hasShortcut(r0)
            if (r0 == 0) goto L26
            com.android.phone.ShortcutViewUtils$Config r0 = r5.mShortcutViewConfig
            com.android.phone.ShortcutViewUtils$PhoneInfo r4 = r0.getPhoneInfo()
            goto L36
        L26:
            com.android.phone.ShortcutViewUtils$Config r0 = r5.mShortcutViewConfig
            java.lang.String r1 = r5.mLastNumber
            boolean r0 = r0.hasPromotedEmergencyNumber(r1)
            if (r0 == 0) goto L38
            com.android.phone.ShortcutViewUtils$Config r0 = r5.mShortcutViewConfig
            com.android.phone.ShortcutViewUtils$PhoneInfo r4 = r0.getPhoneInfo()
        L36:
            r0 = r3
            goto L48
        L38:
            java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
            java.lang.Object r0 = r5.getSystemService(r0)     // Catch: java.lang.IllegalStateException -> L47
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch: java.lang.IllegalStateException -> L47
            java.lang.String r1 = r5.mLastNumber     // Catch: java.lang.IllegalStateException -> L47
            boolean r0 = r0.isEmergencyNumber(r1)     // Catch: java.lang.IllegalStateException -> L47
            goto L48
        L47:
            r0 = r2
        L48:
            if (r0 == 0) goto L61
            java.lang.String r0 = r5.mLastNumber
            if (r0 == 0) goto L5b
            boolean r0 = android.text.TextUtils.isGraphic(r0)
            if (r0 != 0) goto L55
            goto L5b
        L55:
            java.lang.String r0 = r5.mLastNumber
            r5.placeCall(r0, r3, r4)
            goto L64
        L5b:
            r0 = 26
            r5.playTone(r0)
            return
        L61:
            r5.showDialog(r2)
        L64:
            com.android.phone.common.widget.ResizingTextEditText r0 = r5.mDigits
            android.text.Editable r0 = r0.getText()
            com.android.phone.common.widget.ResizingTextEditText r5 = r5.mDigits
            android.text.Editable r5 = r5.getText()
            int r5 = r5.length()
            r0.delete(r2, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.EmergencyDialer.placeCall():void");
    }

    private void placeCall(String str, int i, ShortcutViewUtils.PhoneInfo phoneInfo) {
        Log.d("EmergencyDialer", "Place emergency call from " + callSourceToString(i) + ", entry = " + entryTypeToString(this.mEntryType));
        Bundle bundle = new Bundle();
        bundle.putInt("android.telecom.extra.CALL_SOURCE", i);
        bundle.putBoolean("android.telecom.extra.IS_USER_INTENT_EMERGENCY_CALL", true);
        if (phoneInfo != null && phoneInfo.getPhoneAccountHandle() != null) {
            bundle.putParcelable("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneInfo.getPhoneAccountHandle());
        }
        ((TelecomManager) getSystemService(TelecomManager.class)).placeCall(Uri.fromParts("tel", str, null), bundle);
    }

    void playTone(int i) {
        int ringerMode;
        if (!this.mDTMFToneEnabled || (ringerMode = ((AudioManager) getSystemService("audio")).getRingerMode()) == 0 || ringerMode == 1) {
            return;
        }
        synchronized (this.mToneGeneratorLock) {
            if (this.mToneGenerator == null) {
                Log.w("EmergencyDialer", "playTone: mToneGenerator == null, tone: " + i);
                return;
            }
            this.mToneGenerator.startTone(i, 150);
        }
    }

    private CharSequence createErrorMessage(String str) {
        if (!TextUtils.isEmpty(str)) {
            String string = getString(R.string.dial_emergency_error, new Object[]{str});
            int iIndexOf = string.indexOf(str);
            int length = str.length() + iIndexOf;
            SpannableString spannableString = new SpannableString(string);
            PhoneNumberUtils.addTtsSpan(spannableString, iIndexOf, length);
            return spannableString;
        }
        return getText(R.string.dial_emergency_empty_error).toString();
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (i != 0) {
            return null;
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this, R.style.EmergencyDialerAlertDialogTheme).setTitle(getText(R.string.emergency_enable_radio_dialog_title)).setMessage(createErrorMessage(this.mLastNumber)).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).setCancelable(true).create();
        alertDialogCreate.getWindow().addFlags(4);
        setShowWhenLocked(true);
        return alertDialogCreate;
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int i, Dialog dialog) {
        super.onPrepareDialog(i, dialog);
        if (i == 0) {
            ((AlertDialog) dialog).setMessage(createErrorMessage(this.mLastNumber));
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void updateDialAndDeleteButtonStateEnabledAttr() {
        this.mDelete.setEnabled(this.mDigits.length() != 0);
    }

    private void removePreviousDigitIfPossible() {
        int selectionStart = this.mDigits.getSelectionStart();
        if (selectionStart > 0) {
            this.mDigits.setSelection(selectionStart);
            this.mDigits.getText().delete(selectionStart - 1, selectionStart);
        }
    }

    private void updateTtsSpans() {
        for (TtsSpan ttsSpan : (TtsSpan[]) this.mDigits.getText().getSpans(0, this.mDigits.getText().length(), TtsSpan.class)) {
            this.mDigits.getText().removeSpan(ttsSpan);
        }
        PhoneNumberUtils.ttsSpanAsPhoneNumber(this.mDigits.getText(), 0, this.mDigits.getText().length());
    }

    @Override // android.app.WallpaperManager.OnColorsChangedListener
    public void onColorsChanged(WallpaperColors wallpaperColors, int i) {
        if ((i & 2) != 0) {
            this.mBackgroundDrawable.setColor(getPrimaryColor(wallpaperColors));
            updateTheme(supportsDarkText(wallpaperColors));
        }
    }

    private void maybeShowWfcEmergencyCallingWarning() {
        if (!this.mIsWfcEmergencyCallingWarningEnabled) {
            Log.i("EmergencyDialer", "maybeShowWfcEmergencyCallingWarning: warning disabled by carrier.");
        } else {
            new AsyncTask<Void, Void, Boolean>() { // from class: com.android.phone.EmergencyDialer.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Boolean doInBackground(Void... voidArr) {
                    TelephonyManager telephonyManager = (TelephonyManager) EmergencyDialer.this.getSystemService(TelephonyManager.class);
                    boolean zIsWifiCallingAvailable = telephonyManager.isWifiCallingAvailable();
                    ServiceState serviceState = telephonyManager.getServiceState();
                    boolean z = serviceState.getRilVoiceRadioTechnology() != 0;
                    Log.i("EmergencyDialer", "showWfcWarningTask: isWfcAvailable=" + zIsWifiCallingAvailable + " isCellAvailable=" + z + "(rat=" + serviceState.getRilVoiceRadioTechnology() + ")");
                    return Boolean.valueOf(zIsWifiCallingAvailable && !z);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(Boolean bool) {
                    if (bool.booleanValue()) {
                        Log.i("EmergencyDialer", "showWfcWarningTask: showing ecall warning");
                        EmergencyDialer.this.mDigits.setHint(R.string.dial_emergency_calling_not_available);
                    } else {
                        Log.i("EmergencyDialer", "showWfcWarningTask: hiding ecall warning");
                        EmergencyDialer.this.mDigits.setHint("");
                    }
                    EmergencyDialer.this.maybeChangeHintSize();
                }
            }.execute(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeChangeHintSize() {
        if (TextUtils.isEmpty(this.mDigits.getHint()) || !TextUtils.isEmpty(this.mDigits.getText().toString())) {
            this.mDigits.setTextSize(2, this.mDefaultDigitsTextSize);
            this.mDigits.setResizeEnabled(true);
            Log.i("EmergencyDialer", "no hint - setting to " + this.mDigits.getScaledTextSize());
            return;
        }
        this.mDigits.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.emergency_call_warning_size));
        this.mDigits.setResizeEnabled(false);
        Log.i("EmergencyDialer", "hint - setting to " + this.mDigits.getScaledTextSize());
    }

    private void setupEmergencyShortcutsView() {
        this.mEmergencyShortcutView = findViewById(R.id.emergency_dialer_shortcuts);
        this.mDialpadView = findViewById(R.id.emergency_dialer);
        this.mEmergencyShortcutView.setAccessibilityDelegate(this.mAccessibilityDelegate);
        this.mDialpadView.setAccessibilityDelegate(this.mAccessibilityDelegate);
        findViewById(R.id.floating_action_button_dialpad).setOnClickListener(this);
        this.mEmergencyInfoGroup.setOnConfirmClickListener(this);
        this.mEmergencyShortcutButtonList = new ArrayList();
        setupEmergencyCallShortcutButton();
        updateLocationAndEccInfo();
        switchView(this.mEmergencyShortcutView, this.mDialpadView, false);
    }

    private void setLocationInfo() {
        String displayCountry;
        View viewFindViewById = findViewById(R.id.location_info);
        String countryIso = this.mShortcutViewConfig.getCountryIso();
        if (TextUtils.isEmpty(countryIso)) {
            displayCountry = null;
        } else {
            Locale locale = Locale.getDefault();
            displayCountry = new Locale(locale.getLanguage(), countryIso, locale.getVariant()).getDisplayCountry();
        }
        if (TextUtils.isEmpty(displayCountry)) {
            viewFindViewById.setVisibility(4);
        } else {
            ((TextView) viewFindViewById.findViewById(R.id.location_text)).setText(displayCountry);
            viewFindViewById.setVisibility(0);
        }
    }

    private void setupEmergencyCallShortcutButton() {
        final ViewGroup viewGroup = (ViewGroup) findViewById(R.id.emergency_shortcut_buttons_container);
        viewGroup.setClipToOutline(true);
        final TextView textView = (TextView) findViewById(R.id.emergency_number_title);
        this.mShortcutAdapter = new EccShortcutAdapter(this) { // from class: com.android.phone.EmergencyDialer.4
            @Override // com.android.phone.EccShortcutAdapter
            public View inflateView(View view, ViewGroup viewGroup2, CharSequence charSequence, CharSequence charSequence2, int i) {
                EmergencyShortcutButton emergencyShortcutButton = (EmergencyShortcutButton) EmergencyDialer.this.getLayoutInflater().inflate(R.layout.emergency_shortcut_button, viewGroup2, false);
                emergencyShortcutButton.setPhoneNumber(charSequence);
                emergencyShortcutButton.setPhoneDescription(charSequence2);
                emergencyShortcutButton.setPhoneTypeIcon(i);
                emergencyShortcutButton.setOnConfirmClickListener(EmergencyDialer.this);
                return emergencyShortcutButton;
            }
        };
        DataSetObserver dataSetObserver = new DataSetObserver() { // from class: com.android.phone.EmergencyDialer.5
            @Override // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                updateLayout();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                super.onInvalidated();
                updateLayout();
            }

            private void updateLayout() {
                viewGroup.removeAllViews();
                EmergencyDialer.this.mEmergencyShortcutButtonList.clear();
                for (int i = 0; i < EmergencyDialer.this.mShortcutAdapter.getCount() && i < 3; i++) {
                    EmergencyShortcutButton emergencyShortcutButton = (EmergencyShortcutButton) EmergencyDialer.this.mShortcutAdapter.getView(i, null, viewGroup);
                    EmergencyDialer.this.mEmergencyShortcutButtonList.add(emergencyShortcutButton);
                    viewGroup.addView(emergencyShortcutButton);
                }
                if (EmergencyDialer.this.mEmergencyShortcutButtonList.size() > 1) {
                    textView.setText(EmergencyDialer.this.getString(R.string.numerous_emergency_numbers_title));
                } else {
                    textView.setText(EmergencyDialer.this.getText(R.string.single_emergency_number_title));
                }
            }
        };
        this.mShortcutDataSetObserver = dataSetObserver;
        this.mShortcutAdapter.registerDataSetObserver(dataSetObserver);
    }

    private void updateLocationAndEccInfo() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        setLocationInfo();
        EccShortcutAdapter eccShortcutAdapter = this.mShortcutAdapter;
        if (eccShortcutAdapter != null) {
            eccShortcutAdapter.updateCountryEccInfo(this, this.mShortcutViewConfig.getPhoneInfo());
        }
    }

    private void onPreTouchEvent(MotionEvent motionEvent) {
        this.mEmergencyActionGroup.onPreTouchEvent(motionEvent);
        this.mEmergencyInfoGroup.onPreTouchEvent(motionEvent);
        List<EmergencyShortcutButton> list = this.mEmergencyShortcutButtonList;
        if (list != null) {
            Iterator<EmergencyShortcutButton> it = list.iterator();
            while (it.hasNext()) {
                it.next().onPreTouchEvent(motionEvent);
            }
        }
    }

    private void onPostTouchEvent(MotionEvent motionEvent) {
        this.mEmergencyActionGroup.onPostTouchEvent(motionEvent);
        this.mEmergencyInfoGroup.onPostTouchEvent(motionEvent);
        List<EmergencyShortcutButton> list = this.mEmergencyShortcutButtonList;
        if (list != null) {
            Iterator<EmergencyShortcutButton> it = list.iterator();
            while (it.hasNext()) {
                it.next().onPostTouchEvent(motionEvent);
            }
        }
    }

    private void switchView(View view, View view2, boolean z) {
        if (view == null || view2 == null || view.getVisibility() == 0) {
            return;
        }
        if (z) {
            crossfade(view2, view);
        } else {
            view2.setVisibility(8);
            view.setVisibility(0);
        }
    }

    private void crossfade(final View view, View view2) {
        if (view == null || view2 == null) {
            return;
        }
        int integer = getResources().getInteger(android.R.integer.config_shortAnimTime);
        view2.setAlpha(0.0f);
        view2.setVisibility(0);
        long j = integer;
        view2.animate().alpha(1.0f).setDuration(j).setListener(null);
        view.animate().alpha(0.0f).setDuration(j).setListener(new AnimatorListenerAdapter(this) { // from class: com.android.phone.EmergencyDialer.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(8);
            }
        });
    }

    private String entryTypeToString(int i) {
        if (i == 1) {
            return "LockScreen";
        }
        if (i == 2) {
            return "PowerMenu";
        }
        return "Unknown-" + i;
    }

    private String callSourceToString(int i) {
        if (i == 1) {
            return "DialPad";
        }
        if (i == 2) {
            return "Shortcut";
        }
        return "Unknown-" + i;
    }

    private WallpaperManager getWallpaperManager() {
        return (WallpaperManager) getSystemService(WallpaperManager.class);
    }

    private static boolean supportsDarkText(WallpaperColors wallpaperColors) {
        return (wallpaperColors == null || (wallpaperColors.getColorHints() & 1) == 0) ? false : true;
    }

    private int getPrimaryColor(WallpaperColors wallpaperColors) {
        Color colorValueOf;
        if (wallpaperColors == null) {
            return -16777216;
        }
        Color primaryColor = wallpaperColors.getPrimaryColor();
        if (this.mSupportsDarkText) {
            colorValueOf = Color.valueOf(-16777216);
        } else {
            colorValueOf = Color.valueOf(-1);
        }
        Color colorValueOf2 = Color.valueOf(-16725933);
        while (!checkContrastRatio(primaryColor, colorValueOf)) {
            primaryColor = getNextColor(primaryColor, this.mSupportsDarkText);
        }
        if (!this.mSupportsDarkText) {
            while (!checkContrastRatio(primaryColor, colorValueOf2)) {
                primaryColor = getNextColor(primaryColor, this.mSupportsDarkText);
            }
        }
        return primaryColor.toArgb();
    }

    private Color getNextColor(Color color, boolean z) {
        float f = (z ? 1.0f : -1.0f) * 0.0625f;
        float fRed = color.red() + f;
        float fGreen = color.green() + f;
        float fBlue = color.blue() + f;
        if (fRed < 0.0f) {
            fRed = 0.0f;
        }
        if (fGreen < 0.0f) {
            fGreen = 0.0f;
        }
        if (fBlue < 0.0f) {
            fBlue = 0.0f;
        }
        if (fRed > 1.0f) {
            fRed = 1.0f;
        }
        if (fGreen > 1.0f) {
            fGreen = 1.0f;
        }
        return Color.valueOf(fRed, fGreen, fBlue <= 1.0f ? fBlue : 1.0f);
    }

    private boolean checkContrastRatio(Color color, Color color2) {
        float fLuminance = color.luminance();
        float fLuminance2 = color2.luminance();
        return ((fLuminance > fLuminance2 ? 1 : (fLuminance == fLuminance2 ? 0 : -1)) >= 0 ? (((double) fLuminance) + 0.05d) / (((double) fLuminance2) + 0.05d) : (((double) fLuminance2) + 0.05d) / (((double) fLuminance) + 0.05d)) > 5.0d;
    }
}
