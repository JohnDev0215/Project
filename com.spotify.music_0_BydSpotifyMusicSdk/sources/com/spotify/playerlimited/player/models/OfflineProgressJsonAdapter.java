package com.spotify.playerlimited.player.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class OfflineProgressJsonAdapter extends f<OfflineProgress> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;
    public final f e;

    public OfflineProgressJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("download_speed", "percent_complete", "queued_bytes", "queued_tracks", "seconds_left", "synced_bytes", "synced_tracks", "syncing", "total_bytes", "total_tracks");
        v03.i(bVarA, "of(\"download_speed\",\n   …l_bytes\", \"total_tracks\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(Integer.class, emptySet, "downloadSpeed");
        v03.i(fVarF, "moshi.adapter(Int::class…tySet(), \"downloadSpeed\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(Float.class, emptySet, "percentComplete");
        v03.i(fVarF2, "moshi.adapter(Float::cla…Set(), \"percentComplete\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(Long.class, emptySet, "queuedBytes");
        v03.i(fVarF3, "moshi.adapter(Long::clas…mptySet(), \"queuedBytes\")");
        this.d = fVarF3;
        f fVarF4 = oVar.f(Boolean.class, emptySet, "syncing");
        v03.i(fVarF4, "moshi.adapter(Boolean::c…e, emptySet(), \"syncing\")");
        this.e = fVarF4;
    }

    @Override // com.squareup.moshi.f
    public final OfflineProgress fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Integer num = null;
        Long l = null;
        Integer num2 = null;
        Long l2 = null;
        Long l3 = null;
        Integer num3 = null;
        Boolean bool = null;
        Long l4 = null;
        Integer num4 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        Float f = null;
        while (jsonReader.hasNext()) {
            Integer num5 = num4;
            int iM0 = jsonReader.M0(this.a);
            Long l5 = l4;
            f fVar = this.d;
            Boolean bool2 = bool;
            f fVar2 = this.b;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    num = (Integer) fVar2.fromJson(jsonReader);
                    num4 = num5;
                    l4 = l5;
                    bool = bool2;
                    z = true;
                    continue;
                case 1:
                    f = (Float) this.c.fromJson(jsonReader);
                    num4 = num5;
                    l4 = l5;
                    bool = bool2;
                    z2 = true;
                    continue;
                case 2:
                    l = (Long) fVar.fromJson(jsonReader);
                    num4 = num5;
                    l4 = l5;
                    bool = bool2;
                    z3 = true;
                    continue;
                case 3:
                    num2 = (Integer) fVar2.fromJson(jsonReader);
                    num4 = num5;
                    l4 = l5;
                    bool = bool2;
                    z4 = true;
                    continue;
                case 4:
                    l2 = (Long) fVar.fromJson(jsonReader);
                    num4 = num5;
                    l4 = l5;
                    bool = bool2;
                    z5 = true;
                    continue;
                case 5:
                    l3 = (Long) fVar.fromJson(jsonReader);
                    num4 = num5;
                    l4 = l5;
                    bool = bool2;
                    z6 = true;
                    continue;
                case 6:
                    num3 = (Integer) fVar2.fromJson(jsonReader);
                    num4 = num5;
                    l4 = l5;
                    bool = bool2;
                    z7 = true;
                    continue;
                case 7:
                    bool = (Boolean) this.e.fromJson(jsonReader);
                    num4 = num5;
                    l4 = l5;
                    z8 = true;
                    continue;
                case 8:
                    l4 = (Long) fVar.fromJson(jsonReader);
                    num4 = num5;
                    bool = bool2;
                    z9 = true;
                    continue;
                case 9:
                    num4 = (Integer) fVar2.fromJson(jsonReader);
                    l4 = l5;
                    bool = bool2;
                    z10 = true;
                    continue;
            }
            num4 = num5;
            l4 = l5;
            bool = bool2;
        }
        Boolean bool3 = bool;
        Long l6 = l4;
        Integer num6 = num4;
        jsonReader.q();
        OfflineProgress offlineProgress = new OfflineProgress();
        if (z) {
            offlineProgress.h = num;
        }
        if (z2) {
            offlineProgress.i = f;
        }
        if (z3) {
            offlineProgress.b = l;
        }
        if (z4) {
            offlineProgress.a = num2;
        }
        if (z5) {
            offlineProgress.j = l2;
        }
        if (z6) {
            offlineProgress.d = l3;
        }
        if (z7) {
            offlineProgress.c = num3;
        }
        if (z8) {
            offlineProgress.g = bool3;
        }
        if (z9) {
            offlineProgress.f = l6;
        }
        if (z10) {
            offlineProgress.e = num6;
        }
        return offlineProgress;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, OfflineProgress offlineProgress) {
        OfflineProgress offlineProgress2 = offlineProgress;
        v03.j(mVar, "writer");
        if (offlineProgress2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("download_speed");
        Integer num = offlineProgress2.h;
        f fVar = this.b;
        fVar.toJson(mVar, num);
        mVar.J0("percent_complete");
        this.c.toJson(mVar, offlineProgress2.i);
        mVar.J0("queued_bytes");
        Long l = offlineProgress2.b;
        f fVar2 = this.d;
        fVar2.toJson(mVar, l);
        mVar.J0("queued_tracks");
        fVar.toJson(mVar, offlineProgress2.a);
        mVar.J0("seconds_left");
        fVar2.toJson(mVar, offlineProgress2.j);
        mVar.J0("synced_bytes");
        fVar2.toJson(mVar, offlineProgress2.d);
        mVar.J0("synced_tracks");
        fVar.toJson(mVar, offlineProgress2.c);
        mVar.J0("syncing");
        this.e.toJson(mVar, offlineProgress2.g);
        mVar.J0("total_bytes");
        fVar2.toJson(mVar, offlineProgress2.f);
        mVar.J0("total_tracks");
        fVar.toJson(mVar, offlineProgress2.e);
        mVar.X();
    }

    public final String toString() {
        return u4.b(37, "GeneratedJsonAdapter(OfflineProgress)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
