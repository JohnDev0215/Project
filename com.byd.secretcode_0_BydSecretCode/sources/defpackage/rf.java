package defpackage;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: CipherManager.java */
/* JADX INFO: loaded from: classes.dex */
public class rf {
    public static rf a;
    public static final int[] b = {-2, -1, 0, 1, 2};
    public String c;
    public List<String> d = new ArrayList();
    public Date e;

    public static rf k() {
        if (a == null) {
            synchronized (rf.class) {
                if (a == null) {
                    a = new rf();
                }
            }
        }
        return a;
    }

    public final String a(String str, int i) {
        if (d(str)) {
            return null;
        }
        String strSubstring = this.c.substring(0, 6);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.e);
        calendar.add(10, b[i]);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHH", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String str2 = simpleDateFormat.format(calendar.getTime());
        if (!d(strSubstring) && !d(str2) && strSubstring.length() == 6 && str2.length() == 6) {
            int[] iArr = new int[6];
            for (int i2 = 0; i2 < 6; i2++) {
                iArr[i2] = Integer.parseInt(String.valueOf(strSubstring.charAt(i2))) + Integer.parseInt(String.valueOf(str2.charAt(i2)));
            }
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int i3 = 0; i3 < 6; i3++) {
                sb.append(str.charAt(Integer.parseInt(String.valueOf(strSubstring.charAt(i3)))));
                sb2.append(str.charAt(iArr[i3]));
            }
            String strEncodeToString = Base64.getEncoder().encodeToString(f(sb.toString().getBytes(), sb2.toString().getBytes()));
            if (!d(strEncodeToString) && strEncodeToString.length() >= 6) {
                if (strEncodeToString.matches("^[a-zA-Z0-9]+$")) {
                    return strEncodeToString.substring(0, 6).toUpperCase();
                }
                StringBuilder sb3 = new StringBuilder();
                for (int i4 = 0; i4 < strEncodeToString.length(); i4++) {
                    if (Character.isDigit(strEncodeToString.charAt(i4)) || Character.isLetter(strEncodeToString.charAt(i4))) {
                        sb3.append(strEncodeToString.charAt(i4));
                        if (sb3.length() == 6) {
                            return sb3.toString().toUpperCase();
                        }
                    }
                }
                if (sb3.length() < 6) {
                    while (sb3.length() < 6) {
                        sb3.append("B");
                    }
                }
                return sb3.toString().toUpperCase();
            }
        }
        return null;
    }

    public final List<String> b() {
        List<String> listI = i();
        List<String> listG = g();
        ArrayList arrayList = new ArrayList();
        if (listI.isEmpty() || listG.isEmpty()) {
            return null;
        }
        for (int i = 0; i < listI.size(); i++) {
            String str = listI.get(i);
            String str2 = listG.get(i);
            if (d(str) || d(str2)) {
                return null;
            }
            try {
                arrayList.add(Base64.getEncoder().encodeToString(e(str, str2.getBytes())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public final List<String> c(String str) {
        List<String> listB;
        if (d(str) || !h(str) || str.length() != 6) {
            return null;
        }
        this.e = new Date();
        this.c = str;
        this.d.clear();
        if (this.d == null || (listB = b()) == null) {
            return null;
        }
        for (int i = 0; i < listB.size(); i++) {
            this.d.add(a(listB.get(i), i));
        }
        return this.d;
    }

    public final boolean d(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public final byte[] e(String str, byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, new IvParameterSpec("1995020213467928".getBytes()));
        return cipher.doFinal(bArr);
    }

    public final byte[] f(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    public final List<String> g() {
        ArrayList arrayList = new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyddMMHH", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        Calendar calendar = Calendar.getInstance();
        int i = 0;
        while (true) {
            int[] iArr = b;
            if (i >= iArr.length) {
                return arrayList;
            }
            calendar.setTime(this.e);
            calendar.add(10, iArr[i]);
            arrayList.add(simpleDateFormat.format(calendar.getTime()) + this.c);
            i++;
        }
    }

    public final boolean h(String str) {
        return Pattern.compile("^[-\\+]?[\\d]*$").matcher(str).matches();
    }

    public final List<String> i() {
        ArrayList arrayList = new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddyyyyHH", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int i = 0;
        while (true) {
            int[] iArr = b;
            if (i >= iArr.length) {
                return arrayList;
            }
            calendar.setTime(date);
            calendar.add(10, iArr[i]);
            arrayList.add(this.c + simpleDateFormat.format(calendar.getTime()));
            i++;
        }
    }

    public boolean j(String str, String str2) {
        List<String> listC;
        if (!d(str) && h(str) && str.length() == 6 && !d(str2) && str2.length() == 6 && str2.matches("^[a-zA-Z0-9]+$") && (listC = c(str)) != null && listC.size() != 0) {
            for (int i = 0; i < listC.size(); i++) {
                if (str2.equals(listC.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
