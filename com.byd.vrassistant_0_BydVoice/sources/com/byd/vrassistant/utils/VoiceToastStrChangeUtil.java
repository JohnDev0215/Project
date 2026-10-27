package com.byd.vrassistant.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.bydauto.adas.BYDAutoADASDevice;
import android.text.TextUtils;
import android.util.Log;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.R;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import java.io.File;
import java.util.Locale;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceToastStrChangeUtil {
    private static final String TAG = TagUtil.getTagName(VoiceToastStrChangeUtil.class);

    public enum ToastString {
        REVERSE,
        AUDIO_OCCUPANCY,
        BOOT_NOT_FINISHED,
        REGISTRATION_IS_NOT_COMPLETE,
        VOICE_INIT_FAIL,
        AUDIO_MUTE,
        AUDIO_UNMUTE
    }

    public static String getToastString(ToastString toastString) {
        String string;
        String str = TAG;
        Log.i(str, "getToastString: toastString = " + toastString.name());
        Context context = BydVoiceApplication.getContext();
        Locale countryLocale = VoiceAssistantManager.get().getCountryLocale();
        RLog.e(str, "getToastString countryLocale:" + countryLocale.getLanguage());
        Locale language = getLanguage(countryLocale.getLanguage());
        if (language == null) {
            File file = new File(VoiceAssistantManager.get().getLanguage());
            if (file.exists()) {
                Log.i(str, "getToastString: currentVr = " + file.getName());
                Locale vRLanguage = getVRLanguage(file.getName());
                Log.i(str, "getToastString: currentVr locale language = " + vRLanguage.getLanguage());
                context = changeLang(context, vRLanguage);
            }
        } else {
            context = changeLang(context, countryLocale);
            Log.i(str, "getToastString: language = " + language);
        }
        switch (AnonymousClass1.$SwitchMap$com$byd$vrassistant$utils$VoiceToastStrChangeUtil$ToastString[toastString.ordinal()]) {
            case 1:
                Log.i(str, "getToastString: REVERSE");
                string = context.getResources().getString(R.string.car_in_reverse_toast);
                break;
            case 2:
                Log.i(str, "getToastString: AUDIO_OCCUPANCY");
                string = context.getResources().getString(R.string.mic_occupied_toast);
                break;
            case 3:
                Log.i(str, "getToastString: BOOT_NOT_FINISHED");
                string = context.getResources().getString(R.string.boot_guide_toast);
                break;
            case 4:
                Log.i(str, "getToastString: REGISTRATION_IS_NOT_COMPLETE");
                string = context.getResources().getString(R.string.boot_registing_toast);
                break;
            case 5:
                Log.i(str, "getToastString: VOICE_INIT_FAIL");
                string = context.getResources().getString(R.string.voice_init_failed);
                break;
            case 6:
                Log.i(str, "getToastString: AUDIO_MUTE");
                string = context.getResources().getString(R.string.audio_mute);
                break;
            case 7:
                Log.i(str, "getToastString: AUDIO_UNMUTE");
                string = context.getResources().getString(R.string.audio_unmute);
                break;
            default:
                Log.i(str, "getToastString: default");
                string = "null";
                break;
        }
        Log.i(str, "getToastString: result = " + string);
        return string;
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.utils.VoiceToastStrChangeUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$vrassistant$utils$VoiceToastStrChangeUtil$ToastString;

        static {
            int[] iArr = new int[ToastString.values().length];
            $SwitchMap$com$byd$vrassistant$utils$VoiceToastStrChangeUtil$ToastString = iArr;
            try {
                iArr[ToastString.REVERSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$utils$VoiceToastStrChangeUtil$ToastString[ToastString.AUDIO_OCCUPANCY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$utils$VoiceToastStrChangeUtil$ToastString[ToastString.BOOT_NOT_FINISHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$utils$VoiceToastStrChangeUtil$ToastString[ToastString.REGISTRATION_IS_NOT_COMPLETE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$utils$VoiceToastStrChangeUtil$ToastString[ToastString.VOICE_INIT_FAIL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$utils$VoiceToastStrChangeUtil$ToastString[ToastString.AUDIO_MUTE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$utils$VoiceToastStrChangeUtil$ToastString[ToastString.AUDIO_UNMUTE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static Locale getLanguage(String str) {
        Log.i(TAG, "getLanguage() called with: systemLanguage = [" + str + "]");
        if (str.contains("en")) {
            return new Locale("en");
        }
        if (str.contains("da")) {
            return new Locale("da");
        }
        if (str.contains("de")) {
            return new Locale("de");
        }
        if (str.contains("es") && str.contains("ES")) {
            return new Locale("es", "es");
        }
        if (str.contains("es_LA")) {
            return new Locale("es", "mx");
        }
        if (str.contains("es")) {
            return new Locale("es", "es");
        }
        if (str.contains("fr")) {
            return new Locale("fr");
        }
        if (str.contains("ja")) {
            return new Locale("ja");
        }
        if (str.contains("nb")) {
            return new Locale("nb");
        }
        if (str.contains("nl")) {
            return new Locale("nl");
        }
        if (str.contains("pt") && str.contains("PT")) {
            return new Locale("pt", "pt");
        }
        if (str.contains("pt") && str.contains("BR")) {
            return new Locale("pt", CompressorStreamFactory.BROTLI);
        }
        if (str.contains("pt")) {
            return new Locale("pt");
        }
        if (str.contains("sv")) {
            return new Locale("sv");
        }
        if (str.contains("th")) {
            return new Locale("th");
        }
        if (str.contains("ko")) {
            return new Locale("ko");
        }
        if (str.contains("zh_TW") || str.contains("zh_HK")) {
            return new Locale("zh", "hk");
        }
        if (str.contains("zh_CN") || str.contains("zh")) {
            return new Locale("zh", "cn");
        }
        if (str.contains("it")) {
            return new Locale("it");
        }
        if (str.contains("iw")) {
            return new Locale("iw");
        }
        if (str.contains("tr")) {
            return new Locale("tr");
        }
        return null;
    }

    private static Context changeLang(Context context, Locale locale) {
        Log.i(TAG, "changeLang() called with: context = [" + context + "], mLocale = [" + locale + "]");
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        return context.createConfigurationContext(configuration);
    }

    private static Locale getVRLanguage(String str) {
        Log.i(TAG, "getVRLanguage() called with: currentLanguage = [" + str + "]");
        Locale locale = new Locale("en");
        if (TextUtils.isEmpty(str)) {
            return new Locale("en");
        }
        if (str.contains("eng")) {
            return new Locale("en");
        }
        if (str.contains("por") && str.contains("PRT")) {
            return new Locale("pt", "pt");
        }
        if (str.contains("por") && str.contains("BRA")) {
            return new Locale("pt", CompressorStreamFactory.BROTLI);
        }
        if (str.contains("spa") && str.contains(BYDAutoADASDevice.FEATURE_ADAS_ESP)) {
            return new Locale("es", "es");
        }
        if (str.contains("spa") && str.contains("XLA")) {
            return new Locale("es", "mx");
        }
        if (str.contains("nor")) {
            return new Locale("nb");
        }
        if (str.contains("tha")) {
            return new Locale("th");
        }
        if (str.contains("jap") || str.contains("jan") || str.contains("jpn")) {
            return new Locale("ja");
        }
        if (str.contains("fra")) {
            return new Locale("fr");
        }
        if (str.contains("deu")) {
            return new Locale("de");
        }
        if (str.contains("ita")) {
            return new Locale("it");
        }
        if (str.contains("cmn")) {
            return new Locale("zh", "cn");
        }
        if (str.contains("yue")) {
            return new Locale("zh", "hk");
        }
        if (str.contains("dan")) {
            return new Locale("da");
        }
        if (str.contains("heb")) {
            return new Locale("iw");
        }
        if (str.contains("nld")) {
            return new Locale("nl");
        }
        if (str.contains("kor")) {
            return new Locale("ko");
        }
        if (str.contains("tur")) {
            return new Locale("tr");
        }
        return str.contains("swe") ? new Locale("sv") : locale;
    }
}
