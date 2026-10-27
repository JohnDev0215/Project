package com.android.car.developeroptions.inputmethod;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.hardware.input.InputDeviceIdentifier;
import android.hardware.input.InputManager;
import android.hardware.input.KeyboardLayout;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.UserHandle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.InputDevice;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Settings$KeyboardLayoutPickerActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.inputmethod.KeyboardLayoutDialogFragment;
import com.android.car.developeroptions.inputmethod.PhysicalKeyboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.utils.ThreadUtils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class PhysicalKeyboardFragment extends SettingsPreferenceFragment implements InputManager.InputDeviceListener, KeyboardLayoutDialogFragment.OnSetupKeyboardLayoutsListener {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.inputmethod.PhysicalKeyboardFragment.3
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.physical_keyboard_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private InputManager mIm;
    private Intent mIntentWaitingForResult;
    private PreferenceCategory mKeyboardAssistanceCategory;
    private SwitchPreference mShowVirtualKeyboardSwitch;
    private final ArrayList<HardKeyboardDeviceInfo> mLastHardKeyboards = new ArrayList<>();
    private final Preference.OnPreferenceChangeListener mShowVirtualKeyboardSwitchPreferenceChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.android.car.developeroptions.inputmethod.-$$Lambda$PhysicalKeyboardFragment$E76nNxvmS-SOzJo9i9ENN6WsfwY
        @Override // androidx.preference.Preference.OnPreferenceChangeListener
        public final boolean onPreferenceChange(Preference preference, Object obj) {
            return this.f$0.lambda$new$3$PhysicalKeyboardFragment(preference, obj);
        }
    };
    private final ContentObserver mContentObserver = new ContentObserver(new Handler(true)) { // from class: com.android.car.developeroptions.inputmethod.PhysicalKeyboardFragment.2
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            PhysicalKeyboardFragment.this.updateShowVirtualKeyboardSwitch();
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 346;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        FragmentActivity activity = getActivity();
        Objects.requireNonNull(activity);
        addPreferencesFromResource(R.xml.physical_keyboard_settings);
        InputManager inputManager = (InputManager) activity.getSystemService(InputManager.class);
        Objects.requireNonNull(inputManager);
        this.mIm = inputManager;
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("keyboard_assistance_category");
        Objects.requireNonNull(preferenceCategory);
        PreferenceCategory preferenceCategory2 = preferenceCategory;
        this.mKeyboardAssistanceCategory = preferenceCategory2;
        SwitchPreference switchPreference = (SwitchPreference) preferenceCategory2.findPreference("show_virtual_keyboard_switch");
        Objects.requireNonNull(switchPreference);
        this.mShowVirtualKeyboardSwitch = switchPreference;
        findPreference("keyboard_shortcuts_helper").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.inputmethod.PhysicalKeyboardFragment.1
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference) {
                PhysicalKeyboardFragment.this.toggleKeyboardShortcutsMenu();
                return true;
            }
        });
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mLastHardKeyboards.clear();
        scheduleUpdateHardKeyboards();
        this.mIm.registerInputDeviceListener(this, null);
        this.mShowVirtualKeyboardSwitch.setOnPreferenceChangeListener(this.mShowVirtualKeyboardSwitchPreferenceChangeListener);
        registerShowVirtualKeyboardSettingsObserver();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mLastHardKeyboards.clear();
        this.mIm.unregisterInputDeviceListener(this);
        this.mShowVirtualKeyboardSwitch.setOnPreferenceChangeListener(null);
        unregisterShowVirtualKeyboardSettingsObserver();
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceAdded(int i) {
        scheduleUpdateHardKeyboards();
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceRemoved(int i) {
        scheduleUpdateHardKeyboards();
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceChanged(int i) {
        scheduleUpdateHardKeyboards();
    }

    private void scheduleUpdateHardKeyboards() {
        final Context context = getContext();
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.inputmethod.-$$Lambda$PhysicalKeyboardFragment$arLAO0iAtZoq7T73cur7EHaxZIc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$scheduleUpdateHardKeyboards$1$PhysicalKeyboardFragment(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$scheduleUpdateHardKeyboards$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$scheduleUpdateHardKeyboards$1$PhysicalKeyboardFragment(Context context) {
        final List<HardKeyboardDeviceInfo> hardKeyboards = getHardKeyboards(context);
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.inputmethod.-$$Lambda$PhysicalKeyboardFragment$WEzH950mO-83FAPQRCk2pDXJunA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$scheduleUpdateHardKeyboards$0$PhysicalKeyboardFragment(hardKeyboards);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateHardKeyboards, reason: merged with bridge method [inline-methods] */
    public void lambda$scheduleUpdateHardKeyboards$0$PhysicalKeyboardFragment(List<HardKeyboardDeviceInfo> list) {
        if (Objects.equals(this.mLastHardKeyboards, list)) {
            return;
        }
        this.mLastHardKeyboards.clear();
        this.mLastHardKeyboards.addAll(list);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        PreferenceCategory preferenceCategory = new PreferenceCategory(getPrefContext());
        preferenceCategory.setTitle(R.string.builtin_keyboard_settings_title);
        preferenceCategory.setOrder(0);
        preferenceScreen.addPreference(preferenceCategory);
        for (final HardKeyboardDeviceInfo hardKeyboardDeviceInfo : list) {
            Preference preference = new Preference(getPrefContext());
            preference.setTitle(hardKeyboardDeviceInfo.mDeviceName);
            preference.setSummary(hardKeyboardDeviceInfo.mLayoutLabel);
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.inputmethod.-$$Lambda$PhysicalKeyboardFragment$ToV4lbGrfkpzh0SO5sK30Vvvjl8
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return this.f$0.lambda$updateHardKeyboards$2$PhysicalKeyboardFragment(hardKeyboardDeviceInfo, preference2);
                }
            });
            preferenceCategory.addPreference(preference);
        }
        this.mKeyboardAssistanceCategory.setOrder(1);
        preferenceScreen.addPreference(this.mKeyboardAssistanceCategory);
        updateShowVirtualKeyboardSwitch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateHardKeyboards$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updateHardKeyboards$2$PhysicalKeyboardFragment(HardKeyboardDeviceInfo hardKeyboardDeviceInfo, Preference preference) {
        showKeyboardLayoutDialog(hardKeyboardDeviceInfo.mDeviceIdentifier);
        return true;
    }

    private void showKeyboardLayoutDialog(InputDeviceIdentifier inputDeviceIdentifier) {
        KeyboardLayoutDialogFragment keyboardLayoutDialogFragment = new KeyboardLayoutDialogFragment(inputDeviceIdentifier);
        keyboardLayoutDialogFragment.setTargetFragment(this, 0);
        keyboardLayoutDialogFragment.show(getActivity().getSupportFragmentManager(), "keyboardLayout");
    }

    private void registerShowVirtualKeyboardSettingsObserver() {
        unregisterShowVirtualKeyboardSettingsObserver();
        getActivity().getContentResolver().registerContentObserver(Settings.Secure.getUriFor("show_ime_with_hard_keyboard"), false, this.mContentObserver, UserHandle.myUserId());
        updateShowVirtualKeyboardSwitch();
    }

    private void unregisterShowVirtualKeyboardSettingsObserver() {
        getActivity().getContentResolver().unregisterContentObserver(this.mContentObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShowVirtualKeyboardSwitch() {
        this.mShowVirtualKeyboardSwitch.setChecked(Settings.Secure.getInt(getContentResolver(), "show_ime_with_hard_keyboard", 0) != 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleKeyboardShortcutsMenu() {
        getActivity().requestShowKeyboardShortcuts();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$new$3$PhysicalKeyboardFragment(Preference preference, Object obj) {
        Settings.Secure.putInt(getContentResolver(), "show_ime_with_hard_keyboard", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }

    @Override // com.android.car.developeroptions.inputmethod.KeyboardLayoutDialogFragment.OnSetupKeyboardLayoutsListener
    public void onSetupKeyboardLayouts(InputDeviceIdentifier inputDeviceIdentifier) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClass(getActivity(), Settings$KeyboardLayoutPickerActivity.class);
        intent.putExtra("input_device_identifier", (Parcelable) inputDeviceIdentifier);
        this.mIntentWaitingForResult = intent;
        startActivityForResult(intent, 0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Intent intent2 = this.mIntentWaitingForResult;
        if (intent2 != null) {
            InputDeviceIdentifier inputDeviceIdentifier = (InputDeviceIdentifier) intent2.getParcelableExtra("input_device_identifier");
            this.mIntentWaitingForResult = null;
            showKeyboardLayoutDialog(inputDeviceIdentifier);
        }
    }

    private static String getLayoutLabel(InputDevice inputDevice, Context context, InputManager inputManager) {
        String currentKeyboardLayoutForInputDevice = inputManager.getCurrentKeyboardLayoutForInputDevice(inputDevice.getIdentifier());
        if (currentKeyboardLayoutForInputDevice == null) {
            return context.getString(R.string.keyboard_layout_default_label);
        }
        KeyboardLayout keyboardLayout = inputManager.getKeyboardLayout(currentKeyboardLayoutForInputDevice);
        if (keyboardLayout == null) {
            return context.getString(R.string.keyboard_layout_default_label);
        }
        return TextUtils.emptyIfNull(keyboardLayout.getLabel());
    }

    static List<HardKeyboardDeviceInfo> getHardKeyboards(Context context) {
        ArrayList arrayList = new ArrayList();
        InputManager inputManager = (InputManager) context.getSystemService(InputManager.class);
        if (inputManager == null) {
            return new ArrayList();
        }
        for (int i : InputDevice.getDeviceIds()) {
            InputDevice device = InputDevice.getDevice(i);
            if (device != null && !device.isVirtual() && device.isFullKeyboard()) {
                arrayList.add(new HardKeyboardDeviceInfo(device.getName(), device.getIdentifier(), getLayoutLabel(device, context, inputManager)));
            }
        }
        final Collator collator = Collator.getInstance();
        arrayList.sort(new Comparator() { // from class: com.android.car.developeroptions.inputmethod.-$$Lambda$PhysicalKeyboardFragment$20-wJ3ZhEmmqIRyzW0xmaEH6dzo
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return PhysicalKeyboardFragment.lambda$getHardKeyboards$4(collator, (PhysicalKeyboardFragment.HardKeyboardDeviceInfo) obj, (PhysicalKeyboardFragment.HardKeyboardDeviceInfo) obj2);
            }
        });
        return arrayList;
    }

    static /* synthetic */ int lambda$getHardKeyboards$4(Collator collator, HardKeyboardDeviceInfo hardKeyboardDeviceInfo, HardKeyboardDeviceInfo hardKeyboardDeviceInfo2) {
        int iCompare = collator.compare(hardKeyboardDeviceInfo.mDeviceName, hardKeyboardDeviceInfo2.mDeviceName);
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompareTo = hardKeyboardDeviceInfo.mDeviceIdentifier.getDescriptor().compareTo(hardKeyboardDeviceInfo2.mDeviceIdentifier.getDescriptor());
        return iCompareTo != 0 ? iCompareTo : collator.compare(hardKeyboardDeviceInfo.mLayoutLabel, hardKeyboardDeviceInfo2.mLayoutLabel);
    }

    public static final class HardKeyboardDeviceInfo {
        public final InputDeviceIdentifier mDeviceIdentifier;
        public final String mDeviceName;
        public final String mLayoutLabel;

        public HardKeyboardDeviceInfo(String str, InputDeviceIdentifier inputDeviceIdentifier, String str2) {
            this.mDeviceName = TextUtils.emptyIfNull(str);
            this.mDeviceIdentifier = inputDeviceIdentifier;
            this.mLayoutLabel = str2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !(obj instanceof HardKeyboardDeviceInfo)) {
                return false;
            }
            HardKeyboardDeviceInfo hardKeyboardDeviceInfo = (HardKeyboardDeviceInfo) obj;
            return TextUtils.equals(this.mDeviceName, hardKeyboardDeviceInfo.mDeviceName) && Objects.equals(this.mDeviceIdentifier, hardKeyboardDeviceInfo.mDeviceIdentifier) && TextUtils.equals(this.mLayoutLabel, hardKeyboardDeviceInfo.mLayoutLabel);
        }
    }
}
