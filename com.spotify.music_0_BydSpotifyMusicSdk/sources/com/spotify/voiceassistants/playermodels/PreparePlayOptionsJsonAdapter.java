package com.spotify.voiceassistants.playermodels;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.b;
import com.spotify.base.java.logging.Logger;
import com.spotify.player.model.AudioStream;
import com.spotify.player.model.Suppressions;
import com.spotify.player.model.command.options.PlayerOptionOverrides;
import com.spotify.player.model.command.options.PrefetchLevel;
import com.spotify.player.model.command.options.PreparePlayOptions;
import com.spotify.player.model.command.options.SkipToTrack;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import p.ly0;
import p.ou;
import p.v03;
import p.zl2;

/* JADX INFO: loaded from: classes.dex */
public final class PreparePlayOptionsJsonAdapter extends f<PreparePlayOptions> {
    private final void readConfigurationOverride(JsonReader jsonReader, PreparePlayOptions.Builder builder) {
        b bVarA = ImmutableMap.a();
        jsonReader.c();
        while (jsonReader.hasNext()) {
            bVarA.b(jsonReader.r0(), String.valueOf(jsonReader.L0()));
        }
        jsonReader.q();
        builder.d(bVarA.a());
    }

    private final void readPlayerOptionOverrides(JsonReader jsonReader, PreparePlayOptions.Builder builder) {
        jsonReader.c();
        if (jsonReader.hasNext()) {
            PlayerOptionOverrides.Builder builderA = PlayerOptionOverrides.a();
            while (jsonReader.hasNext()) {
                String strR0 = jsonReader.r0();
                if (strR0 != null) {
                    int iHashCode = strR0.hashCode();
                    if (iHashCode != -1459599913) {
                        if (iHashCode != -1400336410) {
                            if (iHashCode == 45542259 && strR0.equals("repeating_track")) {
                                builderA.c(Boolean.valueOf(jsonReader.w0()));
                            }
                        } else if (strR0.equals("shuffling_context")) {
                            builderA.d(Boolean.valueOf(jsonReader.w0()));
                        }
                    } else if (strR0.equals("repeating_context")) {
                        builderA.b(Boolean.valueOf(jsonReader.w0()));
                    }
                }
                Logger.g("Unknown JSON property: %s", strR0);
                jsonReader.D();
            }
            builder.h(builderA.a());
        }
        jsonReader.q();
    }

