package com.android.phone.settings.fdn;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DialerKeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.internal.telephony.PhoneFactory;
import com.android.phone.PhoneGlobals;
import com.android.phone.R;
import com.android.phone.SubscriptionInfoHelper;

/* JADX INFO: loaded from: classes.dex */
public class EditFdnContactScreen extends Activity {
    private static final Intent CONTACT_IMPORT_INTENT;
    private static final String[] NUM_PROJECTION = {"display_name", "data1"};
    private boolean mAddContact;
    private Button mButton;
    private boolean mDataBusy;
    private String mName;
    private EditText mNameField;
    private String mNumber;
    private EditText mNumberField;
    private String mPin2;
    private LinearLayout mPinFieldContainer;
    private QueryHandler mQueryHandler;
    private SubscriptionInfoHelper mSubscriptionInfoHelper;
    private Handler mHandler = new Handler();
    private int mFdnNumberLimitLength = 20;
    private final View.OnClickListener mClicked = new View.OnClickListener() { // from class: com.android.phone.settings.fdn.EditFdnContactScreen.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (EditFdnContactScreen.this.mPinFieldContainer.getVisibility() != 0) {
                return;
            }
            if (view == EditFdnContactScreen.this.mNameField) {
                EditFdnContactScreen.this.mButton.requestFocus();
                return;
            }
            if (view == EditFdnContactScreen.this.mNumberField) {
                EditFdnContactScreen.this.mButton.requestFocus();
                return;
            }
            if (view == EditFdnContactScreen.this.mButton) {
                if (!EditFdnContactScreen.this.isValidNumber(PhoneNumberUtils.convertAndStrip(EditFdnContactScreen.this.getNumberFromTextField()))) {
                    EditFdnContactScreen.this.handleResult(false, true);
                } else {
                    if (EditFdnContactScreen.this.mDataBusy) {
                        return;
                    }
                    EditFdnContactScreen.this.authenticatePin2();
                }
            }
        }
    };
    private final View.OnFocusChangeListener mOnFocusChangeHandler = new View.OnFocusChangeListener(this) { // from class: com.android.phone.settings.fdn.EditFdnContactScreen.3
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                Selection.selectAll((Spannable) ((TextView) view).getText());
            }
        }
    };
    private final TextWatcher mTextWatcher = new TextWatcher() { // from class: com.android.phone.settings.fdn.EditFdnContactScreen.4
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            EditFdnContactScreen.this.setButtonEnabled();
        }
    };

    static {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        CONTACT_IMPORT_INTENT = intent;
        intent.setType("vnd.android.cursor.item/phone_v2");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        PersistableBundle carrierConfig;
        super.onCreate(bundle);
        resolveIntent();
        getWindow().requestFeature(5);
        setContentView(R.layout.edit_fdn_contact_screen);
        setupView();
        setTitle(this.mAddContact ? R.string.add_fdn_contact : R.string.edit_fdn_contact);
        if (this.mSubscriptionInfoHelper.hasSubId()) {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mSubscriptionInfoHelper.getSubId());
        } else {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfig();
        }
        if (carrierConfig != null) {
            this.mFdnNumberLimitLength = carrierConfig.getInt("fdn_number_length_limit_int");
        }
        displayProgress(false);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        byte b = 0;
        if (i == 100) {
            Bundle extras = intent != null ? intent.getExtras() : null;
            if (extras == null) {
                if (i2 != -1) {
                    finish();
                    return;
                }
                return;
            } else {
                this.mPin2 = extras.getString("pin2");
                if (this.mAddContact) {
                    addContact();
                    return;
                } else {
                    updateContact();
                    return;
                }
            }
        }
        if (i == 200 && i2 == -1) {
            try {
                Cursor cursorQuery = getContentResolver().query(intent.getData(), NUM_PROJECTION, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    this.mNameField.setText(cursorQuery.getString(0));
                    this.mNumberField.setText(cursorQuery.getString(1));
                    if (cursorQuery != null) {
                        cursorQuery.close();
                        return;
                    }
                    return;
                }
                Log.w("PhoneGlobals", "onActivityResult: bad contact data, no results found.");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    (b == true ? 1 : 0).close();
                }
                throw th;
            }
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Resources resources = getResources();
        menu.add(0, 1, 0, resources.getString(R.string.importToFDNfromContacts)).setIcon(R.drawable.ic_menu_contact);
        menu.add(0, 2, 0, resources.getString(R.string.menu_delete)).setIcon(android.R.drawable.ic_menu_delete);
        return true;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean zOnPrepareOptionsMenu = super.onPrepareOptionsMenu(menu);
        if (this.mDataBusy) {
            return false;
        }
        return zOnPrepareOptionsMenu;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            startActivityForResult(CONTACT_IMPORT_INTENT, 200);
            return true;
        }
        if (itemId == 2) {
            deleteSelected();
            return true;
        }
        if (itemId == 16908332) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void resolveIntent() {
        Intent intent = getIntent();
        this.mSubscriptionInfoHelper = new SubscriptionInfoHelper(this, intent);
        this.mName = intent.getStringExtra("name");
        String stringExtra = intent.getStringExtra("number");
        this.mNumber = stringExtra;
        this.mAddContact = TextUtils.isEmpty(stringExtra);
    }

    private void setupView() {
        EditText editText = (EditText) findViewById(R.id.fdn_name);
        this.mNameField = editText;
        if (editText != null) {
            editText.setOnFocusChangeListener(this.mOnFocusChangeHandler);
            this.mNameField.setOnClickListener(this.mClicked);
            this.mNameField.addTextChangedListener(this.mTextWatcher);
        }
        EditText editText2 = (EditText) findViewById(R.id.fdn_number);
        this.mNumberField = editText2;
        if (editText2 != null) {
            editText2.setTextDirection(3);
            this.mNumberField.setKeyListener(DialerKeyListener.getInstance());
            this.mNumberField.setOnFocusChangeListener(this.mOnFocusChangeHandler);
            this.mNumberField.setOnClickListener(this.mClicked);
            this.mNumberField.addTextChangedListener(this.mTextWatcher);
        }
        if (!this.mAddContact) {
            EditText editText3 = this.mNameField;
            if (editText3 != null) {
                editText3.setText(this.mName);
            }
            EditText editText4 = this.mNumberField;
            if (editText4 != null) {
                editText4.setText(this.mNumber);
            }
        }
        Button button = (Button) findViewById(R.id.button);
        this.mButton = button;
        if (button != null) {
            button.setOnClickListener(this.mClicked);
            setButtonEnabled();
        }
        this.mPinFieldContainer = (LinearLayout) findViewById(R.id.pinc);
    }

    private String getNameFromTextField() {
        return this.mNameField.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNumberFromTextField() {
        return this.mNumberField.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setButtonEnabled() {
        EditText editText;
        Button button = this.mButton;
        if (button == null || (editText = this.mNameField) == null || this.mNumberField == null) {
            return;
        }
        button.setEnabled(editText.length() > 0 && this.mNumberField.length() > 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidNumber(String str) {
        return str.length() <= this.mFdnNumberLimitLength && str.length() > 0;
    }

    private void addContact() {
        String strConvertAndStrip = PhoneNumberUtils.convertAndStrip(getNumberFromTextField());
        if (!isValidNumber(strConvertAndStrip)) {
            handleResult(false, true);
            return;
        }
        Uri contentUri = FdnList.getContentUri(this.mSubscriptionInfoHelper);
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("tag", getNameFromTextField());
        contentValues.put("number", strConvertAndStrip);
        contentValues.put("pin2", this.mPin2);
        QueryHandler queryHandler = new QueryHandler(getContentResolver());
        this.mQueryHandler = queryHandler;
        queryHandler.startInsert(0, null, contentUri, contentValues);
        displayProgress(true);
        showStatus(getResources().getText(R.string.adding_fdn_contact));
    }

    private void updateContact() {
        String nameFromTextField = getNameFromTextField();
        String strConvertAndStrip = PhoneNumberUtils.convertAndStrip(getNumberFromTextField());
        if (!isValidNumber(strConvertAndStrip)) {
            handleResult(false, true);
            return;
        }
        Uri contentUri = FdnList.getContentUri(this.mSubscriptionInfoHelper);
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag", this.mName);
        contentValues.put("number", this.mNumber);
        contentValues.put("newTag", nameFromTextField);
        contentValues.put("newNumber", strConvertAndStrip);
        contentValues.put("pin2", this.mPin2);
        QueryHandler queryHandler = new QueryHandler(getContentResolver());
        this.mQueryHandler = queryHandler;
        queryHandler.startUpdate(0, null, contentUri, contentValues, null, null);
        displayProgress(true);
        showStatus(getResources().getText(R.string.updating_fdn_contact));
    }

    private void deleteSelected() {
        if (!this.mAddContact) {
            Intent intent = this.mSubscriptionInfoHelper.getIntent(DeleteFdnContactScreen.class);
            intent.putExtra("name", this.mName);
            intent.putExtra("number", this.mNumber);
            startActivity(intent);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void authenticatePin2() {
        Intent intent = new Intent();
        intent.setClass(this, GetPin2Screen.class);
        intent.setData(FdnList.getContentUri(this.mSubscriptionInfoHelper));
        startActivityForResult(intent, 100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayProgress(boolean z) {
        this.mDataBusy = z;
        getWindow().setFeatureInt(5, this.mDataBusy ? -1 : -2);
        this.mButton.setClickable(!this.mDataBusy);
    }

    private void showStatus(CharSequence charSequence) {
        if (charSequence != null) {
            Toast.makeText(this, charSequence, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(boolean z, boolean z2) {
        if (z) {
            showStatus(getResources().getText(this.mAddContact ? R.string.fdn_contact_added : R.string.fdn_contact_updated));
        } else if (z2) {
            showStatus(getResources().getString(R.string.fdn_invalid_number, Integer.valueOf(this.mFdnNumberLimitLength)));
        } else if (PhoneFactory.getDefaultPhone().getIccCard().getIccPin2Blocked()) {
            showStatus(getResources().getText(R.string.fdn_enable_puk2_requested));
        } else if (PhoneFactory.getDefaultPhone().getIccCard().getIccPuk2Blocked()) {
            showStatus(getResources().getText(R.string.puk2_blocked));
        } else {
            showStatus(getResources().getText(R.string.pin2_or_fdn_invalid));
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.android.phone.settings.fdn.EditFdnContactScreen.1
            @Override // java.lang.Runnable
            public void run() {
                EditFdnContactScreen.this.finish();
            }
        }, 2000L);
    }

    private class QueryHandler extends AsyncQueryHandler {
        @Override // android.content.AsyncQueryHandler
        protected void onDeleteComplete(int i, Object obj, int i2) {
        }

        @Override // android.content.AsyncQueryHandler
        protected void onQueryComplete(int i, Object obj, Cursor cursor) {
        }

        public QueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        @Override // android.content.AsyncQueryHandler
        protected void onInsertComplete(int i, Object obj, Uri uri) {
            EditFdnContactScreen.this.displayProgress(false);
            EditFdnContactScreen.this.handleResult(uri != null, false);
        }

        @Override // android.content.AsyncQueryHandler
        protected void onUpdateComplete(int i, Object obj, int i2) {
            EditFdnContactScreen.this.displayProgress(false);
            EditFdnContactScreen.this.handleResult(i2 > 0, false);
        }
    }
}
