package spi;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: CarUiText.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u1 {
    public final int a;
    public final int b;
    public final List<CharSequence> c;

    /* JADX INFO: compiled from: CarUiText.java */
    public static final class b {
        public int a;
        public int b;
        public final List<CharSequence> c;

        public b(@NonNull CharSequence charSequence) {
            this((List<CharSequence>) Collections.singletonList(charSequence));
        }

        public final u1 a() {
            return new u1(this);
        }

        public b(@NonNull List<CharSequence> list) {
            this.a = Integer.MAX_VALUE;
            this.b = Integer.MAX_VALUE;
            this.c = list;
        }
    }

    public static CharSequence a(@NonNull List<u1> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String str = "";
        for (u1 u1Var : list) {
            spannableStringBuilder.append((CharSequence) str).append(u1Var.b() == null ? " " : u1Var.b());
            str = "\n";
        }
        return spannableStringBuilder;
    }

    public final CharSequence b() {
        return this.c.get(0);
    }

    private u1(b bVar) {
        this.c = bVar.c;
        this.b = bVar.b;
        this.a = bVar.a;
    }

    @Deprecated
    public u1(@NonNull CharSequence charSequence) {
        this(charSequence, Integer.MAX_VALUE);
    }

    @Deprecated
    public u1(@NonNull CharSequence charSequence, int i) {
        this.c = Collections.singletonList(charSequence);
        this.a = i;
        this.b = Integer.MAX_VALUE;
    }

    @Deprecated
    public u1(@NonNull List<CharSequence> list, int i) {
        this.c = list;
        this.a = i;
        this.b = Integer.MAX_VALUE;
    }

    @Deprecated
    public u1(@NonNull List<CharSequence> list, int i, int i2) {
        this.c = list;
        this.a = i;
        this.b = i2;
    }
}
