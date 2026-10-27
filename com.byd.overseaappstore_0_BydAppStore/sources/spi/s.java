package spi;

import com.byd.overseaappstore.module.ability.util.act.AssistActState;

/* JADX INFO: loaded from: classes.dex */
public final class s {
    public AssistActState a;

    public static class a {
        public static s a = new s(null);

        private a() {
        }
    }

    private s() {
        this.a = new AssistActState();
    }

    public /* synthetic */ s(d dVar) {
        this();
    }

    public final AssistActState a() {
        this.a.setCreate(false);
        this.a.setResume(false);
        this.a.setStop(false);
        this.a.setSaveInstanceState(false);
        this.a.setDestroy(false);
        return this.a;
    }
}
