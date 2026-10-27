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
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class JavaCrashArtifactProcessor extends AbstractDetArtifactProcessor {
    public static final Set<String> JAVA_CRASH_ARTIFACT_TAGS;
    public static final Set<String> JAVA_CRASH_ARTIFACT_WHITELIST_TAGS;
    public static final Set<String> JAVA_STACK_TRACE_TAGS;
    public static final int MAX_LINES_TO_CHECK_FOR_NATIVE_CRASH = 50;
    private final CrashDescriptorDedupeUtil mCrashDedupeUtil;
    private final DetUtil.HeaderProcessor mJavaHeaderProcessor;
    private final MetricsHeaderProcessor mMetricsHeaderProcessor;
    private static final DPLogger log = new DPLogger("JavaCrashArtifactProcessor");
    public static final String REGEX_STACK_TRACE_DATA = "([a-zA-Z0-9_\\.\\$]+(Exception|Error|TerribleFailure))|(at\\s.*\\(.*\\))|#\\d+\\s+pc\\s+[\\w\\d]+\\s+([^\\+\\r\\n]+)";
    public static final Pattern REGEX_STACK_TRACE_PATTERN = Pattern.compile(REGEX_STACK_TRACE_DATA);
    public static int REGEX_GROUP_ID = 0;

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public /* bridge */ /* synthetic */ InputStream processArtifact(Artifact artifact) throws Exception {
        return super.processArtifact(artifact);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public /* bridge */ /* synthetic */ InputStream processArtifact(Artifact artifact, String str) throws Exception {
        return super.processArtifact(artifact, str);
    }

    static {
        HashSet hashSet = new HashSet(8);
        hashSet.add("system_app_crash");
        hashSet.add("data_app_crash");
        hashSet.add("data_app_exception");
        hashSet.add("system_app_wtf");
        hashSet.add("system_server_crash");
        hashSet.add("system_server_watchdog");
        hashSet.add("system_server_wtf");
        hashSet.add("data_app_native_crash");
        hashSet.add("system_app_native_crash");
        JAVA_CRASH_ARTIFACT_TAGS = Collections.unmodifiableSet(hashSet);
        HashSet hashSet2 = new HashSet(2);
        hashSet2.add("data_app_wtf");
        JAVA_CRASH_ARTIFACT_WHITELIST_TAGS = Collections.unmodifiableSet(hashSet2);
        HashSet hashSet3 = new HashSet(4);
        hashSet3.add("system_app_crash");
        hashSet3.add("data_app_crash");
        hashSet3.add("system_server_crash");
        hashSet3.add("system_app_wtf");
        hashSet3.add("system_server_wtf");
        JAVA_STACK_TRACE_TAGS = Collections.unmodifiableSet(hashSet3);
    }

    public JavaCrashArtifactProcessor(DetUtil detUtil, String str, Map<String, String> map, MetricsHeaderProcessorFactory metricsHeaderProcessorFactory, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil) {
        super(detUtil, str, map);
        if (metricsHeaderProcessorFactory == null) {
            throw new IllegalArgumentException("Metrics header processor factory must not be null.");
        }
        if (crashDescriptorDedupeUtil == null) {
            throw new IllegalArgumentException("CrashDedupeUtil must not be null.");
        }
        MetricsHeaderProcessor metricsHeaderProcessorConstruct = metricsHeaderProcessorFactory.construct(this.mHeaderWriter);
        this.mMetricsHeaderProcessor = metricsHeaderProcessorConstruct;
        HashMap map2 = new HashMap(3);
        map2.put("Process", metricsHeaderProcessorConstruct);
        map2.put("hasForegroundActivities", metricsHeaderProcessorConstruct);
        map2.put("Package", new ExtractJavaVersionHeaderProcessor(this.mHeaderWriter));
        this.mJavaHeaderProcessor = new DetUtil.DefaultHeaderProcessor(map2, this.mHeaderWriter);
        this.mCrashDedupeUtil = crashDescriptorDedupeUtil;
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public boolean canProcessTag(String str) {
        return JAVA_CRASH_ARTIFACT_TAGS.contains(str) || JAVA_CRASH_ARTIFACT_WHITELIST_TAGS.contains(str);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor
    protected void addSpecificHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer, String str) throws Exception {
        this.mMetricsHeaderProcessor.setProcessTag(artifact.getTag());
        this.mMetricsHeaderProcessor.setAction(str);
        this.mJavaHeaderProcessor.process("ContentType", "JavaCrash", writer);
        String strProcessHeaders = this.mDetUtil.processHeaders(bufferedReader, writer, this.mJavaHeaderProcessor);
        if (JAVA_STACK_TRACE_TAGS.contains(artifact.getTag()) && TextUtils.isEmpty(strProcessHeaders)) {
            strProcessHeaders = CrashDescriptorUtil.calculateCrashDescriptorFromTrace(bufferedReader, writer, this.mJavaHeaderProcessor, this.mMetricsHeaderProcessor.getProcessName(), CrashManagerActions.JAVA_CRASH, this.mBuffer, this.mMetricsHeaderProcessor, null);
        }
        this.mJavaHeaderProcessor.process("CrashDescriptor", strProcessHeaders, writer);
        CrashDescriptorDedupeUtil.crashDuplicateHeader(artifact.getTag(), strProcessHeaders, this.mCrashDedupeUtil, writer, this.mJavaHeaderProcessor);
        artifact.setCrashDescriptor(strProcessHeaders);
        artifact.addMetadata("appName", this.mMetricsHeaderProcessor.getProcessName());
    }
}
