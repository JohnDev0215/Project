package com.amazon.device.utils.det;

import android.os.Build;
import android.util.Log;
import java.io.BufferedReader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class DetUtil {
    private static final Pattern HEADER_VALUE_REGEX = Pattern.compile("^(.+?): (.+)$");
    private static final String TAG = "DetUtil";
    private static final SimpleDateFormat sDetDateFormat;
    private int mDetUploadTimeOut = (int) TimeUnit.MILLISECONDS.convert(120, TimeUnit.SECONDS);

    public interface HeaderProcessor {
        void process(String str, String str2, Writer writer) throws Exception;
    }

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd:HHmmss", Locale.US);
        sDetDateFormat = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static class DefaultHeaderProcessor implements HeaderProcessor {
        private final HeaderProcessor mDefaultHeaderProcessor;
        private final Map<String, HeaderProcessor> mHeaderProcessors;

        public DefaultHeaderProcessor(Map<String, HeaderProcessor> map, HeaderProcessor headerProcessor) {
            this.mHeaderProcessors = map;
            this.mDefaultHeaderProcessor = headerProcessor;
        }

        @Override // com.amazon.device.utils.det.DetUtil.HeaderProcessor
        public void process(String str, String str2, Writer writer) throws Exception {
            HeaderProcessor headerProcessor = this.mHeaderProcessors.get(str);
            if (headerProcessor != null) {
                headerProcessor.process(str, str2, writer);
            } else {
                this.mDefaultHeaderProcessor.process(str, str2, writer);
            }
        }
    }

    public static class HeaderWriter implements HeaderProcessor {
        @Override // com.amazon.device.utils.det.DetUtil.HeaderProcessor
        public void process(String str, String str2, Writer writer) throws Exception {
            if (str == null) {
                str = "null";
            }
            writer.write(str);
            writer.write(": ");
            if (str2 == null) {
                str2 = "null";
            }
            writer.write(str2);
            writer.write("\n");
        }
    }

    public String processHeaders(BufferedReader bufferedReader, Writer writer, HeaderProcessor headerProcessor) throws Exception {
        String str = null;
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null && !"".equals(line)) {
                Matcher matcher = HEADER_VALUE_REGEX.matcher(line);
                if (!matcher.matches()) {
                    break;
                }
                String strGroup = matcher.group(1);
                String strGroup2 = matcher.group(2);
                headerProcessor.process(strGroup, strGroup2, writer);
                if (strGroup.equals("TraceHashCode")) {
                    str = strGroup2;
                }
            } else {
                return str;
            }
        }
        return str;
    }

    public void addMetadataSectionHeader(HeaderProcessor headerProcessor, String str, Map<String, String> map, Writer writer) throws Exception {
        writer.write("[Metadata]\n");
        headerProcessor.process("DeviceType", str, writer);
        headerProcessor.process("SystemVersion", Build.FINGERPRINT, writer);
        headerProcessor.process("BuildType", Build.TYPE, writer);
        headerProcessor.process("BuildTags", Build.TAGS, writer);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    Log.d(TAG, "addMetadataSectionHeader: Extra info key or value set to null. Skipping. Key :" + entry.getKey() + " Value :" + entry.getValue());
                } else {
                    headerProcessor.process(entry.getKey(), entry.getValue(), writer);
                }
            }
        }
    }

    public void addEventsSectionHeader(Writer writer) throws Exception {
        writer.write("\n[Events]\n");
    }

    public String formatDate(long j) {
        return sDetDateFormat.format(new Date(j));
    }
}
