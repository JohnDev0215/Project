package com.amazon.device.crashmanager.utils;

import android.text.TextUtils;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.metrics.MetricsConstants;
import com.amazon.device.crashmanager.processor.AnrArtifactProcessor;
import com.amazon.device.crashmanager.processor.JavaCrashArtifactProcessor;
import com.amazon.device.crashmanager.processor.MetricsHeaderProcessor;
import com.amazon.device.crashmanager.processor.NativeCrashArtifactProcessor;
import com.amazon.device.utils.det.DetUtil;
import com.amazon.dp.logger.DPLogger;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: classes2.dex */
public class CrashDescriptorUtil {
    static final int ARTIFACT_BUFFER_SIZE = 25600;
    static final int MAX_NUMBER_OF_LINES_TO_SEARCH_FOR_PROCESS_NAME = 10;
    static final int MAX_NUMBER_OF_LINES_TO_SEARCH_FOR_STACK_TRACE = 100;
    private static final DPLogger log = new DPLogger("CrashDescriptorUtil");

    public static String calculateCrashDescriptor(Throwable th, String str) throws Exception {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        StringBuilder sb = new StringBuilder();
        sb.append(stringWriter);
        if (str != null) {
            sb.append(str);
        }
        return calculateCrashDescriptor(sb);
    }

    public static String calculateCrashDescriptor(StringBuilder sb) throws Exception {
        if (sb == null || sb.length() == 0) {
            return null;
        }
        return new BigInteger(MessageDigest.getInstance("SHA1").digest(sb.toString().getBytes())).abs().toString(16);
    }

    public static String calculateCrashDescriptorFromTrace(BufferedReader bufferedReader, Writer writer, DetUtil.HeaderProcessor headerProcessor, String str, String str2, char[] cArr, MetricsHeaderProcessor metricsHeaderProcessor, MetricEvent metricEvent) throws Exception {
        BufferedReader bufferedReaderFetchBodyFromReader;
        StringBuilder sbExtractStackTraceForPinFailures;
        bufferedReaderFetchBodyFromReader = fetchBodyFromReader(bufferedReader, cArr);
        str2.hashCode();
        switch (str2) {
            case "PIN_FAILURE":
                sbExtractStackTraceForPinFailures = extractStackTraceForPinFailures(bufferedReaderFetchBodyFromReader, metricEvent);
                break;
            case "ANR":
                sbExtractStackTraceForPinFailures = extractStackTraceForANR(bufferedReaderFetchBodyFromReader, metricsHeaderProcessor);
                break;
            case "STRICT_MODE_VIOLATION":
                sbExtractStackTraceForPinFailures = extractStackTraceForSMV(bufferedReaderFetchBodyFromReader);
                break;
            case "JAVA_CRASH":
                sbExtractStackTraceForPinFailures = extractStackTraceForJavaCrash(bufferedReaderFetchBodyFromReader, str);
                break;
            case "NATIVE_CRASH":
                sbExtractStackTraceForPinFailures = extractStackTraceForNativeCrash(bufferedReaderFetchBodyFromReader, writer, headerProcessor);
                break;
            default:
                return null;
        }
        return calculateCrashDescriptor(sbExtractStackTraceForPinFailures);
    }

    public static BufferedReader fetchBodyFromReader(BufferedReader bufferedReader, char[] cArr) throws IOException {
        bufferedReader.mark(ARTIFACT_BUFFER_SIZE);
        try {
            bufferedReader.read(cArr, 0, ARTIFACT_BUFFER_SIZE);
            bufferedReader.reset();
            return new BufferedReader(new CharArrayReader(cArr), 1);
        } catch (Throwable th) {
            bufferedReader.reset();
            throw th;
        }
    }

