package com.amazon.device.crashmanager.source;

import android.os.DropBoxManager;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.Artifact;
import com.amazon.device.crashmanager.WhitelistAppArtifact;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class WhitelistAppArtifactSource extends DropBoxArtifactSource {
    private static Map<String, Pattern> mWhitelistedAppPatternPerTag = new HashMap();

    public WhitelistAppArtifactSource(DropBoxManager dropBoxManager, SettingsStore settingsStore, Map<String, Pattern> map) {
        super(dropBoxManager, settingsStore, map.keySet());
        mWhitelistedAppPatternPerTag = map;
    }

    @Override // com.amazon.device.crashmanager.source.DropBoxArtifactSource, com.amazon.device.crashmanager.source.ArtifactSource
    public Artifact getNextArtifact(MetricEvent metricEvent, String str) {
        Artifact nextArtifact = super.getNextArtifact(metricEvent, str);
        if (nextArtifact != null) {
            return new WhitelistAppArtifact(nextArtifact.getTag(), nextArtifact.getInputStream(), nextArtifact.getCreationTimeUTCMillis());
        }
        return null;
    }

    public static Pattern getWhiteListedAppPatternsForTag(String str) {
        return mWhitelistedAppPatternPerTag.get(str);
    }
}
