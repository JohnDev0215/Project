package com.byd.vrassistant.manager;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.library.JsonKey;
import com.byd.vrassistant.bean.CloudSdkBean;
import com.byd.vrassistant.utils.DataUtils;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SevenZipUtil;
import com.cerence.assistant.controller.VoiceAssistantDatapackHelper;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public class LongTermManager {
    public static final String PROFILE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "VRProfile" + File.separator + "profile" + File.separator;
    private static final String TAG = "NEU_LongTermManager";

    public static String startProcessVoiceDataPackCompatible(Context context, String str, String str2) throws Throwable {
        String str3 = context.getDataDir().getAbsolutePath() + File.separator + JsonKey.RESULTS_DATA;
        File file = new File(str3);
        if (!file.exists()) {
            file.mkdirs();
        }
        RLog.e(TAG, "appDataPath :" + str3);
        String str4 = str3 + File.separator + "data_vr-BASE-8155.7z";
        String str5 = str3 + File.separator + "data_vr-BASE";
        DataUtils.copyFileFromAssets(context, "data_vr-BASE-8155.7z", str4);
        File file2 = new File(str5);
        File file3 = new File(str4);
        if (file2.exists()) {
            RLog.e(TAG, str5 + " exists delete:" + DataUtils.deleteDir(file2));
            try {
                SevenZipUtil.unCompress(file3, new File(str3));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                SevenZipUtil.unCompress(file3, new File(str3));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        String str6 = DataUtils.getPlatformDataPackPathEx() + File.separator + "compatible";
        File file4 = new File(str6);
        if (!file4.exists()) {
            file4.mkdirs();
        }
        String platformDataPackPathEx = DataUtils.getPlatformDataPackPathEx();
        if (!new File(platformDataPackPathEx + "/data_" + str2).exists() && str2.contains("eng-USA")) {
            Log.e(TAG, "migration init path data " + platformDataPackPathEx + "/data_" + str2 + " not exsit");
            platformDataPackPathEx = "/vr/BydVoice/data/";
        }
        Log.e(TAG, String.format("migration: basePath: %s, genPath:%s, intlPath: %s, lang:%s", str5, str6, platformDataPackPathEx, str2));
        String voiceAssistantWorkDatapack = VoiceAssistantDatapackHelper.getVoiceAssistantWorkDatapack(str5, str6, platformDataPackPathEx, str2);
        Log.e(TAG, "voiceDataPath : " + voiceAssistantWorkDatapack);
        return voiceAssistantWorkDatapack;
    }

    public static void startProcessVoiceCloudConfigration(Context context, String str, String str2, int i, int i2) {
        String file2String;
        final String strSubstring;
        String str3;
        String str4 = PROFILE_PATH;
        File file = new File(str4);
        if (!file.exists()) {
            Log.e(TAG, "not exsit :" + str4);
            Log.e(TAG, "cloud_file_path: default_edge2cloud_arbitration_rule_v3.json");
            file2String = DataUtils.readAssets2String(context, "default_edge2cloud_arbitration_rule_v3.json");
        } else {
            String str5 = file.list().length > 0 ? (String) Arrays.stream(file.list()).findFirst().get() : "";
            Log.e(TAG, "cloud_file_path: " + str4 + str5);
            file2String = DataUtils.readFile2String(str4 + str5);
        }
        if (file2String == null) {
            Log.e(TAG, "cloud_file_json get null return");
            return;
        }
        Log.e(TAG, "cloud_file_json: " + file2String);
        try {
            final String str6 = i + "";
            final String upperCase = Integer.toHexString(i2).toUpperCase(Locale.ROOT);
            String strSubstring2 = new File(str).getName().substring(5);
            if (TextUtils.isEmpty(str2)) {
                RLog.e(TAG, "setCloudExtraHeadersAsr err");
                return;
            }
            String strSubstring3 = str2.substring(0, 7);
            if (str2.contains("eng-")) {
                strSubstring = str2.substring(str2.length() - 7, str2.length());
                str3 = strSubstring;
            } else {
                strSubstring = strSubstring2;
                str3 = strSubstring3;
            }
            RLog.e(TAG, "headersAsr : " + str3);
            Log.e(TAG, "dataName: " + strSubstring);
            final AtomicReference atomicReference = new AtomicReference("");
            final AtomicReference atomicReference2 = new AtomicReference("");
            RLog.e(TAG, "country_title :" + str6);
            RLog.e(TAG, "veichel_title :" + upperCase);
            CloudSdkBean cloudSdkBean = (CloudSdkBean) VoiceAssistantManager.gson.fromJson(file2String, new TypeToken<CloudSdkBean>() { // from class: com.byd.vrassistant.manager.LongTermManager.1
            }.getType());
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
            final AtomicBoolean atomicBoolean3 = new AtomicBoolean(false);
            final AtomicBoolean atomicBoolean4 = new AtomicBoolean(false);
            if (cloudSdkBean.getRules() == null) {
                RLog.e(TAG, "rules get null returned");
                return;
            }
            cloudSdkBean.getRules().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda7
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return LongTermManager.lambda$startProcessVoiceCloudConfigration$0(str6, atomicBoolean, upperCase, atomicBoolean2, (CloudSdkBean.CloudSdkSubBean) obj);
                }
            }).findFirst().ifPresent(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda10
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    LongTermManager.lambda$startProcessVoiceCloudConfigration$6(strSubstring, atomicReference, (CloudSdkBean.CloudSdkSubBean) obj);
                }
            });
            if (!atomicBoolean.get() || !atomicBoolean2.get()) {
                cloudSdkBean.getRules().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda12
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return LongTermManager.lambda$startProcessVoiceCloudConfigration$7((CloudSdkBean.CloudSdkSubBean) obj);
                    }
                }).forEach(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda13
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        LongTermManager.lambda$startProcessVoiceCloudConfigration$10(strSubstring, atomicReference, (CloudSdkBean.CloudSdkSubBean) obj);
                    }
                });
                if (((String) atomicReference.get()).isEmpty()) {
                    cloudSdkBean.getRules().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda14
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return LongTermManager.lambda$startProcessVoiceCloudConfigration$11((CloudSdkBean.CloudSdkSubBean) obj);
                        }
                    }).forEach(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda15
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            LongTermManager.lambda$startProcessVoiceCloudConfigration$14(atomicReference, (CloudSdkBean.CloudSdkSubBean) obj);
                        }
                    });
                }
            }
            if (cloudSdkBean.getArbitration_timeout() != null) {
                cloudSdkBean.getArbitration_timeout().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda16
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return LongTermManager.lambda$startProcessVoiceCloudConfigration$15(str6, atomicBoolean3, upperCase, atomicBoolean4, (CloudSdkBean.CloudSdkArbitrationBean) obj);
                    }
                }).findFirst().ifPresent(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda17
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        LongTermManager.lambda$startProcessVoiceCloudConfigration$21(strSubstring, atomicReference2, (CloudSdkBean.CloudSdkArbitrationBean) obj);
                    }
                });
            }
            if (cloudSdkBean.getArbitration_timeout() != null && (!atomicBoolean3.get() || !atomicBoolean4.get())) {
                cloudSdkBean.getArbitration_timeout().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda18
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return LongTermManager.lambda$startProcessVoiceCloudConfigration$22((CloudSdkBean.CloudSdkArbitrationBean) obj);
                    }
                }).forEach(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda19
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        LongTermManager.lambda$startProcessVoiceCloudConfigration$25(strSubstring, atomicReference2, (CloudSdkBean.CloudSdkArbitrationBean) obj);
                    }
                });
                if (((String) atomicReference2.get()).isEmpty()) {
                    cloudSdkBean.getArbitration_timeout().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda8
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return LongTermManager.lambda$startProcessVoiceCloudConfigration$26((CloudSdkBean.CloudSdkArbitrationBean) obj);
                        }
                    }).forEach(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda9
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            LongTermManager.lambda$startProcessVoiceCloudConfigration$29(atomicReference2, (CloudSdkBean.CloudSdkArbitrationBean) obj);
                        }
                    });
                }
            }
            Log.e(TAG, "============> overrideConfiguration: 'embedded_on_cloud'  rule: " + ((String) atomicReference.get()));
            Log.e(TAG, "============> overrideConfiguration: 'arbitration_timeout' : " + ((String) atomicReference2.get()));
            if (!((String) atomicReference.get()).isEmpty()) {
                Log.e(TAG, "============> overrideConfiguration: 'embedded_on_cloud'  result: " + ArkAssistant.get().overrideConfiguration("embedded_on_cloud", (String) atomicReference.get()));
            }
            if (((String) atomicReference2.get()).isEmpty()) {
                return;
            }
            Log.e(TAG, "============> overrideConfiguration: 'arbitration_timeout'  result: " + ArkAssistant.get().overrideConfiguration("arbitration_timeout", (String) atomicReference2.get()));
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "JsonSyntaxException: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e2) {
            Log.e(TAG, "Exception: " + e2.getMessage());
            e2.printStackTrace();
        }
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$0(String str, AtomicBoolean atomicBoolean, String str2, AtomicBoolean atomicBoolean2, CloudSdkBean.CloudSdkSubBean cloudSdkSubBean) {
        if (!cloudSdkSubBean.validData()) {
            RLog.e(TAG, "rule invalid data");
            return false;
        }
        RLog.e(TAG, "rule note:" + cloudSdkSubBean.getNote());
        if (cloudSdkSubBean.getCountry_codes().contains(str)) {
            atomicBoolean.set(true);
            RLog.e(TAG, "rule contains country:" + str);
        } else {
            RLog.e(TAG, "rule !contains country:" + str);
            atomicBoolean.set(false);
        }
        if (cloudSdkSubBean.getVehicle_codes().contains(str2)) {
            atomicBoolean2.set(true);
            RLog.e(TAG, "rule contains veichel:" + str2);
        } else {
            RLog.e(TAG, "rule !contains veichel:" + str2);
            atomicBoolean2.set(false);
            if (cloudSdkSubBean.getVehicle_codes().contains("default")) {
                RLog.e(TAG, "rule contains default veichel");
                atomicBoolean2.set(true);
            }
        }
        return atomicBoolean.get() && atomicBoolean2.get();
    }

    static /* synthetic */ void lambda$startProcessVoiceCloudConfigration$6(final String str, final AtomicReference atomicReference, CloudSdkBean.CloudSdkSubBean cloudSdkSubBean) {
        List<CloudSdkBean.LanguageRule> language_rules = cloudSdkSubBean.getLanguage_rules();
        if (language_rules == null) {
            RLog.e(TAG, "language_rules get null returned");
        } else if (language_rules.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LongTermManager.lambda$startProcessVoiceCloudConfigration$1(str, (CloudSdkBean.LanguageRule) obj);
            }
        }).findAny().isPresent()) {
            language_rules.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return LongTermManager.lambda$startProcessVoiceCloudConfigration$2(str, (CloudSdkBean.LanguageRule) obj);
                }
            }).findAny().ifPresent(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    LongTermManager.lambda$startProcessVoiceCloudConfigration$3(atomicReference, (CloudSdkBean.LanguageRule) obj);
                }
            });
        } else {
            language_rules.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return LongTermManager.lambda$startProcessVoiceCloudConfigration$4((CloudSdkBean.LanguageRule) obj);
                }
            }).findAny().ifPresent(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda6
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    LongTermManager.lambda$startProcessVoiceCloudConfigration$5(atomicReference, (CloudSdkBean.LanguageRule) obj);
                }
            });
        }
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$1(String str, CloudSdkBean.LanguageRule languageRule) {
        return languageRule.validData() && languageRule.getLanguages().contains(str);
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$2(String str, CloudSdkBean.LanguageRule languageRule) {
        return languageRule.validData() && languageRule.getLanguages().contains(str);
    }

    static /* synthetic */ void lambda$startProcessVoiceCloudConfigration$3(AtomicReference atomicReference, CloudSdkBean.LanguageRule languageRule) {
        RLog.e(TAG, "rule:" + languageRule);
        atomicReference.set(languageRule.getRule().toString());
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$4(CloudSdkBean.LanguageRule languageRule) {
        return languageRule.validData() && languageRule.getLanguages().contains("default");
    }

    static /* synthetic */ void lambda$startProcessVoiceCloudConfigration$5(AtomicReference atomicReference, CloudSdkBean.LanguageRule languageRule) {
        RLog.e(TAG, "the defalt rule:" + languageRule);
        atomicReference.set(languageRule.getRule().toString());
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$7(CloudSdkBean.CloudSdkSubBean cloudSdkSubBean) {
        return cloudSdkSubBean.validData() && cloudSdkSubBean.getCountry_codes().contains("default");
    }

    static /* synthetic */ void lambda$startProcessVoiceCloudConfigration$10(final String str, final AtomicReference atomicReference, CloudSdkBean.CloudSdkSubBean cloudSdkSubBean) {
        RLog.e(TAG, "1 after defalt rule:" + cloudSdkSubBean);
        cloudSdkSubBean.getLanguage_rules().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda20
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LongTermManager.lambda$startProcessVoiceCloudConfigration$8(str, (CloudSdkBean.LanguageRule) obj);
            }
        }).findFirst().ifPresent(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda21
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                atomicReference.set(((CloudSdkBean.LanguageRule) obj).getRule().toString());
            }
        });
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$8(String str, CloudSdkBean.LanguageRule languageRule) {
        RLog.e(TAG, "1 after defalt rulelan:" + languageRule);
        return languageRule.validData() && languageRule.getLanguages().contains(str);
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$11(CloudSdkBean.CloudSdkSubBean cloudSdkSubBean) {
        return cloudSdkSubBean.validData() && cloudSdkSubBean.getCountry_codes().contains("default");
    }

    static /* synthetic */ void lambda$startProcessVoiceCloudConfigration$14(final AtomicReference atomicReference, CloudSdkBean.CloudSdkSubBean cloudSdkSubBean) {
        RLog.e(TAG, "2 after defalt rule:" + cloudSdkSubBean);
        cloudSdkSubBean.getLanguage_rules().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda29
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LongTermManager.lambda$startProcessVoiceCloudConfigration$12((CloudSdkBean.LanguageRule) obj);
            }
        }).findFirst().ifPresent(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                atomicReference.set(((CloudSdkBean.LanguageRule) obj).getRule().toString());
            }
        });
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$12(CloudSdkBean.LanguageRule languageRule) {
        RLog.e(TAG, "2 after defalt rulelan:" + languageRule);
        return languageRule.validData() && languageRule.getLanguages().contains("default");
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$15(String str, AtomicBoolean atomicBoolean, String str2, AtomicBoolean atomicBoolean2, CloudSdkBean.CloudSdkArbitrationBean cloudSdkArbitrationBean) {
        if (!cloudSdkArbitrationBean.validData()) {
            RLog.e(TAG, "timeout invalid data");
            return false;
        }
        RLog.e(TAG, "timeout note:" + cloudSdkArbitrationBean.getNote());
        if (cloudSdkArbitrationBean.getCountry_codes().contains(str)) {
            atomicBoolean.set(true);
            RLog.e(TAG, "timeout contains country:" + str);
        } else {
            RLog.e(TAG, "timeout !contains country:" + str);
            atomicBoolean.set(false);
        }
        if (cloudSdkArbitrationBean.getVehicle_codes().contains(str2)) {
            atomicBoolean2.set(true);
            RLog.e(TAG, "timeout contains veichel:" + str2);
        } else {
            RLog.e(TAG, "timeout !contains veichel:" + str2);
            atomicBoolean2.set(false);
            if (cloudSdkArbitrationBean.getVehicle_codes().contains("default")) {
                RLog.e(TAG, "timeout contains default veichel");
                atomicBoolean2.set(true);
            }
        }
        return atomicBoolean.get() && atomicBoolean2.get();
    }

    static /* synthetic */ void lambda$startProcessVoiceCloudConfigration$21(final String str, final AtomicReference atomicReference, CloudSdkBean.CloudSdkArbitrationBean cloudSdkArbitrationBean) {
        List<CloudSdkBean.LanguageArbitrationTimeout> language_timeout = cloudSdkArbitrationBean.getLanguage_timeout();
        if (language_timeout == null) {
            RLog.e(TAG, "timeouts get null returned");
        } else if (language_timeout.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda24
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LongTermManager.lambda$startProcessVoiceCloudConfigration$16(str, (CloudSdkBean.LanguageArbitrationTimeout) obj);
            }
        }).findAny().isPresent()) {
            language_timeout.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda25
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return LongTermManager.lambda$startProcessVoiceCloudConfigration$17(str, (CloudSdkBean.LanguageArbitrationTimeout) obj);
                }
            }).findAny().ifPresent(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    LongTermManager.lambda$startProcessVoiceCloudConfigration$18(atomicReference, (CloudSdkBean.LanguageArbitrationTimeout) obj);
                }
            });
        } else {
            language_timeout.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda27
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return LongTermManager.lambda$startProcessVoiceCloudConfigration$19((CloudSdkBean.LanguageArbitrationTimeout) obj);
                }
            }).findAny().ifPresent(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda28
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    LongTermManager.lambda$startProcessVoiceCloudConfigration$20(atomicReference, (CloudSdkBean.LanguageArbitrationTimeout) obj);
                }
            });
        }
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$16(String str, CloudSdkBean.LanguageArbitrationTimeout languageArbitrationTimeout) {
        return languageArbitrationTimeout.validData() && languageArbitrationTimeout.getLanguages().contains(str);
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$17(String str, CloudSdkBean.LanguageArbitrationTimeout languageArbitrationTimeout) {
        return languageArbitrationTimeout.validData() && languageArbitrationTimeout.getLanguages().contains(str);
    }

    static /* synthetic */ void lambda$startProcessVoiceCloudConfigration$18(AtomicReference atomicReference, CloudSdkBean.LanguageArbitrationTimeout languageArbitrationTimeout) {
        RLog.e(TAG, "timeout:" + languageArbitrationTimeout);
        atomicReference.set(languageArbitrationTimeout.getArbitration_timeout().toString());
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$19(CloudSdkBean.LanguageArbitrationTimeout languageArbitrationTimeout) {
        return languageArbitrationTimeout.validData() && languageArbitrationTimeout.getLanguages().contains("default");
    }

    static /* synthetic */ void lambda$startProcessVoiceCloudConfigration$20(AtomicReference atomicReference, CloudSdkBean.LanguageArbitrationTimeout languageArbitrationTimeout) {
        RLog.e(TAG, "the defalt timeout:" + languageArbitrationTimeout);
        atomicReference.set(languageArbitrationTimeout.getArbitration_timeout().toString());
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$22(CloudSdkBean.CloudSdkArbitrationBean cloudSdkArbitrationBean) {
        return cloudSdkArbitrationBean.validData() && cloudSdkArbitrationBean.getCountry_codes().contains("default");
    }

    static /* synthetic */ void lambda$startProcessVoiceCloudConfigration$25(final String str, final AtomicReference atomicReference, CloudSdkBean.CloudSdkArbitrationBean cloudSdkArbitrationBean) {
        RLog.e(TAG, "1 after defalt ti:" + cloudSdkArbitrationBean);
        cloudSdkArbitrationBean.getLanguage_timeout().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda22
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LongTermManager.lambda$startProcessVoiceCloudConfigration$23(str, (CloudSdkBean.LanguageArbitrationTimeout) obj);
            }
        }).findFirst().ifPresent(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda23
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                atomicReference.set(((CloudSdkBean.LanguageArbitrationTimeout) obj).getArbitration_timeout().toString());
            }
        });
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$23(String str, CloudSdkBean.LanguageArbitrationTimeout languageArbitrationTimeout) {
        RLog.e(TAG, "1 after defalt til:" + languageArbitrationTimeout);
        return languageArbitrationTimeout.validData() && languageArbitrationTimeout.getLanguages().contains(str);
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$26(CloudSdkBean.CloudSdkArbitrationBean cloudSdkArbitrationBean) {
        return cloudSdkArbitrationBean.validData() && cloudSdkArbitrationBean.getCountry_codes().contains("default");
    }

    static /* synthetic */ void lambda$startProcessVoiceCloudConfigration$29(final AtomicReference atomicReference, CloudSdkBean.CloudSdkArbitrationBean cloudSdkArbitrationBean) {
        RLog.e(TAG, "2 after defalt ti:" + cloudSdkArbitrationBean);
        cloudSdkArbitrationBean.getLanguage_timeout().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LongTermManager.lambda$startProcessVoiceCloudConfigration$27((CloudSdkBean.LanguageArbitrationTimeout) obj);
            }
        }).findFirst().ifPresent(new Consumer() { // from class: com.byd.vrassistant.manager.LongTermManager$$ExternalSyntheticLambda11
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                atomicReference.set(((CloudSdkBean.LanguageArbitrationTimeout) obj).getArbitration_timeout().toString());
            }
        });
    }

    static /* synthetic */ boolean lambda$startProcessVoiceCloudConfigration$27(CloudSdkBean.LanguageArbitrationTimeout languageArbitrationTimeout) {
        RLog.e(TAG, "2 after defalt til:" + languageArbitrationTimeout);
        return languageArbitrationTimeout.validData() && languageArbitrationTimeout.getLanguages().contains("default");
    }
}
