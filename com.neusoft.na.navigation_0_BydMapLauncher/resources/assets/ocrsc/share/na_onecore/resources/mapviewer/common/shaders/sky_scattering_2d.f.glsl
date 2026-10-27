
#ifdef GL_ES
    precision mediump float;
#endif

varying vec3 vNrm;

uniform sampler2D scatteringTable;

uniform vec3 uSunLightDirection;

const float PI = 3.141592654;

// Rayleigh scale height
const float Hr = 7.994;

// Mie scale height
const float Hm = 1.2;
// Earth radius
const float radiusEarth = 6360.0;
// Atmosphere radius
const float radiusAtmosphere = 6519.0;


float intersect(in vec3 start, in vec3 dir)
{
   float a = dot(dir, dir);
   float b = 2.0 * dot(dir, start);
   float c = dot(start, start) - radiusAtmosphere * radiusAtmosphere;

   float d = b * b - 4.0 * a * c;

   float t = (-b + sqrt(d)) / (2.0 * a);

   return t;
}

vec3 remapValue(in vec4 value, float scale)
{
   float r0 = value.r * 255.0 * 256.0 + 255.0 * value.g;
   r0 *= scale;
   return vec3(r0, value.b * r0, value.a * r0);
}

vec3 tonemapUncharted(in vec3 x)
{
   const float A = 0.15;
   const float B = 0.50;
   const float C = 0.10;
   const float D = 0.20;
   const float E = 0.02;
   const float F = 0.30;
   const float W = 11.2;
   return ((x*(A*x+C*B)+D*E)/(x*(A*x+B)+D*F))-E/F;

}

void main(void)
{
   vec3 viewDir = normalize(vNrm);

   vec3 betaR = vec3(5.5e-6, 13.0e-6, 22.4e-6);
   vec3 betaM = vec3(21e-6, 21e-6, 21e-6);

   float tcx = acos(viewDir.z) / PI;

   float tcy = acos(uSunLightDirection.z) / PI;    // range from 0 to 1
   tcy = 1.0 - tcy;

   vec3 sumR = remapValue(texture2D(scatteringTable, vec2(tcx, 0.5 + 0.5 * tcy)), 0.980112);
   vec3 sumM = remapValue(texture2D(scatteringTable, vec2(tcx, 0.5 * tcy)), 0.181696);

   float mu = dot(viewDir, uSunLightDirection);
   float phaseR = 3.0 / (16.0 * PI) * (1.0 + mu * mu);
   const float g = 0.98;
   float phaseM = 3.0 / (8.0 * PI) * ((1.0 - g * g) * (1.0 + mu * mu))/((2.0 + g * g) * pow(1.0 + g * g - 2.0 * g * mu, 1.5));

   vec3 sr = vec3(1.0);
   vec3 col = 20.0 * (sumR * phaseR * betaR * sr + sumM * phaseM * betaM);

   if(true)
   {
      col /= (col + vec3(1.0));
      col = pow(col, vec3(1.0 / 2.2));
   }
   else
   {
      float ExposureBias = 2.0;
      vec3 curr = tonemapUncharted(ExposureBias * col);
      vec3 whiteScale = 1.0 / tonemapUncharted(vec3(11.2));
      vec3 color = curr * whiteScale;
      col = pow(color, vec3(1.0 / 2.2));
   }

   gl_FragColor = vec4(col, 1.0);
}
// file:sky_scattering_2d.f.glsl
