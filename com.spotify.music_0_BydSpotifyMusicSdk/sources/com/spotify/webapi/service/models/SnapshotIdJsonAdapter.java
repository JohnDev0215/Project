package com.spotify.webapi.service.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SnapshotIdJsonAdapter extends f<SnapshotId> {
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public SnapshotIdJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("snapshot_id");
        v03.i(bVarA, "of(\"snapshot_id\")");
        this.options = bVarA;
        f<String> fVarF = oVar.f(String.class, EmptySet.c, "snapshot_id");
        v03.i(fVarF, "moshi.adapter(String::cl…mptySet(), \"snapshot_id\")");
        this.nullableStringAdapter = fVarF;
    }

    public String toString() {
        return u4.b(32, "GeneratedJsonAdapter(SnapshotId)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public SnapshotId fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        SnapshotId snapshotId = new SnapshotId();
        if (z) {
            snapshotId.snapshot_id = strFromJson;
        }
        return snapshotId;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, SnapshotId snapshotId) {
        v03.j(mVar, "writer");
        if (snapshotId == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("snapshot_id");
        this.nullableStringAdapter.toJson(mVar, snapshotId.snapshot_id);
        mVar.X();
    }
}
