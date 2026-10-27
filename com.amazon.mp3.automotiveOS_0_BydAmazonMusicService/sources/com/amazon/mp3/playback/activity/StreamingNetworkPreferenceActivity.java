package com.amazon.mp3.playback.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingNetworkPreferenceActivity extends BaseActivity {
    private static final String LOGTAG = "StreamingNetworkPreferenceActivity";
    CheckedTextView mCheckAll;
    CheckedTextView mCheckWifiOnly;
    View.OnClickListener mNetworkSelectedListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str;
            StreamingNetworkPreferenceActivity streamingNetworkPreferenceActivity = StreamingNetworkPreferenceActivity.this;
            if (view == streamingNetworkPreferenceActivity.mCheckAll) {
                str = "ALL_NETWORKS";
            } else {
                str = view == streamingNetworkPreferenceActivity.mCheckWifiOnly ? "WIFI_ONLY" : null;
            }
            SettingsUtil.setStreamingNetworkPreference(streamingNetworkPreferenceActivity, str, streamingNetworkPreferenceActivity.mType);
            SettingsUtil.sendStreamingNetworkPreferenceMetrics(str, StreamingNetworkPreferenceActivity.this.mType);
            Log.debug(StreamingNetworkPreferenceActivity.LOGTAG, "Streaming network preference set to: %s", str);
            StreamingNetworkPreferenceActivity.this.finish();
        }
    };
    private Type mType;

    public enum Type {
        STREAMING_NETWORK_PREFERENCE_TYPE_AUDIO,
        STREAMING_NETWORK_PREFERENCE_TYPE_VIDEO
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_settings_streaming_network_prefs);
        initialize();
    }

    public void initialize() {
        this.mType = (Type) getIntent().getExtras().getSerializable("STREAMING_NETWORK_PREFERENCE_TYPE");
        this.mCheckAll = (CheckedTextView) findViewById(R.id.StreamingNetworkPrefAll);
        this.mCheckWifiOnly = (CheckedTextView) findViewById(R.id.StreamingNetworkPrefWifiOnly);
        this.mCheckAll.setOnClickListener(this.mNetworkSelectedListener);
        this.mCheckWifiOnly.setOnClickListener(this.mNetworkSelectedListener);
        TextView textView = (TextView) findViewById(R.id.StreamingNetworkPrefTitle);
        TextView textView2 = (TextView) findViewById(R.id.StreamingNetworkPrefHeader);
        String streamingNetworkPreference = SettingsUtil.getStreamingNetworkPreference(this, this.mType);
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$playback$activity$StreamingNetworkPreferenceActivity$Type[this.mType.ordinal()];
        if (i == 1) {
            textView.setText(R.string.dmusic_setting_audio_streaming_network_title);
            textView2.setText(R.string.dmusic_setting_audio_streaming_network_item_header);
        } else if (i == 2) {
            textView.setText(R.string.dmusic_setting_video_streaming_network_title);
            textView2.setText(R.string.dmusic_setting_video_streaming_network_item_header);
        } else {
            Log.error(LOGTAG, "Wrong type passed to StreamingNetworkPreferenceActivity! type: " + this.mType);
            return;
        }
        if ("ALL_NETWORKS".equals(streamingNetworkPreference) || "WIFI_OR_3G4G".equals(streamingNetworkPreference)) {
            this.mCheckAll.setChecked(true);
        } else {
            if ("WIFI_ONLY".equals(streamingNetworkPreference)) {
                this.mCheckWifiOnly.setChecked(true);
                return;
            }
            throw new IllegalArgumentException("Unknown streaming network pref value");
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playback$activity$StreamingNetworkPreferenceActivity$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$com$amazon$mp3$playback$activity$StreamingNetworkPreferenceActivity$Type = iArr;
            try {
                iArr[Type.STREAMING_NETWORK_PREFERENCE_TYPE_AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$activity$StreamingNetworkPreferenceActivity$Type[Type.STREAMING_NETWORK_PREFERENCE_TYPE_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
