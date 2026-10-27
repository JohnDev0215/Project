package com.amazon.device.crashmanager.processor;

import com.amazon.device.utils.det.DetUtil;
import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
class ExtractJavaVersionHeaderProcessor implements DetUtil.HeaderProcessor {
    private static final Pattern JAVA_APP_VERSION_REGEX = Pattern.compile("^\\S+\\s+v(\\d+)\\s+\\((.+)\\)$");
    private final DetUtil.HeaderProcessor mNextProcessor;

    public ExtractJavaVersionHeaderProcessor(DetUtil.HeaderProcessor headerProcessor) {
        this.mNextProcessor = headerProcessor;
    }

    @Override // com.amazon.device.utils.det.DetUtil.HeaderProcessor
    public void process(String str, String str2, Writer writer) throws Exception {
        Matcher matcher = JAVA_APP_VERSION_REGEX.matcher(str2);
        if (matcher.matches()) {
            this.mNextProcessor.process(JsonDocumentFields.VERSION, matcher.group(1), writer);
        }
        this.mNextProcessor.process(str, str2, writer);
    }
}
