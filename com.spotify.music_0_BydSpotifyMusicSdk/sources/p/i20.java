package p;

import com.spotify.player.legacyplayer.PlayOptions$AudioStream;
import com.spotify.player.legacyplayer.PlayOptions$Operation;
import com.spotify.player.legacyplayer.PlayOptions$Trigger;
import com.spotify.player.model.AudioStream;
import com.squareup.moshi.JsonReader;

/* JADX INFO: loaded from: classes.dex */
public final class i20 extends com.squareup.moshi.f {
    public final /* synthetic */ int a;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        switch (this.a) {
            case 0:
                v03.j(jsonReader, "reader");
                String strU = jsonReader.u();
                PlayOptions$AudioStream playOptions$AudioStream = PlayOptions$AudioStream.c;
                if (strU == null) {
                    return playOptions$AudioStream;
                }
                if (strU.equals("alarm")) {
                    return PlayOptions$AudioStream.e;
                }
                strU.equals("default");
                return playOptions$AudioStream;
            case 1:
                v03.j(jsonReader, "reader");
                String strU2 = jsonReader.u();
                if (v03.b(strU2, "alarm")) {
                    return AudioStream.e;
                }
                v03.b(strU2, "default");
                return AudioStream.c;
            case 2:
                v03.j(jsonReader, "reader");
                String strU3 = jsonReader.u();
                PlayOptions$Operation playOptions$Operation = PlayOptions$Operation.c;
                if (strU3 == null) {
                    return playOptions$Operation;
                }
                switch (strU3) {
                    case "enqueue":
                        return PlayOptions$Operation.e;
                    case "push":
                        return PlayOptions$Operation.k;
                    case "replace":
                    default:
                        return playOptions$Operation;
                }
            default:
                v03.j(jsonReader, "reader");
                String strU4 = jsonReader.u();
                PlayOptions$Trigger playOptions$Trigger = PlayOptions$Trigger.c;
                if (strU4 == null) {
                    return playOptions$Trigger;
                }
                switch (strU4.hashCode()) {
                    case -1800037122:
                        if (!strU4.equals("immediately")) {
                        }
                        break;
                    case 123455259:
                        if (!strU4.equals("advanced_past_track")) {
                        }
                        break;
                    case 400349055:
                        if (!strU4.equals("advanced_past_context")) {
                        }
                        break;
                    default:
                        break;
                }
                /*  JADX ERROR: Method code generation error
                    java.lang.NullPointerException: Switch insn not found in header
                    	at java.base/java.util.Objects.requireNonNull(Objects.java:259)
                    	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:246)
                    	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:88)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                    	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:267)
                    	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:88)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                    */
                /*
                    Method dump skipped, instruction units count: 240
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: p.i20.fromJson(com.squareup.moshi.JsonReader):java.lang.Object");
            }

            @Override // com.squareup.moshi.f
            public final void toJson(com.squareup.moshi.m mVar, Object obj) {
                int i;
                switch (this.a) {
                    case 0:
                        PlayOptions$AudioStream playOptions$AudioStream = (PlayOptions$AudioStream) obj;
                        v03.j(mVar, "writer");
                        i = playOptions$AudioStream != null ? h20.a[playOptions$AudioStream.ordinal()] : -1;
                        if (i == 1) {
                            mVar.W0("alarm");
                            break;
                        } else if (i == 2) {
                            mVar.W0("default");
                            break;
                        }
                        break;
                    case 1:
                        AudioStream audioStream = (AudioStream) obj;
                        v03.j(mVar, "writer");
                        i = audioStream != null ? n20.a[audioStream.ordinal()] : -1;
                        if (i == 1) {
                            mVar.W0("alarm");
                            break;
                        } else if (i == 2) {
                            mVar.W0("default");
                            break;
                        }
                        break;
                    case 2:
                        PlayOptions$Operation playOptions$Operation = (PlayOptions$Operation) obj;
                        v03.j(mVar, "writer");
                        i = playOptions$Operation != null ? o20.a[playOptions$Operation.ordinal()] : -1;
                        if (i == 1) {
                            mVar.W0("push");
                            break;
                        } else if (i == 2) {
                            mVar.W0("enqueue");
                            break;
                        } else if (i == 3) {
                            mVar.W0("replace");
                            break;
                        }
                        break;
                    default:
                        PlayOptions$Trigger playOptions$Trigger = (PlayOptions$Trigger) obj;
                        v03.j(mVar, "writer");
                        i = playOptions$Trigger != null ? v20.a[playOptions$Trigger.ordinal()] : -1;
                        if (i == 1) {
                            mVar.W0("immediately");
                            break;
                        } else if (i == 2) {
                            mVar.W0("advanced_past_track");
                            break;
                        } else if (i == 3) {
                            mVar.W0("advanced_past_context");
                            break;
                        }
                        break;
                }
            }
        }
