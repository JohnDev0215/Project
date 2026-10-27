package com.stingray.companion;

import A7.k;
import G7.c;
import H5.d;
import H5.e;
import H5.f;
import M5.b;
import S8.AbstractC0727y;
import S8.E;
import V8.AbstractC0828q;
import V8.J;
import V8.L;
import V8.N;
import V8.V;
import V8.d0;
import V8.f0;
import X8.m;
import android.annotation.SuppressLint;
import android.hardware.bydauto.ac.tsimpl.a;
import b7.AbstractC1001b;
import b7.C1000a;
import b7.i;
import b7.o;
import b7.r;
import b7.s;
import c7.InterfaceC1035a;
import com.stingray.companion.CompanionManager;
import com.stingray.companion.model.HostConfig;
import com.stingray.companion.model.HostInfo;
import com.stingray.companion.model.HostStatus;
import com.stingray.companion.model.LobbyInfo;
import com.stingray.companion.model.PlayerInfo;
import com.stingray.companion.model.PlaylistDataProperties;
import com.stingray.companion.model.SongQueueInfo;
import h2.AbstractC1347d;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import k7.C1707a;
import kotlin.Metadata;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 Q2\u00020\u0001:\u0001RB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001f\u0010\u001eJ\u0017\u0010\"\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020 H\u0002¢\u0006\u0004\b\"\u0010#Jc\u0010/\u001a\u00020\u000b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040$2\b\u0010&\u001a\u0004\u0018\u00010\u00042\b\u0010'\u001a\u0004\u0018\u00010\u00042\u0006\u0010(\u001a\u00020\u00062\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020)2\b\u0010.\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b/\u00100J\u001f\u00102\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0006H\u0002¢\u0006\u0004\b2\u00103J'\u00106\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u0006H\u0002¢\u0006\u0004\b6\u00107J\u000f\u00108\u001a\u00020\u000bH\u0002¢\u0006\u0004\b8\u0010\u0003J'\u00109\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00062\u0006\u0010-\u001a\u00020)H\u0002¢\u0006\u0004\b9\u0010:J\u0017\u0010<\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020;H\u0002¢\u0006\u0004\b<\u0010=J\u0017\u0010>\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020;H\u0002¢\u0006\u0004\b>\u0010=J\u0017\u0010@\u001a\u00020\u000b2\u0006\u0010?\u001a\u00020\u0004H\u0002¢\u0006\u0004\b@\u0010AJ\u000f\u0010B\u001a\u00020\u000bH\u0002¢\u0006\u0004\bB\u0010\u0003R\u0018\u0010C\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u0010DR\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020\u001b0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010GR\u001e\u0010J\u001a\n I*\u0004\u0018\u00010H0H8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0016\u0010M\u001a\u00020L8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020 8\u0002X\u0082D¢\u0006\u0006\n\u0004\bO\u0010P¨\u0006S"}, d2 = {"Lcom/stingray/companion/CompanionManager;", "", "<init>", "()V", "", "deviceId", "", "operatorId", "clientId", "Lcom/stingray/companion/model/HostConfig;", "hostConfig", "Lm7/q;", "connect", "(Ljava/lang/String;ILjava/lang/String;Lcom/stingray/companion/model/HostConfig;)V", "Lcom/stingray/companion/model/PlayerInfo;", "info", "updateCurrentPlayerInfo", "(Lcom/stingray/companion/model/PlayerInfo;)V", "Lcom/stingray/companion/model/SongQueueInfo;", "updateSongQueue", "(Lcom/stingray/companion/model/SongQueueInfo;)V", "updateHostConfig", "(Lcom/stingray/companion/model/HostConfig;)V", "Lcom/stingray/companion/model/HostStatus;", "hostStatus", "updateHostInfo", "(Lcom/stingray/companion/model/HostStatus;)V", "Lcom/stingray/companion/CompanionReceiveListener;", "listener", "addListener", "(Lcom/stingray/companion/CompanionReceiveListener;)V", "removeListener", "", "time", "onSeek", "(J)V", "", "songIds", "playlistId", "artistId", "index", "", "playNow", "resetQueue", "replaceOldestSongs", "scoreSong", "length", "onAddSongs", "([Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZZZZLjava/lang/Integer;)V", "songId", "onRemoveSong", "(Ljava/lang/String;I)V", "fromIndex", "toIndex", "onMoveSong", "(Ljava/lang/String;II)V", "onClearQueue", "onSelectSong", "(Ljava/lang/String;IZ)V", "Lcom/stingray/companion/model/LobbyInfo;", "reconnectLobby", "(Lcom/stingray/companion/model/LobbyInfo;)V", "createLobby", "data", "receive", "(Ljava/lang/String;)V", "onConnectionChanged", "currentInfo", "Lcom/stingray/companion/model/PlayerInfo;", "", "listeners", "Ljava/util/List;", "Lb7/r;", "kotlin.jvm.PlatformType", "socket", "Lb7/r;", "Ljava/util/Timer;", "sendPlaybackUpdateTimerTask", "Ljava/util/Timer;", "playbackUpdateTimerIntervalInMS", "J", "Companion", "H5/e", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CompanionManager {
    public static final e Companion = new e();
    private static L _companionSessionId;

    @SuppressLint({"ConstantLocale"})
    private static final L _languageFlow;
    private static final d0 companionSessionId;
    private static final d0 companionUrl;
    private PlayerInfo currentInfo;
    private final List<CompanionReceiveListener> listeners = new ArrayList();
    private final long playbackUpdateTimerIntervalInMS;
    private Timer sendPlaybackUpdateTimerTask;
    private r socket;

    static {
        f0 f0VarC = AbstractC0828q.c("");
        _companionSessionId = f0VarC;
        companionSessionId = new N(f0VarC);
        f0 f0VarC2 = AbstractC0828q.c(Locale.getDefault().getLanguage());
        _languageFlow = f0VarC2;
        J j3 = new J(_companionSessionId, f0VarC2, new d(3, null, 0));
        Z8.e eVar = E.f8084a;
        companionUrl = AbstractC0828q.m(j3, AbstractC0727y.a(m.f10573a), V.a(), "");
    }

    public CompanionManager() {
        i iVar;
        r rVar;
        String str;
        C1000a c1000a = new C1000a();
        c1000a.f13508k = new String[]{"websocket"};
        Logger logger = AbstractC1001b.f12277a;
        URI uri = new URI("https://karaoke-companion-bridge-prod.stingray.com");
        Pattern pattern = s.f12324a;
        String scheme = uri.getScheme();
        scheme = (scheme == null || !scheme.matches("^https?|wss?$")) ? "https" : scheme;
        int port = uri.getPort();
        if (port == -1) {
            if ("http".equals(scheme) || "ws".equals(scheme)) {
                port = 80;
            } else if ("https".equals(scheme) || "wss".equals(scheme)) {
                port = 443;
            }
        }
        String rawPath = uri.getRawPath();
        rawPath = (rawPath == null || rawPath.length() == 0) ? "/" : rawPath;
        String rawUserInfo = uri.getRawUserInfo();
        String rawQuery = uri.getRawQuery();
        String rawFragment = uri.getRawFragment();
        String host = uri.getHost();
        if (host == null) {
            String rawAuthority = uri.getRawAuthority();
            if (rawAuthority == null) {
                throw new RuntimeException("unable to parse the host from the authority");
            }
            Matcher matcher = s.f12324a.matcher(rawAuthority);
            if (!matcher.matches()) {
                throw new RuntimeException("unable to parse the host from the authority");
            }
            host = matcher.group(2);
        }
        StringBuilder sbL = k.l(scheme, "://");
        sbL.append(rawUserInfo != null ? rawUserInfo.concat("@") : "");
        sbL.append(host);
        sbL.append(port != -1 ? a.e(port, ":") : "");
        sbL.append(rawPath);
        sbL.append(rawQuery != null ? "?".concat(rawQuery) : "");
        sbL.append(rawFragment != null ? "#".concat(rawFragment) : "");
        URI uriCreate = URI.create(sbL.toString());
        String str2 = scheme + "://" + host + ":" + port;
        ConcurrentHashMap concurrentHashMap = AbstractC1001b.f12278b;
        boolean z6 = concurrentHashMap.containsKey(str2) && ((i) concurrentHashMap.get(str2)).B.containsKey(uriCreate.getPath());
        String query = uriCreate.getQuery();
        if (query != null && ((str = c1000a.f13509m) == null || str.isEmpty())) {
            c1000a.f13509m = query;
        }
        if (z6) {
            Logger logger2 = AbstractC1001b.f12277a;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.fine("ignoring socket cache for " + uriCreate);
            }
            iVar = new i(uriCreate, c1000a);
        } else {
            if (!concurrentHashMap.containsKey(str2)) {
                Logger logger3 = AbstractC1001b.f12277a;
                if (logger3.isLoggable(Level.FINE)) {
                    logger3.fine("new io instance for " + uriCreate);
                }
                concurrentHashMap.putIfAbsent(str2, new i(uriCreate, c1000a));
            }
            iVar = (i) concurrentHashMap.get(str2);
        }
        String path = uriCreate.getPath();
        synchronized (iVar.B) {
            try {
                rVar = (r) iVar.B.get(path);
                if (rVar == null) {
                    rVar = new r(iVar, path, c1000a);
                    iVar.B.put(path, rVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.socket = rVar;
        this.sendPlaybackUpdateTimerTask = new Timer();
        this.playbackUpdateTimerIntervalInMS = 5000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$0(CompanionManager companionManager, String str, int i7, String str2, HostConfig hostConfig, Object[] objArr) {
        d0 d0Var = companionSessionId;
        if (((CharSequence) d0Var.getValue()).length() == 0) {
            companionManager.createLobby(new LobbyInfo("", str, i7, str2, hostConfig));
        } else {
            companionManager.reconnectLobby(new LobbyInfo((String) d0Var.getValue(), str, i7, str2, hostConfig));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$1(Object[] objArr) {
        A7.m.c(objArr);
        A7.m.f("COMPANION: error " + AbstractC1996k.N(objArr), "text");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$10(CompanionManager companionManager, Object[] objArr) throws JSONException {
        Object obj = objArr[0];
        A7.m.d(obj, "null cannot be cast to non-null type org.json.JSONObject");
        Object obj2 = ((JSONObject) obj).get("position");
        A7.m.d(obj2, "null cannot be cast to non-null type kotlin.String");
        String str = (String) obj2;
        if (str.length() > 0) {
            A7.m.f("COMPANION: playbackSeek: ".concat(str), "text");
            companionManager.onSeek(Long.parseLong(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$15(CompanionManager companionManager, Object[] objArr) throws JSONException {
        String str;
        String str2;
        Integer num;
        String str3;
        String str4;
        boolean zBooleanValue = false;
        Object obj = objArr[0];
        A7.m.d(obj, "null cannot be cast to non-null type org.json.JSONObject");
        JSONObject jSONObject = (JSONObject) obj;
        String[] strArr = new String[0];
        Integer num2 = null;
        if (jSONObject.has("songIds")) {
            Object obj2 = jSONObject.get("songIds");
            A7.m.d(obj2, "null cannot be cast to non-null type org.json.JSONArray");
            JSONArray jSONArray = (JSONArray) obj2;
            G7.d dVarB0 = AbstractC2673a.b0(0, jSONArray.length());
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(dVarB0, 10));
            c it = dVarB0.iterator();
            while (it.f2715n) {
                arrayList.add(jSONArray.getString(it.b()));
            }
            strArr = (String[]) AbstractC1998m.U(arrayList).toArray(new String[0]);
        } else if (jSONObject.has("songs")) {
            Object obj3 = jSONObject.get("songs");
            A7.m.d(obj3, "null cannot be cast to non-null type org.json.JSONArray");
            JSONArray jSONArray2 = (JSONArray) obj3;
            G7.d dVarB02 = AbstractC2673a.b0(0, jSONArray2.length());
            ArrayList<JSONObject> arrayList2 = new ArrayList(AbstractC2000o.J(dVarB02, 10));
            c it2 = dVarB02.iterator();
            int i7 = 0;
            while (it2.f2715n) {
                it2.b();
                int i10 = i7 + 1;
                if (i7 < 0) {
                    AbstractC1999n.I();
                    throw null;
                }
                arrayList2.add(jSONArray2.getJSONObject(i7));
                i7 = i10;
            }
            ArrayList arrayList3 = new ArrayList();
            for (JSONObject jSONObject2 : arrayList2) {
                String string = jSONObject2.has("id") ? jSONObject2.getString("id") : null;
                if (string != null) {
                    arrayList3.add(string);
                }
            }
            strArr = (String[]) AbstractC1998m.U(arrayList3).toArray(new String[0]);
        }
        String[] strArr2 = strArr;
        Object obj4 = jSONObject.get("index");
        A7.m.d(obj4, "null cannot be cast to non-null type kotlin.Int");
        int iIntValue = ((Integer) obj4).intValue();
        Object obj5 = jSONObject.get("playNow");
        A7.m.d(obj5, "null cannot be cast to non-null type kotlin.Boolean");
        boolean zBooleanValue2 = ((Boolean) obj5).booleanValue();
        Object obj6 = jSONObject.get("resetQueue");
        A7.m.d(obj6, "null cannot be cast to non-null type kotlin.Boolean");
        boolean zBooleanValue3 = ((Boolean) obj6).booleanValue();
        Object obj7 = jSONObject.get("replaceOldestSongs");
        A7.m.d(obj7, "null cannot be cast to non-null type kotlin.Boolean");
        boolean zBooleanValue4 = ((Boolean) obj7).booleanValue();
        if (jSONObject.has("scoreSong")) {
            Object obj8 = jSONObject.get("scoreSong");
            A7.m.d(obj8, "null cannot be cast to non-null type kotlin.Boolean");
            zBooleanValue = ((Boolean) obj8).booleanValue();
        }
        boolean z6 = zBooleanValue;
        if (jSONObject.has("playlistData")) {
            Object obj9 = jSONObject.get("playlistData");
            A7.m.d(obj9, "null cannot be cast to non-null type org.json.JSONObject");
            JSONObject jSONObject3 = (JSONObject) obj9;
            PlaylistDataProperties playlistDataProperties = PlaylistDataProperties.PARTY_MIX_ID;
            if (jSONObject3.has(playlistDataProperties.getDataName())) {
                Object obj10 = jSONObject3.get(playlistDataProperties.getDataName());
                A7.m.d(obj10, "null cannot be cast to non-null type kotlin.String");
                str3 = (String) obj10;
            } else {
                str3 = null;
            }
            PlaylistDataProperties playlistDataProperties2 = PlaylistDataProperties.ARTIST_ID;
            if (jSONObject3.has(playlistDataProperties2.getDataName())) {
                Object obj11 = jSONObject3.get(playlistDataProperties2.getDataName());
                A7.m.d(obj11, "null cannot be cast to non-null type kotlin.String");
                str4 = (String) obj11;
            } else {
                str4 = null;
            }
            PlaylistDataProperties playlistDataProperties3 = PlaylistDataProperties.LENGTH;
            if (jSONObject3.has(playlistDataProperties3.getDataName())) {
                Object obj12 = jSONObject3.get(playlistDataProperties3.getDataName());
                A7.m.d(obj12, "null cannot be cast to non-null type kotlin.Int");
                num2 = (Integer) obj12;
            }
            str = str3;
            str2 = str4;
            num = num2;
        } else {
            str = null;
            str2 = null;
            num = null;
        }
        if (strArr2.length == 0) {
            return;
        }
        StringBuilder sbL = b.l("COMPANION: songQueueAddSongs for ", " songs at ", strArr2.length, iIntValue, " with playNow=");
        sbL.append(zBooleanValue2);
        sbL.append(" and resetQueue=");
        sbL.append(zBooleanValue3);
        A7.m.f(sbL.toString(), "text");
        companionManager.onAddSongs(strArr2, str, str2, iIntValue, zBooleanValue2, zBooleanValue3, zBooleanValue4, z6, num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$16(CompanionManager companionManager, Object[] objArr) throws JSONException {
        Object obj = objArr[0];
        A7.m.d(obj, "null cannot be cast to non-null type org.json.JSONObject");
        JSONObject jSONObject = (JSONObject) obj;
        Object obj2 = jSONObject.get("songId");
        A7.m.d(obj2, "null cannot be cast to non-null type kotlin.String");
        String str = (String) obj2;
        Object obj3 = jSONObject.get("fromIndex");
        A7.m.d(obj3, "null cannot be cast to non-null type kotlin.Int");
        int iIntValue = ((Integer) obj3).intValue();
        Object obj4 = jSONObject.get("toIndex");
        A7.m.d(obj4, "null cannot be cast to non-null type kotlin.Int");
        int iIntValue2 = ((Integer) obj4).intValue();
        if (str.length() > 0) {
            A7.m.f("COMPANION: songQueueMoveSong for: " + str + " from: " + iIntValue + " to: " + iIntValue2, "text");
            companionManager.onMoveSong(str, iIntValue, iIntValue2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$17(CompanionManager companionManager, Object[] objArr) throws JSONException {
        Object obj = objArr[0];
        A7.m.d(obj, "null cannot be cast to non-null type org.json.JSONObject");
        JSONObject jSONObject = (JSONObject) obj;
        Object obj2 = jSONObject.get("songId");
        A7.m.d(obj2, "null cannot be cast to non-null type kotlin.String");
        String str = (String) obj2;
        Object obj3 = jSONObject.get("index");
        A7.m.d(obj3, "null cannot be cast to non-null type kotlin.Int");
        int iIntValue = ((Integer) obj3).intValue();
        if (str.length() > 0) {
            A7.m.f("COMPANION: songQueueRemoveSong " + str + " at index " + iIntValue, "text");
            companionManager.onRemoveSong(str, iIntValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$18(CompanionManager companionManager, Object[] objArr) throws JSONException {
        boolean zBooleanValue = false;
        Object obj = objArr[0];
        A7.m.d(obj, "null cannot be cast to non-null type org.json.JSONObject");
        JSONObject jSONObject = (JSONObject) obj;
        Object obj2 = jSONObject.get("songId");
        A7.m.d(obj2, "null cannot be cast to non-null type kotlin.String");
        String str = (String) obj2;
        Object obj3 = jSONObject.get("index");
        A7.m.d(obj3, "null cannot be cast to non-null type kotlin.Int");
        int iIntValue = ((Integer) obj3).intValue();
        if (jSONObject.has("scoreSong")) {
            Object obj4 = jSONObject.get("scoreSong");
            A7.m.d(obj4, "null cannot be cast to non-null type kotlin.Boolean");
            zBooleanValue = ((Boolean) obj4).booleanValue();
        }
        if (str.length() > 0) {
            A7.m.f("COMPANION: songQueueSelectSong " + str + " at index " + iIntValue, "text");
            companionManager.onSelectSong(str, iIntValue, zBooleanValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$2(Object[] objArr) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$20(CompanionManager companionManager, String str, int i7, String str2, HostConfig hostConfig, Object[] objArr) throws JSONException {
        Object obj = objArr[0];
        A7.m.d(obj, "null cannot be cast to non-null type org.json.JSONObject");
        Object obj2 = ((JSONObject) obj).get("category");
        A7.m.d(obj2, "null cannot be cast to non-null type kotlin.String");
        String str3 = (String) obj2;
        A7.m.f("COMPANION: songQueueError: ".concat(str3), "text");
        if (str3.equals("songQueueUpdatedError")) {
            companionManager.reconnectLobby(new LobbyInfo((String) companionSessionId.getValue(), str, i7, str2, hostConfig));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$3(CompanionManager companionManager, Object[] objArr) throws JSONException {
        Object obj = objArr[0];
        A7.m.d(obj, "null cannot be cast to non-null type org.json.JSONObject");
        Object obj2 = ((JSONObject) obj).get("id");
        A7.m.d(obj2, "null cannot be cast to non-null type kotlin.String");
        String str = (String) obj2;
        if (str.length() > 0) {
            A7.m.f("COMPANION: lobbyCreated: ".concat(str), "text");
            Companion.getClass();
            e.a(str);
            companionManager.onConnectionChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$4(CompanionManager companionManager, Object[] objArr) throws JSONException {
        Object obj = objArr[0];
        A7.m.d(obj, "null cannot be cast to non-null type org.json.JSONObject");
        Object obj2 = ((JSONObject) obj).get("id");
        A7.m.d(obj2, "null cannot be cast to non-null type kotlin.String");
        String str = (String) obj2;
        if (str.length() > 0) {
            A7.m.f("COMPANION: lobbyReconnected: ".concat(str), "text");
            Companion.getClass();
            e.a(str);
            companionManager.onConnectionChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$5(CompanionManager companionManager, String str, int i7, String str2, HostConfig hostConfig, Object[] objArr) throws JSONException {
        Object obj = objArr[0];
        A7.m.d(obj, "null cannot be cast to non-null type org.json.JSONObject");
        Object obj2 = ((JSONObject) obj).get("category");
        A7.m.d(obj2, "null cannot be cast to non-null type kotlin.String");
        String str3 = (String) obj2;
        A7.m.f("COMPANION: lobbyError: ".concat(str3), "text");
        if (str3.equals("lobbyReconnectError")) {
            Companion.getClass();
            e.a("");
            companionManager.createLobby(new LobbyInfo("", str, i7, str2, hostConfig));
        } else if (str3.equals("lobbyCreationError")) {
            r rVar = companionManager.socket;
            rVar.getClass();
            C1707a.a(new o(rVar, 1));
            Companion.getClass();
            e.a("");
            r rVar2 = companionManager.socket;
            rVar2.getClass();
            C1707a.a(new o(rVar2, 0));
        }
    }

    private final void createLobby(LobbyInfo info) {
        A7.m.f("COMPANION: lobbyCreate " + info.getId(), "text");
        this.socket.N0("lobbyCreate", new JSONObject(new j5.m().e(info)));
    }

    private final void onAddSongs(String[] songIds, String playlistId, String artistId, int index, boolean playNow, boolean resetQueue, boolean replaceOldestSongs, boolean scoreSong, Integer length) {
        StringBuilder sb = new StringBuilder("COMPANION: onAddSongs: ");
        sb.append(songIds);
        AbstractC1347d.r(sb, ", ", playlistId, ", ", artistId);
        sb.append(", ");
        sb.append(index);
        sb.append(", ");
        sb.append(playNow);
        sb.append(", ");
        sb.append(resetQueue);
        sb.append(", ");
        sb.append(replaceOldestSongs);
        sb.append(", ");
        sb.append(length);
        A7.m.f(sb.toString(), "text");
        Iterator<CompanionReceiveListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onAddSongs(songIds, playlistId, artistId, index, playNow, resetQueue, replaceOldestSongs, scoreSong, length);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClearQueue() {
        Iterator<CompanionReceiveListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onClearQueue();
        }
    }

    private final synchronized void onConnectionChanged() {
        Iterator<CompanionReceiveListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onConnectionChanged();
        }
    }

    private final void onMoveSong(String songId, int fromIndex, int toIndex) {
        Iterator<CompanionReceiveListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onMoveSong(songId, fromIndex, toIndex);
        }
    }

    private final void onRemoveSong(String songId, int index) {
        Iterator<CompanionReceiveListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onRemoveSong(songId, index);
        }
    }

    private final void onSeek(long time) {
        Iterator<CompanionReceiveListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onSeek(time);
        }
    }

    private final void onSelectSong(String songId, int index, boolean scoreSong) {
        Iterator<CompanionReceiveListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onSelectSong(songId, index, scoreSong);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void receive(String data) {
        Iterator<CompanionReceiveListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onCommandReceived(data);
        }
    }

    private final void reconnectLobby(LobbyInfo info) {
        A7.m.f("COMPANION: lobbyReconnect: " + info, "text");
        this.socket.N0("lobbyReconnect", new JSONObject(new j5.m().e(info)));
    }

    public final void addListener(CompanionReceiveListener listener) {
        A7.m.f(listener, "listener");
        this.listeners.add(listener);
    }

    public final void connect(final String deviceId, final int operatorId, final String clientId, final HostConfig hostConfig) {
        A7.m.f(deviceId, "deviceId");
        A7.m.f(clientId, "clientId");
        A7.m.f(hostConfig, "hostConfig");
        if (this.socket.f12315n) {
            return;
        }
        final int i7 = 0;
        this.socket.T0("connect", new InterfaceC1035a(this) { // from class: H5.a

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2829b;

            {
                this.f2829b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                switch (i7) {
                    case 0:
                        CompanionManager.connect$lambda$0(this.f2829b, deviceId, operatorId, clientId, hostConfig, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$5(this.f2829b, deviceId, operatorId, clientId, hostConfig, objArr);
                        break;
                    default:
                        CompanionManager.connect$lambda$20(this.f2829b, deviceId, operatorId, clientId, hostConfig, objArr);
                        break;
                }
            }
        });
        final int i10 = 1;
        this.socket.T0("connect_error", new InterfaceC1035a() { // from class: H5.b
            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) {
                switch (i10) {
                    case 0:
                        CompanionManager.connect$lambda$2(objArr);
                        break;
                    default:
                        CompanionManager.connect$lambda$1(objArr);
                        break;
                }
            }
        });
        final int i11 = 0;
        this.socket.T0("disconnect", new InterfaceC1035a() { // from class: H5.b
            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) {
                switch (i11) {
                    case 0:
                        CompanionManager.connect$lambda$2(objArr);
                        break;
                    default:
                        CompanionManager.connect$lambda$1(objArr);
                        break;
                }
            }
        });
        final int i12 = 0;
        this.socket.T0("lobbyCreated", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i13 = i12;
                CompanionManager companionManager = this.f2834b;
                switch (i13) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i13 = 1;
        this.socket.T0("lobbyReconnected", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i13;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i14 = 1;
        this.socket.T0("lobbyError", new InterfaceC1035a(this) { // from class: H5.a

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2829b;

            {
                this.f2829b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                switch (i14) {
                    case 0:
                        CompanionManager.connect$lambda$0(this.f2829b, deviceId, operatorId, clientId, hostConfig, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$5(this.f2829b, deviceId, operatorId, clientId, hostConfig, objArr);
                        break;
                    default:
                        CompanionManager.connect$lambda$20(this.f2829b, deviceId, operatorId, clientId, hostConfig, objArr);
                        break;
                }
            }
        });
        final int i15 = 2;
        this.socket.T0("playbackPaused", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i15;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i16 = 3;
        this.socket.T0("playbackResumed", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i16;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i17 = 4;
        this.socket.T0("playbackSkip", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i17;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i18 = 5;
        this.socket.T0("playbackPrevious", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i18;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i19 = 6;
        this.socket.T0("playbackSeek", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i19;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i20 = 7;
        this.socket.T0("playbackUpdateNeeded", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i20;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i21 = 8;
        this.socket.T0("songQueueAddSongs", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i21;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i22 = 9;
        this.socket.T0("songQueueMoveSong", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i22;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i23 = 10;
        this.socket.T0("songQueueRemoveSong", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i23;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i24 = 11;
        this.socket.T0("songQueueSelectSong", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i24;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i25 = 12;
        this.socket.T0("songQueueClear", new InterfaceC1035a(this) { // from class: H5.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2834b;

            {
                this.f2834b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                int i132 = i25;
                CompanionManager companionManager = this.f2834b;
                switch (i132) {
                    case 0:
                        CompanionManager.connect$lambda$3(companionManager, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$4(companionManager, objArr);
                        break;
                    case 2:
                        companionManager.receive("paused");
                        break;
                    case 3:
                        companionManager.receive("resumed");
                        break;
                    case 4:
                        companionManager.receive("skip");
                        break;
                    case 5:
                        companionManager.receive("previous");
                        break;
                    case 6:
                        CompanionManager.connect$lambda$10(companionManager, objArr);
                        break;
                    case 7:
                        companionManager.receive("status");
                        break;
                    case 8:
                        CompanionManager.connect$lambda$15(companionManager, objArr);
                        break;
                    case 9:
                        CompanionManager.connect$lambda$16(companionManager, objArr);
                        break;
                    case 10:
                        CompanionManager.connect$lambda$17(companionManager, objArr);
                        break;
                    case 11:
                        CompanionManager.connect$lambda$18(companionManager, objArr);
                        break;
                    default:
                        companionManager.onClearQueue();
                        break;
                }
            }
        });
        final int i26 = 2;
        this.socket.T0("songQueueError", new InterfaceC1035a(this) { // from class: H5.a

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CompanionManager f2829b;

            {
                this.f2829b = this;
            }

            @Override // c7.InterfaceC1035a
            public final void q(Object[] objArr) throws JSONException {
                switch (i26) {
                    case 0:
                        CompanionManager.connect$lambda$0(this.f2829b, deviceId, operatorId, clientId, hostConfig, objArr);
                        break;
                    case 1:
                        CompanionManager.connect$lambda$5(this.f2829b, deviceId, operatorId, clientId, hostConfig, objArr);
                        break;
                    default:
                        CompanionManager.connect$lambda$20(this.f2829b, deviceId, operatorId, clientId, hostConfig, objArr);
                        break;
                }
            }
        });
        r rVar = this.socket;
        rVar.getClass();
        C1707a.a(new o(rVar, 0));
        this.sendPlaybackUpdateTimerTask.schedule(new f(this, 0), 0L, this.playbackUpdateTimerIntervalInMS);
    }

    public final void removeListener(CompanionReceiveListener listener) {
        A7.m.f(listener, "listener");
        this.listeners.remove(listener);
    }

    public final void updateCurrentPlayerInfo(PlayerInfo info) {
        A7.m.f(info, "info");
        if (((CharSequence) companionSessionId.getValue()).length() <= 0 || !this.socket.f12315n) {
            return;
        }
        this.currentInfo = info;
        this.socket.N0("playbackUpdate", new JSONObject(new j5.m().e(info)));
    }

    public final void updateHostConfig(HostConfig hostConfig) {
        A7.m.f(hostConfig, "hostConfig");
        if (((CharSequence) companionSessionId.getValue()).length() <= 0 || !this.socket.f12315n) {
            return;
        }
        HashMap map = new HashMap();
        map.put("hostConfig", hostConfig);
        this.socket.N0("hostConfigUpdated", new JSONObject(new j5.m().e(map)));
    }

    public final void updateHostInfo(HostStatus hostStatus) {
        A7.m.f(hostStatus, "hostStatus");
        if (((CharSequence) companionSessionId.getValue()).length() <= 0 || !this.socket.f12315n) {
            return;
        }
        this.socket.N0("hostInfoUpdated", new JSONObject(new j5.m().e(new HostInfo(hostStatus))));
    }

    public final void updateSongQueue(SongQueueInfo info) {
        A7.m.f(info, "info");
        if (((CharSequence) companionSessionId.getValue()).length() <= 0 || !this.socket.f12315n) {
            return;
        }
        this.socket.N0("songQueueUpdated", new JSONObject(new j5.m().e(info)));
    }
}