    private static StringBuilder extractStackTraceForSMV(BufferedReader bufferedReader) throws Exception {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null || line.trim().equals("")) {
                break;
            }
            sb.append(line);
        }
        if (sb.length() > 0) {
            return sb;
        }
        log.warn("extractSmvInfoFromCrashBody", "No stack trace.", new Object[0]);
        return null;
    }

    private static StringBuilder extractStackTraceForNativeCrash(BufferedReader bufferedReader, Writer writer, DetUtil.HeaderProcessor headerProcessor) throws Exception {
        String line;
        StringBuilder sb = new StringBuilder();
        String strExtractProcessNameFromNativeCrash = extractProcessNameFromNativeCrash(bufferedReader);
        if (TextUtils.isEmpty(strExtractProcessNameFromNativeCrash)) {
            return null;
        }
        sb.append(strExtractProcessNameFromNativeCrash);
        headerProcessor.process("Process", strExtractProcessNameFromNativeCrash, writer);
        for (int i = 0; i < 100 && (line = bufferedReader.readLine()) != null; i++) {
            Matcher matcher = NativeCrashArtifactProcessor.STACKFRAME_IDENTIFIER.matcher(line);
            if (matcher.find()) {
                sb.append(matcher.group(1));
                while (true) {
                    String line2 = bufferedReader.readLine();
                    if (line2 == null) {
                        break;
                    }
                    Matcher matcher2 = NativeCrashArtifactProcessor.STACKFRAME_IDENTIFIER.matcher(line2);
                    if (!matcher2.find()) {
                        break;
                    }
                    sb.append(matcher2.group(1));
                }
                return sb;
            }
        }
        log.warn("extractNativeCrashInfoFromCrashBody", "Could not find stack trace in native crash.", new Object[0]);
        return null;
    }

    public static String extractProcessNameFromNativeCrash(BufferedReader bufferedReader) throws IOException {
        String line;
        for (int i = 0; i < 10 && (line = bufferedReader.readLine()) != null; i++) {
            Matcher matcher = NativeCrashArtifactProcessor.NATIVE_APP_NAME_REGEX.matcher(line);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        log.warn("extractNativeCrashInfoFromCrashBody", "Could not extract 'Process' from native crash.", new Object[0]);
        return null;
    }

    private static StringBuilder extractStackTraceForJavaCrash(BufferedReader bufferedReader, String str) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        }
        String line = bufferedReader.readLine();
        JavaCrashArtifactProcessor.REGEX_GROUP_ID = 0;
        if (line != null) {
            if (line.startsWith("***")) {
                JavaCrashArtifactProcessor.REGEX_GROUP_ID = 4;
                for (int i = 0; !bufferedReader.readLine().startsWith("backtrace:") && i < 50; i++) {
                }
                line = bufferedReader.readLine();
            }
            do {
                Matcher matcher = JavaCrashArtifactProcessor.REGEX_STACK_TRACE_PATTERN.matcher(line);
                if (!matcher.find()) {
                    break;
                }
                sb.append(matcher.group(JavaCrashArtifactProcessor.REGEX_GROUP_ID));
                line = bufferedReader.readLine();
            } while (line != null);
        }
        if (sb.length() > 0) {
            return sb;
        }
        log.warn("extractJavaCrashInfoFromCrashBody", "No stack trace.", new Object[0]);
        return null;
    }

    private static StringBuilder extractStackTraceForANR(BufferedReader bufferedReader, MetricsHeaderProcessor metricsHeaderProcessor) throws Exception {
        String line;
        do {
            line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
        } while (!line.startsWith("Cmd line: "));
        bufferedReader.readLine();
        String line2 = bufferedReader.readLine();
        if (line2 == null || !line2.startsWith("DALVIK THREADS")) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (metricsHeaderProcessor.getProcessName() != null) {
            sb.append(metricsHeaderProcessor.getProcessName());
        }
        int i = 0;
        while (true) {
            String line3 = bufferedReader.readLine();
            if (line3 == null) {
                break;
            }
            i++;
            if ((line3.isEmpty() || line3.charAt(0) == '\n' || line3.charAt(0) == '\r') && (i >= 10 || !sb.toString().equals(metricsHeaderProcessor.getProcessName()))) {
                break;
            }
            if (AnrArtifactProcessor.REGEX_STACK_TRACE_PATTERN.matcher(line3).find()) {
                sb.append(line3);
            }
        }
        if (sb.length() > 0) {
            return sb;
        }
        log.warn("extractAnrInfoFromCrashBody", "No stack trace.", new Object[0]);
        return null;
    }

    private static StringBuilder extractStackTraceForPinFailures(BufferedReader bufferedReader, MetricEvent metricEvent) throws Exception {
        String line = bufferedReader.readLine();
        if (TextUtils.isEmpty(line)) {
            log.warn("extractStackTraceForPinFailures", "Domain and/or Certificate information not present in the PinFailure", new Object[0]);
            metricEvent.incrementCounter(MetricsConstants.COUNTER_EMPTY_PINCRASH, 1.0d);
            return null;
        }
        return new StringBuilder(line);
    }
}
