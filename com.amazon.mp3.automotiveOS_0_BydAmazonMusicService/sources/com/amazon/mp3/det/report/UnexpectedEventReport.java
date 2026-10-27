package com.amazon.mp3.det.report;

import android.content.Context;
import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.util.Log;
import com.amazon.music.marketplace.Marketplace;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes3.dex */
public class UnexpectedEventReport extends AbstractNewDetReport {
    private static final String TAG = "UnexpectedEventReport";
    private String mCrashDescriptor;
    private final Throwable mException;

    public UnexpectedEventReport(Context context, Throwable exception, ReportType reportType) {
        super(context, reportType);
        this.mException = exception;
        this.mCrashDescriptor = buildCrashDescriptor();
    }

    @Override // com.amazon.mp3.det.report.AbstractNewDetReport
    public StringBuilder createReportBody() {
        StringBuilder sb = new StringBuilder(getCrashLogHeader());
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        this.mException.printStackTrace(printWriter);
        printWriter.close();
        sb.append(stringWriter.toString());
        return sb;
    }

    private String getCrashLogHeader() {
        Marketplace homeMarketPlace;
        try {
            homeMarketPlace = AccountDetailUtil.get(this.mContext).getHomeMarketPlace();
        } catch (Exception unused) {
            homeMarketPlace = null;
            Log.error(TAG, "Unable to obtain marketplace.  Not including marketplace information in crash log header.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[Metadata]\nProcess: ");
        sb.append(this.mContext.getPackageName());
        sb.append("\nVersion: ");
        sb.append(ApplicationAttributes.getInstance(this.mContext).getVersion());
        sb.append("\nOsBuildNumber: ");
        sb.append(Build.VERSION.RELEASE);
        sb.append("\nCrashTimeUtc: ");
        sb.append(System.currentTimeMillis());
        sb.append("\nCrashType: ");
        sb.append(this.mException.getClass().getSimpleName());
        sb.append("\nCrashDescriptor: ");
        sb.append(this.mCrashDescriptor);
        sb.append("\nMarketplaceID: ");
        sb.append(homeMarketPlace != null ? homeMarketPlace.getObfuscatedId() : EnvironmentCompat.MEDIA_UNKNOWN);
        sb.append("\ncountryOfResidence: ");
        sb.append(AccountDetailUtil.getMusicTerritoryOfResidence());
        sb.append("\n\n[Events]\n");
        return sb.toString();
    }

    public String getCrashDescriptor() {
        return this.mCrashDescriptor;
    }

    private String buildCrashDescriptor() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            this.mException.printStackTrace(printWriter);
            String string = Integer.toString(stringWriter.toString().hashCode());
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$det$report$ReportType[getReportType().ordinal()];
            if (i == 1) {
                string = string + "-crash";
            } else if (i == 2) {
                string = string + "-exception";
            }
            printWriter.close();
            return string;
        } catch (Throwable th) {
            try {
                printWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.det.report.UnexpectedEventReport$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$det$report$ReportType;

        static {
            int[] iArr = new int[ReportType.values().length];
            $SwitchMap$com$amazon$mp3$det$report$ReportType = iArr;
            try {
                iArr[ReportType.UNCAUGHT_EXCEPTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$det$report$ReportType[ReportType.CAUGHT_EXCEPTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
