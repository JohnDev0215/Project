package androidx.core.os;

import android.os.LocaleList;
import androidx.annotation.DoNotInline;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class LocaleListCompat {
    private static final LocaleListCompat sEmptyLocaleList = create(new Locale[0]);
    private final LocaleListInterface mImpl;

    private LocaleListCompat(LocaleListInterface localeListInterface) {
        this.mImpl = localeListInterface;
    }

    @RequiresApi(24)
    @Deprecated
    public static LocaleListCompat wrap(Object obj) {
        return wrap((LocaleList) obj);
    }

    @NonNull
    @RequiresApi(24)
    public static LocaleListCompat wrap(@NonNull LocaleList localeList) {
        return new LocaleListCompat(new LocaleListPlatformWrapper(localeList));
    }

    @Nullable
    public Object unwrap() {
        return this.mImpl.getLocaleList();
    }

    @NonNull
    public static LocaleListCompat create(@NonNull Locale... localeArr) {
        return wrap(Api24Impl.createLocaleList(localeArr));
    }

    @Nullable
    public Locale get(int i) {
        return this.mImpl.get(i);
    }

    public boolean isEmpty() {
        return this.mImpl.isEmpty();
    }

    @IntRange(from = 0)
    public int size() {
        return this.mImpl.size();
    }

    @IntRange(from = -1)
    public int indexOf(@Nullable Locale locale) {
        return this.mImpl.indexOf(locale);
    }

    @NonNull
    public String toLanguageTags() {
        return this.mImpl.toLanguageTags();
    }

    @Nullable
    public Locale getFirstMatch(@NonNull String[] strArr) {
        return this.mImpl.getFirstMatch(strArr);
    }

    @NonNull
    public static LocaleListCompat getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    @NonNull
    public static LocaleListCompat forLanguageTags(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] strArrSplit = str.split(",", -1);
        int length = strArrSplit.length;
        Locale[] localeArr = new Locale[length];
        for (int i = 0; i < length; i++) {
            localeArr[i] = Api21Impl.forLanguageTag(strArrSplit[i]);
        }
        return create(localeArr);
    }

    static Locale forLanguageTagCompat(String str) {
        if (str.contains("-")) {
            String[] strArrSplit = str.split("-", -1);
            if (strArrSplit.length > 2) {
                return new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]);
            }
            if (strArrSplit.length > 1) {
                return new Locale(strArrSplit[0], strArrSplit[1]);
            }
            if (strArrSplit.length == 1) {
                return new Locale(strArrSplit[0]);
            }
        } else if (str.contains(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)) {
            String[] strArrSplit2 = str.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, -1);
            if (strArrSplit2.length > 2) {
                return new Locale(strArrSplit2[0], strArrSplit2[1], strArrSplit2[2]);
            }
            if (strArrSplit2.length > 1) {
                return new Locale(strArrSplit2[0], strArrSplit2[1]);
            }
            if (strArrSplit2.length == 1) {
                return new Locale(strArrSplit2[0]);
            }
        } else {
            return new Locale(str);
        }
        throw new IllegalArgumentException("Can not parse language tag: [" + str + "]");
    }

    @NonNull
    @Size(min = 1)
    public static LocaleListCompat getAdjustedDefault() {
        return wrap(Api24Impl.getAdjustedDefault());
    }

    @NonNull
    @Size(min = 1)
    public static LocaleListCompat getDefault() {
        return wrap(Api24Impl.getDefault());
    }

    public boolean equals(Object obj) {
        return (obj instanceof LocaleListCompat) && this.mImpl.equals(((LocaleListCompat) obj).mImpl);
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    @NonNull
    public String toString() {
        return this.mImpl.toString();
    }

    @RequiresApi(24)
    static class Api24Impl {
        private Api24Impl() {
        }

        @DoNotInline
        static LocaleList createLocaleList(Locale... localeArr) {
            return new LocaleList(localeArr);
        }

        @DoNotInline
        static LocaleList getAdjustedDefault() {
            return LocaleList.getAdjustedDefault();
        }

        @DoNotInline
        static LocaleList getDefault() {
            return LocaleList.getDefault();
        }
    }

    @RequiresApi(21)
    static class Api21Impl {
        private Api21Impl() {
        }

        @DoNotInline
        static Locale forLanguageTag(String str) {
            return Locale.forLanguageTag(str);
        }
    }
}