    private final void readSupressions(JsonReader jsonReader, PreparePlayOptions.Builder builder) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        jsonReader.c();
        if (jsonReader.hasNext() && v03.b(jsonReader.r0(), "providers")) {
            jsonReader.b();
            while (jsonReader.hasNext()) {
                String strU = jsonReader.u();
                v03.i(strU, "jsonReader.nextString()");
                linkedHashSet.add(strU);
            }
            jsonReader.i();
        }
        jsonReader.q();
        if (!linkedHashSet.isEmpty()) {
            Set setQ0 = ou.q0(linkedHashSet);
            builder.getClass();
            builder.m(Suppressions.a(setQ0));
        }
    }

    private final void writeConfigurationOverride(m mVar, PreparePlayOptions preparePlayOptions) {
        mVar.J0("configuration_override").i();
        Set<Map.Entry> setEntrySet = preparePlayOptions.d().entrySet();
        v03.i(setEntrySet, "playOptions.configurationOverride().entries");
        for (Map.Entry entry : setEntrySet) {
            mVar.J0((String) entry.getKey()).W0((String) entry.getValue());
        }
        mVar.X();
    }

    private final void writePlayerOptionsOverride(m mVar, PreparePlayOptions preparePlayOptions) {
        mVar.J0("player_options_override").i();
        if (preparePlayOptions.h().d()) {
            PlayerOptionOverrides playerOptionOverrides = (PlayerOptionOverrides) preparePlayOptions.h().c();
            if (playerOptionOverrides.d().d()) {
                m mVarJ0 = mVar.J0("shuffling_context");
                Object objC = playerOptionOverrides.d().c();
                v03.i(objC, "optionsOverride.shufflingContext().get()");
                mVarJ0.X0(((Boolean) objC).booleanValue());
            }
            if (playerOptionOverrides.b().d()) {
                m mVarJ02 = mVar.J0("repeating_context");
                Object objC2 = playerOptionOverrides.b().c();
                v03.i(objC2, "optionsOverride.repeatingContext().get()");
                mVarJ02.X0(((Boolean) objC2).booleanValue());
            }
            if (playerOptionOverrides.c().d()) {
                m mVarJ03 = mVar.J0("repeating_track");
                Object objC3 = playerOptionOverrides.c().c();
                v03.i(objC3, "optionsOverride.repeatingTrack().get()");
                mVarJ03.X0(((Boolean) objC3).booleanValue());
            }
        }
        mVar.X();
    }

    private final void writeSkipTo(PreparePlayOptions preparePlayOptions, m mVar) {
        Optional optionalG;
        SkipToTrack skipToTrack = (SkipToTrack) preparePlayOptions.l().h();
        if (skipToTrack == null || (optionalG = skipToTrack.g()) == null || !optionalG.d()) {
            return;
        }
        mVar.J0("skip_to").i().J0("track_uri").W0((String) ((SkipToTrack) preparePlayOptions.l().c()).g().c()).X();
    }

    private final void writeSuppressions(m mVar, PreparePlayOptions preparePlayOptions) {
        mVar.J0("suppressions").i();
        if (preparePlayOptions.m().d()) {
            mVar.J0("providers").b();
            ImmutableSet immutableSetB = ((Suppressions) preparePlayOptions.m().c()).b();
            v03.i(immutableSetB, "playOptions.suppressions().get().providers()");
            Iterator<E> it = immutableSetB.iterator();
            while (it.hasNext()) {
                mVar.W0((String) it.next());
            }
            mVar.E();
        }
        mVar.X();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.squareup.moshi.f
    @ly0
    public PreparePlayOptions fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "jsonReader");
        jsonReader.c();
        PreparePlayOptions.Builder builderC = PreparePlayOptions.c();
        while (jsonReader.hasNext()) {
            String strR0 = jsonReader.r0();
            if (strR0 != null) {
                switch (strR0.hashCode()) {
                    case -2040777380:
                        if (strR0.equals("initially_paused")) {
                            builderC.e(jsonReader.w0());
                        }
                        break;
                    case -1869996565:
                        if (strR0.equals("player_options_override")) {
                            v03.i(builderC, "optionsBuilder");
                            readPlayerOptionOverrides(jsonReader, builderC);
                        }
                        break;
                    case -1593963275:
                        if (strR0.equals("configuration_override")) {
                            v03.i(builderC, "optionsBuilder");
                            readConfigurationOverride(jsonReader, builderC);
                        }
                        break;
                    case -1589692065:
                        if (strR0.equals("always_play_something")) {
                            builderC.a(jsonReader.w0());
                        }
                        break;
                    case -1434528759:
                        if (strR0.equals("audio_stream")) {
                            String strU = jsonReader.u();
                            v03.i(strU, "jsonReader.nextString()");
                            builderC.b(AudioStream.valueOf(strU));
                        }
                        break;
                    case 166757441:
                        if (strR0.equals("license")) {
                            builderC.f(jsonReader.u());
                        }
                        break;
                    case 725855648:
                        if (strR0.equals("suppressions")) {
                            v03.i(builderC, "optionsBuilder");
                            readSupressions(jsonReader, builderC);
                        }
                        break;
                    case 1164766012:
                        if (strR0.equals("prefetch_level")) {
                            String strU2 = jsonReader.u();
                            v03.i(strU2, "jsonReader.nextString()");
                            builderC.i(PrefetchLevel.valueOf(strU2));
                        }
                        break;
                    case 1578925787:
                        if (strR0.equals("system_initiated")) {
                            builderC.n(jsonReader.w0());
                        }
                        break;
                    case 1661853540:
                        if (strR0.equals("session_id")) {
                            builderC.k(jsonReader.u());
                        }
                        break;
                    case 1706303935:
                        if (strR0.equals("playback_id")) {
                            builderC.g(jsonReader.u());
                        }
                        break;
                    case 1971810722:
                        if (strR0.equals("seek_to")) {
                            builderC.j(Long.valueOf(jsonReader.Z()));
                        }
                        break;
                    case 2147428667:
                        if (strR0.equals("skip_to")) {
                            jsonReader.c();
                            if (jsonReader.hasNext() && v03.b(jsonReader.r0(), "track_uri")) {
                                builderC.l(SkipToTrack.b(jsonReader.u()));
                            }
                            jsonReader.q();
                        }
                        break;
                }
            }
            Logger.g("Unknown JSON property: %s", strR0);
            jsonReader.D();
        }
        jsonReader.q();
        PreparePlayOptions preparePlayOptionsC = builderC.c();
        v03.i(preparePlayOptionsC, "optionsBuilder.build()");
        return preparePlayOptionsC;
    }

    @Override // com.squareup.moshi.f
    @zl2
    public void toJson(m mVar, PreparePlayOptions preparePlayOptions) {
        v03.j(mVar, "writer");
        mVar.i();
        if (preparePlayOptions != null) {
            mVar.J0("playback_id").W0((String) preparePlayOptions.g().h());
            m mVarJ0 = mVar.J0("always_play_something");
            Optional<Boolean> optionalA = preparePlayOptions.a();
            Boolean bool = Boolean.FALSE;
            Object objG = optionalA.g(bool);
            v03.i(objG, "playOptionsNonNull.alwaysPlaySomething().or(false)");
            mVarJ0.X0(((Boolean) objG).booleanValue());
            writeSkipTo(preparePlayOptions, mVar);
            if (preparePlayOptions.j().d()) {
                m mVarJ02 = mVar.J0("seek_to");
                Object objC = preparePlayOptions.j().c();
                v03.i(objC, "playOptionsNonNull.seekTo().get()");
                mVarJ02.U0(((Number) objC).longValue());
            }
            m mVarJ03 = mVar.J0("initially_paused");
            Object objG2 = preparePlayOptions.e().g(bool);
            v03.i(objG2, "playOptionsNonNull.initiallyPaused().or(false)");
            mVarJ03.X0(((Boolean) objG2).booleanValue());
            if (preparePlayOptions.n().d()) {
                m mVarJ04 = mVar.J0("system_initiated");
                Object objC2 = preparePlayOptions.n().c();
                v03.i(objC2, "playOptionsNonNull.systemInitiated().get()");
                mVarJ04.X0(((Boolean) objC2).booleanValue());
            }
            writePlayerOptionsOverride(mVar, preparePlayOptions);
            writeSuppressions(mVar, preparePlayOptions);
            if (preparePlayOptions.i().d()) {
                mVar.J0("prefetch_level").W0(((PrefetchLevel) preparePlayOptions.i().c()).toString());
            }
            if (preparePlayOptions.b().d()) {
                mVar.J0("audio_stream").W0(preparePlayOptions.b().toString());
            }
            if (preparePlayOptions.k().d()) {
                mVar.J0("session_id").W0((String) preparePlayOptions.k().c());
            }
            if (preparePlayOptions.k().d()) {
                mVar.J0("license").W0((String) preparePlayOptions.f().c());
            }
            writeConfigurationOverride(mVar, preparePlayOptions);
        }
        mVar.X();
    }
}
