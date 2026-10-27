package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.metrics.pmet.util.ReportableString;
import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleException extends Exception {
    private final SubtitleErrorCode mCode;

    public enum SubtitleErrorCode {
        UNKNOWN,
        DOWNLOAD,
        PARSING;

        private static final Set<String> ERROR_NAMES = new HashSet();

        static {
            for (SubtitleErrorCode subtitleErrorCode : values()) {
                ERROR_NAMES.add(subtitleErrorCode.name());
            }
        }

        public static ReportableString toReportableString(@Nonnull SubtitleErrorCode subtitleErrorCode) {
            Preconditions.checkNotNull(subtitleErrorCode, "error");
            return new ReportableString(subtitleErrorCode.name(), ERROR_NAMES, "UNKNOWN_ERROR");
        }
    }

    public SubtitleException() {
        this(SubtitleErrorCode.UNKNOWN);
    }

    public SubtitleException(SubtitleErrorCode subtitleErrorCode) {
        this.mCode = subtitleErrorCode;
    }

    public SubtitleException(SubtitleErrorCode subtitleErrorCode, String str) {
        super(str);
        this.mCode = subtitleErrorCode;
    }

    public SubtitleException(SubtitleErrorCode subtitleErrorCode, String str, Throwable th) {
        super(str, th);
        this.mCode = subtitleErrorCode;
    }

    public SubtitleErrorCode getErrorCode() {
        return this.mCode;
    }
}
