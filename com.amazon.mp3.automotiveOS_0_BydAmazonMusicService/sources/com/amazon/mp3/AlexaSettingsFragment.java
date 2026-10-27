package com.amazon.mp3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class AlexaSettingsFragment extends DialogFragment {
    static final List<String> SUPPORTED_DOMAIN = Collections.unmodifiableList(Arrays.asList("Weather.WeatherQueryIntent", "Traffic", "HomeAutomation", "Calendar", "TODO"));

    public static boolean shouldPromptSettings(@NonNull final SharedPreferences preferences, final String domain) {
        Validate.notNull(preferences);
        if (!SUPPORTED_DOMAIN.contains(domain)) {
            return false;
        }
        long currentTimeInSeconds = getCurrentTimeInSeconds();
        int i = preferences.getInt("LocationSettingPromptCount", 0);
        long j = currentTimeInSeconds - preferences.getLong("LocationSettingPromptTimestamp", 0L);
        return ((i < 5) && ((j > 86400L ? 1 : (j == 86400L ? 0 : -1)) > 0)) || ((j > 2629746L ? 1 : (j == 2629746L ? 0 : -1)) > 0);
    }

    public static void recordPromptSettings(final SharedPreferences preferences) {
        long currentTimeInSeconds = getCurrentTimeInSeconds();
        int i = preferences.getInt("LocationSettingPromptCount", 0) + 1;
        SharedPreferences.Editor editorEdit = preferences.edit();
        editorEdit.putInt("LocationSettingPromptCount", i);
        editorEdit.putLong("LocationSettingPromptTimestamp", currentTimeInSeconds);
        editorEdit.apply();
    }

    private static long getCurrentTimeInSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static AlexaSettingsFragment newInstance(@NonNull final String domain) {
        Validate.notNull(domain);
        Bundle bundle = new Bundle();
        bundle.putString("DomainKey", domain);
        AlexaSettingsFragment alexaSettingsFragment = new AlexaSettingsFragment();
        alexaSettingsFragment.setArguments(bundle);
        return alexaSettingsFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.alexa_settings_fragment, parent, false);
        if (getDomainFromArguments().toLowerCase().contains("weather")) {
            ((TextView) viewInflate.findViewById(R.id.settings_text)).setText(R.string.alexa_weather_settings_text);
        }
        viewInflate.findViewById(R.id.alexa_app_button).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.AlexaSettingsFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AlexaSettingsFragment.this.sendUiClickMetric("alexaLocationCompanionLink");
                AlexaSettingsFragment.this.startCompanionApp();
                AlexaSettingsFragment.this.dismiss();
            }
        });
        viewInflate.findViewById(R.id.dismiss_button).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.AlexaSettingsFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AlexaSettingsFragment.this.dismiss();
            }
        });
        sendUiPageViewMetric("alexaFirstTimeWeather");
        return viewInflate;
    }

    @NonNull
    private String getDomainFromArguments() {
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString("DomainKey") : null;
        return string != null ? string : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCompanionApp() {
        Intent intent;
        if (getContext().getPackageManager().getLaunchIntentForPackage("com.amazon.dee.app") == null) {
            intent = new Intent();
            intent.setData(Uri.parse("market://details?id=com.amazon.dee.app"));
        } else {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("http://alexa.amazon.com/?fragment=settings"));
            intent.setPackage("com.amazon.dee.app");
        }
        intent.addFlags(268435456);
        startActivity(intent);
    }

    private void sendUiPageViewMetric(final String identifier) {
        NavigationAppEvent.builder(identifier).publish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUiClickMetric(final String identifier) {
        UserInteractionAppEvent.builder(identifier).publish();
    }
}
