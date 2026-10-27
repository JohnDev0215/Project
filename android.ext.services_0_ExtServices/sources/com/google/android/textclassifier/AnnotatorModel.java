package com.google.android.textclassifier;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class AnnotatorModel implements AutoCloseable {
    public static final double INVALID_LATITUDE = 180.0d;
    public static final float INVALID_LOCATION_ACCURACY_METERS = 0.0f;
    public static final double INVALID_LONGITUDE = 360.0d;
    static final String TYPE_ADDRESS = "address";
    static final String TYPE_DATE = "date";
    static final String TYPE_DATE_TIME = "datetime";
    static final String TYPE_EMAIL = "email";
    static final String TYPE_FLIGHT_NUMBER = "flight";
    static final String TYPE_OTHER = "other";
    static final String TYPE_PHONE = "phone";
    static final String TYPE_UNKNOWN = "";
    static final String TYPE_URL = "url";
    private long annotatorPtr;
    private final AtomicBoolean isClosed = new AtomicBoolean(false);
    private LangIdModel langIdModel;

    private native AnnotatedSpan[] nativeAnnotate(long j, String str, AnnotationOptions annotationOptions);

    private native AnnotatedSpan[][] nativeAnnotateStructuredInput(long j, InputFragment[] inputFragmentArr, AnnotationOptions annotationOptions);

    private native ClassificationResult[] nativeClassifyText(long j, String str, int i, int i2, ClassificationOptions classificationOptions, Object obj, String str2);

    private native void nativeCloseAnnotator(long j);

    private static native String nativeGetLocales(int i);

    private static native String nativeGetLocalesWithOffset(int i, long j, long j2);

    private static native String nativeGetName(int i);

    private static native String nativeGetNameWithOffset(int i, long j, long j2);

    private native long nativeGetNativeModelPtr(long j);

    private static native int nativeGetVersion(int i);

    private static native int nativeGetVersionWithOffset(int i, long j, long j2);

    private native boolean nativeInitializeContactEngine(long j, byte[] bArr);

    private native boolean nativeInitializeInstalledAppEngine(long j, byte[] bArr);

    private native boolean nativeInitializeKnowledgeEngine(long j, byte[] bArr);

    private native boolean nativeInitializePersonNameEngine(long j, int i, long j2, long j3);

    private native byte[] nativeLookUpKnowledgeEntity(long j, String str);

    private static native long nativeNewAnnotator(int i);

    private static native long nativeNewAnnotatorFromPath(String str);

    private static native long nativeNewAnnotatorWithOffset(int i, long j, long j2);

    private native void nativeSetLangId(long j, long j2);

    private native int[] nativeSuggestSelection(long j, String str, int i, int i2, SelectionOptions selectionOptions);

    static {
        System.loadLibrary("textclassifier");
    }

    public enum AnnotationUsecase {
        SMART(0),
        RAW(1);

        private final int value;

        AnnotationUsecase(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public AnnotatorModel(int i) {
        long jNativeNewAnnotator = nativeNewAnnotator(i);
        this.annotatorPtr = jNativeNewAnnotator;
        if (jNativeNewAnnotator == 0) {
            throw new IllegalArgumentException("Couldn't initialize TC from file descriptor.");
        }
    }

    public AnnotatorModel(String str) {
        long jNativeNewAnnotatorFromPath = nativeNewAnnotatorFromPath(str);
        this.annotatorPtr = jNativeNewAnnotatorFromPath;
        if (jNativeNewAnnotatorFromPath == 0) {
            throw new IllegalArgumentException("Couldn't initialize TC from given file.");
        }
    }

    public void initializeKnowledgeEngine(byte[] bArr) {
        if (!nativeInitializeKnowledgeEngine(this.annotatorPtr, bArr)) {
            throw new IllegalArgumentException("Couldn't initialize the KG engine");
        }
    }

    public void initializeContactEngine(byte[] bArr) {
        if (!nativeInitializeContactEngine(this.annotatorPtr, bArr)) {
            throw new IllegalArgumentException("Couldn't initialize the contact engine");
        }
    }

    public void initializeInstalledAppEngine(byte[] bArr) {
        if (!nativeInitializeInstalledAppEngine(this.annotatorPtr, bArr)) {
            throw new IllegalArgumentException("Couldn't initialize the installed app engine");
        }
    }

    public void setLangIdModel(LangIdModel langIdModel) {
        this.langIdModel = langIdModel;
        nativeSetLangId(this.annotatorPtr, langIdModel == null ? 0L : langIdModel.getNativePointer());
    }

    public int[] suggestSelection(String str, int i, int i2, SelectionOptions selectionOptions) {
        return nativeSuggestSelection(this.annotatorPtr, str, i, i2, selectionOptions);
    }

    public ClassificationResult[] classifyText(String str, int i, int i2, ClassificationOptions classificationOptions) {
        return classifyText(str, i, i2, classificationOptions, null, null);
    }

    public ClassificationResult[] classifyText(String str, int i, int i2, ClassificationOptions classificationOptions, Object obj, String str2) {
        return nativeClassifyText(this.annotatorPtr, str, i, i2, classificationOptions, obj, str2);
    }

    public AnnotatedSpan[] annotate(String str, AnnotationOptions annotationOptions) {
        return nativeAnnotate(this.annotatorPtr, str, annotationOptions);
    }

    public AnnotatedSpan[][] annotateStructuredInput(InputFragment[] inputFragmentArr, AnnotationOptions annotationOptions) {
        return nativeAnnotateStructuredInput(this.annotatorPtr, inputFragmentArr, annotationOptions);
    }

    public byte[] lookUpKnowledgeEntity(String str) {
        return nativeLookUpKnowledgeEntity(this.annotatorPtr, str);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.isClosed.compareAndSet(false, true)) {
            nativeCloseAnnotator(this.annotatorPtr);
            this.annotatorPtr = 0L;
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public static String getLocales(int i) {
        return nativeGetLocales(i);
    }

    public static int getVersion(int i) {
        return nativeGetVersion(i);
    }

    public static String getName(int i) {
        return nativeGetName(i);
    }

    public static final class DatetimeResult {
        public static final int GRANULARITY_DAY = 3;
        public static final int GRANULARITY_HOUR = 4;
        public static final int GRANULARITY_MINUTE = 5;
        public static final int GRANULARITY_MONTH = 1;
        public static final int GRANULARITY_SECOND = 6;
        public static final int GRANULARITY_WEEK = 2;
        public static final int GRANULARITY_YEAR = 0;
        private final int granularity;
        private final long timeMsUtc;

        public DatetimeResult(long j, int i) {
            this.timeMsUtc = j;
            this.granularity = i;
        }

        public long getTimeMsUtc() {
            return this.timeMsUtc;
        }

        public int getGranularity() {
            return this.granularity;
        }
    }

    public static final class ClassificationResult {
        private final String appName;
        private final String appPackageName;
        private final String collection;
        private final String contactEmailAddress;
        private final String contactFamilyName;
        private final String contactGivenName;
        private final String contactId;
        private final String contactName;
        private final String contactNickname;
        private final String contactPhoneNumber;
        private final DatetimeResult datetimeResult;
        private final long durationMs;
        private final NamedVariant[] entityData;
        private final double numericDoubleValue;
        private final long numericValue;
        private final RemoteActionTemplate[] remoteActionTemplates;
        private final float score;
        private final byte[] serializedEntityData;
        private final byte[] serializedKnowledgeResult;

        public ClassificationResult(String str, float f, DatetimeResult datetimeResult, byte[] bArr, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, NamedVariant[] namedVariantArr, byte[] bArr2, RemoteActionTemplate[] remoteActionTemplateArr, long j, long j2, double d) {
            this.collection = str;
            this.score = f;
            this.datetimeResult = datetimeResult;
            this.serializedKnowledgeResult = bArr;
            this.contactName = str2;
            this.contactGivenName = str3;
            this.contactFamilyName = str4;
            this.contactNickname = str5;
            this.contactEmailAddress = str6;
            this.contactPhoneNumber = str7;
            this.contactId = str8;
            this.appName = str9;
            this.appPackageName = str10;
            this.entityData = namedVariantArr;
            this.serializedEntityData = bArr2;
            this.remoteActionTemplates = remoteActionTemplateArr;
            this.durationMs = j;
            this.numericValue = j2;
            this.numericDoubleValue = d;
        }

        public String getCollection() {
            return this.collection;
        }

        public float getScore() {
            return this.score;
        }

        public DatetimeResult getDatetimeResult() {
            return this.datetimeResult;
        }

        public byte[] getSerializedKnowledgeResult() {
            return this.serializedKnowledgeResult;
        }

        public String getContactName() {
            return this.contactName;
        }

        public String getContactGivenName() {
            return this.contactGivenName;
        }

        public String getContactFamilyName() {
            return this.contactFamilyName;
        }

        public String getContactNickname() {
            return this.contactNickname;
        }

        public String getContactEmailAddress() {
            return this.contactEmailAddress;
        }

        public String getContactPhoneNumber() {
            return this.contactPhoneNumber;
        }

        public String getContactId() {
            return this.contactId;
        }

        public String getAppName() {
            return this.appName;
        }

        public String getAppPackageName() {
            return this.appPackageName;
        }

        public NamedVariant[] getEntityData() {
            return this.entityData;
        }

        public byte[] getSerializedEntityData() {
            return this.serializedEntityData;
        }

        public RemoteActionTemplate[] getRemoteActionTemplates() {
            return this.remoteActionTemplates;
        }

        public long getDurationMs() {
            return this.durationMs;
        }

        public long getNumericValue() {
            return this.numericValue;
        }

        public double getNumericDoubleValue() {
            return this.numericDoubleValue;
        }
    }

    public static final class AnnotatedSpan {
        private final ClassificationResult[] classification;
        private final int endIndex;
        private final int startIndex;

        AnnotatedSpan(int i, int i2, ClassificationResult[] classificationResultArr) {
            this.startIndex = i;
            this.endIndex = i2;
            this.classification = classificationResultArr;
        }

        public int getStartIndex() {
            return this.startIndex;
        }

        public int getEndIndex() {
            return this.endIndex;
        }

        public ClassificationResult[] getClassification() {
            return this.classification;
        }
    }

    public static final class InputFragment {
        private final DatetimeOptions datetimeOptionsNullable;
        private final String text;

        public static final class DatetimeOptions {
            private final Long referenceTimeMsUtc;
            private final String referenceTimezone;

            public DatetimeOptions(String str, Long l) {
                this.referenceTimeMsUtc = l;
                this.referenceTimezone = str;
            }
        }

        public InputFragment(String str) {
            this.text = str;
            this.datetimeOptionsNullable = null;
        }

        public InputFragment(String str, DatetimeOptions datetimeOptions) {
            this.text = str;
            this.datetimeOptionsNullable = datetimeOptions;
        }

        public String getText() {
            return this.text;
        }

        public boolean hasDatetimeOptions() {
            return this.datetimeOptionsNullable != null;
        }

        public long getReferenceTimeMsUtc() {
            return this.datetimeOptionsNullable.referenceTimeMsUtc.longValue();
        }

        public String getReferenceTimezone() {
            return this.datetimeOptionsNullable.referenceTimezone;
        }
    }

    public static final class SelectionOptions {
        private final int annotationUsecase;
        private final String detectedTextLanguageTags;
        private final String locales;
        private final float userLocationAccuracyMeters;
        private final double userLocationLat;
        private final double userLocationLng;

        public SelectionOptions(String str, String str2, int i) {
            this.locales = str;
            this.detectedTextLanguageTags = str2;
            this.annotationUsecase = i;
            this.userLocationLat = 180.0d;
            this.userLocationLng = 360.0d;
            this.userLocationAccuracyMeters = AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS;
        }

        public SelectionOptions(String str, String str2) {
            this(str, str2, AnnotationUsecase.SMART.getValue());
        }

        public String getLocales() {
            return this.locales;
        }

        public String getDetectedTextLanguageTags() {
            return this.detectedTextLanguageTags;
        }

        public int getAnnotationUsecase() {
            return this.annotationUsecase;
        }

        public double getUserLocationLat() {
            return this.userLocationLat;
        }

        public double getUserLocationLng() {
            return this.userLocationLng;
        }

        public float getUserLocationAccuracyMeters() {
            return this.userLocationAccuracyMeters;
        }
    }

    public static final class ClassificationOptions {
        private final int annotationUsecase;
        private final String detectedTextLanguageTags;
        private final String locales;
        private final long referenceTimeMsUtc;
        private final String referenceTimezone;
        private final String userFamiliarLanguageTags;
        private final float userLocationAccuracyMeters;
        private final double userLocationLat;
        private final double userLocationLng;

        public ClassificationOptions(long j, String str, String str2, String str3, int i, String str4) {
            this.referenceTimeMsUtc = j;
            this.referenceTimezone = str;
            this.locales = str2;
            this.detectedTextLanguageTags = str3;
            this.annotationUsecase = i;
            this.userLocationLat = 180.0d;
            this.userLocationLng = 360.0d;
            this.userLocationAccuracyMeters = AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS;
            this.userFamiliarLanguageTags = str4;
        }

        public ClassificationOptions(long j, String str, String str2, String str3) {
            this(j, str, str2, str3, AnnotationUsecase.SMART.getValue(), AnnotatorModel.TYPE_UNKNOWN);
        }

        public long getReferenceTimeMsUtc() {
            return this.referenceTimeMsUtc;
        }

        public String getReferenceTimezone() {
            return this.referenceTimezone;
        }

        public String getLocale() {
            return this.locales;
        }

        public String getDetectedTextLanguageTags() {
            return this.detectedTextLanguageTags;
        }

        public int getAnnotationUsecase() {
            return this.annotationUsecase;
        }

        public double getUserLocationLat() {
            return this.userLocationLat;
        }

        public double getUserLocationLng() {
            return this.userLocationLng;
        }

        public float getUserLocationAccuracyMeters() {
            return this.userLocationAccuracyMeters;
        }

        public String getUserFamiliarLanguageTags() {
            return this.userFamiliarLanguageTags;
        }
    }

    public static final class AnnotationOptions {
        private final int annotationUsecase;
        private final String detectedTextLanguageTags;
        private final String[] entityTypes;
        private final boolean hasLocationPermission;
        private final boolean hasPersonalizationPermission;
        private final boolean isSerializedEntityDataEnabled;
        private final String locales;
        private final long referenceTimeMsUtc;
        private final String referenceTimezone;
        private final float userLocationAccuracyMeters;
        private final double userLocationLat;
        private final double userLocationLng;

        public AnnotationOptions(long j, String str, String str2, String str3, Collection<String> collection, int i, boolean z, boolean z2, boolean z3, double d, double d2, float f) {
            this.referenceTimeMsUtc = j;
            this.referenceTimezone = str;
            this.locales = str2;
            this.detectedTextLanguageTags = str3;
            String[] strArr = new String[0];
            this.entityTypes = collection != null ? (String[]) collection.toArray(strArr) : strArr;
            this.annotationUsecase = i;
            this.isSerializedEntityDataEnabled = z3;
            this.userLocationLat = d;
            this.userLocationLng = d2;
            this.userLocationAccuracyMeters = f;
            this.hasLocationPermission = z;
            this.hasPersonalizationPermission = z2;
        }

        public AnnotationOptions(long j, String str, String str2, String str3, Collection<String> collection, int i, boolean z, double d, double d2, float f) {
            this(j, str, str2, str3, collection, i, true, true, z, d, d2, f);
        }

        public AnnotationOptions(long j, String str, String str2, String str3, Collection<String> collection, int i, boolean z) {
            this(j, str, str2, str3, collection, i, z, 180.0d, 360.0d, AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS);
        }

        public AnnotationOptions(long j, String str, String str2, String str3) {
            this(j, str, str2, str3, null, AnnotationUsecase.SMART.getValue(), false);
        }

        public long getReferenceTimeMsUtc() {
            return this.referenceTimeMsUtc;
        }

        public String getReferenceTimezone() {
            return this.referenceTimezone;
        }

        public String getLocale() {
            return this.locales;
        }

        public String getDetectedTextLanguageTags() {
            return this.detectedTextLanguageTags;
        }

        public String[] getEntityTypes() {
            return this.entityTypes;
        }

        public int getAnnotationUsecase() {
            return this.annotationUsecase;
        }

        public boolean isSerializedEntityDataEnabled() {
            return this.isSerializedEntityDataEnabled;
        }

        public double getUserLocationLat() {
            return this.userLocationLat;
        }

        public double getUserLocationLng() {
            return this.userLocationLng;
        }

        public float getUserLocationAccuracyMeters() {
            return this.userLocationAccuracyMeters;
        }

        public boolean hasLocationPermission() {
            return this.hasLocationPermission;
        }

        public boolean hasPersonalizationPermission() {
            return this.hasPersonalizationPermission;
        }
    }

    long getNativeAnnotatorPointer() {
        return nativeGetNativeModelPtr(this.annotatorPtr);
    }
}
