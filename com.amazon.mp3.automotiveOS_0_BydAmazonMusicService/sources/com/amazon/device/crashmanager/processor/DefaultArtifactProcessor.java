package com.amazon.device.crashmanager.processor;

import com.amazon.device.crashmanager.Artifact;
import com.amazon.device.utils.det.DetUtil;
import com.amazon.dp.logger.DPLogger;
import java.io.BufferedReader;
import java.io.Writer;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class DefaultArtifactProcessor extends AbstractDetArtifactProcessor {
    private static final DPLogger log = new DPLogger("DefaultArtifactProcessor");

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor
    protected void addSpecificHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer, String str) throws Exception {
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public boolean canProcessTag(String str) {
        return true;
    }

    public DefaultArtifactProcessor(DetUtil detUtil, String str, Map<String, String> map) {
        super(detUtil, str, map);
    }
}
