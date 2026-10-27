package com.amazon.appmanager.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.appmanager.R$raw;
import com.amazon.appmanager.lib.PreloadMetricData;
import com.amazon.appmanager.lib.metrics.MaftMetricsFactory;
import com.amazon.maft.metrics.PreloadMetrics;
import com.amazon.maft.metrics.PreloadMetricsFactory;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.firebase.perf.plugin.util.AsmString;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
class DefaultPreloadManager extends PreloadManager {
    public static final Signature SIGNATURE_AMAZON = new Signature("3082056130820349020601200123ab56300d06092a864886f70d01010b05003074311430120603550408130b4e657661646120433d5553313e303c060355040b13354a617661204f626a656374205369676e696e67204f3d416d617a6f6e205365727669636573204c4c43204c3d4c6173205665676173311c301a06035504031313416d617a6f6e2053656c662d5369676e204341301e170d3039303331333138343132365a170d3333313032333030303030305a3074311430120603550408130b4e657661646120433d5553313e303c060355040b13354a617661204f626a656374205369676e696e67204f3d416d617a6f6e205365727669636573204c4c43204c3d4c6173205665676173311c301a06035504031313416d617a6f6e205365727669636573204c4c4330820222300d06092a864886f70d01010105000382020f003082020a0282020100ac60dafe1c962683e46a6e5b5c2945f0f1a75ef8f268f67994c3841a9366aec784ab1baf7ef8c90e95616272fbc71535a478812f872163e0c07a562c6e5acda475f12ec78212ba395ff90b21bc8c0e4ffc927cbdcec695d91c9c369481a7a230956ff3150f43e5ba307c345558095f52c8d830bfd3b29e0f4c81e7e2cf7154dc1a4ae7b6647a983079256fa507eefada8cab1c59c8220e65feee0c0f46f0c2010df8749d4011508f2ae92d73d4b16247e5f068ce826897ff6fe1986a5f296910ab402f8c9625dedd7acf40ea15f11b012f6fde1569527d7604139d3d7f072a71747c6bf0de03e4fe84fa246a9ce581f483d0922e10225e732e632e98861644bf3b5919927086a2ace441daec6bfa237d9a61ea4a529126f7d55dde74039bad8dbfa1d5c51b9c845f504b24893a2857202b1b6e9cb9f1d0746140b58c4aff193446cd20b1d0346258b33dc8ed6fc454d872e255e8cc6d357cc4483b1473adf20bba5243979b63374ae989d8c11b46a136c698c52558593bd5bbcbbad3b5c42a74a2b91387d03d952dd84090aa033cc36b20b8781713544b2a7049725f745d9d2849c9e0199ff6c27bb002831dbe4183b87caf311852bc4709134417a8f4f4887f02f2c550cfd1bcdf6bfba151aef437e4bb67aa48315224c2052bd6e2d5c2daf533e00e73816885dd30ec16f6b0c5eb5876bb2f619986ba30fbad68139c4c08c70203010001300d06092a864886f70d01010b0500038202010050c9ccc6a3ea540ba5e7a0e9723e1e92d4e250edd32b33b56b3400b9b3a9648c2a830df99040226dd223d64f68088192188f790e0a44db321f487774f119ce9f72528e57b38456b17def3a59353211fa3e3510500359e787e075007f52edf39a45cd376a7b8988910ebefb9b09c2a6ace54526a540ab50bac457e5a45e3f17acc27c2ff7beed697c403fdca64396057e4c3780e21ff47cdba1cb7579ef72ac6c0917d8dad13083d6648ba42a1af8dc5176258ea265f7aa255738a429ea6a4363ca93185fb8ae1774684c3633a6762b22f0582f844fcf4cb6fe266eb986a2ac028e5f7fc8cf99d91ac36a556a4be9ea250fa440a958e2c4f9a05387b333b947ae7a40e107a8d9dd28b327c5645f5e5fbdd114f03770970f6c288bb7c4ce690209b1c44e6839c433c2c3cceb9b2a32c98a33144ed3d323a749dec1d0ecd73e0f1bdc37fffcb89ee2c19b413f8442aeaf2aaaa2272f54ce88653bdcd5c81073f32d07907750ee75b09244c3bbb1653021574289107fb39abe22227b8825cb4fbe469b7aca007b49e3f57716cc96f0f89770254233cb4ef86b3c9d76a79e0401816ed9695c5b4728be2df38455050091a3182968bf02a7cd7a7d16995a2463116c2b76ea722cb550864146c52bebeaa39d9ad32a2da1556dc54e79d7c6a3d16756bf26c7eb499e6e369170b732c67d2ee2377d38e157f440e7c63642d581ea65dd1e");
    private Set<Signature> signaturesAccepted;
    private String preloadId = null;
    private boolean triedGettingPreloadId = false;

