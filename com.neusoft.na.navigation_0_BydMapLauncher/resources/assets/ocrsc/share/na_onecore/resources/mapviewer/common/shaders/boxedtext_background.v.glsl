uniform mat4 displayMat;

attribute vec4 aPosition;
attribute vec4 aColor;

varying vec4 vColor;
varying float vOpacity;

void main(void)
{
   vOpacity = aPosition.w;
   vec4 tp = displayMat * vec4(aPosition.xy, 0.0, 1.0);
   gl_Position = vec4(tp.xy / tp.w, aPosition.z, 1.0);

   vColor= aColor;
}

