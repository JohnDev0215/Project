package com.amazon.mp3.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DownloadPreferenceModel;
import com.amazon.mp3.activity.StreamingPreferenceModel;
import com.amazon.mp3.activity.TwoLinesListPreferenceModel;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.fragment.view.KatanaPreferencesBinding;
import com.amazon.mp3.fragment.viewmodel.BaseViewModel;
import com.amazon.mp3.fragment.viewmodel.KatanaDownloadPreferencesViewModel;
import com.amazon.mp3.fragment.viewmodel.KatanaStreamingPreferencesViewModel;
import com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentKatanaPreferences.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0002R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/fragment/FragmentKatanaPreferences;", "Lcom/amazon/mp3/fragment/BaseFragment;", "()V", "mPurpose", "", "Ljava/lang/Integer;", "mViewBinding", "Lcom/amazon/mp3/fragment/view/KatanaPreferencesBinding;", "mViewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "buildOTAStreamingQualityPreferencesList", "", "Lcom/amazon/mp3/activity/TwoLinesListPreferenceModel;", "buildWifiStreamingQualityPreferencesList", "createDownloadQualityPreferencesList", "initViewModelCallbacks", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setViewModel", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FragmentKatanaPreferences extends BaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String PURPOSE = "purpose";
    private Integer mPurpose;
    private KatanaPreferencesBinding mViewBinding = new KatanaPreferencesBinding();
    private ViewModelCallbacks mViewModelCallbacks;

    /* JADX INFO: compiled from: FragmentKatanaPreferences.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/fragment/FragmentKatanaPreferences$Companion;", "", "()V", "PURPOSE", "", "newInstance", "Lcom/amazon/mp3/fragment/FragmentKatanaPreferences;", "purpose", "Lcom/amazon/mp3/fragment/KatanaPreferencesPurpose;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FragmentKatanaPreferences newInstance(KatanaPreferencesPurpose purpose) {
            Intrinsics.checkNotNullParameter(purpose, "purpose");
            FragmentKatanaPreferences fragmentKatanaPreferences = new FragmentKatanaPreferences();
            Bundle bundle = new Bundle();
            bundle.putInt(FragmentKatanaPreferences.PURPOSE, purpose.ordinal());
            fragmentKatanaPreferences.setArguments(bundle);
            return fragmentKatanaPreferences;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_katana_settings_preferences, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…rences, container, false)");
        this.mViewBinding.setView(viewInflate);
        initViewModelCallbacks();
        Bundle arguments = getArguments();
        if (arguments != null) {
            Integer numValueOf = Integer.valueOf(arguments.getInt(PURPOSE, Integer.MIN_VALUE));
            this.mPurpose = numValueOf;
            if (numValueOf == null || numValueOf.intValue() != Integer.MIN_VALUE) {
                setViewModel();
            }
        }
        return viewInflate;
    }

    private final void setViewModel() {
        Context context;
        Integer num = this.mPurpose;
        int iOrdinal = KatanaPreferencesPurpose.STREAMING_PREFERENCES.ordinal();
        if (num != null && num.intValue() == iOrdinal) {
            Context context2 = getContext();
            if (context2 == null) {
                return;
            }
            List<TwoLinesListPreferenceModel> listBuildWifiStreamingQualityPreferencesList = buildWifiStreamingQualityPreferencesList();
            List<TwoLinesListPreferenceModel> listBuildOTAStreamingQualityPreferencesList = buildOTAStreamingQualityPreferencesList();
            String string = context2.getString(R.string.dmusic_streaming_bitrate_preferences);
            Intrinsics.checkNotNullExpressionValue(string, "it.getString(R.string.dm…ming_bitrate_preferences)");
            String string2 = context2.getString(R.string.dmusic_button_save);
            Intrinsics.checkNotNullExpressionValue(string2, "it.getString(dmusic_button_save)");
            String string3 = context2.getString(R.string.dmusic_button_cancel);
            Intrinsics.checkNotNullExpressionValue(string3, "it.getString(dmusic_button_cancel)");
            new KatanaStreamingPreferencesViewModel(context2, this.mViewBinding, new StreamingPreferenceModel(string, listBuildWifiStreamingQualityPreferencesList, listBuildOTAStreamingQualityPreferencesList, null, string2, string3), this.mViewModelCallbacks);
            MetricsLogger.qualitySettingsDialogViewed(ContentName.STREAMING_SETTINGS);
            return;
        }
        int iOrdinal2 = KatanaPreferencesPurpose.DOWNLOAD_PREFERENCES.ordinal();
        if (num == null || num.intValue() != iOrdinal2 || (context = getContext()) == null) {
            return;
        }
        List<TwoLinesListPreferenceModel> listCreateDownloadQualityPreferencesList = createDownloadQualityPreferencesList();
        String string4 = context.getString(R.string.dmusic_download_quality_preference_title);
        Intrinsics.checkNotNullExpressionValue(string4, "it.getString(dmusic_down…quality_preference_title)");
        String string5 = context.getString(R.string.dmusic_button_save);
        Intrinsics.checkNotNullExpressionValue(string5, "it.getString(dmusic_button_save)");
        String string6 = context.getString(R.string.dmusic_button_cancel);
        Intrinsics.checkNotNullExpressionValue(string6, "it.getString(dmusic_button_cancel)");
        new KatanaDownloadPreferencesViewModel(context, this.mViewBinding, new DownloadPreferenceModel(string4, listCreateDownloadQualityPreferencesList, null, string5, string6), this.mViewModelCallbacks);
        MetricsLogger.qualitySettingsDialogViewed(ContentName.DOWNLOAD_SETTINGS);
    }

    private final List<TwoLinesListPreferenceModel> createDownloadQualityPreferencesList() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        if (context != null) {
            String downloadQualityPreference = SettingsUtil.getDownloadQualityPreference(context);
            DownloadQualityPreferences[] downloadQualityPreferencesArrValues = DownloadQualityPreferences.values();
            int i = 0;
            int length = downloadQualityPreferencesArrValues.length;
            while (i < length) {
                DownloadQualityPreferences downloadQualityPreferences = downloadQualityPreferencesArrValues[i];
                i++;
                if (!AmazonApplication.getCapabilities().isKatanaSupported()) {
                    DownloadQualityPreferences downloadQualityPreferences2 = DownloadQualityPreferences.AUTO;
                    if (Intrinsics.areEqual(downloadQualityPreference, downloadQualityPreferences2.getEntryValue())) {
                        downloadQualityPreference = DownloadQualityPreferences.HIGH.getEntryValue();
                    }
                    if (downloadQualityPreferences == downloadQualityPreferences2) {
                    }
                }
                if (!UserSubscriptionUtil.getUserSubscription().isKatana()) {
                    DownloadQualityPreferences downloadQualityPreferences3 = DownloadQualityPreferences.HD;
                    if (Intrinsics.areEqual(downloadQualityPreference, downloadQualityPreferences3.getEntryValue())) {
                        downloadQualityPreference = DownloadQualityPreferences.AUTO.getEntryValue();
                    }
                    if (downloadQualityPreferences == downloadQualityPreferences3) {
                    }
                }
                String string = downloadQualityPreferences.getEntryResourceDescription() == -1 ? null : context.getString(downloadQualityPreferences.getEntryResourceDescription());
                String string2 = context.getString(downloadQualityPreferences.getEntryResourceId());
                Intrinsics.checkNotNullExpressionValue(string2, "it.getString(item.getEntryResourceId())");
                arrayList.add(new TwoLinesListPreferenceModel(string2, downloadQualityPreferences.getEntryValue(), string, Intrinsics.areEqual(downloadQualityPreferences.getEntryValue(), downloadQualityPreference)));
            }
        }
        return arrayList;
    }

    private final List<TwoLinesListPreferenceModel> buildWifiStreamingQualityPreferencesList() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        if (context != null) {
            String multiBitrateStreamingPreferenceForWifi = SettingsUtil.getMultiBitrateStreamingPreferenceForWifi(getContext());
            StreamingBitrate[] streamingBitrateArrValues = StreamingBitrate.values();
            int i = 0;
            int length = streamingBitrateArrValues.length;
            while (i < length) {
                StreamingBitrate streamingBitrate = streamingBitrateArrValues[i];
                i++;
                if (!AmazonApplication.getCapabilities().isKatanaSupported()) {
                    StreamingBitrate streamingBitrate2 = StreamingBitrate.KATANA;
                    if (Intrinsics.areEqual(multiBitrateStreamingPreferenceForWifi, streamingBitrate2.getEntryValue())) {
                        multiBitrateStreamingPreferenceForWifi = StreamingBitrate.AUTO.getEntryValue();
                    }
                    if (streamingBitrate == streamingBitrate2) {
                    }
                }
                if (streamingBitrate == StreamingBitrate.KATANA && !AmazonApplication.getCapabilities().isKatanaSupported()) {
                    if (Intrinsics.areEqual(streamingBitrate.getEntryValue(), multiBitrateStreamingPreferenceForWifi)) {
                        multiBitrateStreamingPreferenceForWifi = StreamingBitrate.AUTO.getEntryValue();
                    }
                } else {
                    String string = streamingBitrate.getEntryResourcedescription() == -1 ? null : context.getString(streamingBitrate.getEntryResourcedescription());
                    if (streamingBitrate != StreamingBitrate.LOW) {
                        String string2 = context.getString(streamingBitrate.getEntryResourceId());
                        Intrinsics.checkNotNullExpressionValue(string2, "it.getString(item.entryResourceId)");
                        arrayList.add(new TwoLinesListPreferenceModel(string2, streamingBitrate.getEntryValue(), string, Intrinsics.areEqual(streamingBitrate.getEntryValue(), multiBitrateStreamingPreferenceForWifi)));
                    }
                }
            }
        }
        return arrayList;
    }

    private final List<TwoLinesListPreferenceModel> buildOTAStreamingQualityPreferencesList() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        if (context != null) {
            String multiBitrateStreamingPreferenceForOTA = SettingsUtil.getMultiBitrateStreamingPreferenceForOTA(getContext());
            StreamingBitrate[] streamingBitrateArrValues = StreamingBitrate.values();
            int i = 0;
            int length = streamingBitrateArrValues.length;
            while (i < length) {
                StreamingBitrate streamingBitrate = streamingBitrateArrValues[i];
                i++;
                if (!AmazonApplication.getCapabilities().isKatanaSupported()) {
                    StreamingBitrate streamingBitrate2 = StreamingBitrate.KATANA;
                    if (Intrinsics.areEqual(multiBitrateStreamingPreferenceForOTA, streamingBitrate2.getEntryValue())) {
                        multiBitrateStreamingPreferenceForOTA = StreamingBitrate.AUTO.getEntryValue();
                    }
                    if (streamingBitrate == streamingBitrate2) {
                    }
                }
                if (streamingBitrate == StreamingBitrate.KATANA && !AmazonApplication.getCapabilities().isKatanaSupported()) {
                    if (Intrinsics.areEqual(streamingBitrate.getEntryValue(), multiBitrateStreamingPreferenceForOTA)) {
                        multiBitrateStreamingPreferenceForOTA = StreamingBitrate.AUTO.getEntryValue();
                    }
                } else {
                    String string = streamingBitrate.getEntryResourcedescription() == -1 ? null : context.getString(streamingBitrate.getEntryResourcedescription());
                    String string2 = context.getString(streamingBitrate.getEntryResourceId());
                    Intrinsics.checkNotNullExpressionValue(string2, "it.getString(item.entryResourceId)");
                    arrayList.add(new TwoLinesListPreferenceModel(string2, streamingBitrate.getEntryValue(), string, Intrinsics.areEqual(streamingBitrate.getEntryValue(), multiBitrateStreamingPreferenceForOTA)));
                }
            }
        }
        return arrayList;
    }

    private final void initViewModelCallbacks() {
        this.mViewModelCallbacks = new ViewModelCallbacks() { // from class: com.amazon.mp3.fragment.FragmentKatanaPreferences.initViewModelCallbacks.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                FragmentActivity activity;
                FragmentActivity activity2;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (callerId == KatanaPreferencesPurpose.STREAMING_PREFERENCES.ordinal()) {
                    if (actionId != 1 || (activity2 = FragmentKatanaPreferences.this.getActivity()) == null) {
                        return;
                    }
                    activity2.finish();
                    return;
                }
                if (callerId == KatanaPreferencesPurpose.DOWNLOAD_PREFERENCES.ordinal() && actionId == 1 && (activity = FragmentKatanaPreferences.this.getActivity()) != null) {
                    activity.finish();
                }
            }
        };
    }
}
