package com.android.car.developeroptions.notification;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.android.car.developeroptions.SettingsPreferenceFragment;

/* JADX INFO: loaded from: classes.dex */
public class SettingPref {
    protected final int mDefault;
    protected DropDownPreference mDropDown;
    private final String mKey;
    protected final String mSetting;
    protected TwoStatePreference mTwoState;
    protected final int mType;
    private final Uri mUri;
    private final int[] mValues;

    public boolean isApplicable(Context context) {
        return true;
    }

    public SettingPref(int i, String str, String str2, int i2, int... iArr) {
        this.mType = i;
        this.mKey = str;
        this.mSetting = str2;
        this.mDefault = i2;
        this.mValues = iArr;
        this.mUri = getUriFor(i, str2);
    }

    protected String getCaption(Resources resources, int i) {
        throw new UnsupportedOperationException();
    }

    public Preference init(SettingsPreferenceFragment settingsPreferenceFragment) {
        final FragmentActivity activity = settingsPreferenceFragment.getActivity();
        Preference preferenceFindPreference = settingsPreferenceFragment.getPreferenceScreen().findPreference(this.mKey);
        if (preferenceFindPreference != null && !isApplicable(activity)) {
            settingsPreferenceFragment.getPreferenceScreen().removePreference(preferenceFindPreference);
            preferenceFindPreference = null;
        }
        if (preferenceFindPreference instanceof TwoStatePreference) {
            this.mTwoState = (TwoStatePreference) preferenceFindPreference;
        } else if (preferenceFindPreference instanceof DropDownPreference) {
            this.mDropDown = (DropDownPreference) preferenceFindPreference;
            int[] iArr = this.mValues;
            CharSequence[] charSequenceArr = new CharSequence[iArr.length];
            CharSequence[] charSequenceArr2 = new CharSequence[iArr.length];
            for (int i = 0; i < this.mValues.length; i++) {
                charSequenceArr[i] = getCaption(activity.getResources(), this.mValues[i]);
                charSequenceArr2[i] = Integer.toString(this.mValues[i]);
            }
            this.mDropDown.setEntries(charSequenceArr);
            this.mDropDown.setEntryValues(charSequenceArr2);
        }
        update(activity);
        if (this.mTwoState != null) {
            preferenceFindPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.developeroptions.notification.SettingPref.1
                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public boolean onPreferenceChange(Preference preference, Object obj) {
                    SettingPref.this.setSetting(activity, ((Boolean) obj).booleanValue() ? 1 : 0);
                    return true;
                }
            });
            return this.mTwoState;
        }
        if (this.mDropDown == null) {
            return null;
        }
        preferenceFindPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.developeroptions.notification.SettingPref.2
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object obj) {
                return SettingPref.this.setSetting(activity, Integer.parseInt((String) obj));
            }
        });
        return this.mDropDown;
    }

    protected boolean setSetting(Context context, int i) {
        return putInt(this.mType, context.getContentResolver(), this.mSetting, i);
    }

    public Uri getUri() {
        return this.mUri;
    }

    public String getKey() {
        return this.mKey;
    }

    public void update(Context context) {
        int i = getInt(this.mType, context.getContentResolver(), this.mSetting, this.mDefault);
        TwoStatePreference twoStatePreference = this.mTwoState;
        if (twoStatePreference != null) {
            twoStatePreference.setChecked(i != 0);
            return;
        }
        DropDownPreference dropDownPreference = this.mDropDown;
        if (dropDownPreference != null) {
            dropDownPreference.setValue(Integer.toString(i));
        }
    }

    private static Uri getUriFor(int i, String str) {
        if (i == 1) {
            return Settings.Global.getUriFor(str);
        }
        if (i == 2) {
            return Settings.System.getUriFor(str);
        }
        if (i == 3) {
            return Settings.Secure.getUriFor(str);
        }
        throw new IllegalArgumentException();
    }

    protected static boolean putInt(int i, ContentResolver contentResolver, String str, int i2) {
        if (i == 1) {
            return Settings.Global.putInt(contentResolver, str, i2);
        }
        if (i == 2) {
            return Settings.System.putInt(contentResolver, str, i2);
        }
        if (i == 3) {
            return Settings.Secure.putInt(contentResolver, str, i2);
        }
        throw new IllegalArgumentException();
    }

    protected static int getInt(int i, ContentResolver contentResolver, String str, int i2) {
        if (i == 1) {
            return Settings.Global.getInt(contentResolver, str, i2);
        }
        if (i == 2) {
            return Settings.System.getInt(contentResolver, str, i2);
        }
        if (i == 3) {
            return Settings.Secure.getInt(contentResolver, str, i2);
        }
        throw new IllegalArgumentException();
    }
}
