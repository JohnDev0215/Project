package com.amazon.music.destination.parser;

import com.amazon.music.destination.SettingsDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class SettingsDeeplinkParser implements DeeplinkParser<SettingsDestination> {
    private static final String SETTINGS_SEGMENT = "settings";

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    @Override // com.amazon.music.destination.core.DeeplinkParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.music.destination.SettingsDestination parse(android.net.Uri r3) {
        /*
            r2 = this;
            java.lang.String r0 = "settings"
            java.lang.String r3 = com.amazon.music.destination.parser.ParserUtil.findSegmentAfter(r3, r0)
            if (r3 == 0) goto L16
            com.amazon.music.destination.SettingsDestination$SettingTarget r0 = com.amazon.music.destination.SettingsDestination.SettingTarget.STREAMING_AUDIO_QUALITY
            java.lang.String r1 = r0.getValue()
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L16
            goto L27
        L16:
            if (r3 == 0) goto L25
            com.amazon.music.destination.SettingsDestination$SettingTarget r0 = com.amazon.music.destination.SettingsDestination.SettingTarget.NOTIFICATIONS
            java.lang.String r1 = r0.getValue()
            boolean r3 = r3.contains(r1)
            if (r3 == 0) goto L25
            goto L27
        L25:
            com.amazon.music.destination.SettingsDestination$SettingTarget r0 = com.amazon.music.destination.SettingsDestination.SettingTarget.DOWNLOAD_AUDIO_QUALITY
        L27:
            com.amazon.music.destination.SettingsDestination r3 = new com.amazon.music.destination.SettingsDestination
            r1 = 0
            r3.<init>(r0, r1, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.destination.parser.SettingsDeeplinkParser.parse(android.net.Uri):com.amazon.music.destination.SettingsDestination");
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return String.format("/%s/(%s|%s|%s)(/.*)?", SETTINGS_SEGMENT, SettingsDestination.STREAMING_AUDIO_QUALITY, SettingsDestination.DOWNLOAD_AUDIO_QUALITY, SettingsDestination.NOTIFICATIONS);
    }
}
