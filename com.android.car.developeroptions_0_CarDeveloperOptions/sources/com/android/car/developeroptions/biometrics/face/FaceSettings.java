package com.android.car.developeroptions.biometrics.face;

import android.content.Context;
import android.content.Intent;
import android.hardware.face.FaceManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.biometrics.face.FaceSettingsRemoveButtonPreferenceController;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.password.ChooseLockSettingsHelper;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FaceSettings extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.biometrics.face.FaceSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.security_settings_face;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private FaceSettingsAttentionPreferenceController mAttentionController;
    private List<AbstractPreferenceController> mControllers;
    private FaceManager mFaceManager;
    private final FaceSettingsRemoveButtonPreferenceController.Listener mRemovalListener = new FaceSettingsRemoveButtonPreferenceController.Listener() { // from class: com.android.car.developeroptions.biometrics.face.-$$Lambda$FaceSettings$pTML7EfAUAIOaxAUzG_yfIg917A
        @Override // com.android.car.developeroptions.biometrics.face.FaceSettingsRemoveButtonPreferenceController.Listener
        public final void onRemoved() {
            this.f$0.lambda$new$0$FaceSettings();
        }
    };
    private FaceSettingsRemoveButtonPreferenceController mRemoveController;
    private byte[] mToken;
    private int mUserId;
    private UserManager mUserManager;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "FaceSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1511;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.security_settings_face;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$FaceSettings() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public static boolean isAvailable(Context context) {
        FaceManager faceManagerOrNull = Utils.getFaceManagerOrNull(context);
        return faceManagerOrNull != null && faceManagerOrNull.isHardwareDetected();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putByteArray("key_token", this.mToken);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUserManager = (UserManager) getPrefContext().getSystemService(UserManager.class);
        this.mFaceManager = (FaceManager) getPrefContext().getSystemService(FaceManager.class);
        this.mUserId = getActivity().getIntent().getIntExtra("android.intent.extra.USER_ID", UserHandle.myUserId());
        for (AbstractPreferenceController abstractPreferenceController : this.mControllers) {
            if (abstractPreferenceController instanceof FaceSettingsPreferenceController) {
                ((FaceSettingsPreferenceController) abstractPreferenceController).setUserId(this.mUserId);
            }
        }
        this.mRemoveController.setUserId(this.mUserId);
        if (this.mUserManager.isManagedProfile(this.mUserId)) {
            removePreference("security_settings_face_keyguard");
        }
        if (bundle != null) {
            this.mToken = bundle.getByteArray("key_token");
        }
        if (this.mToken == null) {
            if (new ChooseLockSettingsHelper(getActivity(), this).launchConfirmationActivity(4, getString(R.string.security_settings_face_preference_title), (CharSequence) null, (CharSequence) null, this.mFaceManager.generateChallenge(), this.mUserId)) {
                return;
            }
            Log.e("FaceSettings", "Password not set");
            finish();
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        byte[] bArr = this.mToken;
        if (bArr != null) {
            this.mAttentionController.setToken(bArr);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 4 && (i2 == 1 || i2 == -1)) {
            this.mFaceManager.setActiveUser(this.mUserId);
            if (intent != null) {
                byte[] byteArrayExtra = intent.getByteArrayExtra("hw_auth_token");
                this.mToken = byteArrayExtra;
                if (byteArrayExtra != null) {
                    this.mAttentionController.setToken(byteArrayExtra);
                }
            }
        }
        if (this.mToken == null) {
            getActivity().finish();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        int iRevokeChallenge;
        super.onDestroy();
        if (!getActivity().isFinishing() || (iRevokeChallenge = this.mFaceManager.revokeChallenge()) >= 0) {
            return;
        }
        Log.w("FaceSettings", "revokeChallenge failed, result: " + iRevokeChallenge);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        if (!isAvailable(context)) {
            return null;
        }
        List<AbstractPreferenceController> listBuildPreferenceControllers = buildPreferenceControllers(context, getSettingsLifecycle());
        this.mControllers = listBuildPreferenceControllers;
        for (AbstractPreferenceController abstractPreferenceController : listBuildPreferenceControllers) {
            if (abstractPreferenceController instanceof FaceSettingsAttentionPreferenceController) {
                this.mAttentionController = (FaceSettingsAttentionPreferenceController) abstractPreferenceController;
            } else if (abstractPreferenceController instanceof FaceSettingsRemoveButtonPreferenceController) {
                FaceSettingsRemoveButtonPreferenceController faceSettingsRemoveButtonPreferenceController = (FaceSettingsRemoveButtonPreferenceController) abstractPreferenceController;
                this.mRemoveController = faceSettingsRemoveButtonPreferenceController;
                faceSettingsRemoveButtonPreferenceController.setListener(this.mRemovalListener);
                this.mRemoveController.setActivity((SettingsActivity) getActivity());
            }
        }
        return this.mControllers;
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FaceSettingsVideoPreferenceController(context));
        arrayList.add(new FaceSettingsKeyguardPreferenceController(context));
        arrayList.add(new FaceSettingsAppPreferenceController(context));
        arrayList.add(new FaceSettingsAttentionPreferenceController(context));
        arrayList.add(new FaceSettingsRemoveButtonPreferenceController(context));
        arrayList.add(new FaceSettingsFooterPreferenceController(context));
        arrayList.add(new FaceSettingsConfirmPreferenceController(context));
        return arrayList;
    }
}
