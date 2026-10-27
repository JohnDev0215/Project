package com.neusoft.na.dltdroid;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Toast;
import androidx.core.content.res.ResourcesCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.rdrei.android.dirchooser.DirectoryChooserActivity;

/* JADX INFO: compiled from: GeneralPreferencesFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\"\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0017J\u001c\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\nH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0002J\u0012\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/neusoft/na/dltdroid/GeneralPreferencesFragment;", "Landroidx/preference/PreferenceFragmentCompat;", "()V", "mConfig", "Lcom/neusoft/na/dltdroid/Configuration;", "mDltLogPathPref", "Landroidx/preference/Preference;", "sBindPreferenceSummaryToValueListener", "Landroidx/preference/Preference$OnPreferenceChangeListener;", "bindPreferenceSummaryToValue", "", "preference", "getDrawable", "Landroid/graphics/drawable/Drawable;", "id", "", "onActivityResult", "requestCode", "resultCode", "resultData", "Landroid/content/Intent;", "onCreatePreferences", "p0", "Landroid/os/Bundle;", "p1", "", "onPause", "refreshDltLogPathPreference", "setDltLogPath", "dirPath", "Landroid/net/Uri;", "Companion", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class GeneralPreferencesFragment extends PreferenceFragmentCompat {
    private Configuration mConfig;
    private Preference mDltLogPathPref;
    private static final int OPEN_DOCUMENT_TREE = 40;
    private static final int OPEN_DIR_CHOOSER = 41;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() { // from class: com.neusoft.na.dltdroid.GeneralPreferencesFragment$$ExternalSyntheticLambda0
        @Override // androidx.preference.Preference.OnPreferenceChangeListener
        public final boolean onPreferenceChange(Preference preference, Object obj) {
            return GeneralPreferencesFragment.sBindPreferenceSummaryToValueListener$lambda$0(preference, obj);
        }
    };

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View viewFindViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null || (viewFindViewById = view2.findViewById(i)) == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean sBindPreferenceSummaryToValueListener$lambda$0(Preference preference, Object obj) {
        preference.setSummary(obj.toString());
        return true;
    }

    private final void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this.sBindPreferenceSummaryToValueListener);
        this.sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(), ""));
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle p0, String p1) {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        this.mConfig = new Configuration(activity);
        addPreferencesFromResource(R.xml.pref_general);
        Preference preferenceFindPreference = findPreference(getString(R.string.pref_key_logger_port));
        Intrinsics.checkNotNullExpressionValue(preferenceFindPreference, "findPreference(getString…ng.pref_key_logger_port))");
        bindPreferenceSummaryToValue(preferenceFindPreference);
        Preference preferenceFindPreference2 = findPreference(getString(R.string.pref_key_logger_ws_port));
        Intrinsics.checkNotNullExpressionValue(preferenceFindPreference2, "findPreference(getString…pref_key_logger_ws_port))");
        bindPreferenceSummaryToValue(preferenceFindPreference2);
        Preference preferenceFindPreference3 = findPreference(getString(R.string.pref_key_logsink_file_maxFileLen));
        Intrinsics.checkNotNullExpressionValue(preferenceFindPreference3, "findPreference(getString…logsink_file_maxFileLen))");
        bindPreferenceSummaryToValue(preferenceFindPreference3);
        Preference preferenceFindPreference4 = findPreference(getString(R.string.pref_key_logsink_file_ecu));
        Intrinsics.checkNotNullExpressionValue(preferenceFindPreference4, "findPreference(getString…ef_key_logsink_file_ecu))");
        bindPreferenceSummaryToValue(preferenceFindPreference4);
        Preference preferenceFindPreference5 = findPreference(getString(R.string.pref_key_logsink_tcpip_port));
        Intrinsics.checkNotNullExpressionValue(preferenceFindPreference5, "findPreference(getString…_key_logsink_tcpip_port))");
        bindPreferenceSummaryToValue(preferenceFindPreference5);
        Preference preferenceFindPreference6 = findPreference(getString(R.string.pref_key_ip));
        if (preferenceFindPreference6 != null) {
            String ipAsString = UtilKt.getIpAsString();
            if (ipAsString == null) {
                ipAsString = getString(R.string.ip_label_undefined);
            }
            preferenceFindPreference6.setSummary(ipAsString);
        }
        Preference preferenceFindPreference7 = findPreference(getString(R.string.pref_key_logsink_file_path));
        Intrinsics.checkNotNullExpressionValue(preferenceFindPreference7, "findPreference(getString…f_key_logsink_file_path))");
        this.mDltLogPathPref = preferenceFindPreference7;
        refreshDltLogPathPreference();
        Preference preference = this.mDltLogPathPref;
        if (preference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDltLogPathPref");
            preference = null;
        }
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.neusoft.na.dltdroid.GeneralPreferencesFragment$$ExternalSyntheticLambda1
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return GeneralPreferencesFragment.onCreatePreferences$lambda$4(this.f$0, preference2);
            }
        });
        findPreference(getString(R.string.pref_key_logsink_file_enable)).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.neusoft.na.dltdroid.GeneralPreferencesFragment$$ExternalSyntheticLambda2
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference2, Object obj) {
                return GeneralPreferencesFragment.onCreatePreferences$lambda$7(this.f$0, preference2, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreatePreferences$lambda$4(GeneralPreferencesFragment this$0, Preference preference) {
        boolean z;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            intent.addFlags(67);
            this$0.startActivityForResult(intent, OPEN_DOCUMENT_TREE);
            z = true;
        } catch (ActivityNotFoundException unused) {
            z = false;
        }
        if (!z) {
            Intent intent2 = new Intent(this$0.getActivity(), (Class<?>) DirectoryChooserActivity.class);
            intent2.putExtra(DirectoryChooserActivity.EXTRA_NEW_DIR_NAME, "dlt");
            this$0.startActivityForResult(intent2, OPEN_DIR_CHOOSER);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreatePreferences$lambda$7(final GeneralPreferencesFragment this$0, Preference preference, Object obj) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return true;
        }
        new Handler(activity.getMainLooper()).post(new Runnable() { // from class: com.neusoft.na.dltdroid.GeneralPreferencesFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                GeneralPreferencesFragment.onCreatePreferences$lambda$7$lambda$6$lambda$5(this.f$0);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreatePreferences$lambda$7$lambda$6$lambda$5(GeneralPreferencesFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.refreshDltLogPathPreference();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        Toast.makeText(getActivity(), "Changes will be applied when the logger is (re)started.", 0).show();
        super.onPause();
    }

    private final void setDltLogPath(Uri dirPath) {
        Configuration configuration = null;
        if (dirPath != null) {
            Configuration configuration2 = this.mConfig;
            if (configuration2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mConfig");
            } else {
                configuration = configuration2;
            }
            configuration.setLogSinkFilePath(dirPath.toString());
        } else {
            Configuration configuration3 = this.mConfig;
            if (configuration3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mConfig");
                configuration3 = null;
            }
            configuration3.setLogSinkFilePath(null);
        }
        refreshDltLogPathPreference();
    }

    private final Drawable getDrawable(int id) {
        Resources resources;
        FragmentActivity activity = getActivity();
        if (activity == null || (resources = activity.getResources()) == null) {
            return null;
        }
        FragmentActivity activity2 = getActivity();
        return ResourcesCompat.getDrawable(resources, id, activity2 != null ? activity2.getTheme() : null);
    }

    private final void refreshDltLogPathPreference() {
        Configuration configuration = this.mConfig;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mConfig");
            configuration = null;
        }
        String logSinkFilePath = configuration.getLogSinkFilePath();
        if (logSinkFilePath != null) {
            Uri dltLogUri = Uri.parse(logSinkFilePath);
            FragmentActivity activity = getActivity();
            if (activity != null) {
                FragmentActivity fragmentActivity = activity;
                Intrinsics.checkNotNullExpressionValue(dltLogUri, "dltLogUri");
                if (UtilKt.isPathAccessible(fragmentActivity, dltLogUri) && UtilKt.checkFilePermissions(fragmentActivity, dltLogUri)) {
                    Long freeSpace = UtilKt.getFreeSpace(fragmentActivity, dltLogUri);
                    if (freeSpace != null) {
                        logSinkFilePath = logSinkFilePath + " (" + Formatter.formatFileSize(fragmentActivity, freeSpace.longValue()) + " free)";
                    }
                    Preference preference = this.mDltLogPathPref;
                    if (preference == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mDltLogPathPref");
                        preference = null;
                    }
                    preference.setSummary(logSinkFilePath);
                    Preference preference2 = this.mDltLogPathPref;
                    if (preference2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mDltLogPathPref");
                        preference2 = null;
                    }
                    preference2.setIcon((Drawable) null);
                    return;
                }
            }
            Preference preference3 = this.mDltLogPathPref;
            if (preference3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDltLogPathPref");
                preference3 = null;
            }
            preference3.setSummary(logSinkFilePath + " (INACCESSIBLE)");
            Preference preference4 = this.mDltLogPathPref;
            if (preference4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDltLogPathPref");
                preference4 = null;
            }
            Preference preference5 = this.mDltLogPathPref;
            if (preference5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDltLogPathPref");
                preference5 = null;
            }
            preference4.setIcon(preference5.isEnabled() ? getDrawable(R.drawable.ic_settings_invalid_red) : null);
            return;
        }
        Preference preference6 = this.mDltLogPathPref;
        if (preference6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDltLogPathPref");
            preference6 = null;
        }
        preference6.setSummary("Please select a log path!");
        Preference preference7 = this.mDltLogPathPref;
        if (preference7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDltLogPathPref");
            preference7 = null;
        }
        Preference preference8 = this.mDltLogPathPref;
        if (preference8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDltLogPathPref");
            preference8 = null;
        }
        preference7.setIcon(preference8.isEnabled() ? getDrawable(R.drawable.ic_settings_invalid_red) : null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        ContentResolver contentResolver;
        if (requestCode != OPEN_DOCUMENT_TREE) {
            if (requestCode == OPEN_DIR_CHOOSER && resultCode == 1) {
                String stringExtra = resultData != null ? resultData.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR) : null;
                if (stringExtra != null) {
                    Uri uri = DocumentFile.fromFile(new File(stringExtra)).getUri();
                    Intrinsics.checkNotNullExpressionValue(uri, "fromFile(File(path)).uri");
                    FragmentActivity activity = getActivity();
                    if (activity != null) {
                        FragmentActivity activity2 = getActivity();
                        activity.grantUriPermission(activity2 != null ? activity2.getPackageName() : null, uri, 67);
                    }
                    setDltLogPath(uri);
                    return;
                }
                return;
            }
            return;
        }
        if (resultCode != -1 || resultData == null) {
            return;
        }
        Uri data = resultData.getData();
        FragmentActivity activity3 = getActivity();
        if (activity3 != null) {
            FragmentActivity activity4 = getActivity();
            activity3.grantUriPermission(activity4 != null ? activity4.getPackageName() : null, data, 67);
        }
        FragmentActivity activity5 = getActivity();
        if (activity5 != null && (contentResolver = activity5.getContentResolver()) != null) {
            Intrinsics.checkNotNull(data);
            contentResolver.takePersistableUriPermission(data, 3);
        }
        setDltLogPath(data);
    }
}
