package com.amazon.mp3.auto.carmode.detection;

import android.content.Context;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: CarDetectionDataLoader.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u00102\u00020\u0001:\u0003\u0011\u0010\u0012B\u000f\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarDetectionDataLoader;", "", "", "fileName", "", "loadCarNameTxt", "", "Lcom/amazon/mp3/auto/carmode/detection/CarDetectionDataLoader$CarDetectionRegex;", "loadCarRegexJson", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "Companion", "CarDetectionRegex", "RegexFileData", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarDetectionDataLoader {
    private static final String TAG = CarDetectionDataLoader.class.getSimpleName();
    private final Context context;

    public CarDetectionDataLoader(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX INFO: compiled from: CarDetectionDataLoader.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarDetectionDataLoader$RegexFileData;", "", "", "toString", "", "hashCode", "other", "", "equals", "regex", "Ljava/lang/String;", "getRegex", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "getType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class RegexFileData {
        private final String regex;
        private final String type;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RegexFileData)) {
                return false;
            }
            RegexFileData regexFileData = (RegexFileData) other;
            return Intrinsics.areEqual(this.regex, regexFileData.regex) && Intrinsics.areEqual(this.type, regexFileData.type);
        }

        public int hashCode() {
            return (this.regex.hashCode() * 31) + this.type.hashCode();
        }

        public String toString() {
            return "RegexFileData(regex=" + this.regex + ", type=" + this.type + ')';
        }

        public final String getRegex() {
            return this.regex;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: CarDetectionDataLoader.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarDetectionDataLoader$CarDetectionRegex;", "", "", "toString", "", "hashCode", "other", "", "equals", "Ljava/util/regex/Pattern;", "pattern", "Ljava/util/regex/Pattern;", "getPattern", "()Ljava/util/regex/Pattern;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/String;", "getType", "()Ljava/lang/String;", "<init>", "(Ljava/util/regex/Pattern;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class CarDetectionRegex {
        private final Pattern pattern;
        private final String type;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CarDetectionRegex)) {
                return false;
            }
            CarDetectionRegex carDetectionRegex = (CarDetectionRegex) other;
            return Intrinsics.areEqual(this.pattern, carDetectionRegex.pattern) && Intrinsics.areEqual(this.type, carDetectionRegex.type);
        }

        public int hashCode() {
            return (this.pattern.hashCode() * 31) + this.type.hashCode();
        }

        public String toString() {
            return "CarDetectionRegex(pattern=" + this.pattern + ", type=" + this.type + ')';
        }

        public CarDetectionRegex(Pattern pattern, String type) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            Intrinsics.checkNotNullParameter(type, "type");
            this.pattern = pattern;
            this.type = type;
        }

        public final Pattern getPattern() {
            return this.pattern;
        }

        public final String getType() {
            return this.type;
        }
    }

    public final Set<String> loadCarNameTxt(String fileName) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        try {
            InputStream inputStreamOpen = this.context.getAssets().open(fileName);
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(fileName)");
            Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                HashSet hashSet = SequencesKt___SequencesKt.toHashSet(SequencesKt___SequencesKt.filter(TextStreamsKt.lineSequence(bufferedReader), new Function1<String, Boolean>() { // from class: com.amazon.mp3.auto.carmode.detection.CarDetectionDataLoader$loadCarNameTxt$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(String it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(!(it.length() == 0));
                    }
                }));
                CloseableKt.closeFinally(bufferedReader, null);
                return hashSet;
            } finally {
            }
        } catch (IOException e) {
            Log.error(TAG, "Fail to load txt file " + fileName + " due to error", e);
            return null;
        }
    }

    public final List<CarDetectionRegex> loadCarRegexJson(String fileName) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        try {
            Type type = new TypeToken<List<? extends RegexFileData>>() { // from class: com.amazon.mp3.auto.carmode.detection.CarDetectionDataLoader$loadCarRegexJson$type$1
            }.getType();
            Gson gson = new Gson();
            InputStream inputStreamOpen = this.context.getAssets().open(fileName);
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(fileName)");
            Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
            Object objFromJson = gson.fromJson(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192), type);
            Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(context.…).bufferedReader(), type)");
            List<RegexFileData> list = (List) objFromJson;
            ArrayList arrayList = new ArrayList();
            for (RegexFileData regexFileData : list) {
                try {
                    Pattern pattern = Pattern.compile(regexFileData.getRegex(), 66);
                    Intrinsics.checkNotNullExpressionValue(pattern, "pattern");
                    arrayList.add(new CarDetectionRegex(pattern, regexFileData.getType()));
                } catch (Exception e) {
                    Log.error(TAG, Intrinsics.stringPlus("Unable to compile regex pattern for vehicle type ", regexFileData.getType()), e);
                }
            }
            return arrayList;
        } catch (Exception e2) {
            Log.error(TAG, "Fail to load json file " + fileName + " due to error", e2);
            return null;
        }
    }
}
