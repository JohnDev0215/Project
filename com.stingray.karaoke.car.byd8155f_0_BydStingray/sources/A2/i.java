package A2;

import android.opengl.GLES20;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final float[] f324i = {1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final float[] f325j = {1.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final float[] f326k = {0.5f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f327a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f328b;
    public W2.j c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f329e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f330f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f331g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f332h;

    public static boolean b(h hVar) {
        g[] gVarArr = hVar.f322a.f318a;
        if (gVarArr.length != 1 || gVarArr[0].f319m != 0) {
            return false;
        }
        g[] gVarArr2 = hVar.f323b.f318a;
        return gVarArr2.length == 1 && gVarArr2[0].f319m == 0;
    }

    public final void a() {
        try {
            W2.j jVar = new W2.j("uniform mat4 uMvpMatrix;\nuniform mat3 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec2 aTexCoords;\nvarying vec2 vTexCoords;\n// Standard transformation.\nvoid main() {\n  gl_Position = uMvpMatrix * aPosition;\n  vTexCoords = (uTexMatrix * vec3(aTexCoords, 1)).xy;\n}\n", "// This is required since the texture data is GL_TEXTURE_EXTERNAL_OES.\n#extension GL_OES_EGL_image_external : require\nprecision mediump float;\n// Standard texture rendering shader.\nuniform samplerExternalOES uTexture;\nvarying vec2 vTexCoords;\nvoid main() {\n  gl_FragColor = texture2D(uTexture, vTexCoords);\n}\n");
            this.c = jVar;
            this.d = GLES20.glGetUniformLocation(jVar.f9750m, "uMvpMatrix");
            this.f329e = GLES20.glGetUniformLocation(this.c.f9750m, "uTexMatrix");
            this.f330f = this.c.n("aPosition");
            this.f331g = this.c.n("aTexCoords");
            this.f332h = GLES20.glGetUniformLocation(this.c.f9750m, "uTexture");
        } catch (h2.j e10) {
            h6.n.i("ProjectionRenderer", "Failed to initialize the program", e10);
        }
    }
}
