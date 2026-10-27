package com.android.textclassifier.common.logging;

import android.content.Context;
import android.text.TextUtils;
import com.android.textclassifier.common.logging.ResultIdUtils;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class ResultIdUtils {
    private static final Pattern EXTRACT_MODEL_NAME_FROM_RESULT_ID = Pattern.compile("^[^|]*\\|([^|]*)\\|[^|]*$");

    public static String createId(Context context, String str, int i, int i2, List<Optional<ModelInfo>> list) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(list);
        return createId(Objects.hashCode(str, Integer.valueOf(i), Integer.valueOf(i2), context.getPackageName()), list);
    }

    public static String createId(int i, List<Optional<ModelInfo>> list) {
        Preconditions.checkNotNull(list);
        ArrayList arrayList = new ArrayList();
        Iterator<Optional<ModelInfo>> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((String) it.next().transform(new Function() { // from class: com.android.textclassifier.common.logging.-$$Lambda$oDz1qPSnjjDY1RvRhoswzP9Xa8s
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return ((ResultIdUtils.ModelInfo) obj).toModelName();
                }
            }).or(""));
        }
        return String.format(Locale.US, "%s|%s|%d", "androidtc", Joiner.on(";").join(arrayList), Integer.valueOf(i));
    }

    public static boolean isFromDefaultTextClassifier(String str) {
        return str.startsWith("androidtc|");
    }

    public static ImmutableList<String> getModelNames(String str) {
        if (TextUtils.isEmpty(str)) {
            return ImmutableList.of();
        }
        Matcher matcher = EXTRACT_MODEL_NAME_FROM_RESULT_ID.matcher(str);
        if (!matcher.find()) {
            return ImmutableList.of();
        }
        return ImmutableList.copyOf((Collection) Splitter.on(";").splitToList(matcher.group(1)));
    }

    public static class ModelInfo {
        private final String modelName;

        public ModelInfo(int i, String str) {
            this.modelName = createModelName(i, str);
        }

        private static String createModelName(int i, String str) {
            return String.format(Locale.US, "%s_v%d", str, Integer.valueOf(i));
        }

        public String toModelName() {
            return this.modelName;
        }
    }
}
