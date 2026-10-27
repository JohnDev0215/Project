package com.byd.filter2.g;

import android.content.res.Resources;
import com.byd.auto_photo.R;

/* JADX INFO: loaded from: classes2.dex */
public class h extends com.byd.filter2.e.a {
    public h(Resources resources) {
        super(resources);
    }

    @Override // com.byd.filter2.e.a
    protected String E() {
        return "precision mediump float;\nvarying mediump vec2 textureCoordinate;\nuniform sampler2D vTexture;\nuniform sampler2D mapTexture;\nuniform sampler2D vignetteTexture;\nuniform float strength;\nvoid main()\n{\n   vec4 sourceColor = texture2D(vTexture, textureCoordinate.xy);\n   if(sourceColor.a == 0.0){\n      gl_FragColor = sourceColor;\n      return;\n   }\n   vec3 textureColor = sourceColor.rgb;\n   vec2 red = vec2(textureColor.r, 0.16666);\n   vec2 green = vec2(textureColor.g, 0.5);\n   vec2 blue = vec2(textureColor.b, 0.83333);\n   textureColor.rgb = vec3(\n                    texture2D(mapTexture, red).r,\n                    texture2D(mapTexture, green).g,\n                    texture2D(mapTexture, blue).b);\n   vec2 tc = (2.0 * textureCoordinate) - 1.0;\n   float d = dot(tc, tc);\n   vec2 lookup = vec2(d, textureColor.r);\n   textureColor.r = texture2D(vignetteTexture, lookup).r;\n   lookup.y = textureColor.g;\n   textureColor.g = texture2D(vignetteTexture, lookup).g;\n   lookup.y = textureColor.b;\n   textureColor.b\t= texture2D(vignetteTexture, lookup).b;\n   textureColor.rgb = mix(sourceColor.rgb, textureColor.rgb, strength);\n   gl_FragColor = vec4(textureColor, 1.0);\n}";
    }

    @Override // com.byd.filter2.e.a
    protected String[] G() {
        return null;
    }

    @Override // com.byd.filter2.e.a
    protected int[] H() {
        return new int[]{R.drawable.lomo_map, R.drawable.lomo_vignette};
    }

    @Override // com.byd.filter2.e.a
    protected String[] K() {
        return new String[]{"mapTexture", "vignetteTexture"};
    }
}
