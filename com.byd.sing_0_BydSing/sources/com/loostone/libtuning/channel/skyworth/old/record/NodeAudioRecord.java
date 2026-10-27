package com.loostone.libtuning.channel.skyworth.old.record;

import OooO00o.OooO00o.OooO0O0.OooO0o0.f.a;
import OooO00o.OooO00o.OooO0O0.b;

/* JADX INFO: loaded from: classes.dex */
public class NodeAudioRecord implements IAudioRecord {
    private final String TAG = "NodeAudioRecord";

    @Override // com.loostone.libtuning.channel.skyworth.old.record.IAudioRecord
    public void create(int i, int i2, int i3, int i4) {
        b.f41a.b("NodeAudioRecord", "create");
        a.f37a.b(i, i2, i3);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.record.IAudioRecord
    public void destroy() {
        b.f41a.b("NodeAudioRecord", "destroy");
        a.f37a.d();
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.record.IAudioRecord
    public int read(byte[] bArr, int i, int i2) {
        return a.f37a.c(bArr, i, i2);
    }

    @Override // com.loostone.libtuning.channel.skyworth.old.record.IAudioRecord
    public void read2Cache() {
        a.f37a.a();
    }
}
