package com.amazon.device.crashmanager.processor;

import android.text.TextUtils;
import com.amazon.device.crashmanager.Artifact;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil;
import com.amazon.device.crashmanager.utils.CrashDescriptorUtil;
import com.amazon.device.utils.det.DetUtil;
import com.amazon.dp.logger.DPLogger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class SmvArtifactProcessor extends AbstractDetArtifactProcessor {
    public static final Set<String> SMV_ARTIFACT_TAGS;
    private static final DPLogger log = new DPLogger("SmvArtifactProcessor");
    private final CrashDescriptorDedupeUtil mCrashDedupeUtil;
    private final MetricsHeaderProcessor mMetricsHeaderProcessor;
    private final DetUtil.HeaderProcessor mSmvHeaderProcessor;

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public /* bridge */ /* synthetic */ InputStream processArtifact(Artifact artifact) throws Exception {
        return super.processArtifact(artifact);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public /* bridge */ /* synthetic */ InputStream processArtifact(Artifact artifact, String str) throws Exception {
        return super.processArtifact(artifact, str);
    }

    static {
        HashSet hashSet = new HashSet(2);
        hashSet.add("system_app_strictmode");
        hashSet.add("data_app_strictmode");
        SMV_ARTIFACT_TAGS = Collections.unmodifiableSet(hashSet);
    }

    public SmvArtifactProcessor(DetUtil detUtil, String str, Map<String, String> map, MetricsHeaderProcessorFactory metricsHeaderProcessorFactory, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil) {
        super(detUtil, str, map);
        if (metricsHeaderProcessorFactory == null) {
            throw new IllegalArgumentException("Metrics header processor factory must not be null.");
        }
        if (crashDescriptorDedupeUtil == null) {
            throw new IllegalArgumentException("CrashDedupeUTil must not be null.");
        }
        MetricsHeaderProcessor metricsHeaderProcessorConstruct = metricsHeaderProcessorFactory.construct(this.mHeaderWriter);
        this.mMetricsHeaderProcessor = metricsHeaderProcessorConstruct;
        HashMap map2 = new HashMap(3);
        map2.put("Process", metricsHeaderProcessorConstruct);
        map2.put("hasForegroundActivities", metricsHeaderProcessorConstruct);
        map2.put("Package", new ExtractJavaVersionHeaderProcessor(this.mHeaderWriter));
        this.mSmvHeaderProcessor = new DetUtil.DefaultHeaderProcessor(map2, this.mHeaderWriter);
        this.mCrashDedupeUtil = crashDescriptorDedupeUtil;
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public boolean canProcessTag(String str) {
        return SMV_ARTIFACT_TAGS.contains(str);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor
    protected void addSpecificHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer, String str) throws Exception {
        this.mMetricsHeaderProcessor.setProcessTag(artifact.getTag());
        this.mMetricsHeaderProcessor.setAction(str);
        this.mSmvHeaderProcessor.process("ContentType", "JavaCrash", writer);
        String strProcessHeaders = this.mDetUtil.processHeaders(bufferedReader, writer, this.mSmvHeaderProcessor);
        if (TextUtils.isEmpty(strProcessHeaders)) {
            strProcessHeaders = CrashDescriptorUtil.calculateCrashDescriptorFromTrace(bufferedReader, writer, this.mSmvHeaderProcessor, null, CrashManagerActions.STRICT_MODE_VIOLATION, this.mBuffer, this.mMetricsHeaderProcessor, null);
        }
        this.mSmvHeaderProcessor.process("CrashDescriptor", strProcessHeaders, writer);
        CrashDescriptorDedupeUtil.crashDuplicateHeader(artifact.getTag(), strProcessHeaders, this.mCrashDedupeUtil, writer, this.mSmvHeaderProcessor);
        artifact.setCrashDescriptor(strProcessHeaders);
    }
}