    private static boolean checkEnableMetrics(PreloadMetricData preloadMetricData) {
        return preloadMetricData != null;
    }

    DefaultPreloadManager() {
    }

    @Override // com.amazon.appmanager.lib.PreloadManager
    @Nullable
    public String getPreloadAssociateTagWithoutRegionId_Minerva(@NonNull Context context) throws RemoteException, SecurityException, IOException, IllegalArgumentException {
        Log.d("MaftPreloadManagerAndroidLibLogs", "getPreloadAssociateTagWithoutRegionId_Minerva, internally building minerva client");
        return getPreloadId_Minerva(context, MaftMetricsFactory.getInstance(context), null);
    }

    private synchronized String getPreloadId_Minerva(Context context, PreloadMetricsFactory preloadMetricsFactory, PreloadMetricData preloadMetricData) throws RemoteException, SecurityException, IOException {
        if (this.triedGettingPreloadId) {
            return this.preloadId;
        }
        boolean z = true;
        this.triedGettingPreloadId = true;
        PreloadMetrics preloadMetricsNewPreloadMetrics = preloadMetricsFactory.newPreloadMetrics("tj9grzel", "0ps9/2/03330400");
        PreloadMetrics preloadMetricsNewPreloadMetrics2 = preloadMetricsFactory.newPreloadMetrics("tj9grzel", "0ps9/2/03330400");
        LinkedList linkedList = new LinkedList();
        linkedList.add(AsmString.METHOD_FIREBASE_PERF_TRACE_START);
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.amazon.appmanager.lib.PreloadManager", 0);
        this.preloadId = sharedPreferences.getString("preload_id", null);
        if (checkEnableMetrics(preloadMetricData)) {
            preloadMetricData.setPTPreviousValue(this.preloadId);
        }
        if (this.preloadId != null) {
            z = false;
        }
        if (z) {
            linkedList.add("first");
        }
        if (z || "".equals(this.preloadId)) {
            String preloadIdFromAllSources_Minerva = getPreloadIdFromAllSources_Minerva(context, preloadMetricsFactory, preloadMetricData);
            this.preloadId = preloadIdFromAllSources_Minerva;
            if (preloadIdFromAllSources_Minerva == null) {
                sharedPreferences.edit().putString("preload_id", "").apply();
                linkedList.add(context.getPackageName());
                preloadMetricsNewPreloadMetrics.addProperty("MetricVariableName", TextUtils.join("/", linkedList)).addCount("MetricVariableCount", 1.0d);
                recordMinervaMetric(preloadMetricsNewPreloadMetrics);
                return null;
            }
            sharedPreferences.edit().putString("preload_id", this.preloadId).apply();
            if (!z) {
                linkedList.add(context.getPackageName());
            }
        } else if (checkEnableMetrics(preloadMetricData)) {
            preloadMetricData.setPTSource(PreloadMetricData.SourceType.SP.name());
        }
        linkedList.add(this.preloadId);
        preloadMetricsNewPreloadMetrics2.addProperty("MetricVariableName", TextUtils.join("/", linkedList)).addCount("MetricVariableCount", 1.0d);
        recordMinervaMetric(preloadMetricsNewPreloadMetrics2);
        return this.preloadId;
    }

