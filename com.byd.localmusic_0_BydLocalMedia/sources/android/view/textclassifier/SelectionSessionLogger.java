package android.view.textclassifier;

/* JADX INFO: loaded from: classes3.dex */
public final class SelectionSessionLogger {
    private static final String CLASSIFIER_ID = "androidtc";

    static boolean isPlatformLocalTextClassifierSmartSelection(String str) {
        return "androidtc".equals(SignatureParser.getClassifierId(str));
    }

    public static final class SignatureParser {
        static String getClassifierId(String str) {
            int iIndexOf;
            return (str != null && (iIndexOf = str.indexOf("|")) >= 0) ? str.substring(0, iIndexOf) : "";
        }
    }
}
