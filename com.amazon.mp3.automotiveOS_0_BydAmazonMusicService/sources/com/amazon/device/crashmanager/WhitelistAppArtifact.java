package com.amazon.device.crashmanager;

import com.amazon.device.crashmanager.source.WhitelistAppArtifactSource;
import com.amazon.dp.logger.DPLogger;
import java.io.InputStream;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class WhitelistAppArtifact extends Artifact {
    private static final DPLogger log = new DPLogger("WhitelistAppArtifact");

    public WhitelistAppArtifact(String str, InputStream inputStream, long j) {
        super(str, inputStream, j);
    }

    @Override // com.amazon.device.crashmanager.Artifact
    public boolean shouldUploadArtifact() {
        DPLogger dPLogger = log;
        dPLogger.info("shouldUploadArtifact", "Verifying that the artifact can be uploaded", new Object[0]);
        Pattern whiteListedAppPatternsForTag = WhitelistAppArtifactSource.getWhiteListedAppPatternsForTag(getTag());
        String str = getMetadataMap().get("appName");
        dPLogger.info("shouldUploadArtifact", "mAppName: " + str, new Object[0]);
        if (whiteListedAppPatternsForTag.matcher(str).matches()) {
            return true;
        }
        dPLogger.info("shouldUploadArtifact", "Artifact is not whitelisted to be uploaded", new Object[0]);
        return false;
    }
}