    private String getPreloadIdFromAllSources_Minerva(Context context, PreloadMetricsFactory preloadMetricsFactory, PreloadMetricData preloadMetricData) throws Exception {
        String preloadIdFromContentProvider_Minerva = getPreloadIdFromContentProvider_Minerva(context, preloadMetricsFactory, preloadMetricData);
        if (preloadIdFromContentProvider_Minerva == null) {
            preloadIdFromContentProvider_Minerva = getPreloadIdFromEmbeddedFile_Minerva(context, preloadMetricsFactory, preloadMetricData);
            if (preloadIdFromContentProvider_Minerva != null && checkEnableMetrics(preloadMetricData)) {
                preloadMetricData.setPTSource(PreloadMetricData.SourceType.File.name());
            }
        } else if (checkEnableMetrics(preloadMetricData)) {
            preloadMetricData.setPTSource(PreloadMetricData.SourceType.CP.name());
        }
        return preloadIdFromContentProvider_Minerva;
    }

    @SuppressFBWarnings
    private String getPreloadIdFromContentProvider_Minerva(Context context, PreloadMetricsFactory preloadMetricsFactory, PreloadMetricData preloadMetricData) throws Exception {
        String str;
        Uri uri = Uri.parse(String.format("content://com.amazon.appmanager.preload.app_info.provider/%s", context.getPackageName()));
        ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider(uri.getEncodedAuthority(), 0);
        if (providerInfoResolveContentProvider == null) {
            return null;
        }
        if (!isSignatureValid(context, providerInfoResolveContentProvider.packageName)) {
            PreloadMetrics preloadMetricsNewPreloadMetrics = preloadMetricsFactory.newPreloadMetrics("tj9grzel", "seyv/2/03330400");
            preloadMetricsNewPreloadMetrics.addProperty("ErrorName", SecurityException.class.getSimpleName()).addCount("ErrorCount", 1.0d);
            recordMinervaMetric(preloadMetricsNewPreloadMetrics);
            if (checkEnableMetrics(preloadMetricData)) {
                preloadMetricData.logException("getPreloadIdFromContentProvider", "SecurityException");
            }
            throw new SecurityException("ContentProvider package has invalid signature.");
        }
        try {
            str = "getPreloadIdFromContentProvider";
            try {
                Cursor cursorQuery = context.getContentResolver().query(uri, null, null, null, null);
                try {
                    if (cursorQuery == null) {
                        PreloadMetrics preloadMetricsNewPreloadMetrics2 = preloadMetricsFactory.newPreloadMetrics("tj9grzel", "seyv/2/03330400");
                        preloadMetricsNewPreloadMetrics2.addProperty("ErrorName", "ContentProviderError").addCount("ErrorCount", 1.0d);
                        recordMinervaMetric(preloadMetricsNewPreloadMetrics2);
                        if (checkEnableMetrics(preloadMetricData)) {
                            preloadMetricData.logException(str, "CursorNULL");
                        }
                        if (cursorQuery == null) {
                            return null;
                        }
                        cursorQuery.close();
                        return null;
                    }
                    if (cursorQuery.getCount() == 0) {
                        if (checkEnableMetrics(preloadMetricData)) {
                            preloadMetricData.logException(str, "CursorCount0");
                        }
                        cursorQuery.close();
                        return null;
                    }
                    cursorQuery.moveToFirst();
                    String string = cursorQuery.getString(0);
                    cursorQuery.close();
                    return string;
                } finally {
                }
            } catch (Exception e) {
                e = e;
                if (checkEnableMetrics(preloadMetricData)) {
                    preloadMetricData.logException(str, e.getClass().getSimpleName());
                }
                throw e;
            }
        } catch (Exception e2) {
            e = e2;
            str = "getPreloadIdFromContentProvider";
        }
    }

    private String getPreloadIdFromEmbeddedFile_Minerva(Context context, PreloadMetricsFactory preloadMetricsFactory, PreloadMetricData preloadMetricData) throws IOException {
        return readStringFromRawResourceFile_Minerva(context, preloadMetricsFactory, R$raw.plm_associate_tag, preloadMetricData);
    }

