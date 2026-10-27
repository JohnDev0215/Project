package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;
import com.google.i18n.phonenumbers.internal.RegexCache;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class PhoneNumberUtil {

    /* JADX INFO: renamed from: a */
    private static final Logger f4121a = Logger.getLogger(PhoneNumberUtil.class.getName());

    /* JADX INFO: renamed from: b */
    private static final Map<Integer, String> f4122b;

    /* JADX INFO: renamed from: c */
    private static final Set<Integer> f4123c;

    /* JADX INFO: renamed from: d */
    private static final Set<Integer> f4124d;

    /* JADX INFO: renamed from: e */
    private static final Map<Character, Character> f4125e;
    private static final Map<Character, Character> f;
    private static final Map<Character, Character> g;
    private static final Map<Character, Character> h;
    private static final Pattern i;
    private static final String j;
    static final Pattern k;
    private static final Pattern l;
    private static final Pattern m;
    private static final Pattern n;
    static final Pattern o;
    static final Pattern p;
    private static final Pattern q;
    private static final String r;
    private static final String s;
    static final String t;
    private static final Pattern u;
    private static final Pattern v;
    static final Pattern w;
    private static final Pattern x;
    private static final Pattern y;
    private static PhoneNumberUtil z;
    private final e A;
    private final Map<Integer, List<String>> B;
    private final com.google.i18n.phonenumbers.internal.a C = com.google.i18n.phonenumbers.internal.b.b();
    private final Set<String> D = new HashSet(35);
    private final RegexCache E = new RegexCache(100);
    private final Set<String> F = new HashSet(320);
    private final Set<Integer> G = new HashSet();

    public enum PhoneNumberFormat {
        E164,
        INTERNATIONAL,
        NATIONAL,
        RFC3966
    }

    public enum PhoneNumberType {
        FIXED_LINE,
        MOBILE,
        FIXED_LINE_OR_MOBILE,
        TOLL_FREE,
        PREMIUM_RATE,
        SHARED_COST,
        VOIP,
        PERSONAL_NUMBER,
        PAGER,
        UAN,
        VOICEMAIL,
        UNKNOWN
    }

    public enum ValidationResult {
        IS_POSSIBLE,
        IS_POSSIBLE_LOCAL_ONLY,
        INVALID_COUNTRY_CODE,
        TOO_SHORT,
        INVALID_LENGTH,
        TOO_LONG
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f4129a;

        /* JADX INFO: renamed from: b */
        static final /* synthetic */ int[] f4130b;

        /* JADX INFO: renamed from: c */
        static final /* synthetic */ int[] f4131c;

        static {
            int[] iArr = new int[PhoneNumberType.values().length];
            f4131c = iArr;
            try {
                iArr[PhoneNumberType.PREMIUM_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4131c[PhoneNumberType.TOLL_FREE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4131c[PhoneNumberType.MOBILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4131c[PhoneNumberType.FIXED_LINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4131c[PhoneNumberType.FIXED_LINE_OR_MOBILE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4131c[PhoneNumberType.SHARED_COST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4131c[PhoneNumberType.VOIP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4131c[PhoneNumberType.PERSONAL_NUMBER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f4131c[PhoneNumberType.PAGER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f4131c[PhoneNumberType.UAN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f4131c[PhoneNumberType.VOICEMAIL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr2 = new int[PhoneNumberFormat.values().length];
            f4130b = iArr2;
            try {
                iArr2[PhoneNumberFormat.E164.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f4130b[PhoneNumberFormat.INTERNATIONAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f4130b[PhoneNumberFormat.RFC3966.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f4130b[PhoneNumberFormat.NATIONAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused15) {
            }
            int[] iArr3 = new int[Phonenumber$PhoneNumber.CountryCodeSource.values().length];
            f4129a = iArr3;
            try {
                iArr3[Phonenumber$PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f4129a[Phonenumber$PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_IDD.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f4129a[Phonenumber$PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f4129a[Phonenumber$PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    static {
        HashMap map = new HashMap();
        map.put(52, "1");
        map.put(54, "9");
        f4122b = Collections.unmodifiableMap(map);
        HashSet hashSet = new HashSet();
        hashSet.add(86);
        f4123c = Collections.unmodifiableSet(hashSet);
        HashSet hashSet2 = new HashSet();
        hashSet2.add(52);
        hashSet2.add(54);
        hashSet2.add(55);
        hashSet2.add(62);
        hashSet2.addAll(hashSet);
        f4124d = Collections.unmodifiableSet(hashSet2);
        HashMap map2 = new HashMap();
        map2.put('0', '0');
        map2.put('1', '1');
        map2.put('2', '2');
        map2.put('3', '3');
        map2.put('4', '4');
        map2.put('5', '5');
        map2.put('6', '6');
        map2.put('7', '7');
        map2.put('8', '8');
        map2.put('9', '9');
        HashMap map3 = new HashMap(40);
        map3.put('A', '2');
        map3.put('B', '2');
        map3.put('C', '2');
        map3.put('D', '3');
        map3.put('E', '3');
        map3.put('F', '3');
        map3.put('G', '4');
        map3.put('H', '4');
        map3.put('I', '4');
        map3.put('J', '5');
        map3.put('K', '5');
        map3.put('L', '5');
        map3.put('M', '6');
        map3.put('N', '6');
        map3.put('O', '6');
        map3.put('P', '7');
        map3.put('Q', '7');
        map3.put('R', '7');
        map3.put('S', '7');
        map3.put('T', '8');
        map3.put('U', '8');
        map3.put('V', '8');
        map3.put('W', '9');
        map3.put('X', '9');
        map3.put('Y', '9');
        map3.put('Z', '9');
        Map<Character, Character> mapUnmodifiableMap = Collections.unmodifiableMap(map3);
        f = mapUnmodifiableMap;
        HashMap map4 = new HashMap(100);
        map4.putAll(mapUnmodifiableMap);
        map4.putAll(map2);
        g = Collections.unmodifiableMap(map4);
        HashMap map5 = new HashMap();
        map5.putAll(map2);
        map5.put('+', '+');
        map5.put('*', '*');
        map5.put('#', '#');
        f4125e = Collections.unmodifiableMap(map5);
        HashMap map6 = new HashMap();
        Iterator<Character> it = mapUnmodifiableMap.keySet().iterator();
        while (it.hasNext()) {
            char cCharValue = it.next().charValue();
            map6.put(Character.valueOf(Character.toLowerCase(cCharValue)), Character.valueOf(cCharValue));
            map6.put(Character.valueOf(cCharValue), Character.valueOf(cCharValue));
        }
        map6.putAll(map2);
        map6.put('-', '-');
        map6.put((char) 65293, '-');
        map6.put((char) 8208, '-');
        map6.put((char) 8209, '-');
        map6.put((char) 8210, '-');
        map6.put((char) 8211, '-');
        map6.put((char) 8212, '-');
        map6.put((char) 8213, '-');
        map6.put((char) 8722, '-');
        map6.put('/', '/');
        map6.put((char) 65295, '/');
        map6.put(' ', ' ');
        map6.put((char) 12288, ' ');
        map6.put((char) 8288, ' ');
        map6.put('.', '.');
        map6.put((char) 65294, '.');
        h = Collections.unmodifiableMap(map6);
        i = Pattern.compile("[\\d]+(?:[~⁓∼～][\\d]+)?");
        StringBuilder sb = new StringBuilder();
        Map<Character, Character> map7 = f;
        sb.append(Arrays.toString(map7.keySet().toArray()).replaceAll("[, \\[\\]]", ""));
        sb.append(Arrays.toString(map7.keySet().toArray()).toLowerCase().replaceAll("[, \\[\\]]", ""));
        String string = sb.toString();
        j = string;
        k = Pattern.compile("[+＋]+");
        l = Pattern.compile("[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]+");
        m = Pattern.compile("(\\p{Nd})");
        n = Pattern.compile("[+＋\\p{Nd}]");
        o = Pattern.compile("[\\\\/] *x");
        p = Pattern.compile("[[\\P{N}&&\\P{L}]&&[^#]]+$");
        q = Pattern.compile("(?:.*?[A-Za-z]){3}.*");
        String str = "\\p{Nd}{2}|[+＋]*+(?:[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*]*\\p{Nd}){3,}[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*" + string + "\\p{Nd}]*";
        r = str;
        String strC = c(",;xｘ#＃~～");
        s = strC;
        t = c("xｘ#＃~～");
        u = Pattern.compile("(?:" + strC + ")$", 66);
        v = Pattern.compile(str + "(?:" + strC + ")?", 66);
        w = Pattern.compile("(\\D+)");
        x = Pattern.compile("(\\$\\d)");
        y = Pattern.compile("\\(?\\$1\\)?");
        z = null;
    }

    PhoneNumberUtil(e eVar, Map<Integer, List<String>> map) {
        this.A = eVar;
        this.B = map;
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            if (value.size() == 1 && "001".equals(value.get(0))) {
                this.G.add(entry.getKey());
            } else {
                this.F.addAll(value);
            }
        }
        if (this.F.remove("001")) {
            f4121a.log(Level.WARNING, "invalid metadata (country calling code was mapped to the non-geo entity as well as specific region(s))");
        }
        this.D.addAll(map.get(1));
    }

    static boolean A(CharSequence charSequence) {
        if (charSequence.length() < 2) {
            return false;
        }
        return v.matcher(charSequence).matches();
    }

    static StringBuilder F(StringBuilder sb) {
        if (q.matcher(sb).matches()) {
            sb.replace(0, sb.length(), I(sb, g, true));
        } else {
            sb.replace(0, sb.length(), H(sb));
        }
        return sb;
    }

    static StringBuilder G(CharSequence charSequence, boolean z2) {
        StringBuilder sb = new StringBuilder(charSequence.length());
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char cCharAt = charSequence.charAt(i2);
            int iDigit = Character.digit(cCharAt, 10);
            if (iDigit != -1) {
                sb.append(iDigit);
            } else if (z2) {
                sb.append(cCharAt);
            }
        }
        return sb;
    }

    public static String H(CharSequence charSequence) {
        return G(charSequence, false).toString();
    }

    private static String I(CharSequence charSequence, Map<Character, Character> map, boolean z2) {
        StringBuilder sb = new StringBuilder(charSequence.length());
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char cCharAt = charSequence.charAt(i2);
            Character ch = map.get(Character.valueOf(Character.toUpperCase(cCharAt)));
            if (ch != null) {
                sb.append(ch);
            } else if (!z2) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    private void L(CharSequence charSequence, String str, boolean z2, boolean z3, Phonenumber$PhoneNumber phonenumber$PhoneNumber) throws NumberParseException {
        int iB;
        if (charSequence == null) {
            throw new NumberParseException(NumberParseException.ErrorType.NOT_A_NUMBER, "The phone number supplied was null.");
        }
        if (charSequence.length() > 250) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_LONG, "The string supplied was too long to parse.");
        }
        StringBuilder sb = new StringBuilder();
        String string = charSequence.toString();
        a(string, sb);
        if (!A(sb)) {
            throw new NumberParseException(NumberParseException.ErrorType.NOT_A_NUMBER, "The string supplied did not seem to be a phone number.");
        }
        if (z3 && !b(sb, str)) {
            throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Missing or invalid default region.");
        }
        if (z2) {
            phonenumber$PhoneNumber.setRawInput(string);
        }
        String strC = C(sb);
        if (strC.length() > 0) {
            phonenumber$PhoneNumber.setExtension(strC);
        }
        Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadataM = m(str);
        StringBuilder sb2 = new StringBuilder();
        try {
            iB = B(sb, phonemetadata$PhoneMetadataM, sb2, z2, phonenumber$PhoneNumber);
        } catch (NumberParseException e2) {
            Matcher matcher = k.matcher(sb);
            if (e2.getErrorType() != NumberParseException.ErrorType.INVALID_COUNTRY_CODE || !matcher.lookingAt()) {
                throw new NumberParseException(e2.getErrorType(), e2.getMessage());
            }
            iB = B(sb.substring(matcher.end()), phonemetadata$PhoneMetadataM, sb2, z2, phonenumber$PhoneNumber);
            if (iB == 0) {
                throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Could not interpret numbers after plus-sign.");
            }
        }
        if (iB != 0) {
            String strS = s(iB);
            if (!strS.equals(str)) {
                phonemetadata$PhoneMetadataM = n(iB, strS);
            }
        } else {
            sb2.append((CharSequence) F(sb));
            if (str != null) {
                phonenumber$PhoneNumber.setCountryCode(phonemetadata$PhoneMetadataM.getCountryCode());
            } else if (z2) {
                phonenumber$PhoneNumber.clearCountryCodeSource();
            }
        }
        if (sb2.length() < 2) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
        }
        if (phonemetadata$PhoneMetadataM != null) {
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder(sb2);
            E(sb4, phonemetadata$PhoneMetadataM, sb3);
            ValidationResult validationResultP = P(sb4, phonemetadata$PhoneMetadataM);
            if (validationResultP != ValidationResult.TOO_SHORT && validationResultP != ValidationResult.IS_POSSIBLE_LOCAL_ONLY && validationResultP != ValidationResult.INVALID_LENGTH) {
                if (z2 && sb3.length() > 0) {
                    phonenumber$PhoneNumber.setPreferredDomesticCarrierCode(sb3.toString());
                }
                sb2 = sb4;
            }
        }
        int length = sb2.length();
        if (length < 2) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
        }
        if (length > 17) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_LONG, "The string supplied is too long to be a phone number.");
        }
        O(sb2, phonenumber$PhoneNumber);
        phonenumber$PhoneNumber.setNationalNumber(Long.parseLong(sb2.toString()));
    }

    private boolean M(Pattern pattern, StringBuilder sb) {
        Matcher matcher = pattern.matcher(sb);
        if (!matcher.lookingAt()) {
            return false;
        }
        int iEnd = matcher.end();
        Matcher matcher2 = m.matcher(sb.substring(iEnd));
        if (matcher2.find() && H(matcher2.group(1)).equals("0")) {
            return false;
        }
        sb.delete(0, iEnd);
        return true;
    }

    static synchronized void N(PhoneNumberUtil phoneNumberUtil) {
        z = phoneNumberUtil;
    }

    static void O(CharSequence charSequence, Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        if (charSequence.length() <= 1 || charSequence.charAt(0) != '0') {
            return;
        }
        phonenumber$PhoneNumber.setItalianLeadingZero(true);
        int i2 = 1;
        while (i2 < charSequence.length() - 1 && charSequence.charAt(i2) == '0') {
            i2++;
        }
        if (i2 != 1) {
            phonenumber$PhoneNumber.setNumberOfLeadingZeros(i2);
        }
    }

    private ValidationResult P(CharSequence charSequence, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata) {
        return Q(charSequence, phonemetadata$PhoneMetadata, PhoneNumberType.UNKNOWN);
    }

    private ValidationResult Q(CharSequence charSequence, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata, PhoneNumberType phoneNumberType) {
        Phonemetadata$PhoneNumberDesc phonemetadata$PhoneNumberDescP = p(phonemetadata$PhoneMetadata, phoneNumberType);
        List<Integer> possibleLengthList = phonemetadata$PhoneNumberDescP.getPossibleLengthList().isEmpty() ? phonemetadata$PhoneMetadata.getGeneralDesc().getPossibleLengthList() : phonemetadata$PhoneNumberDescP.getPossibleLengthList();
        List<Integer> possibleLengthLocalOnlyList = phonemetadata$PhoneNumberDescP.getPossibleLengthLocalOnlyList();
        if (phoneNumberType == PhoneNumberType.FIXED_LINE_OR_MOBILE) {
            if (!f(p(phonemetadata$PhoneMetadata, PhoneNumberType.FIXED_LINE))) {
                return Q(charSequence, phonemetadata$PhoneMetadata, PhoneNumberType.MOBILE);
            }
            Phonemetadata$PhoneNumberDesc phonemetadata$PhoneNumberDescP2 = p(phonemetadata$PhoneMetadata, PhoneNumberType.MOBILE);
            if (f(phonemetadata$PhoneNumberDescP2)) {
                ArrayList arrayList = new ArrayList(possibleLengthList);
                arrayList.addAll(phonemetadata$PhoneNumberDescP2.getPossibleLengthList().size() == 0 ? phonemetadata$PhoneMetadata.getGeneralDesc().getPossibleLengthList() : phonemetadata$PhoneNumberDescP2.getPossibleLengthList());
                Collections.sort(arrayList);
                if (possibleLengthLocalOnlyList.isEmpty()) {
                    possibleLengthLocalOnlyList = phonemetadata$PhoneNumberDescP2.getPossibleLengthLocalOnlyList();
                } else {
                    ArrayList arrayList2 = new ArrayList(possibleLengthLocalOnlyList);
                    arrayList2.addAll(phonemetadata$PhoneNumberDescP2.getPossibleLengthLocalOnlyList());
                    Collections.sort(arrayList2);
                    possibleLengthLocalOnlyList = arrayList2;
                }
                possibleLengthList = arrayList;
            }
        }
        if (possibleLengthList.get(0).intValue() == -1) {
            return ValidationResult.INVALID_LENGTH;
        }
        int length = charSequence.length();
        if (possibleLengthLocalOnlyList.contains(Integer.valueOf(length))) {
            return ValidationResult.IS_POSSIBLE_LOCAL_ONLY;
        }
        int iIntValue = possibleLengthList.get(0).intValue();
        return iIntValue == length ? ValidationResult.IS_POSSIBLE : iIntValue > length ? ValidationResult.TOO_SHORT : possibleLengthList.get(possibleLengthList.size() - 1).intValue() < length ? ValidationResult.TOO_LONG : possibleLengthList.subList(1, possibleLengthList.size()).contains(Integer.valueOf(length)) ? ValidationResult.IS_POSSIBLE : ValidationResult.INVALID_LENGTH;
    }

    private void a(String str, StringBuilder sb) {
        int iIndexOf = str.indexOf(";phone-context=");
        if (iIndexOf >= 0) {
            int i2 = iIndexOf + 15;
            if (i2 < str.length() - 1 && str.charAt(i2) == '+') {
                int iIndexOf2 = str.indexOf(59, i2);
                if (iIndexOf2 > 0) {
                    sb.append(str.substring(i2, iIndexOf2));
                } else {
                    sb.append(str.substring(i2));
                }
            }
            int iIndexOf3 = str.indexOf("tel:");
            sb.append(str.substring(iIndexOf3 >= 0 ? iIndexOf3 + 4 : 0, iIndexOf));
        } else {
            sb.append(h(str));
        }
        int iIndexOf4 = sb.indexOf(";isub=");
        if (iIndexOf4 > 0) {
            sb.delete(iIndexOf4, sb.length());
        }
    }

    private boolean b(CharSequence charSequence, String str) {
        if (z(str)) {
            return true;
        }
        return (charSequence == null || charSequence.length() == 0 || !k.matcher(charSequence).lookingAt()) ? false : true;
    }

    private static String c(String str) {
        return ";ext=(\\p{Nd}{1,7})|[  \\t,]*(?:e?xt(?:ensi(?:ó?|ó))?n?|ｅ?ｘｔｎ?|доб|[" + str + "]|int|anexo|ｉｎｔ)[:\\.．]?[  \\t,-]*(\\p{Nd}{1,7})#?|[- ]+(\\p{Nd}{1,5})#";
    }

    public static PhoneNumberUtil d(c cVar) {
        if (cVar != null) {
            return e(new f(cVar));
        }
        throw new IllegalArgumentException("metadataLoader could not be null.");
    }

    private static PhoneNumberUtil e(e eVar) {
        if (eVar != null) {
            return new PhoneNumberUtil(eVar, b.a());
        }
        throw new IllegalArgumentException("metadataSource could not be null.");
    }

    private static boolean f(Phonemetadata$PhoneNumberDesc phonemetadata$PhoneNumberDesc) {
        return (phonemetadata$PhoneNumberDesc.getPossibleLengthCount() == 1 && phonemetadata$PhoneNumberDesc.getPossibleLength(0) == -1) ? false : true;
    }

    static CharSequence h(CharSequence charSequence) {
        Matcher matcher = n.matcher(charSequence);
        if (!matcher.find()) {
            return "";
        }
        CharSequence charSequenceSubSequence = charSequence.subSequence(matcher.start(), charSequence.length());
        Matcher matcher2 = p.matcher(charSequenceSubSequence);
        if (matcher2.find()) {
            charSequenceSubSequence = charSequenceSubSequence.subSequence(0, matcher2.start());
        }
        Matcher matcher3 = o.matcher(charSequenceSubSequence);
        return matcher3.find() ? charSequenceSubSequence.subSequence(0, matcher3.start()) : charSequenceSubSequence;
    }

    private int i(String str) {
        Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadataM = m(str);
        if (phonemetadata$PhoneMetadataM != null) {
            return phonemetadata$PhoneMetadataM.getCountryCode();
        }
        throw new IllegalArgumentException("Invalid region code: " + str);
    }

    public static String j(int i2) {
        Map<Integer, String> map = f4122b;
        return map.containsKey(Integer.valueOf(i2)) ? map.get(Integer.valueOf(i2)) : "";
    }

    public static synchronized PhoneNumberUtil k() {
        if (z == null) {
            N(d(d.f4133a));
        }
        return z;
    }

    private Phonemetadata$PhoneMetadata n(int i2, String str) {
        return "001".equals(str) ? l(i2) : m(str);
    }

    private PhoneNumberType r(String str, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata) {
        return !x(str, phonemetadata$PhoneMetadata.getGeneralDesc()) ? PhoneNumberType.UNKNOWN : x(str, phonemetadata$PhoneMetadata.getPremiumRate()) ? PhoneNumberType.PREMIUM_RATE : x(str, phonemetadata$PhoneMetadata.getTollFree()) ? PhoneNumberType.TOLL_FREE : x(str, phonemetadata$PhoneMetadata.getSharedCost()) ? PhoneNumberType.SHARED_COST : x(str, phonemetadata$PhoneMetadata.getVoip()) ? PhoneNumberType.VOIP : x(str, phonemetadata$PhoneMetadata.getPersonalNumber()) ? PhoneNumberType.PERSONAL_NUMBER : x(str, phonemetadata$PhoneMetadata.getPager()) ? PhoneNumberType.PAGER : x(str, phonemetadata$PhoneMetadata.getUan()) ? PhoneNumberType.UAN : x(str, phonemetadata$PhoneMetadata.getVoicemail()) ? PhoneNumberType.VOICEMAIL : x(str, phonemetadata$PhoneMetadata.getFixedLine()) ? phonemetadata$PhoneMetadata.getSameMobileAndFixedLinePattern() ? PhoneNumberType.FIXED_LINE_OR_MOBILE : x(str, phonemetadata$PhoneMetadata.getMobile()) ? PhoneNumberType.FIXED_LINE_OR_MOBILE : PhoneNumberType.FIXED_LINE : (phonemetadata$PhoneMetadata.getSameMobileAndFixedLinePattern() || !x(str, phonemetadata$PhoneMetadata.getMobile())) ? PhoneNumberType.UNKNOWN : PhoneNumberType.MOBILE;
    }

    private String u(Phonenumber$PhoneNumber phonenumber$PhoneNumber, List<String> list) {
        String strO = o(phonenumber$PhoneNumber);
        for (String str : list) {
            Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadataM = m(str);
            if (phonemetadata$PhoneMetadataM.hasLeadingDigits()) {
                if (this.E.a(phonemetadata$PhoneMetadataM.getLeadingDigits()).matcher(strO).lookingAt()) {
                    return str;
                }
            } else if (r(strO, phonemetadata$PhoneMetadataM) != PhoneNumberType.UNKNOWN) {
                return str;
            }
        }
        return null;
    }

    private boolean z(String str) {
        return str != null && this.F.contains(str);
    }

    int B(CharSequence charSequence, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata, StringBuilder sb, boolean z2, Phonenumber$PhoneNumber phonenumber$PhoneNumber) throws NumberParseException {
        if (charSequence.length() == 0) {
            return 0;
        }
        StringBuilder sb2 = new StringBuilder(charSequence);
        Phonenumber$PhoneNumber.CountryCodeSource countryCodeSourceD = D(sb2, phonemetadata$PhoneMetadata != null ? phonemetadata$PhoneMetadata.getInternationalPrefix() : "NonMatch");
        if (z2) {
            phonenumber$PhoneNumber.setCountryCodeSource(countryCodeSourceD);
        }
        if (countryCodeSourceD != Phonenumber$PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            if (sb2.length() <= 2) {
                throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_AFTER_IDD, "Phone number had an IDD, but after this was not long enough to be a viable phone number.");
            }
            int iG = g(sb2, sb);
            if (iG == 0) {
                throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Country calling code supplied was not recognised.");
            }
            phonenumber$PhoneNumber.setCountryCode(iG);
            return iG;
        }
        if (phonemetadata$PhoneMetadata != null) {
            int countryCode = phonemetadata$PhoneMetadata.getCountryCode();
            String strValueOf = String.valueOf(countryCode);
            String string = sb2.toString();
            if (string.startsWith(strValueOf)) {
                StringBuilder sb3 = new StringBuilder(string.substring(strValueOf.length()));
                Phonemetadata$PhoneNumberDesc generalDesc = phonemetadata$PhoneMetadata.getGeneralDesc();
                E(sb3, phonemetadata$PhoneMetadata, null);
                if ((!this.C.a(sb2, generalDesc, false) && this.C.a(sb3, generalDesc, false)) || P(sb2, phonemetadata$PhoneMetadata) == ValidationResult.TOO_LONG) {
                    sb.append((CharSequence) sb3);
                    if (z2) {
                        phonenumber$PhoneNumber.setCountryCodeSource(Phonenumber$PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN);
                    }
                    phonenumber$PhoneNumber.setCountryCode(countryCode);
                    return countryCode;
                }
            }
        }
        phonenumber$PhoneNumber.setCountryCode(0);
        return 0;
    }

    String C(StringBuilder sb) {
        Matcher matcher = u.matcher(sb);
        if (!matcher.find() || !A(sb.substring(0, matcher.start()))) {
            return "";
        }
        int iGroupCount = matcher.groupCount();
        for (int i2 = 1; i2 <= iGroupCount; i2++) {
            if (matcher.group(i2) != null) {
                String strGroup = matcher.group(i2);
                sb.delete(matcher.start(), sb.length());
                return strGroup;
            }
        }
        return "";
    }

    Phonenumber$PhoneNumber.CountryCodeSource D(StringBuilder sb, String str) {
        if (sb.length() == 0) {
            return Phonenumber$PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY;
        }
        Matcher matcher = k.matcher(sb);
        if (matcher.lookingAt()) {
            sb.delete(0, matcher.end());
            F(sb);
            return Phonenumber$PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN;
        }
        Pattern patternA = this.E.a(str);
        F(sb);
        return M(patternA, sb) ? Phonenumber$PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_IDD : Phonenumber$PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY;
    }

    boolean E(StringBuilder sb, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata, StringBuilder sb2) {
        int length = sb.length();
        String nationalPrefixForParsing = phonemetadata$PhoneMetadata.getNationalPrefixForParsing();
        if (length != 0 && nationalPrefixForParsing.length() != 0) {
            Matcher matcher = this.E.a(nationalPrefixForParsing).matcher(sb);
            if (matcher.lookingAt()) {
                Phonemetadata$PhoneNumberDesc generalDesc = phonemetadata$PhoneMetadata.getGeneralDesc();
                boolean zA = this.C.a(sb, generalDesc, false);
                int iGroupCount = matcher.groupCount();
                String nationalPrefixTransformRule = phonemetadata$PhoneMetadata.getNationalPrefixTransformRule();
                if (nationalPrefixTransformRule == null || nationalPrefixTransformRule.length() == 0 || matcher.group(iGroupCount) == null) {
                    if (zA && !this.C.a(sb.substring(matcher.end()), generalDesc, false)) {
                        return false;
                    }
                    if (sb2 != null && iGroupCount > 0 && matcher.group(iGroupCount) != null) {
                        sb2.append(matcher.group(1));
                    }
                    sb.delete(0, matcher.end());
                    return true;
                }
                StringBuilder sb3 = new StringBuilder(sb);
                sb3.replace(0, length, matcher.replaceFirst(nationalPrefixTransformRule));
                if (zA && !this.C.a(sb3.toString(), generalDesc, false)) {
                    return false;
                }
                if (sb2 != null && iGroupCount > 1) {
                    sb2.append(matcher.group(1));
                }
                sb.replace(0, sb.length(), sb3.toString());
                return true;
            }
        }
        return false;
    }

    public Phonenumber$PhoneNumber J(CharSequence charSequence, String str) throws NumberParseException {
        Phonenumber$PhoneNumber phonenumber$PhoneNumber = new Phonenumber$PhoneNumber();
        K(charSequence, str, phonenumber$PhoneNumber);
        return phonenumber$PhoneNumber;
    }

    public void K(CharSequence charSequence, String str, Phonenumber$PhoneNumber phonenumber$PhoneNumber) throws NumberParseException {
        L(charSequence, str, false, true, phonenumber$PhoneNumber);
    }

    int g(StringBuilder sb, StringBuilder sb2) {
        if (sb.length() != 0 && sb.charAt(0) != '0') {
            int length = sb.length();
            for (int i2 = 1; i2 <= 3 && i2 <= length; i2++) {
                int i3 = Integer.parseInt(sb.substring(0, i2));
                if (this.B.containsKey(Integer.valueOf(i3))) {
                    sb2.append(sb.substring(i2));
                    return i3;
                }
            }
        }
        return 0;
    }

    Phonemetadata$PhoneMetadata l(int i2) {
        if (this.B.containsKey(Integer.valueOf(i2))) {
            return this.A.a(i2);
        }
        return null;
    }

    Phonemetadata$PhoneMetadata m(String str) {
        if (z(str)) {
            return this.A.b(str);
        }
        return null;
    }

    public String o(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        StringBuilder sb = new StringBuilder();
        if (phonenumber$PhoneNumber.isItalianLeadingZero() && phonenumber$PhoneNumber.getNumberOfLeadingZeros() > 0) {
            char[] cArr = new char[phonenumber$PhoneNumber.getNumberOfLeadingZeros()];
            Arrays.fill(cArr, '0');
            sb.append(new String(cArr));
        }
        sb.append(phonenumber$PhoneNumber.getNationalNumber());
        return sb.toString();
    }

    Phonemetadata$PhoneNumberDesc p(Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata, PhoneNumberType phoneNumberType) {
        switch (a.f4131c[phoneNumberType.ordinal()]) {
            case 1:
                return phonemetadata$PhoneMetadata.getPremiumRate();
            case 2:
                return phonemetadata$PhoneMetadata.getTollFree();
            case 3:
                return phonemetadata$PhoneMetadata.getMobile();
            case 4:
            case 5:
                return phonemetadata$PhoneMetadata.getFixedLine();
            case 6:
                return phonemetadata$PhoneMetadata.getSharedCost();
            case 7:
                return phonemetadata$PhoneMetadata.getVoip();
            case 8:
                return phonemetadata$PhoneMetadata.getPersonalNumber();
            case 9:
                return phonemetadata$PhoneMetadata.getPager();
            case 10:
                return phonemetadata$PhoneMetadata.getUan();
            case 11:
                return phonemetadata$PhoneMetadata.getVoicemail();
            default:
                return phonemetadata$PhoneMetadata.getGeneralDesc();
        }
    }

    public PhoneNumberType q(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadataN = n(phonenumber$PhoneNumber.getCountryCode(), t(phonenumber$PhoneNumber));
        return phonemetadata$PhoneMetadataN == null ? PhoneNumberType.UNKNOWN : r(o(phonenumber$PhoneNumber), phonemetadata$PhoneMetadataN);
    }

    public String s(int i2) {
        List<String> list = this.B.get(Integer.valueOf(i2));
        return list == null ? "ZZ" : list.get(0);
    }

    public String t(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        int countryCode = phonenumber$PhoneNumber.getCountryCode();
        List<String> list = this.B.get(Integer.valueOf(countryCode));
        if (list != null) {
            return list.size() == 1 ? list.get(0) : u(phonenumber$PhoneNumber, list);
        }
        f4121a.log(Level.INFO, "Missing/invalid country_code (" + countryCode + ")");
        return null;
    }

    public List<String> v(int i2) {
        List<String> arrayList = this.B.get(Integer.valueOf(i2));
        if (arrayList == null) {
            arrayList = new ArrayList<>(0);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public boolean w(PhoneNumberType phoneNumberType, int i2) {
        return phoneNumberType == PhoneNumberType.FIXED_LINE || phoneNumberType == PhoneNumberType.FIXED_LINE_OR_MOBILE || (f4124d.contains(Integer.valueOf(i2)) && phoneNumberType == PhoneNumberType.MOBILE);
    }

    boolean x(String str, Phonemetadata$PhoneNumberDesc phonemetadata$PhoneNumberDesc) {
        int length = str.length();
        List<Integer> possibleLengthList = phonemetadata$PhoneNumberDesc.getPossibleLengthList();
        if (possibleLengthList.size() <= 0 || possibleLengthList.contains(Integer.valueOf(length))) {
            return this.C.a(str, phonemetadata$PhoneNumberDesc, false);
        }
        return false;
    }

    public boolean y(Phonenumber$PhoneNumber phonenumber$PhoneNumber, String str) {
        int countryCode = phonenumber$PhoneNumber.getCountryCode();
        Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadataN = n(countryCode, str);
        if (phonemetadata$PhoneMetadataN != null) {
            return ("001".equals(str) || countryCode == i(str)) && r(o(phonenumber$PhoneNumber), phonemetadata$PhoneMetadataN) != PhoneNumberType.UNKNOWN;
        }
        return false;
    }
}
