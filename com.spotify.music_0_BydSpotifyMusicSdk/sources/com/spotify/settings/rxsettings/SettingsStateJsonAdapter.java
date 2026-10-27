package com.spotify.settings.rxsettings;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SettingsStateJsonAdapter extends f<SettingsState> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;

    public SettingsStateJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("offline_mode", "play_explicit_content", "private_session", "download_over_3g", "download_quality", "stream_quality", "stream_non_metered_quality", "allow_audio_quality_downgrade", "ap", "gapless", "automix", "normalize", "loudness_environment", "crossfade", "crossfade_time_seconds", "show_unavailable_tracks", "local_devices_only", "webgate_url", "download_preferred_resource_type", "trim_silence", "downmix", "connect_debug", "dynamic_normalizer", "on_the_fly_normalizer");
        v03.i(bVarA, "of(\"offline_mode\",\n     … \"on_the_fly_normalizer\")");
        this.a = bVarA;
        Class cls = Boolean.TYPE;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(cls, emptySet, "offlineMode");
        v03.i(fVarF, "moshi.adapter(Boolean::c…t(),\n      \"offlineMode\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(Integer.TYPE, emptySet, "downloadQuality");
        v03.i(fVarF2, "moshi.adapter(Int::class…\n      \"downloadQuality\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(String.class, emptySet, "accessPoint");
        v03.i(fVarF3, "moshi.adapter(String::cl…t(),\n      \"accessPoint\")");
        this.d = fVarF3;
    }

    @Override // com.squareup.moshi.f
    public final SettingsState fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean bool = null;
        Boolean bool2 = null;
        Boolean bool3 = null;
        Boolean bool4 = null;
        Integer num = null;
        Integer num2 = null;
        Integer num3 = null;
        Boolean bool5 = null;
        Boolean bool6 = null;
        Boolean bool7 = null;
        Boolean bool8 = null;
        Integer num4 = null;
        Boolean bool9 = null;
        Integer num5 = null;
        Boolean bool10 = null;
        Boolean bool11 = null;
        Integer num6 = null;
        String str = null;
        Boolean bool12 = null;
        Boolean bool13 = null;
        Boolean bool14 = null;
        Boolean bool15 = null;
        Boolean bool16 = null;
        String str2 = null;
        while (true) {
            Integer num7 = num5;
            Boolean bool17 = bool9;
            Integer num8 = num4;
            Boolean bool18 = bool8;
            Boolean bool19 = bool7;
            Boolean bool20 = bool6;
            Boolean bool21 = bool5;
            Integer num9 = num3;
            Integer num10 = num2;
            Integer num11 = num;
            Boolean bool22 = bool4;
            Boolean bool23 = bool3;
            Boolean bool24 = bool2;
            Boolean bool25 = bool;
            if (!jsonReader.hasNext()) {
                jsonReader.q();
                if (bool25 == null) {
                    JsonDataException jsonDataExceptionO = ds2.o("offlineMode", "offline_mode", jsonReader);
                    v03.i(jsonDataExceptionO, "missingProperty(\"offline…ode\",\n            reader)");
                    throw jsonDataExceptionO;
                }
                boolean zBooleanValue = bool25.booleanValue();
                if (bool24 == null) {
                    JsonDataException jsonDataExceptionO2 = ds2.o("playExplicitContent", "play_explicit_content", jsonReader);
                    v03.i(jsonDataExceptionO2, "missingProperty(\"playExp…xplicit_content\", reader)");
                    throw jsonDataExceptionO2;
                }
                boolean zBooleanValue2 = bool24.booleanValue();
                if (bool23 == null) {
                    JsonDataException jsonDataExceptionO3 = ds2.o("privateSession", "private_session", jsonReader);
                    v03.i(jsonDataExceptionO3, "missingProperty(\"private…private_session\", reader)");
                    throw jsonDataExceptionO3;
                }
                boolean zBooleanValue3 = bool23.booleanValue();
                if (bool22 == null) {
                    JsonDataException jsonDataExceptionO4 = ds2.o("downloadOver3g", "download_over_3g", jsonReader);
                    v03.i(jsonDataExceptionO4, "missingProperty(\"downloa…ownload_over_3g\", reader)");
                    throw jsonDataExceptionO4;
                }
                boolean zBooleanValue4 = bool22.booleanValue();
                if (num11 == null) {
                    JsonDataException jsonDataExceptionO5 = ds2.o("downloadQuality", "download_quality", jsonReader);
                    v03.i(jsonDataExceptionO5, "missingProperty(\"downloa…ownload_quality\", reader)");
                    throw jsonDataExceptionO5;
                }
                int iIntValue = num11.intValue();
                if (num10 == null) {
                    JsonDataException jsonDataExceptionO6 = ds2.o("streamQuality", "stream_quality", jsonReader);
                    v03.i(jsonDataExceptionO6, "missingProperty(\"streamQ…\"stream_quality\", reader)");
                    throw jsonDataExceptionO6;
                }
                int iIntValue2 = num10.intValue();
                if (num9 == null) {
                    JsonDataException jsonDataExceptionO7 = ds2.o("streamNonMeteredQuality", "stream_non_metered_quality", jsonReader);
                    v03.i(jsonDataExceptionO7, "missingProperty(\"streamN…ity\",\n            reader)");
                    throw jsonDataExceptionO7;
                }
                int iIntValue3 = num9.intValue();
                if (bool21 == null) {
                    JsonDataException jsonDataExceptionO8 = ds2.o("allowAudioQualityDowngrade", "allow_audio_quality_downgrade", jsonReader);
                    v03.i(jsonDataExceptionO8, "missingProperty(\"allowAu…ality_downgrade\", reader)");
                    throw jsonDataExceptionO8;
                }
                boolean zBooleanValue5 = bool21.booleanValue();
                if (str == null) {
                    JsonDataException jsonDataExceptionO9 = ds2.o("accessPoint", "ap", jsonReader);
                    v03.i(jsonDataExceptionO9, "missingProperty(\"accessPoint\", \"ap\", reader)");
                    throw jsonDataExceptionO9;
                }
                if (bool20 == null) {
                    JsonDataException jsonDataExceptionO10 = ds2.o("gapless", "gapless", jsonReader);
                    v03.i(jsonDataExceptionO10, "missingProperty(\"gapless\", \"gapless\", reader)");
                    throw jsonDataExceptionO10;
                }
                boolean zBooleanValue6 = bool20.booleanValue();
                if (bool19 == null) {
                    JsonDataException jsonDataExceptionO11 = ds2.o("automix", "automix", jsonReader);
                    v03.i(jsonDataExceptionO11, "missingProperty(\"automix\", \"automix\", reader)");
                    throw jsonDataExceptionO11;
                }
                boolean zBooleanValue7 = bool19.booleanValue();
                if (bool18 == null) {
                    JsonDataException jsonDataExceptionO12 = ds2.o("normalize", "normalize", jsonReader);
                    v03.i(jsonDataExceptionO12, "missingProperty(\"normalize\", \"normalize\", reader)");
                    throw jsonDataExceptionO12;
                }
                boolean zBooleanValue8 = bool18.booleanValue();
                if (num8 == null) {
                    JsonDataException jsonDataExceptionO13 = ds2.o("loudnessEnvironment", "loudness_environment", jsonReader);
                    v03.i(jsonDataExceptionO13, "missingProperty(\"loudnes…ess_environment\", reader)");
                    throw jsonDataExceptionO13;
                }
                int iIntValue4 = num8.intValue();
                if (bool17 == null) {
                    JsonDataException jsonDataExceptionO14 = ds2.o("crossfade", "crossfade", jsonReader);
                    v03.i(jsonDataExceptionO14, "missingProperty(\"crossfade\", \"crossfade\", reader)");
                    throw jsonDataExceptionO14;
                }
                boolean zBooleanValue9 = bool17.booleanValue();
                if (num7 == null) {
                    JsonDataException jsonDataExceptionO15 = ds2.o("crossfadeTimeSeconds", "crossfade_time_seconds", jsonReader);
                    v03.i(jsonDataExceptionO15, "missingProperty(\"crossfa…de_time_seconds\", reader)");
                    throw jsonDataExceptionO15;
                }
                int iIntValue5 = num7.intValue();
                if (bool10 == null) {
                    JsonDataException jsonDataExceptionO16 = ds2.o("showUnavailableTracks", "show_unavailable_tracks", jsonReader);
                    v03.i(jsonDataExceptionO16, "missingProperty(\"showUna…vailable_tracks\", reader)");
                    throw jsonDataExceptionO16;
                }
                boolean zBooleanValue10 = bool10.booleanValue();
                if (bool11 == null) {
                    JsonDataException jsonDataExceptionO17 = ds2.o("localDevicesOnly", "local_devices_only", jsonReader);
                    v03.i(jsonDataExceptionO17, "missingProperty(\"localDe…al_devices_only\", reader)");
                    throw jsonDataExceptionO17;
                }
                boolean zBooleanValue11 = bool11.booleanValue();
                if (str2 == null) {
                    JsonDataException jsonDataExceptionO18 = ds2.o("webgateUrl", "webgate_url", jsonReader);
                    v03.i(jsonDataExceptionO18, "missingProperty(\"webgate…\", \"webgate_url\", reader)");
                    throw jsonDataExceptionO18;
                }
                if (num6 == null) {
                    JsonDataException jsonDataExceptionO19 = ds2.o("downloadPreferredResourceType", "download_preferred_resource_type", jsonReader);
                    v03.i(jsonDataExceptionO19, "missingProperty(\"downloa…d_resource_type\", reader)");
                    throw jsonDataExceptionO19;
                }
                int iIntValue6 = num6.intValue();
                if (bool12 == null) {
                    JsonDataException jsonDataExceptionO20 = ds2.o("silenceTrimmer", "trim_silence", jsonReader);
                    v03.i(jsonDataExceptionO20, "missingProperty(\"silence…  \"trim_silence\", reader)");
                    throw jsonDataExceptionO20;
                }
                boolean zBooleanValue12 = bool12.booleanValue();
                if (bool13 == null) {
                    JsonDataException jsonDataExceptionO21 = ds2.o("stereoMonoDownmixer", "downmix", jsonReader);
                    v03.i(jsonDataExceptionO21, "missingProperty(\"stereoM…ixer\", \"downmix\", reader)");
                    throw jsonDataExceptionO21;
                }
                boolean zBooleanValue13 = bool13.booleanValue();
                if (bool14 == null) {
                    JsonDataException jsonDataExceptionO22 = ds2.o("connectDebug", "connect_debug", jsonReader);
                    v03.i(jsonDataExceptionO22, "missingProperty(\"connect…bug\",\n            reader)");
                    throw jsonDataExceptionO22;
                }
                boolean zBooleanValue14 = bool14.booleanValue();
                if (bool15 == null) {
                    JsonDataException jsonDataExceptionO23 = ds2.o("dynamicNormalizer", "dynamic_normalizer", jsonReader);
                    v03.i(jsonDataExceptionO23, "missingProperty(\"dynamic…amic_normalizer\", reader)");
                    throw jsonDataExceptionO23;
                }
                boolean zBooleanValue15 = bool15.booleanValue();
                if (bool16 != null) {
                    return new SettingsState(zBooleanValue, zBooleanValue2, zBooleanValue3, zBooleanValue4, iIntValue, iIntValue2, iIntValue3, zBooleanValue5, str, zBooleanValue6, zBooleanValue7, zBooleanValue8, iIntValue4, zBooleanValue9, iIntValue5, zBooleanValue10, zBooleanValue11, str2, iIntValue6, zBooleanValue12, zBooleanValue13, zBooleanValue14, zBooleanValue15, bool16.booleanValue());
                }
                JsonDataException jsonDataExceptionO24 = ds2.o("onTheFlyNormalizer", "on_the_fly_normalizer", jsonReader);
                v03.i(jsonDataExceptionO24, "missingProperty(\"onTheFl…_fly_normalizer\", reader)");
                throw jsonDataExceptionO24;
            }
            int iM0 = jsonReader.M0(this.a);
            f fVar = this.d;
            f fVar2 = this.c;
            f fVar3 = this.b;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 0:
                    bool = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("offlineMode", "offline_mode", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"offlineM…, \"offline_mode\", reader)");
                        throw jsonDataExceptionW;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    break;
                    break;
                case 1:
                    bool2 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("playExplicitContent", "play_explicit_content", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"playExpl…xplicit_content\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool = bool25;
                    break;
                    break;
                case 2:
                    bool3 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool3 == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("privateSession", "private_session", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"privateS…private_session\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 3:
                    bool4 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool4 == null) {
                        JsonDataException jsonDataExceptionW4 = ds2.w("downloadOver3g", "download_over_3g", jsonReader);
                        v03.i(jsonDataExceptionW4, "unexpectedNull(\"download…ownload_over_3g\", reader)");
                        throw jsonDataExceptionW4;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 4:
                    num = (Integer) fVar2.fromJson(jsonReader);
                    if (num == null) {
                        JsonDataException jsonDataExceptionW5 = ds2.w("downloadQuality", "download_quality", jsonReader);
                        v03.i(jsonDataExceptionW5, "unexpectedNull(\"download…ownload_quality\", reader)");
                        throw jsonDataExceptionW5;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 5:
                    num2 = (Integer) fVar2.fromJson(jsonReader);
                    if (num2 == null) {
                        JsonDataException jsonDataExceptionW6 = ds2.w("streamQuality", "stream_quality", jsonReader);
                        v03.i(jsonDataExceptionW6, "unexpectedNull(\"streamQu…\"stream_quality\", reader)");
                        throw jsonDataExceptionW6;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 6:
                    num3 = (Integer) fVar2.fromJson(jsonReader);
                    if (num3 == null) {
                        JsonDataException jsonDataExceptionW7 = ds2.w("streamNonMeteredQuality", "stream_non_metered_quality", jsonReader);
                        v03.i(jsonDataExceptionW7, "unexpectedNull(\"streamNo…ity\",\n            reader)");
                        throw jsonDataExceptionW7;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 7:
                    bool5 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool5 == null) {
                        JsonDataException jsonDataExceptionW8 = ds2.w("allowAudioQualityDowngrade", "allow_audio_quality_downgrade", jsonReader);
                        v03.i(jsonDataExceptionW8, "unexpectedNull(\"allowAud…ade\",\n            reader)");
                        throw jsonDataExceptionW8;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 8:
                    str = (String) fVar.fromJson(jsonReader);
                    if (str == null) {
                        JsonDataException jsonDataExceptionW9 = ds2.w("accessPoint", "ap", jsonReader);
                        v03.i(jsonDataExceptionW9, "unexpectedNull(\"accessPoint\", \"ap\", reader)");
                        throw jsonDataExceptionW9;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 9:
                    bool6 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool6 == null) {
                        JsonDataException jsonDataExceptionW10 = ds2.w("gapless", "gapless", jsonReader);
                        v03.i(jsonDataExceptionW10, "unexpectedNull(\"gapless\"…       \"gapless\", reader)");
                        throw jsonDataExceptionW10;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 10:
                    bool7 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool7 == null) {
                        JsonDataException jsonDataExceptionW11 = ds2.w("automix", "automix", jsonReader);
                        v03.i(jsonDataExceptionW11, "unexpectedNull(\"automix\"…       \"automix\", reader)");
                        throw jsonDataExceptionW11;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 11:
                    bool8 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool8 == null) {
                        JsonDataException jsonDataExceptionW12 = ds2.w("normalize", "normalize", jsonReader);
                        v03.i(jsonDataExceptionW12, "unexpectedNull(\"normaliz…     \"normalize\", reader)");
                        throw jsonDataExceptionW12;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 12:
                    num4 = (Integer) fVar2.fromJson(jsonReader);
                    if (num4 == null) {
                        JsonDataException jsonDataExceptionW13 = ds2.w("loudnessEnvironment", "loudness_environment", jsonReader);
                        v03.i(jsonDataExceptionW13, "unexpectedNull(\"loudness…ess_environment\", reader)");
                        throw jsonDataExceptionW13;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 13:
                    bool9 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool9 == null) {
                        JsonDataException jsonDataExceptionW14 = ds2.w("crossfade", "crossfade", jsonReader);
                        v03.i(jsonDataExceptionW14, "unexpectedNull(\"crossfad…     \"crossfade\", reader)");
                        throw jsonDataExceptionW14;
                    }
                    num5 = num7;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                    break;
                case 14:
                    num5 = (Integer) fVar2.fromJson(jsonReader);
                    if (num5 == null) {
                        JsonDataException jsonDataExceptionW15 = ds2.w("crossfadeTimeSeconds", "crossfade_time_seconds", jsonReader);
                        v03.i(jsonDataExceptionW15, "unexpectedNull(\"crossfad…de_time_seconds\", reader)");
                        throw jsonDataExceptionW15;
                    }
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 15:
                    bool10 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool10 == null) {
                        JsonDataException jsonDataExceptionW16 = ds2.w("showUnavailableTracks", "show_unavailable_tracks", jsonReader);
                        v03.i(jsonDataExceptionW16, "unexpectedNull(\"showUnav…vailable_tracks\", reader)");
                        throw jsonDataExceptionW16;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 16:
                    bool11 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool11 == null) {
                        JsonDataException jsonDataExceptionW17 = ds2.w("localDevicesOnly", "local_devices_only", jsonReader);
                        v03.i(jsonDataExceptionW17, "unexpectedNull(\"localDev…al_devices_only\", reader)");
                        throw jsonDataExceptionW17;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 17:
                    str2 = (String) fVar.fromJson(jsonReader);
                    if (str2 == null) {
                        JsonDataException jsonDataExceptionW18 = ds2.w("webgateUrl", "webgate_url", jsonReader);
                        v03.i(jsonDataExceptionW18, "unexpectedNull(\"webgateU…   \"webgate_url\", reader)");
                        throw jsonDataExceptionW18;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 18:
                    num6 = (Integer) fVar2.fromJson(jsonReader);
                    if (num6 == null) {
                        JsonDataException jsonDataExceptionW19 = ds2.w("downloadPreferredResourceType", "download_preferred_resource_type", jsonReader);
                        v03.i(jsonDataExceptionW19, "unexpectedNull(\"download…d_resource_type\", reader)");
                        throw jsonDataExceptionW19;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 19:
                    bool12 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool12 == null) {
                        JsonDataException jsonDataExceptionW20 = ds2.w("silenceTrimmer", "trim_silence", jsonReader);
                        v03.i(jsonDataExceptionW20, "unexpectedNull(\"silenceT…, \"trim_silence\", reader)");
                        throw jsonDataExceptionW20;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 20:
                    bool13 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool13 == null) {
                        JsonDataException jsonDataExceptionW21 = ds2.w("stereoMonoDownmixer", "downmix", jsonReader);
                        v03.i(jsonDataExceptionW21, "unexpectedNull(\"stereoMo…ixer\", \"downmix\", reader)");
                        throw jsonDataExceptionW21;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 21:
                    bool14 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool14 == null) {
                        JsonDataException jsonDataExceptionW22 = ds2.w("connectDebug", "connect_debug", jsonReader);
                        v03.i(jsonDataExceptionW22, "unexpectedNull(\"connectD… \"connect_debug\", reader)");
                        throw jsonDataExceptionW22;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 22:
                    bool15 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool15 == null) {
                        JsonDataException jsonDataExceptionW23 = ds2.w("dynamicNormalizer", "dynamic_normalizer", jsonReader);
                        v03.i(jsonDataExceptionW23, "unexpectedNull(\"dynamicN…amic_normalizer\", reader)");
                        throw jsonDataExceptionW23;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                case 23:
                    bool16 = (Boolean) fVar3.fromJson(jsonReader);
                    if (bool16 == null) {
                        JsonDataException jsonDataExceptionW24 = ds2.w("onTheFlyNormalizer", "on_the_fly_normalizer", jsonReader);
                        v03.i(jsonDataExceptionW24, "unexpectedNull(\"onTheFly…_fly_normalizer\", reader)");
                        throw jsonDataExceptionW24;
                    }
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
                default:
                    num5 = num7;
                    bool9 = bool17;
                    num4 = num8;
                    bool8 = bool18;
                    bool7 = bool19;
                    bool6 = bool20;
                    bool5 = bool21;
                    num3 = num9;
                    num2 = num10;
                    num = num11;
                    bool4 = bool22;
                    bool3 = bool23;
                    bool2 = bool24;
                    bool = bool25;
                    break;
            }
        }
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, SettingsState settingsState) {
        SettingsState settingsState2 = settingsState;
        v03.j(mVar, "writer");
        if (settingsState2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("offline_mode");
        Boolean boolValueOf = Boolean.valueOf(settingsState2.a);
        f fVar = this.b;
        fVar.toJson(mVar, boolValueOf);
        mVar.J0("play_explicit_content");
        u4.w(settingsState2.b, fVar, mVar, "private_session");
        u4.w(settingsState2.c, fVar, mVar, "download_over_3g");
        u4.w(settingsState2.d, fVar, mVar, "download_quality");
        Integer numValueOf = Integer.valueOf(settingsState2.e);
        f fVar2 = this.c;
        fVar2.toJson(mVar, numValueOf);
        mVar.J0("stream_quality");
        u4.s(settingsState2.f, fVar2, mVar, "stream_non_metered_quality");
        u4.s(settingsState2.g, fVar2, mVar, "allow_audio_quality_downgrade");
        u4.w(settingsState2.h, fVar, mVar, "ap");
        String str = settingsState2.i;
        f fVar3 = this.d;
        fVar3.toJson(mVar, str);
        mVar.J0("gapless");
        u4.w(settingsState2.j, fVar, mVar, "automix");
        u4.w(settingsState2.k, fVar, mVar, "normalize");
        u4.w(settingsState2.l, fVar, mVar, "loudness_environment");
        u4.s(settingsState2.m, fVar2, mVar, "crossfade");
        u4.w(settingsState2.n, fVar, mVar, "crossfade_time_seconds");
        u4.s(settingsState2.o, fVar2, mVar, "show_unavailable_tracks");
        u4.w(settingsState2.f64p, fVar, mVar, "local_devices_only");
        u4.w(settingsState2.q, fVar, mVar, "webgate_url");
        fVar3.toJson(mVar, settingsState2.r);
        mVar.J0("download_preferred_resource_type");
        u4.s(settingsState2.s, fVar2, mVar, "trim_silence");
        u4.w(settingsState2.t, fVar, mVar, "downmix");
        fVar.toJson(mVar, Boolean.valueOf(settingsState2.u));
        mVar.J0("connect_debug");
        u4.w(settingsState2.v, fVar, mVar, "dynamic_normalizer");
        u4.w(settingsState2.w, fVar, mVar, "on_the_fly_normalizer");
        fVar.toJson(mVar, Boolean.valueOf(settingsState2.x));
        mVar.X();
    }

    public final String toString() {
        return u4.b(35, "GeneratedJsonAdapter(SettingsState)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
