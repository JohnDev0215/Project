package com.android.car.settings.sound;

import android.R;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.TypedValue;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiRadioButtonPreference;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public class RingtonePickerPreferenceController extends PreferenceController<PreferenceGroup> {
    static final String COLUMN_LABEL = "title";
    private static final Logger LOG = new Logger((Class<?>) RingtonePickerPreferenceController.class);
    static final int SILENT_ITEM_POS = -1;
    private static final String SOUND_NAME_RES_PREFIX = "sound_name_";
    private static final int UNKNOWN_POS = -2;
    private Bundle mArgs;
    private int mAttributesFlags;
    private Ringtone mCurrentRingtone;
    private int mCurrentlySelectedPos;
    private TwoStatePreference mCurrentlySelectedPreference;
    private LocalizedCursor mCursor;
    private Handler mHandler;
    private boolean mHasSilentItem;
    private RingtoneManager mRingtoneManager;
    private int mRingtoneType;
    private final Context mUserContext;

    public RingtonePickerPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mCurrentlySelectedPos = -2;
        this.mAttributesFlags = 0;
        this.mUserContext = createPackageContextAsUser(getContext(), UserHandle.myUserId());
        this.mRingtoneManager = new RingtoneManager(getContext(), true);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mArgs = new Bundle();
    }

    public void setArguments(Bundle args) {
        this.mArgs = args;
    }

    public int getCurrentlySelectedPreferencePos() {
        int count = getPreference().getPreferenceCount();
        for (int i = 0; i < count; i++) {
            TwoStatePreference pref = (TwoStatePreference) getPreference().getPreference(i);
            if (pref.isChecked()) {
                return i;
            }
        }
        return 0;
    }

    public void saveRingtone() {
        RingtoneManager.setActualDefaultRingtoneUri(this.mUserContext, this.mRingtoneType, getCurrentlySelectedRingtoneUri());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mRingtoneType = this.mArgs.getInt("android.intent.extra.ringtone.TYPE", -1);
        this.mHasSilentItem = this.mArgs.getBoolean("android.intent.extra.ringtone.SHOW_SILENT", true);
        this.mAttributesFlags |= this.mArgs.getInt("android.intent.extra.ringtone.AUDIO_ATTRIBUTES_FLAGS", 0);
        this.mRingtoneManager.setType(this.mRingtoneType);
        this.mCursor = new LocalizedCursor(this.mRingtoneManager.getCursor(), getContext().getResources(), "title");
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        stopAnyPlayingRingtone();
        clearSelection();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preference) {
        populateRingtones(preference);
        clearSelection();
        Uri currentRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this.mUserContext, this.mRingtoneType);
        initSelection(currentRingtoneUri);
    }

    private void populateRingtones(PreferenceGroup preference) {
        preference.removeAll();
        if (this.mHasSilentItem) {
            String label = getContext().getResources().getString(R.string.permdesc_accessWimaxState);
            preference.addPreference(createRingtonePreference(label, -1));
        }
        int pos = 0;
        this.mCursor.moveToFirst();
        while (!this.mCursor.isAfterLast()) {
            LocalizedCursor localizedCursor = this.mCursor;
            String label2 = localizedCursor.getString(localizedCursor.getColumnIndex("title"));
            preference.addPreference(createRingtonePreference(label2, pos));
            this.mCursor.moveToNext();
            pos++;
        }
    }

    private TwoStatePreference createRingtonePreference(String title, int key) {
        CarUiRadioButtonPreference preference = new CarUiRadioButtonPreference(getContext());
        preference.setTitle(title);
        preference.setKey(Integer.toString(key));
        preference.setChecked(false);
        preference.setViewId(-1);
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.sound.-$$Lambda$RingtonePickerPreferenceController$43VUar2ftEL-BfhtY4GK_LgcVd0
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return this.f$0.lambda$createRingtonePreference$0$RingtonePickerPreferenceController(preference2);
            }
        });
        return preference;
    }

    public /* synthetic */ boolean lambda$createRingtonePreference$0$RingtonePickerPreferenceController(Preference pref) {
        updateCurrentSelection((TwoStatePreference) pref);
        playCurrentlySelectedRingtone();
        return true;
    }

    private void updateCurrentSelection(TwoStatePreference preference) {
        TwoStatePreference twoStatePreference;
        int selectedPos = Integer.parseInt(preference.getKey());
        if (this.mCurrentlySelectedPos != selectedPos && (twoStatePreference = this.mCurrentlySelectedPreference) != null) {
            twoStatePreference.setChecked(false);
            this.mCurrentlySelectedPreference.setViewId(-1);
        }
        this.mCurrentlySelectedPreference = preference;
        this.mCurrentlySelectedPos = selectedPos;
        preference.setChecked(true);
        this.mCurrentlySelectedPreference.setViewId(com.android.car.settings.R.id.ringtone_picker_selected_id);
    }

    private void initSelection(Uri uri) {
        if (uri == null) {
            this.mCurrentlySelectedPos = -1;
        } else {
            this.mCurrentlySelectedPos = this.mRingtoneManager.getRingtonePosition(uri);
        }
        int count = getPreference().getPreferenceCount();
        for (int i = 0; i < count; i++) {
            TwoStatePreference pref = (TwoStatePreference) getPreference().getPreference(i);
            int pos = Integer.parseInt(pref.getKey());
            if (this.mCurrentlySelectedPos == pos) {
                this.mCurrentlySelectedPreference = pref;
                pref.setChecked(true);
                pref.setViewId(com.android.car.settings.R.id.ringtone_picker_selected_id);
            }
        }
    }

    private void clearSelection() {
        int count = getPreference().getPreferenceCount();
        for (int i = 0; i < count; i++) {
            TwoStatePreference pref = (TwoStatePreference) getPreference().getPreference(i);
            pref.setChecked(false);
            pref.setViewId(-1);
        }
        this.mCurrentlySelectedPreference = null;
        this.mCurrentlySelectedPos = -2;
    }

    private void playCurrentlySelectedRingtone() {
        this.mHandler.removeCallbacks(new $$Lambda$RingtonePickerPreferenceController$UM9PZPfnN1CKdItc388Ihj_g2pQ(this));
        this.mHandler.post(new $$Lambda$RingtonePickerPreferenceController$UM9PZPfnN1CKdItc388Ihj_g2pQ(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void run() {
        stopAnyPlayingRingtone();
        int i = this.mCurrentlySelectedPos;
        if (i == -1) {
            return;
        }
        if (i >= 0) {
            this.mCurrentRingtone = this.mRingtoneManager.getRingtone(i);
        }
        Ringtone ringtone = this.mCurrentRingtone;
        if (ringtone != null) {
            if (this.mAttributesFlags != 0) {
                ringtone.setAudioAttributes(new AudioAttributes.Builder(this.mCurrentRingtone.getAudioAttributes()).setFlags(this.mAttributesFlags).build());
            }
            this.mCurrentRingtone.play();
        }
    }

    private void stopAnyPlayingRingtone() {
        this.mHandler.removeCallbacks(new $$Lambda$RingtonePickerPreferenceController$UM9PZPfnN1CKdItc388Ihj_g2pQ(this));
        Ringtone ringtone = this.mCurrentRingtone;
        if (ringtone != null && ringtone.isPlaying()) {
            this.mCurrentRingtone.stop();
        }
        RingtoneManager ringtoneManager = this.mRingtoneManager;
        if (ringtoneManager != null) {
            ringtoneManager.stopPreviousRingtone();
        }
    }

    private Uri getCurrentlySelectedRingtoneUri() {
        int i = this.mCurrentlySelectedPos;
        if (i >= 0) {
            return this.mRingtoneManager.getRingtoneUri(i);
        }
        if (i == -1) {
            return null;
        }
        LOG.e("Requesting ringtone URI for unknown position: " + this.mCurrentlySelectedPos);
        return null;
    }

    private Context createPackageContextAsUser(Context context, int userId) {
        try {
            return context.createPackageContextAsUser(context.getPackageName(), 0, UserHandle.of(userId));
        } catch (PackageManager.NameNotFoundException e) {
            LOG.e("Failed to create user context", e);
            return null;
        }
    }

    private static class LocalizedCursor extends CursorWrapper {
        String mNamePrefix;
        final Resources mResources;
        final Pattern mSanitizePattern;
        final int mTitleIndex;

        LocalizedCursor(Cursor cursor, Resources resources, String columnLabel) {
            super(cursor);
            this.mTitleIndex = this.mCursor.getColumnIndex(columnLabel);
            this.mResources = resources;
            this.mSanitizePattern = Pattern.compile("[^a-zA-Z0-9]");
            if (this.mTitleIndex == -1) {
                RingtonePickerPreferenceController.LOG.e("No index for column " + columnLabel);
                this.mNamePrefix = null;
                return;
            }
            try {
                this.mNamePrefix = String.format("%s:%s/%s", this.mResources.getResourcePackageName(com.android.car.settings.R.string.ringtone_title), this.mResources.getResourceTypeName(com.android.car.settings.R.string.ringtone_title), RingtonePickerPreferenceController.SOUND_NAME_RES_PREFIX);
            } catch (Resources.NotFoundException e) {
                this.mNamePrefix = null;
            }
        }

        private String sanitize(String input) {
            if (input == null) {
                return "";
            }
            return this.mSanitizePattern.matcher(input).replaceAll("_").toLowerCase();
        }

        @Override // android.database.CursorWrapper, android.database.Cursor
        public String getString(int columnIndex) {
            String defaultName = this.mCursor.getString(columnIndex);
            if (columnIndex != this.mTitleIndex || this.mNamePrefix == null) {
                return defaultName;
            }
            TypedValue value = new TypedValue();
            try {
                this.mResources.getValue(this.mNamePrefix + sanitize(defaultName), value, false);
                if (value.type != 3) {
                    RingtonePickerPreferenceController.LOG.e("Invalid value when looking up localized name, using " + defaultName);
                    return defaultName;
                }
                RingtonePickerPreferenceController.LOG.d(String.format("Replacing name %s with %s", defaultName, value.string.toString()));
                return value.string.toString();
            } catch (Resources.NotFoundException e) {
                return defaultName;
            }
        }
    }

    void setRingtoneManager(RingtoneManager ringtoneManager) {
        this.mRingtoneManager = ringtoneManager;
    }
}
