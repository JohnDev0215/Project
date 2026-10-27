
attribute vec3 aPos;
attribute vec2 aNrm;
attribute vec2 aTex;

uniform mat4 uProjMat;
uniform mat4 uCarsorRotMat;
uniform mat4 uCarsorViewMat;

uniform vec3 uCarsorCarPos;

uniform float uCarsorScale;
uniform float uScaleFactor;

uniform vec2  uDisplaySize;
uniform mat4 uCornerRotMat;

varying vec2 vTex;

void main(void)
{
   vec3 pos2 = aPos ;

   //pos2 = vec3(aPos.xy + aNrm.xy, 0.0); //if not use screen coordi

   vec3 cornerRotVert = (uCornerRotMat * vec4(aNrm.xy * uCarsorScale * uScaleFactor,0.0, 1.0)).xyz;

   vec3 rotatedVert = (uCarsorRotMat * vec4(pos2 * uCarsorScale * uScaleFactor, 1.0)).xyz;
   vec4 viewVert = uCarsorViewMat * vec4(rotatedVert + uCarsorCarPos + cornerRotVert, 1.0);

   vec4 tp = uProjMat * vec4( viewVert.xyz, 1.0);

   //vec2 toNDC = vec2( 2.0 * tp.w) / uDisplaySize;
   //tp.xy = tp.xy + aNrm.xy * toNDC ;

   gl_Position = tp ;

   vTex = aTex;
}

// file:compass_corner.v.glsl
