package t9;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class i extends y9.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Pattern[][] f22541e = {new Pattern[]{null, null}, new Pattern[]{Pattern.compile("^<(?:script|pre|style)(?:\\s|>|$)", 2), Pattern.compile("</(?:script|pre|style)>", 2)}, new Pattern[]{Pattern.compile("^<!--"), Pattern.compile("-->")}, new Pattern[]{Pattern.compile("^<[?]"), Pattern.compile("\\?>")}, new Pattern[]{Pattern.compile("^<![A-Z]"), Pattern.compile(">")}, new Pattern[]{Pattern.compile("^<!\\[CDATA\\["), Pattern.compile("\\]\\]>")}, new Pattern[]{Pattern.compile("^</?(?:address|article|aside|base|basefont|blockquote|body|caption|center|col|colgroup|dd|details|dialog|dir|div|dl|dt|fieldset|figcaption|figure|footer|form|frame|frameset|h1|h2|h3|h4|h5|h6|head|header|hr|html|iframe|legend|li|link|main|menu|menuitem|nav|noframes|ol|optgroup|option|p|param|section|source|summary|table|tbody|td|tfoot|th|thead|title|tr|track|ul)(?:\\s|[/]?[>]|$)", 2), null}, new Pattern[]{Pattern.compile("^(?:<[A-Za-z][A-Za-z0-9-]*(?:\\s+[a-zA-Z_:][a-zA-Z0-9:._-]*(?:\\s*=\\s*(?:[^\"'=<>`\\x00-\\x20]+|'[^']*'|\"[^\"]*\"))?)*\\s*/?>|</[A-Za-z][A-Za-z0-9-]*\\s*[>])\\s*$", 2), null}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Pattern f22543b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w9.i f22542a = new w9.i(0);
    public boolean c = false;
    public C1.j d = new C1.j(14, false);

    public i(Pattern pattern) {
        this.f22543b = pattern;
    }

    @Override // y9.a
    public final void a(CharSequence charSequence) {
        C1.j jVar = this.d;
        int i7 = jVar.l;
        StringBuilder sb = (StringBuilder) jVar.f944m;
        if (i7 != 0) {
            sb.append('\n');
        }
        sb.append(charSequence);
        jVar.l++;
        Pattern pattern = this.f22543b;
        if (pattern == null || !pattern.matcher(charSequence).find()) {
            return;
        }
        this.c = true;
    }

    @Override // y9.a
    public final void c() {
        ((StringBuilder) this.d.f944m).toString();
        this.f22542a.getClass();
        this.d = null;
    }

    @Override // y9.a
    public final w9.a d() {
        return this.f22542a;
    }

    @Override // y9.a
    public final P2.g g(f fVar) {
        if (this.c) {
            return null;
        }
        if (fVar.f22531h && this.f22543b == null) {
            return null;
        }
        return P2.g.a(fVar.f22527b);
    }
}
