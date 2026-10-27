package android.sysprop;

import android.media.AudioSystem;
import android.os.SystemProperties;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
public final class TelephonyProperties {
    private TelephonyProperties() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Boolean tryParseBoolean(java.lang.String r5) {
        /*
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r5 = r5.toLowerCase(r0)
            int r0 = r5.hashCode()
            r1 = 48
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L3f
            r1 = 49
            if (r0 == r1) goto L35
            r1 = 3569038(0x36758e, float:5.001287E-39)
            if (r0 == r1) goto L2a
            r1 = 97196323(0x5cb1923, float:1.9099262E-35)
            if (r0 == r1) goto L20
            goto L49
        L20:
            java.lang.String r0 = "false"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L49
            r5 = r2
            goto L4a
        L2a:
            java.lang.String r0 = "true"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L49
            r5 = r4
            goto L4a
        L35:
            java.lang.String r0 = "1"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L49
            r5 = 0
            goto L4a
        L3f:
            java.lang.String r0 = "0"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L49
            r5 = r3
            goto L4a
        L49:
            r5 = -1
        L4a:
            if (r5 == 0) goto L57
            if (r5 == r4) goto L57
            if (r5 == r3) goto L54
            if (r5 == r2) goto L54
            r5 = 0
            return r5
        L54:
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        L57:
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.sysprop.TelephonyProperties.tryParseBoolean(java.lang.String):java.lang.Boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer tryParseInteger(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static Long tryParseLong(String str) {
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static Double tryParseDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String tryParseString(String str) {
        if ("".equals(str)) {
            return null;
        }
        return str;
    }

    private static <T extends Enum<T>> T tryParseEnum(Class<T> cls, String str) {
        try {
            return (T) Enum.valueOf(cls, str.toUpperCase(Locale.US));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static <T> List<T> tryParseList(Function<String, T> function, String str) {
        if ("".equals(str)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            StringBuilder sb = new StringBuilder();
            while (i < str.length() && str.charAt(i) != ',') {
                if (str.charAt(i) == '\\') {
                    i++;
                }
                if (i == str.length()) {
                    break;
                }
                sb.append(str.charAt(i));
                i++;
            }
            arrayList.add(function.apply(sb.toString()));
            if (i == str.length()) {
                return arrayList;
            }
            i++;
        }
    }

    private static <T extends Enum<T>> List<T> tryParseEnumList(Class<T> cls, String str) {
        if ("".equals(str)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(",")) {
            arrayList.add(tryParseEnum(cls, str2));
        }
        return arrayList;
    }

    private static String escape(String str) {
        return str.replaceAll("([\\\\,])", "\\\\$1");
    }

    private static <T> String formatList(List<T> list) {
        StringJoiner stringJoiner = new StringJoiner(",");
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T next = it.next();
            stringJoiner.add(next == null ? "" : escape(next.toString()));
        }
        return stringJoiner.toString();
    }

    private static <T extends Enum<T>> String formatEnumList(List<T> list, Function<T, String> function) {
        StringJoiner stringJoiner = new StringJoiner(",");
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T next = it.next();
            stringJoiner.add(next == null ? "" : function.apply(next));
        }
        return stringJoiner.toString();
    }

    public static Optional<Boolean> airplane_mode_on() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get("persist.radio.airplane_mode_on")));
    }

    public static void airplane_mode_on(Boolean bool) {
        String str;
        if (bool == null) {
            str = "";
        } else {
            str = bool.booleanValue() ? "1" : AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS;
        }
        SystemProperties.set("persist.radio.airplane_mode_on", str);
    }

    public static List<String> baseband_version() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$kemQbl44ndTqXdQVvnYppJuQboQ
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseString((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_BASEBAND_VERSION));
    }

    public static void baseband_version(List<String> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_BASEBAND_VERSION, list == null ? "" : formatList(list));
    }

    public static Optional<String> ril_impl() {
        return Optional.ofNullable(tryParseString(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_RIL_IMPL)));
    }

    public static List<String> operator_alpha() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$BfPaTA0e9gauJmR4vGNCDkGZ3uc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseString((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_ALPHA));
    }

    public static void operator_alpha(List<String> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_ALPHA, list == null ? "" : formatList(list));
    }

    public static List<String> operator_numeric() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$EV4LSOwY7Dsh1rJalZDLmnGJw5I
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseString((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_NUMERIC));
    }

    public static void operator_numeric(List<String> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_NUMERIC, list == null ? "" : formatList(list));
    }

    public static Optional<Boolean> operator_is_manual() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_ISMANUAL)));
    }

    public static List<Boolean> operator_is_roaming() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$UKEfAuJVPm5cKR_UnPj1L66mN34
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseBoolean((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_ISROAMING));
    }

    public static void operator_is_roaming(List<Boolean> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_ISROAMING, list == null ? "" : formatList(list));
    }

    public static List<String> operator_iso_country() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$yK9cdPdkKXdcfM9Ey52BIE31a5M
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseString((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_ISO_COUNTRY));
    }

    public static void operator_iso_country(List<String> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_ISO_COUNTRY, list == null ? "" : formatList(list));
    }

    public static Optional<String> lte_on_cdma_product_type() {
        return Optional.ofNullable(tryParseString(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_LTE_ON_CDMA_PRODUCT_TYPE)));
    }

    public static Optional<Integer> lte_on_cdma_device() {
        return Optional.ofNullable(tryParseInteger(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_LTE_ON_CDMA_DEVICE)));
    }

    public static List<Integer> current_active_phone() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$2V_2ZQoGHfOIfKo_A8Ss547oL-c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseInteger((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.CURRENT_ACTIVE_PHONE));
    }

    public static void current_active_phone(List<Integer> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.CURRENT_ACTIVE_PHONE, list == null ? "" : formatList(list));
    }

    public static List<String> sim_state() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$fdR0mRnJd3OymvjDc_MI1AHnMwc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseString((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_SIM_STATE));
    }

    public static void sim_state(List<String> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_SIM_STATE, list == null ? "" : formatList(list));
    }

    public static List<String> icc_operator_numeric() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$dc-CgjsF3BtDxLSSKL5bQ9ullG0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseString((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_ICC_OPERATOR_NUMERIC));
    }

    public static void icc_operator_numeric(List<String> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_ICC_OPERATOR_NUMERIC, list == null ? "" : formatList(list));
    }

    public static List<String> icc_operator_alpha() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$VtSZ_Uto4bMa49ncgAfdWewMFOU
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseString((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_ICC_OPERATOR_ALPHA));
    }

    public static void icc_operator_alpha(List<String> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_ICC_OPERATOR_ALPHA, list == null ? "" : formatList(list));
    }

    public static List<String> icc_operator_iso_country() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$JNTRmlscGaFlYo_3krOr_WWd2QI
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseString((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_ICC_OPERATOR_ISO_COUNTRY));
    }

    public static void icc_operator_iso_country(List<String> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_ICC_OPERATOR_ISO_COUNTRY, list == null ? "" : formatList(list));
    }

    public static List<String> data_network_type() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$0Zy6hglFVc-K9jiJWmuHmilIMkY
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseString((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_DATA_NETWORK_TYPE));
    }

    public static void data_network_type(List<String> list) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_DATA_NETWORK_TYPE, list == null ? "" : formatList(list));
    }

    public static Optional<Boolean> in_ecm_mode() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_INECM_MODE)));
    }

    public static void in_ecm_mode(Boolean bool) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_INECM_MODE, bool == null ? "" : bool.toString());
    }

    public static Optional<Long> ecm_exit_timer() {
        return Optional.ofNullable(tryParseLong(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_ECM_EXIT_TIMER)));
    }

    public static Optional<String> operator_idp_string() {
        return Optional.ofNullable(tryParseString(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_IDP_STRING)));
    }

    public static void operator_idp_string(String str) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_OPERATOR_IDP_STRING, str == null ? "" : str.toString());
    }

    public static List<String> otasp_num_schema() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$kCQNtMqtfi6MMlFLqtIufNXwOS8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseString((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_OTASP_NUM_SCHEMA));
    }

    public static Optional<Boolean> disable_call() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_DISABLE_CALL)));
    }

    public static Optional<Boolean> ril_sends_multiple_call_ring() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_RIL_SENDS_MULTIPLE_CALL_RING)));
    }

    public static Optional<Integer> call_ring_delay() {
        return Optional.ofNullable(tryParseInteger(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_CALL_RING_DELAY)));
    }

    public static Optional<Integer> cdma_msg_id() {
        return Optional.ofNullable(tryParseInteger(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_CDMA_MSG_ID)));
    }

    public static void cdma_msg_id(Integer num) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_CDMA_MSG_ID, num == null ? "" : num.toString());
    }

    public static Optional<Integer> wake_lock_timeout() {
        return Optional.ofNullable(tryParseInteger(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_WAKE_LOCK_TIMEOUT)));
    }

    public static Optional<Boolean> reset_on_radio_tech_change() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_RESET_ON_RADIO_TECH_CHANGE)));
    }

    public static List<Boolean> sms_receive() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$iJa3afMQmWbO1DX4jS9zkcOKZlY
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseBoolean((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_SMS_RECEIVE));
    }

    public static List<Boolean> sms_send() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$rKoNB08X7R8OCPq-VDCWDOm3lDM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseBoolean((String) obj);
            }
        }, SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_SMS_SEND));
    }

    public static Optional<Boolean> test_csim() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_TEST_CSIM)));
    }

    public static Optional<Boolean> ignore_nitz() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_IGNORE_NITZ)));
    }

    public static Optional<String> multi_sim_config() {
        return Optional.ofNullable(tryParseString(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_MULTI_SIM_CONFIG)));
    }

    public static void multi_sim_config(String str) {
        SystemProperties.set(com.android.internal.telephony.TelephonyProperties.PROPERTY_MULTI_SIM_CONFIG, str == null ? "" : str.toString());
    }

    public static Optional<Boolean> reboot_on_modem_change() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_REBOOT_REQUIRED_ON_MODEM_CHANGE)));
    }

    public static Optional<Integer> videocall_audio_output() {
        return Optional.ofNullable(tryParseInteger(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_VIDEOCALL_AUDIO_OUTPUT)));
    }

    public static Optional<Boolean> enable_esim_ui_by_default() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get("esim.enable_esim_system_ui_by_default")));
    }

    public static List<Integer> default_network() {
        return tryParseList(new Function() { // from class: android.sysprop.-$$Lambda$TelephonyProperties$H4jN0VIBNpZQBeWYt6qS3DCe_M8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseInteger((String) obj);
            }
        }, SystemProperties.get("ro.telephony.default_network"));
    }

    public static Optional<Boolean> data_roaming() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get("ro.com.android.dataroaming")));
    }

    public static Optional<Boolean> mobile_data() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get("ro.com.android.mobiledata")));
    }

    public static Optional<Integer> wps_info() {
        return Optional.ofNullable(tryParseInteger(SystemProperties.get("wifidirect.wps")));
    }

    public static Optional<Integer> max_active_modems() {
        return Optional.ofNullable(tryParseInteger(SystemProperties.get(com.android.internal.telephony.TelephonyProperties.PROPERTY_MAX_ACTIVE_MODEMS)));
    }

    public static Optional<Integer> sim_slots_count() {
        return Optional.ofNullable(tryParseInteger(SystemProperties.get("ro.telephony.sim_slots.count")));
    }
}
