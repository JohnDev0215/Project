package com.byd.vrassistant.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.bydauto.adas.BYDAutoADASDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.location.Location;
import android.os.Handler;
import android.os.LocaleList;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.api.ArkClient;
import cerence.ark.assistant.api.AssistantListener;
import cerence.ark.assistant.api.context.LatLng;
import cerence.ark.assistant.api.context.grpc.LocationSourceGrpc;
import cerence.ark.assistant.api.context.grpc.UiContextGrpc;
import cerence.ark.assistant.api.context.http.LocationSource;
import cerence.ark.assistant.api.data.ShortcutCommand;
import cerence.ark.assistant.api.dynamic.ContentType;
import cerence.ark.assistant.api.notification.DialogEvent;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.controller.event.CancelDialogEvent;
import cerence.ark.assistant.controller.event.PTTEvent;
import cerence.ark.assistant.controller.event.SetEnableZonesEvent;
import cerence.ark.assistant.controller.event.SetSnoozingEvent;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.data.AssistantConfiguration;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;
import cerence.ark.assistant.framework.data.AudioRecordingMode;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.ErrorInfo;
import cerence.ark.assistant.framework.data.InteractionMode;
import cerence.ark.assistant.framework.data.Language;
import cerence.ark.assistant.framework.prompter.IPrompter;
import com.blankj.utilcode.util.SPUtils;
import com.byd.common.bean.WuwInfoBean;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.vrassistant.BuildConfig;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.bean.FacotryVoiceCheck;
import com.byd.vrassistant.bean.LanguageBean;
import com.byd.vrassistant.bean.VoiceLanuageDp;
import com.byd.vrassistant.common.ICommonSuccessInterface;
import com.byd.vrassistant.controller.ControllerFactory;
import com.byd.vrassistant.controller.DemoType;
import com.byd.vrassistant.conversation.BydLocationHelper;
import com.byd.vrassistant.conversation.HvacHelper;
import com.byd.vrassistant.conversation.HvacTemperatureHelper;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.eventbus.AutoRecorderEvent;
import com.byd.vrassistant.eventbus.DataRecordEvent;
import com.byd.vrassistant.eventbus.FinalOperation;
import com.byd.vrassistant.eventbus.SystemStatusEvent;
import com.byd.vrassistant.eventbus.VoiceSateEvent;
import com.byd.vrassistant.eventprocessor.DialogEventProcessor;
import com.byd.vrassistant.hint.PropertiesUtil;
import com.byd.vrassistant.hint.ToolUtils;
import com.byd.vrassistant.hmi.DemoHmi;
import com.byd.vrassistant.utils.Base64Utils;
import com.byd.vrassistant.utils.CSVStringUtils;
import com.byd.vrassistant.utils.CarModeUtils;
import com.byd.vrassistant.utils.DataUtils;
import com.byd.vrassistant.utils.LoggerUtil;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SharedPreferencesUtil;
import com.byd.vrassistant.utils.StringUtils;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import com.byd.vrassistant.utils.VoiceToastStrChangeUtil;
import com.byd.vrassistant.view.ViewManager;
import com.byd.vrassistant.view.ViewStateManger;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import com.byd.vrdownload.model.LanguageUpdate;
import com.cerence.assistant.controller.DomainsAutoController;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceAssistantManager {
    public static final int RETRY_TIMEACCENT = 2;
    public static final int RETRY_TIMEOUT = 4;
    private static final String TAG = "NEU_VoiceAssistantManager";
    private static volatile VoiceAssistantManager mInstance;
    Map<String, String> asrDialogFileListMap;
    InteractionMode assistant_mode;
    Thread autoBatchProcessPCMThread;
    private final PublishSubject<Boolean> cancelEvent;
    CommonViewModel commonViewModel;
    String compatibleDataPath;
    private Context context = BydVoiceApplication.getContext();
    Locale countryLocale;
    String dataPath;
    volatile DialogEvent.Event dialogEvent;
    List<Integer> engCodeAry;
    public int failCountDown;
    boolean initialited;
    boolean isAudioExportLog;
    boolean isAudioRecordStart;
    boolean isAutoBatchStart;
    boolean isAvmForground;
    public CountDownLatch isEventSuccess;
    boolean isFanSppedReduced;
    boolean isHardKeyPTTEvent;
    boolean isShortCutWakeup;
    boolean isShowAfterExit;
    boolean isVirtualKeyPTTEvent;
    boolean isVoiceActiveZonesLocked;
    AtomicBoolean isVoiceAssistantIniting;
    AtomicBoolean isVoiceAssistantStared;
    AtomicBoolean isVoiceAssistantStarting;
    AtomicBoolean isVoiceInitingStared;
    boolean iswakeupbroadcasted;
    BlockingQueue<String> mAsrDialogList;
    private List<String> mAsrLanguages;
    protected ControllerFactory mControllerFactory;
    private List<String> mOtaUseLanguages;
    List<Integer> porCodeAry;
    private IPrompter prompter;
    List<String> shortCutList;
    List<Integer> spaCodeAry;
    int vehicleType;
    private String voiceId;
    private List<VoiceLanuageDp> voiceLanuageDpList;
    private List<VoiceLanuageDp> voiceLanuageFailList;
    private List<VoiceLanuageDp> voiceLanuageInstallList;
    private List<VoiceLanuageDp> voiceLanuageNaviDpList;
    private List<VoiceLanuageDp> voiceLanuageSettingList;
    public static Map<String, HashMap> shortcutWUWMaps = new HashMap<String, HashMap>() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager.1
        {
            HashMap map = new HashMap();
            map.put("KEY_HOME_COMMANDS", Arrays.asList("return to home page", "rotate screen"));
            map.put("KEY_AC_CONTROL", Arrays.asList("turn on AC", "turn off AC"));
            map.put("KEY_VOLUME_CONTROL", Arrays.asList("volume up", "volume down"));
            map.put("KEY_MUSIC_PLAYER", Arrays.asList("previous track", "next track", "resume playing", "pause playing"));
            map.put("KEY_MAP_NAV", Arrays.asList("zoom in map", "zoom out map", "route overview", "stop navigation"));
            map.put(Constant.KEY_TEL_CONTROL, Arrays.asList("answer phone", "hang up"));
            put("eng-", map);
            HashMap map2 = new HashMap();
            map2.put("KEY_HOME_COMMANDS", Arrays.asList("retourner à la page d'accueil", "pivoter l'écran"));
            map2.put("KEY_AC_CONTROL", Arrays.asList("allumer la climatisation", "éteindre la climatisation"));
            map2.put("KEY_VOLUME_CONTROL", Arrays.asList("monter le volume", "baisser le volume"));
            map2.put("KEY_MUSIC_PLAYER", Arrays.asList("reprendre la lecture", "mettre la lecture en pause", "chanson précédente", "chanson suivante"));
            map2.put("KEY_MAP_NAV", Arrays.asList("zoomer la carte", "dézoomer la carte", "aperçu de l'itiniraire", "quitter la navigation"));
            map2.put(Constant.KEY_TEL_CONTROL, Arrays.asList("décrocher", "raccrocher"));
            put("fra-", map2);
            HashMap map3 = new HashMap();
            map3.put("KEY_HOME_COMMANDS", Arrays.asList("zurück zur startseite gehen", "den bildschirm drehen"));
            map3.put("KEY_AC_CONTROL", Arrays.asList("klimaanlage an", "klimaanlage aus"));
            map3.put("KEY_VOLUME_CONTROL", Arrays.asList("lautstärke hoch", "lautstärke runter"));
            map3.put("KEY_MUSIC_PLAYER", Arrays.asList("weiter abspielen", "abspielpause", "vorheriges lied", "nächstes lied"));
            map3.put("KEY_MAP_NAV", Arrays.asList("karte vergrößern", "karte verkleinern", "die ganze route zeigen", "navigation stoppen"));
            map3.put(Constant.KEY_TEL_CONTROL, Arrays.asList("den anruf annehmen", "auflegen"));
            put("deu-", map3);
            HashMap map4 = new HashMap();
            map4.put("KEY_HOME_COMMANDS", Arrays.asList("ホームページに戻る", "スクリーンを回転する"));
            map4.put("KEY_AC_CONTROL", Arrays.asList("エアコンをオン", "エアコンをオフ"));
            map4.put("KEY_VOLUME_CONTROL", Arrays.asList("音量を大きくする", "音量を小さくする"));
            map4.put("KEY_MUSIC_PLAYER", Arrays.asList("再生を再開する", "再生を一時停止する", "前の曲", "次の曲"));
            map4.put("KEY_MAP_NAV", Arrays.asList("地図を拡大する", "地図を縮小する", "ルート概要", "ナビ終了"));
            map4.put(Constant.KEY_TEL_CONTROL, Arrays.asList("電話に出る", "電話を切る"));
            put("jpn-", map4);
            HashMap map5 = new HashMap();
            map5.put("KEY_HOME_COMMANDS", Arrays.asList("กลับ หน้าหลัก", "หมุน หน้าจอ"));
            map5.put("KEY_AC_CONTROL", Arrays.asList("เปิด แอร์", "ปิด แอร์"));
            map5.put("KEY_VOLUME_CONTROL", Arrays.asList("ปรับ ระดับ เสียง ขึ้น", "เพิ่ม เสียง", "ลด เสียง", "ปรับ ระดับ เสียง ลง"));
            map5.put("KEY_MUSIC_PLAYER", Arrays.asList("หยุด เล่น ชั่วคราว", "เพลง ก่อน หน้า", "เพลง ถัด ไป", "เล่น ต่อ", "ขอ เล่น ต่อ"));
            map5.put("KEY_MAP_NAV", Arrays.asList("ซูม แผนที่ ออก", "ซูม แผนที่ เข้า", "หยุด นำทาง", "ภาพรวม เส้นทาง"));
            map5.put(Constant.KEY_TEL_CONTROL, Arrays.asList("รับ สาย", "รับ โทรศัพท์", "วาง สาย", "วาง สาย โทรศัพท์"));
            put("tha-", map5);
            HashMap map6 = new HashMap();
            map6.put("KEY_HOME_COMMANDS", Arrays.asList("volver a la página de inicio", "girar la pantalla"));
            map6.put("KEY_AC_CONTROL", Arrays.asList("encender el aire acondicionado", "apagar el aire acondicionado"));
            map6.put("KEY_VOLUME_CONTROL", Arrays.asList("subir el volumen", "bajar el volumen"));
            map6.put("KEY_MUSIC_PLAYER", Arrays.asList("Continuar la música", "Pausar la música", "canción anterior", "siguiente canción"));
            map6.put("KEY_MAP_NAV", Arrays.asList("acercar el mapa", "alejar el mapa", "ver la ruta completa", "detener la navegación"));
            map6.put(Constant.KEY_TEL_CONTROL, Arrays.asList("contestar el teléfono", "colgar el teléfono"));
            put(Language.SPA_ESP, map6);
            HashMap map7 = new HashMap();
            map7.put("KEY_HOME_COMMANDS", Arrays.asList("volver a la página principal", "rotar la pantalla"));
            map7.put("KEY_AC_CONTROL", Arrays.asList("encender el aire acondicionado", "apagar el aire acondicionado"));
            map7.put("KEY_VOLUME_CONTROL", Arrays.asList("subir el volumen", "bajar el volumen"));
            map7.put("KEY_MUSIC_PLAYER", Arrays.asList("seguir reproduciendo", "pausar reproducción", "canción anterior", "siguiente canción"));
            map7.put("KEY_MAP_NAV", Arrays.asList("agrandar el mapa", "achicar el mapa", "ver viaje completo", "parar navegación"));
            map7.put(Constant.KEY_TEL_CONTROL, Arrays.asList("contestar el teléfono", "colgar la llamada"));
            put("spa-XLA", map7);
            HashMap map8 = new HashMap();
            map8.put("KEY_HOME_COMMANDS", Arrays.asList("gå tilbake til hjemmeside", "roter skjermen"));
            map8.put("KEY_AC_CONTROL", Arrays.asList("slå på klimaanlegget", "slå av klimaanlegget"));
            map8.put("KEY_VOLUME_CONTROL", Arrays.asList("skru opp volumet", "skru ned volumet"));
            map8.put("KEY_MUSIC_PLAYER", Arrays.asList("fortsett avspilling", "sett avspilling på pause", "forrige spor", "neste spor"));
            map8.put("KEY_MAP_NAV", Arrays.asList("zoom in på kartet", "zoom ut på kartet", "ruteoversikt", "stopp navigasjon"));
            map8.put(Constant.KEY_TEL_CONTROL, Arrays.asList("ta telefonen", "legg på"));
            put("nor-", map8);
            HashMap map9 = new HashMap();
            map9.put("KEY_HOME_COMMANDS", Arrays.asList("voltar à página inicial", "rodar o ecrã"));
            map9.put("KEY_AC_CONTROL", Arrays.asList("ligar o ar-condicionado", "desligar o ar-condicionado"));
            map9.put("KEY_VOLUME_CONTROL", Arrays.asList("aumentar o volume", "diminuir o volume"));
            map9.put("KEY_MUSIC_PLAYER", Arrays.asList("retomar a reprodução", "pausar a reprodução", "faixa anterior", "faixa seguinte"));
            map9.put("KEY_MAP_NAV", Arrays.asList("ampliar o mapa", "reduzir o mapa", "visão geral da rota", "parar a navegação"));
            map9.put(Constant.KEY_TEL_CONTROL, Arrays.asList("atender a chamada", "desligar a chamada"));
            put(Language.POR_PRT, map9);
            HashMap map10 = new HashMap();
            map10.put("KEY_HOME_COMMANDS", Arrays.asList("ir para página inicial", "girar a tela"));
            map10.put("KEY_AC_CONTROL", Arrays.asList("ligar o ar-condicionado", "desligar o ar-condicionado"));
            map10.put("KEY_VOLUME_CONTROL", Arrays.asList("aumentar o volume", "baixar o volume"));
            map10.put("KEY_MUSIC_PLAYER", Arrays.asList("voltar a tocar música", "pausar a música", "música anterior", "próxima música"));
            map10.put("KEY_MAP_NAV", Arrays.asList("aumentar o zoom do mapa", "afastar o zoom do mapa", "visão geral da rota", "fechar navegação"));
            map10.put(Constant.KEY_TEL_CONTROL, Arrays.asList("atender telefone", "desligar telefone"));
            put("por-BRA", map10);
            HashMap map11 = new HashMap();
            map11.put("KEY_HOME_COMMANDS", Arrays.asList("tillbaks till startsidan", "rotera skärmen"));
            map11.put("KEY_AC_CONTROL", Arrays.asList("slå på ac", "stäng av ac"));
            map11.put("KEY_VOLUME_CONTROL", Arrays.asList("volym upp", "volym ner"));
            map11.put("KEY_MUSIC_PLAYER", Arrays.asList("fortsätt spela", "pausa musiken", "förra låten", "nästa låt"));
            map11.put("KEY_MAP_NAV", Arrays.asList("zooma in kartan", "zooma ut kartan", "visa hela rutten", "stäng navigationen"));
            map11.put(Constant.KEY_TEL_CONTROL, Arrays.asList("svara på samtalet", "lägg på"));
            put("swe-", map11);
            HashMap map12 = new HashMap();
            map12.put("KEY_HOME_COMMANDS", Arrays.asList("返回主頁", "轉動屏幕"));
            map12.put("KEY_AC_CONTROL", Arrays.asList("打開冷氣", "關閉冷氣"));
            map12.put("KEY_VOLUME_CONTROL", Arrays.asList("大聲啲", "細聲啲"));
            map12.put("KEY_MUSIC_PLAYER", Arrays.asList("恢復播放", "暫停播放", "上一首", "下一首"));
            map12.put("KEY_MAP_NAV", Arrays.asList("放大地圖", "縮細地圖", "路線預覽", "關閉導航"));
            map12.put(Constant.KEY_TEL_CONTROL, Arrays.asList("接聽電話", "收線"));
            put("yue-", map12);
            HashMap map13 = new HashMap();
            map13.put("KEY_HOME_COMMANDS", Arrays.asList("回到主页", "旋转屏幕"));
            map13.put("KEY_AC_CONTROL", Arrays.asList("打开空调", "关闭空调"));
            map13.put("KEY_VOLUME_CONTROL", Arrays.asList("增大音量", "减小音量"));
            map13.put("KEY_MUSIC_PLAYER", Arrays.asList("继续播放", "暂停播放", "上一首", "下一首"));
            map13.put("KEY_MAP_NAV", Arrays.asList("放大地图", "缩小地图", "查看全程", "退出导航"));
            map13.put(Constant.KEY_TEL_CONTROL, Arrays.asList("接听", "挂断"));
            put("cmn-", map13);
            HashMap map14 = new HashMap();
            map14.put("KEY_HOME_COMMANDS", Arrays.asList("torna alla pagina iniziale", "ruota lo schermo"));
            map14.put("KEY_AC_CONTROL", Arrays.asList("accendi l'aria condizionata", "spegni l'aria condizionata"));
            map14.put("KEY_VOLUME_CONTROL", Arrays.asList("alza il volume", "abbassa il volume"));
            map14.put("KEY_MUSIC_PLAYER", Arrays.asList("riprendi la riproduzione", "metti in pausa", "brano precedente", "brano successivo"));
            map14.put("KEY_MAP_NAV", Arrays.asList("ingrandisci la mappa", "riduci la mappa", "panoramica del percorso", "termina navigazione"));
            map14.put(Constant.KEY_TEL_CONTROL, Arrays.asList("rispondi al telefono", "riaggancia"));
            put("ita-", map14);
            HashMap map15 = new HashMap();
            map15.put("KEY_HOME_COMMANDS", Arrays.asList("ana ekrana dön", "ekranı çevir"));
            map15.put("KEY_AC_CONTROL", Arrays.asList("klimayı aç", "klimayı kapat"));
            map15.put("KEY_VOLUME_CONTROL", Arrays.asList("sesi artır", "sesi düşür"));
            map15.put("KEY_MUSIC_PLAYER", Arrays.asList("oynatmaya devam et", "oynatmayı duraklat", "öncekini çal", "sonrakini çal"));
            map15.put("KEY_MAP_NAV", Arrays.asList("haritayı yakınlaştır", "haritayı uzaklaştır", "rotanın tamamını göster", "navigasyonu durdur"));
            map15.put(Constant.KEY_TEL_CONTROL, Arrays.asList("telefonu aç", "telefonu kapat"));
            put("tur-", map15);
            HashMap map16 = new HashMap();
            map16.put("KEY_HOME_COMMANDS", Arrays.asList("tilbage til startsiden", "drej skærmen"));
            map16.put("KEY_AC_CONTROL", Arrays.asList("tænd for AC", "sluk for AC"));
            map16.put("KEY_VOLUME_CONTROL", Arrays.asList("skru op for lydstyrken", "skru ned for lydstyrken"));
            map16.put("KEY_MUSIC_PLAYER", Arrays.asList("genoptag afspilning", "paus afspilning", "forrige nummer", "næste nummer"));
            map16.put("KEY_MAP_NAV", Arrays.asList("zoom ind på kortet", "zoom ud på kortet", "ruteoversigt", "stop navigation"));
            map16.put(Constant.KEY_TEL_CONTROL, Arrays.asList("besvar opkald", "læg på"));
            put("dan-", map16);
            HashMap map17 = new HashMap();
            map17.put("KEY_HOME_COMMANDS", Arrays.asList("wróć do pulpitu", "obróć ekran"));
            map17.put("KEY_AC_CONTROL", Arrays.asList("włącz klimę", "wyłącz klimę"));
            map17.put("KEY_VOLUME_CONTROL", Arrays.asList("głośniej", "ciszej"));
            map17.put("KEY_MUSIC_PLAYER", Arrays.asList("wznów odtwarzanie", "zapauzuj odtwarzanie", "poprzedni utwór", "następny utwór"));
            map17.put("KEY_MAP_NAV", Arrays.asList("przybliż mapę", "oddal mapę", "przegląd trasy", "zatrzymaj nawigację"));
            map17.put(Constant.KEY_TEL_CONTROL, Arrays.asList("odbierz telefon", "rozłącz się"));
            put("pol-", map17);
            HashMap map18 = new HashMap();
            map18.put("KEY_HOME_COMMANDS", Arrays.asList("terug naar de startpagina", "scherm roteren"));
            map18.put("KEY_AC_CONTROL", Arrays.asList("schakel airco in", "schakel airco uit"));
            map18.put("KEY_VOLUME_CONTROL", Arrays.asList("verhoog volume", "verlaag volume"));
            map18.put("KEY_MUSIC_PLAYER", Arrays.asList("hervat het spelen", "pauzeer het spelen", "vorige nummer", "volgende nummer"));
            map18.put("KEY_MAP_NAV", Arrays.asList("zoom in op de kaart", "zoom uit op de kaart", "routeoverzicht", "stop navigatie"));
            map18.put(Constant.KEY_TEL_CONTROL, Arrays.asList("beantwoord de oproep", "hang op"));
            put("nld-", map18);
            HashMap map19 = new HashMap();
            map19.put("KEY_HOME_COMMANDS", Arrays.asList("palaa etusivulle", "kierrä näyttöä"));
            map19.put("KEY_AC_CONTROL", Arrays.asList("laita AC päälle", "sammuta AC"));
            map19.put("KEY_VOLUME_CONTROL", Arrays.asList("ääni kovemmalle", "ääni hiljemmälle"));
            map19.put("KEY_MUSIC_PLAYER", Arrays.asList("jatka soittamista", "keskeytä soittamista", "edellinen kappale", "seuraava kappale"));
            map19.put("KEY_MAP_NAV", Arrays.asList("zoomaa karttaa", "loitonna karttaa", "reitin yleiskatsaus", "lopeta navigointi"));
            map19.put(Constant.KEY_TEL_CONTROL, Arrays.asList("vastaa puheluun", "lopeta puhelu"));
            put("fin-", map19);
            HashMap map20 = new HashMap();
            map20.put("KEY_HOME_COMMANDS", Arrays.asList("חזור לדף הבית", "סובב מסך"));
            map20.put("KEY_AC_CONTROL", Arrays.asList("הדלק את המזגן", "כבה את המזגן"));
            map20.put("KEY_VOLUME_CONTROL", Arrays.asList("הגבר את הקול", "הנמך את הקול"));
            map20.put("KEY_MUSIC_PLAYER", Arrays.asList("המשך לנגן", "הפסק לנגן", "שיר קודם", "שיר הבא"));
            map20.put("KEY_MAP_NAV", Arrays.asList("הגדל את המפה", "הקטן את המפה", "סקירת מסלול", "הפסק את הניווט"));
            map20.put(Constant.KEY_TEL_CONTROL, Arrays.asList("ענה לטלפון", "נתק את הטלפון"));
            put("heb-", map20);
            HashMap map21 = new HashMap();
            map21.put("KEY_HOME_COMMANDS", Arrays.asList("홈으로 돌아가", "스크린 돌려줘"));
            map21.put("KEY_AC_CONTROL", Arrays.asList("에어컨 켜줘", "에어컨 꺼줘"));
            map21.put("KEY_VOLUME_CONTROL", Arrays.asList("볼륨 올려", "볼륨 줄여"));
            map21.put("KEY_MUSIC_PLAYER", Arrays.asList("음악 계속해", "음악 잠깐 멈춰", "이전 곡", "다음 곡"));
            map21.put("KEY_MAP_NAV", Arrays.asList("지도 크게 해줘", "지도 작게 해줘", "경로 표시", "내비 꺼줘"));
            map21.put(Constant.KEY_TEL_CONTROL, Arrays.asList("전화 받아줘", "전화 끊어줘"));
            put("kor-", map21);
            HashMap map22 = new HashMap();
            map22.put("KEY_HOME_COMMANDS", Arrays.asList("Menj vissza a kezdőlapra", "Forgasd a képernyőt"));
            map22.put("KEY_AC_CONTROL", Arrays.asList("Kapcsold be a légkondicionálót", "Kapcsold ki a légkondicionálót"));
            map22.put("KEY_VOLUME_CONTROL", Arrays.asList("Növeld a hangerőt", "Csökkentsd a hangerőt"));
            map22.put("KEY_MUSIC_PLAYER", Arrays.asList("Előző szám", "Következő szám", "Állítsd meg a lejátszást", "Kezdd újra a lejátszást"));
            map22.put("KEY_MAP_NAV", Arrays.asList("Nagyítsd a térképet", "Kicsinyítsd a térképet", "Útvonal áttekintése", "Állítsd le a navigációt"));
            map22.put(Constant.KEY_TEL_CONTROL, Arrays.asList("Vedd fel a telefont", "Tedd le a telefont"));
            put("hun-", map22);
        }
    };
    public static Map<String, LatLng> gpsDebugMaps = new HashMap<String, LatLng>() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager.2
        {
            put("data_eng-GBR", new LatLng(51.509865d, -0.118092d));
            put("data_eng-AUS", new LatLng(-33.865143d, 151.2099d));
            put("data_eng-SGP", new LatLng(1.29027d, 103.851959d));
            put("data_eng-XWW", new LatLng(59.91273d, 10.74609d));
            put("data_eng-USA", new LatLng(42.361145d, -71.057083d));
            put("data_por-BRA", new LatLng(-15.793889d, -47.882778d));
            put("data_por-PRT", new LatLng(38.736946d, -9.142685d));
            put("data_spa-XLA", new LatLng(19.432608d, -99.133209d));
            put("data_spa-ESP", new LatLng(40.416775d, -3.70379d));
            put("data_nor-NOR", new LatLng(59.91273d, 10.74609d));
            put("data_jpn-JPN", new LatLng(35.652832d, 139.839478d));
            put("data_fra-FRA", new LatLng(48.864716d, 2.349014d));
            put("data_tha-THA", new LatLng(13.756331d, 100.501762d));
            put("data_deu-DEU", new LatLng(52.52437d, 13.41053d));
            put("data_cmn-CHN", new LatLng(39.9075d, 116.39723d));
            put("data_ita-ITA", new LatLng(41.902782d, 12.496366d));
            put("data_yue-HKG", new LatLng(22.2575d, 114.15724d));
            put("data_tur-TUR", new LatLng(39.933365d, 32.859741d));
            put("data_kor-KOR", new LatLng(37.5426d, 127.024512d));
            put("data_dan-DNK", new LatLng(55.67609d, 12.56833d));
            put("data_pol-POL", new LatLng(52.237049d, 21.017532d));
            put("data_nld-NLD", new LatLng(52.370216d, 4.895168d));
            put("data_heb-ISR", new LatLng(32.10011689057693d, 34.82847517215217d));
            put("data_fin-FIN", new LatLng(60.169857d, 24.938379d));
            put("data_swe-SWE", new LatLng(59.334591d, 18.06324d));
            put("data_hun-HUN", new LatLng(47.5268207d, 19.125982d));
        }
    };
    public static Gson gson = new Gson();
    private static LinkedHashMap<String, Boolean> innstallEnableMap = new LinkedHashMap<>();

    protected boolean enableBargeIn() {
        return true;
    }

    boolean isNeedToCopyAccentByDPVersion(String str, String str2) {
        return true;
    }

    public VoiceAssistantManager() {
        PublishSubject<Boolean> publishSubjectCreate = PublishSubject.create();
        this.cancelEvent = publishSubjectCreate;
        this.assistant_mode = InteractionMode.WUW;
        this.initialited = false;
        this.isVoiceActiveZonesLocked = false;
        this.isShortCutWakeup = false;
        this.isShowAfterExit = false;
        this.isVoiceAssistantIniting = new AtomicBoolean(false);
        this.isVoiceAssistantStared = new AtomicBoolean(false);
        this.isVoiceInitingStared = new AtomicBoolean(false);
        this.isVoiceAssistantStarting = new AtomicBoolean(false);
        this.mAsrLanguages = new ArrayList();
        this.mOtaUseLanguages = new ArrayList(Arrays.asList("data_eng-USA", "data_eng-AUS", "data_eng-GBR", "data_eng-XWW", "data_eng-SGP", "data_nor-NOR", "data_spa-XLA", "data_spa-ESP", "data_por-BRA", "data_por-PRT", "data_deu-DEU", "data_fra-FRA", "data_jpn-JPN", "data_tha-THA", "data_cmn-CHN", "data_ita-ITA", "data_yue-HKG", "data_kor-KOR", "data_tur-TUR", "data_dan-DNK", "data_nld-NLD", "data_swe-SWE", "data_heb-ISR"));
        this.voiceLanuageDpList = new ArrayList();
        this.voiceLanuageNaviDpList = new ArrayList();
        this.voiceLanuageSettingList = new ArrayList();
        this.voiceLanuageFailList = new ArrayList();
        this.voiceLanuageInstallList = new ArrayList();
        this.shortCutList = new ArrayList();
        this.mAsrDialogList = new LinkedBlockingQueue();
        this.asrDialogFileListMap = new HashMap();
        this.dialogEvent = DialogEvent.Event.CONVERSATION_END;
        this.voiceId = null;
        this.dataPath = "";
        this.compatibleDataPath = "";
        this.failCountDown = 0;
        this.vehicleType = 255;
        this.isHardKeyPTTEvent = false;
        this.isVirtualKeyPTTEvent = false;
        this.isAutoBatchStart = false;
        this.isAudioRecordStart = false;
        this.isAudioExportLog = false;
        this.isFanSppedReduced = false;
        this.isAvmForground = false;
        this.autoBatchProcessPCMThread = new Thread(new Runnable() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda50
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m177lambda$new$1$combydvrassistantmanagerVoiceAssistantManager();
            }
        });
        this.engCodeAry = Arrays.asList(60, 61, 64, 65, 44, 1, 45, 46, 358, 353, 30, 1242);
        this.spaCodeAry = Arrays.asList(1, 34, 52, 56, 57, 506, 598, 1890, 593, 595, 591, 507, 599, 502);
        this.porCodeAry = Arrays.asList(55, 244, 351);
        this.commonViewModel = (CommonViewModel) ViewModelHelper.getAppViewModel(CommonViewModel.class);
        this.iswakeupbroadcasted = false;
        this.isEventSuccess = new CountDownLatch(1);
        this.autoBatchProcessPCMThread.setDaemon(true);
        this.autoBatchProcessPCMThread.setName("autoBatchProcess");
        this.autoBatchProcessPCMThread.start();
        publishSubjectCreate.throttleFirst(1L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda51
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                this.f$0.sendCancelPTTEventActually(((Boolean) obj).booleanValue());
            }
        }, new Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda52
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Log.e(VoiceAssistantManager.TAG, "sendCancelPTTEventActually: onError", (Throwable) obj);
            }
        });
    }

    public boolean isInitialited() {
        return this.initialited;
    }

    public boolean getIsVoiceAssistantStared() {
        return this.isVoiceAssistantStared.get();
    }

    public List<VoiceLanuageDp> getVoiceLanuageDpList() {
        return this.voiceLanuageDpList;
    }

    public List<VoiceLanuageDp> getVoiceLanuageFailList() {
        return this.voiceLanuageFailList;
    }

    public List<VoiceLanuageDp> getVoiceLanuageSettingList() {
        return this.voiceLanuageSettingList;
    }

    public void setVoiceLanuageSettingList(VoiceLanuageDp voiceLanuageDp) {
        this.voiceLanuageSettingList.add(voiceLanuageDp);
        String json = gson.toJson(this.voiceLanuageSettingList);
        RLog.e(TAG, "voiceLanuageSettingJsons:" + json);
        Settings.System.putString(this.context.getContentResolver(), "KEY_LANGUAG_DPSETINGS", json);
    }

    public void removeInstallEnableItem(String str) {
        innstallEnableMap.remove(str);
        RLog.e(TAG, "removeInstallEnableItem:" + innstallEnableMap.toString());
    }

    public Integer getInstallEnableSize() {
        RLog.e(TAG, "getInstallEnableSize:" + innstallEnableMap.toString());
        return Integer.valueOf(innstallEnableMap.size());
    }

    public void addInstallEnableItem(String str) {
        innstallEnableMap.put(str, true);
        RLog.e(TAG, "addInstallEnableItem:" + innstallEnableMap.toString());
    }

    /* JADX INFO: renamed from: lambda$new$1$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m177lambda$new$1$combydvrassistantmanagerVoiceAssistantManager() {
        String str;
        String strTake;
        while (true) {
            try {
                str = TAG;
                RLog.i(str, "autoBatch data take:" + this.mAsrDialogList.size());
                RLog.i(str, "pid:" + Thread.currentThread().getId() + ",name:" + Thread.currentThread().getName());
                strTake = this.mAsrDialogList.take();
                RLog.i(str, "mAsrDialogList take:" + strTake);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isAutoBatchStart() && (this.asrDialogFileListMap.containsKey(strTake) || this.asrDialogFileListMap.containsKey(strTake + ".pcm"))) {
                RLog.i(str, "asrDialogFileListMap has:" + strTake);
                String str2 = this.asrDialogFileListMap.get(strTake);
                RLog.i(str, "asrDialogFile :" + str2);
                EventBus.getDefault().post(new AutoRecorderEvent(0, str2));
            }
        }
    }

    public int getFailCountDown() {
        return this.failCountDown;
    }

    public void setFailCountDown(int i) {
        this.failCountDown = i;
    }

    public boolean isAudioExportLog() {
        return this.isAudioExportLog;
    }

    public List<VoiceLanuageDp> getVoiceLanuageInstallList() {
        return this.voiceLanuageInstallList;
    }

    public void setVoiceLanuageInstallList(List<VoiceLanuageDp> list) {
        this.voiceLanuageInstallList = list;
    }

    public boolean getIsVoiceAssistantIniting() {
        return this.isVoiceAssistantIniting.get();
    }

    public void setIsVoiceAssistantIniting(boolean z) {
        this.isVoiceAssistantIniting.set(z);
        RLog.i(TAG, "setIsVoiceAssistantIniting:" + z);
    }

    public boolean getIsVoiceAssistantStarting() {
        return this.isVoiceAssistantStarting.get();
    }

    public void setIsVoiceAssistantStarting(boolean z) {
        this.isVoiceAssistantStarting.set(z);
        RLog.i(TAG, "isVoiceAssistantStarting:" + z);
    }

    public boolean getShowGuideResult() {
        int size = get().getAsrLanguages().size();
        boolean zAnyMatch = get().getVoiceLanuageDpList().stream().anyMatch(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda14
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return VoiceAssistantManager.lambda$getShowGuideResult$2((VoiceLanuageDp) obj);
            }
        });
        RLog.e(TAG, "asrLanguages.size:" + size + ",usedLanguage:" + zAnyMatch);
        return !zAnyMatch;
    }

    static /* synthetic */ boolean lambda$getShowGuideResult$2(VoiceLanuageDp voiceLanuageDp) {
        return voiceLanuageDp.getUsed() == 1;
    }

    public void setFanSppedReduced() {
        RLog.d(TAG, "degree fan speed");
        HvacHelper.getInstance().reduceOrRestoreWindSpeed(true);
        this.isFanSppedReduced = true;
    }

    public void setFanSppedRaise() {
        RLog.d(TAG, "raise fan speed :" + this.isFanSppedReduced);
        if (this.isFanSppedReduced) {
            HvacHelper.getInstance().reduceOrRestoreWindSpeed(false);
            this.isFanSppedReduced = false;
        }
    }

    public DialogEvent.Event getDialogEvent() {
        return this.dialogEvent;
    }

    public void setDialogEvent(DialogEvent.Event event) {
        this.dialogEvent = event;
        Settings.System.putString(this.context.getContentResolver(), "KEY_VOICE_DIALOGEVENT", event.name());
    }

    public void setAudioExportLog(boolean z) {
        this.isAudioExportLog = z;
    }

    public boolean isAutoBatchStart() {
        return this.isAutoBatchStart;
    }

    public void setAutoBatchStart(boolean z) {
        File[] fileArrListFiles;
        this.isAutoBatchStart = z;
        this.asrDialogFileListMap.clear();
        this.mAsrDialogList.clear();
        if (this.isAutoBatchStart && (fileArrListFiles = new File(Constant.AUTO_RUN_PATH).listFiles()) != null && fileArrListFiles.length > 0) {
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (!fileArrListFiles[i].isDirectory() && fileArrListFiles[i].getName().endsWith("pcm")) {
                    RLog.e(TAG, "autoRunPathSubFiles : " + fileArrListFiles[i].getName());
                    this.asrDialogFileListMap.put(fileArrListFiles[i].getName(), fileArrListFiles[i].getAbsolutePath());
                }
            }
        }
        EventBus.getDefault().post(new AutoRecorderEvent(1, ""));
    }

    public void setAvmForground(boolean z) {
        RLog.e(TAG, "setAvmForground:" + z);
        this.isAvmForground = z;
    }

    public int getVoiceLockMask() {
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), Constant.KEY_REGISTER_SET_RESULT);
        int iConvertVoiceLockMask = get().convertVoiceLockMask(string);
        RLog.i(TAG, "getvoiceLockMask record= " + string + ", mask:" + iConvertVoiceLockMask);
        return iConvertVoiceLockMask;
    }

    public boolean isAvmForground() {
        RLog.e(TAG, "isAvmForground:" + this.isAvmForground);
        return this.isAvmForground;
    }

    public boolean isAudioRecordStart() {
        return this.isAudioRecordStart;
    }

    public void setAudioRecordStart(boolean z) {
        this.isAudioRecordStart = z;
    }

    public void setShowAfterExit(boolean z) {
        this.isShowAfterExit = z;
        RLog.e(TAG, "setShowAfterExit:" + this.isShowAfterExit);
    }

    public BlockingQueue<String> getAsrDialogList() {
        return this.mAsrDialogList;
    }

    public Map<String, String> getAsrDialogFileListMap() {
        return this.asrDialogFileListMap;
    }

    public void setAsrDialogList(List<String> list) {
        this.mAsrDialogList = this.mAsrDialogList;
    }

    public void setAsrDialogList(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        ((List) Arrays.stream(str.split(",")).collect(Collectors.toList())).forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda39
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m180x8942c636((String) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setAsrDialogList$3$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m180x8942c636(String str) {
        this.mAsrDialogList.offer(str);
    }

    public int getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(int i) {
        this.vehicleType = i;
    }

    public boolean isHardKeyPTTEvent() {
        return this.isHardKeyPTTEvent;
    }

    public void setHardKeyPTTEvent(boolean z) {
        this.isHardKeyPTTEvent = z;
    }

    public boolean isVirtualKeyPTTEvent() {
        return this.isVirtualKeyPTTEvent;
    }

    public void setVirtualKeyPTTEvent(boolean z) {
        this.isVirtualKeyPTTEvent = z;
    }

    public boolean isVoiceActiveZonesLocked() {
        return this.isVoiceActiveZonesLocked;
    }

    public void setVoiceActiveZonesLocked(boolean z) {
        RLog.i(TAG, "setVoiceActiveZonesLocked : " + z);
        this.isVoiceActiveZonesLocked = z;
    }

    public Locale getCountryLocale() {
        return this.countryLocale;
    }

    public void setCountryLocale(Locale locale) {
        this.countryLocale = locale;
    }

    public List<String> getAsrLanguages() {
        return this.mAsrLanguages;
    }

    public static VoiceAssistantManager get() {
        if (mInstance == null) {
            synchronized (VoiceAssistantManager.class) {
                if (mInstance == null) {
                    mInstance = new VoiceAssistantManager();
                }
            }
        }
        return mInstance;
    }

    protected void setDemoType() {
        DataManager.get().setType(DemoType.custom);
        DataManager.get().setEnableWordStreaming(true);
    }

    public void uninit() {
        stopVoiceAssistant();
        destroyVoiceAssistant();
        BydLocationHelper.stopLocationUpdates();
    }

    public final void runOnUiThreadDelayEx(Runnable runnable, int i) {
        new Handler(this.context.getMainLooper()).postDelayed(runnable, i);
    }

    public void reinit() {
        if (!isInitialited()) {
            RLog.e(TAG, "reinit: isInitialited = false ,return");
            return;
        }
        if (get().getIsVoiceAssistantIniting() || get().getIsVoiceAssistantStarting()) {
            RLog.e(TAG, "reinit: isIniting:" + get().getIsVoiceAssistantIniting() + "isStarting:" + get().getIsVoiceAssistantStarting());
            return;
        }
        destroyVoiceAssistant();
        initLanguageDatas();
        runOnUiThreadDelayEx(new Runnable() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda37
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m179xc4ca2516();
            }
        }, 50);
    }

    /* JADX INFO: renamed from: lambda$reinit$4$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m179xc4ca2516() {
        try {
            initVoiceAssistant();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean getAvailableLangs(String str) {
        return ArkAssistant.getAvailableAsrLanguages(str).size() > 0;
    }

    public void initLanguageDatas() {
        if (!isInitialited()) {
            RLog.e(TAG, "initLanguageDatas: isInitialited = false ,return");
            return;
        }
        SystemPropertiesUtils.set(Constant.PROP_CHANGE_NAVI_AUTH, "1");
        String str = TAG;
        RLog.i(str, "initLanguageDatas ....");
        this.context.getResources();
        this.countryLocale = Resources.getSystem().getConfiguration().getLocales().get(0);
        RLog.e(str, "initLanguageDatas countryLocale:" + this.countryLocale.getLanguage());
        RLog.e(str, "initLanguageDatas mAsrLanguages:" + this.mAsrLanguages.toString());
        this.mAsrLanguages.clear();
        this.voiceLanuageDpList.clear();
        this.voiceLanuageNaviDpList.clear();
        this.voiceLanuageInstallList.clear();
        DataUtils.getPlatformDataPackPathEx();
        String platformUSADataPackPathEx = DataUtils.getPlatformUSADataPackPathEx();
        if (getAvailableLangs(platformUSADataPackPathEx)) {
            this.mAsrLanguages.add(platformUSADataPackPathEx);
            VoiceLanuageDp voiceLanuageDp = new VoiceLanuageDp("data_eng-USA", DataUtils.getPlatformUSADataPackPathEx());
            voiceLanuageDp.setVersion(getDataPackVersion(DataUtils.getPlatformUSADataPackPathEx()));
            this.voiceLanuageDpList.add(voiceLanuageDp);
        }
        File file = new File(DataUtils.getPlatformDataPackPathEx());
        if (!file.exists()) {
            RLog.i("NEU_File", "create dir ,result=" + file.mkdirs());
        }
        File[] fileArrListFiles = file.listFiles();
        RLog.i(str, "user dataPath:" + this.dataPath);
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isDirectory() && !fileArrListFiles[i].getName().endsWith("_bk") && !fileArrListFiles[i].getName().contains("data_eng-USA")) {
                    String str2 = TAG;
                    RLog.e(str2, "dataPathSubFiles : " + fileArrListFiles[i].getAbsolutePath());
                    if (getAvailableLangs(fileArrListFiles[i].getAbsolutePath())) {
                        RLog.e(str2, "mAsrLanguages : " + fileArrListFiles[i].getAbsolutePath());
                        this.mAsrLanguages.add(fileArrListFiles[i].getAbsolutePath());
                        VoiceLanuageDp voiceLanuageDp2 = new VoiceLanuageDp(fileArrListFiles[i].getName(), fileArrListFiles[i].getAbsolutePath());
                        voiceLanuageDp2.setVersion(getDataPackVersion(fileArrListFiles[i].getAbsolutePath()));
                        if (this.mOtaUseLanguages.contains(voiceLanuageDp2.getName())) {
                            this.voiceLanuageDpList.add(voiceLanuageDp2);
                        }
                    }
                }
            }
        }
        String platformDataPackPathEx = DataUtils.getPlatformDataPackPathEx();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
                if (fileArrListFiles[i2].isDirectory() && fileArrListFiles[i2].getAbsolutePath().contains(platformDataPackPathEx)) {
                    String str3 = TAG;
                    RLog.e(str3, "navi dataPathSubFiles : " + fileArrListFiles[i2].getAbsolutePath());
                    if (ArkAssistant.getAvailableAsrLanguages(fileArrListFiles[i2].getAbsolutePath()).size() > 0) {
                        RLog.e(str3, "navi mAsrLanguages : " + fileArrListFiles[i2].getAbsolutePath());
                        this.mAsrLanguages.add(fileArrListFiles[i2].getAbsolutePath());
                        VoiceLanuageDp voiceLanuageDp3 = new VoiceLanuageDp(fileArrListFiles[i2].getName(), fileArrListFiles[i2].getAbsolutePath());
                        voiceLanuageDp3.setVersion(getDataPackVersion(fileArrListFiles[i2].getAbsolutePath()));
                        this.voiceLanuageNaviDpList.add(voiceLanuageDp3);
                    }
                }
            }
        }
        RLog.e(TAG, "init voiceLanuageDpJsons:" + gson.toJson(this.voiceLanuageDpList));
    }

    public void init() throws FileNotFoundException {
        setDemoType();
        this.mControllerFactory = new ControllerFactory();
        this.shortCutList.clear();
        String str = TAG;
        RLog.i(str, "VoiceAssistantManager init ....");
        this.initialited = true;
        try {
            initLanguageDatas();
            initShortCutCommandValue();
            initSubWUWValue();
            RLog.i(str, "not support ShortCut");
            deleteShortCutCommandsAll();
            initDLDHEnabled();
            initAudioWakeupCommands();
            initVoiceAcvtiveZone();
            processVoiceLockZone();
            registerAssistantListener();
            if (Settings.System.getInt(this.context.getContentResolver(), "DEBUG_AUDIO_OUTPUT", 0) == 1) {
                Logger.initialize(Logger.Config.builder(this.context).setLogConsoleEnable(true).setLogFileEnable(true).setMaxLogSize(52428800L).setSizePerLogFile(10485760L).build());
            }
            if (SystemSettingManager.get().getPowerCtlStatus()) {
                initVoiceAssistant();
                SpotifyManager.get().init(new CountDownLatch(1));
                AmazonManager.get().init(null);
                KaraokeManager.get().init();
            } else {
                RLog.i(str, "acc off can't initVoiceAssistant  ....");
            }
        } catch (FileNotFoundException e) {
            RLog.e(TAG, "initVoiceAssistant error.", e);
        }
        RLog.i(TAG, "VoiceAssistantManager init done ....");
    }

    public void initDLDHEnabled() {
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_CONTINUE_DIALOG");
        if (string == null) {
            Settings.System.putString(this.context.getContentResolver(), "KEY_CONTINUE_DIALOG", "true");
            string = "true";
        }
        RLog.e(TAG, "KEY_CONTINUE_DIALOG:" + string);
        DataManager.get().setIsEnableDLDH(string.equals("true"));
    }

    public void setDLDHEnabled() {
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_CONTINUE_DIALOG");
        if (string == null) {
            string = "true";
        }
        RLog.e(TAG, "KEY_CONTINUE_DIALOG:" + string);
        DataManager.get().setIsEnableDLDH(string.equals("true"));
        ArkAssistant.get().sendAppEvent(new SetSnoozingEvent.Builder().setSnoozingDuration(string.equals("true") ? 10000 : 0).build(), null);
    }

    public String getDataPackVersion(String str) {
        String dpVersion = getDpVersion(str);
        if (!TextUtils.isEmpty(dpVersion)) {
            RLog.e(TAG, str + "-----getDpVersion : " + dpVersion);
            return dpVersion;
        }
        String vrVersion = DomainsAutoController.getVrVersion(str);
        String string = "";
        try {
            JSONObject jSONObject = new JSONObject(vrVersion);
            if (!jSONObject.has("vr_dp_version")) {
                return "";
            }
            string = jSONObject.getString("vr_dp_version");
            RLog.e(TAG, str + "-----getDataPackVersion : " + string);
            return string;
        } catch (JSONException e) {
            RLog.e(TAG, "read vr_dp_version error [" + vrVersion + "]", e);
            return string;
        }
    }

    private String getDpVersion(final String str) {
        Optional<VoiceLanuageDp> optionalFindAny;
        VoiceLanuageDp voiceLanuageDp;
        List<VoiceLanuageDp> list = this.voiceLanuageDpList;
        if (list == null || list.size() <= 0 || (optionalFindAny = this.voiceLanuageDpList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return str.equals(((VoiceLanuageDp) obj).getPath());
            }
        }).findAny()) == null || !optionalFindAny.isPresent() || (voiceLanuageDp = optionalFindAny.get()) == null) {
            return null;
        }
        return voiceLanuageDp.getVersion();
    }

    protected void notifyContentChanged() {
        ArkAssistant.get().notifyContentChanged(ContentType.CONTACT);
        ArkAssistant.get().notifyContentChanged(ContentType.MUSIC);
        ArkAssistant.get().notifyContentChanged(ContentType.APP);
    }

    public IPrompter getPrompter() {
        return this.prompter;
    }

    public String getLanguage() {
        RLog.i(TAG, "getLanguage:" + SPUtils.getInstance().getString(Constant.SP_KEY_CUR_LANGUAGE));
        return SPUtils.getInstance().getString(Constant.SP_KEY_CUR_LANGUAGE);
    }

    static /* synthetic */ boolean lambda$filterEngVoiceDp$6(String str, VoiceLanuageDp voiceLanuageDp) {
        return voiceLanuageDp.getUsed() == 1 && voiceLanuageDp.getName().contains(str);
    }

    void filterEngVoiceDp() {
        VoiceLanuageDp voiceLanuageDp;
        final String str = "data_eng";
        if (this.voiceLanuageDpList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda24
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return VoiceAssistantManager.lambda$filterEngVoiceDp$6(str, (VoiceLanuageDp) obj);
            }
        }).findAny().isPresent()) {
            RLog.i(TAG, "eng used findAny().isPresent");
            voiceLanuageDp = this.voiceLanuageDpList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda25
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VoiceAssistantManager.lambda$filterEngVoiceDp$7(str, (VoiceLanuageDp) obj);
                }
            }).findAny().get();
        } else {
            RLog.i(TAG, "eng used findAny() not isPresent");
            voiceLanuageDp = this.voiceLanuageDpList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda26
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((VoiceLanuageDp) obj).getName().contains(str);
                }
            }).findAny().isPresent() ? this.voiceLanuageDpList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda27
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((VoiceLanuageDp) obj).getName().contains(str);
                }
            }).findAny().get() : null;
        }
        this.voiceLanuageDpList.removeIf(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda28
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((VoiceLanuageDp) obj).getName().contains(str);
            }
        });
        if (voiceLanuageDp != null) {
            this.voiceLanuageDpList.add(voiceLanuageDp);
        }
    }

    static /* synthetic */ boolean lambda$filterEngVoiceDp$7(String str, VoiceLanuageDp voiceLanuageDp) {
        return voiceLanuageDp.getUsed() == 1 && voiceLanuageDp.getName().contains(str);
    }

    void filterSpaAndPorByCountryCode() {
        RLog.e(TAG, "countryCode : " + CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext()));
    }

    public void setLanguage(final String str) {
        String str2 = TAG;
        RLog.i(str2, "setLanguage:" + str);
        if (!str.isEmpty()) {
            SPUtils.getInstance().put(Constant.SP_KEY_CUR_LANGUAGE, str, true);
        }
        final boolean zContains = str.contains("data_eng");
        this.voiceLanuageDpList.stream().forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                VoiceAssistantManager.lambda$setLanguage$11(str, zContains, (VoiceLanuageDp) obj);
            }
        });
        filterSpaAndPorByCountryCode();
        String json = gson.toJson(this.voiceLanuageDpList);
        RLog.e(str2, "voiceLanuageDpJsons:" + json);
        Settings.System.putString(this.context.getContentResolver(), "KEY_LANGUAG_DPS", json);
        String json2 = gson.toJson(this.voiceLanuageNaviDpList);
        RLog.e(str2, "voiceLanuageNaviDpJsons:" + json2);
        Settings.System.putString(this.context.getContentResolver(), "KEY_LANGUAG_NAVIDPS", json2);
        Settings.System.putString(this.context.getContentResolver(), "SP_KEY_CUR_LANGUAGE", str);
    }

    static /* synthetic */ void lambda$setLanguage$11(String str, boolean z, VoiceLanuageDp voiceLanuageDp) {
        if (voiceLanuageDp.getPath().equals(str) && !str.isEmpty()) {
            voiceLanuageDp.setUsed(1);
            RLog.i(TAG, "used Language:" + str);
        } else {
            voiceLanuageDp.setUsed(0);
        }
        if (z && voiceLanuageDp.getPath().contains(str)) {
            RLog.i(TAG, "eng tones used Language:" + str);
            voiceLanuageDp.setUsed(1);
        }
        if (voiceLanuageDp.getPath().contains("data_eng")) {
            String str2 = TAG;
            RLog.i(str2, "1 set name USA :" + voiceLanuageDp.getName());
            voiceLanuageDp.setName("data_eng-USA");
            RLog.i(str2, "2 set name USA :" + voiceLanuageDp.getName());
        }
    }

    private List<String> filterVoiceId(List<TtsVoice> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (TtsVoice ttsVoice : list) {
                arrayList.add("[" + ttsVoice.getVoiceName() + "] - [" + ttsVoice.getGender() + "] - [" + ttsVoice.getLanguage() + "]");
            }
        }
        return arrayList;
    }

    public void setUserDataRetentionEnabled() {
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_USER_IMPROVEMENT");
        if (string == null) {
            string = "false";
        }
        RLog.e(TAG, "KEY_USER_IMPROVEMENT:" + string);
        ArkAssistant.get().setUserDataRetentionEnabled(string.equals("true"));
    }

    public void deleteUserCloudSessionData() {
        RLog.e(TAG, "deleteUserCloudSessionData");
        new Thread(new Runnable() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m168x362ce2ed();
            }
        }).start();
    }

    /* JADX INFO: renamed from: lambda$deleteUserCloudSessionData$12$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m168x362ce2ed() {
        DomainsAutoController.deleteSession(getLanguage(), ActiveManager.get().getUserId(), ActiveManager.get().getDeviceId());
    }

    protected void initVoicePrompter() {
        if (ArkAssistant.get().getAssistantState() != AssistantState.UNINITIALIZED) {
            List<String> availablePrompterNames = ArkAssistant.get().getAvailablePrompterNames();
            String str = TAG;
            RLog.e(str, "prompterNameList:" + availablePrompterNames.toString());
            if (availablePrompterNames.isEmpty()) {
                return;
            }
            String str2 = availablePrompterNames.get(0);
            RLog.e(str, "prompterNameList 0:" + str2);
            this.prompter = ArkAssistant.get().createPrompter(str2, 16);
        }
    }

    String getLanguageDataPath(String str, String str2) {
        String platformUSADataPackPathEx;
        RLog.e(TAG, "getLanguageDataPath suffix :" + str + ",defaultsufx:" + str2);
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= this.mAsrLanguages.size()) {
                platformUSADataPackPathEx = "";
                break;
            }
            if (this.mAsrLanguages.get(i2).contains(str)) {
                platformUSADataPackPathEx = this.mAsrLanguages.get(i2);
                break;
            }
            i2++;
        }
        RLog.e(TAG, "0 getLanguageDataPath:" + platformUSADataPackPathEx);
        if (platformUSADataPackPathEx.isEmpty()) {
            while (true) {
                if (i >= this.mAsrLanguages.size()) {
                    break;
                }
                if (this.mAsrLanguages.get(i).contains(str2)) {
                    platformUSADataPackPathEx = this.mAsrLanguages.get(i);
                    break;
                }
                i++;
            }
        }
        String str3 = TAG;
        RLog.e(str3, "1 getLanguageDataPath:" + platformUSADataPackPathEx);
        if (platformUSADataPackPathEx.isEmpty()) {
            platformUSADataPackPathEx = DataUtils.getPlatformUSADataPackPathEx();
        }
        RLog.e(str3, "2 getLanguageDataPath:" + platformUSADataPackPathEx);
        return platformUSADataPackPathEx;
    }

    public String getLanguageDataPathSuffixEx(String str) {
        String str2 = TAG;
        RLog.e(str2, "getLanguageDataPathSuffixEx  suffix" + str);
        String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
        RLog.e(str2, "countryCode : " + countryCode);
        RLog.e(str2, "language:" + this.countryLocale.getLanguage().toLowerCase() + ",local:" + this.countryLocale + ",country:" + this.countryLocale.getCountry());
        if (str.contains("eng")) {
            this.dataPath = DataUtils.getPlatformUSADataPackPathEx();
            if (countryCode.isEmpty()) {
                return this.dataPath;
            }
            chooseEnglishAccentByCountryCode(Integer.parseInt(countryCode.trim()), true);
        }
        return this.dataPath;
    }

    private String chooseSpanishByCountryCode(String str, boolean z) {
        String languageDataPath = getLanguageDataPath("data_spa-XLA", "data_spa-ESP");
        this.dataPath = languageDataPath;
        return languageDataPath;
    }

    private String choosePortugueseByCountryCode(String str, boolean z) {
        String languageDataPath = getLanguageDataPath("data_por-BRA", "data_por-PRT");
        this.dataPath = languageDataPath;
        return languageDataPath;
    }

    private boolean getRetryDataPath() {
        RLog.e(TAG, "getRetryDataPath");
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (this.dataPath.contains("data_spa-")) {
            if (this.voiceLanuageDpList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda30
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((VoiceLanuageDp) obj).getPath().contains("data_spa-");
                }
            }).count() >= 2) {
                this.voiceLanuageDpList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda31
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return this.f$0.m172xe20bae4f((VoiceLanuageDp) obj);
                    }
                }).findFirst().ifPresent(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda32
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.f$0.m173x2596cc10(atomicBoolean, (VoiceLanuageDp) obj);
                    }
                });
            }
        } else if (this.dataPath.contains("data_por-") && this.voiceLanuageDpList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda34
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((VoiceLanuageDp) obj).getPath().contains("data_por-");
            }
        }).count() >= 2) {
            this.voiceLanuageDpList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda35
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f$0.m174xacad0792((VoiceLanuageDp) obj);
                }
            }).findFirst().ifPresent(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda36
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.m175xf0382553(atomicBoolean, (VoiceLanuageDp) obj);
                }
            });
        }
        return atomicBoolean.get();
    }

    /* JADX INFO: renamed from: lambda$getRetryDataPath$14$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ boolean m172xe20bae4f(VoiceLanuageDp voiceLanuageDp) {
        return voiceLanuageDp.getName().contains("data_spa-") && !voiceLanuageDp.getPath().equals(this.dataPath);
    }

    /* JADX INFO: renamed from: lambda$getRetryDataPath$15$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m173x2596cc10(AtomicBoolean atomicBoolean, VoiceLanuageDp voiceLanuageDp) {
        this.dataPath = voiceLanuageDp.getPath();
        RLog.e(TAG, "spa getRetryDataPath : " + this.dataPath);
        atomicBoolean.set(true);
    }

    /* JADX INFO: renamed from: lambda$getRetryDataPath$17$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ boolean m174xacad0792(VoiceLanuageDp voiceLanuageDp) {
        return voiceLanuageDp.getName().contains("data_por-") && !voiceLanuageDp.getPath().equals(this.dataPath);
    }

    /* JADX INFO: renamed from: lambda$getRetryDataPath$18$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m175xf0382553(AtomicBoolean atomicBoolean, VoiceLanuageDp voiceLanuageDp) {
        this.dataPath = voiceLanuageDp.getPath();
        RLog.e(TAG, "por getRetryDataPath : " + this.dataPath);
        atomicBoolean.set(true);
    }

    private void chooseEnglishAccentByCountryCode(int i, boolean z) {
        if (this.engCodeAry.contains(Integer.valueOf(i))) {
            this.dataPath = DataUtils.getPlatformUSADataPackPathEx();
        }
        processAccentCopy2USA(false);
    }

    public String getLanguageDataPathSuffix(boolean z, boolean z2) {
        this.context.getResources();
        this.countryLocale = Resources.getSystem().getConfiguration().getLocales().get(0);
        this.dataPath = DataUtils.getPlatformUSADataPackPathEx();
        String str = TAG;
        RLog.e(str, "getLanguageDataPathSuffix copyaccent " + z2);
        String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
        RLog.e(str, "countryCode : " + countryCode);
        String lowerCase = this.countryLocale.getLanguage().toLowerCase();
        RLog.e(str, "system_language : " + lowerCase);
        if (lowerCase == null || lowerCase.isEmpty()) {
            lowerCase = "en";
        }
        if (z) {
            lowerCase = "en";
        }
        if (lowerCase.equals("en")) {
            this.dataPath = DataUtils.getPlatformUSADataPackPathEx();
            if (countryCode.isEmpty()) {
                return this.dataPath;
            }
            chooseEnglishAccentByCountryCode(Integer.parseInt(countryCode.trim()), z2);
        } else if (lowerCase.equals("nb")) {
            this.dataPath = getLanguageDataPath("data_nor-NOR", "data_eng-USA");
        } else if (lowerCase.equals("es")) {
            this.dataPath = chooseSpanishByCountryCode(countryCode, z2);
        } else if (lowerCase.equals("pt")) {
            this.dataPath = choosePortugueseByCountryCode(countryCode, z2);
        } else if (lowerCase.equals("de")) {
            this.dataPath = getLanguageDataPath("data_deu-DEU", "data_eng-USA");
        } else if (lowerCase.equals("fr")) {
            this.dataPath = getLanguageDataPath("data_fra-FRA", "data_eng-USA");
        } else if (lowerCase.equals("ja")) {
            this.dataPath = getLanguageDataPath("data_jpn-JPN", "data_eng-USA");
        } else if (lowerCase.equals("th")) {
            this.dataPath = getLanguageDataPath("data_tha-THA", "data_eng-USA");
        } else if (lowerCase.equals("it")) {
            this.dataPath = getLanguageDataPath("data_ita-ITA", "data_eng-USA");
        } else if (lowerCase.equals("zh")) {
            if (this.countryLocale.getCountry().equals("CN")) {
                this.dataPath = getLanguageDataPath("data_cmn-CHN", "data_eng-USA");
            } else if (this.countryLocale.getCountry().equals("TW") || this.countryLocale.getCountry().equals("HK")) {
                this.dataPath = getLanguageDataPath("data_yue-HKG", "data_eng-USA");
                if (Integer.parseInt(countryCode.trim()) == 886) {
                    this.dataPath = getLanguageDataPath("data_cmn-CHN", "data_eng-USA");
                }
            }
        } else if (lowerCase.equals("sv")) {
            this.dataPath = getLanguageDataPath("data_swe-SWE", "data_eng-USA");
        } else if (lowerCase.equals("ko")) {
            this.dataPath = getLanguageDataPath("data_kor-KOR", "data_eng-USA");
        } else if (lowerCase.equals("da")) {
            this.dataPath = getLanguageDataPath("data_dan-DNK", "data_eng-USA");
        } else if (lowerCase.equals("nl")) {
            this.dataPath = getLanguageDataPath("data_nld-NLD", "data_eng-USA");
        } else if (lowerCase.equals("pl")) {
            this.dataPath = getLanguageDataPath("data_pol-POL", "data_eng-USA");
        } else if (lowerCase.equals("fi")) {
            this.dataPath = getLanguageDataPath("data_fin-FIN", "data_eng-USA");
        } else if (lowerCase.equals("tr")) {
            this.dataPath = getLanguageDataPath("data_tur-TUR", "data_eng-USA");
        } else if (lowerCase.equals("iw")) {
            this.dataPath = getLanguageDataPath("data_heb-ISR", "data_eng-USA");
        } else if (lowerCase.equals("hu")) {
            this.dataPath = getLanguageDataPath("data_hun-HUN", "data_eng-USA");
        } else {
            this.dataPath = DataUtils.getPlatformUSADataPackPathEx();
            RLog.e(str, "get default usa" + this.dataPath);
        }
        return this.dataPath;
    }

    public void deleteShortCutCommandsAll() {
        try {
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "KEY_HOME_COMMANDS", "false");
            setShortCutCommandValue("KEY_HOME_COMMANDS", "false");
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "KEY_MUSIC_PLAYER", "false");
            setShortCutCommandValue("KEY_MUSIC_PLAYER", "false");
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "KEY_AC_CONTROL", "false");
            setShortCutCommandValue("KEY_AC_CONTROL", "false");
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "KEY_VOLUME_CONTROL", "false");
            setShortCutCommandValue("KEY_VOLUME_CONTROL", "false");
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "KEY_MAP_NAV", "false");
            setShortCutCommandValue("KEY_MAP_NAV", "false");
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), Constant.KEY_TEL_CONTROL, "true");
            setShortCutCommandValue(Constant.KEY_TEL_CONTROL, "false");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initAudioWakeupCommands() {
        try {
            String voiceSetting = SharedPreferencesUtil.getVoiceSetting(this.context, "KEY_AUDIO_WAKEUP");
            if (voiceSetting == null || voiceSetting.isEmpty()) {
                voiceSetting = "true";
                SharedPreferencesUtil.putVoiceSetting(this.context, "KEY_AUDIO_WAKEUP", "true");
                RLog.e(TAG, "initAudioWakeupCommands KEY_AUDIO_WAKEUP:".concat("true"));
            }
            Settings.System.putString(this.context.getContentResolver(), "KEY_AUDIO_WAKEUP", voiceSetting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processAccentCopy2USA(boolean z) {
        String str = TAG;
        RLog.e(str, "processAccentCopy2USA");
        File file = new File(this.dataPath);
        if (!this.dataPath.contains("eng-USA") || file.exists()) {
            return;
        }
        RLog.e(str, "search eng accent data");
        for (String str2 : this.mAsrLanguages) {
            if (str2.contains("eng-") && !str2.contains("USA")) {
                if (z) {
                    RLog.e(TAG, "eng accent data finded " + str2 + " copy 2 USA");
                    modifyDataPackAccent(str2, DataUtils.getPlatformDataPackPathEx() + "/data_eng-USA/", "eng-USA", "eng-USA");
                    return;
                } else {
                    RLog.e(TAG, "eng accent data finded, only use " + str2);
                    this.dataPath = str2;
                    return;
                }
            }
        }
    }

    private String processUSAAceentSuffix(int i) {
        if (this.engCodeAry.contains(Integer.valueOf(i))) {
            if (i == 44 || i == 353) {
                return "@eng-GBR";
            }
            if (i == 65 || i == 60) {
                return "@eng-SGP";
            }
            if (i == 61 || i == 64) {
                return "@eng-AUS";
            }
            if (i != 1 && i != 1242) {
                return "@eng-XWW";
            }
        } else if (i == 47) {
            return "@eng-XWW";
        }
        return "@eng-USA";
    }

    private String processPorAccentSuffix(int i) {
        return (this.porCodeAry.contains(Integer.valueOf(i)) && i == 55) ? "@por-BRA" : "@por-PRT";
    }

    private String processSpaAccentSuffix(int i) {
        return (!this.spaCodeAry.contains(Integer.valueOf(i)) || i == 34) ? "@spa-ESP" : "@spa-XLA";
    }

    public String getVoiceAccentSuffixBydCountry(String str, int i) {
        String strProcessSpaAccentSuffix = ((str.contains(Language.CMN_CHN) || str.contains(Language.YUE_HKG)) && i == 86) ? "@China" : "";
        if (str.contains("eng-USA")) {
            strProcessSpaAccentSuffix = processUSAAceentSuffix(i);
            RLog.e(TAG, "voice eng language suffix:" + strProcessSpaAccentSuffix);
        }
        if (str.contains("spa-XLA")) {
            strProcessSpaAccentSuffix = processSpaAccentSuffix(i);
            RLog.e(TAG, "voice spa language suffix:" + strProcessSpaAccentSuffix);
        }
        if (!str.contains("por-BRA")) {
            return strProcessSpaAccentSuffix;
        }
        String strProcessPorAccentSuffix = processPorAccentSuffix(i);
        RLog.e(TAG, "voice por language suffix:" + strProcessPorAccentSuffix);
        return strProcessPorAccentSuffix;
    }

    private void setCloudExtraHeadersAsr(String str) {
        String str2 = TAG;
        RLog.e(str2, "setCloudExtraHeadersAsr : " + str);
        if (TextUtils.isEmpty(str)) {
            RLog.e(str2, "setCloudExtraHeadersAsr err");
            return;
        }
        String strSubstring = str.substring(0, 7);
        if (str.contains("eng-")) {
            strSubstring = str.substring(str.length() - 7, str.length());
        }
        RLog.e(str2, "headersAsr : " + strSubstring);
        RLog.e(str2, "setCloudExtraHeadersAsr : " + ArkAssistant.get().overrideConfiguration("cloud_extra_headers_asr", "[\"cerence-language:" + strSubstring + "\"]"));
    }

    private boolean isAssistantInitialized() {
        AssistantState assistantState = ArkClient.get().getAssistantState();
        return assistantState == AssistantState.INITIALIZED || assistantState == AssistantState.RUNNING;
    }

    private boolean isAssistantStarted() {
        return ArkClient.get().getAssistantState() == AssistantState.RUNNING;
    }

    public void initVoiceAssistant() throws FileNotFoundException {
        String str;
        String voiceAccentSuffixBydCountry;
        String carMode;
        boolean z;
        File filesDir;
        String string = " -.--.----- ";
        String str2 = TAG;
        RLog.e(str2, "initVoiceAssistant: start");
        if (!isInitialited()) {
            RLog.e(str2, "initVoiceAssistant: isInitialited = false ,return");
            return;
        }
        if (this.isVoiceAssistantIniting.get() || this.isVoiceAssistantStarting.get()) {
            RLog.e(str2, "isVoiceAssistantIniting ：" + this.isVoiceAssistantIniting.get() + ",isVoiceAssistantStarting:" + this.isVoiceAssistantStarting.get());
            return;
        }
        setIsVoiceAssistantIniting(true);
        RLog.e(str2, "initVoiceAssistant");
        Context context = this.context;
        String absolutePath = (context == null || (filesDir = context.getFilesDir()) == null) ? "" : filesDir.getAbsolutePath();
        if (TextUtils.isEmpty(absolutePath)) {
            absolutePath = this.context.getFilesDir().getAbsolutePath();
        }
        if (deleteVoiceDataFirstRun()) {
            initLanguageDatas();
            get().recordDataPackaProcess("firstrun");
        }
        this.dialogEvent = DialogEvent.Event.CONVERSATION_END;
        Settings.System.putString(this.context.getContentResolver(), "KEY_VOICE_DIALOGEVENT", this.dialogEvent.name());
        this.dataPath = SPUtils.getInstance().getString(Constant.OTA_KEY_CUR_LANGUAGE, "");
        RLog.e(str2, "OTA TEST dataPath:" + this.dataPath);
        String str3 = this.dataPath;
        if (str3 == null || str3.isEmpty()) {
            this.dataPath = getLanguageDataPathSuffix(false, true);
            RLog.e(str2, "system dataPath:" + this.dataPath);
        } else if (getInstallEnableSize().intValue() > 0) {
            final String str4 = (String) innstallEnableMap.entrySet().stream().findFirst().map(new Function() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return (String) ((Map.Entry) obj).getKey();
                }
            }).orElse(null);
            boolean zAnyMatch = get().getVoiceLanuageDpList().stream().anyMatch(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda46
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((VoiceLanuageDp) obj).getPath().equals(str4);
                }
            });
            if (!TextUtils.isEmpty(str4) && zAnyMatch) {
                this.dataPath = str4;
                removeInstallEnableItem(str4);
                RLog.e(str2, "getInstallEnableSize()>0----frstKey:" + str4);
            } else {
                getDataPathIsSpcial();
            }
        } else {
            getDataPathIsSpcial();
        }
        RLog.e(str2, "failCountDown : " + this.failCountDown);
        int i = this.failCountDown;
        if (i > 2 && i < 4) {
            String string2 = SPUtils.getInstance().getString(Constant.OTA_KEY_CUR_LANGUAGE, "");
            String string3 = SPUtils.getInstance().getString(Constant.OTA_KEY_SUC_LANGUAGE, "");
            if (!string2.isEmpty() && !string3.isEmpty() && this.dataPath.equals(string2) && !string2.equals(string3)) {
                RLog.e(str2, "ota_cur_path : " + string2 + " fail retry ota_suc_path : " + string3);
                SPUtils.getInstance().put(Constant.OTA_KEY_CUR_LANGUAGE, string3, true);
                this.dataPath = string3;
            }
        }
        if (this.failCountDown >= 4 && !this.dataPath.equals(DataUtils.getPlatformUSADataPackPathEx())) {
            this.dataPath = DataUtils.getPlatformUSADataPackPathEx();
            SPUtils.getInstance().put(Constant.OTA_KEY_CUR_LANGUAGE, "", true);
            SPUtils.getInstance().put(Constant.OTA_KEY_SUC_LANGUAGE, "", true);
        }
        int i2 = this.failCountDown;
        if (i2 > 0 && i2 <= 2 && !this.dataPath.equals(DataUtils.getPlatformUSADataPackPathEx())) {
            RLog.e(str2, "getRetryDataPath:" + getRetryDataPath());
        }
        File file = new File(this.dataPath);
        if (!file.exists()) {
            RLog.e(str2, "BYDAssistant dataPath not exist =>" + this.dataPath);
            if (!this.dataPath.equals(DataUtils.getPlatformUSADataPackPathEx())) {
                SPUtils.getInstance().put(Constant.OTA_KEY_CUR_LANGUAGE, "", true);
                SPUtils.getInstance().put(Constant.OTA_KEY_SUC_LANGUAGE, "", true);
                this.dataPath = DataUtils.getPlatformUSADataPackPathEx();
                file = new File(this.dataPath);
            }
        }
        setLanguage(this.dataPath);
        String strStartProcessVoiceDataPackCompatible = LongTermManager.startProcessVoiceDataPackCompatible(this.context, this.dataPath, new File(this.dataPath).getName().substring(5));
        if (strStartProcessVoiceDataPackCompatible != null && !strStartProcessVoiceDataPackCompatible.isEmpty() && strStartProcessVoiceDataPackCompatible != "null") {
            RLog.e(str2, "voiceDataPath genereat:" + strStartProcessVoiceDataPackCompatible);
            this.compatibleDataPath = strStartProcessVoiceDataPackCompatible;
        } else {
            RLog.e(str2, "voiceDataPath need not genereat:");
            this.compatibleDataPath = "";
        }
        if (this.isAudioExportLog) {
            RLog.e(str2, "isAudioExportLog:" + this.isAudioExportLog);
            String platformExportLogPath = DataUtils.getPlatformExportLogPath();
            File file2 = new File(platformExportLogPath);
            if (!file2.exists()) {
                RLog.e(str2, "mkdirs  " + platformExportLogPath + " : " + file2.mkdirs());
            }
        } else {
            RLog.e(str2, "deleteDir  " + DataUtils.getPlatformExportLogPath() + " : " + DataUtils.deleteDir(new File(DataUtils.getPlatformExportLogPath())));
        }
        String str5 = absolutePath + File.separator + "file";
        File file3 = new File(str5);
        if (!file3.exists()) {
            RLog.e(str2, "writepath mkdir :" + file3.mkdirs());
        }
        String deviceId = ActiveManager.get().getDeviceId();
        String userId = ActiveManager.get().getUserId();
        RLog.e(str2, "voiceId:" + Base64Utils.getBase64(this.voiceId) + ",devcieId=" + deviceId + ",userId=" + userId);
        final int vehicleType = SystemSettingManager.get().getVehicleType(this.context);
        Settings.System.putInt(BydVoiceApplication.getContext().getContentResolver(), "KEY_VEHICLE_TYPE", vehicleType);
        Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "KEY_DEVICE_ID", deviceId);
        Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "KEY_USER_ID", userId);
        if (!file.exists()) {
            setIsVoiceAssistantIniting(false);
            EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.VOICE_INIT_FAIL), true));
        }
        RLog.e(str2, "dataPath:" + this.dataPath);
        RLog.e(str2, "writePath:" + str5);
        List<String> availableAsrLanguages = ArkAssistant.getAvailableAsrLanguages(this.dataPath);
        String vrVersion = DomainsAutoController.getVrVersion(this.dataPath);
        try {
            JSONObject jSONObject = new JSONObject(vrVersion);
            if (jSONObject.has("vr_sdk_version")) {
                string = jSONObject.getString("vr_sdk_version");
            }
        } catch (JSONException e) {
            RLog.e(TAG, "read vr_sdk_version error [" + vrVersion + "]", e);
        }
        RLog.e(TAG, "vr_sdk_version:" + string);
        try {
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "KEY_VOICE_VERSION", string);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
        String str6 = TAG;
        RLog.e(str6, "countryCode : " + countryCode);
        int i3 = countryCode != null ? Integer.parseInt(countryCode.trim()) : 1;
        if (availableAsrLanguages.size() > 0) {
            str = availableAsrLanguages.get(0);
            voiceAccentSuffixBydCountry = getVoiceAccentSuffixBydCountry(str, i3);
        } else {
            str = "";
            voiceAccentSuffixBydCountry = str;
        }
        RLog.e(str6, "language:" + str);
        if (str == null || str.isEmpty()) {
            RLog.e(str6, "the language can not be empty");
            setIsVoiceAssistantIniting(false);
            EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.VOICE_INIT_FAIL), true));
            return;
        }
        List<TtsVoice> availableTtsVoices = ArkAssistant.getAvailableTtsVoices(this.dataPath, str);
        availableTtsVoices.forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda47
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                RLog.e(VoiceAssistantManager.TAG, "availableTtsVoices:" + Base64Utils.getBase64(((TtsVoice) obj).getVoiceId()));
            }
        });
        filterVoiceId(availableTtsVoices).forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                RLog.e(VoiceAssistantManager.TAG, "voices:" + Base64Utils.getBase64((String) obj));
            }
        });
        if (!availableTtsVoices.isEmpty()) {
            SPUtils.getInstance().put(Constant.SP_KEY_CUR_VOICE_ID, availableTtsVoices.get(0).getVoiceId());
            RLog.e(str6, "SP_KEY_CUR_VOICE_ID:" + Base64Utils.getBase64(availableTtsVoices.get(0).getVoiceId()));
        }
        String string4 = SPUtils.getInstance().getString(Constant.SP_KEY_CUR_VOICE_ID, "");
        this.voiceId = string4;
        if (TextUtils.isEmpty(string4)) {
            RLog.e(str6, "voiceId == null ,not continue init voice assistant");
            setIsVoiceAssistantIniting(false);
            EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.VOICE_INIT_FAIL), true));
            return;
        }
        String string5 = Integer.toString(SystemSettingManager.get().getAmplifierType(this.context));
        if (string5.equals("3")) {
            string5 = "4";
        }
        RLog.i(str6, "paType==========>" + string5);
        getDataPackVersion(this.dataPath);
        if (vehicleType == 0) {
            carMode = "car_8155_" + string5 + "_1";
        } else {
            carMode = CarModeUtils.getCarMode(vehicleType, string5, "1");
        }
        RLog.i(str6, "CarMode=>" + carMode);
        Settings.System.putString(this.context.getContentResolver(), "KEY_VOICE_CARMODE", carMode);
        if (!StringUtils.contains(carMode, CarModeUtils.SCD_WhiteList)) {
            RLog.i(str6, "check carMode not in SCD_WhiteList");
            RLog.i(str6, "check carMode not in SCD_WhiteList,disable vrassistant&vrsettings");
            BydVoiceApplication.getContext().sendBroadcast(new Intent(Constant.ACTION_VOICE_PERMISSION_DENY));
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "INIT_VOICE_RESULT_MSG", "check carMode not in SCD_WhiteList");
            setLanguage("");
            if (!SPUtils.getInstance().getString(Constant.OTA_KEY_CUR_LANGUAGE, "").isEmpty() && this.isVoiceAssistantIniting.get()) {
                VoiceLanuageDp voiceLanuageDp = new VoiceLanuageDp(new File(this.dataPath).getName(), this.dataPath);
                voiceLanuageDp.setState(3);
                setVoiceLanuageSettingList(voiceLanuageDp);
                this.failCountDown = 3;
                EventBus.getDefault().postSticky(new SystemStatusEvent(this.dataPath, 3));
                setIsVoiceAssistantIniting(false);
            }
            EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.VOICE_INIT_FAIL), true));
            this.context.getPackageManager().setApplicationEnabledSetting("com.byd.vrsettings", 2, 1);
            this.context.getPackageManager().setApplicationEnabledSetting(BuildConfig.APPLICATION_ID, 2, 1);
            return;
        }
        File file4 = new File(this.compatibleDataPath.isEmpty() ? this.dataPath : this.compatibleDataPath + File.separator + "device_model_specified");
        if (file4.exists()) {
            for (File file5 : file4.listFiles()) {
                if (file5.isDirectory() && file5.getName().equals(carMode)) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        if (!z) {
            carMode = "car_8155_0_1";
        }
        String str7 = TAG;
        RLog.i(str7, "really used CarMode=>" + carMode);
        Settings.System.putString(this.context.getContentResolver(), Constant.KEY_VR_VOICEZONES, CarModeUtils.getZoneType().equals("1") ? "quad" : "double");
        Settings.System.putString(this.context.getContentResolver(), "KEY_REALLY_CARMODE", carMode);
        SystemSettingManager.get().setAudioMicGainReference();
        final String str8 = str + voiceAccentSuffixBydCountry;
        RLog.i(str7, "setAsrLanguage:" + str8);
        Config configBuild = new Config.Builder().setAsrLanguage(str8).dataPath(this.compatibleDataPath.isEmpty() ? this.dataPath : this.compatibleDataPath).writablePath(str5).setDeviceId(deviceId).userId(userId).isSaveInputAudio(Settings.System.getInt(this.context.getContentResolver(), "DEBUG_AUDIO_OUTPUT", 0) == 1).isSaveOutputAudio(Settings.System.getInt(this.context.getContentResolver(), "DEBUG_AUDIO_OUTPUT", 0) == 1).setMaxAudioLogSize(52428800L).setAudioRecordingMode(AudioRecordingMode.APP).setAudioPlaybackMode(AudioPlaybackMode.APP).setDeviceModel(carMode).build();
        this.voiceLanuageSettingList.clear();
        Settings.System.putString(this.context.getContentResolver(), "KEY_LANGUAG_DPSETINGS", "");
        Settings.System.putString(this.context.getContentResolver(), "KEY_LANGUAG_INSTALLDPS", "");
        Settings.System.putString(this.context.getContentResolver(), "KEY_VOICE_SUFFIX", str8);
        Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "KEY_COMPATIBLE_PATH", this.compatibleDataPath);
        final String str9 = this.dataPath;
        initBydLocationHelper();
        PropertiesUtil.loadLanguage();
        final int i4 = i3;
        ArkAssistant.get().initializeVoiceAssistant(this.context, configBuild, new DemoHmi(), this.mControllerFactory.getController(), new IResultCallback() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda49
            @Override // cerence.ark.assistant.common.callback.IResultCallback
            public final void onResult(Result result) {
                this.f$0.m176xd9268651(str8, i4, vehicleType, str9, result);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initVoiceAssistant$22$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m176xd9268651(String str, int i, int i2, String str2, Result result) {
        if (result.isSuccess()) {
            String str3 = TAG;
            RLog.e(str3, "init voice assistant success");
            setIsVoiceAssistantIniting(false);
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "INIT_VOICE_RESULT_MSG", MediaControl.PARAM_SUCCESS);
            updateLocationToAssistantEx(BydLocationHelper.getCurrentLocation());
            notifyContentChanged();
            LongTermManager.startProcessVoiceCloudConfigration(this.context, this.dataPath, str, i, i2);
            setCloudExtraHeadersAsr(str);
            startVoiceAssistant(true);
            initVoicePrompter();
            setContextLanguage(this.dataPath);
            if (!SPUtils.getInstance().getString(Constant.OTA_KEY_CUR_LANGUAGE, "").isEmpty()) {
                VoiceLanuageDp voiceLanuageDp = new VoiceLanuageDp(new File(str2).getName(), str2);
                voiceLanuageDp.setState(2);
                setVoiceLanuageSettingList(voiceLanuageDp);
            }
            RLog.d(str3, "8155 setWeatherTempUnit start");
            ArkAssistant.get().updateAssistantContext(new UiContextGrpc.Builder().setTemperatureExpression(HvacTemperatureHelper.getInstance().getTemperUnit()).build());
            return;
        }
        RLog.e(TAG, String.format("init fail, error message = %s", result.getErrorMessage()));
        Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "INIT_VOICE_RESULT_MSG", result.getErrorMessage());
        setIsVoiceAssistantIniting(false);
        if (isAssistantInitialized()) {
            return;
        }
        this.failCountDown++;
        setLanguage("");
        if (!SPUtils.getInstance().getString(Constant.OTA_KEY_CUR_LANGUAGE, "").isEmpty() && this.isVoiceAssistantIniting.get()) {
            VoiceLanuageDp voiceLanuageDp2 = new VoiceLanuageDp(new File(str2).getName(), str2);
            voiceLanuageDp2.setState(3);
            setVoiceLanuageSettingList(voiceLanuageDp2);
        }
        int i3 = this.failCountDown;
        if (i3 <= 2) {
            EventBus.getDefault().postSticky(new SystemStatusEvent(str2, 2));
        } else if (i3 <= 4) {
            EventBus.getDefault().postSticky(new SystemStatusEvent(str2, 3));
        } else if (i3 > 4) {
            EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.VOICE_INIT_FAIL), true));
        }
    }

    private void getDataPathIsSpcial() {
        if (this.dataPath.contains("spa")) {
            this.dataPath = getLanguageDataPathSuffixEx("spa");
        } else if (this.dataPath.contains("por")) {
            this.dataPath = getLanguageDataPathSuffixEx("por");
        } else if (this.dataPath.contains("eng")) {
            this.dataPath = getLanguageDataPathSuffixEx("eng");
        }
    }

    public void initBydLocationHelper() {
        if (SystemSettingManager.get().isDisGPSCountryCode() || SystemSettingManager.get().isDisGPSICCID()) {
            RLog.e(TAG, "disGPS initBydLocationHelper return");
        } else {
            BydLocationHelper.requestLocationUpdates(this.context, new BydLocationHelper.OnLocationChangeListener() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager.3
                @Override // com.byd.vrassistant.conversation.BydLocationHelper.OnLocationChangeListener
                public void onLastKnownLocation(Location location) {
                    VoiceAssistantManager.this.updateLocationToAssistantEx(location);
                }

                @Override // com.byd.vrassistant.conversation.BydLocationHelper.OnLocationChangeListener
                public void onLocationChanged(Location location) {
                    VoiceAssistantManager.this.updateLocationToAssistantEx(location);
                }
            });
        }
    }

    public void setContextLanguage(String str) {
        String str2 = TAG;
        RLog.i(str2, "setContextLanguage: " + str);
        LanguageBean language = getLanguage(str);
        Locale locale = new Locale(language.getLanguage(), language.getCountry());
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        Configuration configuration = new Configuration(this.context.getResources().getConfiguration());
        configuration.setLocales(localeList);
        configuration.setLocale(locale);
        this.context = this.context.createConfigurationContext(configuration);
        Log.i(str2, "setContextLanguage: result language=" + locale.getLanguage() + ",country=" + locale.getCountry());
        ViewManager.get().updateLocale(locale);
        this.commonViewModel.setLanguageContext(this.context);
    }

    public LanguageBean getLanguage(String str) {
        String str2 = TAG;
        RLog.i(str2, "getLanguage: system language=" + this.context.getApplicationContext().getResources().getConfiguration().locale.getLanguage());
        if (TextUtils.isEmpty(str)) {
            return new LanguageBean(this.context.getApplicationContext().getResources().getConfiguration().locale.getLanguage());
        }
        if (str.contains("data_eng")) {
            return new LanguageBean("en");
        }
        if (str.contains("data_nor")) {
            return new LanguageBean("nb");
        }
        if (str.contains("data_spa")) {
            if (str.contains("XLA")) {
                return new LanguageBean("es", "MX");
            }
            if (str.contains(BYDAutoADASDevice.FEATURE_ADAS_ESP)) {
                return new LanguageBean("es", "ES");
            }
            return new LanguageBean("es");
        }
        if (str.contains("data_por")) {
            if (str.contains("BRA")) {
                return new LanguageBean("pt", "BR");
            }
            if (str.contains("PRT")) {
                return new LanguageBean("pt", "PT");
            }
            return new LanguageBean("pt");
        }
        if (str.contains("data_deu")) {
            return new LanguageBean("de");
        }
        if (str.contains("data_fra")) {
            return new LanguageBean("fr");
        }
        if (str.contains("data_jpn")) {
            return new LanguageBean("ja");
        }
        if (str.contains("data_tha")) {
            return new LanguageBean("th");
        }
        if (str.contains("data_ita")) {
            return new LanguageBean("it");
        }
        if (str.contains("data_cmn")) {
            return new LanguageBean("zh", "CN");
        }
        if (str.contains("data_yue")) {
            return new LanguageBean("zh", "HK");
        }
        if (str.contains("data_kor")) {
            return new LanguageBean("ko");
        }
        if (str.contains("data_heb-ISR")) {
            return new LanguageBean("iw");
        }
        if (str.contains("data_nld-NLD")) {
            return new LanguageBean("nl");
        }
        if (str.contains("data_swe")) {
            return new LanguageBean("sv");
        }
        Log.e(str2, "getLanguage: " + str + "is not supported,plz adapt it");
        return new LanguageBean(this.context.getApplicationContext().getResources().getConfiguration().locale.getLanguage());
    }

    private void updateLocationToAssistant(Location location) {
        if (location == null) {
            RLog.e(TAG, "location is null");
            return;
        }
        ArkAssistant.get().updateAssistantContext(DataManager.get().getNavigationContextBuilder().setCurrentLocation(new LatLng(location.getLatitude(), location.getLongitude())).setCurrentLocationSource(LocationSource.GPS).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLocationToAssistantEx(Location location) {
        if (SystemSettingManager.get().isDisGPSCountryCode() || SystemSettingManager.get().isDisGPSICCID()) {
            RLog.e(TAG, "disGPS updateLocationToAssistantEx return");
            return;
        }
        LatLng latLng = new LatLng(0.0d, 0.0d);
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "DEBUG_VRGPS");
        if (string != null && string.equals("true")) {
            RLog.e(TAG, "DEBUG_VRGPS " + string);
            if (!this.dataPath.isEmpty()) {
                latLng = gpsDebugMaps.get(new File(this.dataPath).getName());
            }
        } else {
            if (location == null) {
                RLog.e(TAG, "location is null");
                return;
            }
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
        }
        RLog.e(TAG, "updateLocationToAssistantEx");
        ArkAssistant.get().updateAssistantContext(DataManager.get().getNavigationContextGrpcBuilder().setCurrentLocation(latLng).setCurrentLocationSource(LocationSourceGrpc.GPS).build());
    }

    public void clearAllView() {
        ViewManager.get().removeAllView(false);
        DialogEventProcessor.getInstance().setHideViewInitiative(true);
    }

    public void destroyVoiceAssistant() {
        String str = TAG;
        RLog.e(str, "destroyVoiceAssistant");
        if (!isInitialited()) {
            RLog.e(str, "destroyVoiceAssistant: isInitialited = false ,return");
            return;
        }
        if (this.isVoiceAssistantIniting.get() || this.isVoiceAssistantStarting.get()) {
            RLog.e(str, "isVoiceAssistantIniting ：" + this.isVoiceAssistantIniting.get() + ",isVoiceAssistantStarting:" + this.isVoiceAssistantStarting.get());
            return;
        }
        clearAllView();
        sendReleaseBroadcast();
        stopVoiceAssistant();
        ArkAssistant.get().destroyVoiceAssistant(null);
        if (this.prompter != null) {
            ArkAssistant.get().destroyPrompter(this.prompter);
        }
    }

    private void startVoiceAssistant(boolean z) {
        this.isVoiceInitingStared.set(z);
        startVoiceAssistant();
    }

    public synchronized void startVoiceAssistant() {
        String str = TAG;
        RLog.e(str, "startVoiceAssistant");
        if (!this.isVoiceAssistantIniting.get() && !this.isVoiceAssistantStarting.get()) {
            setIsVoiceAssistantStarting(true);
            String subWakeUpWord = getSubWakeUpWord();
            ArrayList arrayList = new ArrayList();
            if (subWakeUpWord != null && !subWakeUpWord.isEmpty()) {
                RLog.e(str, "start Assistant wuws add " + subWakeUpWord);
                arrayList.add(subWakeUpWord);
            }
            String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_AUDIO_WAKEUP");
            if (string != null) {
                this.assistant_mode = string.equals("true") ? InteractionMode.WUW : InteractionMode.PTT;
            } else {
                this.assistant_mode = InteractionMode.WUW;
            }
            if (PropertiesUtil.IfCurrentLanguageIsEng()) {
                initDLDHEnabled();
            } else {
                DataManager.get().setIsEnableDLDH(false);
            }
            AssistantConfiguration assistantConfigurationBuild = new AssistantConfiguration.Builder().enableBargeIn(enableBargeIn()).listeningTimeout(10000).snoozingDuration(DataManager.get().getIsEnableDLDH() ? 10000 : 0).setTtsVoiceId(this.voiceId).setWakeupWords(arrayList).interactionMode(this.assistant_mode).build();
            if (PhoneHelper.getInstance().isEcallModel.booleanValue()) {
                return;
            }
            setUserDataRetentionEnabled();
            ArkAssistant.get().startVoiceAssistant(assistantConfigurationBuild, new IResultCallback() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda40
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    this.f$0.m185x7c2c979a(result);
                }
            });
            return;
        }
        RLog.e(str, "isVoiceAssistantIniting ：" + this.isVoiceAssistantIniting.get() + ",isVoiceAssistantStarting:" + this.isVoiceAssistantStarting.get());
    }

    /* JADX INFO: renamed from: lambda$startVoiceAssistant$23$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m185x7c2c979a(Result result) {
        Settings.System.putString(this.context.getContentResolver(), com.byd.vrassistant.hint.Constant.VR_START_SUCCESS, String.valueOf(result.isSuccess()));
        if (result.isSuccess()) {
            String str = TAG;
            RLog.e(str, "start Assistant success");
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "START_VOICE_RESULT_MSG", MediaControl.PARAM_SUCCESS);
            this.failCountDown = 0;
            initShortCutCommand();
            this.isVoiceAssistantStared.set(true);
            EventBus.getDefault().post(new VoiceSateEvent(this.isVoiceAssistantStared.get(), getShowGuideResult()));
            String str2 = this.dataPath + "_bk";
            RLog.e(str, "backup path " + str2);
            File file = new File(str2);
            if (file.exists() && DataUtils.deleteDir(file)) {
                RLog.e(str, file + " backup delete");
            }
            if (deleteAccentData()) {
                RLog.e(str, "deleteAccentData true reinit voice datas");
                initLanguageDatas();
                setLanguage(getLanguage());
            }
            String string = SPUtils.getInstance().getString(Constant.OTA_KEY_CUR_LANGUAGE, "");
            if (!string.isEmpty() && this.dataPath.equals(string)) {
                RLog.e(str, "ota_cur_path start ok :" + string);
                SPUtils.getInstance().put(Constant.OTA_KEY_SUC_LANGUAGE, string, true);
            }
            removeInstallEnableItem(this.dataPath);
            if (this.isVoiceInitingStared.get()) {
                this.isVoiceInitingStared.set(false);
            }
            if (this.isVoiceAssistantStarting.get()) {
                setIsVoiceAssistantStarting(false);
                return;
            }
            return;
        }
        String str3 = TAG;
        RLog.e(str3, "start Assistant fail :" + result.getErrorMessage());
        Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "START_VOICE_RESULT_MSG", result.getErrorMessage());
        if (isAssistantStarted()) {
            if (this.isVoiceAssistantStarting.get()) {
                setIsVoiceAssistantStarting(false);
                return;
            }
            return;
        }
        if (ArkClient.get().getAssistantState() != AssistantState.INITIALIZED) {
            RLog.e(str3, "voice is not inited can't start");
            if (this.isVoiceAssistantStarting.get()) {
                setIsVoiceAssistantStarting(false);
                return;
            }
            return;
        }
        this.failCountDown++;
        setLanguage("");
        if (this.isVoiceAssistantStarting.get()) {
            setIsVoiceAssistantStarting(false);
        }
        if (this.isVoiceInitingStared.get()) {
            int i = this.failCountDown;
            if (i <= 2) {
                EventBus.getDefault().postSticky(new SystemStatusEvent(this.dataPath, 2));
            } else if (i <= 4) {
                EventBus.getDefault().postSticky(new SystemStatusEvent(this.dataPath, 3));
            } else if (i > 4) {
                EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.VOICE_INIT_FAIL), true));
            }
            this.isVoiceInitingStared.set(false);
        }
    }

    public synchronized void stopVoiceAssistant() {
        String str = TAG;
        RLog.e(str, "stopVoiceAssistant");
        if (!isInitialited()) {
            RLog.e(str, "stopVoiceAssistant: isInitialited = false ,return");
            return;
        }
        if (!this.isVoiceAssistantIniting.get() && !this.isVoiceAssistantStarting.get()) {
            this.dialogEvent = DialogEvent.Event.CONVERSATION_END;
            Settings.System.putString(this.context.getContentResolver(), "KEY_VOICE_DIALOGEVENT", this.dialogEvent.name());
            this.isVoiceAssistantStared.set(false);
            ViewStateManger.get().removeViewWhenNotInConversation();
            ArkAssistant.get().stopVoiceAssistant(new IResultCallback() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda29
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    VoiceAssistantManager.lambda$stopVoiceAssistant$24(result);
                }
            });
            return;
        }
        RLog.e(str, "isVoiceAssistantIniting ：" + this.isVoiceAssistantIniting.get() + ",isVoiceAssistantStarting:" + this.isVoiceAssistantStarting.get());
    }

    static /* synthetic */ void lambda$stopVoiceAssistant$24(Result result) {
        if (result.isSuccess()) {
            RLog.e(TAG, "stop Assistant success");
        } else {
            RLog.e(TAG, "stop Assistant fail :" + result.getErrorMessage());
        }
    }

    void processVoiceAssistantMode(InteractionMode interactionMode) {
        String subWakeUpWord = getSubWakeUpWord();
        ArrayList arrayList = new ArrayList();
        if (subWakeUpWord != null && !subWakeUpWord.isEmpty()) {
            RLog.e(TAG, "start Assistant wuws add " + subWakeUpWord);
            arrayList.add(subWakeUpWord);
        }
        this.assistant_mode = interactionMode;
        RLog.e(TAG, "assistant_mode :" + this.assistant_mode.name());
        if (PhoneHelper.getInstance().isEcallModel.booleanValue()) {
            return;
        }
        this.isVoiceAssistantStared.set(false);
        ArkAssistant.get().stopVoiceAssistant(new IResultCallback() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda15
            @Override // cerence.ark.assistant.common.callback.IResultCallback
            public final void onResult(Result result) {
                VoiceAssistantManager.lambda$processVoiceAssistantMode$25(result);
            }
        });
        if (PropertiesUtil.IfCurrentLanguageIsEng()) {
            initDLDHEnabled();
        } else {
            DataManager.get().setIsEnableDLDH(false);
        }
        ArkAssistant.get().startVoiceAssistant(new AssistantConfiguration.Builder().enableBargeIn(enableBargeIn()).listeningTimeout(10000).snoozingDuration(DataManager.get().getIsEnableDLDH() ? 10000 : 0).setTtsVoiceId(this.voiceId).setWakeupWords(arrayList).interactionMode(this.assistant_mode).build(), new IResultCallback() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda16
            @Override // cerence.ark.assistant.common.callback.IResultCallback
            public final void onResult(Result result) {
                this.f$0.m178x1110418d(result);
            }
        });
    }

    static /* synthetic */ void lambda$processVoiceAssistantMode$25(Result result) {
        if (result.isSuccess()) {
            RLog.e(TAG, "processVoiceAssistantMode stop Assistant success");
        } else {
            RLog.d(TAG, "processVoiceAssistantMode stop Assistant fail :" + result.getErrorMessage());
        }
    }

    /* JADX INFO: renamed from: lambda$processVoiceAssistantMode$26$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m178x1110418d(Result result) {
        Settings.System.putString(this.context.getContentResolver(), com.byd.vrassistant.hint.Constant.VR_START_SUCCESS, String.valueOf(result.isSuccess()));
        if (result.isSuccess()) {
            this.isVoiceAssistantStared.set(true);
            EventBus.getDefault().post(new VoiceSateEvent(this.isVoiceAssistantStared.get(), getShowGuideResult()));
            RLog.e(TAG, "processVoiceAssistantMode start Assistant success");
            return;
        }
        RLog.e(TAG, "processVoiceAssistantMode start Assistant fail :" + result.getErrorMessage());
    }

    public void setVoiceAssistantWUW(final List<String> list) {
        if (isInitialited()) {
            this.isVoiceAssistantStared.set(false);
            ArkAssistant.get().stopVoiceAssistant(new IResultCallback() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda3
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    this.f$0.m184x237132c8(list, result);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$setVoiceAssistantWUW$28$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m184x237132c8(List list, Result result) {
        if (result.isSuccess()) {
            RLog.e(TAG, "processVoiceAssistantWUW stop Assistant success");
            if (PropertiesUtil.IfCurrentLanguageIsEng()) {
                initDLDHEnabled();
            } else {
                DataManager.get().setIsEnableDLDH(false);
            }
            ArkAssistant.get().startVoiceAssistant(new AssistantConfiguration.Builder().enableBargeIn(enableBargeIn()).listeningTimeout(10000).snoozingDuration(DataManager.get().getIsEnableDLDH() ? 10000 : 0).setTtsVoiceId(this.voiceId).setWakeupWords(list).interactionMode(this.assistant_mode).build(), new IResultCallback() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda41
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result2) {
                    this.f$0.m183xdfe61507(result2);
                }
            });
            return;
        }
        RLog.e(TAG, "processVoiceAssistantWUW stop Assistant fail :" + result.getErrorMessage());
    }

    /* JADX INFO: renamed from: lambda$setVoiceAssistantWUW$27$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m183xdfe61507(Result result) {
        Settings.System.putString(this.context.getContentResolver(), com.byd.vrassistant.hint.Constant.VR_START_SUCCESS, String.valueOf(result.isSuccess()));
        if (result.isSuccess()) {
            this.isVoiceAssistantStared.set(true);
            EventBus.getDefault().post(new VoiceSateEvent(this.isVoiceAssistantStared.get(), getShowGuideResult()));
            RLog.e(TAG, "processVoiceAssistantWUW start Assistant success");
            return;
        }
        RLog.e(TAG, "processVoiceAssistantWUW start Assistant fail :" + result.getErrorMessage());
    }

    public void initShortCutCommandValue() {
        RLog.e(TAG, "initShortCutCommandValue:");
        setShortCutCommandValue("KEY_HOME_COMMANDS", "");
        setShortCutCommandValue("KEY_MUSIC_PLAYER", "");
        setShortCutCommandValue("KEY_AC_CONTROL", "");
        setShortCutCommandValue("KEY_VOLUME_CONTROL", "");
        setShortCutCommandValue("KEY_MAP_NAV", "");
        Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), Constant.KEY_TEL_CONTROL, "true");
        SharedPreferencesUtil.putVoiceSetting(this.context, Constant.KEY_TEL_CONTROL, "true");
        setShortCutCommandValue(Constant.KEY_TEL_CONTROL, "");
    }

    public void initSubWUWValue() {
        String str = "KEY_WAKEUP_WORD-" + new File(getLanguage()).getName();
        RLog.e(TAG, "initSubWUWValue : " + str);
        if (TextUtils.isEmpty(SharedPreferencesUtil.getVoiceSetting(this.context, str))) {
            SharedPreferencesUtil.putVoiceSetting(this.context, str, "");
        }
    }

    public void setSubWUWValue(String str) {
        String str2 = "KEY_WAKEUP_WORD-" + new File(getLanguage()).getName();
        RLog.e(TAG, "initSubWUWValue : " + str2);
        String voiceSetting = SharedPreferencesUtil.getVoiceSetting(this.context, str2);
        if (str == null || voiceSetting.equals(str)) {
            return;
        }
        SharedPreferencesUtil.putVoiceSetting(this.context, str2, str);
    }

    public void setShortCutCommandValue(String str, String str2) {
        String voiceSetting = SharedPreferencesUtil.getVoiceSetting(this.context, str);
        RLog.e(TAG, "setShortCutCommandValue key:" + str + ",value:" + str2);
        if (voiceSetting == null || voiceSetting.isEmpty()) {
            Settings.System.putString(this.context.getContentResolver(), str, "false");
            SharedPreferencesUtil.putVoiceSetting(this.context, str, "false");
        } else {
            if (str2 == null || str2.isEmpty() || str2.equals(voiceSetting)) {
                return;
            }
            SharedPreferencesUtil.putVoiceSetting(this.context, str, str2);
        }
    }

    public String getShortCutCommandValue(String str) {
        String voiceSetting = SharedPreferencesUtil.getVoiceSetting(this.context, str);
        RLog.e(TAG, "getShortCutCommandValue key:" + str + ",value:" + voiceSetting);
        return voiceSetting;
    }

    public void initVoiceAcvtiveZone() {
        String voiceSetting = SharedPreferencesUtil.getVoiceSetting(this.context, "KEY_REGISTER_LOCK");
        if (voiceSetting == null || voiceSetting.isEmpty()) {
            SharedPreferencesUtil.putVoiceSetting(this.context, "KEY_REGISTER_LOCK", "true");
            voiceSetting = "true";
        }
        Settings.System.putString(this.context.getContentResolver(), "KEY_REGISTER_LOCK", voiceSetting);
        RLog.e(TAG, "initVoiceAcvtiveZone KEY_REGISTER_LOCK:" + voiceSetting);
        this.isVoiceActiveZonesLocked = voiceSetting.equals("true");
    }

    public void initPrivacyPolicy(boolean z) {
        if (z) {
            SharedPreferencesUtil.putGuide(BydVoiceApplication.getContext(), true);
        }
        if (SharedPreferencesUtil.getGuide(BydVoiceApplication.getContext()).booleanValue()) {
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), "KEY_PRIVACY_POLICY", "false");
            SharedPreferencesUtil.putGuide(BydVoiceApplication.getContext(), false);
        }
    }

    public void processVoiceAcvtiveZone() {
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_REGISTER_LOCK");
        if (string == null || string.isEmpty()) {
            string = "true";
        }
        SharedPreferencesUtil.putVoiceSetting(this.context, "KEY_REGISTER_LOCK", string);
        RLog.e(TAG, "processVoiceAcvtiveZone KEY_REGISTER_LOCK:" + string);
        this.isVoiceActiveZonesLocked = string.equals("true");
    }

    public void processVoiceLockZone() {
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), Constant.KEY_REGISTER_SET_RESULT);
        RLog.e(TAG, "processVoiceLockZone key_register_set:" + string);
        convertVoiceLockMask(string);
    }

    public void requestVoiceZone() {
        final String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), Constant.KEY_REGISTER_SET_REQUEST);
        ArkAssistant.get().sendAppEvent(new SetEnableZonesEvent.Builder().setEnabledZones(DataManager.getEnableAudioZones(get().convertVoiceLockMask(string))).build(), new IResultCallback<Void>() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager.4
            @Override // cerence.ark.assistant.common.callback.IResultCallback
            public void onResult(Result<Void> result) {
                if (result.isSuccess()) {
                    Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), Constant.KEY_REGISTER_SET_RESULT, string);
                    RLog.d(VoiceAssistantManager.TAG, "setEnableZones event success");
                } else {
                    String string2 = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), Constant.KEY_REGISTER_SET_RESULT);
                    Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), Constant.KEY_REGISTER_SET_RESULT, "");
                    Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), Constant.KEY_REGISTER_SET_RESULT, string2);
                    RLog.d(VoiceAssistantManager.TAG, "setEnableZones event failure");
                }
            }
        });
    }

    public int convertVoiceLockMask(String str) {
        if (str == null || str.isEmpty()) {
            str = "8";
        }
        int i = (str == null || str.isEmpty()) ? 8 : Integer.parseInt(str);
        int i2 = SystemSettingManager.get().isRightDriver() ? 2 : 1;
        switch (i) {
            case 1:
                break;
            case 2:
                i2 = 3;
                break;
            case 3:
                i2 |= 4;
                break;
            case 4:
                i2 |= 8;
                break;
            case 5:
                i2 = i2 | 8 | 4;
                break;
            case 6:
                i2 = 7;
                break;
            case 7:
                i2 = 11;
                break;
            case 8:
            default:
                i2 = 15;
                break;
        }
        RLog.e(TAG, "convertVoiceLockMask voiceLockMask:" + i2);
        return i2;
    }

    public void openVoiceAssistant() {
        if (isInitialited() && this.assistant_mode != InteractionMode.WUW) {
            processVoiceAssistantMode(InteractionMode.WUW);
        }
    }

    public void closeVoiceAssistant() {
        if (isInitialited() && this.assistant_mode != InteractionMode.PTT) {
            processVoiceAssistantMode(InteractionMode.PTT);
        }
    }

    public void setVoiceAssistantLanguage(final String str) {
        RLog.e(TAG, "setVoiceAssistantLanguage:" + str);
        this.mAsrLanguages.forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda53
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m182x1c6c21e8(str, (String) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setVoiceAssistantLanguage$29$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m182x1c6c21e8(String str, String str2) {
        if (!str2.contains(str) || getLanguage().equals(str2)) {
            return;
        }
        RLog.e(TAG, "setVoiceAssistantLanguage change:" + str2);
        get().setLanguage(str2);
        get().destroyVoiceAssistant();
        try {
            get().initVoiceAssistant();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> getShortCutListByLanguage(String str) {
        String str2 = TAG;
        RLog.e(str2, "getShortCutListByLanguage key :" + str);
        if (this.dataPath.contains("eng-")) {
            HashMap map = shortcutWUWMaps.get("eng-");
            RLog.e(str2, "get eng- :" + str + ",shortcutList:" + map.toString());
            return (List) map.get(str);
        }
        if (this.dataPath.contains("nor-")) {
            HashMap map2 = shortcutWUWMaps.get("nor-");
            RLog.e(str2, "get nor- :" + str + ",shortcutList:" + map2.toString());
            return (List) map2.get(str);
        }
        if (this.dataPath.contains("spa-XLA")) {
            HashMap map3 = shortcutWUWMaps.get("spa-XLA");
            RLog.e(str2, "get spa-XLA :" + str + ",shortcutList:" + map3.toString());
            return (List) map3.get(str);
        }
        if (this.dataPath.contains(Language.SPA_ESP)) {
            HashMap map4 = shortcutWUWMaps.get(Language.SPA_ESP);
            RLog.e(str2, "get spa-ESP :" + str + ",shortcutList:" + map4.toString());
            return (List) map4.get(str);
        }
        if (this.dataPath.contains(Language.POR_PRT)) {
            HashMap map5 = shortcutWUWMaps.get(Language.POR_PRT);
            RLog.e(str2, "get por-PRT :" + str + ",shortcutList:" + map5.toString());
            return (List) map5.get(str);
        }
        if (this.dataPath.contains("por-BRA")) {
            HashMap map6 = shortcutWUWMaps.get("por-BRA");
            RLog.e(str2, "get por-BRA :" + str + ",shortcutList:" + map6.toString());
            return (List) map6.get(str);
        }
        if (this.dataPath.contains("jpn-")) {
            HashMap map7 = shortcutWUWMaps.get("jpn-");
            RLog.e(str2, "get jpn- :" + str + ",shortcutList:" + map7.toString());
            return (List) map7.get(str);
        }
        if (this.dataPath.contains("fra-")) {
            HashMap map8 = shortcutWUWMaps.get("fra-");
            RLog.e(str2, "get fra- :" + str + ",shortcutList:" + map8.toString());
            return (List) map8.get(str);
        }
        if (this.dataPath.contains("deu-")) {
            HashMap map9 = shortcutWUWMaps.get("deu-");
            RLog.e(str2, "get deu- :" + str + ",shortcutList:" + map9.toString());
            return (List) map9.get(str);
        }
        if (this.dataPath.contains("tha-")) {
            HashMap map10 = shortcutWUWMaps.get("tha-");
            RLog.e(str2, "get tha- :" + str + ",shortcutList:" + map10.toString());
            return (List) map10.get(str);
        }
        if (this.dataPath.contains("yue-")) {
            HashMap map11 = shortcutWUWMaps.get("yue-");
            RLog.e(str2, "get yue- :" + str + ",shortcutList:" + map11.toString());
            return (List) map11.get(str);
        }
        if (this.dataPath.contains("cmn-")) {
            HashMap map12 = shortcutWUWMaps.get("cmn-");
            RLog.e(str2, "get cmn- :" + str + ",shortcutList:" + map12.toString());
            return (List) map12.get(str);
        }
        if (this.dataPath.contains("ita-")) {
            HashMap map13 = shortcutWUWMaps.get("ita-");
            RLog.e(str2, "get ita- :" + str + ",shortcutList:" + map13.toString());
            return (List) map13.get(str);
        }
        if (this.dataPath.contains("swe-")) {
            HashMap map14 = shortcutWUWMaps.get("swe-");
            RLog.e(str2, "get swe- :" + str + ",shortcutList:" + map14.toString());
            return (List) map14.get(str);
        }
        if (this.dataPath.contains("tur-")) {
            HashMap map15 = shortcutWUWMaps.get("tur-");
            RLog.e(str2, "get tur- :" + str + ",shortcutList:" + map15.toString());
            return (List) map15.get(str);
        }
        if (this.dataPath.contains("dan-")) {
            HashMap map16 = shortcutWUWMaps.get("dan-");
            RLog.e(str2, "get dan- :" + str + ",shortcutList:" + map16.toString());
            return (List) map16.get(str);
        }
        if (this.dataPath.contains("pol-")) {
            HashMap map17 = shortcutWUWMaps.get("pol-");
            RLog.e(str2, "get pol- :" + str + ",shortcutList:" + map17.toString());
            return (List) map17.get(str);
        }
        if (this.dataPath.contains("nld-")) {
            HashMap map18 = shortcutWUWMaps.get("nld-");
            RLog.e(str2, "get nld- :" + str + ",shortcutList:" + map18.toString());
            return (List) map18.get(str);
        }
        if (this.dataPath.contains("fin-")) {
            HashMap map19 = shortcutWUWMaps.get("fin-");
            RLog.e(str2, "get fin- :" + str + ",shortcutList:" + map19.toString());
            return (List) map19.get(str);
        }
        if (this.dataPath.contains("heb-")) {
            HashMap map20 = shortcutWUWMaps.get("heb-");
            RLog.e(str2, "get heb- :" + str + ",shortcutList:" + map20.toString());
            return (List) map20.get(str);
        }
        if (this.dataPath.contains("kor-")) {
            HashMap map21 = shortcutWUWMaps.get("kor-");
            RLog.e(str2, "get kor- :" + str + ",shortcutList:" + map21.toString());
            return (List) map21.get(str);
        }
        if (!this.dataPath.contains("hun-")) {
            return null;
        }
        HashMap map22 = shortcutWUWMaps.get("hun-");
        RLog.e(str2, "get hun- :" + str + ",shortcutList:" + map22.toString());
        return (List) map22.get(str);
    }

    private void processShortCutByKeySettings(String str) {
        if (isInitialited()) {
            if (isSetShortCutCommand() || Constant.KEY_TEL_CONTROL.equals(str)) {
                boolean freeWakeupWordsValue = SystemSettingManager.get().getFreeWakeupWordsValue(str);
                List<String> shortCutListByLanguage = getShortCutListByLanguage(str);
                if (shortCutListByLanguage == null) {
                    RLog.e(TAG, str + " null");
                    return;
                }
                setShortCutCommandValue(str, String.valueOf(freeWakeupWordsValue));
                if (freeWakeupWordsValue) {
                    setShortcutCommand(shortCutListByLanguage);
                    RLog.e(TAG, "set " + str);
                } else {
                    delShortcutCommand(shortCutListByLanguage);
                    RLog.e(TAG, "del " + str);
                }
            }
        }
    }

    public void processHomeShortCutCommand() {
        processShortCutByKeySettings("KEY_HOME_COMMANDS");
    }

    public void processMusicShortCutCommand() {
        processShortCutByKeySettings("KEY_MUSIC_PLAYER");
    }

    public void processACShortCutCommand() {
        processShortCutByKeySettings("KEY_AC_CONTROL");
    }

    public void processVolumeShortCutCommand() {
        processShortCutByKeySettings("KEY_VOLUME_CONTROL");
    }

    public void processNavShortCutCommand() {
        processShortCutByKeySettings("KEY_MAP_NAV");
    }

    public void processTelShortCutCommand() {
        processShortCutByKeySettings(Constant.KEY_TEL_CONTROL);
    }

    public void processSubWUW() {
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_WAKEUP_WORD");
        if (string.isEmpty()) {
            return;
        }
        RLog.e(TAG, "processSubWUW");
        ArrayList arrayList = new ArrayList();
        arrayList.add(string);
        setVoiceAssistantWUW(arrayList);
    }

    public String getSubWakeUpWord() {
        String str = "KEY_WAKEUP_WORD-" + new File(getLanguage()).getName();
        String str2 = TAG;
        RLog.e(str2, "initSubWUWValue : " + str);
        String voiceSetting = SharedPreferencesUtil.getVoiceSetting(this.context, str);
        RLog.e(str2, "wuw_value : " + voiceSetting);
        return voiceSetting;
    }

    public void initShortCutCommand() {
        this.shortCutList.clear();
        if (!SystemSettingManager.get().isSetSwitchControl(Constant.KEY_TEL_CONTROL)) {
            SystemSettingManager.get().saveFreeSwitchStatus(Constant.KEY_TEL_CONTROL, true);
        }
        processHomeShortCutCommand();
        processMusicShortCutCommand();
        processACShortCutCommand();
        processVolumeShortCutCommand();
        processNavShortCutCommand();
        processTelShortCutCommand();
    }

    public void setShortcutCommand(List<String> list) {
        if (list == null) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        list.forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda42
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m181x65148b27((String) obj);
            }
        });
        this.shortCutList.forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda43
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                VoiceAssistantManager.lambda$setShortcutCommand$31(arrayList, (String) obj);
            }
        });
        ArkAssistant.get().updateShortcutCommand(arrayList, null);
    }

    /* JADX INFO: renamed from: lambda$setShortcutCommand$30$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m181x65148b27(String str) {
        if (this.shortCutList.contains(str)) {
            return;
        }
        this.shortCutList.add(str);
    }

    static /* synthetic */ void lambda$setShortcutCommand$31(List list, String str) {
        RLog.e(TAG, "setShortcutCommand s:" + str);
        list.add(ShortcutCommand.builder().setPhrase(str).build());
    }

    public void delShortcutCommand(List<String> list) {
        if (list == null) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        arrayList.clear();
        list.forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m166x120ec9d2((String) obj);
            }
        });
        this.shortCutList.forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                VoiceAssistantManager.lambda$delShortcutCommand$33(arrayList, (String) obj);
            }
        });
        ArkAssistant.get().updateShortcutCommand(arrayList, null);
    }

    /* JADX INFO: renamed from: lambda$delShortcutCommand$32$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m166x120ec9d2(String str) {
        this.shortCutList.remove(str);
    }

    static /* synthetic */ void lambda$delShortcutCommand$33(List list, String str) {
        RLog.e(TAG, "delShortcutCommand s:" + str);
        list.add(ShortcutCommand.builder().setPhrase(str).build());
    }

    public CountDownLatch getIsEventSuccess() {
        return this.isEventSuccess;
    }

    public void sendPTTEvent(String str) {
        if (isInitialited()) {
            ViewStateManger.get().setPttEventZone(str);
            sendExitNotification();
            ArkAssistant.get().sendAppEvent(new PTTEvent.Builder().setActiveZone(str).build(), new IResultCallback<Void>() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager.5
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public void onResult(Result<Void> result) {
                    VoiceAssistantManager.this.isEventSuccess.countDown();
                    if (result.isSuccess()) {
                        RLog.d(VoiceAssistantManager.TAG, "send ptt event success");
                        return;
                    }
                    if (USBCopyDataManager.get().has_ota_copyed) {
                        Settings.System.putInt(VoiceAssistantManager.this.context.getContentResolver(), "KEY_LANGUAG_INSTALLING", 0);
                        Settings.System.putInt(VoiceAssistantManager.this.context.getContentResolver(), "KEY_LANGUAG_INSTALLING", 1);
                    }
                    RLog.d(VoiceAssistantManager.TAG, "send ptt event failure");
                    if (PhoneHelper.getInstance().callState == PhoneHelper.phoneState.CALL_STATE_OFFHOOK || PhoneHelper.getInstance().callState == PhoneHelper.phoneState.CALL_STATE_DIALING || PhoneHelper.getInstance().isEcallModel.booleanValue() || SystemSettingManager.get().isCarplayCallSending() || SystemSettingManager.get().isCarplayCallOthers() || SystemSettingManager.get().isCarplayCallRinging()) {
                        EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.AUDIO_OCCUPANCY), true));
                    } else {
                        EventBus.getDefault().post(new VoiceSateEvent(VoiceAssistantManager.this.isVoiceAssistantStared.get(), VoiceAssistantManager.this.getShowGuideResult()));
                    }
                }
            });
            try {
                this.isEventSuccess.await(500L, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPTTEvent(String str, final ICommonSuccessInterface iCommonSuccessInterface) throws RemoteException {
        if (!isInitialited()) {
            iCommonSuccessInterface.isSuccess(false);
            return;
        }
        ViewStateManger.get().setPttEventZone(str);
        sendExitNotification();
        ArkAssistant.get().sendAppEvent(new PTTEvent.Builder().setActiveZone(str).build(), new IResultCallback<Void>() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager.6
            @Override // cerence.ark.assistant.common.callback.IResultCallback
            public void onResult(Result<Void> result) {
                VoiceAssistantManager.this.isEventSuccess.countDown();
                if (result.isSuccess()) {
                    RLog.d(VoiceAssistantManager.TAG, "send ptt event success");
                    iCommonSuccessInterface.isSuccess(true);
                    return;
                }
                if (USBCopyDataManager.get().has_ota_copyed) {
                    Settings.System.putInt(VoiceAssistantManager.this.context.getContentResolver(), "KEY_LANGUAG_INSTALLING", 0);
                    Settings.System.putInt(VoiceAssistantManager.this.context.getContentResolver(), "KEY_LANGUAG_INSTALLING", 1);
                }
                RLog.d(VoiceAssistantManager.TAG, "send ptt event failure");
                if (PhoneHelper.getInstance().callState == PhoneHelper.phoneState.CALL_STATE_OFFHOOK || PhoneHelper.getInstance().callState == PhoneHelper.phoneState.CALL_STATE_DIALING || PhoneHelper.getInstance().isEcallModel.booleanValue()) {
                    EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.AUDIO_OCCUPANCY), true));
                } else {
                    EventBus.getDefault().post(new VoiceSateEvent(VoiceAssistantManager.this.isVoiceAssistantStared.get(), VoiceAssistantManager.this.getShowGuideResult()));
                    iCommonSuccessInterface.isSuccess(false);
                }
            }
        });
        try {
            this.isEventSuccess.await(500L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendWakeUpBroadcast() {
        if (SystemSettingManager.get().getHUForbidStatus() == 0 && !this.isShortCutWakeup) {
            RLog.e(TAG, "sendWakeUpBroadcast");
            Intent intent = new Intent();
            intent.setAction(Constant.ACTION_REQUEST_MIC);
            intent.putExtra("who", "bydvoice");
            intent.putExtra("usemic", "true");
            intent.putExtra("wakefree", this.isShortCutWakeup);
            this.context.sendBroadcast(intent);
            this.iswakeupbroadcasted = true;
            this.isShowAfterExit = true;
        }
    }

    public void sendVirtualKeyPTTBroadcast() {
        Intent intent = new Intent();
        intent.setAction("com.intent.action.StartVoice_From_Launcher");
        intent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.byd.vrassistant.receiver.HardKeyReceiver"));
        this.context.sendBroadcast(intent);
    }

    public void sendExitSplitScreen() {
        RLog.e(TAG, "sendExitSplitScreen");
        Intent intent = new Intent();
        intent.setAction("split.screen.return.to.home.page");
        this.context.sendBroadcast(intent);
    }

    public void sendExitNotification() {
        RLog.e(TAG, "sendExitNotification");
        this.context.sendBroadcast(new Intent("com.byd.action.qsPanel.auto"));
    }

    public void sendReleaseBroadcast() {
        if (this.iswakeupbroadcasted && !this.isShortCutWakeup) {
            RLog.e(TAG, "sendReleaseBroadcast isShowAfterExit " + this.isShowAfterExit);
            Intent intent = new Intent();
            intent.setAction(Constant.ACTION_RELEASE_MIC);
            intent.putExtra("who", "bydvoice");
            intent.putExtra("wakefree", this.isShortCutWakeup);
            intent.putExtra("show", this.isShowAfterExit);
            this.context.sendBroadcast(intent);
            this.iswakeupbroadcasted = false;
        }
    }

    public void sendCancelPTTEventSilence() {
        if (isInitialited()) {
            RLog.i(TAG, "sendCancelPTTEventSilence: ");
            this.cancelEvent.onNext(true);
        }
    }

    public void sendCancelPTTEvent() {
        if (isInitialited()) {
            RLog.i(TAG, "sendCancelPTTEvent: ");
            this.cancelEvent.onNext(true);
        }
    }

    public void sendCancelPTTEventCallBack(final ICommonSuccessInterface iCommonSuccessInterface) {
        if (isInitialited()) {
            RLog.i(TAG, "sendCancelPTTEventCallBack: ");
            ArkAssistant.get().sendAppEvent(new CancelDialogEvent.Builder().setIsNeedToPlayEarcon(true).build(), new IResultCallback() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda38
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    VoiceAssistantManager.lambda$sendCancelPTTEventCallBack$34(iCommonSuccessInterface, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$sendCancelPTTEventCallBack$34(ICommonSuccessInterface iCommonSuccessInterface, Result result) {
        iCommonSuccessInterface.isSuccess(result.isSuccess());
        if (result.isSuccess()) {
            RLog.d(TAG, "send cancel dialog event success");
            DialogEventProcessor.getInstance().setHideViewInitiative(true);
        } else {
            RLog.d(TAG, "send cancel dialog event failure");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCancelPTTEventActually(boolean z) {
        if (isInitialited()) {
            RLog.i(TAG, "sendCancelPTTEventActually: " + z);
            ArkAssistant.get().sendAppEvent(new CancelDialogEvent.Builder().setIsNeedToPlayEarcon(z).build(), new IResultCallback() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda18
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    VoiceAssistantManager.lambda$sendCancelPTTEventActually$35(result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$sendCancelPTTEventActually$35(Result result) {
        if (result.isSuccess()) {
            RLog.d(TAG, "send cancel dialog event success");
            DialogEventProcessor.getInstance().setHideViewInitiative(true);
        } else {
            RLog.d(TAG, "send cancel dialog event failure");
        }
    }

    protected void registerAssistantListener() {
        ArkAssistant.get().registerAssistantListener(new AssistantListener() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager.7
            @Override // cerence.ark.assistant.api.AssistantListener
            public void onDestroyed() {
            }

            @Override // cerence.ark.assistant.api.AssistantListener
            public void onInitialized() {
            }

            @Override // cerence.ark.assistant.api.AssistantListener
            public void onStarted() {
            }

            @Override // cerence.ark.assistant.api.AssistantListener
            public void onStopped() {
            }

            @Override // cerence.ark.assistant.framework.ErrorListener
            public void onError(ErrorInfo errorInfo) {
                RLog.i(VoiceAssistantManager.TAG, "onError============>" + errorInfo.getErrorMessage());
                if (errorInfo.getErrorCode() == ErrorInfo.ErrorCode.FATAL_RECOGNITION_TIMEOUT) {
                    ArkAssistant.get().destroyVoiceAssistant(null);
                    try {
                        VoiceAssistantManager.this.initLanguageDatas();
                        VoiceAssistantManager.this.initVoiceAssistant();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void modifyDataPackAccent(String str, String str2, String str3, String str4) {
        if (!new File(str).exists()) {
            RLog.e(TAG, "modifyDataPackAccent src " + str + " no exsit");
            return;
        }
        if (!this.dataPath.contains(str4) || !getDataPackVersion(str).equals(getDataPackVersion(str2))) {
            USBCopyDataManager.get().startAccentTask(str, str2, str3, str4);
        } else if (isNeedToCopyAccentByDPVersion(str, str2)) {
            USBCopyDataManager.get().startAccentVerifyTask(str, str2, str3, str4);
        }
    }

    public void copyDataPack2Accent(String str, String str2, String str3, String str4) {
        if (!new File(str).exists()) {
            RLog.e(TAG, "src " + str + " no exsit");
            return;
        }
        if (!DataUtils.deleteDir(new File(str2))) {
            RLog.e(TAG, "delete dst " + str2 + " err");
        }
        DataUtils.copy(str, str2);
        String str5 = str + "/asr_" + str3 + "/json/";
        String str6 = str2 + "/asr_" + str3 + "/json/";
        String str7 = TAG;
        RLog.e(str7, "src_asr " + str5 + ",dst_asr " + str6);
        new File(str2).mkdirs();
        DataUtils.copy(str5, str6);
        DataUtils.copy(str + "/version/", str2 + "/version/");
        String file2String = DataUtils.readFile2String(str2 + "/version/version.json");
        if (file2String == null || file2String.isEmpty()) {
            return;
        }
        RLog.e(str7, "version.json:" + file2String);
        try {
            JSONObject jSONObject = new JSONObject(file2String);
            if (jSONObject.has(LanguageUpdate.LANGUAGE)) {
                RLog.e(str7, "language :" + jSONObject.getString(LanguageUpdate.LANGUAGE));
                jSONObject.put(LanguageUpdate.LANGUAGE, "data_" + str4);
                DataUtils.stringToFile(str2 + "/version/version.json", jSONObject.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DataUtils.copyFileFromAssets(this.context, str4 + "/cloud_services.json", str6 + "/cloud_services.json");
    }

    private boolean deleteVoiceDataFirstRun() throws Throwable {
        if (new File(DataUtils.getPlatformDataPackPathEx() + "/voice_first_run.dat").exists()) {
            RLog.e(TAG, "firstRunMarkFile is exists");
            return false;
        }
        RLog.e(TAG, "start deleteVoiceDataFirstRun");
        String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
        String assets2String = DataUtils.readAssets2String(this.context, "FactoryVoiceCheck8155.json");
        if (assets2String != null) {
            try {
                if (!assets2String.isEmpty()) {
                    try {
                        deleteVoiceDataByJson(assets2String, countryCode);
                        get().recordDataPackaProcess("firstrun");
                        return true;
                    } catch (JsonSyntaxException e) {
                        RLog.e(TAG, "deleteVoiceDataFirstRun :" + e.getMessage());
                        e.printStackTrace();
                        return true;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        get().recordDataPackaProcess("firstrun");
        return true;
    }

    private boolean deleteAccentData() {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        java.util.function.Consumer<? super String> consumer = new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda57
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m167x2ff37b1(atomicBoolean, (String) obj);
            }
        };
        if (this.dataPath.contains("data_eng-USA")) {
            this.mAsrLanguages.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VoiceAssistantManager.lambda$deleteAccentData$37((String) obj);
                }
            }).forEach(consumer);
        }
        return atomicBoolean.get();
    }

    /* JADX INFO: renamed from: lambda$deleteAccentData$36$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m167x2ff37b1(AtomicBoolean atomicBoolean, String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            String dataPackVersion = getDataPackVersion(str);
            if (DataUtils.deleteDir(file)) {
                RLog.e(TAG, "delete : " + str + " success");
                recordDataPackaVersionProcess(DataRecordEvent.ACCENT_DEL.name(), str, dataPackVersion);
                atomicBoolean.set(true);
                return;
            }
            RLog.e(TAG, "delete : " + str + " error");
        }
    }

    static /* synthetic */ boolean lambda$deleteAccentData$37(String str) {
        return str.contains("data_eng-") && !str.contains("data_eng-USA");
    }

    private void deleteVoiceDataByJson(String str, final String str2) {
        List list = (List) gson.fromJson(str, new TypeToken<ArrayList<FacotryVoiceCheck>>() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager.8
        }.getType());
        list.forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda54
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                RLog.e(VoiceAssistantManager.TAG, "factoryVoiceCheckList :" + ((FacotryVoiceCheck) obj));
            }
        });
        list.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda55
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return VoiceAssistantManager.lambda$deleteVoiceDataByJson$39(str2, (FacotryVoiceCheck) obj);
            }
        }).forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda56
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m171x80a1a4d8((FacotryVoiceCheck) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$deleteVoiceDataByJson$39(String str, FacotryVoiceCheck facotryVoiceCheck) {
        if (str.isEmpty()) {
            return false;
        }
        return facotryVoiceCheck.getCountry_code().contains(Integer.valueOf(Integer.parseInt(str.trim())));
    }

    /* JADX INFO: renamed from: lambda$deleteVoiceDataByJson$45$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m171x80a1a4d8(final FacotryVoiceCheck facotryVoiceCheck) {
        if (facotryVoiceCheck.getDp_vers() != null && facotryVoiceCheck.getDp_vers().size() > 0) {
            this.mAsrLanguages.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda8
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VoiceAssistantManager.lambda$deleteVoiceDataByJson$40((String) obj);
                }
            }).filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda9
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VoiceAssistantManager.lambda$deleteVoiceDataByJson$41(facotryVoiceCheck, (String) obj);
                }
            }).forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda10
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.m169xb6004b95((String) obj);
                }
            });
        } else {
            this.mAsrLanguages.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda12
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VoiceAssistantManager.lambda$deleteVoiceDataByJson$43((String) obj);
                }
            }).forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.m170x3d168717((String) obj);
                }
            });
        }
    }

    static /* synthetic */ boolean lambda$deleteVoiceDataByJson$40(String str) {
        return !str.contains("data_eng-USA");
    }

    static /* synthetic */ boolean lambda$deleteVoiceDataByJson$41(FacotryVoiceCheck facotryVoiceCheck, String str) {
        Iterator<FacotryVoiceCheck.DpNamever> it = facotryVoiceCheck.getDp_vers().iterator();
        while (it.hasNext()) {
            if (str.contains(it.next().getDp_name())) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: lambda$deleteVoiceDataByJson$42$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m169xb6004b95(String str) {
        String dataPackVersion = getDataPackVersion(str);
        if (DataUtils.deleteDir(new File(str))) {
            RLog.e(TAG, "delete : " + str + " success");
            recordDataPackaVersionProcess(DataRecordEvent.FIRST_DEL.name(), str, dataPackVersion);
        } else {
            RLog.e(TAG, "delete : " + str + " error");
        }
    }

    static /* synthetic */ boolean lambda$deleteVoiceDataByJson$43(String str) {
        return !str.contains("data_eng-USA");
    }

    /* JADX INFO: renamed from: lambda$deleteVoiceDataByJson$44$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ void m170x3d168717(String str) {
        String dataPackVersion = getDataPackVersion(str);
        if (DataUtils.deleteDir(new File(str))) {
            RLog.e(TAG, "delete : " + str + " success");
            recordDataPackaVersionProcess(DataRecordEvent.FIRST_DEL.name(), str, dataPackVersion);
        } else {
            RLog.e(TAG, "delete : " + str + " error");
        }
    }

    public void startFactoryVoiceCheckProcess() throws Throwable {
        String str = TAG;
        RLog.e(str, "startFactoryVoiceCheckProcess ");
        boolean z = SystemPropertiesUtils.getBoolean("debug.factory.voice.check", false);
        RLog.e(str, "isfactoryVoiceCheck :" + z);
        if (z) {
            String str2 = SystemPropertiesUtils.get("apps.setting.product.outswver", "");
            String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
            String assets2String = DataUtils.readAssets2String(this.context, "FactoryVoiceCheck.json");
            if (assets2String == null || assets2String.isEmpty()) {
                return;
            }
            RLog.e(str, "version.json:" + assets2String);
            RLog.e(str, "ivi_ver:" + str2);
            checkFactoryVoiceCheckJson(assets2String, countryCode);
        }
    }

    public void startFactoryVoiceDataPackageCheck(String str, String str2, String str3, String str4) throws Throwable {
        String file2String;
        boolean z;
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "plaform is empty");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            RLog.e(TAG, "country is empty");
            return;
        }
        RLog.e(TAG, "plaform:" + str + ", country:" + str2);
        if (!str.equals("8155") && str.equals(Constant.PLATFORM_6125)) {
            SystemPropertiesUtils.set(BYDAutoSysPropertiesDevice.COUNTRY_CODE, str2);
            String platformDataPackPathEx = TextUtils.isEmpty(str3) ? DataUtils.getPlatformDataPackPathEx() : str3;
            if (TextUtils.isEmpty(str4)) {
                file2String = DataUtils.readAssets2String(this.context, "FactoryVoiceCheck.json");
            } else {
                file2String = DataUtils.readFile2String(platformDataPackPathEx + File.separator + str4);
            }
            if (TextUtils.isEmpty(file2String)) {
                return;
            }
            initLanguageDatas();
            deleteVoiceDataByJson(file2String, str2);
            initLanguageDatas();
            File file = new File(DataUtils.getPlatformDataPackPathEx() + File.separator + "factoryVoiceDPCheck.csv");
            List listAsList = Arrays.asList("DataVersion", "CountryCode", "Languages", "DataSize", ",,,,,,,,,,");
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (file.exists()) {
                z = true;
            } else {
                arrayList2.add(listAsList);
                z = false;
            }
            arrayList.add(this.voiceLanuageDpList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((VoiceLanuageDp) obj).getName().contains("USA");
                }
            }).findFirst().get().getVersion());
            arrayList.add(str2);
            String str5 = "";
            long folderSize = 0;
            String str6 = "";
            long folderSize2 = 0;
            for (VoiceLanuageDp voiceLanuageDp : this.voiceLanuageDpList) {
                str6 = str6 + voiceLanuageDp.getName() + System.getProperty("line.separator");
                folderSize2 += DataUtils.getFolderSize(new File(voiceLanuageDp.getPath()));
            }
            if (str3.equals(DataUtils.getPlatformDataPackPathEx())) {
                str5 = str6;
                folderSize = folderSize2;
            } else {
                RLog.e(TAG, "dppath not cerence data : " + str3);
                arrayList.clear();
                arrayList.add("0.0");
                arrayList.add(str2);
                File[] fileArrListFiles = new File(str3).listFiles();
                if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                    for (int i = 0; i < fileArrListFiles.length; i++) {
                        if (fileArrListFiles[i].isDirectory()) {
                            RLog.e(TAG, "dataPathSubFiles : " + fileArrListFiles[i].getAbsolutePath());
                            str5 = str5 + fileArrListFiles[i].getName() + System.getProperty("line.separator");
                            folderSize += DataUtils.getFolderSize(new File(fileArrListFiles[i].getAbsolutePath()));
                        }
                    }
                }
            }
            if (!str5.isEmpty()) {
                arrayList.add("\"" + str5 + "\"");
            }
            arrayList.add(CSVStringUtils.transUnitBySize(folderSize));
            arrayList2.add(arrayList);
            RLog.e(TAG, "csv :" + arrayList2);
            try {
                CSVStringUtils.writeCSVFile(arrayList2, file, z);
            } catch (IOException e) {
                RLog.e(TAG, "CSVStringUtils:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void startFactoryVoiceCheckProcess8155() throws Throwable {
        String str = TAG;
        RLog.e(str, "startFactoryVoiceCheckProcess8155 ");
        String str2 = SystemPropertiesUtils.get("ro.product.version.soc.name", "");
        String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
        String assets2String = DataUtils.readAssets2String(this.context, "FactoryVoiceCheck8155.json");
        if (assets2String == null || assets2String.isEmpty()) {
            return;
        }
        RLog.e(str, "version.json:" + assets2String);
        RLog.e(str, "ivi_ver:" + str2);
        checkFactoryVoiceCheckJson(assets2String, countryCode);
    }

    private void checkFactoryVoiceCheckJson(String str, final String str2) {
        try {
            List list = (List) gson.fromJson(str, new TypeToken<ArrayList<FacotryVoiceCheck>>() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager.9
            }.getType());
            list.forEach(new java.util.function.Consumer() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RLog.e(VoiceAssistantManager.TAG, "factoryVoiceCheckList :" + ((FacotryVoiceCheck) obj));
                }
            });
            boolean zIsPresent = list.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda21
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VoiceAssistantManager.lambda$checkFactoryVoiceCheckJson$48(str2, (FacotryVoiceCheck) obj);
                }
            }).filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda23
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f$0.m165xdbf66aec((FacotryVoiceCheck) obj);
                }
            }).findAny().isPresent();
            Intent intent = new Intent();
            intent.setAction(Constant.ACTION_FACTORY_VOICE_CHECK);
            String str3 = "PASS";
            intent.putExtra(WuwInfoBean.ReturnKey, zIsPresent ? "PASS" : "FAIL");
            this.context.sendBroadcast(intent);
            if (!zIsPresent) {
                str3 = "FAIL";
            }
            SystemPropertiesUtils.set(Constant.ACTION_FACTORY_VOICE_CHECK, str3);
            RLog.e(TAG, "startFactoryVoiceCheckProcess result:" + zIsPresent);
        } catch (JsonSyntaxException e) {
            RLog.e(TAG, "startFactoryVoiceCheckProcess :" + e.getMessage());
            e.printStackTrace();
        }
    }

    static /* synthetic */ boolean lambda$checkFactoryVoiceCheckJson$48(String str, FacotryVoiceCheck facotryVoiceCheck) {
        if (str.isEmpty()) {
            return false;
        }
        return facotryVoiceCheck.getCountry_code().contains(Integer.valueOf(Integer.parseInt(str.trim())));
    }

    /* JADX INFO: renamed from: lambda$checkFactoryVoiceCheckJson$54$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ boolean m165xdbf66aec(final FacotryVoiceCheck facotryVoiceCheck) {
        if (facotryVoiceCheck.getDp_vers() == null || facotryVoiceCheck.getDp_vers().size() <= 0) {
            return true ^ this.mAsrLanguages.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda44
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VoiceAssistantManager.lambda$checkFactoryVoiceCheckJson$53((String) obj);
                }
            }).findAny().isPresent();
        }
        return facotryVoiceCheck.getDp_vers().stream().allMatch(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda11
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.m164xcdc9f3e8((FacotryVoiceCheck.DpNamever) obj);
            }
        }) && this.mAsrLanguages.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda22
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return VoiceAssistantManager.lambda$checkFactoryVoiceCheckJson$51((String) obj);
            }
        }).filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda33
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return VoiceAssistantManager.lambda$checkFactoryVoiceCheckJson$52(facotryVoiceCheck, (String) obj);
            }
        }).count() <= 0;
    }

    /* JADX INFO: renamed from: lambda$checkFactoryVoiceCheckJson$50$com-byd-vrassistant-manager-VoiceAssistantManager, reason: not valid java name */
    /* synthetic */ boolean m164xcdc9f3e8(FacotryVoiceCheck.DpNamever dpNamever) {
        final String dp_name = dpNamever.getDp_name();
        String dp_ver = dpNamever.getDp_ver();
        String str = TAG;
        RLog.e(str, "dp_name :" + dp_name);
        RLog.e(str, "dp_ver :" + dp_ver);
        if (this.mAsrLanguages.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda19
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((String) obj).contains(dp_name);
            }
        }).findAny().isPresent()) {
            return getDataPackVersion(DataUtils.getPlatformDataPackPathEx() + "/" + dp_name).equalsIgnoreCase(dp_ver);
        }
        return false;
    }

    static /* synthetic */ boolean lambda$checkFactoryVoiceCheckJson$51(String str) {
        return !str.contains("data_eng-USA");
    }

    static /* synthetic */ boolean lambda$checkFactoryVoiceCheckJson$52(FacotryVoiceCheck facotryVoiceCheck, String str) {
        Iterator<FacotryVoiceCheck.DpNamever> it = facotryVoiceCheck.getDp_vers().iterator();
        while (it.hasNext()) {
            if (str.contains(it.next().getDp_name())) {
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ boolean lambda$checkFactoryVoiceCheckJson$53(String str) {
        return !str.contains("data_eng-USA");
    }

    public void recordDataPackaProcess(String str) {
        File file = new File(DataUtils.getPlatformDataPackPathEx() + "/voice_first_run.dat");
        RLog.e(TAG, "recordDataPackaProcess : " + str);
        DataUtils.writeStringlToFile(file, str + "|" + new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()) + "|" + this.voiceLanuageNaviDpList.stream().map(new Function() { // from class: com.byd.vrassistant.manager.VoiceAssistantManager$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return VoiceAssistantManager.lambda$recordDataPackaProcess$55((VoiceLanuageDp) obj);
            }
        }).collect(Collectors.toList()));
    }

    static /* synthetic */ String lambda$recordDataPackaProcess$55(VoiceLanuageDp voiceLanuageDp) {
        return "{name='" + voiceLanuageDp.getName() + "', path='" + voiceLanuageDp.getPath() + "', version='" + voiceLanuageDp.getVersion() + '}';
    }

    public void recordDataPackaProcessEx(String str, String str2) {
        LoggerUtil.writerLog(DataUtils.getPlatformDataPackPathEx() + "/RecordDataLogs/", str + "\t" + str2 + "\t" + getDataPackVersion(str2));
    }

    public void recordDataPackaVersionProcess(String str, String str2, String str3) {
        LoggerUtil.writerLog(DataUtils.getPlatformDataPackPathEx() + "/RecordDataLogs/", str + "\t" + str2 + "\t" + str3);
    }

    private boolean isSetShortCutCommand() {
        String language = getLanguage();
        if (TextUtils.isEmpty(language)) {
            return false;
        }
        String dataPackVersion = getDataPackVersion(this.dataPath);
        if ((language.contains("eng") || (language.contains("por") && language.contains("BRA"))) && isVersionIsHigher(dataPackVersion, com.byd.vrassistant.hint.Constant.ENG_POR_FREE_WORDS_SUPPORT_VERSION)) {
            RLog.e(TAG, "isSetShortCutCommand eng or por may set shorcut");
            return true;
        }
        if (!language.contains("cmn") || !isVersionIsHigher(dataPackVersion, com.byd.vrassistant.hint.Constant.CMN_FREE_WORDS_SUPPORT_VERSION)) {
            return false;
        }
        RLog.e(TAG, "isSetShortCutCommand cmn may set shorcut");
        return true;
    }

    private boolean isVersionIsHigher(String str, String str2) {
        RLog.e(TAG, "isVersionIsHigher:" + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return ToolUtils.compareNewVersionIsHigher(str2, str.replace("V.", ""));
    }
}
