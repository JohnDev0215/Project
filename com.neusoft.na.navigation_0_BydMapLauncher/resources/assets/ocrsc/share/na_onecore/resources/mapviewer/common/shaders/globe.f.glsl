#ifdef GL_ES
    precision highp float;     // requires highp precisions on some GPUs (see OC-14202, OC-14205)
#endif

uniform sampler2D texGlobe;
uniform float shininess;

varying vec2 oTex;
varying vec3 oNrm;
varying vec3 vsLightDir;
varying vec3 vsHalfVec;

void main(void)
{
   vec3 n = normalize(oNrm);
   float brightness = max(dot(n, normalize(vsLightDir)), 0.4);
   float specCoeff = pow(max(dot(normalize(vsHalfVec), n), 0.0), shininess);

   vec4 earthCol = texture2D(texGlobe, oTex);
   vec3 waterColor = vec3(0.12157, 0.29412, 0.4667);
   vec3 diffTexCol = earthCol.rgb;

   diffTexCol = mix(waterColor, earthCol.rgb, earthCol.a);

   vec3 finalColor = diffTexCol * brightness + diffTexCol * (1.0 - earthCol.a) * specCoeff;

   gl_FragColor = vec4( finalColor, 1.0);
}

// file:globe.f.glsl