    public String readStringFromRawResourceFile_Minerva(Context context, PreloadMetricsFactory preloadMetricsFactory, int i, PreloadMetricData preloadMetricData) throws Throwable {
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(i), Charset.forName(ParserUtil.UTF_8)));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line.trim());
                    }
                    String string = sb.toString();
                    String str = string.length() > 0 ? string : null;
                    try {
                        bufferedReader2.close();
                        return str;
                    } catch (IOException e) {
                        PreloadMetrics preloadMetricsNewPreloadMetrics = preloadMetricsFactory.newPreloadMetrics("tj9grzel", "seyv/2/03330400");
                        preloadMetricsNewPreloadMetrics.addProperty("ErrorName", IOException.class.getSimpleName()).addCount("ErrorCount", 1.0d);
                        recordMinervaMetric(preloadMetricsNewPreloadMetrics);
                        if (checkEnableMetrics(preloadMetricData)) {
                            preloadMetricData.logException("readStringFromRawResourceFile", e.getClass().getSimpleName());
                        }
                        throw new IOException(e);
                    }
                } catch (Resources.NotFoundException e2) {
                    e = e2;
                    PreloadMetrics preloadMetricsNewPreloadMetrics2 = preloadMetricsFactory.newPreloadMetrics("tj9grzel", "seyv/2/03330400");
                    preloadMetricsNewPreloadMetrics2.addProperty("ErrorName", Resources.NotFoundException.class.getSimpleName()).addCount("ErrorCount", 1.0d);
                    recordMinervaMetric(preloadMetricsNewPreloadMetrics2);
                    if (checkEnableMetrics(preloadMetricData)) {
                        preloadMetricData.logException("readStringFromRawResourceFile", e.getClass().getSimpleName());
                    }
                    throw new RuntimeException(e);
                } catch (IOException e3) {
                    e = e3;
                    PreloadMetrics preloadMetricsNewPreloadMetrics3 = preloadMetricsFactory.newPreloadMetrics("tj9grzel", "seyv/2/03330400");
                    preloadMetricsNewPreloadMetrics3.addProperty("ErrorName", IOException.class.getSimpleName()).addCount("ErrorCount", 1.0d);
                    recordMinervaMetric(preloadMetricsNewPreloadMetrics3);
                    if (checkEnableMetrics(preloadMetricData)) {
                        preloadMetricData.logException("readStringFromRawResourceFile", e.getClass().getSimpleName());
                    }
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            PreloadMetrics preloadMetricsNewPreloadMetrics4 = preloadMetricsFactory.newPreloadMetrics("tj9grzel", "seyv/2/03330400");
                            preloadMetricsNewPreloadMetrics4.addProperty("ErrorName", IOException.class.getSimpleName()).addCount("ErrorCount", 1.0d);
                            recordMinervaMetric(preloadMetricsNewPreloadMetrics4);
                            if (checkEnableMetrics(preloadMetricData)) {
                                preloadMetricData.logException("readStringFromRawResourceFile", e4.getClass().getSimpleName());
                            }
                            throw new IOException(e4);
                        }
                    }
                    throw th;
                }
            } catch (Resources.NotFoundException e5) {
                e = e5;
            } catch (IOException e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    protected boolean isSignatureValid(Context context, String str) {
        if (this.signaturesAccepted == null) {
            HashSet hashSet = new HashSet();
            this.signaturesAccepted = hashSet;
            hashSet.add(SIGNATURE_AMAZON);
            try {
                for (Signature signature : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
                    this.signaturesAccepted.add(signature);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        try {
            for (Signature signature2 : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                if (this.signaturesAccepted.contains(signature2)) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused2) {
        }
        return false;
    }

    void recordMinervaMetric(PreloadMetrics preloadMetrics) {
        try {
            preloadMetrics.record();
            Log.d("MaftPreloadManagerAndroidLibLogs", "minerva metric recorded successfully.");
        } catch (Exception e) {
            Log.d("MaftPreloadManagerAndroidLibLogs", e.getClass().getSimpleName() + " this exception occurred while recording minerva metric");
        }
    }
}
